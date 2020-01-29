package dream.work.cal.pmptrlmonth.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.work.cal.pmptrlmonth.dto.WorkCalPmPtrlMonthCommonDTO;
import dream.work.cal.pmptrlmonth.form.WorkCalPmPtrlMonthListForm;
import dream.work.cal.pmptrlmonth.service.WorkCalPmPtrlMonthListService;

/**
 * 월간순회일정 - 목록 action
 * @author  youngjoo38
 * @version $Id: WorkCalPmPtrlMonthListAction.java,v 1.0 2017/09/24 09:10:21 youngjoo38 Exp $
 * @since   1.0
 * @struts:action path="/workCalPmPtrlMonthList" name="workCalPmPtrlMonthListForm"
 *                input="/dream/work/cal/pmptrlmonth/workCalPmPtrlMonthList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workCalPmPtrlMonthList" path="/dream/work/cal/pmptrlmonth/workCalPmPtrlMonthList.jsp"
 *                        redirect="false"
 */
public class WorkCalPmPtrlMonthListAction extends AuthAction
{
    /** 조회 */
    public static final int MONTH_SCHED_LIST_FIND 			= 1001;
    /** 삭제 */
    public static final int MONTH_SCHED_LIST_DELETE 		= 7002;
    /** 스케쥴조회 */
    public static final int MONTH_SCHED_FIND				= 1003;

    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        WorkCalPmPtrlMonthListForm workCalPmPtrlMonthListForm = (WorkCalPmPtrlMonthListForm) form;

        switch (workCalPmPtrlMonthListForm.getStrutsAction())
        {
            case WorkCalPmPtrlMonthListAction.MONTH_SCHED_LIST_FIND:
                findSchedList(request, workCalPmPtrlMonthListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkCalPmPtrlMonthListAction.BASE_SET_HEADER:
                setHeader(request, response, workCalPmPtrlMonthListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkCalPmPtrlMonthListAction.MONTH_SCHED_LIST_DELETE:
            	deleteSched(request, workCalPmPtrlMonthListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case WorkCalPmPtrlMonthListAction.MONTH_SCHED_FIND:
            	findSchedule(request,workCalPmPtrlMonthListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkCalPmPtrlMonthListAction.BASE_GRID_EXPORT:
            	findSchedList(request, workCalPmPtrlMonthListForm, response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, WorkCalPmPtrlMonthListForm workCalPmPtrlMonthListForm) throws IOException
    {
        super.setHeader(request, response, workCalPmPtrlMonthListForm.getListId(),workCalPmPtrlMonthListForm.getCurrentPageId());
    }

    /**
     * grid find
     * @author  youngjoo38
     * @version $Id: WorkCalPmPtrlMonthListAction.java,v 1.0 2017/09/24 09:10:21 youngjoo38 Exp $
     * @since   1.0
     *
     * @param request
     * @param workCalPmPtrlMonthListForm
     * @param response
     * @throws Exception
     */
    private void findSchedList(HttpServletRequest request, WorkCalPmPtrlMonthListForm workCalPmPtrlMonthListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {   // 달력에서 숫자를 선택했을 때
        
    	WorkCalPmPtrlMonthListService workCalPmPtrlMonthListService = (WorkCalPmPtrlMonthListService) getBean("workCalPmPtrlMonthListService");
    	
    	WorkCalPmPtrlMonthCommonDTO workCalPmPtrlMonthCommonDTO = workCalPmPtrlMonthListForm.getWorkCalPmPtrlMonthCommonDTO();
    	workCalPmPtrlMonthCommonDTO.setCompNo(getUser(request).getCompNo());

    	//Paging
    	workCalPmPtrlMonthCommonDTO.setIsLoadMaxCount("Y".equals(workCalPmPtrlMonthListForm.getIsLoadMaxCount())?true:false);
    	workCalPmPtrlMonthCommonDTO.setFirstRow(workCalPmPtrlMonthListForm.getFirstRow());
    	workCalPmPtrlMonthCommonDTO.setOrderBy(workCalPmPtrlMonthListForm.getOrderBy());
    	workCalPmPtrlMonthCommonDTO.setDirection(workCalPmPtrlMonthListForm.getDirection());
    	
        //리스트를 조회한다.
        List resultList = workCalPmPtrlMonthListService.findSchedList(workCalPmPtrlMonthCommonDTO,getUser(request));

        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workCalPmPtrlMonthListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workCalPmPtrlMonthListService.findTotalCount(workCalPmPtrlMonthCommonDTO,getUser(request));
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, workCalPmPtrlMonthListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
	}

    /**
     * schedule find
     * @author  youngjoo38
     * @version $Id: WorkCalPmPtrlMonthListAction.java,v 1.0 2017/09/24 09:10:21 youngjoo38 Exp $
     * @since   1.0
     *
     * @param request
     * @param workCalPmPtrlMonthListForm
     * @param response
     * @throws Exception
     */
    private void findSchedule(HttpServletRequest request, WorkCalPmPtrlMonthListForm workCalPmPtrlMonthListForm, HttpServletResponse response) throws Exception
    {   // filter에서 검색 버튼을 눌렀을 때 
        
    	WorkCalPmPtrlMonthListService workCalPmPtrlMonthListService = (WorkCalPmPtrlMonthListService) getBean("workCalPmPtrlMonthListService");

    	WorkCalPmPtrlMonthCommonDTO workCalPmPtrlMonthCommonDTO = workCalPmPtrlMonthListForm.getWorkCalPmPtrlMonthCommonDTO();
        //리스트를 조회한다.
        String result = workCalPmPtrlMonthListService.findSchedule(workCalPmPtrlMonthCommonDTO,getUser(request));

        response.getWriter().print(result);
	}

    /**
     * delete
     * @author  youngjoo38
     * @version $Id: WorkCalPmPtrlMonthListAction.java,v 1.2 2017/09/24 01:21:30 youngjoo38 Exp $
     * @since   1.0
     *
     * @param request
     * @param workCalPmPtrlMonthListForm
     */
    private void deleteSched(HttpServletRequest request, WorkCalPmPtrlMonthListForm workCalPmPtrlMonthListForm) throws Exception
    {
    	WorkCalPmPtrlMonthListService workCalPmPtrlMonthListService = (WorkCalPmPtrlMonthListService) getBean("workCalPmPtrlMonthListService");

    	String[] deleteRows = workCalPmPtrlMonthListForm.getDeleteRows();

    	workCalPmPtrlMonthListService.deleteSched(deleteRows, getUser(request));

        setAjaxStatus(request);
    }
}
