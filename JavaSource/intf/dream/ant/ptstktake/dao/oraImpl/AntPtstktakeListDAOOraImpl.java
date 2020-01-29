package intf.dream.ant.ptstktake.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import intf.dream.ant.ptstktake.dao.AntPtstktakeListDAO;
/**
 *  dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="antPtstktakeListDAOTarget"
 * @spring.txbn id="antPtstktakeListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AntPtstktakeListDAOOraImpl extends BaseJdbcDaoSupportOra implements AntPtstktakeListDAO
{    
    public List findStktakeList(Map map) throws Exception
    {
    	QueryBuffer query = new QueryBuffer(); 

    	String compNo = String.valueOf(map.get("compNo"));
    	String wcodeId = String.valueOf(map.get("wcodeId"));
    	String deptId = String.valueOf(map.get("deptId"));
    	String plant = String.valueOf(map.get("plant"));
    	
    	query.append("SELECT											");
        query.append("      x.COMP_NO									");
        query.append("      ,x.PTSTKTAKELIST_ID							");
        query.append("      ,x.PTSTKTAKELIST_NO							");
        query.append("      ,x.DESCRIPTION								");
        query.append("      ,x.PTSTKTAKE_STATUS							");
        query.append("      ,x.WCODE_ID									");
        query.append("      ,x.DEPT_ID									");
        query.append("      ,x.REG_DATE									");
        query.append("      ,x.REG_ID									");
        query.append("      ,x.PLAN_DATE								");
        query.append("      ,x.ACT_DATE									");
        query.append("      ,x.REMARK									");
    	query.append("FROM TAPTSTKTAKELIST x							");
        query.append("WHERE 1=1											");
    	query.getStringEqualQuery("x.comp_no", compNo);
    	query.getStringEqualQuery("x.PTSTKTAKE_STATUS", "W");
    	query.append("AND x.plant='"+plant+"'			");
    	query.getAndQuery("x.wcode_id", wcodeId);
    	query.getAndQuery("x.dept_id", deptId);
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }
    public List findStktakeItem(Map map) throws Exception
    {
    	QueryBuffer query = new QueryBuffer(); 

    	String compNo = String.valueOf(map.get("compNo"));
    	String wcodeId = String.valueOf(map.get("wcodeId"));
    	String deptId = String.valueOf(map.get("deptId"));
    	String plant = String.valueOf(map.get("plant"));
    	
    	query.append("SELECT											");
        query.append("      x.COMP_NO									");
        query.append("      ,x.PTSTKTAKEITEM_ID							");
        query.append("      ,x.PTSTKTAKELIST_ID							");
        query.append("      ,x.PART_ID									");
        query.append("      ,x.PART_GRADE								");
        query.append("      ,x.STOCK_QTY								");
        query.append("      ,x.REAL_QTY									");
        query.append("      ,x.GAP_QTY									");
        query.append("      ,x.PT_REC_ISS_TYPE							");
        query.append("      ,x.PTISSHIST_ID								");
        query.append("      ,x.PTRECHIST_ID								");
        query.append("      ,x.REMARK									");
        query.append("      ,(SELECT y.BIN_NO FROM TAPTSTOCK y			");
        query.append("        WHERE y.comp_no = x.comp_no				");
        query.append("        AND   y.part_id = x.part_id				");
        query.append("        AND   y.part_grade = x.part_grade			");
        query.append("        AND   y.wcode_id = (SELECT a.wcode_id FROM TAPTSTKTAKELIST a			");
        query.append("        						WHERE a.comp_no = x.comp_no						");
        query.append("        						AND   a.PTSTKTAKELIST_ID = x.PTSTKTAKELIST_ID)	");
        query.append("        ) bin_no									");
    	query.append("FROM TAPTSTKTAKEITEM x							");
        query.append("WHERE 1=1											");
    	query.getStringEqualQuery("x.comp_no", compNo);
    	query.append("AND x.PTSTKTAKELIST_ID IN (						");
    	query.append("		SELECT y.PTSTKTAKELIST_ID					");
    	query.append("		FROM TAPTSTKTAKELIST y						");
    	query.append("		WHERE 1=1									");
    	query.getStringEqualQuery("y.comp_no", compNo);
    	query.getStringEqualQuery("y.PTSTKTAKE_STATUS", "W");
    	query.append("AND y.plant='"+plant+"'			");
    	query.getAndQuery("y.wcode_id", wcodeId);
    	query.getAndQuery("y.dept_id", deptId);
    	query.append("		)											");
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }


    public int savePtstktakeItem(Map map) {
		
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("MERGE INTO TAPTSTKTAKEITEM a																			");
    	query.append("USING(	SELECT 	? compNo,																			");
    	query.append("					? ptStkTakeItemId,																	");
    	query.append("					? ptStkTakeListId,																	");
    	query.append("					? partId,																			");
    	query.append("					? partGrade,																		");
    	query.append("					? stockQty,																			");
    	query.append("					? realQty,																			");
    	query.append("					? gapQty,																			");
    	query.append("					? remark																			");
    	query.append("			FROM DUAL) b																				");
    	query.append("ON(	a.comp_no = b.compNo																			");
    	query.append("	AND a.PTSTKTAKELIST_ID = b.ptStkTakeListId															");
    	query.append("	AND a.PART_ID = b.partId																			");
    	query.append("	AND a.PART_GRADE = b.partGrade																		");
    	query.append("	)																									");
    	query.append("WHEN MATCHED THEN																						");
    	query.append("	UPDATE SET 	a.STOCK_QTY = b.stockQty,																");
    	query.append("				a.REAL_QTY = b.realQty,																	");
    	query.append("				a.GAP_QTY   = b.gapQty,																	");
    	query.append("				a.REMARK   = b.remark																	");
    	query.append("WHEN NOT MATCHED THEN																					");
    	query.append("	INSERT (a.comp_no,		a.PTSTKTAKEITEM_ID,			a.PTSTKTAKELIST_ID,	a.PART_ID, 	a.PART_GRADE,	");
    	query.append("			a.STOCK_QTY,	a.REAL_QTY,					a.GAP_QTY,			a.REMARK				)	");
    	query.append("	VALUES (b.compNo,		SQAPTSTKTAKEITEM_ID.nextval,b.ptStkTakeListId,	b.partId,	b.partGrade,	");
    	query.append("			b.stockQty,		b.realQty,					b.gapQty,			b.remark				)	");
    	
    	Object[] objects = new Object[] {
    			convertString(map.get("compNo")),
    			convertString(map.get("ptStkTakeItemId")),
    			convertString(map.get("ptStkTakeListId")),
    			convertString(map.get("partId")),
    			convertString(map.get("partGrade")),
    			convertString(map.get("stockQty")),
    			convertString(map.get("realQty")),
    			convertString(map.get("gapQty")),
    			convertString(map.get("remark"))
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);

	}
}