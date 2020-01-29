package dream.mgr.usrrpt.service;

import common.bean.User;
import dream.mgr.usrrpt.dto.MaUserRptCommonDTO;
import dream.mgr.usrrpt.dto.MaUserRptOrdDetailDTO;

/**
 * »ó¼¼
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface MaUserRptOrdDetailService
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
    public MaUserRptOrdDetailDTO findDetail(MaUserRptCommonDTO maPtMstrCommonDTO, User loginUser)throws Exception;
    
    /**
     * detail update
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maUserRptOrdDetailDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int updateDetail(MaUserRptOrdDetailDTO maUserRptOrdDetailDTO, User loginUser) throws Exception;
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maUserRptOrdDetailDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int insertDetail(MaUserRptOrdDetailDTO maUserRptOrdDetailDTO, User loginUser) throws Exception;

	public int checkNextNum(String usrrptlistId);
}
