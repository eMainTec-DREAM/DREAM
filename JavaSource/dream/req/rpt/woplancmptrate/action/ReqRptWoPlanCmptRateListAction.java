package dream.req.rpt.woplancmptrate.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.req.rpt.woplancmptrate.dto.ReqRptWoPlanCmptDetailListDTO;
import dream.req.rpt.woplancmptrate.dto.ReqRptWoPlanCmptRateCommonDTO;
import dream.req.rpt.woplancmptrate.form.ReqRptWoPlanCmptRateListForm;
import dream.req.rpt.woplancmptrate.service.ReqRptWoPlanCmptRateListService;

/**
 * 작업의뢰 계획대비 실행 비율 목록 - List Action
 * 
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/reqRptWoPlanCmptRateList" name="reqRptWoPlanCmptRateListForm"
 *                input="/dream/req/rpt/woplancmptrate/reqRptWoPlanCmptRateList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="reqRptWoPlanCmptRateList" path="/dream/req/rpt/woplancmptrate/reqRptWoPlanCmptRateList.jsp"
 *                        redirect="false"
 */
public class ReqRptWoPlanCmptRateListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND       = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        ReqRptWoPlanCmptRateListForm reqRptWoPlanCmptRateListForm = (ReqRptWoPlanCmptRateListForm) form;
        
        switch (reqRptWoPlanCmptRateListForm.getStrutsAction())
        {
            case ReqRptWoPlanCmptRateListAction.BASE_SET_HEADER:
                setHeader(request, response, reqRptWoPlanCmptRateListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ReqRptWoPlanCmptRateListAction.LIST_FIND:
                findList(request, response, reqRptWoPlanCmptRateListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case ReqRptWoPlanCmptRateListAction.BASE_GRID_EXPORT:
                findList(request, response, reqRptWoPlanCmptRateListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("reqRptWoPlanCmptRateList");
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, ReqRptWoPlanCmptRateListForm reqRptWoPlanCmptRateListForm) throws IOException
    {
        super.setHeader(request, response, reqRptWoPlanCmptRateListForm.getListId(), reqRptWoPlanCmptRateListForm.getCurrentPageId()); 
    }
   
    /**
     * FIND LIST
     * @author  js.lee
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param reqRptWoPlanCmptRateListForm
     */
    
    private void findList(HttpServletRequest request, HttpServletResponse response, ReqRptWoPlanCmptRateListForm reqRptWoPlanCmptRateListForm, boolean excelExport) throws Exception
    {
        ReqRptWoPlanCmptRateListService reqRptWoPlanCmptRateListService = (ReqRptWoPlanCmptRateListService) getBean("reqRptWoPlanCmptRateListService");
        ReqRptWoPlanCmptRateCommonDTO reqRptWoPlanCmptRateCommonDTO = reqRptWoPlanCmptRateListForm.getReqRptWoPlanCmptRateCommonDTO();
       
        //Paging
        reqRptWoPlanCmptRateCommonDTO.setIsLoadMaxCount("Y".equals(reqRptWoPlanCmptRateListForm.getIsLoadMaxCount())?true:false);
        reqRptWoPlanCmptRateCommonDTO.setFirstRow(reqRptWoPlanCmptRateListForm.getFirstRow());
        reqRptWoPlanCmptRateCommonDTO.setOrderBy(reqRptWoPlanCmptRateListForm.getOrderBy());
        reqRptWoPlanCmptRateCommonDTO.setDirection(reqRptWoPlanCmptRateListForm.getDirection());
        
        User user = getUser(request);
        List resultList = reqRptWoPlanCmptRateListService.findList(reqRptWoPlanCmptRateCommonDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(reqRptWoPlanCmptRateListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = reqRptWoPlanCmptRateListService.findTotalCount(reqRptWoPlanCmptRateCommonDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,reqRptWoPlanCmptRateListForm.getListId(),reqRptWoPlanCmptRateListForm.getCurrentPageId(), reqRptWoPlanCmptRateListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
}