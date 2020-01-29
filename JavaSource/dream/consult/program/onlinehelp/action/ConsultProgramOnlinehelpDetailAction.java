package dream.consult.program.onlinehelp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.ConsultAuthAction;
import dream.consult.program.onlinehelp.dto.ConsultProgramOnlinehelpDetailDTO;
import dream.consult.program.onlinehelp.form.ConsultProgramOnlinehelpDetailForm;
import dream.consult.program.onlinehelp.service.ConsultProgramOnlinehelpDetailService;

/**
 * 도움말 - 상세 action
 * 
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/consultProgramOnlinehelpDetail" name="consultProgramOnlinehelpDetailForm"
 *                input="/dream/consult/program/onlinehelp/consultProgramOnlinehelpDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="consultProgramOnlinehelpDetail" path="/dream/consult/program/onlinehelp/consultProgramOnlinehelpDetail.jsp"
 *                        redirect="false"
 */
public class ConsultProgramOnlinehelpDetailAction extends ConsultAuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int HELP_DETAIL_INIT 			= 1001;
    /** 저장 */
    public static final int HELP_DETAIL_INPUT 		= 1002;
    /** 수정 */
    public static final int HELP_DETAIL_UPDATE 		= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        ConsultProgramOnlinehelpDetailForm consultProgramOnlinehelpDetailForm = (ConsultProgramOnlinehelpDetailForm) form;
        
        switch (consultProgramOnlinehelpDetailForm.getStrutsAction())
        {
            case ConsultProgramOnlinehelpDetailAction.HELP_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, consultProgramOnlinehelpDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case ConsultProgramOnlinehelpDetailAction.HELP_DETAIL_INPUT:
            	insertDetail(consultProgramOnlinehelpDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case ConsultProgramOnlinehelpDetailAction.HELP_DETAIL_UPDATE:
            	updateDetail(consultProgramOnlinehelpDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 도움말 상세 조회
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param consultProgramOnlinehelpDetailForm
     */
    private void findDetail(HttpServletRequest request, ConsultProgramOnlinehelpDetailForm consultProgramOnlinehelpDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	ConsultProgramOnlinehelpDetailService consultProgramOnlinehelpDetailService = (ConsultProgramOnlinehelpDetailService)getBean("consultProgramOnlinehelpDetailService");

        // 넘겨진 Id 구함
        String id = consultProgramOnlinehelpDetailForm.getConsultProgramOnlinehelpCommonDTO().getOnlineHelpId();
        
        // 조회된 상세 부분
        ConsultProgramOnlinehelpDetailDTO consultProgramOnlinehelpDetailDTO = consultProgramOnlinehelpDetailService.findDetail(id,getUser(request));
        
        // 조회된 상세  셋팅한다.
        consultProgramOnlinehelpDetailForm.setConsultProgramOnlinehelpDetailDTO(consultProgramOnlinehelpDetailDTO);
    }
    /**
     * detail insert
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param consultProgramOnlinehelpDetailForm
     * @param request
     */
    private void insertDetail(ConsultProgramOnlinehelpDetailForm consultProgramOnlinehelpDetailForm, HttpServletRequest request) throws Exception
    {
        ConsultProgramOnlinehelpDetailService consultProgramOnlinehelpDetailService = (ConsultProgramOnlinehelpDetailService) getBean("consultProgramOnlinehelpDetailService");
        
        ConsultProgramOnlinehelpDetailDTO consultProgramOnlinehelpDetailDTO = consultProgramOnlinehelpDetailForm.getConsultProgramOnlinehelpDetailDTO();
        
        consultProgramOnlinehelpDetailDTO.setEnterBy(getUser(request).getUserId());
        
        consultProgramOnlinehelpDetailService.insertDetail(consultProgramOnlinehelpDetailDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail update
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param consultProgramOnlinehelpDetailForm
     * @param request
     */
    private void updateDetail(ConsultProgramOnlinehelpDetailForm consultProgramOnlinehelpDetailForm, HttpServletRequest request) throws Exception
    {
    	ConsultProgramOnlinehelpDetailService consultProgramOnlinehelpDetailService = (ConsultProgramOnlinehelpDetailService) getBean("consultProgramOnlinehelpDetailService");
        
        ConsultProgramOnlinehelpDetailDTO consultProgramOnlinehelpDetailDTO = consultProgramOnlinehelpDetailForm.getConsultProgramOnlinehelpDetailDTO();
        
        consultProgramOnlinehelpDetailDTO.setEnterBy(getUser(request).getUserId());
        
        consultProgramOnlinehelpDetailService.updateDetail(consultProgramOnlinehelpDetailDTO);
        
        setAjaxStatus(request);
    }
}