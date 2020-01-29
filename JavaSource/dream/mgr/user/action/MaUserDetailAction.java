package dream.mgr.user.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.mgr.user.dto.MaUserCommonDTO;
import dream.mgr.user.dto.MaUserDetailDTO;
import dream.mgr.user.form.MaUserDetailForm;
import dream.mgr.user.service.MaUserDetailService;

/**
 * ����� - �� action
 * 
 * @author 
 * @version $Id: $
 * @since 1.0
 * @struts:action path="/maUserDetail" name="maUserDetailForm"
 *                input="/dream/mgr/user/maUserDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maUserDetail" path="/dream/mgr/user/maUserDetail.jsp"
 *                        redirect="false"
 */
public class MaUserDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int USER_DETAIL_INIT 		= 8001;
    /** ���� */
    public static final int USER_DETAIL_INPUT 		= 5002;
    /** ���� */
    public static final int USER_DETAIL_UPDATE 		= 6003;
    /** �ߺ� üũ */
    public static final int USER_DETAIL_CHECK 		= 1004;
    /** ���� �뺸 */
    public static final int USER_DETAIL_SEND_MAIL 	= 1005;
    /** PW Reset Mail �뺸 */
    public static final int USER_DETAIL_SEND_PW_MAIL= 1006;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaUserDetailForm maUserDetailForm = (MaUserDetailForm) form;
        
        super.updateAudit(maUserDetailForm.getMaUserDetailDTO().getAuditKey()==""?maUserDetailForm.getMaUserCommonDTO().getAuditKey():maUserDetailForm.getMaUserDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maUserDetailForm.getStrutsAction())
        {
            case MaUserDetailAction.USER_DETAIL_INIT:
                this.findDetail(request, maUserDetailForm);
                returnActionForward = mapping.findForward("maUserDetail");
                break;
            case MaUserDetailAction.USER_DETAIL_INPUT:
            	insertDetail(maUserDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaUserDetailAction.USER_DETAIL_UPDATE:
            	updateDetail(maUserDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaUserDetailAction.USER_DETAIL_CHECK:
            	validUserNo(maUserDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaUserDetailAction.USER_DETAIL_SEND_MAIL:
                sendUserInfoMail(maUserDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaUserDetailAction.USER_DETAIL_SEND_PW_MAIL:
                sendUserPwMail(maUserDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            default:
                returnActionForward = mapping.findForward("maUserDetail");
                break;
        }

        return returnActionForward;
    }
    
    private void sendUserPwMail(MaUserDetailForm maUserDetailForm, HttpServletRequest request) throws Exception
    {
        MaUserDetailService maUserDetailService = (MaUserDetailService) getBean("maUserDetailService");
        
        MaUserCommonDTO maUserCommonDTO = maUserDetailForm.getMaUserCommonDTO();
        
        int result = maUserDetailService.sendUserPwMail(maUserCommonDTO, getUser(request));
        
        setAjaxDesc(request, result);
    }

    /**
     * ����� �� ��ȸ
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param maUserDetailForm
     */
    private void findDetail(HttpServletRequest request, MaUserDetailForm maUserDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaUserDetailService maUserDetailService = (MaUserDetailService)getBean("maUserDetailService");
    	
    	User user = getUser(request);

        // �Ѱ��� ����ڹ�ȣ ����
    	MaUserCommonDTO maUserCommonDTO = maUserDetailForm.getMaUserCommonDTO();
    	maUserCommonDTO.setCompNo(user.getCompNo());
        maUserCommonDTO.setFilterCompNo(user.getCompNo());
        
        // ��ȸ�� �� �κ�
        MaUserDetailDTO maUserDetailDTO = maUserDetailService.findDetail(maUserCommonDTO, user);
        
        // ��ȸ�� ��  �����Ѵ�.
        maUserDetailForm.setMaUserDetailDTO(maUserDetailDTO);
    }
    
    /**
     * detail insert
     * @author  
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maUserDetailForm
     * @param request
     */
    private void insertDetail(MaUserDetailForm maUserDetailForm, HttpServletRequest request) throws Exception
    {
        MaUserDetailService maUserDetailService = (MaUserDetailService) getBean("maUserDetailService");
        
        MaUserDetailDTO maUserDetailDTO = maUserDetailForm.getMaUserDetailDTO();
        
        maUserDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        maUserDetailService.insertDetail(maUserDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * detail update
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maUserDetailForm
     * @param request
     */
    private void updateDetail(MaUserDetailForm maUserDetailForm, HttpServletRequest request) throws Exception
    {
    	MaUserDetailService maUserDetailService = (MaUserDetailService) getBean("maUserDetailService");
        
        MaUserDetailDTO maUserDetailDTO = maUserDetailForm.getMaUserDetailDTO();
        
        maUserDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        maUserDetailService.updateDetail(maUserDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * valid user id
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maUserDetailForm
     * @param request
     */
    private void validUserNo(MaUserDetailForm maUserDetailForm, HttpServletRequest request) throws Exception
    {
    	MaUserDetailService maUserDetailService = (MaUserDetailService) getBean("maUserDetailService");
        
        MaUserDetailDTO maUserDetailDTO = maUserDetailForm.getMaUserDetailDTO();
        
        maUserDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        String isValid = maUserDetailService.validUserNo(maUserDetailDTO, getUser(request));
        
        setAjaxDesc(request, isValid);
    }
    
    private void sendUserInfoMail(MaUserDetailForm maUserDetailForm, HttpServletRequest request) throws Exception
    {
        MaUserDetailService maUserDetailService = (MaUserDetailService) getBean("maUserDetailService");
        
        MaUserCommonDTO maUserCommonDTO = maUserDetailForm.getMaUserCommonDTO();
        
        int result = maUserDetailService.sendUserInfoMail(maUserCommonDTO, getUser(request));
        
        setAjaxDesc(request, result);
    }

}