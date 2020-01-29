package dream.work.list.dao;

import java.util.List;

import common.bean.User;
import dream.work.list.dto.MaWoResultSelectDTO;

/**
 * 작업 형태, 종류 선택팝업
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