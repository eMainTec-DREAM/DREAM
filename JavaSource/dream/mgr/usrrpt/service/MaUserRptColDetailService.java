package dream.mgr.usrrpt.service;

import common.bean.User;
import dream.mgr.usrrpt.dto.MaUserRptColDetailDTO;
import dream.mgr.usrrpt.dto.MaUserRptCommonDTO;

/**
 * »ó¼¼
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface MaUserRptColDetailService
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
    public MaUserRptColDetailDTO findDetail(MaUserRptCommonDTO maPtMstrCommonDTO, User loginUser)throws Exception;
    
    /**
     * detail update
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maUserRptColDetailDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int updateDetail(MaUserRptColDetailDTO maUserRptColDetailDTO, User loginUser) throws Exception;
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maUserRptColDetailDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int insertDetail(MaUserRptColDetailDTO maUserRptColDetailDTO, User loginUser) throws Exception;

	public int checkNextNum(String usrrptlistId);
}
