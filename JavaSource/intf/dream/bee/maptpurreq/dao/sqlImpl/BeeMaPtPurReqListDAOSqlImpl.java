package intf.dream.bee.maptpurreq.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportSql;
import common.util.CommonUtil;
import common.util.QuerySqlBuffer;
import intf.dream.bee.common_value.BeeCommonValues;
import intf.dream.bee.maptpurreq.dao.BeeMaPtPurReqListDAO;
import intf.dream.bee.maptpurreq.dto.BeeMaPtPurReqCommonDTO;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="beeMaPtPurReqListDAOTarget"
 * @spring.txbn id="beeMaPtPurReqListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class BeeMaPtPurReqListDAOSqlImpl extends BaseJdbcDaoSupportSql implements BeeMaPtPurReqListDAO
{
	public List findMaPtPurReqList(BeeMaPtPurReqCommonDTO beeMaPtPurReqCommonDTO, Map map) throws Exception
    {
		String compNo = CommonUtil.convertString(String.valueOf(map.get("compNo")));
		String lang = CommonUtil.convertString(String.valueOf(map.get("lang")));
    	
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT a.ptpnlist_id 																AS ptpnlist_id  ");
        query.append("            ,a.ptpnlist_no 														AS ptpnlist_no  ");
        query.append("            ,a.PTPNLIST_STATUS 													AS status_id    ");
        query.append("            ,dbo.SFACODE_TO_DESC(a.ptpnlist_status, 'PTPNLIST_STATUS', 'SYS', '"+ compNo +"','"+lang+"') AS status_desc  ");
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
    	    	
    	query.append(this.getMaPtPurReqWhere(beeMaPtPurReqCommonDTO, map));
    	
        return getJdbcTemplate().queryForList(query.toString(beeMaPtPurReqCommonDTO.getIsLoadMaxCount(), beeMaPtPurReqCommonDTO.getFirstRow()));
    } 
	
	public List findMaPtPurReqCount(BeeMaPtPurReqCommonDTO beeMaPtPurReqCommonDTO, Map map) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT COUNT(1) AS COUNT						");
        query.append("FROM TAPTPNLIST a LEFT OUTER JOIN TAPARTS c	");
    	query.append("ON a.comp_no = c.comp_no						");
    	query.append("AND a.part_id = c.part_id						");
        query.append("WHERE 1 =1         							");
    	    	
    	query.append(this.getMaPtPurReqWhere(beeMaPtPurReqCommonDTO, map));
    	
    	return this.getJdbcTemplate().queryForList(query.toString());
    } 
	
	private String getMaPtPurReqWhere(BeeMaPtPurReqCommonDTO beeMaPtPurReqCommonDTO, Map map)
	{
		String compNo = CommonUtil.convertString(String.valueOf(map.get("compNo")));
    	String startDate = CommonUtil.convertString(String.valueOf(map.get("startDate")));
    	String endDate = CommonUtil.convertString(String.valueOf(map.get("endDate")));
    	String deptId = CommonUtil.convertString(String.valueOf(map.get("deptId")));
    	String empId = CommonUtil.convertString(String.valueOf(map.get("empId")));
    	String ptpnlistId = CommonUtil.convertString(String.valueOf(map.get("ptpnlistId")));
    	String partDesc = CommonUtil.convertString(String.valueOf(map.get("partDesc")));
    	String plant = CommonUtil.convertString(String.valueOf(map.get("plant")));
    	
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.getStringEqualQuery("a.comp_no", compNo);
        query.getStringEqualQuery("c.is_deleted", "N");
    	query.append("AND a.plant = '"+plant+"'																			");
    	query.getAndQuery("a.ptpnlist_id", ptpnlistId);
    	query.getLikeQuery("c.full_desc", partDesc);
    	query.append("AND a.ptpnlist_status IN ("+CommonUtil.getCommaStringFromArray(BeeCommonValues.USED_PTPN_STATUS, "''")+")	");
    	query.append("  and a.req_date >= '"+startDate+"'																");
    	query.append("  and a.req_date <=  '"+endDate+"'																");
    	query.getAndQuery("a.user_id", empId);
    	query.getDeptLevelQuery("a.dept_id", deptId, "", compNo);
    	
    	return query.toString();
	}
	
	public int deleteMaPtPurReq(Map map) throws Exception
    {
		QuerySqlBuffer query = new QuerySqlBuffer();
        Object[] objects;
        
        //헤더도 삭제해야 함.

        query.append("DELETE FROM TAPTPNLIST					");
        query.append("WHERE comp_no 		  = ?				");
        query.append("AND ptpnlist_id         = ?				");
        
        objects = new Object[] {
        		convertString(map.get("compNo"))
        		,convertString(map.get("ptpnlistId"))
        };
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
	
	public int insertMaPtPurReq(Map map) throws Exception
    {
		QuerySqlBuffer query = new QuerySqlBuffer();
		
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
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
	public int updateMaPtPurReq(Map map) throws Exception
    {
		QuerySqlBuffer query = new QuerySqlBuffer();
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
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
	
	public int confirmMaPtPurReq(Map map) throws Exception
    {
		QuerySqlBuffer query = new QuerySqlBuffer();
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