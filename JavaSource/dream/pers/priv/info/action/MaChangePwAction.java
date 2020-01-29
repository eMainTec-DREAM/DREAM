package dream.pers.priv.info.action;

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
import dream.pers.priv.info.dto.MaChangePwDTO;
import dream.pers.priv.info.form.MaChangePwForm;
import dream.pers.priv.info.service.MaChangePwService;

/**
 * ��й�ȣ���� �˾�
 * @author  kim21017
 * @version $Id: MaChangePwAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maChangePw" name="maChangePwForm"
 *                input="/dream/pers/priv/info/maChangePw.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maChangePw" path="/dream/pers/priv/info/maChangePw.jsp"
 *                        redirect="false"
 */
public class MaChangePwAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int CHG_PW_INIT			= 1001;
    /** ���� */
    public static final int CHG_PW_UPDATE		= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaChangePwForm maChangePwForm = (MaChangePwForm) form;
        switch (maChangePwForm.getStrutsAction())
        {
            case MaChangePwAction.CHG_PW_INIT:
                // ��ȸ
                this.findDetail(request, maChangePwForm);
                returnActionForward = mapping.findForward("maChangePw");
                break;
            case MaChangePwAction.CHG_PW_UPDATE:
            	updateDetail(maChangePwForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maChangePw");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * �ý����ڵ� �з� ��ȸ
     * @author kim2107
     * @version $Id: MaChangePwAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maChangePwForm
     */
    private void findDetail(HttpServletRequest request, MaChangePwForm maChangePwForm)throws Exception 
    {   
        // Service ��ü ����
    	MaChangePwService maChangePwService = (MaChangePwService)getBean("maChangePwService");
    	MaChangePwDTO maChangePwDTO = maChangePwForm.getMaChangePwDTO();
        
        maChangePwDTO.setUserId(getUser(request).getUserId());
        maChangePwDTO.setCompNo(getUser(request).getCompNo());
        // ��ȸ�� ��  �����Ѵ�.
        maChangePwForm.setMaChangePwDTO(maChangePwService.findDetail(maChangePwDTO));
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaChangePwAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maChangePwForm
     * @param request
     */
    private void updateDetail(MaChangePwForm maChangePwForm, HttpServletRequest request) throws Exception
    {
    	MaChangePwService maChangePwService = (MaChangePwService) getBean("maChangePwService");
        MaChangePwDTO maChangePwDTO = maChangePwForm.getMaChangePwDTO();
        maChangePwDTO.setEnterBy(getUser(request).getUserId());
        maChangePwDTO.setCompNo(getUser(request).getCompNo());
        
        
        Map<String,String> rtnMap = maChangePwService.checkPassword(maChangePwDTO, getUser(request));

        setAjaxDesc(request, rtnMap.get("MESSAGE"));
        
    }
    
    private String checkPwHist(MaChangePwService maChangePwService, MaChangePwDTO maChangePwDTO, String pwChangeHistCnt) throws Exception
	{
		return maChangePwService.checkPwHist(maChangePwDTO, pwChangeHistCnt);
	}
}