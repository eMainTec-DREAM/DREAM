package dream.mgr.usrrpt.dao;

import common.bean.User;
import dream.mgr.usrrpt.dto.MaUserRptCommonDTO;
import dream.mgr.usrrpt.dto.MaUserRptOrdDetailDTO;

/**
 * »ó¼¼ dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface MaUserRptOrdDetailDAO
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
    public MaUserRptOrdDetailDTO findDetail(MaUserRptCommonDTO maPtMstrCommonDTO, User loginUser);
    
    /**
     * detail update
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maUserRptOrdDetailDTO
     * @param loginUser
     * @return
     */
    public int updateDetail(MaUserRptOrdDetailDTO maUserRptOrdDetailDTO, User loginUser);
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maUserRptOrdDetailDTO
     * @param loginUser
     * @return
     */
    public int insertDetail(MaUserRptOrdDetailDTO maUserRptOrdDetailDTO, User loginUser);

	public int checkNextNum(String usrrptlistId);

}