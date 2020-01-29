package dream.part.list.service;

import common.bean.User;
import dream.part.list.dto.MaPtMstrCommonDTO;
import dream.part.list.dto.MaPtMstrVendorDetailDTO;

/**
 * 何前芭贰贸 惑技
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface MaPtMstrVendorDetailService
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
    public MaPtMstrVendorDetailDTO findDetail(MaPtMstrCommonDTO maPtMstrCommonDTO, User loginUser)throws Exception;
    
    /**
     * detail update
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtMstrVendorDetailDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int updateDetail(MaPtMstrVendorDetailDTO maPtMstrVendorDetailDTO, User loginUser) throws Exception;
   
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtMstrVendorDetailDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int insertDetail(MaPtMstrVendorDetailDTO maPtMstrVendorDetailDTO, User loginUser) throws Exception;
}
