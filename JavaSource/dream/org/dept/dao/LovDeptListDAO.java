package dream.org.dept.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.org.dept.dto.LovDeptListDTO;

/**
 * 부서검색 팝업
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface LovDeptListDAO
{
    /**
     * 자재 검색
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovDeptListDTO
     * @param loginUser
     * @return
     */
    public List findDeptList(LovDeptListDTO lovDeptListDTO, User loginUser);

	public List findDeptAcList(LovDeptListDTO lovDeptListDTO, User user, Map<String, String> columnMap, Map<String, String> conditionMap);
}