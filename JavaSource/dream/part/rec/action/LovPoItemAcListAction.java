package dream.part.rec.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.part.rec.dto.LovPoItemAcListDTO;
import dream.part.rec.form.LovPoItemAcListForm;
import dream.part.rec.service.LovPoItemAcListService;

/**
 * 발주 선택 팝업
 * @author  nhkim8548
 * @version $Id: LovPoItemAcListAction.java,v 1.0 2018/09/13 14:57:40 nhkim8548 Exp $
 * @since   1.0
 * 
 * @struts:action path="/lovPoItemAcList" name="lovPoItemAcListForm"
 *                input="/dream/part/rec/lovPoItemAcList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovPoItemAcList" path="/dream/part/rec/lovPoItemAcList.jsp"
 *                        redirect="false"
 */
public class LovPoItemAcListAction extends AuthAction
{
    public static final int LOV_CON_DEFAULT	        = 1001;
    /** 조회 */
    public static final int LOV_PT_REC_FIND			= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
    	LovPoItemAcListForm lovPoItemAcListForm = (LovPoItemAcListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovPoItemAcListForm.getStrutsAction())
        {
        	case LovPoItemAcListAction.LOV_CON_DEFAULT :
        		returnActionForward = mapping.findForward("lovPoItemAcList");
        		break;
        	case LovPoItemAcListAction.BASE_SET_HEADER:
                setHeader(request, response, lovPoItemAcListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovPoItemAcListAction.LOV_PT_REC_FIND :
                findList(request, response, lovPoItemAcListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                returnActionForward = mapping.findForward("lovPoItemAcList");
                break;
        }
        return returnActionForward;
    }
    
    private void setHeader(HttpServletRequest request, HttpServletResponse response, LovPoItemAcListForm lovPoItemAcListForm) throws IOException
    {
        super.setHeader(request, response, lovPoItemAcListForm.getListId(), lovPoItemAcListForm.getCurrentPageId()); 
    }
    /**
     * 발주 선택 Lov List 
     * @author  nhkim8548
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param lovPoItemAcListForm
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, LovPoItemAcListForm lovPoItemAcListForm) throws IOException
    {
        LovPoItemAcListService lovPoItemAcListService = (LovPoItemAcListService)getBean("lovPoItemAcListService");
        LovPoItemAcListDTO lovPoItemAcListDTO = lovPoItemAcListForm.getLovPoItemAcListDTO();
        
        List resultList = lovPoItemAcListService.findList(lovPoItemAcListDTO, getUser(request));
        
        super.makeJsonResult(resultList, request, response, lovPoItemAcListForm.getListId());
    }
}