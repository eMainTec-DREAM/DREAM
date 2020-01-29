package dream.work.rpt.pmi.point.value.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.work.rpt.pmi.point.value.dto.WorkRptPmiPointValueCommonDTO;
import dream.work.rpt.pmi.point.value.form.WorkRptPmiPointValueListForm;
import dream.work.rpt.pmi.point.value.service.WorkRptPmiPointValueListService;

/**
 * 정기점검항목결과 - 목록 Action
 * @author  nhkim8548
 * @version $Id: WorkRptPmiPointValueListAction.java, v1.0 2019/07/10 10:26:30 nhkim8548 Exp $
 * @since   1.0
 * @struts:action path="/workRptPmiPointValueList" name="workRptPmiPointValueListForm"
 *                input="/dream/work/rpt/pmi/point/value/workRptPmiPointValueList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workRptPmiPointValueList" path="/dream/work/rpt/pmi/point/value/workRptPmiPointValueList.jsp"
 *                        redirect="false"
 */
public class WorkRptPmiPointValueListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int WORK_PMI_POINT_VALUE_LIST_FIND  = 8001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkRptPmiPointValueListForm workRptPmiPointValueListForm = (WorkRptPmiPointValueListForm) form;
        
        switch (workRptPmiPointValueListForm.getStrutsAction())
        {
        
            case WorkRptPmiPointValueListAction.WORK_PMI_POINT_VALUE_LIST_FIND:
                findList(request,response, workRptPmiPointValueListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptPmiPointValueListAction.BASE_SET_HEADER:
                super.setHeader(request, response, workRptPmiPointValueListForm.getListId(), workRptPmiPointValueListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptPmiPointValueListAction.BASE_GRID_EXPORT:
                findList(request,response, workRptPmiPointValueListForm, true);
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
     * @author  nhkim8548
     * @version $Id: WorkRptPmiPointValueListAction.java, v1.0 2019/07/10 10:33:21 nhkim8548 Exp $
     * @since   1.0
     * 
     * @param   request
     * @param   workRptPmiPointValueListForm
     * @throws  Exception
     */
    private void findList(HttpServletRequest request,HttpServletResponse response, WorkRptPmiPointValueListForm workRptPmiPointValueListForm, boolean excelExport) throws Exception
    {
        WorkRptPmiPointValueListService workRptPmiPointValueListService = (WorkRptPmiPointValueListService) getBean("workRptPmiPointValueListService");
        WorkRptPmiPointValueCommonDTO workRptPmiPointValueCommonDTO = workRptPmiPointValueListForm.getWorkRptPmiPointValueCommonDTO();
        
        //Paging
        workRptPmiPointValueCommonDTO.setIsLoadMaxCount("Y".equals(workRptPmiPointValueListForm.getIsLoadMaxCount())?true:false);
        workRptPmiPointValueCommonDTO.setFirstRow(workRptPmiPointValueListForm.getFirstRow());
        workRptPmiPointValueCommonDTO.setOrderBy(workRptPmiPointValueListForm.getOrderBy());
        workRptPmiPointValueCommonDTO.setDirection(workRptPmiPointValueListForm.getDirection());

        User user = getUser(request);
        List resultList = workRptPmiPointValueListService.findPointValueList(workRptPmiPointValueCommonDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workRptPmiPointValueListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workRptPmiPointValueListService.findTotalCount(workRptPmiPointValueCommonDTO, user);
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, workRptPmiPointValueListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
}