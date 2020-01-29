package dream.part.rpt.stockkpi.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.part.rpt.stockkpi.dto.StockKpiCommonDTO;
import dream.part.rpt.stockkpi.form.StockKpiListForm;
import dream.part.rpt.stockkpi.service.StockKpiListService;

/**
 * 재고지표
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/stockKpiList" name="stockKpiListForm"
 *                input="/dream/part/rpt/stockkpi/stockKpiList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="stockKpiList" path="/dream/part/rpt/stockkpi/stockKpiList.jsp"
 *                        redirect="false"
 */
public class StockKpiListAction extends AuthAction
{
    /** 조회 */
    public static final int PMINS_ACH_LIST_FIND 			= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        StockKpiListForm stockKpiListForm = (StockKpiListForm) form;
        
        switch (stockKpiListForm.getStrutsAction())
        {
        
            case StockKpiListAction.PMINS_ACH_LIST_FIND:
                findList(request,response, stockKpiListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case StockKpiListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, stockKpiListForm.getListId(), stockKpiListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case StockKpiListAction.BASE_GRID_EXPORT:
            	findList(request,response, stockKpiListForm, true);
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
     * @param stockKpiListForm
     * @param excelExport
     * @throws Exception
     */
    private void findList(HttpServletRequest request,HttpServletResponse response, StockKpiListForm stockKpiListForm, boolean excelExport) throws Exception
    {
        StockKpiListService stockKpiListService = (StockKpiListService) getBean("stockKpiListService");
        
        StockKpiCommonDTO stockKpiCommonDTO = stockKpiListForm.getStockKpiCommonDTO();
        
        //Paging
        stockKpiCommonDTO.setIsLoadMaxCount("Y".equals(stockKpiListForm.getIsLoadMaxCount())?true:false);
        stockKpiCommonDTO.setFirstRow(stockKpiListForm.getFirstRow());
        stockKpiCommonDTO.setOrderBy(stockKpiListForm.getOrderBy());
        stockKpiCommonDTO.setDirection(stockKpiListForm.getDirection());
        
        List resultList = stockKpiListService.findList(stockKpiCommonDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(stockKpiListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = stockKpiListService.findTotalCount(stockKpiCommonDTO,getUser(request));
        
        if(excelExport) super.makeGridExport(resultList, request, response,stockKpiListForm.getListId(),stockKpiListForm.getCurrentPageId(), stockKpiListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
}