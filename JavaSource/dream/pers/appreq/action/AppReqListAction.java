package dream.pers.appreq.action;

import java.util.Collection;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.pers.appreq.dto.AppReqCommonDTO;
import dream.pers.appreq.form.AppReqListForm;
import dream.pers.appreq.service.AppReqListService;

/**
 * ��û���� - ��� 
 * @author  javaworker
 * @version $Id: AppReqListAction.java,v 1.1 2013/08/30 09:13:19 javaworker Exp $
 * @since   1.0
 * @struts:action path="/appReqList" name="appReqListForm"
 *                input="/dream/pers/appreq/appReqList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="appReqList" path="/dream/pers/appreq/appReqList.jsp"
 *                        redirect="false"
 */
public class AppReqListAction extends AuthAction
{
    /** ��ȸ */
    public static final int APP_REQ_FIND = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AppReqListForm appReqListForm = (AppReqListForm) form;
        
        switch (appReqListForm.getStrutsAction())
        {
            case AppReqListAction.APP_REQ_FIND:
                setTotalCount(request, appReqListForm);
                findMasterList(request, appReqListForm, true);
                returnActionForward = mapping.findForward("gridList");
                break;
            case AppReqListAction.BASE_GRID_EXPORT:
                findMasterList(request, appReqListForm, false);
                returnActionForward = new ActionForward("/gridExport", false);
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }
        return returnActionForward;
    }
    
    /**
     * DB�� �� �Ǽ��� ��ȸ�Ѵ�.
     * @author  javaworker
     * @version $Id: AppReqListAction.java,v 1.1 2013/08/30 09:13:19 javaworker Exp $
     * @since   1.0
     * 
     * @param request
     * @param appReqListForm
     */
    private void setTotalCount(HttpServletRequest request, AppReqListForm appReqListForm)
    {
        AppReqListService appReqListService = (AppReqListService) getBean("appReqListService");

        AppReqCommonDTO appReqCommonDTO = appReqListForm.getAppReqCommonDTO();
        appReqCommonDTO.setEnterBy(getUser(request).getUserId());
        
        // ������ ����Ʈ�� ��ȸ�Ѵ�.
        int totalCount = appReqListService.findAppReqListCount(appReqCommonDTO);
        
        // ó�� ��ȸ�Ҷ� ������ �ִ� total count�� ��� ������ �ִ´�.
        request.setAttribute(TOTAL_COUNT_ATTRIBUTE, totalCount+"");
    }
    
    /**
     * grid find
     * @author  javaworker
     * @version $Id: AppReqListAction.java,v 1.1 2013/08/30 09:13:19 javaworker Exp $
     * @since   1.0
     * 
     * @param request
     * @param appReqListForm
     * @throws Exception
     */
    private void findMasterList(HttpServletRequest request, AppReqListForm appReqListForm, boolean loadMaxCount) 
    {
        AppReqListService appReqListService = (AppReqListService) getBean("appReqListService");        

        AppReqCommonDTO appReqCommonDTO = appReqListForm.getAppReqCommonDTO();
        appReqCommonDTO.setEnterBy(getUser(request).getUserId());
        appReqCommonDTO.setIsLoadMaxCount(loadMaxCount);
        
        // ������ ����Ʈ�� ��ȸ�Ѵ�.
        List resultList = appReqListService.findAppReqList(appReqCommonDTO);
        
        // ��ȸ�� List �� form�� �����Ѵ�.
        request.setAttribute(FIND_XML_ATTRIBUTE, resultList);
    }
    
}