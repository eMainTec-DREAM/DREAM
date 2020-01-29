package dream.part.iss.wo.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.part.iss.wo.dto.PartIssWoPartListDTO;
import dream.part.iss.wo.form.PartIssWoPartListForm;
import dream.part.iss.wo.service.PartIssWoPartListService;

/**
 * 자재출고WO - 목록 action
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/partIssWoPartList" name="partIssWoPartListForm"
 *                input="/dream/part/iss/wo/partIssWoPartList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="partIssWoPartList" path="/dream/part/iss/wo/partIssWoPartList.jsp"
 *                        redirect="false"
 */
public class PartIssWoPartListAction extends AuthAction
{
    /** 조회 */
    public static final int WOPART_LIST_FIND 	= 1001;
    /** 삭제 */
    public static final int WOPART_LIST_DELETE  = 1002;
    /** 연결 */
    public static final int WOPART_LIST_LINK    = 1003;
    /** 추가 */
    public static final int WOPART_LIST_ADD     = 1004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        PartIssWoPartListForm partIssWoPartListForm = (PartIssWoPartListForm) form;
        
        switch (partIssWoPartListForm.getStrutsAction())
        {
            case PartIssWoPartListAction.WOPART_LIST_FIND:
            	findWoPartList(request, partIssWoPartListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case PartIssWoPartListAction.BASE_SET_HEADER:
                setHeader(request, response, partIssWoPartListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case PartIssWoPartListAction.BASE_GRID_EXPORT:
            	findWoPartList(request, partIssWoPartListForm,response ,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            case PartIssWoPartListAction.WOPART_LIST_DELETE:
                deleteWoPartList(request, partIssWoPartListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case PartIssWoPartListAction.WOPART_LIST_LINK:
                linkWoPartList(request, partIssWoPartListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case PartIssWoPartListAction.WOPART_LIST_ADD:
                addWoPartList(request, partIssWoPartListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            default:
                returnActionForward = mapping.findForward("partIssWoPartList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, PartIssWoPartListForm partIssWoPartListForm) throws IOException
    {
        super.setHeader(request, response, partIssWoPartListForm.getListId(), partIssWoPartListForm.getCurrentPageId()); 
    }
    
    private void findWoPartList(HttpServletRequest request, PartIssWoPartListForm partIssWoPartListForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	PartIssWoPartListService partIssWoPartListService = (PartIssWoPartListService) getBean("partIssWoPartListService");        

    	PartIssWoPartListDTO partIssWoPartListDTO = partIssWoPartListForm.getPartIssWoPartListDTO();
    	
    	//Paging
        partIssWoPartListDTO.setIsLoadMaxCount("Y".equals(partIssWoPartListForm.getIsLoadMaxCount())?true:false);
        partIssWoPartListDTO.setFirstRow(partIssWoPartListForm.getFirstRow());
        partIssWoPartListDTO.setOrderBy(partIssWoPartListForm.getOrderBy());
        partIssWoPartListDTO.setDirection(partIssWoPartListForm.getDirection());
    	
        //리스트를 조회한다.
        List resultList = partIssWoPartListService.findWoPartList(partIssWoPartListDTO,getUser(request));
        
        String totalCount = "";
        if(Integer.parseInt(partIssWoPartListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = partIssWoPartListService.findWoPartTotalCount(partIssWoPartListDTO,getUser(request));
        
        if(excelExport) super.makeGridExport(resultList, request, response,partIssWoPartListForm.getListId(),partIssWoPartListForm.getCurrentPageId(), partIssWoPartListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    private void deleteWoPartList(HttpServletRequest request, PartIssWoPartListForm partIssWoPartListForm) throws Exception
    {
        PartIssWoPartListService partIssWoPartListService = (PartIssWoPartListService) getBean("partIssWoPartListService");        
        
        String[] deleteRows = partIssWoPartListForm.getDeleteRows();
        
        partIssWoPartListService.deleteWoPartList(deleteRows, getUser(request));
        
        setAjaxStatus(request);
    }
    
    private void linkWoPartList(HttpServletRequest request, PartIssWoPartListForm partIssWoPartListForm) throws Exception
    {
        PartIssWoPartListService partIssWoPartListService = (PartIssWoPartListService) getBean("partIssWoPartListService");   
        
        PartIssWoPartListDTO partIssWoPartListDTO = partIssWoPartListForm.getPartIssWoPartListDTO();
        
        partIssWoPartListService.linkWoPartList(partIssWoPartListDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    private void addWoPartList(HttpServletRequest request, PartIssWoPartListForm partIssWoPartListForm) throws Exception
    {
        PartIssWoPartListService partIssWoPartListService = (PartIssWoPartListService) getBean("partIssWoPartListService");   
        
        PartIssWoPartListDTO partIssWoPartListDTO = partIssWoPartListForm.getPartIssWoPartListDTO();
        
        partIssWoPartListService.addWoPartList(partIssWoPartListDTO, getUser(request));
        
        setAjaxStatus(request);
    }
}
