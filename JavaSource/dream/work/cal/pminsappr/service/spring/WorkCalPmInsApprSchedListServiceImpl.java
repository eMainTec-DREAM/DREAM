package dream.work.cal.pminsappr.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.cal.pminsappr.dao.WorkCalPmInsApprSchedListDAO;
import dream.work.cal.pminsappr.dto.WorkCalPmInsApprCommonDTO;
import dream.work.cal.pminsappr.dto.WorkCalPmInsApprDetailDTO;
import dream.work.cal.pminsappr.service.WorkCalPmInsApprSchedListService;
import dream.work.planappr.dto.WorkPlanApprCommonDTO;
import dream.work.planappr.dto.WorkPlanApprDetailDTO;

/**
 * 예방점검계획승인 - 점검작업 serviceimpl
 * @author kim21017
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="workCalPmInsApprSchedListServiceTarget"
 * @spring.txbn id="workCalPmInsApprSchedListService"
 * @spring.property name="workCalPmInsApprSchedListDAO" ref="workCalPmInsApprSchedListDAO"
 */
public class WorkCalPmInsApprSchedListServiceImpl implements WorkCalPmInsApprSchedListService
{
    private WorkCalPmInsApprSchedListDAO workCalPmInsApprSchedListDAO = null;

    
	public WorkCalPmInsApprSchedListDAO getWorkCalPmInsApprSchedListDAO() {
		return workCalPmInsApprSchedListDAO;
	}


	public void setWorkCalPmInsApprSchedListDAO(WorkCalPmInsApprSchedListDAO workCalPmInsApprSchedListDAO) {
		this.workCalPmInsApprSchedListDAO = workCalPmInsApprSchedListDAO;
	}


	public List findList(WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO,WorkCalPmInsApprDetailDTO workCalPmInsApprDetailDTO, User user)
    {      
        return workCalPmInsApprSchedListDAO.findList(workCalPmInsApprCommonDTO,workCalPmInsApprDetailDTO,user);
    }
	
    public void insertPmiSched(WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO,WorkCalPmInsApprDetailDTO workCalPmInsApprDetailDTO, User user)
    {
        workCalPmInsApprSchedListDAO.deletePmInsSchedApprList(workCalPmInsApprCommonDTO,workCalPmInsApprDetailDTO, user);
        
        //미점검 조회
        if("NOT".equals(workCalPmInsApprCommonDTO.getPminsschedapprType()))
        {
            workCalPmInsApprCommonDTO.setPmschedStatus("P+S");
        }
        
        workCalPmInsApprSchedListDAO.insertPmInsSchedApprList(workCalPmInsApprCommonDTO,workCalPmInsApprDetailDTO, user);
    }

	@Override
	public String findTotalCount(WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO, WorkCalPmInsApprDetailDTO workCalPmInsApprDetailDTO, User user) {
		return workCalPmInsApprSchedListDAO.findTotalCount(workCalPmInsApprCommonDTO, workCalPmInsApprDetailDTO, user);
	}
	
}