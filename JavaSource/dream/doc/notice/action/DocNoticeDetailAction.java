package dream.doc.notice.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.doc.notice.dto.DocNoticeCommonDTO;
import dream.doc.notice.dto.DocNoticeDetailDTO;
import dream.doc.notice.form.DocNoticeDetailForm;
import dream.doc.notice.service.DocNoticeDetailService;

/**
 * DocNotice Page - Detail Action
 * 
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @struts:action path="/docNoticeDetail" name="docNoticeDetailForm"
 *                input="/dream/doc/notice/docNoticeDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/docNoticeCheckDetail" name="docNoticeDetailForm"
 *                input="/dream/doc/notice/docNoticeCheckDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="docNoticeDetail" path="/dream/doc/notice/docNoticeDetail.jsp"
 *                        redirect="false"
 */
public class DocNoticeDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int DETAIL_INIT                 = 8001;
    /** 저장 */
    public static final int DETAIL_INPUT                = 5002;
    /** 수정 */
    public static final int DETAIL_UPDATE               = 6003;
    /** 공지하기 */
    public static final int GO_NOTICE		            = 6004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        DocNoticeDetailForm docNoticeDetailForm = (DocNoticeDetailForm) form;
        
        super.updateAudit(docNoticeDetailForm.getDocNoticeDetailDTO().getAuditKey()==""?docNoticeDetailForm.getDocNoticeCommonDTO().getAuditKey():docNoticeDetailForm.getDocNoticeDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (docNoticeDetailForm.getStrutsAction())
        {
            case DocNoticeDetailAction.DETAIL_INIT:
                this.findDetail(request, response, docNoticeDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case DocNoticeDetailAction.DETAIL_INPUT:
                insertDetail(request, response, docNoticeDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case DocNoticeDetailAction.DETAIL_UPDATE:
                updateDetail(request, response, docNoticeDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case DocNoticeDetailAction.GO_NOTICE:
                goNotice(request, response, docNoticeDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * FIND DETAIL
     * @param request
     * @param response
     * @param docNoticeDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, DocNoticeDetailForm docNoticeDetailForm) throws Exception 
    {   
        DocNoticeDetailService docNoticeDetailService = (DocNoticeDetailService)getBean("docNoticeDetailService",request);
        
        DocNoticeCommonDTO docNoticeCommonDTO = docNoticeDetailForm.getDocNoticeCommonDTO(); 

        User user = getUser(request);
        DocNoticeDetailDTO docNoticeDetailDTO = docNoticeDetailService.findDetail(docNoticeCommonDTO, user);
        docNoticeDetailForm.setDocNoticeDetailDTO(docNoticeDetailDTO);
    }

    /**
     * INSERT DETAIL
     * @param request
     * @param response
     * @param docNoticeDetailForm
     * @throws Exception
     */
    private void insertDetail(HttpServletRequest request, HttpServletResponse response, DocNoticeDetailForm docNoticeDetailForm) throws Exception
    {
        DocNoticeDetailService docNoticeDetailService = (DocNoticeDetailService)getBean("docNoticeDetailService");
        DocNoticeDetailDTO  docNoticeDetailDTO = docNoticeDetailForm.getDocNoticeDetailDTO(); 
        
        User user = getUser(request);
        docNoticeDetailService.insertDetail(docNoticeDetailDTO, user);
        setAjaxStatus(request);
    }

    /**
     * UPDATE DETAIL
     * @param request
     * @param response
     * @param docNoticeDetailForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, DocNoticeDetailForm docNoticeDetailForm) throws Exception
    {
        DocNoticeDetailService docNoticeDetailService = (DocNoticeDetailService)getBean("docNoticeDetailService");
        DocNoticeDetailDTO  docNoticeDetailDTO = docNoticeDetailForm.getDocNoticeDetailDTO();
        
        User user = getUser(request);
        docNoticeDetailService.updateDetail(docNoticeDetailDTO, user);
        setAjaxStatus(request);
    }
    
    /**
     * GO NOTICE
     * @param request
     * @param response
     * @param docNoticeDetailForm
     * @throws Exception
     */
    private void goNotice(HttpServletRequest request, HttpServletResponse response, DocNoticeDetailForm docNoticeDetailForm) throws Exception
    {
        DocNoticeDetailService docNoticeDetailService = (DocNoticeDetailService)getBean("docNoticeDetailService",request);
        DocNoticeDetailDTO  docNoticeDetailDTO = docNoticeDetailForm.getDocNoticeDetailDTO();
        DocNoticeCommonDTO docNoticeCommonDTO = docNoticeDetailForm.getDocNoticeCommonDTO();
        
        User user = getUser(request);
        docNoticeDetailService.goNotice(docNoticeCommonDTO, docNoticeDetailDTO, user);
        setAjaxStatus(request);
    }

}
