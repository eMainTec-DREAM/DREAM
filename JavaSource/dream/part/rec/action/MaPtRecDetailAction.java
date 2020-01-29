package dream.part.rec.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.part.rec.dto.MaPtRecCommonDTO;
import dream.part.rec.dto.MaPtRecDetailDTO;
import dream.part.rec.form.MaPtRecDetailForm;
import dream.part.rec.service.MaPtRecDetailService;

/**
 * 구매입고 - 상세 action
 * 
 * @author ssong
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/maPtRecDetail" name="maPtRecDetailForm"
 *                input="/dream/part/rec/maPtRecDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPtRecDetail" path="/dream/part/rec/maPtRecDetail.jsp"
 *                        redirect="false"
 */
public class MaPtRecDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int PTREC_DETAIL_INIT         = 8001;
    /** 저장 */
    public static final int PTREC_DETAIL_INPUT        = 5002;
    /** 수정 */
    public static final int PTREC_DETAIL_UPDATE       = 6003;
    /** 상태수정 */
    public static final int PTREC_DETAIL_STATUS_UPDATE= 6004;
    /** 중복체크 */
    public static final int PTREC_DETAIL_CHECK        = 8005;
    /** 시리얼 확인 */
    public static final int SERIAL_COUNT      	      = 8006;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaPtRecDetailForm maPtRecDetailForm = (MaPtRecDetailForm) form;

        super.updateAudit(maPtRecDetailForm.getMaPtRecDetailDTO().getAuditKey()==""?maPtRecDetailForm.getMaPtRecCommonDTO().getAuditKey():maPtRecDetailForm.getMaPtRecDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));

        switch (maPtRecDetailForm.getStrutsAction())
        {
            case MaPtRecDetailAction.PTREC_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, maPtRecDetailForm);
                returnActionForward = mapping.findForward("maPtRecDetail");
                break;
            case MaPtRecDetailAction.PTREC_DETAIL_INPUT:
            	insertDetail(maPtRecDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPtRecDetailAction.PTREC_DETAIL_UPDATE:
            	updateDetail(maPtRecDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPtRecDetailAction.PTREC_DETAIL_STATUS_UPDATE:
                updatePtRecListStatus(maPtRecDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaPtRecDetailAction.PTREC_DETAIL_CHECK:
                validPtRecListNo(maPtRecDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaPtRecDetailAction.SERIAL_COUNT:
                validSerialCount(maPtRecDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            default:
                returnActionForward = mapping.findForward("maPtRecDetail");
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
     * @param maPtRecDetailForm
     */
    private void findDetail(HttpServletRequest request, MaPtRecDetailForm maPtRecDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaPtRecDetailService maPtRecDetailService = (MaPtRecDetailService)getBean("maPtRecDetailService", request);
    	
    	MaPtRecCommonDTO maPtRecCommonDTO = maPtRecDetailForm.getMaPtRecCommonDTO();
    	
        // 조회된 상세 부분
        MaPtRecDetailDTO maPtRecDetailDTO = maPtRecDetailService.findDetail(getUser(request), maPtRecCommonDTO.getPrRecListId());
        
        // 조회된 상세  셋팅한다.
        maPtRecDetailForm.setMaPtRecDetailDTO(maPtRecDetailDTO);
    }
    
    /**
     * detail insert
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRecDetailForm
     * @param request
     */
    private void insertDetail(MaPtRecDetailForm maPtRecDetailForm, HttpServletRequest request) throws Exception
    {
        MaPtRecDetailService maPtRecDetailService = (MaPtRecDetailService) getBean("maPtRecDetailService", request);
        
        MaPtRecDetailDTO maPtRecDetailDTO = maPtRecDetailForm.getMaPtRecDetailDTO();
        
        maPtRecDetailDTO.setCompNo(getUser(request).getCompNo());
        
        User user = getUser(request);
        
        maPtRecDetailService.insertDetail(maPtRecDetailDTO, user);
        
        setAjaxStatus(request);
    }
    
    /**
     * detail update
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRecDetailForm
     * @param request
     */
    private void updateDetail(MaPtRecDetailForm maPtRecDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPtRecDetailService maPtRecDetailService = (MaPtRecDetailService) getBean("maPtRecDetailService", request);
        
        MaPtRecDetailDTO maPtRecDetailDTO = maPtRecDetailForm.getMaPtRecDetailDTO();
        
        maPtRecDetailDTO.setCompNo(getUser(request).getCompNo());
        
        User user = getUser(request);
        
        maPtRecDetailService.updateDetail(maPtRecDetailDTO, getUser(request));
                
        setAjaxStatus(request);
    }
    
    /**
     * detail 상태 update
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRecDetailForm
     * @param request
     */
    private void updatePtRecListStatus(MaPtRecDetailForm maPtRecDetailForm, HttpServletRequest request) throws Exception
    {
        MaPtRecDetailService maPtRecDetailService = (MaPtRecDetailService) getBean("maPtRecDetailService", request);
        
        MaPtRecDetailDTO maPtRecDetailDTO = maPtRecDetailForm.getMaPtRecDetailDTO();
        
        maPtRecDetailDTO.setCompNo(getUser(request).getCompNo());
        
        User user = getUser(request);
        
        maPtRecDetailService.updatePtRecListStatus(maPtRecDetailDTO,user);
        
        setAjaxStatus(request);
    }
    
    /**
     * valid No
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPtRecDetailForm
     * @param request
     */
    private void validPtRecListNo(MaPtRecDetailForm maPtRecDetailForm, HttpServletRequest request) throws Exception
    {
        MaPtRecDetailService maPtRecDetailService = (MaPtRecDetailService) getBean("maPtRecDetailService", request);
        
        MaPtRecDetailDTO maPtRecDetailDTO = maPtRecDetailForm.getMaPtRecDetailDTO();
        
        maPtRecDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        String isValid = maPtRecDetailService.validPrRecListNo(maPtRecDetailDTO);
        
        setAjaxDesc(request, isValid);
    }
    
    /**
     * valid No
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPtRecDetailForm
     * @param request
     */
    private void validSerialCount(MaPtRecDetailForm maPtRecDetailForm, HttpServletRequest request) throws Exception
    {
        MaPtRecDetailService maPtRecDetailService = (MaPtRecDetailService) getBean("maPtRecDetailService", request);
        
        MaPtRecDetailDTO maPtRecDetailDTO = maPtRecDetailForm.getMaPtRecDetailDTO();
        
        maPtRecDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        String isValid = maPtRecDetailService.validSerialCount(maPtRecDetailDTO);
        
        setAjaxDesc(request, isValid);
    }
}