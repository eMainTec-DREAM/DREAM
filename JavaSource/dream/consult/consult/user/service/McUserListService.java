package dream.consult.consult.user.service;

import java.util.List;

import common.bean.User;
import dream.consult.consult.user.dto.McUserCommonDTO;


/**
 * ����� - ��� service
 * @author ssong 
 * @version $Id:  $
 * @since   1.0
 */
public interface McUserListService
{     
    /**
     *  grid find
     * @author  ssong
     * @version $Id:  $
     * @since   1.0
     * 
     * @param mcUserCommonDTO
     * @param loginUser
     * @return ��ȸ ��� 
     * @throws Exception
     */
    public List findUserList(McUserCommonDTO mcUserCommonDTO, User loginUser);    
    
    /**
     * delete List
     * @author ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param deleteRows
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int deleteList(String[] deleteRows, User loginUser) throws Exception;
    
    
    public void resetPassword(McUserCommonDTO mcUserCommonDTO, User loginUser);
}
