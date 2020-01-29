package dream.app.onlinehelp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.app.onlinehelp.dto.AppOnlinehelpCommonDTO;
import dream.app.onlinehelp.dto.AppOnlinehelpDetailDTO;
import dream.app.onlinehelp.form.AppOnlinehelpDetailForm;
import dream.app.onlinehelp.service.AppOnlinehelpDetailService;

/**
 * 도움말 상세
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/appOnlinehelpDetail" name="appOnlinehelpDetailForm"
 *                input="/dream/app/onlinehelp/appOnlinehelpDetail.jsp" scope="request"
 *                validate="false"
 */
public class AppOnlinehelpDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int ONLINE_HELP_DETAIL_INIT 	= 1001;
    /** 수정 */
    public static final int ONLINE_HELP_DETAIL_UPDATE 	= 1002;
    /** 입력 */
    public static final int ONLINE_HELP_DETAIL_INPUT 	= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        AppOnlinehelpDetailForm appOnlinehelpDetailForm = (AppOnlinehelpDetailForm) form;
        switch (appOnlinehelpDetailForm.getStrutsAction())
        {
            case AppOnlinehelpDetailAction.ONLINE_HELP_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, appOnlinehelpDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case AppOnlinehelpDetailAction.ONLINE_HELP_DETAIL_UPDATE:
            	updateDetail(appOnlinehelpDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case AppOnlinehelpDetailAction.ONLINE_HELP_DETAIL_INPUT:
            	insertDetail(appOnlinehelpDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                this.findDetail(request, appOnlinehelpDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 도움말 상세 조회
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param appOnlinehelpDetailForm
     */
    private void findDetail(HttpServletRequest request, AppOnlinehelpDetailForm appOnlinehelpDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	AppOnlinehelpDetailService appOnlinehelpDetailService = (AppOnlinehelpDetailService)getBean("appOnlinehelpDetailService");

    	// 넘겨진 pageId 구함
    	AppOnlinehelpCommonDTO appOnlinehelpCommonDTO = appOnlinehelpDetailForm.getAppOnlinehelpCommonDTO();
        
        // 조회된 상세 부분
        AppOnlinehelpDetailDTO appOnlinehelpDetailDTO = appOnlinehelpDetailService.findDetail(appOnlinehelpCommonDTO, getUser(request));
        
        // 조회된 상세  셋팅한다.
        appOnlinehelpDetailForm.setAppOnlinehelpDetailDTO(appOnlinehelpDetailDTO);
    }
    /**
     * detail update
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param appOnlinehelpDetailForm
     * @param request
     */
    private void updateDetail(AppOnlinehelpDetailForm appOnlinehelpDetailForm, HttpServletRequest request) throws Exception
    {
    	AppOnlinehelpDetailService appOnlinehelpDetailService = (AppOnlinehelpDetailService) getBean("appOnlinehelpDetailService");
        
        AppOnlinehelpDetailDTO appOnlinehelpDetailDTO = appOnlinehelpDetailForm.getAppOnlinehelpDetailDTO();
        AppOnlinehelpCommonDTO appOnlinehelpCommonDTO = appOnlinehelpDetailForm.getAppOnlinehelpCommonDTO();
        
        appOnlinehelpDetailService.updateDetail(appOnlinehelpDetailDTO,appOnlinehelpCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param appOnlinehelpDetailForm
     * @param request
     */
    private void insertDetail(AppOnlinehelpDetailForm appOnlinehelpDetailForm, HttpServletRequest request) throws Exception
    {
    	AppOnlinehelpDetailService appOnlinehelpDetailService = (AppOnlinehelpDetailService) getBean("appOnlinehelpDetailService");
        
        AppOnlinehelpDetailDTO appOnlinehelpDetailDTO = appOnlinehelpDetailForm.getAppOnlinehelpDetailDTO();
        
        AppOnlinehelpCommonDTO appOnlinehelpCommonDTO = appOnlinehelpDetailForm.getAppOnlinehelpCommonDTO();
        
        appOnlinehelpDetailService.insertDetail(appOnlinehelpDetailDTO, appOnlinehelpCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }
}