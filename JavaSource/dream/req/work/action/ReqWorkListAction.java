package dream.req.work.action;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.ResponseDTO;
import common.bean.User;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.req.work.dto.ReqWorkCommonDTO;
import dream.req.work.form.ReqWorkListForm;
import dream.req.work.service.ReqWorkListService;

/**
 * 작업요청서 - 목록 action
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/reqWorkList" name="reqWorkListForm"
 *                input="/dream/req/work/reqWorkList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="reqWorkList" path="/dream/req/work/reqWorkList.jsp"
 *                        redirect="false"
 */
public class ReqWorkListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND         = 1001;
    /** 삭제 */
    public static final int LIST_DELETE       = 7002;

    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        ReqWorkListForm reqWorkListForm = (ReqWorkListForm) form;

        super.updateAudit(reqWorkListForm.getReqWorkCommonDTO().getAuditKey()==""?reqWorkListForm.getReqWorkCommonDTO().getAuditKey():reqWorkListForm.getReqWorkCommonDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (reqWorkListForm.getStrutsAction())
        {
            case ReqWorkListAction.LIST_FIND:
            	findList(request, reqWorkListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ReqWorkListAction.LIST_DELETE:
            	deleteList(request, response, reqWorkListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ReqWorkListAction.BASE_SET_HEADER:
                setHeader(request, response, reqWorkListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ReqWorkListAction.BASE_GRID_EXPORT:
            	findList(request, reqWorkListForm, response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("reqWorkList");
                break;
        }

        return returnActionForward;
    }

    private void deleteList(HttpServletRequest request, HttpServletResponse response, ReqWorkListForm reqWorkListForm) throws Exception
    {
        ReqWorkListService reqWorkListService = (ReqWorkListService) getBean("reqWorkListService");

        String[] deleteRows = reqWorkListForm.getDeleteRows();    // sheet 내역

        ResponseDTO responseDTO = reqWorkListService.deleteList(deleteRows,getUser(request));

        CommonUtil.makeJsonResult(responseDTO, response);
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, ReqWorkListForm reqWorkListForm) throws IOException
    {
        super.setHeader(request, response, reqWorkListForm.getListId(), reqWorkListForm.getCurrentPageId());
    }

    /**
     * grid find
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     *
     * @param request
     * @param reqWorkListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, ReqWorkListForm reqWorkListForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	ReqWorkListService reqWorkListService = (ReqWorkListService) getBean("reqWorkListService");

    	ReqWorkCommonDTO reqWorkCommonDTO = reqWorkListForm.getReqWorkCommonDTO();
    	reqWorkCommonDTO.setCompNo(getUser(request).getCompNo());

    	//Paging
        reqWorkCommonDTO.setIsLoadMaxCount("Y".equals(reqWorkListForm.getIsLoadMaxCount())?true:false);
        reqWorkCommonDTO.setFirstRow(reqWorkListForm.getFirstRow());
        reqWorkCommonDTO.setOrderBy(reqWorkListForm.getOrderBy());
        reqWorkCommonDTO.setDirection(reqWorkListForm.getDirection());
        
        User user = getUser(request);
        //리스트를 조회한다.
        List resultList = reqWorkListService.findList(reqWorkCommonDTO,getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(reqWorkListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = reqWorkListService.findTotalCount(reqWorkCommonDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,reqWorkListForm.getListId(),reqWorkListForm.getCurrentPageId(), reqWorkListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }

}
