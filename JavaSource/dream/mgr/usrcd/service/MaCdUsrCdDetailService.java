package dream.mgr.usrcd.service;

import common.bean.User;
import dream.mgr.usrcd.dto.MaCdUsrCdDetailDTO;
import dream.mgr.usrcd.dto.MaCdUsrCommonDTO;

/**
 * 유형별 세부코드 LOV
 * @author  
 * @version $Id:  $
 * @since   1.0
 */
public interface MaCdUsrCdDetailService
{    
    /**
     * detail find
     * @author  
     * @version $Id:$
     * @since   1.0
     * 
     * @param compNo
     * @param cdUsrdId
     * @return
     * @throws Exception
     */
    public MaCdUsrCdDetailDTO findDetail(MaCdUsrCommonDTO maCdUsrCommonDTO, User user)throws Exception;
    
    /**
     * detail insert
     * @author  
     * @version $Id:$
     * @since   1.0
     * 
     * @param maCdUsrCdDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaCdUsrCdDetailDTO maCdUsrCdDetailDTO, User user) throws Exception;
    
    /**
     * detail update
     * @author  
     * @version $Id:$
     * @since   1.0
     * 
     * @param maCdUsrCdDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaCdUsrCdDetailDTO maCdUsrCdDetailDTO, User user) throws Exception;
    
    /**
     * valid code
     * @author  
     * @version $Id:$
     * @since   1.0
     * 
     * @param maCdUsrCdDetailDTO
     * @return
     * @throws Exception
     */
    public String validCode(MaCdUsrCdDetailDTO maCdUsrCdDetailDTO, User user) throws Exception;
}
