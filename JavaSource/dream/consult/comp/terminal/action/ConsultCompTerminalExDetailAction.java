package dream.consult.comp.terminal.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.ConsultAuthAction;
import dream.consult.comp.terminal.dto.ConsultCompTerminalExDetailDTO;
import dream.consult.comp.terminal.form.ConsultCompTerminalExDetailForm;
import dream.consult.comp.terminal.service.ConsultCompTerminalExDetailService;

/**
 * 접근터미널 - 상세 action
 *
 * @author kim2107
 * @version $Id: ConsultCompTerminalExDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/consultCompTerminalExDetail" name="consultCompTerminalExDetailForm"
 *                input="/dream/consult/comp/terminal/consultCompTerminalExDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="consultCompTerminalExDetail" path="/dream/consult/comp/terminal/consultCompTerminalExDetail.jsp"
 *                        redirect="false"
 */
public class ConsultCompTerminalExDetailAction extends ConsultAuthAction
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
        ConsultCompTerminalExDetailForm consultCompTerminalExDetailForm = (ConsultCompTerminalExDetailForm) form;

        switch (consultCompTerminalExDetailForm.getStrutsAction())
        {
            case ConsultCompTerminalExDetailAction.TERMINAL_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, consultCompTerminalExDetailForm);
                returnActionForward = mapping.findForward("consultCompTerminalExDetail");
                break;
            case ConsultCompTerminalExDetailAction.TERMINAL_DETAIL_INPUT:
            	insertDetail(consultCompTerminalExDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case ConsultCompTerminalExDetailAction.TERMINAL_DETAIL_UPDATE:
            	updateDetail(consultCompTerminalExDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("consultCompTerminalExDetail");
                break;
        }

        return returnActionForward;
    }

    /**
     * 회사설정 상세 조회
     * @author kim2107
     * @version $Id: ConsultCompTerminalExDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     *
     * @param request
     * @param consultCompTerminalExDetailForm
     */
    private void findDetail(HttpServletRequest request, ConsultCompTerminalExDetailForm consultCompTerminalExDetailForm)throws Exception
    {
        // Service 객체 생성
    	ConsultCompTerminalExDetailService consultCompTerminalExDetailService = (ConsultCompTerminalExDetailService)getBean("consultCompTerminalExDetailService");

        // 조회된 상세 부분
        ConsultCompTerminalExDetailDTO consultCompTerminalExDetailDTO = consultCompTerminalExDetailService.findDetail(consultCompTerminalExDetailForm.getConsultCompTerminalExCommonDTO(), getUser(request));

        // 조회된 상세  셋팅한다.
        consultCompTerminalExDetailForm.setConsultCompTerminalExDetailDTO(consultCompTerminalExDetailDTO);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: ConsultCompTerminalExDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     *
     * @param consultCompTerminalExDetailForm
     * @param request
     */
    private void insertDetail(ConsultCompTerminalExDetailForm consultCompTerminalExDetailForm, HttpServletRequest request) throws Exception
    {
        ConsultCompTerminalExDetailService consultCompTerminalExDetailService = (ConsultCompTerminalExDetailService) getBean("consultCompTerminalExDetailService");

        ConsultCompTerminalExDetailDTO consultCompTerminalExDetailDTO = consultCompTerminalExDetailForm.getConsultCompTerminalExDetailDTO();

        consultCompTerminalExDetailDTO.setEnterBy(getUser(request).getUserId());

        consultCompTerminalExDetailService.insertDetail(consultCompTerminalExDetailDTO, getUser(request));

        setAjaxStatus(request);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: ConsultCompTerminalExDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     *
     * @param consultCompTerminalExDetailForm
     * @param request
     */
    private void updateDetail(ConsultCompTerminalExDetailForm consultCompTerminalExDetailForm, HttpServletRequest request) throws Exception
    {
    	ConsultCompTerminalExDetailService consultCompTerminalExDetailService = (ConsultCompTerminalExDetailService) getBean("consultCompTerminalExDetailService");

        ConsultCompTerminalExDetailDTO consultCompTerminalExDetailDTO = consultCompTerminalExDetailForm.getConsultCompTerminalExDetailDTO();

        consultCompTerminalExDetailDTO.setEnterBy(getUser(request).getUserId());

        consultCompTerminalExDetailService.updateDetail(consultCompTerminalExDetailDTO, getUser(request));


        setAjaxStatus(request);
    }
}