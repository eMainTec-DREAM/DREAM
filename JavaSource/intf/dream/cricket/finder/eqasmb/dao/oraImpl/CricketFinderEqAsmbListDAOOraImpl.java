package intf.dream.cricket.finder.eqasmb.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import intf.dream.cricket.finder.eqasmb.dao.CricketFinderEqAsmbListDAO;
/**
 *  dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="cricketFinderEqAsmbListDAOTarget"
 * @spring.txbn id="cricketFinderEqAsmbListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class CricketFinderEqAsmbListDAOOraImpl extends BaseJdbcDaoSupportOra implements CricketFinderEqAsmbListDAO
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
        query.getLikeQuery("x.eqasmb_no||x.full_desc", fullDesc);
        query.getAndQuery("x.equip_id",equipId);

        query.append("START WITH p_eqasmb_id = '0'						");
        query.append("CONNECT BY PRIOR eqasmb_id = p_eqasmb_id			");
        query.append("ORDER SIBLINGS BY ord_no							");
        return getJdbcTemplate().queryForList(query.toString());
    } 
}