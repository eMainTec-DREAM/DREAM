package intf.dream.android.online.maptpurreq.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportOra;
import common.util.CommonUtil;
import common.util.QueryBuffer;
import intf.dream.android.common.CommonValues;
import intf.dream.android.online.maptpurreq.dao.AnOnMaPtPurReqListDAO;
/**
 *  dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="anOnMaPtPurReqListDAOTarget"
 * @spring.txbn id="anOnMaPtPurReqListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AnOnMaPtPurReqListDAOOraImpl extends BaseJdbcDaoSupportOra implements AnOnMaPtPurReqListDAO
{
	public List findMaPtPurReqList(Map map) throws Exception
    {
		String compNo = String.valueOf(map.get("compNo"));
		String lang = String.valueOf(map.get("lang"));
    	String startDate = String.valueOf(map.get("startDate"));
    	String endDate = String.valueOf(map.get("endDate"));
    	String deptId = String.valueOf(map.get("deptId"));
    	String empId = String.valueOf(map.get("empId"));
    	String ptpnlistId = String.valueOf(map.get("ptpnlistId"));
    	String partDesc = String.valueOf(map.get("partDesc"));
    	String plant = String.valueOf(map.get("plant"));
    	
    	
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT a.ptpnlist_id 																AS ptpnlist_id  ");
        query.append("            ,a.ptpnlist_no 														AS ptpnlist_no  ");
        query.append("            ,a.PTPNLIST_STATUS 													AS status_id    ");
        query.append("            ,(SELECT SFACODE_TO_DESC(a.ptpnlist_status, 'PTPNLIST_STATUS', 'SYS', '"+ compNo +"','"+lang+"') FROM DUAL) AS status_desc  ");
        query.append("            ,a.dept_id 															AS dept_id      ");
        query.append("            ,(SELECT x.description        														");
        query.append("                    FROM tadept x        															");
        query.append("                    WHERE x.comp_no = a.comp_no        											");
        query.append("                    AND x.dept_id = a.dept_id) 									AS dept_desc    ");
        query.append("            ,a.user_id 															AS user_id      ");
        query.append("            ,(SELECT x.emp_name        															");
        query.append("                    FROM taemp x        															");
        query.append("                    WHERE x.comp_no = a.comp_no        											");
        query.append("                    AND x.emp_id = a.user_id) 									AS user_name    ");
        query.append("            ,a.rec_dept 															AS rec_dept_id  ");
        query.append("            ,(SELECT x.description        														");
        query.append("                    FROM tadept x        															");
        query.append("                    WHERE x.comp_no = a.comp_no        											");
        query.append("                    AND x.dept_id = a.rec_dept) 									AS rec_dept_desc ");
        query.append("            ,a.rec_by 															AS rec_id       ");
        query.append("            ,(SELECT x.emp_name        															");
        query.append("                    FROM taemp x        															");
        query.append("                    WHERE x.comp_no = a.comp_no        											");
        query.append("                    AND x.emp_id = a.rec_by) 										AS rec_desc     ");
        query.append("            ,a.req_date 															AS req_date     ");
        query.append("            ,a.part_id 															AS part_id      ");
        query.append("            ,CASE WHEN a.part_id is null THEN a.description										");
        query.append("            ELSE c.description END 												AS part_desc	");
        query.append("            ,CASE WHEN a.part_id is null THEN a.pt_size											");
        query.append("            ELSE c.pt_size END 													AS pt_size		");
        query.append("            ,c.model 																AS model        ");
        query.append("            ,a.rec_qty 															AS rec_qty      ");
        query.append("            ,a.remark 															AS remark       ");
        query.append("            ,equip_id 															AS eqiup_id     ");
        query.append("            ,(SELECT x.description        														");
        query.append("                FROM taequipment x        														");
        query.append("                WHERE x.comp_no = a.comp_no        												");
        query.append("                AND x.equip_id = a.equip_id) 										AS equip_desc   ");
        query.append("            ,a.USAGE 																AS USAGE        ");
        query.append("            ,a.plant 																AS plant_id     ");
        query.append("            ,(SELECT x.description        														");
        query.append("                FROM taplant x        															");
        query.append("                WHERE x.comp_no = a.comp_no        												");
        query.append("                and x.plant = a.plant) 											as plant_desc   ");
        query.append("            ,a.pr_qty 															AS pr_qty       ");
        query.append("            ,c.part_no 															AS part_no      ");
        query.append("FROM TAPTPNLIST a LEFT OUTER JOIN TAPARTS c														");
    	query.append("ON a.comp_no = c.comp_no																			");
    	query.append("AND a.part_id = c.part_id																			");
        query.append("WHERE 1 =1         																				");
    	query.getStringEqualQuery("a.comp_no", compNo);
    	query.append("AND a.plant = '"+plant+"'																			");
    	query.getAndQuery("a.ptpnlist_id", ptpnlistId);
    	query.getLikeQuery("c.full_desc", partDesc);
    	query.append("AND a.ptpnlist_status IN ("+CommonUtil.getCommaStringFromArray(CommonValues.USED_PTPN_STATUS, "''")+")	");
    	query.append("  and a.req_date >= '"+startDate+"'																");
    	query.append("  and a.req_date <=  '"+endDate+"'																");
    	query.getAndQuery("a.user_id", empId);
    	query.getDeptLevelQuery("a.dept_id", deptId, "", compNo);
    	
    	
    	return getJdbcTemplate().queryForList(query.toString());
    } 
	
	
	public int deleteMaPtPurReq(Map map) throws Exception
    {
		QueryBuffer query = new QueryBuffer();
        Object[] objects;
        
        //헤더도 삭제해야 함.

        query.append("DELETE FROM TAPTPNLIST					");
        query.append("WHERE comp_no 		  = ?				");
        query.append("AND ptpnlist_id         = ?				");
        
        objects = new Object[] {
        		convertString(map.get("compNo"))
        		,convertString(map.get("ptpnlistId"))
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
	
	public int insertMaPtPurReq(Map map) throws Exception
    {
		
		QueryBuffer query = new QueryBuffer();
		
		query.append("INSERT INTO TAPTPNLIST (                                   ");
        query.append("       COMP_NO, PTPNLIST_ID, PTPNLIST_NO, PTPNLIST_STATUS  ");
        query.append("       ,DEPT_ID,USER_ID, REQ_DATE, PART_ID                 ");
        query.append("       ,PT_SIZE, REC_QTY, REMARK, DESCRIPTION              ");
        query.append("       ,EQUIP_ID, USAGE, PLANT, REC_BY                     ");
        query.append("       ,REC_DEPT                                           ");
        query.append("       )                                                   ");
        query.append("VALUES(                                                    ");
        query.append("      ?,?,?,?,                                             ");
        query.append("      ?,?,?,?,                                             ");
        query.append("      ?,?,?,?,                                             ");
        query.append("      ?,?,?,?,                                             ");
        query.append("		?		                                             ");
        query.append("      )                                                    ");
        
        Object[] objects = new Object[] {
        		convertString(map.get("compNo"))
        		,convertString(map.get("ptpnlistId"))
        		,convertString(map.get("ptpnlistId"))
        		,convertString(map.get("status"))
        		,convertString(map.get("reqDeptId"))
        		,convertString(map.get("reqById"))
        		,convertString(map.get("reqDate"))
        		,convertString(map.get("partId"))
        		,convertString(map.get("ptSize"))
        		,convertString(map.get("recQty"))
        		,convertString(map.get("remark"))
        		,convertString(map.get("partDesc"))
        		,convertString(map.get("equipId"))
        		,convertString(map.get("usage"))
        		,convertString(map.get("plant"))
        		,convertString(map.get("recById"))
        		,convertString(map.get("recDeptId"))
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
	public int updateMaPtPurReq(Map map) throws Exception
    {
		QueryBuffer query = new QueryBuffer();
        Object[] objects;

        query.append("UPDATE TAPTPNLIST SET                       ");
        query.append("        PART_ID           = ?               ");
        query.append("       ,DESCRIPTION       = ?               ");
        query.append("       ,REC_QTY           = ?               ");
        query.append("       ,REQ_DATE          = ?               ");
        query.append("       ,PLANT             = ?               ");
        query.append("       ,DEPT_ID           = ?               ");
        query.append("       ,USER_ID           = ?               ");
        query.append("       ,EQUIP_ID          = ?               ");
        query.append("       ,USAGE             = ?               ");
        query.append("       ,REMARK            = ?               ");
        query.append("       ,REC_BY            = ?               ");
        query.append("       ,REC_DEPT          = ?               ");
        query.append("       ,PT_SIZE           = ?               ");
        query.append("WHERE COMP_NO             = ?               ");
        query.append("  AND PTPNLIST_ID         = ?               ");
        
        objects = new Object[] {
        		convertString(map.get("partId"))
        		,convertString(map.get("partDesc"))
        		,convertString(map.get("recQty"))
        		,convertString(map.get("reqDate"))
        		,convertString(map.get("plant"))
        		,convertString(map.get("reqDeptId"))
        		,convertString(map.get("reqById"))
        		,convertString(map.get("equipId"))
        		,convertString(map.get("usage"))
        		,convertString(map.get("remark"))
        		,convertString(map.get("recById"))
        		,convertString(map.get("recDeptId"))
        		,convertString(map.get("ptSize"))
        		,convertString(map.get("compNo"))
        		,convertString(map.get("ptpnlistId"))
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
	
	public int confirmMaPtPurReq(Map map) throws Exception
    {
		QueryBuffer query = new QueryBuffer();
        Object[] objects;

        query.append("UPDATE TAPTPNLIST SET                       ");
        query.append("        PART_ID           = ?               ");
        query.append("       ,DESCRIPTION       = ?               ");
        query.append("       ,REC_QTY           = ?               ");
        query.append("       ,REQ_DATE          = ?               ");
        query.append("       ,PLANT             = ?               ");
        query.append("       ,DEPT_ID           = ?               ");
        query.append("       ,USER_ID           = ?               ");
        query.append("       ,EQUIP_ID          = ?               ");
        query.append("       ,USAGE             = ?               ");
        query.append("       ,REMARK            = ?               ");
        query.append("       ,PTPNLIST_STATUS   = ?               ");
        query.append("       ,REC_BY            = ?               ");
        query.append("       ,REC_DEPT          = ?               ");
        query.append("       ,PT_SIZE           = ?               ");
        query.append("WHERE COMP_NO             = ?               ");
        query.append("  AND PTPNLIST_ID         = ?               ");
        
        objects = new Object[] {
        		convertString(map.get("partId"))
        		,convertString(map.get("partDesc"))
        		,convertString(map.get("recQty"))
        		,convertString(map.get("reqDate"))
        		,convertString(map.get("plant"))
        		,convertString(map.get("reqDeptId"))
        		,convertString(map.get("reqById"))
        		,convertString(map.get("equipId"))
        		,convertString(map.get("usage"))
        		,convertString(map.get("remark"))
        		,convertString(map.get("status"))
        		,convertString(map.get("recById"))
        		,convertString(map.get("recDeptId"))
        		,convertString(map.get("ptSize"))
        		,convertString(map.get("compNo"))
        		,convertString(map.get("ptpnlistId"))
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
	
}