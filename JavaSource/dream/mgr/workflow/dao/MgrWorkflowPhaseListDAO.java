package dream.mgr.workflow.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.mgr.workflow.dto.MgrWorkflowPhaseListDTO;

/**
 * Wokrflow Phase Page - List DAO
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 *
 */
public interface MgrWorkflowPhaseListDAO extends BaseJdbcDaoSupportIntf
{
	/**
	 * FIND LIST
	 * @param mgrWorkflowPhaseListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public List findWorkflowPhaseList(MgrWorkflowPhaseListDTO mgrWorkflowPhaseListDTO, User user) throws Exception;
    
    /**
     * DELETE LIST
     * @param id
     * @param user
     * @return
     * @throws Exception
     */
    public int deleteWorkflowPhaseList(String id, User user) throws Exception;
    /**
	 * FIND LIST COUNT
	 * @param mgrWorkflowPhaseListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(MgrWorkflowPhaseListDTO mgrWorkflowPhaseListDTO, User user) throws Exception;
    
}