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
import dream.req.rpt.prewoplanrate.dto.ReqRptPreWoPlanRateCommonDTO;
import dream.req.rpt.prewoplanrate.form.ReqRptPreWoPlanRateListForm;
import dream.req.rpt.prewoplanrate.service.ReqRptPreWoPlanRateListService;

/**
 * 작업오더 사전 계획 수립률 목록 - List Action
 * 
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/reqRptPreWoPlanRateList" name="reqRptPreWoPlanRateListForm"
 *                input="/dream/req/rpt/prewoplanrate/reqRptPreWoPlanRateList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="reqRptPreWoPlanRateList" path="/dream/req/rpt/prewoplanrate/reqRptPreWoPlanRateList.jsp"
 *                        redirect="false"
 */
public class ReqRptPreWoPlanRateListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND       = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        ReqRptPreWoPlanRateListForm reqRptPreWoPlanRateListForm = (ReqRptPreWoPlanRateListForm) form;
        
        switch (reqRptPreWoPlanRateListForm.getStrutsAction())
        {
            case ReqRptPreWoPlanRateListAction.BASE_SET_HEADER:
                setHeader(request, response, reqRptPreWoPlanRateListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ReqRptPreWoPlanRateListAction.LIST_FIND:
                findList(request, response, reqRptPreWoPlanRateListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case ReqRptPreWoPlanRateListAction.BASE_GRID_EXPORT:
                findList(request, response, reqRptPreWoPlanRateListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("reqRptPreWoPlanRateList");
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, ReqRptPreWoPlanRateListForm reqRptPreWoPlanRateListForm) throws IOException
    {
        super.setHeader(request, response, reqRptPreWoPlanRateListForm.getListId(), reqRptPreWoPlanRateListForm.getCurrentPageId()); 
    }
   
    /**
     * FIND LIST
     * @author  js.lee
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param reqRptPreWoPlanRateListForm
     */
    
    private void findList(HttpServletRequest request, HttpServletResponse response, ReqRptPreWoPlanRateListForm reqRptPreWoPlanRateListForm, boolean excelExport) throws Exception
    {
        ReqRptPreWoPlanRateListService reqRptPreWoPlanRateListService = (ReqRptPreWoPlanRateListService) getBean("reqRptPreWoPlanRateListService");
        ReqRptPreWoPlanRateCommonDTO reqRptPreWoPlanRateCommonDTO = reqRptPreWoPlanRateListForm.getReqRptPreWoPlanRateCommonDTO();
      
        //Paging
        reqRptPreWoPlanRateCommonDTO.setIsLoadMaxCount("Y".equals(reqRptPreWoPlanRateListForm.getIsLoadMaxCount())?true:false);
        reqRptPreWoPlanRateCommonDTO.setFirstRow(reqRptPreWoPlanRateListForm.getFirstRow());
        reqRptPreWoPlanRateCommonDTO.setOrderBy(reqRptPreWoPlanRateListForm.getOrderBy());
        reqRptPreWoPlanRateCommonDTO.setDirection(reqRptPreWoPlanRateListForm.getDirection());
        
        User user = getUser(request);
        List resultList = reqRptPreWoPlanRateListService.findList(reqRptPreWoPlanRateCommonDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(reqRptPreWoPlanRateListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = reqRptPreWoPlanRateListService.findTotalCount(reqRptPreWoPlanRateCommonDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,reqRptPreWoPlanRateListForm.getListId(),reqRptPreWoPlanRateListForm.getCurrentPageId(), reqRptPreWoPlanRateListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
}