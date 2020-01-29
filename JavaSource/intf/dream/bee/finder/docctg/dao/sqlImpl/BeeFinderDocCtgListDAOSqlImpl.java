package intf.dream.bee.finder.docctg.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import intf.dream.bee.finder.docctg.dao.BeeFinderDocCtgListDAO;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="beeFinderDocCtgListDAOTarget"
 * @spring.txbn id="beeFinderDocCtgListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class BeeFinderDocCtgListDAOSqlImpl extends BaseJdbcDaoSupportSql implements BeeFinderDocCtgListDAO
{
	public List findDocCtgList(Map map) throws Exception
    {
		String compNo   = String.valueOf(map.get("compNo"));
		String docCtgDesc = String.valueOf(map.get("docCtgDesc"));
		String docCtgNo = String.valueOf(map.get("docCtgNo"));
    	
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;	");
        query.append("SELECT											");
        query.append("        x.docctg_id AS ID							");
        query.append("       ,x.docctg_no AS NO							");
        query.append("       ,x.description AS DESCRIPTION				");
        query.append("       ,x.p_docctg_id AS PID						");
        query.append("       ,y.lvl AS LVL								");
        query.append("FROM   TADOCCTG x ,(SELECT * FROM dbo.SFADOCCTG_ALL('"+compNo+"','0')) y ");
    	query.append("WHERE x.docctg_id = y.docctg_id					");
        query.getAndQuery("x.comp_no",compNo);
        query.getStringEqualQuery("x.docctg_no", docCtgNo);
        query.getLikeQuery("x.docctg_no+x.description", docCtgDesc);
    	query.append("ORDER BY ISNULL(x.ord_no, '99999999')");
        
        return getJdbcTemplate().queryForList(query.toString());
    } 
}