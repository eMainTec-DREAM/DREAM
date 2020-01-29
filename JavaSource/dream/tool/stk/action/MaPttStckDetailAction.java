package dream.tool.stk.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.tool.stk.dto.MaPttStckCommonDTO;
import dream.tool.stk.dto.MaPttStckDetailDTO;
import dream.tool.stk.form.MaPttStckDetailForm;
import dream.tool.stk.service.MaPttStckDetailService;

/**
 * 자재재고 - 상세 action
 * 
 * @author ssong
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/maPttStckDetail" name="maPttStckDetailForm"
 *                input="/dream/tool/stk/maPttStckDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPttStckDetail" path="/dream/tool/stk/maPttStckDetail.jsp"
 *                        redirect="false"
 */
public class MaPttStckDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int PTSTCK_DETAIL_INIT         = 8001;
    /** 저장 */
    public static final int PTSTCK_DETAIL_INPUT        = 5002;
    /** 수정 */
    public static final int PTSTCK_DETAIL_UPDATE       = 6003;
    /** 중복체크 */
    public static final int PTSTCK_DETAIL_CHECK        = 1004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaPttStckDetailForm maPttStckDetailForm = (MaPttStckDetailForm) form;
        
        super.updateAudit(maPttStckDetailForm.getMaPttStckDetailDTO().getAuditKey()==""?maPttStckDetailForm.getMaPttStckCommonDTO().getAuditKey():maPttStckDetailForm.getMaPttStckDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));

        switch (maPttStckDetailForm.getStrutsAction())
        {
            case MaPttStckDetailAction.PTSTCK_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, maPttStckDetailForm);
                returnActionForward = mapping.findForward("maPttStckDetail");
                break;
            case MaPttStckDetailAction.PTSTCK_DETAIL_INPUT:
            	insertDetail(maPttStckDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPttStckDetailAction.PTSTCK_DETAIL_UPDATE:
            	updateDetail(maPttStckDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPttStckDetailAction.PTSTCK_DETAIL_CHECK:
                validPtStck(maPttStckDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            default:
                returnActionForward = mapping.findForward("maPttStckDetail");
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
     * @param maPttStckDetailForm
     */
    private void findDetail(HttpServletRequest request, MaPttStckDetailForm maPttStckDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaPttStckDetailService maPttStckDetailService = (MaPttStckDetailService)getBean("maPttStckDetailService");
    	
    	MaPttStckCommonDTO maPttStckCommonDTO = maPttStckDetailForm.getMaPttStckCommonDTO();
    	
    	maPttStckCommonDTO.setCompNo(getUser(request).getCompNo());
        // 조회된 상세 부분
        MaPttStckDetailDTO maPttStckDetailDTO = maPttStckDetailService.findDetail(maPttStckCommonDTO);
        
        // 조회된 상세  셋팅한다.
        maPttStckDetailForm.setMaPttStckDetailDTO(maPttStckDetailDTO);
    }
    /**
     * detail insert
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttStckDetailForm
     * @param request
     */
    private void insertDetail(MaPttStckDetailForm maPttStckDetailForm, HttpServletRequest request) throws Exception
    {
        MaPttStckDetailService maPttStckDetailService = (MaPttStckDetailService) getBean("maPttStckDetailService");
        
        MaPttStckDetailDTO maPttStckDetailDTO = maPttStckDetailForm.getMaPttStckDetailDTO();
        
        maPttStckDetailDTO.setEnterBy(getUser(request).getUserId());
        maPttStckDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maPttStckDetailService.insertDetail(maPttStckDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * detail update
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttStckDetailForm
     * @param request
     */
    private void updateDetail(MaPttStckDetailForm maPttStckDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPttStckDetailService maPttStckDetailService = (MaPttStckDetailService) getBean("maPttStckDetailService");
        
        MaPttStckDetailDTO maPttStckDetailDTO = maPttStckDetailForm.getMaPttStckDetailDTO();
        
        maPttStckDetailDTO.setEnterBy(getUser(request).getUserId());
        maPttStckDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maPttStckDetailService.updateDetail(maPttStckDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * valid PtStock
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPttStckDetailForm
     * @param request
     */
    private void validPtStck(MaPttStckDetailForm maPttStckDetailForm, HttpServletRequest request) throws Exception
    {
        MaPttStckDetailService maPttStckDetailService = (MaPttStckDetailService) getBean("maPttStckDetailService");
        
        MaPttStckDetailDTO maPttStckDetailDTO = maPttStckDetailForm.getMaPttStckDetailDTO();
        
        maPttStckDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        String isValid = maPttStckDetailService.validPtStck(maPttStckDetailDTO);
        
        setAjaxDesc(request, isValid);
    }
}