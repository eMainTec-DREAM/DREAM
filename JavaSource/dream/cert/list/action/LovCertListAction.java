package dream.cert.list.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.cert.list.dto.LovCertListDTO;
import dream.cert.list.form.LovCertListForm;
import dream.cert.list.service.LovCertListService;

/**
 * 자격증 LOV
 * @author  hyosung
 * @version $Id: LovCertListAction.java,v 1.0 2015/01/18 07:49:29 hyosung Exp $
 * @since   1.0
 * 
 * @struts:action path="/lovCertList" name="lovCertListForm"
 *                input="/dream/cert/list/lovCertList.jsp" scope="request"
 *                validate="false"
 */
public class LovCertListAction extends AuthAction
{
    public static final int LOV_DEFAULT 	= 1001;
    public static final int LOV_AC_FIND 	= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovCertListForm lovCertListForm = (LovCertListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovCertListForm.getStrutsAction())
        {
            case LovCertListAction.LOV_DEFAULT :
                returnActionForward = mapping.findForward("lovCertList");
                break;
            case LovCertListAction.LOV_AC_FIND :
                findCertList(request, lovCertListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovCertListAction.BASE_SET_HEADER:
                setHeader(request, response, lovCertListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
                returnActionForward =mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, LovCertListForm lovCertListForm) throws IOException
    {
        super.setHeader(request, response, lovCertListForm.getListId(),lovCertListForm.getCurrentPageId()); 
    }

    /**
     * TACERTLIST을 검색한다.
     * @author  hyosung
     * @version $Id: LovCertListAction.java,v 1.2 2014/01/28 07:49:29 hyosung Exp $
     * @since   1.0
     * 
     * @param request
     * @param lovCertListForm
     */
    private void findCertList(HttpServletRequest request,LovCertListForm lovCertListForm,HttpServletResponse response) throws IOException
    {
        LovCertListService lovCertListService = (LovCertListService)getBean("lovCertListService");
        LovCertListDTO lovCertListDTO = lovCertListForm.getLovCertListDTO();
        List resultList = lovCertListService.findCertList(lovCertListDTO, getUser(request),lovCertListForm);
        
        super.makeJsonResult(resultList, request, response, lovCertListForm.getListId());
    	
    }

}