package dream.mgr.usrrpt.service;

import java.util.List;

import common.bean.User;
import dream.mgr.usrrpt.dto.MaUserRptCommonDTO;

/**
 *  ¸ñ·Ï
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface MaUserRptJoinListService
{     
    /**
     *  grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtMstrCommonDTO
     * @param loginUser
     * @throws Exception
     */
    public List findList(MaUserRptCommonDTO maUserRptCommonDTO, User loginUser);
    
    /**
     *  delete
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param deleteRows
     * @param loginUser
     * @throws Exception
     */
    public int deleteList(String[] deleteRows, User loginUser) throws Exception;

}
