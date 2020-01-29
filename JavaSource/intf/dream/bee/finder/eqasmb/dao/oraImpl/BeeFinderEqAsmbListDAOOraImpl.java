package intf.dream.bee.finder.eqasmb.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import intf.dream.bee.finder.eqasmb.dao.BeeFinderEqAsmbListDAO;
/**
 *  dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="beeFinderEqAsmbListDAOTarget"
 * @spring.txbn id="beeFinderEqAsmbListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class BeeFinderEqAsmbListDAOOraImpl extends BaseJdbcDaoSupportOra implements BeeFinderEqAsmbListDAO
{
	public List findEqAsmbList(Map map) throws Exception
    {
		String compNo = String.valueOf(map.get("compNo"));
		String fullDesc = String.valueOf(map.get("fullDesc"));
		String eqAsmbNo = String.valueOf(map.get("eqAsmbNo"));
		String equipId = String.valueOf(map.get("equipId"));
    	
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                               ");
        query.append("         x.eqasmb_id id                              ");
        query.append("         ,x.eqasmb_no no                             ");
        query.append("         ,x.full_desc description                    ");
        query.append("         ,x.p_eqasmb_id as pid                       ");
        query.append("         ,LEVEL AS LVL                               ");
        query.append("FROM TAEQASMB x                                      ");
        query.append("WHERE 1=1                                            ");
        query.getAndQuery("x.comp_no",compNo);
        query.getStringEqualQuery("x.eqasmb_no", eqAsmbNo);
    	query.getStringEqualQuery("x.is_use", "Y");
        query.getLikeQuery("x.eqasmb_no||x.full_desc", fullDesc);
        query.getAndQuery("x.equip_id",equipId);

        query.append("START WITH p_eqasmb_id = '0'						");
        query.append("CONNECT BY PRIOR eqasmb_id = p_eqasmb_id			");
        query.append("ORDER SIBLINGS BY ord_no							");
        return getJdbcTemplate().queryForList(query.toString());
    } 
}