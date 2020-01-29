/**
 * 
 */
package dream.pers.appr.service;

import java.util.List;

import common.bean.User;
import dream.pers.appr.dto.PersApprHistCommonDTO;
/**
 * 결재이력 - List Service
 * @author sy.yang
 * @version $Id:$
 * @since 1.0
 */
public interface PersApprHistListService {
	/**
	 * FIND PROGRAM GUIDE LIST
	 * @param persApprHistCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List findApprHistList(PersApprHistCommonDTO persApprHistCommonDTO, User user) throws Exception;
	/**
	 * DELETE PROGRAM GUIDE LIST
	 * @param deleteRows
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int deletePgmGuideList(String[] deleteRows, User user) throws Exception;
	/**
	 * FIND PROGRAM GUIDE LIST COUNT
	 * @param persApprHistCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(PersApprHistCommonDTO persApprHistCommonDTO, User user) throws Exception;
}
