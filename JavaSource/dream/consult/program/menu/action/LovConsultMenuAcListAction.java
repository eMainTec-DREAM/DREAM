package dream.consult.program.menu.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.SuperAuthAction;
import dream.consult.program.menu.dto.LovConsultMenuAcListDTO;
import dream.consult.program.menu.form.LovConsultMenuAcListForm;
import dream.consult.program.menu.service.LovConsultMenuAcListService;

/**
 * 컨설트용 메뉴 팝업
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * 
 * @struts:action path="/lovConsultMenuAcList" name="lovConsultMenuAcListForm"
 *                input="/dream/consult/program/menu/lovConsultMenuAcList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovConsultMenuAcList" path="/dream/consult/program/menu/lovConsultMenuAcList.jsp"
 *                        redirect="false"
 */
public class LovConsultMenuAcListAction extends SuperAuthAction
{
    public static final int LOV_MENU_DEFAULT 	= 1001;
    public static final int LOV_MENU_FIND     = 1002;
    
    public static final int LOV_MENU_AC_FIND     = 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovConsultMenuAcListForm lovConsultMenuAcListForm = (LovConsultMenuAcListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovConsultMenuAcListForm.getStrutsAction())
        {
            case LovConsultMenuAcListAction.LOV_MENU_DEFAULT :
                returnActionForward = mapping.getInputForward();
                break;
            case LovConsultMenuAcListAction.LOV_MENU_FIND :
                findMenuList(request, lovConsultMenuAcListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovConsultMenuAcListAction.LOV_MENU_AC_FIND :
                findMenuAcList(request, lovConsultMenuAcListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovConsultMenuAcListAction.BASE_SET_HEADER:
                setHeader(request, response, lovConsultMenuAcListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
                returnActionForward = mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, LovConsultMenuAcListForm lovConsultMenuAcListForm) throws IOException
    {
        super.setHeader(request, response, lovConsultMenuAcListForm.getListId(),lovConsultMenuAcListForm.getCurrentPageId()); 
    }

    /**
     * TAMENU 를 검색한다.
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param lovConsultMenuAcListForm
     */
    private void findMenuList(HttpServletRequest request,
            LovConsultMenuAcListForm lovConsultMenuAcListForm,HttpServletResponse response) throws IOException
    {
        LovConsultMenuAcListService lovConsultMenuAcListService = (LovConsultMenuAcListService)getBean("lovConsultMenuAcListService");
        
        LovConsultMenuAcListDTO lovConsultMenuAcListDTO = lovConsultMenuAcListForm.getLovConsultMenuAcListDTO();
        List resultList = lovConsultMenuAcListService.findMenuList(lovConsultMenuAcListDTO, getUser(request));
        
        super.makeTreeJsonResult(resultList, request, response, lovConsultMenuAcListForm.getListId());
    	
    }
    
    private void findMenuAcList(HttpServletRequest request,
            LovConsultMenuAcListForm lovConsultMenuAcListForm,HttpServletResponse response) throws IOException
    {
        LovConsultMenuAcListService lovConsultMenuAcListService = (LovConsultMenuAcListService)getBean("lovConsultMenuAcListService");
        
        LovConsultMenuAcListDTO lovConsultMenuAcListDTO = lovConsultMenuAcListForm.getLovConsultMenuAcListDTO();
        List resultList = lovConsultMenuAcListService.findMenuAcList(lovConsultMenuAcListDTO, getUser(request),lovConsultMenuAcListForm);
        
        super.makeTreeJsonResult(resultList, request, response, lovConsultMenuAcListForm.getListId());
        
    }

}