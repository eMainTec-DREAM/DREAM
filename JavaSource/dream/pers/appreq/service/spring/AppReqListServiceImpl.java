package dream.pers.appreq.service.spring;

import java.util.List;

import dream.pers.appreq.dao.AppReqListDAO;
import dream.pers.appreq.dto.AppReqCommonDTO;
import dream.pers.appreq.service.AppReqListService;

/**
 * 요청문서 - 목록
 * @author javaworker
 * @version $Id: AppReqListServiceImpl.java,v 1.1 2013/08/30 09:14:51 javaworker Exp $
 * @since 1.0
 * 
 * @spring.bean id="appReqListServiceTarget"
 * @spring.txbn id="appReqListService"
 * @spring.property name="appReqListDAO" ref="appReqListDAO"
 */
public class AppReqListServiceImpl implements AppReqListService
{
    /** 요청문서-목록DAO */
    private AppReqListDAO appReqListDAO = null;

    public AppReqListDAO getAppReqListDAO()
    {
        return appReqListDAO;
    }

    public void setAppReqListDAO(AppReqListDAO appReqListDAO)
    {
        this.appReqListDAO = appReqListDAO;
    }
    
    public List findAppReqList(AppReqCommonDTO appReqCommonDTO)
    {
        return appReqListDAO.findAppReqList(appReqCommonDTO);
    }
    
    public int findAppReqListCount(AppReqCommonDTO appReqCommonDTO)
    {
        return appReqListDAO.findAppReqListCount(appReqCommonDTO);
    }
}