package dream.mgr.tobeprocess.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.mgr.tobeprocess.dto.MgrToBeProcessCommonDTO;

/**
 * ToBeProcess Page - List DAO
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 *
 */
public interface MgrToBeProcessListDAO extends BaseJdbcDaoSupportIntf
{
	/**
	 * FIND LIST
	 * @param mgrToBeProcessCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public List findToBeProcessList(MgrToBeProcessCommonDTO mgrToBeProcessCommonDTO, User user) throws Exception;
    
    /**
	 * FIND LIST COUNT
	 * @param mgrToBeProcessCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(MgrToBeProcessCommonDTO mgrToBeProcessCommonDTO, User user) throws Exception;
    
}