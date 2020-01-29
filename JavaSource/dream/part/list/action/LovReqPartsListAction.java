package dream.part.list.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.part.list.dto.LovReqPartsListDTO;
import dream.part.list.form.LovReqPartsListForm;
import dream.part.list.service.LovReqPartsListService;

/**
 * 자재 팝업
 * @author  kim21017
 * @version $Id: LovReqPartsListAction.java,v 1.0 2015/01/18 07:49:29 kim21017 Exp $
 * @since   1.0
 * 
 * @struts:action path="/lovReqPartsList" name="lovReqPartsListForm"
 *                input="/dream/part/list/lovReqPartsList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovReqPartsPopup" path="/dream/part/list/lovReqPartsPopup.jsp"
 *                        redirect="false"
 */
public class LovReqPartsListAction extends AuthAction
{
    public static final int LOV_PARTS_DEFAULT 	= 1001;
    public static final int LOV_PARTS_FIND 		= 1002;

    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovReqPartsListForm lovReqPartsListForm = (LovReqPartsListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovReqPartsListForm.getStrutsAction())
        {
            case LovReqPartsListAction.LOV_PARTS_DEFAULT :
                returnActionForward = mapping.findForward("lovReqPartsPopup");
                break;
            case LovReqPartsListAction.LOV_PARTS_FIND :
                findPartsList(request, lovReqPartsListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovReqPartsListAction.BASE_SET_HEADER:
                setHeader(request, response, lovReqPartsListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
                break;
        }
        
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, LovReqPartsListForm lovReqPartsListForm) throws IOException
    {
        super.setHeader(request, response, lovReqPartsListForm.getListId(),lovReqPartsListForm.getCurrentPageId()); 
    }

    /**
     * TAPARTS 를 검색한다.
     * @author  kim21017
     * @version $Id: LovReqPartsListAction.java,v 1.2 2014/01/28 07:49:29 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param lovReqPartsListForm
     */
    private void findPartsList(HttpServletRequest request,
            LovReqPartsListForm lovReqPartsListForm,HttpServletResponse response) throws IOException
    {
        LovReqPartsListService lovReqPartsListService = (LovReqPartsListService)getBean("lovReqPartsListService");
        
        LovReqPartsListDTO lovReqPartsListDTO = lovReqPartsListForm.getLovReqPartsListDTO();
        List resultList = lovReqPartsListService.findPartsList(lovReqPartsListDTO, getUser(request));
        
        super.makeJsonResult(resultList, request, response, lovReqPartsListForm.getListId());
    	
    }

}