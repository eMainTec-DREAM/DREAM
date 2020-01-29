package dream.mgr.intf.dao;

import java.util.List;

import common.bean.User;
import dream.mgr.intf.dto.MgrIntfCommonDTO;

/**
 * Interface Page - List DAO
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 *
 */
public interface MgrIntfListDAO
{
	/**
	 * FIND LIST
	 * @param mgrIntfCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public List findList(MgrIntfCommonDTO mgrIntfCommonDTO, User user) throws Exception;
    
    /**
     * DELETE LIST
     * @param id
     * @param user
     * @return
     * @throws Exception
     */
    public int deleteList(String id, User user) throws Exception;
    /**
	 * FIND LIST COUNT
	 * @param mgrIntfCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(MgrIntfCommonDTO mgrIntfCommonDTO, User user) throws Exception;
    
}