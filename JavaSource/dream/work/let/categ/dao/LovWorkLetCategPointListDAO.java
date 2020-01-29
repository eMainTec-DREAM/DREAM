package dream.work.let.categ.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.work.let.categ.dto.LovWorkLetCategPointListDTO;

/**
 * 안전작업허가서 표준점검항목 Lov DAO
 * @author  syyang
 * @version $Id:$
 * @since   1.0
 */
public interface LovWorkLetCategPointListDAO
{

	/**
	 * AC Find List
	 * @param lovWorkLetCategPointListDTO
	 * @param user
	 * @param conditionMap
	 * @return
	 */
	public List findWorkLetCategPointAcList(LovWorkLetCategPointListDTO lovWorkLetCategPointListDTO, User user, Map<String, String> conditionMap);

	/**
     * FIND TOTAL LIST
     * @param lovWorkLetCategPointListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(LovWorkLetCategPointListDTO lovWorkLetCategPointListDTO, User user, Map<String, String> conditionMap) throws Exception;
    
}