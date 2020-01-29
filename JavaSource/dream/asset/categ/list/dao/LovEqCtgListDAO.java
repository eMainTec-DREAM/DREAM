package dream.asset.categ.list.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;

import dream.asset.categ.list.dto.LovEqCtgListDTO;

/**
 * ���������˻� �˾�
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface LovEqCtgListDAO
{
    /**
     * ��������
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovEqCtgListDTO
     * @param loginUser
     * @return
     */
    public List findEqCtgList(LovEqCtgListDTO lovEqCtgListDTO, User loginUser);

	public List findEqCtgAcList(LovEqCtgListDTO lovEqCtgListDTO, User user, Map<String, String> conditionMap);
}