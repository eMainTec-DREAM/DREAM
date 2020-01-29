package dream.part.list.dao;

import common.bean.User;
import dream.part.list.dto.MaPtMstrCommonDTO;
import dream.part.list.dto.MaPtMstrVendorDetailDTO;

/**
 * 何前芭贰贸 惑技 dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface MaPtMstrVendorDetailDAO
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
    public MaPtMstrVendorDetailDTO findDetail(MaPtMstrCommonDTO maPtMstrCommonDTO, User loginUser);
    
    /**
     * detail update
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtMstrVendorDetailDTO
     * @return
     */
    public int updateVendor(MaPtMstrVendorDetailDTO maPtMstrVendorDetailDTO, User loginUser);
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtMstrVendorDetailDTO
     * @return
     */
    public int insertPtVendor(MaPtMstrVendorDetailDTO maPtMstrVendorDetailDTO, User loginUser);
}