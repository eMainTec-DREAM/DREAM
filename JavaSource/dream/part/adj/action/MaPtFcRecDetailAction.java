package dream.part.adj.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.part.adj.dto.MaPtFcRecCommonDTO;
import dream.part.adj.dto.MaPtFcRecDetailDTO;
import dream.part.adj.form.MaPtFcRecDetailForm;
import dream.part.adj.service.MaPtFcRecDetailService;

/**
 * 무상입고 - 상세 action
 * 
 * @author ssong
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/maPtFcRecDetail" name="maPtFcRecDetailForm"
 *                input="/dream/part/adj/maPtFcRecDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPtFcRecDetail" path="/dream/part/adj/maPtFcRecDetail.jsp"
 *                        redirect="false"
 */
public class MaPtFcRecDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int PTFCREC_DETAIL_INIT         = 8001;
    /** 저장 */
    public static final int PTFCREC_DETAIL_INPUT        = 5002;
    /** 수정 */
    public static final int PTFCREC_DETAIL_UPDATE       = 6003;
    /** 상태수정 */
    public static final int PTFCREC_DETAIL_STATUS_UPDATE= 6004;
    /** 중복체크 */
    public static final int PTFCREC_DETAIL_CHECK        = 8005;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaPtFcRecDetailForm maPtFcRecDetailForm = (MaPtFcRecDetailForm) form;
        
        super.updateAudit(maPtFcRecDetailForm.getMaPtFcRecDetailDTO().getAuditKey()==""?maPtFcRecDetailForm.getMaPtFcRecCommonDTO().getAuditKey():maPtFcRecDetailForm.getMaPtFcRecDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maPtFcRecDetailForm.getStrutsAction())
        {
            case MaPtFcRecDetailAction.PTFCREC_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, maPtFcRecDetailForm);
                returnActionForward = mapping.findForward("maPtFcRecDetail");
                break;
            case MaPtFcRecDetailAction.PTFCREC_DETAIL_INPUT:
            	insertDetail(maPtFcRecDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPtFcRecDetailAction.PTFCREC_DETAIL_UPDATE:
            	updateDetail(maPtFcRecDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPtFcRecDetailAction.PTFCREC_DETAIL_STATUS_UPDATE:
                updatePtFcRecListStatus(maPtFcRecDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaPtFcRecDetailAction.PTFCREC_DETAIL_CHECK:
                validPtFcRecListNo(maPtFcRecDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            default:
                returnActionForward = mapping.findForward("maPtFcRecDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 구매입고 상세 조회
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maPtFcRecDetailForm
     */
    private void findDetail(HttpServletRequest request, MaPtFcRecDetailForm maPtFcRecDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaPtFcRecDetailService maPtFcRecDetailService = (MaPtFcRecDetailService)getBean("maPtFcRecDetailService");
    	
    	MaPtFcRecCommonDTO maPtFcRecCommonDTO = maPtFcRecDetailForm.getMaPtFcRecCommonDTO();
    	
        // 조회된 상세 부분
        MaPtFcRecDetailDTO maPtFcRecDetailDTO = maPtFcRecDetailService.findDetail(getUser(request), maPtFcRecCommonDTO.getFcRecListId());
        
        // 조회된 상세  셋팅한다.
        maPtFcRecDetailForm.setMaPtFcRecDetailDTO(maPtFcRecDetailDTO);
    }
    
    /**
     * detail insert
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtFcRecDetailForm
     * @param request
     */
    private void insertDetail(MaPtFcRecDetailForm maPtFcRecDetailForm, HttpServletRequest request) throws Exception
    {
        MaPtFcRecDetailService maPtFcRecDetailService = (MaPtFcRecDetailService) getBean("maPtFcRecDetailService");
        
        MaPtFcRecDetailDTO maPtFcRecDetailDTO = maPtFcRecDetailForm.getMaPtFcRecDetailDTO();
        
        maPtFcRecDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maPtFcRecDetailService.insertDetail(maPtFcRecDetailDTO);
        
        setAjaxStatus(request);
    }
    
    /**
     * detail update
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtFcRecDetailForm
     * @param request
     */
    private void updateDetail(MaPtFcRecDetailForm maPtFcRecDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPtFcRecDetailService maPtFcRecDetailService = (MaPtFcRecDetailService) getBean("maPtFcRecDetailService");
        
        MaPtFcRecDetailDTO maPtFcRecDetailDTO = maPtFcRecDetailForm.getMaPtFcRecDetailDTO();
        
        maPtFcRecDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maPtFcRecDetailService.updateDetail(maPtFcRecDetailDTO);
        
        setAjaxStatus(request);
    }
    
    /**
     * detail 상태 update
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtFcRecDetailForm
     * @param request
     */
    private void updatePtFcRecListStatus(MaPtFcRecDetailForm maPtFcRecDetailForm, HttpServletRequest request) throws Exception
    {
        MaPtFcRecDetailService maPtFcRecDetailService = (MaPtFcRecDetailService) getBean("maPtFcRecDetailService");
        
        MaPtFcRecDetailDTO maPtFcRecDetailDTO = maPtFcRecDetailForm.getMaPtFcRecDetailDTO();
        
        maPtFcRecDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maPtFcRecDetailService.updatePtFcRecListStatus(maPtFcRecDetailDTO);
        
        setAjaxStatus(request);
    }
    
    /**
     * valid No
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPtFcRecDetailForm
     * @param request
     */
    private void validPtFcRecListNo(MaPtFcRecDetailForm maPtFcRecDetailForm, HttpServletRequest request) throws Exception
    {
        MaPtFcRecDetailService maPtFcRecDetailService = (MaPtFcRecDetailService) getBean("maPtFcRecDetailService");
        
        MaPtFcRecDetailDTO maPtFcRecDetailDTO = maPtFcRecDetailForm.getMaPtFcRecDetailDTO();
        
        maPtFcRecDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        String isValid = maPtFcRecDetailService.validFcRecListNo(maPtFcRecDetailDTO);
        
        setAjaxDesc(request, isValid);
    }
}