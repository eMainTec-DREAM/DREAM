package dream.part.list.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.part.list.dto.MaPtMstrCommonDTO;
import dream.part.list.dto.PartListSafQtyListDTO;
import dream.part.list.form.PartListSafQtyListForm;
import dream.part.list.service.PartListSafQtyListService;

/**
 * 부품창고 보관위치 - List Action
 * 
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/partListSafQtyList" name="partListSafQtyListForm"
 *                input="/dream/part/list/partListSafQtyList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="partListSafQtyList" path="/dream/part/list/partListSafQtyList.jsp"
 *                        redirect="false"
 */

public class PartListSafQtyListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int LIST_DELETE 	= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        PartListSafQtyListForm partListSafQtyListForm = (PartListSafQtyListForm) form;
        
        switch (partListSafQtyListForm.getStrutsAction())
        {
            case PartListSafQtyListAction.BASE_SET_HEADER:
                setHeader(request, response, partListSafQtyListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case PartListSafQtyListAction.LIST_FIND:
                findList(request, response, partListSafQtyListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case PartListSafQtyListAction.LIST_DELETE:
            	deleteList(request, partListSafQtyListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case PartListSafQtyListAction.BASE_GRID_EXPORT:
            	findList(request, response, partListSafQtyListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("partListSafQtyList");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, PartListSafQtyListForm partListSafQtyListForm) throws IOException
    {
        super.setHeader(request, response, partListSafQtyListForm.getListId(), partListSafQtyListForm.getCurrentPageId()); 
    }
    
    /**
     * FIND LIST
     * @author  cjscjs9
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param partListSafQtyListForm
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, PartListSafQtyListForm partListSafQtyListForm, boolean excelExport) throws Exception
    {
    	PartListSafQtyListService partListSafQtyListService = (PartListSafQtyListService) getBean("partListSafQtyListService");
    	PartListSafQtyListDTO partListSafQtyListDTO = partListSafQtyListForm.getPartListSafQtyListDTO();
    	MaPtMstrCommonDTO maPtMstrCommonDTO = partListSafQtyListForm.getMaPtMstrCommonDTO();

    	//Paging
    	partListSafQtyListDTO.setIsLoadMaxCount("Y".equals(partListSafQtyListForm.getIsLoadMaxCount())?true:false);
    	partListSafQtyListDTO.setFirstRow(partListSafQtyListForm.getFirstRow());
    	partListSafQtyListDTO.setOrderBy(partListSafQtyListForm.getOrderBy());
    	partListSafQtyListDTO.setDirection(partListSafQtyListForm.getDirection());
    	
    	User user = getUser(request);
    	partListSafQtyListDTO.setPartId(maPtMstrCommonDTO.getPartId());
    	
        List resultList = partListSafQtyListService.findPtWhEmpList(partListSafQtyListDTO, user);
        
        //Paging
        String totalCount = "";
        if(Integer.parseInt(partListSafQtyListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = partListSafQtyListService.findTotalCount(partListSafQtyListDTO,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,partListSafQtyListForm.getListId(),partListSafQtyListForm.getCurrentPageId(), partListSafQtyListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * DELETE LIST
     * @author  cjscjs9
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param partListSafQtyListForm
     */
    private void deleteList(HttpServletRequest request, PartListSafQtyListForm partListSafQtyListForm) throws Exception
    {
    	PartListSafQtyListService partListSafQtyListService = (PartListSafQtyListService) getBean("partListSafQtyListService");
        String[] deleteRows = partListSafQtyListForm.getDeleteRows();
        
    	User user = getUser(request);
        
        partListSafQtyListService.deletePtWhEmpList(deleteRows, user);
        setAjaxStatus(request);
    }
    
}
