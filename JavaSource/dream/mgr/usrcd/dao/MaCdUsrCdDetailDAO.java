package dream.mgr.usrcd.dao;

import common.bean.User;
import dream.mgr.usrcd.dto.MaCdUsrCdDetailDTO;
import dream.mgr.usrcd.dto.MaCdUsrCommonDTO;

/**
 * 유형별 세부코드 dao
 * @author   
 * @version $Id: $
 * @since   1.0
 */
public interface MaCdUsrCdDetailDAO
{
    /**
     * detail find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param cdUsrdId
     * @return
     */
    public MaCdUsrCdDetailDTO findDetail(MaCdUsrCommonDTO maCdUsrCommonDTO,User user);

    /**
     * detail insert
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maCdUsrCdDetailDTO
     * @return
     */
    public int insertDetail(MaCdUsrCdDetailDTO maCdUsrCdDetailDTO, User user);
    
    public int updateFullDesc(MaCdUsrCdDetailDTO maCdUsrCdDetailDTO, User user);
    
    public int updateDirType(MaCdUsrCdDetailDTO maCdUsrCdDetailDTO, User user);

    /**
     * detail update
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maCdUsrCdDetailDTO
     * @return
     */
    public int updateDetail(MaCdUsrCdDetailDTO maCdUsrCdDetailDTO, User user);
    
    /**
     * valid code
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maCdUsrCdDetailDTO
     * @return
     */
    public String validCode(MaCdUsrCdDetailDTO maCdUsrCdDetailDTO, User user);
}