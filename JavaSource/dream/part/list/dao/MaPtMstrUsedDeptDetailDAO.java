package dream.part.list.dao;

import common.bean.User;
import dream.part.list.dto.MaPtMstrCommonDTO;
import dream.part.list.dto.MaPtMstrUsedDeptDetailDTO;

/**
 * 何前荤侩何辑 惑技 dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface MaPtMstrUsedDeptDetailDAO
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
    public MaPtMstrUsedDeptDetailDTO findDetail(MaPtMstrCommonDTO maPtMstrCommonDTO, User loginUser);
    
    /**
     * detail update
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtMstrUsedDeptDetailDTO
     * @param loginUser
     * @return
     */
    public int updateDetail(MaPtMstrUsedDeptDetailDTO maPtMstrUsedDeptDetailDTO, User loginUser);
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtMstrUsedDeptDetailDTO
     * @param loginUser
     * @return
     */
    public int insertDetail(MaPtMstrUsedDeptDetailDTO maPtMstrUsedDeptDetailDTO, User loginUser);
}