package dream.work.cal.pmmonth.action;


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
import dream.work.cal.pmmonth.dto.MaWoMonthSchedCommonDTO;
import dream.work.cal.pmmonth.form.MaWoMonthSchedListForm;
import dream.work.cal.pmmonth.service.MaWoMonthSchedListService;

/**
 * 월간예방일정 - 목록 action
 * @author  kim21017
 * @version $Id: MaWoMonthSchedListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maWoMonthSchedList" name="maWoMonthSchedListForm"
 *                input="/dream/work/cal/pmmonth/maWoMonthSchedList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workCalPmcMonthList" name="maWoMonthSchedListForm"
 *                input="/dream/work/cal/pmmonth/workCalPmcMonthList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maWoMonthSchedList" path="/dream/work/cal/pmmonth/maWoMonthSchedList.jsp"
 *                        redirect="false"
 */
public class MaWoMonthSchedListAction extends AuthAction
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
        MaWoMonthSchedListForm maWoMonthSchedListForm = (MaWoMonthSchedListForm) form;
        
        switch (maWoMonthSchedListForm.getStrutsAction())
        {
            case MaWoMonthSchedListAction.MONTH_SCHED_LIST_FIND:
                findSchedList(request, maWoMonthSchedListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaWoMonthSchedListAction.BASE_SET_HEADER:
                setHeader(request, response, maWoMonthSchedListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaWoMonthSchedListAction.MONTH_SCHED_LIST_DELETE:
            	deleteSched(request, maWoMonthSchedListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaWoMonthSchedListAction.MONTH_SCHED_DAILY_SCHEDULED:
            	dailyScheduled(request, response, maWoMonthSchedListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaWoMonthSchedListAction.MONTH_SCHED_FIND:
            	findSchedule(request,maWoMonthSchedListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaWoMonthSchedListAction.BASE_GRID_EXPORT:
            	findSchedList(request, maWoMonthSchedListForm, response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            case MaWoMonthSchedListAction.MONTH_SCHED_CHANGE:
                changeSchedule(request,maWoMonthSchedListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

    private void changeSchedule(HttpServletRequest request,MaWoMonthSchedListForm maWoMonthSchedListForm, HttpServletResponse response) throws IOException
    {
        MaWoMonthSchedListService maWoMonthSchedListService = (MaWoMonthSchedListService) getBean("maWoMonthSchedListService");     
        
        List gridList = setGridMap(request);

        maWoMonthSchedListService.updateSchedule(gridList, getUser(request));
        
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("status", "ok");
        response.getWriter().print(jsonObj.toString());
        
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaWoMonthSchedListForm maWoMonthSchedListForm) throws IOException
    {
        super.setHeader(request, response, maWoMonthSchedListForm.getListId(),maWoMonthSchedListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaWoMonthSchedListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maWoMonthSchedListForm
     * @param response
     * @throws Exception
     */
    private void findSchedList(HttpServletRequest request, MaWoMonthSchedListForm maWoMonthSchedListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	MaWoMonthSchedListService maWoMonthSchedListService = (MaWoMonthSchedListService) getBean("maWoMonthSchedListService", request);        

    	MaWoMonthSchedCommonDTO maWoMonthSchedCommonDTO = maWoMonthSchedListForm.getMaWoMonthSchedCommonDTO();
    	maWoMonthSchedCommonDTO.setCompNo(getUser(request).getCompNo());

    	//Paging
    	maWoMonthSchedCommonDTO.setIsLoadMaxCount("Y".equals(maWoMonthSchedListForm.getIsLoadMaxCount())?true:false);
    	maWoMonthSchedCommonDTO.setFirstRow(maWoMonthSchedListForm.getFirstRow());
    	maWoMonthSchedCommonDTO.setOrderBy(maWoMonthSchedListForm.getOrderBy());
    	maWoMonthSchedCommonDTO.setDirection(maWoMonthSchedListForm.getDirection());

        //리스트를 조회한다.
        List resultList = maWoMonthSchedListService.findSchedList(maWoMonthSchedCommonDTO,getUser(request));
    	        
    	//Paging
    	String totalCount = "";
    	        
    	if(Integer.parseInt(maWoMonthSchedListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maWoMonthSchedListService.findTotalCount(maWoMonthSchedCommonDTO,getUser(request));
    	        
    	if(excelExport) super.makeGridExport(resultList, request, response, maWoMonthSchedListForm.getListId(),maWoMonthSchedListForm.getCurrentPageId(), maWoMonthSchedListForm.getFileName());
    	else super.makeJsonResult(resultList, request, response, totalCount);
	}
    
    /**
     * schedule find
     * @author  kim2107
     * @version $Id: MaWoMonthSchedListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maWoMonthSchedListForm
     * @param response
     * @throws Exception
     */
    private void findSchedule(HttpServletRequest request, MaWoMonthSchedListForm maWoMonthSchedListForm, HttpServletResponse response) throws Exception
    {
    	MaWoMonthSchedListService maWoMonthSchedListService = (MaWoMonthSchedListService) getBean("maWoMonthSchedListService", request);        

    	MaWoMonthSchedCommonDTO maWoMonthSchedCommonDTO = maWoMonthSchedListForm.getMaWoMonthSchedCommonDTO();
    	maWoMonthSchedCommonDTO.setCompNo(getUser(request).getCompNo());
        //리스트를 조회한다.
        String result = maWoMonthSchedListService.findSchedule(maWoMonthSchedCommonDTO,getUser(request));
        
        response.getWriter().print(result);
	}
    
    /**
     * delete
     * @author  kim21017
     * @version $Id: MaWoMonthSchedListAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maWoMonthSchedListForm
     */
    private void deleteSched(HttpServletRequest request, MaWoMonthSchedListForm maWoMonthSchedListForm) throws Exception
    {
    	MaWoMonthSchedListService maWoMonthSchedListService = (MaWoMonthSchedListService) getBean("maWoMonthSchedListService");        

    	String[] deleteRows = maWoMonthSchedListForm.getDeleteRows();
    	
    	maWoMonthSchedListService.deleteSched(deleteRows, getUser(request));
    	
        setAjaxStatus(request);
    }
    
    /**
     * monthly 확정
     * @author  kim21017
     * @version $Id: MaWoMonthSchedListAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maWoMonthSchedListForm
     */
    private void dailyScheduled(HttpServletRequest request, HttpServletResponse response, MaWoMonthSchedListForm maWoMonthSchedListForm) throws Exception
    {
    	MaWoMonthSchedListService maWoMonthSchedListService = (MaWoMonthSchedListService) getBean("maWoMonthSchedListService");
    	MaWoMonthSchedCommonDTO maWoMonthSchedCommonDTO = maWoMonthSchedListForm.getMaWoMonthSchedCommonDTO();
    	maWoMonthSchedCommonDTO.setCompNo(getUser(request).getCompNo());
    	
    	String[] fixRows = maWoMonthSchedListForm.getSelectRows();
    	
    	ResponseDTO responseDTO = maWoMonthSchedListService.dailyScheduled(fixRows,maWoMonthSchedCommonDTO,getUser(request));
    	
        CommonUtil.makeJsonResult(responseDTO, response);
    }
}
