package dream.mgr.usrcd.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.SuperAuthAction;
import dream.mgr.usrcd.dto.LovCdUsrdAcListDTO;
import dream.mgr.usrcd.form.LovCdUsrdAcListForm;
import dream.mgr.usrcd.service.LovCdUsrdAcListService;

/**
 * 상세코드 팝업
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * 
 * @struts:action path="/lovCdUsrdAcList" name="lovCdUsrdAcListForm"
 *                input="/dream/mgr/usrcd/lovCdUsrdAcList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovCdUsrdAcList" path="/dream/mgr/usrcd/lovCdUsrdAcList.jsp"
 *                        redirect="false"
 */
public class LovCdUsrdAcListAction extends SuperAuthAction
{
    public static final int LOV_CDUSRD_AC_FIND     = 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovCdUsrdAcListForm lovCdUsrdAcListForm = (LovCdUsrdAcListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovCdUsrdAcListForm.getStrutsAction())
        {
            case LovCdUsrdAcListAction.LOV_CDUSRD_AC_FIND :
                findCdUsrdAcList(request, lovCdUsrdAcListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
                
            case LovCdUsrdAcListAction.BASE_SET_HEADER:
                setHeader(request, response, lovCdUsrdAcListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
            	returnActionForward = mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }
    
	private void setHeader(HttpServletRequest request, HttpServletResponse response, LovCdUsrdAcListForm lovCdUsrdAcListForm) throws IOException
    {
        super.setHeader(request, response, lovCdUsrdAcListForm.getListId(),lovCdUsrdAcListForm.getCurrentPageId()); 
    }
	
	
	/**
     * FIND LIST
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param lovCdUsrdAcListForm
     */
    private void findCdUsrdAcList(HttpServletRequest request, LovCdUsrdAcListForm lovCdUsrdAcListForm,
            HttpServletResponse response) throws IOException {
        
        LovCdUsrdAcListService lovCdUsrdAcListService = (LovCdUsrdAcListService)getBean("lovCdUsrdAcListService");
        
        LovCdUsrdAcListDTO lovCdUsrdAcListDTO = lovCdUsrdAcListForm.getLovCdUsrdAcListDTO();
        
        List resultList = lovCdUsrdAcListService.findCdUsrdAcList(lovCdUsrdAcListDTO,getUser(request), lovCdUsrdAcListForm);
        super.makeTreeJsonResult(resultList, request, response, lovCdUsrdAcListForm.getListId());
    }


}