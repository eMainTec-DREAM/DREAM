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
import dream.work.list.dto.WoPlanPartListDTO;
import dream.work.list.form.WoPlanPartListForm;
import dream.work.list.service.WoPlanPartListService;

/**
 * 작업계획목록 - 투입부품 목록
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @struts:action path="/woPlanPartList" name="woPlanPartListForm"
 *                input="/dream/work/list/woPlanPartList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="woPlanPartList" path="/dream/work/list/woPlanPartList.jsp"
 *                        redirect="false"
 */
public class WoPlanPartListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int WO_RESULT_PART_LIST_FIND 			= 1001;
    /** 삭제 */
    public static final int WO_RESULT_PART_LIST_DELETE 			= 7002;
    /** 입력 */
    public static final int WO_RESULT_PART_LIST_INPUT           = 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WoPlanPartListForm woPlanPartListForm = (WoPlanPartListForm) form;
        String strutsAction = request.getParameter("strutsAction");
        if(strutsAction!="") woPlanPartListForm.setStrutsAction(Integer.parseInt(strutsAction));
        
        switch (woPlanPartListForm.getStrutsAction())
        {
        
            case WoPlanPartListAction.WO_RESULT_PART_LIST_FIND:
                findPartList(request,response, woPlanPartListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WoPlanPartListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, woPlanPartListForm.getListId(), woPlanPartListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WoPlanPartListAction.WO_RESULT_PART_LIST_DELETE:
            	deletePartList(request,woPlanPartListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WoPlanPartListAction.WO_RESULT_PART_LIST_INPUT:
                inputPartList(request,woPlanPartListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case WoPlanPartListAction.BASE_GRID_EXPORT:
            	findPartList(request,response, woPlanPartListForm, true);
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
     * @param woPlanPartListForm
     * @throws Exception
     */
    private void findPartList(HttpServletRequest request,HttpServletResponse response, WoPlanPartListForm woPlanPartListForm, boolean excelExport) throws Exception
    {
        WoPlanPartListService woPlanPartListService = (WoPlanPartListService) getBean("woPlanPartListService");
        WoPlanCommonDTO woPlanCommonDTO = woPlanPartListForm.getWoPlanCommonDTO();
        WoPlanPartListDTO woPlanPartListDTO = woPlanPartListForm.getWoPlanPartListDTO();
        
        //Paging
        woPlanPartListDTO.setIsLoadMaxCount("Y".equals(woPlanPartListForm.getIsLoadMaxCount())?true:false);
        woPlanPartListDTO.setFirstRow(woPlanPartListForm.getFirstRow());
        woPlanPartListDTO.setOrderBy(woPlanPartListForm.getOrderBy());
        woPlanPartListDTO.setDirection(woPlanPartListForm.getDirection());
        
        User user = getUser(request);
        List resultList = woPlanPartListService.findPartList(woPlanCommonDTO,woPlanPartListDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(woPlanPartListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = woPlanPartListService.findTotalCount(woPlanCommonDTO, woPlanPartListDTO,user);
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, woPlanPartListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param request
     * @param woPlanPartListForm
     * @throws Exception
     */
    private void deletePartList(HttpServletRequest request, WoPlanPartListForm woPlanPartListForm) throws Exception
    {
    	WoPlanPartListService woPlanPartListService = (WoPlanPartListService) getBean("woPlanPartListService");
        
    	String[] deleteRows = woPlanPartListForm.getDeleteRows();
    
    	woPlanPartListService.deletePartList(deleteRows, getUser(request).getCompNo());
    	
    	setAjaxStatus(request);
    }
    
    /**
     * input
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param woPlanPartListForm
     * @throws Exception
     */
    private void inputPartList(HttpServletRequest request, WoPlanPartListForm woPlanPartListForm) throws Exception
    {
        WoPlanPartListService woPlanPartListService = (WoPlanPartListService) getBean("woPlanPartListService");
        
        WoPlanCommonDTO woPlanCommonDTO = woPlanPartListForm.getWoPlanCommonDTO();
        WoPlanPartListDTO woPlanPartListDTO = woPlanPartListForm.getWoPlanPartListDTO();

        woPlanCommonDTO.setCompNo(getUser(request).getCompNo());
        
        woPlanPartListService.inputPartList(woPlanCommonDTO, woPlanPartListDTO);
        
        setAjaxStatus(request);
    }
}