package dream.mgr.usrrpt.service;

import common.bean.User;
import dream.mgr.usrrpt.dto.MaUserRptCommonDTO;
import dream.mgr.usrrpt.dto.MaUserRptTableDetailDTO;

/**
 * »ó¼¼
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface MaUserRptTableDetailService
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
    public MaUserRptTableDetailDTO findDetail(MaUserRptCommonDTO maPtMstrCommonDTO, User loginUser)throws Exception;
    
    /**
     * detail update
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maUserRptTableDetailDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int updateDetail(MaUserRptTableDetailDTO maUserRptTableDetailDTO, User loginUser) throws Exception;
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maUserRptTableDetailDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int insertDetail(MaUserRptTableDetailDTO maUserRptTableDetailDTO, User loginUser) throws Exception;
    
    public int checkNextNum(String string);
}
