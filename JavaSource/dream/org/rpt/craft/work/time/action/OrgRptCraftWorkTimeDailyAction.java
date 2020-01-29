package dream.org.rpt.craft.work.time.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.org.rpt.craft.work.time.dto.OrgRptCraftWorkTimeDailyDTO;
import dream.org.rpt.craft.work.time.form.OrgRptCraftWorkTimeDailyForm;
import dream.org.rpt.craft.work.time.service.OrgRptCraftWorkTimeDailyService;

/**
 * 작업자 일별 작업시간
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/orgRptCraftWorkTimeDaily" name="orgRptCraftWorkTimeDailyForm"
 *                input="/dream/org/rpt/craft/work/time/orgRptCraftWorkTimeDaily.jsp" scope="request"
 *                validate="false"
 */
public class OrgRptCraftWorkTimeDailyAction extends AuthAction
{
    public static final int LIST_FIND 	= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        OrgRptCraftWorkTimeDailyForm orgRptCraftWorkTimeDailyForm = (OrgRptCraftWorkTimeDailyForm) form;
        switch (orgRptCraftWorkTimeDailyForm.getStrutsAction())
        {
            case OrgRptCraftWorkTimeDailyAction.LIST_FIND:
                findList(request, response, orgRptCraftWorkTimeDailyForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case OrgRptCraftWorkTimeDailyAction.BASE_SET_HEADER:
                super.setHeader(request, response, orgRptCraftWorkTimeDailyForm.getListId(), orgRptCraftWorkTimeDailyForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case OrgRptCraftWorkTimeDailyAction.BASE_GRID_EXPORT:
            	findList(request,response, orgRptCraftWorkTimeDailyForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 작업자 일별 작업시간 조회
     * @author js.lee
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param orgRptCraftWorkTimeDailyForm
     */
    private void findList(HttpServletRequest request,HttpServletResponse response, OrgRptCraftWorkTimeDailyForm orgRptCraftWorkTimeDailyForm, boolean excelExport) throws Exception
    {
        OrgRptCraftWorkTimeDailyService orgRptCraftWorkTimeDailyService = (OrgRptCraftWorkTimeDailyService) getBean("orgRptCraftWorkTimeDailyService");
        OrgRptCraftWorkTimeDailyDTO orgRptCraftWorkTimeDailyDTO = orgRptCraftWorkTimeDailyForm.getOrgRptCraftWorkTimeDailyDTO();
        
        //Paging
        orgRptCraftWorkTimeDailyDTO.setIsLoadMaxCount("Y".equals(orgRptCraftWorkTimeDailyForm.getIsLoadMaxCount())?true:false);
        orgRptCraftWorkTimeDailyDTO.setFirstRow(orgRptCraftWorkTimeDailyForm.getFirstRow());
        orgRptCraftWorkTimeDailyDTO.setOrderBy(orgRptCraftWorkTimeDailyForm.getOrderBy());
        orgRptCraftWorkTimeDailyDTO.setDirection(orgRptCraftWorkTimeDailyForm.getDirection());
        
        
        List resultList = orgRptCraftWorkTimeDailyService.findList(orgRptCraftWorkTimeDailyDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(orgRptCraftWorkTimeDailyForm.getIsTotalCount()) == 0 && !excelExport) totalCount = orgRptCraftWorkTimeDailyService.findTotalCount(orgRptCraftWorkTimeDailyDTO,getUser(request));
        
        if(excelExport) super.makeGridExport(resultList, request, response,orgRptCraftWorkTimeDailyForm.getListId(),orgRptCraftWorkTimeDailyForm.getCurrentPageId(), orgRptCraftWorkTimeDailyForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
  
    }
}