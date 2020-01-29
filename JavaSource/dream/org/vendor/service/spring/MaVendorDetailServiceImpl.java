package dream.org.vendor.service.spring;

import common.bean.User;
import dream.org.vendor.dao.MaVendorDetailDAO;
import dream.org.vendor.dto.MaVendorDetailDTO;
import dream.org.vendor.service.MaVendorDetailService;

/**
 * 관련업체 - 상세 serviceimpl 
 * @author  
 * @version $Id:  $
 * @since   1.0
 * @spring.bean id="maVendorDetailServiceTarget"
 * @spring.txbn id="maVendorDetailService"
 * @spring.property name="maVendorDetailDAO" ref="maVendorDetailDAO"
 */
public class MaVendorDetailServiceImpl implements MaVendorDetailService
{
    private MaVendorDetailDAO maVendorDetailDAO = null;
    
    public MaVendorDetailDAO getMaVendorDetailDAO() 
    {
		return maVendorDetailDAO;
	}

	public void setMaVendorDetailDAO(MaVendorDetailDAO maVendorDetailDAO) 
	{
		this.maVendorDetailDAO = maVendorDetailDAO;
	}

	public MaVendorDetailDTO findDetail(String compNo, String deptNo, User user)throws Exception
    {
        return maVendorDetailDAO.findDetail(compNo, deptNo, user);
    }
    
	public int insertDetail(MaVendorDetailDTO maVendorDetailDTO) throws Exception
    {        
        return maVendorDetailDAO.insertDetail(maVendorDetailDTO);
    }
	
	public int updateDetail(MaVendorDetailDTO maVendorDetailDTO) throws Exception
    {        
        return maVendorDetailDAO.updateDetail(maVendorDetailDTO);
    }
	
	public String validVendorNo(MaVendorDetailDTO maVendorDetailDTO) throws Exception
    {
        return maVendorDetailDAO.validVendorNo(maVendorDetailDTO);
    }
}
