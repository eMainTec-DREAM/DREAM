package dream.app.tracelog.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.app.tracelog.dto.AppTracelogCommonDTO;
import dream.app.tracelog.dto.AppTracelogDetailDTO;
import dream.app.tracelog.form.AppTracelogDetailForm;
import dream.app.tracelog.service.AppTracelogDetailService;

/**
 * TraceLog - Detail Action
 * 
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/appTracelogDetail" name="appTracelogDetailForm"
 *                input="/dream/app/tracelog/appTracelogDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="appTracelogDetail" path="/dream/app/tracelog/appTracelogDetail.jsp"
 *                        redirect="false"
 */
public class AppTracelogDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int DETAIL_INIT 		= 1001;
    /** 저장 */
    public static final int DETAIL_INPUT 		= 1002;
    /** 수정 */
    public static final int DETAIL_UPDATE 		= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        AppTracelogDetailForm appTracelogDetailForm = (AppTracelogDetailForm) form;
        
        switch (appTracelogDetailForm.getStrutsAction())
        {
            case AppTracelogDetailAction.DETAIL_INIT:
                this.findDetail(request, response, appTracelogDetailForm);
                returnActionForward = mapping.findForward("appTracelogDetail");
                break;
            case AppTracelogDetailAction.DETAIL_INPUT:
            	insertDetail(request, response, appTracelogDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case AppTracelogDetailAction.DETAIL_UPDATE:
            	updateDetail(request, response, appTracelogDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("appTracelogDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * FIND DETAIL
     * @param request
     * @param response
     * @param appTracelogDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, AppTracelogDetailForm appTracelogDetailForm) throws Exception 
    {   
    	AppTracelogDetailService appTracelogDetailService = (AppTracelogDetailService)getBean("appTracelogDetailService");
    	
    	AppTracelogCommonDTO appTracelogCommonDTO = appTracelogDetailForm.getAppTracelogCommonDTO(); 
    	
    	AppTracelogDetailDTO appTracelogDetailDTO = appTracelogDetailService.findCompTracelogDetail(appTracelogCommonDTO,getUser(request));
    	appTracelogDetailForm.setAppTracelogDetailDTO(appTracelogDetailDTO);
    }

    /**
     * INSERT DETAIL
     * @param request
     * @param response
     * @param appTracelogDetailForm
     * @throws Exception
     */
    private void insertDetail(HttpServletRequest request, HttpServletResponse response, AppTracelogDetailForm appTracelogDetailForm) throws Exception
    {
    	AppTracelogDetailService appTracelogDetailService = (AppTracelogDetailService)getBean("appTracelogDetailService");
    	AppTracelogDetailDTO  appTracelogDetailDTO = appTracelogDetailForm.getAppTracelogDetailDTO(); 
    	
    	appTracelogDetailService.insertCompTracelogDetail(appTracelogDetailDTO,getUser(request));
        setAjaxStatus(request);
    }

    /**
     * UPDATE DETAIL
     * @param request
     * @param response
     * @param appTracelogDetailForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, AppTracelogDetailForm appTracelogDetailForm) throws Exception
    {
    	AppTracelogDetailService appTracelogDetailService = (AppTracelogDetailService)getBean("appTracelogDetailService");
    	AppTracelogDetailDTO  appTracelogDetailDTO = appTracelogDetailForm.getAppTracelogDetailDTO();
    	
    	appTracelogDetailService.updateCompTracelogDetail(appTracelogDetailDTO,getUser(request));
        
        setAjaxStatus(request);
    }

}