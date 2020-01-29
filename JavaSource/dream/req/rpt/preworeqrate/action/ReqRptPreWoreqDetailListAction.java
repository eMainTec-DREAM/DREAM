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
import dream.req.rpt.preworeqrate.dto.ReqRptPreWoreqDetailListDTO;
import dream.req.rpt.preworeqrate.form.ReqRptPreWoreqDetailListForm;
import dream.req.rpt.preworeqrate.service.ReqRptPreWoreqDetailListService;

/**
 * 작업의뢰 시스템 요청 목록 - List Action
 * 
 * @author nhkim8548
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/reqRptPreWoreqDetailList" name="reqRptPreWoreqDetailListForm"
 *                input="/dream/req/rpt/preworeqrate/reqRptPreWoreqDetailList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="reqRptPreWoreqDetailList" path="/dream/req/rpt/preworeqrate/reqRptPreWoreqDetailList.jsp"
 *                        redirect="false"
 */
public class ReqRptPreWoreqDetailListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND       = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        ReqRptPreWoreqDetailListForm reqRptPreWoreqDetailListForm = (ReqRptPreWoreqDetailListForm) form;
        
        switch (reqRptPreWoreqDetailListForm.getStrutsAction())
        {
            case ReqRptPreWoreqDetailListAction.BASE_SET_HEADER:
                setHeader(request, response, reqRptPreWoreqDetailListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ReqRptPreWoreqDetailListAction.LIST_FIND:
                findList(request, response, reqRptPreWoreqDetailListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case ReqRptPreWoreqDetailListAction.BASE_GRID_EXPORT:
                findList(request, response, reqRptPreWoreqDetailListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("reqRptPreWoreqDetailList");
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, ReqRptPreWoreqDetailListForm reqRptPreWoreqDetailListForm) throws IOException
    {
        super.setHeader(request, response, reqRptPreWoreqDetailListForm.getListId(), reqRptPreWoreqDetailListForm.getCurrentPageId()); 
    }
   
    /**
     * FIND LIST
     * @author  js.lee
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param reqRptPreWoreqDetailListForm
     */
    
    private void findList(HttpServletRequest request, HttpServletResponse response, ReqRptPreWoreqDetailListForm reqRptPreWoreqDetailListForm, boolean excelExport) throws Exception
    {
        ReqRptPreWoreqDetailListService reqRptPreWoreqDetailListService = (ReqRptPreWoreqDetailListService) getBean("reqRptPreWoreqDetailListService");
        ReqRptPreWoreqDetailListDTO reqRptPreWoreqDetailListDTO = reqRptPreWoreqDetailListForm.getReqRptPreWoreqDetailListDTO();
      
        //Paging
        reqRptPreWoreqDetailListDTO.setIsLoadMaxCount("Y".equals(reqRptPreWoreqDetailListForm.getIsLoadMaxCount())?true:false);
        reqRptPreWoreqDetailListDTO.setFirstRow(reqRptPreWoreqDetailListForm.getFirstRow());
        reqRptPreWoreqDetailListDTO.setOrderBy(reqRptPreWoreqDetailListForm.getOrderBy());
        reqRptPreWoreqDetailListDTO.setDirection(reqRptPreWoreqDetailListForm.getDirection());
        
        User user = getUser(request);
        List resultList = reqRptPreWoreqDetailListService.findList(reqRptPreWoreqDetailListDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(reqRptPreWoreqDetailListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = reqRptPreWoreqDetailListService.findTotalCount(reqRptPreWoreqDetailListDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,reqRptPreWoreqDetailListForm.getListId(),reqRptPreWoreqDetailListForm.getCurrentPageId(), reqRptPreWoreqDetailListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
}