package dream.part.rep.service;

import java.util.List;

import common.bean.User;
import dream.part.rep.dto.LovPtAppListDTO;

/**
 * �������ǰ�Ǽ� �˾� Service
 * @author  ssong
 * @version $Id: $
 * @since   1.0
 *
 */
public interface LovPtAppListService
{

    /**
     * �������ǰ�Ǽ� �˻�
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param LovPtAppListDTO
     * @param loginUser
     * @return
     */
    List findPtAppList(LovPtAppListDTO lovPtAppListDTO, User loginUser);
}