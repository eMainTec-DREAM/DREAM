package intf.dream.android.online.woreq.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportOra;
import common.util.CommonUtil;
import common.util.QueryBuffer;
import intf.dream.android.common.CommonValues;
import intf.dream.android.online.woreq.dao.AnOnWoReqListDAO;
/**
 *  dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="anOnWoReqListDAOTarget"
 * @spring.txbn id="anOnWoReqListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AnOnWoReqListDAOOraImpl extends BaseJdbcDaoSupportOra implements AnOnWoReqListDAO
{
	public List findWoReqList(Map map) throws Exception
    {
		String compNo = String.valueOf(map.get("compNo"));
		String lang = String.valueOf(map.get("lang"));
    	String startDate = String.valueOf(map.get("startDate"));
    	String endDate = String.valueOf(map.get("endDate"));
    	String deptId = String.valueOf(map.get("deptId"));
    	String wkctrId = String.valueOf(map.get("wkctrId"));
    	String eqctgType = String.valueOf(map.get("eqctgType"));
    	String eqlocId = String.valueOf(map.get("eqlocId"));
    	String woReqId = String.valueOf(map.get("woReqId"));
    	String equipDesc = String.valueOf(map.get("equipDesc"));
    	String empId = String.valueOf(map.get("empId"));
    	String plant = String.valueOf(map.get("plant"));
    	String isApproval = String.valueOf(map.get("isApproval"));
    	String equipId = String.valueOf(map.get("equipId"));
    	
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                            ");
        query.append("         a.COMP_NO COMPNO                                                         ");
        query.append("         ,a.WOREQ_ID WOREQID                                                      ");
        query.append("         ,a.WOREQ_NO WOREQNO                                                      ");
        query.append("         ,a.DESCRIPTION DESCRIPTION                                               ");
        query.append("         ,a.EQLOC_ID EQLOCID                                                      ");
        query.append("         ,nvl((SELECT x.full_desc FROM TAEQLOC x                                  ");
        query.append("                  WHERE x.comp_no = a.comp_no                                     ");
        query.append("                  AND   x.eqloc_id = a.eqloc_id                                   ");
        query.append("                  ),'') EQLOCDESC                                                 ");
        query.append("         ,a.EQUIP_ID EQUIPID                                                      ");
        query.append("         ,nvl((SELECT x.item_no FROM TAEQUIPMENT x                                ");
        query.append("                  WHERE x.comp_no = a.comp_no                                     ");
        query.append("                  AND   x.equip_id = a.equip_id                                   ");
        query.append("                  ),'') ITEMNO                                                    ");
        query.append("         ,nvl((SELECT x.description FROM TAEQUIPMENT x                            ");
        query.append("                  WHERE x.comp_no = a.comp_no                                     ");
        query.append("                  AND   x.equip_id = a.equip_id                                   ");
        query.append("                  ),'') EQUIPDESC                                                 ");
        query.append("         ,a.REQ_DATE REQDATE                                                      ");
        query.append("         ,a.REQ_DEPT_ID REQDEPTID                                                 ");
        query.append("         ,nvl((SELECT x.description FROM TADEPT x                                 ");
        query.append("                  WHERE x.comp_no = a.comp_no                                     ");
        query.append("                  AND   x.dept_id = a.req_dept_id                                 ");
        query.append("                  ),'') REQDEPTDESC                                               ");
        query.append("         ,a.REQ_EMP_ID REQEMPID                                                   ");
        query.append("         ,nvl((SELECT x.emp_name FROM TAEMP x                                     ");
        query.append("                  WHERE x.comp_no = a.comp_no                                     ");
        query.append("                  AND   x.emp_id = a.req_emp_id                                   ");
        query.append("                  ),'') REQEMPDESC                                                ");
        query.append("         ,a.WOREQ_STATUS WOREQSTATUS                                              ");
        query.append("         ,(SELECT                                                                 ");
        query.append("                  (SELECT y.key_name FROM TALANG y                                ");
        query.append("                  WHERE y.key_type = x.key_type                                   ");
        query.append("                  AND   y.key_no = x.key_no                                       ");
        query.append("                  AND   y.lang = '"+lang+"'                                       ");
        query.append("                  )                                                               ");
        query.append("              FROM TACDSYSD x                                                     ");
        query.append("              WHERE x.list_type='WOREQ_STATUS'                                    ");
        query.append("              AND x.cdsysd_no = a.woreq_status) WOREQSTATUSDESC                   ");
        query.append("         ,a.REQ_PHONE REQPHONE                                                    ");
        query.append("         ,a.REC_DEPT_ID RECDEPTID                                                 ");
        query.append("         ,nvl((SELECT x.description FROM TADEPT x                                 ");
        query.append("                  WHERE x.comp_no = a.comp_no                                     ");
        query.append("                  AND   x.dept_id = a.rec_dept_id                                 ");
        query.append("                  ),'') RECDEPTDESC                                               ");
        query.append("         ,a.REC_WKCTR_ID RECWKCTRID                                               ");
        query.append("         ,nvl((SELECT x.full_desc FROM TAWKCTR x                                  ");
        query.append("                  WHERE x.comp_no = a.comp_no                                     ");
        query.append("                  AND   x.wkctr_id = a.rec_wkctr_id                               ");
        query.append("                  ),'') RECWKCTRDESC                                              ");
        query.append("         ,a.REC_EMP_ID RECEMPID                                                   ");
        query.append("         ,nvl((SELECT x.emp_name FROM TAEMP x                                     ");
        query.append("                  WHERE x.comp_no = a.comp_no                                     ");
        query.append("                  AND   x.emp_id = a.rec_emp_id                                   ");
        query.append("                  ),'') RECEMPDESC                                                ");
        query.append("         ,a.REQ_PRIORITY REQPRIORITY                                              ");
        query.append("         ,(SELECT                                                                 ");
        query.append("                  (SELECT y.key_name FROM TALANG y                                ");
        query.append("                  WHERE y.key_type = x.key_type                                   ");
        query.append("                  AND   y.key_no = x.key_no                                       ");
        query.append("                  AND   y.lang = '"+lang+"'                                       ");
        query.append("                  )                                                               ");
        query.append("              FROM TACDSYSD x                                                     ");
        query.append("              WHERE x.list_type='REQ_PRIORITY'                                    ");
        query.append("              AND x.cdsysd_no = a.req_priority) REQPRIORITYDESC                   ");
        query.append("         ,a.REQ_COM_DATE REQCOMDATE                                               ");
        query.append("         ,a.REQUEST REQUEST                                                       ");
    	query.append("		,(SELECT count(*) FROM tadocdata WHERE doc_id IN (SELECT x.doc_id FROM TAOBJDOC x WHERE x.comp_no = a.comp_no 												");
    	query.append("			AND x.object_id = a.woreq_id AND object_type='WOREQ'))	AS PHOTOCNT		");
    	query.append("         ,a.EQDN_START_DATE EQDNSTARTDATE                                         ");
    	query.append("         ,a.EQDN_START_TIME EQDNSTARTTIME                                         ");
        query.append("FROM TAWOREQ a                                                                    ");
        query.append("WHERE 1=1                                                                         ");
        query.getAndQuery("a.comp_no",compNo);
        query.getAndQuery("a.woreq_id",woReqId);
        
        if(!"Y".equals(isApproval)){
        	query.getStringEqualQuery("a.woreq_status", "WRT");
        	query.append("  AND a.req_date >= '"+startDate+"'	");
        	query.append("  AND a.req_date <=  '"+endDate+"'	");
        }
        
        if(!"null".equals(equipId) &&!"".equals(equipId)){
        	query.getAndQuery("a.equip_id", equipId);
        }
        
        query.append("AND a.plant = '"+plant+"' ");
    	query.getAndQuery("a.req_emp_id", empId);
        query.getDeptLevelQuery("a.req_dept_id", deptId, "", compNo);
        
        if (!"null".equals(eqctgType) && !"".equals(eqctgType))
        {
    		query.append("AND a.equip_id IN (						");
    		query.append("					SELECT x.equip_id		");
    		query.append("					FROM TAEQUIPMENT x		");
    		query.append("					WHERE 1=1				");
    		query.getStringEqualQuery("x.comp_no", compNo);
    		query.getStringEqualQuery("x.eqctg_type", eqctgType);
    		query.append("			)								");
        }
        if (!"null".equals(eqlocId) && !"".equals(eqlocId))
        {
    		query.append("AND a.equip_id IN (						");
    		query.append("					SELECT x.equip_id		");
    		query.append("					FROM TAEQUIPMENT x		");
    		query.append("					WHERE 1=1				");
    		query.getStringEqualQuery("x.comp_no", compNo);
    		query.getEqLocLevelQuery("x.eqloc_id", eqlocId, "", compNo);
    		query.append("			)								");
        }

        if(!"null".equals(equipDesc) &&!"".equals(equipDesc)){
            query.append("AND equip_id IN (                                                             ");
            query.append("  SELECT aa.equip_id                                                          ");
            query.append("  FROM TAEQUIPMENT aa                                                         ");
            query.append("  WHERE 1=1                                                                   ");
            query.getAndQuery("aa.comp_no",compNo);
            query.getStringEqualQuery("aa.is_deleted","N");
            query.getStringEqualQuery("aa.is_last_version","Y");
            query.append("AND (aa.item_no like '%"+equipDesc+"%' OR aa.description like '%"+equipDesc+"%')      ");
            query.append("  )                                                                           ");
        }
        
        query.append("ORDER BY a.req_date                                                               ");
    	
        return getJdbcTemplate().queryForList(query.toString());
    	
    } 
	
	public int deleteWoReq(Map map) throws Exception
    {
		QueryBuffer query = new QueryBuffer();
        Object[] objects;

        query.append("DELETE FROM TAWOREQ			");
        query.append("WHERE comp_no = ?				");
        query.append("AND woreq_id   = ?			");
        
        objects = new Object[] {
        		convertString(map.get("compNo"))
        		,convertString(map.get("woReqId"))
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
	
	public int insertWoReq(Map map) throws Exception
    {
		QueryBuffer query = new QueryBuffer();
        Object[] objects;

        query.append("INSERT INTO TAWOREQ (                                                     ");
        query.append("     COMP_NO, WOREQ_ID, WOREQ_NO, DESCRIPTION, EQLOC_ID                   ");
        query.append("     , EQUIP_ID, WOREQ_STATUS, REQ_DATE, REQ_DEPT_ID, REQ_EMP_ID          ");
        query.append("     , REQ_PHONE, REQ_EMAIL, REQUEST, REC_DEPT_ID, REC_WKCTR_ID           ");
        query.append("     , REC_EMP_ID, REQ_TIME, REQ_PRIORITY, REQ_COM_DATE, REVIEW           ");
        query.append("     , PLANT, EQDN_START_DATE, EQDN_START_TIME,MO_CD,MO_DESC              ");
        query.append("     , WOREQ_CHANNEL                                                      ");
        query.append("      )                                                                   ");
        query.append("VALUES (                                                                  ");
        query.append("      ?,?,?,?,?                                                           ");
        query.append("     ,?,?,?,?,?                                                           ");
        query.append("     ,?,?,?,?,?                                                           ");
        query.append("     ,?,?,?,?,?                                                           ");
        query.append("     ,?,?,?,?,?                                                           ");
        query.append("     ,?                                                                   ");
        query.append("         )                                                                ");
        
        objects = new Object[] {
        		convertString(map.get("compNo"))
        		,convertString(map.get("woReqId"))
        		,convertString(map.get("woReqId"))
        		,convertString(map.get("woReqDesc"))
        		,convertString(map.get("eqLocId"))
        		,convertString(map.get("equipId"))
        		,"Y".equals(convertString(map.get("isReq")))?"REQ":"WRT"
        		,convertString(map.get("reqDate"))
        		,convertString(map.get("reqDeptId"))
        		,convertString(map.get("reqEmpId"))
        		,convertString(map.get("reqPhone"))
        		,"" //email
        		,convertString(map.get("request"))
        		,convertString(map.get("recDeptId"))
        		,convertString(map.get("recWkCtrId"))
        		,convertString(map.get("recEmpId"))
        		,""//req_time
        		,convertString(map.get("reqPriority"))
        		,convertString(map.get("reqComDate"))
        		,""//review
        		,convertString(map.get("plant"))
        		,convertString(map.get("eqdnStartDate"))
        		,convertString(map.get("eqdnStartTime"))
        		,convertString(map.get("moId"))
        		,convertString(map.get("moDesc"))
        		,"DREAMO"
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
	
	public int updateWoReq(Map map) throws Exception
    {
		QueryBuffer query = new QueryBuffer();
        Object[] objects;

        query.append("UPDATE TAWOREQ SET                                          ");
        query.append("       description           = ?                            ");
        query.append("       ,equip_id             = ?                            ");
        query.append("       ,eqloc_id             = ?                            ");
        query.append("       ,req_date             = ?                            ");
        query.append("       ,req_emp_id           = ?                            ");
        query.append("       ,req_dept_id          = ?                            ");
        query.append("       ,req_phone            = ?                            ");
        query.append("       ,rec_dept_id          = ?                            ");
        query.append("       ,rec_wkctr_id         = ?                            ");
        query.append("       ,rec_emp_id           = ?                            ");
        query.append("       ,req_priority         = ?                            ");
        query.append("       ,req_com_date         = ?                            ");
        query.append("       ,request              = ?                            ");
        query.append("       ,woreq_status         = ?                            ");
        query.append("WHERE comp_no     = ?                                       ");
        query.append("  AND woreq_id    = ?                                       ");
        
        objects = new Object[] {
        		convertString(map.get("woReqDesc"))
        		,convertString(map.get("equipId"))
        		,convertString(map.get("eqLocId"))
        		,convertString(map.get("reqDate"))
        		,convertString(map.get("reqEmpId"))
        		,convertString(map.get("reqDeptId"))
        		,convertString(map.get("reqPhone"))
        		,convertString(map.get("recDeptId"))
        		,convertString(map.get("recWkCtrId"))
        		,convertString(map.get("recEmpId"))
        		,convertString(map.get("reqPriority"))
        		,convertString(map.get("reqComDate"))
        		,convertString(map.get("request"))
        		,convertString(map.get("woReqStatus"))
        		,convertString(map.get("compNo"))
        		,convertString(map.get("woReqId"))
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
	public int updateWoReqStatus(Map map) throws Exception
    {
		QueryBuffer query = new QueryBuffer();
        Object[] objects;

        query.append("UPDATE TAWOREQ SET                                          ");
        query.append("       woreq_status          = ?                            ");
        query.append("WHERE comp_no     = ?                                       ");
        query.append("  AND woreq_id    = ?                                       ");
        
        objects = new Object[] {
                convertString(map.get("woReqStatus"))
        		,convertString(map.get("compNo"))
        		,convertString(map.get("woReqId"))
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
	public List findWoResList(Map map) throws Exception
    {
		String compNo = String.valueOf(map.get("compNo"));
		String lang = String.valueOf(map.get("lang"));
    	String startDate = String.valueOf(map.get("startDate"));
    	String endDate = String.valueOf(map.get("endDate"));
    	String deptId = String.valueOf(map.get("deptId"));
    	String wkctrId = String.valueOf(map.get("wkctrId"));
    	String eqctgType = String.valueOf(map.get("eqctgType"));
    	String eqlocId = String.valueOf(map.get("eqlocId"));
    	String woReqId = String.valueOf(map.get("woReqId"));
    	String equipDesc = String.valueOf(map.get("equipDesc"));
    	String empId = String.valueOf(map.get("empId"));
    	String plant = String.valueOf(map.get("plant"));
    	
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                            ");
        query.append("         a.COMP_NO COMPNO                                                         ");
        query.append("         ,a.WOREQ_ID WOREQID                                                      ");
        query.append("         ,a.WOREQ_NO WOREQNO                                                      ");
        query.append("         ,a.DESCRIPTION DESCRIPTION                                               ");
        query.append("         ,a.EQLOC_ID EQLOCID                                                      ");
        query.append("         ,nvl((SELECT x.full_desc FROM TAEQLOC x                                  ");
        query.append("                  WHERE x.comp_no = a.comp_no                                     ");
        query.append("                  AND   x.eqloc_id = a.eqloc_id                                   ");
        query.append("                  ),'') EQLOCDESC                                                 ");
        query.append("         ,a.EQUIP_ID EQUIPID                                                      ");
        query.append("         ,nvl((SELECT x.item_no  FROM TAEQUIPMENT x                               ");
        query.append("                  WHERE x.comp_no = a.comp_no                                     ");
        query.append("                  AND   x.equip_id = a.equip_id                                   ");
        query.append("                  ),'') ITEMNO                                                    ");
        query.append("         ,nvl((SELECT x.description FROM TAEQUIPMENT x                            ");
        query.append("                  WHERE x.comp_no = a.comp_no                                     ");
        query.append("                  AND   x.equip_id = a.equip_id                                   ");
        query.append("                  ),'') EQUIPDESC                                                 ");
        query.append("         ,a.REQ_DATE REQDATE                                                      ");
        query.append("         ,a.REQ_DEPT_ID REQDEPTID                                                 ");
        query.append("         ,nvl((SELECT x.description FROM TADEPT x                                 ");
        query.append("                  WHERE x.comp_no = a.comp_no                                     ");
        query.append("                  AND   x.dept_id = a.req_dept_id                                 ");
        query.append("                  ),'') REQDEPTDESC                                               ");
        query.append("         ,a.REQ_EMP_ID REQEMPID                                                   ");
        query.append("         ,nvl((SELECT x.emp_name FROM TAEMP x                                     ");
        query.append("                  WHERE x.comp_no = a.comp_no                                     ");
        query.append("                  AND   x.emp_id = a.req_emp_id                                   ");
        query.append("                  ),'') REQEMPDESC                                                ");
        query.append("         ,a.WOREQ_STATUS WOREQSTATUS                                              ");
        query.append("         ,(SELECT                                                                 ");
        query.append("                  (SELECT y.key_name FROM TALANG y                                ");
        query.append("                  WHERE y.key_type = x.key_type                                   ");
        query.append("                  AND   y.key_no = x.key_no                                       ");
        query.append("                  AND   y.lang = '"+lang+"'                                       ");
        query.append("                  )                                                               ");
        query.append("              FROM TACDSYSD x                                                     ");
        query.append("              WHERE x.list_type='WOREQ_STATUS'                                    ");
        query.append("              AND x.cdsysd_no = a.woreq_status) WOREQSTATUSDESC                   ");
        query.append("         ,a.REQ_PHONE REQPHONE                                                    ");
        query.append("         ,a.REC_DEPT_ID RECDEPTID                                                 ");
        query.append("         ,nvl((SELECT x.description FROM TADEPT x                                 ");
        query.append("                  WHERE x.comp_no = a.comp_no                                     ");
        query.append("                  AND   x.dept_id = a.rec_dept_id                                 ");
        query.append("                  ),'') RECDEPTDESC                                               ");
        query.append("         ,a.REC_WKCTR_ID RECWKCTRID                                               ");
        query.append("         ,nvl((SELECT x.full_desc FROM TAWKCTR x                                  ");
        query.append("                  WHERE x.comp_no = a.comp_no                                     ");
        query.append("                  AND   x.wkctr_id = a.rec_wkctr_id                               ");
        query.append("                  ),'') RECWKCTRDESC                                              ");
        query.append("         ,a.REC_EMP_ID RECEMPID                                                   ");
        query.append("         ,nvl((SELECT x.emp_name FROM TAEMP x                                     ");
        query.append("                  WHERE x.comp_no = a.comp_no                                     ");
        query.append("                  AND   x.emp_id = a.rec_emp_id                                   ");
        query.append("                  ),'') RECEMPDESC                                                ");
        query.append("         ,a.REQ_PRIORITY REQPRIORITY                                              ");
        query.append("         ,(SELECT                                                                 ");
        query.append("                  (SELECT y.key_name FROM TALANG y                                ");
        query.append("                  WHERE y.key_type = x.key_type                                   ");
        query.append("                  AND   y.key_no = x.key_no                                       ");
        query.append("                  AND   y.lang = '"+lang+"'                                       ");
        query.append("                  )                                                               ");
        query.append("              FROM TACDSYSD x                                                     ");
        query.append("              WHERE x.list_type='REQ_PRIORITY'                                    ");
        query.append("              AND x.cdsysd_no = a.req_priority) REQPRIORITYDESC                   ");
        query.append("         ,a.REQ_COM_DATE REQCOMDATE                                               ");
        query.append("         ,a.REQUEST REQUEST                                                       ");
    	query.append("		,(SELECT count(*) FROM tadocdata WHERE doc_id IN (SELECT x.doc_id FROM TAOBJDOC x WHERE x.comp_no = a.comp_no 				");
    	query.append("			AND x.object_id = a.woreq_id AND object_type='WOREQ'))	AS PHOTOCNT		");
    	query.append("		,(SELECT count(1) FROM TAWOREQRES x WHERE x.comp_no = a.comp_no 			");
    	query.append("			AND x.woreq_id = a.woreq_id )	AS REQRESCNT							");
    	query.append("         ,a.EQDN_START_DATE EQDNSTARTDATE                                         ");
    	query.append("         ,a.EQDN_START_TIME EQDNSTARTTIME                                         ");
    	query.append("         ,a.MO_CD MOID        													");
    	query.append("         ,a.MO_DESC MODESC               											");
    	query.append("         ,(SELECT x.description        											");
    	query.append("         FROM tafailure x        													");
    	query.append("         WHERE x.comp_no = a.comp_no        										");
    	query.append("         AND x.failure_id = a.mo_cd) MO        									");
        query.append("FROM TAWOREQ a                                                                    ");
        query.append("WHERE 1=1                                                                         ");
        query.getAndQuery("a.comp_no",compNo);
    	query.append("AND a.plant = '"+plant+"' ");
    	query.append("  AND a.woreq_status IN ("+CommonUtil.getCommaStringFromArray(CommonValues.USED_WORES_STATES, "''")+") ");
    	query.append("  AND a.req_date >= '"+startDate+"'	");
    	query.append("  AND a.req_date <=  '"+endDate+"'	");
        query.getAndQuery("a.woreq_id",woReqId);

        
        query.getAndQuery("a.rec_emp_id", empId);
        query.getWkCtrLevelQuery("a.rec_wkctr_id", wkctrId, "", compNo);
        query.getDeptLevelQuery("a.rec_dept_id", deptId, "", compNo);
        
        if (!"null".equals(eqctgType) && !"".equals(eqctgType))
        {
    		query.append("AND a.equip_id IN (						");
    		query.append("					SELECT x.equip_id		");
    		query.append("					FROM TAEQUIPMENT x		");
    		query.append("					WHERE 1=1				");
    		query.getStringEqualQuery("x.comp_no", compNo);
    		query.getStringEqualQuery("x.eqctg_type", eqctgType);
    		query.append("			)								");
        }
        if (!"null".equals(eqlocId) && !"".equals(eqlocId))
        {
    		query.append("AND a.equip_id IN (						");
    		query.append("					SELECT x.equip_id		");
    		query.append("					FROM TAEQUIPMENT x		");
    		query.append("					WHERE 1=1				");
    		query.getStringEqualQuery("x.comp_no", compNo);
    		query.getEqLocLevelQuery("x.eqloc_id", eqlocId, "", compNo);
    		query.append("			)								");
        }

        if(!"".equals(equipDesc)){
            query.append("AND equip_id IN (                                                             ");
            query.append("  SELECT aa.equip_id                                                          ");
            query.append("  FROM TAEQUIPMENT aa                                                         ");
            query.append("  WHERE 1=1                                                                   ");
            query.getAndQuery("aa.comp_no",compNo);
            query.getStringEqualQuery("aa.is_deleted","N");
            query.getStringEqualQuery("aa.is_last_version","Y");
            query.append("AND (aa.item_no like '%"+equipDesc+"%' OR aa.description like '%"+equipDesc+"%')      ");
            query.append("  )                                                                           ");
        }
        query.append("ORDER BY a.req_date                                                               ");
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }
	
    public int updateWoRes(Map map) throws Exception
    {
		QueryBuffer query = new QueryBuffer();
        Object[] objects;

        query.append("UPDATE TAWOREQ SET                                           ");
        query.append("       rec_dept_id            = ?                            ");
        query.append("       ,rec_wkctr_id          = ?                            ");
        query.append("       ,rec_emp_id            = ?                            ");
        query.append("       ,equip_id              = ?                            ");
        query.append("WHERE comp_no     = ?                                        ");
        query.append("  AND woreq_id    = ?                                        ");
        
        objects = new Object[] {
              convertString(map.get("recDeptId"))
              ,convertString(map.get("recWkCtrId"))
              ,convertString(map.get("recEmpId"))
              ,convertString(map.get("equipId"))
              ,convertString(map.get("compNo"))
              ,convertString(map.get("woReqId"))
        };
        
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
	public int deleteWoRes(Map map) throws Exception
    {
		QueryBuffer query = new QueryBuffer();

        query.append("DELETE FROM TAWOREQ			");
        query.append("WHERE 1=1						");
        query.getAndQuery("comp_no", convertString(map.get("compNo")));
        query.getAndQuery("woreq_id", convertString(map.get("woReqId")));
        
        return getJdbcTemplate().update(query.toString());
    }
	
    public List findWoReqResList(Map map) throws Exception
    {
		String compNo 		= String.valueOf(map.get("compNo"));
    	String lang 		= String.valueOf(map.get("lang"));
    	String woReqId 		= String.valueOf(map.get("woReqId"));
    	
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                            ");
        query.append("         a.COMP_NO COMPNO                                         ");
        query.append("         ,a.WOREQRES_ID WOREQRESID                                ");
        query.append("         ,a.WOREQ_ID WOREQ_ID                                     ");
        query.append("         ,a.RES_DATE RES_DATE                                     ");
        query.append("         ,a.WORES_STATUS WORESSTATUS                              ");
        query.append("         ,(SELECT                                                 ");
        query.append("                  (SELECT y.key_name FROM TALANG y                ");
        query.append("                  WHERE y.key_type = x.key_type                   ");
        query.append("                  AND   y.key_no = x.key_no                       ");
        query.append("                  AND   y.lang = '"+lang+"'                       ");
        query.append("                  )                                               ");
        query.append("              FROM TACDSYSD x                                     ");
        query.append("              WHERE x.list_type='WORES_STATUS'                    ");
        query.append("              AND x.cdsysd_no = a.WORES_STATUS) WORESSTATUSDESC   ");
        query.append("         ,a.DEPT_ID DEPTID                                        ");
        query.append("         ,nvl((SELECT y.description FROM TADEPT y                 ");
        query.append("                  WHERE y.comp_no = a.comp_no                     ");
        query.append("                  AND   y.dept_id = a.dept_id                     ");
        query.append("                  ),'') DEPTDESC                                  ");
        query.append("         ,a.EMP_ID EMPID                                          ");
        query.append("         ,nvl((SELECT y.emp_name FROM TAEMP y                     ");
        query.append("                  WHERE y.comp_no = a.comp_no                     ");
        query.append("                  AND   y.emp_id = a.emp_id                       ");
        query.append("                  ),'') EMPDESC                                   ");
        query.append("         ,a.RESPONSE RESPONSE                                     ");
        query.append("         ,a.WKOR_ID WKORID                                        ");
        query.append("         ,(SELECT                                                 ");
        query.append("                  (SELECT y.key_name FROM TALANG y                ");
        query.append("                  WHERE y.key_type = x.key_type                   ");
        query.append("                  AND   y.key_no = x.key_no                       ");
        query.append("                  AND   y.lang = '"+lang+"'                       ");
        query.append("                  )                                               ");
        query.append("              FROM TACDSYSD x                                     ");
        query.append("              WHERE x.list_type='WO_TYPE'                         ");
        query.append("              AND x.cdsysd_no = b.wo_type) WOTYPEDESC             ");
        query.append("         ,(SELECT                                                 ");
        query.append("                  (SELECT y.key_name FROM TALANG y                ");
        query.append("                  WHERE y.key_type = x.key_type                   ");
        query.append("                  AND   y.key_no = x.key_no                       ");
        query.append("                  AND   y.lang = '"+lang+"'                       ");
        query.append("                  )                                               ");
        query.append("              FROM TACDSYSD x                                     ");
        query.append("              WHERE x.list_type=b.wo_type||'_TYPE'                ");
        query.append("              AND x.cdsysd_no = b.pm_type) PMTYPEDESC             ");
        query.append("          ,(SELECT x.item_no                                      ");
        query.append("              FROM TAEQUIPMENT x                                  ");
        query.append("              WHERE x.comp_no = a.comp_no                         ");
        query.append("                AND x.equip_id = (SELECT y.equip_id               ");
        query.append("                                  FROM TAWOEQUIP  y               ");
        query.append("                                  WHERE y.comp_no = a.comp_no     ");
        query.append("                                  AND y.wkor_id = b.wkor_id)      ");
        query.append("            )    ITEMNO                                           ");
        query.append("          ,(SELECT x.description                                  ");
        query.append("              FROM TAEQUIPMENT x                                  ");
        query.append("              WHERE x.comp_no = a.comp_no                         ");
        query.append("                AND x.equip_id = (SELECT y.equip_id               ");
        query.append("                                  FROM TAWOEQUIP  y               ");
        query.append("                                  WHERE y.comp_no = a.comp_no     ");
        query.append("                                  AND y.wkor_id = b.wkor_id)      ");
        query.append("            )    EQUIPDESC                                        ");
        query.append("          , b.START_DATE STARTDATE                                ");
        query.append("          , b.START_TIME STARTTIME                                ");
        query.append("          , b.END_DATE ENDDATE                                    ");
        query.append("          , b.END_TIME ENDTIME                                    ");
        query.append("          , b.WO_STATUS WOSTATUS                                  ");
        query.append("FROM TAWOREQRES a INNER JOIN TAWORKORDER b                        ");
        query.append("ON  a.comp_no = b.comp_no                                         ");
        query.append("AND a.wkor_id = b.wkor_id                                         ");
        query.append("WHERE 1=1                                                         ");
        query.getAndQuery("a.comp_no",compNo);
        query.getAndQuery("a.woreq_id",woReqId);
        query.getAndQuery("a.woreqres_method","WO");
    	query.append("ORDER BY a.woreqres_id											");
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }
	public int insertWoReqRes(Map map) throws Exception
    {
		QueryBuffer query = new QueryBuffer();
        Object[] objects;

        query.append("INSERT INTO TAWOREQRES (                                    ");
        query.append("       COMP_NO, WOREQRES_ID, WOREQ_ID, RES_DATE             ");
        query.append("       ,WORES_STATUS, DEPT_ID, EMP_ID, RESPONSE             ");
        query.append("       ,WKOR_ID, WOREQRES_METHOD,RES_TIME                   ");
        query.append("       )                                                    ");
        query.append("VALUES(                                                     ");
        query.append("      ?,?,?,?,											  ");
        query.append("      ?,?,?,?, 											  ");
        query.append("      ?,?,?   )                                             ");
        
        objects = new Object[] {
        		convertString(map.get("compNo"))
        		,convertString(map.get("woReqResId"))
        		,convertString(map.get("woReqId"))
        		,convertString(map.get("resDate"))
        		,convertString(map.get("woResStatus"))
        		,convertString(map.get("deptId"))
        		,convertString(map.get("empId"))
        		,convertString(map.get("response"))
        		,convertString(map.get("wkorId"))
        		,convertString(map.get("woReqResMethod"))
        		,convertString(map.get("resTime"))
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
	public int updateWoReqRes(Map map) throws Exception
    {
		QueryBuffer query = new QueryBuffer();
        Object[] objects;

        query.append("UPDATE TAWOREQRES SET                                    ");
        query.append("       DEPT_ID            = ?                            ");
        query.append("       ,EMP_ID            = ?                            ");
        query.append("       ,RESPONSE          = ?                            ");
        query.append("WHERE COMP_NO        = ?                                 ");
        query.append("  AND WOREQRES_ID    = ?                                 ");
        query.append("  AND WOREQ_ID       = ?                                 ");
        
        objects = new Object[] {
        		convertString(map.get("deptId"))
        		,convertString(map.get("empId"))
        		,convertString(map.get("response"))
        		,convertString(map.get("compNo"))
        		,convertString(map.get("woReqResId"))
        		,convertString(map.get("woReqId"))
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
	public int deleteWoReqRes(Map map) throws Exception
    {
		QueryBuffer query = new QueryBuffer();

        query.append("DELETE TAWORKORDER WHERE wkor_id = 															");
        query.append("(SELECT wkor_id FROM TAWOREQRES WHERE woreqres_id = "+convertString(map.get("woReqResId"))+")	");
        query.getAndQuery("comp_no", convertString(map.get("compNo")));
        
        getJdbcTemplate().update(query.toString());
        
        
        query = new QueryBuffer();
        query.append("DELETE FROM TAWOREQRES								");
        query.append("WHERE 1=1												");
        query.getAndQuery("comp_no", convertString(map.get("compNo")));
        query.getAndQuery("woreqres_id", convertString(map.get("woReqResId")));
        
        return getJdbcTemplate().update(query.toString());
    }

    

    public List findPhotoList(Map map) throws Exception
    {
		String compNo 		= String.valueOf(map.get("compNo"));
    	String lang 		= String.valueOf(map.get("lang"));
    	String objectId 	= String.valueOf(map.get("objectId"));
    	String objectType 	= String.valueOf(map.get("objectType"));
    	String regId 		= String.valueOf(map.get("regId"));
    	String docDataId 	= String.valueOf(map.get("docDataId"));
    	
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT a.docdata_id							AS ID			");
    	query.append("		,a.comp_no								AS COMP_NO		");
    	query.append("		,a.doc_id								AS file_id		");
    	query.append("		,b.object_type							AS OBJECT_TYPE	");
    	query.append("		,'"+objectId+"'							AS OBJECT_ID	");
    	query.append("		,b.dept_id								AS DEPT_ID		");
    	query.append("		,b.reg_id								AS REG_ID		");
    	query.append("		,b.reg_date								AS REG_DATE		");
    	query.append("		,a.nf_file_path							AS FILE_PATH	");
    	query.append("		,a.file_name							AS FILE_NAME	");
    	query.append("		,a.file_ext								AS EXTENSION	");
    	query.append("		,a.file_name							AS REMARK		");
    	query.append("		,'dream/android/'||a.docdata_id 		AS URL			");
    	query.append("FROM TADOCDATA a INNER JOIN TADOCUMENT b						");
    	query.append("ON a.comp_no = b.comp_no										");
    	query.append("AND a.doc_id = b.doc_id										");
    	query.append("WHERE 1=1														");
    	query.getStringEqualQuery("object_type", objectType);
    	query.append("AND a.file_ext IN ((SELECT TRIM(REGEXP_SUBSTR(value$, '[^,]+', 1, LEVEL))											");
    	query.append("						FROM (SELECT value$ FROM TACONFIG WHERE comp_no = '"+compNo+"' AND NAME='IMG_FILE_TYPE')	");
    	query.append("						CONNECT BY  INSTR(value$, ',', 1, LEVEL - 1) > 0))											");
    	query.getStringEqualQuery("a.docdata_id", docDataId);
    	query.append("AND a.doc_id IN (												");
    	query.append("			SELECT doc_id										");
    	query.append("			FROM TAOBJDOC										");
    	query.append("			WHERE 1=1											");
    	query.getStringEqualQuery("comp_no", compNo);
    	query.getStringEqualQuery("object_type", objectType);
    	query.getAndQuery("object_id", objectId);
    	query.append("			)													");
    	query.append("ORDER BY file_name											");
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }
	public String findDoc(Map map ) throws Exception
    {
		String compNo = convertString(map.get("compNo"));
		String docId = convertString(map.get("docId"));
		
		QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT COUNT(*)				");
        query.append("FROM TADOCUMENT x				");
        query.append("WHERE 1=1						");
        query.getAndQuery("x.comp_no",compNo);
        query.getAndQuery("x.doc_id",docId);
        
        return (int)getJdbcTemplate().queryForObject(query.toString(), Integer.class)+""; 
    }
}