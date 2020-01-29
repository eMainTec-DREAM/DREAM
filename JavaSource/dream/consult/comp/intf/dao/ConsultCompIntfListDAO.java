package dream.consult.comp.intf.dao;

import java.util.List;

import common.bean.User;
import dream.consult.comp.intf.dto.ConsultCompIntfCommonDTO;

/**
 * Interface Page - List DAO
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 *
 */
public interface ConsultCompIntfListDAO
{
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
     * @param id
     * @param user
     * @return
     * @throws Exception
     */
    public int deleteList(String id, User user) throws Exception;
    /**
	 * FIND LIST COUNT
	 * @param consultCompIntfCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(ConsultCompIntfCommonDTO consultCompIntfCommonDTO, User user) throws Exception;
    
}