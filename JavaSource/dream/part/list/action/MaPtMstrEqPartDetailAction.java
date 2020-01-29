package dream.part.list.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.part.list.dto.MaPtMstrEqPartDetailDTO;
import dream.part.list.form.MaPtMstrEqPartDetailForm;
import dream.part.list.service.MaPtMstrEqPartDetailService;

/**
 * 사용설비
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/maPtMstrEqPartDetail" name="maPtMstrEqPartDetailForm"
 *                input="/dream/part/list/maPtMstrEqPartDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPtMstrEqPartDetail" path="/dream/part/list/maPtMstrEqPartDetail.jsp"
 *                        redirect="false"
 */
public class MaPtMstrEqPartDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int PTMSTR_EQPART_DETAIL_INIT 	= 8001;
    /** 수정 */
    public static final int PTMSTR_EQPART_DETAIL_UPDATE = 6002;
    /** 입력 */
    public static final int PTMSTR_EQPART_DETAIL_INPUT 	= 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaPtMstrEqPartDetailForm maPtMstrEqPartDetailForm = (MaPtMstrEqPartDetailForm) form;
        
        super.updateAudit(maPtMstrEqPartDetailForm.getMaPtMstrEqPartDetailDTO().getAuditKey()==""?maPtMstrEqPartDetailForm.getMaPtMstrCommonDTO().getAuditKey():maPtMstrEqPartDetailForm.getMaPtMstrEqPartDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maPtMstrEqPartDetailForm.getStrutsAction())
        {
            case MaPtMstrEqPartDetailAction.PTMSTR_EQPART_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, maPtMstrEqPartDetailForm);
                returnActionForward = mapping.findForward("maPtMstrEqPartDetail");
                break;
            case MaPtMstrEqPartDetailAction.PTMSTR_EQPART_DETAIL_UPDATE:
            	updateDetail(maPtMstrEqPartDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPtMstrEqPartDetailAction.PTMSTR_EQPART_DETAIL_INPUT:
            	insertDetail(maPtMstrEqPartDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maPtMstrEqPartDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     *  상세 조회
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maPtMstrEqPartDetailForm
     */
    private void findDetail(HttpServletRequest request, MaPtMstrEqPartDetailForm maPtMstrEqPartDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaPtMstrEqPartDetailService maPtMstrEqPartDetailService = (MaPtMstrEqPartDetailService)getBean("maPtMstrEqPartDetailService");

        // 조회된 상세 부분
        MaPtMstrEqPartDetailDTO maPtMstrEqPartDetailDTO = maPtMstrEqPartDetailService.findDetail(maPtMstrEqPartDetailForm.getMaPtMstrCommonDTO(), getUser(request));
        
        // 조회된 상세  셋팅한다.
        maPtMstrEqPartDetailForm.setMaPtMstrEqPartDetailDTO(maPtMstrEqPartDetailDTO);
    }
    
    /**
     * detail update
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtMstrEqPartDetailForm
     * @param request
     */
    private void updateDetail(MaPtMstrEqPartDetailForm maPtMstrEqPartDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPtMstrEqPartDetailService maPtMstrEqPartDetailService = (MaPtMstrEqPartDetailService) getBean("maPtMstrEqPartDetailService");
        
        MaPtMstrEqPartDetailDTO maPtMstrEqPartDetailDTO = maPtMstrEqPartDetailForm.getMaPtMstrEqPartDetailDTO();
        
        maPtMstrEqPartDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        maPtMstrEqPartDetailService.updateDetail(maPtMstrEqPartDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * detail insert
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtMstrEqPartDetailForm
     * @param request
     */
    private void insertDetail(MaPtMstrEqPartDetailForm maPtMstrEqPartDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPtMstrEqPartDetailService maPtMstrEqPartDetailService = (MaPtMstrEqPartDetailService) getBean("maPtMstrEqPartDetailService");
        
        MaPtMstrEqPartDetailDTO maPtMstrEqPartDetailDTO = maPtMstrEqPartDetailForm.getMaPtMstrEqPartDetailDTO();
        
        maPtMstrEqPartDetailDTO.setCompNo((getUser(request).getCompNo()));
        // 자재Id 구함
        String partId = maPtMstrEqPartDetailForm.getMaPtMstrCommonDTO().getPartId();
        maPtMstrEqPartDetailDTO.setPartId(partId);
        
        maPtMstrEqPartDetailService.insertDetail(maPtMstrEqPartDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
}