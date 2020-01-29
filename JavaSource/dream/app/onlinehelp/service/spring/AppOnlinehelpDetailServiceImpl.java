package dream.app.onlinehelp.service.spring;

import common.bean.User;
import dream.app.onlinehelp.dao.AppOnlinehelpDetailDAO;
import dream.app.onlinehelp.dto.AppOnlinehelpCommonDTO;
import dream.app.onlinehelp.dto.AppOnlinehelpDetailDTO;
import dream.app.onlinehelp.service.AppOnlinehelpDetailService;

/**
 * 도움말 상세
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="appOnlinehelpDetailServiceTarget"
 * @spring.txbn id="appOnlinehelpDetailService"
 * @spring.property name="appOnlinehelpDetailDAO" ref="appOnlinehelpDetailDAO"
 */
public class AppOnlinehelpDetailServiceImpl implements AppOnlinehelpDetailService
{
    private AppOnlinehelpDetailDAO appOnlinehelpDetailDAO = null;
    
    public AppOnlinehelpDetailDAO getAppOnlinehelpDetailDAO() {
		return appOnlinehelpDetailDAO;
	}

	public void setAppOnlinehelpDetailDAO(AppOnlinehelpDetailDAO appOnlinehelpDetailDAO) {
		this.appOnlinehelpDetailDAO = appOnlinehelpDetailDAO;
	}

	public AppOnlinehelpDetailDTO findDetail(AppOnlinehelpCommonDTO appOnlinehelpCommonDTO, User user)throws Exception
    {
	    return appOnlinehelpDetailDAO.findDetail(appOnlinehelpCommonDTO, user);
    }
    
	public int updateDetail(AppOnlinehelpDetailDTO appOnlinehelpDetailDTO, AppOnlinehelpCommonDTO appOnlinehelpCommonDTO, User user) throws Exception
    {        
        return appOnlinehelpDetailDAO.updateDetail(appOnlinehelpDetailDTO, appOnlinehelpCommonDTO, user);
    }
	public int insertDetail(AppOnlinehelpDetailDTO appOnlinehelpDetailDTO, AppOnlinehelpCommonDTO appOnlinehelpCommonDTO, User user) throws Exception
    {        
        return appOnlinehelpDetailDAO.insertDetail( appOnlinehelpDetailDTO, appOnlinehelpCommonDTO, user);
    }
}
