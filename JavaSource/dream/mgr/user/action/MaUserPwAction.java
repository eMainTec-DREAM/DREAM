package dream.mgr.user.action;

import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.MwareConfig;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.mgr.user.dto.MaUserCommonDTO;
import dream.mgr.user.dto.MaUserPwDTO;
import dream.mgr.user.form.MaUserPwForm;
import dream.mgr.user.service.MaUserPwService;
import dream.pers.priv.info.dto.MaChangePwDTO;
import dream.pers.priv.info.service.MaChangePwService;

/**
 * ��й�ȣ���� �˾�
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/maUserPw" name="maUserPwForm"
 *                input="/dream/mgr/user/maUserPw.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maUserPw" path="/dream/mgr/user/maUserPw.jsp"
 *                        redirect="false"
 */
public class MaUserPwAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int USER_PW_INIT      = 1001;
    /** ���� */
    public static final int USER_PW_UPDATE    = 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaUserPwForm maUserPwForm = (MaUserPwForm) form;
        switch (maUserPwForm.getStrutsAction())
        {
            case MaUserPwAction.USER_PW_INIT:
                // ��ȸ
                this.findDetail(request, maUserPwForm);
                returnActionForward = mapping.getInputForward();
                break;
            case MaUserPwAction.USER_PW_UPDATE:
            	updateDetail(maUserPwForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * �ý����ڵ� �з� ��ȸ
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param maUserPwForm
     */
    private void findDetail(HttpServletRequest request, MaUserPwForm maUserPwForm)throws Exception 
    {   
        // Service ��ü ����
    	MaUserPwService maUserPwService = (MaUserPwService)getBean("maUserPwService");
        
    	MaUserCommonDTO maUserCommonDTO = maUserPwForm.getMaUserCommonDTO();
    	if("".equals(maUserCommonDTO.getCompNo())) maUserCommonDTO.setCompNo(getUser(request).getCompNo());
        
        // ��ȸ�� �� �κ�
        MaUserPwDTO maUserPwDTO = maUserPwService.findDetail(maUserCommonDTO, getUser(request));
        // ��ȸ�� ��  �����Ѵ�.
        maUserPwForm.setMaUserPwDTO(maUserPwDTO);
    }
    
    /**
     * detail update
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maUserPwForm
     * @param request
     */
    private void updateDetail(MaUserPwForm maUserPwForm, HttpServletRequest request) throws Exception
    {
    	MaUserPwService maUserPwService = (MaUserPwService) getBean("maUserPwService");
        
        MaUserPwDTO maUserPwDTO = maUserPwForm.getMaUserPwDTO();
        // �α� ȸ���ڵ带 �����Ѵ�.
        if("".equals(maUserPwDTO.getCompNo())) maUserPwDTO.setCompNo((getUser(request).getCompNo()));
        maUserPwDTO.setEnterBy(getUser(request).getUserId());
        
        MaChangePwService maChangePwService = (MaChangePwService) getBean("maChangePwService", request);
        
        MaChangePwDTO maChangePwDTO = new MaChangePwDTO();

        maChangePwDTO.setCompNo(maUserPwDTO.getCompNo());
        maChangePwDTO.setUserId(maUserPwDTO.getUserId());
        
        MaChangePwDTO rsltDTO = maChangePwService.findDetail(maChangePwDTO);
        
        maChangePwDTO.setNewPw(maUserPwDTO.getPassword());  //���ο�(�ٲ�) PW
        maChangePwDTO.setConfirmPw(maUserPwDTO.getConfirmPw());
        maChangePwDTO.setOldPw(CommonUtil.aesDecodeString(rsltDTO.getOldPwTemp())); //���� PW Ȯ�ο�
        
        Map<String,String> rtnMap = maChangePwService.checkPassword(maChangePwDTO, getUser(request));

        setAjaxDesc(request, rtnMap.get("MESSAGE"));
    }
    
    private String checkPwHist(MaUserPwService maUserPwService, MaUserPwDTO maUserPwDTO, String pwChangeHistCnt) throws Exception
    {
    	return maUserPwService.checkPwHist(maUserPwDTO, pwChangeHistCnt);
    }
}