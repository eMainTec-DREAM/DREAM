package dream.part.rpt.orgptusehist.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.part.rpt.orgptusehist.dto.PartRptOrgPtUseHistDetailDTO;
import dream.part.rpt.orgptusehist.form.PartRptOrgPtUseHistListForm;
import dream.part.rpt.orgptusehist.service.PartRptOrgPtUseHistDetailService;

/**
 * PartRptOrgPtUseHist Page - Detail Action
 * 
 * @author youngjoo38
 * @version $Id: $
 * @since 1.0
 * @struts:action path="/partRptOrgPtUseHistDetailList" name="partRptOrgPtUseHistListForm"
 *                input="/dream/part/rpt/orgptusehist/partRptOrgPtUseHistDetailList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/partRptOrgPtUseHistDetailChart" name="partRptOrgPtUseHistListForm"
 *                input="/dream/part/rpt/orgptusehist/partRptOrgPtUseHistDetailChart.jsp" scope="request"
 *                validate="false"
 */
public class PartRptOrgPtUseHistDetailAction extends AuthAction
{
    /** 상세조회 (파트별)*/
    public static final int DETAIL_FIND       = 1001;
    /** 상세조회 (일별 차트)*/
    public static final int CHART_FIND         = 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        PartRptOrgPtUseHistListForm partRptOrgPtUseHistListForm = (PartRptOrgPtUseHistListForm) form;
        
        switch (partRptOrgPtUseHistListForm.getStrutsAction())
        {
            case PartRptOrgPtUseHistDetailAction.BASE_SET_HEADER:
                setHeader(request, response, partRptOrgPtUseHistListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case PartRptOrgPtUseHistDetailAction.DETAIL_FIND:
                findDetail(request, response, partRptOrgPtUseHistListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case PartRptOrgPtUseHistDetailAction.CHART_FIND:
                findChart(request, response, partRptOrgPtUseHistListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case PartRptOrgPtUseHistDetailAction.BASE_GRID_EXPORT:
                findDetail(request, response, partRptOrgPtUseHistListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, PartRptOrgPtUseHistListForm partRptOrgPtUseHistListForm) throws IOException
    {
        super.setHeader(request, response, partRptOrgPtUseHistListForm.getListId(), partRptOrgPtUseHistListForm.getCurrentPageId()); 
    }
   
    /**
     * FIND DETAIL
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param partRptOrgPtUseHistListForm
     */
    
    private void findDetail(HttpServletRequest request, HttpServletResponse response, PartRptOrgPtUseHistListForm partRptOrgPtUseHistListForm, boolean excelExport) throws Exception
    {
        PartRptOrgPtUseHistDetailService partRptOrgPtUseHistDetailService = (PartRptOrgPtUseHistDetailService) getBean("partRptOrgPtUseHistDetailService");
        PartRptOrgPtUseHistDetailDTO partRptOrgPtUseHistDetailDTO = partRptOrgPtUseHistListForm.getPartRptOrgPtUseHistDetailDTO();
      
        User user = getUser(request);
        List resultList = partRptOrgPtUseHistDetailService.findDetail(partRptOrgPtUseHistDetailDTO, user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,partRptOrgPtUseHistListForm.getListId(),partRptOrgPtUseHistListForm.getCurrentPageId(), partRptOrgPtUseHistListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, partRptOrgPtUseHistListForm.getListId());
    }
    
    /**
     * FIND CHART
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param partRptOrgPtUseHistListForm
     */
    
    private void findChart(HttpServletRequest request, HttpServletResponse response, PartRptOrgPtUseHistListForm partRptOrgPtUseHistListForm, boolean excelExport) throws Exception
    {
        PartRptOrgPtUseHistDetailService partRptOrgPtUseHistDetailService = (PartRptOrgPtUseHistDetailService) getBean("partRptOrgPtUseHistDetailService");
        PartRptOrgPtUseHistDetailDTO partRptOrgPtUseHistDetailDTO = partRptOrgPtUseHistListForm.getPartRptOrgPtUseHistDetailDTO();
        
        User user = getUser(request);
        List resultList = partRptOrgPtUseHistDetailService.findChart(partRptOrgPtUseHistDetailDTO, user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,partRptOrgPtUseHistListForm.getListId(),partRptOrgPtUseHistListForm.getCurrentPageId(), partRptOrgPtUseHistListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, partRptOrgPtUseHistListForm.getListId());
    }
}