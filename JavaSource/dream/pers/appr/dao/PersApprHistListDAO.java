package dream.pers.appr.dao;

import java.util.List;

import common.bean.User;
import dream.pers.appr.dto.PersApprHistCommonDTO;

/**
 * 결재이력 - List DAO
 * @author sy.yang
 * @version $Id:$
 * @since 1.0
 *
 */
public interface PersApprHistListDAO
{
	/**
	 * FIND LIST
	 * @param persApprHistCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public List findApprHistList(PersApprHistCommonDTO persApprHistCommonDTO, User user) throws Exception;
    
    /**
     * DELETE LIST
     * @param id
     * @param user
     * @return
     * @throws Exception
     */
    public int deletePgmGuideList(String id, User user) throws Exception;
    /**
	 * FIND LIST COUNT
	 * @param persApprHistCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(PersApprHistCommonDTO persApprHistCommonDTO, User user) throws Exception;
    
}