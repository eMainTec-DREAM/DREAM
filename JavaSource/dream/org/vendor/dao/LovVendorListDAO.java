package dream.org.vendor.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.org.vendor.dto.LovVendorListDTO;
import dream.part.list.dto.LovPartsListDTO;

/**
 * 거래처검색 팝업
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface LovVendorListDAO
{
    /**
     * 자재 검색
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovVendorListDTO
     * @param loginUser
     * @return
     */
    public List findVendorList(LovVendorListDTO lovVendorListDTO, User loginUser);

	/**
	 * Find AC Vendor
	 * @param lovVendorListDTO
	 * @param user
	 * @param conditionMap
	 * @return
	 */
	public List findVendorAcList(LovVendorListDTO lovVendorListDTO, User user, Map<String, String> conditionMap);

    public String findTotalCount(LovVendorListDTO lovVendorListDTO, User user, Map<String, String> conditionMap);
}