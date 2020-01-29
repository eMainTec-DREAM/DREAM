package dream.mgr.workflow.service.spring;

import common.bean.User;
import dream.mgr.workflow.dao.MgrWorkflowPhaseDetailDAO;
import dream.mgr.workflow.dto.MgrWorkflowPhaseDetailDTO;
import dream.mgr.workflow.dto.MgrWorkflowPhaseListDTO;
import dream.mgr.workflow.service.MgrWorkflowPhaseDetailService;

/**
 * Wokrflow Phase Page - Detail Service implements
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="mgrWorkflowPhaseDetailServiceTarget"
 * @spring.txbn id="mgrWorkflowPhaseDetailService"
 * @spring.property name="mgrWorkflowPhaseDetailDAO" ref="mgrWorkflowPhaseDetailDAO"
 */
public class MgrWorkflowPhaseDetailServiceImpl implements MgrWorkflowPhaseDetailService
{
    private MgrWorkflowPhaseDetailDAO mgrWorkflowPhaseDetailDAO = null;
    
    public MgrWorkflowPhaseDetailDTO findWorkflowPhaseDetail(MgrWorkflowPhaseListDTO mgrWorkflowPhaseListDTO, User user) throws Exception
    {
    	return mgrWorkflowPhaseDetailDAO.findWorkflowPhaseDetail(mgrWorkflowPhaseListDTO, user);
    }
    
    public int insertWorkflowPhaseDetail(MgrWorkflowPhaseDetailDTO mgrWorkflowPhaseDetailDTO, User user) throws Exception
    {
    	return mgrWorkflowPhaseDetailDAO.insertWorkflowPhaseDetail(mgrWorkflowPhaseDetailDTO, user);
    }
    
    public int updateWorkflowPhaseDetail(MgrWorkflowPhaseDetailDTO mgrWorkflowPhaseDetailDTO, User user) throws Exception
    {
    	 return mgrWorkflowPhaseDetailDAO.updateWorkflowPhaseDetail(mgrWorkflowPhaseDetailDTO, user);
    }

	public MgrWorkflowPhaseDetailDAO getMgrWorkflowPhaseDetailDAO() {
		return mgrWorkflowPhaseDetailDAO;
	}

	public void setMgrWorkflowPhaseDetailDAO(MgrWorkflowPhaseDetailDAO mgrWorkflowPhaseDetailDAO) {
		this.mgrWorkflowPhaseDetailDAO = mgrWorkflowPhaseDetailDAO;
	}
}
