package dream.mgr.usrrpt.service;

import common.bean.User;
import dream.mgr.usrrpt.dto.MaUserRptCommonDTO;
import dream.mgr.usrrpt.dto.MaUserRptJoinDetailDTO;

/**
 * »ó¼¼
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface MaUserRptJoinDetailService
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
     * @throws Exception
     */
    public MaUserRptJoinDetailDTO findDetail(MaUserRptCommonDTO maPtMstrCommonDTO, User loginUser)throws Exception;
    
    /**
     * detail update
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maUserRptJoinDetailDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int updateDetail(MaUserRptJoinDetailDTO maUserRptJoinDetailDTO, User loginUser) throws Exception;
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maUserRptJoinDetailDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int insertDetail(MaUserRptJoinDetailDTO maUserRptJoinDetailDTO, User loginUser) throws Exception;
}
