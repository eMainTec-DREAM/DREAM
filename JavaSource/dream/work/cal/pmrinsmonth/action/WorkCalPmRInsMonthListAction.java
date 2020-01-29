package dream.work.cal.pmrinsmonth.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.fins.org.json.JSONObject;

import common.bean.ResponseDTO;
import common.bean.User;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.work.cal.pmrinsmonth.dto.WorkCalPmRInsMonthCommonDTO;
import dream.work.cal.pmrinsmonth.form.WorkCalPmRInsMonthListForm;
import dream.work.cal.pmrinsmonth.service.WorkCalPmRInsMonthListService;

/**
 * 월간예방일정 - 목록 action
 * @author  kim21017
 * @version $Id: WorkCalPmRInsMonthListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/workCalPmRInsMonthList" name="workCalPmRInsMonthListForm"
 *                input="/dream/work/cal/pmrinsmonth/workCalPmRInsMonthList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workCalPmRInsMonthList" path="/dream/work/cal/pmrinsmonth/workCalPmRInsMonthList.jsp"
 *                        redirect="false"
 */
public class WorkCalPmRInsMonthListAction extends AuthAction
{
    /** 조회 */
    public static final int MONTH_SCHED_LIST_FIND 			= 1001;
    /** 삭제 */
    public static final int MONTH_SCHED_LIST_DELETE 		= 7002;
    /** 스케쥴조회 */
    public static final int MONTH_SCHED_FIND				= 1003;
    /** 일단위 확정 */
    public static final int MONTH_SCHED_DAILY_SCHEDULED		= 1005;
    /** 스케줄 변경 */
    public static final int MONTH_SCHED_CHANGE              = 1006;

    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        WorkCalPmRInsMonthListForm workCalPmRInsMonthListForm = (WorkCalPmRInsMonthListForm) form;

