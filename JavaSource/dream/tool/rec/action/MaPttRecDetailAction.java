package dream.tool.rec.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.tool.rec.dto.MaPttRecCommonDTO;
import dream.tool.rec.dto.MaPttRecDetailDTO;
import dream.tool.rec.form.MaPttRecDetailForm;
import dream.tool.rec.service.MaPttRecDetailService;

/**
 * 구매입고 - 상세 action
 * 
 * @author ssong
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/maPttRecDetail" name="maPttRecDetailForm"
 *                input="/dream/tool/rec/maPttRecDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPttRecDetail" path="/dream/tool/rec/maPttRecDetail.jsp"
 *                        redirect="false"
 */
public class MaPttRecDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int PTREC_DETAIL_INIT        		  = 8001;
    /** 저장 */
    public static final int PTREC_DETAIL_INPUT       	 	  = 5002;
    /** 수정 */
    public static final int PTREC_DETAIL_UPDATE     		  = 6003;
    /** 상태수정 */
    public static final int PTREC_DETAIL_STATUS_UPDATE		  = 6004;
    /** 중복체크 */
    public static final int PTREC_DETAIL_CHECK       		  = 8005;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaPttRecDetailForm maPttRecDetailForm = (MaPttRecDetailForm) form;
        
        super.updateAudit(maPttRecDetailForm.getMaPttRecDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maPttRecDetailForm.getStrutsAction())
        {
            case MaPttRecDetailAction.PTREC_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, maPttRecDetailForm);
                returnActionForward = mapping.findForward("maPttRecDetail");
                break;
            case MaPttRecDetailAction.PTREC_DETAIL_INPUT:
            	insertDetail(maPttRecDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPttRecDetailAction.PTREC_DETAIL_UPDATE:
            	updateDetail(maPttRecDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPttRecDetailAction.PTREC_DETAIL_STATUS_UPDATE:
                updatePtRecListStatus(maPttRecDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaPttRecDetailAction.PTREC_DETAIL_CHECK:
                validPtRecListNo(maPttRecDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            default:
                returnActionForward = mapping.findForward("maPttRecDetail");
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
     * @param maPttRecDetailForm
     */
    private void findDetail(HttpServletRequest request, MaPttRecDetailForm maPttRecDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaPttRecDetailService maPttRecDetailService = (MaPttRecDetailService)getBean("maPttRecDetailService");
    	
    	MaPttRecCommonDTO maPttRecCommonDTO = maPttRecDetailForm.getMaPttRecCommonDTO();
    	
        // 조회된 상세 부분
        MaPttRecDetailDTO maPttRecDetailDTO = maPttRecDetailService.findDetail(getUser(request), maPttRecCommonDTO.getPrRecListId());
        
        // 조회된 상세  셋팅한다.
        maPttRecDetailForm.setMaPttRecDetailDTO(maPttRecDetailDTO);
    }
    
    /**
     * detail insert
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttRecDetailForm
     * @param request
     */
    private void insertDetail(MaPttRecDetailForm maPttRecDetailForm, HttpServletRequest request) throws Exception
    {
        MaPttRecDetailService maPttRecDetailService = (MaPttRecDetailService) getBean("maPttRecDetailService");
        
        MaPttRecDetailDTO maPttRecDetailDTO = maPttRecDetailForm.getMaPttRecDetailDTO();
        
        maPttRecDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maPttRecDetailService.insertDetail(maPttRecDetailDTO);
        
        setAjaxStatus(request);
    }
    
    /**
     * detail update
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttRecDetailForm
     * @param request
     */
    private void updateDetail(MaPttRecDetailForm maPttRecDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPttRecDetailService maPttRecDetailService = (MaPttRecDetailService) getBean("maPttRecDetailService");
        
        MaPttRecDetailDTO maPttRecDetailDTO = maPttRecDetailForm.getMaPttRecDetailDTO();
        
        maPttRecDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maPttRecDetailService.updateDetail(maPttRecDetailDTO);
        
        setAjaxStatus(request);
    }
    
    /**
     * detail 상태 update
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttRecDetailForm
     * @param request
     */
    private void updatePtRecListStatus(MaPttRecDetailForm maPttRecDetailForm, HttpServletRequest request) throws Exception
    {
        MaPttRecDetailService maPttRecDetailService = (MaPttRecDetailService) getBean("maPttRecDetailService");
        
        MaPttRecDetailDTO maPttRecDetailDTO = maPttRecDetailForm.getMaPttRecDetailDTO();
        
        maPttRecDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maPttRecDetailService.updatePtRecListStatus(maPttRecDetailDTO);
        
        setAjaxStatus(request);
    }
    
    /**
     * valid No
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPttRecDetailForm
     * @param request
     */
    private void validPtRecListNo(MaPttRecDetailForm maPttRecDetailForm, HttpServletRequest request) throws Exception
    {
        MaPttRecDetailService maPttRecDetailService = (MaPttRecDetailService) getBean("maPttRecDetailService");
        
        MaPttRecDetailDTO maPttRecDetailDTO = maPttRecDetailForm.getMaPttRecDetailDTO();
        
        maPttRecDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        String isValid = maPttRecDetailService.validPrRecListNo(maPttRecDetailDTO);
        
        setAjaxDesc(request, isValid);
    }
}