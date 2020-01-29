package dream.work.rpt.org.mtbfmttr.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.work.rpt.org.mtbfmttr.dto.WorkRptOrgMtbfmttrCommonDTO;
import dream.work.rpt.org.mtbfmttr.form.WorkRptOrgMtbfmttrListForm;
import dream.work.rpt.org.mtbfmttr.service.WorkRptOrgMtbfmttrListService;

/**
 * 조직별MTBF,MTTR
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/workRptOrgMtbfmttrList" name="workRptOrgMtbfmttrListForm"
 *                input="/dream/work/rpt/org/mtbfmttr/workRptOrgMtbfmttrList.jsp" scope="request"
 *                validate="false"
 */
public class WorkRptOrgMtbfmttrListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int MTTRMTBF_EQUIP_LIST_FIND 			= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkRptOrgMtbfmttrListForm workRptOrgMtbfmttrListForm = (WorkRptOrgMtbfmttrListForm) form;
        
        switch (workRptOrgMtbfmttrListForm.getStrutsAction())
        {
        
            case WorkRptOrgMtbfmttrListAction.MTTRMTBF_EQUIP_LIST_FIND:
                findList(request,response, workRptOrgMtbfmttrListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptOrgMtbfmttrListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, workRptOrgMtbfmttrListForm.getListId(), workRptOrgMtbfmttrListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptOrgMtbfmttrListAction.BASE_GRID_EXPORT:
            	findList(request,response, workRptOrgMtbfmttrListForm, true);
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
     * @param workRptOrgMtbfmttrListForm
     * @param excelExport
     * @throws Exception
     */
    private void findList(HttpServletRequest request,HttpServletResponse response, WorkRptOrgMtbfmttrListForm workRptOrgMtbfmttrListForm, boolean excelExport) throws Exception
    {
        WorkRptOrgMtbfmttrListService workRptOrgMtbfmttrListService = (WorkRptOrgMtbfmttrListService) getBean("workRptOrgMtbfmttrListService");
        
        WorkRptOrgMtbfmttrCommonDTO workRptOrgMtbfmttrCommonDTO = workRptOrgMtbfmttrListForm.getWorkRptOrgMtbfmttrCommonDTO();
        
        //Paging
        workRptOrgMtbfmttrCommonDTO.setIsLoadMaxCount("Y".equals(workRptOrgMtbfmttrListForm.getIsLoadMaxCount())?true:false);
        workRptOrgMtbfmttrCommonDTO.setFirstRow(workRptOrgMtbfmttrListForm.getFirstRow());
        workRptOrgMtbfmttrCommonDTO.setOrderBy(workRptOrgMtbfmttrListForm.getOrderBy());
        workRptOrgMtbfmttrCommonDTO.setDirection(workRptOrgMtbfmttrListForm.getDirection());
        
        List resultList = workRptOrgMtbfmttrListService.findList(workRptOrgMtbfmttrCommonDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workRptOrgMtbfmttrListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workRptOrgMtbfmttrListService.findTotalCount(workRptOrgMtbfmttrCommonDTO,getUser(request));
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, workRptOrgMtbfmttrListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
}