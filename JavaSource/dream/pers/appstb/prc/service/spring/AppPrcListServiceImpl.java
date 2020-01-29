package dream.pers.appstb.prc.service.spring;

import java.util.List;

import common.bean.User;
import dream.pers.appreq.dto.AppReqCommonDTO;
import dream.pers.appstb.prc.dao.AppPrcListDAO;
import dream.pers.appstb.prc.service.AppPrcListService;

/**
 * 결재문서 - 목록
 * @author javaworker
 * @version $Id: AppPrcListServiceImpl.java,v 1.1 2013/08/30 09:12:10 javaworker Exp $
 * @since 1.0
 * 
 * @spring.bean id="appPrcListServiceTarget"
 * @spring.txbn id="appPrcListService"
 * @spring.property name="appPrcListDAO" ref="appPrcListDAO"
 */
public class AppPrcListServiceImpl implements AppPrcListService
{
    /** 결재문서-목록DAO */
    private AppPrcListDAO appPrcListDAO = null;

    public AppPrcListDAO getAppPrcListDAO()
    {
        return appPrcListDAO;
    }

    public void setAppPrcListDAO(AppPrcListDAO appPrcListDAO)
    {
        this.appPrcListDAO = appPrcListDAO;
    }
    
    public List findAppPrcList(AppReqCommonDTO appReqCommonDTO, User loginUser)
    {
        return appPrcListDAO.findAppPrcList(appReqCommonDTO,loginUser);
    }

    public int deleteLine(String[] deleteRows, String compNo)
    {
        int result = 0;
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + appPrcListDAO.deleteLine(id,compNo);
            }
        return result;
    }

	@Override
	public String findTotalCount(AppReqCommonDTO appReqCommonDTO, User user) throws Exception {
		return appPrcListDAO.findTotalCount(appReqCommonDTO, user);
	}
}