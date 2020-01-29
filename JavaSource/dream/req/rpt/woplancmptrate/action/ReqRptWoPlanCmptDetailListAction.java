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
import dream.req.rpt.woplancmptrate.form.ReqRptWoPlanCmptDetailListForm;
import dream.req.rpt.woplancmptrate.service.ReqRptWoPlanCmptDetailListService;

/**
 * 작업의뢰 시스템 요청 목록 - List Action
 * 
 * @author nhkim8548
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/reqRptWoPlanCmptDetailList" name="reqRptWoPlanCmptDetailListForm"
 *                input="/dream/req/rpt/woplancmptrate/reqRptWoPlanCmptDetailList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="reqRptWoPlanCmptDetailList" path="/dream/req/rpt/woplancmptrate/reqRptWoPlanCmptDetailList.jsp"
 *                        redirect="false"
 */
public class ReqRptWoPlanCmptDetailListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND       = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        ReqRptWoPlanCmptDetailListForm reqRptWoPlanCmptDetailListForm = (ReqRptWoPlanCmptDetailListForm) form;
        
        switch (reqRptWoPlanCmptDetailListForm.getStrutsAction())
        {
            case ReqRptWoPlanCmptDetailListAction.BASE_SET_HEADER:
                setHeader(request, response, reqRptWoPlanCmptDetailListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ReqRptWoPlanCmptDetailListAction.LIST_FIND:
                findList(request, response, reqRptWoPlanCmptDetailListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case ReqRptWoPlanCmptDetailListAction.BASE_GRID_EXPORT:
                findList(request, response, reqRptWoPlanCmptDetailListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("reqRptWoPlanCmptDetailList");
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, ReqRptWoPlanCmptDetailListForm reqRptWoPlanCmptDetailListForm) throws IOException
    {
        super.setHeader(request, response, reqRptWoPlanCmptDetailListForm.getListId(), reqRptWoPlanCmptDetailListForm.getCurrentPageId()); 
    }
   
    /**
     * FIND LIST
     * @author  js.lee
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param reqRptWoPlanCmptDetailListForm
     */
    
    private void findList(HttpServletRequest request, HttpServletResponse response, ReqRptWoPlanCmptDetailListForm reqRptWoPlanCmptDetailListForm, boolean excelExport) throws Exception
    {
        ReqRptWoPlanCmptDetailListService reqRptWoPlanCmptDetailListService = (ReqRptWoPlanCmptDetailListService) getBean("reqRptWoPlanCmptDetailListService");
        ReqRptWoPlanCmptDetailListDTO reqRptWoPlanCmptDetailListDTO = reqRptWoPlanCmptDetailListForm.getReqRptWoPlanCmptDetailListDTO();
      
        //Paging
        reqRptWoPlanCmptDetailListDTO.setIsLoadMaxCount("Y".equals(reqRptWoPlanCmptDetailListForm.getIsLoadMaxCount())?true:false);
        reqRptWoPlanCmptDetailListDTO.setFirstRow(reqRptWoPlanCmptDetailListForm.getFirstRow());
        reqRptWoPlanCmptDetailListDTO.setOrderBy(reqRptWoPlanCmptDetailListForm.getOrderBy());
        reqRptWoPlanCmptDetailListDTO.setDirection(reqRptWoPlanCmptDetailListForm.getDirection());
        
        User user = getUser(request);
        List resultList = reqRptWoPlanCmptDetailListService.findList(reqRptWoPlanCmptDetailListDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(reqRptWoPlanCmptDetailListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = reqRptWoPlanCmptDetailListService.findTotalCount(reqRptWoPlanCmptDetailListDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,reqRptWoPlanCmptDetailListForm.getListId(),reqRptWoPlanCmptDetailListForm.getCurrentPageId(), reqRptWoPlanCmptDetailListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
}