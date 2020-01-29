package dream.pers.appstb.prc.service.spring;

import java.util.List;

import common.bean.User;
import dream.app.AppObjDTO;
import dream.app.AppObjService;
import dream.pers.appreq.dto.AppReqCommonDTO;
import dream.pers.appstb.prc.dao.AppPrcDetailDAO;
import dream.pers.appstb.prc.dto.AppPrcCommonDTO;
import dream.pers.appstb.prc.dto.AppPrcDetailDTO;
import dream.pers.appstb.prc.service.AppPrcDetailService;

/**
 * 결재문서-상세 
 * @author  javaworker
 * @version $Id: AppPrcDetailServiceImpl.java,v 1.3 2014/03/07 02:40:22 javaworker Exp $
 * @since   1.0
 * @spring.bean id="appPrcDetailServiceTarget"
 * @spring.txbn id="appPrcDetailService"
 * @spring.property name="appObjService" ref="appObjService"
 * @spring.property name="appPrcDetailDAO" ref="appPrcDetailDAO"
 */
public class AppPrcDetailServiceImpl implements AppPrcDetailService
{
    /** 문서처리 */
    private AppObjService appObjService = null;
    /** 결재문서-상세DAO */
    private AppPrcDetailDAO appPrcDetailDAO = null;
    
    public AppPrcDetailDAO getAppPrcDetailDAO()
    {
        return appPrcDetailDAO;
    }
    public void setAppPrcDetailDAO(AppPrcDetailDAO appPrcDetailDAO)
    {
        this.appPrcDetailDAO = appPrcDetailDAO;
    }
    
    public AppObjService getAppObjService()
    {
        return appObjService;
    }
    public void setAppObjService(AppObjService appObjService)
    {
        this.appObjService = appObjService;
    }

    public void insertDetail(AppPrcDetailDTO appPrcDetailDTO, User user)
    {
        appPrcDetailDAO.insertDetail(appPrcDetailDTO);
    }

    public void updateDetail(AppPrcDetailDTO appPrcDetailDTO, User user)
    {
        appPrcDetailDAO.updateDetail(appPrcDetailDTO);
    }

    public AppPrcDetailDTO findDetail(AppReqCommonDTO appReqCommonDTO)
    {
        // TODO Auto-generated method stub
        return appPrcDetailDAO.findDetail(appReqCommonDTO);
    }
	@Override
	public String checkSeqNum(AppReqCommonDTO appReqCommonDTO, AppPrcDetailDTO appPrcDetailDTO, User user) {
		return appPrcDetailDAO.checkSeqNum(appReqCommonDTO, appPrcDetailDTO);
	}
	
	public String nextAppSeq(AppReqCommonDTO appReqCommonDTO, AppPrcDetailDTO appPrcDetailDTO, User user) {
		return appPrcDetailDAO.nextAppSeq(appReqCommonDTO, appPrcDetailDTO);
	}
}