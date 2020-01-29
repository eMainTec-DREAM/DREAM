package dream.work.let.permit.service.spring;

import common.bean.User;
import dream.work.let.permit.dao.WorkLetPermitCraftDetailDAO;
import dream.work.let.permit.dto.WorkLetPermitCraftDetailDTO;
import dream.work.let.permit.dto.WorkLetPermitDetailDTO;
import dream.work.let.permit.service.WorkLetPermitCraftDetailService;

/**
 * 안전작업허가서유형 - 작업자 상세 service
 * @author syyang
 * @version $Id: WorkLetPermitCraftDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 syyang Exp $
 * @since 1.0
 * 
 * @spring.bean id="workLetPermitCraftDetailServiceTarget"
 * @spring.txbn id="workLetPermitCraftDetailService"
 * @spring.property name="workLetPermitCraftDetailDAO" ref="workLetPermitCraftDetailDAO"
 */
public class WorkLetPermitCraftDetailServiceImpl implements WorkLetPermitCraftDetailService
{
    private WorkLetPermitCraftDetailDAO workLetPermitCraftDetailDAO = null;
    
    public WorkLetPermitCraftDetailDAO getWorkLetPermitCraftDetailDAO() {
		return workLetPermitCraftDetailDAO;
	}

	public void setWorkLetPermitCraftDetailDAO(WorkLetPermitCraftDetailDAO workLetPermitCraftDetailDAO) {
		this.workLetPermitCraftDetailDAO = workLetPermitCraftDetailDAO;
	}

	public WorkLetPermitCraftDetailDTO findDetail(String woLetListId, String woLetListCraftId,User user)throws Exception
    {
        return workLetPermitCraftDetailDAO.findDetail(woLetListId, woLetListCraftId, user);
    }
    
	public int updateDetail(WorkLetPermitDetailDTO workLetPermitDetailDTO, WorkLetPermitCraftDetailDTO workLetPermitCraftDetailDTO, User user) throws Exception
    {        
        return workLetPermitCraftDetailDAO.updateDetail(workLetPermitDetailDTO, workLetPermitCraftDetailDTO, user);
    }
	public int insertDetail(WorkLetPermitDetailDTO workLetPermitDetailDTO, WorkLetPermitCraftDetailDTO workLetPermitCraftDetailDTO, User user) throws Exception
    {        
        return workLetPermitCraftDetailDAO.insertDetail(workLetPermitDetailDTO, workLetPermitCraftDetailDTO, user);
    }

}
