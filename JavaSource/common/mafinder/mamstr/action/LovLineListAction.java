package common.mafinder.mamstr.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.mafinder.mamstr.dto.LovLineListDTO;
import common.mafinder.mamstr.form.LovLineListForm;
import common.mafinder.mamstr.service.LovLineListService;
import common.struts.AuthAction;

/**
 * 무정지라인 팝업
 * @author  kim21017
 * @version $Id: LovLineListAction.java,v 1.0 2015/01/18 07:49:29 kim21017 Exp $
 * @since   1.0
 * 
 * @struts:action path="/lovLineList" name="lovLineListForm"
 *                input="/common/mafinder/mamstr/lovLineList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovLinePopup" path="/common/mafinder/mamstr/lovLinePopup.jsp"
 *                        redirect="false"
 */
public class LovLineListAction extends AuthAction
{
    public static final int LOV_LINE_DEFAULT 	= 1001;
    public static final int LOV_LINE_FIND 		= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovLineListForm lovLineListForm = (LovLineListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovLineListForm.getStrutsAction())
        {
            case LovLineListAction.LOV_LINE_DEFAULT :
                returnActionForward = mapping.findForward("lovLinePopup");
                break;
            case LovLineListAction.LOV_LINE_FIND :
                findLineList(request, lovLineListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovLineListAction.BASE_SET_HEADER:
                setHeader(request, response, lovLineListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
                break;
        }
        
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, LovLineListForm lovLineListForm) throws IOException
    {
        super.setHeader(request, response, lovLineListForm.getListId(),lovLineListForm.getCurrentPageId()); 
    }

    /**
     * TAPOPLINE을 검색한다.
     * @author  kim21017
     * @version $Id: LovLineListAction.java,v 1.2 2014/01/28 07:49:29 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param lovLineListForm
     */
    private void findLineList(HttpServletRequest request,
            LovLineListForm lovLineListForm,HttpServletResponse response) throws IOException
    {
        LovLineListService lovLineListService = (LovLineListService)getBean("lovLineListService");
        LovLineListDTO lovLineListDTO = lovLineListForm.getLovLineListDTO();
        List resultList = lovLineListService.findLineList(lovLineListDTO, getUser(request));
        
        super.makeJsonResult(resultList, request, response, lovLineListForm.getListId());
    	
    }

}