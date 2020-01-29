package dream.cert.list.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.cert.list.dto.CertCommonDTO;
import dream.cert.list.dto.CertDetailDTO;
import dream.cert.list.form.CertDetailForm;
import dream.cert.list.service.CertDetailService;

/**
 * 자격증분류 - 상세 action
 * 
 * @author ssong
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/certDetail" name="certDetailForm"
 *                input="/dream/cert/list/certDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="certDetail" path="/dream/cert/list/certDetail.jsp"
 *                        redirect="false"
 */
public class CertDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int CERT_DETAIL_INIT         = 8001;
    /** 저장 */
    public static final int CERT_DETAIL_INPUT        = 5002;
    /** 수정 */
    public static final int CERT_DETAIL_UPDATE       = 6003;

    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        CertDetailForm certDetailForm = (CertDetailForm) form;
        
        super.updateAudit(certDetailForm.getCertDetailDTO().getAuditKey()==""?certDetailForm.getCertCommonDTO().getAuditKey():certDetailForm.getCertDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (certDetailForm.getStrutsAction())
        {
            case CertDetailAction.CERT_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, certDetailForm);
                returnActionForward = mapping.findForward("certDetail");
                break;
            case CertDetailAction.CERT_DETAIL_INPUT:
            	insertDetail(certDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case CertDetailAction.CERT_DETAIL_UPDATE:
            	updateDetail(certDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("certDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 자격증분류 상세 조회
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param certDetailForm
     */
    private void findDetail(HttpServletRequest request, CertDetailForm certDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	CertDetailService certDetailService = (CertDetailService)getBean("certDetailService");
    	
    	CertCommonDTO certCommonDTO = certDetailForm.getCertCommonDTO();
    	
        // 조회된 상세 부분
        CertDetailDTO certDetailDTO = certDetailService.findDetail(getUser(request), certCommonDTO.getCertListId());
        
        // 조회된 상세  셋팅한다.
        certDetailForm.setCertDetailDTO(certDetailDTO);
    }
    
    /**
     * detail insert
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param certDetailForm
     * @param request
     */
    private void insertDetail(CertDetailForm certDetailForm, HttpServletRequest request) throws Exception
    {
        CertDetailService certDetailService = (CertDetailService) getBean("certDetailService");
        
        CertDetailDTO certDetailDTO = certDetailForm.getCertDetailDTO();
        
        certDetailDTO.setCompNo(getUser(request).getCompNo());
        
        certDetailService.insertDetail(certDetailDTO);
        
        setAjaxStatus(request);
    }
    
    /**
     * detail update
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param certDetailForm
     * @param request
     */
    private void updateDetail(CertDetailForm certDetailForm, HttpServletRequest request) throws Exception
    {
    	CertDetailService certDetailService = (CertDetailService) getBean("certDetailService");
        
        CertDetailDTO certDetailDTO = certDetailForm.getCertDetailDTO();
        
        certDetailDTO.setCompNo(getUser(request).getCompNo());
        
        certDetailService.updateDetail(certDetailDTO);
        
        setAjaxStatus(request);
    }

}