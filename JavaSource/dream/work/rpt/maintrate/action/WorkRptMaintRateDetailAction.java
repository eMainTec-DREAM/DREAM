package dream.work.rpt.maintrate.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.work.rpt.maintrate.dto.WorkRptMaintRateDetailDTO;
import dream.work.rpt.maintrate.form.WorkRptMaintRateListForm;
import dream.work.rpt.maintrate.service.WorkRptMaintRateDetailService;

/**
 * WorkRptMaintRate Page - Detail Action
 * 
 * @author youngjoo38
 * @version $Id: $
 * @since 1.0
 * @struts:action path="/workRptMaintRateDetailByPartList" name="workRptMaintRateListForm"
 *                input="/dream/work/rpt/maintrate/workRptMaintRateDetailByPartList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workRptMaintRateDetailByTypeList" name="workRptMaintRateListForm"
 *                input="/dream/work/rpt/maintrate/workRptMaintRateDetailByTypeList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workRptMaintRateDetailDayChart" name="workRptMaintRateListForm"
 *                input="/dream/work/rpt/maintrate/workRptMaintRateDetailDayChart.jsp" scope="request"
 *                validate="false"
 */
public class WorkRptMaintRateDetailAction extends AuthAction
{
    /** 상세조회 (파트별)*/
    public static final int PART_DETAIL_FIND       = 1001;
    /** 상세조회 (유형별)*/
    public static final int TYPE_DETAIL_FIND       = 1002;
    /** 상세조회 (일별 차트)*/
    public static final int DAY_CHART_FIND         = 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        WorkRptMaintRateListForm workRptMaintRateListForm = (WorkRptMaintRateListForm) form;
        
        switch (workRptMaintRateListForm.getStrutsAction())
        {
            case WorkRptMaintRateDetailAction.BASE_SET_HEADER:
                setHeader(request, response, workRptMaintRateListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptMaintRateDetailAction.PART_DETAIL_FIND:
                findPartDetail(request, response, workRptMaintRateListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case WorkRptMaintRateDetailAction.TYPE_DETAIL_FIND:
                findTypeDetail(request, response, workRptMaintRateListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case WorkRptMaintRateDetailAction.DAY_CHART_FIND:
                findDayChart(request, response, workRptMaintRateListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case WorkRptMaintRateDetailAction.BASE_GRID_EXPORT:
                findPartDetail(request, response, workRptMaintRateListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, WorkRptMaintRateListForm workRptMaintRateListForm) throws IOException
    {
        super.setHeader(request, response, workRptMaintRateListForm.getListId(), workRptMaintRateListForm.getCurrentPageId()); 
    }
   
    /**
     * PART FIND DETAIL
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param workRptMaintRateListForm
     */
    
    private void findPartDetail(HttpServletRequest request, HttpServletResponse response, WorkRptMaintRateListForm workRptMaintRateListForm, boolean excelExport) throws Exception
    {
        WorkRptMaintRateDetailService workRptMaintRateDetailService = (WorkRptMaintRateDetailService) getBean("workRptMaintRateDetailService");
        WorkRptMaintRateDetailDTO workRptMaintRateDetailDTO = workRptMaintRateListForm.getWorkRptMaintRateDetailDTO();
      
        User user = getUser(request);
        List resultList = workRptMaintRateDetailService.findPartDetail(workRptMaintRateDetailDTO, user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,workRptMaintRateListForm.getListId(),workRptMaintRateListForm.getCurrentPageId(), workRptMaintRateListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, workRptMaintRateListForm.getListId());
    }
    /**
     * TYPE FIND DETAIL
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param workRptMaintRateListForm
     */
    
    private void findTypeDetail(HttpServletRequest request, HttpServletResponse response, WorkRptMaintRateListForm workRptMaintRateListForm, boolean excelExport) throws Exception
    {
        WorkRptMaintRateDetailService workRptMaintRateDetailService = (WorkRptMaintRateDetailService) getBean("workRptMaintRateDetailService");
        WorkRptMaintRateDetailDTO workRptMaintRateDetailDTO = workRptMaintRateListForm.getWorkRptMaintRateDetailDTO();
        
        User user = getUser(request);
        List resultList = workRptMaintRateDetailService.findTypeDetail(workRptMaintRateDetailDTO, user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,workRptMaintRateListForm.getListId(),workRptMaintRateListForm.getCurrentPageId(), workRptMaintRateListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, workRptMaintRateListForm.getListId());
    }
    /**
     * FIND DAY CHART
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param workRptMaintRateListForm
     */
    
    private void findDayChart(HttpServletRequest request, HttpServletResponse response, WorkRptMaintRateListForm workRptMaintRateListForm, boolean excelExport) throws Exception
    {
        WorkRptMaintRateDetailService workRptMaintRateDetailService = (WorkRptMaintRateDetailService) getBean("workRptMaintRateDetailService");
        WorkRptMaintRateDetailDTO workRptMaintRateDetailDTO = workRptMaintRateListForm.getWorkRptMaintRateDetailDTO();
        
        User user = getUser(request);
        List resultList = workRptMaintRateDetailService.findDayChart(workRptMaintRateDetailDTO, user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,workRptMaintRateListForm.getListId(),workRptMaintRateListForm.getCurrentPageId(), workRptMaintRateListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, workRptMaintRateListForm.getListId());
    }
}