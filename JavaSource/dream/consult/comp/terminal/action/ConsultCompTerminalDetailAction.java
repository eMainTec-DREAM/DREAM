package dream.consult.comp.terminal.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.ConsultAuthAction;
import dream.consult.comp.terminal.dto.ConsultCompTerminalDetailDTO;
import dream.consult.comp.terminal.form.ConsultCompTerminalDetailForm;
import dream.consult.comp.terminal.service.ConsultCompTerminalDetailService;

/**
 * 접근터미널 - 상세 action
 *
 * @author kim2107
 * @version $Id: ConsultCompTerminalDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/consultCompTerminalDetail" name="consultCompTerminalDetailForm"
 *                input="/dream/consult/comp/terminal/consultCompTerminalDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="consultCompTerminalDetail" path="/dream/consult/comp/terminal/consultCompTerminalDetail.jsp"
 *                        redirect="false"
 */
public class ConsultCompTerminalDetailAction extends ConsultAuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int TERMINAL_DETAIL_INIT 		= 1001;
    /** 저장 */
    public static final int TERMINAL_DETAIL_INPUT 		= 1002;
    /** 수정 */
    public static final int TERMINAL_DETAIL_UPDATE 		= 1003;

    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        ConsultCompTerminalDetailForm consultCompTerminalDetailForm = (ConsultCompTerminalDetailForm) form;

        switch (consultCompTerminalDetailForm.getStrutsAction())
        {
            case ConsultCompTerminalDetailAction.TERMINAL_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, consultCompTerminalDetailForm);
                returnActionForward = mapping.findForward("consultCompTerminalDetail");
                break;
            case ConsultCompTerminalDetailAction.TERMINAL_DETAIL_INPUT:
            	insertDetail(consultCompTerminalDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case ConsultCompTerminalDetailAction.TERMINAL_DETAIL_UPDATE:
            	updateDetail(consultCompTerminalDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("consultCompTerminalDetail");
                break;
        }

        return returnActionForward;
    }

    /**
     * 회사설정 상세 조회
     * @author kim2107
     * @version $Id: ConsultCompTerminalDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     *
     * @param request
     * @param consultCompTerminalDetailForm
     */
    private void findDetail(HttpServletRequest request, ConsultCompTerminalDetailForm consultCompTerminalDetailForm)throws Exception
    {
        // Service 객체 생성
    	ConsultCompTerminalDetailService consultCompTerminalDetailService = (ConsultCompTerminalDetailService)getBean("consultCompTerminalDetailService");

        // 조회된 상세 부분
        ConsultCompTerminalDetailDTO consultCompTerminalDetailDTO = consultCompTerminalDetailService.findDetail(consultCompTerminalDetailForm.getConsultCompTerminalCommonDTO(), getUser(request));

        // 조회된 상세  셋팅한다.
        consultCompTerminalDetailForm.setConsultCompTerminalDetailDTO(consultCompTerminalDetailDTO);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: ConsultCompTerminalDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     *
     * @param consultCompTerminalDetailForm
     * @param request
     */
    private void insertDetail(ConsultCompTerminalDetailForm consultCompTerminalDetailForm, HttpServletRequest request) throws Exception
    {
        ConsultCompTerminalDetailService consultCompTerminalDetailService = (ConsultCompTerminalDetailService) getBean("consultCompTerminalDetailService");

        ConsultCompTerminalDetailDTO consultCompTerminalDetailDTO = consultCompTerminalDetailForm.getConsultCompTerminalDetailDTO();

        consultCompTerminalDetailDTO.setEnterBy(getUser(request).getUserId());

        consultCompTerminalDetailService.insertDetail(consultCompTerminalDetailDTO, getUser(request));

        setAjaxStatus(request);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: ConsultCompTerminalDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     *
     * @param consultCompTerminalDetailForm
     * @param request
     */
    private void updateDetail(ConsultCompTerminalDetailForm consultCompTerminalDetailForm, HttpServletRequest request) throws Exception
    {
    	ConsultCompTerminalDetailService consultCompTerminalDetailService = (ConsultCompTerminalDetailService) getBean("consultCompTerminalDetailService");

        ConsultCompTerminalDetailDTO consultCompTerminalDetailDTO = consultCompTerminalDetailForm.getConsultCompTerminalDetailDTO();

        consultCompTerminalDetailDTO.setEnterBy(getUser(request).getUserId());

        consultCompTerminalDetailService.updateDetail(consultCompTerminalDetailDTO, getUser(request));


        setAjaxStatus(request);
    }
}