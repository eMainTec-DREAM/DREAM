package dream.mgr.usrrpt.dao;

import common.bean.User;
import dream.mgr.usrrpt.dto.MaUserRptColDetailDTO;
import dream.mgr.usrrpt.dto.MaUserRptCommonDTO;

/**
 * »ó¼¼ dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface MaUserRptColDetailDAO
{
    /**
     * detail find
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtMstrCommonDTO
     * @param loginUser
     * @return
     */
    public MaUserRptColDetailDTO findDetail(MaUserRptCommonDTO maPtMstrCommonDTO, User loginUser);
    
    /**
     * detail update
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maUserRptColDetailDTO
     * @param loginUser
     * @return
     */
    public int updateDetail(MaUserRptColDetailDTO maUserRptColDetailDTO, User loginUser);
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maUserRptColDetailDTO
     * @param loginUser
     * @return
     */
    public int insertDetail(MaUserRptColDetailDTO maUserRptColDetailDTO, User loginUser);

	public int checkNextNum(String usrrptlistId);

}