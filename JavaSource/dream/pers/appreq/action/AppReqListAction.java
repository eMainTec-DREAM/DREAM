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
 * 요청문서 - 목록 
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
    /** 조회 */
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
     * DB의 총 건수를 조회한다.
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
        
        // 마스터 리스트를 조회한다.
        int totalCount = appReqListService.findAppReqListCount(appReqCommonDTO);
        
        // 처음 조회할때 가지고 있던 total count를 계속 가지고 있는다.
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
        
        // 마스터 리스트를 조회한다.
        List resultList = appReqListService.findAppReqList(appReqCommonDTO);
        
        // 조회한 List 를 form에 셋팅한다.
        request.setAttribute(FIND_XML_ATTRIBUTE, resultList);
    }
    
}