package dream.mgr.workflow.service;

import common.bean.User;
import dream.mgr.workflow.dto.MgrWorkflowPhaseDetailDTO;
import dream.mgr.workflow.dto.MgrWorkflowPhaseListDTO;
/**
 * Wokrflow Phase Page - Detail Service
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 */
public interface MgrWorkflowPhaseDetailService
{    
	/**
	 * FIND WORKFLOW PHASE DETAIL
	 * @param mgrWorkflowPhaseListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public MgrWorkflowPhaseDetailDTO findWorkflowPhaseDetail(MgrWorkflowPhaseListDTO mgrWorkflowPhaseListDTO, User user) throws Exception;
	/**
	 * INSERT WORKFLOW PHASE DETAIL
	 * @param mgrWorkflowPhaseDetailDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public int insertWorkflowPhaseDetail(MgrWorkflowPhaseDetailDTO mgrWorkflowPhaseDetailDTO, User user) throws Exception;
    /**
     * UPDATE WORKFLOW PHASE DETAIL
     * @param mgrWorkflowPhaseDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateWorkflowPhaseDetail(MgrWorkflowPhaseDetailDTO mgrWorkflowPhaseDetailDTO, User user) throws Exception;
}
