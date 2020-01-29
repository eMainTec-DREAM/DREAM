package intf.dream.android.online.stock.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportSql;
import common.util.CommonUtil;
import common.util.DateUtil;
import common.util.QuerySqlBuffer;
import intf.dream.android.online.stock.dao.AnOnStockListDAO;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="anOnStockListDAOTarget"
 * @spring.txbn id="anOnStockListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AnOnStockListDAOSqlImpl extends BaseJdbcDaoSupportSql implements AnOnStockListDAO
{
	public List findStockList(Map map) throws Exception
    {
		String compNo = String.valueOf(map.get("compNo"));
		String lang = String.valueOf(map.get("lang"));
    	String wcodeId = String.valueOf(map.get("wcodeId"));
    	String partId = String.valueOf(map.get("partId"));
    	String partGrade = String.valueOf(map.get("partGrade"));
    	String partDesc = String.valueOf(map.get("partDesc"));
    	String plant = String.valueOf(map.get("plant"));
    	
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT a.comp_no														AS COMP_NO			");
    	query.append("		,a.wcode_id														AS WCODE_ID			");
        query.append("		,(SELECT x.wname																	");
        query.append("			FROM TAWAREHOUSE x																");
        query.append("			WHERE x.comp_no = a.comp_no														");
        query.append("			AND x.wcode_id = a.wcode_id) 								AS WCODE_DESC		");
    	query.append("		,a.part_id														AS PART_ID			");
    	query.append("		,a.part_grade													AS PART_GRADE		");
        query.append("		,dbo.SFACODE_TO_DESC(a.part_grade,'PART_GRADE','SYS','','"+lang+"')	AS PART_GRADE_DESC	");
    	query.append("		,a.stock_qty													AS STOCK_QTY		");
    	query.append("		,a.bin_no														AS BIN_NO			");
    	query.append("		,a.unit_price													AS UNIT_PRICE		");
    	query.append("		,b.part_no														AS PART_NO			");
    	query.append("		,b.description													AS PART_DESC		");
    	query.append("		,ISNULL(b.description,'') +','+ ISNULL(b.pt_size,'')+','+ISNULL(b.model,'')+','+ISNULL(b.maker,'')	AS FULL_DESC		");
    	query.append("		,b.uom															AS UOM				");
    	query.append("FROM TAPTSTOCK a INNER JOIN TAPARTS b														");
    	query.append("ON a.comp_no = b.comp_no																	");
    	query.append("AND a.part_id = b.part_id																	");
    	query.append("WHERE 1=1																					");
    	query.getAndQuery("a.comp_no", compNo);
    	query.getLikeQuery("ISNULL(b.description,'') +','+ ISNULL(b.pt_size,'')+','+ISNULL(b.model,'')+','+ISNULL(b.maker,'')+ISNULL(b.part_no,'')", partDesc);
    	query.getAndQuery("a.part_grade", partGrade);
    	query.getAndQuery("a.part_id", partId);
        query.append("AND a.stock_qty>=0.001			");
        query.append("AND b.is_stock_control='Y'		");
        query.append("AND b.part_categ='SPPT'			");
    	query.append("AND a.wcode_id IN (SELECT x.wcode_id FROM TAWAREHOUSE x	");
    	query.append("								WHERE 1=1					");
    	query.getAndQuery("x.comp_no", compNo);
    	query.append("						AND x.plant = '"+plant+"' 			");
        query.append("  					AND x.wcode_id = '"+wcodeId+"'		");
        query.append("  					AND x.wh_categ='PART'				");
    	query.append("		)													");
    	query.append("ORDER BY b.part_no										");
    	return getJdbcTemplate().queryForList(query.toString());
    } 
	
	public int updateStock(Map map) throws Exception
    {
		QuerySqlBuffer query = new QuerySqlBuffer();
        Object[] objects;

        query.append("UPDATE TAPTSTOCK SET                                          ");
        query.append("       bin_no     = ?                                         ");
        query.append("WHERE comp_no     = ?                                         ");
        query.append("  AND part_id     = ?                                         ");
        query.append("  AND wcode_id    = ?                                         ");
        query.append("  AND part_grade  = ?                                         ");
        
        objects = new Object[] {
        		convertString(map.get("binNo"))
        		,convertString(map.get("compNo"))
        		,convertString(map.get("partId"))
        		,convertString(map.get("wcodeId"))
        		,convertString(map.get("partGrade"))
        };
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
	
	public int insertStockLog(Map map) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        Object[] objects = null;

        query.append("INSERT INTO TXPTSTOCKADJ(                  ");
        query.append("     comp_no                               ");
        query.append("    ,ptstockadj_id                         ");
        query.append("    ,upd_date                              ");
        query.append("    ,wcode                                 ");
        query.append("    ,part_no                               ");
        query.append("    ,part_grade                            ");
        query.append("    ,bin_no                                ");
        query.append("    ,new_bin_no                            ");
        query.append("    ,adj_by                                ");
        query.append("    ,user_no                               ");
        query.append(") values(                                  ");
        query.append("     ?                                     ");
        query.append("    ,next value for SQAPTSTOCKADJ_ID       ");
        query.append("    ,?                                     ");
        query.append("    ,?                                     ");
        query.append("    ,(SELECT part_no FROM TAPARTS WHERE comp_no = ? AND part_id = ?) ");
        query.append("    ,?                                     ");
        query.append("    ,(SELECT x.bin_no FROM TAPTSTOCK x WHERE x.comp_no = ? AND x.part_id = ? AND x.part_grade=  ? AND x.wcode_id = ?) ");
        query.append("    ,?                                     ");
        query.append("    ,?                                     ");
        query.append("    ,?                                     ");
        query.append(")                                          ");


        objects = new Object[] {
        		CommonUtil.convertString(map.get("compNo"))
        		,DateUtil.getDate()+" "+DateUtil.getDateTime("HH")+""+DateUtil.getDateTime("mm")
                ,CommonUtil.convertString(map.get("wcodeId"))
                ,CommonUtil.convertString(map.get("compNo"))
                ,CommonUtil.convertString(map.get("partId"))
                ,CommonUtil.convertString(map.get("partGrade"))
                ,CommonUtil.convertString(map.get("compNo"))
                ,CommonUtil.convertString(map.get("partId"))
                ,CommonUtil.convertString(map.get("partGrade"))
                ,CommonUtil.convertString(map.get("wcodeId"))
                ,CommonUtil.convertString(map.get("binNo"))
                ,CommonUtil.convertString(map.get("userId"))
                ,CommonUtil.convertString(map.get("userNo"))
        };

        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
	
}