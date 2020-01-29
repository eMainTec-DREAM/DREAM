package dream.mgr.mail.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.mgr.mail.dto.MaMailCommonDTO;
import dream.mgr.mail.dto.MaMailDetailDTO;
import dream.mgr.mail.form.MaMailDetailForm;
import dream.mgr.mail.service.MaMailDetailService;

/**
 * ���ϼ����ڼ��� - �� action
 * 
 * @author kim2107
 * @version $Id: MaMailDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/maMailDetail" name="maMailDetailForm"
 *                input="/dream/mgr/mail/maMailDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maMailGroupDetail" name="maMailDetailForm"
 *                input="/dream/mgr/mail/maMailGroupDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maMailDetail" path="/dream/mgr/mail/maMailDetail.jsp"
 *                        redirect="false"
 */
public class MaMailDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int MAIL_DETAIL_INIT 		= 8001;
    /** ���� */
    public static final int MAIL_DETAIL_UPDATE 		= 6002;
    /** �Է� */
    public static final int MAIL_DETAIL_INPUT 		= 5003;
    /** Send Message */
    public static final int MAIL_DETAIL_SEND 		= 5004;
    /** Send Message Check */
    public static final int MAIL_DETAIL_SEND_CHECK 		= 5005;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaMailDetailForm maMailDetailForm = (MaMailDetailForm) form;
        
        super.updateAudit(maMailDetailForm.getMaMailDetailDTO().getAuditKey()==""?maMailDetailForm.getMaMailCommonDTO().getAuditKey():maMailDetailForm.getMaMailDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maMailDetailForm.getStrutsAction())
        {
            case MaMailDetailAction.MAIL_DETAIL_INIT:
                this.findDetail(request, maMailDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case MaMailDetailAction.MAIL_DETAIL_UPDATE:
            	updateDetail(maMailDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaMailDetailAction.MAIL_DETAIL_INPUT:
            	insertDetail(maMailDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaMailDetailAction.MAIL_DETAIL_SEND:
            	sendMessage(maMailDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaMailDetailAction.MAIL_DETAIL_SEND_CHECK:
            	sendMessageCheck(maMailDetailForm, request);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * ���ϼ����ڼ��� �� ��ȸ
     * @author kim2107
     * @version $Id: MaMailDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maMailDetailForm
     */
    private void findDetail(HttpServletRequest request, MaMailDetailForm maMailDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaMailDetailService maMailDetailService = (MaMailDetailService)getBean("maMailDetailService");
    	MaMailCommonDTO maMailCommonDTO = maMailDetailForm.getMaMailCommonDTO();
    	maMailCommonDTO.setCompNo(getUser(request).getCompNo());
    	maMailCommonDTO.setUserLang(getUser(request).getLangId());
        // ��ȸ�� �� �κ�
        MaMailDetailDTO maMailDetailDTO = maMailDetailService.findDetail(maMailCommonDTO);
        
        // ��ȸ�� ��  �����Ѵ�.
        maMailDetailForm.setMaMailDetailDTO(maMailDetailDTO);
    }
    
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaMailDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maMailDetailForm
     * @param request
     */
    private void insertDetail(MaMailDetailForm maMailDetailForm, HttpServletRequest request) throws Exception
    {
    	MaMailDetailService maMailDetailService = (MaMailDetailService) getBean("maMailDetailService");
        
        MaMailDetailDTO maMailDetailDTO = maMailDetailForm.getMaMailDetailDTO();
        maMailDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maMailDetailService.insertDetail(maMailDetailDTO);
        
        setAjaxStatus(request);
    }
    
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaMailDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maMailDetailForm
     * @param request
     */
    private void updateDetail(MaMailDetailForm maMailDetailForm, HttpServletRequest request) throws Exception
    {
    	MaMailDetailService maMailDetailService = (MaMailDetailService) getBean("maMailDetailService");
        
        MaMailDetailDTO maMailDetailDTO = maMailDetailForm.getMaMailDetailDTO();
        maMailDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maMailDetailService.updateDetail(maMailDetailDTO);
        
        setAjaxStatus(request);
    }
    
    /**
     * Send Message
     * @author  hankyul
     * @version $Id: MaMailDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maMailDetailForm
     * @param request
     */
    private void sendMessage(MaMailDetailForm maMailDetailForm, HttpServletRequest request) throws Exception
    {
    	MaMailDetailService maMailDetailService = (MaMailDetailService) getBean("maMailDetailService");
        
        MaMailDetailDTO maMailDetailDTO = maMailDetailForm.getMaMailDetailDTO();
        maMailDetailDTO.setCompNo(getUser(request).getCompNo());
        
        // result = 0:����, -1:���ϼ��� �̵��, -2:���۴���̾���, -3:���������̾���(���� script ����� 0��)
        int result = maMailDetailService.sendMessage(maMailDetailDTO);
        
        setAjaxDesc(request, result);
        
    }
    /**
     * Send Message
     * @author  sy.yang
     * @version $Id: MaMailDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maMailDetailForm
     * @param request
     */
    private void sendMessageCheck(MaMailDetailForm maMailDetailForm, HttpServletRequest request) throws Exception
    {
    	MaMailDetailService maMailDetailService = (MaMailDetailService) getBean("maMailDetailService");
    	
    	MaMailDetailDTO maMailDetailDTO = maMailDetailForm.getMaMailDetailDTO();
    	maMailDetailDTO.setCompNo(getUser(request).getCompNo());
    	
    	// result = 0:����, -1:���ϼ��� �̵��, -2:���۴���̾���, -3:���������̾���(���� script ����� 0��)
    	int result = maMailDetailService.sendMessageCheck(maMailDetailDTO);
    	
    	setAjaxDesc(request, result);
    	
    }
}