package intf.dream.android.offline.woreq.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportSql;
import common.util.CommonUtil;
import common.util.DateUtil;
import common.util.QuerySqlBuffer;
import intf.dream.android.common.CommonValues;
import intf.dream.android.offline.woreq.dao.AnIfWoReqListDAO;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="anIfWoReqListDAOTarget"
 * @spring.txbn id="anIfWoReqListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AnIfWoReqListDAOSqlImpl extends BaseJdbcDaoSupportSql implements AnIfWoReqListDAO
{
	public List findWoReqList(Map map) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer(); 
    	
    	String compNo = String.valueOf(map.get("compNo"));
    	String startDate = String.valueOf(map.get("startDate"));
    	String endDate = String.valueOf(map.get("endDate"));
    	String plant = String.valueOf(map.get("plant"));
    	
    	String deptId = String.valueOf(map.get("deptId"));
    	String empId = String.valueOf(map.get("empId"));
    	String wkctrId = String.valueOf(map.get("wkctrId"));
    	String eqctgType = String.valueOf(map.get("eqctgType"));
    	String eqlocId = String.valueOf(map.get("eqlocId"));

    	query.append("SELECT						");
    	query.append("		a.COMP_NO				");
    	query.append("		,a.WOREQ_ID				");
    	query.append("		,a.WOREQ_NO				");
    	query.append("		,a.DESCRIPTION			");
    	query.append("		,a.EQLOC_ID				");
    	query.append("		,a.EQUIP_ID				");
    	query.append("		,a.WOREQ_STATUS			");
    	query.append("		,a.REQ_DATE				");
    	query.append("		,a.REQ_DEPT_ID			");
    	query.append("		,a.REQ_EMP_ID			");
    	query.append("		,a.REQ_PHONE			");
    	query.append("		,a.REQ_EMAIL			");
    	query.append("		,a.REQUEST				");
    	query.append("		,a.REC_DEPT_ID			");
    	query.append("		,a.REC_WKCTR_ID			");
    	query.append("		,a.REC_EMP_ID			");
    	query.append("		,a.REQ_TIME				");
    	query.append("		,a.REQ_PRIORITY			");
    	query.append("		,a.REQ_COM_DATE			");
    	query.append("		,a.REVIEW				");
    	query.append("FROM TAWOREQ a				");
    	query.append("WHERE 1=1						");
    	query.getStringEqualQuery("a.comp_no", compNo);
    	query.append("  AND a.req_date >= '"+startDate+"'	");
    	query.append("  AND a.req_date <=  '"+endDate+"'	");
        query.append("  AND a.plant 	= '"+plant+"'		");
    	query.append("  AND a.woreq_status IN ("+CommonUtil.getCommaStringFromArray(CommonValues.USED_WORES_STATES, "''")+") ");
        
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
        
    	return getJdbcTemplate().queryForList(query.toString());
    } 
	public List findWoReqFileList(Map map) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer(); 
    	
    	String compNo = String.valueOf(map.get("compNo"));
    	String startDate = String.valueOf(map.get("startDate"));
    	String endDate = String.valueOf(map.get("endDate"));
    	String plant = String.valueOf(map.get("plant"));

    	String deptId = String.valueOf(map.get("deptId"));
    	String empId = String.valueOf(map.get("empId"));
    	String wkctrId = String.valueOf(map.get("wkctrId"));
    	String eqctgType = String.valueOf(map.get("eqctgType"));
    	String eqlocId = String.valueOf(map.get("eqlocId"));


    	query.append("SELECT x.comp_no AS comp_no					");
    	query.append("		,x.docdata_id AS file_id				");
    	query.append("		,y.object_type AS object_type			");
    	query.append("		,(SELECT a.object_id FROM TAOBJDOC a WHERE a.comp_no = x.comp_no AND a.doc_id = x.doc_id)   AS object_id	");
    	query.append("		,y.dept_id AS dept_id					");
    	query.append("		,y.reg_id AS reg_id						");
    	query.append("		,y.reg_date AS reg_date					");
    	query.append("		,x.nf_file_path AS file_path			");
    	query.append("		,x.file_name AS file_name				");
    	query.append("		,x.file_ext AS extension				");
    	query.append("		,'' AS remark							");
    	query.append("FROM TADOCDATA x INNER JOIN TADOCUMENT y		");
    	query.append("	ON x.comp_no = y.comp_no					");
    	query.append("	AND x.doc_id = y.doc_id						");
    	query.append("WHERE 1=1										");
    	query.getStringEqualQuery("x.comp_no", compNo);
    	query.append("AND x.file_ext IN (SELECT value											");
    	query.append("						FROM dbo.SPLIT_STR_TO_TABLE((select value$ from taconfig where comp_no = '"+compNo+"'		");
    	query.append("														AND name='IMG_FILE_TYPE'),',') )							");
    	query.append("AND x.doc_id IN (SELECT doc_id 				");
    	query.append("					FROM TAOBJDOC				");
    	query.append("					WHERE 1=1					");
    	query.getStringEqualQuery("comp_no", compNo);
    	query.getStringEqualQuery("object_type", "WOREQ");
    	query.append("					AND object_id IN ( 							");
    	query.append("									SELECT						");
    	query.append("											a.WOREQ_ID			");
    	query.append("									FROM TAWOREQ a				");
    	query.append("									WHERE 1=1					");
    	query.getStringEqualQuery("a.comp_no", compNo);
    	query.append("  AND a.req_date >= '"+startDate+"'							");
    	query.append("  AND a.req_date <=  '"+endDate+"'							");
        query.append("  AND a.plant 	= '"+plant+"'								");
    	query.append("  AND a.woreq_status IN ("+CommonUtil.getCommaStringFromArray(CommonValues.USED_WORES_STATES, "''")+") ");
        
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
    	query.append("									)							");
    	query.append("					)											");
    	
    	return getJdbcTemplate().queryForList(query.toString());
    } 
    public List findWoReqResList(Map map) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer(); 

    	String compNo = String.valueOf(map.get("compNo"));
    	String startDate = String.valueOf(map.get("startDate"));
    	String endDate = String.valueOf(map.get("endDate"));
    	String plant = String.valueOf(map.get("plant"));

    	String deptId = String.valueOf(map.get("deptId"));
    	String empId = String.valueOf(map.get("empId"));
    	String wkctrId = String.valueOf(map.get("wkctrId"));
    	String eqctgType = String.valueOf(map.get("eqctgType"));
    	String eqlocId = String.valueOf(map.get("eqlocId"));
    	
    	query.append("SELECT											");
    	query.append("		x.COMP_NO									");
    	query.append("		,x.WOREQRES_ID								");
    	query.append("		,x.WOREQ_ID									");
    	query.append("		,x.RES_DATE									");
    	query.append("		,x.WORES_STATUS								");
    	query.append("		,x.DEPT_ID									");
    	query.append("		,x.EMP_ID									");
    	query.append("		,x.RESPONSE									");
    	query.append("		,x.WKOR_ID									");
    	query.append("		,x.WOREQRES_METHOD							");
    	query.append("		,x.RES_TIME									");
    	query.append("FROM TAWOREQRES x									");
    	query.append("WHERE 1=1											");
    	query.append("AND x.woreq_id IN (								");
    	query.append("	SELECT											");
    	query.append("		a.WOREQ_ID									");
    	query.append("	FROM TAWOREQ a									");
    	query.append("	WHERE 1=1										");
    	query.getStringEqualQuery("a.comp_no", compNo);
    	query.append("  AND a.req_date >= '"+startDate+"'				");
    	query.append("  AND a.req_date <=  '"+endDate+"'				");
        query.append("  AND a.plant 	= '"+plant+"'					");
    	query.append("  AND a.woreq_status IN ("+CommonUtil.getCommaStringFromArray(CommonValues.USED_WORES_STATES, "''")+") ");
        
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
    	query.append("				)								");
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }

    public int insertWoReqList(Map map, String woReqId)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        Object[] objects;

        query.append("INSERT INTO TAWOREQ (                                                     ");
        query.append("     COMP_NO, WOREQ_ID, WOREQ_NO, DESCRIPTION, EQLOC_ID                   ");
        query.append("     , EQUIP_ID, WOREQ_STATUS, REQ_DATE, REQ_DEPT_ID, REQ_EMP_ID          ");
        query.append("     , REQ_PHONE, REQ_EMAIL, REQUEST, REC_DEPT_ID, REC_WKCTR_ID           ");
        query.append("     , REC_EMP_ID, REQ_TIME, REQ_PRIORITY, REQ_COM_DATE, REVIEW           ");
        query.append("     , PLANT, WOREQ_CHANNEL                                               ");
        query.append("      )                                                                   ");
        query.append("VALUES (                                                                  ");
        query.append("      ?,?,?,?,?                                                           ");
        query.append("     ,?,?,?,?,?                                                           ");
        query.append("     ,?,?,?,?,?                                                           ");
        query.append("     ,?,?,?,?,?                                                           ");
        query.append("     ,?,?                                                                 ");
        query.append("         )                                                                ");
        
        objects = new Object[] {
        		convertString(map.get("compNo"))
        		,woReqId
        		,woReqId
        		,convertString(map.get("woReqDesc"))
        		,convertString(map.get("eqLocId"))
        		,convertString(map.get("equipId"))
        		,"Y".equals(convertString(map.get("isReq")))?"REQ":"WRT"
        		,convertString(map.get("reqDate"))
        		,convertString(map.get("reqDeptId"))
        		,convertString(map.get("reqEmpId"))
        		,convertString(map.get("reqPhone"))
        		,convertString(map.get("reqEmail"))
        		,convertString(map.get("request"))
        		,convertString(map.get("recDeptId"))
        		,convertString(map.get("recWkCtrId"))
        		,convertString(map.get("recEmpId"))
        		,convertString(map.get("reqTime"))
        		,convertString(map.get("reqPriority"))
        		,convertString(map.get("reqComDate"))
        		,""
        		,convertString(map.get("plant"))
        		,"DREAMO"
        };
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    public int changeFileSeq(Map map, String woReqId)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        Object[] objects;

        query.append("UPDATE TADOCUMENT SET                  ");
        query.append("  doc_no            = ?                ");
        query.append("WHERE doc_no        = ?                ");
        query.append("    and object_type = ?                ");
        query.append("    and comp_no     = ?                ");

        
        objects = new Object[] {
        		woReqId,
                convertString(map.get("woReqId")),
                "WOREQ",
                convertString(map.get("compNo"))
        };
        getJdbcTemplate().update(query.toString(), getObject(objects));
        
        query = new QuerySqlBuffer();
        query.append("UPDATE TAOBJDOC SET                   ");
        query.append("  object_id           = ?             ");
        query.append("WHERE object_id       = ?             ");
        query.append("    and object_type   = ?             ");
        query.append("    and comp_no       = ?             ");

        
        objects = new Object[] {
        		woReqId,
                convertString(map.get("woReqId")),
                "WOREQ",
                convertString(map.get("compNo"))
        };
        
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    public int updateWoResList(Map map)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        Object[] objects;

        query.append("UPDATE TAWOREQ SET                  		    ");
        query.append("  REC_DEPT_ID          = ?,                   ");
        query.append("  REC_WKCTR_ID         = ?,                   ");
        query.append("  REC_EMP_ID       	 = ?,                   ");
        query.append("  REVIEW         		 = ?                    ");
        query.append("WHERE woreq_id      	 = ?                    ");
        query.append("    AND comp_no    	 = ?                    ");

        
        objects = new Object[] {
        		convertString(map.get("recDeptId")),
        		convertString(map.get("recWkCtrId")),
        		convertString(map.get("recEmpId")),
        		convertString(map.get("review")),
        		convertString(map.get("woReqId")),
        		convertString(map.get("compNo"))
        };

        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    public int insertWoReqResList(Map map)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        Object[] objects;

        query.append("INSERT INTO TAWOREQRES (                                      ");
        query.append("     COMP_NO, WOREQRES_ID, WOREQ_ID,RES_DATE,                 ");
        query.append("     WORES_STATUS, DEPT_ID, EMP_ID, RESPONSE,                 ");
        query.append("     WKOR_ID, WOREQRES_METHOD                                 ");
        query.append("      )                                                       ");
        query.append("VALUES (                                                      ");
        query.append("     ?,next value for SQAWOREQRES_ID,?,?,                     ");
        query.append("     ?,?,?,?,                                                 ");
        query.append("     ?,?                                                      ");
        query.append("         )                                                    ");
        
        objects = new Object[] {
        		convertString(map.get("COMPNO")),
        		convertString(map.get("WOREQID")),
        		DateUtil.getDate(),
        		"WRK",
        		convertString(map.get("DEPTID")),
        		convertString(map.get("EMPID")),
        		convertString(map.get("RESPONSE")),
        		convertString(map.get("WKORID")),
        		"WO"
        };
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    public int updateWoReqStatus(Map map)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        Object[] objects;

        query.append("UPDATE TAWOREQ SET WOREQ_STATUS = ?   ");
        query.append("WHERE COMP_NO = ?                     ");
        query.append("AND   WOREQ_ID = ?                    ");
        
        objects = new Object[] {
        		"WRK",
        		convertString(map.get("COMPNO")),
        		convertString(map.get("WOREQID"))
        };
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
	@Override
	public String getWoReqStatus(Map map) throws Exception {
		
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("SELECT woreq_status		");
		query.append("FROM TAWOREQ				");
		query.append("WHERE comp_no = ?			");
		query.append("AND woreq_id  = ?			");
		
		Object[] objects = new Object[] {
				convertString(map.get("COMPNO")),
				convertString(map.get("WOREQID"))
		};
		
		return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString(),getObject(objects)));
}
}