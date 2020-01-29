package mobile.dream.work.pm.list.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import mobile.dream.work.pm.list.dao.WorkPmWorkDetailDAO;
import mobile.dream.work.pm.list.dto.WorkPmWorkCommonDTO;
import mobile.dream.work.pm.list.dto.WorkPmWorkDetailDTO;


/**
 * 상세 dao
 * 
 * @author jung7126
 * @version $Id: WorkPmWorkDetailDAO.java,v 1.0 2015/12/02 08:25:47 jung7126 Exp $
 * @since 1.0
 * @spring.bean id="workPmWorkDetailDAOTarget"
 * @spring.txbn id="workPmWorkDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkPmWorkDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements WorkPmWorkDetailDAO
{
	/**
     * detail find
     * @author jung7126
     * @version $Id: WorkPmWorkDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param eqCtgId
     * @param compNo
     * @return
     */
    public WorkPmWorkDetailDTO findDetail(WorkPmWorkCommonDTO dto, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        String compNo = user.getCompNo();
        String lang = user.getLangId();
        
        query.append("SELECT																			");
        query.append("		x.wkor_id													wkOrId,			");
        query.append("		x.wo_no														woNo,			");
        query.append("		x.wo_status													woStatusId,		");
        query.append("		dbo.SFACODE_TO_DESC(x.wo_status,'WO_STATUS','SYS','','"+lang+"')				woStatusDesc,	");
        query.append("		z.equip_id 													AS equipId,		");
        query.append("		z.description						 						AS equipDesc,	");
        query.append("		(SELECT c.full_desc															");
        query.append("		 FROM TAEQLOC c																");
        query.append("		 WHERE c.comp_no = x.comp_no												");
        query.append("			AND c.eqloc_id = z.eqloc_id	) 							AS eqLocDesc,	");
        query.append("		x.description												wkOrDesc,		");
        query.append("		x.wo_type													woTypeId,		");
        query.append("		dbo.SFACODE_TO_DESC(x.wo_type,'WO_TYPE','SYS','','"+lang+"')					woTypeDesc,		");
        query.append("		x.pm_type													pmTypeId,		");
        query.append("		dbo.SFACODE_TO_DESC(x.pm_type,x.wo_type+'_TYPE','SYS','','"+lang+"')		pmTypeDesc,		");
        query.append("		x.shift_type												shiftTypeId,	");
        query.append("		dbo.SFACODE_TO_DESC(x.shift_type,'SHIFT_TYPE','SYS','','"+lang+"')			shiftTypeDesc,	");
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
        query.append("		x.start_date												startDate,		");
        query.append("		x.start_time												startTime,		");
        query.append("		x.end_date													endDate,		");
        query.append("		x.end_time													endTime,		");
        query.append("		x.work_time													workTime,		");
        query.append("		x.wkor_date													wkorDate,		");
        query.append("		x.vendor_id													vendorId,		");
        query.append("		x.tot_amt													totAmt,			");
        query.append("      dbo.SFAIDTODESC(x.vendor_id, '', 'VENDOR', x.comp_no)           vendorDesc,  	"); 
        query.append("		x.self_vendor_type											selfVendorType,	");
        query.append("		dbo.SFACODE_TO_DESC(x.self_vendor_type,'SELF_VENDOR_TYPE','SYS','','"+lang+"') selfVendorTypeDesc,");
        query.append("		x.perform													perform,		");
        query.append("		x.pmaction													pmAction,		");
        query.append("		x.wo_gen_type												woGenType,		");
        query.append("		x.eqloc_id													eqLocId,		");
        query.append("		x.p_wkor_id													parentWoId,		");
        query.append("      (SELECT user_name                                                           ");
        query.append("       FROM TAUSER                                                                ");
        query.append("       WHERE comp_no = x.comp_no                                                  ");
        query.append("        AND user_id = x.close_id)                               	closeBy,   		");
        query.append("		x.close_date												closeDate,		");
        query.append("		(SELECT wo_no FROM TAWORKORDER aa WHERE aa.comp_no = x.comp_no AND aa.wkor_id = x.p_wkor_id)	parentWoNo");
        query.append("FROM TAWORKORDER x INNER JOIN TAWOEQUIP y											");
        query.append("ON x.comp_no = y.comp_no and x.wkor_id = y.wkor_id								");
        query.append("		INNER JOIN TAEQUIPMENT z													");
        query.append("		ON y.comp_no = z.comp_no AND y.equip_id = z.equip_id						");
        query.append("WHERE 1=1																			");
        query.append("  AND x.wkor_id = ?																");
        query.append("  AND x.comp_no = ?																");
    
        Object[] objects = new Object[] {
        		dto.getWkorId(),
        		user.getCompNo()
    	};
  
        WorkPmWorkDetailDTO workPmWorkDetailDTO = 
        		(WorkPmWorkDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new WorkPmWorkDetailDTO()));
        
        return workPmWorkDetailDTO;
    }
    
    /**
     * detail insert
     * @author jung7126
     * @version $Id: WorkPmWorkDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param workPmWorkDetailDTO
     * @return
     */
    public int insertDetail(WorkPmWorkDetailDTO workPmWorkDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;
    	
    	query.append("INSERT INTO TAPMLST                      ");
    	query.append("(                                        ");
    	query.append("    pm_id,           comp_no,            ");
    	query.append("    pm_no,           description,        ");
    	query.append("    dept_id,                             ");
    	query.append("    pm_type,         schedule_type,      ");
    	query.append("    cycle,           period_type,        ");
    	query.append("    USAGE,           wo_res_bef,         ");
    	query.append("    init_wrk_date,   last_sch_date,      ");
    	query.append("    wo_type,         pm_categ,           ");
    	query.append("    emp_id,          eqloc_id,           ");
    	query.append("    is_active,       remark,             ");
    	query.append("    wkctr_id				               ");
    	query.append(")VALUES                                  ");
    	query.append("(                                        ");
    	query.append("    ?,               ?,                  ");
    	query.append("    ?,               ?,                  ");
    	query.append("    ?,                                   ");
    	query.append("    ?,               ?,                  ");
    	query.append("    ?,               ?,                  ");
    	query.append("    ?,               ?,                  ");
    	query.append("    ?,               ?,                  ");
    	query.append("    ?,               ?,                  ");
    	query.append("    ?,               ?,                  ");
    	query.append("    ?,               ?,                  ");
    	query.append("    ?					                   ");
    	query.append(")                                        ");

    	
    	Object[] objects = new Object[] {
//    	        workPmWorkDetailDTO.getPmId(),
//    	        workPmWorkDetailDTO.getCompNo(),
//    	        workPmWorkDetailDTO.getPmNo(),
//    	        workPmWorkDetailDTO.getDescription(),
//    	        workPmWorkDetailDTO.getDeptId(),
//    	        workPmWorkDetailDTO.getPmType(),
//    	        workPmWorkDetailDTO.getScheduleType(),
//    	        workPmWorkDetailDTO.getCycle(),
//    	        workPmWorkDetailDTO.getPeriodType(),
//    	        workPmWorkDetailDTO.getUsage(),
//    	        workPmWorkDetailDTO.getWoResBef(),
//    	        workPmWorkDetailDTO.getInitWrkDate(),
//    	        workPmWorkDetailDTO.getInitWrkDate(),
//    	        workPmWorkDetailDTO.getWoType(),
//    	        workPmWorkDetailDTO.getPmCateg(),
//    	        workPmWorkDetailDTO.getEmpId(),
//    	        workPmWorkDetailDTO.getEqLocId(),
//    	        workPmWorkDetailDTO.getIsActive(),
//    	        workPmWorkDetailDTO.getRemark(),
//    	        workPmWorkDetailDTO.getWkCtrId()
    	};
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), objects);
    	
    	if(!"".equals(workPmWorkDetailDTO.getEquipId())&&workPmWorkDetailDTO.getEquipId()!=null){
    		query = new QuerySqlBuffer();
    		
    		query.append("INSERT INTO TAPMEQUIP(comp_no, pmequip_id,pm_id,equip_id)		");
    		query.append("VALUES(?,SQAPMEQUIP_ID.NEXTVAL,?,?)							");
    		Object[] objects1 = new Object[] {
        	        workPmWorkDetailDTO.getCompNo(),
//        	        workPmWorkDetailDTO.getPmId(),
        	        workPmWorkDetailDTO.getEquipId()
        	};
        	rtnValue = this.getJdbcTemplate().update(query.toString(), objects1);
    	}
    	return rtnValue;
    }
    /**
     * detail update
     * @author jung7126
     * @version $Id: WorkPmWorkDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param workPmWorkDetailDTO
     * @return
     */
    public int updateDetail(WorkPmWorkDetailDTO workPmWorkDetailDTO) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;
    	
    	query.append("UPDATE TAPMLST SET               ");
    	query.append("       pm_no            = ?,     ");
    	query.append("       description      = ?,     ");
    	query.append("       dept_id          = ?,     ");
    	query.append("       pm_type          = ?,     ");
    	query.append("       schedule_type    = ?,     ");
    	query.append("       cycle            = ?,     ");
    	query.append("       period_type      = ?,     ");
    	query.append("       USAGE            = ?,     ");
    	query.append("       wo_res_bef       = ?,     ");
    	query.append("       init_wrk_date    = ?,     ");
    	query.append("       last_sch_date    = ?,     ");
    	query.append("       wo_type          = ?,     ");
    	query.append("       pm_categ         = ?,     ");
    	query.append("       emp_id           = ?,     ");
    	query.append("       is_active        = ?,     ");
    	query.append("       remark           = ?,     ");
    	query.append("       eqloc_id         = ?,     ");
    	query.append("       wkctr_id         = ?      ");
    	query.append("WHERE  pm_id            = ?      ");
    	query.append("  AND  comp_no          = ?      ");

        Object[] objects = new Object[] {
//                workPmWorkDetailDTO.getPmNo(),
//                workPmWorkDetailDTO.getDescription(),
//                workPmWorkDetailDTO.getDeptId(),
//                workPmWorkDetailDTO.getPmType(),
//                workPmWorkDetailDTO.getScheduleType(),
//                workPmWorkDetailDTO.getCycle(),
//                workPmWorkDetailDTO.getPeriodType(),
//                workPmWorkDetailDTO.getUsage(),
//                workPmWorkDetailDTO.getWoResBef(),
//                workPmWorkDetailDTO.getInitWrkDate(),
//                workPmWorkDetailDTO.getInitWrkDate(),
//                workPmWorkDetailDTO.getWoType(),
//                workPmWorkDetailDTO.getPmCateg(),
//                workPmWorkDetailDTO.getEmpId(),
//                workPmWorkDetailDTO.getIsActive(),
//                workPmWorkDetailDTO.getRemark(),
//                workPmWorkDetailDTO.getEqLocId(),
//                workPmWorkDetailDTO.getWkCtrId(),
//                workPmWorkDetailDTO.getPmId(),
//                workPmWorkDetailDTO.getCompNo()
        };
        
        rtnValue = getJdbcTemplate().update(query.toString(), objects);

        if(!"".equals(workPmWorkDetailDTO.getEquipId())&&workPmWorkDetailDTO.getEquipId()!=null){
    		query = new QuerySqlBuffer();
    		
    		query.append("UPDATE TAPMEQUIP SET equip_id = ?			");
    		query.append("WHERE comp_no = ?							");
    		query.append("  AND pm_id   = ?							");
    		Object[] objects1 = new Object[] {
        	        workPmWorkDetailDTO.getEquipId(),
        	        workPmWorkDetailDTO.getCompNo(),
//        	        workPmWorkDetailDTO.getPmId(),
        	};
        	rtnValue = this.getJdbcTemplate().update(query.toString(), objects1);
    	}
    	return rtnValue;
    }
    public int updateWorkOrder(WorkPmWorkDetailDTO workPmWorkDetailDTO) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;
    	
    	query.append("UPDATE TAWORKORDER SET          ");
    	query.append("       dept_id         = ?,     ");
    	query.append("       wkctr_id        = ?,     ");
    	query.append("       emp_id          = ?      ");
    	query.append("WHERE  pm_id           = ?      ");
    	query.append("  AND  comp_no         = ?      ");
    	query.append("  AND  wo_status       = ?      ");
    	
    	Object[] objects = new Object[] {
//    			workPmWorkDetailDTO.getDeptId(),
//    			workPmWorkDetailDTO.getWkCtrId(),
//    			workPmWorkDetailDTO.getEmpId(),
//    			workPmWorkDetailDTO.getPmId(),
//    			workPmWorkDetailDTO.getCompNo(),
    			"P"
    	};
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), objects);
    	return rtnValue;
    }

    /**
     * 오늘날짜 이후 wo미발행 스케쥴 삭제
     * @author jung7126
     * @version $Id: WorkPmWorkDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param workPmWorkDetailDTO
     * @return
     */
    public int deletePmSched(WorkPmWorkDetailDTO workPmWorkDetailDTO) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("DELETE FROM TAPMSCHED x								");
    	query.append("WHERE  x.plan_date > TO_CHAR(SYSDATE,'yyyymmdd')		");
    	query.append("  AND  x.pm_id = ?									");
    	query.append("  AND  x.comp_no	= ?									");
    	query.append("  AND  x.wkor_id	IS NULL								");
    	
    	Object[] objects = new Object[] {
//    			workPmWorkDetailDTO.getPmId(),
    			workPmWorkDetailDTO.getCompNo()
    	};
    	
    	return this.getJdbcTemplate().update(query.toString(), objects);
    }
    
    public int createPmSchedule(WorkPmWorkDetailDTO workPmWorkDetailDTO) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("DECLARE @plusdate VARCHAR(8) 											");
        query.append("SET @plusdate = LEFT(CONVERT(VARCHAR, dateadd(MONTH, +12, getdate()), 112),4)+'1231'	");
        query.append("EXEC dbo.SP_PM_MAKE_SCHEDULE_BYONE                             		");
        query.append("                  '"+workPmWorkDetailDTO.getCompNo()+"'          		");
