package dream.app.not.action;

import java.util.Collection;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.app.not.dto.AppNotCommonDTO;
import dream.app.not.form.AppNotListForm;
import dream.app.not.service.AppNotListService;

/**
 * 통보문서 - 목록 
 * @author  javaworker
 * @version $Id: AppNotListAction.java,v 1.1 2013/08/30 09:14:35 javaworker Exp $
 * @since   1.0
 * @struts:action path="/appNotList" name="appNotListForm"
 *                input="/dream/app/not/appNotList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="appNotList" path="/dream/app/not/appNotList.jsp"
 *                        redirect="false"
 */
public class AppNotListAction extends AuthAction
{
    /** 조회 */
    public static final int APP_NOT_FIND = 1001;
    /** 통보문서확인 */
    public static final int APP_NOT_CONFIRM = 2001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AppNotListForm appNotListForm = (AppNotListForm) form;
        
        switch (appNotListForm.getStrutsAction())
        {
            case AppNotListAction.APP_NOT_CONFIRM :
                confirmAppNot(request, appNotListForm);    
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case AppNotListAction.APP_NOT_FIND:
                setTotalCount(request, appNotListForm);
                findMasterList(request, appNotListForm, true);
                returnActionForward = mapping.findForward("gridList");
                break;
            case AppNotListAction.BASE_GRID_EXPORT:
                findMasterList(request, appNotListForm, false);
                returnActionForward = new ActionForward("/gridExport", false);
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }
        return returnActionForward;
    }
    
    /**
     * 통보문서 확인
     * @author  javaworker
     * @version $Id: AppNotListAction.java,v 1.1 2013/08/30 09:14:35 javaworker Exp $
     * @since   1.0
     * 
     * @param request
     * @param appNotListForm
     */
    private void confirmAppNot(HttpServletRequest request,
            AppNotListForm appNotListForm)
    {
        AppNotListService appNotListService = (AppNotListService) getBean("appNotListService");
        
        List appNotList = appNotListForm.getAppNotList();
        AppNotCommonDTO appNotCommonDTO = appNotListForm.getAppNotCommonDTO();
        appNotCommonDTO.setEnterBy(getUser(request).getUserId());
        
        appNotListService.confirmAppNot(appNotList, appNotCommonDTO);
        
        setAjaxStatus(request);
    }

    /**
     * DB의 총 건수를 조회한다.
     * @author  javaworker
     * @version $Id: AppNotListAction.java,v 1.1 2013/08/30 09:14:35 javaworker Exp $
     * @since   1.0
     * 
     * @param request
     * @param appNotListForm
     */
    private void setTotalCount(HttpServletRequest request, AppNotListForm appNotListForm)
    {
        AppNotListService appNotListService = (AppNotListService) getBean("appNotListService");

        AppNotCommonDTO appNotCommonDTO = appNotListForm.getAppNotCommonDTO();
        appNotCommonDTO.setEnterBy(getUser(request).getUserId());
        
        // 마스터 리스트를 조회한다.
        int totalCount = appNotListService.findAppNotListCount(appNotCommonDTO);
        
        // 처음 조회할때 가지고 있던 total count를 계속 가지고 있는다.
        request.setAttribute(TOTAL_COUNT_ATTRIBUTE, totalCount+"");
    }
    
    /**
     * grid find
     * @author  javaworker
     * @version $Id: AppNotListAction.java,v 1.1 2013/08/30 09:14:35 javaworker Exp $
     * @since   1.0
     * 
     * @param request
     * @param appNotListForm
     * @throws Exception
     */
    private void findMasterList(HttpServletRequest request, AppNotListForm appNotListForm, boolean loadMaxCount) 
    {
        AppNotListService appNotListService = (AppNotListService) getBean("appNotListService");        

        AppNotCommonDTO appNotCommonDTO = appNotListForm.getAppNotCommonDTO();
        appNotCommonDTO.setEnterBy(getUser(request).getUserId());
        appNotCommonDTO.setIsLoadMaxCount(loadMaxCount);
        
        // 마스터 리스트를 조회한다.
        List resultList = appNotListService.findAppNotList(appNotCommonDTO);
        
        // 조회한 List 를 form에 셋팅한다.
        request.setAttribute(FIND_XML_ATTRIBUTE, resultList);
    }
}