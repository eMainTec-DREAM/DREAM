package dream.work.let.categ.service.spring;

import common.bean.User;
import dream.work.let.categ.dao.WorkLetCategPointDetailDAO;
import dream.work.let.categ.dto.WorkLetCategCommonDTO;
import dream.work.let.categ.dto.WorkLetCategPointDetailDTO;
import dream.work.let.categ.dto.WorkLetCategPointListDTO;
import dream.work.let.categ.service.WorkLetCategPointDetailService;

/**
 * 안전작업유형 점검항목 detail Page - Detail Service implements
 * @author euna0207
 * @version $Id: WorkLetCategPointDetailServiceImpl.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
 * @since 1.0
 * @spring.bean id="workLetCategPointDetailServiceTarget"
 * @spring.txbn id="workLetCategPointDetailService"
 * @spring.property name="workLetCategPointDetailDAO" ref="workLetCategPointDetailDAO"
 */
public class WorkLetCategPointDetailServiceImpl implements WorkLetCategPointDetailService
{
    private WorkLetCategPointDetailDAO workLetCategPointDetailDAO = null;
    
    public WorkLetCategPointDetailDTO findDetail(WorkLetCategCommonDTO workLetCategCommonDTO, WorkLetCategPointListDTO workLetCategPointListDTO, User user) throws Exception
    {
    	return workLetCategPointDetailDAO.findDetail(workLetCategCommonDTO, workLetCategPointListDTO, user);
    }
    
    public int insertDetail(WorkLetCategCommonDTO workLetCategCommonDTO, WorkLetCategPointDetailDTO workLetCategPointDetailDTO, User user) throws Exception
    {
    	return workLetCategPointDetailDAO.insertDetail(workLetCategCommonDTO, workLetCategPointDetailDTO, user);
    }
    
    public int updateDetail(WorkLetCategCommonDTO workLetCategCommonDTO, WorkLetCategPointDetailDTO workLetCategPointDetailDTO, User user) throws Exception
    {
    	 return workLetCategPointDetailDAO.updateDetail(workLetCategCommonDTO, workLetCategPointDetailDTO, user);
    }

	public WorkLetCategPointDetailDAO getWorkLetCategPointDetailDAO() {
		return workLetCategPointDetailDAO;
	}

	public void setWorkLetCategPointDetailDAO(WorkLetCategPointDetailDAO workLetCategPointDetailDAO) {
		this.workLetCategPointDetailDAO = workLetCategPointDetailDAO;
	}

    
}
