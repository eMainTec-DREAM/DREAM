package dream.work.cal.pmrinsyear.action;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.fins.org.json.JSONObject;
import com.google.gson.Gson;

import common.bean.ResponseDTO;
import common.report.service.ReportService;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.work.cal.pmrinsyear.dto.WorkCalPmRInsYearCommonDTO;
import dream.work.cal.pmrinsyear.form.WorkCalPmRInsYearListForm;
import dream.work.cal.pmrinsyear.service.WorkCalPmRInsYearListService;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRException;

/**
 * 연간작업일정 - 목록 action
 * @author  kim21017
 * @version $Id: WorkCalPmRInsListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/workCalPmRInsYearList" name="workCalPmRInsYearListForm"
 *                input="/dream/work/cal/pmrinsyear/workCalPmRInsYearList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workCalPmRInsYearList" path="/dream/work/cal/pmrinsyear/workCalPmRInsYearList.jsp"
 *                        redirect="false"
 */
public class WorkCalPmRInsYearListAction extends AuthAction
{
    /** 조회 */
    public static final int YEAR_SCHED_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int YEAR_SCHED_LIST_DELETE 		= 7002;
    /** 월별 스케쥴 갯수 조회 */
    public static final int YEAR_SCHED_FIND				= 1003;
    /** 스케쥴조정 */
    public static final int YEAR_SCHED_CHANGE           = 6001;
    /** 연간 점검표 */
    public static final int YEAR_SCHED_REPORT           = 1005;
    /** 일단위 확정 */
    public static final int YEAR_SCHED_DAILY_SCHEDULED	= 6002;

    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        WorkCalPmRInsYearListForm workCalPmRInsYearListForm = (WorkCalPmRInsYearListForm) form;

