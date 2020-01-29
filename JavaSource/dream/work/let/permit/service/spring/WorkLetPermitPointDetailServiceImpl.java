package dream.work.let.permit.service.spring;

import common.bean.User;
import dream.work.let.permit.dao.WorkLetPermitPointDetailDAO;
import dream.work.let.permit.dto.WorkLetPermitDetailDTO;
import dream.work.let.permit.dto.WorkLetPermitPointDetailDTO;
import dream.work.let.permit.dto.WorkLetPermitPointListDTO;
import dream.work.let.permit.service.WorkLetPermitPointDetailService;

/**
 * 안전작업허가서 작업유형 - 점검항목 상세 Service implements
 * @author syyang
 * @version $Id: WorkLetPermitPointDetailServiceImpl.java,v 1.0 2015/12/02 09:12:40 syyang Exp $
 * @since 1.0
 * @spring.bean id="workLetPermitPointDetailServiceTarget"
 * @spring.txbn id="workLetPermitPointDetailService"
 * @spring.property name="workLetPermitPointDetailDAO" ref="workLetPermitPointDetailDAO"
 */
public class WorkLetPermitPointDetailServiceImpl implements WorkLetPermitPointDetailService
{
    private WorkLetPermitPointDetailDAO workLetPermitPointDetailDAO = null;

	public WorkLetPermitPointDetailDAO getWorkLetPermitPointDetailDAO() {
		return workLetPermitPointDetailDAO;
	}
	public void setWorkLetPermitPointDetailDAO(WorkLetPermitPointDetailDAO workLetPermitPointDetailDAO) {
		this.workLetPermitPointDetailDAO = workLetPermitPointDetailDAO;
	}
	
	
    public WorkLetPermitPointDetailDTO findDetail(WorkLetPermitDetailDTO workLetPermitDetailDTO, WorkLetPermitPointListDTO workLetPermitPointListDTO, User user) throws Exception
    {
    	return workLetPermitPointDetailDAO.findDetail(workLetPermitDetailDTO, workLetPermitPointListDTO, user);
    }
    
    public int insertDetail(WorkLetPermitDetailDTO workLetPermitDetailDTO, WorkLetPermitPointDetailDTO workLetPermitPointDetailDTO, User user) throws Exception
    {
    	return workLetPermitPointDetailDAO.insertDetail(workLetPermitDetailDTO, workLetPermitPointDetailDTO, user);
    }
    
    public int updateDetail(WorkLetPermitDetailDTO workLetPermitDetailDTO, WorkLetPermitPointDetailDTO workLetPermitPointDetailDTO, User user) throws Exception
    {
    	 return workLetPermitPointDetailDAO.updateDetail(workLetPermitDetailDTO, workLetPermitPointDetailDTO, user);
    }
    
}
