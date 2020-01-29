package mobile.dream.org.emp.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.SuperAuthAction;
import mobile.dream.org.emp.dto.OrgEmpLovListDTO;
import mobile.dream.org.emp.form.OrgEmpLovListForm;
import mobile.dream.org.emp.service.OrgEmpLovListService;

/**
 * 사원 팝업
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * 
 * @struts:action path="/orgEmpLovList" name="orgEmpLovListForm"
 *                input="/mobile/dream/org/emp/orgEmpLovList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="orgEmpLovPopup" path="/mobile/dream/org/emp/orgEmpLovPopup.jsp"
 *                        redirect="false"
 */
public class OrgEmpLovListAction extends SuperAuthAction
{
    public static final int LOV_EMP_DEFAULT 	= 1001;
    public static final int LOV_EMP_FIND     = 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        OrgEmpLovListForm orgEmpLovListForm = (OrgEmpLovListForm)form;
        ActionForward returnActionForward = null;
        
        switch (orgEmpLovListForm.getStrutsAction())
        {
            case OrgEmpLovListAction.LOV_EMP_DEFAULT :
                returnActionForward = mapping.findForward("orgEmpLovPopup");
                break;
            case OrgEmpLovListAction.LOV_EMP_FIND :
                findEmpList(request, orgEmpLovListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case OrgEmpLovListAction.BASE_SET_HEADER:
                setHeader(request, response, orgEmpLovListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
                break;
        }
        
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, OrgEmpLovListForm orgEmpLovListForm) throws IOException
    {
        super.setHeader(request, response, orgEmpLovListForm.getListId(),orgEmpLovListForm.getCurrentPageId()); 
    }

    /**
     * TAUSER 를 검색한다.
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param orgEmpLovListForm
     */
    private void findEmpList(HttpServletRequest request,
            OrgEmpLovListForm orgEmpLovListForm,HttpServletResponse response) throws IOException
    {
        OrgEmpLovListService orgEmpLovListService = (OrgEmpLovListService)getBean("orgEmpLovListService");
        
        OrgEmpLovListDTO orgEmpLovListDTO = orgEmpLovListForm.getOrgEmpLovListDTO();
        
        List resultList = orgEmpLovListService.findEmpList(orgEmpLovListDTO,getUser(request));
        
        super.makeJsonResult(resultList, request, response, orgEmpLovListForm.getListId());
    	
    }

}