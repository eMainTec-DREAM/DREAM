package dream.consult.comp.time.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.consult.comp.time.dao.LovLnWrkAcListDAO;
import dream.consult.comp.time.dto.LovLnWrkAcListDTO;

/**
 * 가동달력 LOV - List DAO implements
 * @author youngjoo38
 * @version $Id: LovLnWrkAcListDAOSqlImpl.java,v 1.0 2017/12/14 11:09:40 youngjoo38 Exp $
 * @since 1.0
 * 
 * @spring.bean id="lovLnWrkAcListDAOTarget"
 * @spring.txbn id="lovLnWrkAcListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovLnWrkAcListDAOSqlImpl extends BaseJdbcDaoSupportSql implements LovLnWrkAcListDAO
{
	public List findList(LovLnWrkAcListDTO lovLnWrkAcListDTO, User user, Map<String, String> columnMap,
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
        query.append(this.getWhere(lovLnWrkAcListDTO, user, columnMap, conditionMap));
        query.getOrderByQuery("a.lnwrklist_id","a.description", lovLnWrkAcListDTO.getOrderBy(), lovLnWrkAcListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(lovLnWrkAcListDTO.getIsLoadMaxCount(), lovLnWrkAcListDTO.getFirstRow()));
    } 

	private String getWhere(LovLnWrkAcListDTO lovLnWrkAcListDTO, User user, Map<String, String> columnMap,
			Map<String, String> conditionMap)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.getAndQuery("a.comp_no", conditionMap);
        query.getAndQuery("a.lnwrk_calendar_type", "R");
        
        // 가동달력명
        query.getLikeQuery("a.description", lovLnWrkAcListDTO.getLnWrkListDesc());
        
        // 회사명
        if(!"".equals(lovLnWrkAcListDTO.getCompNo())) {
            query.getAndQuery("a.comp_no", lovLnWrkAcListDTO.getCompNo());
        }
        else {
            query.append("AND a.comp_no IN(SELECT comp_no FROM TACOMP       ");
            query.append("                            WHERE 1=1     ");
            query.getLikeQuery("description", lovLnWrkAcListDTO.getCompDesc());
            query.append("                            )     ");
        }

    	return query.toString();
    }

    public String findTotalCount(LovLnWrkAcListDTO lovLnWrkAcListDTO, User user, Map<String, String> columnMap,
			Map<String, String> conditionMap) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                        ");
        query.append("      COUNT(1)                ");
        query.append("FROM TALNWRKLIST a            ");
        query.append("WHERE  1=1                    ");
        query.append(this.getWhere(lovLnWrkAcListDTO, user, columnMap, conditionMap));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
}