package dream.part.rpt.mapartrptmonthly.action;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.part.rpt.mapartrptmonthly.dto.PartRptMonthlyStockListDTO;
import dream.part.rpt.mapartrptmonthly.form.PartRptMonthlyStockListForm;
import dream.part.rpt.mapartrptmonthly.service.PartRptMonthlyStockListService;

/**
 * 부품수불부 요약 Action
 * @author  euna0207
 * @version $Id: PartRptMonthlyStockListAction.java,v 1.0 2015/12/02 09:10:21 euna0207 Exp $
 * @since   1.0
 * @struts:action path="/partRptMonthlyStockList" name="partRptMonthlyStockListForm"
 *                input="/dream/part/rpt/mapartrptmonthly/partRptMonthlyStockList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="partRptMonthlyStockList" path="/dream/part/rpt/mapartrptmonthly/partRptMonthlyStockList.jsp"
 *                        redirect="false"
 */
public class PartRptMonthlyStockListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND 		= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        PartRptMonthlyStockListForm partRptMonthlyStockListForm = (PartRptMonthlyStockListForm) form;
        
        switch (partRptMonthlyStockListForm.getStrutsAction())
        {
            case PartRptMonthlyStockListAction.LIST_FIND:
                returnActionForward = findList(request, partRptMonthlyStockListForm, response, mapping);
                break;
            default:
                returnActionForward = mapping.findForward("partRptMonthlyStockList");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  euna0207
     * @version $Id: PartRptMonthlyStockListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param partRptMonthlyStockListForm
     * @param response
     * @return 
     * @throws Exception
     */
    private ActionForward findList(HttpServletRequest request, PartRptMonthlyStockListForm partRptMonthlyStockListForm, HttpServletResponse response, ActionMapping mapping) throws Exception
    {
    	PartRptMonthlyStockListService partRptMonthlyStockListService = (PartRptMonthlyStockListService) getBean("partRptMonthlyStockListService");        

    	PartRptMonthlyStockListDTO partRptMonthlyStockListDTO = partRptMonthlyStockListForm.getPartRptMonthlyStockListDTO();
    	
    	boolean excelExport = "Y".equals(partRptMonthlyStockListForm.getExcelYn())?true:false;
    	//Paging
    	partRptMonthlyStockListDTO.setIsLoadMaxCount("Y".equals(partRptMonthlyStockListForm.getIsLoadMaxCount())?true:false);
    	partRptMonthlyStockListDTO.setFirstRow(partRptMonthlyStockListForm.getFirstRow());
    	partRptMonthlyStockListDTO.setOrderBy(partRptMonthlyStockListForm.getOrderBy());
    	partRptMonthlyStockListDTO.setDirection(partRptMonthlyStockListForm.getDirection());
        
    	partRptMonthlyStockListDTO.setCompNo(getUser(request).getCompNo());
        //리스트를 조회한다.
    	User user = getUser(request);
        List resultList = partRptMonthlyStockListService.findList(partRptMonthlyStockListDTO,getUser(request));

        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(partRptMonthlyStockListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = partRptMonthlyStockListService.findTotalCount(partRptMonthlyStockListDTO, user);
        
        if(excelExport) {
            CommonUtil.makeGridExport(resultList, request, response,partRptMonthlyStockListForm);
            return new ActionForward("/gridExport");
        }
        else {
            CommonUtil.makeJsonResult(resultList, request, response, totalCount);
            return mapping.findForward("jsonPage");
        }
	}
    
}
