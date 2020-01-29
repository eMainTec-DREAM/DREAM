package intf.dream.bee.finder.docctg.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import intf.dream.bee.finder.docctg.dao.BeeFinderDocCtgListDAO;
/**
 *  dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="beeFinderDocCtgListDAOTarget"
 * @spring.txbn id="beeFinderDocCtgListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class BeeFinderDocCtgListDAOOraImpl extends BaseJdbcDaoSupportOra implements BeeFinderDocCtgListDAO
{
	public List findDocCtgList(Map map) throws Exception
    {
		String compNo = String.valueOf(map.get("compNo"));
		String docCtgDesc = String.valueOf(map.get("docCtgDesc"));
		String docCtgNo = String.valueOf(map.get("docCtgNo"));
    	
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT											");
        query.append("        x.docctg_id AS ID							");
        query.append("       ,x.docctg_no AS NO							");
        query.append("       ,x.description AS DESCRIPTION				");
        query.append("       ,x.p_docctg_id AS PID						");
        query.append("       ,LEVEL AS LVL								");
        query.append("FROM   TADOCCTG x									");
    	query.append("WHERE  1=1										");
        query.getAndQuery("x.comp_no",compNo);
        query.getStringEqualQuery("x.docctg_no", docCtgNo);
        query.getLikeQuery("x.docctg_no||x.description", docCtgDesc);
        query.append("START WITH p_docctg_id = '0'						");
        query.append("CONNECT BY PRIOR docctg_id = p_docctg_id			");
        query.append("ORDER SIBLINGS BY x.ord_no						");
        return getJdbcTemplate().queryForList(query.toString());
    } 
}