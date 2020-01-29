package intf.dream.bee.finder.ctctr.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportSql;
import common.util.QueryBuffer;
import intf.dream.bee.finder.ctctr.dao.BeeFinderCtctrListDAO;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="beeFinderCtctrListDAOTarget"
 * @spring.txbn id="beeFinderCtctrListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class BeeFinderCtctrListDAOSqlImpl extends BaseJdbcDaoSupportSql implements BeeFinderCtctrListDAO
{
	public List findCtctrList(Map map) throws Exception
    {
		String compNo = String.valueOf(map.get("compNo"));
		String ctCtrDesc = String.valueOf(map.get("ctCtrDesc"));
		String ctCtrNo = String.valueOf(map.get("ctCtrNo"));
		String inWcodeId = String.valueOf(map.get("inWcodeId"));
		String outWcodeId = String.valueOf(map.get("outWcodeId"));
    	
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                               ");
        query.append("         x.ctctr_id id                               ");
        query.append("         ,x.ctctr_no no                              ");
        query.append("         ,x.description                              ");
        query.append("FROM TACTCTR x                                       ");
        query.append("WHERE 1=1                                            ");
        query.getAndQuery("x.comp_no",compNo);
        query.getStringEqualQuery("x.ctctr_no",ctCtrNo);
        query.getStringEqualQuery("x.in_wcode_id",inWcodeId);
        query.getStringEqualQuery("x.out_wcode_id",outWcodeId);
        query.getLikeQuery("x.ctctr_no+x.description", ctCtrDesc);
        query.append("ORDER BY ctctr_no asc									");
        
        return getJdbcTemplate().queryForList(query.toString());
    } 

}