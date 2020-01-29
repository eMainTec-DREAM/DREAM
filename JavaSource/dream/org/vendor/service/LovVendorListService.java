package dream.org.vendor.service;

import java.util.List;

import common.bean.User;
import dream.org.vendor.dto.LovVendorListDTO;
import dream.org.vendor.form.LovVendorListForm;

/**
 * �ŷ�ó�˾� Service
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public interface LovVendorListService
{

    /**
     * �ŷ�ó�˻�
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param LovVendorListDTO
     * @param loginUser
     * @return
     */
    List findVendorList(LovVendorListDTO lovVendorListDTO, User loginUser);

	/**
	 * Find Ac Vendor
	 * @param lovVendorListDTO
	 * @param user
	 * @param lovVendorListForm
	 * @return
	 */
	List findVendorAcList(LovVendorListDTO lovVendorListDTO, User user, LovVendorListForm lovVendorListForm);
    public String findTotalCount(LovVendorListForm lovVendorListForm, User user);
}