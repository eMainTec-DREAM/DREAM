package dream.work.cal.pmrinsmonth.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.CommonUtil;
import common.util.DateUtil;
import common.util.QuerySqlBuffer;
import dream.work.cal.pmrinsmonth.dao.WorkCalPmRInsMonthListDAO;
import dream.work.cal.pmrinsmonth.dto.WorkCalPmRInsMonthCommonDTO;

/**
 * 월간예방일정 - 목록 dao
 * @author  kim21017
 * @version $Id: WorkCalPmRInsMonthListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="workCalPmRInsMonthListDAOTarget"
 * @spring.txbn id="workCalPmRInsMonthListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkCalPmRInsMonthListDAOSqlImpl extends BaseJdbcDaoSupportSql implements WorkCalPmRInsMonthListDAO
{
	/**
     * grid find
     * @author  kim21017
     * @version $Id: WorkCalPmRInsMonthListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     *
     * @param workCalPmRInsMonthCommonDTO
     * @return List
     */
    public List findSchedList(WorkCalPmRInsMonthCommonDTO workCalPmRInsMonthCommonDTO, User user)
    {

        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                  ");
        query.append("SELECT                                                                                   ");
        query.append("        ''                                                               as seqNo        ");
        query.append("       ,''                                                               as isDelCheck   ");
        query.append("       ,x.pminssched_id                                                  as pmInsSchedId,");
        query.getDate("x.plan_date", "planDate");
        query.append("       ,x.sched_date                                                     as schedDate,   ");
        query.getDate("x.actual_date", "completeDate");
        query.append("       ,y.pm_no                                                          as pmNo         ");
        query.append("       ,y.description                                                    as pmDesc       ");
        query.append("       ,x.pmsched_status                                                 as pmStatusCode ");
        query.append("       ,dbo.SFACODE_TO_DESC(x.pmsched_status,'PMSCHED_STATUS','SYS','','"+user.getLangId()+"') as PMSCHEDSTATUS ");
        query.append("       , x.measure_time                                                  AS measureTime  ");
        query.append("       ,(SELECT full_desc                                                                 ");
        query.append("          FROM TAEQLOC                                                                    ");
        query.append("         WHERE comp_no = c.comp_no                                                        ");
        query.append("           AND eqloc_id = c.eqloc_id)                                    AS eqLocDesc     ");
        query.append("       ,c.item_no                                                        as EQUIPNO       ");
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
        query.append("      ,dbo.SFACODE_TO_DESC(y.schedule_type,'SCHEDULE_TYPE','SYS','','"+user.getLangId()+"')    as scheduleTypeDesc");
        query.append("      ,STR(y.cycle) + y.period_type                                            as cycle           ");
        query.append("      ,y.USAGE                                                           as USAGE           ");
        query.append("      ,y.pm_id                                                           as pmId            ");
        query.append("      ,x.PMINSLIST_ID                                                    as pmInsListId     ");
        query.append("		,(SELECT param2 FROM TACDSYSD WHERE cdsysd_no=y.pm_type AND list_type= y.wo_type+'_TYPE') param  ");
        query.append("      ,y.pm_type                                                         as pmType          ");
        query.append("      ,d.emp_id                                                          as empId           ");
        query.append("      ,(SELECT emp_name FROM TAEMP WHERE comp_no = y.comp_no AND emp_id = y.emp_id) planEmpDesc ");
        query.append("      ,(SELECT emp_name FROM TAEMP WHERE comp_no = d.comp_no AND emp_id = d.emp_id) exeEmpDesc ");
        query.append("      ,x.remark                                                          as remark          ");
        query.append("      ,x.sched_date                                                      as hiddensdate      ");
        query.append("FROM TAPMINSSCHED x inner join TAPMLST y on x.comp_no = y.comp_no and x.pm_id = y.pm_id       ");
        query.append("LEFT OUTER JOIN TAEQUIPMENT c                                                                 ");
        query.append("ON x.equip_id = c.equip_id AND x.comp_no = c.comp_no AND c.is_deleted='N'                     ");
        query.append("LEFT OUTER JOIN TAPMINSLIST d                                                                 ");
        query.append("ON x.pminslist_id = d.pminslist_id AND x.comp_no = d.comp_no                                  ");
        query.append("WHERE 1=1                                                                                   ");
        query.append(this.getWhere(workCalPmRInsMonthCommonDTO,user));
        query.getOrderByQuery("x.pminssched_id", "x.sched_date, y.description", workCalPmRInsMonthCommonDTO.getOrderBy(), workCalPmRInsMonthCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(workCalPmRInsMonthCommonDTO.getIsLoadMaxCount(), workCalPmRInsMonthCommonDTO.getFirstRow()));

    }

    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id: WorkCalPmRInsMonthListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
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
    private String getWhere(WorkCalPmRInsMonthCommonDTO workCalPmRInsMonthCommonDTO,User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        String yyyymmdd    = CommonUtil.getRowDateToNum(workCalPmRInsMonthCommonDTO.getYyyymmdd());
        String deptId      = workCalPmRInsMonthCommonDTO.getDeptId();
        String deptDesc    = workCalPmRInsMonthCommonDTO.getDeptDesc();
        String eqLocId     = workCalPmRInsMonthCommonDTO.getEqLocId();
        String eqLocDesc   = workCalPmRInsMonthCommonDTO.getEqLocDesc();
        String eqCtgId     = workCalPmRInsMonthCommonDTO.getEqCtgId();
        String eqCtgDesc   = workCalPmRInsMonthCommonDTO.getEqCtgDesc();
        String eqCtgTypeId     = workCalPmRInsMonthCommonDTO.getEqCtgTypeId();
        String eqCtgTypeDesc   = workCalPmRInsMonthCommonDTO.getEqCtgTypeDesc();
        String mainMngId   = workCalPmRInsMonthCommonDTO.getMainMngId();
        String mainMngName = workCalPmRInsMonthCommonDTO.getMainMngName();
        String subMngId    = workCalPmRInsMonthCommonDTO.getSubMngId();
        String subMngName  = workCalPmRInsMonthCommonDTO.getSubMngName();
        String isLawEq     = workCalPmRInsMonthCommonDTO.getIsLawEq();
        String plfTypeId   = workCalPmRInsMonthCommonDTO.getPlfTypeId();
        String plfTypeDesc = workCalPmRInsMonthCommonDTO.getPlfTypeDesc();
        String wkCtrId     = workCalPmRInsMonthCommonDTO.getWkCtrId();
        String wkCtrDesc   = workCalPmRInsMonthCommonDTO.getWkCtrDesc();
        String equipId     = workCalPmRInsMonthCommonDTO.getEquipId();
        String equipDesc   = workCalPmRInsMonthCommonDTO.getEquipDesc();
        String pmNo        = workCalPmRInsMonthCommonDTO.getPmNo();
        String plantId     = workCalPmRInsMonthCommonDTO.getPlantId();
        String plantDesc   = workCalPmRInsMonthCommonDTO.getPlantDesc();
        
        if(!"".equals(workCalPmRInsMonthCommonDTO.getPmInsListId())){
            query.getAndQuery("x.pmInsList_id", workCalPmRInsMonthCommonDTO.getPmInsListId());
            return query.toString();
        }
        
        query.getAndNumKeyQuery("x.pminssched_id", workCalPmRInsMonthCommonDTO.getPmInsSchedId());
        
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        query.getStringEqualQuery("x.IS_DELETED", "N");

        query.getAndQuery("y.cycle", workCalPmRInsMonthCommonDTO.getCycle());
        query.getAndQuery("y.period_type", workCalPmRInsMonthCommonDTO.getPeriodType());
    	query.append("  AND x.sched_date like        '"+yyyymmdd+"%'				");
    	
    	query.getSysCdQuery("x.pmsched_status", workCalPmRInsMonthCommonDTO.getSchedType(), "", "PMSCHED_STATUS", user.getCompNo(), user.getLangId());
        
    	//법정설비여부
    	query.getLikeQuery("c.is_law_eq", isLawEq);
        
    	//예방점검기준 번호
    	query.getAndQuery("y.pm_no", pmNo);
    	//계획부서
    	query.getDeptLevelQuery("y.dept_id", deptId, deptDesc, user.getCompNo());
    	//실행부서
    	query.getDeptLevelQuery("d.dept_id", workCalPmRInsMonthCommonDTO.getExeDeptId(), workCalPmRInsMonthCommonDTO.getExeDeptDesc(), user.getCompNo());
    	//위치
    	query.getEqLocLevelQuery("c.eqloc_id", eqLocId, eqLocDesc, user.getCompNo());
    	
    	//종류
    	query.getEqCtgLevelQuery("c.eqctg_id", eqCtgId, eqCtgDesc, user.getCompNo());
    	
    	//담당자(정)
    	query.getCodeLikeQuery("c.main_mng_id", "(SELECT emp_name FROM TAEMP WHERE emp_id = c.main_mng_id AND comp_no=c.comp_no)", mainMngId, mainMngName);
    	
    	//담당자(부)
    	query.getCodeLikeQuery("c.sub_mng_id", "(SELECT emp_name FROM TAEMP WHERE emp_id = c.sub_mng_id AND comp_no=c.comp_no)", subMngId, subMngName);
    	
    	//설비
    	query.getCodeLikeQuery("c.equip_id", "c.description+c.item_no",equipId, equipDesc);
    	
    	//내/외자 구분
    	query.getSysCdQuery("c.plf_type", plfTypeId, plfTypeDesc, "PLF_TYPE", user.getCompNo(),user.getLangId());
    	
    	//설비유형
    	query.getSysCdQuery("c.eqctg_type", eqCtgTypeId, eqCtgTypeDesc, "EQCTG_TYPE", user.getCompNo(),user.getLangId());
    	
        query.getAndQuery("y.cycle", workCalPmRInsMonthCommonDTO.getCycle());
        query.getAndQuery("y.period_type", workCalPmRInsMonthCommonDTO.getPeriodType());
        query.getWkCtrLevelQuery("y.wkctr_id", wkCtrId, wkCtrDesc, user.getCompNo());

        //계획담당자
        query.getCodeLikeQuery("y.emp_id", "(SELECT emp_name FROM TAEMP WHERE comp_no=y.comp_no AND emp_id=y.emp_id)", workCalPmRInsMonthCommonDTO.getPlanEmpId(), workCalPmRInsMonthCommonDTO.getPlanEmpDesc());
        //실행담당자
        query.getCodeLikeQuery("d.emp_id", "(SELECT emp_name FROM TAEMP WHERE comp_no=d.comp_no AND emp_id=d.emp_id)", workCalPmRInsMonthCommonDTO.getEmpId(), workCalPmRInsMonthCommonDTO.getEmpDesc());
        
        //공장코드
        query.getCodeLikeQuery("y.plant", "(SELECT description FROM TAPLANT WHERE comp_no = y.comp_no AND plant = y.plant)", plantId, plantDesc);
        
        //최신설비
        query.getAndQuery("c.is_last_version", "Y");
        
        return query.toString();
    }
    
    public String checkSched(String pmInsSchedId, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                  ");
    	query.append("SELECT  PMSCHED_STATUS         ");
    	query.append("FROM TAPMINSSCHED             ");
        query.append("WHERE comp_no = ?              ");
        query.append("  AND PMINSSCHED_ID =  ?      ");
        Object[] objects = new Object[]{
        		user.getCompNo()
        		,pmInsSchedId
        };
        
        return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString(),objects));
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: WorkCalPmRInsMonthListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param id
     * @param compNo
     * @return
     */
    public int deleteSched(String id, User user)
    {
    	
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	String pmInsSchedId = id;
    	
    	int result = 0;
        query.append("DELETE FROM TAPMINSPOINT      ");
        query.append("WHERE comp_no = ?             ");
        query.append("  AND pminssched_id =  ?      ");
        Object[] objects = new Object[]{
        		user.getCompNo()
        		,pmInsSchedId
        };
        result = this.getJdbcTemplate().update(query.toString(),getObject(objects));
        
        query.setClear();
        query.append("DELETE FROM TAPMINSLIST       ");
        query.append("WHERE comp_no = ?             ");
        query.append("  AND pminssched_id =  ?      ");
        objects = new Object[]{
        		user.getCompNo()
        		,pmInsSchedId
        };
        result = this.getJdbcTemplate().update(query.toString(),getObject(objects));
        
        
        
        query.setClear();
        query.append("DELETE FROM TAPMINSSCHED      ");
        query.append("WHERE comp_no = ?             ");
        query.append("  AND pminssched_id =  ?      ");
        objects = new Object[]{
        		user.getCompNo()
        		,pmInsSchedId
        };
        
        return  this.getJdbcTemplate().update(query.toString(),getObject(objects));
    }
    
    public void SP_PM_MAKE_TO_PMI(String pminsschedId, User user ) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("EXEC                                                           ");
        query.append(" dbo.SP_PM_MAKE_TO_PMI '"+user.getCompNo()+"',"+pminsschedId+"; ");

        this.getJdbcTemplate().execute(query.toString());
    }
    
    
  
   public int updateScheduleDate(String id, String scheDate, String remark, User user)
   {
   	QuerySqlBuffer query = new QuerySqlBuffer();
   	
       String pmInsSchedId = id;
   	
       query.append("UPDATE TAPMINSSCHED  SET      ");
       query.append("     SCHED_DATE = ?           ");
       query.append("     ,REMARK    = ?           ");
       query.append("WHERE comp_no = ?             ");
       query.append("  AND pminssched_id =  ?      ");
       Object[] objects = new Object[]{
       		 scheDate
       		,remark
       		,user.getCompNo()
       		,pmInsSchedId
       };
       
       return this.getJdbcTemplate().update(query.toString(),getObject(objects));
       
   }
   
   public int updateResultSchedDetail(String id, String scheDate, User user)
   {
	   QuerySqlBuffer query = new QuerySqlBuffer();
	   
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
		
	   return this.getJdbcTemplate().update(query.toString(),getObject(objects));
	   
   }
   
   
   public int updateDeleteTagSched(String pmInsSchedId, User user)
   {
   	
	   QuerySqlBuffer query = new QuerySqlBuffer();
   	
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
       result = this.getJdbcTemplate().update(query.toString(),getObject(objects));
       
       
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
       
       return  this.getJdbcTemplate().update(query.toString(),getObject(objects));
   }

    @Override
    public String findTotalCount(WorkCalPmRInsMonthCommonDTO workCalPmRInsMonthCommonDTO, User user) throws Exception {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                  ");
        query.append("SELECT         		");
        query.append("       COUNT(1)       ");
        query.append("FROM TAPMINSSCHED x inner join TAPMLST y on x.comp_no = y.comp_no and x.pm_id = y.pm_id       ");
        query.append("LEFT OUTER JOIN TAEQUIPMENT c                                                                 ");
        query.append("ON x.equip_id = c.equip_id AND x.comp_no = c.comp_no AND c.is_deleted='N'                     ");
        query.append("LEFT OUTER JOIN TAPMINSLIST d                                                                 ");
        query.append("ON x.pminslist_id = d.pminslist_id AND x.comp_no = d.comp_no                                  ");
        query.append("WHERE 1=1                                                                                   ");
        query.append(this.getWhere(workCalPmRInsMonthCommonDTO,user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    
    }
}