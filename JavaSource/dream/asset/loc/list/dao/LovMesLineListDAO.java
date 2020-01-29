package dream.asset.loc.list.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.asset.loc.list.dto.LovMesLineListDTO;

/**
 * MESLINE검색 팝업
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface LovMesLineListDAO
{
    /**
     * MESLINE 검색
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovMesLineListDTO
     * @return
     */
    public List findMesLineList(LovMesLineListDTO lovMesLineListDTO, User loginUser);

    public List findMesLineACList(LovMesLineListDTO lovMesLineListDTO, User user, Map<String, String> conditionMap);
}