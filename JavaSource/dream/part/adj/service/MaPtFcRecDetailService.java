package dream.part.adj.service;

import common.bean.User;
import dream.part.adj.dto.MaPtFcRecDetailDTO;

/**
 * 무상입고 - 상세 service
 * 
 * @author ssong
 * @version $Id:$
 * @since 1.0
 */
public interface MaPtFcRecDetailService
{    
    /**
     * detail find
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param compNo
     * @param ptFcRecListId
     * @return
     * @throws Exception
     */
    public MaPtFcRecDetailDTO findDetail(User user, String ptFcRecListId)throws Exception;
   
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtFcRecDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaPtFcRecDetailDTO maPtFcRecDetailDTO) throws Exception;
    
    /**
     * detail update
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtFcRecDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaPtFcRecDetailDTO maPtFcRecDetailDTO) throws Exception;
    
    /**
     * detail 상태 update
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtFcRecDetailDTO
     * @return
     * @throws Exception
     */
    public int updatePtFcRecListStatus(MaPtFcRecDetailDTO maPtFcRecDetailDTO) throws Exception;
    
    /**
     * valid No
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtFcRecDetailDTO
     * @return
     * @throws Exception
     */
    public String validFcRecListNo(MaPtFcRecDetailDTO maPtFccDetailDTO) throws Exception;
}
