package dream.work.let.categ.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.work.let.categ.dto.LovWorkLetCategListDTO;

/**
 * 안전작업유형 팝업 DAO
 * @author  syyang
 * @version $Id:$
 * @since   1.0
 */
public interface LovWorkLetCategListDAO
{

	/**
	 * AC Find List
	 * @param lovWorkLetCategListDTO
	 * @param user
	 * @param conditionMap
	 * @return
	 */
	public List findWorkLetCategAcList(LovWorkLetCategListDTO lovWorkLetCategListDTO, User user, Map<String, String> conditionMap);

	/**
     * FIND TOTAL LIST
     * @param lovWorkLetCategListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(LovWorkLetCategListDTO lovWorkLetCategListDTO, User user, Map<String, String> conditionMap) throws Exception;
    
}