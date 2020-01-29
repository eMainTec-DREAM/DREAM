package dream.consult.comp.warehouse.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.consult.comp.warehouse.dto.LovWhToolListDTO;
import dream.consult.comp.warehouse.form.LovWhToolListForm;
import dream.consult.comp.warehouse.service.LovWhToolListService;

/**
 * 사용창고 팝업
 * @author  kim21017
 * @version $Id: LovWhToolListAction.java,v 1.0 2015/01/18 07:49:29 kim21017 Exp $
 * @since   1.0
 * 
 * @struts:action path="/lovWhToolList" name="lovWhToolListForm"
 *                input="/dream/consult/comp/warehouse/lovWhToolList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovWhToolPopup" path="/dream/consult/comp/warehouse/lovWhToolPopup.jsp"
 *                        redirect="false"
 */
public class LovWhToolListAction extends AuthAction
{
    public static final int LOV_WH_DEFAULT 	= 1001;
    public static final int LOV_WH_FIND 		= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovWhToolListForm lovWhToolListForm = (LovWhToolListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovWhToolListForm.getStrutsAction())
        {
            case LovWhToolListAction.LOV_WH_DEFAULT :
                returnActionForward = mapping.findForward("lovWhToolPopup");
                break;
            case LovWhToolListAction.LOV_WH_FIND :
                findWhList(request, lovWhToolListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovWhToolListAction.BASE_SET_HEADER:
                setHeader(request, response, lovWhToolListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
                break;
        }
        
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, LovWhToolListForm lovWhToolListForm) throws IOException
    {
        super.setHeader(request, response, lovWhToolListForm.getListId(),lovWhToolListForm.getCurrentPageId()); 
    }

    /**
     * 사용창고 리스트 검색.
     * @author  kim21017
     * @version $Id: LovWhToolListAction.java,v 1.2 2014/01/28 07:49:29 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param lovWhToolListForm
     */
    private void findWhList(HttpServletRequest request,
            LovWhToolListForm lovWhToolListForm,HttpServletResponse response) throws IOException
    {
        LovWhToolListService lovWhToolListService = (LovWhToolListService)getBean("lovWhToolListService");
        
        LovWhToolListDTO lovWhToolListDTO = lovWhToolListForm.getLovWhToolListDTO();
        List resultList = lovWhToolListService.findWhList(lovWhToolListDTO, getUser(request));
        
        super.makeJsonResult(resultList, request, response, lovWhToolListForm.getListId());
    	
    }

}