package dream.consult.program.table.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.consult.program.table.dao.TableColValLovDAO;
import dream.consult.program.table.dto.TableColValLovDTO;

/**
 * 테이블 Column LOV - List DAO implements
 * @author kim21017
 * @version $Id: TableColValLovDAOOraImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="tableColValLovDAOTarget"
 * @spring.txbn id="tableColValLovDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class TableColValLovDAOOraImpl extends BaseJdbcDaoSupportOra implements TableColValLovDAO
{
	public List findList(TableColValLovDTO tableColValLovDTO, User user, Map<String, String> columnMap,
			Map<String, String> conditionMap) throws Exception
    { 
        QueryBuffer query = new QueryBuffer();
       
        query.append("SELECT													");
        query.append("		x.tabcol_id						AS tabcol_id		");
        query.append("		,x.column_name					AS column_name		");
        query.append("		,x.description					AS description		");
        query.append("FROM TATABCOL x											");
    	query.append("WHERE  1=1												");
    	query.append(this.getWhere(tableColValLovDTO, user, columnMap, conditionMap));
        query.getOrderByQuery("x.tabcol_id", tableColValLovDTO.getOrderBy(), tableColValLovDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(tableColValLovDTO.getIsLoadMaxCount(), tableColValLovDTO.getFirstRow()));
    } 

	private String getWhere(TableColValLovDTO tableColValLovDTO, User user, Map<String, String> columnMap,
			Map<String, String> conditionMap)
    {        
        QueryBuffer query = new QueryBuffer();
        //테이블 id
        query.getAndQuery("table_id", conditionMap);
        //컬럼 명
        query.getLikeQuery("x.column_name", tableColValLovDTO.getFilterColName());
        //컬럼 설명
        query.getLikeQuery("x.description", tableColValLovDTO.getFilterColDesc());

    	return query.toString();
    }

    public String findTotalCount(TableColValLovDTO tableColValLovDTO, User user, Map<String, String> columnMap,
			Map<String, String> conditionMap) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT						");
        query.append("		COUNT(1)				");
        query.append("FROM TATABCOL x				");
    	query.append("WHERE  1=1					");
    	query.append(this.getWhere(tableColValLovDTO, user, columnMap, conditionMap));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
}