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
import dream.part.list.dto.PartListBinListDTO;
import dream.part.list.form.PartListBinListForm;
import dream.part.list.service.PartListBinListService;

/**
 * 부품창고 보관위치 - List Action
 * 
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/partListBinList" name="partListBinListForm"
 *                input="/dream/part/list/partListBinList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="partListBinList" path="/dream/part/list/partListBinList.jsp"
 *                        redirect="false"
 */

public class PartListBinListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int LIST_DELETE 	= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        PartListBinListForm partListBinListForm = (PartListBinListForm) form;
        
        switch (partListBinListForm.getStrutsAction())
        {
            case PartListBinListAction.BASE_SET_HEADER:
                setHeader(request, response, partListBinListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case PartListBinListAction.LIST_FIND:
                findList(request, response, partListBinListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case PartListBinListAction.LIST_DELETE:
            	deleteList(request, partListBinListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case PartListBinListAction.BASE_GRID_EXPORT:
            	findList(request, response, partListBinListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("partListBinList");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, PartListBinListForm partListBinListForm) throws IOException
    {
        super.setHeader(request, response, partListBinListForm.getListId(), partListBinListForm.getCurrentPageId()); 
    }
    
    /**
     * FIND LIST
     * @author  cjscjs9
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param partListBinListForm
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, PartListBinListForm partListBinListForm, boolean excelExport) throws Exception
    {
    	PartListBinListService partListBinListService = (PartListBinListService) getBean("partListBinListService");
    	PartListBinListDTO partListBinListDTO = partListBinListForm.getPartListBinListDTO();
    	MaPtMstrCommonDTO maPtMstrCommonDTO = partListBinListForm.getMaPtMstrCommonDTO();

    	//Paging
    	partListBinListDTO.setIsLoadMaxCount("Y".equals(partListBinListForm.getIsLoadMaxCount())?true:false);
    	partListBinListDTO.setFirstRow(partListBinListForm.getFirstRow());
    	partListBinListDTO.setOrderBy(partListBinListForm.getOrderBy());
    	partListBinListDTO.setDirection(partListBinListForm.getDirection());
    	
    	User user = getUser(request);
    	partListBinListDTO.setPartId(maPtMstrCommonDTO.getPartId());
    	
        List resultList = partListBinListService.findPtWhBinList(partListBinListDTO, user);
        
        //Paging
        String totalCount = "";
        if(Integer.parseInt(partListBinListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = partListBinListService.findTotalCount(partListBinListDTO,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,partListBinListForm.getListId(),partListBinListForm.getCurrentPageId(), partListBinListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * DELETE LIST
     * @author  cjscjs9
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param partListBinListForm
     */
    private void deleteList(HttpServletRequest request, PartListBinListForm partListBinListForm) throws Exception
    {
    	PartListBinListService partListBinListService = (PartListBinListService) getBean("partListBinListService");
        String[] deleteRows = partListBinListForm.getDeleteRows();
        
    	User user = getUser(request);
        
        partListBinListService.deletePtWhBinList(deleteRows, user);
        setAjaxStatus(request);
    }
    
}
