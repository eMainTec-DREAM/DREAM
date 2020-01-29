package dream.rcm.system.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.rcm.system.dto.RcmSysCommonDTO;
import dream.rcm.system.dto.RcmSysEqAsmbDetailDTO;
import dream.rcm.system.dto.RcmSysEqAsmbListDTO;
import dream.rcm.system.form.RcmSysEqAsmbDetailForm;
import dream.rcm.system.service.RcmSysEqAsmbDetailService;

/**
 * 설비부위 상세
 * @author  kim21017
 * @version $Id: RcmSysEqAsmbDetailAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/rcmSysEqAsmbDetail" name="rcmSysEqAsmbDetailForm"
 *                input="/dream/rcm/system/rcmSysEqAsmbDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="rcmSysEqAsmbDetail" path="/dream/rcm/system/rcmSysEqAsmbDetail.jsp"
 *                        redirect="false"
 */
public class RcmSysEqAsmbDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int RCM_SYS_EQASMB_DETAIL_INIT 		= 8001;
    /** 수정 */
    public static final int RCM_SYS_EQASMB_DETAIL_UPDATE 	= 6002;
    /** 입력 */
    public static final int RCM_SYS_EQASMB_DETAIL_INPUT 	= 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        RcmSysEqAsmbDetailForm rcmSysEqAsmbDetailForm = (RcmSysEqAsmbDetailForm) form;
        
        super.updateAudit(rcmSysEqAsmbDetailForm.getRcmSysEqAsmbDetailDTO().getAuditKey()==""?rcmSysEqAsmbDetailForm.getRcmSysEqAsmbListDTO().getAuditKey():rcmSysEqAsmbDetailForm.getRcmSysEqAsmbDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (rcmSysEqAsmbDetailForm.getStrutsAction())
        {
            case RcmSysEqAsmbDetailAction.RCM_SYS_EQASMB_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, rcmSysEqAsmbDetailForm);
                returnActionForward = mapping.findForward("rcmSysEqAsmbDetail");
                break;
            case RcmSysEqAsmbDetailAction.RCM_SYS_EQASMB_DETAIL_UPDATE:
            	updateDetail(rcmSysEqAsmbDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case RcmSysEqAsmbDetailAction.RCM_SYS_EQASMB_DETAIL_INPUT:
            	insertDetail(rcmSysEqAsmbDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("rcmSysEqAsmbDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 답변 - 상세 조회
     * @author kim2107
     * @version $Id: RcmSysEqAsmbDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param rcmSysEqAsmbDetailForm
     */
    private void findDetail(HttpServletRequest request, RcmSysEqAsmbDetailForm rcmSysEqAsmbDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	RcmSysEqAsmbDetailService rcmSysEqAsmbDetailService = (RcmSysEqAsmbDetailService)getBean("rcmSysEqAsmbDetailService");
    	RcmSysCommonDTO rcmSysCommonDTO = rcmSysEqAsmbDetailForm.getRcmSysCommonDTO();
    	RcmSysEqAsmbListDTO rcmSysEqAsmbListDTO = rcmSysEqAsmbDetailForm.getRcmSysEqAsmbListDTO();
    	
        // 조회된 상세 부분
        RcmSysEqAsmbDetailDTO rcmSysEqAsmbDetailDTO = rcmSysEqAsmbDetailService.findDetail(rcmSysEqAsmbListDTO,rcmSysCommonDTO,getUser(request));
        
        // 조회된 상세  셋팅한다.
        rcmSysEqAsmbDetailForm.setRcmSysEqAsmbDetailDTO(rcmSysEqAsmbDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: RcmSysEqAsmbDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysEqAsmbDetailForm
     * @param request
     */
    private void updateDetail(RcmSysEqAsmbDetailForm rcmSysEqAsmbDetailForm, HttpServletRequest request) throws Exception
    {
    	RcmSysEqAsmbDetailService rcmSysEqAsmbDetailService = (RcmSysEqAsmbDetailService) getBean("rcmSysEqAsmbDetailService");
        
        RcmSysEqAsmbDetailDTO rcmSysEqAsmbDetailDTO = rcmSysEqAsmbDetailForm.getRcmSysEqAsmbDetailDTO();
        RcmSysCommonDTO rcmSysCommonDTO = rcmSysEqAsmbDetailForm.getRcmSysCommonDTO();
    	rcmSysCommonDTO.setCompNo(getUser(request).getCompNo());
        
        rcmSysEqAsmbDetailService.updateDetail(rcmSysEqAsmbDetailDTO,rcmSysCommonDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: RcmSysEqAsmbDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysEqAsmbDetailForm
     * @param request
     */
    private void insertDetail(RcmSysEqAsmbDetailForm rcmSysEqAsmbDetailForm, HttpServletRequest request) throws Exception
    {
    	RcmSysEqAsmbDetailService rcmSysEqAsmbDetailService = (RcmSysEqAsmbDetailService) getBean("rcmSysEqAsmbDetailService");
        
        RcmSysEqAsmbDetailDTO rcmSysEqAsmbDetailDTO = rcmSysEqAsmbDetailForm.getRcmSysEqAsmbDetailDTO();
        
        RcmSysCommonDTO rcmSysCommonDTO = rcmSysEqAsmbDetailForm.getRcmSysCommonDTO();
    	rcmSysCommonDTO.setCompNo(getUser(request).getCompNo());
        
        rcmSysEqAsmbDetailService.insertDetail(rcmSysEqAsmbDetailDTO, rcmSysCommonDTO);
        
        setAjaxStatus(request);
    }
}