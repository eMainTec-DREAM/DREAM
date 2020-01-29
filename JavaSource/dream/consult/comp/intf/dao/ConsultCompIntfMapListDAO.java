package dream.consult.comp.intf.dao;

import java.util.List;

import common.bean.User;
import dream.consult.comp.intf.dto.ConsultCompIntfCommonDTO;
import dream.consult.comp.intf.dto.ConsultCompIntfMapListDTO;

/**
 * Interface Log Page - List DAO
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 *
 */
public interface ConsultCompIntfMapListDAO
{
	/**
	 * FIND LIST
	 * @param consultCompIntfMapListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public List findList(ConsultCompIntfCommonDTO consultCompIntfCommonDTO, ConsultCompIntfMapListDTO consultCompIntfMapListDTO, User user) throws Exception;
    
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
	 * @param consultCompIntfMapListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(ConsultCompIntfCommonDTO consultCompIntfCommonDTO, ConsultCompIntfMapListDTO consultCompIntfMapListDTO, User user) throws Exception;
    
}