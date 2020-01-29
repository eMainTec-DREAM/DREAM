package dream.part.stk.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.part.stk.dto.MaPtStckCommonDTO;
import dream.part.stk.dto.MaPtStckDetailDTO;
import dream.part.stk.form.MaPtStckDetailForm;
import dream.part.stk.service.MaPtStckDetailService;

/**
 * 자재재고 - 상세 action
 * 
 * @author ssong
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/maPtStckDetail" name="maPtStckDetailForm"
 *                input="/dream/part/stk/maPtStckDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPtStckDetail" path="/dream/part/stk/maPtStckDetail.jsp"
 *                        redirect="false"
 */
public class MaPtStckDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int PTSTCK_DETAIL_INIT         = 8001;
    /** 저장 */
    public static final int PTSTCK_DETAIL_INPUT        = 5002;
    /** 수정 */
    public static final int PTSTCK_DETAIL_UPDATE       = 6003;
    /** 중복체크 */
    public static final int PTSTCK_DETAIL_CHECK        = 8004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaPtStckDetailForm maPtStckDetailForm = (MaPtStckDetailForm) form;
        
        super.updateAudit(maPtStckDetailForm.getMaPtStckDetailDTO().getAuditKey()==""?maPtStckDetailForm.getMaPtStckCommonDTO().getAuditKey():maPtStckDetailForm.getMaPtStckDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maPtStckDetailForm.getStrutsAction())
        {
            case MaPtStckDetailAction.PTSTCK_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, maPtStckDetailForm);
                returnActionForward = mapping.findForward("maPtStckDetail");
                break;
            case MaPtStckDetailAction.PTSTCK_DETAIL_INPUT:
            	insertDetail(maPtStckDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPtStckDetailAction.PTSTCK_DETAIL_UPDATE:
            	updateDetail(maPtStckDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPtStckDetailAction.PTSTCK_DETAIL_CHECK:
                validPtStck(maPtStckDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaPtStckDetailAction.BASE_ACTION_REPORT:
            	findReport(request, maPtStckDetailForm);
                returnActionForward = mapping.findForward("pdfViewer");
                break;
            default:
                returnActionForward = mapping.findForward("maPtStckDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 자재재고 상세 조회
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maPtStckDetailForm
     */
    private void findDetail(HttpServletRequest request, MaPtStckDetailForm maPtStckDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaPtStckDetailService maPtStckDetailService = (MaPtStckDetailService)getBean("maPtStckDetailService");
    	
    	MaPtStckCommonDTO maPtStckCommonDTO = maPtStckDetailForm.getMaPtStckCommonDTO();
    	
    	maPtStckCommonDTO.setCompNo(getUser(request).getCompNo());
        // 조회된 상세 부분
        MaPtStckDetailDTO maPtStckDetailDTO = maPtStckDetailService.findDetail(maPtStckCommonDTO);
        
        // 조회된 상세  셋팅한다.
        maPtStckDetailForm.setMaPtStckDetailDTO(maPtStckDetailDTO);
    }
    /**
     * detail insert
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtStckDetailForm
     * @param request
     */
    private void insertDetail(MaPtStckDetailForm maPtStckDetailForm, HttpServletRequest request) throws Exception
    {
        MaPtStckDetailService maPtStckDetailService = (MaPtStckDetailService) getBean("maPtStckDetailService");
        
        MaPtStckDetailDTO maPtStckDetailDTO = maPtStckDetailForm.getMaPtStckDetailDTO();
        
        maPtStckDetailDTO.setEnterBy(getUser(request).getUserId());
        maPtStckDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maPtStckDetailService.insertDetail(maPtStckDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * detail update
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtStckDetailForm
     * @param request
     */
    private void updateDetail(MaPtStckDetailForm maPtStckDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPtStckDetailService maPtStckDetailService = (MaPtStckDetailService) getBean("maPtStckDetailService");
        
        MaPtStckDetailDTO maPtStckDetailDTO = maPtStckDetailForm.getMaPtStckDetailDTO();
        
        maPtStckDetailDTO.setEnterBy(getUser(request).getUserId());
        maPtStckDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maPtStckDetailService.updateDetail(maPtStckDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * valid PtStock
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPtStckDetailForm
     * @param request
     */
    private void validPtStck(MaPtStckDetailForm maPtStckDetailForm, HttpServletRequest request) throws Exception
    {
        MaPtStckDetailService maPtStckDetailService = (MaPtStckDetailService) getBean("maPtStckDetailService");
        
        MaPtStckDetailDTO maPtStckDetailDTO = maPtStckDetailForm.getMaPtStckDetailDTO();
        
        maPtStckDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        String isValid = maPtStckDetailService.validPtStck(maPtStckDetailDTO);
        
        setAjaxDesc(request, isValid);
    }
    private void findReport(HttpServletRequest request, MaPtStckDetailForm maPtStckDetailForm)
    {
    	MaPtStckDetailService maPtStckDetailService = (MaPtStckDetailService) getBean("maPtStckDetailService");
        
        MaPtStckDetailDTO maPtStckDetailDTO = maPtStckDetailForm.getMaPtStckDetailDTO();
        
        List reportList = maPtStckDetailService.getReportView(maPtStckDetailDTO,getUser(request));

        //System.out.println(getUser(request).getCompNo());
        String rptName = "ptStockDetail";
        
        // 조회한 List 를 form에 셋팅한다.
        request.setAttribute(REPORT_NAME_ATTRIBUTE, rptName);
        request.setAttribute(REPORT_MAP_ATTRIBUTE, reportList);
    }
}