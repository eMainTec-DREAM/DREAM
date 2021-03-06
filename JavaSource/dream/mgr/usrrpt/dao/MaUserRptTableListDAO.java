package dream.mgr.usrrpt.dao;

import java.util.List;

import common.bean.User;
import dream.mgr.usrrpt.dto.MaUserRptCommonDTO;

/**
 * ��� dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface MaUserRptTableListDAO
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

	public int deleteJoinList(String id, User loginUser);

	public int deleteColList(String id, User loginUser);

	public int deleteParamList(String id, User loginUser);

	public int deleteOrdList(String id, User loginUser);
}