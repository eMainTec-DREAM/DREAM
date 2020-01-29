package dream.mgr.user.service.spring;

import common.bean.User;
import dream.mgr.user.dao.MaUserPwDAO;
import dream.mgr.user.dto.MaUserCommonDTO;
import dream.mgr.user.dto.MaUserPwDTO;
import dream.mgr.user.service.MaUserPwService;

/**
 * 비밀번호설정
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="maUserPwServiceTarget"
 * @spring.txbn id="maUserPwService"
 * @spring.property name="maUserPwDAO" ref="maUserPwDAO"
 */
public class MaUserPwServiceImpl implements MaUserPwService
{
    private MaUserPwDAO maUserPwDAO = null;
    
    public MaUserPwDAO getMaUserPwDAO() 
    {
		return maUserPwDAO;
	}

	public void setMaUserPwDAO(MaUserPwDAO maUserPwDAO) 
	{
		this.maUserPwDAO = maUserPwDAO;
	}

	public MaUserPwDTO findDetail(MaUserCommonDTO maUserCommonDTO, User loginUser)throws Exception
    {
        return maUserPwDAO.findDetail(maUserCommonDTO, loginUser);
    }
    
	public int updateDetail(MaUserPwDTO maUserPwDTO, User loginUser) throws Exception
    {        
		maUserPwDAO.insertPwChanHist(maUserPwDTO, loginUser);
        return maUserPwDAO.updateDetail(maUserPwDTO, loginUser);
    }
	
	public String checkPwHist(MaUserPwDTO maUserPwDTO, String pwChangeHistCnt) throws Exception
	{        
		return maUserPwDAO.checkPwHist(maUserPwDTO, pwChangeHistCnt);
	}
}
