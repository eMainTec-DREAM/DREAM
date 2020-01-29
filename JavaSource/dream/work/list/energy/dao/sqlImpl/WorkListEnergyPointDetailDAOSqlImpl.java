package dream.work.list.energy.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.work.list.energy.dao.WorkListEnergyPointDetailDAO;
import dream.work.list.energy.dto.WorkListEnergyMstrDetailDTO;
import dream.work.list.energy.dto.WorkListEnergyMstrListCommonDTO;
import dream.work.list.energy.dto.WorkListEnergyPointDetailDTO;
import dream.work.list.energy.dto.WorkListEnergyPointListDTO;

/**
 * 에너지 값 측정항목 상세 dao
 * @author  sy.yang
 * @version $Id: WorkListEnergyPointDetailDAO.java,v 1.0 2015/12/04 08:10:27 sy.yang Exp $
 * @since   1.0
 * @spring.bean id="workListEnergyPointDetailDAOTarget"
 * @spring.txbn id="workListEnergyPointDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkListEnergyPointDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements WorkListEnergyPointDetailDAO
{
	 /**
     * detail find
     * @author sy.yang
     * @version $Id: WorkListEnergyPointDetailDAO.java,v 1.0 20155/12/02 08:25:47 sy.yang Exp $
     * @since   1.0
     * 
     * @param wkOrId
     * @param woPointId
     * @param compNo
     * @return
     */
	public WorkListEnergyPointDetailDTO findDetail(WorkListEnergyMstrListCommonDTO workListEnergyMstrListCommonDTO, WorkListEnergyPointListDTO workListEnergyPointListDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	// 작업결과 Id
		String pminslistId = workListEnergyMstrListCommonDTO.getPminslistId();
		// 점검항목 id
		String pmInsPointId = workListEnergyPointListDTO.getPmInsPointId();
		// 점검항목 id
		String pmPointId = workListEnergyPointListDTO.getPmPointId(); 
		String searchId = (pmInsPointId=="")?pmPointId:pmInsPointId;
		
		query.append("SELECT																		");
		query.append("		y.pm_point_id	 							AS pmPointId				");
		query.append("		,x.pminspoint_id 							AS pmInsPointId				");
		query.append("		,y.step_num 								AS stepNum					");
		query.append("      ,y.std_check_point_id						AS stdCheckPointId      	");
		query.append("      ,(SELECT a.check_point                     								");
		query.append("        FROM TASTDCHECKPOINT a                   								");
		query.append("        WHERE a.comp_no = y.comp_no                   						");
		query.append("         AND a.std_check_point_id=y.std_check_point_id)	AS stdCheckPointDesc	");
		query.append("		,y.check_point 								AS 'checkPoint'				");
		query.append("		,y.uom										AS uom						");
		query.append("		,x.result_value 							AS resultValue				");
		query.append("		,x.is_run_value 							AS isRunValue				");
		query.append("		,x.remark 									AS remark					");
		query.append("		,isnull(x.cal_value,0)						AS calValue					");
		
		// 작업완료
		if(workListEnergyMstrListCommonDTO.getPmschedStatusId().equals("C"))
		{
			//이전측정값
			query.append("		,isnull(x.pre_result_value,0)				AS preResultValue		");
		}
		// 작업중
		else
		{
			//이전측정값
			/*작업중이면 이전완료한 값중에서 날짜, 시간대가 MAX인 값을 화면에 셋팅*/
			query.append("		,(SELECT isnull(max(bb.result_value),0) 							");
			query.append("		  FROM TAPMINSLIST aa INNER JOIN TAPMINSPOINT bb					");
			query.append("        ON aa.comp_no=bb.comp_no											");
			query.append("          AND aa.pminslist_id=bb.pminslist_id              				");
			query.append("		  WHERE 1=1															");
			query.append("			AND aa.pm_id = x.pm_id											");
			query.append("			AND bb.pm_point_id = x.pm_point_id       						");
			query.append("			AND aa.pmsched_status = 'C'										");
			query.append("			AND aa.is_deleted = 'N'					 						");
			query.append("			AND aa.wkor_date = (SELECT max(wkor_date) FROM TAPMINSLIST WHERE comp_no = x.comp_no AND pm_id = x.pm_id AND pmsched_status = 'C')	");
			query.append("			AND isnull(aa.measure_time,0) = (SELECT isnull(max(a.measure_time),0)	");
			query.append("								   			 FROM TAPMINSLIST a    					");
			query.append("								   			 WHERE a.comp_no=x.comp_no   			");
			query.append("								   			  AND a.pm_id=x.pm_id    				");
			query.append("								   			  AND a.pmsched_status='C'    			");
			query.append("								   			  AND a.wkor_date = (SELECT max(wkor_date) FROM TAPMINSLIST WHERE comp_no = x.comp_no AND pm_id = x.pm_id AND pmsched_status = 'C')	");
			query.append("								   		   )										");
			query.append("		)											AS preResultValue		");
		}
		query.append("FROM TAPMINSPOINT x INNER JOIN TAPMPOINT y							    	");
		query.append("ON x.comp_no = y.comp_no														");
		query.append(" AND x.pm_point_id = y.pm_point_id											");
		query.append(" AND x.pminslist_id 	= ?														");
		query.append(" AND y.is_deleted 	= ?														");
		query.append("WHERE 1=1																		");
		query.append("  AND y.comp_no = ?															");
		query.append("	AND y.pm_id = (SELECT pm_id 												");
		query.append("				     FROM TAPMINSLIST 											");
		query.append("				    WHERE comp_no 	 = ?										");
		query.append("				      AND pminslist_id = ?										");
		query.append("				  )																");
		
        if(!"".equals(pmInsPointId))
        {
        	query.append("  AND x.pminspoint_id = ?																				");
        }
        else
        {
        	query.append("  AND y.pm_point_id = ?																				");	
        }
        
        Object[] objects = new Object[] {
        		pminslistId
				,"N"
    			,user.getCompNo()
    			,user.getCompNo()
    			,pminslistId
    			,searchId
    	};
    
        WorkListEnergyPointDetailDTO workListEnergyPointDetailDTO = (WorkListEnergyPointDetailDTO)getJdbcTemplate().query(query.toString(), getObject(objects), new MwareExtractor(new WorkListEnergyPointDetailDTO()));
        
        return workListEnergyPointDetailDTO;
    }
	
    /**
     * detail update
     * @author sy.yang
     * @version $Id: WorkListEnergyPointDetailDAO.java,v 1.0 20155/12/02 08:25:47 sy.yang Exp $
     * @since   1.0
     * 
     * @param workListEnergyPointDetailDTO
     * @param workListEnergyMstrListCommonDTO
     * @return
     */
    public int updateDetail(WorkListEnergyPointDetailDTO workListEnergyPointDetailDTO, WorkListEnergyMstrListCommonDTO workListEnergyMstrListCommonDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	Object[] objects;
    	
		query.append("UPDATE TAPMINSPOINT SET			");
		query.append("	result_value			= ?		");
    	query.append("	,pre_result_value		= (SELECT isnull(max(bb.result_value),0)			");
    	query.append("							   FROM TAPMINSLIST aa INNER JOIN TAPMINSPOINT bb   ");
    	query.append("						       ON aa.comp_no = bb.comp_no                    	");
    	query.append("						        AND aa.pminslist_id = bb.pminslist_id           ");
    	query.append("						       WHERE 1=1                                        ");
    	query.append("						        AND aa.pm_id = ?                          		");
    	query.append("						        AND aa.pmsched_status = 'C'                     ");
		query.append("				  		  		AND aa.is_deleted = 'N'					 		");
		query.append("				  		  		AND bb.pm_point_id = ?       					");
    	query.append("						        AND aa.wkor_date = (SELECT max(wkor_date) FROM TAPMINSLIST WHERE comp_no = ? AND pm_id = ? AND pmsched_status = 'C')	");
    	query.append("						        AND isnull(aa.measure_time,0) = (SELECT isnull(max(a.measure_time),0)	");
    	query.append("						                                    	 FROM TAPMINSLIST a                		");
    	query.append("						                                    	 WHERE a.comp_no = ?         			");
    	query.append("						                                    	  AND a.pm_id = ?               		");
    	query.append("						                                    	  AND a.pmsched_status = 'C'	  		");
    	query.append("						                                    	  AND a.wkor_date = (SELECT max(wkor_date) FROM TAPMINSLIST WHERE comp_no = ? AND pm_id = ? AND pmsched_status = 'C')	");
    	query.append("						                                    	)         		");
    	query.append("						      )													");
    	query.append("	,cal_value				= ?		");
    	query.append("	,is_saved				= ?		");
    	query.append("	,remark					= ?		");
    	query.append("WHERE pminspoint_id		= ?		");
    	query.append("  AND comp_no				= ?		");
    	
    	objects = new Object[] {
    			 workListEnergyPointDetailDTO.getResultValue()
     			,workListEnergyMstrListCommonDTO.getPmId()
     			,workListEnergyPointDetailDTO.getPmPointId()
     			,user.getCompNo()
     			,workListEnergyMstrListCommonDTO.getPmId()
     			,user.getCompNo()
     			,workListEnergyMstrListCommonDTO.getPmId()
     			,user.getCompNo()
     			,workListEnergyMstrListCommonDTO.getPmId()
     			,workListEnergyPointDetailDTO.getCalValue()
     			,"Y"
     			,workListEnergyPointDetailDTO.getRemark()
     			,workListEnergyPointDetailDTO.getPmInsPointId()
     			,user.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
	public int insertEnergyPmPoint(WorkListEnergyPointDetailDTO workListEnergyPointDetailDTO, WorkListEnergyMstrDetailDTO workListEnergyMstrDetailDTO, User user)
	{
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
		query.append("INSERT INTO TAPMINSPOINT										");
		query.append("(		 comp_no      		,pminspoint_id      ,pminslist_id	");
		query.append("      ,pminssched_id		,pm_id             	,pmequip_id		");
		query.append("      ,equip_id       	,pmsched_status		,pm_point_id	");
		query.append("      ,is_saved       	,fit_rate           ,is_run_value	");
		query.append(")																");
		query.append("SELECT 														");
		query.append("       comp_no   			,NEXT VALUE FOR SQAPMINSPOINT_ID	,?   	");
		query.append("      ,?					,pm_id         		,isnull(pmequip_id,(SELECT equip_id FROM TAPMEQUIP WHERE pm_id =aa.pm_id AND comp_no=aa.comp_no))	");
		query.append("      ,(SELECT EQUIP_ID 						               	");
		query.append("        FROM TAPMEQUIP                						");
		query.append("        WHERE pm_id =aa.pm_id               					");
		query.append("         AND comp_no=aa.comp_no)               				");
		query.append("      					,?  				,pm_point_id 	");
		query.append("      ,?               	,fit_rate           ,is_run_value	");
		query.append("FROM TAPMPOINT aa												");
		query.append("WHERE 1=1        												");
		query.append(" AND comp_no = ?        										");
		query.append(" AND pm_id = ?        										");
		query.append(" AND is_deleted = ?        									");
		
		
		Object[] objects = new Object[] {
				workListEnergyMstrDetailDTO.getPminslistId()
				,workListEnergyMstrDetailDTO.getPmInsSchedId()
				,workListEnergyMstrDetailDTO.getPmschedStatusId()
				,"N"
    			,user.getCompNo()
    			,workListEnergyMstrDetailDTO.getPmId()
    			,"N"
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    	
	}
	
}