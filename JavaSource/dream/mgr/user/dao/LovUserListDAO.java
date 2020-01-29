package dream.mgr.user.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.mgr.user.dto.LovUserListDTO;

/**
 * 사용자 팝업
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface LovUserListDAO
{
    /**
     * 검색
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovUserListDTO
     * @param loginUser
     * @return
     */
    public List findUserList(LovUserListDTO lovUserListDTO, User loginUser);

	public List findUserAcList(LovUserListDTO lovUserListDTO, User user, Map<String, String> conditionMap);
}