package dream.org.emp.service.spring;

import common.bean.User;
import common.util.CommonUtil;
import dream.org.emp.dao.MaEmpDetailDAO;
import dream.org.emp.dto.MaEmpCommonDTO;
import dream.org.emp.dto.MaEmpDetailDTO;
import dream.org.emp.service.MaEmpDetailService;
import dream.org.emp.service.MaEmpListService;

/**
 * 사원 - 상세 serviceimpl 
 * @author  
 * @version $Id:  $
 * @since   1.0
 * @spring.bean id="maEmpDetailServiceTarget"
 * @spring.txbn id="maEmpDetailService"
 * @spring.property name="maEmpDetailDAO" ref="maEmpDetailDAO"
 */
public class MaEmpDetailServiceImpl implements MaEmpDetailService
{
    private MaEmpDetailDAO maEmpDetailDAO = null;
    
    public MaEmpDetailDAO getMaEmpDetailDAO() 
    {
		return maEmpDetailDAO;
	}

	public void setMaEmpDetailDAO(MaEmpDetailDAO maEmpDetailDAO) 
	{
		this.maEmpDetailDAO = maEmpDetailDAO;
	}

	public MaEmpDetailDTO findDetail(MaEmpCommonDTO maEmpCommonDTO, User user)throws Exception
    {
	    MaEmpListService maEmpListService = (MaEmpListService) CommonUtil.getBean("maEmpListService", user);
        return (MaEmpDetailDTO) CommonUtil.makeDetailFromList(maEmpListService.findEmpList(maEmpCommonDTO, user), new MaEmpDetailDTO());
    }
    
	public int insertDetail(MaEmpDetailDTO maEmpDetailDTO) throws Exception
    {        
        return maEmpDetailDAO.insertDetail(maEmpDetailDTO);
    }
	
	public int updateDetail(MaEmpDetailDTO maEmpDetailDTO, User user) throws Exception
    {        
		if (!"Y".equals(maEmpDetailDTO.getIsJoin())) maEmpDetailDAO.updateUserStatus(maEmpDetailDTO, user);
		
        return maEmpDetailDAO.updateDetail(maEmpDetailDTO);
    }
	
	public String validEmpNo(String empId, String empNo, User user) throws Exception
    {
        return maEmpDetailDAO.validEmpNo(empId, empNo, user);
    }
}
