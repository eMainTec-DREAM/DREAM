package dream.consult.program.table.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.consult.program.table.dao.TableValLovDAO;
import dream.consult.program.table.dto.TableValLovDTO;

/**
 * 테이블 LOV - List DAO implements
 * @author kim21017
 * @version $Id: TableValLovDAOOraImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="tableValLovDAOTarget"
 * @spring.txbn id="tableValLovDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class TableValLovDAOOraImpl extends BaseJdbcDaoSupportOra implements TableValLovDAO
{
	public List findList(TableValLovDTO tableValLovDTO, User user, Map<String, String> columnMap,
			Map<String, String> conditionMap) throws Exception
    { 
        QueryBuffer query = new QueryBuffer();
       
        query.append("SELECT																			");
        query.append("		x.table_id												AS table_id			");
        query.append("		,x.table_name											AS table_name		");
        query.append("		,x.description											AS description		");
        query.append("FROM TATABLE x																	");
    	query.append("WHERE  1=1																		");
    	query.append(this.getWhere(tableValLovDTO, user, columnMap, conditionMap));
        query.getOrderByQuery("x.table_id", tableValLovDTO.getOrderBy(), tableValLovDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(tableValLovDTO.getIsLoadMaxCount(), tableValLovDTO.getFirstRow()));
    } 

	private String getWhere(TableValLovDTO tableValLovDTO, User user, Map<String, String> columnMap,
			Map<String, String> conditionMap)
    {        
        QueryBuffer query = new QueryBuffer();

        query.getAndQuery("is_use", conditionMap);
        //테이블 명
        query.getLikeQuery("x.table_name", tableValLovDTO.getFilterTableName());
        //테이블 설명
        query.getLikeQuery("x.description", tableValLovDTO.getFilterTableDesc());

    	return query.toString();
    }

    public String findTotalCount(TableValLovDTO tableValLovDTO, User user, Map<String, String> columnMap,
			Map<String, String> conditionMap) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT						");
        query.append("		COUNT(1)				");
        query.append("FROM TATABLE x				");
    	query.append("WHERE  1=1					");
    	query.append(this.getWhere(tableValLovDTO, user, columnMap, conditionMap));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
}