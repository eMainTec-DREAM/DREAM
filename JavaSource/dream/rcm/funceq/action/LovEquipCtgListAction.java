package dream.rcm.funceq.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.rcm.funceq.dto.LovEquipCtgListDTO;
import dream.rcm.funceq.form.LovEquipCtgListForm;
import dream.rcm.funceq.service.LovEquipCtgListService;

/**
 * 설비 팝업
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * 
 * @struts:action path="/lovEquipCtgList" name="lovEquipCtgListForm"
 *                input="/dream/rcm/funceq/lovEquipCtgList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovEquipCtgPopup" path="/dream/rcm/funceq/lovEquipCtgPopup.jsp"
 *                        redirect="false"
 */
public class LovEquipCtgListAction extends AuthAction
{
    public static final int LOV_EQUIP_DEFAULT 	= 1001;
    public static final int LOV_EQUIP_FIND     = 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovEquipCtgListForm lovEquipCtgListForm = (LovEquipCtgListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovEquipCtgListForm.getStrutsAction())
        {
            case LovEquipCtgListAction.LOV_EQUIP_DEFAULT :
                returnActionForward = mapping.findForward("lovEquipCtgPopup");
                break;
            case LovEquipCtgListAction.LOV_EQUIP_FIND :
                findEquipList(request, lovEquipCtgListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovEquipCtgListAction.BASE_SET_HEADER:
                setHeader(request, response, lovEquipCtgListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
                break;
        }
        
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, LovEquipCtgListForm lovEquipCtgListForm) throws IOException
    {
        super.setHeader(request, response, lovEquipCtgListForm.getListId(),lovEquipCtgListForm.getCurrentPageId()); 
    }

    /**
     * TAPARTS 를 검색한다.
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param lovEquipCtgListForm
     */
    private void findEquipList(HttpServletRequest request,
            LovEquipCtgListForm lovEquipCtgListForm,HttpServletResponse response) throws IOException
    {
        LovEquipCtgListService lovEquipCtgListService = (LovEquipCtgListService)getBean("lovEquipCtgListService");
        
        LovEquipCtgListDTO lovEquipCtgListDTO = lovEquipCtgListForm.getLovEquipCtgListDTO();
        List resultList = lovEquipCtgListService.findEquipList(lovEquipCtgListDTO, getUser(request));
        
        super.makeJsonResult(resultList, request, response, lovEquipCtgListForm.getListId());
    	
    }

}