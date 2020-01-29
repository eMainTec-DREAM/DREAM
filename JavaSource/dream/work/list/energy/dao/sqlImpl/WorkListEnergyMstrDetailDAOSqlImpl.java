package dream.work.list.energy.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.pers.appreq.dto.AppReqDetailDTO;
import dream.work.list.energy.dao.WorkListEnergyMstrDetailDAO;
import dream.work.list.energy.dto.WorkListEnergyMstrDetailDTO;
import dream.work.list.energy.dto.WorkListEnergyMstrListCommonDTO;

/**
 * 에너지관리 - 에너지값 상세  DAO
 * @author sy.yang
 * @version $Id: WorkListEnergyMstrDetailDAO.java,v 1.0 2015/12/02 08:25:47 sy.yang Exp $
 * @since 1.0
 * @spring.bean id="workListEnergyMstrDetailDAOTarget"
 * @spring.txbn id="workListEnergyMstrDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkListEnergyMstrDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements WorkListEnergyMstrDetailDAO
{
	/**
     * detail find
     * @author sy.yang
     * @version $Id: WorkListEnergyMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 sy.yang Exp $
     * @since   1.0
     * 
     * @param workListEnergyMstrListCommonDTO
     * @return
     */
    public WorkListEnergyMstrDetailDTO findDetail(WorkListEnergyMstrListCommonDTO workListEnergyMstrListCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        String lang = user.getLangId();
        
        query.append("SELECT																				");
        query.append("		x.pminslist_id												AS pminslistId		");
        query.append("		,x.pmsched_status 											AS pmschedStatusId	");
        query.append("		,dbo.SFACODE_TO_DESC(x.pmsched_status,'PMSCHED_STATUS','SYS','','"+lang+"')	AS pmschedStatusDesc	");
        query.append("		,x.wo_type													AS woTypeId			");
        query.append("		,dbo.SFACODE_TO_DESC(x.wo_type,'WO_TYPE','SYS','','"+lang+"')	AS woTypeDesc	");
        query.append("		,x.pm_type													AS pmTypeId			");
        query.append("		,dbo.SFACODE_TO_DESC(x.pm_type,x.wo_type+'_TYPE','SYS','','"+lang+"')		AS pmTypeDesc			");
        query.append("		,x.equip_id													AS equipId			");
        query.append("		,(SELECT description															");
        query.append("			FROM TAEQUIPMENT a															");
        query.append("		   WHERE a.comp_no = x.comp_no													");
        query.append("			 AND a.equip_id = x.equip_id) 							AS equipDesc		");
        query.append("		,x.description												AS pmiDesc			");
        query.append("		,x.wkor_date												AS wkorDate			");
        query.append("      ,x.measure_time									  			AS measureTime		");
        query.append("		,x.dept_id													AS deptId			");
        query.append("		,(SELECT description															");
        query.append("		    FROM TADEPT																	");
        query.append("		   WHERE comp_no = x.comp_no													");
        query.append("			 AND dept_id = x.dept_id)								AS deptDesc			");
        query.append("       ,x.wkctr_id	                             				AS wkCtrId			");
        query.append("		 ,(SELECT description															");
        query.append("		  	 FROM TAWKCTR																");
        query.append("		  	WHERE comp_no = x.comp_no													");
        query.append("		  	  AND wkctr_id = x.wkctr_id) 			 				AS wkCtrDesc		");
        query.append("		,x.emp_id													AS empId			");
        query.append("		,(SELECT emp_name																");
        query.append("		    FROM TAEMP																	");
        query.append("		   WHERE comp_no = x.comp_no													");
        query.append("			 AND emp_id = x.emp_id)									AS empDesc			");
        query.append("		,x.remark													AS remark			");
        query.append("		,x.pminssched_id										    AS pmInscchedId 	");
        query.append("		,x.pm_id													AS pmId				");
        query.append("		,(select a.pm_no from tapmlst a where a.comp_no = x.comp_no and a.pm_id = x.pm_id)	AS pmNo	");
        query.append("		,( SELECT param2  																");
        query.append("		   FROM TACDSYSD  																");
        query.append("		   WHERE cdsysd_no=x.pm_type 													");
        query.append("		    AND list_type= x.wo_type+'_TYPE' )        				AS param			");
        query.append("FROM TAPMINSLIST x																	");
        query.append("WHERE 1=1																				");
        query.append("  AND x.pminslist_id = ?																");
        query.append("  AND x.comp_no = ?																	");
    
        Object[] objects = new Object[] {
        		workListEnergyMstrListCommonDTO.getPminslistId()
        		,user.getCompNo()
    	};
        
        WorkListEnergyMstrDetailDTO workListEnergyMstrDetailDTO = 
        		(WorkListEnergyMstrDetailDTO)getJdbcTemplate().query(query.toString(), getObject(objects), new MwareExtractor(new WorkListEnergyMstrDetailDTO()));
        
        return workListEnergyMstrDetailDTO;
    }

	public int insertDetail(WorkListEnergyMstrListCommonDTO workListEnergyMstrListCommonDTO, WorkListEnergyMstrDetailDTO workListEnergyMstrDetailDTO, User user) throws Exception 
	{
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;
    	
    	if ("".equals(workListEnergyMstrDetailDTO.getWoTypeId()) || workListEnergyMstrDetailDTO == null) {
			workListEnergyMstrDetailDTO.setWoTypeId("PMU");;
		}

    	query.append("INSERT INTO TAPMINSLIST							");
    	query.append("(	 comp_no		,pminslist_id	,pminssched_id	");
    	query.append("	,pmsched_status	,equip_id		,pm_id			");
    	query.append("	,wo_type		,pm_type		,description	");
    	query.append("	,wkor_date		,measure_time	,dept_id		");
    	query.append("	,wkctr_id		,emp_id			,remark			");
    	query.append("(	 ?				,?				,?				");
    	query.append("	,?				,?				,?				");
    	query.append("	,?				,?				,?				");
    	query.append("	,?				,?				,?				");
    	query.append("	,?				,?				,?				");
    	query.append(")													");
    	
    	Object[] objects = new Object[] {
    			 user.getCompNo()
    			,workListEnergyMstrDetailDTO.getPminslistId()
    			,workListEnergyMstrDetailDTO.getPmInscchedId()
    			
    			,workListEnergyMstrDetailDTO.getPmschedStatusId()
    			,workListEnergyMstrDetailDTO.getEquipId()
    			,workListEnergyMstrDetailDTO.getPmId()
    			
    			,workListEnergyMstrDetailDTO.getWoTypeId()
    			,workListEnergyMstrDetailDTO.getPmTypeId()
    			,workListEnergyMstrDetailDTO.getPmiDesc()
    			
    			,workListEnergyMstrDetailDTO.getWkorDate()
    			,workListEnergyMstrDetailDTO.getMeasureTime()
    			,workListEnergyMstrDetailDTO.getDeptId()
    			
    			,workListEnergyMstrDetailDTO.getWkCtrId()
    			,workListEnergyMstrDetailDTO.getEmpId()
    			,workListEnergyMstrDetailDTO.getRemark()
    	};
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), objects);
    	
		return rtnValue;
	}
    
    /**
     * detail update
     * @author sy.yang
     * @version $Id: WorkListEnergyMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 sy.yang Exp $
     * @since   1.0
     * 
     * @param workListEnergyMstrDetailDTO
     * @return
     */
    public int updateDetail(WorkListEnergyMstrDetailDTO workListEnergyMstrDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;

    	query.append("UPDATE TAPMINSLIST SET	");
    	query.append("	description			= ?	");
    	query.append("	,wkor_date			= ?	");
    	query.append("	,measure_time		= ?	");
    	query.append("	,dept_id			= ?	");
    	query.append("	,wkctr_id			= ?	");
    	query.append("	,emp_id				= ?	");
    	query.append("	,remark				= ?	");
    	query.append("WHERE pminslist_id	= ?	");
    	query.append("  AND comp_no			= ?	");
    	
    	Object[] objects = new Object[] {
    			workListEnergyMstrDetailDTO.getPmiDesc()
    			,workListEnergyMstrDetailDTO.getWkorDate()
    			,workListEnergyMstrDetailDTO.getMeasureTime()
    			,workListEnergyMstrDetailDTO.getDeptId()
    			,workListEnergyMstrDetailDTO.getWkCtrId()
    			,workListEnergyMstrDetailDTO.getEmpId()
    			,workListEnergyMstrDetailDTO.getRemark()
    			,workListEnergyMstrDetailDTO.getPminslistId()
    			,user.getCompNo()
    	};
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), getObject(objects));
    	
    	query = new QuerySqlBuffer();
    	
    	query.append("UPDATE TAPMINSSCHED SET  ");
        query.append("    sched_date = ?        ");
        query.append("WHERE pminslist_id = ?   ");
        query.append("    AND comp_no = ?       ");
        
        objects = new Object[] {
        		workListEnergyMstrDetailDTO.getWkorDate()
        		,workListEnergyMstrDetailDTO.getPminslistId()
        		,user.getCompNo()
        };
        
        rtnValue =  getJdbcTemplate().update(query.toString(), getObject(objects));
    	
    	return rtnValue;
    }
    
    /**
     * detail update
     * @author sy.yang
     * @version $Id: WorkListEnergyMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 sy.yang Exp $
     * @since   1.0
     * 
     * @param workListEnergyMstrDetailDTO
     * @return
     */
    public int completeDetail(WorkListEnergyMstrDetailDTO workListEnergyMstrDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;
    	
    	query.append("UPDATE TAPMINSLIST SET		");
    	query.append("	pmsched_status		= ?		");
    	query.append("	,close_id			= ?  	");
    	query.append("	,close_date			= CONVERT(VARCHAR, GETDATE(), 112)  	");
    	query.append("WHERE pminslist_id	= ?		");
    	query.append("  AND comp_no			= ?		");
    	
    	Object[] objects = new Object[] {
    			workListEnergyMstrDetailDTO.getPmschedStatusId()
    			,user.getUserId()
    			,workListEnergyMstrDetailDTO.getPminslistId()
    			,user.getCompNo()
    	};
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), getObject(objects));
    	
    	return rtnValue;
    }
    
    public String checkPoint(WorkListEnergyMstrDetailDTO workListEnergyMstrDetailDTO,User user) {

		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("SELECT isnull(a.val,0)                                	");
		query.append("FROM (SELECT                                      		");
		query.append("			CONVERT(VARCHAR, SUM(CASE WHEN x.is_saved = 'Y' THEN 0 WHEN x.is_saved = 'N' THEN 1 END)) AS val	");
		query.append("      FROM TAPMINSPOINT x RIGHT OUTER JOIN TAPMPOINT y	");
		query.append("      ON x.comp_no = y.comp_no                            ");
		query.append("       AND x.pm_point_id = y.pm_point_id                  ");
		query.append("WHERE 1=1                                              	");
		query.getStringEqualQuery("x.comp_no", user.getCompNo());
		query.getStringEqualQuery("x. pminslist_id", workListEnergyMstrDetailDTO.getPminslistId());
		query.getAndQuery("y.is_deleted", "N");
		query.append("         ) a                                              ");

		return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
    
    public String isLastPoint(WorkListEnergyMstrDetailDTO workListEnergyMstrDetailDTO,User user) {
    	
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	// 최신여부 확인 (날짜비교 후 시간대 비교)
    	query.append("SELECT 																				");
    	query.append("  CASE WHEN (x.wkor_date=(SELECT max(wkor_date) FROM TAPMINSLIST WHERE comp_no = x.comp_no AND pm_id = x.pm_id AND pmsched_status = 'C'))	");
    	query.append("  	THEN																			");
    	query.append("			CASE WHEN (isnull(x.measure_time,0)=(SELECT isnull(max(a.measure_time),0)	");
    	query.append("                                             	 FROM TAPMINSLIST a                  	");
    	query.append("                                             	 WHERE a.comp_no=x.comp_no           	");
    	query.append("                                             	  AND a.pm_id=x.pm_id                	");
    	query.append("                                             	  AND a.pmsched_status = 'C'         	");
    	query.append("                                             	  AND a.wkor_date = (SELECT max(wkor_date) FROM TAPMINSLIST WHERE comp_no = x.comp_no AND pm_id = x.pm_id AND pmsched_status = 'C')	");
    	query.append("                                             	)										");
    	query.append("                    )																	");
    	query.append("          	THEN 1																	");
    	query.append("          	ELSE 0																	");
    	query.append("			END																			");
    	query.append("      ELSE 0																			");
    	query.append("	END     											AS val							");
    	query.append("FROM TAPMINSLIST x																	");
    	query.append("WHERE 1=1																				");
    	query.getAndQuery("x.comp_no", user.getCompNo());
    	query.getAndQuery("x.pminslist_id", workListEnergyMstrDetailDTO.getPminslistId());
    	query.getAndQuery("x.is_deleted", "N");
    	
    	return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
    
    /**
     * detail update
     * @author sy.yang
     * @version $Id: WorkListEnergyMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 sy.yang Exp $
     * @since   1.0
     * 
     * @param workListEnergyMstrDetailDTO
     * @return
     */
    public int completeSched(WorkListEnergyMstrDetailDTO workListEnergyMstrDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;
    	
    	query.append("UPDATE TAPMINSSCHED SET		");
    	query.append("	 pmsched_status		= ?		");
    	query.append("	,check_by			= ? 	");
    	query.append("WHERE pminslist_id	= ?		");
    	query.append("  AND comp_no			= ?		");
    	
    	Object[] objects = new Object[] {
    			workListEnergyMstrDetailDTO.getPmschedStatusId()
    			,user.getEmpId()
    			,workListEnergyMstrDetailDTO.getPminslistId()
    			,user.getCompNo()
    	};
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), getObject(objects));
    	
    	return rtnValue;
    }
    
    /**
     * detail update
     * @author sy.yang
     * @version $Id: WorkListEnergyMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 sy.yang Exp $
     * @since   1.0
     * 
     * @param workListEnergyMstrDetailDTO
     * @return
     */
    public int completePoint(WorkListEnergyMstrDetailDTO workListEnergyMstrDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;
    	
    	query.append("UPDATE TAPMINSPOINT SET		");
    	query.append("	 pmsched_status		= ?		");
    	query.append("WHERE pminslist_id	= ?		");
    	query.append("  AND comp_no			= ?		");
    	
    	Object[] objects = new Object[] {
    			workListEnergyMstrDetailDTO.getPmschedStatusId()
    			,workListEnergyMstrDetailDTO.getPminslistId()
    			,user.getCompNo()
    	};
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), getObject(objects));
    	
    	return rtnValue;
    }

    public int setStatus(AppReqDetailDTO appReqDetailDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("UPDATE TAPMINSLIST SET                  ");
        query.append("       pmsched_status   = CASE WHEN pmsched_status='C' THEN 'C' ELSE ? END             ");
        query.append("WHERE  pminslist_id     = ?             ");
        query.append("  AND  comp_no       	  = ?             ");
        
        Object[] objects = new Object[] {
                appReqDetailDTO.getParentStatus(),
                appReqDetailDTO.getObjectId(),
                user.getCompNo()
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }

    public int insertEnergyPmInsSched(WorkListEnergyMstrListCommonDTO workListEnergyMstrListCommonDTO, WorkListEnergyMstrDetailDTO workListEnergyMstrDetailDTO, User user) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("INSERT INTO TAPMINSSCHED					");
    	query.append("(  comp_no     		,pminssched_id		");
    	query.append("  ,pminslist_id		,pmequip_id			");
    	query.append("  ,equip_id      		,pm_id          	");
    	query.append("  ,plan_init_date		,plan_date    		");
    	query.append("  ,sched_date         ,pmsched_status		");
    	query.append("  ,work_number							");
    	query.append(")											");
    	query.append("SELECT									");
    	query.append("   a.comp_no			,?       			");
    	query.append("  ,?					,b.pmequip_id		");
    	query.append("  ,b.equip_id      	,a.pm_id           	");
    	query.append("  ,CONVERT(NVARCHAR, GETDATE(),112)	,CONVERT(NVARCHAR, GETDATE(),112)	");
    	query.append("  ,CONVERT(NVARCHAR, GETDATE(),112)	,?	");
    	query.append("  ,1										");
    	query.append("FROM TAPMLST a, TAPMEQUIP b				");
    	query.append("WHERE 1 = 1								");
    	query.append(" AND a.comp_no = b.comp_no				");
    	query.append(" AND a.pm_id = b.pm_id					");
    	query.append(" AND a.comp_no = ?						");
    	query.append(" AND a.pm_id = ?							");
    	query.append(" AND a.is_active = 'Y'					");
    	query.append(" AND b.is_active = 'Y'					");
    	
    	
    	Object[] objects = new Object[]	{
			workListEnergyMstrDetailDTO.getPmInsSchedId()
			,workListEnergyMstrDetailDTO.getPminslistId()
			,workListEnergyMstrDetailDTO.getPmschedStatusId()		//"P"
			,user.getCompNo()
			,workListEnergyMstrDetailDTO.getPmId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
	public int insertWoEnergyMstrDetail(WorkListEnergyMstrListCommonDTO workListEnergyMstrListCommonDTO, WorkListEnergyMstrDetailDTO workListEnergyMstrDetailDTO, User user) throws Exception
	{
        QuerySqlBuffer query = new QuerySqlBuffer();

        String deptId = (user.getDeptId()==null)?"":(user.getDeptId().equals("null")?"":user.getDeptId());
        String wkctridId = (user.getWkctrId())==null?"":(user.getWkctrId().equals("null")?"":user.getWkctrId());
        String empId = (user.getEmpId())==null?"":(user.getEmpId().equals("null")?"":user.getEmpId());
        
        
        query.append("INSERT INTO TAPMINSLIST							");
    	query.append("(	 comp_no		,pminslist_id	,pminssched_id	");
    	query.append("	,pmsched_status	,pm_id			,equip_id		");
    	query.append("	,wo_type		,pm_type		,description	");
        query.append("	,plant			,wkor_date    	,dept_id		");
    	query.append("	,wkctr_id		,emp_id							");
    	query.append(")													");
    	query.append("SELECT                                          	");
    	query.append("	 comp_no		,?				,?              ");
    	query.append("	 ,?				,?								");
        query.append("	 ,(SELECT TOP 1 aa.equip_id						");
        query.append("	   FROM TAPMEQUIP aa							");
        query.append("	   WHERE aa.comp_no = x.comp_no					");
        query.append("		AND aa.pm_id = x.pm_id)						");
        query.append("	,?				,pm_type		,description	");
        query.append("	,plant	,convert(nvarchar, getDate(), 112)	,?	");
    	query.append("	,?				,?								");
        query.append("FROM TAPMLST x                                   	");
        query.append("WHERE 1=1                                			");
        query.append("  AND comp_no = ?                                	");
        query.append("  AND pm_id = ?                                	");

        
        Object[] objects = new Object[] {
        		 workListEnergyMstrDetailDTO.getPminslistId()
         		,workListEnergyMstrDetailDTO.getPmInsSchedId()        	
        		,workListEnergyMstrDetailDTO.getPmschedStatusId()		//"P"
        		,workListEnergyMstrDetailDTO.getPmId()
        		,workListEnergyMstrDetailDTO.getWoTypeId()
        		,deptId
        		,wkctridId
        		,empId
        		,user.getCompNo()
        		,workListEnergyMstrDetailDTO.getPmId()
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
	}

	@Override
	public String checkConfirm(WorkListEnergyMstrDetailDTO workListEnergyMstrDetailDTO, User user) {

		QuerySqlBuffer query = new QuerySqlBuffer();
			
		query.append("SELECT COUNT(*)						");
		query.append("FROM TAPMINSLIST x					");
		query.append("WHERE 1=1								");
		query.append("AND x.comp_no = ?						");
		query.append("AND x.pm_id = ?						");
		query.append("AND x.pminslist_id != ?				");
		query.append("AND x.wo_type = ?						");
		query.append("AND x.wkor_date >= ?					");
		query.append("AND ISNULL(x.MEASURE_TIME,'23:59') >= ?	");
		query.append("AND x.PMSCHED_STATUS = ?				");
		query.append("AND x.IS_DELETED = ?					");
		
        Object[] objects = new Object[] {
        		user.getCompNo()
        		,workListEnergyMstrDetailDTO.getPmId()
        		,workListEnergyMstrDetailDTO.getPminslistId()
        		,"PMU"
        		,workListEnergyMstrDetailDTO.getWkorDate()
        		,workListEnergyMstrDetailDTO.getMeasureTime()
        		,"C"
        		,"N"
       };

		return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString(), objects));
    }
}