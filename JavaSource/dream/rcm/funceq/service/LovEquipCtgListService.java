package dream.rcm.funceq.service;

import java.util.List;

import common.bean.User;
import dream.rcm.funceq.dto.LovEquipCtgListDTO;

/**
 * ���� Service
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public interface LovEquipCtgListService
{

    /**
     * ����˻�
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param LovEquipCtgListDTO
     * @param loginUser
     * @return
     */
    List findEquipList(LovEquipCtgListDTO lovEquipCtgListDTO, User loginUser);
}