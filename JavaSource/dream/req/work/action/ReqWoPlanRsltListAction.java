package dream.req.work.action;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.req.work.dto.MaWoReqCommonDTO;
import dream.req.work.dto.ReqWoPlanRsltListDTO;
import dream.req.work.form.ReqWoPlanRsltListForm;
import dream.req.work.service.ReqWoPlanRsltListService;

/**
 * 작업요청서-작업계획 목록 action
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/reqWoPlanRsltList" name="reqWoPlanRsltListForm"
 *                input="/dream/req/work/reqWoPlanRsltList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="reqWoPlanRsltList" path="/dream/req/work/reqWoPlanRsltList.jsp"
 *                        redirect="false"
 */
public class ReqWoPlanRsltListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND         = 1001;
    /** 삭제 */
    public static final int LIST_DELETE       = 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        ReqWoPlanRsltListForm reqWoPlanRsltListForm = (ReqWoPlanRsltListForm) form;

        switch (reqWoPlanRsltListForm.getStrutsAction())
        {
            case ReqWoPlanRsltListAction.LIST_FIND:
            	findList(request, reqWoPlanRsltListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ReqWoPlanRsltListAction.LIST_DELETE:
            	deleteList(request, reqWoPlanRsltListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case ReqWoPlanRsltListAction.BASE_SET_HEADER:
                setHeader(request, response, reqWoPlanRsltListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ReqWoPlanRsltListAction.BASE_GRID_EXPORT:
            	findList(request, reqWoPlanRsltListForm, response, true);
            	returnActionForward = new ActionForward("/gridExport");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

	private void deleteList(HttpServletRequest request, ReqWoPlanRsltListForm reqWoPlanRsltListForm)
    {
        ReqWoPlanRsltListService reqWoPlanRsltListService = (ReqWoPlanRsltListService) getBean("reqWoPlanRsltListService");        

        String[] deleteRows = reqWoPlanRsltListForm.getDeleteRows();    // sheet 내역
        
        reqWoPlanRsltListService.deleteList(deleteRows,getUser(request).getCompNo());
        
        setAjaxStatus(request);
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, ReqWoPlanRsltListForm reqWoPlanRsltListForm) throws IOException
    {
        super.setHeader(request, response, reqWoPlanRsltListForm.getListId(), reqWoPlanRsltListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param reqWoPlanRsltListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, ReqWoPlanRsltListForm reqWoPlanRsltListForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	ReqWoPlanRsltListService reqWoPlanRsltListService = (ReqWoPlanRsltListService) getBean("reqWoPlanRsltListService");        

    	MaWoReqCommonDTO maWoReqCommonDTO = reqWoPlanRsltListForm.getMaWoReqCommonDTO();
    	maWoReqCommonDTO.setCompNo(getUser(request).getCompNo());
    	ReqWoPlanRsltListDTO reqWoPlanRsltListDTO = reqWoPlanRsltListForm.getReqWoPlanRsltListDTO();
    	
        //Paging
        reqWoPlanRsltListDTO.setIsLoadMaxCount("Y".equals(reqWoPlanRsltListForm.getIsLoadMaxCount())?true:false);
        reqWoPlanRsltListDTO.setFirstRow(reqWoPlanRsltListForm.getFirstRow());
        reqWoPlanRsltListDTO.setOrderBy(reqWoPlanRsltListForm.getOrderBy());
        reqWoPlanRsltListDTO.setDirection(reqWoPlanRsltListForm.getDirection());
        
        User user = getUser(request);
        //리스트를 조회한다.
        List resultList = reqWoPlanRsltListService.findList(maWoReqCommonDTO,reqWoPlanRsltListDTO,getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(reqWoPlanRsltListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = reqWoPlanRsltListService.findTotalCount(maWoReqCommonDTO,reqWoPlanRsltListDTO,user);
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, reqWoPlanRsltListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
}
