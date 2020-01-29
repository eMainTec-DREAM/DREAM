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
import dream.work.list.dto.WoPlanCraftDetailDTO;
import dream.work.list.dto.WoPlanCraftListDTO;
import dream.work.list.form.WoPlanCraftListForm;
import dream.work.list.service.WoPlanCraftListService;

/**
 * 작업계획목록 - 작업자 목록
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @struts:action path="/woPlanCraftList" name="woPlanCraftListForm"
 *                input="/dream/work/list/woPlanCraftList.jsp" scope="request"
 *                validate="false"
 */
public class WoPlanCraftListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int WO_RESULT_CRAFT_LIST_FIND 			= 1001;
    /** 삭제 */
    public static final int WO_RESULT_CRAFT_LIST_DELETE 		= 7002;
    /** 입력 */
    public static final int WO_RESULT_CRAFT_LIST_INPUT          = 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WoPlanCraftListForm woPlanCraftListForm = (WoPlanCraftListForm) form;
        String strutsAction = request.getParameter("strutsAction");
        if(strutsAction!="") woPlanCraftListForm.setStrutsAction(Integer.parseInt(strutsAction));
        
        switch (woPlanCraftListForm.getStrutsAction())
        {
        
            case WoPlanCraftListAction.WO_RESULT_CRAFT_LIST_FIND:
                findCraftList(request,response, woPlanCraftListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WoPlanCraftListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, woPlanCraftListForm.getListId(), woPlanCraftListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WoPlanCraftListAction.WO_RESULT_CRAFT_LIST_DELETE:
            	deleteCraftList(request,woPlanCraftListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WoPlanCraftListAction.WO_RESULT_CRAFT_LIST_INPUT:
                inputCraftList(request,woPlanCraftListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case WoPlanCraftListAction.BASE_GRID_EXPORT:
            	findCraftList(request,response, woPlanCraftListForm, true);
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
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param request
     * @param woPlanCraftListForm
     * @throws Exception
     */
    private void findCraftList(HttpServletRequest request,HttpServletResponse response, WoPlanCraftListForm woPlanCraftListForm, boolean excelExport) throws Exception
    {
        WoPlanCraftListService woPlanCraftListService = (WoPlanCraftListService) getBean("woPlanCraftListService");
        WoPlanCommonDTO woPlanCommonDTO = woPlanCraftListForm.getWoPlanCommonDTO();
        WoPlanCraftListDTO woPlanCraftListDTO = woPlanCraftListForm.getWoPlanCraftListDTO();
        
        //Paging
        woPlanCraftListDTO.setIsLoadMaxCount("Y".equals(woPlanCraftListForm.getIsLoadMaxCount())?true:false);
        woPlanCraftListDTO.setFirstRow(woPlanCraftListForm.getFirstRow());
        woPlanCraftListDTO.setOrderBy(woPlanCraftListForm.getOrderBy());
        woPlanCraftListDTO.setDirection(woPlanCraftListForm.getDirection());
        
        User user = getUser(request);
        List resultList = woPlanCraftListService.findCraftList(woPlanCommonDTO, woPlanCraftListDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(woPlanCraftListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = woPlanCraftListService.findTotalCount(woPlanCommonDTO,woPlanCraftListDTO,user);
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, woPlanCraftListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param request
     * @param woPlanCraftListForm
     * @throws Exception
     */
    private void deleteCraftList(HttpServletRequest request, WoPlanCraftListForm woPlanCraftListForm) throws Exception
    {
    	WoPlanCraftListService woPlanCraftListService = (WoPlanCraftListService) getBean("woPlanCraftListService");
        
    	String[] deleteRows = woPlanCraftListForm.getDeleteRows();
    
    	woPlanCraftListService.deleteCraftList(deleteRows, getUser(request).getCompNo());
    	
    	setAjaxStatus(request);
    }
    
    /**
     * input
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maWoResultCraftListForm
     * @throws Exception
     */
    private void inputCraftList(HttpServletRequest request, WoPlanCraftListForm woPlanCraftListForm) throws Exception
    {
        WoPlanCraftListService woPlanCraftListService = (WoPlanCraftListService) getBean("woPlanCraftListService");
        
        WoPlanCommonDTO woPlanCommonDTO = woPlanCraftListForm.getWoPlanCommonDTO();
        
        WoPlanCraftDetailDTO woPlanCraftDetailDTO = woPlanCraftListForm.getWoPlanCraftDetailDTO();

        woPlanCommonDTO.setCompNo(getUser(request).getCompNo());
        
        woPlanCraftListService.inputCraftList(woPlanCraftDetailDTO, woPlanCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }
}