package dream.mgr.usrrpt.dao;

import common.bean.User;
import dream.mgr.usrrpt.dto.MaUserRptCommonDTO;
import dream.mgr.usrrpt.dto.MaUserRptTableDetailDTO;

/**
 * »ó¼¼ dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface MaUserRptTableDetailDAO
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
    public MaUserRptTableDetailDTO findDetail(MaUserRptCommonDTO maPtMstrCommonDTO, User loginUser);
    
    /**
     * detail update
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maUserRptTableDetailDTO
     * @param loginUser
     * @return
     */
    public int updateDetail(MaUserRptTableDetailDTO maUserRptTableDetailDTO, User loginUser);
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maUserRptTableDetailDTO
     * @param loginUser
     * @return
     */
    public int insertDetail(MaUserRptTableDetailDTO maUserRptTableDetailDTO, User loginUser);

	public int checkNextNum(String string);
}