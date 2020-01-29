package dream.part.rep.service;

import common.bean.User;
import dream.part.rep.dto.MaPtRepCommonDTO;
import dream.part.rep.dto.MaPtRepDetailDTO;

/**
 * 부품수리 - 상세 service
 * 
 * @author ssong
 * @version $Id:$
 * @since 1.0
 */
public interface MaPtRepDetailService
{    
    /**
     * detail find
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRepCommonDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public MaPtRepDetailDTO findDetail(MaPtRepCommonDTO maPtRepCommonDTO, User loginUser)throws Exception;
   
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRepDetailDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int insertDetail(MaPtRepDetailDTO maPtRepDetailDTO, User loginUser) throws Exception;
    
    /**
     * detail update
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRepDetailDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int updateDetail(MaPtRepDetailDTO maPtRepDetailDTO, User loginUser) throws Exception;
    
    /**
     * detail 상태 update
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRepDetailDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int updatePtRepairListStatus(MaPtRepDetailDTO maPtRepDetailDTO, User loginUser) throws Exception;
    
    /**
     * detail 상태 update 및 수리취소
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRepDetailDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int updatePtRepairListStatusCancel(MaPtRepDetailDTO maPtRepDetailDTO, User loginUser) throws Exception;
    
    /**
     * valid No
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRepDetailDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public String validPtRepairListNo(MaPtRepDetailDTO maPtRepDetailDTO, User loginUser) throws Exception;
}
