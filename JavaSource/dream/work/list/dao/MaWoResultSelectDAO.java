package dream.work.list.dao;

import java.util.List;

import common.bean.User;
import dream.work.list.dto.MaWoResultSelectDTO;

/**
 * �۾� ����, ���� �����˾�
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface MaWoResultSelectDAO
{
    /**
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param loginUser
     * @return
     */
    public List findWoTypeList(User loginUser, MaWoResultSelectDTO maWoResultSelectDTO);

    /**
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param loginUser
     * @return
     */
    public List findTypeList(User loginUser, MaWoResultSelectDTO maWoResultSelectDTO);
}