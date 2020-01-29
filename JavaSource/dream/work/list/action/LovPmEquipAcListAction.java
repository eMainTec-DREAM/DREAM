package dream.work.list.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.SuperAuthAction;
import dream.work.list.dto.LovPmEquipAcListDTO;
import dream.work.list.form.LovPmEquipAcListForm;
import dream.work.list.service.LovPmEquipAcListService;

/**
 * 생산품목 팝업
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * 
 * @struts:action path="/lovPmEquipAcList" name="lovPmEquipAcListForm"
 *                input="/dream/work/list/lovPmEquipAcList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovPmEquipAcList" path="/dream/work/list/lovPmEquipAcList.jsp"
 *                        redirect="false"
 */
public class LovPmEquipAcListAction extends SuperAuthAction
{
    public static final int LOV_AC_FIND     = 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovPmEquipAcListForm lovPmEquipAcListForm = (LovPmEquipAcListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovPmEquipAcListForm.getStrutsAction())
        {
            case LovPmEquipAcListAction.LOV_AC_FIND :
                findPmEquipAcAcList(request, lovPmEquipAcListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
                
            case LovPmEquipAcListAction.BASE_SET_HEADER:
                setHeader(request, response, lovPmEquipAcListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
            	returnActionForward = mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }
    
	private void setHeader(HttpServletRequest request, HttpServletResponse response, LovPmEquipAcListForm lovPmEquipAcListForm) throws IOException
    {
        super.setHeader(request, response, lovPmEquipAcListForm.getListId(),lovPmEquipAcListForm.getCurrentPageId()); 
    }
	
	
	/**
     * FIND LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param lovPmEquipAcListForm
     */
    private void findPmEquipAcAcList(HttpServletRequest request, LovPmEquipAcListForm lovPmEquipAcListForm,
            HttpServletResponse response) throws IOException {
        
        LovPmEquipAcListService lovPmEquipAcListService = (LovPmEquipAcListService)getBean("lovPmEquipAcListService");
        
        LovPmEquipAcListDTO lovPmEquipAcListDTO = lovPmEquipAcListForm.getLovPmEquipAcListDTO();
        
        List resultList = lovPmEquipAcListService.findPmEquipAcAcList(lovPmEquipAcListDTO,getUser(request), lovPmEquipAcListForm);
        
        super.makeJsonResult(resultList, request, response, lovPmEquipAcListForm.getListId());
    }


}