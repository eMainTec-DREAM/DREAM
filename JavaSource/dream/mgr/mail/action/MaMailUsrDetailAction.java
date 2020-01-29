package dream.mgr.mail.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.mgr.mail.dto.MaMailCommonDTO;
import dream.mgr.mail.dto.MaMailUsrDetailDTO;
import dream.mgr.mail.dto.MaMailUsrListDTO;
import dream.mgr.mail.form.MaMailUsrDetailForm;
import dream.mgr.mail.service.MaMailUsrDetailService;

/**
 * 메일수신자설정 - 수신자 팝업
 * @author  kim21017
 * @version $Id: MaMailUsrDetailAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maMailUsrDetail" name="maMailUsrDetailForm"
 *                input="/dream/mgr/mail/maMailUsrDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maMailUsrDetail" path="/dream/mgr/mail/maMailUsrDetail.jsp"
 *                        redirect="false"
 */
public class MaMailUsrDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int MAIL_USR_DETAIL_INIT 		= 8001;
    /** 수정 */
    public static final int MAIL_USR_DETAIL_UPDATE 		= 6002;
    /** 입력 */
    public static final int MAIL_USR_DETAIL_INPUT 		= 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaMailUsrDetailForm maMailUsrDetailForm = (MaMailUsrDetailForm) form;
        
        super.updateAudit(maMailUsrDetailForm.getMaMailUsrDetailDTO().getAuditKey()==""?maMailUsrDetailForm.getMaMailUsrListDTO().getAuditKey():maMailUsrDetailForm.getMaMailUsrDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maMailUsrDetailForm.getStrutsAction())
        {
            case MaMailUsrDetailAction.MAIL_USR_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, maMailUsrDetailForm);
                returnActionForward = mapping.findForward("maMailUsrDetail");
                break;
            case MaMailUsrDetailAction.MAIL_USR_DETAIL_UPDATE:
            	updateDetail(maMailUsrDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaMailUsrDetailAction.MAIL_USR_DETAIL_INPUT:
            	insertDetail(maMailUsrDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maMailUsrDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 메일수신자설정 - 수신자 조회
     * @author kim2107
     * @version $Id: MaMailUsrDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maMailUsrDetailForm
     */
    private void findDetail(HttpServletRequest request, MaMailUsrDetailForm maMailUsrDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaMailUsrDetailService maMailUsrDetailService = (MaMailUsrDetailService)getBean("maMailUsrDetailService");
    	MaMailCommonDTO maMailCommonDTO = maMailUsrDetailForm.getMaMailCommonDTO();
    	MaMailUsrListDTO maMailUsrListDTO = maMailUsrDetailForm.getMaMailUsrListDTO();
    	maMailCommonDTO.setCompNo(getUser(request).getCompNo());
        // 조회된 상세 부분
        MaMailUsrDetailDTO maMailUsrDetailDTO = maMailUsrDetailService.findDetail(maMailUsrListDTO,maMailCommonDTO);
        
        // 조회된 상세  셋팅한다.
        maMailUsrDetailForm.setMaMailUsrDetailDTO(maMailUsrDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaMailUsrDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maMailUsrDetailForm
     * @param request
     */
    private void updateDetail(MaMailUsrDetailForm maMailUsrDetailForm, HttpServletRequest request) throws Exception
    {
    	MaMailUsrDetailService maMailUsrDetailService = (MaMailUsrDetailService) getBean("maMailUsrDetailService");
        
        MaMailUsrDetailDTO maMailUsrDetailDTO = maMailUsrDetailForm.getMaMailUsrDetailDTO();
        MaMailCommonDTO maMailCommonDTO = maMailUsrDetailForm.getMaMailCommonDTO();
    	maMailCommonDTO.setCompNo(getUser(request).getCompNo());
        
        maMailUsrDetailService.updateDetail(maMailUsrDetailDTO,maMailCommonDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaMailUsrDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maMailUsrDetailForm
     * @param request
     */
    private void insertDetail(MaMailUsrDetailForm maMailUsrDetailForm, HttpServletRequest request) throws Exception
    {
    	MaMailUsrDetailService maMailUsrDetailService = (MaMailUsrDetailService) getBean("maMailUsrDetailService");
        
        MaMailUsrDetailDTO maMailUsrDetailDTO = maMailUsrDetailForm.getMaMailUsrDetailDTO();
        
        MaMailCommonDTO maMailCommonDTO = maMailUsrDetailForm.getMaMailCommonDTO();
    	maMailCommonDTO.setCompNo(getUser(request).getCompNo());
        
        maMailUsrDetailService.insertDetail(maMailUsrDetailDTO, maMailCommonDTO);
        
        setAjaxStatus(request);
    }
}