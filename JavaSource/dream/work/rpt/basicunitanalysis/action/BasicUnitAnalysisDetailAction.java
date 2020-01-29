package dream.work.rpt.basicunitanalysis.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.rpt.basicunitanalysis.dto.BasicUnitAnalysisDetailDTO;
import dream.work.rpt.basicunitanalysis.form.BasicUnitAnalysisDetailForm;
import dream.work.rpt.basicunitanalysis.service.BasicUnitAnalysisDetailService;

/**
 * 원단위분석 상세
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/basicUnitAnalysisDetailList" name="basicUnitAnalysisDetailForm"
 *                input="/dream/work/rpt/basicunitanalysis/basicUnitAnalysisDetailList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/basicUnitAnalysisDetailChart" name="basicUnitAnalysisDetailForm"
 *                input="/dream/work/rpt/basicunitanalysis/basicUnitAnalysisDetailChart.jsp" scope="request"
 *                validate="false"            
 */
public class BasicUnitAnalysisDetailAction extends AuthAction
{
    public static final int BASIC_UNIT_ANALYSIS_DETAIL_FIND 	= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        BasicUnitAnalysisDetailForm basicUnitAnalysisDetailForm = (BasicUnitAnalysisDetailForm) form;
        switch (basicUnitAnalysisDetailForm.getStrutsAction())
        {
            case BasicUnitAnalysisDetailAction.BASIC_UNIT_ANALYSIS_DETAIL_FIND:
                // 페이지 조회
                this.findDetail(request, response, basicUnitAnalysisDetailForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case BasicUnitAnalysisDetailAction.BASE_SET_HEADER:
                super.setHeader(request, response, basicUnitAnalysisDetailForm.getListId(), basicUnitAnalysisDetailForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case BasicUnitAnalysisDetailAction.BASE_GRID_EXPORT:
                findDetail(request,response, basicUnitAnalysisDetailForm);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 조회 
     * @author youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param basicUnitAnalysisDetailForm
     */
    private void findDetail(HttpServletRequest request,HttpServletResponse response, BasicUnitAnalysisDetailForm basicUnitAnalysisDetailForm) throws Exception
    {
        BasicUnitAnalysisDetailService basicUnitAnalysisDetailService = (BasicUnitAnalysisDetailService) getBean("basicUnitAnalysisDetailService");
        
        BasicUnitAnalysisDetailDTO basicUnitAnalysisDetailDTO = basicUnitAnalysisDetailForm.getBasicUnitAnalysisDetailDTO();
        
        List resultList = basicUnitAnalysisDetailService.findDetail(basicUnitAnalysisDetailDTO, getUser(request));
        
        super.makeJsonResult(resultList, request, response, basicUnitAnalysisDetailForm.getListId());
    }
    
}