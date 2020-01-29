/**
 * 
 */
package dream.mgr.workflow.service;

import java.util.List;

import common.bean.User;
import dream.mgr.workflow.dto.MgrWorkflowPhaseListDTO;
/**
 * Wokrflow Phase Page - List Service
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 */
public interface MgrWorkflowPhaseListService {
	/**
	 * FIND WORKFLOW PHASE LIST
	 * @param mgrWorkflowPhaseListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List findWorkflowPhaseList(MgrWorkflowPhaseListDTO mgrWorkflowPhaseListDTO, User user) throws Exception;
	/**
	 * DELETE WORKFLOW PHASE LIST
	 * @param deleteRows
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int deleteWorkflowPhaseList(String[] deleteRows, User user) throws Exception;
	/**
	 * FIND WORKFLOW PHASE LIST COUNT
	 * @param mgrWorkflowPhaseListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(MgrWorkflowPhaseListDTO mgrWorkflowPhaseListDTO, User user) throws Exception;
}
