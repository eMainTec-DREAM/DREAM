package dream.mgr.workflow.service;

import common.bean.User;
import dream.mgr.workflow.dto.MgrWorkflowCommonDTO;
import dream.mgr.workflow.dto.MgrWorkflowDetailDTO;
/**
 * Wokrflow Page - Detail Service
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 */
public interface MgrWorkflowDetailService
{    
	/**
	 * FIND WORKFLOW DETAIL
	 * @param mgrWorkflowCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public MgrWorkflowDetailDTO findWorkflowDetail(MgrWorkflowCommonDTO mgrWorkflowCommonDTO, User user) throws Exception;
	/**
	 * INSERT WORKFLOW
	 * @param mgrWorkflowDetailDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public int insertWorkflowDetail(MgrWorkflowDetailDTO mgrWorkflowDetailDTO, User user) throws Exception;
    /**
     * UPDATE WORKFLOW
     * @param mgrWorkflowDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateWorkflowDetail(MgrWorkflowDetailDTO mgrWorkflowDetailDTO, User user) throws Exception;
}
