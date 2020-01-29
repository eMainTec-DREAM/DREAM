package intf.dream.android.online.stktake.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import intf.dream.android.online.stktake.dao.AnOnStkTakeListDAO;
/**
 *  dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="anOnStkTakeListDAOTarget"
 * @spring.txbn id="anOnStkTakeListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AnOnStkTakeListDAOOraImpl extends BaseJdbcDaoSupportOra implements AnOnStkTakeListDAO
{
	public List findStkTakeList(Map map) throws Exception
    {
		String compNo = String.valueOf(map.get("compNo"));
    	String startDate = String.valueOf(map.get("startDate"));
    	String endDate = String.valueOf(map.get("endDate"));
    	String deptId = String.valueOf(map.get("deptId"));
    	String wcodeId = String.valueOf(map.get("wcodeId"));
    	String ptStkTakeListId = String.valueOf(map.get("ptStkTakeListId"));
    	String description = String.valueOf(map.get("description"));
    	String plant = String.valueOf(map.get("plant"));
    	
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT a.comp_no														AS COMP_NO			");
    	query.append("		,a.ptstktakelist_id												AS PTSTKTAKELIST_ID	");
    	query.append("		,a.ptstktakelist_no												AS PTSTKTAKELIST_NO	");
    	query.append("		,a.description													AS DESCRIPTION		");
    	query.append("		,a.wcode_id														AS WCODE_ID			");
        query.append("		,(SELECT x.wname																	");
        query.append("			FROM TAWAREHOUSE x																");
        query.append("			WHERE x.comp_no = a.comp_no														");
        query.append("			AND x.wcode_id = a.wcode_id) 								AS WCODE_DESC		");
    	query.append("		,a.dept_id														AS DEPT_ID			");
        query.append("		,(SELECT x.description																");
        query.append("			FROM TADEPT x																	");
        query.append("			WHERE x.comp_no = a.comp_no														");
        query.append("			AND x.dept_id = a.dept_id) 									AS DEPT_DESC		");
    	query.append("		,a.plan_date													AS PLAN_DATE		");
    	query.append("FROM TAPTSTKTAKELIST a																	");
    	query.append("WHERE 1=1																					");
    	query.getStringEqualQuery("a.comp_no", compNo);
    	query.getStringEqualQuery("a.ptstktake_status", "W");
    	query.append("AND a.plant = '"+plant+"' ");
    	query.getLikeQuery("a.description", description);
    	query.getAndQuery("a.wcode_id", wcodeId);
    	query.getAndQuery("a.ptstktakelist_id", ptStkTakeListId);
    	query.append("  and a.plan_date >= '"+startDate+"'														");
    	query.append("  and a.plan_date <=  '"+endDate+"'														");
    	
    	if (!deptId.equals("null") && !"".equals(deptId))
        {
        	query.append("   and a.dept_id in ( select x.dept_id									");
        	query.append("                             from tadept x								");
        	query.append("                             where 1=1									");
        	query.getStringEqualQuery("x.comp_no", compNo);
        	query.append("                              start with x.dept_id = "+deptId+"			");
        	query.append("                              connect by prior x.dept_id = x.p_dept_id	");
        	query.append("                          )												");
        }
    	query.append("  ORDER BY a.description														");
    	return getJdbcTemplate().queryForList(query.toString());
    } 
	
	
	public int deleteStkTake(Map map) throws Exception
    {
		QueryBuffer query = new QueryBuffer();
        Object[] objects;

        query.append("DELETE FROM TAPTSTKTAKELIST				");
        query.append("WHERE comp_no 		 = ?				");
        query.append("AND ptstktakelist_id   = ?				");
        query.append("AND ptstktake_status   = ?				");
        
        objects = new Object[] {
        		convertString(map.get("compNo"))
        		,convertString(map.get("ptStkTakeListId"))
        		,"W"
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
	public List findStkTakeItemList(Map map) throws Exception
    {
		String compNo = String.valueOf(map.get("compNo"));
    	String lang = String.valueOf(map.get("lang"));
    	String ptStkTakeListId = String.valueOf(map.get("ptStkTakeListId"));
    	String ptStkTakeItemId = String.valueOf(map.get("ptStkTakeItemId"));
    	String partDesc = String.valueOf(map.get("partDesc"));
    	String partId = String.valueOf(map.get("partId"));
    	String partGrade = String.valueOf(map.get("partGrade"));
    	
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT a.ptstktakeitem_id												AS ID					");
    	query.append("		,a.comp_no														AS COMP_NO				");
    	query.append("		,a.ptstktakeitem_id												AS PTSTKTAKEITEM_ID		");
    	query.append("		,a.ptstktakelist_id												AS PTSTKTAKELIST_ID		");
    	query.append("		,a.part_id														AS PART_ID				");
    	query.append("		,b.part_no														AS PART_NO				");
    	query.append("		,b.description													AS PART_DESC			");
    	query.append("		,NVL(b.description,'') ||','|| NVL(b.pt_size,'')||','||NVL(b.model,'')||','||NVL(b.maker,'')	AS FULL_DESC			");
    	query.append("		,a.part_grade													AS PART_GRADE			");
        query.append("		,(SELECT SFACODE_TO_DESC(a.part_grade,'PART_GRADE','SYS','','"+lang+"') FROM DUAL)	AS PART_GRADE_DESC		");
    	query.append("		,a.STOCK_QTY													AS STOCK_QTY			");
    	query.append("		,a.REAL_QTY														AS REAL_QTY				");
    	query.append("		,a.GAP_QTY														AS GAP_QTY				");
    	query.append("		,a.REMARK														AS REMARK				");
    	query.append("		,(SELECT x.bin_no FROM TAPTSTOCK x WHERE x.comp_no = a.comp_no 							");
    	query.append("			AND x.part_grade = a.part_grade AND	x.wcode_id = (SELECT wcode_id					");
    	query.append("			FROM TAPTSTKTAKELIST WHERE comp_no = a.comp_no 										");
    	query.append("			AND ptstktakelist_id = a.ptstktakelist_id	 										");
    	query.append("			) AND x.part_id = a.part_id )  AS BIN_NO											");
    	query.append("		,b.UOM															AS UOM					");
    	query.append("FROM TAPTSTKTAKEITEM a INNER JOIN TAPARTS b													");
    	query.append("ON a.comp_no = b.comp_no																		");
    	query.append("AND a.part_id = b.part_id																		");
    	query.append("WHERE 1=1																						");
    	query.getStringEqualQuery("a.comp_no", compNo);
        query.getAndQuery("a.ptstktakelist_id", ptStkTakeListId);
    	query.getLikeQuery("NVL(b.description,'') ||','|| NVL(b.pt_size,'')||','||NVL(b.model,'')||','||NVL(b.maker,'')||NVL(b.part_no,'')", partDesc);
    	query.getAndQuery("a.ptstktakeitem_id", ptStkTakeItemId);
    	query.getAndQuery("a.part_id", partId);
    	query.getAndQuery("a.part_grade", partGrade);
    	query.append("  ORDER BY b.part_no																			");
    	
    	return getJdbcTemplate().queryForList(query.toString());
    } 
	public int deleteStkTakeItem(Map map) throws Exception
    {
		QueryBuffer query = new QueryBuffer();
        Object[] objects;

        query.append("DELETE FROM TAPTSTKTAKEITEM				");
        query.append("WHERE comp_no 		 = ?				");
        query.append("AND ptstktakelist_id   = ?				");
        query.append("AND ptstktakeitem_id   = ?				");
        
        objects = new Object[] {
        		convertString(map.get("compNo"))
        		,convertString(map.get("ptStkTakeListId"))
        		,convertString(map.get("ptStkTakeItemId"))
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
	
	public int insertStkTakeItem(Map map) throws Exception
    {
		QueryBuffer query = new QueryBuffer();
        Object[] objects;

        query.append("INSERT INTO TAPTSTKTAKEITEM (                                               ");
        query.append("       comp_no, ptstktakeitem_id, ptstktakelist_id, part_id                 ");
        query.append("       ,part_grade, stock_qty, real_qty, gap_qty                            ");
        query.append("       )                                                                    ");
        query.append("VALUES(                                                                     ");
        query.append("      ?,?,?,?,                                                              ");
        query.append("      ?,?,?,?                                                               ");
        query.append("      )                                                                     ");
        
        objects = new Object[] {
        		convertString(map.get("compNo"))
        		,convertString(map.get("ptStkTakeItemId"))
        		,convertString(map.get("ptStkTakeListId"))
        		,convertString(map.get("partId"))
        		,convertString(map.get("partGrade"))
        		,convertString(map.get("stockQty"))
        		,convertString(map.get("realQty"))
        		,convertString(map.get("gapQty"))
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
	public int updateStkTakeItem(Map map) throws Exception
    {
		QueryBuffer query = new QueryBuffer();
        Object[] objects;

        query.append("UPDATE TAPTSTKTAKEITEM SET                                      ");
        query.append("       part_id        = ?                                       ");
        query.append("       ,part_grade    = ?                                       ");
        query.append("       ,real_qty      = ?                                       ");
        query.append("       ,gap_qty       = ?                                       ");
        query.append("WHERE comp_no         = ?                                       ");
        query.append("  AND ptstktakeitem_id= ?                                       ");
        
        objects = new Object[] {
        		convertString(map.get("partId"))
        		,convertString(map.get("partGrade"))
        		,convertString(map.get("realQty"))
        		,convertString(map.get("gapQty"))
        		,convertString(map.get("compNo"))
        		,convertString(map.get("ptStkTakeItemId"))
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
	
}