package dream.mgr.workflow.service.spring;

import java.util.List;

import common.bean.User;
import dream.mgr.workflow.dao.MgrWorkflowListDAO;
import dream.mgr.workflow.dto.MgrWorkflowCommonDTO;
import dream.mgr.workflow.service.MgrWorkflowListService;

/**
 * Wokrflow Page - List Service implements
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="mgrWorkflowListServiceTarget"
 * @spring.txbn id="mgrWorkflowListService"
 * @spring.property name="mgrWorkflowListDAO" ref="mgrWorkflowListDAO"
 */
public class MgrWorkflowListServiceImpl implements MgrWorkflowListService
{
	private MgrWorkflowListDAO mgrWorkflowListDAO = null;

	public List findWorkflowList(MgrWorkflowCommonDTO mgrWorkflowCommonDTO, User user) throws Exception
    {      
        return mgrWorkflowListDAO.findWorkflowList(mgrWorkflowCommonDTO,user);
    }

	public int deleteWorkflowList( String[] deleteRows, User user) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + mgrWorkflowListDAO.deleteWorkflowList(id, user);
            }
        return result;
    }

	public MgrWorkflowListDAO getMgrWorkflowListDAO() {
		return mgrWorkflowListDAO;
	}

	public void setMgrWorkflowListDAO(MgrWorkflowListDAO mgrWorkflowListDAO) {
		this.mgrWorkflowListDAO = mgrWorkflowListDAO;
	}
	public String findTotalCount(MgrWorkflowCommonDTO mgrWorkflowCommonDTO,User user) throws Exception
    {
        return mgrWorkflowListDAO.findTotalCount(mgrWorkflowCommonDTO, user);
    }
}

