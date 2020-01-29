package dream.part.pur.po.dao;

import common.bean.User;
import dream.part.pur.po.dto.MaPtPoDetailDTO;

/**
 * 발주이력 - 상세 dao
 * 
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 */
public interface MaPtPoDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param compNo
     * @param poListId
     * @return
     */
    public MaPtPoDetailDTO findDetail(String poListId, User user);
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtPoDetailDTO
     * @return
     */
    public int insertDetail(MaPtPoDetailDTO maPtPoDetailDTO, User user);
    
    /**
     * detail
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRecDetailDTO
     * @return
     */
    public int updateDetail(MaPtPoDetailDTO maPtPoDetailDTO, User user);
    
    /**
     * detail 상태 Update
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRecDetailDTO
     * @return
     */
    public int updatePtPoListStatus(MaPtPoDetailDTO maPtRecDetailDTO, User user);
}