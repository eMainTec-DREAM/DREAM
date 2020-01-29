package dream.part.adj.stkmove.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.part.adj.stkmove.dto.PartAdjStkMoveCommonDTO;
import dream.part.adj.stkmove.form.PartAdjStkMoveListForm;
import dream.part.adj.stkmove.service.PartAdjStkMoveListService;

/**
 * 재고이동 - 목록 action
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/partAdjStkMoveList" name="partAdjStkMoveListForm"
 *                input="/dream/part/adj/stkmove/partAdjStkMoveList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="partAdjStkMoveList" path="/dream/part/adj/stkmove/partAdjStkMoveList.jsp"
 *                        redirect="false"
 */
public class PartAdjStkMoveListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int LIST_DELETE 	= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        PartAdjStkMoveListForm partAdjStkMoveListForm = (PartAdjStkMoveListForm) form;
        
        switch (partAdjStkMoveListForm.getStrutsAction())
        {
            case PartAdjStkMoveListAction.LIST_FIND:
                findList(request, partAdjStkMoveListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case PartAdjStkMoveListAction.BASE_SET_HEADER:
                setHeader(request, response, partAdjStkMoveListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case PartAdjStkMoveListAction.LIST_DELETE:
                deleteList(request, partAdjStkMoveListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case PartAdjStkMoveListAction.BASE_GRID_EXPORT:
            	findList(request, partAdjStkMoveListForm, response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("partAdjStkMoveList");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, PartAdjStkMoveListForm partAdjStkMoveListForm) throws IOException
    {
        super.setHeader(request, response, partAdjStkMoveListForm.getListId(), partAdjStkMoveListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param partAdjStkMoveListForm
     * @throws IOException 
     * @throws Exception
     */
    private void findList(HttpServletRequest request, PartAdjStkMoveListForm partAdjStkMoveListForm, HttpServletResponse response, boolean excelExport) throws Exception 
    {
    	PartAdjStkMoveListService partAdjStkMoveListService = (PartAdjStkMoveListService) getBean("partAdjStkMoveListService");        

    	PartAdjStkMoveCommonDTO partAdjStkMoveCommonDTO = partAdjStkMoveListForm.getPartAdjStkMoveCommonDTO();
    	partAdjStkMoveCommonDTO.setCompNo(getUser(request).getCompNo());
        
    	
    	//Paging
        partAdjStkMoveCommonDTO.setIsLoadMaxCount("Y".equals(partAdjStkMoveListForm.getIsLoadMaxCount())?true:false);
        partAdjStkMoveCommonDTO.setFirstRow(partAdjStkMoveListForm.getFirstRow());
        partAdjStkMoveCommonDTO.setOrderBy(partAdjStkMoveListForm.getOrderBy());
        partAdjStkMoveCommonDTO.setDirection(partAdjStkMoveListForm.getDirection());
        
        User user = getUser(request);
        //리스트를 조회한다.
        List resultList = partAdjStkMoveListService.findList(partAdjStkMoveCommonDTO,getUser(request));
 
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(partAdjStkMoveListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = partAdjStkMoveListService.findTotalCount(partAdjStkMoveCommonDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,partAdjStkMoveListForm.getListId(),partAdjStkMoveListForm.getCurrentPageId(), partAdjStkMoveListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param partAdjStkMoveListForm
     * @param request
     */
    private void deleteList(HttpServletRequest request, PartAdjStkMoveListForm partAdjStkMoveListForm) throws Exception
    {
    	PartAdjStkMoveListService partAdjStkMoveListService = (PartAdjStkMoveListService) getBean("partAdjStkMoveListService");        

    	String[] deleteRows = partAdjStkMoveListForm.getDeleteRows();    // sheet 내역
        
    	partAdjStkMoveListService.deleteList(deleteRows,getUser(request));
        
        setAjaxStatus(request);
    }
    
}
