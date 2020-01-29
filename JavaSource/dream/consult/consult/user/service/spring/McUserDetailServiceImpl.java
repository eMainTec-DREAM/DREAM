package dream.consult.consult.user.service.spring;

import common.bean.User;
import dream.consult.consult.user.dao.McUserDetailDAO;
import dream.consult.consult.user.dto.McUserCommonDTO;
import dream.consult.consult.user.dto.McUserDetailDTO;
import dream.consult.consult.user.service.McUserDetailService;


/**
 * 사용자 - 상세 serviceimpl 
 * @author  
 * @version $Id:  $
 * @since   1.0
 * @spring.bean id="mcUserDetailServiceTarget"
 * @spring.txbn id="mcUserDetailService"
 * @spring.property name="mcUserDetailDAO" ref="mcUserDetailDAO"
 */
public class McUserDetailServiceImpl implements McUserDetailService
{
    private McUserDetailDAO mcUserDetailDAO = null;
    
    public McUserDetailDAO getMcUserDetailDAO() 
    {
		return mcUserDetailDAO;
	}

	public void setMcUserDetailDAO(McUserDetailDAO mcUserDetailDAO) 
	{
		this.mcUserDetailDAO = mcUserDetailDAO;
	}

	public McUserDetailDTO findDetail(McUserCommonDTO mcUserCommonDTO, User loginUser)throws Exception
    {
        return mcUserDetailDAO.findDetail(mcUserCommonDTO, loginUser);
    }
    
	public int insertDetail(McUserDetailDTO mcUserDetailDTO, User loginUser) throws Exception
    {        
        return mcUserDetailDAO.insertDetail(mcUserDetailDTO, loginUser);
    }
	
	public int updateDetail(McUserDetailDTO mcUserDetailDTO, User loginUser) throws Exception
    {        
        return mcUserDetailDAO.updateDetail(mcUserDetailDTO, loginUser);
    }
	
	public String validUserNo(McUserDetailDTO mcUserDetailDTO, User loginUser) throws Exception
    {
        return mcUserDetailDAO.validUserNo(mcUserDetailDTO, loginUser);
    }
}
