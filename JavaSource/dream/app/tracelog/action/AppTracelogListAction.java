package dream.app.tracelog.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.app.tracelog.dto.AppTracelogCommonDTO;
import dream.app.tracelog.form.AppTracelogListForm;
import dream.app.tracelog.service.AppTracelogListService;

/**
 * TraceLog - List Action
 * 
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/appTracelogList" name="appTracelogListForm"
 *                input="/dream/app/tracelog/appTracelogList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="appTracelogList" path="/dream/app/tracelog/appTracelogList.jsp"
 *                        redirect="false"
 */

public class AppTracelogListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int LIST_DELETE 	= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AppTracelogListForm appTracelogListForm = (AppTracelogListForm) form;
        
        switch (appTracelogListForm.getStrutsAction())
        {
            case AppTracelogListAction.BASE_SET_HEADER:
                setHeader(request, response, appTracelogListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AppTracelogListAction.LIST_FIND:
                findList(request, response, appTracelogListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case AppTracelogListAction.LIST_DELETE:
            	deleteList(request, appTracelogListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case AppTracelogListAction.BASE_GRID_EXPORT:
            	findList(request, response, appTracelogListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("appTracelogList");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, AppTracelogListForm appTracelogListForm) throws IOException
    {
        super.setHeader(request, response, appTracelogListForm.getListId(), appTracelogListForm.getCurrentPageId()); 
    }
    
    /**
     * FIND LIST
     * @author  ghlee
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param appTracelogListForm
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, AppTracelogListForm appTracelogListForm, boolean excelExport) throws Exception
    {
    	AppTracelogListService appTracelogListService = (AppTracelogListService) getBean("appTracelogListService");
    	AppTracelogCommonDTO appTracelogCommonDTO = appTracelogListForm.getAppTracelogCommonDTO();

    	//Paging
    	appTracelogCommonDTO.setIsLoadMaxCount("Y".equals(appTracelogListForm.getIsLoadMaxCount())?true:false);
    	appTracelogCommonDTO.setFirstRow(appTracelogListForm.getFirstRow());
    	appTracelogCommonDTO.setOrderBy(appTracelogListForm.getOrderBy());
    	appTracelogCommonDTO.setDirection(appTracelogListForm.getDirection());
    	
        List resultList = appTracelogListService.findCompTracelogList(appTracelogCommonDTO,getUser(request));
        //Paging
        String totalCount = "";
        if(Integer.parseInt(appTracelogListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = appTracelogListService.findTotalCount(appTracelogCommonDTO,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,appTracelogListForm.getListId(),appTracelogListForm.getCurrentPageId(), appTracelogListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * DELETE LIST
     * @author  ghlee
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param appTracelogListForm
     */
    private void deleteList(HttpServletRequest request, AppTracelogListForm appTracelogListForm) throws Exception
    {
    	AppTracelogListService appTracelogListService = (AppTracelogListService) getBean("appTracelogListService");
        String[] deleteRows = appTracelogListForm.getDeleteRows();
        
        appTracelogListService.deleteCompTracelogList(deleteRows,getUser(request));
        setAjaxStatus(request);
    }
    
}
