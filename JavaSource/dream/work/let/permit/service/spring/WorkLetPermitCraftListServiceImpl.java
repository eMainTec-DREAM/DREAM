package dream.work.let.permit.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.let.permit.dao.WorkLetPermitCraftListDAO;
import dream.work.let.permit.dto.WorkLetPermitCraftDetailDTO;
import dream.work.let.permit.dto.WorkLetPermitCraftListDTO;
import dream.work.let.permit.dto.WorkLetPermitDetailDTO;
import dream.work.let.permit.service.WorkLetPermitCraftDetailService;
import dream.work.let.permit.service.WorkLetPermitCraftListService;

/**
 * 안전작업허가서유형 - 작업자 목록 service
 * @author syyang
 * @version $Id: WorkLetPermitCraftListServiceImpl.java,v 1.0 2015/12/02 09:12:51 syyang Exp $
 * @since 1.0
 * 
 * @spring.bean id="workLetPermitCraftListServiceTarget"
 * @spring.txbn id="workLetPermitCraftListService"
 * @spring.property name="workLetPermitCraftListDAO" ref="workLetPermitCraftListDAO"
 */
public class WorkLetPermitCraftListServiceImpl implements WorkLetPermitCraftListService
{
    private WorkLetPermitCraftListDAO workLetPermitCraftListDAO = null;

	public WorkLetPermitCraftListDAO getWorkLetPermitCraftListDAO() {
		return workLetPermitCraftListDAO;
	}
	public void setWorkLetPermitCraftListDAO(WorkLetPermitCraftListDAO workLetPermitCraftListDAO) {
		this.workLetPermitCraftListDAO = workLetPermitCraftListDAO;
	}
	
    
    public List findCraftList(WorkLetPermitDetailDTO workLetPermitDetailDTO, WorkLetPermitCraftListDTO workLetPermitCraftListDTO, User loginUser)
    {      
        return workLetPermitCraftListDAO.findCraftList(workLetPermitDetailDTO, workLetPermitCraftListDTO, loginUser);
    }
    
	public int deleteCraftList(String[] deleteRows, String compNo) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + workLetPermitCraftListDAO.deleteCraftList(id, compNo);
            }
        
        return result;
    }

	@Override
	public String findTotalCount(WorkLetPermitDetailDTO workLetPermitDetailDTO,
			WorkLetPermitCraftListDTO workLetPermitCraftListDTO, User user) throws Exception {
		return workLetPermitCraftListDAO.findTotalCount(workLetPermitDetailDTO, workLetPermitCraftListDTO, user);
	}
	
}

