package dream.work.list.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.list.dto.WoPlanCommonDTO;
import dream.work.list.form.WoPlanListForm;
import dream.work.list.service.WoPlanListService;

/**
 * 작업계획목록 - 목록 action
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @struts:action path="/woPlanList" name="woPlanListForm"
 *                input="/dream/work/list/woPlanList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="woPlanList" path="/dream/work/list/woPlanList.jsp"
 *                        redirect="false"
 */
public class WoPlanListAction extends AuthAction
{
    /** 조회 */
    public static final int WO_PLAN_LIST_FIND 	 = 1001;
    /** 삭제 */
    public static final int WO_PLAN_LIST_DELETE  = 7002;

    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        WoPlanListForm woPlanListForm = (WoPlanListForm) form;
        
        switch (woPlanListForm.getStrutsAction())
        {
            case WoPlanListAction.WO_PLAN_LIST_FIND:
            	findWoResultMstrList(request, woPlanListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WoPlanListAction.BASE_SET_HEADER:
                setHeader(request, response, woPlanListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WoPlanListAction.WO_PLAN_LIST_DELETE:
            	deleteWoResultMstr(request, woPlanListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case WoPlanListAction.BASE_GRID_EXPORT:
            	findWoResultMstrList(request, woPlanListForm,response, true);
            	returnActionForward =new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, WoPlanListForm woPlanListForm) throws IOException
    {
        super.setHeader(request, response, woPlanListForm.getListId(), woPlanListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param request
     * @param woPlanListForm
     * @param excelExport 
     * @throws Exception
     */
    private void findWoResultMstrList(HttpServletRequest request, WoPlanListForm woPlanListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	WoPlanListService woPlanListService = (WoPlanListService) getBean("woPlanListService");        

    	WoPlanCommonDTO woPlanCommonDTO = woPlanListForm.getWoPlanCommonDTO();
    	woPlanCommonDTO.setCompNo(getUser(request).getCompNo());
    	
    	woPlanCommonDTO.setIsLoadMaxCount("Y".equals(woPlanListForm.getIsLoadMaxCount())?true:false);
    	woPlanCommonDTO.setFirstRow(woPlanListForm.getFirstRow());
    	woPlanCommonDTO.setOrderBy(woPlanListForm.getOrderBy());
    	woPlanCommonDTO.setDirection(woPlanListForm.getDirection());
    	
        //리스트를 조회한다.
        List resultList = woPlanListService.findWoResultMstrList(woPlanCommonDTO,getUser(request));
        
        String totalCount = "";
        if(Integer.parseInt(woPlanListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = woPlanListService.findTotalCount(woPlanCommonDTO,getUser(request),"");

        if(excelExport) super.makeGridExport(resultList, request, response,woPlanListForm.getListId(),woPlanListForm.getCurrentPageId(), woPlanListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * delete
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param woPlanListForm
     * @param request
     */
    private void deleteWoResultMstr(HttpServletRequest request, WoPlanListForm woPlanListForm) throws Exception
    {
    	WoPlanListService woPlanListService = (WoPlanListService) getBean("woPlanListService");
    	String[] deleteRows = woPlanListForm.getDeleteRows();    // sheet 내역
        
        woPlanListService.deleteWoResultMstr(deleteRows,getUser(request));
        
        setAjaxStatus(request);
    }
}
