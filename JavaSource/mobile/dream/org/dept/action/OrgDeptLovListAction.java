package mobile.dream.org.dept.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.SuperAuthAction;
import mobile.dream.org.dept.dto.OrgDeptLovListDTO;
import mobile.dream.org.dept.form.OrgDeptLovListForm;
import mobile.dream.org.dept.service.OrgDeptLovListService;

/**
 * 부서 팝업
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * 
 * @struts:action path="/orgDeptLovList" name="orgDeptLovListForm"
 *                input="/mobile/dream/org/dept/orgDeptLovList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="orgDeptLovPopup" path="/mobile/dream/org/dept/orgDeptLovPopup.jsp"
 *                        redirect="false"
 */
public class OrgDeptLovListAction extends SuperAuthAction
{
    public static final int LOV_DEPT_DEFAULT 	       = 1001;
    public static final int LOV_DEPT_FIND              = 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        OrgDeptLovListForm orgDeptLovListForm = (OrgDeptLovListForm)form;
        ActionForward returnActionForward = null;
        
        switch (orgDeptLovListForm.getStrutsAction())
        {
            case OrgDeptLovListAction.LOV_DEPT_DEFAULT :
                returnActionForward = mapping.findForward("orgDeptLovPopup");
                break;
            case OrgDeptLovListAction.LOV_DEPT_FIND :
                findDeptList(request, orgDeptLovListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case OrgDeptLovListAction.BASE_SET_HEADER:
                setHeader(request, response, orgDeptLovListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
                break;
        }
        
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, OrgDeptLovListForm orgDeptLovListForm) throws IOException
    {
        super.setHeader(request, response, orgDeptLovListForm.getListId(),orgDeptLovListForm.getCurrentPageId()); 
    }

    /**
     * TAPARTS 를 검색한다.
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param orgDeptLovListForm
     */
    private void findDeptList(HttpServletRequest request,
            OrgDeptLovListForm orgDeptLovListForm,HttpServletResponse response) throws IOException
    {
        OrgDeptLovListService orgDeptLovListService = (OrgDeptLovListService)getBean("orgDeptLovListService");
        
        OrgDeptLovListDTO orgDeptLovListDTO = orgDeptLovListForm.getOrgDeptLovListDTO();
        List resultList = orgDeptLovListService.findDeptList(orgDeptLovListDTO, getUser(request));
        
//        super.makeJsonResult(resultList, request, response, orgDeptLovListForm.getListId());
        super.makeJsonResult(resultList, request, response, orgDeptLovListForm.getListId());
    	
    }

}