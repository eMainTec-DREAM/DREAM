package dream.req.rpt.preworeqrate.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.req.rpt.preworeqrate.dto.ReqRptPreWoreqRateCommonDTO;
import dream.req.rpt.preworeqrate.form.ReqRptPreWoreqRateListForm;
import dream.req.rpt.preworeqrate.service.ReqRptPreWoreqRateListService;

/**
 * 작업의뢰 사전 시스템 요청율 목록 - List Action
 * 
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/reqRptPreWoreqRateList" name="reqRptPreWoreqRateListForm"
 *                input="/dream/req/rpt/preworeqrate/reqRptPreWoreqRateList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="reqRptPreWoreqRateList" path="/dream/req/rpt/preworeqrate/reqRptPreWoreqRateList.jsp"
 *                        redirect="false"
 */
public class ReqRptPreWoreqRateListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND       = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        ReqRptPreWoreqRateListForm reqRptPreWoreqRateListForm = (ReqRptPreWoreqRateListForm) form;
        
        switch (reqRptPreWoreqRateListForm.getStrutsAction())
        {
            case ReqRptPreWoreqRateListAction.BASE_SET_HEADER:
                setHeader(request, response, reqRptPreWoreqRateListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ReqRptPreWoreqRateListAction.LIST_FIND:
                findList(request, response, reqRptPreWoreqRateListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case ReqRptPreWoreqRateListAction.BASE_GRID_EXPORT:
                findList(request, response, reqRptPreWoreqRateListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("reqRptPreWoreqRateList");
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, ReqRptPreWoreqRateListForm reqRptPreWoreqRateListForm) throws IOException
    {
        super.setHeader(request, response, reqRptPreWoreqRateListForm.getListId(), reqRptPreWoreqRateListForm.getCurrentPageId()); 
    }
   
    /**
     * FIND LIST
     * @author  js.lee
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param reqRptPreWoreqRateListForm
     */
    
    private void findList(HttpServletRequest request, HttpServletResponse response, ReqRptPreWoreqRateListForm reqRptPreWoreqRateListForm, boolean excelExport) throws Exception
    {
        ReqRptPreWoreqRateListService reqRptPreWoreqRateListService = (ReqRptPreWoreqRateListService) getBean("reqRptPreWoreqRateListService");
        ReqRptPreWoreqRateCommonDTO reqRptPreWoreqRateCommonDTO = reqRptPreWoreqRateListForm.getReqRptPreWoreqRateCommonDTO();
      
        //Paging
        reqRptPreWoreqRateCommonDTO.setIsLoadMaxCount("Y".equals(reqRptPreWoreqRateListForm.getIsLoadMaxCount())?true:false);
        reqRptPreWoreqRateCommonDTO.setFirstRow(reqRptPreWoreqRateListForm.getFirstRow());
        reqRptPreWoreqRateCommonDTO.setOrderBy(reqRptPreWoreqRateListForm.getOrderBy());
        reqRptPreWoreqRateCommonDTO.setDirection(reqRptPreWoreqRateListForm.getDirection());
        
        User user = getUser(request);
        List resultList = reqRptPreWoreqRateListService.findList(reqRptPreWoreqRateCommonDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(reqRptPreWoreqRateListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = reqRptPreWoreqRateListService.findTotalCount(reqRptPreWoreqRateCommonDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,reqRptPreWoreqRateListForm.getListId(),reqRptPreWoreqRateListForm.getCurrentPageId(), reqRptPreWoreqRateListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
}