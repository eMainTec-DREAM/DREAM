package dream.consult.program.linkedfunc.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.SuperAuthAction;
import dream.consult.program.linkedfunc.dto.LovLinkedFuncAcListDTO;
import dream.consult.program.linkedfunc.form.LovLinkedFuncAcListForm;
import dream.consult.program.linkedfunc.service.LovLinkedFuncAcListService;

/**
 * 페이지 팝업
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * 
 * @struts:action path="/lovLinkedFuncAcList" name="lovLinkedFuncAcListForm"
 *                input="/dream/consult/program/linkedfunc/lovLinkedFuncAcList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovLinkedFuncAcList" path="/dream/consult/program/linkedfunc/lovLinkedFuncAcList.jsp"
 *                        redirect="false"
 */
public class LovLinkedFuncAcListAction extends SuperAuthAction
{
    public static final int LOV_DEFAULT 	= 1001;
    public static final int LOV_FIND    	= 1002;
    public static final int LOV_AC_FIND    = 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovLinkedFuncAcListForm lovLinkedFuncAcListForm = (LovLinkedFuncAcListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovLinkedFuncAcListForm.getStrutsAction())
        {
            case LovLinkedFuncAcListAction.LOV_DEFAULT :
                returnActionForward = mapping.getInputForward();
                break;
            case LovLinkedFuncAcListAction.LOV_FIND :
                findLinkedFuncAcList(request, lovLinkedFuncAcListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovLinkedFuncAcListAction.BASE_SET_HEADER:
                setHeader(request, response, lovLinkedFuncAcListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovLinkedFuncAcListAction.LOV_AC_FIND :
                findAcList(request, lovLinkedFuncAcListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
                returnActionForward = mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, LovLinkedFuncAcListForm lovLinkedFuncAcListForm) throws IOException
    {
        super.setHeader(request, response, lovLinkedFuncAcListForm.getListId(),lovLinkedFuncAcListForm.getCurrentPageId()); 
    }

    /**
     * TALINKEDFUNC 를 검색한다.
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param lovLinkedFuncAcListForm
     */
    private void findLinkedFuncAcList(HttpServletRequest request,
            LovLinkedFuncAcListForm lovLinkedFuncAcListForm,HttpServletResponse response) throws IOException
    {
        LovLinkedFuncAcListService lovLinkedFuncAcListService = (LovLinkedFuncAcListService)getBean("lovLinkedFuncAcListService");
        
        LovLinkedFuncAcListDTO lovLinkedFuncAcListDTO = lovLinkedFuncAcListForm.getLovLinkedFuncAcListDTO();
        List resultList = lovLinkedFuncAcListService.findLinkedFuncAcList(lovLinkedFuncAcListDTO, getUser(request));
        
        super.makeJsonResult(resultList, request, response, lovLinkedFuncAcListForm.getListId());
    	
    }
    private void findAcList(HttpServletRequest request,
            LovLinkedFuncAcListForm lovLinkedFuncAcListForm,HttpServletResponse response) throws IOException
    {
        LovLinkedFuncAcListService lovLinkedFuncAcListService = (LovLinkedFuncAcListService)getBean("lovLinkedFuncAcListService");
        
        LovLinkedFuncAcListDTO lovLinkedFuncAcListDTO = lovLinkedFuncAcListForm.getLovLinkedFuncAcListDTO();
        List resultList = lovLinkedFuncAcListService.findAcList(lovLinkedFuncAcListDTO, getUser(request), lovLinkedFuncAcListForm);
        
        super.makeJsonResult(resultList, request, response, lovLinkedFuncAcListForm.getListId());
        
    }

}