package intf.dream.android.online.finder.wh.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import intf.dream.android.online.finder.wh.dao.AnOnFinderWhListDAO;
/**
 *  dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="anOnFinderWhListDAOTarget"
 * @spring.txbn id="anOnFinderWhListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AnOnFinderWhListDAOOraImpl extends BaseJdbcDaoSupportOra implements AnOnFinderWhListDAO
{
	public List findWhList(Map map) throws Exception
    {
		String compNo = String.valueOf(map.get("compNo"));
		String plant = String.valueOf(map.get("plant"));
		String whCateg = String.valueOf(map.get("whCateg"));
		String whDesc = String.valueOf(map.get("whDesc"));
		String wcode = String.valueOf(map.get("wcode"));
    	
        QueryBuffer query = new QueryBuffer();
        
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
        query.getLikeQuery("x.wcode||x.wname", whDesc);
        
        query.append("ORDER BY wcode									");
        return getJdbcTemplate().queryForList(query.toString());
    } 
}