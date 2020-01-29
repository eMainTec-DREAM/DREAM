package dream.org.vendor.service;

import java.util.List;

import common.bean.User;
import dream.org.vendor.dto.MaVendorCommonDTO;

/**
 * ���þ�ü - ��� service
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface MaVendorListService
{     
    /**
     *  grid find
     * @author  ssong
     * @version $Id: $
     * @param maVendorCommonDTO
     * @since   1.0
     * 
     * @return ��ȸ ��� 
     * @throws Exception
     */
    public List findList(MaVendorCommonDTO maVendorCommonDTO, User user);    
    public String findTotalCount(MaVendorCommonDTO maVendorCommonDTO, User user);
   
    /**
     * delete List
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param deleteRows
     * @return
     * @throws Exception
     */
    public int deleteList(String compNo, String[] deleteRows) throws Exception;
}
