package dream.mgr.workflow.service.spring;

import common.bean.User;
import dream.mgr.workflow.dao.MgrWorkflowDetailDAO;
import dream.mgr.workflow.dto.MgrWorkflowCommonDTO;
import dream.mgr.workflow.dto.MgrWorkflowDetailDTO;
import dream.mgr.workflow.service.MgrWorkflowDetailService;

/**
 * Wokrflow Page - Detail Service implements
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="mgrWorkflowDetailServiceTarget"
 * @spring.txbn id="mgrWorkflowDetailService"
 * @spring.property name="mgrWorkflowDetailDAO" ref="mgrWorkflowDetailDAO"
 */
public class MgrWorkflowDetailServiceImpl implements MgrWorkflowDetailService
{
    private MgrWorkflowDetailDAO mgrWorkflowDetailDAO = null;
    
    public MgrWorkflowDetailDTO findWorkflowDetail(MgrWorkflowCommonDTO mgrWorkflowCommonDTO, User user) throws Exception
    {
    	return mgrWorkflowDetailDAO.findWorkflowDetail(mgrWorkflowCommonDTO, user);
    }
    
    public int insertWorkflowDetail(MgrWorkflowDetailDTO mgrWorkflowDetailDTO, User user) throws Exception
    {
    	return mgrWorkflowDetailDAO.insertWorkflowDetail(mgrWorkflowDetailDTO, user);
    }
    
    public int updateWorkflowDetail(MgrWorkflowDetailDTO mgrWorkflowDetailDTO, User user) throws Exception
    {
    	 return mgrWorkflowDetailDAO.updateWorkflowDetail(mgrWorkflowDetailDTO, user);
    }

	public MgrWorkflowDetailDAO getMgrWorkflowDetailDAO() {
		return mgrWorkflowDetailDAO;
	}

	public void setMgrWorkflowDetailDAO(MgrWorkflowDetailDAO mgrWorkflowDetailDAO) {
		this.mgrWorkflowDetailDAO = mgrWorkflowDetailDAO;
	}
}
