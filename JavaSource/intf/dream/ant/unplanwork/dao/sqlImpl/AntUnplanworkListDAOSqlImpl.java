package intf.dream.ant.unplanwork.dao.sqlImpl;

import java.util.Map;

import common.spring.BaseJdbcDaoSupportSql;
import common.util.DateUtil;
import common.util.QuerySqlBuffer;
import intf.dream.ant.unplanwork.dao.AntUnplanworkListDAO;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="antUnplanworkListDAOTarget"
 * @spring.txbn id="antUnplanworkListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AntUnplanworkListDAOSqlImpl extends BaseJdbcDaoSupportSql implements AntUnplanworkListDAO
{
    public int updateWorkOrder(Map map)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        Object[] objects;

        query.append("UPDATE TAWORKORDER SET                    ");
        query.append("  pm_type          = ?,                   ");
        query.append("  wkor_date        = ?,                   ");
        query.append("  start_date       = ?,                   ");
        query.append("  start_time       = ?,                   ");
        query.append("  end_date         = ?,                   ");
        query.append("  end_time         = ?,                   ");
        query.append("  shift_type       = ?,                   ");
        query.append("  emp_id           = ?,                   ");
        query.append("  perform          = ?,                   ");
        query.append("  upd_date         = CONVERT(VARCHAR, GETDATE(), 112),");
        query.append("  upd_by           = ?,                   ");
        query.append("  wkctr_id         = ?,                   ");
        query.append("  work_time        = ?,                   ");
        query.append("  wo_status        = ?                    ");
        query.append("WHERE wkor_id      = ?                    ");
        query.append("    and comp_no    = ?                    ");

        
        objects = new Object[] {
        		convertString(map.get("pmType")),
        		convertString(map.get("wkorDate")),
        		convertString(map.get("startDate")),
        		convertString(map.get("startTime")),
        		convertString(map.get("endDate")),
        		convertString(map.get("endTime")),
        		convertString(map.get("shiftType")),
        		convertString(map.get("empId")),
        		convertString(map.get("perform")),
                convertString(map.get("userId")),
                convertString(map.get("wkctrId")),
                convertString(map.get("workTime")),
                "PRW",
                convertString(map.get("wkorId")),
                convertString(map.get("compNo"))
        };

        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    public int insertWorkOrder(Map map, String wkorId)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        Object[] objects;
        query.append("INSERT INTO TAWORKORDER (                                     ");
        query.append("     COMP_NO, WKOR_ID, WO_NO,                                 ");
        query.append("     DESCRIPTION, WO_STATUS, WO_TYPE, PM_TYPE,                ");
        query.append("     START_DATE, START_TIME, END_DATE, END_TIME,              ");
        query.append("     WORK_TIME, DEPT_ID, EMP_ID, PERFORM,                     ");
        query.append("     PM_ID, PMSCHED_ID, WO_GEN_TYPE, WOPOINT_ID,              ");
        query.append("     UPD_DATE, UPD_BY, SELF_VENDOR_TYPE, VENDOR_ID,           ");
        query.append("     P_WKOR_ID, WKOR_DATE, SHIFT_TYPE, TOT_AMT,               ");
        query.append("     PMACTION, EQLOC_ID, WKCTR_ID, CLOSE_ID,                  ");
        query.append("     CLOSE_DATE,IS_DELETED, PLANT                             ");
        query.append("      ) VALUES                                                ");
        query.append("     (?,?,?,                                                  ");
        query.append("     ?,?,?,?,                                                 ");
        query.append("     ?,?,?,?,                                                 ");
        query.append("     ?,?,?,?,                                                 ");
        query.append("     ?,?,?,?,                                                 ");
        query.append("     ?,?,?,?,                                                 ");
        query.append("     ?,?,?,?,                                                 ");
        query.append("     ?,?,?,?,                                                 ");
        query.append("     ?,?,?                                                    ");
        query.append("         )                                                    ");
        
        
        objects = new Object[] {
        		convertString(map.get("compNo")),
        		wkorId,
        		wkorId,
        		convertString(map.get("woDesc")),
        		"PRW",
        		convertString(map.get("woType")),
        		convertString(map.get("pmType")),
        		convertString(map.get("startDate")),
        		convertString(map.get("startTime")),
                convertString(map.get("endDate")),
                convertString(map.get("endTime")),
                convertString(map.get("workTime")),
                convertString(map.get("deptId")),
                convertString(map.get("empId")),
                convertString(map.get("perform")),
                "",
                "",
                convertString(map.get("woGenType")),
                "",
                convertString(map.get("updDate")),
                convertString(map.get("updBy")),
                "",
                "",
                "",
        		convertString(map.get("wkorDate")),
        		convertString(map.get("shiftType")),
                "",
                "",
                "",
        		convertString(map.get("wkctrId")),
                "",
                "",
                "N",
        		convertString(map.get("plant"))
        };

        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    public int insertWorkOrderLog(Map map, String wkorId)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        Object[] objects = null;
 
        query.append("INSERT INTO TXPDAWORKORDER                                    ");
        query.append("  (comp_no,       txpdaworkorder_id,                          ");
        query.append("   cre_date,      cre_time,                                   ");
        query.append("   mobins_yn,     mobins_wkor_id,                             ");
        query.append("   wkor_id,       wo_no,                                      ");
        query.append("   wo_title,      dept_no,                                    ");
        query.append("   dept_name,     emp_no,                                     ");
        query.append("   emp_name,      item_no,                                    ");
        query.append("   item_name,     start_date,                                 ");
        query.append("   start_time,    end_date,                                   ");
        query.append("   end_time,      ca_code,                                    ");
        query.append("   ca_name,       mo_code,                                    ");
        query.append("   mo_name,       re_code,                                    ");
        query.append("   re_name,       perform, user_no ,wo_type, pm_type          ");
        query.append("  )   VALUES                                                  ");
        query.append("  (?,             next value for SQATXPDAWORKORDER_ID,        ");
        query.append("   ?,             ?,                                          ");
        query.append("   ?,             ?,                                          ");
        query.append("   ?,             ?,                                          ");
        query.append("   ?,             ?,                                          ");
        query.append("   ?,             ?,                                          ");
        query.append("   ?,             ?,                                          ");
        query.append("   ?,             ?,                                          ");
        query.append("   ?,             ?,                                          ");
        query.append("   ?,             ?,                                          ");
        query.append("   ?,             ?,                                          ");
        query.append("   ?,             ?,                                          ");
        query.append("   ?,             ?, ? ,?,?                                   ");
        query.append("  )                                                           ");
        
        objects = new Object[] {
                convertString(map.get("compNo")),
                DateUtil.getDate(),
                DateUtil.getDateTime("HH")+""+DateUtil.getDateTime("mm"),
                "Y",
                convertString(map.get("wkorId")),
                wkorId,
                wkorId,
                convertString(map.get("woDesc")),
                convertString(map.get("deptId")),
                "",
                convertString(map.get("empId")),
                "",
                "",
                "",
        		convertString(map.get("startDate")),
        		convertString(map.get("startTime")),
                convertString(map.get("endDate")),
                convertString(map.get("endTime")),
                "",
                "",
                "",
                "",
                "",
                "",
                convertString(map.get("perform")),
                convertString(map.get("userNo")),
                convertString(map.get("woType")),
                convertString(map.get("pmType"))
        };
            
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    public int changeFileSeq(Map map, String wkorId)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        Object[] objects;

        query.append("UPDATE TADOCUMENT SET                  ");
        query.append("  doc_no            = ?,               ");
        query.append("  remark            = ?                ");
        query.append("WHERE doc_no        = ?                ");
        query.append("    and object_type = ?                ");
        query.append("    and comp_no     = ?                ");

        
        objects = new Object[] {
        		wkorId,
        		convertString(map.get("remark")),
                convertString(map.get("wkorId")),
                "WORESULT",
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
        		wkorId,
                convertString(map.get("wkorId")),
                "WORESULT",
                convertString(map.get("compNo"))
        };
        
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    public int changeWoReqResWkorId(Map map, String wkorId)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        Object[] objects;

        query.append("UPDATE TAWOREQRES SET                ");
        query.append("  wkor_id         = ?,               ");
        query.append("  wores_status    = ?                ");
        query.append("WHERE wkor_id     = ?                ");
        query.append("  AND comp_no     = ?                ");

        
        objects = new Object[] {
        		wkorId,
        		"WRK",
                convertString(map.get("wkorId")),
                convertString(map.get("compNo"))
        };
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    public int changeWoReqStatus(Map map, String wkorId)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        Object[] objects;

        query.append("UPDATE TAWOREQ SET                   ");
        query.append("  woreq_status    = ?                ");
        query.append("WHERE comp_no     = ?                ");
        query.append("AND woreq_id IN ( SELECT woreq_id FROM TAWOREQRES         ");
        query.append("					WHERE comp_no = ? AND wkor_id = ? )     ");

        
        objects = new Object[] {
        		"WRK",
                convertString(map.get("compNo")),
                convertString(map.get("compNo")),
                wkorId
        };
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }

    public int saveWoequip(Map map, String wkorId) {
		
		QuerySqlBuffer query = new QuerySqlBuffer();
    	
		query.append("DECLARE @t1 TABLE(												");
		query.append("	compNo NVARCHAR(1000)											");
		query.append("	,wkorId NVARCHAR(1000)											");
		query.append("	,equipId NVARCHAR(1000)											");
		query.append("	)																");
		query.append("INSERT INTO @t1 VALUES(?,?,?)											");
		query.append("IF EXISTS(														");
		query.append("		SELECT 1													");
		query.append("		FROM TAWOEQUIP a, @t1 b										");
		query.append("		WHERE 	a.comp_no = b.compNo								");
    	query.append("		AND 	a.wkor_id = b.wkorId								");
    	query.append("		AND 	a.equip_id = b.equipId								");
		query.append("	)																");
		query.append("	BEGIN															");
		query.append("		UPDATE TAWOEQUIP SET										");
		query.append("			comp_no = b.compNo										");
		query.append("		FROM TAWOEQUIP a, @t1 b										");
		query.append("		WHERE 	a.comp_no = b.compNo								");
    	query.append("		AND 	a.wkor_id = b.wkorId								");
    	query.append("		AND 	a.equip_id = b.equipId								");
		query.append("	END																");
		query.append("ELSE																");
		query.append("	BEGIN															");
		query.append("		INSERT INTO TAWOEQUIP										");
		query.append("			(	comp_no,	woequip_id,	wkor_id,	equip_id	)	");
		query.append("		SELECT b.compNo, next value for SQAWOEQUIP_ID,	b.wkorId,	b.equipId	");
		query.append("		FROM @t1 b													");
		query.append("END																");
    	
    	Object[] objects = new Object[] {
    			convertString(map.get("COMPNO")),
    			wkorId,
    			convertString(map.get("EQUIPID"))
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));

	}

    public int saveWofail(Map map, String wkorId) {
		
		QuerySqlBuffer query = new QuerySqlBuffer();
    	
		query.append("DECLARE @t1 TABLE(												");
		query.append("	compNo NVARCHAR(1000)											");
		query.append("	,wofailId NVARCHAR(1000)										");
		query.append("	,wkorId NVARCHAR(1000)											");
		query.append("	,caCd NVARCHAR(1000)											");
		query.append("	,caDesc NVARCHAR(1000)											");
		query.append("	,reCd NVARCHAR(1000)											");
		query.append("	,reDesc NVARCHAR(1000)											");
		query.append("	,moCd NVARCHAR(1000)											");
		query.append("	,moDesc NVARCHAR(1000)											");
		query.append("	,eqDnStartDate NVARCHAR(1000)									");
		query.append("	,eqDnStartTime NVARCHAR(1000)									");
		query.append("	,eqDnEndDate NVARCHAR(1000)										");
		query.append("	,eqDnEndTime NVARCHAR(1000)										");
		query.append("	,eqDnWorkTime NVARCHAR(1000)									");
		query.append("	,lnDnStartDate NVARCHAR(1000)									");
		query.append("	,lnDnStartTime NVARCHAR(1000)									");
		query.append("	,lnDnEndDate NVARCHAR(1000)										");
		query.append("	,lnDnEndTime NVARCHAR(1000)										");
		query.append("	,lnDnWorkTime NVARCHAR(1000)									");
		query.append("	,remark NVARCHAR(1000)											");
		query.append("	,eqAsmbId NVARCHAR(1000)										");
		query.append("	)																");
		query.append("INSERT INTO @t1 VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)	");
		query.append("IF EXISTS(														");
		query.append("		SELECT 1													");
		query.append("		FROM TAWOFAIL a, @t1 b										");
		query.append("		WHERE 	a.comp_no = b.compNo								");
    	query.append("		AND 	a.wkor_id = b.wkorId								");
    	query.append("		AND 	a.wofail_id = b.wofailId							");
		query.append("	)																");
		query.append("	BEGIN															");
		query.append("		UPDATE TAWOFAIL SET											");
		query.append("			ca_cd = b.caCd,											");
    	query.append("			ca_desc = b.caDesc,										");
    	query.append("			re_cd = b.reCd,											");
    	query.append("			re_desc = b.reDesc,										");
    	query.append("			mo_cd = b.moCd,											");
    	query.append("			mo_desc = b.moDesc,										");
    	query.append("			eqdn_start_date = b.eqDnStartDate,						");
    	query.append("			eqdn_start_time = b.eqDnStartTime,						");
    	query.append("			eqdn_end_date = b.eqDnEndDate,							");
    	query.append("			eqdn_end_time = b.eqDnEndTime,							");
    	query.append("			eqdn_work_time = b.eqDnWorkTime,						");
    	query.append("			lndn_start_date = b.lnDnStartDate,						");
    	query.append("			lndn_start_time = b.lnDnStartTime,						");
    	query.append("			lndn_end_date = b.lnDnEndDate,							");
    	query.append("			lndn_end_time = b.lnDnEndTime,							");
    	query.append("			lndn_work_time = b.lnDnWorkTime,						");
    	query.append("			remark = b.remark,										");
    	query.append("			eqAsmbId = b.eqAsmbId									");
		query.append("		FROM TAWOFAIL a, @t1 b										");
		query.append("		WHERE 	a.comp_no = b.compNo								");
    	query.append("		AND 	a.wkor_id = b.wkorId								");
    	query.append("		AND 	a.wofail_id = b.wofailId							");
		query.append("	END																");
		query.append("ELSE																");
		query.append("	BEGIN															");
		query.append("		INSERT INTO TAWOFAIL										");
		query.append("			(comp_no,			wofail_id,			wkor_id,		ca_cd,						");
    	query.append("			ca_desc,			re_cd,				re_desc,		eqdn_start_date,			");
    	query.append("			eqdn_start_time,	eqdn_end_date,		eqdn_end_time,	eqdn_work_time,				");
    	query.append("			lndn_start_date,	lndn_start_time,	lndn_end_date,	lndn_end_time,				");
    	query.append("			lndn_work_time,	remark, 				mo_cd,			mo_desc, eqasmb_id	)		");
		query.append("		SELECT b.compNo,		next value for SQAWOFAIL_ID,	b.wkorId,	b.caCd,				");
    	query.append("			b.caDesc,			b.reCd,					b.reDesc,			b.eqDnStartDate,	");
    	query.append("			b.eqDnStartTime,	b.eqDnEndDate,			b.eqDnEndTime,		b.eqDnWorkTime,		");
    	query.append("			b.lnDnStartDate,	b.lnDnStartTime,		b.lnDnEndDate,		b.lnDnEndTime,		");
    	query.append("			b.lnDnWorkTime,		b.remark,				b.moCd,				b.moDesc,			");
    	query.append("			b.eqAsmbId																			");
		query.append("		FROM @t1 b													");
		query.append("END																");
		
    	Object[] objects = new Object[] {
    			convertString(map.get("COMPNO")),
    			convertString(map.get("WOFAILID")),
    			wkorId,
    			convertString(map.get("CACD")),
    			convertString(map.get("CADESC")),
    			convertString(map.get("RECD")),
    			convertString(map.get("REDESC")),
    			convertString(map.get("MOCD")),
    			convertString(map.get("MODESC")),
    			convertString(map.get("EQDNSTARTDATE")),
    			convertString(map.get("EQDNSTARTTIME")),
    			convertString(map.get("EQDNENDDATE")),
    			convertString(map.get("EQDNENDTIME")),
    			convertString(map.get("EQDNWORKTIME")),
    			convertString(map.get("LNDNSTARTDATE")),
    			convertString(map.get("LNDNSTARTTIME")),
    			convertString(map.get("LNDNENDDATE")),
    			convertString(map.get("LNDNENDTIME")),
    			convertString(map.get("LNDNWORKTIME")),
    			convertString(map.get("REMARK")),
    			convertString(map.get("EQASMBID")),
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));

	}

    public int saveWocraft(Map map, String wkorId, String woCraftId) {
		
		QuerySqlBuffer query = new QuerySqlBuffer();
    	
		query.append("DECLARE @t1 TABLE(												");
		query.append("	compNo NVARCHAR(1000)											");
		query.append("	,wkorId NVARCHAR(1000)											");
		query.append("	,empId NVARCHAR(1000)											");
		query.append("	,startDate NVARCHAR(1000)										");
		query.append("	,startTime NVARCHAR(1000)										");
		query.append("	,endDate NVARCHAR(1000)											");
		query.append("	,endTime NVARCHAR(1000)											");
		query.append("	,workTime NVARCHAR(1000)										");
		query.append("	,remark NVARCHAR(1000)											");
		query.append("	)																");
		query.append("INSERT INTO @t1 VALUES(?,?,?,?,?,?,?,?,?)							");
		query.append("IF EXISTS(														");
		query.append("		SELECT 1													");
		query.append("		FROM TAWOCRAFT a, @t1 b										");
		query.append("		WHERE 	a.comp_no = b.compNo								");
    	query.append("		AND 	a.wkor_id = b.wkorId								");
    	query.append("		AND 	a.emp_id  = b.empId									");
		query.append("	)																");
		query.append("	BEGIN															");
		query.append("		UPDATE TAWOCRAFT SET										");
		query.append("			start_date = b.startDate,								");
    	query.append("			start_time = b.startTime,								");
    	query.append("			end_date   = b.endDate,									");
    	query.append("			end_time   = b.endTime,									");
    	query.append("			work_time  = b.workTime,								");
    	query.append("			remark     = b.remark									");
		query.append("		FROM TAWOCRAFT a, @t1 b										");
		query.append("		WHERE 	a.comp_no = b.compNo								");
    	query.append("		AND 	a.wkor_id = b.wkorId								");
    	query.append("		AND 	a.emp_id  = b.empId									");
		query.append("	END																");
		query.append("ELSE																");
		query.append("	BEGIN															");
		query.append("		INSERT INTO TAWOCRAFT										");
		query.append("			(comp_no,		wocraft_id,			wkor_id,	emp_id, 		start_date,		");
    	query.append("			start_time,	end_date,				end_time,	work_time,	remark	)			");
		query.append("		SELECT b.compNo,	?,					b.wkorId,	b.empId,		b.startDate,	");
    	query.append("			b.startTime,	b.endDate,			b.endTime,	b.workTime,		b.remark		");
		query.append("		FROM @t1 b													");
		query.append("END																");
		
    	Object[] objects = new Object[] {
    			convertString(map.get("COMPNO")),
    			wkorId,
    			convertString(map.get("EMPID")),
    			convertString(map.get("STARTDATE")),
    			convertString(map.get("STARTTIME")),
    			convertString(map.get("ENDDATE")),
    			convertString(map.get("ENDTIME")),
    			convertString(map.get("WORKTIME")),
    			convertString(map.get("REMARK")),
    			woCraftId
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));

	}

    public int saveWoparts(Map map, String wkorId, String woPartId) {
		
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("DECLARE @t1 TABLE(												");
		query.append("	compNo NVARCHAR(1000)											");
		query.append("	,wopartId NVARCHAR(1000)										");
		query.append("	,wkorId NVARCHAR(1000)											");
		query.append("	,wcodeId NVARCHAR(1000)											");
		query.append("	,partId NVARCHAR(1000)											");
		query.append("	,partGrade NVARCHAR(1000)										");
		query.append("	,useQty NVARCHAR(1000)											");
		query.append("	,remark NVARCHAR(1000)											");
		query.append("	,unitPrice NVARCHAR(1000)										");
		query.append("	,totPrice NVARCHAR(1000)										");
		query.append("	)																");
		query.append("INSERT INTO @t1 VALUES(?,?,?,?,?,?,?,?,?,?)						");
		query.append("IF EXISTS(														");
		query.append("		SELECT 1													");
		query.append("		FROM TAWOPARTS a, @t1 b										");
		query.append("		WHERE 	a.comp_no   = b.compNo								");
    	query.append("		AND 	a.wopart_id = b.wopartId							");
    	query.append("		AND 	a.wkor_id  = b.wkorId								");
		query.append("	)																");
		query.append("	BEGIN															");
		query.append("		UPDATE TAWOPARTS SET										");
		query.append("			wcode_id    = b.wcodeId,								");
    	query.append("			part_id     = b.partId,									");
    	query.append("			part_grade  = b.partGrade,								");
    	query.append("			use_qty     = b.useQty,									");
    	query.append("			remark      = b.remark,									");
    	query.append("			unit_price  = b.unitPrice,								");
    	query.append("			tot_price   = b.totPrice								");
		query.append("		FROM TAWOPARTS a, @t1 b										");
		query.append("		WHERE 	a.comp_no   = b.compNo								");
    	query.append("		AND 	a.wopart_id = b.wopartId							");
    	query.append("		AND 	a.wkor_id  = b.wkorId								");
		query.append("	END																");
		query.append("ELSE																");
		query.append("	BEGIN															");
		query.append("		INSERT INTO TAWOPARTS										");
		query.append("			(comp_no,		wopart_id,			wkor_id,	wcode_id,		part_id,	");
    	query.append("			part_grade,	use_qty,				remark,	unit_price,	tot_price	)		");
		query.append("		SELECT b.compNo,	?,					b.wkorId,	b.wcodeId,		b.partId,	");
    	query.append("			b.partGrade,	b.useQty,			b.remark,	b.unitPrice,	b.totPrice	");
		query.append("		FROM @t1 b													");
		query.append("END																");

    	Object[] objects = new Object[] {
    			convertString(map.get("COMPNO")),
    			convertString(map.get("WOPARTID")),
    			wkorId,
    			convertString(map.get("WCODEID")),
    			convertString(map.get("PARTID")),
    			convertString(map.get("PARTGRADE")),
    			convertString(map.get("USEQTY")),
    			convertString(map.get("REMARK")),
    			convertString(map.get("UNITPRICE")),
    			convertString(map.get("TOTPRICE")),
    			woPartId
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));

	}
    public int updateWorkOrderEqAsmgId(Map map, String wkorId)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
		Object[] objects;
		
		query.append("UPDATE TAWORKORDER SET                                   ");
        query.append("       EQASMB_ID             = ?                         ");
        query.append("WHERE COMP_NO    = ?                                     ");
        query.append("  AND WKOR_ID    = ?                                     ");
        
        objects = new Object[] {
        		convertString(map.get("EQASMBID"))
        		,convertString(map.get("COMPNO"))
        		,wkorId
        };
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
	@Override
	public String getWoStatusOfWorkOrder(Map map) {
		
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("SELECT wo_status		");
		query.append("FROM TAWORKORDER		");
		query.append("WHERE comp_no = ?		");
		query.append("AND wkor_id = ?		");
		
		Object[] objects = new Object[] {
				convertString(map.get("compNo")),
				convertString(map.get("wkorId"))
		};
		
		return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString(), getObject(objects)));
}
}