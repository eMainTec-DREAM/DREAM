package dream.work.pm.list.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.work.pm.list.dto.LovWorkPmListUInsListDTO;

/**
 * ������ ���������ֱ� Lov DAO
 * @author  sy.yang
 * @version $Id:$
 * @since   1.0
 */
public interface LovWorkPmListUInsListDAO
{
    /**
     * �˻�
     * @author  sy.yang
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovWorkPmListUInsListDTO
     * @param loginUser
     * @return
     */
 
	public List findPmUInsListAcList(LovWorkPmListUInsListDTO lovWorkPmListUInsListDTO, User user, Map<String, String> conditionMap) throws Exception;
	public String findTotalCount(LovWorkPmListUInsListDTO lovWorkPmListUInsListDTO, User user, Map<String, String> conditionMap) throws Exception;
}