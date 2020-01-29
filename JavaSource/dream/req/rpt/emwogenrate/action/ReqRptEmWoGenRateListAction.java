package dream.req.rpt.emwogenrate.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.req.rpt.emwogenrate.dto.ReqRptEmWoGenRateCommonDTO;
import dream.req.rpt.emwogenrate.form.ReqRptEmWoGenRateListForm;
import dream.req.rpt.emwogenrate.service.ReqRptEmWoGenRateListService;

/**
 * 사후 작업오더 발생률 목록 - List Action
 * 
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/reqRptEmWoGenRateList" name="reqRptEmWoGenRateListForm"
 *                input="/dream/req/rpt/emwogenrate/reqRptEmWoGenRateList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="reqRptEmWoGenRateList" path="/dream/req/rpt/emwogenrate/reqRptEmWoGenRateList.jsp"
 *                        redirect="false"
 */
public class ReqRptEmWoGenRateListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND       = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        ReqRptEmWoGenRateListForm reqRptEmWoGenRateListForm = (ReqRptEmWoGenRateListForm) form;
        
        switch (reqRptEmWoGenRateListForm.getStrutsAction())
        {
            case ReqRptEmWoGenRateListAction.BASE_SET_HEADER:
                setHeader(request, response, reqRptEmWoGenRateListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ReqRptEmWoGenRateListAction.LIST_FIND:
                findList(request, response, reqRptEmWoGenRateListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case ReqRptEmWoGenRateListAction.BASE_GRID_EXPORT:
                findList(request, response, reqRptEmWoGenRateListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("reqRptEmWoGenRateList");
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, ReqRptEmWoGenRateListForm reqRptEmWoGenRateListForm) throws IOException
    {
        super.setHeader(request, response, reqRptEmWoGenRateListForm.getListId(), reqRptEmWoGenRateListForm.getCurrentPageId()); 
    }
   
    /**
     * FIND LIST
     * @author  js.lee
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param reqRptEmWoGenRateListForm
     */
    
    private void findList(HttpServletRequest request, HttpServletResponse response, ReqRptEmWoGenRateListForm reqRptEmWoGenRateListForm, boolean excelExport) throws Exception
    {
        ReqRptEmWoGenRateListService reqRptEmWoGenRateListService = (ReqRptEmWoGenRateListService) getBean("reqRptEmWoGenRateListService");
        ReqRptEmWoGenRateCommonDTO reqRptEmWoGenRateCommonDTO = reqRptEmWoGenRateListForm.getReqRptEmWoGenRateCommonDTO();
      
        //Paging
        reqRptEmWoGenRateCommonDTO.setIsLoadMaxCount("Y".equals(reqRptEmWoGenRateListForm.getIsLoadMaxCount())?true:false);
        reqRptEmWoGenRateCommonDTO.setFirstRow(reqRptEmWoGenRateListForm.getFirstRow());
        reqRptEmWoGenRateCommonDTO.setOrderBy(reqRptEmWoGenRateListForm.getOrderBy());
        reqRptEmWoGenRateCommonDTO.setDirection(reqRptEmWoGenRateListForm.getDirection());
        
        User user = getUser(request);
        List resultList = reqRptEmWoGenRateListService.findList(reqRptEmWoGenRateCommonDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(reqRptEmWoGenRateListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = reqRptEmWoGenRateListService.findTotalCount(reqRptEmWoGenRateCommonDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,reqRptEmWoGenRateListForm.getListId(),reqRptEmWoGenRateListForm.getCurrentPageId(), reqRptEmWoGenRateListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
}