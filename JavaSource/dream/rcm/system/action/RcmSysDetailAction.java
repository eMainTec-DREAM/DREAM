package dream.rcm.system.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.rcm.system.dto.RcmSysDetailDTO;
import dream.rcm.system.form.RcmSysDetailForm;
import dream.rcm.system.service.RcmSysDetailService;

/**
 * System분석 - 상세 action
 * 
 * @author 
 * @version $Id: $
 * @since 1.0
 * @struts:action path="/rcmSysDetail" name="rcmSysDetailForm"
 *                input="/dream/rcm/system/rcmSysDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="rcmSysDetail" path="/dream/rcm/system/rcmSysDetail.jsp"
 *                        redirect="false"
 */
public class RcmSysDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int RCM_DETAIL_INIT 		= 8001;
    /** 저장 */
    public static final int RCM_DETAIL_INPUT 		= 5002;
    /** 수정 */
    public static final int RCM_DETAIL_UPDATE 		= 6003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        RcmSysDetailForm rcmSysDetailForm = (RcmSysDetailForm) form;
        
        super.updateAudit(rcmSysDetailForm.getRcmSysDetailDTO().getAuditKey()==""?rcmSysDetailForm.getRcmSysCommonDTO().getAuditKey():rcmSysDetailForm.getRcmSysDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (rcmSysDetailForm.getStrutsAction())
        {
            case RcmSysDetailAction.RCM_DETAIL_INIT:
                this.findDetail(request, rcmSysDetailForm);
                returnActionForward = mapping.findForward("rcmSysDetail");
                break;
            case RcmSysDetailAction.RCM_DETAIL_INPUT:
            	insertDetail(rcmSysDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case RcmSysDetailAction.RCM_DETAIL_UPDATE:
            	updateDetail(rcmSysDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("rcmSysDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 사원 상세 조회
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param rcmSysDetailForm
     */
    private void findDetail(HttpServletRequest request, RcmSysDetailForm rcmSysDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	RcmSysDetailService rcmSysDetailService = (RcmSysDetailService)getBean("rcmSysDetailService");

        // 넘겨진 사원번호 구함
        String compNo = rcmSysDetailForm.getRcmSysCommonDTO().getCompNo();
        String id  = rcmSysDetailForm.getRcmSysCommonDTO().getRcmListId();
        
        // 조회된 상세 부분
        RcmSysDetailDTO rcmSysDetailDTO = rcmSysDetailService.findDetail(getUser(request), id);
        
        // 조회된 상세  셋팅한다.
        rcmSysDetailForm.setRcmSysDetailDTO(rcmSysDetailDTO);
    }
    /**
     * detail insert
     * @author  
     * @version $Id:  $
     * @since   1.0
     * 
     * @param rcmSysDetailForm
     * @param request
     */
    private void insertDetail(RcmSysDetailForm rcmSysDetailForm, HttpServletRequest request) throws Exception
    {
        RcmSysDetailService rcmSysDetailService = (RcmSysDetailService) getBean("rcmSysDetailService");
        
        RcmSysDetailDTO rcmSysDetailDTO = rcmSysDetailForm.getRcmSysDetailDTO();
        
        rcmSysDetailService.insertDetail(getUser(request), rcmSysDetailDTO);
        
        setAjaxStatus(request);
    }
    
    /**
     * detail update
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param rcmSysDetailForm
     * @param request
     */
    private void updateDetail(RcmSysDetailForm rcmSysDetailForm, HttpServletRequest request) throws Exception
    {
    	RcmSysDetailService rcmSysDetailService = (RcmSysDetailService) getBean("rcmSysDetailService");
        
        RcmSysDetailDTO rcmSysDetailDTO = rcmSysDetailForm.getRcmSysDetailDTO();
        
        rcmSysDetailService.updateDetail(getUser(request), rcmSysDetailDTO);
        
        setAjaxStatus(request);
    }
}