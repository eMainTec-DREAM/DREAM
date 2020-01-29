package dream.work.cal.pmrinsperiod.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.fins.org.json.JSONObject;

import common.bean.User;
import common.struts.AuthAction;
import dream.work.cal.pmrinsperiod.dto.WorkCalPmRInsPeriodCommonDTO;
import dream.work.cal.pmrinsperiod.form.WorkCalPmRInsPeriodListForm;
import dream.work.cal.pmrinsperiod.service.WorkCalPmRInsPeriodListService;

/**
 * 예방작업일정(기간) - 목록 action
 * @author  kim21017
 * @version $Id: MaWoSchedListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/workCalPmRInsPeriodList" name="workCalPmRInsPeriodListForm"
 *                input="/dream/work/cal/pmrinsperiod/workCalPmRInsPeriodList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workCalPmRInsPeriodList" path="/dream/work/cal/pmrinsperiod/workCalPmRInsPeriodList.jsp"
 *                        redirect="false"
 */
public class WorkCalPmRInsPeriodListAction extends AuthAction
{
    /** 조회 */
    public static final int MONTH_SCHED_LIST_FIND 			= 1001;
    /** 삭제 */
    public static final int MONTH_SCHED_LIST_DELETE 		= 7002;

    /** 스케줄 변경 */
    public static final int MONTH_SCHED_CHANGE              = 1006;

    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        WorkCalPmRInsPeriodListForm workCalPmRInsPeriodListForm = (WorkCalPmRInsPeriodListForm) form;

        switch (workCalPmRInsPeriodListForm.getStrutsAction())
        {
            case WorkCalPmRInsPeriodListAction.MONTH_SCHED_LIST_FIND:
                findSchedList(request, workCalPmRInsPeriodListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkCalPmRInsPeriodListAction.BASE_SET_HEADER:
                setHeader(request, response, workCalPmRInsPeriodListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkCalPmRInsPeriodListAction.MONTH_SCHED_LIST_DELETE:
            	deleteSched(request, workCalPmRInsPeriodListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;

            case WorkCalPmRInsPeriodListAction.BASE_GRID_EXPORT:
            	findSchedList(request, workCalPmRInsPeriodListForm, response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            case WorkCalPmRInsPeriodListAction.MONTH_SCHED_CHANGE:
                changeSchedule(request,workCalPmRInsPeriodListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                returnActionForward = mapping.findForward("workCalPmRInsPeriodList");
                break;
        }

        return returnActionForward;
    }

    private void changeSchedule(HttpServletRequest request,WorkCalPmRInsPeriodListForm workCalPmRInsPeriodListForm, HttpServletResponse response) throws Exception
    {
        WorkCalPmRInsPeriodListService workCalPmRInsPeriodListService = (WorkCalPmRInsPeriodListService) getBean("workCalPmRInsPeriodListService");

        List gridList = setGridMap(request);

        workCalPmRInsPeriodListService.updateSchedule(gridList, getUser(request));

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("status", "ok");
        response.getWriter().print(jsonObj.toString());

    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, WorkCalPmRInsPeriodListForm workCalPmRInsPeriodListForm) throws IOException
    {
        super.setHeader(request, response, workCalPmRInsPeriodListForm.getListId(),workCalPmRInsPeriodListForm.getCurrentPageId());
    }

    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaWoSchedListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     *
     * @param request
     * @param workCalPmRInsPeriodListForm
     * @param response
     * @throws Exception
     */
    private void findSchedList(HttpServletRequest request, WorkCalPmRInsPeriodListForm workCalPmRInsPeriodListForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	WorkCalPmRInsPeriodListService workCalPmRInsPeriodListService = (WorkCalPmRInsPeriodListService) getBean("workCalPmRInsPeriodListService");

    	WorkCalPmRInsPeriodCommonDTO workCalPmRInsPeriodCommonDTO = workCalPmRInsPeriodListForm.getWorkCalPmRInsPeriodCommonDTO();

    	//Paging
        workCalPmRInsPeriodCommonDTO.setIsLoadMaxCount("Y".equals(workCalPmRInsPeriodListForm.getIsLoadMaxCount())?true:false);
        workCalPmRInsPeriodCommonDTO.setFirstRow(workCalPmRInsPeriodListForm.getFirstRow());
        workCalPmRInsPeriodCommonDTO.setOrderBy(workCalPmRInsPeriodListForm.getOrderBy());
        workCalPmRInsPeriodCommonDTO.setDirection(workCalPmRInsPeriodListForm.getDirection());
        
        User user = getUser(request);
        //리스트를 조회한다.
        List resultList = workCalPmRInsPeriodListService.findSchedList(workCalPmRInsPeriodCommonDTO ,getUser(request));

        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workCalPmRInsPeriodListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workCalPmRInsPeriodListService.findTotalCount(workCalPmRInsPeriodCommonDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,workCalPmRInsPeriodListForm.getListId(),workCalPmRInsPeriodListForm.getCurrentPageId(), workCalPmRInsPeriodListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
	}


    /**
     * delete
     * @author  kim21017
     * @version $Id: MaWoSchedListAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     *
     * @param request
     * @param workCalPmRInsPeriodListForm
     */
    private void deleteSched(HttpServletRequest request, WorkCalPmRInsPeriodListForm workCalPmRInsPeriodListForm) throws Exception
    {
    	WorkCalPmRInsPeriodListService workCalPmRInsPeriodListService = (WorkCalPmRInsPeriodListService) getBean("workCalPmRInsPeriodListService");

    	String[] deleteRows = workCalPmRInsPeriodListForm.getDeleteRows();

    	workCalPmRInsPeriodListService.deleteSched(deleteRows, getUser(request));

        setAjaxStatus(request);
    }

}
