package dream.org.dept.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.org.dept.dto.MaDeptDetailDTO;
import dream.org.dept.form.MaDeptDetailForm;
import dream.org.dept.service.MaDeptDetailService;

/**
 * 보전부서 - 상세 action
 * 
 * @author 
 * @version $Id: $
 * @since 1.0
 * @struts:action path="/maDeptDetail" name="maDeptDetailForm"
 *                input="/dream/org/dept/maDeptDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maDeptDetail" path="/dream/org/dept/maDeptDetail.jsp"
 *                        redirect="false"
 */
public class MaDeptDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int DEPT_DETAIL_INIT 		= 8001;
    /** 저장 */
    public static final int DEPT_DETAIL_INPUT 		= 5002;
    /** 수정 */
    public static final int DEPT_DETAIL_UPDATE 		= 6003;
    /** 중복 체크 */
    public static final int DEPT_DETAIL_CHECK 		= 1004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaDeptDetailForm maDeptDetailForm = (MaDeptDetailForm) form;
        
        super.updateAudit(maDeptDetailForm.getMaDeptDetailDTO().getAuditKey()==""?maDeptDetailForm.getMaDeptCommonDTO().getAuditKey():maDeptDetailForm.getMaDeptDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maDeptDetailForm.getStrutsAction())
        {
            case MaDeptDetailAction.DEPT_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, maDeptDetailForm);
                returnActionForward = mapping.findForward("maDeptDetail");
                break;
            case MaDeptDetailAction.DEPT_DETAIL_INPUT:
            	insertDetail(maDeptDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaDeptDetailAction.DEPT_DETAIL_UPDATE:
            	updateDetail(maDeptDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaDeptDetailAction.DEPT_DETAIL_CHECK:
            	validDeptNo(maDeptDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maDeptDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 보전부서 상세 조회
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param maDeptDetailForm
     */
    private void findDetail(HttpServletRequest request, MaDeptDetailForm maDeptDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaDeptDetailService maDeptDetailService = (MaDeptDetailService)getBean("maDeptDetailService");

        // 넘겨진 부서코드 구함
        String compNo = maDeptDetailForm.getMaDeptCommonDTO().getCompNo();
        String deptId = maDeptDetailForm.getMaDeptCommonDTO().getDeptId();
        
        // 조회된 상세 부분
        MaDeptDetailDTO maDeptDetailDTO = maDeptDetailService.findDetail(getUser(request), deptId);
        
        // 조회된 상세  셋팅한다.
        maDeptDetailForm.setMaDeptDetailDTO(maDeptDetailDTO);
    }
    /**
     * detail insert
     * @author  
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maDeptDetailForm
     * @param request
     */
    private void insertDetail(MaDeptDetailForm maDeptDetailForm, HttpServletRequest request) throws Exception
    {
        MaDeptDetailService maDeptDetailService = (MaDeptDetailService) getBean("maDeptDetailService");
        
        MaDeptDetailDTO maDeptDetailDTO = maDeptDetailForm.getMaDeptDetailDTO();
        
        maDeptDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        maDeptDetailService.insertDetail(maDeptDetailDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail update
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maDeptDetailForm
     * @param request
     */
    private void updateDetail(MaDeptDetailForm maDeptDetailForm, HttpServletRequest request) throws Exception
    {
    	MaDeptDetailService maDeptDetailService = (MaDeptDetailService) getBean("maDeptDetailService");
        
        MaDeptDetailDTO maDeptDetailDTO = maDeptDetailForm.getMaDeptDetailDTO();
        
        maDeptDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        maDeptDetailService.updateDetail(maDeptDetailDTO);
        
        setAjaxStatus(request);
    }
    /**
     * valid dept no
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maDeptDetailForm
     * @param request
     */
    private void validDeptNo(MaDeptDetailForm maDeptDetailForm, HttpServletRequest request) throws Exception
    {
    	MaDeptDetailService maDeptDetailService = (MaDeptDetailService) getBean("maDeptDetailService");
        
        MaDeptDetailDTO maDeptDetailDTO = maDeptDetailForm.getMaDeptDetailDTO();
        
        maDeptDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        String isValid = maDeptDetailService.validDeptNo(maDeptDetailDTO);
        
        setAjaxDesc(request, isValid);
    }
    
}