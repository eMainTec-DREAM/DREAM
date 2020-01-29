package dream.part.stk.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.part.stk.dto.PartStkSerialListDTO;
import dream.part.stk.form.PartStkSerialListForm;
import dream.part.stk.service.PartStkSerialListService;

/**
 * 자재출고확정 - 목록 action
 * @author  hyosung
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/partStkSerialList" name="partStkSerialListForm"
 *                input="/dream/part/stk/partStkSerialList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="partStkSerialList" path="/dream/part/stk/partStkSerialList.jsp"
 *                        redirect="false"
 */
public class PartStkSerialListAction extends AuthAction
{
    /** 조회 */
    public static final int STKSERIAL_LIST_FIND 	= 1001;
    
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        PartStkSerialListForm partStkSerialListForm = (PartStkSerialListForm) form;
        
        switch (partStkSerialListForm.getStrutsAction())
        {
            case PartStkSerialListAction.STKSERIAL_LIST_FIND:
                findSerialList(request, partStkSerialListForm, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case PartStkSerialListAction.BASE_SET_HEADER:
                setHeader(request, response, partStkSerialListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case PartStkSerialListAction.BASE_GRID_EXPORT:
                findSerialList(request, partStkSerialListForm,response);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("partStkSerialList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, PartStkSerialListForm partStkSerialListForm) throws IOException
    {
        super.setHeader(request, response, partStkSerialListForm.getListId(), partStkSerialListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  hyosung
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param partStkSerialListAction
     * @throws ServiceException 
     * @throws Exception
     */
    private void findSerialList(HttpServletRequest request, PartStkSerialListForm partStkSerialListForm, HttpServletResponse response) throws IOException, ServiceException
    {
    	PartStkSerialListService partStkSerialListService = (PartStkSerialListService) getBean("partStkSerialListService");        

    	PartStkSerialListDTO partStkSerialListDTO = partStkSerialListForm.getPartStkSerialListDTO();
    	partStkSerialListDTO.setCompNo(getUser(request).getCompNo());
    	
        //리스트를 조회한다.
        List resultList = partStkSerialListService.findSerialList(partStkSerialListDTO,getUser(request));
        super.makeJsonResult(resultList, request, response, partStkSerialListForm.getListId());
    }
    
   
}
