package dream.consult.program.menu.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.SuperAuthAction;
import dream.consult.program.menu.dto.LovMenuListDTO;
import dream.consult.program.menu.form.LovMenuListForm;
import dream.consult.program.menu.service.LovMenuListService;

/**
 * 메뉴 팝업
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * 
 * @struts:action path="/lovMenuList" name="lovMenuListForm"
 *                input="/dream/consult/program/menu/lovMenuList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/menuValLov" name="lovMenuListForm"
 *                input="/dream/consult/program/menu/menuValLov.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovMenuPopup" path="/dream/consult/program/menu/lovMenuPopup.jsp"
 *                        redirect="false"
 */
public class LovMenuListAction extends SuperAuthAction
{
    public static final int LOV_MENU_DEFAULT 	= 1001;
    public static final int LOV_MENU_FIND     = 1002;
    
    public static final int LOV_MENU_AC_FIND     = 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovMenuListForm lovMenuListForm = (LovMenuListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovMenuListForm.getStrutsAction())
        {
            case LovMenuListAction.LOV_MENU_DEFAULT :
                returnActionForward = mapping.getInputForward();
                break;
            case LovMenuListAction.LOV_MENU_FIND :
                findMenuList(request, lovMenuListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovMenuListAction.LOV_MENU_AC_FIND :
                findMenuAcList(request, lovMenuListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovMenuListAction.BASE_SET_HEADER:
                setHeader(request, response, lovMenuListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
                returnActionForward = mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, LovMenuListForm lovMenuListForm) throws IOException
    {
        super.setHeader(request, response, lovMenuListForm.getListId(),lovMenuListForm.getCurrentPageId()); 
    }

    /**
     * TAMENU 를 검색한다.
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param lovMenuListForm
     */
    private void findMenuList(HttpServletRequest request,
            LovMenuListForm lovMenuListForm,HttpServletResponse response) throws IOException
    {
        LovMenuListService lovMenuListService = (LovMenuListService)getBean("lovMenuListService");
        
        LovMenuListDTO lovMenuListDTO = lovMenuListForm.getLovMenuListDTO();
        List resultList = lovMenuListService.findMenuList(lovMenuListDTO, getUser(request));
        
        super.makeTreeJsonResult(resultList, request, response, lovMenuListForm.getListId());
    	
    }
    
    private void findMenuAcList(HttpServletRequest request,
            LovMenuListForm lovMenuListForm,HttpServletResponse response) throws IOException
    {
        LovMenuListService lovMenuListService = (LovMenuListService)getBean("lovMenuListService");
        
        LovMenuListDTO lovMenuListDTO = lovMenuListForm.getLovMenuListDTO();
        List resultList = lovMenuListService.findMenuAcList(lovMenuListDTO, getUser(request),lovMenuListForm);
        
        super.makeTreeJsonResult(resultList, request, response, lovMenuListForm.getListId());
        
    }

}