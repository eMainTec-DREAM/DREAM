package dream.work.rpt.pmins.orgach.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.work.rpt.pmins.orgach.dto.WorkRptPminsOrgAchCommonDTO;
import dream.work.rpt.pmins.orgach.form.WorkRptPminsOrgAchListForm;
import dream.work.rpt.pmins.orgach.service.WorkRptPminsOrgAchListService;

/**
 * 예방점검 이행율(조직)
 * @author  sy.yang
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/workRptPminsOrgAchList" name="workRptPminsOrgAchListForm"
 *                input="/dream/work/rpt/pmins/orgach/workRptPminsOrgAchList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workRptPminsOrgAchList" path="/dream/work/rpt/pmins/orgach/workRptPminsOrgAchList.jsp"
 *                        redirect="false"
 */
public class WorkRptPminsOrgAchListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int PMINS_ACH_LIST_FIND 			= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkRptPminsOrgAchListForm workRptPminsOrgAchListForm = (WorkRptPminsOrgAchListForm) form;
        
        switch (workRptPminsOrgAchListForm.getStrutsAction())
        {
        
            case WorkRptPminsOrgAchListAction.PMINS_ACH_LIST_FIND:
                findList(request,response, workRptPminsOrgAchListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptPminsOrgAchListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, workRptPminsOrgAchListForm.getListId(), workRptPminsOrgAchListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptPminsOrgAchListAction.BASE_GRID_EXPORT:
            	findList(request,response, workRptPminsOrgAchListForm, true);
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
     * @author  sy.yang
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param workRptPminsOrgAchListForm
     * @param excelExport
     * @throws Exception
     */
    private void findList(HttpServletRequest request,HttpServletResponse response, WorkRptPminsOrgAchListForm workRptPminsOrgAchListForm, boolean excelExport) throws Exception
    {
        WorkRptPminsOrgAchListService workRptPminsOrgAchListService = (WorkRptPminsOrgAchListService) getBean("workRptPminsOrgAchListService");
        WorkRptPminsOrgAchCommonDTO workRptPminsOrgAchCommonDTO = workRptPminsOrgAchListForm.getWorkRptPminsOrgAchCommonDTO();
        
        //Paging
        workRptPminsOrgAchCommonDTO.setIsLoadMaxCount("Y".equals(workRptPminsOrgAchListForm.getIsLoadMaxCount())?true:false);
        workRptPminsOrgAchCommonDTO.setFirstRow(workRptPminsOrgAchListForm.getFirstRow());
        workRptPminsOrgAchCommonDTO.setOrderBy(workRptPminsOrgAchListForm.getOrderBy());
        workRptPminsOrgAchCommonDTO.setDirection(workRptPminsOrgAchListForm.getDirection());
        
        List resultList = workRptPminsOrgAchListService.findList(workRptPminsOrgAchCommonDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workRptPminsOrgAchListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workRptPminsOrgAchListService.findTotalCount(workRptPminsOrgAchCommonDTO,getUser(request));
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, workRptPminsOrgAchListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
}