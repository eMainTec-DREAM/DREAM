package dream.mgr.workflow.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.mgr.workflow.dto.MgrWorkflowCommonDTO;

/**
 * Wokrflow Page - List DAO
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 *
 */
public interface MgrWorkflowListDAO extends BaseJdbcDaoSupportIntf
{
	/**
	 * FIND LIST
	 * @param mgrWorkflowCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public List findWorkflowList(MgrWorkflowCommonDTO mgrWorkflowCommonDTO, User user) throws Exception;
    
    /**
     * DELETE LIST
     * @param id
     * @param user
     * @return
     * @throws Exception
     */
    public int deleteWorkflowList(String id, User user) throws Exception;
    /**
	 * FIND LIST COUNT
	 * @param mgrWorkflowCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(MgrWorkflowCommonDTO mgrWorkflowCommonDTO, User user) throws Exception;
    
}