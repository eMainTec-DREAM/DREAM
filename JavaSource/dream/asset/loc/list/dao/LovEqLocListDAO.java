package dream.asset.loc.list.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.asset.loc.list.dto.LovEqLocListDTO;

/**
 * ��ġ�з��˻� �˾�
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface LovEqLocListDAO
{
    /**
     * ��ġ�з�
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovEqLocListDTO
     * @return
     */
    public List findEqLocList(LovEqLocListDTO lovEqLocListDTO, User loginUser);
    /**
     * ��ġ�з� AC
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovEqLocListDTO
     * @param loginUser
     * @param columnMap
     * @param conditionMap
     * @return List
     */
    public List findEqLocAcList(LovEqLocListDTO lovEqLocListDTO, User loginUser, Map<String, String> columnMap, Map<String, String> conditionMap);
}