        switch (workCalPmRInsMonthListForm.getStrutsAction())
        {
            case WorkCalPmRInsMonthListAction.MONTH_SCHED_LIST_FIND:
                findSchedList(request, workCalPmRInsMonthListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkCalPmRInsMonthListAction.BASE_SET_HEADER:
                setHeader(request, response, workCalPmRInsMonthListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkCalPmRInsMonthListAction.MONTH_SCHED_LIST_DELETE:
            	deleteSched(request, workCalPmRInsMonthListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case WorkCalPmRInsMonthListAction.MONTH_SCHED_DAILY_SCHEDULED:
            	dailyScheduled(request, response, workCalPmRInsMonthListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkCalPmRInsMonthListAction.MONTH_SCHED_FIND:
            	findSchedule(request,workCalPmRInsMonthListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkCalPmRInsMonthListAction.BASE_GRID_EXPORT:
            	findSchedList(request, workCalPmRInsMonthListForm, response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            case WorkCalPmRInsMonthListAction.MONTH_SCHED_CHANGE:
                changeSchedule(request,workCalPmRInsMonthListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                returnActionForward = mapping.findForward("workCalPmRInsMonthList");
                break;
        }

        return returnActionForward;
    }

    private void changeSchedule(HttpServletRequest request,WorkCalPmRInsMonthListForm workCalPmRInsMonthListForm, HttpServletResponse response) throws IOException
    {
        WorkCalPmRInsMonthListService workCalPmRInsMonthListService = (WorkCalPmRInsMonthListService) getBean("workCalPmRInsMonthListService", request);

        List gridList = setGridMap(request);

        workCalPmRInsMonthListService.updateSchedule(gridList, getUser(request));

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("status", "ok");
        response.getWriter().print(jsonObj.toString());

    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, WorkCalPmRInsMonthListForm workCalPmRInsMonthListForm) throws IOException
    {
        super.setHeader(request, response, workCalPmRInsMonthListForm.getListId(),workCalPmRInsMonthListForm.getCurrentPageId());
    }

    /**
     * grid find
     * @author  kim2107
     * @version $Id: WorkCalPmRInsMonthListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     *
     * @param request
     * @param workCalPmRInsMonthListForm
     * @param response
     * @throws Exception
     */
    private void findSchedList(HttpServletRequest request, WorkCalPmRInsMonthListForm workCalPmRInsMonthListForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	WorkCalPmRInsMonthListService workCalPmRInsMonthListService = (WorkCalPmRInsMonthListService) getBean("workCalPmRInsMonthListService", request);

    	WorkCalPmRInsMonthCommonDTO workCalPmRInsMonthCommonDTO = workCalPmRInsMonthListForm.getWorkCalPmRInsMonthCommonDTO();
    	workCalPmRInsMonthCommonDTO.setCompNo(getUser(request).getCompNo());

    	//Paging
        workCalPmRInsMonthCommonDTO.setIsLoadMaxCount("Y".equals(workCalPmRInsMonthListForm.getIsLoadMaxCount())?true:false);
        workCalPmRInsMonthCommonDTO.setFirstRow(workCalPmRInsMonthListForm.getFirstRow());
        workCalPmRInsMonthCommonDTO.setOrderBy(workCalPmRInsMonthListForm.getOrderBy());
        workCalPmRInsMonthCommonDTO.setDirection(workCalPmRInsMonthListForm.getDirection());
        
        User user = getUser(request);
        //리스트를 조회한다.
        List resultList = workCalPmRInsMonthListService.findSchedList(workCalPmRInsMonthCommonDTO,getUser(request));

        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workCalPmRInsMonthListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workCalPmRInsMonthListService.findTotalCount(workCalPmRInsMonthCommonDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,workCalPmRInsMonthListForm.getListId(),workCalPmRInsMonthListForm.getCurrentPageId(), workCalPmRInsMonthListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
	}

    /**
     * schedule find
     * @author  kim2107
     * @version $Id: WorkCalPmRInsMonthListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     *
     * @param request
     * @param workCalPmRInsMonthListForm
     * @param response
     * @throws Exception
     */
    private void findSchedule(HttpServletRequest request, WorkCalPmRInsMonthListForm workCalPmRInsMonthListForm, HttpServletResponse response) throws Exception
    {
    	WorkCalPmRInsMonthListService workCalPmRInsMonthListService = (WorkCalPmRInsMonthListService) getBean("workCalPmRInsMonthListService", request);

    	WorkCalPmRInsMonthCommonDTO workCalPmRInsMonthCommonDTO = workCalPmRInsMonthListForm.getWorkCalPmRInsMonthCommonDTO();
        //리스트를 조회한다.
        String result = workCalPmRInsMonthListService.findSchedule(workCalPmRInsMonthCommonDTO,getUser(request));

        response.getWriter().print(result);
	}

    /**
     * delete
     * @author  kim21017
     * @version $Id: WorkCalPmRInsMonthListAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     *
     * @param request
     * @param workCalPmRInsMonthListForm
     */
    private void deleteSched(HttpServletRequest request, WorkCalPmRInsMonthListForm workCalPmRInsMonthListForm) throws Exception
    {
    	WorkCalPmRInsMonthListService workCalPmRInsMonthListService = (WorkCalPmRInsMonthListService) getBean("workCalPmRInsMonthListService", request);

    	String[] deleteRows = workCalPmRInsMonthListForm.getDeleteRows();

    	workCalPmRInsMonthListService.deleteSched(deleteRows, getUser(request));

        setAjaxStatus(request);
    }

    /**
     * monthly 확정
     * @author  kim21017
     * @version $Id: WorkCalPmRInsMonthListAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     *
     * @param request
     * @param response 
     * @param workCalPmRInsMonthListForm
     */
    private void dailyScheduled(HttpServletRequest request, HttpServletResponse response, WorkCalPmRInsMonthListForm workCalPmRInsMonthListForm) throws Exception
    {
    	WorkCalPmRInsMonthListService workCalPmRInsMonthListService = (WorkCalPmRInsMonthListService) getBean("workCalPmRInsMonthListService", request);
    	WorkCalPmRInsMonthCommonDTO workCalPmRInsMonthCommonDTO = workCalPmRInsMonthListForm.getWorkCalPmRInsMonthCommonDTO();
    	workCalPmRInsMonthCommonDTO.setCompNo(getUser(request).getCompNo());

    	String[] fixRows = workCalPmRInsMonthListForm.getSelectRows();

    	ResponseDTO responseDTO = workCalPmRInsMonthListService.dailyScheduled(fixRows,workCalPmRInsMonthCommonDTO,getUser(request));
    	
    	CommonUtil.makeJsonResult(responseDTO, response);
    }
}
