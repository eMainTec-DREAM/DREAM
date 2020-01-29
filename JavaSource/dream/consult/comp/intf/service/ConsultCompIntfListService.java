/**
 * 
 */
package dream.consult.comp.intf.service;

import java.util.List;

import common.bean.User;
import dream.consult.comp.intf.dto.ConsultCompIntfCommonDTO;
/**
 * Interface Page - List Service
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 */
public interface ConsultCompIntfListService {
	/**
	 * FIND LIST
	 * @param consultCompIntfCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List findList(ConsultCompIntfCommonDTO consultCompIntfCommonDTO, User user) throws Exception;
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
	 * @param consultCompIntfCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(ConsultCompIntfCommonDTO consultCompIntfCommonDTO, User user) throws Exception;
}
