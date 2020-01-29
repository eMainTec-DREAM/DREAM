package dream.org.vendor.dao;

import java.util.List;

import common.bean.User;
import dream.org.vendor.dto.MaVendorCommonDTO;

/**
 * ���þ�ü - ��� dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface MaVendorListDAO
{
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maVendorCommonDTO
     * @return List
     */
    public List findList(MaVendorCommonDTO maVendorCommonDTO, User user);
    public String findTotalCount(MaVendorCommonDTO maVendorCommonDTO, User user);
    
    /**
     * ����
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param vendorId
     * @return
     */
    public int deleteParts(String compNo, String vendorId);
}