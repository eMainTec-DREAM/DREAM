package dream.part.iss.emg.req.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.part.iss.emg.req.dto.PartIssEmgReqCommonDTO;
import dream.part.iss.emg.req.form.PartIssEmgReqListForm;
import dream.part.iss.emg.req.service.PartIssEmgReqListService;

/**
 * 부품출고 - List Action
 * 
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/partIssEmgReqList" name="partIssEmgReqListForm"
 *                input="/dream/part/iss/emg/req/partIssEmgReqList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="partIssEmgReqList" path="/dream/part/iss/emg/req/partIssEmgReqList.jsp"
 *                        redirect="false"
 */

public class PartIssEmgReqListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int LIST_DELETE 	= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        PartIssEmgReqListForm partIssEmgReqListForm = (PartIssEmgReqListForm) form;
        
        switch (partIssEmgReqListForm.getStrutsAction())
        {
            case PartIssEmgReqListAction.BASE_SET_HEADER:
                setHeader(request, response, partIssEmgReqListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case PartIssEmgReqListAction.LIST_FIND:
                findList(request, response, partIssEmgReqListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case PartIssEmgReqListAction.LIST_DELETE:
            	deleteList(request, partIssEmgReqListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case PartIssEmgReqListAction.BASE_GRID_EXPORT:
            	findList(request, response, partIssEmgReqListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("partIssEmgReqList");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, PartIssEmgReqListForm partIssEmgReqListForm) throws IOException
    {
        super.setHeader(request, response, partIssEmgReqListForm.getListId(), partIssEmgReqListForm.getCurrentPageId()); 
    }
    
    /**
     * FIND LIST
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param partIssEmgReqListForm
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, PartIssEmgReqListForm partIssEmgReqListForm, boolean excelExport) throws Exception
    {
    	PartIssEmgReqListService partIssEmgReqListService = (PartIssEmgReqListService) getBean("partIssEmgReqListService");
    	PartIssEmgReqCommonDTO partIssEmgReqCommonDTO = partIssEmgReqListForm.getPartIssEmgReqCommonDTO();

    	//Paging
    	partIssEmgReqCommonDTO.setIsLoadMaxCount("Y".equals(partIssEmgReqListForm.getIsLoadMaxCount())?true:false);
    	partIssEmgReqCommonDTO.setFirstRow(partIssEmgReqListForm.getFirstRow());
    	partIssEmgReqCommonDTO.setOrderBy(partIssEmgReqListForm.getOrderBy());
    	partIssEmgReqCommonDTO.setDirection(partIssEmgReqListForm.getDirection());
    	
    	User user = getUser(request);
    	
        List resultList = partIssEmgReqListService.findIssReqList(partIssEmgReqCommonDTO, user);
        //Paging
        String totalCount = "";
        if(Integer.parseInt(partIssEmgReqListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = partIssEmgReqListService.findTotalCount(partIssEmgReqCommonDTO,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,partIssEmgReqListForm.getListId(),partIssEmgReqListForm.getCurrentPageId(), partIssEmgReqListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * DELETE LIST
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param partIssEmgReqListForm
     */
    private void deleteList(HttpServletRequest request, PartIssEmgReqListForm partIssEmgReqListForm) throws Exception
    {
    	PartIssEmgReqListService partIssEmgReqListService = (PartIssEmgReqListService) getBean("partIssEmgReqListService");
        String[] deleteRows = partIssEmgReqListForm.getDeleteRows();
        
    	User user = getUser(request);
        
        partIssEmgReqListService.deleteIssReqList(deleteRows, user);
        setAjaxStatus(request);
    }
    
}