//        query.append("                 ,'"+maPmInsDetailDTO.getPmId()+"'            		");
        query.append("                 ,'"+workPmWorkDetailDTO.getEnterBy()+"'         		");
        query.append("                 ,@plusdate											");
        
        this.getJdbcTemplate().execute(query.toString());
        
        return 0;
    }
    public int createWorkOrder(WorkPmWorkDetailDTO workPmWorkDetailDTO) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("begin                                                          ");
    	query.append("SP_PM_MAKE_WO_BYALL(                                          ");
    	query.append("                  '"+workPmWorkDetailDTO.getCompNo()+"'          ");
    	query.append("                 ,'"+workPmWorkDetailDTO.getEnterBy()+"'         ");
    	query.append("                 );                                            ");
    	query.append("end;                                                           ");
    	
    	this.getJdbcTemplate().execute(query.toString());
    	
    	return 0;
    }
    public String checkDetail(WorkPmWorkDetailDTO workPmWorkDetailDTO, User user)
    {
        
    	QuerySqlBuffer query = new QuerySqlBuffer();
//    	String pmId = workPmWorkDetailDTO.getPmId();
//        
//        query.append("SELECT count(1) FROM TAPMEQUIP 			");
//        query.append("WHERE comp_no = '"+user.getCompNo()+"'	");
//        query.append("  AND pm_id = '"+pmId+"'					");
     
        return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    
    }

}