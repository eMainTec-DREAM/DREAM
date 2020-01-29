package dream.work.cal.unitedmonth.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.work.cal.unitedmonth.dto.WorkCalUnitedMonthCommonDTO;
import dream.work.cal.unitedmonth.form.WorkCalUnitedMonthListForm;
import dream.work.cal.unitedmonth.service.WorkCalUnitedMonthListService;
import dream.work.list.dto.MaWoResultMstrCommonDTO;

/**
 * 월간작업일정 - 목록 action
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @struts:action path="/workCalUnitedMonthList" name="workCalUnitedMonthListForm"
 *                input="/dream/work/cal/unitedmonth/workCalUnitedMonthList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workCalUnitedMonthList" path="/dream/work/cal/unitedmonth/workCalUnitedMonthList.jsp"
 *                        redirect="false"
 */
public class WorkCalUnitedMonthListAction extends AuthAction
{
    /** WO 조회 */
    public static final int MONTH_WO_LIST_FIND 			= 2001;
    /** PMI 조회 */
    public static final int MONTH_PMI_LIST_FIND 		= 2002;
    /** 통합 스케쥴조회 */
    public static final int MONTH_FIND					= 2003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        WorkCalUnitedMonthListForm workCalUnitedMonthListForm = (WorkCalUnitedMonthListForm) form;
        
        switch (workCalUnitedMonthListForm.getStrutsAction())
        {
            case WorkCalUnitedMonthListAction.MONTH_WO_LIST_FIND:
                findSchedList(request, workCalUnitedMonthListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkCalUnitedMonthListAction.MONTH_PMI_LIST_FIND:
            	findPmiList(request, workCalUnitedMonthListForm, response, false);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case WorkCalUnitedMonthListAction.MONTH_FIND:
            	findSchedule(request,workCalUnitedMonthListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkCalUnitedMonthListAction.BASE_SET_HEADER:
                setHeader(request, response, workCalUnitedMonthListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkCalUnitedMonthListAction.BASE_GRID_EXPORT:
            	
            	switch (workCalUnitedMonthListForm.getStAct())
                {
            		case WorkCalUnitedMonthListAction.MONTH_WO_LIST_FIND:
            			findSchedList(request, workCalUnitedMonthListForm, response, true);
            			break;
            		case WorkCalUnitedMonthListAction.MONTH_PMI_LIST_FIND:
                    	findPmiList(request, workCalUnitedMonthListForm, response, true);
                    	break;
                }
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("workCalUnitedMonthList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, WorkCalUnitedMonthListForm workCalUnitedMonthListForm) throws IOException
    {
        super.setHeader(request, response, workCalUnitedMonthListForm.getListId(),workCalUnitedMonthListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param request
     * @param workCalUnitedMonthListForm
     * @param response
     * @throws Exception
     */
    private void findSchedList(HttpServletRequest request, WorkCalUnitedMonthListForm workCalUnitedMonthListForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	WorkCalUnitedMonthListService workCalUnitedMonthListService = (WorkCalUnitedMonthListService) getBean("workCalUnitedMonthListService");        

    	WorkCalUnitedMonthCommonDTO workCalUnitedMonthCommonDTO = workCalUnitedMonthListForm.getWorkCalUnitedMonthCommonDTO();
    	MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = workCalUnitedMonthListForm.getMaWoResultMstrCommonDTO();
    	workCalUnitedMonthCommonDTO.setCompNo(getUser(request).getCompNo());

    	//Paging
        workCalUnitedMonthCommonDTO.setIsLoadMaxCount("Y".equals(workCalUnitedMonthListForm.getIsLoadMaxCount())?true:false);
        workCalUnitedMonthCommonDTO.setFirstRow(workCalUnitedMonthListForm.getFirstRow());
        workCalUnitedMonthCommonDTO.setOrderBy(workCalUnitedMonthListForm.getOrderBy());
        workCalUnitedMonthCommonDTO.setDirection(workCalUnitedMonthListForm.getDirection());
        
        User user = getUser(request);
        //리스트를 조회한다.
        List resultList = workCalUnitedMonthListService.findWorkSchedList(workCalUnitedMonthCommonDTO, maWoResultMstrCommonDTO, getUser(request));

        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workCalUnitedMonthListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workCalUnitedMonthListService.findTotalCount(workCalUnitedMonthCommonDTO,maWoResultMstrCommonDTO, user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,workCalUnitedMonthListForm.getListId(),workCalUnitedMonthListForm.getCurrentPageId(), workCalUnitedMonthListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
	}
    
    
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param request
     * @param workCalUnitedMonthListForm
     * @param response
     * @throws Exception
     */
    private void findPmiList(HttpServletRequest request, WorkCalUnitedMonthListForm workCalUnitedMonthListForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	WorkCalUnitedMonthListService workCalUnitedMonthListService = (WorkCalUnitedMonthListService) getBean("workCalUnitedMonthListService");        
    	
    	WorkCalUnitedMonthCommonDTO workCalUnitedMonthCommonDTO = workCalUnitedMonthListForm.getWorkCalUnitedMonthCommonDTO();
    	MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = workCalUnitedMonthListForm.getMaWoResultMstrCommonDTO();
    	workCalUnitedMonthCommonDTO.setCompNo(getUser(request).getCompNo());
    	
    	//Paging
    	workCalUnitedMonthCommonDTO.setIsLoadMaxCount("Y".equals(workCalUnitedMonthListForm.getIsLoadMaxCount())?true:false);
    	workCalUnitedMonthCommonDTO.setFirstRow(workCalUnitedMonthListForm.getFirstRow());
    	workCalUnitedMonthCommonDTO.setOrderBy(workCalUnitedMonthListForm.getOrderBy());
    	workCalUnitedMonthCommonDTO.setDirection(workCalUnitedMonthListForm.getDirection());
    	
    	User user = getUser(request);
    	//리스트를 조회한다.
    	List resultList = workCalUnitedMonthListService.findPmiSchedList(workCalUnitedMonthCommonDTO, maWoResultMstrCommonDTO, getUser(request));
    	
    	//Paging
    	String totalCount = "";
    	
    	if(Integer.parseInt(workCalUnitedMonthListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workCalUnitedMonthListService.findPmiTotalCount(workCalUnitedMonthCommonDTO,maWoResultMstrCommonDTO,user);
    	
    	if(excelExport) super.makeGridExport(resultList, request, response,workCalUnitedMonthListForm.getListId(),workCalUnitedMonthListForm.getCurrentPageId(), workCalUnitedMonthListForm.getFileName());
    	else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * schedule find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param request
     * @param workCalUnitedMonthListForm
     * @param response
     * @throws Exception
     */
    private void findSchedule(HttpServletRequest request, WorkCalUnitedMonthListForm workCalUnitedMonthListForm, HttpServletResponse response) throws Exception
    {
    	WorkCalUnitedMonthListService workCalUnitedMonthListService = (WorkCalUnitedMonthListService) getBean("workCalUnitedMonthListService");        

    	WorkCalUnitedMonthCommonDTO workCalUnitedMonthCommonDTO = workCalUnitedMonthListForm.getWorkCalUnitedMonthCommonDTO();
    	MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = workCalUnitedMonthListForm.getMaWoResultMstrCommonDTO();
    	workCalUnitedMonthCommonDTO.setCompNo(getUser(request).getCompNo());
        //리스트를 조회한다.
        String result = workCalUnitedMonthListService.findSchedule(workCalUnitedMonthCommonDTO,maWoResultMstrCommonDTO, getUser(request));

        response.getWriter().print(result);
	}
}
