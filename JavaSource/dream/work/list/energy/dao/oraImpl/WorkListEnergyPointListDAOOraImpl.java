package dream.work.list.energy.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.work.list.energy.dao.WorkListEnergyPointListDAO;
import dream.work.list.energy.dto.WorkListEnergyMstrListCommonDTO;
import dream.work.list.energy.dto.WorkListEnergyPointListDTO;

/**
 * 에너지 값 측정항목 목록 dao
 * @author  sy.yang
 * @version $Id: WorkListEnergyPointListDAO.java,v 1.0 2015/12/02 09:14:12 sy.yang Exp $
 * @since   1.0
 * @spring.bean id="workListEnergyPointListDAOTarget"
 * @spring.txbn id="workListEnergyPointListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkListEnergyPointListDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkListEnergyPointListDAO
{
    /**
     * grid find
     * @author  sy.yang
     * @version $Id: WorkListEnergyPointListDAO.java,v 1.0 2015/12/02 09:14:12 sy.yang Exp $
     * @since   1.0
     * 
     * @param workListEnergyMstrListCommonDTO
     * @param workListEnergyPointListDTO
     * @param loginUser
     * @return List
     */
    public List findPointList(WorkListEnergyMstrListCommonDTO workListEnergyMstrListCommonDTO, WorkListEnergyPointListDTO workListEnergyPointListDTO, User loginUser) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT																");
        query.append("		''					 					AS isDelCheck			");
        query.append("		,'' 									AS seqNo				");
        query.append("		,y.step_num 							AS stepNum				");
        query.append("      ,(SELECT a.check_point                  	   					");
        query.append("        FROM TASTDCHECKPOINT a                	   					");
        query.append("        WHERE a.comp_no = y.comp_no           	        			");
        query.append("         AND a.std_check_point_id = y.std_check_point_id)	AS stdCheckPoint	");
        query.append("      ,y.std_check_point_id					AS stdCheckPointId		");
        query.append("		,y.check_point 							AS checkpoint			");
        query.append("		,x.result_value 						AS resultValue			");
        query.append("		,y.uom 									AS uom					");
        query.append("		,x.is_run_value 						AS isRunValue			");
        query.append("      ,x.remark                         		AS remark        		");
        query.append("		,x.pminspoint_id 						AS pmInsPointId			");
        query.append("		,y.pm_point_id	 						AS pmPointId			");
        query.append("		,x.pminslist_id	 						AS pmInsListId			");
        query.append("      ,nvl(x.cal_value,0)           			AS calValue        		");
        // 작업완료 (이전측정값)
 		if(workListEnergyMstrListCommonDTO.getPmschedStatusId().equals("C"))
 		{
 			query.append("		,nvl(x.pre_result_value,0)				AS preResultValue	");
 		}
 		// 작업중 (이전측정값)
 		else
 		{
			/* 저장시 서버에서 이전완료한 값을 찾아서 저장*/
			/*작업중이면 이전완료한 값중에서 날짜, 시간대가 MAX인 값을 화면에 셋팅*/
			query.append("		,(SELECT nvl(max(bb.result_value),0) 							");
			query.append("		  FROM TAPMINSLIST aa INNER JOIN TAPMINSPOINT bb			");
			query.append("        ON aa.comp_no=bb.comp_no									");
			query.append("         AND aa.pminslist_id=bb.pminslist_id              		");
			query.append("		  WHERE 1=1													");
			query.append("		   AND aa.pm_id = x.pm_id									");
			query.append("		   AND bb.pm_point_id = x.pm_point_id       				");
			query.append("		   AND aa.pmsched_status = 'C'								");
			query.append("		   AND aa.is_deleted = 'N'					 				");
			query.append("		   AND aa.wkor_date = (SELECT max(wkor_date) FROM TAPMINSLIST WHERE comp_no = x.comp_no AND pm_id = x.pm_id AND pmsched_status = 'C')	");
			query.append("		   AND nvl(aa.measure_time,0) = (SELECT nvl(max(a.measure_time),0)	");
			query.append("		   					   			FROM TAPMINSLIST a    				");
			query.append("		   					   			WHERE a.comp_no=x.comp_no   		");
			query.append("		   					   			 AND a.pm_id=x.pm_id    			");
			query.append("		   					   			 AND a.pmsched_status='C'    		");
			query.append("		   					   			 AND a.wkor_date = (SELECT max(wkor_date) FROM TAPMINSLIST WHERE comp_no = x.comp_no AND pm_id = x.pm_id AND pmsched_status = 'C')	");
			query.append("		   					   		   )									");
			query.append("		 )									AS preResultValue		");
 		}
        query.append("FROM TAPMINSPOINT x INNER JOIN TAPMPOINT y							");
        query.append("	ON x.comp_no = y.comp_no											");
        query.append(" AND x.pm_point_id = y.pm_point_id									");
        query.getAndNumKeyQuery("x.pminslist_id", workListEnergyMstrListCommonDTO.getPminslistId());
        query.append("WHERE 1=1																");
        query.append(this.getWhere(workListEnergyMstrListCommonDTO,workListEnergyPointListDTO,loginUser));
        query.getOrderByQuery("y.step_num", workListEnergyMstrListCommonDTO.getOrderBy(), workListEnergyMstrListCommonDTO.getDirection());

        return getJdbcTemplate().queryForList(query.toString(workListEnergyMstrListCommonDTO.getIsLoadMaxCount(), workListEnergyMstrListCommonDTO.getFirstRow()));

    }
    
    private String getWhere(WorkListEnergyMstrListCommonDTO workListEnergyMstrListCommonDTO, WorkListEnergyPointListDTO workListEnergyPointListDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.getStringEqualQuery("y.comp_no", loginUser.getCompNo());
    	
    	if (!"".equals(workListEnergyMstrListCommonDTO.getPminslistId()))
        {
    		query.append("AND y.pm_id = (SELECT pm_id 													");
    		query.append("				   FROM TAPMINSLIST 											");
    		query.append("				  WHERE comp_no 	 = '"+loginUser.getCompNo()+"'				");
    		query.append("				    AND pminslist_id = '"+workListEnergyMstrListCommonDTO.getPminslistId()+"'	");
    		query.append("				 )																");
        }
    	
        query.getAndQuery("y.is_deleted", "N");

    	if (!"".equals(workListEnergyPointListDTO.getPmInsPointId()))
        {
            query.getAndQuery("x.pminspoint_id", workListEnergyPointListDTO.getPmInsPointId());
            return query.toString();
        } 
    	else if (!"".equals(workListEnergyPointListDTO.getPmPointId())) 
    	{
    		query.getAndQuery("y.pm_point_id", workListEnergyPointListDTO.getPmPointId());
            return query.toString();
        }
    	
    	return query.toString();
    }
    
    public String findTotalCount(WorkListEnergyMstrListCommonDTO workListEnergyMstrListCommonDTO, WorkListEnergyPointListDTO workListEnergyPointListDTO, User loginUser, boolean isComplete) throws Exception
    {
    	QueryBuffer query = new QueryBuffer();
    	
        query.append("SELECT											");
        query.append("       COUNT(1)                                   ");
        query.append("FROM TAPMINSPOINT x INNER JOIN TAPMPOINT y	    ");
        query.append("	ON x.comp_no = y.comp_no						");
        query.append(" AND x.pm_point_id = y.pm_point_id				");
        query.getAndNumKeyQuery("x.pminslist_id", workListEnergyMstrListCommonDTO.getPminslistId());
        query.append("WHERE 1=1											");
        query.append(this.getWhere(workListEnergyMstrListCommonDTO,workListEnergyPointListDTO,loginUser));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
}