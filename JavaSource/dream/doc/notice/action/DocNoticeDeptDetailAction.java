package dream.doc.notice.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.doc.notice.dto.DocNoticeCommonDTO;
import dream.doc.notice.dto.DocNoticeDeptDetailDTO;
import dream.doc.notice.dto.DocNoticeDeptListDTO;
import dream.doc.notice.form.DocNoticeDeptDetailForm;
import dream.doc.notice.service.DocNoticeDeptDetailService;

/**
 * 공지부서 - Detail Action
 * 
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @struts:action path="/docNoticeDeptDetail" name="docNoticeDeptDetailForm"
 *                input="/dream/doc/notice/docNoticeDeptDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="docNoticeDeptDetail" path="/dream/doc/notice/docNoticeDeptDetail.jsp"
 *                        redirect="false"
 */
public class DocNoticeDeptDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int DETAIL_INIT 		= 8001;
    /** 저장 */
    public static final int DETAIL_INPUT 		= 5002;
    /** 수정 */
    public static final int DETAIL_UPDATE 		= 6003;
    /** 부서 중복 체크 */
    public static final int DETAIL_CHECK        = 1004;

    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        DocNoticeDeptDetailForm docNoticeDeptDetailForm = (DocNoticeDeptDetailForm) form;
        
        super.updateAudit(docNoticeDeptDetailForm.getDocNoticeDeptDetailDTO().getAuditKey()==""?docNoticeDeptDetailForm.getDocNoticeDeptListDTO().getAuditKey():docNoticeDeptDetailForm.getDocNoticeDeptDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (docNoticeDeptDetailForm.getStrutsAction())
        {
            case DocNoticeDeptDetailAction.DETAIL_INIT:
                this.findDetail(request, response, docNoticeDeptDetailForm);
                returnActionForward = mapping.findForward("docNoticeDeptDetail");
                break;
            case DocNoticeDeptDetailAction.DETAIL_INPUT:
            	insertDetail(request, response, docNoticeDeptDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case DocNoticeDeptDetailAction.DETAIL_UPDATE:
            	updateDetail(request, response, docNoticeDeptDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case DocNoticeDeptDetailAction.DETAIL_CHECK:
            	checkDetail(request, response, docNoticeDeptDetailForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("docNoticeDeptDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * FIND DETAIL
     * @param request
     * @param response
     * @param docNoticeDeptDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, DocNoticeDeptDetailForm docNoticeDeptDetailForm) throws Exception 
    {   
    	DocNoticeDeptDetailService docNoticeDeptDetailService = (DocNoticeDeptDetailService)getBean("docNoticeDeptDetailService");
    	
    	DocNoticeCommonDTO docNoticeCommonDTO = docNoticeDeptDetailForm.getDocNoticeCommonDTO(); 
    	DocNoticeDeptListDTO docNoticeDeptListDTO = docNoticeDeptDetailForm.getDocNoticeDeptListDTO();
    	DocNoticeDeptDetailDTO docNoticeDeptDetailDTO = docNoticeDeptDetailService.findDetail(docNoticeCommonDTO,docNoticeDeptListDTO, getUser(request));
    	docNoticeDeptDetailForm.setDocNoticeDeptDetailDTO(docNoticeDeptDetailDTO);
    }

    /**
     * INSERT DETAIL
     * @param request
     * @param response
     * @param docNoticeDeptDetailForm
     * @throws Exception
     */
    private void insertDetail(HttpServletRequest request, HttpServletResponse response, DocNoticeDeptDetailForm docNoticeDeptDetailForm) throws Exception
    {
    	DocNoticeDeptDetailService docNoticeDeptDetailService = (DocNoticeDeptDetailService)getBean("docNoticeDeptDetailService");
    	DocNoticeCommonDTO docNoticeCommonDTO = docNoticeDeptDetailForm.getDocNoticeCommonDTO(); 
    	DocNoticeDeptDetailDTO  docNoticeDeptDetailDTO = docNoticeDeptDetailForm.getDocNoticeDeptDetailDTO(); 
    	docNoticeDeptDetailService.insertDetail(docNoticeCommonDTO,docNoticeDeptDetailDTO, getUser(request));
        setAjaxStatus(request);
    }

    /**
     * UPDATE DETAIL
     * @param request
     * @param response
     * @param docNoticeDeptDetailForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, DocNoticeDeptDetailForm docNoticeDeptDetailForm) throws Exception
    {
    	DocNoticeDeptDetailService docNoticeDeptDetailService = (DocNoticeDeptDetailService)getBean("docNoticeDeptDetailService");
    	DocNoticeCommonDTO docNoticeCommonDTO = docNoticeDeptDetailForm.getDocNoticeCommonDTO(); 
    	DocNoticeDeptDetailDTO  docNoticeDeptDetailDTO = docNoticeDeptDetailForm.getDocNoticeDeptDetailDTO();
    	docNoticeDeptDetailService.updateDetail(docNoticeCommonDTO, docNoticeDeptDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }

    /**
     * CHECK DETAIL
     * @param request
     * @param response
     * @param docNoticeDeptDetailForm
     * @throws Exception
     */
    private void checkDetail(HttpServletRequest request, HttpServletResponse response, DocNoticeDeptDetailForm docNoticeDeptDetailForm) throws Exception
    {
    	DocNoticeDeptDetailService docNoticeDeptDetailService = (DocNoticeDeptDetailService) getBean("docNoticeDeptDetailService");
    	DocNoticeDeptDetailDTO docNoticeDeptDetailDTO = docNoticeDeptDetailForm.getDocNoticeDeptDetailDTO();
        
        String checkDetail = docNoticeDeptDetailService.checkDetail(docNoticeDeptDetailDTO, getUser(request));
        setAjaxDesc(request, checkDetail);
    }
}