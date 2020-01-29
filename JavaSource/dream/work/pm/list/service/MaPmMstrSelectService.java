package dream.work.pm.list.service;

import java.util.List;

import common.bean.User;
import dream.work.pm.list.dto.MaPmMstrSelectDTO;

/**
 * 작업형태 팝업 Service
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public interface MaPmMstrSelectService
{

    /**
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param loginUser
     * @return
     */
    List findWoTypeList(User loginUser, MaPmMstrSelectDTO maPmMstrSelectDTO);
    
    List findPmTypeList(User loginUser, MaPmMstrSelectDTO maPmMstrSelectDTO);
    
}