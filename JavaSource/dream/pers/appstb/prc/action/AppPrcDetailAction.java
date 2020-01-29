package dream.pers.appstb.prc.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.pers.appreq.dto.AppReqCommonDTO;
import dream.pers.appstb.prc.dto.AppPrcDetailDTO;
import dream.pers.appstb.prc.form.AppPrcDetailForm;
import dream.pers.appstb.prc.service.AppPrcDetailService;

/**
 * 결재문서-상세
 * @author  javaworker
 * @version $Id: AppPrcDetailAction.java,v 1.1 2013/08/30 09:11:16 javaworker Exp $
 * @since   1.0
 *
 * @struts:action path="/appPrcDetail" name="appPrcDetailForm"
 *                input="/dream/pers/appstb/prc/appPrcDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maAppPrcDetail" name="appPrcDetailForm"
 *                input="/dream/pers/appstb/prc/maAppPrcDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="appPrcDetail" path="/dream/pers/appstb/prc/appPrcDetail.jsp"
 *                redirect="false"
 */
public class AppPrcDetailAction extends AuthAction
{
    /** 결재 내역 초기 */
    public static final int APP_PRC_INIT 		= 8001;
    /** 결재자 생성 */
    public static final int APP_PRC_INPUT 		= 5003;
    /** 결재자 수정 */
    public static final int APP_PRC_UPDATE 		= 6004;
    /** 결재 Sequence Check */
    public static final int APP_PRC_SEQ 		= 8005;
    /** 다음 결재순번 얻기 */
    public static final int APP_PRC_NEXT_SEQ 	= 8006;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {    
        AppPrcDetailForm appPrcDetailForm = (AppPrcDetailForm)form;
        ActionForward returnActionForward = null;
        
        switch(appPrcDetailForm.getStrutsAction())
        {
            case AppPrcDetailAction.APP_PRC_INIT :
                findDetail(appPrcDetailForm, request);  
                returnActionForward = mapping.getInputForward();
                break;
            case AppPrcDetailAction.APP_PRC_INPUT :
                inputDetail(appPrcDetailForm, request);  
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case AppPrcDetailAction.APP_PRC_UPDATE :
                updateDetail(appPrcDetailForm, request);  
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case AppPrcDetailAction.APP_PRC_SEQ :
                checkAppSeq(appPrcDetailForm, request);  
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case AppPrcDetailAction.APP_PRC_NEXT_SEQ :
                nextAppSeq(appPrcDetailForm, request);  
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            default :
                returnActionForward = mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }

    /**
     * Check App Sequence Validation
     * @param appPrcDetailForm
     * @param request
     */
    private void checkAppSeq(AppPrcDetailForm appPrcDetailForm, HttpServletRequest request) {
    	 AppPrcDetailService appPrcDetailService = (AppPrcDetailService)getBean("appPrcDetailService");
         
         AppPrcDetailDTO appPrcDetailDTO = appPrcDetailForm.getAppPrcDetailDTO();
         AppReqCommonDTO appReqCommonDTO = appPrcDetailForm.getAppReqCommonDTO();
         appPrcDetailDTO.setCompNo((getUser(request).getCompNo()));
         
         String resultStr = appPrcDetailService.checkSeqNum(appReqCommonDTO,appPrcDetailDTO, getUser(request));

         setAjaxDesc(request, resultStr);
	}
    private void nextAppSeq(AppPrcDetailForm appPrcDetailForm, HttpServletRequest request) {
    	AppPrcDetailService appPrcDetailService = (AppPrcDetailService)getBean("appPrcDetailService");
        
        AppPrcDetailDTO appPrcDetailDTO = appPrcDetailForm.getAppPrcDetailDTO();
        AppReqCommonDTO appReqCommonDTO = appPrcDetailForm.getAppReqCommonDTO();
        appPrcDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        String resultStr = appPrcDetailService.nextAppSeq(appReqCommonDTO,appPrcDetailDTO, getUser(request));

        setAjaxDesc(request, resultStr);
	}
	private void updateDetail(AppPrcDetailForm appPrcDetailForm, HttpServletRequest request)
    {
        AppPrcDetailService appPrcDetailService = (AppPrcDetailService)getBean("appPrcDetailService");
        
        AppPrcDetailDTO appPrcDetailDTO = appPrcDetailForm.getAppPrcDetailDTO();
        
        appPrcDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        appPrcDetailService.updateDetail(appPrcDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }

    private void inputDetail(AppPrcDetailForm appPrcDetailForm, HttpServletRequest request)
    {
        AppPrcDetailService appPrcDetailService = (AppPrcDetailService)getBean("appPrcDetailService");
        
        AppPrcDetailDTO appPrcDetailDTO = appPrcDetailForm.getAppPrcDetailDTO();
        
        appPrcDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        appPrcDetailService.insertDetail(appPrcDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }

    /**
     * 결재자 조회
     * @author  javaworker
     * @version $Id: AppPrcDetailAction.java,v 1.1 2013/08/30 09:11:16 javaworker Exp $
     * @since   1.0
     * 
     * @param appPrcDetailForm
     * @param request
     */
    private void findDetail(AppPrcDetailForm appPrcDetailForm, HttpServletRequest request)
    {
        AppPrcDetailService appPrcDetailService = (AppPrcDetailService)getBean("appPrcDetailService");
        AppReqCommonDTO appReqCommonDTO = appPrcDetailForm.getAppReqCommonDTO();

        appReqCommonDTO.setCompNo((getUser(request).getCompNo()));
        appReqCommonDTO.setEnterBy(getUser(request).getUserId());   // 조회조건
        appReqCommonDTO.setUserLang(getUser(request).getLangId());
        
        AppPrcDetailDTO appPrcDetailDTO = appPrcDetailService.findDetail(appReqCommonDTO);
        appPrcDetailForm.setAppPrcDetailDTO(appPrcDetailDTO);

    }

}