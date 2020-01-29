package dream.org.vendor.service;

import common.bean.User;
import dream.org.vendor.dto.MaVendorDetailDTO;

/**
 * 관련업체 - 상세 service
 * 
 * @author 
 * @version $Id:$
 * @since 1.0
 */
public interface MaVendorDetailService
{    
    /**
     * detail find
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param deptNo
     * @param user 
     * @return
     * @throws Exception
     */
    public MaVendorDetailDTO findDetail(String compNo, String deptNo, User user)throws Exception;
    
    /**
     * detail insert
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param maVendorDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaVendorDetailDTO maVendorDetailDTO) throws Exception;
    
    /**
     * detail update
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param maVendorDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaVendorDetailDTO maVendorDetailDTO) throws Exception;
    
    /**
     * valid No
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param maVendorDetailDTO
     * @return
     * @throws Exception
     */
    public String validVendorNo(MaVendorDetailDTO maVendorDetailDTO) throws Exception;
}
