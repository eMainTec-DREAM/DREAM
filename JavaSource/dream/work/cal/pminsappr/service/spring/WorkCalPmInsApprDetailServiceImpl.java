package dream.work.cal.pminsappr.service.spring;

import common.bean.User;
import common.util.CommonUtil;
import dream.pers.appreq.dto.AppReqDetailDTO;
import dream.work.cal.pminsappr.dao.WorkCalPmInsApprDetailDAO;
import dream.work.cal.pminsappr.dto.WorkCalPmInsApprCommonDTO;
import dream.work.cal.pminsappr.dto.WorkCalPmInsApprDetailDTO;
import dream.work.cal.pminsappr.service.WorkCalPmInsApprDetailService;
import dream.work.cal.pminsappr.service.WorkCalPmInsApprSchedListService;

/**
 * øππÊ¡°∞À∞Ë»πΩ¬¿Œ- ªÛºº serviceimpl 
 * @author  kim21017
 * @version $Id$
 * @since   1.0
 * @spring.bean id="workCalPmInsApprDetailServiceTarget"
 * @spring.txbn id="workCalPmInsApprDetailService"
 * @spring.property name="workCalPmInsApprDetailDAO" ref="workCalPmInsApprDetailDAO"
 */
public class WorkCalPmInsApprDetailServiceImpl implements WorkCalPmInsApprDetailService 
{
    private WorkCalPmInsApprDetailDAO workCalPmInsApprDetailDAO = null;

	public WorkCalPmInsApprDetailDAO getWorkCalPmInsApprDetailDAO() {
		return workCalPmInsApprDetailDAO;
	}

	public void setWorkCalPmInsApprDetailDAO(WorkCalPmInsApprDetailDAO workCalPmInsApprDetailDAO) {
		this.workCalPmInsApprDetailDAO = workCalPmInsApprDetailDAO;
	}

	public WorkCalPmInsApprDetailDTO findDetail(WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO, User user)throws Exception
    {
        return workCalPmInsApprDetailDAO.findDetail(workCalPmInsApprCommonDTO,user);
    }
	
	public int insertDetail(WorkCalPmInsApprDetailDTO workCalPmInsApprDetailDTO, WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO,User user) throws Exception
    {        
	    WorkCalPmInsApprSchedListService workCalPmInsApprSchedListService = (WorkCalPmInsApprSchedListService)CommonUtil.getBean("workCalPmInsApprSchedListService");
	    
	    workCalPmInsApprSchedListService.insertPmiSched(workCalPmInsApprCommonDTO, workCalPmInsApprDetailDTO, user);
		return workCalPmInsApprDetailDAO.insertDetail(workCalPmInsApprDetailDTO, workCalPmInsApprCommonDTO, user);
    }

	public int updateDetail(WorkCalPmInsApprDetailDTO workCalPmInsApprDetailDTO, WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO, User user) throws Exception
    {        
	    WorkCalPmInsApprSchedListService workCalPmInsApprSchedListService = (WorkCalPmInsApprSchedListService)CommonUtil.getBean("workCalPmInsApprSchedListService");
        
        workCalPmInsApprSchedListService.insertPmiSched(workCalPmInsApprCommonDTO, workCalPmInsApprDetailDTO, user);
        return workCalPmInsApprDetailDAO.updateDetail(workCalPmInsApprDetailDTO, workCalPmInsApprCommonDTO, user);
    }
	public int updateStatus(WorkCalPmInsApprDetailDTO workCalPmInsApprDetailDTO, User user) throws Exception
    {        
        return workCalPmInsApprDetailDAO.updateStatus(workCalPmInsApprDetailDTO, user);
    }
	public String checkAppr(WorkCalPmInsApprDetailDTO workCalPmInsApprDetailDTO, User user) throws Exception
	{
		return workCalPmInsApprDetailDAO.checkAppr(workCalPmInsApprDetailDTO,user);
	}
	
	@Override
    public void appProcess(AppReqDetailDTO appReqDetailDTO, User user) throws Exception
    {
        if("C".equals(appReqDetailDTO.getParentStatus()))
        {
        	WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO = new WorkCalPmInsApprCommonDTO();
        	workCalPmInsApprCommonDTO.setPmInsSchedApprId(appReqDetailDTO.getObjectId());
        	workCalPmInsApprCommonDTO.setCompNo(user.getCompNo());
            WorkCalPmInsApprDetailDTO workCalPmInsApprDetailDTO = workCalPmInsApprDetailDAO.findDetail(workCalPmInsApprCommonDTO, user);
            
            this.updateStatus(workCalPmInsApprDetailDTO, user);
        }
        else
        {
            workCalPmInsApprDetailDAO.setStatus(appReqDetailDTO, user);
        }
    }
}
