package dream.mgr.usrrpt.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.mgr.usrrpt.dao.LovUsrRptTabAcListDAO;
import dream.mgr.usrrpt.dto.LovUsrRptTabAcListDTO;

/**
 * LOV - List DAO implements
 * @author youngjoo38
 * @version $Id: LovUsrRptTabAcListDAOSqlImpl.java,v 1.0 2017/09/12 16:15:40 youngjoo38 Exp $
 * @since 1.0
 * 
 * @spring.bean id="lovUsrRptTabAcListDAOTarget"
 * @spring.txbn id="lovUsrRptTabAcListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovUsrRptTabAcListDAOSqlImpl extends BaseJdbcDaoSupportSql implements LovUsrRptTabAcListDAO
{
	public List findList(LovUsrRptTabAcListDTO lovUsrRptTabAcListDTO, User user, Map<String, String> columnMap,
			Map<String, String> conditionMap) throws Exception
    { 
        QuerySqlBuffer query = new QuerySqlBuffer();
       
        query.append("SELECT                                                ");
        query.append("       DISTINCT(table_name)    AS Zdisplay            ");
        query.append("     , x.usrrpttab_id                                 ");
        query.append("     , x.usrrptlist_id                                ");
        query.append("     , x.table_id                                     ");
        query.append("     , x.table_name                                   ");
        query.append("FROM   TAUSRRPTTAB x                                  ");
        query.append("WHERE  1 = 1                                          ");
        
        query.append(this.getWhere(lovUsrRptTabAcListDTO, user, columnMap, conditionMap));
        query.getOrderByQuery("x.usrrpttab_id","x.table_id", lovUsrRptTabAcListDTO.getOrderBy(), lovUsrRptTabAcListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(lovUsrRptTabAcListDTO.getIsLoadMaxCount(), lovUsrRptTabAcListDTO.getFirstRow()));
    } 

	private String getWhere(LovUsrRptTabAcListDTO lovUsrRptTabAcListDTO, User user, Map<String, String> columnMap,
			Map<String, String> conditionMap)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.getAndQuery("comp_no", conditionMap);
        // 테이블명
        query.getLikeQuery("x.table_name", lovUsrRptTabAcListDTO.getTableDesc());

    	return query.toString();
    }

    public String findTotalCount(LovUsrRptTabAcListDTO lovUsrRptTabAcListDTO, User user, Map<String, String> columnMap,
			Map<String, String> conditionMap) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                        ");
        query.append("      COUNT(1)                ");
        query.append("FROM TAUSRRPTTAB x            ");
        query.append("WHERE  1=1                    ");
        query.append(this.getWhere(lovUsrRptTabAcListDTO, user, columnMap, conditionMap));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
}