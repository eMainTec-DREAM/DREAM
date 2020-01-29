package dream.work.cal.pminsappr.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.cal.pminsappr.dao.WorkCalPmInsApprRsltListDAO;
import dream.work.cal.pminsappr.dto.WorkCalPmInsApprCommonDTO;
import dream.work.cal.pminsappr.dto.WorkCalPmInsApprDetailDTO;
import dream.work.cal.pminsappr.service.WorkCalPmInsApprRsltListService;

/**
 * 예방점검계획승인 - 점검작업 serviceimpl
 * @author kim21017
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="workCalPmInsApprRsltListServiceTarget"
 * @spring.txbn id="workCalPmInsApprRsltListService"
 * @spring.property name="workCalPmInsApprRsltListDAO" ref="workCalPmInsApprRsltListDAO"
 */
public class WorkCalPmInsApprRsltListServiceImpl implements WorkCalPmInsApprRsltListService
{
    private WorkCalPmInsApprRsltListDAO workCalPmInsApprRsltListDAO = null;

    
	public WorkCalPmInsApprRsltListDAO getWorkCalPmInsApprRsltListDAO() {
		return workCalPmInsApprRsltListDAO;
	}


	public void setWorkCalPmInsApprRsltListDAO(WorkCalPmInsApprRsltListDAO workCalPmInsApprRsltListDAO) {
		this.workCalPmInsApprRsltListDAO = workCalPmInsApprRsltListDAO;
	}


	public List findList(WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO,WorkCalPmInsApprDetailDTO workCalPmInsApprDetailDTO, User user)
    {      
        return workCalPmInsApprRsltListDAO.findList(workCalPmInsApprCommonDTO,workCalPmInsApprDetailDTO,user);
    }

	@Override
	public String findTotalCount(WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO, WorkCalPmInsApprDetailDTO workCalPmInsApprDetailDTO, User user) {
		return workCalPmInsApprRsltListDAO.findTotalCount(workCalPmInsApprCommonDTO, workCalPmInsApprDetailDTO, user);
	}
	
}