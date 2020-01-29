package dream.org.emp.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.org.emp.dto.LovEmpListDTO;

/**
 * 사원검색 팝업
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface LovEmpListDAO
{
    /**
     * 검색
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovEmpListDTO
     * @param loginUser
     * @return
     */
    public List findEmpList(LovEmpListDTO lovEmpListDTO, User loginUser);

	/**
	 * AC Find List
	 * @param lovEmpListDTO
	 * @param user
	 * @param conditionMap
	 * @return
	 */
	public List findEmpAcList(LovEmpListDTO lovEmpListDTO, User user, Map<String, String> conditionMap);

	public String findTotalCount(LovEmpListDTO lovEmpListDTO, User user, Map<String, String> columnMap, Map<String, String> conditionMap);
}