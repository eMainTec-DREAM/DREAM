package dream.mgr.workflow.dao;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.mgr.workflow.dto.MgrWorkflowPhaseListDTO;
import dream.mgr.workflow.dto.MgrWorkflowPhaseDetailDTO;

/**
 * Wokrflow Phase Page - Detail DAO
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 *
 */
public interface MgrWorkflowPhaseDetailDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * FIND DETAIL
     * @param mgrWorkflowPhaseListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public MgrWorkflowPhaseDetailDTO findWorkflowPhaseDetail(MgrWorkflowPhaseListDTO mgrWorkflowPhaseListDTO, User user) throws Exception;
    
    /**
     * INSERT DETAIL
     * @param mgrWorkflowPhaseDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertWorkflowPhaseDetail(MgrWorkflowPhaseDetailDTO mgrWorkflowPhaseDetailDTO, User user) throws Exception;
    
    /**
     * UPDATE DETAIL
     * @param mgrWorkflowPhaseDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateWorkflowPhaseDetail(MgrWorkflowPhaseDetailDTO mgrWorkflowPhaseDetailDTO, User user) throws Exception;
    
}