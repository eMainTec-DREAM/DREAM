package dream.rcm.fmea.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.rcm.fmea.dto.RcmFmeaCommonDTO;
import dream.rcm.fmea.dto.RcmFmeaDetailDTO;
import dream.rcm.fmea.form.RcmFmeaDetailForm;
import dream.rcm.fmea.service.RcmFmeaDetailService;

/**
 * 상세 action
 * 
 * @author kim2107
 * @version $Id: RcmFmeaDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/rcmFmeaDetail" name="rcmFmeaDetailForm"
 *                input="/dream/rcm/fmea/rcmFmeaDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="rcmFmeaDetail" path="/dream/rcm/fmea/rcmFmeaDetail.jsp"
 *                        redirect="false"
 */
public class RcmFmeaDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int FMEA_DETAIL_INIT 		= 8001;
    /** 수정 */
    public static final int FMEA_DETAIL_UPDATE 		= 6002;
    /** 입력 */
    public static final int FMEA_DETAIL_INPUT 		= 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        RcmFmeaDetailForm rcmFmeaDetailForm = (RcmFmeaDetailForm) form;
        
        super.updateAudit(rcmFmeaDetailForm.getRcmFmeaDetailDTO().getAuditKey()==""?rcmFmeaDetailForm.getRcmFmeaCommonDTO().getAuditKey():rcmFmeaDetailForm.getRcmFmeaDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (rcmFmeaDetailForm.getStrutsAction())
        {
            case RcmFmeaDetailAction.FMEA_DETAIL_INIT:
                this.findDetail(request, rcmFmeaDetailForm);
                returnActionForward = mapping.findForward("rcmFmeaDetail");
                break;
            case RcmFmeaDetailAction.FMEA_DETAIL_UPDATE:
            	updateDetail(rcmFmeaDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case RcmFmeaDetailAction.FMEA_DETAIL_INPUT:
            	insertDetail(rcmFmeaDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("rcmFmeaDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 질의 상세 조회
     * @author kim2107
     * @version $Id: RcmFmeaDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param rcmFmeaDetailForm
     */
    private void findDetail(HttpServletRequest request, RcmFmeaDetailForm rcmFmeaDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	RcmFmeaDetailService rcmFmeaDetailService = (RcmFmeaDetailService)getBean("rcmFmeaDetailService");
    	RcmFmeaCommonDTO rcmFmeaCommonDTO = rcmFmeaDetailForm.getRcmFmeaCommonDTO();
    	rcmFmeaCommonDTO.setCompNo(getUser(request).getCompNo());
        // 조회된 상세 부분
        RcmFmeaDetailDTO rcmFmeaDetailDTO = rcmFmeaDetailService.findDetail(rcmFmeaCommonDTO);
        
        if("".equals(rcmFmeaDetailDTO.getRcmfmeaId()))rcmFmeaDetailForm.setStrutsAction(0);
        
        // 조회된 상세  셋팅한다.
        rcmFmeaDetailForm.setRcmFmeaDetailDTO(rcmFmeaDetailDTO);
    }
    
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: RcmFmeaDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFmeaDetailForm
     * @param request
     */
    private void insertDetail(RcmFmeaDetailForm rcmFmeaDetailForm, HttpServletRequest request) throws Exception
    {
    	RcmFmeaDetailService rcmFmeaDetailService = (RcmFmeaDetailService) getBean("rcmFmeaDetailService");
        
        RcmFmeaDetailDTO rcmFmeaDetailDTO = rcmFmeaDetailForm.getRcmFmeaDetailDTO();
        rcmFmeaDetailDTO.setCompNo(getUser(request).getCompNo());
        
        rcmFmeaDetailService.insertDetail(rcmFmeaDetailDTO);
        
        setAjaxStatus(request);
    }
    
    /**
     * detail update
     * @author  kim21017
     * @version $Id: RcmFmeaDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFmeaDetailForm
     * @param request
     */
    private void updateDetail(RcmFmeaDetailForm rcmFmeaDetailForm, HttpServletRequest request) throws Exception
    {
    	RcmFmeaDetailService rcmFmeaDetailService = (RcmFmeaDetailService) getBean("rcmFmeaDetailService");
        
        RcmFmeaDetailDTO rcmFmeaDetailDTO = rcmFmeaDetailForm.getRcmFmeaDetailDTO();
        rcmFmeaDetailDTO.setCompNo(getUser(request).getCompNo());
        
        rcmFmeaDetailService.updateDetail(rcmFmeaDetailDTO);
        
        setAjaxStatus(request);
    }
}