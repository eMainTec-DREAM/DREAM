package dream.rcm.system.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.rcm.system.dto.RcmSysCommonDTO;
import dream.rcm.system.dto.RcmSysFEnvDetailDTO;
import dream.rcm.system.dto.RcmSysFEnvListDTO;
import dream.rcm.system.form.RcmSysFEnvDetailForm;
import dream.rcm.system.service.RcmSysFEnvDetailService;

/**
 * 운전환경 상세
 * @author  kim21017
 * @version $Id: RcmSysFEnvDetailAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/rcmSysFEnvDetail" name="rcmSysFEnvDetailForm"
 *                input="/dream/rcm/system/rcmSysFEnvDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="rcmSysFEnvDetail" path="/dream/rcm/system/rcmSysFEnvDetail.jsp"
 *                        redirect="false"
 */
public class RcmSysFEnvDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int RCM_SYS_FENV_DETAIL_INIT 		= 8001;
    /** 수정 */
    public static final int RCM_SYS_FENV_DETAIL_UPDATE 		= 6002;
    /** 입력 */
    public static final int RCM_SYS_FENV_DETAIL_INPUT 		= 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        RcmSysFEnvDetailForm rcmSysFEnvDetailForm = (RcmSysFEnvDetailForm) form;
        
        super.updateAudit(rcmSysFEnvDetailForm.getRcmSysFEnvDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (rcmSysFEnvDetailForm.getStrutsAction())
        {
            case RcmSysFEnvDetailAction.RCM_SYS_FENV_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, rcmSysFEnvDetailForm);
                returnActionForward = mapping.findForward("rcmSysFEnvDetail");
                break;
            case RcmSysFEnvDetailAction.RCM_SYS_FENV_DETAIL_UPDATE:
            	updateDetail(rcmSysFEnvDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case RcmSysFEnvDetailAction.RCM_SYS_FENV_DETAIL_INPUT:
            	insertDetail(rcmSysFEnvDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("rcmSysFEnvDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 답변 - 상세 조회
     * @author kim2107
     * @version $Id: RcmSysFEnvDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param rcmSysFEnvDetailForm
     */
    private void findDetail(HttpServletRequest request, RcmSysFEnvDetailForm rcmSysFEnvDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	RcmSysFEnvDetailService rcmSysFEnvDetailService = (RcmSysFEnvDetailService)getBean("rcmSysFEnvDetailService");
    	RcmSysCommonDTO rcmSysCommonDTO = rcmSysFEnvDetailForm.getRcmSysCommonDTO();
    	RcmSysFEnvListDTO rcmSysFEnvListDTO = rcmSysFEnvDetailForm.getRcmSysFEnvListDTO();
    	
        // 조회된 상세 부분
        RcmSysFEnvDetailDTO rcmSysFEnvDetailDTO = rcmSysFEnvDetailService.findDetail(rcmSysFEnvListDTO,rcmSysCommonDTO,getUser(request));
        
        // 조회된 상세  셋팅한다.
        rcmSysFEnvDetailForm.setRcmSysFEnvDetailDTO(rcmSysFEnvDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: RcmSysFEnvDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysFEnvDetailForm
     * @param request
     */
    private void updateDetail(RcmSysFEnvDetailForm rcmSysFEnvDetailForm, HttpServletRequest request) throws Exception
    {
    	RcmSysFEnvDetailService rcmSysFEnvDetailService = (RcmSysFEnvDetailService) getBean("rcmSysFEnvDetailService");
        
        RcmSysFEnvDetailDTO rcmSysFEnvDetailDTO = rcmSysFEnvDetailForm.getRcmSysFEnvDetailDTO();
        RcmSysCommonDTO rcmSysCommonDTO = rcmSysFEnvDetailForm.getRcmSysCommonDTO();
    	rcmSysCommonDTO.setCompNo(getUser(request).getCompNo());
        
        rcmSysFEnvDetailService.updateDetail(rcmSysFEnvDetailDTO,rcmSysCommonDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: RcmSysFEnvDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysFEnvDetailForm
     * @param request
     */
    private void insertDetail(RcmSysFEnvDetailForm rcmSysFEnvDetailForm, HttpServletRequest request) throws Exception
    {
    	RcmSysFEnvDetailService rcmSysFEnvDetailService = (RcmSysFEnvDetailService) getBean("rcmSysFEnvDetailService");
        
        RcmSysFEnvDetailDTO rcmSysFEnvDetailDTO = rcmSysFEnvDetailForm.getRcmSysFEnvDetailDTO();
        
        RcmSysCommonDTO rcmSysCommonDTO = rcmSysFEnvDetailForm.getRcmSysCommonDTO();
    	rcmSysCommonDTO.setCompNo(getUser(request).getCompNo());
        
        rcmSysFEnvDetailService.insertDetail(rcmSysFEnvDetailDTO, rcmSysCommonDTO);
        
        setAjaxStatus(request);
    }
}