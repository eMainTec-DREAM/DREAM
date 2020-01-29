package dream.consult.comp.dept.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.ConsultAuthAction;
import dream.consult.comp.dept.dto.ConsultCompDeptCommonDTO;
import dream.consult.comp.dept.dto.ConsultCompDeptDetailDTO;
import dream.consult.comp.dept.form.ConsultCompDeptDetailForm;
import dream.consult.comp.dept.service.ConsultCompDeptDetailService;

/**
 * 보전부서 - 상세 action
 * 
 * @author hyosung
 * @version $Id: $
 * @since 1.0
 * @struts:action path="/consultCompDeptDetail" name="consultCompDeptDetailForm"
 *                input="/dream/consult/comp/dept/consultCompDeptDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="consultCompDeptDetail" path="/dream/consult/comp/dept/consultCompDeptDetail.jsp"
 *                        redirect="false"
 */
public class ConsultCompDeptDetailAction extends ConsultAuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int DEPT_DETAIL_INIT 		= 1001;
    /** 저장 */
    public static final int DEPT_DETAIL_INPUT 		= 1002;
    /** 수정 */
    public static final int DEPT_DETAIL_UPDATE 		= 1003;
    /** 중복 체크 */
    public static final int DEPT_DETAIL_CHECK 		= 1004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        ConsultCompDeptDetailForm consultCompDeptDetailForm = (ConsultCompDeptDetailForm) form;
        
        switch (consultCompDeptDetailForm.getStrutsAction())
        {
            case ConsultCompDeptDetailAction.DEPT_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, consultCompDeptDetailForm);
                returnActionForward = mapping.findForward("consultCompDeptDetail");
                break;
            case ConsultCompDeptDetailAction.DEPT_DETAIL_INPUT:
            	this.insertDetail(consultCompDeptDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case ConsultCompDeptDetailAction.DEPT_DETAIL_UPDATE:
            	this.updateDetail(consultCompDeptDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case ConsultCompDeptDetailAction.DEPT_DETAIL_CHECK:
            	this.validDeptNo(consultCompDeptDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("consultCompDeptDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 보전부서 상세 조회
     * @author  hyosung
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param consultCompDeptDetailForm
     */
    private void findDetail(HttpServletRequest request, ConsultCompDeptDetailForm consultCompDeptDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	ConsultCompDeptDetailService consultCompDeptDetailService = (ConsultCompDeptDetailService)getBean("consultCompDeptDetailService");
    	// 조회된 상세 부분
    	ConsultCompDeptCommonDTO consultCompDeptCommonDTO= consultCompDeptDetailForm.getConsultCompDeptCommonDTO();
    	
    	ConsultCompDeptDetailDTO consultCompDeptDetailDTO = consultCompDeptDetailService.findDetail(consultCompDeptCommonDTO,getUser(request).getLangId());
    	
        // 조회된 상세  셋팅한다.
        consultCompDeptDetailForm.setConsultCompDeptDetailDTO(consultCompDeptDetailDTO);
    }
    /**
     * detail insert
     * @author  hyosung
     * @version $Id:  $
     * @since   1.0
     * 
     * @param consultCompDeptDetailForm
     * @param request
     */
    private void insertDetail(ConsultCompDeptDetailForm consultCompDeptDetailForm, HttpServletRequest request) throws Exception
    {
        ConsultCompDeptDetailService consultCompDeptDetailService = (ConsultCompDeptDetailService) getBean("consultCompDeptDetailService");
        
        ConsultCompDeptDetailDTO consultCompDeptDetailDTO = consultCompDeptDetailForm.getConsultCompDeptDetailDTO();
        
        consultCompDeptDetailService.insertDetail(consultCompDeptDetailDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail update
     * @author  hyosung
     * @version $Id: $
     * @since   1.0
     * 
     * @param consultCompDeptDetailForm
     * @param request
     */
    private void updateDetail(ConsultCompDeptDetailForm consultCompDeptDetailForm, HttpServletRequest request) throws Exception
    {
    	ConsultCompDeptDetailService consultCompDeptDetailService = (ConsultCompDeptDetailService) getBean("consultCompDeptDetailService");
        
        ConsultCompDeptDetailDTO consultCompDeptDetailDTO = consultCompDeptDetailForm.getConsultCompDeptDetailDTO();
        
        consultCompDeptDetailService.updateDetail(consultCompDeptDetailDTO);
        
        setAjaxStatus(request);
    }
    /**
     * valid dept no
     * @author  hyosung
     * @version $Id: $
     * @since   1.0
     * 
     * @param consultCompDeptDetailForm
     * @param request
     */
    private void validDeptNo(ConsultCompDeptDetailForm consultCompDeptDetailForm, HttpServletRequest request) throws Exception
    {
    	ConsultCompDeptDetailService consultCompDeptDetailService = (ConsultCompDeptDetailService) getBean("consultCompDeptDetailService");
        
        ConsultCompDeptDetailDTO consultCompDeptDetailDTO = consultCompDeptDetailForm.getConsultCompDeptDetailDTO();
        
        consultCompDeptDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        String isValid = consultCompDeptDetailService.validDeptNo(consultCompDeptDetailDTO);
        
        setAjaxDesc(request, isValid);
    }
    
}