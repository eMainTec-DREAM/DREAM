/**
 * 
 */
package dream.mgr.workflow.service;

import java.util.List;

import common.bean.User;
import dream.mgr.workflow.dto.MgrWorkflowCommonDTO;
/**
 * Wokrflow Page - List Service
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 */
public interface MgrWorkflowListService {
	/**
	 * FIND WORKFLOW LIST
	 * @param mgrWorkflowCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List findWorkflowList(MgrWorkflowCommonDTO mgrWorkflowCommonDTO, User user) throws Exception;
	/**
	 * DELETE WORKFLOW LIST
	 * @param deleteRows
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int deleteWorkflowList(String[] deleteRows, User user) throws Exception;
	/**
	 * FIND WORKFLOW LIST COUNT
	 * @param mgrWorkflowCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(MgrWorkflowCommonDTO mgrWorkflowCommonDTO, User user) throws Exception;
}
