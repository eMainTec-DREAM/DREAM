package dream.req.rpt.emwogenrate.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.req.rpt.emwogenrate.dto.ReqRptEmWoGenRateCommonDTO;
import dream.req.rpt.emwogenrate.form.ReqRptEmWoGenDetailForm;
import dream.req.rpt.emwogenrate.service.ReqRptEmWoGenDetailService;

/**
 * 작업의뢰 작업발행율 상세
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/reqRptEmWoGenDetailList" name="reqRptEmWoGenDetailForm"
 *                input="/dream/req/rpt/emwogenrate/reqRptEmWoGenDetailList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="reqRptEmWoGenDetailList" path="/dream/req/rpt/emwogenrate/reqRptEmWoGenDetailList.jsp"
 *                        redirect="false"
 */
public class ReqRptEmWoGenDetailAction extends AuthAction
{
    public static final int DETAIL_FIND 	= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        ReqRptEmWoGenDetailForm reqRptEmWoGenDetailForm = (ReqRptEmWoGenDetailForm) form;
        switch (reqRptEmWoGenDetailForm.getStrutsAction())
        {
            case ReqRptEmWoGenDetailAction.DETAIL_FIND:
                // 페이지 조회
                this.findDetail(request, response, reqRptEmWoGenDetailForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ReqRptEmWoGenDetailAction.BASE_SET_HEADER:
                super.setHeader(request, response, reqRptEmWoGenDetailForm.getListId(), reqRptEmWoGenDetailForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ReqRptEmWoGenDetailAction.BASE_GRID_EXPORT:
                findDetail(request,response, reqRptEmWoGenDetailForm, true);
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
     * @param reqRptEmWoGenDetailForm
     */
    private void findDetail(HttpServletRequest request,HttpServletResponse response, ReqRptEmWoGenDetailForm reqRptEmWoGenDetailForm, boolean excelExport) throws Exception
    {
        ReqRptEmWoGenDetailService reqRptEmWoGenDetailService = (ReqRptEmWoGenDetailService) getBean("reqRptEmWoGenDetailService");
        
        ReqRptEmWoGenRateCommonDTO reqRptEmWoGenRateCommonDTO = reqRptEmWoGenDetailForm.getReqRptEmWoGenRateCommonDTO();
        
        reqRptEmWoGenRateCommonDTO.setCompNo(getUser(request).getCompNo());
    	
        reqRptEmWoGenRateCommonDTO.setIsLoadMaxCount("Y".equals(reqRptEmWoGenDetailForm.getIsLoadMaxCount())?true:false);
        reqRptEmWoGenRateCommonDTO.setFirstRow(reqRptEmWoGenDetailForm.getFirstRow());
        reqRptEmWoGenRateCommonDTO.setOrderBy(reqRptEmWoGenDetailForm.getOrderBy());
        reqRptEmWoGenRateCommonDTO.setDirection(reqRptEmWoGenDetailForm.getDirection());
    	
        List resultList = reqRptEmWoGenDetailService.findDetail(reqRptEmWoGenRateCommonDTO, getUser(request));
        
        String totalCount = "";
        if(Integer.parseInt(reqRptEmWoGenDetailForm.getIsTotalCount()) == 0 && !excelExport) totalCount = reqRptEmWoGenDetailService.findTotalCount(reqRptEmWoGenRateCommonDTO,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,reqRptEmWoGenDetailForm.getListId(),reqRptEmWoGenDetailForm.getCurrentPageId(), reqRptEmWoGenDetailForm.getFileName());
        else super.makeJsonResult(resultList, request, response, reqRptEmWoGenDetailForm.getListId());
    }
    
}