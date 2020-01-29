package dream.part.list.service;

import common.bean.User;
import dream.part.list.dto.MaPtMstrCommonDTO;
import dream.part.list.dto.MaPtMstrUsedDeptDetailDTO;

/**
 * 何前荤侩何辑 惑技
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface MaPtMstrUsedDeptDetailService
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
     * @throws Exception
     */
    public MaPtMstrUsedDeptDetailDTO findDetail(MaPtMstrCommonDTO maPtMstrCommonDTO, User loginUser)throws Exception;
    
    /**
     * detail update
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtMstrUsedDeptDetailDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int updateDetail(MaPtMstrUsedDeptDetailDTO maPtMstrUsedDeptDetailDTO, User loginUser) throws Exception;
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtMstrUsedDeptDetailDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int insertDetail(MaPtMstrUsedDeptDetailDTO maPtMstrUsedDeptDetailDTO, User loginUser) throws Exception;
}
