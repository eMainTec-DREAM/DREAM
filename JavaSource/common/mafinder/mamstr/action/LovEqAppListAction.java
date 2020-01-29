package common.mafinder.mamstr.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.mafinder.mamstr.dto.LovEqAppListDTO;
import common.mafinder.mamstr.form.LovEqAppListForm;
import common.mafinder.mamstr.service.LovEqAppListService;
import common.struts.AuthAction;

/**
 * 설비기안품의서 팝업
 * @author  kim21017
 * @version $Id: LovEqAppListAction.java,v 1.0 2015/01/18 07:49:29 kim21017 Exp $
 * @since   1.0
 * 
 * @struts:action path="/lovEqAppList" name="lovEqAppListForm"
 *                input="/common/mafinder/mamstr/lovEqAppList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovEqAppPopup" path="/common/mafinder/mamstr/lovEqAppPopup.jsp"
 *                        redirect="false"
 */
public class LovEqAppListAction extends AuthAction
{
    public static final int LOV_EQAPP_DEFAULT 	= 1001;
    public static final int LOV_EQAPP_FIND 		= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovEqAppListForm lovEqAppListForm = (LovEqAppListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovEqAppListForm.getStrutsAction())
        {
            case LovEqAppListAction.LOV_EQAPP_DEFAULT :
                returnActionForward = mapping.findForward("lovEqAppPopup");
                break;
            case LovEqAppListAction.LOV_EQAPP_FIND :
                findEqAppList(request, lovEqAppListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovEqAppListAction.BASE_SET_HEADER:
                setHeader(request, response, lovEqAppListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
                break;
        }
        
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, LovEqAppListForm lovEqAppListForm) throws IOException
    {
        super.setHeader(request, response, lovEqAppListForm.getListId(),lovEqAppListForm.getCurrentPageId()); 
    }

    /**
     * 설비기안품의서 리스트 검색.
     * @author  kim21017
     * @version $Id: LovEqAppListAction.java,v 1.2 2014/01/28 07:49:29 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param lovEqAppListForm
     */
    private void findEqAppList(HttpServletRequest request,
            LovEqAppListForm lovEqAppListForm,HttpServletResponse response) throws IOException
    {
        LovEqAppListService lovEqAppListService = (LovEqAppListService)getBean("lovEqAppListService");
        
        LovEqAppListDTO lovEqAppListDTO = lovEqAppListForm.getLovEqAppListDTO();
        List resultList = lovEqAppListService.findEqAppList(lovEqAppListDTO, getUser(request));
        
        super.makeJsonResult(resultList, request, response, lovEqAppListForm.getListId());
    	
    }

}