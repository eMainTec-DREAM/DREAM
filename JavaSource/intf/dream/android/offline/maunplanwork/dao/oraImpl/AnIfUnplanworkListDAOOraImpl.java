package intf.dream.android.offline.maunplanwork.dao.oraImpl;

import java.util.Map;

import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import intf.dream.android.offline.maunplanwork.dao.AnIfUnplanworkListDAO;
/**
 *  dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="anIfUnplanworkListDAOTarget"
 * @spring.txbn id="anIfUnplanworkListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AnIfUnplanworkListDAOOraImpl extends BaseJdbcDaoSupportOra implements AnIfUnplanworkListDAO
{
    public int updateWorkOrder(Map map)
    {
        QueryBuffer query = new QueryBuffer();
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
        query.append("  upd_date         = to_char(sysdate,'yyyymmdd'),");
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

        
        return getJdbcTemplate().update(query.toString(), objects);
    }
    public int insertWorkOrder(Map map, String wkorId)
    {
        QueryBuffer query = new QueryBuffer();
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

        
        return getJdbcTemplate().update(query.toString(), objects);
    }
    public int insertWorkOrderLog(Map map, String wkorId)
    {
        QueryBuffer query = new QueryBuffer();
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
        query.append("  (?,             SQATXPDAWORKORDER_ID.nextval,               ");
        query.append("   TO_CHAR(sysdate,'YYYYMMDD'), TO_CHAR(sysdate,'HHMMSS'),    ");
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
            
        return getJdbcTemplate().update(query.toString(), objects);
    }
    public int changeFileSeq(Map map, String wkorId)
    {
        QueryBuffer query = new QueryBuffer();
        Object[] objects;

        query.append("UPDATE TADOCUMENT SET                  ");
        query.append("  doc_no            = ?                ");
        query.append("WHERE doc_no        = ?                ");
        query.append("    and object_type = ?                ");
        query.append("    and comp_no     = ?                ");

        
        objects = new Object[] {
        		wkorId,
                convertString(map.get("wkorId")),
                "WORESULT",
                convertString(map.get("compNo"))
        };
        getJdbcTemplate().update(query.toString(), objects);
        
        query = new QueryBuffer();
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
        
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
    public int changeWoReqResWkorId(Map map, String wkorId)
    {
        QueryBuffer query = new QueryBuffer();
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
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
    public int changeWoReqStatus(Map map, String wkorId)
    {
    	QueryBuffer query = new QueryBuffer();
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
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
    public int saveWoequip(Map map, String wkorId) {
		
		QueryBuffer query = new QueryBuffer();
    	
    	query.append("MERGE INTO TAWOEQUIP a													");
    	query.append("USING(	SELECT 	? compNo,												");
    	query.append("					? wkorId,												");
    	query.append("					? equipId												");
    	query.append("			FROM DUAL) b													");
    	query.append("ON(	a.comp_no = b.compNo												");
    	query.append("	AND a.wkor_id = b.wkorId												");
    	query.append("	AND a.equip_id = b.equipId	)											");
    	query.append("WHEN NOT MATCHED THEN														");
    	query.append("	INSERT (a.comp_no,	a.woequip_id,			a.wkor_id,	a.equip_id	)	");
    	query.append("	VALUES (b.compNo,	SQAWOEQUIP_ID.nextval,	b.wkorId,	b.equipId	)	");
    	
    	Object[] objects = new Object[] {
    			convertString(map.get("COMPNO")),
    			wkorId,
    			convertString(map.get("EQUIPID"))
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);

	}

    public int saveWofail(Map map, String wkorId) {
		
		QueryBuffer query = new QueryBuffer();
    	
    	query.append("MERGE INTO TAWOFAIL a																			");
    	query.append("USING(	SELECT 	? compNo,																	");
    	query.append("					? wofailId,																	");
    	query.append("					? wkorId,																	");
    	query.append("					? caCd,																		");
    	query.append("					? caDesc,																	");
    	query.append("					? reCd,																		");
    	query.append("					? reDesc,																	");
    	query.append("					? moCd,																		");
    	query.append("					? moDesc,																	");
    	query.append("					? eqDnStartDate,															");
    	query.append("					? eqDnStartTime,															");
    	query.append("					? eqDnEndDate,																");
    	query.append("					? eqDnEndTime,																");
    	query.append("					? eqDnWorkTime,																");
    	query.append("					? lnDnStartDate,															");
    	query.append("					? lnDnStartTime,															");
    	query.append("					? lnDnEndDate,																");
    	query.append("					? lnDnEndTime,																");
    	query.append("					? lnDnWorkTime,																");
    	query.append("					? remark,																	");
    	query.append("					? eqasmb_id																	");
    	query.append("			FROM DUAL) b																		");
    	query.append("ON(	a.comp_no = b.compNo																	");
    	query.append("	AND a.wkor_id = b.wkorId																	");
    	query.append("	AND a.wofail_id = b.wofailId	)															");
    	query.append("WHEN MATCHED THEN																				");
    	query.append("	UPDATE SET 	a.ca_cd = b.caCd,																");
    	query.append("				a.ca_desc = b.caDesc,															");
    	query.append("				a.re_cd = b.reCd,																");
    	query.append("				a.re_desc = b.reDesc,															");
    	query.append("				a.mo_cd = b.moCd,																");
    	query.append("				a.mo_desc = b.moDesc,															");
    	query.append("				a.eqdn_start_date = b.eqDnStartDate,											");
    	query.append("				a.eqdn_start_time = b.eqDnStartTime,											");
    	query.append("				a.eqdn_end_date = b.eqDnEndDate,												");
    	query.append("				a.eqdn_end_time = b.eqDnEndTime,												");
    	query.append("				a.eqdn_work_time = b.eqDnWorkTime,												");
    	query.append("				a.lndn_start_date = b.lnDnStartDate,											");
    	query.append("				a.lndn_start_time = b.lnDnStartTime,											");
    	query.append("				a.lndn_end_date = b.lnDnEndDate,												");
    	query.append("				a.lndn_end_time = b.lnDnEndTime,												");
    	query.append("				a.lndn_work_time = b.lnDnWorkTime,												");
    	query.append("				a.remark = b.remark,															");
    	query.append("				a.eqasmb_id = b.eqasmb_id														");
    	query.append("WHEN NOT MATCHED THEN																			");
    	query.append("	INSERT (a.comp_no,			a.wofail_id,			a.wkor_id,			a.ca_cd,			");
    	query.append("			a.ca_desc,			a.re_cd,				a.re_desc,			a.eqdn_start_date,	");
    	query.append("			a.eqdn_start_time,	a.eqdn_end_date,		a.eqdn_end_time,	a.eqdn_work_time,	");
    	query.append("			a.lndn_start_date,	a.lndn_start_time,		a.lndn_end_date,	a.lndn_end_time,	");
    	query.append("			a.lndn_work_time,	a.remark, 				a.mo_cd,			a.mo_desc,			");
    	query.append("			a.eqasmb_id		)																	");
    	query.append("	VALUES (b.compNo,			SQAWOFAIL_ID.nextval,	b.wkorId,			b.caCd,				");
    	query.append("			b.caDesc,			b.reCd,					b.reDesc,			b.eqDnStartDate,	");
    	query.append("			b.eqDnStartTime,	b.eqDnEndDate,			b.eqDnEndTime,		b.eqDnWorkTime,		");
    	query.append("			b.lnDnStartDate,	b.lnDnStartTime,		b.lnDnEndDate,		b.lnDnEndTime,		");
    	query.append("			b.lnDnWorkTime,		b.remark,				b.moCd,				b.moDesc,			");
    	query.append("			b.eqasmb_id )																		");
    	
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
    			convertString(map.get("EQASMBID"))
    			
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);

	}

    public int saveWocraft(Map map, String wkorId, String woCraftId) {
		
		QueryBuffer query = new QueryBuffer();
    	
    	query.append("MERGE INTO TAWOCRAFT a																			");
    	query.append("USING(	SELECT 	? compNo,																		");
    	query.append("					? wkorId,																		");
    	query.append("					? empId,																		");
    	query.append("					? startDate,																	");
    	query.append("					? startTime,																	");
    	query.append("					? endDate,																		");
    	query.append("					? endTime,																		");
    	query.append("					? workTime,																		");
    	query.append("					? remark																		");
    	query.append("			FROM DUAL) b																			");
    	query.append("ON(	a.comp_no = b.compNo																		");
    	query.append("	AND a.wkor_id = b.wkorId																		");
    	query.append("	AND a.emp_id  = b.empId	)																		");
    	query.append("WHEN MATCHED THEN																					");
    	query.append("	UPDATE SET 	a.start_date = b.startDate,															");
    	query.append("				a.start_time = b.startTime,															");
    	query.append("				a.end_date   = b.endDate,															");
    	query.append("				a.end_time   = b.endTime,															");
    	query.append("				a.work_time  = b.workTime,															");
    	query.append("				a.remark     = b.remark																");
    	query.append("WHEN NOT MATCHED THEN																				");
    	query.append("	INSERT (a.comp_no,		a.wocraft_id,			a.wkor_id,	a.emp_id, 		a.start_date,		");
    	query.append("			a.start_time,	a.end_date,				a.end_time,	a.work_time,	a.remark		)	");
    	query.append("	VALUES (b.compNo,		?,						b.wkorId,	b.empId,		b.startDate,		");
    	query.append("			b.startTime,	b.endDate,				b.endTime,	b.workTime,		b.remark		)	");
    	
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
    	
    	return getJdbcTemplate().update(query.toString(), objects);

	}

    public int saveWoparts(Map map, String wkorId, String woPartId) {
		
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("MERGE INTO TAWOPARTS a																			");
    	query.append("USING(	SELECT 	? compNo,																		");
    	query.append("					? wopartId,																		");
    	query.append("					? wkorId,																		");
    	query.append("					? wcodeId,																		");
    	query.append("					? partId,																		");
    	query.append("					? partGrade,																	");
    	query.append("					? useQty,																		");
    	query.append("					? remark,																		");
    	query.append("					? unitPrice,																	");
    	query.append("					? totPrice																		");
    	query.append("			FROM DUAL) b																			");
    	query.append("ON(	a.comp_no   = b.compNo																		");
    	query.append("	AND a.wopart_id = b.wopartId																	");
    	query.append("	AND a.wkor_id  = b.wkorId	)																	");
    	query.append("WHEN MATCHED THEN																					");
    	query.append("	UPDATE SET 	a.part_id     = b.partId,															");
    	query.append("				a.part_grade  = b.partGrade,														");
    	query.append("				a.use_qty     = b.useQty,															");
    	query.append("				a.remark      = b.remark,															");
    	query.append("				a.unit_price  = b.unitPrice,														");
    	query.append("				a.tot_price   = b.totPrice															");
    	query.append("WHEN NOT MATCHED THEN																				");
    	query.append("	INSERT (a.comp_no,		a.wopart_id,			a.wkor_id,	a.wcode_id,		a.part_id,			");
    	query.append("			a.part_grade,	a.use_qty,				a.remark,	a.unit_price,	a.tot_price		)	");
    	query.append("	VALUES (b.compNo,		?,						b.wkorId,	b.wcodeId,		b.partId,			");
    	query.append("			b.partGrade,	b.useQty,				b.remark,	b.unitPrice,	b.totPrice		)	");
    	
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
    	
    	return getJdbcTemplate().update(query.toString(), objects);

	}
    
    public int updateWorkOrderEqAsmgId(Map map, String wkorId)
    {
		QueryBuffer query = new QueryBuffer();
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
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
	@Override
	public String getWoStatusOfWorkOrder(Map map) {
		
		QueryBuffer query = new QueryBuffer();
		
		query.append("SELECT wo_status		");
		query.append("FROM TAWORKORDER		");
		query.append("WHERE comp_no = ?		");
		query.append("AND wkor_id = ?		");
		
		Object[] objects = new Object[] {
				convertString(map.get("compNo")),
				convertString(map.get("wkorId"))
		};
		
		return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString(),objects));
}
    
}