package dream.work.cal.pmrinsyear.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.DateUtil;
import common.util.QueryBuffer;
import dream.work.cal.pmrinsyear.dao.WorkCalPmRInsYearListDAO;
import dream.work.cal.pmrinsyear.dto.WorkCalPmRInsYearCommonDTO;

/**
 * 연간작업일정 - 목록 dao
 * @author  kim21017
 * @version $Id: WorkCalPmRInsYearListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="workCalPmRInsYearListDAOTarget"
 * @spring.txbn id="workCalPmRInsYearListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkCalPmRInsYearListDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkCalPmRInsYearListDAO
{
	/**
     * grid find
     * @author  kim21017
     * @version $Id: WorkCalPmRInsYearListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     *
     * @param workCalPmRInsYearCommonDTO
     * @return List
     */
    public List findSchedMonthlyList(WorkCalPmRInsYearCommonDTO workCalPmRInsYearCommonDTO,User user)
    {
    	
        QueryBuffer query = new QueryBuffer();
        query.append("SELECT                                                                                   ");
        query.append("        ''                                                               as seqNo        ");
        query.append("       ,''                                                               as isDelCheck   ");
        query.append("       ,x.pminssched_id                                                  as pmInsSchedId, ");
        query.getDate("x.plan_date", "planDate");
        query.append("       ,x.sched_date                                                     as schedDate,    ");
        query.getDate("x.actual_date", "completeDate");
        query.append("       ,y.pm_no                                                          as pmNo         ");
        query.append("       ,y.description                                                    as pmDesc       ");
        query.append("       ,x.pmsched_status                                                 as pmStatusCode ");
        query.append("       ,y.emp_id                                                         as empId        ");
        query.append("       ,(SELECT emp_name FROM TAEMP WHERE comp_no = y.comp_no AND emp_id = y.emp_id)             as planEmpDesc      ");
        query.append("       ,(SELECT emp_name FROM TAEMP WHERE comp_no = d.comp_no AND emp_id = d.emp_id)             as exeEmpDesc      ");
        query.append("       ,SFACODE_TO_DESC(x.pmsched_status,'PMSCHED_STATUS','SYS','','"+user.getLangId()+"') as pmSchedStatus ");
        query.append("       , x.measure_time                                                  AS measureTime     ");
        query.append("       ,(SELECT full_desc                                                                   ");
        query.append("          FROM TAEQLOC                                                                      ");
        query.append("         WHERE comp_no = c.comp_no                                                          ");
        query.append("           AND eqloc_id = c.eqloc_id)                                    AS eqLocDesc       ");
        query.append("       ,c.item_no                                                        as equipNo         ");
        query.append("       ,c.description                                                    as equipDesc       ");
        query.append("       ,(SELECT aa.description FROM TADEPT aa                                               ");
        query.append("         WHERE y.comp_no = aa.comp_no                                                       ");
        query.append("           AND y.dept_id = aa.dept_id)                                   as planDeptDesc    ");
        query.append("       ,(SELECT aa.description FROM TADEPT aa                                               ");
        query.append("         WHERE d.comp_no = aa.comp_no                                                       ");
        query.append("           AND d.dept_id = aa.dept_id)                                   as exeDeptDesc     ");
        query.append("       ,(SELECT aa.description FROM TAWKCTR aa                                              ");
        query.append("         WHERE y.comp_no = aa.comp_no                                                       ");
        query.append("              AND y.wkctr_id = aa.wkctr_id)                              as wkCtrDesc       ");
        query.append("      ,SFACODE_TO_DESC(y.schedule_type,'SCHEDULE_TYPE','SYS','','"+user.getLangId()+"')    as scheduleTypeDesc");
        query.append("      ,y.cycle||y.period_type                                            as cycle           ");
        query.append("      ,y.USAGE                                                           as USAGE           ");
        query.append("      ,y.pm_id                                                           as pmId            ");
        query.append("      ,x.PMINSLIST_ID                                                    as pmInsListId     ");
        query.append("		,(SELECT param2 FROM TACDSYSD WHERE cdsysd_no=y.pm_type AND list_type= y.wo_type||'_TYPE') param  ");
        query.append("		,x.remark                                                          as remark          ");
        query.append("FROM TAPMINSSCHED x inner join TAPMLST y on x.comp_no = y.comp_no and x.pm_id = y.pm_id     ");
        query.append("        LEFT OUTER JOIN TAEQUIPMENT c                                                       ");
        query.append("           ON x.equip_id = c.equip_id AND x.comp_no = c.comp_no AND c.is_deleted='N'        ");
        query.append("        LEFT OUTER JOIN TAPMINSLIST d                                                       ");
        query.append("           ON x.pminslist_id = d.pminslist_id AND x.comp_no = d.comp_no                     ");
        query.append("WHERE 1=1                                                                                   ");
        query.append(this.getWhere(workCalPmRInsYearCommonDTO,user));
        query.getOrderByQuery("x.sched_date, y.description", workCalPmRInsYearCommonDTO.getOrderBy(), workCalPmRInsYearCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(workCalPmRInsYearCommonDTO.getIsLoadMaxCount(), workCalPmRInsYearCommonDTO.getFirstRow()));

    }
    public List findYearReport(WorkCalPmRInsYearCommonDTO workCalPmRInsYearCommonDTO,User user)
    {
    	
        QueryBuffer query = new QueryBuffer();
        query.append("SELECT '"+workCalPmRInsYearCommonDTO.getFilterYear()+"' YEAR,									");
        query.append("		CASE WHEN '"+workCalPmRInsYearCommonDTO.getFilterDeptDesc()+"' is null then ' (전체) '	");
        query.append("			ELSE ' ('||'"+workCalPmRInsYearCommonDTO.getFilterDeptDesc()+"'||')' END DEPT FROM DUAL	");

        return getJdbcTemplate().queryForList(query.toString());
    }
    public List findYearInsReport(WorkCalPmRInsYearCommonDTO workCalPmRInsYearCommonDTO,User user)
    {
    	String yyyy        = workCalPmRInsYearCommonDTO.getFilterYear();
    	
        QueryBuffer query = new QueryBuffer();
        query.append("SELECT ROWNUM seqNo, equipDesc, period,m1,m2,m3,m4,m5,m6,m7,m8,m9,m10,m11,m12				");
        query.append("FROM (																					");
        query.append("	SELECT 																					");
        query.append("			(SELECT a.description FROM TAEQUIPMENT a WHERE a.comp_no = x.comp_no AND a.equip_id = x.equip_id) equipDesc		");
        query.append("			,y.cycle||y.period_type period													");
        query.append("			,sum(CASE WHEN x.sched_date LIKE '"+yyyy+"' || '01%' THEN 1 ELSE 0 END) AS M1 	");
        query.append("			,sum(CASE WHEN x.sched_date LIKE '"+yyyy+"' || '02%' THEN 1 ELSE 0 END) AS M2 	");
        query.append("			,sum(CASE WHEN x.sched_date LIKE '"+yyyy+"' || '03%' THEN 1 ELSE 0 END) AS M3 	");
        query.append("			,sum(CASE WHEN x.sched_date LIKE '"+yyyy+"' || '04%' THEN 1 ELSE 0 END) AS M4 	");
        query.append("			,sum(CASE WHEN x.sched_date LIKE '"+yyyy+"' || '05%' THEN 1 ELSE 0 END) AS M5 	");
        query.append("			,sum(CASE WHEN x.sched_date LIKE '"+yyyy+"' || '06%' THEN 1 ELSE 0 END) AS M6 	");
        query.append("			,sum(CASE WHEN x.sched_date LIKE '"+yyyy+"' || '07%' THEN 1 ELSE 0 END) AS M7 	");
        query.append("			,sum(CASE WHEN x.sched_date LIKE '"+yyyy+"' || '08%' THEN 1 ELSE 0 END) AS M8 	");
        query.append("			,sum(CASE WHEN x.sched_date LIKE '"+yyyy+"' || '09%' THEN 1 ELSE 0 END) AS M9 	");
        query.append("			,sum(CASE WHEN x.sched_date LIKE '"+yyyy+"' || '10%' THEN 1 ELSE 0 END) AS M10 	");
        query.append("			,sum(CASE WHEN x.sched_date LIKE '"+yyyy+"' || '11%' THEN 1 ELSE 0 END) AS M11 	");
        query.append("			,sum(CASE WHEN x.sched_date LIKE '"+yyyy+"' || '12%' THEN 1 ELSE 0 END) AS M12 	");
        query.append("  FROM TAPMINSSCHED x INNER JOIN TAPMLST y                                                ");
        query.append("  ON x.comp_no = y.comp_no AND x.pm_id = y.pm_id                                          ");
        query.append("        LEFT OUTER JOIN TAEQUIPMENT c                                                     ");
        query.append("        ON x.equip_id = c.equip_id AND x.comp_no = c.comp_no AND c.is_deleted='N'         ");
        query.append("	WHERE 1 = 1															                    ");
        query.append(this.getWhereByTotalCount(workCalPmRInsYearCommonDTO,user));
        query.append("	GROUP BY x.comp_no, x.equip_id,y.cycle,y.period_type									");
        query.append("	ORDER BY (SELECT a.description FROM TAEQUIPMENT a WHERE a.equip_id = x.equip_id)		");
        query.append(")																							");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    private String getWhereByTotalCount(WorkCalPmRInsYearCommonDTO workCalPmRInsYearCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        String yyyy        = workCalPmRInsYearCommonDTO.getFilterYear();
        String deptId      = workCalPmRInsYearCommonDTO.getFilterDeptId();
        String deptDesc    = workCalPmRInsYearCommonDTO.getFilterDeptDesc();
        String eqLocId     = workCalPmRInsYearCommonDTO.getFilterEqLocId();
        String eqLocDesc   = workCalPmRInsYearCommonDTO.getFilterEqLocDesc();
        String eqCtgId     = workCalPmRInsYearCommonDTO.getFilterEqCtgId();
        String eqCtgDesc   = workCalPmRInsYearCommonDTO.getFilterEqCtgDesc();
        String eqCtgTypeId     = workCalPmRInsYearCommonDTO.getFilterEqCtgTypeId();
        String eqCtgTypeDesc   = workCalPmRInsYearCommonDTO.getFilterEqCtgTypeDesc();
        String mainMngId   = workCalPmRInsYearCommonDTO.getFilterMainMngId();
        String mainMngName = workCalPmRInsYearCommonDTO.getFilterMainMngName();
        String subMngId    = workCalPmRInsYearCommonDTO.getFilterSubMngId();
        String subMngName  = workCalPmRInsYearCommonDTO.getFilterSubMngName();
        String isLawEq     = workCalPmRInsYearCommonDTO.getFilterIsLawEq();
        String plfTypeId   = workCalPmRInsYearCommonDTO.getFilterPlfTypeId();
        String plfTypeDesc = workCalPmRInsYearCommonDTO.getFilterPlfTypeDesc();
        String wkCtrId     = workCalPmRInsYearCommonDTO.getFilterWkCtrId();
        String wkCtrDesc   = workCalPmRInsYearCommonDTO.getFilterWkCtrDesc();
        String equipId     = workCalPmRInsYearCommonDTO.getFilterEquipId();
        String equipDesc   = workCalPmRInsYearCommonDTO.getFilterEquipDesc();
        String pmNo       = workCalPmRInsYearCommonDTO.getFilterPmNo();
        
        String empId     = workCalPmRInsYearCommonDTO.getFilterEmpId();
        String empDesc   = workCalPmRInsYearCommonDTO.getFilterEmpDesc();
        
        String plantId     = workCalPmRInsYearCommonDTO.getFilterPlantId();
        String plantDesc   = workCalPmRInsYearCommonDTO.getFilterPlantDesc();
        
        String usageDept   = workCalPmRInsYearCommonDTO.getFilterUsageDeptId();
        String usageDeptDesc   = workCalPmRInsYearCommonDTO.getFilterUsageDeptDesc();
        
        //사용부서
        query.getDeptLevelQuery("c.usage_dept", usageDept, usageDeptDesc, user.getCompNo());
        
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        query.getStringEqualQuery("x.IS_DELETED", "N");

        query.append("  AND x.sched_date like '"+yyyy+"%'                           ");
        //법정설비여부
        query.getLikeQuery("c.is_law_eq", isLawEq);
        
        //pmNo
        query.getAndQuery("y.pm_no", pmNo);
        //부서
        query.getDeptLevelQuery("y.dept_id", deptId, deptDesc, user.getCompNo());
        //위치
        query.getEqLocLevelQuery("c.eqloc_id", eqLocId, eqLocDesc, user.getCompNo());
        
        //종류
        query.getEqCtgLevelQuery("c.eqctg_id", eqCtgId, eqCtgDesc, user.getCompNo());
        
        //담당자(정)
        query.getCodeLikeQuery("c.main_mng_id", "(SELECT emp_name FROM TAEMP WHERE emp_id = c.main_mng_id AND comp_no=c.comp_no)", mainMngId, mainMngName);
        
        //담당자(부)
        query.getCodeLikeQuery("c.sub_mng_id", "(SELECT emp_name FROM TAEMP WHERE emp_id = c.sub_mng_id AND comp_no=c.comp_no)", subMngId, subMngName);
        
        //설비
        query.getCodeLikeQuery("c.equip_id", "c.description||c.item_no",equipId, equipDesc);
        
        //내/외자 구분
        query.getSysCdQuery("c.plf_type", plfTypeId, plfTypeDesc, "PLF_TYPE", user.getCompNo()  ,user.getLangId());
        
        //설비유형
        query.getSysCdQuery("c.eqctg_type", eqCtgTypeId, eqCtgTypeDesc, "EQCTG_TYPE", user.getCompNo(),user.getLangId());
        
        //작업그룹
        query.getWkCtrLevelQuery("y.wkctr_id", wkCtrId, wkCtrDesc, user.getCompNo());
        
        //담당자
        query.getCodeLikeQuery("y.emp_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = y.emp_id AND a.comp_no='"+user.getCompNo()+"')", 
               empId, empDesc);

        //공장코드
        query.getCodeLikeQuery("y.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = y.comp_no AND a.plant = y.plant )", 
                plantId, plantDesc);
        
        //최신버전의 설비의 작업만 보여줌.
        query.getAndQuery("c.is_last_version", "Y");
        
        return query.toString();
    }
    
    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id: WorkCalPmRInsYearListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     *
     * @param yyyymm
     * @param status
     * @param compNo
     * @param deptId
     * @param deptDesc
     * @return
     * @throws Exception
     */
    private String getWhere(WorkCalPmRInsYearCommonDTO workCalPmRInsYearCommonDTO,User user)
    {
        
    	QueryBuffer query = new QueryBuffer();
    	
    	String yyyymm      = workCalPmRInsYearCommonDTO.getYyyymm();
        String compNo      = user.getCompNo();
        String deptId      = workCalPmRInsYearCommonDTO.getDeptId();
        String deptDesc    = workCalPmRInsYearCommonDTO.getDeptDesc();
        String eqLocId     = workCalPmRInsYearCommonDTO.getEqLocId();
        String eqLocDesc   = workCalPmRInsYearCommonDTO.getEqLocDesc();
        String eqCtgId     = workCalPmRInsYearCommonDTO.getEqCtgId();
        String eqCtgDesc   = workCalPmRInsYearCommonDTO.getEqCtgDesc();
        String eqCtgTypeId     = workCalPmRInsYearCommonDTO.getEqCtgTypeId();
        String eqCtgTypeDesc   = workCalPmRInsYearCommonDTO.getEqCtgTypeDesc();
        String mainMngId   = workCalPmRInsYearCommonDTO.getMainMngId();
        String mainMngName = workCalPmRInsYearCommonDTO.getMainMngName();
        String subMngId    = workCalPmRInsYearCommonDTO.getSubMngId();
        String subMngName  = workCalPmRInsYearCommonDTO.getSubMngName();
        String isLawEq     = workCalPmRInsYearCommonDTO.getIsLawEq();
        String plfTypeId   = workCalPmRInsYearCommonDTO.getPlfTypeId();
        String plfTypeDesc = workCalPmRInsYearCommonDTO.getPlfTypeDesc();
        String wkCtrId     = workCalPmRInsYearCommonDTO.getWkCtrId();
        String wkCtrDesc   = workCalPmRInsYearCommonDTO.getWkCtrDesc();
        String equipId     = workCalPmRInsYearCommonDTO.getEquipId();
        String equipDesc   = workCalPmRInsYearCommonDTO.getEquipDesc();
        String pmNo        = workCalPmRInsYearCommonDTO.getPmNo();
        
        String empId     = workCalPmRInsYearCommonDTO.getEmpId();
        String empDesc   = workCalPmRInsYearCommonDTO.getEmpDesc();
        
        String plantId     = workCalPmRInsYearCommonDTO.getPlantId();
        String plantDesc   = workCalPmRInsYearCommonDTO.getPlantDesc();
        
        String usageDept   = workCalPmRInsYearCommonDTO.getFilterUsageDeptId();
        String usageDeptDesc   = workCalPmRInsYearCommonDTO.getFilterUsageDeptDesc();
        
        if(!"".equals(workCalPmRInsYearCommonDTO.getPmInsListId())){
            query.getAndQuery("x.pmInsList_id", workCalPmRInsYearCommonDTO.getPmInsListId());
            return query.toString();
        }
        
        query.getAndNumKeyQuery("x.pminssched_id", workCalPmRInsYearCommonDTO.getPmInsSchedId());
        
        //사용부서
        query.getDeptLevelQuery("c.usage_dept", usageDept, usageDeptDesc, user.getCompNo());
        
    	query.getStringEqualQuery("x.comp_no", compNo);
    	query.getStringEqualQuery("x.IS_DELETED", "N");
    	
    	query.append("  AND x.sched_date like        '"+yyyymm+"%'				");
    	
        query.getSysCdQuery("x.pmsched_status", workCalPmRInsYearCommonDTO.getSchedType(), "", "PMSCHED_STATUS", compNo, user.getLangId());
        
        //법정설비여부
        query.getLikeQuery("c.is_law_eq", isLawEq);
        
        //pmNo
        query.getAndQuery("y.pm_no", pmNo);
        //계획부서
        query.getDeptLevelQuery("y.dept_id", deptId, deptDesc, compNo);
        //실행부서
        query.getDeptLevelQuery("d.dept_id", workCalPmRInsYearCommonDTO.getExeDeptId(), workCalPmRInsYearCommonDTO.getExeDeptDesc(), compNo);
        
        //위치
        query.getEqLocLevelQuery("c.eqloc_id", eqLocId, eqLocDesc, compNo);
        
        //종류
        query.getEqCtgLevelQuery("c.eqctg_id", eqCtgId, eqCtgDesc, compNo);
        
        //담당자(정)
        query.getCodeLikeQuery("c.main_mng_id", "(SELECT emp_name FROM TAEMP WHERE emp_id = c.main_mng_id AND comp_no=c.comp_no)", mainMngId, mainMngName);
        
        //담당자(부)
        query.getCodeLikeQuery("c.sub_mng_id", "(SELECT emp_name FROM TAEMP WHERE emp_id = c.sub_mng_id AND comp_no=c.comp_no)", subMngId, subMngName);
        
        //설비
        query.getCodeLikeQuery("c.equip_id", "c.description||c.item_no",equipId, equipDesc);
        
        //내/외자 구분
        query.getSysCdQuery("c.plf_type", plfTypeId, plfTypeDesc, "PLF_TYPE", compNo  ,user.getLangId());
        
        //설비유형
        query.getSysCdQuery("c.eqctg_type", eqCtgTypeId, eqCtgTypeDesc, "EQCTG_TYPE", compNo,user.getLangId());
        
    	//작업그룹
    	query.getWkCtrLevelQuery("y.wkctr_id", wkCtrId, wkCtrDesc, compNo);

    	//계획담당자
    	query.getCodeLikeQuery("y.emp_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = y.emp_id AND a.comp_no=y.comp_no)", empId, empDesc);
    	
    	//실행담당자
        query.getCodeLikeQuery("d.emp_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = d.emp_id AND a.comp_no=d.comp_no)", workCalPmRInsYearCommonDTO.getExeEmpId(), workCalPmRInsYearCommonDTO.getExeEmpDesc());

        //공장코드
        query.getCodeLikeQuery("y.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = y.comp_no AND a.plant = y.plant )", plantId, plantDesc);
        
        //최신버전의 설비의 작업만 보여줌.
        query.getAndQuery("c.is_last_version", "Y");
        
        return query.toString();
    }

    public String findTotalCount(WorkCalPmRInsYearCommonDTO workCalPmRInsYearCommonDTO,User user)
    {

        QueryBuffer query = new QueryBuffer();
        query.append("SELECT                                                                                       ");
        query.append("       COUNT(1)                                                                              ");
        query.append("FROM TAPMINSSCHED x inner join TAPMLST y on x.comp_no = y.comp_no and x.pm_id = y.pm_id     ");
        query.append("        LEFT OUTER JOIN TAEQUIPMENT c                                                       ");
        query.append("           ON x.equip_id = c.equip_id AND x.comp_no = c.comp_no AND c.is_deleted='N'        ");
        query.append("        LEFT OUTER JOIN TAPMINSLIST d                                                       ");
        query.append("           ON x.pminslist_id = d.pminslist_id AND x.comp_no = d.comp_no                     ");
        query.append("  WHERE 1=1                                                                                  ");
        query.append(this.getWhere(workCalPmRInsYearCommonDTO,user));
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
    
    
    
    /**
     * delete
     * @author kim21017
     * @version $Id: WorkCalPmRInsYearListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param id
     * @param compNo
     * @return
     */
    public int deleteSched(String id,User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	String pmInsSchedId = id;
    	
        query.setClear();
        query.append("DELETE FROM TAPMINSSCHED      ");
        query.append("WHERE comp_no = ?             ");
        query.append("  AND pminssched_id =  ?      ");
        Object[] objects = new Object[]{
        		user.getCompNo()
        		,pmInsSchedId
        };
        
        return  this.getJdbcTemplate().update(query.toString(),objects);
        
    	
    }
    
    
    /**
     * delete
     * @author kim21017
     * @version $Id: WorkCalPmRInsYearListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param id
     * @param compNo
     * @return
     */
    public int updateScheduleDate(String id, String scheDate, String remark, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
        String pmInsSchedId = id;
    	
        query.append("UPDATE TAPMINSSCHED  SET      ");
        query.append("     SCHED_DATE = ?           ");
        query.append("     ,REMARK    = ?           ");
        query.append("WHERE comp_no = ?             ");
        query.append("  AND pminssched_id =  ?      ");
        
        Object[] objects = new Object[]{
        		scheDate.replaceAll("-", "")
        		,remark
        		,user.getCompNo()
        		,pmInsSchedId
        };
        
        return this.getJdbcTemplate().update(query.toString(),objects);
        
    }
    
    public int updateResultSchedDetail(String id, String scheDate, User user)
    {
 	   QueryBuffer query = new QueryBuffer();
 	   
 	   String pmInsSchedId = id;
 	   
 	   query.append("UPDATE  TAPMINSLIST SET        ");
 	   query.append("           WKOR_DATE     = ?    ");
 	   query.append("          ,START_DATE    = ?    ");
 	   query.append("          ,END_DATE      = ?    ");
 	   query.append("WHERE COMP_NO = ?               ");
 	   query.append("    AND PMINSLIST_ID = (        ");
 	   query.append("                         SELECT PMINSLIST_ID        ");
 	   query.append("                         FROM TAPMINSSCHED          ");
 	   query.append("                         WHERE COMP_NO = ?           ");
 	   query.append("                              AND PMINSSCHED_ID = ? ");
 	   query.append("                        )                            ");
 	   Object[] objects = new Object[]{
 			    scheDate
 			   ,scheDate
 			   ,scheDate
 			   ,user.getCompNo()
 			   ,user.getCompNo()
 			   ,pmInsSchedId
 	   };
 		
 	   return this.getJdbcTemplate().update(query.toString(),objects);
 	   
    }
    
    public String checkSched(String pmInsSchedId, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("SELECT  PMSCHED_STATUS         ");
    	query.append("FROM TAPMINSSCHED             ");
        query.append("WHERE comp_no = ?              ");
        query.append("  AND PMINSSCHED_ID =  ?      ");
        Object[] objects = new Object[]{
        		user.getCompNo()
        		,pmInsSchedId
        };
        
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString(),objects));
    }
    
    public int updateDeleteTagSched(String pmInsSchedId, User user)
    {
    	
    	QueryBuffer query = new QueryBuffer();
    	
    	int result = 0;
    	
        query.append("UPDATE TAPMINSLIST  SET      ");
        query.append("         IS_DELETED = 'Y'     ");
        query.append("        ,DELETE_BY  = ?       ");
        query.append("        ,DELETE_TIME = ?      ");
        query.append("WHERE COMP_NO = ?             ");
    	query.append("  AND PMINSLIST_ID = (        ");
    	query.append("                         SELECT PMINSLIST_ID              ");
    	query.append("                         FROM TAPMINSSCHED                ");
    	query.append("                         WHERE COMP_NO = ?                 ");
    	query.append("                              AND PMINSSCHED_ID = ?       ");
    	query.append("                        )                                  ");
    	Object[] objects = new Object[]{
    			user.getEmpId()
    			,DateUtil.getDateTime()
        		,user.getCompNo()
        		,user.getCompNo()
        		,pmInsSchedId
        };
        result = this.getJdbcTemplate().update(query.toString(),objects);
        
        
        query.setClear();
        query.append("UPDATE TAPMINSSCHED  SET     ");
        query.append("         IS_DELETED = 'Y'     ");
        query.append("        ,DELETE_BY  = ?       ");
        query.append("        ,DELETE_TIME = ?     ");
        query.append("WHERE COMP_NO = ?              ");
        query.append("  AND PMINSSCHED_ID =  ?      ");
        objects = new Object[]{
        		user.getEmpId()
        		,DateUtil.getDateTime()
        		,user.getCompNo()
        		,pmInsSchedId
        };
        
        return  this.getJdbcTemplate().update(query.toString(),objects);
    }


	@Override
	public void SP_PM_MAKE_TO_PMI(String pminsschedId, User user) throws Exception
	{
		QueryBuffer query = new QueryBuffer();

        query.append("begin                                                       ");
        query.append("SP_PM_MAKE_TO_PMI('"+user.getCompNo()+"',"+pminsschedId+"); ");
        query.append("end;                                                        ");

        this.getJdbcTemplate().execute(query.toString());
	}
    
    
}