package dream.req.rpt.woreqrate.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.req.rpt.woreqrate.dto.ReqRptWoReqRateDetailDTO;
import dream.req.rpt.woreqrate.form.ReqRptWoReqRateDetailForm;
import dream.req.rpt.woreqrate.service.ReqRptWoReqRateDetailService;

/**
 * 요청접수율(처리자) 상세
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/reqRptWoReqRateDetailList" name="reqRptWoReqRateDetailForm"
 *                input="/dream/req/rpt/woreqrate/reqRptWoReqRateDetailList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/reqRptWoReqRateDetailChart" name="reqRptWoReqRateDetailForm"
 *                input="/dream/req/rpt/woreqrate/reqRptWoReqRateDetailChart.jsp" scope="request"
 *                validate="false"
 */
public class ReqRptWoReqRateDetailAction extends AuthAction
{
    public static final int DETAIL_FIND 	= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        ReqRptWoReqRateDetailForm reqRptWoReqRateDetailForm = (ReqRptWoReqRateDetailForm) form;
        switch (reqRptWoReqRateDetailForm.getStrutsAction())
        {
            case ReqRptWoReqRateDetailAction.DETAIL_FIND:
                // 페이지 조회
                this.findDetail(request, response, reqRptWoReqRateDetailForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ReqRptWoReqRateDetailAction.BASE_SET_HEADER:
                super.setHeader(request, response, reqRptWoReqRateDetailForm.getListId(), reqRptWoReqRateDetailForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ReqRptWoReqRateDetailAction.BASE_GRID_EXPORT:
                findDetail(request,response, reqRptWoReqRateDetailForm);
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
     * @param reqRptWoReqRateDetailForm
     */
    private void findDetail(HttpServletRequest request,HttpServletResponse response, ReqRptWoReqRateDetailForm reqRptWoReqRateDetailForm) throws Exception
    {
        ReqRptWoReqRateDetailService reqRptWoReqRateDetailService = (ReqRptWoReqRateDetailService) getBean("reqRptWoReqRateDetailService");
        ReqRptWoReqRateDetailDTO reqRptWoReqRateDetailDTO = reqRptWoReqRateDetailForm.getReqRptWoReqRateDetailDTO();
        
        //Paging
        reqRptWoReqRateDetailDTO.setIsLoadMaxCount("Y".equals(reqRptWoReqRateDetailForm.getIsLoadMaxCount())?true:false);
        reqRptWoReqRateDetailDTO.setFirstRow(reqRptWoReqRateDetailForm.getFirstRow());
        reqRptWoReqRateDetailDTO.setOrderBy(reqRptWoReqRateDetailForm.getOrderBy());
        reqRptWoReqRateDetailDTO.setDirection(reqRptWoReqRateDetailForm.getDirection());
        
        List resultList = reqRptWoReqRateDetailService.findDetail(reqRptWoReqRateDetailDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        super.makeJsonResult(resultList, request, response, totalCount);
    }
}