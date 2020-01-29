package dream.mgr.workflow.dao;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.mgr.workflow.dto.MgrWorkflowCommonDTO;
import dream.mgr.workflow.dto.MgrWorkflowDetailDTO;

/**
 * Wokrflow Page - Detail DAO
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 *
 */
public interface MgrWorkflowDetailDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * FIND DETAIL
     * @param mgrWorkflowCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public MgrWorkflowDetailDTO findWorkflowDetail(MgrWorkflowCommonDTO mgrWorkflowCommonDTO, User user) throws Exception;
    
    /**
     * INSERT DETAIL
     * @param mgrWorkflowDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertWorkflowDetail(MgrWorkflowDetailDTO mgrWorkflowDetailDTO, User user) throws Exception;
    
    /**
     * UPDATE DETAIL
     * @param mgrWorkflowDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateWorkflowDetail(MgrWorkflowDetailDTO mgrWorkflowDetailDTO, User user) throws Exception;
    
}