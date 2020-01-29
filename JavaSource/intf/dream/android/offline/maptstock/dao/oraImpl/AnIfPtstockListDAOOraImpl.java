package intf.dream.android.offline.maptstock.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportOra;
import common.util.CommonUtil;
import common.util.QueryBuffer;
import intf.dream.android.offline.maptstock.dao.AnIfPtstockListDAO;
/**
 *  dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="anIfPtstockListDAOTarget"
 * @spring.txbn id="anIfPtstockListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AnIfPtstockListDAOOraImpl extends BaseJdbcDaoSupportOra implements AnIfPtstockListDAO
{
	public List findPartsList(Map map) throws Exception
    { 
    	QueryBuffer query = new QueryBuffer(); 
    	
    	query.append("SELECT 					");
    	query.append("       comp_no			");
    	query.append("      ,part_id			");
        query.append("      ,part_no			");
        query.append("      ,description		");
        query.append("      ,pt_size			");
        query.append("      ,uom				");
        query.append("      ,full_desc			");
        query.append("      ,model				");
        query.append("      ,maker				");
        query.append("      ,usage				");
        query.append("      ,last_price			");
        query.append("      ,plf_type			");
        query.append("      ,pco_type			");
        query.append("      ,seller				");
        query.append("      ,lead_time			");
        query.append("      ,is_use				");
        query.append("      ,remark				");
        query.append("      ,upd_date			");
        query.append("      ,upd_by				");
        query.append("      ,mro_type			");
        query.append("      ,kind				");
        query.append("      ,var_class			");
        query.append("      ,part_group			");
        query.append("      ,is_inpart			");
        query.append("      ,vendor_desc		");
        query.append("      ,vendor_code		");
        query.append("      ,part_categ			");
        query.append("      ,is_serial_part		");
        query.append("      ,last_gr_date		");
        query.append("      ,last_iss_date		");
        query.append("      ,out_upd_date		");
        query.append("      ,pt_abc_class		");
        query.append("FROM TAPARTS				");
        query.append("WHERE 1=1					");
        query.append("  AND is_use='Y'			");
        query.append("  AND is_stock_control='Y'		");
        query.append("  AND part_categ='SPPT'			");
        query.getStringEqualQuery("comp_no", String.valueOf(map.get("compNo")));
        query.append("  AND part_id IN ( SELECT part_id						");
        query.append("  					FROM TAPTSTOCK a INNER JOIN TAWAREHOUSE b		");
        query.append("  					ON a.comp_no = b.comp_no		");
        query.append("  					AND a.wcode_id = b.wcode_id		");
        query.append("  					WHERE 1=1		");
        query.getStringEqualQuery("a.comp_no", String.valueOf(map.get("compNo")));
        query.append("  					AND b.plant = '"+String.valueOf(map.get("plant"))+"'		");
//        query.append("  					AND a.wcode_id = '"+String.valueOf(map.get("wcodeId"))+"'	");
        query.append("  					AND b.wh_categ='PART'			");
        query.append("  			)			");
    	
    	return getJdbcTemplate().queryForList(query.toString());
    } 
    
    public List findStockList(Map map) throws Exception
    {
    	QueryBuffer query = new QueryBuffer(); 

    	String compNo = String.valueOf(map.get("compNo"));
    	String wcodeId = String.valueOf(map.get("wcodeId"));
    	
    	query.append("SELECT											");
        query.append("      x.COMP_NO									");
        query.append("      ,x.WCODE_ID									");
        query.append("      ,x.PART_ID									");
        query.append("      ,x.PART_GRADE								");
        query.append("      ,x.STOCK_QTY								");
        query.append("      ,x.BIN_NO									");
        query.append("      ,x.UNIT_PRICE								");
    	query.append("FROM TAPTSTOCK x									");
        query.append("WHERE 1=1											");
    	query.getStringEqualQuery("x.comp_no", compNo);
        query.append("  AND x.wcode_id = '"+wcodeId+"'					");
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }

    public int savePtstock(Map map) {
		
		QueryBuffer query = new QueryBuffer();
    	
    	query.append("UPDATE TAPTSTOCK SET 		");
    	query.append("		 BIN_NO 	= ?		");
    	query.append("WHERE comp_no 	= ?		");
    	query.append("  AND part_id 	= ?		");
    	query.append("  AND wcode_id 	= ?		");
    	query.append("  AND part_grade 	= ?		");
    	
    	Object[] objects = new Object[] {
    			convertString(map.get("binNo")),
    			convertString(map.get("compNo")),
    			convertString(map.get("partId")),
    			convertString(map.get("wcodeId")),
    			convertString(map.get("partGrade"))
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);

	}
    public int savePtstockLog(Map map, String ptStockAdjId) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        Object[] objects = null;

        query.append("insert into TXPTSTOCKADJ(                  ");
        query.append("     COMP_NO                               ");
        query.append("    ,PTSTOCKADJ_ID                         ");
        query.append("    ,UPD_DATE                              ");
        query.append("    ,WCODE                                 ");
        query.append("    ,PART_NO                               ");
        query.append("    ,PART_GRADE                            ");
        query.append("    ,NEW_BIN_NO                            ");
        query.append("    ,ADJ_BY                                ");
        query.append("    ,USER_NO                               ");
        query.append(") values(                                  ");
        query.append("     ?                                     ");
        query.append("    ,?                                     ");
        query.append("    ,TO_CHAR(SYSDATE,'YYYYMMDD HH24MISS')  ");
        query.append("    ,?                                     ");
        query.append("    ,(SELECT part_no FROM TAPARTS WHERE comp_no = ? AND part_id = ?) ");
        query.append("    ,?                                     ");
        query.append("    ,?                                     ");
        query.append("    ,?                                     ");
        query.append("    ,?                                     ");
        query.append(")                                          ");


        objects = new Object[] {
        		CommonUtil.convertString(map.get("compNo"))
                ,ptStockAdjId
                ,CommonUtil.convertString(map.get("wcodeId"))
                ,CommonUtil.convertString(map.get("compNo"))
                ,CommonUtil.convertString(map.get("partId"))
                ,CommonUtil.convertString(map.get("partGrade"))
                ,CommonUtil.convertString(map.get("binNo"))
                ,CommonUtil.convertString(map.get("userId"))
                ,CommonUtil.convertString(map.get("userNo"))
        };

        return getJdbcTemplate().update(query.toString(), objects);
    }
}