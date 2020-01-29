package dream.req.work.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.req.work.dto.ReqWorkCommonDTO;
import dream.req.work.dto.ReqWorkResListDTO;
import dream.req.work.form.ReqWorkResListForm;
import dream.req.work.service.ReqWorkResListService;

/**
 * 작업요청서-처리사항 목록 action
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/reqWorkResList" name="reqWorkResListForm"
 *                input="/dream/req/work/reqWorkResList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/reqInvWorkResList" name="reqWorkResListForm"
 *                input="/dream/req/work/reqInvWorkResList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="reqWorkResList" path="/dream/req/work/reqWorkResList.jsp"
 *                        redirect="false"
 */
public class ReqWorkResListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND         = 1001;
    /** 삭제 */
    public static final int LIST_DELETE       = 7002;
    /** 기존 W/O 연결 */
    public static final int LIST_WO_LINK      = 5003;

    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        ReqWorkResListForm reqWorkResListForm = (ReqWorkResListForm) form;

        switch (reqWorkResListForm.getStrutsAction())
        {
            case ReqWorkResListAction.LIST_FIND:
            	findList(request, reqWorkResListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ReqWorkResListAction.LIST_DELETE:
            	deleteList(request, reqWorkResListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case ReqWorkResListAction.LIST_WO_LINK:
            	linkList(request, reqWorkResListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case ReqWorkResListAction.BASE_SET_HEADER:
                setHeader(request, response, reqWorkResListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ReqWorkResListAction.BASE_GRID_EXPORT:
            	findList(request, reqWorkResListForm, response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

    private void linkList(HttpServletRequest request, ReqWorkResListForm reqWorkResListForm) throws Exception {
    	// Service 객체 생성
        ReqWorkResListService reqWorkResListService = (ReqWorkResListService) getBean("reqWorkResListService");

        reqWorkResListService.linkWo(reqWorkResListForm.getReqWorkCommonDTO(), getUser(request));

        setAjaxStatus(request);
	}

	private void deleteList(HttpServletRequest request, ReqWorkResListForm reqWorkResListForm)
    {
        ReqWorkResListService reqWorkResListService = (ReqWorkResListService) getBean("reqWorkResListService");

        String[] deleteRows = reqWorkResListForm.getDeleteRows();    // sheet 내역

        reqWorkResListService.deleteList(deleteRows,getUser(request).getCompNo());

        setAjaxStatus(request);
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, ReqWorkResListForm reqWorkResListForm) throws IOException
    {
        super.setHeader(request, response, reqWorkResListForm.getListId(), reqWorkResListForm.getCurrentPageId());
    }

    /**
     * grid find
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     *
     * @param request
     * @param reqWorkResListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, ReqWorkResListForm reqWorkResListForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	ReqWorkResListService reqWorkResListService = (ReqWorkResListService) getBean("reqWorkResListService");

    	ReqWorkCommonDTO reqWorkCommonDTO = reqWorkResListForm.getReqWorkCommonDTO();
    	reqWorkCommonDTO.setCompNo(getUser(request).getCompNo());
    	ReqWorkResListDTO reqWorkResListDTO = reqWorkResListForm.getReqWorkResListDTO();
    	
        //Paging
        reqWorkResListDTO.setIsLoadMaxCount("Y".equals(reqWorkResListForm.getIsLoadMaxCount())?true:false);
        reqWorkResListDTO.setFirstRow(reqWorkResListForm.getFirstRow());
        reqWorkResListDTO.setOrderBy(reqWorkResListForm.getOrderBy());
        reqWorkResListDTO.setDirection(reqWorkResListForm.getDirection());
        
        User user = getUser(request);
        //리스트를 조회한다.
        List resultList = reqWorkResListService.findList(reqWorkCommonDTO,reqWorkResListDTO,getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(reqWorkResListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = reqWorkResListService.findTotalCount(reqWorkCommonDTO,reqWorkResListDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,reqWorkResListForm.getListId(),reqWorkResListForm.getCurrentPageId(), reqWorkResListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }

}
