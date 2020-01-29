package dream.part.list.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.part.list.dto.MaPtMstrUsedDeptDetailDTO;
import dream.part.list.form.MaPtMstrUsedDeptDetailForm;
import dream.part.list.service.MaPtMstrUsedDeptDetailService;

/**
 * 부품사용부서
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/maPtMstrUsedDeptDetail" name="maPtMstrUsedDeptDetailForm"
 *                input="/dream/part/list/maPtMstrUsedDeptDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPtMstrUsedDeptDetail" path="/dream/part/list/maPtMstrUsedDeptDetail.jsp"
 *                        redirect="false"
 */
public class MaPtMstrUsedDeptDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int PTMSTR_USEDDEPT_DETAIL_INIT 	= 1001;
    /** 수정 */
    public static final int PTMSTR_USEDDEPT_DETAIL_UPDATE = 1002;
    /** 입력 */
    public static final int PTMSTR_USEDDEPT_DETAIL_INPUT 	= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaPtMstrUsedDeptDetailForm maPtMstrUsedDeptDetailForm = (MaPtMstrUsedDeptDetailForm) form;
        switch (maPtMstrUsedDeptDetailForm.getStrutsAction())
        {
            case MaPtMstrUsedDeptDetailAction.PTMSTR_USEDDEPT_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, maPtMstrUsedDeptDetailForm);
                returnActionForward = mapping.findForward("maPtMstrUsedDeptDetail");
                break;
            case MaPtMstrUsedDeptDetailAction.PTMSTR_USEDDEPT_DETAIL_UPDATE:
            	updateDetail(maPtMstrUsedDeptDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPtMstrUsedDeptDetailAction.PTMSTR_USEDDEPT_DETAIL_INPUT:
            	insertDetail(maPtMstrUsedDeptDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maPtMstrUsedDeptDetail");
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
     * @param maPtMstrUsedDeptDetailForm
     */
    private void findDetail(HttpServletRequest request, MaPtMstrUsedDeptDetailForm maPtMstrUsedDeptDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaPtMstrUsedDeptDetailService maPtMstrUsedDeptDetailService = (MaPtMstrUsedDeptDetailService)getBean("maPtMstrUsedDeptDetailService");

        // 조회된 상세 부분
        MaPtMstrUsedDeptDetailDTO maPtMstrUsedDeptDetailDTO = maPtMstrUsedDeptDetailService.findDetail(maPtMstrUsedDeptDetailForm.getMaPtMstrCommonDTO(), getUser(request));
        
        // 조회된 상세  셋팅한다.
        maPtMstrUsedDeptDetailForm.setMaPtMstrUsedDeptDetailDTO(maPtMstrUsedDeptDetailDTO);
    }
    
    /**
     * detail update
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtMstrUsedDeptDetailForm
     * @param request
     */
    private void updateDetail(MaPtMstrUsedDeptDetailForm maPtMstrUsedDeptDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPtMstrUsedDeptDetailService maPtMstrUsedDeptDetailService = (MaPtMstrUsedDeptDetailService) getBean("maPtMstrUsedDeptDetailService");
        
        MaPtMstrUsedDeptDetailDTO maPtMstrUsedDeptDetailDTO = maPtMstrUsedDeptDetailForm.getMaPtMstrUsedDeptDetailDTO();
        
        maPtMstrUsedDeptDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        maPtMstrUsedDeptDetailService.updateDetail(maPtMstrUsedDeptDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * detail insert
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtMstrUsedDeptDetailForm
     * @param request
     */
    private void insertDetail(MaPtMstrUsedDeptDetailForm maPtMstrUsedDeptDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPtMstrUsedDeptDetailService maPtMstrUsedDeptDetailService = (MaPtMstrUsedDeptDetailService) getBean("maPtMstrUsedDeptDetailService");
        
        MaPtMstrUsedDeptDetailDTO maPtMstrUsedDeptDetailDTO = maPtMstrUsedDeptDetailForm.getMaPtMstrUsedDeptDetailDTO();
        
        maPtMstrUsedDeptDetailDTO.setCompNo((getUser(request).getCompNo()));
        // 자재Id 구함
        String partId = maPtMstrUsedDeptDetailForm.getMaPtMstrCommonDTO().getPartId();
        maPtMstrUsedDeptDetailDTO.setPartId(partId);
        
        maPtMstrUsedDeptDetailService.insertDetail(maPtMstrUsedDeptDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
}