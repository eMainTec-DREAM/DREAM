package dream.consult.comp.emp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.ConsultAuthAction;
import dream.consult.comp.emp.dto.ConsultCompEmpCommonDTO;
import dream.consult.comp.emp.dto.ConsultCompEmpDetailDTO;
import dream.consult.comp.emp.form.ConsultCompEmpDetailForm;
import dream.consult.comp.emp.service.ConsultCompEmpDetailService;

/**
 * 사원 - 상세 action
 * 
 * @author 
 * @version $Id: $
 * @since 1.0
 * @struts:action path="/consultCompEmpDetail" name="consultCompEmpDetailForm"
 *                input="/dream/consult/comp/emp/consultCompEmpDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="consultCompEmpDetail" path="/dream/consult/comp/emp/consultCompEmpDetail.jsp"
 *                        redirect="false"
 */
public class ConsultCompEmpDetailAction extends ConsultAuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int EMP_DETAIL_INIT 		= 1001;
    /** 저장 */
    public static final int EMP_DETAIL_INPUT 		= 1002;
    /** 수정 */
    public static final int EMP_DETAIL_UPDATE 		= 1003;
    /** 중복 체크 */
    public static final int EMP_DETAIL_CHECK 		= 1004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        ConsultCompEmpDetailForm consultCompEmpDetailForm = (ConsultCompEmpDetailForm) form;
        
        switch (consultCompEmpDetailForm.getStrutsAction())
        {
            case ConsultCompEmpDetailAction.EMP_DETAIL_INIT:
                this.findDetail(request, consultCompEmpDetailForm);
                returnActionForward = mapping.findForward("consultCompEmpDetail");
                break;
            case ConsultCompEmpDetailAction.EMP_DETAIL_INPUT:
            	insertDetail(consultCompEmpDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case ConsultCompEmpDetailAction.EMP_DETAIL_UPDATE:
            	updateDetail(consultCompEmpDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case ConsultCompEmpDetailAction.EMP_DETAIL_CHECK:
            	validEmpNo(consultCompEmpDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("consultCompEmpDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 사원 상세 조회
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param consultCompEmpDetailForm
     */
    private void findDetail(HttpServletRequest request, ConsultCompEmpDetailForm consultCompEmpDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	ConsultCompEmpDetailService consultCompEmpDetailService = (ConsultCompEmpDetailService)getBean("consultCompEmpDetailService");
        
        User user=getUser(request);
        
        // 조회된 상세 부분
        ConsultCompEmpDetailDTO consultCompEmpDetailDTO = consultCompEmpDetailService.findDetail(user, consultCompEmpDetailForm.getConsultCompEmpCommonDTO());
        
        // 조회된 상세  셋팅한다.
        consultCompEmpDetailForm.setConsultCompEmpDetailDTO(consultCompEmpDetailDTO);
    }
    /**
     * detail insert
     * @author  
     * @version $Id:  $
     * @since   1.0
     * 
     * @param consultCompEmpDetailForm
     * @param request
     */
    private void insertDetail(ConsultCompEmpDetailForm consultCompEmpDetailForm, HttpServletRequest request) throws Exception
    {
        ConsultCompEmpDetailService consultCompEmpDetailService = (ConsultCompEmpDetailService) getBean("consultCompEmpDetailService");
        
        ConsultCompEmpDetailDTO consultCompEmpDetailDTO = consultCompEmpDetailForm.getConsultCompEmpDetailDTO();
        
        //consultCompEmpDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        consultCompEmpDetailService.insertDetail(consultCompEmpDetailDTO);
        
        setAjaxStatus(request);
    }
    
    /**
     * detail update
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param consultCompEmpDetailForm
     * @param request
     */
    private void updateDetail(ConsultCompEmpDetailForm consultCompEmpDetailForm, HttpServletRequest request) throws Exception
    {
    	ConsultCompEmpDetailService consultCompEmpDetailService = (ConsultCompEmpDetailService) getBean("consultCompEmpDetailService");
        
        ConsultCompEmpDetailDTO consultCompEmpDetailDTO = consultCompEmpDetailForm.getConsultCompEmpDetailDTO();
        
        //consultCompEmpDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        consultCompEmpDetailService.updateDetail(consultCompEmpDetailDTO);
        
        setAjaxStatus(request);
    }
    
    /**
     * valid emp id
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param consultCompEmpDetailForm
     * @param request
     */
    private void validEmpNo(ConsultCompEmpDetailForm consultCompEmpDetailForm, HttpServletRequest request) throws Exception
    {
    	ConsultCompEmpDetailService consultCompEmpDetailService = (ConsultCompEmpDetailService) getBean("consultCompEmpDetailService");
        
        ConsultCompEmpDetailDTO consultCompEmpDetailDTO = consultCompEmpDetailForm.getConsultCompEmpDetailDTO();
        consultCompEmpDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        String isValid = consultCompEmpDetailService.validEmpNo(consultCompEmpDetailDTO);
        
        setAjaxDesc(request, isValid);
    }

}