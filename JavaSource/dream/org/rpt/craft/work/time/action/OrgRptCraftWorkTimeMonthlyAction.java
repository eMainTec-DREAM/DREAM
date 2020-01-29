package dream.org.rpt.craft.work.time.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.org.rpt.craft.work.time.dto.OrgRptCraftWorkTimeMonthlyDTO;
import dream.org.rpt.craft.work.time.form.OrgRptCraftWorkTimeMonthlyForm;
import dream.org.rpt.craft.work.time.service.OrgRptCraftWorkTimeMonthlyService;

/**
 * 작업자 월별 작업시간
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/orgRptCraftWorkTimeMonthly" name="orgRptCraftWorkTimeMonthlyForm"
 *                input="/dream/org/rpt/craft/work/time/orgRptCraftWorkTimeMonthly.jsp" scope="request"
 *                validate="false"
 */
public class OrgRptCraftWorkTimeMonthlyAction extends AuthAction
{
    public static final int LIST_FIND 	= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        OrgRptCraftWorkTimeMonthlyForm orgRptCraftWorkTimeMonthlyForm = (OrgRptCraftWorkTimeMonthlyForm) form;
        switch (orgRptCraftWorkTimeMonthlyForm.getStrutsAction())
        {
            case OrgRptCraftWorkTimeMonthlyAction.LIST_FIND:
                findList(request, response, orgRptCraftWorkTimeMonthlyForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case OrgRptCraftWorkTimeMonthlyAction.BASE_SET_HEADER:
                super.setHeader(request, response, orgRptCraftWorkTimeMonthlyForm.getListId(), orgRptCraftWorkTimeMonthlyForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case OrgRptCraftWorkTimeMonthlyAction.BASE_GRID_EXPORT:
            	findList(request,response, orgRptCraftWorkTimeMonthlyForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 작업자 월별 작업시간 조회
     * @author js.lee
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param orgRptCraftWorkTimeMonthlyForm
     */
    private void findList(HttpServletRequest request,HttpServletResponse response, OrgRptCraftWorkTimeMonthlyForm orgRptCraftWorkTimeMonthlyForm, boolean excelExport) throws Exception
    {
        OrgRptCraftWorkTimeMonthlyService orgRptCraftWorkTimeMonthlyService = (OrgRptCraftWorkTimeMonthlyService) getBean("orgRptCraftWorkTimeMonthlyService");
        OrgRptCraftWorkTimeMonthlyDTO orgRptCraftWorkTimeMonthlyDTO = orgRptCraftWorkTimeMonthlyForm.getOrgRptCraftWorkTimeMonthlyDTO();
        
        //Paging
        orgRptCraftWorkTimeMonthlyDTO.setIsLoadMaxCount("Y".equals(orgRptCraftWorkTimeMonthlyForm.getIsLoadMaxCount())?true:false);
        orgRptCraftWorkTimeMonthlyDTO.setFirstRow(orgRptCraftWorkTimeMonthlyForm.getFirstRow());
        orgRptCraftWorkTimeMonthlyDTO.setOrderBy(orgRptCraftWorkTimeMonthlyForm.getOrderBy());
        orgRptCraftWorkTimeMonthlyDTO.setDirection(orgRptCraftWorkTimeMonthlyForm.getDirection());
        
        
        List resultList = orgRptCraftWorkTimeMonthlyService.findList(orgRptCraftWorkTimeMonthlyDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(orgRptCraftWorkTimeMonthlyForm.getIsTotalCount()) == 0 && !excelExport) totalCount = orgRptCraftWorkTimeMonthlyService.findTotalCount(orgRptCraftWorkTimeMonthlyDTO,getUser(request));
        
        if(excelExport) super.makeGridExport(resultList, request, response,orgRptCraftWorkTimeMonthlyForm.getListId(),orgRptCraftWorkTimeMonthlyForm.getCurrentPageId(), orgRptCraftWorkTimeMonthlyForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
  
    }
}