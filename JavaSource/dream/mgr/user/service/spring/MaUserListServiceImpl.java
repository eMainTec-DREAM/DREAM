package dream.mgr.user.service.spring;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.mgr.user.dao.MaUserListDAO;
import dream.mgr.user.dto.MaUserCommonDTO;
import dream.mgr.user.dto.MaUserListDTO;
import dream.mgr.user.service.MaUserListService;

/**
 * 사용자 - 목록 serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="maUserListServiceTarget"
 * @spring.txbn id="maUserListService"
 * @spring.property name="maUserListDAO" ref="maUserListDAO"
 */
public class MaUserListServiceImpl implements MaUserListService
{
    private MaUserListDAO maUserListDAO = null;

    public MaUserListDAO getMaUserListDAO() 
    {
		return maUserListDAO;
	}

	public void setMaUserListDAO(MaUserListDAO maUserListDAO) 
	{
		this.maUserListDAO = maUserListDAO;
	}

	public List findUserList(MaUserCommonDTO maUserCommonDTO, User loginUser)
    {      
	    return maUserListDAO.findUserList(maUserCommonDTO, loginUser);
    }
	public String findTotalCount(MaUserCommonDTO maUserCommonDTO, User loginUser)
    {
        return maUserListDAO.findTotalCount(maUserCommonDTO, loginUser);
    }
	public int deleteList(String[] deleteRows, User loginUser) throws Exception
    {
        int result = 0;
        
        if(!deleteRows.equals(null))
        {
            MaUserListDTO maUserListDTO = null;
            for(String id : deleteRows)
            {
                maUserListDTO = new MaUserListDTO();
                maUserListDTO.setUserId(id);
                result = result + maUserListDAO.deleteUser(maUserListDTO, loginUser);
            }
        }
        
        return result;
    }

	@Override
	public void resetPassword(MaUserCommonDTO maUserCommonDTO, User loginUser) {
		List list = maUserListDAO.findUserList(maUserCommonDTO, loginUser);
	    
	    for(Object obj : list)
	    {
	        Map map = (Map)obj;
	        maUserListDAO.updateUsers(map, loginUser);
	    }
	}
}

