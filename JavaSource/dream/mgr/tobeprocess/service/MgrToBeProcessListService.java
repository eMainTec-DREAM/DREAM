/**
 * 
 */
package dream.mgr.tobeprocess.service;

import java.util.List;

import common.bean.User;
import dream.mgr.tobeprocess.dto.MgrToBeProcessCommonDTO;
/**
 * ToBeProcess Page - List Service
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 */
public interface MgrToBeProcessListService {
	/**
	 * FIND WORKFLOW LIST
	 * @param mgrToBeProcessCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List findToBeProcessList(MgrToBeProcessCommonDTO mgrToBeProcessCommonDTO, User user) throws Exception;
	/**
	 * FIND WORKFLOW LIST COUNT
	 * @param mgrToBeProcessCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(MgrToBeProcessCommonDTO mgrToBeProcessCommonDTO, User user) throws Exception;
}
