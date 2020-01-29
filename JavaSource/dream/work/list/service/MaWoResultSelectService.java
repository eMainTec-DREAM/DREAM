package dream.work.list.service;

import java.util.List;

import common.bean.User;
import dream.work.list.dto.MaWoResultSelectDTO;

/**
 * 작업종류/형태 팝업 Service
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public interface MaWoResultSelectService
{

    /**
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param loginUser
     * @return
     */
    List findWoTypeList(User loginUser, MaWoResultSelectDTO maWoResultSelectDTO);
    /**
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param loginUser
     * @return
     */
    List findTypeList(User loginUser, MaWoResultSelectDTO maWoResultSelectDTO);
}