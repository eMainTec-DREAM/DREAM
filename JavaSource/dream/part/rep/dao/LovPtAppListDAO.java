package dream.part.rep.dao;

import java.util.List;

import common.bean.User;
import dream.part.rep.dto.LovPtAppListDTO;

/**
 * �������ǰ�Ǽ� �˾�
 * @author  ssong
 * @version $Id: $
 * @since   1.0
 */
public interface LovPtAppListDAO
{
    /**
     * �������ǰ�Ǽ� �˻�
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param lovPtAppListDTO
     * @param loginUser
     * @return
     */
    public List findPtAppList(LovPtAppListDTO lovPtAppListDTO, User loginUser);
}