package dream.work.rpt.pm.monthnew.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.work.rpt.pm.monthnew.dto.WorkRptPmMonthNewListDTO;
import dream.work.rpt.pm.monthnew.form.WorkRptPmMonthNewListForm;
import dream.work.rpt.pm.monthnew.service.WorkRptPmMonthNewListService;

/**
 * �ű����˵����Ȳ Action
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @struts:action path="/workRptPmMonthNewList" name="workRptPmMonthNewListForm"
 *                input="/dream/work/rpt/pm/monthnew/workRptPmMonthNewList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workRptPmMonthNewList" path="/dream/work/rpt/pm/monthnew/workRptPmMonthNewList.jsp"
 *                        redirect="false"
 */
public class WorkRptPmMonthNewListAction extends AuthAction
{
    /** �ű����˵����Ȳ ����Ʈ ��ȸ */
    public static final int LIST_FIND			= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        WorkRptPmMonthNewListForm workRptPmMonthNewListForm = (WorkRptPmMonthNewListForm) form;
        
        switch (workRptPmMonthNewListForm.getStrutsAction())
        {
	        case WorkRptPmMonthNewListAction.LIST_FIND:
	            findConnList(request, workRptPmMonthNewListForm, response);
	            returnActionForward = mapping.findForward("jsonPage");
	            break;
            default:
                returnActionForward = mapping.findForward("workRptPmMonthNewList");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param request
     * @param workRptPmMonthNewListForm
     * @param response
     * @throws Exception
     */
    private void findConnList(HttpServletRequest request, WorkRptPmMonthNewListForm workRptPmMonthNewListForm, HttpServletResponse response) throws IOException
    {
    	WorkRptPmMonthNewListService workRptPmMonthNewListService = (WorkRptPmMonthNewListService) getBean("workRptPmMonthNewListService");        

    	WorkRptPmMonthNewListDTO workRptPmMonthNewListDTO = workRptPmMonthNewListForm.getWorkRptPmMonthNewListDTO();
    	workRptPmMonthNewListDTO.setCompNo(getUser(request).getCompNo());
    	
    	User loginUser = getUser(request);
        //����Ʈ�� ��ȸ�Ѵ�.
        List resultList = workRptPmMonthNewListService.findConnList(workRptPmMonthNewListDTO, loginUser);

        super.makeTreeJsonResult(resultList, request, response, workRptPmMonthNewListForm.getListId());
	}
}
