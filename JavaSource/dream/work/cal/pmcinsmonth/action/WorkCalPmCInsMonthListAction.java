package dream.work.cal.pmcinsmonth.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.fins.org.json.JSONObject;

import common.bean.ResponseDTO;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.work.cal.pmcinsmonth.dto.WorkCalPmCInsMonthCommonDTO;
import dream.work.cal.pmcinsmonth.form.WorkCalPmCInsMonthListForm;
import dream.work.cal.pmcinsmonth.service.WorkCalPmCInsMonthListService;

/**
 * 월간예방일정 - 목록 action
 * @author  kim21017
 * @version $Id: WorkCalPmCInsMonthListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/workCalPmCInsMonthList" name="workCalPmCInsMonthListForm"
 *                input="/dream/work/cal/pmcinsmonth/workCalPmCInsMonthList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workCalPmCInsMonthList" path="/dream/work/cal/pmcinsmonth/workCalPmCInsMonthList.jsp"
 *                        redirect="false"
 */
public class WorkCalPmCInsMonthListAction extends AuthAction
{
    /** 조회 */
    public static final int MONTH_SCHED_LIST_FIND 			= 1001;
    /** 삭제 */
    public static final int MONTH_SCHED_LIST_DELETE 		= 7002;
    /** 스케쥴조회 */
    public static final int MONTH_SCHED_FIND				= 1003;
    /** 월단위 확정 */
    public static final int MONTH_SCHED_MONTHLY_SCHEDULED	= 1004;
    /** 일단위 확정 */
    public static final int MONTH_SCHED_DAILY_SCHEDULED		= 1005;
    /** 스케줄 변경 */
    public static final int MONTH_SCHED_CHANGE              = 6006;

    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        WorkCalPmCInsMonthListForm workCalPmCInsMonthListForm = (WorkCalPmCInsMonthListForm) form;

