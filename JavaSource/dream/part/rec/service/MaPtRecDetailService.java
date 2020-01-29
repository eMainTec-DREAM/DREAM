package dream.part.rec.service;

import common.bean.User;
import dream.part.rec.dto.MaPtRecDetailDTO;

/**
 * 구매입고 - 상세 service
 * 
 * @author ssong
 * @version $Id:$
 * @since 1.0
 */
public interface MaPtRecDetailService
{    
    /**
     * detail find
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param compNo
     * @param ptRecListId
     * @return
     * @throws Exception
     */
    public MaPtRecDetailDTO findDetail(User user, String ptRecListId)throws Exception;
   
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRecDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaPtRecDetailDTO maPtRecDetailDTO, User loginUser) throws Exception;
    
    /**
     * detail update
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRecDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaPtRecDetailDTO maPtRecDetailDTO, User user) throws Exception;
    
    /**
     * detail 상태 update
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRecDetailDTO
     * @return
     * @throws Exception
     */
    public int updatePtRecListStatus(MaPtRecDetailDTO maPtRecDetailDTO, User user) throws Exception;
    public int confirmPtRec(MaPtRecDetailDTO maPtRecDetailDTO, User user) throws Exception;
    public int confirmIsSerialPt(MaPtRecDetailDTO maPtRecDetailDTO, User user) throws Exception;
    public int makePtMstr(MaPtRecDetailDTO maPtRecDetailDTO, User user) throws Exception;
    /**
     * valid No
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRecDetailDTO
     * @return
     * @throws Exception
     */
    public String validPrRecListNo(MaPtRecDetailDTO maPtRecDetailDTO) throws Exception;
    
    /**
     * valid No
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRecDetailDTO
     * @return
     * @throws Exception
     */
    public String validSerialCount(MaPtRecDetailDTO maPtRecDetailDTO) throws Exception;
}
