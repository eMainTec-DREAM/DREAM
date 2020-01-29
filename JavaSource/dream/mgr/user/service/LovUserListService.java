package dream.mgr.user.service;

import java.util.List;

import common.bean.User;
import dream.mgr.user.dto.LovUserListDTO;
import dream.mgr.user.form.LovUserListForm;

/**
 * 사용자 팝업 Service
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public interface LovUserListService
{

    /**
     * 검색
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param LovUserListDTO
     * @param loginUser
     * @return
     */
    List findUserList(LovUserListDTO lovUserListDTO, User loginUser);

	/**
	 * AC Find List
	 * @param lovUserListDTO
	 * @param user
	 * @param lovUserListForm
	 * @return
	 */
	List findUserList(LovUserListDTO lovUserListDTO, User user, LovUserListForm lovUserListForm);
}