package dream.rcm.system.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.rcm.system.dto.RcmSysCommonDTO;
import dream.rcm.system.dto.RcmSysEqDetailDTO;
import dream.rcm.system.form.RcmSysEqDetailForm;
import dream.rcm.system.service.RcmSysEqDetailService;

/**
 * 설비설정
 * @author  kim21017
 * @version $Id: RcmSysEqDetailAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/rcmSysEqDetail" name="rcmSysEqDetailForm"
 *                input="/dream/rcm/system/rcmSysEqDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="rcmSysEqDetail" path="/dream/rcm/system/rcmSysEqDetail.jsp"
 *                        redirect="false"
 */
public class RcmSysEqDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int RCM_SYS_EQ_DETAIL_INIT 		= 8001;
    /** 수정 */
    public static final int RCM_SYS_EQ_DETAIL_UPDATE 	= 6002;
    /** 입력 */
    public static final int RCM_SYS_EQ_DETAIL_INPUT 	= 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        RcmSysEqDetailForm rcmSysEqDetailForm = (RcmSysEqDetailForm) form;
        
        super.updateAudit(rcmSysEqDetailForm.getRcmSysEqDetailDTO().getAuditKey()==""?rcmSysEqDetailForm.getRcmSysEqListDTO().getAuditKey():rcmSysEqDetailForm.getRcmSysEqDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (rcmSysEqDetailForm.getStrutsAction())
        {
            case RcmSysEqDetailAction.RCM_SYS_EQ_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, rcmSysEqDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case RcmSysEqDetailAction.RCM_SYS_EQ_DETAIL_UPDATE:
            	updateDetail(rcmSysEqDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case RcmSysEqDetailAction.RCM_SYS_EQ_DETAIL_INPUT:
            	insertDetail(rcmSysEqDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 상세 조회
     * @author kim2107
     * @version $Id: RcmSysEqDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param rcmSysEqDetailForm
     */
    private void findDetail(HttpServletRequest request, RcmSysEqDetailForm rcmSysEqDetailForm)throws Exception 
    {   
    	RcmSysEqDetailService rcmSysEqDetailService = (RcmSysEqDetailService)getBean("rcmSysEqDetailService");

        String rcmListId = rcmSysEqDetailForm.getRcmSysCommonDTO().getRcmListId();
        String rcmEqId = rcmSysEqDetailForm.getRcmSysEqListDTO().getRcmEqId();
        
        // 조회된 상세 부분
        RcmSysEqDetailDTO rcmSysEqDetailDTO = rcmSysEqDetailService.findDetail(rcmListId, rcmEqId, getUser(request));
        
        // 조회된 상세  셋팅한다.
        rcmSysEqDetailForm.setRcmSysEqDetailDTO(rcmSysEqDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: RcmSysEqDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysEqDetailForm
     * @param request
     */
    private void updateDetail(RcmSysEqDetailForm rcmSysEqDetailForm, HttpServletRequest request) throws Exception
    {
    	RcmSysEqDetailService rcmSysEqDetailService = (RcmSysEqDetailService) getBean("rcmSysEqDetailService");
        
        RcmSysEqDetailDTO rcmSysEqDetailDTO = rcmSysEqDetailForm.getRcmSysEqDetailDTO();
        RcmSysCommonDTO maPmMstrMstrCommonDTO = rcmSysEqDetailForm.getRcmSysCommonDTO();
        rcmSysEqDetailDTO.setEnterBy(getUser(request).getUserId());
        maPmMstrMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        
        rcmSysEqDetailService.updateDetail(rcmSysEqDetailDTO,maPmMstrMstrCommonDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: RcmSysEqDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysEqDetailForm
     * @param request
     */
    private void insertDetail(RcmSysEqDetailForm rcmSysEqDetailForm, HttpServletRequest request) throws Exception
    {
    	RcmSysEqDetailService rcmSysEqDetailService = (RcmSysEqDetailService) getBean("rcmSysEqDetailService");
        
        RcmSysEqDetailDTO rcmSysEqDetailDTO = rcmSysEqDetailForm.getRcmSysEqDetailDTO();
        
        RcmSysCommonDTO maPmMstrMstrCommonDTO = rcmSysEqDetailForm.getRcmSysCommonDTO();
        rcmSysEqDetailDTO.setEnterBy(getUser(request).getUserId());
        maPmMstrMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        
        rcmSysEqDetailService.insertDetail(rcmSysEqDetailDTO, maPmMstrMstrCommonDTO);
        
        setAjaxStatus(request);
    }
}