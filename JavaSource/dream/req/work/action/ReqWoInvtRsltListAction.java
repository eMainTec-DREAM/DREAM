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
import dream.req.work.dto.MaWoReqCommonDTO;
import dream.req.work.dto.ReqWoInvtRsltListDTO;
import dream.req.work.form.ReqWoInvtRsltListForm;
import dream.req.work.service.MaWoReqDetailService;
import dream.req.work.service.ReqWoInvtRsltListService;

/**
 * 작업요청서-투자결과 목록 action
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/reqWoInvtRsltList" name="reqWoInvtRsltListForm"
 *                input="/dream/req/work/reqWoInvtRsltList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="reqWoInvtRsltList" path="/dream/req/work/reqWoInvtRsltList.jsp"
 *                        redirect="false"
 */
public class ReqWoInvtRsltListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND         = 1001;
    /** 삭제 */
    public static final int LIST_DELETE       = 7002;
    /** 기존 투자 연결 */
    public static final int LIST_INVT_LINK	  = 5004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        ReqWoInvtRsltListForm reqWoInvtRsltListForm = (ReqWoInvtRsltListForm) form;

        super.updateAudit(reqWoInvtRsltListForm.getMaWoReqCommonDTO().getAuditKey()==""?reqWoInvtRsltListForm.getMaWoReqCommonDTO().getAuditKey():reqWoInvtRsltListForm.getMaWoReqCommonDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (reqWoInvtRsltListForm.getStrutsAction())
        {
            case ReqWoInvtRsltListAction.LIST_FIND:
            	findList(request, reqWoInvtRsltListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ReqWoInvtRsltListAction.LIST_DELETE:
            	deleteList(request, reqWoInvtRsltListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case ReqWoInvtRsltListAction.LIST_INVT_LINK:
            	invtLinkList(request, reqWoInvtRsltListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case ReqWoInvtRsltListAction.BASE_SET_HEADER:
                setHeader(request, response, reqWoInvtRsltListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ReqWoInvtRsltListAction.BASE_GRID_EXPORT:
            	findList(request, reqWoInvtRsltListForm, response, true);
            	returnActionForward = new ActionForward("/gridExport");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

    /**
     * 기존 투자 연결
     * @author  js.lee
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param reqWoInvtRsltListForm
     * @throws Exception
     */
    private void invtLinkList(HttpServletRequest request, ReqWoInvtRsltListForm reqWoInvtRsltListForm) throws Exception {
    	// Service 객체 생성
        ReqWoInvtRsltListService reqWoInvtRsltListService = (ReqWoInvtRsltListService) getBean("reqWoInvtRsltListService", request);        
        MaWoReqDetailService maWoReqDetailService = (MaWoReqDetailService) getBean("maWoReqDetailService");
        MaWoReqCommonDTO maWoReqCommonDTO = reqWoInvtRsltListForm.getMaWoReqCommonDTO();
        
        reqWoInvtRsltListService.linkInvt(maWoReqCommonDTO, reqWoInvtRsltListForm.getReqWoInvtRsltListDTO(), getUser(request));
        String status = maWoReqDetailService.checkStatus(maWoReqCommonDTO, getUser(request));
        
        setAjaxDesc(request, status);
	}

	private void deleteList(HttpServletRequest request, ReqWoInvtRsltListForm reqWoInvtRsltListForm) throws Exception
    {
        ReqWoInvtRsltListService reqWoInvtRsltListService = (ReqWoInvtRsltListService) getBean("reqWoInvtRsltListService", request);       
        MaWoReqDetailService maWoReqDetailService = (MaWoReqDetailService) getBean("maWoReqDetailService");
        
        MaWoReqCommonDTO maWoReqCommonDTO = reqWoInvtRsltListForm.getMaWoReqCommonDTO();
        
        String[] deleteRows = reqWoInvtRsltListForm.getDeleteRows();    // sheet 내역
        
        reqWoInvtRsltListService.deleteList(deleteRows, getUser(request));
        String status = maWoReqDetailService.checkStatus(maWoReqCommonDTO, getUser(request));
        
        setAjaxDesc(request, status);
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, ReqWoInvtRsltListForm reqWoInvtRsltListForm) throws IOException
    {
        super.setHeader(request, response, reqWoInvtRsltListForm.getListId(), reqWoInvtRsltListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param reqWoInvtRsltListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, ReqWoInvtRsltListForm reqWoInvtRsltListForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	ReqWoInvtRsltListService reqWoInvtRsltListService = (ReqWoInvtRsltListService) getBean("reqWoInvtRsltListService", request);       

    	MaWoReqCommonDTO maWoReqCommonDTO = reqWoInvtRsltListForm.getMaWoReqCommonDTO();
    	maWoReqCommonDTO.setCompNo(getUser(request).getCompNo());
    	ReqWoInvtRsltListDTO reqWoInvtRsltListDTO = reqWoInvtRsltListForm.getReqWoInvtRsltListDTO();
    	
        //Paging
        reqWoInvtRsltListDTO.setIsLoadMaxCount("Y".equals(reqWoInvtRsltListForm.getIsLoadMaxCount())?true:false);
        reqWoInvtRsltListDTO.setFirstRow(reqWoInvtRsltListForm.getFirstRow());
        reqWoInvtRsltListDTO.setOrderBy(reqWoInvtRsltListForm.getOrderBy());
        reqWoInvtRsltListDTO.setDirection(reqWoInvtRsltListForm.getDirection());
        
        User user = getUser(request);
        //리스트를 조회한다.
        List resultList = reqWoInvtRsltListService.findList(maWoReqCommonDTO,reqWoInvtRsltListDTO,getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(reqWoInvtRsltListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = reqWoInvtRsltListService.findTotalCount(maWoReqCommonDTO,reqWoInvtRsltListDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,reqWoInvtRsltListForm.getListId(),reqWoInvtRsltListForm.getCurrentPageId(), reqWoInvtRsltListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
}
