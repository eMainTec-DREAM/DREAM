package dream.req.rpt.prewoplanrate.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.req.rpt.prewoplanrate.dto.ReqRptPreWoPlanDetailListDTO;
import dream.req.rpt.prewoplanrate.form.ReqRptPreWoPlanDetailListForm;
import dream.req.rpt.prewoplanrate.service.ReqRptPreWoPlanDetailListService;

/**
 * 작업오더 사전 계획 수립률 목록 - List Action
 * 
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/reqRptPreWoPlanDetailList" name="reqRptPreWoPlanDetailListForm"
 *                input="/dream/req/rpt/prewoplanrate/reqRptPreWoPlanDetailList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="reqRptPreWoPlanDetailList" path="/dream/req/rpt/prewoplanrate/reqRptPreWoPlanDetailList.jsp"
 *                        redirect="false"
 */
public class ReqRptPreWoPlanDetailListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND       = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        ReqRptPreWoPlanDetailListForm reqRptPreWoPlanDetailListForm = (ReqRptPreWoPlanDetailListForm) form;
        
        switch (reqRptPreWoPlanDetailListForm.getStrutsAction())
        {
            case ReqRptPreWoPlanDetailListAction.BASE_SET_HEADER:
                setHeader(request, response, reqRptPreWoPlanDetailListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ReqRptPreWoPlanDetailListAction.LIST_FIND:
                findList(request, response, reqRptPreWoPlanDetailListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case ReqRptPreWoPlanDetailListAction.BASE_GRID_EXPORT:
                findList(request, response, reqRptPreWoPlanDetailListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("reqRptPreWoPlanDetailList");
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, ReqRptPreWoPlanDetailListForm reqRptPreWoPlanDetailListForm) throws IOException
    {
        super.setHeader(request, response, reqRptPreWoPlanDetailListForm.getListId(), reqRptPreWoPlanDetailListForm.getCurrentPageId()); 
    }
   
    /**
     * FIND LIST
     * @author  js.lee
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param reqRptPreWoPlanDetailListForm
     */
    
    private void findList(HttpServletRequest request, HttpServletResponse response, ReqRptPreWoPlanDetailListForm reqRptPreWoPlanDetailListForm, boolean excelExport) throws Exception
    {
        ReqRptPreWoPlanDetailListService reqRptPreWoPlanDetailListService = (ReqRptPreWoPlanDetailListService) getBean("reqRptPreWoPlanDetailListService");
        ReqRptPreWoPlanDetailListDTO reqRptPreWoPlanDetailListDTO = reqRptPreWoPlanDetailListForm.getReqRptPreWoPlanDetailListDTO();
      
        //Paging
        reqRptPreWoPlanDetailListDTO.setIsLoadMaxCount("Y".equals(reqRptPreWoPlanDetailListForm.getIsLoadMaxCount())?true:false);
        reqRptPreWoPlanDetailListDTO.setFirstRow(reqRptPreWoPlanDetailListForm.getFirstRow());
        reqRptPreWoPlanDetailListDTO.setOrderBy(reqRptPreWoPlanDetailListForm.getOrderBy());
        reqRptPreWoPlanDetailListDTO.setDirection(reqRptPreWoPlanDetailListForm.getDirection());
        
        User user = getUser(request);
        List resultList = reqRptPreWoPlanDetailListService.findList(reqRptPreWoPlanDetailListDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(reqRptPreWoPlanDetailListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = reqRptPreWoPlanDetailListService.findTotalCount(reqRptPreWoPlanDetailListDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,reqRptPreWoPlanDetailListForm.getListId(),reqRptPreWoPlanDetailListForm.getCurrentPageId(), reqRptPreWoPlanDetailListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
}