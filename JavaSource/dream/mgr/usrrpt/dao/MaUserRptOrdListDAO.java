package dream.mgr.usrrpt.dao;

import java.util.List;

import common.bean.User;
import dream.mgr.usrrpt.dto.MaUserRptCommonDTO;

/**
 * ¸ñ·Ï dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface MaUserRptOrdListDAO
{
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtMstrCommonDTO
     * @param loginUser
     * @return List
     */
    public List findList(MaUserRptCommonDTO maUserRptCommonDTO, User loginUser);
    
    /**
     * delete
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param eqPartId
     * @param loginUser
     * @return
     */
    public int deleteList(String eqPartId, User loginUser);
}