package dream.part.list.dao;

import common.bean.User;
import dream.part.list.dto.MaPtMstrCommonDTO;
import dream.part.list.dto.MaPtMstrEqPartDetailDTO;

/**
 * 사용설비 상세 dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface MaPtMstrEqPartDetailDAO
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
    public MaPtMstrEqPartDetailDTO findDetail(MaPtMstrCommonDTO maPtMstrCommonDTO, User loginUser);
    
    /**
     * detail update
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtMstrEqPartDetailDTO
     * @param loginUser
     * @return
     */
    public int updateDetail(MaPtMstrEqPartDetailDTO maPtMstrEqPartDetailDTO, User loginUser);
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtMstrEqPartDetailDTO
     * @param loginUser
     * @return
     */
    public int insertDetail(MaPtMstrEqPartDetailDTO maPtMstrEqPartDetailDTO, User loginUser);
}