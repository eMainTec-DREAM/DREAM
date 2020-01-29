package dream.work.list.dao.oraImpl;

import java.util.List;

import common.bean.MwareConfig;
import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.CommonUtil;
import common.util.QueryBuffer;
import dream.pers.appreq.dto.AppReqDetailDTO;
import dream.work.list.dao.MaWoResultMstrDetailDAO;
import dream.work.list.dto.MaWoResultFailDetailDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultMstrDetailDTO;
import dream.work.list.dto.MaWoResultPmCalDTO;

/**
 * 작업결과 - 상세 dao
 * 
 * @author kim21017
 * @version $Id: MaWoResultMstrDetailDAO.java,v 1.0 2015/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="maWoResultMstrDetailDAOTarget"
 * @spring.txbn id="maWoResultMstrDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaWoResultMstrDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MaWoResultMstrDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrCommonDTO
     * @return
     */
    public MaWoResultMstrDetailDTO findDetail(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        String lang = maWoResultMstrCommonDTO.getUserLang();
        
        query.append("SELECT																			");
        query.append("		x.wkor_id													wkOrId,			");
        query.append("		x.wo_no														woNo,			");
        query.append("		x.wo_status													woStatus,		");
        query.append("		x.wo_status													woStatusId,		");
        query.append("		SFACODE_TO_DESC(x.wo_status,'WO_STATUS','SYS','','"+lang+"') woStatusDesc,	");
        query.append("      (SELECT b.old_eq_no                                                         ");
        query.append("          FROM TAWOEQUIP a, TAEQUIPMENT b                                         ");
        query.append("          WHERE a.comp_no = b.comp_no                                             ");
        query.append("              AND a.equip_id = b.equip_id                                         ");
        query.append("              AND a.wkor_id = x.wkor_id                                           ");
        query.append("              AND a.comp_no = x.comp_no                                           ");
        query.append("              AND rownum = 1              )                       AS oldEqNo,     ");
        query.append("		(SELECT b.equip_id															");
        query.append("			FROM TAWOEQUIP a, TAEQUIPMENT b											");
        query.append("			WHERE a.comp_no = b.comp_no												");
        query.append("				AND a.equip_id = b.equip_id											");
        query.append("				AND a.wkor_id = x.wkor_id											");
        query.append("				AND a.comp_no = x.comp_no											");
        query.append("				AND rownum = 1				) 						AS equipId,		");
        query.append("      (SELECT b.item_no                                                           ");
        query.append("          FROM TAWOEQUIP a, TAEQUIPMENT b                                         ");
        query.append("          WHERE a.comp_no = b.comp_no                                             ");
        query.append("              AND a.equip_id = b.equip_id                                         ");
        query.append("              AND a.wkor_id = x.wkor_id                                           ");
        query.append("              AND a.comp_no = x.comp_no                                           ");
        query.append("              AND rownum = 1              )                       AS itemNo,      ");
        query.append("      (SELECT b.p_equip_id                                                        ");
        query.append("          FROM TAWOEQUIP a, TAEQUIPMENT b                                         ");
        query.append("          WHERE a.comp_no = b.comp_no                                             ");
        query.append("              AND a.equip_id = b.equip_id                                         ");
        query.append("              AND a.wkor_id = x.wkor_id                                           ");
        query.append("              AND a.comp_no = x.comp_no                                           ");
        query.append("              AND rownum = 1              )                       AS pequipId,    ");
        query.append("      (SELECT (SELECT c.description FROM TAEQUIPMENT c WHERE c.equip_id = b.p_equip_id) ");
        query.append("          FROM TAWOEQUIP a, TAEQUIPMENT b                                         ");
        query.append("          WHERE a.comp_no = b.comp_no                                             ");
        query.append("              AND a.equip_id = b.equip_id                                         ");
        query.append("              AND a.wkor_id = x.wkor_id                                           ");
        query.append("              AND a.comp_no = x.comp_no                                           ");
        query.append("              AND rownum = 1              )                       AS pequipDesc,  ");
        query.append("		(SELECT b.eqctg_type														");
        query.append("			FROM TAWOEQUIP a, TAEQUIPMENT b											");
        query.append("			WHERE a.comp_no = b.comp_no												");
        query.append("				AND a.equip_id = b.equip_id											");
        query.append("				AND a.wkor_id = x.wkor_id											");
        query.append("				AND a.comp_no = x.comp_no											");
        query.append("				AND rownum = 1				) 						AS eqctgType,	");
        query.append("		(SELECT MAX(b.description)													");
        query.append("			FROM TAWOEQUIP a, TAEQUIPMENT b											");
        query.append("			WHERE a.comp_no = b.comp_no												");
        query.append("				AND a.equip_id = b.equip_id											");
        query.append("				AND a.wkor_id = x.wkor_id											");
        query.append("				AND a.comp_no = x.comp_no											");
        query.append("			GROUP By a.comp_no, a.wkor_id	) 						AS equipDesc,	");

        query.append("        (SELECT b.item_no                                                         ");
        query.append("            FROM TAWOEQUIP a, TAEQUIPMENT b                                       ");
        query.append("            WHERE a.comp_no = b.comp_no                                           ");
        query.append("                AND a.equip_id = b.equip_id                                       ");
        query.append("                AND a.wkor_id = x.wkor_id                                         ");
        query.append("                AND a.comp_no = x.comp_no                                         ");
        query.append("                AND ROWNUM = 1                )                   AS equipNo      ");
        query.append("       , (SELECT 																	");
        query.append("                (SELECT c.item_no FROM TAEQUIPMENT c WHERE c.comp_no = a.comp_no AND c.equip_id = b.p_equip_id)                                                            		");
        query.append("            FROM TAWOEQUIP a, TAEQUIPMENT b                                       ");
        query.append("            WHERE a.comp_no = b.comp_no                                           ");
        query.append("                AND a.equip_id = b.equip_id                                       ");
        query.append("                AND a.wkor_id = x.wkor_id                                         ");
        query.append("                AND a.comp_no = x.comp_no                                         ");
        query.append("                AND ROWNUM = 1                )                   AS pequipNo     ");
        query.append("       , (SELECT 																	");
        query.append("                (SELECT c.description FROM TAEQUIPMENT c WHERE c.comp_no = a.comp_no AND c.equip_id = b.p_equip_id)                                                            		");
        query.append("            FROM TAWOEQUIP a, TAEQUIPMENT b                                       ");
        query.append("            WHERE a.comp_no = b.comp_no                                           ");
        query.append("                AND a.equip_id = b.equip_id                                       ");
        query.append("                AND a.wkor_id = x.wkor_id                                         ");
        query.append("                AND a.comp_no = x.comp_no                                         ");
        query.append("                AND ROWNUM = 1                )                   AS pequipDesc   ");
        query.append("       , (SELECT 																	");
        query.append("                (SELECT f.description FROM TADEPT f WHERE f.comp_no = a.comp_no AND f.dept_id = (SELECT c.usage_dept FROM TAEQUIPMENT c WHERE c.comp_no = a.comp_no AND c.equip_id = b.p_equip_id))                                                            		");
        query.append("            FROM TAWOEQUIP a, TAEQUIPMENT b                                       ");
        query.append("            WHERE a.comp_no = b.comp_no                                           ");
        query.append("                AND a.equip_id = b.equip_id                                       ");
        query.append("                AND a.wkor_id = x.wkor_id                                         ");
        query.append("                AND a.comp_no = x.comp_no                                         ");
        query.append("                AND ROWNUM = 1                )                   AS pequipUsaDeptDesc		");
        query.append("       ,        																	");

        //TAWOEQUIP 의 설비가 없으면 TAWORKORDER의 eqloc_id로 설비위치를 불러옵니다.
        //설비를 등록하지 않고 위치만 등록하는 경우 불러올 때 문제가 생겨서 수정하였습니다 2017-11-03 김정우
        query.append("		CASE WHEN (SELECT count(*) 													");
        query.append("					FROM TAWOEQUIP a												");
        query.append("					WHERE a.comp_no = x.comp_no										");
        query.append("					AND a.wkor_id = x.wkor_id										");
        query.append("					) > 0 															");
        query.append("		THEN 																		");
        query.append("		(SELECT CASE WHEN (count(*) > 1)											");
        query.append("				THEN (max(c.full_desc)||(SELECT ' '||key_name||' ' 					");
        query.append("										 FROM TALANG 								");
        query.append("							 			 WHERE lang='"+lang+"'						");
        query.append("							 			  AND key_no='aFew' AND key_type='LABEL') 	");
        query.append("						  			  ||to_char(count(*)-1 )						");
        query.append("						 )															");
        query.append("              ELSE max(c.full_desc)												");
        query.append("              END																	");
        query.append("			FROM TAWOEQUIP a, TAEQUIPMENT b, TAEQLOC c								");
        query.append("			WHERE a.comp_no = b.comp_no												");
        query.append("				AND b.comp_no = c.comp_no											");
        query.append("				AND a.equip_id = b.equip_id											");
        query.append("				AND b.eqloc_id = c.eqloc_id											");
        query.append("				AND a.wkor_id = x.wkor_id											");
        query.append("				AND a.comp_no = x.comp_no											");
        query.append("			GROUP By a.comp_no, a.wkor_id	) 										");
        query.append("		ELSE (SELECT a.full_desc													");
        query.append("				FROM TAEQLOC a														");
        query.append("				WHERE a.comp_no = x.comp_no											");
        query.append("				AND a.eqloc_id = x.eqloc_id	)										");
        query.append("		END 																		");
        query.append("																	AS eqLocDesc,	");
        
        query.append("		x.description												wkOrDesc,		");
        query.append("		x.wo_type													woTypeId,		");
        query.append("		SFACODE_TO_DESC(x.wo_type,'WO_TYPE','SYS','','"+lang+"')					woTypeDesc,		");
        query.append("		x.pm_type													pmTypeId,		");
        query.append("		SFACODE_TO_DESC(x.pm_type,x.wo_type||'_TYPE','SYS','','"+lang+"')		pmTypeDesc,		");
        query.append("		x.shift_type												shiftTypeId,	");
        query.append("		SFACODE_TO_DESC(x.shift_type,'SHIFT_TYPE','SYS','','"+lang+"')			shiftTypeDesc,	");
        query.append("		x.dept_id													deptId,			");
        query.append("		(SELECT description															");
        query.append("		   FROM TADEPT																");
        query.append("		  WHERE comp_no = x.comp_no													");
        query.append("			AND dept_id = x.dept_id)								deptDesc,		");
        query.append("       x.wkctr_id	                             					wkCtrId,		");
        query.append("		 (SELECT description														");
        query.append("		  FROM TAWKCTR																");
        query.append("		  WHERE comp_no = x.comp_no													");
        query.append("		  AND wkctr_id = x.wkctr_id) 			 					wkCtrDesc,		");
        query.append("		x.emp_id													empId,			");
        query.append("		(SELECT emp_name															");
        query.append("		   FROM TAEMP																");
        query.append("		  WHERE comp_no = x.comp_no													");
        query.append("			AND emp_id = x.emp_id)									empDesc,		");
        query.append("		x.sub_emp_id												subEmpId,		");
        query.append("		(SELECT emp_name															");
        query.append("		   FROM TAEMP																");
        query.append("		  WHERE comp_no = x.comp_no													");
        query.append("			AND emp_id = x.sub_emp_id)								subEmpDesc,		");
        query.append("		x.start_date												startDate,		");
        query.append("		x.start_time												startTime,		");
        query.append("		x.end_date													endDate,		");
        query.append("		x.end_time													endTime,		");
        query.append("		x.work_time													workTime,		");
        query.append("		x.wkor_date													wkorDate,		");
        query.append("		x.vendor_id													vendorId,		");
        query.append("		x.labor_amt													laborAmt,		");
        query.append("      SFAIDTODESC(x.vendor_id, '', 'VENDOR', x.comp_no)           vendorDesc,  	"); 
        query.append("      x.vendor_desc                                                 vendorName,       ");
        query.append("		x.self_vendor_type											selfVendorType,	");
        query.append("		SFACODE_TO_DESC(x.self_vendor_type,'SELF_VENDOR_TYPE','SYS','','"+lang+"') selfVendorTypeDesc,");
        query.append("		x.perform													perform,		");
        query.append("		x.pmaction													pmAction,		");
        query.append("		x.wo_gen_type												woGenType,		");
        query.append("		x.eqloc_id													eqLocId,		");
        query.append("		x.p_wkor_id													parentWoId,		");
        query.append("      (SELECT description FROM TAWORKORDER a WHERE a.comp_no = x.comp_no AND a.wkor_id = x.p_wkor_id) parentWoDesc, ");
        query.append("		(SELECT wo_no FROM TAWORKORDER aa WHERE aa.comp_no = x.comp_no AND aa.wkor_id = x.p_wkor_id)	parentWoNo,");
        query.append("        (SELECT user_name                                                         			");
        query.append("           FROM TAUSER                                                            			");
        query.append("          WHERE comp_no = x.comp_no                                               			");
        query.append("            AND user_id = x.close_id)                               			closeBy,   		");
        query.append("		x.close_date															closeDate,		");
		query.append("		(SELECT c.pmcalibstdtp_id																");
		query.append("		FROM TAWOEQUIP a INNER JOIN TAEQUIPMENT b												");
		query.append("		ON a.comp_no = b.comp_no																");
		query.append("		AND a.equip_id = b.equip_id																");
		query.append("		INNER JOIN TAEQTOOL c																	");
		query.append("		ON b.comp_no = c.comp_no																");
		query.append("		AND b.equip_id = c.equip_id																");
		query.append("		WHERE 1=1																				");
		query.append("		AND a.comp_no = x.comp_no																");
		query.append("		AND a.wkor_id = x.wkor_id																");
		query.append("		AND ROWNUM=1)															pmCalibStdTpId,	");
		query.append("        x.courselist_id                                      					courselistId,   ");
        query.append("        (SELECT description FROM TACOURSELIST WHERE courselist_id=x.courselist_id)     				trainDesc   ");
        query.append(", (SELECT MAX(a.woreq_id) FROM TAWOREQRES a WHERE a.comp_no = x.comp_no AND a.wkor_id = x.wkor_id)	woReqId		");
        query.append("      ,x.eqasmb_id                                                     		eqAsmbId   		");
        query.append("      ,(SELECT full_desc                                                          			");
        query.append("         FROM TAEQASMB                                                            			");
        query.append("        WHERE comp_no = x.comp_no                                                 			");
        query.append("          AND eqasmb_id = x.eqasmb_id)                                		eqAsmbDesc  	");
        query.append("		, x.is_cleaning 														isCleaningId 	");
        query.append("		, SFACODE_TO_DESC(x.is_cleaning,'IS_USE','SYS','','"+lang+"')			isCleaning		");
        query.append("      , x.upd_date															updDate			");
        query.append("      , x.cre_date															creDate			");
        query.append("      , x.is_draft															isDraft			");
        query.append("		,( SELECT pm_id 																		");
        query.append("		   FROM TAPMSCHED  																		");
        query.append("		   WHERE comp_no=x.comp_no 																");
        query.append("		    AND wkor_id=x.wkor_id )												pmId			");
        query.append("		,( SELECT param2  																		");
        query.append("		   FROM TACDSYSD  																		");
        query.append("		   WHERE cdsysd_no=x.pm_type 															");
        query.append("		    AND list_type= x.wo_type||'_TYPE' )        							pmParam			");
        query.append("      , y.cycle    															calibCycle     	");
        query.append("      , y.period_type    														periodTypeId    ");
        query.append("      , SFACODE_TO_DESC(y.period_type, 'PERIOD_TYPE', 'SYS', '','"+lang+"') 	periodTypeDesc  ");
        
        query.append("      , x.plant                                                                   AS plant      	");
        query.append("		, (SELECT a.description																		");
        query.append("			 FROM TAPLANT a																			");
        query.append("			WHERE a.comp_no = x.comp_no																");
        query.append("			  AND a.plant = x.plant)  												AS plantDesc	");
        query.append("		, z.bef_eq_status                                                           AS currentEqStatusNo	");
        query.append("      , SFACODE_TO_DESC(z.bef_eq_status,'EQ_STATUS','SYS','','"+lang+"')         	AS currentEqStatusDesc	");
        query.append("      , z.aft_eq_status                                                           AS changedEqStatusNo	");
        query.append("      , SFACODE_TO_DESC(z.aft_eq_status,'EQ_STATUS','SYS','','"+lang+"')         	AS changedEqStatusDesc	");
        query.append("      , z.bef_eqloc_no                                                            AS currentEqLocNo		");
        query.append("      , z.bef_eqloc_desc                                                        	AS currentEqLocDesc		");
        query.append("      , z.aft_eqloc_no                                                            AS changedEqLocNo		");
        query.append("      , z.aft_eqloc_desc                                                        	AS changedEqLocDesc		");
        query.append("FROM TAWORKORDER x left outer join TAPMLST y													");
        query.append("ON x.pm_id = y.pm_id AND x.comp_no = y.comp_no												");
        query.append("LEFT OUTER JOIN TAWOEQUIP z ON x.wkor_id = z.wkor_id AND x.comp_no = z.comp_no		        ");
        query.append("LEFT OUTER JOIN TAEQUIPMENT c ON c.equip_id = z.equip_id AND c.comp_no = z.comp_no	        ");
        query.append("WHERE 1=1																						");
        query.append("  AND x.wkor_id = ?																			");
        query.append("  AND x.comp_no = ?																			");
    
        Object[] objects = new Object[] {
        		maWoResultMstrCommonDTO.getWkOrId(),
        		maWoResultMstrCommonDTO.getCompNo()
    	};
        
        MaWoResultMstrDetailDTO maWoResultMstrDetailDTO = 
        		(MaWoResultMstrDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new MaWoResultMstrDetailDTO()));
        
        return maWoResultMstrDetailDTO;
    }
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrCommonDTO
     * @return
     */
    public MaWoResultFailDetailDTO findFailDetail(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO)
    {
    	QueryBuffer query = new QueryBuffer();
    	String wkOrId = maWoResultMstrCommonDTO.getWkOrId();
    	String compNo = maWoResultMstrCommonDTO.getCompNo();
    	String lang   = maWoResultMstrCommonDTO.getUserLang();
    	
    	query.append("SELECT																				");
        query.append("		x.eqasmb_id														eqAsmbId,		");
        query.append("		(SELECT full_desc																");
        query.append("		   FROM TAEQASMB																");
        query.append("		  WHERE comp_no = x.comp_no														");
        query.append("			AND eqasmb_id = x.eqasmb_id) 								eqAsmbDesc,		");
        query.append("		x.fs_cd															fsCd,			");
        query.append("		(SELECT (select b.key_name														");
        query.append("            			from talang b													");
        query.append("            			where  b.lang = '"+lang+"'										");
        query.append("            			and aa.key_type = b.key_type									");
        query.append("            			and aa.key_no = b.key_no)	description							");
        query.append("		   FROM TAFAILURE aa															");
        query.append("		  WHERE aa.comp_no = x.comp_no													");
        query.append("			AND aa.failure_id = x.fs_cd) 								fsCdDesc,		");
        query.append("		x.fs_desc														fsDesc,			");
        query.append("		x.mo_cd															moCd,			");
        query.append("		(SELECT (select b.key_name														");
        query.append("            			from talang b													");
        query.append("            			where  b.lang = '"+lang+"'										");
        query.append("            			and aa.key_type = b.key_type									");
        query.append("            			and aa.key_no = b.key_no)	description							");
        query.append("		   FROM TAFAILURE aa															");
        query.append("		  WHERE aa.comp_no = x.comp_no													");
        query.append("			AND aa.failure_id = x.mo_cd) 								moCdDesc,		");
        query.append("		x.ca_cd															caCd,			");
        query.append("		x.mo_desc														moDesc,			");
        query.append("		(SELECT (select b.key_name														");
        query.append("            			from talang b													");
        query.append("            			where  b.lang = '"+lang+"'										");
        query.append("            			and aa.key_type = b.key_type									");
        query.append("            			and aa.key_no = b.key_no)	description							");
        query.append("		   FROM TAFAILURE aa															");
        query.append("		  WHERE aa.comp_no = x.comp_no													");
        query.append("			AND aa.failure_id = x.ca_cd) 								caCdDesc,		");
        query.append("		x.ca_desc														caDesc,			");
        query.append("		x.re_cd															reCd,			");
        query.append("		(SELECT (select b.key_name														");
        query.append("            			from talang b													");
        query.append("            			where  b.lang = '"+lang+"'										");
        query.append("            			and aa.key_type = b.key_type									");
        query.append("            			and aa.key_no = b.key_no)	description							");
        query.append("		   FROM TAFAILURE aa															");
        query.append("		  WHERE aa.comp_no = x.comp_no													");
        query.append("			AND aa.failure_id = x.re_cd) 								reCdDesc,		");
        query.append("		x.re_desc														reDesc,			");
        query.append("		x.eqdn_start_date												eqDnStartDate,	");
        query.append("		x.eqdn_start_time												eqDnStartTime,	");
        query.append("		x.eqdn_end_date													eqDnEndDate,	");
        query.append("		x.eqdn_end_time													eqDnEndTime,	");
        query.append("		x.eqdn_work_time												eqDnWorkTime,	");
        query.append("		x.lndn_start_date												lnDnStartDate,	");
        query.append("		x.lndn_start_time												lnDnStartTime,	");
        query.append("		x.lndn_end_date													lnDnEndDate,	");
        query.append("		x.lndn_end_time													lnDnEndTime,	");
        query.append("		x.lndn_work_time												lnDnWorkTime,	");
        query.append("		x.remark														remark,			");
        query.append("		(SELECT equip_id																");
        query.append("		   FROM TAWOEQUIP																");
        query.append("		  WHERE comp_no = x.comp_no														");
        query.append("			AND wkor_id = x.wkor_id 												    ");
        query.append("			AND rownum = 1         ) 									equipId			");
        query.append("FROM   TAWOFAIL x																		");
        query.append("WHERE  x.wkor_id  = ?         														");
        query.append("  AND  x.comp_no  = ?         														");
        query.append("  AND ROWNUM = 1																		");
    
        Object[] objects = new Object[] {
        		maWoResultMstrCommonDTO.getWkOrId(),
            	maWoResultMstrCommonDTO.getCompNo()
    	};
        
//    	MaWoResultFailDetailDTO maWoResultFailDetailDTO = 
//    			(MaWoResultFailDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaWoResultFailDetailDTO()));
    	
    	MaWoResultFailDetailDTO maWoResultFailDetailDTO = 
    			(MaWoResultFailDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new MaWoResultFailDetailDTO()));
    	
    	return maWoResultFailDetailDTO;
    }
    
    /**
     * detail calib find
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrCommonDTO
     * @return
     */
    public MaWoResultPmCalDTO findCalDetail(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO)
    {
    	QueryBuffer query = new QueryBuffer();
    	String wkOrId = maWoResultMstrCommonDTO.getWkOrId();
    	String compNo = maWoResultMstrCommonDTO.getCompNo();
    	String lang   = maWoResultMstrCommonDTO.getUserLang();
    	
    	query.append("SELECT																				");
        query.append("		x.wkor_id														wkorId,			");
        query.append("		x.calib_env														calEnv,			");
        query.append("		x.calib_corp													calCorpId,		");
        query.append("		SFACODE_TO_DESC(x.calib_corp,'CALIB_CORP','USR','"+compNo+"','"+lang+"')	calCorpDesc,	");
        query.append("		x.calib_type													calTypeId,		");
        query.append("		SFACODE_TO_DESC(x.calib_type,'CALIB_TYPE','SYS','','"+lang+"')	calTypeDesc,	");
        query.append("		x.calib_device													calDevice,		");
        query.append("		x.calib_result_status											calRsltStatId,	");
        query.append("		SFACODE_TO_DESC(x.calib_result_status,'CALIB_RSLT_STATUS','SYS','','"+lang+"')	calRsltStatDesc,	");
        query.append("		x.calib_sopdoc_no    											calibSopdocNo, 	");
        query.append("		SFACODE_TO_DESC(x.calib_sopdoc_no,'SOP_NO','USR','"+compNo+"','"+lang+"')	calibSopdocNoDesc	");
        query.append("		,x.calib_cert_no    											calibCertNo 	");
        query.append("		,x.next_calib_date    											nextCalibDate 	");
        query.append("FROM   TAWOCALIB x																	");
        query.append("WHERE  x.wkor_id  = ?         														");
        query.append("  AND  x.comp_no  = ?         														");
        query.append("  AND ROWNUM = 1																		");
    
        Object[] objects = new Object[] {
        		wkOrId,
        		compNo
    	};
        
        MaWoResultPmCalDTO maWoResultPmCalDTO = 
    			(MaWoResultPmCalDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new MaWoResultPmCalDTO()));
    	
    	return maWoResultPmCalDTO;
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrDetailDTO
     * @return
     */
    public int insertDetail(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	if ("".equals(maWoResultMstrDetailDTO.getWoGenType()) || maWoResultMstrDetailDTO == null) {
			maWoResultMstrDetailDTO.setWoGenType("WORSLT");;
		}
    	
    	query.append("INSERT INTO TAWORKORDER							");
    	query.append("	(comp_no,		wkor_id,		wo_status,		");
    	query.append("					description,	wo_type,		");
    	query.append("	 dept_id,		pm_type,		emp_id,			");
    	query.append("	 start_date,	start_time,		end_date,		");
    	query.append("	 end_time,		work_time,		perform,		");
    	query.append("	 wo_no,         vendor_id,      self_vendor_type,");
    	query.append("   wo_gen_type,   p_wkor_id,		shift_type,		");
    	query.append("   wkor_date,		labor_amt,		eqloc_id,		");
    	query.append("   pmaction,		wkctr_id,     	courselist_id,	");
    	query.append("   vendor_desc,	sub_emp_id,     plant,          ");
    	query.append("   eqasmb_id,     is_cleaning,    upd_date,       ");
    	query.append("   cre_date,      upd_by,			cre_by,			");
    	query.append("   is_draft										");
    	query.append("	)	VALUES										");
    	query.append("	(?,				?,				?,				");
    	query.append("	 				?,				?,				");
    	query.append("	 ?,				?,				?,				");
    	query.append("	 ?,				?,				?,				");
    	query.append("	 ?,				?,				?,				");
    	query.append("	 ?,				?,				?,				");
    	query.append("   ?,             ?,				?,				");
    	query.append("   ?,				?,				?,				");
    	query.append("   ?,				?,    			?,			    ");
    	query.append("   ?,             ?,				?,              ");
    	query.append("   ?,				?,				?,              ");
    	query.append("   ?,				?,				?,              ");
    	query.append("   ?									            ");
    	query.append("	)												");
    	
    	Object[] objects = new Object[] {
    			maWoResultMstrDetailDTO.getCompNo(),
    			maWoResultMstrDetailDTO.getWkOrId(),
    			maWoResultMstrDetailDTO.getWoStatusId(),
    			maWoResultMstrDetailDTO.getWkOrDesc(),
    			maWoResultMstrDetailDTO.getWoTypeId(),
    			maWoResultMstrDetailDTO.getDeptId(),
    			maWoResultMstrDetailDTO.getPmTypeId(),
    			maWoResultMstrDetailDTO.getEmpId(),
    			maWoResultMstrDetailDTO.getStartDate(),
    			maWoResultMstrDetailDTO.getStartTime(),
    			maWoResultMstrDetailDTO.getEndDate(),
    			maWoResultMstrDetailDTO.getEndTime(),
    			maWoResultMstrDetailDTO.getWorkTime(),
    			maWoResultMstrDetailDTO.getPerform(),
    			maWoResultMstrDetailDTO.getWoNo(),
    			maWoResultMstrDetailDTO.getVendorId(),
    			maWoResultMstrDetailDTO.getSelfVendorType(),
    			maWoResultMstrDetailDTO.getWoGenType(),
    			maWoResultMstrDetailDTO.getParentWoId(),
    			maWoResultMstrDetailDTO.getShiftTypeId(),
    			maWoResultMstrDetailDTO.getWkorDate(),
    			CommonUtil.getRowMoneyToNum(maWoResultMstrDetailDTO.getLaborAmt()),
    			maWoResultMstrDetailDTO.getEqLocId(),
    			maWoResultMstrDetailDTO.getPmAction(),
    			maWoResultMstrDetailDTO.getWkCtrId(),
    			maWoResultMstrDetailDTO.getCourseListId(),
    			maWoResultMstrDetailDTO.getVendorName(),
    			maWoResultMstrDetailDTO.getSubEmpId(),
    			loginUser.getPlant(),
    			maWoResultMstrDetailDTO.getEqAsmbId(),
    			maWoResultMstrDetailDTO.getIsCleaningId(),
    			CommonUtil.getRowDateToNum(maWoResultMstrDetailDTO.getUpdDate()),
    			CommonUtil.getRowDateToNum(maWoResultMstrDetailDTO.getCreDate()),
    			loginUser.getUserId(),
    			loginUser.getUserId(),
    			maWoResultMstrDetailDTO.getIsDraft()
    	};
    	rtnValue = getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    public String selectWoequipCnt(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO){
    	QueryBuffer query = new QueryBuffer();

    	query.append("SELECT count(*) FROM TAWOEQUIP							");
    	query.append("WHERE 1=1													");
    	query.append("AND comp_no = '"+maWoResultMstrDetailDTO.getCompNo()+"' 	");
    	query.append(" AND wkor_id = "+maWoResultMstrDetailDTO.getWkOrId()+"	");
    	
    	return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    	
    }
    
    public int insertWoequip(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO)
    {
        QueryBuffer query = new QueryBuffer();

        int rtnValue  = 0;
        
        if(!"".equals(maWoResultMstrDetailDTO.getEquipId())&&maWoResultMstrDetailDTO.getEquipId()!=null){
			query.append("INSERT INTO TAWOEQUIP (																	");
        	query.append("		comp_no																				");
        	query.append("	  , woequip_id																			");
        	query.append("    , wkor_id																				");
        	query.append("    , equip_id																			");
        	query.append("    , eqctg_id																			");
        	query.append("    , description																			");
        	query.append("    , work_time																			");
        	query.append("    , eqasmb_id																			");
        	query.append("    , bef_eq_status																		");
        	query.append("    , aft_eq_status																		");
        	query.append("    , bef_eqloc_no																		");
        	query.append("    , bef_eqloc_desc																		");
        	query.append("    , aft_eqloc_no																		");
        	query.append("    , aft_eqloc_desc																		");
        	query.append(") VALUES (																				");
        	query.append("      ?																					");
        	query.append("    , SQAWOEQUIP_ID.NEXTVAL																");
        	query.append("    , ?																					");
        	query.append("    , ?																					");
        	query.append("    , (SELECT a.eqctg_id FROM TAEQUIPMENT a WHERE a.comp_no = ? AND a.equip_id = ?)		");
        	query.append("    , (SELECT a.description FROM TAEQUIPMENT a WHERE a.comp_no = ? AND a.equip_id = ?)	");
        	query.append("    , ?																					");
        	query.append("    , ?																					");
        	query.append("    , ?																					");
        	query.append("    , ?																					");
        	query.append("    , ?																					");
        	query.append("    , ?																					");
        	query.append("    , ?																					");
        	query.append("    , ?																					");
        	query.append(")																							");

            Object[] objects = new Object[] {
                     maWoResultMstrDetailDTO.getCompNo()
                   , maWoResultMstrDetailDTO.getWkOrId()
                   , maWoResultMstrDetailDTO.getEquipId()
                   , maWoResultMstrDetailDTO.getCompNo()
                   , maWoResultMstrDetailDTO.getEquipId()
                   , maWoResultMstrDetailDTO.getCompNo()
                   , maWoResultMstrDetailDTO.getEquipId()
                   , maWoResultMstrDetailDTO.getWorkTime()
                   , maWoResultMstrDetailDTO.getEqAsmbId()
                   , maWoResultMstrDetailDTO.getCurrentEqStatusNo()
                   , maWoResultMstrDetailDTO.getChangedEqStatusNo()
                   , maWoResultMstrDetailDTO.getCurrentEqLocNo()
                   , maWoResultMstrDetailDTO.getCurrentEqLocDesc()
                   , maWoResultMstrDetailDTO.getChangedEqLocNo()
                   , maWoResultMstrDetailDTO.getChangedEqLocDesc()
        	};
            rtnValue = this.getJdbcTemplate().update(query.toString(), objects);
        }
        return rtnValue;
    }
    
    public int updateWoequip(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO)
    {
        QueryBuffer query = new QueryBuffer();
        
        int rtnValue  = 0;
        
        if(!"".equals(maWoResultMstrDetailDTO.getEquipId())&&maWoResultMstrDetailDTO.getEquipId()!=null){
			query.append("UPDATE TAWOEQUIP SET          ");
        	query.append("       comp_no          	= ?	");
        	query.append("     , equip_id         	= ? ");
        	query.append("     , eqctg_id         	= (SELECT a.eqctg_id FROM TAEQUIPMENT a WHERE a.comp_no = ? AND a.equip_id = ?)   	");
        	query.append("     , description      	= (SELECT a.description FROM TAEQUIPMENT a WHERE a.comp_no = ? AND a.equip_id = ?)	");
        	query.append("     , work_time        	= ? ");
        	query.append("     , eqasmb_id        	= ? ");
        	query.append("     , bef_eq_status    	= ? ");
        	query.append("     , aft_eq_status    	= ? ");
        	query.append("     , bef_eqloc_no     	= ? ");
        	query.append("     , bef_eqloc_desc   	= ? ");
        	query.append("     , aft_eqloc_no     	= ? ");
        	query.append("     , aft_eqloc_desc		= ?	");
            query.append(" WHERE comp_no   			= ? ");
            query.append("   AND wkor_id   			= ?	");
           
            Object[] objects = new Object[] {
            		 maWoResultMstrDetailDTO.getCompNo()
                   , maWoResultMstrDetailDTO.getEquipId()
                   , maWoResultMstrDetailDTO.getCompNo()
                   , maWoResultMstrDetailDTO.getEquipId()
                   , maWoResultMstrDetailDTO.getCompNo()
                   , maWoResultMstrDetailDTO.getEquipId()
                   , maWoResultMstrDetailDTO.getWorkTime()
                   , maWoResultMstrDetailDTO.getEqAsmbId()
                   , maWoResultMstrDetailDTO.getCurrentEqStatusNo()
                   , maWoResultMstrDetailDTO.getChangedEqStatusNo()
                   , maWoResultMstrDetailDTO.getCurrentEqLocNo()
                   , maWoResultMstrDetailDTO.getCurrentEqLocDesc()
                   , maWoResultMstrDetailDTO.getChangedEqLocNo()
                   , maWoResultMstrDetailDTO.getChangedEqLocDesc()
                   , maWoResultMstrDetailDTO.getCompNo()
                   , maWoResultMstrDetailDTO.getWkOrId()
        	};
        	rtnValue = this.getJdbcTemplate().update(query.toString(), objects);
        }
        return rtnValue;
    }
    
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrDetailDTO
     * @return
     */
    public int updateDetail(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	
    	query.append("UPDATE TAWORKORDER SET		");
    	query.append("	description			= ?,	");
    	query.append("  wo_status           = ?,    ");
    	query.append("	wo_type				= ?,	");
    	query.append("	dept_id				= ?,	");
    	query.append("	pm_type				= ?,	");
    	query.append("	emp_id				= ?,	");
    	query.append("	start_date			= ?,	");
    	query.append("	start_time			= ?,	");
    	query.append("	end_date			= ?,	");
    	query.append("	end_time			= ?,	");
    	query.append("	work_time			= ?,	");
    	query.append("	self_vendor_type	= ?,	");
    	query.append("	vendor_id			= ?,	");
    	query.append("	perform				= ?,	");
    	query.append("	pmaction			= ?,	");
    	query.append("	wkor_date			= ?,	");
    	query.append("	labor_amt			= ?,	");
    	query.append("	shift_type			= ?,	");
    	query.append("	p_wkor_id			= ?,	");
    	query.append("	eqloc_id			= ?,	");
    	query.append("	wkctr_id			= ?,	");
    	query.append("  courselist_id       = ?,    ");
    	query.append("  vendor_desc         = ?,    ");
    	query.append("  sub_emp_id          = ?,    ");
    	query.append("  eqasmb_id           = ?,    ");
    	query.append("  is_cleaning         = ?,    ");
    	query.append("  upd_date	        = ?,    ");
    	query.append("  upd_by              = ?,    ");
    	query.append("  is_draft            = ?     ");
    	query.append("WHERE wkor_id	 		= ?		");
    	query.append("  AND comp_no			= ?		");
    	
    	Object[] objects = new Object[] {
    			maWoResultMstrDetailDTO.getWkOrDesc(),
    			maWoResultMstrDetailDTO.getWoStatusId(),
    			maWoResultMstrDetailDTO.getWoTypeId(),
    			maWoResultMstrDetailDTO.getDeptId(),
    			maWoResultMstrDetailDTO.getPmTypeId(),
    			maWoResultMstrDetailDTO.getEmpId(),
    			maWoResultMstrDetailDTO.getStartDate(),
    			maWoResultMstrDetailDTO.getStartTime(),
    			maWoResultMstrDetailDTO.getEndDate(),
    			maWoResultMstrDetailDTO.getEndTime(),
    			maWoResultMstrDetailDTO.getWorkTime(),
    			maWoResultMstrDetailDTO.getSelfVendorType(),
    			maWoResultMstrDetailDTO.getVendorId(),
    			maWoResultMstrDetailDTO.getPerform(),
    			maWoResultMstrDetailDTO.getPmAction(),
    			maWoResultMstrDetailDTO.getWkorDate(),
    			CommonUtil.getRowMoneyToNum(maWoResultMstrDetailDTO.getLaborAmt()),
    			maWoResultMstrDetailDTO.getShiftTypeId(),
    			maWoResultMstrDetailDTO.getParentWoId(),
    			maWoResultMstrDetailDTO.getEqLocId(),
    			maWoResultMstrDetailDTO.getWkCtrId(),
    			maWoResultMstrDetailDTO.getCourseListId(),
    			maWoResultMstrDetailDTO.getVendorName(),
    			maWoResultMstrDetailDTO.getSubEmpId(),
    			maWoResultMstrDetailDTO.getEqAsmbId(),
    			maWoResultMstrDetailDTO.getIsCleaningId(),
    			CommonUtil.getRowDateToNum(maWoResultMstrDetailDTO.getUpdDate()),
    			loginUser.getUserId(),
    			maWoResultMstrDetailDTO.getIsDraft(),
    			maWoResultMstrDetailDTO.getWkOrId(),
    			maWoResultMstrDetailDTO.getCompNo()
    	};
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultFailDetailDTO
     * @param maWoResultMstrCommonDTO
     * @return
     */
    public int updateFailDetail(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultFailDetailDTO maWoResultFailDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("MERGE INTO TAWOFAIL a																							");
    	query.append("USING(	SELECT 	? eqAsmbId,																					");
    	query.append("					? fsCd,																						");
    	query.append("					? fsDesc,																					");
    	query.append("					? moCd,																						");
    	query.append("					? moDesc,																					");
    	query.append("					? caCd,																						");
    	query.append("					? caDesc,																					");
    	query.append("					? reCd,																						");
    	query.append("					? reDesc,																					");
    	query.append("					? eqDnStartDate,																			");
    	query.append("					? eqDnStartTime,																			");
    	query.append("					? eqDnEndDate,																				");
    	query.append("					? eqDnEndTime,																				");
    	query.append("					? eqDnWorkTime,																				");
    	query.append("					? lnDnStartDate,																			");
    	query.append("					? lnDnStartTime,																			");
    	query.append("					? lnDnEndDate,																				");
    	query.append("					? lnDnEndTime,																				");
    	query.append("					? lnDnWorkTime,																				");
    	query.append("					? remark,																					");
    	query.append("					? wkOrId,																					");
    	query.append("					? compNo																					");
    	query.append("			FROM DUAL) b																						");
    	query.append("ON(	a.comp_no = b.compNo																					");
    	query.append("	AND a.wkor_id = b.wkOrId	)																				");
    	query.append("WHEN MATCHED THEN																								");
    	query.append("	UPDATE SET 	a.eqasmb_id = b.eqAsmbId,																		");
    	query.append("				a.fs_cd = b.fsCd,																				");
    	query.append("				a.fs_desc = b.fsDesc,																			");
    	query.append("				a.mo_cd = b.moCd,																				");
    	query.append("				a.mo_desc = b.moDesc,																			");
    	query.append("				a.ca_cd = b.caCd,																				");
    	query.append("				a.ca_desc = b.caDesc,																			");
    	query.append("				a.re_cd = b.reCd,																				");
    	query.append("				a.re_desc = reDesc,																				");
    	query.append("				a.eqdn_start_date = b.eqDnStartDate,															");
    	query.append("				a.eqdn_start_time = b.eqDnStartTime,															");
    	query.append("				a.eqdn_end_date = b.eqDnEndDate,																");
    	query.append("				a.eqdn_end_time = b.eqDnEndTime,																");
    	query.append("				a.eqdn_work_time = b.eqDnWorkTime,																");
    	query.append("				a.lndn_start_date = b.lnDnStartDate,															");
    	query.append("				a.lndn_start_time = b.lnDnStartTime,															");
    	query.append("				a.lndn_end_date = b.lnDnEndDate,																");
    	query.append("				a.lndn_end_time = b.lnDnEndTime,																");
    	query.append("				a.lndn_work_time = b.lnDnWorkTime,																");
    	query.append("				a.remark	= b.remark																			");
    	query.append("WHEN NOT MATCHED THEN																							");
    	query.append("	INSERT (a.comp_no,			a.wofail_id,		a.wkor_id,			a.eqasmb_id, 		a.ca_cd,			");
    	query.append("			a.ca_desc, 			a.re_cd,			a.re_desc,			a.eqdn_start_date,	a.eqdn_start_time,	");
    	query.append("			a.eqdn_end_date,	a.eqdn_end_time,	a.eqdn_work_time,	a.lndn_start_date,	a.lndn_start_time,	");
    	query.append("			a.lndn_end_date,	a.lndn_end_time,	a.lndn_work_time,	a.remark,							 	");
    	query.append("			a.mo_cd,			a.mo_desc,          a.fs_cd,			a.fs_desc                     )		    ");
    	query.append("	VALUES (b.compNo,			SQAWOFAIL_ID.nextval,b.wkOrId,			b.eqAsmbId,			b.caCd,				");
    	query.append("			b.caDesc,			b.reCd,				b.reDesc,			b.eqDnStartDate,	b.eqDnStartTime,	");
    	query.append("			b.eqDnEndDate,		b.eqDnEndTime,		b.eqDnWorkTime,		b.lnDnStartDate,	b.lnDnStartTime,	");
    	query.append("			b.lnDnEndDate,		b.lnDnEndTime,		b.lnDnWorkTime,		b.remark,								");
    	query.append("			b.moCd,				b.moDesc,           b.fsCd,				b.fsDesc       )						");
    	
    	Object[] objects = new Object[] {
    			maWoResultFailDetailDTO.getEqAsmbId(),
    			maWoResultFailDetailDTO.getFsCd(),
    			maWoResultFailDetailDTO.getFsDesc(),
    			maWoResultFailDetailDTO.getMoCd(),
    			maWoResultFailDetailDTO.getMoDesc(),
    			maWoResultFailDetailDTO.getCaCd(),
    			maWoResultFailDetailDTO.getCaDesc(),
    			maWoResultFailDetailDTO.getReCd(),
    			maWoResultFailDetailDTO.getReDesc(),
    			maWoResultFailDetailDTO.getEqDnStartDate(),
    			maWoResultFailDetailDTO.getEqDnStartTime(),
    			maWoResultFailDetailDTO.getEqDnEndDate(),
    			maWoResultFailDetailDTO.getEqDnEndTime(),
    			maWoResultFailDetailDTO.getEqDnWorkTime(),
    			maWoResultFailDetailDTO.getLnDnStartDate(),
    			maWoResultFailDetailDTO.getLnDnStartTime(),
    			maWoResultFailDetailDTO.getLnDnEndDate(),
    			maWoResultFailDetailDTO.getLnDnEndTime(),
    			maWoResultFailDetailDTO.getLnDnWorkTime(),
    			maWoResultFailDetailDTO.getRemark(),
    			maWoResultMstrCommonDTO.getWkOrId(),
    			maWoResultMstrCommonDTO.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail calib update
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailDAO.java,v 1.0 2015/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultPmCalDTO
     * @param maWoResultMstrCommonDTO
     * @return
     */
    public int updateCalDetail(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultPmCalDTO maWoResultPmCalDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("MERGE INTO TAWOCALIB a											");
    	query.append("USING(	SELECT 	? 							calEnv				");
    	query.append("					,? 							calCorpId			");
    	query.append("					,? 							calTypeId			");
    	query.append("					,? 							calRsltStatId		");
    	query.append("					,? 							wkOrId				");
    	query.append("					,? 							calibSopdocNo    	");
    	query.append("					,? 							compNo				");
    	query.append("					,? 							calibCertNo			");
    	query.append("					,? 							nextCalibDate		");
    	query.append("                  ,?                          periodType          ");
    	query.append("                  ,?                          cycle               ");
    	query.append("			FROM DUAL) b											");
    	query.append("ON(	a.comp_no = b.compNo										");
    	query.append("	AND a.wkor_id = b.wkOrId	)									");
    	query.append("WHEN MATCHED THEN													");
    	query.append("	UPDATE SET 	a.calib_env = b.calEnv								");
    	query.append("				,a.calib_corp = b.calCorpId							");
    	query.append("				,a.calib_type = b.calTypeId							");
    	query.append("				,a.calib_sopdoc_no = b.calibSopdocNo			    ");
    	query.append("				,a.calib_result_status = b.calRsltStatId			");
    	query.append("				,a.calib_cert_no = b.calibCertNo					");
    	query.append("				,a.next_calib_date = b.nextCalibDate				");
    	query.append("              ,a.period_type = b.periodType                       ");
    	query.append("              ,a.cycle = b.cycle                                  ");
    	query.append("WHEN NOT MATCHED THEN														");
    	query.append("	INSERT (a.comp_no			,a.wkor_id			,a.calib_env			");
    	query.append("			,a.calib_corp		,a.calib_type		,a.calib_result_status	");
    	query.append("			,a.calib_sopdoc_no	,a.calib_cert_no	,a.next_calib_date		");
    	query.append("          ,a.period_type      ,a.cycle                                    ");
    	query.append("			)																");
    	query.append("	VALUES (b.compNo			,b.wkOrId			,b.calEnv				");
    	query.append("			,b.calCorpId		,b.calTypeId		,b.calRsltStatId	 	");
    	query.append("			,b.calibSopdocNo	,b.calibCertNo 		,b.nextCalibDate		");
    	query.append("          ,b.periodType       ,b.cycle                                    ");
    	query.append("			)																");
    	
    	Object[] objects = new Object[] {
    			maWoResultPmCalDTO.getCalEnv()
    			,maWoResultPmCalDTO.getCalCorpId()
    			,maWoResultPmCalDTO.getCalTypeId()
    			,maWoResultPmCalDTO.getCalRsltStatId()
    			,maWoResultMstrCommonDTO.getWkOrId()
    			,maWoResultPmCalDTO.getCalibSopdocNo()
    			,maWoResultMstrCommonDTO.getCompNo()
    			,maWoResultPmCalDTO.getCalibCertNo()
    			,maWoResultPmCalDTO.getNextCalibDate()
    			,maWoResultPmCalDTO.getPeriodTypeId()
    			,maWoResultPmCalDTO.getCalibCycle()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrDetailDTO
     * @return
     */
    public int completeDetail(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO, String woStaus)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query.append("UPDATE TAWORKORDER SET		");
    	query.append("	wo_status			= ?,	");
    	query.append("	upd_by			    = ?,	");
    	query.append("	upd_date			= ?,	");
    	query.append("	emp_id  			= NVL(emp_id,?),  	");
    	query.append("	close_id			= ?,  	");
    	query.append("	close_date			= to_char(sysdate,'yyyymmdd')  	");
    	query.append("WHERE wkor_id	 		= ?		");
    	query.append("  AND comp_no			= ?		");
    	
    	Object[] objects = new Object[] {
    			woStaus,
    			maWoResultMstrDetailDTO.getEnterBy(),
    			CommonUtil.getRowDateToNum(maWoResultMstrDetailDTO.getUpdDate()),
    			maWoResultMstrDetailDTO.getLoginUser()!=null?maWoResultMstrDetailDTO.getLoginUser().getEmpId():"",
    			maWoResultMstrDetailDTO.getEnterBy(),
    			maWoResultMstrDetailDTO.getWkOrId(),
    			maWoResultMstrDetailDTO.getCompNo()
    	};
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
        
    /**
     * update pm sched
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrDetailDTO
     * @return
     */
    public int updatePmSched(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query.append("UPDATE TAPMSCHED	 SET								");
    	query.append("	 pmsched_status		= 'C'							");
    	query.append("	,actual_date		= ?	                            ");
    	query.append("	,actual_time		= to_char(sysdate,'HH24MI')		");
    	query.append("	,check_by			= ?								");
    	query.append("	,wo_status			= 'C'							");
    	query.append("WHERE wkor_id	 		= ?								");
    	query.append("  AND comp_no			= ?								");
    	
    	Object[] objects = new Object[] {
    			maWoResultMstrDetailDTO.getWkorDate().length() > 8?CommonUtil.convertDate(maWoResultMstrDetailDTO.getWkorDate()):maWoResultMstrDetailDTO.getWkorDate(),
    			maWoResultMstrDetailDTO.getEnterBy(),
    			maWoResultMstrDetailDTO.getWkOrId(),
    			maWoResultMstrDetailDTO.getCompNo()
    	};
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    public int insertWorkFmea(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	String woFmeaId = getNextSequence("SQAWOFMEA_ID");
    	
    	query.append("INSERT INTO TAWOFMEA(				");
    	query.append("		comp_no,	wofmea_id,	wkor_id,	wofmea_no,	wofmea_status		");
    	query.append("		,equip_id,	eqloc_id,	req_date,	req_dept,	req_by,repair		");
    	query.append("		)																	");
    	query.append("VALUES(?,			?,			?,			?,			?					");
    	query.append("		,?,			?,	to_char(sysdate,'yyyymmdd'),	?,		?,?			");
    	query.append("		)																	");
    	
    	Object[] objects = new Object[] {
    			maWoResultMstrDetailDTO.getCompNo()
    			,woFmeaId
    			,maWoResultMstrDetailDTO.getWkOrId()
    			,woFmeaId
    			,"WRT"
    			,maWoResultMstrDetailDTO.getEquipId()
    			,maWoResultMstrDetailDTO.getEqLocId()
    			,maWoResultMstrDetailDTO.getDeptId()
    			,maWoResultMstrDetailDTO.getEmpId()
    			,maWoResultMstrDetailDTO.getPerform()
    	};
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    public int deleteWorkFmea(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query.append("DELETE FROM TAWOFMEA 				");
    	query.append("WHERE COMP_NO = ?  				");
    	query.append("    AND WKOR_ID = ? 				");
    	query.append("    AND WOFMEA_STATUS = ?     	");
    	
    	Object[] objects = new Object[] {
    			maWoResultMstrDetailDTO.getCompNo()
    			,maWoResultMstrDetailDTO.getWkOrId()
    			,"WRT"
    	};
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    
    
    /**
     * create point data
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrDetailDTO
     * @return
     */
    public int createPoint(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query.append("DELETE FROM TAWOSTPOINT ");
    	query.append("WHERE comp_no = '"+maWoResultMstrDetailDTO.getCompNo()+"'");
    	query.append("AND wkor_id   = '"+maWoResultMstrDetailDTO.getWkOrId()+"'");
    	
    	rtnValue = getJdbcTemplate().update(query.toString());
    	
    	query = new QueryBuffer();
    	query.append("INSERT INTO TAWOSTPOINT												");
    	query.append("	(comp_no ,wostpoint_id,wkor_id,stwrk_point_id, st_point_rslt_status)");
    	query.append("SELECT a.comp_no, SQASTWRK_POINT_ID.NEXTVAL, ?, stwrk_point_id,'GD'	");
    	query.append("FROM TASTWRKPOINT a, TASTWRKLST b										");
    	query.append("WHERE 1=1																");
    	query.append("AND a.comp_no = b.comp_no												");
    	query.append("AND a.stwrk_id = b.stwrk_id											");
    	query.getAndQuery("a.comp_no", maWoResultMstrDetailDTO.getCompNo());
    	query.getAndQuery("a.is_active", "Y");
    	query.getAndQuery("b.is_active", "Y");
    	query.getAndQuery("b.is_force", "Y");
    	query.getAndQuery("b.pm_type", maWoResultMstrDetailDTO.getPmTypeId());
    	
    	
    	Object[] objects = new Object[] {
    			maWoResultMstrDetailDTO.getWkOrId()
    	};
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    /**
     * 정상출고내역 데이터 생성
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrDetailDTO
     * @return
     */
    public int insertPtIssList(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO, String wopartId, String ptisslistId, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query.append("INSERT INTO  TAPTISSLIST											");
    	query.append("(comp_no,				ptisslist_id,		wopart_id,				");
    	query.append(" wkor_id,				wcode_id,			part_grade,				");
    	query.append(" issue_date,			part_id,			ptiss_status,   		");
    	query.append(" issue_qty,			unit_price,			tot_price,				");
    	query.append(" ptiss_type,			plant,              rec_by,        			");
    	query.append(" rec_dept        											)		");
    	query.append("SELECT x.comp_no													");
    	query.append("		,?															");
    	query.append("		,x.wopart_id												");
    	query.append("		,x.wkor_id													");
    	query.append("		,NVL(x.wcode_id,0)											");
    	query.append("		,x.part_grade												");
    	query.append("		,TO_CHAR(sysdate,'yyyymmdd')								");
    	query.append("		,x.part_id													");
    	query.append("      ,'W'                                                        ");
    	query.append("		,NVL(x.use_qty,0)											");
    	query.append("		,NVL(x.unit_price,0)										");
    	query.append("		,NVL(x.tot_price,0)											");
    	query.append("      ,'WOISS'                                                    ");
    	query.append("		,(select aa.plant from taworkorder aa where x.comp_no = aa.comp_no and x.wkor_id = aa.wkor_id) as plant ");
    	query.append("		,(select aa.emp_id from taworkorder aa where x.comp_no = aa.comp_no and x.wkor_id = aa.wkor_id) as rec_by ");
    	query.append("		,(select aa.dept_id from taworkorder aa where x.comp_no = aa.comp_no and x.wkor_id = aa.wkor_id) as rec_dept ");
    	query.append("FROM TAWOPARTS x													");
    	query.append("WHERE comp_no			= ?											");
    	query.append("  AND wkor_id			= ?											");
    	query.append("  AND wopart_id		= ?											");
    	query.append("  and exists (select 1 from taparts aa where x.comp_no = aa.comp_no and x.part_id = aa.part_id and aa.is_stock_control = 'Y')  "); //재고관리 하는 품목만 처리..

    	
    	Object[] objects = new Object[] {
    			ptisslistId,
    			maWoResultMstrDetailDTO.getCompNo(),
    			maWoResultMstrDetailDTO.getWkOrId(),
    			wopartId
    	};
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    public int deletePtIssList(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query.append("DELETE  TAPTISSLIST		    									");
    	query.append("WHERE comp_no			= ?											");
    	query.append("  AND wkor_id     	= ?											");
    	query.append("  AND ptiss_status != 'C'                                           ");
    	
    	Object[] objects = new Object[] {
    			maWoResultMstrDetailDTO.getCompNo()
    			,maWoResultMstrDetailDTO.getWkOrId()
    	};
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    
    public void insertPtissSerialList(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO, String wopartId,String ptisslistId) 
	{
		QueryBuffer query = new QueryBuffer();
    	
		query.append("INSERT INTO TAPTISSLIST_SERIAL											        ");
		query.append("(comp_no,       ptisslist_serial_id,        ptisslist_id,      			        ");
		query.append(" part_id,       serial_no,                  equip_id)						        ");
		query.append("SELECT 																	        ");
		query.append("      x.comp_no,     SQAPTISSLIST_SERIAL_ID.nextval,      ?,				        ");
		query.append("      x.part_id,     x.in_serial_no,        				x.in_equip_id	        ");
		query.append("FROM TAWOPARTS_SERIAL x   												        ");
		query.append("WHERE comp_no			= ?											                ");
		query.append("    and x.wopart_id 	= ?													        ");
		query.append("    AND x.part_id = (SELECT a.part_id FROM TAWOPARTS a WHERE wopart_id = ? )		");
		query.append("    AND x.out_equip_id IS NOT NULL										        ");

    	Object[] objects = new Object[] {
    			ptisslistId,
    			maWoResultMstrDetailDTO.getCompNo(),
    			wopartId,
    			wopartId
    	};
    	
    	getJdbcTemplate().update(query.toString(), objects);

	}
    
    
    
    public int createPtRepairList(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query.append("insert into taptrepairlist(                                                 ");
    	query.append("    comp_no ,ptrepairlist_id ,ptrepairlist_no ,ptrepairlist_status ,wkor_id ");
    	query.append("    ,wopart_id ,reg_date ,dept_id ,request_date ,request_by                 ");
    	query.append("    ,wcode_id ,part_id ,repair_qty, part_grade  ,plant                      ");
    	query.append("    ,equip_id ,eqasmb_id                                                    ");
    	query.append(")                                                                           ");
    	query.append("select                                                                      ");
    	query.append("    a.comp_no                           as comp_no                          ");
    	query.append("    ,sqaptrepairlist_id.nextval         as ptrepairlist_id                  ");
    	query.append("    ,sqaptrepairlist_id.currval         as ptrepairlist_no                  ");
    	query.append("    ,'W'                                as ptrepairlist_status              ");
    	query.append("    ,a.wkor_id                          as wkor_id                          ");
    	query.append("    ,a.wopart_id                        as wopart_id                        ");
    	query.append("    ,to_char(sysdate,'yyyymmdd')        as reg_date                         ");
    	query.append("    ,c.dept_id                          as dept_id                          ");
    	query.append("    ,to_char(sysdate,'yyyymmdd')        as request_date                     ");
    	query.append("    ,c.emp_id                           as request_by                       ");
    	query.append("    ,a.wcode_id                         as wcode_id                         ");
    	query.append("    ,a.part_id                          as part_id                          ");
    	query.append("    ,a.use_qty                          as repair_qty                       ");
    	query.append("    ,nvl((select case when nvl(value$,'Y') = 'Y'                            ");
    	query.append("                         then  'B'                                          ");
    	query.append("                         else  (select nvl(value$,'B') from taconfig where comp_no = ? and name = 'PART_GRADE')   ");
    	query.append("                   end as part_grade                                        ");
    	query.append("         from taconfig                                                      ");
    	query.append("         where comp_no = ?                                                  ");
    	query.append("             and name = 'IS_USE_PART_GRADE'),'B') as part_grade             ");
    	query.append("	  ,c.plant										as plant		     	  ");
    	query.append("	  ,(select equip_id from tawoequip							     	      ");
    	query.append("	    where comp_no = c.comp_no 	     	                                  ");
    	query.append("	    and wkor_id = c.wkor_id)					as equip_id		     	  ");
    	query.append("	  ,c.eqasmb_id									as eqasmb_id		      ");
    	query.append("from tawoparts a, taparts b, taworkorder c                                  ");
    	query.append("where a.comp_no = b.comp_no                                                 ");
    	query.append("    and a.part_id = b.part_id                                               ");
    	query.append("    and a.comp_no = c.comp_no                                               ");
    	query.append("    and a.wkor_id = c.wkor_id                                               ");
    	query.append("    and b.part_categ = 'SPPT'                                               "); //--보전자재만 
    	query.append("    and b.mro_type = 'R'                                                    "); //--수리순환품
    	query.append("    and a.comp_no = ?                                                       ");
    	query.append("    and a.wkor_id = ?                                                       ");
    	query.append("    and a.wopart_id not in (select wopart_id from taptrepairlist where comp_no = ? and wkor_id = ? )        ");

    	
    	Object[] objects = new Object[] {
    			maWoResultMstrDetailDTO.getCompNo()
    			,maWoResultMstrDetailDTO.getCompNo()
    			,maWoResultMstrDetailDTO.getCompNo()
    			,maWoResultMstrDetailDTO.getWkOrId()
    			,maWoResultMstrDetailDTO.getCompNo()
    			,maWoResultMstrDetailDTO.getWkOrId()
    	};
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    

	public int createSerialPtRepairList(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO) {
		QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query.append("insert into taptrepairlist(                                                 	");
    	query.append("    comp_no ,ptrepairlist_id ,ptrepairlist_no ,ptrepairlist_status ,wkor_id 	");
    	query.append("    ,wopart_id ,reg_date ,dept_id ,request_date ,request_by                 	");
    	query.append("    ,wcode_id ,part_id ,repair_qty,serial_no, equip_id, plant                 ");
    	query.append(")                                                                           	");
    	query.append("select                                                                        ");
    	query.append("    a.comp_no                           as comp_no                          	");
    	query.append("    ,sqaptrepairlist_id.nextval         as ptrepairlist_id                  	");
    	query.append("    ,sqaptrepairlist_id.nextval         as ptrepairlist_no                  	");
    	query.append("    ,'W'                                as ptrepairlist_status              	");
    	query.append("    ,a.wkor_id                          as wkor_id                          	");
    	query.append("    ,a.wopart_id                        as wopart_id                        	");
    	query.append("    ,to_char(sysdate,'yyyymmdd')        as reg_date                         	");
    	query.append("    ,c.dept_id                          as dept_id                          	");
    	query.append("    ,to_char(sysdate,'yyyymmdd')        as request_date                     	");
    	query.append("    ,c.emp_id                           as request_by                       	");
    	query.append("    ,a.wcode_id                         as wcode_id                         	");
    	query.append("    ,a.part_id                          as part_id                          	");
    	query.append("    ,'1'                                as repair_qty							");
    	query.append("    ,f.serial_no 																");
    	query.append("    ,f.equip_id                												");
    	query.append("	  ,c.plant							  as plant								");
    	query.append("from tawoparts a, taparts b, taworkorder c, TAWOPARTS_SERIAL h, taeqdevice d, taequipment f                                 	");
    	query.append("where a.comp_no = b.comp_no                                                 	");
    	query.append("    and a.part_id = b.part_id                                               	");
    	query.append("    and a.comp_no = c.comp_no                                               	");
    	query.append("    and a.wkor_id = c.wkor_id						                            ");
    	query.append("    and a.wopart_id = h.wopart_id					                            ");
    	query.append("    and h.part_id = d.part_id     				                            ");
    	query.append("    and d.equip_id = f.equip_id        			                            ");
    	query.append("    and h.out_serial_no = f.serial_no      		                            ");
    	query.append("    and b.is_serial_part ='Y'                                                 ");
    	query.append("    and b.part_categ = 'SPPT'                                               	");
//    	query.append("    and b.mro_type = 'R'                                                    	");
    	query.append("    and a.comp_no = ?                                                       	");
    	query.append("    and a.wkor_id = ?                                                       	");

    	
    	Object[] objects = new Object[] {
    			maWoResultMstrDetailDTO.getCompNo(),
    			maWoResultMstrDetailDTO.getWkOrId()
    	};
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
		
	}
    
    
    /**
     * WOPART 최신단가(TAPARTS)로 수정 및 사용수량과 창고확인
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrDetailDTO
     * @return
     */
    public int updatePrice(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO, String wopartId)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query.append("UPDATE TAWOPARTS x	SET											 ");
    	query.append("	unit_price = (	SELECT NVL(last_price,0)						 ");
    	query.append("					FROM TAPARTS									 ");
    	query.append("					WHERE comp_no = x.comp_no						 ");
    	query.append("					  AND part_id = x.part_id)						 ");
    	query.append("	,tot_price = ((	SELECT NVL(last_price,0)						 ");
    	query.append("					FROM TAPARTS									 ");
    	query.append("					WHERE comp_no = x.comp_no						 ");
    	query.append("					  AND part_id = x.part_id)*NVL(use_qty,0))		 ");
    	query.append("	,wcode_id = NVL(wcode_id,(SELECT wcode_id FROM TADEPT            ");            
    	query.append("                            WHERE  comp_no = ?                     ");      
    	query.append("                             AND  dept_id = (SELECT b.dept_id 	 ");
    	query.append("                                             FROM TAEMP b 		 ");
    	query.append("                                             WHERE b.emp_id = ?))) ");
    	query.append("	,use_qty = NVL(use_qty, 0)                                       ");
        query.append("	,ptisslist_id = (SELECT ptisslist_id                             ");
        query.append("                  FROM   TAPTISSLIST                               ");
        query.append("                  WHERE comp_no = x.comp_no                        ");
        query.append("                    AND wopart_id = x.wopart_id                    ");
        //query.append("                    AND ptiss_status ! = 'X'                       ");
        query.append("                    AND wkor_id = x.wkor_id)                       ");
    	query.append("WHERE x.comp_no = ?												 ");
    	query.append("  AND x.wopart_id	= ?												 ");
    	query.append("  AND x.part_id is not null										 ");

    	Object[] objects = new Object[] {
    			maWoResultMstrDetailDTO.getCompNo(),
    			maWoResultMstrDetailDTO.getEmpId(),
    			maWoResultMstrDetailDTO.getCompNo(),
    			wopartId
    	};
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    /**
     * 부품수리 아이디 업데이트
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrDetailDTO
     * @return
     */
    public int updatePtRepairId(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query.append("UPDATE TAWOPARTS a SET						                            ");
    	query.append("	      a.ptrepairlist_id	= (						                        ");
    	query.append("		                        SELECT ptrepairlist_id 						");
    	query.append("		                        FROM TAPTREPAIRLIST							");
    	query.append("		                        WHERE wkor_id = a.wkor_id					");
    	query.append("		                            AND wopart_id = a.wopart_id				");
    	query.append("		                            AND comp_no = a.comp_no)				");
    	query.append("WHERE wkor_id	 		= ?							                        ");
    	query.append("  AND comp_no			= ?							                        ");
    	
    	Object[] objects = new Object[] {
    			maWoResultMstrDetailDTO.getWkOrId(),
    			maWoResultMstrDetailDTO.getCompNo()
    	};
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    /**
     * 설비구성자재 수정
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrDetailDTO
     * @return
     */
    public int updateEqPart(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query.append("MERGE INTO TAEQPART a													");
    	query.append("USING ( 	 SELECT														");
    	query.append("               ?          equipId                                     ");
    	query.append("				 ,x.part_id  partId										");
    	query.append("               ,NVL(x.eqasmb_id, y.eqasmb_id)  eqasmb_id              ");
    	query.append("				 ,x.comp_no compNo										");
    	query.append("			 FROM TAWOPARTS x INNER JOIN TAWORKORDER y					");
    	query.append("			   ON x.comp_no = y.comp_no AND x.wkor_id = y.wkor_id		");
    	query.append("			WHERE x.comp_no = ?											");
    	query.append("			  AND x.wkor_id = ?) b										");
    	query.append("ON (  a.comp_no = b.compNo											");
    	query.append("	AND a.equip_id = b.equipId											");
    	query.append("  and NVL(a.eqasmb_id,0) = NVL(b.eqasmb_id,0)                         ");
    	query.append("	AND a.part_id = b.partId)											");
    	query.append("WHEN NOT MATCHED THEN																					");
    	query.append("INSERT (a.comp_no, a.eqpart_id, a.equip_id, a.eqasmb_id, a.part_id, a.consist_qty, is_eqtype_part, is_use) 			");
    	query.append("VALUES (b.compNo, SQAEQPART_ID.NEXTVAL, b.equipId,b.eqasmb_id, b.partId, 1, 'N', 'Y' )			");
    	
    	Object[] objects = new Object[] {
    			maWoResultMstrDetailDTO.getEquipId(),
    			maWoResultMstrDetailDTO.getCompNo(),
    			maWoResultMstrDetailDTO.getWkOrId()

    	};
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    public int updateCancelEqPart(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	
//    	query.append("MERGE INTO TAEQPART a													");
//    	query.append("USING ( 	 SELECT														");
//    	query.append("               ?          equipId                                     ");
//    	query.append("				 ,part_id    partId										");
//    	query.append("               ,eqasmb_id  eqasmb_id                                  ");
//    	query.append("				 ,comp_no compNo										");
//    	query.append("			 FROM TAWOPARTS												");
//    	query.append("			WHERE comp_no = ?											");
//    	query.append("			  AND wkor_id = ?) b										");
//    	query.append("ON (  a.comp_no = b.compNo											");
//    	query.append("	AND a.equip_id = b.equipId											");
//    	query.append("  and a.eqasmb_id = b.eqasmb_id                                       ");
//    	query.append("	AND a.part_id = b.partId)											");
//    	query.append("WHEN MATCHED THEN														");
//    	query.append("	UPDATE SET a.issue_time = NVL(a.issue_time,0) - 1, a.issue_last_date = to_char(sysdate,'yyyymmdd')	");
//    	
//    	
//    	Object[] objects = new Object[] {
//    			maWoResultMstrDetailDTO.getEquipId(),
//    			maWoResultMstrDetailDTO.getCompNo(),
//    			maWoResultMstrDetailDTO.getWkOrId()
//    			
//    	};
//    	
//    	rtnValue = getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    public int detetePtRepairList(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	
    	query.append("DELETE  taptrepairlist	    									");
    	query.append("WHERE comp_no			= ?											");
    	query.append("  AND wkor_id     	= ?											");
    	query.append("  and ptrepairlist_status = 'W'									");
    	
    	Object[] objects = new Object[] {
    			maWoResultMstrDetailDTO.getCompNo()
    			,maWoResultMstrDetailDTO.getWkOrId()
    	};
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    /**
     * 이상점검 조치결과 업데이트
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrDetailDTO
     * @return
     */
    public int updateBdPoint(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	

    	query.append("UPDATE TAWOPOINT						");
    	query.append("SET pm_point_rep_status = 'GD'		");
    	query.append("	 ,repair_date = (SELECT end_date	");
    	query.append("					FROM TAWORKORDER	");
    	query.append("					WHERE comp_no = ?	");
    	query.append("					AND wkor_id= ?)		");
    	query.append("	 ,repair_by = (SELECT emp_id		");
    	query.append("					FROM TAWORKORDER	");
    	query.append("					WHERE comp_no = ?	");
    	query.append("					AND wkor_id= ?)		");
    	query.append("	 ,repair_desc = (SELECT perform		");
    	query.append("					FROM TAWORKORDER	");
    	query.append("					WHERE comp_no = ?	");
    	query.append("					AND wkor_id= ?)		");
    	query.append("WHERE comp_no = ?						");
    	query.append("AND wopoint_id = (SELECT wopoint_id	");
    	query.append("					FROM TAWORKORDER	");
    	query.append("					WHERE comp_no = ?	");
    	query.append("					AND wkor_id= ?)		");
    	
    	
    	
    	Object[] objects = new Object[] {
    			maWoResultMstrDetailDTO.getCompNo(),
    			maWoResultMstrDetailDTO.getWkOrId(),
    			maWoResultMstrDetailDTO.getCompNo(),
    			maWoResultMstrDetailDTO.getWkOrId(),
    			maWoResultMstrDetailDTO.getCompNo(),
    			maWoResultMstrDetailDTO.getWkOrId(),
    			maWoResultMstrDetailDTO.getCompNo(),
    			maWoResultMstrDetailDTO.getCompNo(),
    			maWoResultMstrDetailDTO.getWkOrId()
    			
    	};
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    public int updateNgBdPoint(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	

    	query.append("UPDATE TAWONGPOINT					");
    	query.append("SET pm_point_rep_status = 'GD'		");
    	query.append("	 ,repair_date = (SELECT end_date	");
    	query.append("					FROM TAWORKORDER	");
    	query.append("					WHERE comp_no = ?	");
    	query.append("					AND wkor_id= ?)		");
    	query.append("	 ,repair_by = (SELECT emp_id		");
    	query.append("					FROM TAWORKORDER	");
    	query.append("					WHERE comp_no = ?	");
    	query.append("					AND wkor_id= ?)		");
    	query.append("	 ,repair_desc = (SELECT perform		");
    	query.append("					FROM TAWORKORDER	");
    	query.append("					WHERE comp_no = ?	");
    	query.append("					AND wkor_id= ?)		");
    	query.append("WHERE comp_no = ?						");
    	query.append("AND wongpoint_id = (SELECT wongpoint_id	");
    	query.append("					FROM TAWORKORDER	");
    	query.append("					WHERE comp_no = ?	");
    	query.append("					AND wkor_id= ?)		");
    	
    	
    	
    	Object[] objects = new Object[] {
    			maWoResultMstrDetailDTO.getCompNo(),
    			maWoResultMstrDetailDTO.getWkOrId(),
    			maWoResultMstrDetailDTO.getCompNo(),
    			maWoResultMstrDetailDTO.getWkOrId(),
    			maWoResultMstrDetailDTO.getCompNo(),
    			maWoResultMstrDetailDTO.getWkOrId(),
    			maWoResultMstrDetailDTO.getCompNo(),
    			maWoResultMstrDetailDTO.getCompNo(),
    			maWoResultMstrDetailDTO.getWkOrId()
    			
    	};
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    
    /**
     * 리포트 바디
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public List findReportWoDetail(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO) {

		QueryBuffer query = new QueryBuffer();
		String lang = maWoResultMstrDetailDTO.getUserLang();
		
        query.append("SELECT																	");
        query.append("		x.wo_no woNo														");
        query.append("		,(SELECT description												");
        query.append("			FROM TADEPT														");
        query.append("			WHERE comp_no = x.comp_no										");
        query.append("			  AND dept_id = x.dept_id) deptDesc								");
        query.append("		,(SELECT emp_name													");
        query.append("			FROM TAEMP														");
        query.append("			WHERE comp_no = x.comp_no										");
        query.append("			  AND emp_id = x.emp_id) empdesc								");
        query.append("		,TO_CHAR(TO_DATE(x.start_date,'yyyymmdd'),'yyyy-mm-dd')||' '||		");
        query.append("			TO_CHAR(TO_DATE(x.start_time,'HH24MISS'),'HH24:MI')||' ~ '||	");
        query.append("			TO_CHAR(TO_DATE(x.end_date,'yyyymmdd'),'yyyy-mm-dd')||' '||		");
        query.append("			TO_CHAR(TO_DATE(x.end_time,'HH24MISS'),'HH24:MI') woDate		");
        query.append("		,x.work_time workTime												");
        query.append("		,x.description woDesc												");
        query.append("		,y.description 											AS eqDesc	");
        query.append("		,(SELECT full_desc													");
        query.append("			FROM TAEQLOC													");
        query.append("			WHERE comp_no = x.comp_no										");
        query.append("				AND eqloc_id = x.eqloc_id	) 				AS eqLocDesc	");
        query.append("		,SFACODE_TO_DESC(x.wo_type,'WO_TYPE','SYS','','"+lang+"') woTypeDesc				");
        query.append("		,SFACODE_TO_DESC(x.pm_type,x.wo_type||'_TYPE','SYS','','"+lang+"') pmTypeDesc	");
        query.append("		,(SELECT (SELECT (select b.key_name									");
        query.append("            			from talang b										");
        query.append("            			where  b.lang = '"+lang+"'							");
        query.append("            			and aa.key_type = b.key_type						");
        query.append("            			and aa.key_no = b.key_no)	description				");
        query.append("					FROM TAFAILURE aa										");
        query.append("					WHERE aa.comp_no = x.comp_no							");
        query.append("					  AND aa.failure_id = ca_cd 							");
        query.append("					  AND aa.fail_type='CA')								");
        query.append("			FROM TAWOFAIL													");
        query.append("			WHERE comp_no = x.comp_no										");
        query.append("			  AND wkor_id = x.wkor_id) caCdDesc								");
        query.append("		,(SELECT (SELECT (select b.key_name									");
        query.append("            			from talang b										");
        query.append("            			where  b.lang = '"+lang+"'							");
        query.append("            			and aa.key_type = b.key_type						");
        query.append("            			and aa.key_no = b.key_no)	description				");
        query.append("					FROM TAFAILURE aa										");
        query.append("					WHERE aa.comp_no = x.comp_no							");
        query.append("					  AND aa.failure_id = re_cd								");
        query.append("					  AND aa.fail_type='RE')								");
        query.append("			FROM TAWOFAIL													");
        query.append("			WHERE comp_no = x.comp_no										");
        query.append("			  AND wkor_id = x.wkor_id) reCdDesc								");
        query.append("		,x.perform perform													");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='woResultReport' AND key_type='LABEL') woResultReport		");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='woDate' AND key_type='LABEL') workDate						");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='woEquip' AND key_type='LABEL') woEquip						");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='costTime' AND key_type='LABEL') costTime					");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='checkPoint' AND key_type='LABEL') checkPoint				");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='woName' AND key_type='LABEL') woName						");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='eqLocName' AND key_type='LABEL') equipLoc					");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='woType' AND key_type='LABEL') woType						");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='woRemark' AND key_type='LABEL') woRemark					");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='woCraft' AND key_type='LABEL') workCraft					");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='insertPart' AND key_type='LABEL') insertPart				");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='dept' AND key_type='LABEL') dept							");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='manager' AND key_type='LABEL') manager						");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='reason' AND key_type='LABEL') reason						");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='pmPoint' AND key_type='LABEL') pmPoint						");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='equipNo' AND key_type='LABEL') equipNo						");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='equipName' AND key_type='LABEL') equipName					");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='empNumber' AND key_type='LABEL') empNumber					");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='name' AND key_type='LABEL') empName							");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='startDate' AND key_type='LABEL') startDate					");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='endDate' AND key_type='LABEL') endDate						");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='costTime' AND key_type='LABEL') costTime2					");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='remark' AND key_type='LABEL') remark						");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='partNo' AND key_type='LABEL') partNumber					");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='partNameSize' AND key_type='LABEL') partNameSize			");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='useQty' AND key_type='LABEL') useQty						");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='seqNo' AND key_type='LABEL') seqNo							");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='asmbInspect' AND key_type='LABEL') asmbInspect				");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='checkMethod' AND key_type='LABEL') checkMethod				");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='fitBasis' AND key_type='LABEL') fitBasis					");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='checkType' AND key_type='LABEL') checkType					");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='pmPointRsltStatus' AND key_type='LABEL') pmPointRsltStatus	");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='checkValUom' AND key_type='LABEL') checkValUom				");
        query.append("		,(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='resultVal' AND key_type='LABEL') resultVal					");
        query.append("FROM TAWORKORDER x  LEFT OUTER JOIN (SELECT                                                         		");
        query.append("                                     a.comp_no                                                    		");
        query.append("                                     , a.wkor_id                                                    		");
        query.append("                                     , b.description                                                		");
        query.append("                                     , b.sub_mng_id                                                		");
        query.append("                                     , b.eqctg_id                                                    		");
        query.append("                                     , b .equip_id                                                   		");
        query.append("                                     , b.item_no                                                  		");
        query.append("                                     , b.eqloc_id                                                 		");
        query.append("                                     , b.old_eq_no                                                		");
        query.append("                                     , b.eqctg_type                                                		");
        query.append("                                    FROM TAWOEQUIP a                                                 		");
        query.append("                                    INNER JOIN TAEQUIPMENT b                                      		");
        query.append("                                    ON a.comp_no = b.comp_no                                        		");
        query.append("                                    AND a.equip_id = b.equip_id                                    		");
        query.append("                                     ) y                                                           		");
        query.append("ON x.comp_no = y.comp_no                                                                             		");
        query.append("AND x.wkor_id = y.wkor_id                                                                            		");
        query.append("WHERE 1=1                                                                 ");
        query.append("	AND x.comp_no = '"+maWoResultMstrDetailDTO.getCompNo()+"'				");
        query.append("  AND x.wkor_id = '"+maWoResultMstrDetailDTO.getWkOrId()+"'				");

		return getJdbcTemplate().queryForList(query.toString());
	}
    
    /**
     * 리포트 작업자
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public List findReportWoCraftDetail(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO) {

		QueryBuffer query = new QueryBuffer();

        query.append("SELECT																		");
        query.append("		(SELECT emp_no															");
        query.append("			FROM TAEMP															");
        query.append("			WHERE comp_no = x.comp_no											");
        query.append("			  AND emp_id = x.emp_id) craftEmpNo									");
        query.append("		,(SELECT emp_name														");
        query.append("			FROM TAEMP															");
        query.append("			WHERE comp_no = x.comp_no											");
        query.append("			  AND emp_id = x.emp_id) craftEmpDesc								");
        query.append("		,TO_CHAR(TO_DATE(x.start_date,'yyyymmdd'),'yyyy-mm-dd')||' '||			");
        query.append("			TO_CHAR(TO_DATE(x.start_time,'HH24MISS'),'HH24:MI') craftStartDate	");
        query.append("		,TO_CHAR(TO_DATE(x.end_date,'yyyymmdd'),'yyyy-mm-dd')||' '||			");
        query.append("			TO_CHAR(TO_DATE(x.end_time,'HH24MISS'),'HH24:MI') craftEndDate		");
        query.append("		,TO_CHAR(x.work_time) craftWorkTime										");
        query.append("		,x.remark craftRemark													");
        query.append("FROM TAWOCRAFT x																");
        query.append("WHERE x.comp_no = '"+maWoResultMstrDetailDTO.getCompNo()+"'					");
        query.append("  AND x.wkor_id = '"+maWoResultMstrDetailDTO.getWkOrId()+"'					");
        query.append("UNION ALL																		");
        query.append("SELECT '','','','','','' FROM DUAL											");

		return getJdbcTemplate().queryForList(query.toString());
	}
    
    /**
     * 리포트 투입부품
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public List findReportWoPartDetail(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO) {

		QueryBuffer query = new QueryBuffer();

        query.append("SELECT														");
        query.append("		y.part_no partNo										");
        query.append("		,y.description||', '||y.pt_size partDesc				");
        query.append("		,TO_CHAR(x.use_qty) partUseQty							");
        query.append("FROM TAWOPARTS x, TAPARTS y									");
        query.append("WHERE x.comp_no = y.comp_no									");
        query.append("  AND x.part_id = y.part_id									");
        query.append("  AND x.comp_no = '"+maWoResultMstrDetailDTO.getCompNo()+"'	");
        query.append("  AND x.wkor_id = '"+maWoResultMstrDetailDTO.getWkOrId()+"'	");
        query.append("UNION ALL														");
        query.append("SELECT '','','' FROM DUAL										");

		return getJdbcTemplate().queryForList(query.toString());
	}
    /**
     * 리포트 검사항목
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param maEqMstrDetailDTO
     * @return
     */
    public List findReportWoPointDetail(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO) {

		QueryBuffer query = new QueryBuffer();
		String lang = maWoResultMstrDetailDTO.getUserLang();

        query.append("SELECT TO_CHAR(y.step_num) AS POSEQNO,											");
        query.append("		(SELECT full_desc															");
        query.append("		  FROM TAEQASMB																");
        query.append("		  WHERE comp_no = x.comp_no													");
        query.append("		  AND eqasmb_id = y.eqasmb_id)||'/'||										");
        query.append("		y.check_point AS POCHECKPOINT,												");
        query.append("		y.check_method||'/'||y.fit_basis AS POFITBASIS,								");
        query.append("		SFACODE_TO_DESC(y.check_type,'CHECK_TYPE','SYS',x.comp_no,'"+lang+"') AS POCHECKTYPE,		");
        query.append("		y.check_min||'/'||y.check_basis_val||'/'||y.check_max||'('||y.uom||')' AS POUOM,			");
        query.append("		DECODE(z.wo_status,'C',														");
        query.append("		SFACODE_TO_DESC(x.pm_point_rslt_status,'PM_POINT_RSLT_STATUS','SYS',x.comp_no,'"+lang+"'),	");
        query.append("		'')||'' AS POSTATUS,														");
        query.append("		DECODE(z.wo_status,'C', x.result_value ,'')||'' AS POVALUE					");
        query.append("FROM TAWOPOINT x, TAPMPOINT y, TAWORKORDER z										");
        query.append("WHERE x.comp_no = y.comp_no														");
        query.append("AND x.comp_no = z.comp_no															");
        query.append("AND x.wkor_id = z.wkor_id															");
        query.append("	AND x.pm_point_id = y.pm_point_id												");
        query.getAndQuery("x.wkor_id", maWoResultMstrDetailDTO.getWkOrId());
        query.getAndQuery("x.comp_no", maWoResultMstrDetailDTO.getCompNo());
        query.append("UNION ALL																			");
        query.append("SELECT '','','','','','','' FROM DUAL												");

		return getJdbcTemplate().queryForList(query.toString());
	}
    
    /**
     * 리포트 작업설비항목
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param maEqMstrDetailDTO
     * @return
     */
    public List findReportWoEqDetail(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO) {

		QueryBuffer query = new QueryBuffer();

		query.append("SELECT (SELECT item_no 								");
        query.append("			FROM TAEQUIPMENT 							");
        query.append("			WHERE comp_no = x.comp_no					");
        query.append("			AND   equip_id = x.equip_id )  itemNumber	");
        query.append("		,(SELECT description 							");
        query.append("			FROM TAEQUIPMENT 							");
        query.append("			WHERE comp_no = x.comp_no					");
        query.append("			AND   equip_id = x.equip_id )  itemDesc		");
        query.append("FROM TAWOEQUIP x										");
        query.append("WHERE 1=1												");
        query.getAndQuery("x.wkor_id", maWoResultMstrDetailDTO.getWkOrId());
        query.getAndQuery("x.comp_no", maWoResultMstrDetailDTO.getCompNo());

		return getJdbcTemplate().queryForList(query.toString());
	}
    /**
     * 리포트 필수검사항목
     * @author kim21017
     * @version $Id: MaWoResultMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param maEqMstrDetailDTO
     * @return
     */
    public List findReportWoStPointDetail(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO) {

		QueryBuffer query = new QueryBuffer();
		String lang = maWoResultMstrDetailDTO.getUserLang();

        query.append("SELECT TO_CHAR(y.step_num) AS STSEQNO,											");
        query.append("		(SELECT full_desc															");
        query.append("		  FROM TAEQASMB																");
        query.append("		  WHERE comp_no = x.comp_no													");
        query.append("		  AND eqasmb_id = y.eqasmb_id)||'/'||										");
        query.append("		y.check_point AS STCHECKPOINT,												");
        query.append("		y.check_method||'/'||y.fit_basis AS STFITBASIS,								");
        query.append("		SFACODE_TO_DESC(y.check_type,'CHECK_TYPE','SYS',x.comp_no,'"+lang+"') AS STCHECKTYPE,	");
        query.append("		y.check_min||'/'||y.check_basis_val||'/'||y.check_max||'('||y.uom||')' AS STUOM,");
        query.append("		DECODE(z.wo_status,'C',														");
        query.append("		SFACODE_TO_DESC(x.st_point_rslt_status,'ST_POINT_RSLT_STATUS','SYS',x.comp_no,'"+lang+"') ,	");
        query.append("		'')||'' AS STSTATUS,														");
        query.append("		DECODE(z.wo_status,'C', x.result_value ,'')||'' AS STVALUE					");
        query.append("FROM TAWOSTPOINT x, TASTWRKPOINT y, TAWORKORDER z									");
        query.append("WHERE x.comp_no = y.comp_no														");
        query.append("AND x.comp_no = z.comp_no															");
        query.append("AND x.wkor_id = z.wkor_id															");
        query.append("	AND x.stwrk_point_id = y.stwrk_point_id											");
        query.getAndQuery("x.wkor_id", maWoResultMstrDetailDTO.getWkOrId());
        query.getAndQuery("x.comp_no", maWoResultMstrDetailDTO.getCompNo());
        query.append("UNION ALL																			");
        query.append("SELECT '','','','','','','' FROM DUAL												");

		return getJdbcTemplate().queryForList(query.toString());
	}
    
    /**
     * 교정 리포트 바디
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public List findPmGnlReportWoDetail(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO) {

        QueryBuffer query = new QueryBuffer();
        String lang = maWoResultMstrDetailDTO.getUserLang();
        
        query.append("SELECT                                                                                                                                                                                          ");
        query.append("        x.wo_no                                                                                                                                                  woNo,                       ");
        query.append("        NVL((SELECT description FROM TAEQUIPMENT WHERE b.p_equip_id=equip_id AND b.comp_no=comp_no),'-')           equipNameVal,            ");
        query.append("        NVL((SELECT item_no FROM TAEQUIPMENT WHERE b.p_equip_id=equip_id AND b.comp_no=comp_no),'-')               equipNoVal,               ");
        query.append("        NVL(b.description,'-')                                                                                                                              toolDescVal,              ");
        query.append("        NVL(b.item_no,'-')                                                                                                                                  toolNoVal,                 ");
        query.append("        NVL(b.maker,'-')                                                                                                                                     makerVal,                 ");
        query.append("        NVL(b.model_no,'-')                                                                                                                                 modelVal,                 ");
        query.append("        NVL((SELECT full_desc                                                                                                                                                              ");
        query.append("            FROM TAEQLOC                                                                                                                                                                   ");
        query.append("            WHERE comp_no=(SELECT comp_no FROM TAEQUIPMENT WHERE b.p_equip_id=equip_id)                                                                  ");
        query.append("            AND eqloc_id=(SELECT eqloc_id FROM TAEQUIPMENT WHERE b.p_equip_id=equip_id)),'-')                                 locationVal,              ");
        query.append("        NVL((SELECT description                                                                                                                                                           ");
        query.append("            FROM TADEPT                                                                                                                                                                     ");
        query.append("            WHERE comp_no = x.comp_no                                                                                                                                                ");
        query.append("            AND dept_id = x.dept_id),'-')                                                                                                                 manageDeptVal,        ");
        query.append("        NVL((SELECT emp_name                                                                                                                                                           ");
        query.append("            FROM TAEMP                                                                                                                                                                      ");
        query.append("            WHERE comp_no = x.comp_no                                                                                                                                                ");
        query.append("            AND emp_id = x.emp_id),'-')                                                                                                                  managerVal,             ");
        query.append("        NVL2(x.wkor_date                                                                                                                                                                    ");
        query.append("            ,SUBSTR(x.wkor_date,1,4)||' 년 '                                                                                                                                             ");
        query.append("            ||SUBSTR(x.wkor_date,5,2)||' 월 '                                                                                                                                           ");
        query.append("            ||SUBSTR(x.wkor_date,7,2)||' 일','-')                                                                                                      wkorDateVal,            ");
        query.append("        NVL(x.perform,'-')                                                                                                                                    performVal,              ");
        query.append("        NVL2(x.wkor_date                                                                                                                                                                    ");
        query.append("            ,SUBSTR(x.wkor_date,1,4)||' . '                                                                                                                                              ");
        query.append("            ||SUBSTR(x.wkor_date,5,2)||' . '                                                                                                                                            ");
        query.append("            ||SUBSTR(x.wkor_date,7,2),'-')                                                                                                             openDateVal,            ");
        query.append("        NVL((SELECT user_name                                                                                                                                                           ");
        query.append("            FROM TAUSER                                                                                                                                                                     ");
        query.append("            WHERE comp_no = x.comp_no                                                                                                                                                ");
        query.append("            AND user_id = x.close_id),'-')                                                                                                                 closeByVal,              ");
        query.append("        NVL2(x.close_date                                                                                                                                                                    ");
        query.append("            ,SUBSTR(x.close_date,1,4)||' . '                                                                                                                                              ");
        query.append("            ||SUBSTR(x.close_date,5,2)||' . '                                                                                                                                            ");
        query.append("            ||SUBSTR(x.close_date,7,2),'-')                                                                                                             closeDateVal,            ");
        query.append("        NVL((SELECT measure_unit                                                                                                                                                        ");
        query.append("            FROM TAEQTOOL                                                                                                                                                                  ");
        query.append("            WHERE comp_no = b.comp_no                                                                                                                                                ");
        query.append("            AND b.equip_id=equip_id),'-')                                                                                                                  uom,                      ");
        query.append("        NVL(y.calib_env,'-')                                                                                                                                  calEnvVal,               ");
        query.append("        NVL(SFACODE_TO_DESC(y.calib_corp,'CALIB_CORP','USR','100','"+lang+"'),'-')                                                      calCorpVal,              ");
        query.append("        NVL(SFACODE_TO_DESC(y.calib_type,'CALIB_TYPE','SYS','','"+lang+"'),'-')                                                           calTypeVal,             ");
        query.append("        NVL(SFACODE_TO_DESC(y.calib_result_status,'CALIB_RSLT_STATUS','SYS','','"+lang+"'),'-')                                    calRsltStatVal,         ");
        query.append("        NVL(y.calib_sopdoc_no,'-')                                                                                                                         calibSopdocNoVal,    ");
        query.append("        NVL(TO_CHAR((SELECT min_unit_value FROM TAEQTOOL WHERE b.equip_id=equip_id AND b.comp_no=comp_no)),'-') minUnitValue,           ");
        query.append("        NVL((SELECT all_range FROM TAEQTOOL WHERE b.equip_id=equip_id AND b.comp_no=comp_no),'-')                        calibRange,              ");
        query.append("        NVL((SELECT use_range FROM TAEQTOOL WHERE b.equip_id=equip_id AND b.comp_no=comp_no),'-')                      useRange,               ");
        query.append("        NVL((SELECT bb.cycle||SFACODE_TO_DESC(bb.period_type,'PERIOD_TYPE','SYS','','"+lang+"')                                                                ");
        query.append("            FROM TAEQPMCYCLE ba INNER JOIN TAPMLST bb                                                                                                                       ");
        query.append("            ON ba.pm_id=bb.pm_id and ba.comp_no=bb.comp_no                                                                                                                  ");
        query.append("            WHERE ba.comp_no = b.comp_no                                                                                                                                             ");
        query.append("            AND b.equip_id=ba.equip_id),'-')                                                                                                              calibCycleVal,           ");
        query.append("        NVL2((SELECT next_sch_date FROM TAPMEQUIP WHERE x.pm_id=pm_id AND x.comp_no=comp_no)                                                          ");
        query.append("            ,SUBSTR((SELECT next_sch_date FROM TAPMEQUIP WHERE x.pm_id=pm_id AND x.comp_no=comp_no),1,4)||' 년 '                                   ");
        query.append("            ||SUBSTR((SELECT next_sch_date FROM TAPMEQUIP WHERE x.pm_id=pm_id AND x.comp_no=comp_no),5,2)||' 월 '                                   ");
        query.append("            ||SUBSTR((SELECT next_sch_date FROM TAPMEQUIP WHERE x.pm_id=pm_id AND x.comp_no=comp_no),7,2)||' 일','-') nextPlanCalDateVal ");

        query.append("        ,'교 정 성 적 서'                             calibRst            ");
        query.append("        ,'표준기명'                                   calibEq             ");
        query.append("        ,'표준기 번호'                                calibEqNo          ");
        query.append("        ,'HVQ03-001-F07 (Ver. 2.00)'         formInfo           ");
        
        query.append("FROM TAWORKORDER x LEFT OUTER JOIN TAWOCALIB y           ");
        query.append("  ON x.comp_no = y.comp_no     ");
        query.append("  AND x.wkor_id = y.wkor_id     ");
        query.append("LEFT OUTER JOIN TAWOEQUIP a         ");
        query.append("  ON a.wkor_id = x.wkor_id                                                 ");
        query.append("  AND a.comp_no = x.comp_no             ");
        query.append("LEFT OUTER JOIN TAEQUIPMENT b         ");
        query.append("  ON a.comp_no = b.comp_no                                                     ");
        query.append("  AND a.equip_id = b.equip_id                                                   ");
        query.append("WHERE 1=1         ");
        query.append("AND x.comp_no = '"+maWoResultMstrDetailDTO.getCompNo()+"'               ");
        query.append("AND x.wkor_id = '"+maWoResultMstrDetailDTO.getWkOrId()+"'               ");

        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * 교정 리포트 바디(압력계)
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public List findPmPrsReportWoDetail(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO) {

        QueryBuffer query = new QueryBuffer();
        String lang = maWoResultMstrDetailDTO.getUserLang();
        
        query.append("SELECT                                                                                                                                                                                          ");
        query.append("        x.wo_no                                                                                                                                                  woNo,                       ");
        query.append("        NVL((SELECT description FROM TAEQUIPMENT WHERE b.p_equip_id=equip_id AND b.comp_no=comp_no),'-')           equipNameVal,            ");
        query.append("        NVL((SELECT item_no FROM TAEQUIPMENT WHERE b.p_equip_id=equip_id AND b.comp_no=comp_no),'-')               equipNoVal,               ");
        query.append("        NVL(b.description,'-')                                                                                                                              toolDescVal,              ");
        query.append("        NVL(b.item_no,'-')                                                                                                                                  toolNoVal,                 ");
        query.append("        NVL(b.maker,'-')                                                                                                                                     makerVal,                 ");
        query.append("        NVL(b.model_no,'-')                                                                                                                                 modelVal,                 ");
        query.append("        NVL((SELECT full_desc                                                                                                                                                              ");
        query.append("            FROM TAEQLOC                                                                                                                                                                   ");
        query.append("            WHERE comp_no=(SELECT comp_no FROM TAEQUIPMENT WHERE b.p_equip_id=equip_id)                                                                  ");
        query.append("            AND eqloc_id=(SELECT eqloc_id FROM TAEQUIPMENT WHERE b.p_equip_id=equip_id)),'-')                                 locationVal,              ");
        query.append("        NVL((SELECT description                                                                                                                                                           ");
        query.append("            FROM TADEPT                                                                                                                                                                     ");
        query.append("            WHERE comp_no = x.comp_no                                                                                                                                                ");
        query.append("            AND dept_id = x.dept_id),'-')                                                                                                                 manageDeptVal,        ");
        query.append("        NVL((SELECT emp_name                                                                                                                                                           ");
        query.append("            FROM TAEMP                                                                                                                                                                      ");
        query.append("            WHERE comp_no = x.comp_no                                                                                                                                                ");
        query.append("            AND emp_id = x.emp_id),'-')                                                                                                                  managerVal,             ");
        query.append("        NVL2(x.wkor_date                                                                                                                                                                    ");
        query.append("            ,SUBSTR(x.wkor_date,1,4)||' 년 '                                                                                                                                             ");
        query.append("            ||SUBSTR(x.wkor_date,5,2)||' 월 '                                                                                                                                           ");
        query.append("            ||SUBSTR(x.wkor_date,7,2)||' 일','-')                                                                                                      wkorDateVal,            ");
        query.append("        NVL(x.perform,'-')                                                                                                                                    performVal,              ");
        query.append("        NVL2(x.wkor_date                                                                                                                                                                    ");
        query.append("            ,SUBSTR(x.wkor_date,1,4)||' . '                                                                                                                                              ");
        query.append("            ||SUBSTR(x.wkor_date,5,2)||' . '                                                                                                                                            ");
        query.append("            ||SUBSTR(x.wkor_date,7,2),'-')                                                                                                             openDateVal,            ");
        query.append("        NVL((SELECT user_name                                                                                                                                                           ");
        query.append("            FROM TAUSER                                                                                                                                                                     ");
        query.append("            WHERE comp_no = x.comp_no                                                                                                                                                ");
        query.append("            AND user_id = x.close_id),'-')                                                                                                                 closeByVal,              ");
        query.append("        NVL2(x.close_date                                                                                                                                                                    ");
        query.append("            ,SUBSTR(x.close_date,1,4)||' . '                                                                                                                                              ");
        query.append("            ||SUBSTR(x.close_date,5,2)||' . '                                                                                                                                            ");
        query.append("            ||SUBSTR(x.close_date,7,2),'-')                                                                                                             closeDateVal,            ");
        query.append("        NVL((SELECT measure_unit                                                                                                                                                        ");
        query.append("            FROM TAEQTOOL                                                                                                                                                                  ");
        query.append("            WHERE comp_no = b.comp_no                                                                                                                                                ");
        query.append("            AND b.equip_id=equip_id),'-')                                                                                                                  uom,                      ");
        query.append("        NVL(y.calib_env,'-')                                                                                                                                  calEnvVal,               ");
        query.append("        NVL(SFACODE_TO_DESC(y.calib_corp,'CALIB_CORP','USR','100','"+lang+"'),'-')                                                      calCorpVal,              ");
        query.append("        NVL(SFACODE_TO_DESC(y.calib_type,'CALIB_TYPE','SYS','','"+lang+"'),'-')                                                           calTypeVal,             ");
        query.append("        NVL(SFACODE_TO_DESC(y.calib_result_status,'CALIB_RSLT_STATUS','SYS','','"+lang+"'),'-')                                    calRsltStatVal,         ");
        query.append("        NVL(y.calib_sopdoc_no,'-')                                                                                                                         calibSopdocNoVal,    ");
        query.append("        NVL(TO_CHAR((SELECT min_unit_value FROM TAEQTOOL WHERE b.equip_id=equip_id AND b.comp_no=comp_no)),'-') minUnitValue,           ");
        query.append("        NVL((SELECT all_range FROM TAEQTOOL WHERE b.equip_id=equip_id AND b.comp_no=comp_no),'-')                        calibRange,              ");
        query.append("        NVL((SELECT use_range FROM TAEQTOOL WHERE b.equip_id=equip_id AND b.comp_no=comp_no),'-')                      useRange,               ");
        query.append("        NVL((SELECT bb.cycle||SFACODE_TO_DESC(bb.period_type,'PERIOD_TYPE','SYS','','"+lang+"')                                                                ");
        query.append("            FROM TAEQPMCYCLE ba INNER JOIN TAPMLST bb                                                                                                                       ");
        query.append("            ON ba.pm_id=bb.pm_id and ba.comp_no=bb.comp_no                                                                                                                  ");
        query.append("            WHERE ba.comp_no = b.comp_no                                                                                                                                             ");
        query.append("            AND b.equip_id=ba.equip_id),'-')                                                                                                              calibCycleVal,           ");
        query.append("        NVL2((SELECT next_sch_date FROM TAPMEQUIP WHERE x.pm_id=pm_id AND x.comp_no=comp_no)                                                          ");
        query.append("            ,SUBSTR((SELECT next_sch_date FROM TAPMEQUIP WHERE x.pm_id=pm_id AND x.comp_no=comp_no),1,4)||' 년 '                                   ");
        query.append("            ||SUBSTR((SELECT next_sch_date FROM TAPMEQUIP WHERE x.pm_id=pm_id AND x.comp_no=comp_no),5,2)||' 월 '                                   ");
        query.append("            ||SUBSTR((SELECT next_sch_date FROM TAPMEQUIP WHERE x.pm_id=pm_id AND x.comp_no=comp_no),7,2)||' 일','-') nextPlanCalDateVal ");

        query.append("        ,'교 정 성 적 서(압력계용)'                 calibRst           ");
        query.append("        ,'사용 장비명'                                calibEq            ");
        query.append("        ,'장비번호'                                   calibEqNo         ");
        query.append("        ,'HVQ03-001-F09 (Ver. 2.00)'         formInfo          ");
        
        query.append("FROM TAWORKORDER x LEFT OUTER JOIN TAWOCALIB y           ");
        query.append("  ON x.comp_no = y.comp_no     ");
        query.append("  AND x.wkor_id = y.wkor_id     ");
        query.append("LEFT OUTER JOIN TAWOEQUIP a         ");
        query.append("  ON a.wkor_id = x.wkor_id                                                 ");
        query.append("  AND a.comp_no = x.comp_no             ");
        query.append("LEFT OUTER JOIN TAEQUIPMENT b         ");
        query.append("  ON a.comp_no = b.comp_no                                                     ");
        query.append("  AND a.equip_id = b.equip_id                                                   ");
        query.append("WHERE 1=1         ");
        query.append("AND x.comp_no = '"+maWoResultMstrDetailDTO.getCompNo()+"'               ");
        query.append("AND x.wkor_id = '"+maWoResultMstrDetailDTO.getWkOrId()+"'               ");

        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * 교정 리포트 바디(저울)
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public List findPmSclReportWoDetail(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO) {

        QueryBuffer query = new QueryBuffer();
        String lang = maWoResultMstrDetailDTO.getUserLang();
        
//        query.append("SELECT                                                                                                                        ");
//        query.append("        x.wo_no                                                                                           woNo,                   ");
//        query.append("        NVL((SELECT description FROM TAEQUIPMENT WHERE b.p_equip_id=equip_id),'-')     equipNameVal,        ");
//        query.append("        NVL((SELECT item_no FROM TAEQUIPMENT WHERE b.p_equip_id=equip_id),'-')         equipNoVal,              ");
//        query.append("        NVL(b.description,'-')                                                                                    toolDescVal,          ");
//        query.append("        NVL(b.item_no,'-')                                                                                        toolNoVal,                ");
//        query.append("        NVL(b.maker,'-')                                                                                           makerVal,                ");
//        query.append("        NVL(b.model_no,'-')                                                                                      modelVal,                  ");
//        query.append("        NVL(b.serial_no,'-')                                                                                      serialNoVal,                  ");
//        query.append("        NVL((SELECT full_desc                                                                                                                 ");
//        query.append("            FROM TAEQLOC                                                                                                                      ");
//        query.append("            WHERE comp_no=b.comp_no                                                                                                      ");
//        query.append("            AND eqloc_id=b.eqloc_id),'-')                                                                     locationVal,   ");
//        query.append("        NVL((SELECT emp_name                                                                                                          ");
//        query.append("            FROM TAEMP                                                                                                            ");
//        query.append("            WHERE comp_no = x.comp_no                                                                                         ");
//        query.append("            AND emp_id = x.emp_id),'-')                                                                managerVal,              ");
//        query.append("        NVL(x.wkor_date,'-')                                                                                    wkorDateVal,            ");
//        query.append("        NVL(x.perform,'-')                                                                                        performVal,               ");
//        query.append("        NVL((SELECT user_name                                                                                                         ");
//        query.append("            FROM TAUSER                                                                                                           ");
//        query.append("            WHERE comp_no = x.comp_no                                                                                         ");
//        query.append("            AND user_id = x.close_id),'-')                                                               closeByVal,                ");
//        query.append("        NVL(x.close_date,'-')                                                                                    closeDateVal,      ");
//        query.append("        NVL(y.calib_env,'-')                                                        calEnvVal,                  ");
//        query.append("        NVL(SFACODE_TO_DESC(y.calib_corp,'CALIB_CORP','USR','100','"+lang+"'),'-')    calCorpVal,             ");
//        query.append("        NVL(SFACODE_TO_DESC(y.calib_type,'CALIB_TYPE','SYS','','"+lang+"'),'-')    calTypeVal,            ");
//        query.append("        NVL(SFACODE_TO_DESC(y.calib_result_status,'CALIB_RSLT_STATUS','SYS','','"+lang+"'),'-')    calRsltStatVal,            ");
//        query.append("        NVL(TO_CHAR((SELECT min_unit_value FROM TAEQTOOL WHERE b.equip_id=equip_id)),'-')                                        minUnitValue,      ");
//        query.append("        NVL((SELECT tolerance FROM TAEQTOOL WHERE b.equip_id=equip_id),'-')                                                 tolerance,      ");
//        query.append("        NVL((SELECT NVL2(min_value||max_value,min_value||' ~ '||max_value,'') FROM TAEQTOOL WHERE b.equip_id=equip_id),'-')    calibRange,     ");
//        query.append("        NVL((SELECT use_range FROM TAEQTOOL WHERE b.equip_id=equip_id),'-')                                                                      useRange,      ");
//        query.append("        NVL((SELECT next_plan_date FROM TAPMEQUIP where x.pm_id=pm_id),'-')                                                                      nextPlanCalDateVal ");
        
        query.append("SELECT                                                                                                                                                                                          ");
        query.append("        x.wo_no                                                                                                                                                  woNo,                       ");
        query.append("        NVL((SELECT description FROM TAEQUIPMENT WHERE b.p_equip_id=equip_id AND b.comp_no=comp_no),'-')           equipNameVal,            ");
        query.append("        NVL((SELECT item_no FROM TAEQUIPMENT WHERE b.p_equip_id=equip_id AND b.comp_no=comp_no),'-')               equipNoVal,               ");
        query.append("        NVL(b.description,'-')                                                                                                                              toolDescVal,              ");
        query.append("        NVL(b.item_no,'-')                                                                                                                                  toolNoVal,                 ");
        query.append("        NVL(b.maker,'-')                                                                                                                                     makerVal,                 ");
        query.append("        NVL(b.model_no,'-')                                                                                                                                 modelVal,                 ");
        query.append("        NVL(b.serial_no,'-')                                                                                                                                 serialNoVal,                  ");
        query.append("        NVL((SELECT full_desc                                                                                                                                                              ");
        query.append("            FROM TAEQLOC                                                                                                                                                                   ");
        query.append("            WHERE comp_no=(SELECT comp_no FROM TAEQUIPMENT WHERE b.p_equip_id=equip_id)                                                                  ");
        query.append("            AND eqloc_id=(SELECT eqloc_id FROM TAEQUIPMENT WHERE b.p_equip_id=equip_id)),'-')                                 locationVal,              ");
        query.append("        NVL((SELECT description                                                                                                                                                           ");
        query.append("            FROM TADEPT                                                                                                                                                                     ");
        query.append("            WHERE comp_no = x.comp_no                                                                                                                                                ");
        query.append("            AND dept_id = x.dept_id),'-')                                                                                                                 manageDeptVal,        ");
        query.append("        NVL((SELECT emp_name                                                                                                                                                           ");
        query.append("            FROM TAEMP                                                                                                                                                                      ");
        query.append("            WHERE comp_no = x.comp_no                                                                                                                                                ");
        query.append("            AND emp_id = x.emp_id),'-')                                                                                                                  managerVal,             ");
        query.append("        NVL2(x.wkor_date                                                                                                                                                                    ");
        query.append("            ,SUBSTR(x.wkor_date,1,4)||' 년 '                                                                                                                                             ");
        query.append("            ||SUBSTR(x.wkor_date,5,2)||' 월 '                                                                                                                                           ");
        query.append("            ||SUBSTR(x.wkor_date,7,2)||' 일','-')                                                                                                      wkorDateVal,            ");
        query.append("        NVL(x.perform,'-')                                                                                                                                    performVal,              ");
        query.append("        NVL2(x.wkor_date                                                                                                                                                                    ");
        query.append("            ,SUBSTR(x.wkor_date,1,4)||' . '                                                                                                                                              ");
        query.append("            ||SUBSTR(x.wkor_date,5,2)||' . '                                                                                                                                            ");
        query.append("            ||SUBSTR(x.wkor_date,7,2),'-')                                                                                                             openDateVal,            ");
        query.append("        NVL((SELECT user_name                                                                                                                                                           ");
        query.append("            FROM TAUSER                                                                                                                                                                     ");
        query.append("            WHERE comp_no = x.comp_no                                                                                                                                                ");
        query.append("            AND user_id = x.close_id),'-')                                                                                                                 closeByVal,              ");
        query.append("        NVL2(x.close_date                                                                                                                                                                    ");
        query.append("            ,SUBSTR(x.close_date,1,4)||' . '                                                                                                                                              ");
        query.append("            ||SUBSTR(x.close_date,5,2)||' . '                                                                                                                                            ");
        query.append("            ||SUBSTR(x.close_date,7,2),'-')                                                                                                             closeDateVal,            ");
        query.append("        NVL((SELECT measure_unit                                                                                                                                                        ");
        query.append("            FROM TAEQTOOL                                                                                                                                                                  ");
        query.append("            WHERE comp_no = b.comp_no                                                                                                                                                ");
        query.append("            AND b.equip_id=equip_id),'-')                                                                                                                  uom,                      ");
        query.append("        NVL(y.calib_env,'-')                                                                                                                                  calEnvVal,               ");
        query.append("        NVL(SFACODE_TO_DESC(y.calib_corp,'CALIB_CORP','USR','100','"+lang+"'),'-')                                                      calCorpVal,              ");
        query.append("        NVL(SFACODE_TO_DESC(y.calib_type,'CALIB_TYPE','SYS','','"+lang+"'),'-')                                                           calTypeVal,             ");
        query.append("        NVL(SFACODE_TO_DESC(y.calib_result_status,'CALIB_RSLT_STATUS','SYS','','"+lang+"'),'-')                                    calRsltStatVal,         ");
        query.append("        NVL(y.calib_sopdoc_no,'-')                                                                                                                         calibSopdocNoVal,    ");
        query.append("        NVL(TO_CHAR((SELECT min_unit_value FROM TAEQTOOL WHERE b.equip_id=equip_id AND b.comp_no=comp_no)),'-') minUnitValue,           ");
        query.append("        NVL((SELECT all_range FROM TAEQTOOL WHERE b.equip_id=equip_id AND b.comp_no=comp_no),'-')                        calibRange,              ");
        query.append("        NVL((SELECT use_range FROM TAEQTOOL WHERE b.equip_id=equip_id AND b.comp_no=comp_no),'-')                      useRange,               ");
        query.append("        NVL((SELECT tolerance FROM TAEQTOOL WHERE b.equip_id=equip_id AND b.comp_no=comp_no),'-')                       tolerance,                   ");
        query.append("        NVL((SELECT bb.cycle||SFACODE_TO_DESC(bb.period_type,'PERIOD_TYPE','SYS','','"+lang+"')                                                                ");
        query.append("            FROM TAEQPMCYCLE ba INNER JOIN TAPMLST bb                                                                                                                       ");
        query.append("            ON ba.pm_id=bb.pm_id and ba.comp_no=bb.comp_no                                                                                                                  ");
        query.append("            WHERE ba.comp_no = b.comp_no                                                                                                                                             ");
        query.append("            AND b.equip_id=ba.equip_id),'-')                                                                                                              calibCycleVal,           ");
        query.append("        NVL2((SELECT next_sch_date FROM TAPMEQUIP WHERE x.pm_id=pm_id AND x.comp_no=comp_no)                                                          ");
        query.append("            ,SUBSTR((SELECT next_sch_date FROM TAPMEQUIP WHERE x.pm_id=pm_id AND x.comp_no=comp_no),1,4)||' 년 '                                   ");
        query.append("            ||SUBSTR((SELECT next_sch_date FROM TAPMEQUIP WHERE x.pm_id=pm_id AND x.comp_no=comp_no),5,2)||' 월 '                                   ");
        query.append("            ||SUBSTR((SELECT next_sch_date FROM TAPMEQUIP WHERE x.pm_id=pm_id AND x.comp_no=comp_no),7,2)||' 일','-') nextPlanCalDateVal ");
        
        query.append("        ,'HVQ03-005-F01 (Ver. 2.00)'         formInfo          ");
        
        query.append("FROM TAWORKORDER x LEFT OUTER JOIN TAWOCALIB y           ");
        query.append("  ON x.comp_no = y.comp_no     ");
        query.append("  AND x.wkor_id = y.wkor_id     ");
        query.append("LEFT OUTER JOIN TAWOEQUIP a         ");
        query.append("  ON a.wkor_id = x.wkor_id                                                 ");
        query.append("  AND a.comp_no = x.comp_no             ");
        query.append("LEFT OUTER JOIN TAEQUIPMENT b         ");
        query.append("  ON a.comp_no = b.comp_no                                                     ");
        query.append("  AND a.equip_id = b.equip_id                                                   ");
        query.append("WHERE 1=1         ");
        query.append("AND x.comp_no = '"+maWoResultMstrDetailDTO.getCompNo()+"'               ");
        query.append("AND x.wkor_id = '"+maWoResultMstrDetailDTO.getWkOrId()+"'               ");

        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * 교정 리포트 표준기
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public List findReportWoCalibStdEqDetail(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("WITH a AS(        ");
        query.append("    (             ");
        query.append("    SELECT            ");
        query.append("           NVL(y.description,'-')             calibEqVal                                  ");
        query.append("           ,NVL(y.item_no,'-')                calibEqNoVal                            ");
        query.append("           ,NVL(x.serial_no,'-')               serialNoVal                        ");
        query.append("           ,NVL(x.wo_no,'-')                  calibRstNoVal                           ");
        query.append("           ,NVL(x.next_plan_date,'-')      nextPlanDateVal            ");
        query.append("    FROM   TAWOCALIBSTDEQ x INNER JOIN TAEQUIPMENT y                  ");
        query.append("      ON   x.equip_id = y.equip_id                                    ");
        query.append("    WHERE  1=1                                    ");
        query.getAndQuery("x.wkor_id", maWoResultMstrDetailDTO.getWkOrId());
        query.getAndQuery("x.comp_no", maWoResultMstrDetailDTO.getCompNo());
        query.append("    ) UNION ALL (             ");
        query.append("    SELECT                ");
        query.append("           '-'          calibEqVal                                        ");
        query.append("           ,'-'         calibEqNoVal                                  ");
        query.append("           ,'-'         serialNoVal                               ");
        query.append("           ,'-'         calibRstNoVal                                     ");
        query.append("           ,'-'         nextPlanDateVal                   ");
        query.append("    FROM dual             ");
        query.append("    )     ");
        query.append(")     ");
        query.append("SELECT        ");
        query.append("    CASE WHEN ROWNUM=(SELECT FLOOR((COUNT(a.calibEqNoVal)+1)/2) FROM a) THEN '교정에 사용한\n표준기' END text      ");
        query.append("    ,a.calibEqVal                                 ");
        query.append("    ,a.calibEqNoVal                           ");
        query.append("    ,a.serialNoVal                        ");
        query.append("    ,a.calibRstNoVal                          ");
        query.append("    ,a.nextPlanDateVal            ");
        query.append("FROM a        ");

        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * 교정 리포트 측정데이터
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public List findReportWoCalibGnlValueDetail(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                ");
        query.append("       NVL(SFACODE_TO_DESC(calib_point_type,'CALIB_POINT_TYPE','SYS','','ko'),'-') clibPointTypeVal               ");
        query.append("       ,NVL(calib_point,'-') calibPointVal                     ");
        query.append("       ,NVL(allow_value,'-') allowValueVal                     ");
        query.append("       ,NVL(asf_std_value,'-') asfStdValueVal                      ");
        query.append("       ,NVL(asf_cal_value,'-') asfCalValueVal                      ");
        query.append("       ,NVL(asf_diff_value,'-') asfDiffValueVal                    ");
        query.append("       ,NVL(asl_std_value,'-') aslStdValueVal                      ");
        query.append("       ,NVL(asl_cal_value,'-') aslCalValueVal                      ");
        query.append("       ,NVL(asl_diff_value,'-') aslDiffValueVal        ");
        query.append("FROM   TAWOCALIBGNLVALUE                              ");
        query.append("WHERE  1=1                                            ");
        query.getAndQuery("wkor_id", maWoResultMstrDetailDTO.getWkOrId());
        query.getAndQuery("comp_no", maWoResultMstrDetailDTO.getCompNo());
        query.append("ORDER BY ord_no       ");

        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * 교정 리포트(저울) 측정데이터
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public List findReportWoCalibSclValueDetail(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                                                                ");
        query.append("        NVL(MAX(li0_value),'-')             AS liValue0                             ");
        query.append("        ,NVL(MAX(li25_value),'-')         AS liValue25                              ");
        query.append("        ,NVL(MAX(li50_value),'-')         AS liValue50                              ");
        query.append("        ,NVL(MAX(li75_value),'-')         AS liValue75                              ");
        query.append("        ,NVL(MAX(li100_value),'-')         AS liValue100                            ");
        query.append("        ,NVL(MAX(ld75_value),'-')         AS ldValue75                          ");
        query.append("        ,NVL(MAX(ld50_value),'-')         AS ldValue50                          ");
        query.append("        ,NVL(MAX(ld25_value),'-')         AS ldValue25                          ");
        query.append("        ,NVL(MAX(ld0_value),'-')             AS ldValue0                            ");
        query.append("        ,NVL(MAX(ecntr_value),'-')         AS ecntrValue                        ");
        query.append("        ,NVL(MAX(ebef_value),'-')         AS ebefValue                          ");
        query.append("        ,NVL(MAX(eaft_value),'-')         AS eaftValue                          ");
        query.append("        ,NVL(MAX(elft_value),'-')         AS elftValue                              ");
        query.append("        ,NVL(MAX(erig_value),'-')         AS erigValue                              ");
        query.append("        ,NVL(MAX(degree1),'-')             AS degree1                               ");
        query.append("        ,NVL(MAX(degree2),'-')             AS degree2                               ");
        query.append("        ,NVL(MAX(degree3),'-')             AS degree3                               ");
        query.append("FROM   TAWOCALIBSCLVALUE                      ");
        query.append("WHERE  1=1                                                                            ");
        query.getAndQuery("wkor_id", maWoResultMstrDetailDTO.getWkOrId());
        query.getAndQuery("comp_no", maWoResultMstrDetailDTO.getCompNo());

        return getJdbcTemplate().queryForList(query.toString());
    }
    
    public String checkPoint(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO,User user) {

		QueryBuffer query = new QueryBuffer();
		query.append("SELECT NVL(val,0) FROM (select to_char(sum(decode(decode(y.check_type,'SEN',x.pm_point_rslt_status,'VAL',x.result_value),null,1,0))) val");
		query.append("from tawopoint x, tapmpoint y					");
		query.append("where x.comp_no = y.comp_no					");
		query.append("and x.pm_point_id = y.pm_point_id				");
		query.getStringEqualQuery("x.comp_no", user.getCompNo());
		query.getStringEqualQuery("x.wkor_id", maWoResultMstrDetailDTO.getWkOrId());
		query.append(")												");
		
		return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
    
    /**
	 * Find WcodeID
	 * @param maWoResultMstrCommonDTO
	 * @return
	 */
	public String findWcodeId(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) 
	{
		QueryBuffer query = new QueryBuffer();
		maWoResultMstrCommonDTO.getWkOrId();
        
        query.append("SELECT x.lvl     id,									");
        query.append("       x.lvl     code,								");
        query.append("       x.lvl     description							");
        query.append("FROM   (SELECT '1' lvl from DUAL						");
        query.append("			union all SELECT '2' lvl from DUAL 			");
        query.append("			union all SELECT '3' lvl from DUAL 			");
        query.append("			union all SELECT '4' lvl from DUAL ) x		");
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QueryBuffer.listToString(resultList);
	}
	
	public String findPmId(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO) 
	{
		QueryBuffer query = new QueryBuffer();
        
//        query.append("SELECT pm_id													");
//        query.append("FROM   TAWORKORDER											");
//        query.append("WHERE  wkor_id = ?                                        	");
//        query.append("    and comp_no = ?			                                ");
        
        query.append("SELECT                                                            ");
        query.append("       c.pm_id                                                    ");
        query.append("FROM   TAWORKORDER x INNER JOIN TAWOEQUIP y ON x.wkor_id = y.wkor_id AND x.comp_no = y.comp_no    ");
        query.append("       INNER JOIN TAPMEQUIP z ON y.equip_id = z.equip_id AND y.comp_no = z.comp_no                ");
        query.append("       INNER JOIN TAPMLST c ON c.pm_id = z.pm_id AND c.comp_no = z.comp_no 						");
        query.append("WHERE  1 = 1                                              		");
        query.append(" 	 AND x.pm_id = c.pm_id                                          ");
        query.append(" 	AND  x.wkor_id = ?                                              ");
        query.append("  AND  x.comp_no = ?                                              ");
        query.append("  AND  x.wo_type = ?                                              ");
        query.append("  AND  ROWNUM = 1                                                 ");

        
        
        Object[] objects = new Object[] {
        		maWoResultMstrDetailDTO.getWkOrId()
        		,maWoResultMstrDetailDTO.getCompNo()
        		,maWoResultMstrDetailDTO.getWoTypeId()
    	};
        
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString(),objects));
	}
	
	public int updateLastSchDate(String compNo, String pmId, String actDate) 
	{
		QueryBuffer query = new QueryBuffer();
        
        query.append("UPDATE TAPMEQUIP SET				");
        query.append("	last_sch_date = ?           	");
        query.append("WHERE  pm_id = ? 			       ");
        query.append(" AND   comp_no = ? 		       ");
         
        Object[] objects = new Object[] {
        		actDate.length()>8?CommonUtil.convertDate(actDate):actDate
        		,pmId
        		,compNo
    	};
        return getJdbcTemplate().update(query.toString(),objects);
	}
	@Override
	public void updateInEquip(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO) 
	{
		QueryBuffer query = new QueryBuffer();

		query.append("UPDATE TAEQUIPMENT 						");
		query.append("   SET eq_status = 'R' 					"); //운영설비
		query.append(" WHERE equip_id IN (						");
		query.append("                    SELECT y.in_equip_id	");
		query.append("                    FROM   TAWOPARTS x INNER JOIN TAWOPARTS_SERIAL y ON x.wopart_id = y.wopart_id and x.part_id = y.part_id	");
		query.append("                    WHERE x.wkor_id =?)	");
//		query.append("   AND eq_status = 'S'					");
    	
    	Object[] objects = new Object[] {
    			maWoResultMstrDetailDTO.getWkOrId()
    	};
    	
    	getJdbcTemplate().update(query.toString(), objects);
	}
	@Override
	public void updateOutEquip(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO) 
	{
		QueryBuffer query = new QueryBuffer();

		query.append("UPDATE TAEQUIPMENT 						");
		query.append("   SET eq_status = 'P' 					");  //수리대기설
		query.append("       ,p_equip_id = '' 					");
		query.append(" WHERE equip_id IN (						");
		query.append("                    SELECT y.out_equip_id	");
		query.append("                    FROM   TAWOPARTS x INNER JOIN TAWOPARTS_SERIAL y ON x.wopart_id = y.wopart_id and x.part_id = y.part_id	");
		query.append("                    WHERE x.wkor_id =?)	");
//		query.append("   AND eq_status = 'R'					");
    	
    	Object[] objects = new Object[] {
    			maWoResultMstrDetailDTO.getWkOrId()
    	};
    	
    	getJdbcTemplate().update(query.toString(), objects);
	}
	
	@Override
	public List findWopartList(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO) 
	{
		QueryBuffer query = new QueryBuffer();

		query.append("SELECT                                     ");
		query.append("       x.wopart_id                         ");
		query.append("       ,x.ptisslist_id                     ");
		query.append("FROM   TAWOPARTS x, TAPARTS y              ");
		query.append("WHERE  x.comp_no = y.comp_no               ");
		query.append("    and x.part_id = y.part_id              ");
		query.append("    and y.is_stock_control = 'Y'           ");
		query.append("    and y.is_use = 'Y'                     ");
		query.append("    and x.wkor_id = ?                      ");
		query.append("    and x.comp_no = ?                      ");
        
        Object[] objects = new Object[] {
    			maWoResultMstrDetailDTO.getWkOrId()
    			,maWoResultMstrDetailDTO.getCompNo()
    	};
        
        return getJdbcTemplate().queryForList(query.toString(), objects);
	}
	
	
	public String[] findWopartIssList(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO) 
	{
		QueryBuffer query = new QueryBuffer();

		query.append("SELECT                                     ");
		query.append("       x.ptisslist_id                      ");
		query.append("FROM   TAPTISSLIST x                       ");
		query.append("WHERE  1=1                                 ");
		query.append("    and x.wkor_id = ?                      ");
		query.append("    and x.comp_no = ?                      ");
		query.append("    and x.ptiss_status = ?                 ");
        
        Object[] objects = new Object[] {
    			maWoResultMstrDetailDTO.getWkOrId()
    			,maWoResultMstrDetailDTO.getCompNo()
    			,"C"
    	};
        
        List resultList=  getJdbcTemplate().queryForList(query.toString(), objects);
        
        return QueryBuffer.toStringSingleArray(resultList);
	}
	
	public String[] findWoEqList(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO) 
	{
		QueryBuffer query = new QueryBuffer();
		
		query.append("SELECT 						");
		query.append("       x.equip_id 			");
		query.append("FROM   TAWOEQUIP x			");
		query.append("WHERE  x.wkor_id = ?			");
		query.append("    and x.comp_no = ?			");
		
		Object[] objects = new Object[] {
				maWoResultMstrDetailDTO.getWkOrId()
				,maWoResultMstrDetailDTO.getCompNo()
		};
		
		List resultList=  getJdbcTemplate().queryForList(query.toString(), objects);
		
		return QueryBuffer.toStringSingleArray(resultList);
	}

	
	@Override
	public int findSerialCount(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO) {
		// TODO Auto-generated method stub
		QueryBuffer query = new QueryBuffer();
    	
		query.append("SELECT CASE status 									");
		query.append("       WHEN 'NO' THEN COUNT(1)						");
		query.append("       ELSE 0											");
		query.append("       END noCnt										");
		query.append("FROM (            									");
		query.append("		SELECT 											");
		query.append("        	   x.wopart_id								");
		query.append("             ,x.wkor_id								");
		query.append("             ,COUNT(y.part_id) serialCnt				");
		query.append("             ,x.use_qty								");
		query.append("             ,CASE COUNT(y.part_id)					");
		query.append("              WHEN x.use_qty THEN 'OK' 				");
		query.append("              ELSE 'NO'								");
		query.append("              END status								");
		query.append("      FROM TAWOPARTS x LEFT OUTER JOIN TAWOPARTS_SERIAL y ON x.wopart_id = y.wopart_id	");
		query.append("      WHERE x.wkor_id = ?        						");
		query.append("      GROUP BY x.wkor_id, x.wopart_id, x.use_qty		");
		query.append("     )												");
		query.append("GROUP BY wkor_id, status								");


    	Object[] objects = new Object[] {
    			maWoResultMstrDetailDTO.getWkOrId()
    	};
    	
    	return (int)getJdbcTemplate().queryForObject(query.toString(), objects, Integer.class);
	}
	@Override
	public int checkIsSerialPart(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO) 
	{
		QueryBuffer query = new QueryBuffer();
    	
		query.append("SELECT 															");
		query.append("       COUNT(1)													");
		query.append("FROM   TAWOPARTS x INNER JOIN TAPARTS y ON x.part_id = y.part_id	");
		query.append("WHERE  y.is_serial_part = 'Y'										");
		query.append("  AND x.wkor_id = ?												");

    	Object[] objects = new Object[] {
    			maWoResultMstrDetailDTO.getWkOrId()
    	};
    	
    	return (int)getJdbcTemplate().queryForObject(query.toString(), objects, Integer.class);
	}
	
	/**
	 * Serial Equipment의 P_EQUIP_ID 세팅
	 */
	public void updatePequip(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO) 
	{
		QueryBuffer query = new QueryBuffer();

		query.append("UPDATE TAEQUIPMENT 						");
		query.append("   SET p_equip_id =  ? 					");  
		query.append(" WHERE equip_id IN (						");
		query.append("                    SELECT y.in_equip_id	");
		query.append("                    FROM   TAWOPARTS x INNER JOIN TAWOPARTS_SERIAL y ON x.wopart_id = y.wopart_id and x.part_id = y.part_id	");
		query.append("                    WHERE x.wkor_id =?)	");
//		query.append("   AND eq_status = 'R'					");
    	
    	Object[] objects = new Object[] {
    			maWoResultMstrDetailDTO.getEquipId(),
    			maWoResultMstrDetailDTO.getWkOrId()
    	};
    	
    	getJdbcTemplate().update(query.toString(), objects);
	}
	@Override
	public int updateEqhistory(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO) 
	{
		QueryBuffer query = new QueryBuffer();
		
		int rtnValue = 0;
		
		query.append("UPDATE TAEQHISTORY SET		");
		query.append("		item_no		 = ?		");
		query.append("		,eqasmb_desc = ?		");
		query.append("		,wkor_date	 = ?		");
		query.append("		,wo_type 	 = ?		");
		query.append("		,description = ?		");
		query.append("		,dept_id	 = ?		");
		query.append("		,emp_name	 = ?		");
		query.append("		,emp_id		 = ?		");
		query.append("		,vendor_name = ?		");
		query.append("		,tot_amt	 = (SELECT NVL(aa.tot_amt,0)	");
		query.append("						FROM TAWORKORDER aa			");
		query.append("						WHERE wkor_id = ?			");
		query.append("						 AND comp_no = ? )			");
		query.append("		,perform	 = ?		");
		query.append("		,ca_desc = (SELECT u.ca_desc  		");
		query.append("					FROM TAWOFAIL u 		");
		query.append("					WHERE u.comp_no = ?		");
		query.append("					  AND u.wkor_id = ? )	");
		query.append("		,re_desc = (SELECT u.re_desc  		");
		query.append("					FROM TAWOFAIL u 		");
		query.append("					WHERE u.comp_no = ?		");
		query.append("					  AND u.wkor_id = ? )	");
		query.append("		,start_date	 = ?		");
		query.append("		,start_time	 = ?		");
		query.append("		,end_date	 = ?		");
		query.append("		,end_time	 = ?		");
		query.append("		,work_time	 = ?		");
		query.append("		,eqhist_gen_type = ?	");
		query.append("WHERE wkor_id		= ?			");
		query.append("	AND comp_no    	= ?			");
		
		Object[] objects = new Object[] {
				maWoResultMstrDetailDTO.getItemNo()
				,maWoResultMstrDetailDTO.getEqAsmbDesc()
				,maWoResultMstrDetailDTO.getWkorDate()
				,maWoResultMstrDetailDTO.getWoTypeId()
				,maWoResultMstrDetailDTO.getWkOrDesc()
				,maWoResultMstrDetailDTO.getDeptId()
				,maWoResultMstrDetailDTO.getEmpDesc()
				,maWoResultMstrDetailDTO.getEmpId()
				,maWoResultMstrDetailDTO.getVendorDesc()
				,maWoResultMstrDetailDTO.getWkOrId()
				,maWoResultMstrDetailDTO.getCompNo()
				,maWoResultMstrDetailDTO.getPerform()
				,maWoResultMstrDetailDTO.getWkOrId()
				,maWoResultMstrDetailDTO.getCompNo()
				,maWoResultMstrDetailDTO.getWkOrId()
				,maWoResultMstrDetailDTO.getCompNo()
				,maWoResultMstrDetailDTO.getStartDate()
				,maWoResultMstrDetailDTO.getStartTime()
				,maWoResultMstrDetailDTO.getEndDate()
				,maWoResultMstrDetailDTO.getEndTime()
				,maWoResultMstrDetailDTO.getWorkTime()
				,maWoResultMstrDetailDTO.getEqHistGenType()
				,maWoResultMstrDetailDTO.getWkOrId()
				,maWoResultMstrDetailDTO.getCompNo()
		};
		
		rtnValue =  getJdbcTemplate().update(query.toString(), objects);
		
		return rtnValue;
	}
		
	public void insertEqhistory(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO) 
	{
		QueryBuffer query = new QueryBuffer();
		
		query.append("INSERT INTO TAEQHISTORY																	");
		query.append("(																							");
		query.append("    comp_no			,eqhistory_id			,item_no 			,serial_no 				");
		query.append("    , wkor_id			,pminslist_id			,ptrepairlist_id	,description 			");
		query.append("    , eq_status		,wo_type				,wkor_date			,tot_amt				");
		query.append("    , start_date		,start_time				,end_date			,end_time				");
		query.append("    , work_time		,dept_id				,wkctr_id			,emp_id					");
		query.append("    , emp_name		,vendor_name		  	,eqasmb_id			,eqasmb_desc			");
		query.append("	  , perform			,ca_desc				,re_desc			,plant				  	");
		query.append("    , self_vendor_type	,vendor_id			,courselist_id		,mo_cd					");
		query.append("    , mo_desc			,ca_cd					,re_cd				,eqdn_start_date 		");
		query.append("    , eqdn_start_time	,eqdn_end_date			,eqdn_end_time		,eqdn_work_time			");
		query.append("    , lndn_start_date ,lndn_start_time		,lndn_end_date		,lndn_end_time			");
		query.append("    , lndn_work_time	,part_desc				,remark				,eqhist_gen_type						");
		query.append(")																							");
		query.append("SELECT																					");
		query.append("    x.comp_no			,SQAEQHISTORY_ID.NEXTVAL	,z.item_no		,z.serial_no			");
		query.append("    ,x.wkor_id		,?						,?					,x.description			");
		query.append("    ,z.eq_status		,x.wo_type				,x.wkor_date		,x.tot_amt				");
		query.append("    ,x.start_date		,x.start_time			,x.end_date			,x.end_time				");
		query.append("    ,x.work_time		,x.dept_id				,x.wkctr_id			,x.emp_id				");
		query.append("    ,(SELECT a.emp_name FROM TAEMP a WHERE a.comp_no = x.comp_no AND a.emp_id = x.emp_id)	");
		query.append("						,(CASE WHEN x.vendor_id is null  									");
		query.append("								THEN x.vendor_desc  										");
		query.append("								ELSE (SELECT a.description FROM TAVENDOR a WHERE a.comp_no = x.comp_no AND a.vendor_id = x.vendor_id)	");
		query.append("						  END)																");
		query.append("						,x.eqasmb_id														");
		query.append("	  					,(SELECT a.full_desc FROM TAEQASMB a WHERE a.comp_no = x.comp_no AND a.eqasmb_id = x.eqasmb_id)					");
		query.append("	  ,x.perform		,u.ca_desc				,u.re_desc			,x.plant				");
		query.append("    ,x.self_vendor_type	,x.vendor_id		,?					,u.mo_cd				");
		query.append("    ,u.mo_desc		,u.ca_cd				,u.re_cd			,u.eqdn_start_date 		");
		query.append("    ,u.eqdn_start_time	,u.eqdn_end_date	,u.eqdn_end_time	,u.eqdn_work_time		");
		query.append("    ,u.lndn_start_date	,u.lndn_start_time	,u.lndn_end_date	,u.lndn_end_time		");
		query.append("    ,u.lndn_work_time	,(SELECT 															");
    	query.append("						  	CASE WHEN (count(*)>1) THEN (max(bb.full_desc)||(SELECT ' '||key_name||' '	");
    	query.append("						  	  							  					 FROM TALANG  				");
    	query.append("						  	  							  					 WHERE lang='ko' 			");
    	query.append("						  	  							  					  AND key_no='aFew' 		");
    	query.append("						  	  							  					  AND key_type='LABEL')		");
    	query.append("						  	  							||(count(*)-1) )					");
    	query.append("						  	  					   ELSE max(bb.full_desc)  					");
    	query.append("						  	END																");
    	query.append("						  FROM TAWOPARTS aa INNER JOIN TAPARTS bb							");
    	query.append("						  ON aa.comp_no = bb.comp_no										");
    	query.append("						   AND aa.part_id = bb.part_id 										");
    	query.append("						  WHERE aa.wkor_id = x.wkor_id										");
    	query.append("						   AND aa.comp_no = x.comp_no										");
    	query.append("						  GROUP By aa.comp_no, aa.wkor_id	) 								");
        query.append("	  , ?					,?																");
		query.append("FROM TAWORKORDER x INNER JOIN TAWOEQUIP y													");
		query.append("ON x.comp_no = y.comp_no 																	");
		query.append(" AND x.wkor_id = y.wkor_id 																");
		query.append("	INNER  JOIN TAEQUIPMENT  z																");
		query.append("	 ON y.comp_no = z.comp_no																");
		query.append("	 AND y.equip_id = z.equip_id															");
		query.append("		LEFT OUTER JOIN TAWOFAIL u															");
		query.append("		 ON x.comp_no = u.comp_no															");
		query.append("		 AND x.wkor_id = u.wkor_id															");
		query.append("WHERE x.wkor_id = ?																		");
		query.append(" AND x.comp_no = ?																		");

		Object[] objects = new Object[] {
    			""
    			,""
    			,""
    			,""
    			,maWoResultMstrDetailDTO.getEqHistGenType()
    			,maWoResultMstrDetailDTO.getWkOrId()
    			,maWoResultMstrDetailDTO.getCompNo()
    	};
	    	
    	getJdbcTemplate().update(query.toString(), objects);
    	
	}
	
	public void deleteEqhistory(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO) 
	{
		QueryBuffer query = new QueryBuffer();
		
		query.append("DELETE  TAEQHISTORY	    									");
    	query.append("WHERE comp_no			= ?											");
    	query.append("  AND wkor_id     	= ?											");
    	
    	Object[] objects = new Object[] {
    			maWoResultMstrDetailDTO.getCompNo()
    			,maWoResultMstrDetailDTO.getWkOrId()
    	};
		
		getJdbcTemplate().update(query.toString(), objects);
	}
    @Override
    public int setStatus(AppReqDetailDTO appReqDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("UPDATE TAWORKORDER SET                    ");
        query.append("       wo_status   = CASE WHEN wo_status='C' THEN 'C' ELSE ? END             ");
        query.append("WHERE  wkor_id       = ?             ");
        query.append("AND  comp_no       = ?             ");
        
        Object[] objects = new Object[] {
                appReqDetailDTO.getParentStatus(),
                appReqDetailDTO.getObjectId(),
                user.getCompNo()
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    @Override
    public String getLastAppEmpId(AppReqDetailDTO appReqDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("SELECT                        ");
    	query.append("      z.user_id				");
    	query.append("FROM TAAPPRUSR x JOIN TAEMP y ");
    	query.append("ON x.appr_by = y.emp_id    	");
    	query.append("AND  x.comp_no = y.comp_no	");
    	query.append("INNER JOIN TAUSER z			");
    	query.append("ON y.comp_no = z.comp_no		");
    	query.append("AND y.emp_id = z.emp_id   	");
    	query.append("WHERE 1=1						");
    	query.append("  AND  x.comp_no 		= ?		");
    	query.append("  AND  x.apprlist_id 	= ?		");
    	query.append("ORDER BY x.appr_seq DESC		");
    	
    	Object[] objects = new Object[] {
    			  user.getCompNo()
     			, appReqDetailDTO.getApprlistId()
    	};
    	
    	return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString(),objects));
    }
    @Override
    public String getStatus(AppReqDetailDTO appReqDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
                
        query.append("SELECT wo_status      ");
        query.append("FROM TAWORKORDER      ");
        query.append("WHERE comp_no = ?     ");
        query.append("and wkor_id = ?       ");
        
        Object[] objects = new Object[] {
                user.getCompNo(),
                appReqDetailDTO.getObjectId()
        };
        
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString(),objects));
    }
    @Override
    public String findPage(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT        ");
        query.append("(SELECT param1 FROM TACDSYSD WHERE list_Type= wo_type||'_TYPE' AND cdsysd_no=pm_type) wkOrPage        ");
        query.append("FROM TAWORKORDER      ");
        query.append("WHERE comp_no = ?     ");
        query.append("AND wkor_id = ?       ");
        
        Object[] objects = new Object[] {
                user.getCompNo(),
                maWoResultMstrCommonDTO.getWkOrId()
        };
        
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString(),objects));
    }
    public int updateWoReqStatus(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;

    	query.append("UPDATE TAWOREQ a SET woreq_status =    									                        ");
    	query.append("                        case when (SELECT count(*) 											    ");
    	query.append("                        			 FROM TAWORKORDER											    ");
    	query.append("                        			 WHERE (comp_no, wkor_id) IN ( SELECT comp_no, wkor_id   		");
    	query.append("                        		 						           FROM TAWOREQRES					");
    	query.append("                        	 							           WHERE  1=1						");
    	query.append("                         								           AND comp_no = a.comp_no	        ");
    	query.append("                         								           AND woreq_id = a.woreq_id	    ");
    	query.append("                         			                               AND wkor_id IS NOT NULL	)		");
    	query.append("                         			 AND wo_status != ?											    ");
    	query.append("                        			)															    ");
    	query.append("                        = 0 then 'COM' else 'WRK'	end										        ");
    	query.append("WHERE 1=1																                            ");
    	query.append("AND (comp_no, woreq_id) IN (SELECT comp_no, woreq_id 										        ");
    	query.append("					          FROM TAWOREQRES										                ");
    	query.append("					          WHERE 1=1											                    ");
    	query.append("					          AND wkor_id = ?										                ");
    	query.append("					          AND comp_no = ?		)								                ");
    	
    	Object[] objects = new Object[] {
    			"C"
    			,maWoResultMstrDetailDTO.getWkOrId()
    			,maWoResultMstrDetailDTO.getCompNo()
    	};
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    public int updateWoResStatus(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO, String woResStatus)
    {
        QueryBuffer query = new QueryBuffer();
        int rtnValue  = 0;

        query.append("UPDATE TAWOREQRES SET wores_status = ?                                   ");
        query.append("WHERE 1=1                                                             ");
        query.append("AND comp_no = ?                                                       ");
        query.append("AND wkor_id = ?                                       ");
        
        Object[] objects = new Object[] {
                woResStatus
                ,maWoResultMstrDetailDTO.getCompNo()
                ,maWoResultMstrDetailDTO.getWkOrId()
        };
        
        rtnValue = getJdbcTemplate().update(query.toString(), objects);
        
        return rtnValue;
    }
    
    // 작업계획목록 존재여부 검사
    public String woPlanCheck(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO,User user) {
        
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT COUNT(1)       ");
        query.append("FROM TAWOPLAN x       ");
        query.append("WHERE 1=1     ");
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        query.getStringEqualQuery("x. wkor_id", maWoResultMstrCommonDTO.getWkOrId());
        
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
    
    @Override
    public int updateWoPlanStatus(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO, String woStatus)
    {
        QueryBuffer query = new QueryBuffer();
        int rtnValue  = 0;

        query.append("UPDATE TAWOPLAN SET wo_status = ?                                   ");
        query.append("WHERE 1=1                                                             ");
        query.append("AND comp_no = ?                                                       ");
        query.append("AND wkor_id = ?                                       ");
        
        Object[] objects = new Object[] {
        		woStatus
                ,maWoResultMstrDetailDTO.getCompNo()
                ,maWoResultMstrDetailDTO.getWkOrId()
        };
        
        rtnValue = getJdbcTemplate().update(query.toString(), objects);
        
        return rtnValue;
    }
    // 이상점검처리(작업결과) 조치결과 정상 업데이트
    public int updatePmPointStatusGdWoRslt(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;

        query.append("UPDATE TAWONGPOINT a SET    									                    ");
    	query.append(" 	pm_point_rep_status =    									                    ");
    	query.append("			(	CASE WHEN															");
    	query.append("            			(SELECT count(*) 											");
    	query.append("             			 FROM TAWORKORDER  											");
    	query.append("            			 WHERE 1=1 													");
    	query.append("                 		 AND wo_status != ?                                         ");
    	query.append("             			 AND (comp_no, wkor_id) IN (SELECT comp_no, wkor_id 		");
    	query.append("            			 				            FROM TAWONGPOINTRES   			");
    	query.append("             			 				            WHERE  1=1 						");
    	query.append("             			 				            AND ngpointres_method = ? 		");
    	query.append("            			 				            AND wkor_id IS NOT NULL 		");
    	query.append("             			 				            AND comp_no = a.comp_no	    	");
    	query.append("             			 				            AND wongpoint_id = a.wongpoint_id ");
    	query.append("            			 				             )								");
    	query.append("             			) = 0														");
    	query.append("					 THEN 'GD' ELSE pm_point_rep_status 							");
    	query.append("				END	)																");
    	query.append(" 	,repair_date = ?   									                            ");
    	query.append(" 	,repair_by   = ?   									                            ");
    	query.append(" 	,repair_desc = ?   									                            ");
    	query.append("WHERE 1=1																			");
		query.append("AND (comp_no, wongpoint_id) IN (SELECT comp_no, wongpoint_id   					");
	    query.append("                                FROM TAWONGPOINTRES      							");
	    query.append("                                WHERE comp_no = ? 								");
	    query.append("                                 AND wkor_id =  ?  								");
	    query.append("                              )  													");									
         
    	
	    Object[] objects = new Object[] {
	    		"C"
	    		,"WORSLT"
	    		,maWoResultMstrDetailDTO.getEndDate()
	    		,maWoResultMstrDetailDTO.getEmpId()
	    		,maWoResultMstrDetailDTO.getPerform()
	    		,maWoResultMstrDetailDTO.getCompNo()
	    		,maWoResultMstrDetailDTO.getWkOrId()
	    };
        
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    // 이상점검처리(작업결과) 조치결과 (정상->이상) 업데이트
    public int updatePmPointStatusBdWoRslt(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO, String pmPointRepStatus)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query.append("UPDATE TAWONGPOINT SET    					");
    	query.append(" 		pm_point_rep_status 	= ?				");
    	query.append("WHERE 1=1										");
    	query.append("AND comp_no = ?								");
    	query.append("AND wongpoint_id IN (SELECT wongpoint_id   	");
    	query.append("                     FROM TAWONGPOINTRES      ");
    	query.append("                     WHERE comp_no = ? 		");
    	query.append("                      AND wkor_id =  ?  		");
    	query.append("                    )  						");									
    	
    	
    	Object[] objects = new Object[] {
    			pmPointRepStatus
    			,maWoResultMstrDetailDTO.getCompNo()
    			,maWoResultMstrDetailDTO.getCompNo()
    			,maWoResultMstrDetailDTO.getWkOrId()
    	};
    	
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    // 이상점검처리(작업계획) 조치결과 정상 업데이트
    public int updatePmPointStatusGdWoPlan(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO)
    {
        QueryBuffer query = new QueryBuffer();
        int rtnValue  = 0;

        query.append("UPDATE TAWONGPOINT a SET    									                    ");
    	query.append(" pm_point_rep_status =    									                    ");
    	query.append("			(	CASE WHEN															");
    	query.append("         				(SELECT count(*)											");
    	query.append("             			 FROM TAWOPLAN 												");
    	query.append("            			 WHERE 1=1 													");
    	query.append("                 		 AND wo_status != ?                                         ");
    	query.append("             			 AND (comp_no, wkor_id) IN (SELECT comp_no, wkor_id 		");
    	query.append("             			 				            FROM TAWONGPOINTRES  			");
    	query.append("            			 				            WHERE  1=1  					");
    	query.append("             			 				            AND ngpointres_method = ?   	");
    	query.append("            			 				            AND wkor_id IS NOT NULL   		");
    	query.append("             			 				            AND comp_no = a.comp_no	    	");
    	query.append("             			 				            AND wongpoint_id = a.wongpoint_id ");
    	query.append("            			 				           )                    			");
    	query.append("            			) = 0														");
    	query.append("					  THEN 'GD' ELSE pm_point_rep_status 							");
    	query.append("				 END	)															");
    	query.append("  ,repair_date = ?                                                                ");
        query.append("  ,repair_by   = ?                                                                ");
        query.append("  ,repair_desc = ?                                                                ");
    	query.append("WHERE 1=1																			");
		query.append("AND (comp_no, wongpoint_id) IN (SELECT comp_no, wongpoint_id   					");
	    query.append("                                FROM TAWONGPOINTRES      							");
	    query.append("                                WHERE comp_no = ? 								");
	    query.append("                                 AND wkor_id =  ?  								");
	    query.append("                               )  												");									
    	
	    Object[] objects = new Object[] {
	    		"C"
	    		,"WOPLAN"
	    		,maWoResultMstrDetailDTO.getEndDate()
                ,maWoResultMstrDetailDTO.getEmpId()
                ,maWoResultMstrDetailDTO.getPerform()
	    		,maWoResultMstrDetailDTO.getCompNo()
	    		,maWoResultMstrDetailDTO.getWkOrId()
	    };
        
        rtnValue = getJdbcTemplate().update(query.toString(), objects);
        
        return rtnValue;
    }
    // 이상점검처리(작업계획) 조치결과 (정상->이상) 업데이트
    public int updatePmPointStatusBdWoPlan(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO, String pmPointRepStatus)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query.append("UPDATE TAWONGPOINT SET    					");
    	query.append(" 		pm_point_rep_status 	= ?				");
    	query.append("WHERE 1=1										");
    	query.append("AND comp_no = ?								");
    	query.append("AND wongpoint_id IN (SELECT wongpoint_id   	");
    	query.append("                     FROM TAWONGPOINTRES   	");
    	query.append("                     WHERE comp_no = ? 		");
    	query.append("                      AND wkor_id =  ?  		");
    	query.append("                    )  						");									
    	
    	Object[] objects = new Object[] {
    			pmPointRepStatus
    			,maWoResultMstrDetailDTO.getCompNo()
    			,maWoResultMstrDetailDTO.getCompNo()
    			,maWoResultMstrDetailDTO.getWkOrId()
    	};
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    // 이상점검처리(작업요청) 조치결과 정상 업데이트
    public int updatePmPointStatusGdWoReq(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO)
    {
        QueryBuffer query = new QueryBuffer();
        int rtnValue  = 0;

        query.append("UPDATE TAWONGPOINT a SET    									               	");
    	query.append(" pm_point_rep_status =    									                ");
    	query.append("			(	CASE WHEN														");
    	query.append("            		(SELECT count(*) 											");
    	query.append("             		 FROM TAWOREQ  												");
    	query.append("             		 WHERE 1=1  												");
    	query.append("             		 AND woreq_status != ?   									");
    	query.append("             		 AND (comp_no, woreq_id) IN (SELECT comp_no, woreq_id  		");
    	query.append("             			 			             FROM TAWONGPOINTRES   			");
    	query.append("             			 			             WHERE  1=1   					");
    	query.append("             			 			             AND ngpointres_method = ?     	");
    	query.append("             			 			             AND woreq_id IS NOT NULL     	");
    	query.append("             			 			             AND comp_no = a.comp_no	    ");
    	query.append("             			 			             AND wongpoint_id = a.wongpoint_id ");
    	query.append("             			 			            )                    			");
    	query.append("         			)=0															");
    	query.append("					  THEN 'GD' ELSE pm_point_rep_status 						");
    	query.append("				END	)															");
    	query.append("  ,repair_date = ?                                                            ");
        query.append("  ,repair_by   = ?                                                            ");
        query.append("  ,repair_desc = ?                                                            ");
    	query.append("WHERE 1=1																		");
        query.append("AND (comp_no, wongpoint_id) IN (SELECT comp_no, wongpoint_id   				");
        query.append("                                FROM TAWONGPOINTRES       					");
        query.append("                                WHERE (comp_no, woreq_id) IN (SELECT comp_no, woreq_id ");
        query.append("                                                             FROM TAWOREQRES  ");
        query.append("                                            	               WHERE comp_no = ?");
        query.getAndQuery("woreqres_id", maWoResultMstrDetailDTO.getWoReqResId());
        query.append("                                           	               AND wkor_id = ?  ");
        query.append("                                           	              )    				");
        query.append("                               )									    		");
    		
	    Object[] objects = new Object[] {
	    		"COM"
	    		,"WOREQ"
	    		,maWoResultMstrDetailDTO.getEndDate()
                ,maWoResultMstrDetailDTO.getEmpId()
                ,maWoResultMstrDetailDTO.getPerform()
	    		,maWoResultMstrDetailDTO.getCompNo()
	    		,maWoResultMstrDetailDTO.getWkOrId()
	    };
        
        rtnValue = getJdbcTemplate().update(query.toString(), objects);
        
        return rtnValue;
    }
    // 이상점검처리(작업요청) 조치결과 (정상->이상) 업데이트
    public int updatePmPointStatusBdWoReq(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO, String pmPointRepStatus)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query.append("UPDATE TAWONGPOINT SET    						                                       ");
    	query.append(" 		pm_point_rep_status 	= ?					                                       ");
    	query.append("WHERE 1=1											                                       ");
    	query.append("AND (comp_no, wongpoint_id) IN (SELECT comp_no, wongpoint_id   		                   ");
    	query.append("                                FROM TAWONGPOINTRES     		                           ");
    	query.append("                                WHERE (comp_no, woreq_id) IN (SELECT comp_no, woreq_id   ");
    	query.append("                                                              FROM TAWOREQRES            ");
    	query.append("                                                              WHERE comp_no=?            ");
    	query.getAndQuery("woreqres_id", maWoResultMstrDetailDTO.getWoReqResId());
    	query.append("                                                              AND wkor_id = ?            ");
    	query.append("                                                             )    			           ");
    	query.append("                                )								                           ");
    	
    	Object[] objects = new Object[] {
    			pmPointRepStatus
    			,maWoResultMstrDetailDTO.getCompNo()
    			,maWoResultMstrDetailDTO.getWkOrId()
    	};
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), objects);
    	return rtnValue;
    }
    
    
	@Override
	public int updateWoStatus(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO, User loginUser) {
		
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
		query.append("UPDATE TAWORKORDER SET                		");
		query.append("       wo_status          = ?         		");
		query.append("WHERE  wkor_id            = ?         		");
		query.append("  AND  comp_no            = ?			   		");

    	Object[] objects = new Object[] {
    			maWoResultMstrDetailDTO.getWoStatusId()
    			,maWoResultMstrDetailDTO.getWkOrId()
    			,loginUser.getCompNo()
    	};
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
	}
	@Override
	public String[] getNextCalibDate(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultPmCalDTO maWoResultPmCalDTO) {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("SELECT 																");
    	if ("Y".equals(MwareConfig.getIsWopmcalibResched())) {
    		query.append("	CASE                                                			");
    		query.append("		WHEN y.period_type = 'D' THEN TO_CHAR(TO_DATE(?,'yyyymmdd') + (y.cycle * 1 ) -1,'yyyymmdd')                ");
    		query.append("      WHEN y.period_type = 'W' THEN TO_CHAR(TO_DATE(?,'yyyymmdd') + (7 * y.cycle * 1 ) -1,'yyyymmdd')            ");
    		query.append("      WHEN y.period_type = 'M' THEN TO_CHAR(ADD_MONTHS(TO_DATE(?,'yyyymmdd'),y.cycle * 1) -1,'yyyymmdd')        	");
    		query.append("      WHEN y.period_type = 'Y' THEN TO_CHAR(ADD_MONTHS(TO_DATE(?,'yyyymmdd'),12 * y.cycle * 1)-1,'yyyymmdd')	");
    		query.append("  END         								AS nextCalibDate	");
    		query.append("  ,y.period_type                              AS periodType       ");
    		query.append("  ,y.cycle                                    AS cycle            ");
    	}else{
    		query.append(" (SELECT                                                    		");
    		query.append("     (SELECT MIN(sched_date)                                 		");
    		query.append("      FROM TAPMSCHED b                                       		");
    		query.append("      WHERE EXISTS                                           		");
    		query.append("        		(SELECT 1                                           ");
    		query.append("         		 FROM TAPMSCHED                                     ");
    		query.append("         		 WHERE wkor_id = a.wkor_id                          ");
    		query.append("         		 AND pm_id   = b.pm_id                              ");
    		query.append("         		 AND comp_no   = a.comp_no )                        ");
    		query.append("      AND b.sched_date > ?	                         			");
    		query.append("      AND b.comp_no = a.comp_no    )                           	");
    		query.append("  FROM TAWORKORDER  a                                      		");
    		query.append("  WHERE a.comp_no = x.comp_no                                     ");
    		query.append("  AND a.wkor_id = x.wkor_id    ) 				AS	nextCalibDate   ");
        	query.append("  ,y.period_type                              AS periodType       ");
            query.append("  ,y.cycle                                    AS cycle            ");
    	}
//    	query.append("FROM TAWORKORDER x LEFT OUTER JOIN TAPMLST y							");
//    	query.append("ON x.comp_no = y.comp_no            									");
//    	query.append("AND x.pm_id = y.pm_id													");
    	query.append("FROM TAWORKORDER x INNER JOIN TAWOEQUIP c ON x.wkor_id = c.wkor_id AND x.comp_no = c.comp_no     ");
    	query.append("     INNER JOIN TAPMEQUIP z ON c.equip_id = z.equip_id     ");
    	query.append("     INNER JOIN TAPMLST y ON z.pm_id = y.pm_id AND y.is_last_version ='Y' AND y.is_deleted ='N'    ");
    	query.append("WHERE x.comp_no = ?													");
    	query.append("AND x.wkor_id = ?														");
    	query.append("AND ROWNUM = 1                                                        ");

    	Object[] objects;
    	if ("Y".equals(MwareConfig.getIsWopmcalibResched())) {
    		objects  = new Object[] {
    				CommonUtil.getRowDateToNum(maWoResultPmCalDTO.getWkorDate()) 
    				,CommonUtil.getRowDateToNum(maWoResultPmCalDTO.getWkorDate())
    				,CommonUtil.getRowDateToNum(maWoResultPmCalDTO.getWkorDate())
    				,CommonUtil.getRowDateToNum(maWoResultPmCalDTO.getWkorDate())
    				,maWoResultMstrCommonDTO.getCompNo()
    				,maWoResultMstrCommonDTO.getWkOrId()
    		};
    	}else{
    		objects  = new Object[] {
    		        CommonUtil.getRowDateToNum(maWoResultPmCalDTO.getWkorDate())
    				,maWoResultMstrCommonDTO.getCompNo()
    				,maWoResultMstrCommonDTO.getWkOrId()
      		};
    	}
    	
    	return QueryBuffer.singleRowToStringArray(getJdbcTemplate().queryForList(query.toString(),objects));
    }
	
	public String[] getNextPmWoDate(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultMstrDetailDTO maWoResultMstrDetailDTO) 
	{
		QueryBuffer query = new QueryBuffer();
		
		query.append("SELECT 																");
		if ("Y".equals(MwareConfig.getIsWopmworkResched())) {
			query.append("	CASE                                                			");
			query.append("		WHEN y.period_type = 'D' THEN TO_CHAR(TO_DATE(?,'yyyymmdd') + (y.cycle * 1 ) -1,'yyyymmdd')             ");
			query.append("      WHEN y.period_type = 'W' THEN TO_CHAR(TO_DATE(?,'yyyymmdd') + (7 * y.cycle * 1 ) -1,'yyyymmdd')         ");
			query.append("      WHEN y.period_type = 'M' THEN TO_CHAR(ADD_MONTHS(TO_DATE(?,'yyyymmdd'),y.cycle * 1) -1,'yyyymmdd')      ");
			query.append("      WHEN y.period_type = 'Y' THEN TO_CHAR(ADD_MONTHS(TO_DATE(?,'yyyymmdd'),12 * y.cycle * 1)-1,'yyyymmdd')	");
			query.append("  END         								AS nextPmWoDate		");
			query.append("  ,y.period_type                              AS periodType       ");
			query.append("  ,y.cycle                                    AS cycle            ");
		}else{
			query.append(" (SELECT                                                    		");
			query.append("     (SELECT MIN(sched_date)                                 		");
			query.append("      FROM TAPMSCHED b                                       		");
			query.append("      WHERE EXISTS                                           		");
			query.append("        		(SELECT 1                                           ");
			query.append("         		 FROM TAPMSCHED                                     ");
			query.append("         		 WHERE wkor_id = a.wkor_id                          ");
			query.append("         		 AND pm_id   = b.pm_id                              ");
			query.append("         		 AND comp_no   = a.comp_no )                        ");
			query.append("      AND b.sched_date > ?	                         			");
			query.append("      AND b.comp_no = a.comp_no    )                           	");
			query.append("  FROM TAWORKORDER  a                                      		");
			query.append("  WHERE a.comp_no = x.comp_no                                     ");
			query.append("  AND a.wkor_id = x.wkor_id    ) 				AS nextPmWoDate   	");
			query.append("  ,y.period_type                              AS periodType       ");
			query.append("  ,y.cycle                                    AS cycle            ");
		}
		query.append("FROM TAWORKORDER x INNER JOIN TAWOEQUIP c      						");
		query.append("ON x.wkor_id = c.wkor_id AND x.comp_no = c.comp_no    				");
		query.append("	INNER JOIN TAPMEQUIP z      										");
		query.append("	ON c.equip_id = z.equip_id     										");
		query.append("  	INNER JOIN TAPMLST y      										");
		query.append("		ON z.pm_id = y.pm_id     										");
		query.append("		 AND y.is_last_version ='Y' AND y.is_deleted ='N'    			");
		query.append("WHERE 1 = 1															");
		query.append(" AND x.pm_id = y.pm_id												");
		query.append(" AND x.comp_no = ?													");
		query.append(" AND x.wkor_id = ?													");
		query.append(" AND x.wo_type = ?													");
		query.append(" AND y.revision_status = 'C'											");
		query.append("AND ROWNUM = 1                                                        ");
		
		Object[] objects;
		if ("Y".equals(MwareConfig.getIsWopmworkResched()))
		{
			objects  = new Object[] {
					CommonUtil.getRowDateToNum(maWoResultMstrDetailDTO.getWkorDate()) 
					,CommonUtil.getRowDateToNum(maWoResultMstrDetailDTO.getWkorDate())
					,CommonUtil.getRowDateToNum(maWoResultMstrDetailDTO.getWkorDate())
					,CommonUtil.getRowDateToNum(maWoResultMstrDetailDTO.getWkorDate())
					,maWoResultMstrCommonDTO.getCompNo()
					,maWoResultMstrCommonDTO.getWkOrId()
					,maWoResultMstrDetailDTO.getWoTypeId()
			};
		}else{
			objects  = new Object[] {
					CommonUtil.getRowDateToNum(maWoResultMstrDetailDTO.getWkorDate())
					,maWoResultMstrCommonDTO.getCompNo()
					,maWoResultMstrCommonDTO.getWkOrId()
					,maWoResultMstrDetailDTO.getWoTypeId()
			};
		}
		
		return QueryBuffer.singleRowToStringArray(getJdbcTemplate().queryForList(query.toString(),objects));
	}
    @Override
    public int linkPtIssList(String wopartId, String ptisslistId, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("UPDATE TAWOPARTS   SET                        ");
        query.append("      ptisslist_id = ?                        ");
        query.append("WHERE 1=1                                     ");
        query.append("AND comp_no   = ?                             ");
        query.append("AND wopart_id = ?                             ");
        
        Object[] objects = new Object[] {
                ptisslistId
                ,loginUser.getCompNo()
                ,wopartId
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
	@Override
	public int updateWoTotAmt(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO, User loginUser) throws Exception
	{
    	QueryBuffer query = new QueryBuffer();
    	
    	int rtnValue  = 0;
    	
    	query.append("UPDATE TAWORKORDER x SET							");
    	query.append("	part_amt = (SELECT NVL(SUM(tot_price),0)		");
    	query.append(" 				FROM TAWOPARTS						");
    	query.append(" 				WHERE comp_no = x.comp_no			");
    	query.append(" 				 AND wkor_id = x.wkor_id )			");
    	query.append("	,tot_amt = ((SELECT NVL(SUM(tot_price),0)		");
    	query.append(" 				 FROM TAWOPARTS						");
    	query.append(" 				 WHERE comp_no = x.comp_no			");
    	query.append(" 				  AND wkor_id = x.wkor_id			");
    	query.append(" 			    ) + NVL(labor_amt,0))				");
    	query.append("WHERE wkor_id	 		= ?							");
    	query.append("  AND comp_no			= ?							");
    	
    	Object[] objects = new Object[] {    			
    			maWoResultMstrDetailDTO.getWkOrId(),
    			maWoResultMstrDetailDTO.getCompNo()
    	};
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
	}
	public int cancelConfirmPmSched(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO, String pmSchedStatus)
    {
        QueryBuffer query = new QueryBuffer();
        int rtnValue  = 0;
        
        query.append("UPDATE TAPMSCHED   SET                                ");
        query.append("   pmsched_status     = ?                             ");
        query.append("  ,actual_date        = ''                            ");
        query.append("  ,actual_time        = ''                            ");
        query.append("  ,check_by           = ''                            ");
        query.append("  ,WO_STATUS          = 'P'                           ");
        query.append("WHERE wkor_id         = ?                             ");
        query.append("  AND comp_no         = ?                             ");
        
        Object[] objects = new Object[] {
                 pmSchedStatus
               , maWoResultMstrDetailDTO.getWkOrId()
               , maWoResultMstrDetailDTO.getCompNo()
        };
        
        rtnValue = getJdbcTemplate().update(query.toString(), objects);
        
        return rtnValue;
    }
	
	public String findPlanInitDate(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO, User loginUser, String pmId) 
	{
		QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                               		");
        query.append("		NVL(MAX(x.plan_init_date),'')		");
        query.append("FROM TAPMSCHED x 							");
        query.append("WHERE  1 = 1                          	");
        query.append("  AND  x.comp_no = ?                 		");
        query.append("  AND  x.pm_id = ?                    	");
        query.append(" 	AND  x.wkor_id = ?                   	");

        
        Object[] objects = new Object[] {
        		loginUser.getCompNo()
        		,pmId
        		,maWoResultMstrDetailDTO.getWkOrId()
    	};
        
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString(),objects));
	}
	
	public int updatePlanInitDate(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO, User loginUser, String pmId, String planInitDate) 
	{
		QueryBuffer query = new QueryBuffer();
        
        query.append("UPDATE TAPMSCHED SET		");
        query.append("	plan_init_date = ?		");
        query.append("WHERE  1 = 1 				");
        query.append("  AND  comp_no = ? 	    ");
        query.append("  AND  pm_id = ? 		    ");
        query.append(" 	AND  wkor_id = ?        ");
         
        Object[] objects = new Object[] {
        		planInitDate.length()>8?CommonUtil.convertDate(planInitDate):planInitDate
  				,loginUser.getCompNo()
        		,pmId
        		,maWoResultMstrDetailDTO.getWkOrId()
    	};
        
        return getJdbcTemplate().update(query.toString(),objects);
	}
	
	public List findWoReqId(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO, User user) 
    {
	    QueryBuffer query = new QueryBuffer();

	    query.append("SELECT woreq_id        AS woReqId     ");
        query.append("     , woreqres_id     AS woReqResId  ");
        query.append("  FROM TAWOREQRES                     ");
        query.append(" WHERE 1 = 1                          ");
        query.append("   AND comp_no = ?                    ");
        query.append("   AND wkor_id = ?                    ");
                 
        Object[] objects = new Object[] {
                user.getCompNo()
                , maWoResultMstrDetailDTO.getWkOrId()
        };
        
        return getJdbcTemplate().queryForList(query.toString(), objects);
    }
}