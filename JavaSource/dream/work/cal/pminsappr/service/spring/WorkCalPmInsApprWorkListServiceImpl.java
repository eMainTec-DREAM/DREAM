package dream.work.cal.pminsappr.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.cal.pminsappr.dao.WorkCalPmInsApprWorkListDAO;
import dream.work.cal.pminsappr.dto.WorkCalPmInsApprCommonDTO;
import dream.work.cal.pminsappr.dto.WorkCalPmInsApprDetailDTO;
import dream.work.cal.pminsappr.service.WorkCalPmInsApprWorkListService;

/**
 * 예방점검계획승인 - 점검작업 serviceimpl
 * @author kim21017
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="workCalPmInsApprWorkListServiceTarget"
 * @spring.txbn id="workCalPmInsApprWorkListService"
 * @spring.property name="workCalPmInsApprWorkListDAO" ref="workCalPmInsApprWorkListDAO"
 */
public class WorkCalPmInsApprWorkListServiceImpl implements WorkCalPmInsApprWorkListService
{
    private WorkCalPmInsApprWorkListDAO workCalPmInsApprWorkListDAO = null;

    
	public WorkCalPmInsApprWorkListDAO getWorkCalPmInsApprWorkListDAO() {
		return workCalPmInsApprWorkListDAO;
	}


	public void setWorkCalPmInsApprWorkListDAO(WorkCalPmInsApprWorkListDAO workCalPmInsApprWorkListDAO) {
		this.workCalPmInsApprWorkListDAO = workCalPmInsApprWorkListDAO;
	}


	public List findList(WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO,WorkCalPmInsApprDetailDTO workCalPmInsApprDetailDTO, User user)
    {      
        return workCalPmInsApprWorkListDAO.findList(workCalPmInsApprCommonDTO,workCalPmInsApprDetailDTO,user);
    }


	@Override
	public String findTotalCount(WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO, WorkCalPmInsApprDetailDTO workCalPmInsApprDetailDTO, User user) {
		return workCalPmInsApprWorkListDAO.findTotalCount(workCalPmInsApprCommonDTO, workCalPmInsApprDetailDTO, user);
	}

}