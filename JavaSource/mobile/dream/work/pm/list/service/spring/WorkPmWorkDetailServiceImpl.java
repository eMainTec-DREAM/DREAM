package mobile.dream.work.pm.list.service.spring;

import common.bean.User;
import mobile.dream.work.pm.list.dao.WorkPmWorkDetailDAO;
import mobile.dream.work.pm.list.dto.WorkPmWorkCommonDTO;
import mobile.dream.work.pm.list.dto.WorkPmWorkDetailDTO;
import mobile.dream.work.pm.list.service.WorkPmWorkDetailService;

/**
 * »ó¼¼ serviceimpl 
 * @author  jung7126
 * @version $Id: WorkPmWorkDetailServiceImpl.java,v 1.0 2015/12/02 09:12:51 jung7126 Exp $
 * @since   1.0
 * @spring.bean id="workPmWorkDetailServiceTarget"
 * @spring.txbn id="workPmWorkDetailService"
 * @spring.property name="workPmWorkDetailDAO" ref="workPmWorkDetailDAO"
 */
public class WorkPmWorkDetailServiceImpl implements WorkPmWorkDetailService
{
    private WorkPmWorkDetailDAO workPmWorkDetailDAO = null;
    
    public WorkPmWorkDetailDAO getWorkPmWorkDetailDAO() {
		return workPmWorkDetailDAO;
	}

	public void setWorkPmWorkDetailDAO(WorkPmWorkDetailDAO workPmWorkDetailDAO) {
		this.workPmWorkDetailDAO = workPmWorkDetailDAO;
	}

	public WorkPmWorkDetailDTO findDetail(WorkPmWorkCommonDTO dto, User user)throws Exception
    {
        return workPmWorkDetailDAO.findDetail(dto,user);
    }
	
	public int insertDetail(WorkPmWorkDetailDTO workPmWorkDetailDTO) throws Exception
    {   workPmWorkDetailDAO.insertDetail(workPmWorkDetailDTO);
//        return workPmWorkDetailDAO.createPmSchedule(workPmWorkDetailDTO);
    	return 1;
    }
	
	public String checkDetail(WorkPmWorkDetailDTO workPmWorkDetailDTO, User user) throws Exception
    {  
//		return workPmWorkDetailDAO.checkDetail(workPmWorkDetailDTO, user);
		return null;
    }
	
	public int updateDetail(WorkPmWorkDetailDTO workPmWorkDetailDTO) throws Exception
    {   
		workPmWorkDetailDAO.updateDetail(workPmWorkDetailDTO);
//    	if("N".equals(workPmWorkDetailDTO.getIsActive())){
//    		return workPmWorkDetailDAO.deletePmSched(workPmWorkDetailDTO);
//    	}else if("Y".equals(workPmWorkDetailDTO.getIsActive())){
//    		workPmWorkDetailDAO.createPmSchedule(workPmWorkDetailDTO);
//    		return workPmWorkDetailDAO.createWorkOrder(workPmWorkDetailDTO);
//    	}else{
    		return 0;
//    	}
    }

}
