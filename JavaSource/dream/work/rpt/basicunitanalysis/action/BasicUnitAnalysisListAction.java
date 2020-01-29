package dream.work.rpt.basicunitanalysis.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.rpt.basicunitanalysis.dto.BasicUnitAnalysisCommonDTO;
import dream.work.rpt.basicunitanalysis.form.BasicUnitAnalysisListForm;
import dream.work.rpt.basicunitanalysis.service.BasicUnitAnalysisListService;

/**
 * 원단위분석
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/basicUnitAnalysisList" name="basicUnitAnalysisListForm"
 *                input="/dream/work/rpt/basicunitanalysis/basicUnitAnalysisList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="basicUnitAnalysisList" path="/dream/work/rpt/basicunitanalysis/basicUnitAnalysisList.jsp"
 *                        redirect="false"
 */
public class BasicUnitAnalysisListAction extends AuthAction
{
    /** 조회 */
    public static final int BASIC_UNIT_ANALYSIS_LIST_FIND 			= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        BasicUnitAnalysisListForm basicUnitAnalysisListForm = (BasicUnitAnalysisListForm) form;
        
        switch (basicUnitAnalysisListForm.getStrutsAction())
        {
        
            case BasicUnitAnalysisListAction.BASIC_UNIT_ANALYSIS_LIST_FIND:
                findList(request,response, basicUnitAnalysisListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case BasicUnitAnalysisListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, basicUnitAnalysisListForm.getListId(), basicUnitAnalysisListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case BasicUnitAnalysisListAction.BASE_GRID_EXPORT:
            	findList(request,response, basicUnitAnalysisListForm, true);
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
     * @param basicUnitAnalysisListForm
     * @param excelExport
     * @throws Exception
     */
    private void findList(HttpServletRequest request,HttpServletResponse response, BasicUnitAnalysisListForm basicUnitAnalysisListForm, boolean excelExport) throws Exception
    {
        BasicUnitAnalysisListService basicUnitAnalysisListService = (BasicUnitAnalysisListService) getBean("basicUnitAnalysisListService");
        
        BasicUnitAnalysisCommonDTO basicUnitAnalysisCommonDTO = basicUnitAnalysisListForm.getBasicUnitAnalysisCommonDTO();
        
        //Paging
        basicUnitAnalysisCommonDTO.setIsLoadMaxCount("Y".equals(basicUnitAnalysisListForm.getIsLoadMaxCount())?true:false);
        basicUnitAnalysisCommonDTO.setFirstRow(basicUnitAnalysisListForm.getFirstRow());
        basicUnitAnalysisCommonDTO.setOrderBy(basicUnitAnalysisListForm.getOrderBy());
        basicUnitAnalysisCommonDTO.setDirection(basicUnitAnalysisListForm.getDirection());
        
        List resultList = basicUnitAnalysisListService.findList(basicUnitAnalysisCommonDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(basicUnitAnalysisListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = basicUnitAnalysisListService.findTotalCount(basicUnitAnalysisCommonDTO,getUser(request));
        
        if(excelExport) super.makeGridExport(resultList, request, response,basicUnitAnalysisListForm.getListId(),basicUnitAnalysisListForm.getCurrentPageId(), basicUnitAnalysisListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
}