package dream.work.cal.pmmonth.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.CommonUtil;
import common.util.QuerySqlBuffer;
import dream.work.cal.pmmonth.dao.MaWoMonthSchedListDAO;
import dream.work.cal.pmmonth.dto.MaWoMonthSchedCommonDTO;

/**
 * 월간예방일정 - 목록 dao
 * @author  kim21017
 * @version $Id: MaWoMonthSchedListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maWoMonthSchedListDAOTarget"
 * @spring.txbn id="maWoMonthSchedListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaWoMonthSchedListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaWoMonthSchedListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaWoMonthSchedListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoMonthSchedCommonDTO
     * @return List
     */
    public List findSchedList(MaWoMonthSchedCommonDTO maWoMonthSchedCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                  																				");
        query.append("SELECT                   																															");
        query.append("       ''																			seqNo,															");
        query.append("		 ''																			isDelCheck,														");
        query.append("       x.pmsched_id																pmSchedId,      												");
        query.getDate("x.plan_date", "planDate");
        query.append(",																																					");
        query.getDate("x.actual_date", "completeDate");
        query.append(",																																					");
        query.append("       x.sched_date																schedDate,                                                      ");
        query.append("       y.pm_no																	pmNo,        													");
        query.append("       dbo.SFACODE_TO_DESC(x.pmsched_status,'PMSCHED_STATUS','SYS','','"+user.getLangId()+"')		pmSchedStatus,									");
        query.append("       z.emp_id                                                                   				empId,       									");
        query.append("       (SELECT emp_name FROM TAEMP WHERE comp_no = y.comp_no AND emp_id = y.emp_id)		        planEmpDesc,	                                ");
        query.append("       (SELECT emp_name FROM TAEMP WHERE comp_no = z.comp_no AND emp_id = z.emp_id)		        exeEmpDesc,	                                    ");
        query.append("       y.description																pmDesc,															");
        query.append("       (SELECT full_desc                                                                                                                          ");
        query.append("          FROM TAEQLOC                                                                                                                            ");
        query.append("          WHERE comp_no = zz.comp_no                                                                                                              ");
        query.append("           AND eqloc_id = zz.eqloc_id)                                            AS eqLocDesc,                                                   ");
        query.append("        CONVERT(NVARCHAR,(CASE zz.eqctg_type WHEN 'MD' THEN '('+zz.old_eq_no+')' + zz.item_no ELSE zz.item_no END))                        AS EQUIPNO,		");
        query.append("        (SELECT description FROM TADEPT WHERE comp_no = zz.comp_no AND dept_id = zz.usage_dept)                                            AS usageDeptDesc	");
        query.append("      , (SELECT a.item_no                                                      																				");
        query.append("                  FROM TAEQUIPMENT a                                                                                                                          ");
        query.append("                  WHERE a.comp_no = zz.comp_no                                                                                                                ");
        query.append("                   AND a.equip_id = zz.p_equip_id)		                                                                                 AS pequipNo		");
        query.append("      , (SELECT a.description                                                      																			");
        query.append("                  FROM TAEQUIPMENT a                                                                                                                          ");
        query.append("                  WHERE a.comp_no = zz.comp_no                                                                                                                ");
        query.append("                   AND a.equip_id = zz.p_equip_id)                                                                                         AS pequipDesc		");
        query.append("      , (SELECT (SELECT b.description FROM TADEPT b WHERE b.comp_no = a.comp_no AND b.dept_id = a.usage_dept)                                                 ");
        query.append("                  FROM TAEQUIPMENT a                                                                                                                          ");
        query.append("                  WHERE a.comp_no = zz.comp_no                                                                                                                ");
        query.append("                   AND a.equip_id = zz.p_equip_id)                                                                                AS pequipUsaDeptDesc		");
        query.append("      , (CASE zz.eqctg_type WHEN 'MD' THEN '('+zz.old_eq_no+')'+zz.description ELSE zz.description END)	                        AS equipDesc,	");
        query.append("       (SELECT description																														");
        query.append("          FROM TADEPT																																");
        query.append("         WHERE comp_no = y.comp_no																												");
        query.append("           AND dept_id = y.dept_id)												planDeptDesc,													");
        query.append("       (SELECT description																														");
        query.append("          FROM TADEPT																																");
        query.append("         WHERE comp_no = z.comp_no																												");
        query.append("           AND dept_id = z.dept_id)												exeDeptDesc,													");
        query.append("		 (SELECT description																														");
        query.append("		  FROM TAWKCTR																																");
        query.append("		  WHERE comp_no = y.comp_no																													");
        query.append("		  AND wkctr_id = y.wkctr_id) 												wkCtrDesc,														");
        query.append("       dbo.SFACODE_TO_DESC(y.pm_type,y.wo_type+'_TYPE','SYS','','"+user.getLangId()+"')			pmTypeDesc,										");
        query.append("       y.pm_type																	pmType,															");
        query.append("       dbo.SFACODE_TO_DESC(y.schedule_type,'SCHEDULE_TYPE','SYS','','"+user.getLangId()+"')		scheduleTypeDesc,								");
        query.append("       CONVERT(NVARCHAR, y.cycle)+y.period_type									cycle,															");
        query.append("       y.USAGE																	USAGE,                                                          ");
        query.append("       z.wo_status                                                          		woStatus,                                    					");
        query.append("       x.pmsched_status															pmStatusCode,                                             		");
        query.append("        y.pm_id																	pmId,                                                           ");
        query.append("       (SELECT param2 FROM TACDSYSD WHERE cdsysd_no=y.pm_type AND list_type= y.wo_type+'_TYPE') param,  											");
        query.append("		(SELECT b.param1 FROM TACDSYSD b WHERE b.list_type=z.wo_type+'_TYPE' AND b.cdsysd_no= z.pm_type) woparam,									");
        query.append("       x.wkor_id																	wkorId                                                          ");
        query.append("      ,z.vendor_id     															vendorId														");
        query.append("      ,dbo.SFAIDTODESC(z.vendor_id, '', 'VENDOR', z.comp_no)           			vendorDesc 														");
        query.append("      ,z.tot_amt       															totAmt															");
        query.append("      ,z.self_vendor_type                                            				selfVendorType    												");
        query.append("      ,dbo.SFACODE_TO_DESC(z.self_vendor_type,'SELF_VENDOR_TYPE','SYS','','"+user.getLangId()+"')	selfVendorTypeDesc                              ");
        query.append("      ,x.remark                                                                   remark                                                          ");
        query.append("FROM TAPMSCHED x INNER JOIN TAPMLST y																												");
        query.append("ON x.comp_no = y.comp_no AND x.pm_id = y.pm_id																									");
        query.append("LEFT OUTER JOIN TAEQUIPMENT zz                                                                                                                    ");
        query.append("ON x.comp_no = zz.comp_no AND x.equip_id = zz.equip_id AND zz.is_deleted='N'                                                                      ");
        query.append("LEFT OUTER JOIN taworkorder z																														");
        query.append("ON x.wkor_id = z.wkor_id AND x.comp_no = z.comp_no																								");
        query.append("WHERE 1=1                    																														");
        query.append(this.getWhere(maWoMonthSchedCommonDTO, user));
        query.getOrderByQuery("x.pmsched_id", "x.sched_date, y.description", maWoMonthSchedCommonDTO.getOrderBy(), maWoMonthSchedCommonDTO.getDirection());

        return getJdbcTemplate().queryForList(query.toString(maWoMonthSchedCommonDTO.getIsLoadMaxCount(), maWoMonthSchedCommonDTO.getFirstRow()));
    }
    
    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id: MaWoMonthSchedListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param day
     * @param status
     * @param compNo
     * @param deptId
     * @param deptDesc
     * @return
     * @throws Exception
     */
    private String getWhere(MaWoMonthSchedCommonDTO maWoMonthSchedCommonDTO, User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        String yyyymmdd    = CommonUtil.getRowDateToNum(maWoMonthSchedCommonDTO.getYyyymmdd());
        String compNo      = maWoMonthSchedCommonDTO.getCompNo();
        String deptId      = maWoMonthSchedCommonDTO.getDeptId();
        String deptDesc    = maWoMonthSchedCommonDTO.getDeptDesc();
        String type        = maWoMonthSchedCommonDTO.getSchedType();
        String eqLocId     = maWoMonthSchedCommonDTO.getEqLocId();
        String eqLocDesc   = maWoMonthSchedCommonDTO.getEqLocDesc();
        String eqCtgId     = maWoMonthSchedCommonDTO.getEqCtgId();
        String eqCtgDesc   = maWoMonthSchedCommonDTO.getEqCtgDesc();
        String eqCtgTypeId     = maWoMonthSchedCommonDTO.getEqCtgTypeId();
        String eqCtgTypeDesc   = maWoMonthSchedCommonDTO.getEqCtgTypeDesc();
        String mainMngId   = maWoMonthSchedCommonDTO.getMainMngId();
        String mainMngName = maWoMonthSchedCommonDTO.getMainMngName();
        String subMngId    = maWoMonthSchedCommonDTO.getSubMngId();
        String subMngName  = maWoMonthSchedCommonDTO.getSubMngName();
        String isLawEq     = maWoMonthSchedCommonDTO.getIsLawEq();
        String plfTypeId   = maWoMonthSchedCommonDTO.getPlfTypeId();
        String plfTypeDesc = maWoMonthSchedCommonDTO.getPlfTypeDesc();
        String woTypeId    = maWoMonthSchedCommonDTO.getWoTypeId();
        String woTypeDesc  = maWoMonthSchedCommonDTO.getWoTypeDesc();
        String pmTypeId    = maWoMonthSchedCommonDTO.getPmTypeId();
        String pmTypeDesc  = maWoMonthSchedCommonDTO.getPmTypeDesc();
        String wkCtrId     = maWoMonthSchedCommonDTO.getWkCtrId();
        String wkCtrDesc   = maWoMonthSchedCommonDTO.getWkCtrDesc();
        String equipId     = maWoMonthSchedCommonDTO.getEquipId();
        String equipDesc   = maWoMonthSchedCommonDTO.getEquipDesc();
        String pmNo  = maWoMonthSchedCommonDTO.getPmNo();
        String empId     = maWoMonthSchedCommonDTO.getEmpId();
        String empDesc   = maWoMonthSchedCommonDTO.getEmpDesc();
        String plantId     = maWoMonthSchedCommonDTO.getPlantId();
        String plantDesc   = maWoMonthSchedCommonDTO.getPlantDesc();
        String wkOrId      = maWoMonthSchedCommonDTO.getWkOrId();
        String equipNo          = maWoMonthSchedCommonDTO.getEquipNo();
        String eqUsaDeptId      = maWoMonthSchedCommonDTO.getEqUsaDeptId();
        String eqUsaDeptDesc    = maWoMonthSchedCommonDTO.getEqUsaDeptDesc();
        String pequipId         = maWoMonthSchedCommonDTO.getPequipId();
        String pequipDesc       = maWoMonthSchedCommonDTO.getPequipDesc();
        String peqUsaDeptId     = maWoMonthSchedCommonDTO.getPeqUsaDeptId();
        String peqUsaDeptDesc   = maWoMonthSchedCommonDTO.getPeqUsaDeptDesc();
        
        if(!"".equals(wkOrId)){
            query.getAndQuery("x.wkor_id", wkOrId);
            return query.toString();
        }
        
        query.getAndNumKeyQuery("x.pmsched_id", maWoMonthSchedCommonDTO.getPmSchedId());
 
        query.append("  AND x.sched_date like '"+yyyymmdd+"%'           ");
        
        query.getSysCdQuery("x.pmsched_status", type, "", "PMSCHED_STATUS", compNo, user.getLangId());
        
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        query.getStringEqualQuery("x.IS_DELETED", "N");

        //법정설비여부
        query.getLikeQuery("zz.is_law_eq", isLawEq);
        
        //예방점검기준 번호
        query.getAndQuery("y.pm_no", pmNo);
        //계획부서
        query.getDeptLevelQuery("y.dept_id", deptId, deptDesc, compNo);
        //실행부서
        query.getDeptLevelQuery("z.dept_id", maWoMonthSchedCommonDTO.getExeDeptId(), maWoMonthSchedCommonDTO.getExeDeptDesc(), compNo);
        //위치
        query.getEqLocLevelQuery("zz.eqloc_id", eqLocId, eqLocDesc, compNo);
        
        //종류
        query.getEqCtgLevelQuery("zz.eqctg_id", eqCtgId, eqCtgDesc, compNo);
        
        //담당자(정)
        query.getCodeLikeQuery("zz.main_mng_id", "(SELECT emp_name FROM TAEMP WHERE emp_id = zz.main_mng_id AND comp_no=zz.comp_no)", mainMngId, mainMngName);
        
        //담당자(부)
        query.getCodeLikeQuery("zz.sub_mng_id", "(SELECT emp_name FROM TAEMP WHERE emp_id = zz.sub_mng_id AND comp_no=zz.comp_no)", subMngId, subMngName);
        
        //설비
        query.getCodeLikeQuery("zz.equip_id", "zz.description+zz.item_no", equipId, equipDesc);
        
        //내/외자 구분
        query.getSysCdQuery("zz.plf_type", plfTypeId, plfTypeDesc, "PLF_TYPE", compNo,user.getLangId());
        
        //설비유형
        query.getSysCdQuery("zz.eqctg_type", eqCtgTypeId, eqCtgTypeDesc, "EQCTG_TYPE", compNo,user.getLangId());
        
    	//작업형태
    	query.getSysCdQuery("y.pm_type", pmTypeId, pmTypeDesc, "y.wo_type+'_TYPE'", compNo,user.getLangId());
    	//작업형태
    	query.getSysCdQuery("y.wo_type", woTypeId, woTypeDesc, "WO_TYPE", compNo,user.getLangId());
    	
        query.getAndQuery("y.cycle", maWoMonthSchedCommonDTO.getCycle());
        query.getAndQuery("y.period_type", maWoMonthSchedCommonDTO.getPeriodType());
        //작업그룹 
        query.getWkCtrLevelQuery("y.wkctr_id", wkCtrId, wkCtrDesc, compNo);
        //계획담당자
        query.getCodeLikeQuery("y.emp_id", "(SELECT emp_name FROM TAEMP WHERE emp_id=y.emp_id AND comp_no=y.comp_no)", maWoMonthSchedCommonDTO.getPlanEmpId(), maWoMonthSchedCommonDTO.getPlanEmpDesc());
        //실행담당자
        query.getCodeLikeQuery("z.emp_id", "(SELECT emp_name FROM TAEMP WHERE emp_id=z.emp_id AND comp_no=z.comp_no)", empId, empDesc);
        //공장코드
        query.getCodeLikeQuery("y.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = y.comp_no AND a.plant = y.plant )", plantId, plantDesc);
        
        //최신버전의 설비의 작업만 보여줌.
        query.getAndQuery("zz.is_last_version", "Y");
        
        //설비번호
        query.getLikeQuery("zz.item_no", equipNo);
        
        //설비사용부서
        query.getDeptLevelQuery("z.usage_dept", eqUsaDeptId, eqUsaDeptDesc, compNo);
        
        //상위설비명
        if(!"".equals(pequipId) || !"".equals(pequipDesc))
        {
            query.append(" AND zz.p_equip_id IN                                 ");
            query.append("                  (SELECT a.equip_id                  ");
            query.append("                    FROM TAEQUIPMENT a                ");
            query.append("                    WHERE a.comp_no = x.comp_no       ");
            query.getCodeLikeQuery("a.equip_id", "a.description", pequipId, pequipDesc);
            query.append("                 )                                    ");
        }
        
        //상위설비사용부서
        if(!"".equals(peqUsaDeptId) || !"".equals(peqUsaDeptDesc))
        {
            query.append(" AND zz.p_equip_id IN (SELECT c.equip_id FROM TAEQUIPMENT c ");
            query.append("                          WHERE c.comp_no = a.comp_no         ");
            query.append("                            AND c.usage_dept IN ( SELECT d.dept_id FROM TADEPT d WHERE d.comp_no = c.comp_no          ");
            query.getDeptLevelQuery("d.dept_id", peqUsaDeptId, peqUsaDeptDesc, compNo);
            query.append("                                                 )            ");
            query.append("                        )                                     ");
        }
        
        return query.toString();
    }
    
    /**
     * W/O 생성
     * @author kim21017
     * @version $Id: MaWoMonthSchedListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoMonthSchedCommonDTO
     * @param type
     * @param id
     * @return
     */
    public int createWorkOrder(MaWoMonthSchedCommonDTO maWoMonthSchedCommonDTO, String id)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	int rtnValue  = 0;
    	
    	query.append("INSERT INTO TAWORKORDER(															");
    	query.append("		comp_no,		wkor_id,		wo_no,										");
    	query.append("		description,	wo_status,		wo_type,	pm_type,						");
    	query.append("		pm_id,			pmsched_id,		start_date,	end_date,						");
    	query.append("		dept_id,		emp_id,			wo_gen_type,wkor_date,						");
    	query.append("		wkctr_id,       plant														");
    	query.append("		)																			");
    	query.append("SELECT a.comp_no,		NEXT VALUE FOR SQAWKOR_ID, (SELECT CURRENT_VALUE FROM SYS.SEQUENCES WHERE name = 'SQAWKOR_ID'), ");
    	query.append("		'['+CASE a.pm_type WHEN 'INS' THEN 'PMI' ELSE 'PMW' END+']'+a.description,	'P',	CASE a.pm_type WHEN 'INS' THEN 'PMI' ELSE 'PMW' END, a.pm_type,			");
    	query.append("		a.pm_id,		a.pmsched_id,		a.sched_date,		a.sched_date,		");
    	query.append("		a.dept_id,		a.main_mng_id,		'PMPLAN',		a.wkor_date,			");
    	query.append("		a.wkctr_id																	");
    	query.append("FROM ( SELECT x.comp_no comp_no,													");
    	query.append("				y.description description,											");
    	query.append("				y.pm_type pm_type,													");
    	query.append("				y.pm_id pm_id,														");
    	query.append("				x.pmsched_id pmsched_id,											");
    	query.append("				y.dept_id dept_id,													");
    	query.append("				x.sched_date sched_date,											");
    	query.append("				x.sched_date wkor_date,												");
        query.append("				(SELECT MIN(b.main_mng_id)											");
        query.append("					FROM TAPMEQUIP a, TAEQUIPMENT b									");
        query.append("					WHERE a.comp_no = b.comp_no										");
        query.append("						AND a.equip_id = b.equip_id									");
        query.append("						AND a.pm_id = y.pm_id										");
        query.append("					GROUP By a.comp_no, a.pm_id	) AS main_mng_id,					");
    	query.append("				x.sched_date plan_date,												");
    	query.append("				y.wkctr_id wkctr_id													");
    	query.append("				y.plant plant														");
        query.append("		FROM TAPMSCHED x, TAPMLST y													");
        query.append("		WHERE x.comp_no  = y.comp_no												");
        query.append("		  AND x.pm_id    = y.pm_id													");
    	query.append("		  AND x.wkor_id is null														");
        query.getStringEqualQuery("y.is_active", "Y");
        query.getStringEqualQuery("x.pmsched_status", "P");
        query.getAndNumKeyQuery("x.pmsched_id", id);
    	query.append("		) a																			");
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAWOEQUIP(comp_no,woequip_id,wkor_id,equip_id)			");
    	query.append("SELECT a.comp_no,NEXT VALUE FOR SQAWOEQUIP_ID,a.wkor_id,b.equip_id			");
    	query.append("FROM TAWORKORDER a, TAPMEQUIP b										");
    	query.append("WHERE a.comp_no = b.comp_no											");
    	query.append("AND a.pm_id = b.pm_id													");
    	query.append("AND a.wo_status IN ('P','PPWDA','PPWRA','PPWOA','PRW','PRWDA','PRWRA','PRWOA')");
    	query.append("AND a.wo_type IN ('PMI','PMW')										");
    	query.append("AND a.pmsched_id IN													");
    	query.append("	( SELECT x.pmsched_id												");
        query.append("		FROM TAPMSCHED x, TAPMLST y										");
        query.append("		WHERE x.comp_no  = y.comp_no									");
        query.append("		  AND x.pm_id    = y.pm_id										");
        query.getStringEqualQuery("y.is_active", "Y");
        query.getAndNumKeyQuery("x.pmsched_id", id);
    	query.append("		) 																");
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	return rtnValue;
    }
    
    /**
     * 작업자 생성
     * @author kim21017
     * @version $Id: MaWoMonthSchedListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoMonthSchedCommonDTO
     * @param type
     * @param id
     * @return
     */
    public int createWoCraft(MaWoMonthSchedCommonDTO maWoMonthSchedCommonDTO, String id)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        String compNo      = maWoMonthSchedCommonDTO.getCompNo();

    	query.append("INSERT INTO TAWOCRAFT(																");
    	query.append("		comp_no,		wocraft_id,		wkor_id,										");
    	query.append("		emp_id,			start_date,		end_date										");
    	query.append("		)																				");
    	query.append("SELECT x.comp_no,		NEXT VALUE FOR SQAWOCRAFT_ID, x.wkor_id,						");
        query.append("				(SELECT MIN(b.main_mng_id)												");
        query.append("					FROM TAPMEQUIP a, TAEQUIPMENT b										");
        query.append("					WHERE a.comp_no = b.comp_no											");
        query.append("						AND a.equip_id = b.equip_id										");
        query.append("						AND a.pm_id = y.pm_id											");
        query.append("						AND a.comp_no = y.comp_no										");
        query.append("					GROUP By a.comp_no, a.pm_id	) AS main_mng_id,						");
    	query.append("		x.sched_date,																	");
    	query.append("		x.sched_date																	");
    	query.append("FROM TAPMSCHED x, TAPMLST y															");
    	query.append("WHERE x.comp_no = y.comp_no															");
    	query.append("  AND x.pm_id = y.pm_id																");
        query.getStringEqualQuery("x.comp_no", compNo);
        query.getAndNumKeyQuery("x.pmsched_id", id);
    	query.append("  AND   (SELECT MIN(b.main_mng_id)                                                    ");
    	query.append("                    FROM TAPMEQUIP a, TAEQUIPMENT b                                   ");
    	query.append("                    WHERE a.comp_no = b.comp_no                                       ");
    	query.append("                        AND a.equip_id = b.equip_id                                   ");
    	query.append("                        AND a.pm_id = y.pm_id                                         ");
    	query.append("                        AND a.comp_no = y.comp_no                                     ");
    	query.append("                    GROUP BY a.comp_no, a.pm_id    ) IS NOT NULL      				");

    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    /**
     * 작업자재 생성
     * @author kim21017
     * @version $Id: MaWoMonthSchedListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoMonthSchedCommonDTO
     * @param type
     * @param id
     * @return
     */
    public int createWoPart(MaWoMonthSchedCommonDTO maWoMonthSchedCommonDTO, String id)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        String compNo      = maWoMonthSchedCommonDTO.getCompNo();

    	query.append("INSERT INTO TAWOPARTS(																");
    	query.append("		comp_no,		wopart_id,		wkor_id,										");
    	query.append("		wcode_id,		part_id,		use_qty											");
    	query.append("		)																				");
    	query.append("SELECT x.comp_no,		NEXT VALUE FOR SQAWOPART_ID, x.wkor_id,							");
    	query.append("		(SELECT wcode_id FROM TADEPT WHERE dept_id = 									");
    	query.append("			(SELECT dept_id FROM TAPMLST WHERE comp_no = x.comp_no AND pm_id=x.pm_id)	");
    	query.append("			AND comp_no = x.comp_no),													");
    	query.append("		y.part_id,																		");
    	query.append("		y.use_qty																		");
    	query.append("FROM TAPMSCHED x, TAPMPART y															");
    	query.append("WHERE x.comp_no = y.comp_no															");
    	query.append("  AND x.pm_id = y.pm_id																");
        query.getStringEqualQuery("x.comp_no", compNo);
        query.getAndNumKeyQuery("x.pmsched_id", id);
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    /**
     * 점검내용 생성
     * @author kim21017
     * @version $Id: MaWoMonthSchedListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoMonthSchedCommonDTO
     * @param type
     * @param id
     * @return
     */
    public int createWoPoint(MaWoMonthSchedCommonDTO maWoMonthSchedCommonDTO, String id)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        String compNo      = maWoMonthSchedCommonDTO.getCompNo();

        query.append("INSERT INTO TAWOPOINT(                                                                ");
        query.append("      comp_no,        wopoint_id,             wkor_id,                                ");
        query.append("      pm_point_id,    pm_point_rslt_status,   pm_point_rep_status                     ");
        query.append("      )                                                                               ");
        query.append("SELECT x.comp_no,     NEXT VALUE FOR SQAWOPOINT_ID,  x.wkor_id,                       ");
        query.append("       y.pm_point_id, (SELECT TOP 1 cdsysd_no FROM 									");
        query.append("                (SELECT * FROM tacdsysd												");
        query.append("                WHERE list_type='PM_POINT_RSLT_STATUS'								");
        query.append("                ORDER BY ISNULL(ord_no, '99999999'))									");
        query.append("                WHERE 1=1),              'GD'                                   		");
        query.append("FROM TAPMSCHED x, TAPMPOINT y                                                         ");
        query.append("WHERE x.comp_no = y.comp_no                                                           ");
        query.append("  AND x.pm_id = y.pm_id                                                               ");
        query.getStringEqualQuery("x.comp_no", compNo);
        query.getAndNumKeyQuery("x.pmsched_id", id);
        
        return this.getJdbcTemplate().update(query.toString());
    }
    
    /**
     * 작업 설비 생성
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param maWoMonthSchedCommonDTO
     * @param id
     * @return
     */
    public int createWoEquip(MaWoMonthSchedCommonDTO maWoMonthSchedCommonDTO, String id)
    {   
        QuerySqlBuffer query = new QuerySqlBuffer();
        String compNo      = maWoMonthSchedCommonDTO.getCompNo();

        query.append("INSERT INTO TAWOEQUIP(                                                                ");
        query.append("      comp_no,        woequip_id,             wkor_id,                                ");
        query.append("      equip_id,       description                         							");
        query.append("      )                                                                               ");
        query.append("SELECT x.comp_no,     NEXT VALUE FOR SQAWOEQUIP_ID,  x.wkor_id,                       ");
        query.append("       y.equip_id,    y.description                       							");
        query.append("FROM TAPMSCHED x, TAPMEQUIP y                                                         ");
        query.append("WHERE x.comp_no = y.comp_no                                                           ");
        query.append("  AND x.pm_id = y.pm_id                                                               ");
        query.getStringEqualQuery("x.comp_no", compNo);
        query.getAndNumKeyQuery("x.pmsched_id", id);
        
        return this.getJdbcTemplate().update(query.toString());
    }
    
    /**
     * 작업일정계획상태 변경
     * @author kim21017
     * @version $Id: MaWoMonthSchedListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param pmschedList
     * @param maWoMonthSchedCommonDTO
     * @param type
     * @param id
     * @return
     */
    public int updatePmSchedStatus(MaWoMonthSchedCommonDTO maWoMonthSchedCommonDTO, String id)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE x from TAPMSCHED x SET x.pmsched_status = 'S'						");
    	query.append("						,x.wkor_id = (SELECT wkor_id						");
    	query.append("										FROM TAWORKORDER					");
    	query.append("									   WHERE comp_no = x.comp_no			");
    	query.append("									     AND pmsched_id = x.pmsched_id)		");
    	query.append("WHERE 1=1 																");
        query.getAndNumKeyQuery("x.pmsched_id", id);
        query.getStringEqualQuery("x.comp_no", maWoMonthSchedCommonDTO.getCompNo());
    	query.append("  AND (SELECT is_active 													");
    	query.append("		   FROM TAPMLST														");
    	query.append("		   WHERE comp_no = x.comp_no										");
    	query.append("			 AND pm_id = x.pm_id) = 'Y'										");
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    /**
     * 주기에 의한 다음 계획 생성
     * @author kim21017
     * @version $Id: MaWoMonthSchedListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param pmschedList
     * @param maWoMonthSchedCommonDTO
     * @param type
     * @param id
     * @return
     */
    public int updateLastSchDate(MaWoMonthSchedCommonDTO maWoMonthSchedCommonDTO, String id)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        String compNo     = maWoMonthSchedCommonDTO.getCompNo();
        
    	query.append("UPDATE a FROM TAPMLST a SET a.last_sch_date = 													");
    	query.append("		CASE a.period_type 																			");
    	query.append("			WHEN 'Y' THEN CONVERT(VARCHAR, DATEADD(MONTH, 1*a.cycle*12, a.last_sch_date), 112)		");
    	query.append("			WHEN 'M' THEN CONVERT(VARCHAR, DATEADD(MONTH, a.cycle*1, a.last_sch_date), 112) 		");
    	query.append("			WHEN 'W' THEN CONVERT(VARCHAR, DATEADD(MONTH, 1*a.cycle*7, a.last_sch_date), 112)		");
    	query.append("			WHEN 'D' THEN CONVERT(VARCHAR, DATEADD(MONTH, a.cycle*1, a.last_sch_date), 112) ) END	");
    	query.append("WHERE 1=1																							");
        query.getStringEqualQuery("a.comp_no", compNo);
        query.getStringEqualQuery("a.is_active", "Y");
    	query.append("  AND a.pm_id IN (SELECT pm_id																	");
    	query.append("					FROM TAPMSCHED																	");
    	query.append("					WHERE comp_no = a.comp_no														");
        query.getAndNumKeyQuery("pmsched_id", id);
    	query.append("		)																							");
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    public void SP_PM_MAKE_WO_BYONE(String compNo, String userNo, String pmschedId) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("EXEC  dbo.SP_PM_MAKE_TO_WO '"+compNo+"',"+pmschedId+"; ");
        //query.append("EXEC dbo.SP_PM_MAKE_WO_BYONE '"+compNo+"',"+pmschedId+"; ");
        
        this.getJdbcTemplate().execute(query.toString());
    }

	@Override
	public String findTotalCount(MaWoMonthSchedCommonDTO maWoMonthSchedCommonDTO, User user)
	{
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                  					");
        query.append("SELECT                                                                            	");
        query.append("       COUNT(1)                                                                   	");
        query.append("FROM TAPMSCHED x INNER JOIN TAPMLST y                                                 ");
        query.append("ON x.comp_no = y.comp_no AND x.pm_id = y.pm_id                                        ");
        query.append("LEFT OUTER JOIN TAEQUIPMENT zz                                                        ");
        query.append("ON x.comp_no = zz.comp_no AND x.equip_id = zz.equip_id AND zz.is_deleted='N'          ");
        query.append("LEFT OUTER JOIN taworkorder z                                                         ");
        query.append("ON x.wkor_id = z.wkor_id AND x.comp_no = z.comp_no                                    ");
        query.append("WHERE 1=1                                                                             ");
        query.append(this.getWhere(maWoMonthSchedCommonDTO, user));

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QuerySqlBuffer.listToString(resultList);
	}
}