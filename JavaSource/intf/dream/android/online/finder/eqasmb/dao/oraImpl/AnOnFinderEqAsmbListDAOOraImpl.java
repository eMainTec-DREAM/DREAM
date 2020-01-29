package intf.dream.android.online.finder.eqasmb.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import intf.dream.android.online.finder.eqasmb.dao.AnOnFinderEqAsmbListDAO;
/**
 *  dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="anOnFinderEqAsmbListDAOTarget"
 * @spring.txbn id="anOnFinderEqAsmbListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AnOnFinderEqAsmbListDAOOraImpl extends BaseJdbcDaoSupportOra implements AnOnFinderEqAsmbListDAO
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
        query.getStringEqualQuery("x.is_use", "Y");
        query.getStringEqualQuery("x.eqasmb_no", eqAsmbNo);
        query.getLikeQuery("x.eqasmb_no||x.full_desc", fullDesc);
        query.getAndQuery("x.equip_id",equipId);

        query.append("START WITH p_eqasmb_id = '0'						");
        query.append("CONNECT BY PRIOR eqasmb_id = p_eqasmb_id			");
        query.append("ORDER SIBLINGS BY case when REGEXP_INSTR(ord_no,'^[+-]?\\d*(\\.?\\d*)$') = 1 then to_number(ord_no)  else 0 end	");
        return getJdbcTemplate().queryForList(query.toString());
    } 
}