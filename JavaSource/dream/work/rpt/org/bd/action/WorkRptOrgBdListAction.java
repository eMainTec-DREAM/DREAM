package dream.work.rpt.org.bd.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.work.rpt.org.bd.dto.WorkRptOrgBdCommonDTO;
import dream.work.rpt.org.bd.form.WorkRptOrgBdListForm;
import dream.work.rpt.org.bd.service.WorkRptOrgBdListService;

/**
 * 조직별 고장분석
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/workRptOrgBdList" name="workRptOrgBdListForm"
 *                input="/dream/work/rpt/org/bd/workRptOrgBdList.jsp" scope="request"
 *                validate="false"
 */
public class WorkRptOrgBdListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int LCC_EQUIP_LIST_FIND 			= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkRptOrgBdListForm workRptOrgBdListForm = (WorkRptOrgBdListForm) form;
        
        switch (workRptOrgBdListForm.getStrutsAction())
        {
        
            case WorkRptOrgBdListAction.LCC_EQUIP_LIST_FIND:
                findList(request,response, workRptOrgBdListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptOrgBdListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, workRptOrgBdListForm.getListId(), workRptOrgBdListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptOrgBdListAction.BASE_GRID_EXPORT:
            	findList(request,response, workRptOrgBdListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param workRptOrgBdListForm
     * @param excelExport
     * @throws Exception
     */
    private void findList(HttpServletRequest request,HttpServletResponse response, WorkRptOrgBdListForm workRptOrgBdListForm, boolean excelExport) throws Exception
    {
        WorkRptOrgBdListService workRptOrgBdListService = (WorkRptOrgBdListService) getBean("workRptOrgBdListService");
        
        WorkRptOrgBdCommonDTO workRptOrgBdCommonDTO = workRptOrgBdListForm.getWorkRptOrgBdCommonDTO();
        
        //Paging
        workRptOrgBdCommonDTO.setIsLoadMaxCount("Y".equals(workRptOrgBdListForm.getIsLoadMaxCount())?true:false);
        workRptOrgBdCommonDTO.setFirstRow(workRptOrgBdListForm.getFirstRow());
        workRptOrgBdCommonDTO.setOrderBy(workRptOrgBdListForm.getOrderBy());
        workRptOrgBdCommonDTO.setDirection(workRptOrgBdListForm.getDirection());
        
        List resultList = workRptOrgBdListService.findList(workRptOrgBdCommonDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workRptOrgBdListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workRptOrgBdListService.findTotalCount(workRptOrgBdCommonDTO,getUser(request));
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, workRptOrgBdListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
}