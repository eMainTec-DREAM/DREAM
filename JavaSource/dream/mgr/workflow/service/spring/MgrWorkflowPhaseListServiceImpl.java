package dream.mgr.workflow.service.spring;

import java.util.List;

import common.bean.User;
import dream.mgr.workflow.dao.MgrWorkflowPhaseListDAO;
import dream.mgr.workflow.dto.MgrWorkflowPhaseListDTO;
import dream.mgr.workflow.service.MgrWorkflowPhaseListService;

/**
 * Wokrflow Phase Page - List Service implements
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="mgrWorkflowPhaseListServiceTarget"
 * @spring.txbn id="mgrWorkflowPhaseListService"
 * @spring.property name="mgrWorkflowPhaseListDAO" ref="mgrWorkflowPhaseListDAO"
 */
public class MgrWorkflowPhaseListServiceImpl implements MgrWorkflowPhaseListService
{
	private MgrWorkflowPhaseListDAO mgrWorkflowPhaseListDAO = null;

	public List findWorkflowPhaseList(MgrWorkflowPhaseListDTO mgrWorkflowPhaseListDTO, User user) throws Exception
    {      
        return mgrWorkflowPhaseListDAO.findWorkflowPhaseList(mgrWorkflowPhaseListDTO,user);
    }

	public int deleteWorkflowPhaseList( String[] deleteRows, User user) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + mgrWorkflowPhaseListDAO.deleteWorkflowPhaseList(id, user);
            }
        return result;
    }

	public MgrWorkflowPhaseListDAO getMgrWorkflowPhaseListDAO() {
		return mgrWorkflowPhaseListDAO;
	}

	public void setMgrWorkflowPhaseListDAO(MgrWorkflowPhaseListDAO mgrWorkflowPhaseListDAO) {
		this.mgrWorkflowPhaseListDAO = mgrWorkflowPhaseListDAO;
	}
	public String findTotalCount(MgrWorkflowPhaseListDTO mgrWorkflowPhaseListDTO,User user) throws Exception
    {
        return mgrWorkflowPhaseListDAO.findTotalCount(mgrWorkflowPhaseListDTO, user);
    }
}

