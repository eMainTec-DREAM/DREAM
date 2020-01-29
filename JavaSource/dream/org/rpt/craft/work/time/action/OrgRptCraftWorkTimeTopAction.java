package dream.org.rpt.craft.work.time.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.org.rpt.craft.work.time.dto.OrgRptCraftWorkTimeTopCommonDTO;
import dream.org.rpt.craft.work.time.form.OrgRptCraftWorkTimeTopForm;
import dream.org.rpt.craft.work.time.service.OrgRptCraftWorkTimeTopService;

/**
 * 작업자 작업시간 Top
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/orgRptCraftWorkTimeTop" name="orgRptCraftWorkTimeTopForm"
 *                input="/dream/org/rpt/craft/work/time/orgRptCraftWorkTimeTop.jsp" scope="request"
 *                validate="false"
 */
public class OrgRptCraftWorkTimeTopAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int LIST_FIND 			= 1001;
    /** 차트 검색 */
    public static final int CHART_FIND 			= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        OrgRptCraftWorkTimeTopForm orgRptCraftWorkTimeTopForm = (OrgRptCraftWorkTimeTopForm) form;
        
        switch (orgRptCraftWorkTimeTopForm.getStrutsAction())
        {
        
            case OrgRptCraftWorkTimeTopAction.LIST_FIND:
                findList(request,response, orgRptCraftWorkTimeTopForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case OrgRptCraftWorkTimeTopAction.CHART_FIND:
                findChartList(request,response, orgRptCraftWorkTimeTopForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case OrgRptCraftWorkTimeTopAction.BASE_SET_HEADER:
            	super.setHeader(request, response, orgRptCraftWorkTimeTopForm.getListId(), orgRptCraftWorkTimeTopForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case OrgRptCraftWorkTimeTopAction.BASE_GRID_EXPORT:
            	findList(request,response, orgRptCraftWorkTimeTopForm, true);
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
     * @author js.lee
     * @since   1.0
     *
     * @param request
     * @param response
     * @param orgRptCraftWorkTimeTopForm
     * @param excelExport
     * @throws Exception
     */
    private void findList(HttpServletRequest request,HttpServletResponse response, OrgRptCraftWorkTimeTopForm orgRptCraftWorkTimeTopForm, boolean excelExport) throws Exception
    {
        OrgRptCraftWorkTimeTopService orgRptCraftWorkTimeTopService = (OrgRptCraftWorkTimeTopService) getBean("orgRptCraftWorkTimeTopService");
        
        OrgRptCraftWorkTimeTopCommonDTO orgRptCraftWorkTimeTopCommonDTO = orgRptCraftWorkTimeTopForm.getOrgRptCraftWorkTimeTopCommonDTO();
        
        //Paging
        orgRptCraftWorkTimeTopCommonDTO.setIsLoadMaxCount("Y".equals(orgRptCraftWorkTimeTopForm.getIsLoadMaxCount())?true:false);
        orgRptCraftWorkTimeTopCommonDTO.setFirstRow(orgRptCraftWorkTimeTopForm.getFirstRow());
        orgRptCraftWorkTimeTopCommonDTO.setOrderBy(orgRptCraftWorkTimeTopForm.getOrderBy());
        orgRptCraftWorkTimeTopCommonDTO.setDirection(orgRptCraftWorkTimeTopForm.getDirection());
        
        List resultList = orgRptCraftWorkTimeTopService.findList(orgRptCraftWorkTimeTopCommonDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(orgRptCraftWorkTimeTopForm.getIsTotalCount()) == 0 && !excelExport) totalCount = orgRptCraftWorkTimeTopService.findTotalCount(orgRptCraftWorkTimeTopCommonDTO,getUser(request));
        
        if(excelExport) super.makeGridExport(resultList, request, response,orgRptCraftWorkTimeTopForm.getListId(),orgRptCraftWorkTimeTopForm.getCurrentPageId(), orgRptCraftWorkTimeTopForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * grid Chart find
     * @author js.lee
     * @since   1.0
     *
     * @param request
     * @param response
     * @param orgRptCraftWorkTimeTopForm
     * @param excelExport
     * @throws Exception
     */
    private void findChartList(HttpServletRequest request,HttpServletResponse response, OrgRptCraftWorkTimeTopForm orgRptCraftWorkTimeTopForm, boolean excelExport) throws Exception
    {
        OrgRptCraftWorkTimeTopService orgRptCraftWorkTimeTopService = (OrgRptCraftWorkTimeTopService) getBean("orgRptCraftWorkTimeTopService");
        
        OrgRptCraftWorkTimeTopCommonDTO orgRptCraftWorkTimeTopCommonDTO = orgRptCraftWorkTimeTopForm.getOrgRptCraftWorkTimeTopCommonDTO();

        List resultList = orgRptCraftWorkTimeTopService.findChartList(orgRptCraftWorkTimeTopCommonDTO, getUser(request));
        
        CommonUtil.makeJsonResult(resultList, request, response, "");
    }
    
}