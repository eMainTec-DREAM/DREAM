package common.mafinder.mamstr.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.mafinder.mamstr.dto.LovInvtListDTO;

/**
 * ���� ��� �˾�
 * @author  js.lee
 * @version $Id: $
 * @since   1.0
 */
public interface LovInvtListDAO
{
    /**
     * ���� ��� �˻�
     * @author  js.lee
     * @version $Id: $
     * @since   1.0
     * 
     * @param lovInvtListDTO
     * @param loginUser
     * @return
     */
    public List findInvtList(LovInvtListDTO lovInvtListDTO, User loginUser, Map<String, String> conditionMap);

}