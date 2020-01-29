package common.mafinder.mamstr.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.mafinder.mamstr.dto.LovInvtListDTO;
import common.mafinder.mamstr.form.LovInvtListForm;
import common.mafinder.mamstr.service.LovInvtListService;
import common.struts.SuperAuthAction;

/**
 * 투자 목록 팝업
 * @author  js.lee
 * @version $Id: $
 * @since   1.0
 * 
 * @struts:action path="/lovInvtList" name="lovInvtListForm"
 *                input="/common/mafinder/mamstr/lovInvtPopup.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovInvtPopup" path="/common/mafinder/mamstr/lovInvtPopup.jsp"
 *                        redirect="false"
 */
public class LovInvtListAction extends SuperAuthAction
{
    public static final int LOV_INVT_DEFAULT 	    = 1001;
    public static final int LOV_INVT_FIND	 		= 1002;
    
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovInvtListForm lovInvtListForm = (LovInvtListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovInvtListForm.getStrutsAction())
        {
            case LovInvtListAction.LOV_INVT_DEFAULT :
                returnActionForward = mapping.findForward("lovInvtPopup");
                break;
            case LovInvtListAction.LOV_INVT_FIND :
                findInvtList(request, lovInvtListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovInvtListAction.BASE_SET_HEADER:
                setHeader(request, response, lovInvtListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
            	returnActionForward = mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }

	private void setHeader(HttpServletRequest request, HttpServletResponse response, LovInvtListForm lovInvtListForm) throws IOException
    {
        super.setHeader(request, response, lovInvtListForm.getListId(),lovInvtListForm.getCurrentPageId()); 
    }

    /**
     *  투자목록 리스트 검색.
     * @author  js.lee
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param lovInvtListForm
     */
    private void findInvtList(HttpServletRequest request,LovInvtListForm lovInvtListForm,HttpServletResponse response) throws IOException
    {
        LovInvtListService lovInvtListService = (LovInvtListService)getBean("lovInvtListService", request);
        
        LovInvtListDTO lovInvtListDTO = lovInvtListForm.getLovInvtListDTO();
        List resultList = lovInvtListService.findInvtList(lovInvtListDTO, getUser(request), lovInvtListForm);
        
        super.makeJsonResult(resultList, request, response, lovInvtListForm.getListId());
    	
    }

}