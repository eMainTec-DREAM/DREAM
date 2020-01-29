package dream.mgr.user.service.spring;

import common.bean.User;
import dream.mgr.user.dao.MgrUserPlantauthDetailDAO;
import dream.mgr.user.dto.MaUserCommonDTO;
import dream.mgr.user.dto.MgrUserPlantauthDetailDTO;
import dream.mgr.user.service.MgrUserPlantauthDetailService;

/**
 * 사용자 - 상세 serviceimpl 
 * @author  
 * @version $Id:  $
 * @since   1.0
 * @spring.bean id="mgrUserPlantauthDetailServiceTarget"
 * @spring.txbn id="mgrUserPlantauthDetailService"
 * @spring.property name="mgrUserPlantauthDetailDAO" ref="mgrUserPlantauthDetailDAO"
 */
public class MgrUserPlantauthDetailServiceImpl implements MgrUserPlantauthDetailService
{
    private MgrUserPlantauthDetailDAO mgrUserPlantauthDetailDAO = null;

    public MgrUserPlantauthDetailDAO getMgrUserPlantauthDetailDAO() 
    {
		return mgrUserPlantauthDetailDAO;
	}

	public void setMgrUserPlantauthDetailDAO(MgrUserPlantauthDetailDAO mgrUserPlantauthDetailDAO) 
	{
		this.mgrUserPlantauthDetailDAO = mgrUserPlantauthDetailDAO;
	}

	public MgrUserPlantauthDetailDTO findDetail(MaUserCommonDTO maUserCommonDTO, User loginUser)throws Exception
    {
        return mgrUserPlantauthDetailDAO.findDetail(maUserCommonDTO, loginUser);
    }
    
	public int insertDetail(MgrUserPlantauthDetailDTO mgrUserPlantauthDetailDTO, User loginUser) throws Exception
    {        
        return mgrUserPlantauthDetailDAO.insertDetail(mgrUserPlantauthDetailDTO, loginUser);
    }
	
	public int updateDetail(MgrUserPlantauthDetailDTO mgrUserPlantauthDetailDTO, User loginUser) throws Exception
    {        
        return mgrUserPlantauthDetailDAO.updateDetail(mgrUserPlantauthDetailDTO, loginUser);
    }

	public String validPlant(MaUserCommonDTO maUserCommonDTO, MgrUserPlantauthDetailDTO mgrUserPlantauthDetailDTO, User user, String isRegbatch) throws Exception 
	{
		return mgrUserPlantauthDetailDAO.validPlant(maUserCommonDTO, mgrUserPlantauthDetailDTO, user, isRegbatch);
	}
}
