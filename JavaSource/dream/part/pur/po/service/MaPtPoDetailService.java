package dream.part.pur.po.service;

import common.bean.User;
import dream.part.pur.po.dto.MaPtPoDetailDTO;

/**
 * 발주이력 - 상세 service
 * 
 * @author kim21017
 * @version $Id:$
 * @since 1.0
 */
public interface MaPtPoDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param compNo
     * @param ptPoListId
     * @return
     * @throws Exception
     */
    public MaPtPoDetailDTO findDetail(String ptPoListId, User user)throws Exception;
   
    /**
     * detail insert
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtPoDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaPtPoDetailDTO maPtPoDetailDTO, User user) throws Exception;
    
    /**
     * detail update
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtPoDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaPtPoDetailDTO maPtPoDetailDTO, User user) throws Exception;
    
    /**
     * detail 상태 update
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtPoDetailDTO
     * @return
     * @throws Exception
     */
    public int updatePtPoListStatus(MaPtPoDetailDTO maPtPoDetailDTO, User user) throws Exception;

    public MaPtPoDetailDTO getStatus(MaPtPoDetailDTO maPtPoDetailDTO, User user) throws Exception;
}
