package dream.pers.appreq.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.pers.appreq.dto.AppReqCommonDTO;
import dream.pers.appreq.dto.AppReqDetailDTO;
import dream.pers.appreq.form.AppReqDetailForm;
import dream.pers.appreq.service.AppReqDetailService;

/**
 * ��û����-��
 * @author  javaworker
 * @version $Id: AppReqDetailAction.java,v 1.2 2014/03/07 05:35:54 javaworker Exp $
 * @since   1.0
 *
 * @struts:action path="/appReqDetail" name="appReqDetailForm"
 *                input="/dream/pers/appreq/appReqDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/appNotDetail" name="appReqDetailForm"
 *                input="/dream/app/not/appNotDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="appReqDetail" path="/dream/pers/appreq/appReqDetail.jsp"
 *                redirect="false"
 */
public class AppReqDetailAction extends AuthAction
{
    /** ���� ���� �ʱ� */
    public static final int APP_REQ_INIT = 1001;
    /** ���� ���� ���� */
    public static final int APP_REQ_UPDATE = 1003;
    /** ���� ���� ���� */
    public static final int APP_REQ_INPUT  = 1004;
    /** ���缱 ��ȸ */
    public static final int FLOW_USER_LIST = 1002;
    /** ��û��� */
    public static final int APP_REQ_CANCEL = 2001;
    /** �����û */
    public static final int APP_REQ_ACTION = 2002;
    /** ������ ���� Ȯ�� */
    public static final int APP_REQ_CHECK  = 2003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {    
        AppReqDetailForm appReqDetailForm = (AppReqDetailForm)form;
        ActionForward returnActionForward = null;
        
        switch(appReqDetailForm.getStrutsAction())
        {
            case AppReqDetailAction.APP_REQ_INPUT :
                insertDetail(appReqDetailForm, request);    
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case AppReqDetailAction.APP_REQ_UPDATE :
                updateDetail(appReqDetailForm, request);    
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case AppReqDetailAction.APP_REQ_CANCEL :
                cancelReqDoc(appReqDetailForm, request);    
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case AppReqDetailAction.APP_REQ_ACTION :
                actionReq(appReqDetailForm, request);    
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case AppReqDetailAction.APP_REQ_CHECK :
                checkUsr(appReqDetailForm, request);    
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case AppReqDetailAction.FLOW_USER_LIST :
                findFlowUserList(appReqDetailForm, request);    
                returnActionForward = mapping.findForward("gridList");
                break;
            case AppReqDetailAction.APP_REQ_INIT :
            default :
                findMstrDetail(appReqDetailForm, request);   
                returnActionForward = mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }
    
    private void actionReq(AppReqDetailForm appReqDetailForm, HttpServletRequest request)
    {
        AppReqDetailService appReqDetailService = (AppReqDetailService)getBean("appReqDetailService");
        
        AppReqDetailDTO appReqDetailDTO = appReqDetailForm.getAppReqDetailDTO();
        appReqDetailDTO.setCompNo(getUser(request).getCompNo());
        appReqDetailDTO.setEnterBy(getUser(request).getUserId() );
        
        try
        {
            appReqDetailService.actionReq(appReqDetailDTO, getUser(request));
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        setAjaxStatus(request);
    }

    private void updateDetail(AppReqDetailForm appReqDetailForm, HttpServletRequest request)
    {
        AppReqDetailService appReqDetailService = (AppReqDetailService)getBean("appReqDetailService");
        
        AppReqDetailDTO appReqDetailDTO = appReqDetailForm.getAppReqDetailDTO();
        appReqDetailDTO.setCompNo(getUser(request).getCompNo());
        appReqDetailDTO.setEnterBy(getUser(request).getUserId() );
        
        appReqDetailService.updateDetail(appReqDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }

    private void insertDetail(AppReqDetailForm appReqDetailForm, HttpServletRequest request)
    {
        AppReqDetailService appReqDetailService = (AppReqDetailService)getBean("appReqDetailService");
        
        AppReqDetailDTO appReqDetailDTO = appReqDetailForm.getAppReqDetailDTO();
        appReqDetailDTO.setCompNo(getUser(request).getCompNo());
        appReqDetailDTO.setEnterBy(getUser(request).getUserId() );
        
        appReqDetailService.insertDetail(appReqDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }

    /**
     * �����û ���
     * @author  javaworker
     * @version $Id: AppReqDetailAction.java,v 1.2 2014/03/07 05:35:54 javaworker Exp $
     * @since   1.0
     * 
     * @param appReqDetailForm
     * @param request
     */
    private void cancelReqDoc(AppReqDetailForm appReqDetailForm,
            HttpServletRequest request)
    {
        AppReqDetailService appReqDetailService = (AppReqDetailService)getBean("appReqDetailService");
        
        AppReqDetailDTO appReqDetailDTO = appReqDetailForm.getAppReqDetailDTO();
        appReqDetailDTO.setCompNo(getUser(request).getCompNo());
        appReqDetailDTO.setEnterBy(getUser(request).getUserId());
        
        appReqDetailService.cancelReqDoc(appReqDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }

    /**
     * ������ Ȯ��
     */
    private void checkUsr(AppReqDetailForm appReqDetailForm,
            HttpServletRequest request){
    	AppReqDetailService appReqDetailService = (AppReqDetailService)getBean("appReqDetailService");
        
        AppReqDetailDTO appReqDetailDTO = appReqDetailForm.getAppReqDetailDTO();
        appReqDetailDTO.setCompNo(getUser(request).getCompNo());
        appReqDetailDTO.setEnterBy(getUser(request).getUserId());
        
        String resultStr = appReqDetailService.checkUsr(appReqDetailDTO, getUser(request));
        
        setAjaxDesc(request, resultStr);
    }
    /**
     * ���系�� ��ȸ
     * @author  javaworker
     * @version $Id: AppReqDetailAction.java,v 1.2 2014/03/07 05:35:54 javaworker Exp $
     * @since   1.0
     * 
     * @param appReqDetailForm
     * @param request
     */
    private void findMstrDetail(AppReqDetailForm appReqDetailForm,
            HttpServletRequest request)
    {
        AppReqDetailService appReqDetailService = (AppReqDetailService)getBean("appReqDetailService");
        AppReqCommonDTO appReqCommonDTO = appReqDetailForm.getAppReqCommonDTO();

        appReqCommonDTO.setEnterBy(getUser(request).getUserId());   // ��ȸ����
        appReqCommonDTO.setCompNo(getUser(request).getCompNo());
        AppReqDetailDTO appReqDetailDTO = appReqDetailService.findMstrDetail(appReqCommonDTO, getUser(request));
        appReqDetailForm.setAppReqDetailDTO(appReqDetailDTO);
    }

    /**
     * ���缱 ��ȸ
     * @author  javaworker
     * @version $Id: AppReqDetailAction.java,v 1.2 2014/03/07 05:35:54 javaworker Exp $
     * @since   1.0
     * 
     * @param appReqDetailForm
     * @param request
     */
    private void findFlowUserList(AppReqDetailForm appReqDetailForm,
            HttpServletRequest request)
    {
        AppReqDetailService appReqDetailService = (AppReqDetailService)getBean("appReqDetailService");
        
        List resultList = appReqDetailService.findFlowUserList(appReqDetailForm.getAppReqDetailDTO());
        
        // ��ȸ�� List �� form�� �����Ѵ�.
        request.setAttribute(FIND_XML_ATTRIBUTE, resultList);
    }
}