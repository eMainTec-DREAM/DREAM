package dream.asset.loc.list.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.asset.loc.list.dto.LovMesLineListDTO;

/**
 * MESLINE�˻� �˾�
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface LovMesLineListDAO
{
    /**
     * MESLINE �˻�
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