package intf.dream.bee.finder.wh.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import intf.dream.bee.finder.wh.dao.BeeFinderWhListDAO;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="beeFinderWhListDAOTarget"
 * @spring.txbn id="beeFinderWhListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class BeeFinderWhListDAOSqlImpl extends BaseJdbcDaoSupportSql implements BeeFinderWhListDAO
{
	public List findWhList(Map map) throws Exception
    {
		String compNo = String.valueOf(map.get("compNo"));
		String plant = String.valueOf(map.get("plant"));
		String whCateg = String.valueOf(map.get("whCateg"));
		String whDesc = String.valueOf(map.get("whDesc"));
		String wcode = String.valueOf(map.get("wcode"));
		String whType = String.valueOf(map.get("whType"));
    	
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT											");
        query.append("         x.wcode_id id							");
        query.append("         ,x.wcode no								");
        query.append("         ,x.wname description						");
        query.append("FROM TAWAREHOUSE x								");
        query.append("WHERE 1=1											");
        query.getAndQuery("x.comp_no",compNo);
        query.append("AND x.plant='"+plant+"'							");
        query.getStringEqualQuery("x.wcode",wcode);
        query.getStringEqualQuery("x.wh_categ",whCateg);
        query.getStringEqualQuery("x.wh_type",whType);
        query.getLikeQuery("x.wcode+x.wname", whDesc);
        
        query.append("ORDER BY wcode									");
        return getJdbcTemplate().queryForList(query.toString());
    } 

}