        switch (workCalPmRInsYearListForm.getStrutsAction())
        {
            case WorkCalPmRInsYearListAction.YEAR_SCHED_LIST_FIND:
            	findSchedMonthlyList(request, workCalPmRInsYearListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkCalPmRInsYearListAction.BASE_SET_HEADER:
                setHeader(request, response, workCalPmRInsYearListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkCalPmRInsYearListAction.YEAR_SCHED_LIST_DELETE:
            	deleteSched(request, workCalPmRInsYearListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case WorkCalPmRInsYearListAction.YEAR_SCHED_FIND:
            	findTotalCountByMonthly(request,workCalPmRInsYearListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkCalPmRInsYearListAction.BASE_GRID_EXPORT:
            	findSchedMonthlyList(request, workCalPmRInsYearListForm, response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            case WorkCalPmRInsYearListAction.YEAR_SCHED_CHANGE:
                changeSchedule(request, workCalPmRInsYearListForm, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkCalPmRInsYearListAction.YEAR_SCHED_REPORT:
                findYearReport(request, workCalPmRInsYearListForm);
                returnActionForward = mapping.findForward("pdfViewer");
                break;
            case WorkCalPmRInsYearListAction.YEAR_SCHED_DAILY_SCHEDULED:
            	dailyScheduled(request, response, workCalPmRInsYearListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            default:
                returnActionForward = mapping.findForward("workCalPmRInsYearList");
                break;
        }

        return returnActionForward;
    }

    private void changeSchedule(HttpServletRequest request, WorkCalPmRInsYearListForm workCalPmRInsYearListForm,  HttpServletResponse response) throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, InstantiationException
    {
        WorkCalPmRInsYearListService workCalPmRInsYearListService = (WorkCalPmRInsYearListService) getBean("workCalPmRInsYearListService");

        List<Map> gridList = setGridMap(request);

        workCalPmRInsYearListService.updateSchedule(gridList, getUser(request));

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("status", "ok");
        response.getWriter().print(jsonObj.toString());
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, WorkCalPmRInsYearListForm workCalPmRInsYearListForm) throws IOException
    {
        super.setHeader(request, response, workCalPmRInsYearListForm.getListId(),workCalPmRInsYearListForm.getCurrentPageId());
    }

    /**
     * grid find
     * @author  kim2107
     * @version $Id: WorkCalPmRInsListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     *
     * @param request
     * @param workCalPmRInsYearListForm
     * @param response
     * @throws Exception
     */
    private void findSchedMonthlyList(HttpServletRequest request, WorkCalPmRInsYearListForm workCalPmRInsYearListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	WorkCalPmRInsYearListService workCalPmRInsYearListService = (WorkCalPmRInsYearListService) getBean("workCalPmRInsYearListService");

    	WorkCalPmRInsYearCommonDTO workCalPmRInsYearCommonDTO = workCalPmRInsYearListForm.getWorkCalPmRInsYearCommonDTO();
    	workCalPmRInsYearCommonDTO.setIsLoadMaxCount("Y".equals(workCalPmRInsYearListForm.getIsLoadMaxCount())?true:false);
        workCalPmRInsYearCommonDTO.setFirstRow(workCalPmRInsYearListForm.getFirstRow());
        workCalPmRInsYearCommonDTO.setOrderBy(workCalPmRInsYearListForm.getOrderBy());
        workCalPmRInsYearCommonDTO.setDirection(workCalPmRInsYearListForm.getDirection());

        //월별 리스트를 조회한다.
        List resultList = workCalPmRInsYearListService.findSchedMonthlyList(workCalPmRInsYearCommonDTO,getUser(request));

        String totalCount = "";
        if(Integer.parseInt(workCalPmRInsYearListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workCalPmRInsYearListService.findTotalCount(workCalPmRInsYearCommonDTO,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,workCalPmRInsYearListForm.getListId(),workCalPmRInsYearListForm.getCurrentPageId(), workCalPmRInsYearListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
	}
    private void findYearReport(HttpServletRequest request, WorkCalPmRInsYearListForm workCalPmRInsYearListForm) throws JRException, DRException, IOException
    {
    	WorkCalPmRInsYearListService workCalPmRInsYearListService = (WorkCalPmRInsYearListService) getBean("workCalPmRInsYearListService");
    	WorkCalPmRInsYearCommonDTO workCalPmRInsYearCommonDTO = workCalPmRInsYearListForm.getWorkCalPmRInsYearCommonDTO();
        
        List reportList = workCalPmRInsYearListService.findYearReport(workCalPmRInsYearCommonDTO,getUser(request));
        
        Map map = (Map)reportList.get(0);
        Gson gson = new Gson();
        String strJson = gson.toJson(map);
        
        ReportService rs = (ReportService)CommonUtil.getBean("reportService", getUser(request));
        String destFileName = rs.viewReport("workCalPmRInsYearList",strJson, getUser(request));
        
        // 조회한 List 를 form에 셋팅한다.
        request.setAttribute(REPORT_NAME_ATTRIBUTE, destFileName);
//        request.setAttribute(REPORT_MAP_ATTRIBUTE, reportList);
    }
    /**
     * schedule find
     * @author  kim2107
     * @version $Id: WorkCalPmRInsListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     *
     * @param request
     * @param workCalPmRInsYearListForm
     * @param response
     * @throws Exception
     */
    private void findTotalCountByMonthly(HttpServletRequest request, WorkCalPmRInsYearListForm workCalPmRInsYearListForm, HttpServletResponse response) throws Exception
    {
    	WorkCalPmRInsYearListService workCalPmRInsYearListService = (WorkCalPmRInsYearListService) getBean("workCalPmRInsYearListService");

    	WorkCalPmRInsYearCommonDTO workCalPmRInsYearCommonDTO = workCalPmRInsYearListForm.getWorkCalPmRInsYearCommonDTO();
    	workCalPmRInsYearCommonDTO.setCompNo(getUser(request).getCompNo());
        //리스트를 조회한다.

    	String result = workCalPmRInsYearListService.findTotalCountByMonthly(workCalPmRInsYearCommonDTO,getUser(request));

    	response.getWriter().print(result);
	}

    /**
     * delete
     * @author  kim21017
     * @version $Id: WorkCalPmRInsListAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     *
     * @param request
     * @param workCalPmRInsYearListForm
     */
    private void deleteSched(HttpServletRequest request, WorkCalPmRInsYearListForm workCalPmRInsYearListForm) throws Exception
    {
    	WorkCalPmRInsYearListService workCalPmRInsYearListService = (WorkCalPmRInsYearListService) getBean("workCalPmRInsYearListService");

    	String[] deleteRows = workCalPmRInsYearListForm.getDeleteRows();

    	workCalPmRInsYearListService.deleteSched(deleteRows, getUser(request));

        setAjaxStatus(request);
    }

    
    /**
     * 일별 확정
     * @author  syyang
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param workCalPmRInsYearListForm
     */
    private void dailyScheduled(HttpServletRequest request, HttpServletResponse response, WorkCalPmRInsYearListForm workCalPmRInsYearListForm) throws Exception
    {
    	WorkCalPmRInsYearListService workCalPmRInsYearListService = (WorkCalPmRInsYearListService) getBean("workCalPmRInsYearListService");
    	WorkCalPmRInsYearCommonDTO workCalPmRInsYearCommonDTO = workCalPmRInsYearListForm.getWorkCalPmRInsYearCommonDTO();
    	workCalPmRInsYearCommonDTO.setCompNo(getUser(request).getCompNo());
    	
    	String[] fixRows = workCalPmRInsYearListForm.getSelectRows();
    	
    	ResponseDTO responseDTO = workCalPmRInsYearListService.dailyScheduled(fixRows, workCalPmRInsYearCommonDTO, getUser(request));
    	
        CommonUtil.makeJsonResult(responseDTO, response);
    }
}
