package dream.work.cal.pmdinsmonth.action;


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
import dream.work.cal.pmdinsmonth.dto.WorkCalPmDInsMonthCommonDTO;
import dream.work.cal.pmdinsmonth.form.WorkCalPmDInsMonthListForm;
import dream.work.cal.pmdinsmonth.service.WorkCalPmDInsMonthListService;

/**
 * 월간예방일정 - 목록 action
 * @author  kim21017
 * @version $Id: WorkCalPmDInsMonthListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/workCalPmDInsMonthList" name="workCalPmDInsMonthListForm"
 *                input="/dream/work/cal/pmdinsmonth/workCalPmDInsMonthList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workCalPmDInsMonthList" path="/dream/work/cal/pmdinsmonth/workCalPmDInsMonthList.jsp"
 *                        redirect="false"
 */
public class WorkCalPmDInsMonthListAction extends AuthAction
{
    /** 조회 */
    public static final int MONTH_SCHED_LIST_FIND 			= 1001;
    /** 삭제 */
    public static final int MONTH_SCHED_LIST_DELETE 		= 7002;
    /** 스케쥴조회 */
    public static final int MONTH_SCHED_FIND				= 1003;
    /** 일정수정 */
    public static final int MONTH_SCHED_LIST_CHANGE 		= 6004;
    /** 일단위 확정 */
    public static final int MONTH_SCHED_DAILY_SCHEDULED		= 6005;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        WorkCalPmDInsMonthListForm workCalPmDInsMonthListForm = (WorkCalPmDInsMonthListForm) form;

