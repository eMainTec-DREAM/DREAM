package dream.rcm.funceq.dao;

import java.util.List;

import common.bean.User;
import dream.rcm.funceq.dto.LovEquipCtgListDTO;

/**
 * ���� �˾�
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface LovEquipCtgListDAO
{
    /**
     * ���� �˻�
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovEquipCtgListDTO
     * @param loginUser
     * @return
     */
    public List findEquipList(LovEquipCtgListDTO lovEquipCtgListDTO, User loginUser);
}