package intf.dream.bee.finder.plant.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import intf.dream.bee.finder.plant.dao.BeeFinderPlantListDAO;
/**
 *  dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="beeFinderPlantListDAOTarget"
 * @spring.txbn id="beeFinderPlantListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class BeeFinderPlantListDAOOraImpl extends BaseJdbcDaoSupportOra implements BeeFinderPlantListDAO
{
	public List findPlantList(Map map) throws Exception
    {
		String compNo = String.valueOf(map.get("compNo"));
		String plantDesc = String.valueOf(map.get("plantDesc"));
		String plant = String.valueOf(map.get("plant"));
    	
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT											");
        query.append("         x.plant id								");
        query.append("         ,x.plant no								");
        query.append("         ,x.description description				");
        query.append("FROM TAPLANT x									");
        query.append("WHERE 1=1											");
        query.getAndQuery("x.comp_no",compNo);
        query.getAndQuery("x.is_use","Y");
        if(!"null".equals(plantDesc)){
        	query.getLikeQuery("x.plant||x.description", plantDesc);
        }
        if(!"null".equals(plant)){
        	query.getAndQuery("x.plant", plant);
        }
        
        query.append("ORDER BY x.description							");
        return getJdbcTemplate().queryForList(query.toString());
    } 
}