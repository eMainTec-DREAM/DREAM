package dream.org.vendor.dao;

import common.bean.User;
import dream.org.vendor.dto.MaVendorDetailDTO;

/**
 * 관련업체 - 상세 dao
 * 
 * @author 
 * @version $Id:  $
 * @since 1.0
 */
public interface MaVendorDetailDAO
{
    /**
     * detail find
     * @author 
     * @version $Id: $
     * @param user 
     * @since   1.0
     * 
     * @param deptNo
     * @return
     */
    public MaVendorDetailDTO findDetail(String compNo, String vendorId, User user);
    
    /**
     * detail insert
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maVendorDetailDTO
     * @return
     */
    public int insertDetail(MaVendorDetailDTO maVendorDetailDTO);
    
    /**
     * detail update
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maVendorDetailDTO
     * @return
     */
    public int updateDetail(MaVendorDetailDTO  maVendorDetailDTO);
    
    /**
     * valid check
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maVendorDetailDTO
     * @return
     */
    public String validVendorNo(MaVendorDetailDTO maVendorDetailDTO);
}