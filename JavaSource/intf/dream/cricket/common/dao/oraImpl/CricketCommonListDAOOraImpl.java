package intf.dream.cricket.common.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import intf.dream.cricket.common.dao.CricketCommonListDAO;
/**
 *  dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="cricketCommonListDAOTarget"
 * @spring.txbn id="cricketCommonListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class CricketCommonListDAOOraImpl extends BaseJdbcDaoSupportOra implements CricketCommonListDAO
{
	public List findNextVal(Map map) throws Exception
    {
		String seqName = String.valueOf(map.get("seqName"));
    	
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT " + seqName + ".nextVal AS NEXTVALUE            ");
        query.append("FROM   DUAL                                            ");
        
        return getJdbcTemplate().queryForList(query.toString());
    } 
	public List findConfigVal(Map map) throws Exception
	{
		String compNo = String.valueOf(map.get("compNo"));
		String name = String.valueOf(map.get("name"));
		
		QueryBuffer query = new QueryBuffer();
		
		query.append("SELECT value$ AS value            ");
		query.append("FROM   TACONFIG                   ");
		query.append("WHERE 1=1                         ");
		query.append("AND comp_no = ?                   ");
		query.append("AND name    = ?                   ");
		
		Object[] objects = new Object[] {
				compNo
				,name
		};
		
		
		return getJdbcTemplate().queryForList(query.toString(),objects);
	} 
    
}