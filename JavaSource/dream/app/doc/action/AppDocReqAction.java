package dream.app.doc.action;

import java.util.Collection;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.app.doc.dto.AppDocReqDTO;
import dream.app.doc.form.AppDocReqForm;
import dream.app.doc.service.AppDocReqService;

/**
 * 결재요청
 * @author  javaworker
 * @version $Id: AppDocReqAction.java,v 1.2 2013/12/23 06:34:52 pochul2423 Exp $
 * @since   1.0
 *
 * @struts:action path="/appDocReq" name="appDocReqForm"
 *                input="/dream/app/doc/appDocReq.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="appDocReq" path="/dream/app/doc/appDocReq.jsp"
 *                redirect="false"
 */
public class AppDocReqAction extends AuthAction
{
    /** 결재 내역 초기  */
    public static final int APP_DOC_INIT = 1001;
    /** 결재선 조회 */
    public static final int FLOW_USER_LIST = 1002;
    /** 결재 상신 */
    public static final int APP_DOC_INPUT = 1004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {    
        AppDocReqForm appDocReqForm = (AppDocReqForm)form;
        ActionForward returnActionForward = null;
        
        switch(appDocReqForm.getStrutsAction())
        {
            case AppDocReqAction.APP_DOC_INPUT :
                inputAppDocReq(appDocReqForm, request);    
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case AppDocReqAction.FLOW_USER_LIST :
                findFlowUserList(appDocReqForm, request);    
                returnActionForward = mapping.findForward("gridList");
                break;
            case AppDocReqAction.BASE_DEFAULT_INIT :
            	// 최초 입력시 제목과 내용이 자동으로 입력되도록 한다.
            	String wfType = appDocReqForm.getAppDocReqDTO().getWfType();
            	String objectNo = appDocReqForm.getAppDocReqDTO().getObjectNo();
            	String title = appDocReqForm.getAppDocReqDTO().getTitle();
            	String remark = appDocReqForm.getAppDocReqDTO().getRemark();
            	
            	// 결재구분과 문서번호가 있고
            	if(wfType != null && !"".equals(wfType)
            			&& objectNo != null && !"".equals(objectNo))
            	{
            		// 제목이 없다면... 최초입력이 된다.
            		if(title == null || "".equals(title))
            		{
            			findDefaultInform(appDocReqForm);
            		}
            	}
            default :
                returnActionForward = mapping.findForward("appDocReq");
                break;
        }
        
        return returnActionForward;
    }

    /**
     * 결재선 조회
     * @author  javaworker
     * @version $Id: AppDocReqAction.java,v 1.2 2013/12/23 06:34:52 pochul2423 Exp $
     * @since   1.0
     * 
     * @param appDocReqForm
     * @param request
     */
    private void findFlowUserList(AppDocReqForm appDocReqForm,
            HttpServletRequest request)
    {
        AppDocReqService appDocReqService = (AppDocReqService)getBean("appDocReqService");
        
        List resultList = appDocReqService.findFlowUserList(appDocReqForm.getAppDocReqDTO());
        
        // 조회한 List 를 form에 셋팅한다.
        request.setAttribute(FIND_XML_ATTRIBUTE, resultList);
    }

    /**
     * 결재상신
     * @author  javaworker
     * @version $Id: AppDocReqAction.java,v 1.2 2013/12/23 06:34:52 pochul2423 Exp $
     * @since   1.0
     * @param AppDocReqForm
     * @param request
     */
    private void inputAppDocReq(AppDocReqForm appDocReqForm, HttpServletRequest request)
    {
        AppDocReqService appDocReqService = (AppDocReqService)getBean("appDocReqService");
        
        AppDocReqDTO appDocReqDTO = appDocReqForm.getAppDocReqDTO();
        appDocReqDTO.setEnterBy(getUser(request).getUserId());
        
//        String appStatus = appDocReqService.inputAppDocReq(appDocReqDTO, appDocReqForm.getFlowDtlDTOList());
//        
//        setAjaxDesc(request, appStatus);
    }

    /**
     * 결재요청시 제목과 내용 default로 가져오기...
     * @author  javaworker
     * @version $Id: AppDocReqAction.java,v 1.2 2013/12/23 06:34:52 pochul2423 Exp $
     * @since   1.0
     * @param AppDocReqForm
     * @param request
     */
    private void findDefaultInform(AppDocReqForm appDocReqForm)
    {
    	AppDocReqService appDocReqService = (AppDocReqService)getBean("appDocReqService");
        
    	AppDocReqDTO appDocReqDTO = appDocReqForm.getAppDocReqDTO();

    	String title = "";
    	String remark = "";
    	
    	String wfType = appDocReqForm.getAppDocReqDTO().getWfType();
    	

        String resultString [] = appDocReqService.findDefaultInform(appDocReqDTO);
    	
    	if(resultString.length == 3)
    	{
	    	appDocReqDTO.setTitle(resultString[0]);
	    	appDocReqDTO.setRemark(resultString[1]);
    	}
    }
    
}