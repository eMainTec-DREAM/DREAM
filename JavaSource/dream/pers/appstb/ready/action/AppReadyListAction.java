package dream.pers.appstb.ready.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.pers.appstb.ready.dto.AppReadyCommonDTO;
import dream.pers.appstb.ready.form.AppReadyListForm;
import dream.pers.appstb.ready.service.AppReadyListService;

/**
 * ���繮�� - ��� 
 * @author  javaworker
 * @version $Id: AppReadyListAction.java,v 1.1 2013/08/30 09:11:16 javaworker Exp $
 * @since   1.0
 * @struts:action path="/appReadyList" name="appReadyListForm"
 *                input="/dream/pers/appstb/ready/appReadyList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/appReadyPopup" name="appReadyListForm"
 *                input="/dream/pers/appstb/ready/appReadyPopup.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="appReadyList" path="/dream/pers/appstb/ready/appReadyList.jsp"
 *                        redirect="false"
 */
public class AppReadyListAction extends AuthAction
{
    /** ��ȸ */
    public static final int APP_PRC_FIND     = 8001;
    /** ���� */
    public static final int APP_PRC_DELETE   = 1002;
    
    /** ����ó�� */
    public static final int APP_READY_ACTION = 6003;
    /** �ݷ�ó�� */
    public static final int APP_READY_REJECT = 6004;
    /** ������Ȯ��ó��. */
    public static final int APP_READY_REF    = 1005;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AppReadyListForm appReadyListForm = (AppReadyListForm) form;
        
        super.updateAudit(appReadyListForm.getAppReadyCommonDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (appReadyListForm.getStrutsAction())
        {
            case AppReadyListAction.APP_PRC_DELETE:
                deleteLine(request, appReadyListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case AppReadyListAction.APP_READY_ACTION:
                approveLine(request, appReadyListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case AppReadyListAction.APP_READY_REF:
                referenceLine(request, appReadyListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case AppReadyListAction.APP_READY_REJECT:
                rejectLine(request, appReadyListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case AppReadyListAction.APP_PRC_FIND:
                findMasterList(request, appReadyListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AppReadyListAction.BASE_SET_HEADER:
                super.setHeader(request, response, appReadyListForm.getListId(), appReadyListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }
    
    /**
     * �ݷ�ó��
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param appReadyListForm
     */
    private void rejectLine(HttpServletRequest request, AppReadyListForm appReadyListForm) throws Exception
    {
        AppReadyListService appReadyListService = (AppReadyListService) getBean("appReadyListService");        
        String[] apprusrIds = appReadyListForm.getDeleteRows();    // sheet ����
        String[] apprlistIds = appReadyListForm.getDeleteRowsExt();
        
        
        appReadyListService.rejectLine(apprusrIds,apprlistIds,getUser(request), appReadyListForm.getAppReadyCommonDTO());
        
        setAjaxStatus(request);
    }

    /**
     * ����ó��
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param appReadyListForm
     */
    private void approveLine(HttpServletRequest request, AppReadyListForm appReadyListForm) throws Exception
    {
        AppReadyListService appReadyListService = (AppReadyListService) getBean("appReadyListService");        
        String[] apprusrIds = appReadyListForm.getDeleteRows(); // sheet ����
        String[] apprlistIds = appReadyListForm.getDeleteRowsExt();
        
        appReadyListService.approveLine(apprusrIds,apprlistIds,getUser(request), appReadyListForm.getAppReadyCommonDTO());
        
        setAjaxStatus(request);
    }
    
    private void referenceLine(HttpServletRequest request, AppReadyListForm appReadyListForm) throws Exception
    {
    	AppReadyListService appReadyListService = (AppReadyListService) getBean("appReadyListService");        
    	String[] apprusrIds = appReadyListForm.getDeleteRows();    // sheet ����
    	String[] apprlistIds = appReadyListForm.getDeleteRowsExt();
    	
    	appReadyListService.referenceLine(apprusrIds,apprlistIds,getUser(request), appReadyListForm.getAppReadyCommonDTO());
    	
    	setAjaxStatus(request);
    }

    private void deleteLine(HttpServletRequest request, AppReadyListForm appReadyListForm) throws Exception
    {
        AppReadyListService appReadyListService = (AppReadyListService) getBean("appReadyListService");        
        String[] deleteRows = appReadyListForm.getDeleteRows();    // sheet ����
        
        appReadyListService.deleteLine(deleteRows,getUser(request).getCompNo());
        
        setAjaxStatus(request);
    }

    /**
     * grid find
     * @author  javaworker
     * @version $Id: AppReadyListAction.java,v 1.1 2013/08/30 09:11:16 javaworker Exp $
     * @since   1.0
     * 
     * @param request
     * @param appReadyListForm
     * @throws IOException 
     * @throws Exception
     */
    private void findMasterList(HttpServletRequest request, AppReadyListForm appReadyListForm, HttpServletResponse response, boolean excelExport) throws IOException 
    {
        AppReadyListService appReadyListService = (AppReadyListService) getBean("appReadyListService");        

        AppReadyCommonDTO appReadyCommonDTO = appReadyListForm.getAppReadyCommonDTO();
        
        //Paging
        appReadyCommonDTO.setIsLoadMaxCount("Y".equals(appReadyListForm.getIsLoadMaxCount())?true:false);
        appReadyCommonDTO.setFirstRow(appReadyListForm.getFirstRow());
        appReadyCommonDTO.setOrderBy(appReadyListForm.getOrderBy());
        appReadyCommonDTO.setDirection(appReadyListForm.getDirection());

        // ������ ����Ʈ�� ��ȸ�Ѵ�.
        List resultList = appReadyListService.findAppReadyList(appReadyCommonDTO, getUser(request));
        
        //Paging
        String totalCount = "";
                
        if(Integer.parseInt(appReadyListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = appReadyListService.findTotalCount(appReadyCommonDTO,getUser(request));
                
        if(excelExport) super.makeGridExport(resultList, request, response, appReadyListForm.getListId(),appReadyListForm.getCurrentPageId(), appReadyListForm.getFileName());      
        // ��ȸ�� List �� form�� �����Ѵ�.
        else super.makeJsonResult(resultList, request, response, totalCount);
    }

}