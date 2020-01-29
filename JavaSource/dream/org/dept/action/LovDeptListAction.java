package dream.org.dept.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.SuperAuthAction;
import dream.org.dept.dto.LovDeptListDTO;
import dream.org.dept.form.LovDeptListForm;
import dream.org.dept.service.LovDeptListService;

/**
 * 부서 팝업
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * 
 * @struts:action path="/lovDeptList" name="lovDeptListForm"
 *                input="/dream/org/dept/lovDeptPopup.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/deptValLov" name="lovDeptListForm"
 *                input="/dream/org/dept/deptValLov.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovDeptPopup" path="/dream/org/dept/lovDeptPopup.jsp"
 *                        redirect="false"
 */
public class LovDeptListAction extends SuperAuthAction
{
    public static final int LOV_DEPT_DEFAULT 	       = 1001;
    public static final int LOV_DEPT_FIND              = 1002;
    
    public static final int LOV_DEPT_AC_FIND           = 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovDeptListForm lovDeptListForm = (LovDeptListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovDeptListForm.getStrutsAction())
        {
            case LovDeptListAction.LOV_DEPT_DEFAULT :
                returnActionForward = mapping.getInputForward();
                break;
            case LovDeptListAction.LOV_DEPT_FIND :
                findDeptList(request, lovDeptListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovDeptListAction.LOV_DEPT_AC_FIND :
                findDeptAcList(request, lovDeptListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovDeptListAction.BASE_SET_HEADER:
                setHeader(request, response, lovDeptListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
            	returnActionForward = mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }

    private void findDeptAcList(HttpServletRequest request, LovDeptListForm lovDeptListForm,
			HttpServletResponse response) throws IOException 
    {
        LovDeptListService lovDeptListService = (LovDeptListService)getBean("lovDeptListService");
        
        LovDeptListDTO lovDeptListDTO = lovDeptListForm.getLovDeptListDTO();
        List resultList = lovDeptListService.findDeptAcList(lovDeptListDTO, getUser(request), lovDeptListForm);
        
//        super.makeJsonResult(resultList, request, response, lovDeptListForm.getListId());
        super.makeTreeJsonResult(resultList, request, response, lovDeptListForm.getListId());
    	
    }

	private void setHeader(HttpServletRequest request, HttpServletResponse response, LovDeptListForm lovDeptListForm) throws IOException
    {
        super.setHeader(request, response, lovDeptListForm.getListId(),lovDeptListForm.getCurrentPageId()); 
    }

    /**
     * TAPARTS 를 검색한다.
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param lovDeptListForm
     */
    private void findDeptList(HttpServletRequest request,
            LovDeptListForm lovDeptListForm,HttpServletResponse response) throws IOException
    {
        LovDeptListService lovDeptListService = (LovDeptListService)getBean("lovDeptListService");
        
        LovDeptListDTO lovDeptListDTO = lovDeptListForm.getLovDeptListDTO();
        List resultList = lovDeptListService.findDeptList(lovDeptListDTO, getUser(request));
        
//        super.makeJsonResult(resultList, request, response, lovDeptListForm.getListId());
        super.makeTreeJsonResult(resultList, request, response, lovDeptListForm.getListId());
    	
    }

}