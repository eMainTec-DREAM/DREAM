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
 * 비밀번호변경 팝업
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
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int CHG_PW_INIT			= 1001;
    /** 수정 */
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
                // 조회
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
     * 시스템코드 분류 조회
     * @author kim2107
     * @version $Id: MaChangePwAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maChangePwForm
     */
    private void findDetail(HttpServletRequest request, MaChangePwForm maChangePwForm)throws Exception 
    {   
        // Service 객체 생성
    	MaChangePwService maChangePwService = (MaChangePwService)getBean("maChangePwService");
    	MaChangePwDTO maChangePwDTO = maChangePwForm.getMaChangePwDTO();
        
        maChangePwDTO.setUserId(getUser(request).getUserId());
        maChangePwDTO.setCompNo(getUser(request).getCompNo());
        // 조회된 상세  셋팅한다.
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