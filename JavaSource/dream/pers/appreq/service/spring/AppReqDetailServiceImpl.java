package dream.pers.appreq.service.spring;

import java.util.List;

import common.bean.User;
import dream.pers.appreq.dao.AppReqDetailDAO;
import dream.pers.appreq.dto.AppReqCommonDTO;
import dream.pers.appreq.dto.AppReqDetailDTO;
import dream.pers.appreq.service.AppReqDetailService;
import dream.pers.appstb.prc.dto.AppPrcDetailDTO;
import dream.pers.appstb.prc.service.AppPrcDetailService;
import dream.pers.appstb.ready.service.AppReadyListService;

/**
 * 요청문서-상세 
 * @author  javaworker
 * @version $Id: AppReqDetailServiceImpl.java,v 1.1 2013/08/30 09:14:51 javaworker Exp $
 * @since   1.0
 * @spring.bean id="appReqDetailServiceTarget"
 * @spring.txbn id="appReqDetailService"
 * @spring.property name="appReqDetailDAO" ref="appReqDetailDAO"
 * @spring.property name="appPrcDetailService" ref="appPrcDetailService"
 * @spring.property name="appReadyListService" ref="appReadyListService"
 */
public class AppReqDetailServiceImpl implements AppReqDetailService
{
    /** 결재문서 상세 */
    private AppPrcDetailService appPrcDetailService = null;
    /** 결재대기문서 */
    private AppReadyListService appReadyListService = null;
    
    /** 요청문서-상세DAO */
    private AppReqDetailDAO appReqDetailDAO = null;
    
    
    public AppReadyListService getAppReadyListService()
    {
        return appReadyListService;
    }
    public void setAppReadyListService(AppReadyListService appReadyListService)
    {
        this.appReadyListService = appReadyListService;
    }
    public AppReqDetailDAO getAppReqDetailDAO()
    {
        return appReqDetailDAO;
    }
    public void setAppReqDetailDAO(AppReqDetailDAO appReqDetailDAO)
    {
        this.appReqDetailDAO = appReqDetailDAO;
    }
    public AppPrcDetailService getAppPrcDetailService()
    {
        return appPrcDetailService;
    }
    public void setAppPrcDetailService(AppPrcDetailService appPrcDetailService)
    {
        this.appPrcDetailService = appPrcDetailService;
    }
    
    @Override
    public List findFlowUserList(AppReqDetailDTO appReqDetailDTO)
    {
        return appReqDetailDAO.findFlowUserList(appReqDetailDTO);
    }
    
    @Override
    public AppReqDetailDTO findMstrDetail(AppReqCommonDTO appReqCommonDTO, User loginUser)
    {
        return appReqDetailDAO.findMstrDetail(appReqCommonDTO, loginUser);
    }
    
    @Override
    public void cancelReqDoc(AppReqDetailDTO appReqDetailDTO, User user)
    {
//        // 결재자 들을 모두 대기 상태로함
//        appReqDetailDAO.setAppWfStatus(appReqDetailDTO);
//        // 최소처리자 입력
//        appReqDetailDAO.insertCancelUser(appReqDetailDTO);
        
        //============================================================
        // 결재문서 취소처리
        AppPrcDetailDTO appPrcDetailDTO = new AppPrcDetailDTO();
        appPrcDetailDTO.setAppStatus("APP05");  // 취소
        appPrcDetailDTO.setEnterBy(appReqDetailDTO.getEnterBy());
        appPrcDetailDTO.setAppDocNo(appReqDetailDTO.getAppDocNo());
        
        // 결재요청문서 취소 처리
//        appPrcDetailService.completeApp(appPrcDetailDTO);
        //============================================================
    }

    public void insertDetail(AppReqDetailDTO appReqDetailDTO, User user)
    {
        appReqDetailDAO.insertDetail(appReqDetailDTO, user);
    }

    public void updateDetail(AppReqDetailDTO appReqDetailDTO, User user)
    {
        appReqDetailDAO.updateDetail(appReqDetailDTO, user);
    }

    public void actionReq(AppReqDetailDTO appReqDetailDTO, User user) throws Exception
    {
        appReqDetailDAO.actionReq(appReqDetailDTO, user);
        
        appReqDetailDAO.initApprUsrStatus(appReqDetailDTO, user);
        
        appReqDetailDAO.setApprUsrStatus(appReqDetailDTO, user);
        
        //결재 문서 상태 변화
        appReadyListService.apprProcess(appReqDetailDTO, user);

    }
    
    public String checkUsr(AppReqDetailDTO appReqDetailDTO, User user){
    	return appReqDetailDAO.checkUsr(appReqDetailDTO, user);
    }

}