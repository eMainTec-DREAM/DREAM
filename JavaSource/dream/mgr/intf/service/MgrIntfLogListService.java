/**
 * 
 */
package dream.mgr.intf.service;

import java.util.List;

import common.bean.User;
import dream.mgr.intf.dto.MgrIntfCommonDTO;
import dream.mgr.intf.dto.MgrIntfLogListDTO;
/**
 * Interface Log Page - List Service
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 */
public interface MgrIntfLogListService {
	/**
	 * FIND LIST
	 * @param mgrIntfCommonDTO
	 * @param mgrIntfLogListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List findList(MgrIntfCommonDTO mgrIntfCommonDTO, MgrIntfLogListDTO mgrIntfLogListDTO, User user) throws Exception;
	/**
	 * DELETE LIST
	 * @param deleteRows
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int deleteList(String[] deleteRows, User user) throws Exception;
	/**
	 * FIND LIST COUNT
	 * @param mgrIntfLogListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(MgrIntfCommonDTO mgrIntfCommonDTO, MgrIntfLogListDTO mgrIntfLogListDTO, User user) throws Exception;
}
