package dream.req.rpt.woreqrate.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.req.rpt.woreqrate.dto.ReqRptWoReqRateCommonDTO;
import dream.req.rpt.woreqrate.form.ReqRptWoReqRateListForm;
import dream.req.rpt.woreqrate.service.ReqRptWoReqRateListService;

/**
 * 요청접수율(처리자)
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/reqRptWoReqRateList" name="reqRptWoReqRateListForm"
 *                input="/dream/req/rpt/woreqrate/reqRptWoReqRateList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="reqRptWoReqRateList" path="/dream/req/rpt/woreqrate/reqRptWoReqRateList.jsp"
 *                        redirect="false"
 */
public class ReqRptWoReqRateListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int LIST_FIND 			= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        ReqRptWoReqRateListForm reqRptWoReqRateListForm = (ReqRptWoReqRateListForm) form;
        
        switch (reqRptWoReqRateListForm.getStrutsAction())
        {
        
            case ReqRptWoReqRateListAction.LIST_FIND:
                findList(request,response, reqRptWoReqRateListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ReqRptWoReqRateListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, reqRptWoReqRateListForm.getListId(), reqRptWoReqRateListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ReqRptWoReqRateListAction.BASE_GRID_EXPORT:
            	findList(request,response, reqRptWoReqRateListForm, true);
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
     * @param reqRptWoReqRateListForm
     * @param excelExport
     * @throws Exception
     */
    private void findList(HttpServletRequest request,HttpServletResponse response, ReqRptWoReqRateListForm reqRptWoReqRateListForm, boolean excelExport) throws Exception
    {
        ReqRptWoReqRateListService reqRptWoReqRateListService = (ReqRptWoReqRateListService) getBean("reqRptWoReqRateListService");
        
        ReqRptWoReqRateCommonDTO reqRptWoReqRateCommonDTO = reqRptWoReqRateListForm.getReqRptWoReqRateCommonDTO();
        
        //Paging
        reqRptWoReqRateCommonDTO.setIsLoadMaxCount("Y".equals(reqRptWoReqRateListForm.getIsLoadMaxCount())?true:false);
        reqRptWoReqRateCommonDTO.setFirstRow(reqRptWoReqRateListForm.getFirstRow());
        reqRptWoReqRateCommonDTO.setOrderBy(reqRptWoReqRateListForm.getOrderBy());
        reqRptWoReqRateCommonDTO.setDirection(reqRptWoReqRateListForm.getDirection());
        
        List resultList = reqRptWoReqRateListService.findList(reqRptWoReqRateCommonDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(reqRptWoReqRateListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = reqRptWoReqRateListService.findTotalCount(reqRptWoReqRateCommonDTO,getUser(request));
        
        if(excelExport) super.makeGridExport(resultList, request, response,reqRptWoReqRateListForm.getListId(),reqRptWoReqRateListForm.getCurrentPageId(), reqRptWoReqRateListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
}