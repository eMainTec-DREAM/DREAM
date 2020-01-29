package dream.mgr.usage.cal.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.mgr.usage.cal.dao.LovUsageWrkAcListDAO;
import dream.mgr.usage.cal.dto.LovUsageWrkAcListDTO;

/**
 * 사용달력 LOV - List DAO implements
 * @author youngjoo38
 * @version $Id: LovUsageWrkAcListDAOSqlImpl.java,v 1.0 2017/12/14 11:09:40 youngjoo38 Exp $
 * @since 1.0
 * 
 * @spring.bean id="lovUsageWrkAcListDAOTarget"
 * @spring.txbn id="lovUsageWrkAcListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovUsageWrkAcListDAOSqlImpl extends BaseJdbcDaoSupportSql implements LovUsageWrkAcListDAO
{
	public List findList(LovUsageWrkAcListDTO lovUsageWrkAcListDTO, User user, Map<String, String> columnMap,
			Map<String, String> conditionMap) throws Exception
    { 
        QuerySqlBuffer query = new QuerySqlBuffer();
       
        query.append("SELECT                                            ");
        query.append("     lnwrklist_id             lnWrkListId         ");
        query.append("    ,comp_no                  compNo              ");
        query.append("    ,(SELECT description FROM TACOMP WHERE comp_no = a.comp_no)   compDesc        ");
        query.append("    ,lnwrklist_id             lnWrkListNo         ");
        query.append("    ,description              description         ");
        query.append("    ,(SELECT description FROM TAEQLOC WHERE comp_no = a.comp_no AND eqloc_id = a.eqloc_id)    eqlocDesc     ");
        query.append("    ,(SELECT description FROM TAWRKCALLIST WHERE comp_no = a.comp_no AND wrkcallist_id = a.wrkcallist_id) wrkCalListDesc      ");
        query.append("    ,(SELECT description FROM TAPLANT WHERE comp_no = a.comp_no AND plant = a.plant)          plantDesc     ");
        query.append("    ,a.REMARK                 remark              ");
        
        query.append("    ,a.plant                  plant             ");
        query.append("    ,a.eqloc_id               eqloc_id              ");
        query.append("    ,(SELECT b.full_desc                          ");
        query.append("       FROM TAEQLOC b                             ");
        query.append("       WHERE b.comp_no = a.comp_no                ");
        query.append("       AND b.eqloc_id = a.eqloc_id)   lineDesc    ");

        query.append("FROM TALNWRKLIST a                                ");
        query.append("WHERE  1 = 1                                      ");
        query.append(this.getWhere(lovUsageWrkAcListDTO, user, columnMap, conditionMap));
        query.getOrderByQuery("lnwrklist_id","a.description", lovUsageWrkAcListDTO.getOrderBy(), lovUsageWrkAcListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(lovUsageWrkAcListDTO.getIsLoadMaxCount(), lovUsageWrkAcListDTO.getFirstRow()));
    } 

	private String getWhere(LovUsageWrkAcListDTO lovUsageWrkAcListDTO, User user, Map<String, String> columnMap,
			Map<String, String> conditionMap)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.getAndQuery("a.comp_no", conditionMap);
        query.getAndQuery("a.lnwrk_calendar_type", "U");
        
        // 사용달력명
        query.getLikeQuery("a.description", lovUsageWrkAcListDTO.getLnWrkListDesc());
        
        // 회사명
        if(!"".equals(lovUsageWrkAcListDTO.getCompNo())) {
            query.getAndQuery("a.comp_no", lovUsageWrkAcListDTO.getCompNo());
        }
        else {
            query.append("AND a.comp_no IN(SELECT comp_no FROM TACOMP       ");
            query.append("                            WHERE 1=1     ");
            query.getLikeQuery("description", lovUsageWrkAcListDTO.getCompDesc());
            query.append("                            )     ");
        }

    	return query.toString();
    }

    public String findTotalCount(LovUsageWrkAcListDTO lovUsageWrkAcListDTO, User user, Map<String, String> columnMap,
			Map<String, String> conditionMap) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                        ");
        query.append("      COUNT(1)                ");
        query.append("FROM TALNWRKLIST a            ");
        query.append("WHERE  1=1                    ");
        query.append(this.getWhere(lovUsageWrkAcListDTO, user, columnMap, conditionMap));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
}