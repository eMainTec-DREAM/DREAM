package dream.org.vendor.service.spring;

import java.util.List;

import common.bean.User;
import dream.org.vendor.dao.MaVendorListDAO;
import dream.org.vendor.dto.MaVendorCommonDTO;
import dream.org.vendor.service.MaVendorListService;

/**
 * 관련업체 - 목록 serviceimpl
 * @author ssong
 * @version
 * @since 1.0
 * 
 * @spring.bean id="maVendorListServiceTarget"
 * @spring.txbn id="maVendorListService"
 * @spring.property name="maVendorListDAO" ref="maVendorListDAO"
 */
public class MaVendorListServiceImpl implements MaVendorListService
{
    private MaVendorListDAO maVendorListDAO = null;

    public MaVendorListDAO getMaVendorListDAO() 
    {
		return maVendorListDAO;
	}

	public void setMaVendorListDAO(MaVendorListDAO maVendorListDAO) 
	{
		this.maVendorListDAO = maVendorListDAO;
	}

	public List findList(MaVendorCommonDTO maVendorCommonDTO, User user)
    {      
        return maVendorListDAO.findList(maVendorCommonDTO, user);
    }

	@Override
	public String findTotalCount(MaVendorCommonDTO maVendorCommonDTO, User user) {
		return maVendorListDAO.findTotalCount(maVendorCommonDTO, user);
	}
	
	public int deleteList(String compNo, String[] deleteRows) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + maVendorListDAO.deleteParts(compNo, id);
            }
        
        return result;
    }
}

