package dream.consult.consult.user.service.spring;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.consult.consult.user.dao.McUserListDAO;
import dream.consult.consult.user.dto.McUserCommonDTO;
import dream.consult.consult.user.dto.McUserListDTO;
import dream.consult.consult.user.service.McUserListService;

/**
 * 사용자 - 목록 serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="mcUserListServiceTarget"
 * @spring.txbn id="mcUserListService"
 * @spring.property name="mcUserListDAO" ref="mcUserListDAO"
 */
public class McUserListServiceImpl implements McUserListService
{
    private McUserListDAO mcUserListDAO = null;

    public McUserListDAO getMcUserListDAO() 
    {
		return mcUserListDAO;
	}

	public void setMcUserListDAO(McUserListDAO mcUserListDAO) 
	{
		this.mcUserListDAO = mcUserListDAO;
	}

	public List findUserList(McUserCommonDTO mcUserCommonDTO, User loginUser)
    {      
	    return mcUserListDAO.findUserList(mcUserCommonDTO, loginUser);
    }

	public int deleteList(String[] deleteRows, User loginUser) throws Exception
    {
        int result = 0;
        
        if(!deleteRows.equals(null))
        {
            McUserListDTO mcUserListDTO = null;
            for(String id : deleteRows)
            {
                mcUserListDTO = new McUserListDTO();
                mcUserListDTO.setUserId(id);
                result = result + mcUserListDAO.deleteUser(mcUserListDTO, loginUser);
            }
        }
        
        return result;
    }

	@Override
	public void resetPassword(McUserCommonDTO mcUserCommonDTO, User loginUser) {
		List list = mcUserListDAO.findUserList(mcUserCommonDTO, loginUser);
	    
	    for(Object obj : list)
	    {
	        Map map = (Map)obj;
	        mcUserListDAO.updateUsers(map);
	    }
	}
}

