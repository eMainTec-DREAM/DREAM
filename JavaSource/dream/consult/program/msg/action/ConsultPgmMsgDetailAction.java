package dream.consult.program.msg.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.ConsultAuthAction;
import dream.consult.program.msg.dto.ConsultPgmMsgDetailDTO;
import dream.consult.program.msg.form.ConsultPgmMsgDetailForm;
import dream.consult.program.msg.service.ConsultPgmMsgDetailService;

/**
 * 메시지 설정(메일, SMS) - 상세 action
 *
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @struts:action path="/consultPgmMsgDetail" name="consultPgmMsgDetailForm"
 *                input="/dream/consult/program/msg/consultPgmMsgDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="consultPgmMsgDetail" path="/dream/consult/program/msg/consultPgmMsgDetail.jsp"
 *                        redirect="false"
 */
public class ConsultPgmMsgDetailAction extends ConsultAuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int DETAIL_INIT				= 1001;
    /** 저장 */
    public static final int DETAIL_INPUT			= 1002;
    /** 수정 */
    public static final int DETAIL_UPDATE			= 1003;
    /** 중복확인 */
    public static final int DETAIL_CHECK			= 1004;

    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        ConsultPgmMsgDetailForm consultPgmMsgDetailForm = (ConsultPgmMsgDetailForm) form;

        switch (consultPgmMsgDetailForm.getStrutsAction())
        {
            case ConsultPgmMsgDetailAction.DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, consultPgmMsgDetailForm);
                returnActionForward = mapping.findForward("consultPgmMsgDetail");
                break;
            case ConsultPgmMsgDetailAction.DETAIL_INPUT:
            	insertDetail(consultPgmMsgDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case ConsultPgmMsgDetailAction.DETAIL_UPDATE:
            	updateDetail(consultPgmMsgDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case ConsultPgmMsgDetailAction.DETAIL_CHECK:
            	validMsgObjType(consultPgmMsgDetailForm, request);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("consultPgmMsgDetail");
                break;
        }

        return returnActionForward;
    }

    /**
     * 메시지 설정(메일, SMS) 상세 조회
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     *
     * @param request
     * @param consultPgmMsgDetailForm
     */
    private void findDetail(HttpServletRequest request, ConsultPgmMsgDetailForm consultPgmMsgDetailForm)throws Exception
    {
        // Service 객체 생성
    	ConsultPgmMsgDetailService consultPgmMsgDetailService = (ConsultPgmMsgDetailService)getBean("consultPgmMsgDetailService");

        // 조회된 상세 부분
        ConsultPgmMsgDetailDTO consultPgmMsgDetailDTO = consultPgmMsgDetailService.findDetail(consultPgmMsgDetailForm.getConsultPgmMsgCommonDTO(), getUser(request));

        // 조회된 상세  셋팅한다.
        consultPgmMsgDetailForm.setConsultPgmMsgDetailDTO(consultPgmMsgDetailDTO);
    }
    /**
     * detail insert
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     *
     * @param consultPgmMsgDetailForm
     * @param request
     */
    private void insertDetail(ConsultPgmMsgDetailForm consultPgmMsgDetailForm, HttpServletRequest request) throws Exception
    {
        ConsultPgmMsgDetailService consultPgmMsgDetailService = (ConsultPgmMsgDetailService) getBean("consultPgmMsgDetailService");

        ConsultPgmMsgDetailDTO consultPgmMsgDetailDTO = consultPgmMsgDetailForm.getConsultPgmMsgDetailDTO();

        consultPgmMsgDetailDTO.setEnterBy(getUser(request).getUserId());

        consultPgmMsgDetailService.insertDetail(consultPgmMsgDetailDTO, getUser(request));

        setAjaxStatus(request);
    }
    /**
     * detail update
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     *
     * @param consultPgmMsgDetailForm
     * @param request
     */
    private void updateDetail(ConsultPgmMsgDetailForm consultPgmMsgDetailForm, HttpServletRequest request) throws Exception
    {
    	ConsultPgmMsgDetailService consultPgmMsgDetailService = (ConsultPgmMsgDetailService) getBean("consultPgmMsgDetailService");

        ConsultPgmMsgDetailDTO consultPgmMsgDetailDTO = consultPgmMsgDetailForm.getConsultPgmMsgDetailDTO();

        consultPgmMsgDetailDTO.setEnterBy(getUser(request).getUserId());

        consultPgmMsgDetailService.updateDetail(consultPgmMsgDetailDTO, getUser(request));


        setAjaxStatus(request);
    }
    /**
     * detail validation
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     *
     * @param consultPgmMsgDetailForm
     * @param request
     */
    private void validMsgObjType(ConsultPgmMsgDetailForm consultPgmMsgDetailForm, HttpServletRequest request) throws Exception
    {
    	ConsultPgmMsgDetailService consultPgmMsgDetailService = (ConsultPgmMsgDetailService) getBean("consultPgmMsgDetailService");
    	
    	ConsultPgmMsgDetailDTO consultPgmMsgDetailDTO = consultPgmMsgDetailForm.getConsultPgmMsgDetailDTO();
    	
    	consultPgmMsgDetailDTO.setEnterBy(getUser(request).getUserId());
    	
    	String isValid = consultPgmMsgDetailService.validMsgObjType(consultPgmMsgDetailDTO, getUser(request));
    	
    	setAjaxDesc(request, isValid);
    }
}