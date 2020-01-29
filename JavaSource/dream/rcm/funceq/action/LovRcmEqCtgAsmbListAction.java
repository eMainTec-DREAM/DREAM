package dream.rcm.funceq.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;

import dream.rcm.funceq.dto.LovRcmEqCtgAsmbListDTO;
import dream.rcm.funceq.form.LovRcmEqCtgAsmbListForm;
import dream.rcm.funceq.service.LovRcmEqCtgAsmbListService;

/**
 * 설비종류작업부위 팝업
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * 
 * @struts:action path="/lovRcmEqCtgAsmbList" name="lovRcmEqCtgAsmbListForm"
 *                input="/dream/rcm/funceq/lovRcmEqCtgAsmbList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovRcmEqCtgAsmbPopup" path="/dream/rcm/funceq/lovRcmEqCtgAsmbPopup.jsp"
 *                        redirect="false"
 */
public class LovRcmEqCtgAsmbListAction extends AuthAction
{
    public static final int LOV_EQCTGASMB_DEFAULT 	= 1001;
    public static final int LOV_EQCTGASMB_FIND      = 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovRcmEqCtgAsmbListForm lovRcmEqCtgAsmbListForm = (LovRcmEqCtgAsmbListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovRcmEqCtgAsmbListForm.getStrutsAction())
        {
            case LovRcmEqCtgAsmbListAction.LOV_EQCTGASMB_DEFAULT :
                returnActionForward = mapping.findForward("lovRcmEqCtgAsmbPopup");
                break;
            case LovRcmEqCtgAsmbListAction.LOV_EQCTGASMB_FIND :
                findEqCtgAsmbList(request, lovRcmEqCtgAsmbListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovRcmEqCtgAsmbListAction.BASE_SET_HEADER:
                setHeader(request, response, lovRcmEqCtgAsmbListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
                break;
        }
        
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, LovRcmEqCtgAsmbListForm lovRcmEqCtgAsmbListForm) throws IOException
    {
        super.setHeader(request, response, lovRcmEqCtgAsmbListForm.getListId(),lovRcmEqCtgAsmbListForm.getCurrentPageId()); 
    }

    /**
     * TAEQCTGASMB 를 검색한다.
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param lovRcmEqCtgAsmbListForm
     */
    private void findEqCtgAsmbList(HttpServletRequest request,
            LovRcmEqCtgAsmbListForm lovRcmEqCtgAsmbListForm,HttpServletResponse response) throws IOException
    {
        LovRcmEqCtgAsmbListService lovRcmEqCtgAsmbListService = (LovRcmEqCtgAsmbListService)getBean("lovRcmEqCtgAsmbListService");
        
        LovRcmEqCtgAsmbListDTO lovRcmEqCtgAsmbListDTO = lovRcmEqCtgAsmbListForm.getLovRcmEqCtgAsmbListDTO();
        List resultList = lovRcmEqCtgAsmbListService.findEqCtgAsmbList(lovRcmEqCtgAsmbListDTO, getUser(request));
        
        super.makeJsonResult(resultList, request, response, lovRcmEqCtgAsmbListForm.getListId());
    	
    }

}