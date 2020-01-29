package intf.dream.bee.finder.eqasmb.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import intf.dream.bee.finder.eqasmb.dao.BeeFinderEqAsmbListDAO;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="beeFinderEqAsmbListDAOTarget"
 * @spring.txbn id="beeFinderEqAsmbListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class BeeFinderEqAsmbListDAOSqlImpl extends BaseJdbcDaoSupportSql implements BeeFinderEqAsmbListDAO
{
	public List findEqAsmbList(Map map) throws Exception
    {
		String compNo = String.valueOf(map.get("compNo"));
		String fullDesc = String.valueOf(map.get("fullDesc"));
		String eqAsmbNo = String.valueOf(map.get("eqAsmbNo"));
		String equipId = String.valueOf(map.get("equipId"));
    	
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                               ");
        query.append("         x.eqasmb_id id                              ");
        query.append("         ,x.eqasmb_no no                             ");
        query.append("         ,x.full_desc description                    ");
        query.append("         ,x.p_eqasmb_id as pid                       ");
        query.append("         ,y.lvl AS LVL                               ");
        query.append("FROM TAEQASMB x,(SELECT * FROM dbo.SFAEQASMB_ALL('"+compNo+"','0')) y   ");
    	query.append("WHERE 1=1						");
    	query.append("AND x.eqasmb_id = y.eqasmb_id ");
        query.getAndQuery("x.comp_no",compNo);
    	query.getStringEqualQuery("x.is_use", "Y");
        query.getStringEqualQuery("x.eqasmb_no", eqAsmbNo);
        query.getLikeQuery("x.eqasmb_no+x.full_desc", fullDesc);
        query.getAndQuery("x.equip_id",equipId);

    	query.append("ORDER BY ISNULL(x.ord_no, '99999999')");
        return getJdbcTemplate().queryForList(query.toString());
    } 
}