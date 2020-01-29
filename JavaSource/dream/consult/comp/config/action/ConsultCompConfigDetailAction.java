package dream.consult.comp.config.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.ConsultAuthAction;
import dream.consult.comp.config.dto.ConsultCompConfigCommonDTO;
import dream.consult.comp.config.dto.ConsultCompConfigDetailDTO;
import dream.consult.comp.config.form.ConsultCompConfigDetailForm;
import dream.consult.comp.config.service.ConsultCompConfigDetailService;

/**
 * 시스템 환경변수 - 상세 action
 * 
 * @author kim2107
 * @version $Id: ConsultCompConfigDetailAction.java,v 1.2 2015/12/02 01:21:30 syyang Exp $
 * @since 1.0
 * @struts:action path="/consultCompConfigDetail" name="consultCompConfigDetailForm"
 *                input="/dream/consult/comp/config/consultCompConfigDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="consultCompConfigDetail" path="/dream/consult/comp/config/consultCompConfigDetail.jsp"
 *                        redirect="false"
 */
public class ConsultCompConfigDetailAction extends ConsultAuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int CONFIG_DETAIL_INIT 		= 1001;
    /** 수정 */
    public static final int CONFIG_DETAIL_UPDATE 	= 1002;
    /** 수정 */
    public static final int CONFIG_DETAIL_INPUT 	= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        ConsultCompConfigDetailForm consultCompConfigDetailForm = (ConsultCompConfigDetailForm) form;
        
        switch (consultCompConfigDetailForm.getStrutsAction())
        {
            case ConsultCompConfigDetailAction.CONFIG_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, consultCompConfigDetailForm);
                returnActionForward = mapping.findForward("consultCompConfigDetail");
                break;
            case ConsultCompConfigDetailAction.CONFIG_DETAIL_UPDATE:
            	updateDetail(consultCompConfigDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case ConsultCompConfigDetailAction.CONFIG_DETAIL_INPUT:
            	insertDetail(consultCompConfigDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("consultCompConfigDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 시스템환경변수 상세 조회
     * @author kim2107
     * @version $Id: ConsultCompConfigDetailAction.java,v 1.2 2015/12/02 01:21:30 syyang Exp $
     * @since   1.0
     * 
     * @param request
     * @param consultCompConfigDetailForm
     */
    private void findDetail(HttpServletRequest request, ConsultCompConfigDetailForm consultCompConfigDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	ConsultCompConfigDetailService consultCompConfigDetailService = (ConsultCompConfigDetailService)getBean("consultCompConfigDetailService");

    	ConsultCompConfigCommonDTO consultCompConfigCommonDTO = consultCompConfigDetailForm.getConsultCompConfigCommonDTO();
    	
        // 조회된 상세 부분
        ConsultCompConfigDetailDTO consultCompConfigDetailDTO = consultCompConfigDetailService.findDetail(consultCompConfigCommonDTO, getUser(request));
        
        // 조회된 상세  셋팅한다.
        consultCompConfigDetailForm.setConsultCompConfigDetailDTO(consultCompConfigDetailDTO);
    }
    /**
     * detail update
     * @author  syyang
     * @version $Id: ConsultCompConfigDetailAction.java,v 1.2 2015/12/02 01:21:30 syyang Exp $
     * @since   1.0
     * 
     * @param consultCompConfigDetailForm
     * @param request
     */
    private void updateDetail(ConsultCompConfigDetailForm consultCompConfigDetailForm, HttpServletRequest request) throws Exception
    {
    	ConsultCompConfigDetailService consultCompConfigDetailService = (ConsultCompConfigDetailService) getBean("consultCompConfigDetailService");
        
        ConsultCompConfigDetailDTO consultCompConfigDetailDTO = consultCompConfigDetailForm.getConsultCompConfigDetailDTO();
        
        consultCompConfigDetailService.updateDetail(consultCompConfigDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  syyang
     * @version $Id: ConsultCompConfigDetailAction.java,v 1.2 2015/12/02 01:21:30 syyang Exp $
     * @since   1.0
     * 
     * @param consultCompConfigDetailForm
     * @param request
     */
    private void insertDetail(ConsultCompConfigDetailForm consultCompConfigDetailForm, HttpServletRequest request) throws Exception
    {
        ConsultCompConfigDetailService consultCompConfigDetailService = (ConsultCompConfigDetailService) getBean("consultCompConfigDetailService");
        
        ConsultCompConfigDetailDTO consultCompConfigDetailDTO = consultCompConfigDetailForm.getConsultCompConfigDetailDTO();
        
        consultCompConfigDetailService.insertDetail(consultCompConfigDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
}