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
import dream.part.iss.wo.dto.PartIssWoItemListDTO;
import dream.part.iss.wo.form.PartIssWoItemListForm;
import dream.part.iss.wo.service.PartIssWoItemListService;

/**
 * 자재출고확정 - 목록 action
 * @author  hyosung
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/partIssWoItemList" name="partIssWoItemListForm"
 *                input="/dream/part/iss/wo/partIssWoItemList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="partIssWoItemList" path="/dream/part/iss/wo/partIssWoItemList.jsp"
 *                        redirect="false"
 */
public class PartIssWoItemListAction extends AuthAction
{
    /** 조회 */
    public static final int PTISSWOITEM_LIST_FIND 	= 1001;
    /** 삭제 */
    public static final int PTISSWOITEM_LIST_DELETE = 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        PartIssWoItemListForm partIssWoItemListForm = (PartIssWoItemListForm) form;
        
        switch (partIssWoItemListForm.getStrutsAction())
        {
            case PartIssWoItemListAction.PTISSWOITEM_LIST_FIND:
            	findPtIssList(request, partIssWoItemListForm, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case PartIssWoItemListAction.BASE_SET_HEADER:
                setHeader(request, response, partIssWoItemListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case PartIssWoItemListAction.BASE_GRID_EXPORT:
            	findPtIssList(request, partIssWoItemListForm,response);
                returnActionForward = new ActionForward("/gridExport");
                break;
            case PartIssWoItemListAction.PTISSWOITEM_LIST_DELETE:
                deleteKey(request, partIssWoItemListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            default:
                returnActionForward = mapping.findForward("partIssWoItemList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, PartIssWoItemListForm partIssWoItemListForm) throws IOException
    {
        super.setHeader(request, response, partIssWoItemListForm.getListId(), partIssWoItemListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param partIssWoItemListAction
     * @throws ServiceException 
     * @throws Exception
     */
    private void findPtIssList(HttpServletRequest request, PartIssWoItemListForm partIssWoItemListForm, HttpServletResponse response) throws IOException, ServiceException
    {
    	PartIssWoItemListService partIssWoItemListService = (PartIssWoItemListService) getBean("partIssWoItemListService");        

    	PartIssWoItemListDTO partIssWoItemListDTO = partIssWoItemListForm.getPartIssWoItemListDTO();
    	partIssWoItemListDTO.setCompNo(getUser(request).getCompNo());
    	
        //리스트를 조회한다.
        List resultList = partIssWoItemListService.findPtIssList(partIssWoItemListDTO,getUser(request));
        super.makeJsonResult(resultList, request, response, partIssWoItemListForm.getListId());
    }
    
    private void deleteKey(HttpServletRequest request, PartIssWoItemListForm partIssWoItemListForm) throws Exception
    {
        PartIssWoItemListService partIssWoItemListService = (PartIssWoItemListService) getBean("partIssWoItemListService");        
        
        String[] deleteRows = partIssWoItemListForm.getDeleteRows();
        
        
        partIssWoItemListService.deleteKey(deleteRows);
        
        setAjaxStatus(request);
    }
}
