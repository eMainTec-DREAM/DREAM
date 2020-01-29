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
import dream.req.work.dto.MaWoReqDetailDTO;
import dream.req.work.dto.ReqWoRsltListDTO;
import dream.req.work.form.ReqWoRsltListForm;
import dream.req.work.service.MaWoReqDetailService;
import dream.req.work.service.ReqWoRsltListService;

/**
 * 작업요청서-작업결과 목록 action
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/reqWoRsltList" name="reqWoRsltListForm"
 *                input="/dream/req/work/reqWoRsltList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="reqWoRsltList" path="/dream/req/work/reqWoRsltList.jsp"
 *                        redirect="false"
 */
public class ReqWoRsltListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND         = 1001;
    /** 삭제 */
    public static final int LIST_DELETE       = 7002;
    /** 기존 투자 연결 */
    public static final int LIST_WO_LINK      = 5004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        ReqWoRsltListForm reqWoRsltListForm = (ReqWoRsltListForm) form;
        
        super.updateAudit(reqWoRsltListForm.getMaWoReqCommonDTO().getAuditKey()==""?reqWoRsltListForm.getMaWoReqCommonDTO().getAuditKey():reqWoRsltListForm.getMaWoReqCommonDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (reqWoRsltListForm.getStrutsAction())
        {
            case ReqWoRsltListAction.LIST_FIND:
                findList(request, reqWoRsltListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ReqWoRsltListAction.LIST_DELETE:
                deleteList(request, reqWoRsltListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case ReqWoRsltListAction.LIST_WO_LINK:
                woLinkList(request, reqWoRsltListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case ReqWoRsltListAction.BASE_SET_HEADER:
                setHeader(request, response, reqWoRsltListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ReqWoRsltListAction.BASE_GRID_EXPORT:
                findList(request, reqWoRsltListForm, response, true);
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
     * @param reqWoRsltListForm
     * @throws Exception
     */
    private void woLinkList(HttpServletRequest request, ReqWoRsltListForm reqWoRsltListForm) throws Exception {
        // Service 객체 생성
        ReqWoRsltListService reqWoRsltListService = (ReqWoRsltListService) getBean("reqWoRsltListService");
        MaWoReqDetailService maWoReqDetailService = (MaWoReqDetailService) getBean("maWoReqDetailService");
        MaWoReqCommonDTO maWoReqCommonDTO = reqWoRsltListForm.getMaWoReqCommonDTO();
        MaWoReqDetailDTO maWoReqDetailDTO = reqWoRsltListForm.getMaWoReqDetailDTO();
        
        reqWoRsltListService.linkWo(maWoReqCommonDTO, maWoReqDetailDTO, getUser(request));
        String status = maWoReqDetailService.checkStatus(maWoReqCommonDTO, getUser(request));
        
        setAjaxDesc(request, status);
    }

    private void deleteList(HttpServletRequest request, ReqWoRsltListForm reqWoRsltListForm) throws Exception
    {
        ReqWoRsltListService reqWoRsltListService = (ReqWoRsltListService) getBean("reqWoRsltListService");        
        MaWoReqDetailService maWoReqDetailService = (MaWoReqDetailService) getBean("maWoReqDetailService");
        
        MaWoReqCommonDTO maWoReqCommonDTO = reqWoRsltListForm.getMaWoReqCommonDTO();
       
        String[] deleteRows = reqWoRsltListForm.getDeleteRows();    // sheet 내역
        
        reqWoRsltListService.deleteList(deleteRows, getUser(request));
        String status = maWoReqDetailService.checkStatus(maWoReqCommonDTO, getUser(request));
        
        setAjaxDesc(request, status);
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, ReqWoRsltListForm reqWoRsltListForm) throws IOException
    {
        super.setHeader(request, response, reqWoRsltListForm.getListId(), reqWoRsltListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param reqWoRsltListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, ReqWoRsltListForm reqWoRsltListForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
        ReqWoRsltListService reqWoRsltListService = (ReqWoRsltListService) getBean("reqWoRsltListService");        

        MaWoReqCommonDTO maWoReqCommonDTO = reqWoRsltListForm.getMaWoReqCommonDTO();
        maWoReqCommonDTO.setCompNo(getUser(request).getCompNo());
        ReqWoRsltListDTO reqWoRsltListDTO = reqWoRsltListForm.getReqWoRsltListDTO();
        
        //Paging
        reqWoRsltListDTO.setIsLoadMaxCount("Y".equals(reqWoRsltListForm.getIsLoadMaxCount())?true:false);
        reqWoRsltListDTO.setFirstRow(reqWoRsltListForm.getFirstRow());
        reqWoRsltListDTO.setOrderBy(reqWoRsltListForm.getOrderBy());
        reqWoRsltListDTO.setDirection(reqWoRsltListForm.getDirection());
        
        User user = getUser(request);
        //리스트를 조회한다.
        List resultList = reqWoRsltListService.findList(maWoReqCommonDTO,reqWoRsltListDTO,getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(reqWoRsltListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = reqWoRsltListService.findTotalCount(maWoReqCommonDTO,reqWoRsltListDTO,user);
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, reqWoRsltListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
}
