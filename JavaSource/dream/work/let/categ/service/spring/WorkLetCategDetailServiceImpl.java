package dream.work.let.categ.service.spring;

import common.bean.User;
import dream.work.let.categ.dao.WorkLetCategDetailDAO;
import dream.work.let.categ.dto.WorkLetCategCommonDTO;
import dream.work.let.categ.dto.WorkLetCategDetailDTO;
import dream.work.let.categ.service.WorkLetCategDetailService;

/**
 * 안전작업유형 Detail Page - Detail Service implements
 * @author euna0207
 * @version $Id: WorkLetCategDetailServiceImpl.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
 * @since 1.0
 * @spring.bean id="workLetCategDetailServiceTarget"
 * @spring.txbn id="workLetCategDetailService"
 * @spring.property name="workLetCategDetailDAO" ref="workLetCategDetailDAO"
 */
public class WorkLetCategDetailServiceImpl implements WorkLetCategDetailService
{
    private WorkLetCategDetailDAO workLetCategDetailDAO = null;
    
    public WorkLetCategDetailDTO findDetail(WorkLetCategCommonDTO workLetCategCommonDTO, User user) throws Exception
    {
    	return workLetCategDetailDAO.findDetail(workLetCategCommonDTO, user);
    }
    
    public int insertDetail(WorkLetCategDetailDTO workLetCategDetailDTO, User user) throws Exception
    {
    	return workLetCategDetailDAO.insertDetail(workLetCategDetailDTO, user);
    }
    
    public int updateDetail(WorkLetCategDetailDTO workLetCategDetailDTO, User user) throws Exception
    {
    	 return workLetCategDetailDAO.updateDetail(workLetCategDetailDTO, user);
    }

	public WorkLetCategDetailDAO getWorkLetCategDetailDAO() {
		return workLetCategDetailDAO;
	}

	public void setWorkLetCategDetailDAO(WorkLetCategDetailDAO workLetCategDetailDAO) {
		this.workLetCategDetailDAO = workLetCategDetailDAO;
	}
}
