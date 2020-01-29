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
 * �����û
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
    /** ���� ���� �ʱ�  */
    public static final int APP_DOC_INIT = 1001;
    /** ���缱 ��ȸ */
    public static final int FLOW_USER_LIST = 1002;
    /** ���� ��� */
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
            	// ���� �Է½� ����� ������ �ڵ����� �Էµǵ��� �Ѵ�.
            	String wfType = appDocReqForm.getAppDocReqDTO().getWfType();
            	String objectNo = appDocReqForm.getAppDocReqDTO().getObjectNo();
            	String title = appDocReqForm.getAppDocReqDTO().getTitle();
            	String remark = appDocReqForm.getAppDocReqDTO().getRemark();
            	
            	// ���籸�а� ������ȣ�� �ְ�
            	if(wfType != null && !"".equals(wfType)
            			&& objectNo != null && !"".equals(objectNo))
            	{
            		// ������ ���ٸ�... �����Է��� �ȴ�.
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
     * ���缱 ��ȸ
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
        
        // ��ȸ�� List �� form�� �����Ѵ�.
        request.setAttribute(FIND_XML_ATTRIBUTE, resultList);
    }

    /**
     * ������
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
     * �����û�� ����� ���� default�� ��������...
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