        switch (workCalPmDInsMonthListForm.getStrutsAction())
        {
            case WorkCalPmDInsMonthListAction.MONTH_SCHED_LIST_FIND:
                findSchedList(request, workCalPmDInsMonthListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkCalPmDInsMonthListAction.BASE_SET_HEADER:
                setHeader(request, response, workCalPmDInsMonthListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkCalPmDInsMonthListAction.MONTH_SCHED_FIND:
            	findSchedule(request,workCalPmDInsMonthListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkCalPmDInsMonthListAction.MONTH_SCHED_LIST_DELETE:
            	deleteSched(request, workCalPmDInsMonthListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case WorkCalPmDInsMonthListAction.MONTH_SCHED_LIST_CHANGE:
            	changeSched(request, workCalPmDInsMonthListForm, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkCalPmDInsMonthListAction.MONTH_SCHED_DAILY_SCHEDULED:
            	dailyScheduled(request, response, workCalPmDInsMonthListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkCalPmDInsMonthListAction.BASE_GRID_EXPORT:
            	findSchedList(request, workCalPmDInsMonthListForm, response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

    

    private void setHeader(HttpServletRequest request, HttpServletResponse response, WorkCalPmDInsMonthListForm workCalPmDInsMonthListForm) throws IOException
    {
        super.setHeader(request, response, workCalPmDInsMonthListForm.getListId(),workCalPmDInsMonthListForm.getCurrentPageId());
    }

    /**
     * grid find
     * @author  kim2107
     * @version $Id: WorkCalPmDInsMonthListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     *
     * @param request
     * @param workCalPmDInsMonthListForm
     * @param response
     * @throws Exception
     */
    private void findSchedList(HttpServletRequest request, WorkCalPmDInsMonthListForm workCalPmDInsMonthListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	WorkCalPmDInsMonthListService workCalPmDInsMonthListService = (WorkCalPmDInsMonthListService) getBean("workCalPmDInsMonthListService");

    	WorkCalPmDInsMonthCommonDTO workCalPmDInsMonthCommonDTO = workCalPmDInsMonthListForm.getWorkCalPmDInsMonthCommonDTO();
    	workCalPmDInsMonthCommonDTO.setCompNo(getUser(request).getCompNo());

    	//Paging
    	workCalPmDInsMonthCommonDTO.setIsLoadMaxCount("Y".equals(workCalPmDInsMonthListForm.getIsLoadMaxCount())?true:false);
    	workCalPmDInsMonthCommonDTO.setFirstRow(workCalPmDInsMonthListForm.getFirstRow());
    	workCalPmDInsMonthCommonDTO.setOrderBy(workCalPmDInsMonthListForm.getOrderBy());
    	workCalPmDInsMonthCommonDTO.setDirection(workCalPmDInsMonthListForm.getDirection());
    	
        //리스트를 조회한다.
        List resultList = workCalPmDInsMonthListService.findSchedList(workCalPmDInsMonthCommonDTO,getUser(request));
        
      //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workCalPmDInsMonthListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workCalPmDInsMonthListService.findTotalCount(workCalPmDInsMonthCommonDTO,getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, workCalPmDInsMonthListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
	}

    /**
     * schedule find
     * @author  kim2107
     * @version $Id: WorkCalPmDInsMonthListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     *
     * @param request
     * @param workCalPmDInsMonthListForm
     * @param response
     * @throws Exception
     */
    private void findSchedule(HttpServletRequest request, WorkCalPmDInsMonthListForm workCalPmDInsMonthListForm, HttpServletResponse response) throws Exception
    {
    	WorkCalPmDInsMonthListService workCalPmDInsMonthListService = (WorkCalPmDInsMonthListService) getBean("workCalPmDInsMonthListService");

    	WorkCalPmDInsMonthCommonDTO workCalPmDInsMonthCommonDTO = workCalPmDInsMonthListForm.getWorkCalPmDInsMonthCommonDTO();
    	workCalPmDInsMonthCommonDTO.setCompNo(getUser(request).getCompNo());
        //리스트를 조회한다.
        String result = workCalPmDInsMonthListService.findSchedule(workCalPmDInsMonthCommonDTO,getUser(request));

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
    private void deleteSched(HttpServletRequest request, WorkCalPmDInsMonthListForm workCalPmDInsMonthListForm) throws Exception
    {
    	WorkCalPmDInsMonthListService workCalPmDInsMonthListService = (WorkCalPmDInsMonthListService) getBean("workCalPmDInsMonthListService");
    	
    	String[] deleteRows = workCalPmDInsMonthListForm.getDeleteRows();
    	
    	workCalPmDInsMonthListService.deleteSched(deleteRows, getUser(request));
    	
    	setAjaxStatus(request);
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
    private void changeSched(HttpServletRequest request, WorkCalPmDInsMonthListForm workCalPmDInsMonthListForm, HttpServletResponse response) throws Exception
    {
    	WorkCalPmDInsMonthListService workCalPmDInsMonthListService = (WorkCalPmDInsMonthListService) getBean("workCalPmDInsMonthListService");

    	List gridList = setGridMap(request);
    	
    	workCalPmDInsMonthListService.updateSchedule(gridList, getUser(request));
    	JSONObject jsonObj = new JSONObject();
        jsonObj.put("status", "ok");
        response.getWriter().print(jsonObj.toString());
    	
    }
    


    /**
     * monthly 확정
     * @author  syyang
     * @version $Id: $
     * @since   1.0
     *
     * @param request
     * @param workCalPmRInsMonthListForm
     */
    private void dailyScheduled(HttpServletRequest request, HttpServletResponse response, WorkCalPmDInsMonthListForm workCalPmDInsMonthListForm) throws Exception
    {
    	WorkCalPmDInsMonthListService workCalPmDInsMonthListService = (WorkCalPmDInsMonthListService) getBean("workCalPmDInsMonthListService");

    	WorkCalPmDInsMonthCommonDTO workCalPmDInsMonthCommonDTO = workCalPmDInsMonthListForm.getWorkCalPmDInsMonthCommonDTO();

    	String[] fixRows = workCalPmDInsMonthListForm.getSelectRows();
    	
    	ResponseDTO responseDTO = workCalPmDInsMonthListService.dailyScheduled(fixRows, workCalPmDInsMonthCommonDTO, getUser(request));

        CommonUtil.makeJsonResult(responseDTO, response);
    }
}
