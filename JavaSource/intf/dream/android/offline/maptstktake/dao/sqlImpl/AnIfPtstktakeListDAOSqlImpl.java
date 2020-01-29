package intf.dream.android.offline.maptstktake.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import intf.dream.android.offline.maptstktake.dao.AnIfPtstktakeListDAO;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="anIfPtstktakeListDAOTarget"
 * @spring.txbn id="anIfPtstktakeListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AnIfPtstktakeListDAOSqlImpl extends BaseJdbcDaoSupportSql implements AnIfPtstktakeListDAO
{
    public List findStktakeList(Map map) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer(); 

    	String compNo  = String.valueOf(map.get("compNo"));
    	String wcodeId = String.valueOf(map.get("wcodeId"));
    	String deptId  = String.valueOf(map.get("deptId"));
    	String plant   = String.valueOf(map.get("plant"));
    	
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
    	QuerySqlBuffer query = new QuerySqlBuffer(); 

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
		
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	

		query.append("DECLARE @t1 TABLE(									");
		query.append("	compNo NVARCHAR(1000)								");
		query.append("	,ptStkTakeItemId NVARCHAR(1000)						");
		query.append("	,ptStkTakeListId NVARCHAR(1000)						");
		query.append("	,partId NVARCHAR(1000)								");
		query.append("	,partGrade NVARCHAR(1000)							");
		query.append("	,stockQty NVARCHAR(1000)							");
		query.append("	,realQty NVARCHAR(1000)								");
		query.append("	,gapQty NVARCHAR(1000)								");
		query.append("	,remark NVARCHAR(1000)								");
		query.append("	)													");
		query.append("INSERT INTO @t1 VALUES(?,?,?,?,?,?,?)						");
		query.append("IF EXISTS(											");
		query.append("		SELECT 1										");
		query.append("		FROM TAPTSTKTAKEITEM a, @t1 b					");
		query.append("		WHERE 	a.comp_no = b.compNo					");
    	query.append("		AND 	a.PTSTKTAKELIST_ID = b.ptStkTakeListId	");
    	query.append("		AND 	a.PART_ID = b.partId					");
    	query.append("		AND 	a.PART_GRADE = b.partGrade				");
		query.append("	)													");
		query.append("	BEGIN												");
		query.append("		UPDATE TAPTSTKTAKEITEM SET						");
		query.append("			STOCK_QTY = b.stockQty,						");
    	query.append("			REAL_QTY = b.realQty,						");
    	query.append("			GAP_QTY   = b.gapQty,						");
    	query.append("			REMARK   = b.remark							");
		query.append("		FROM TAPTSTKTAKEITEM a, @t1 b					");
		query.append("		WHERE 	a.comp_no = b.compNo					");
    	query.append("		AND 	a.PTSTKTAKELIST_ID = b.ptStkTakeListId	");
    	query.append("		AND 	a.PART_ID = b.partId					");
    	query.append("		AND 	a.PART_GRADE = b.partGrade				");
		query.append("	END													");
		query.append("ELSE													");
		query.append("	BEGIN												");
		query.append("		INSERT INTO TAPTSTKTAKEITEM						");
		query.append("			(comp_no,		PTSTKTAKEITEM_ID,			PTSTKTAKELIST_ID,	PART_ID, 	PART_GRADE,				");
    	query.append("			STOCK_QTY,	REAL_QTY,					GAP_QTY,			REMARK			)						");
		query.append("		SELECT b.compNo,	next value for SQAPTSTKTAKEITEM_ID,	b.ptStkTakeListId,	b.partId,	b.partGrade,	");
    	query.append("			b.stockQty,		b.realQty,					b.gapQty,			b.remark							");
		query.append("		FROM @t1 b										");
		query.append("END													");
		
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
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));

	}
}