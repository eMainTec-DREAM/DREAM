package intf.dream.android.online.finder.plant.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import intf.dream.android.online.finder.plant.dao.AnOnFinderPlantListDAO;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="anOnFinderPlantListDAOTarget"
 * @spring.txbn id="anOnFinderPlantListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AnOnFinderPlantListDAOSqlImpl extends BaseJdbcDaoSupportSql implements AnOnFinderPlantListDAO
{
	public List findPlantList(Map map) throws Exception
    {
		String compNo = String.valueOf(map.get("compNo"));
		String plantDesc = String.valueOf(map.get("plantDesc"));
		String plant = String.valueOf(map.get("plant"));
    	
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT											");
        query.append("         x.plant id								");
        query.append("         ,x.plant no								");
        query.append("         ,x.description description				");
        query.append("FROM TAPLANT x									");
        query.append("WHERE 1=1											");
        query.getAndQuery("x.comp_no",compNo);
        query.getAndQuery("x.is_use","Y");
        if(!"null".equals(plantDesc)){
        	query.getLikeQuery("x.plant+x.description", plantDesc);
        }
        if(!"null".equals(plant)){
        	query.getAndQuery("x.plant", plant);
        }
        
        query.append("ORDER BY x.description							");
        return getJdbcTemplate().queryForList(query.toString());
    } 

}