        switch (workCalPmCInsMonthListForm.getStrutsAction())
        {
            case WorkCalPmCInsMonthListAction.MONTH_SCHED_LIST_FIND:
                findSchedList(request, workCalPmCInsMonthListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkCalPmCInsMonthListAction.BASE_SET_HEADER:
                setHeader(request, response, workCalPmCInsMonthListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkCalPmCInsMonthListAction.MONTH_SCHED_LIST_DELETE:
            	deleteSched(request, workCalPmCInsMonthListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case WorkCalPmCInsMonthListAction.MONTH_SCHED_MONTHLY_SCHEDULED:
            	monthlyScheduled(request, response, workCalPmCInsMonthListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case WorkCalPmCInsMonthListAction.MONTH_SCHED_DAILY_SCHEDULED:
            	dailyScheduled(request, response, workCalPmCInsMonthListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case WorkCalPmCInsMonthListAction.MONTH_SCHED_FIND:
            	findSchedule(request,workCalPmCInsMonthListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkCalPmCInsMonthListAction.BASE_GRID_EXPORT:
            	findSchedList(request, workCalPmCInsMonthListForm, response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            case WorkCalPmCInsMonthListAction.MONTH_SCHED_CHANGE:
                changeSchedule(request,workCalPmCInsMonthListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                returnActionForward = mapping.findForward("workCalPmCInsMonthList");
                break;
        }

        return returnActionForward;
    }

    private void changeSchedule(HttpServletRequest request,WorkCalPmCInsMonthListForm workCalPmCInsMonthListForm, HttpServletResponse response) throws IOException
    {
        WorkCalPmCInsMonthListService workCalPmCInsMonthListService = (WorkCalPmCInsMonthListService) getBean("workCalPmCInsMonthListService");

        List gridList = setGridMap(request);

        workCalPmCInsMonthListService.updateSchedule(gridList, getUser(request));

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("status", "ok");
        response.getWriter().print(jsonObj.toString());

    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, WorkCalPmCInsMonthListForm workCalPmCInsMonthListForm) throws IOException
    {
        super.setHeader(request, response, workCalPmCInsMonthListForm.getListId(),workCalPmCInsMonthListForm.getCurrentPageId());
    }

    /**
     * grid find
     * @author  kim2107
     * @version $Id: WorkCalPmCInsMonthListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     *
     * @param request
     * @param workCalPmCInsMonthListForm
     * @param response
     * @throws Exception
     */
    private void findSchedList(HttpServletRequest request, WorkCalPmCInsMonthListForm workCalPmCInsMonthListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	WorkCalPmCInsMonthListService workCalPmCInsMonthListService = (WorkCalPmCInsMonthListService) getBean("workCalPmCInsMonthListService");

    	WorkCalPmCInsMonthCommonDTO workCalPmCInsMonthCommonDTO = workCalPmCInsMonthListForm.getWorkCalPmCInsMonthCommonDTO();
    	workCalPmCInsMonthCommonDTO.setCompNo(getUser(request).getCompNo());
    	
    	//Paging
    	workCalPmCInsMonthCommonDTO.setIsLoadMaxCount("Y".equals(workCalPmCInsMonthListForm.getIsLoadMaxCount())?true:false);
    	workCalPmCInsMonthCommonDTO.setFirstRow(workCalPmCInsMonthListForm.getFirstRow());
    	workCalPmCInsMonthCommonDTO.setOrderBy(workCalPmCInsMonthListForm.getOrderBy());
    	workCalPmCInsMonthCommonDTO.setDirection(workCalPmCInsMonthListForm.getDirection());
    	
        //리스트를 조회한다.
        List resultList = workCalPmCInsMonthListService.findSchedList(workCalPmCInsMonthCommonDTO,getUser(request));

        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workCalPmCInsMonthListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workCalPmCInsMonthListService.findTotalCount(workCalPmCInsMonthCommonDTO,getUser(request));
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, workCalPmCInsMonthListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
	}

    /**
     * schedule find
     * @author  kim2107
     * @version $Id: WorkCalPmCInsMonthListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     *
     * @param request
     * @param workCalPmCInsMonthListForm
     * @param response
     * @throws Exception
     */
    private void findSchedule(HttpServletRequest request, WorkCalPmCInsMonthListForm workCalPmCInsMonthListForm, HttpServletResponse response) throws Exception
    {
    	WorkCalPmCInsMonthListService workCalPmCInsMonthListService = (WorkCalPmCInsMonthListService) getBean("workCalPmCInsMonthListService");

    	WorkCalPmCInsMonthCommonDTO workCalPmCInsMonthCommonDTO = workCalPmCInsMonthListForm.getWorkCalPmCInsMonthCommonDTO();
        //리스트를 조회한다.
        String result = workCalPmCInsMonthListService.findSchedule(workCalPmCInsMonthCommonDTO,getUser(request));

        response.getWriter().print(result);
	}

    /**
     * delete
     * @author  kim21017
     * @version $Id: WorkCalPmCInsMonthListAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     *
     * @param request
     * @param workCalPmCInsMonthListForm
     */
    private void deleteSched(HttpServletRequest request, WorkCalPmCInsMonthListForm workCalPmCInsMonthListForm) throws Exception
    {
    	WorkCalPmCInsMonthListService workCalPmCInsMonthListService = (WorkCalPmCInsMonthListService) getBean("workCalPmCInsMonthListService");

    	String[] deleteRows = workCalPmCInsMonthListForm.getDeleteRows();

    	workCalPmCInsMonthListService.deleteSched(deleteRows, getUser(request));

        setAjaxStatus(request);
    }

    /**
     * monthly 확정
     * @author  kim21017
     * @version $Id: WorkCalPmCInsMonthListAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     *
     * @param request
     * @param workCalPmCInsMonthListForm
     */
    private void monthlyScheduled(HttpServletRequest request, HttpServletResponse response, WorkCalPmCInsMonthListForm workCalPmCInsMonthListForm) throws Exception
    {
    	WorkCalPmCInsMonthListService workCalPmCInsMonthListService = (WorkCalPmCInsMonthListService) getBean("workCalPmCInsMonthListService");
    	WorkCalPmCInsMonthCommonDTO workCalPmCInsMonthCommonDTO = workCalPmCInsMonthListForm.getWorkCalPmCInsMonthCommonDTO();
    	workCalPmCInsMonthCommonDTO.setCompNo(getUser(request).getCompNo());
    	ResponseDTO responseDTO = workCalPmCInsMonthListService.monthlyScheduled(workCalPmCInsMonthCommonDTO,getUser(request));

    	CommonUtil.makeJsonResult(responseDTO, response);
    }

    /**
     * monthly 확정
     * @author  kim21017
     * @version $Id: WorkCalPmCInsMonthListAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     *
     * @param request
     * @param workCalPmCInsMonthListForm
     */
    private void dailyScheduled(HttpServletRequest request, HttpServletResponse response, WorkCalPmCInsMonthListForm workCalPmCInsMonthListForm) throws Exception
    {
    	WorkCalPmCInsMonthListService workCalPmCInsMonthListService = (WorkCalPmCInsMonthListService) getBean("workCalPmCInsMonthListService");
    	WorkCalPmCInsMonthCommonDTO workCalPmCInsMonthCommonDTO = workCalPmCInsMonthListForm.getWorkCalPmCInsMonthCommonDTO();
    	workCalPmCInsMonthCommonDTO.setCompNo(getUser(request).getCompNo());

    	String[] fixRows = workCalPmCInsMonthListForm.getSelectRows();

    	ResponseDTO responseDTO = workCalPmCInsMonthListService.dailyScheduled(fixRows,workCalPmCInsMonthCommonDTO,getUser(request));

        CommonUtil.makeJsonResult(responseDTO, response);
    }
}
