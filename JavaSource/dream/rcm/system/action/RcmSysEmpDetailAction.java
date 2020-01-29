package dream.rcm.system.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.rcm.system.dto.RcmSysCommonDTO;
import dream.rcm.system.dto.RcmSysEmpDetailDTO;
import dream.rcm.system.form.RcmSysEmpDetailForm;
import dream.rcm.system.service.RcmSysEmpDetailService;

/**
 * 분석자
 * @author  kim21017
 * @version $Id: RcmSysEmpDetailAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/rcmSysEmpDetail" name="rcmSysEmpDetailForm"
 *                input="/dream/rcm/system/rcmSysEmpDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="rcmSysEmpDetail" path="/dream/rcm/system/rcmSysEmpDetail.jsp"
 *                        redirect="false"
 */
public class RcmSysEmpDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int RCM_SYS_EMP_DETAIL_INIT 	= 8001;
    /** 수정 */
    public static final int RCM_SYS_EMP_DETAIL_UPDATE 	= 6002;
    /** 입력 */
    public static final int RCM_SYS_EMP_DETAIL_INPUT 	= 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        RcmSysEmpDetailForm rcmSysEmpDetailForm = (RcmSysEmpDetailForm) form;
        
        super.updateAudit(rcmSysEmpDetailForm.getRcmSysEmpDetailDTO().getAuditKey()==""?rcmSysEmpDetailForm.getRcmSysEmpListDTO().getAuditKey():rcmSysEmpDetailForm.getRcmSysEmpDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (rcmSysEmpDetailForm.getStrutsAction())
        {
            case RcmSysEmpDetailAction.RCM_SYS_EMP_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, rcmSysEmpDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case RcmSysEmpDetailAction.RCM_SYS_EMP_DETAIL_UPDATE:
            	updateDetail(rcmSysEmpDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case RcmSysEmpDetailAction.RCM_SYS_EMP_DETAIL_INPUT:
            	insertDetail(rcmSysEmpDetailForm, request);
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
     * @version $Id: RcmSysEmpDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param rcmSysEmpDetailForm
     */
    private void findDetail(HttpServletRequest request, RcmSysEmpDetailForm rcmSysEmpDetailForm)throws Exception 
    {   
    	RcmSysEmpDetailService rcmSysEmpDetailService = (RcmSysEmpDetailService)getBean("rcmSysEmpDetailService");

        String rcmListId = rcmSysEmpDetailForm.getRcmSysCommonDTO().getRcmListId();
        String rcmEmpId = rcmSysEmpDetailForm.getRcmSysEmpListDTO().getRcmEmpId();
        
        // 조회된 상세 부분
        RcmSysEmpDetailDTO rcmSysEmpDetailDTO = rcmSysEmpDetailService.findDetail(rcmListId, rcmEmpId, getUser(request).getCompNo());
        
        // 조회된 상세  셋팅한다.
        rcmSysEmpDetailForm.setRcmSysEmpDetailDTO(rcmSysEmpDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: RcmSysEmpDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysEmpDetailForm
     * @param request
     */
    private void updateDetail(RcmSysEmpDetailForm rcmSysEmpDetailForm, HttpServletRequest request) throws Exception
    {
    	RcmSysEmpDetailService rcmSysEmpDetailService = (RcmSysEmpDetailService) getBean("rcmSysEmpDetailService");
        
        RcmSysEmpDetailDTO rcmSysEmpDetailDTO = rcmSysEmpDetailForm.getRcmSysEmpDetailDTO();
        RcmSysCommonDTO maPmMstrMstrCommonDTO = rcmSysEmpDetailForm.getRcmSysCommonDTO();
        rcmSysEmpDetailDTO.setEnterBy(getUser(request).getUserId());
        maPmMstrMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        
        rcmSysEmpDetailService.updateDetail(rcmSysEmpDetailDTO,maPmMstrMstrCommonDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: RcmSysEmpDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysEmpDetailForm
     * @param request
     */
    private void insertDetail(RcmSysEmpDetailForm rcmSysEmpDetailForm, HttpServletRequest request) throws Exception
    {
    	RcmSysEmpDetailService rcmSysEmpDetailService = (RcmSysEmpDetailService) getBean("rcmSysEmpDetailService");
        
        RcmSysEmpDetailDTO rcmSysEmpDetailDTO = rcmSysEmpDetailForm.getRcmSysEmpDetailDTO();
        
        RcmSysCommonDTO maPmMstrMstrCommonDTO = rcmSysEmpDetailForm.getRcmSysCommonDTO();
        rcmSysEmpDetailDTO.setEnterBy(getUser(request).getUserId());
        maPmMstrMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        
        rcmSysEmpDetailService.insertDetail(rcmSysEmpDetailDTO, maPmMstrMstrCommonDTO);
        
        setAjaxStatus(request);
    }
}