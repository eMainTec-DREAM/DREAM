package dream.mgr.usrrpt.dao;

import common.bean.User;
import dream.mgr.usrrpt.dto.MaUserRptCommonDTO;
import dream.mgr.usrrpt.dto.MaUserRptJoinDetailDTO;

/**
 * »ó¼¼ dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface MaUserRptJoinDetailDAO
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
    public MaUserRptJoinDetailDTO findDetail(MaUserRptCommonDTO maPtMstrCommonDTO, User loginUser);
    
    /**
     * detail update
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maUserRptJoinDetailDTO
     * @param loginUser
     * @return
     */
    public int updateDetail(MaUserRptJoinDetailDTO maUserRptJoinDetailDTO, User loginUser);
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maUserRptJoinDetailDTO
     * @param loginUser
     * @return
     */
    public int insertDetail(MaUserRptJoinDetailDTO maUserRptJoinDetailDTO, User loginUser);

	public int checkNextNum(MaUserRptJoinDetailDTO maUserRptJoinDetailDTO);
}