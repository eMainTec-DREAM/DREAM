package dream.consult.consult.user.service.spring;

import common.bean.User;
import dream.consult.consult.user.dao.McUserPwDAO;
import dream.consult.consult.user.dto.McUserCommonDTO;
import dream.consult.consult.user.dto.McUserPwDTO;
import dream.consult.consult.user.service.McUserPwService;


/**
 * 비밀번호설정
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="mcUserPwServiceTarget"
 * @spring.txbn id="mcUserPwService"
 * @spring.property name="mcUserPwDAO" ref="mcUserPwDAO"
 */
public class McUserPwServiceImpl implements McUserPwService
{
    private McUserPwDAO mcUserPwDAO = null;
    
    public McUserPwDAO getMcUserPwDAO() 
    {
		return mcUserPwDAO;
	}

	public void setMcUserPwDAO(McUserPwDAO mcUserPwDAO) 
	{
		this.mcUserPwDAO = mcUserPwDAO;
	}

	public McUserPwDTO findDetail(McUserCommonDTO mcUserCommonDTO, User loginUser)throws Exception
    {
        return mcUserPwDAO.findDetail(mcUserCommonDTO, loginUser);
    }
    
	public int updateDetail(McUserPwDTO mcUserPwDTO, User loginUser) throws Exception
    {        
        return mcUserPwDAO.updateDetail(mcUserPwDTO, loginUser);
    }
}
