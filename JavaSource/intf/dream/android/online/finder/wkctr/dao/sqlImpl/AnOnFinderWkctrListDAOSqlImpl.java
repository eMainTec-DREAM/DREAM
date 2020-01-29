package intf.dream.android.online.finder.wkctr.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import intf.dream.android.online.finder.wkctr.dao.AnOnFinderWkctrListDAO;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="anOnFinderWkctrListDAOTarget"
 * @spring.txbn id="anOnFinderWkctrListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AnOnFinderWkctrListDAOSqlImpl extends BaseJdbcDaoSupportSql implements AnOnFinderWkctrListDAO
{
	public List findWkctrList(Map map) throws Exception
    {
		String compNo = String.valueOf(map.get("compNo"));
		String wkCtrDesc = String.valueOf(map.get("wkCtrDesc"));
		String wkCtrNo = String.valueOf(map.get("wkCtrNo"));
    	
		QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                               ");
        query.append("         x.wkctr_id id                               ");
        query.append("         ,x.wkctr_no no                              ");
        query.append("         ,x.description                              ");
        query.append("         ,x.p_wkctr_id as pid                        ");
        query.append("         ,y.lvl AS LVL                               ");
        query.append("FROM TAWKCTR x,(SELECT * FROM dbo.SFAWKCTR_ALL('"+compNo+"','0')) y   ");
        query.append("WHERE 1=1                                            ");
        query.append("AND x.wkctr_id = y.wkctr_id                          ");
        query.getAndQuery("x.comp_no",compNo);
        query.getStringEqualQuery("x.wkctr_no",wkCtrNo);
        query.getLikeQuery("x.wkctr_no+x.description", wkCtrDesc);

    	query.append("ORDER BY ISNULL(x.ord_no, '99999999')");
        return getJdbcTemplate().queryForList(query.toString());
    } 

}