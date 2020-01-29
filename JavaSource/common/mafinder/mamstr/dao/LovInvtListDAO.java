package common.mafinder.mamstr.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.mafinder.mamstr.dto.LovInvtListDTO;

/**
 * 투자 목록 팝업
 * @author  js.lee
 * @version $Id: $
 * @since   1.0
 */
public interface LovInvtListDAO
{
    /**
     * 투자 목록 검색
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