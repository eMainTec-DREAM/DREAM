package dream.mgr.usrrpt.dao;

import common.bean.User;
import dream.mgr.usrrpt.dto.MaUserRptCommonDTO;
import dream.mgr.usrrpt.dto.MaUserRptParamDetailDTO;

/**
 * »ó¼¼ dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface MaUserRptParamDetailDAO
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
    public MaUserRptParamDetailDTO findDetail(MaUserRptCommonDTO maPtMstrCommonDTO, User loginUser);
    
    /**
     * detail update
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maUserRptParamDetailDTO
     * @param loginUser
     * @return
     */
    public int updateDetail(MaUserRptParamDetailDTO maUserRptParamDetailDTO, User loginUser);
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maUserRptParamDetailDTO
     * @param loginUser
     * @return
     */
    public int insertDetail(MaUserRptParamDetailDTO maUserRptParamDetailDTO, User loginUser);

	public int checkNextNum(String usrrptlistId);

}