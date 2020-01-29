package dream.tool.rec.service;

import common.bean.User;
import dream.tool.rec.dto.MaPttRecDetailDTO;

/**
 * 구매입고 - 상세 service
 * 
 * @author ssong
 * @version $Id:$
 * @since 1.0
 */
public interface MaPttRecDetailService
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
    public MaPttRecDetailDTO findDetail(User user, String ptRecListId)throws Exception;
   
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttRecDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaPttRecDetailDTO maPttRecDetailDTO) throws Exception;
    
    /**
     * detail update
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttRecDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaPttRecDetailDTO maPttRecDetailDTO) throws Exception;
    
    /**
     * detail 상태 update
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttRecDetailDTO
     * @return
     * @throws Exception
     */
    public int updatePtRecListStatus(MaPttRecDetailDTO maPttRecDetailDTO) throws Exception;
    
    /**
     * valid No
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttRecDetailDTO
     * @return
     * @throws Exception
     */
    public String validPrRecListNo(MaPttRecDetailDTO maPttRecDetailDTO) throws Exception;
}
