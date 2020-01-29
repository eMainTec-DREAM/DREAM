package dream.work.pm.list.dao;

import java.util.List;

import common.bean.User;
import dream.work.pm.list.dto.MaPmMstrSelectDTO;

/**
 * 작업 형태 선택팝업
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface MaPmMstrSelectDAO
{
    /**
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param loginUser
     * @return
     */
    public List findWoTypeList(User loginUser, MaPmMstrSelectDTO maPmMstrSelectDTO);
    
    
    public List findPmTypeList(User loginUser, MaPmMstrSelectDTO maPmMstrSelectDTO);
}