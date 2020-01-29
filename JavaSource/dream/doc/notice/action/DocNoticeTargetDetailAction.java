package dream.doc.notice.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.doc.notice.dto.DocNoticeCommonDTO;
import dream.doc.notice.dto.DocNoticeTargetDetailDTO;
import dream.doc.notice.dto.DocNoticeTargetListDTO;
import dream.doc.notice.form.DocNoticeTargetDetailForm;
import dream.doc.notice.service.DocNoticeTargetDetailService;

/**
 * 공지대상 - Detail Action
 * 
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @struts:action path="/docNoticeTargetDetail" name="docNoticeTargetDetailForm"
 *                input="/dream/doc/notice/docNoticeTargetDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="docNoticeTargetDetail" path="/dream/doc/notice/docNoticeTargetDetail.jsp"
 *                        redirect="false"
 */
public class DocNoticeTargetDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int DETAIL_INIT 		= 8001;
    /** 저장 */
    public static final int DETAIL_INPUT 		= 5002;
    /** 수정 */
    public static final int DETAIL_UPDATE 		= 6003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        DocNoticeTargetDetailForm docNoticeTargetDetailForm = (DocNoticeTargetDetailForm) form;
        
        super.updateAudit(docNoticeTargetDetailForm.getDocNoticeTargetDetailDTO().getAuditKey()==""?docNoticeTargetDetailForm.getDocNoticeTargetListDTO().getAuditKey():docNoticeTargetDetailForm.getDocNoticeTargetDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (docNoticeTargetDetailForm.getStrutsAction())
        {
            case DocNoticeTargetDetailAction.DETAIL_INIT:
                this.findDetail(request, response, docNoticeTargetDetailForm);
                returnActionForward = mapping.findForward("docNoticeTargetDetail");
                break;
            case DocNoticeTargetDetailAction.DETAIL_INPUT:
            	insertDetail(request, response, docNoticeTargetDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case DocNoticeTargetDetailAction.DETAIL_UPDATE:
            	updateDetail(request, response, docNoticeTargetDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("docNoticeTargetDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * FIND DETAIL
     * @param request
     * @param response
     * @param docNoticeTargetDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, DocNoticeTargetDetailForm docNoticeTargetDetailForm) throws Exception 
    {   
    	DocNoticeTargetDetailService docNoticeTargetDetailService = (DocNoticeTargetDetailService)getBean("docNoticeTargetDetailService");
    	
    	DocNoticeCommonDTO docNoticeCommonDTO = docNoticeTargetDetailForm.getDocNoticeCommonDTO(); 
    	DocNoticeTargetListDTO docNoticeTargetListDTO = docNoticeTargetDetailForm.getDocNoticeTargetListDTO();
    	DocNoticeTargetDetailDTO docNoticeTargetDetailDTO = docNoticeTargetDetailService.findDetail(docNoticeCommonDTO,docNoticeTargetListDTO, getUser(request));
    	docNoticeTargetDetailForm.setDocNoticeTargetDetailDTO(docNoticeTargetDetailDTO);
    }

    /**
     * INSERT DETAIL
     * @param request
     * @param response
     * @param docNoticeTargetDetailForm
     * @throws Exception
     */
    private void insertDetail(HttpServletRequest request, HttpServletResponse response, DocNoticeTargetDetailForm docNoticeTargetDetailForm) throws Exception
    {
    	DocNoticeTargetDetailService docNoticeTargetDetailService = (DocNoticeTargetDetailService)getBean("docNoticeTargetDetailService");
    	DocNoticeCommonDTO docNoticeCommonDTO = docNoticeTargetDetailForm.getDocNoticeCommonDTO(); 
    	DocNoticeTargetDetailDTO  docNoticeTargetDetailDTO = docNoticeTargetDetailForm.getDocNoticeTargetDetailDTO(); 
    	docNoticeTargetDetailService.insertDetail(docNoticeCommonDTO,docNoticeTargetDetailDTO, getUser(request));
        setAjaxStatus(request);
    }

    /**
     * UPDATE DETAIL
     * @param request
     * @param response
     * @param docNoticeTargetDetailForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, DocNoticeTargetDetailForm docNoticeTargetDetailForm) throws Exception
    {
    	DocNoticeTargetDetailService docNoticeTargetDetailService = (DocNoticeTargetDetailService)getBean("docNoticeTargetDetailService");
    	DocNoticeCommonDTO docNoticeCommonDTO = docNoticeTargetDetailForm.getDocNoticeCommonDTO(); 
    	DocNoticeTargetDetailDTO  docNoticeTargetDetailDTO = docNoticeTargetDetailForm.getDocNoticeTargetDetailDTO();
    	docNoticeTargetDetailService.updateDetail(docNoticeCommonDTO, docNoticeTargetDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }

}