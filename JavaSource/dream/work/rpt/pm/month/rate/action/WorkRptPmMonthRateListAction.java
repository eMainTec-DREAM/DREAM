package dream.work.rpt.pm.month.rate.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.rpt.pm.month.rate.dto.WorkRptPmMonthRateListDTO;
import dream.work.rpt.pm.month.rate.form.WorkRptPmMonthRateListForm;
import dream.work.rpt.pm.month.rate.service.WorkRptPmMonthRateListService;

/**
 * 월별점검실행율 Action
 * @author  sy.yang
 * @version $Id: WorkRptPmMonthRateListAction.java,v 1.0 2015/12/02 09:10:21 sy.yang Exp $
 * @since   1.0
 * @struts:action path="/workRptPmMonthRateList" name="workRptPmMonthRateListForm"
 *                input="/dream/work/rpt/pm/month/rate/workRptPmMonthRateList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workRptPmMonthRateList" path="/dream/work/rpt/pm/month/rate/workRptPmMonthRateList.jsp"
 *                        redirect="false"
 */
public class WorkRptPmMonthRateListAction extends AuthAction
{
    /** 월별점검실행율 조회 */
    public static final int WO_LIST_FIND 		= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        WorkRptPmMonthRateListForm workRptPmMonthRateListForm = (WorkRptPmMonthRateListForm) form;
        
        switch (workRptPmMonthRateListForm.getStrutsAction())
        {
            case WorkRptPmMonthRateListAction.WO_LIST_FIND:
                findWoList(request, workRptPmMonthRateListForm, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                returnActionForward = mapping.findForward("workRptPmMonthRateList");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: WorkRptPmMonthRateListAction.java,v 1.0 2015/12/02 09:10:21 sy.yang Exp $
     * @since   1.0
     * 
     * @param request
     * @param workRptPmMonthRateListForm
     * @param response
     * @throws Exception
     */
    private void findWoList(HttpServletRequest request, WorkRptPmMonthRateListForm workRptPmMonthRateListForm, HttpServletResponse response) throws IOException
    {
    	WorkRptPmMonthRateListService workRptPmMonthRateListService = (WorkRptPmMonthRateListService) getBean("workRptPmMonthRateListService");        

    	WorkRptPmMonthRateListDTO workRptPmMonthRateListDTO = workRptPmMonthRateListForm.getWorkRptPmMonthRateListDTO();
    	workRptPmMonthRateListDTO.setCompNo(getUser(request).getCompNo());
        //리스트를 조회한다.
        List resultList = workRptPmMonthRateListService.findWoList(workRptPmMonthRateListDTO);

        super.makeTreeJsonResult(resultList, request, response, workRptPmMonthRateListForm.getListId());
	}
}
