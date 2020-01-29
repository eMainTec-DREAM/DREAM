package dream.work.list.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.work.list.dto.WoPlanCommonDTO;
import dream.work.list.dto.WoPlanWoLetDetailDTO;
import dream.work.list.dto.WoPlanWoLetListDTO;
import dream.work.list.form.WoPlanWoLetListForm;
import dream.work.list.service.WoPlanWoLetListService;

/**
 * 작업계획목록 - 안전작업 목록
 * @author  syyang
 * @version $Id$
 * @since   1.0
 * @struts:action path="/woPlanWoLetList" name="woPlanWoLetListForm"
 *                input="/dream/work/list/woPlanWoLetList.jsp" scope="request"
 *                validate="false"
 */
public class WoPlanWoLetListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int WO_PLAN_WO_LET_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int WO_PLAN_WO_LET_LIST_DELETE 		= 7002;
    /** 입력 */
    public static final int WO_PLAN_WO_LET_LIST_INPUT		= 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WoPlanWoLetListForm woPlanWoLetListForm = (WoPlanWoLetListForm) form;
        String strutsAction = request.getParameter("strutsAction");
        if(strutsAction!="") woPlanWoLetListForm.setStrutsAction(Integer.parseInt(strutsAction));
        
        switch (woPlanWoLetListForm.getStrutsAction())
        {
        
            case WoPlanWoLetListAction.WO_PLAN_WO_LET_LIST_FIND:
                findWoLetList(request,response, woPlanWoLetListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WoPlanWoLetListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, woPlanWoLetListForm.getListId(), woPlanWoLetListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WoPlanWoLetListAction.WO_PLAN_WO_LET_LIST_DELETE:
            	deleteWoLetList(request,woPlanWoLetListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WoPlanWoLetListAction.WO_PLAN_WO_LET_LIST_INPUT:
                inputWoLetList(request,woPlanWoLetListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case WoPlanWoLetListAction.BASE_GRID_EXPORT:
            	findWoLetList(request,response, woPlanWoLetListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  syyang
     * @version $Id$
     * @since   1.0
     * 
     * @param request
     * @param woPlanWoLetListForm
     * @throws Exception
     */
    private void findWoLetList(HttpServletRequest request,HttpServletResponse response, WoPlanWoLetListForm woPlanWoLetListForm, boolean excelExport) throws Exception
    {
        WoPlanWoLetListService woPlanWoLetListService = (WoPlanWoLetListService) getBean("woPlanWoLetListService");
        WoPlanCommonDTO woPlanCommonDTO = woPlanWoLetListForm.getWoPlanCommonDTO();
        WoPlanWoLetListDTO woPlanWoLetListDTO = woPlanWoLetListForm.getWoPlanWoLetListDTO();
        
        //Paging
        woPlanWoLetListDTO.setIsLoadMaxCount("Y".equals(woPlanWoLetListForm.getIsLoadMaxCount())?true:false);
        woPlanWoLetListDTO.setFirstRow(woPlanWoLetListForm.getFirstRow());
        woPlanWoLetListDTO.setOrderBy(woPlanWoLetListForm.getOrderBy());
        woPlanWoLetListDTO.setDirection(woPlanWoLetListForm.getDirection());
        
        User user = getUser(request);
        List resultList = woPlanWoLetListService.findWoLetList(woPlanCommonDTO, woPlanWoLetListDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(woPlanWoLetListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = woPlanWoLetListService.findTotalCount(woPlanCommonDTO,woPlanWoLetListDTO,user);
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, woPlanWoLetListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  syyang
     * @version $Id$
     * @since   1.0
     * 
     * @param request
     * @param woPlanWoLetListForm
     * @throws Exception
     */
    private void deleteWoLetList(HttpServletRequest request, WoPlanWoLetListForm woPlanWoLetListForm) throws Exception
    {
    	WoPlanWoLetListService woPlanWoLetListService = (WoPlanWoLetListService) getBean("woPlanWoLetListService");
        
    	String[] deleteRows = woPlanWoLetListForm.getDeleteRows();
    
    	woPlanWoLetListService.deleteWoLetList(deleteRows, getUser(request).getCompNo());
    	
    	setAjaxStatus(request);
    }
    
    /**
     * input
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maWoResultWoLetListForm
     * @throws Exception
     */
    private void inputWoLetList(HttpServletRequest request, WoPlanWoLetListForm woPlanWoLetListForm) throws Exception
    {
        WoPlanWoLetListService woPlanWoLetListService = (WoPlanWoLetListService) getBean("woPlanWoLetListService");
        
        WoPlanCommonDTO woPlanCommonDTO = woPlanWoLetListForm.getWoPlanCommonDTO();
        WoPlanWoLetDetailDTO woPlanWoLetDetailDTO = woPlanWoLetListForm.getWoPlanWoLetDetailDTO();

        
        woPlanWoLetListService.inputWoLetList(woPlanWoLetDetailDTO, woPlanCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }
}