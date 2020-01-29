package dream.consult.comp.wrkcal.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.ConsultAuthAction;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalDetailDTO;
import dream.consult.comp.wrkcal.form.ConsultCompWrkcalDetailForm;
import dream.consult.comp.wrkcal.service.ConsultCompWrkcalDetailService;

/**
 * 회사설정 - 상세 action
 *
 * @author kim2107
 * @version $Id: ConsultCompWrkcalDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/consultCompWrkcalDetail" name="consultCompWrkcalDetailForm"
 *                input="/dream/consult/comp/wrkcal/consultCompWrkcalDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="consultCompWrkcalDetail" path="/dream/consult/comp/wrkcal/consultCompWrkcalDetail.jsp"
 *                        redirect="false"
 */
public class ConsultCompWrkcalDetailAction extends ConsultAuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int WRKCAL_DETAIL_INIT 		= 1001;
    /** 저장 */
    public static final int WRKCAL_DETAIL_INPUT 		= 1002;
    /** 수정 */
    public static final int WRKCAL_DETAIL_UPDATE 		= 1003;

    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        ConsultCompWrkcalDetailForm consultCompWrkcalDetailForm = (ConsultCompWrkcalDetailForm) form;

        switch (consultCompWrkcalDetailForm.getStrutsAction())
        {
            case ConsultCompWrkcalDetailAction.WRKCAL_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, consultCompWrkcalDetailForm);
                returnActionForward = mapping.findForward("consultCompWrkcalDetail");
                break;
            case ConsultCompWrkcalDetailAction.WRKCAL_DETAIL_INPUT:
            	insertDetail(consultCompWrkcalDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case ConsultCompWrkcalDetailAction.WRKCAL_DETAIL_UPDATE:
            	updateDetail(consultCompWrkcalDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("consultCompWrkcalDetail");
                break;
        }

        return returnActionForward;
    }

    /**
     * 회사설정 상세 조회
     * @author kim2107
     * @version $Id: ConsultCompWrkcalDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     *
     * @param request
     * @param consultCompWrkcalDetailForm
     */
    private void findDetail(HttpServletRequest request, ConsultCompWrkcalDetailForm consultCompWrkcalDetailForm)throws Exception
    {
        // Service 객체 생성
    	ConsultCompWrkcalDetailService consultCompWrkcalDetailService = (ConsultCompWrkcalDetailService)getBean("consultCompWrkcalDetailService");

        // 조회된 상세 부분
        ConsultCompWrkcalDetailDTO consultCompWrkcalDetailDTO = consultCompWrkcalDetailService.findDetail(consultCompWrkcalDetailForm.getConsultCompWrkcalCommonDTO(), getUser(request));

        // 조회된 상세  셋팅한다.
        consultCompWrkcalDetailForm.setConsultCompWrkcalDetailDTO(consultCompWrkcalDetailDTO);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: ConsultCompWrkcalDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     *
     * @param consultCompWrkcalDetailForm
     * @param request
     */
    private void insertDetail(ConsultCompWrkcalDetailForm consultCompWrkcalDetailForm, HttpServletRequest request) throws Exception
    {
        ConsultCompWrkcalDetailService consultCompWrkcalDetailService = (ConsultCompWrkcalDetailService) getBean("consultCompWrkcalDetailService");

        ConsultCompWrkcalDetailDTO consultCompWrkcalDetailDTO = consultCompWrkcalDetailForm.getConsultCompWrkcalDetailDTO();

        consultCompWrkcalDetailService.insertDetail(consultCompWrkcalDetailDTO, getUser(request));

        setAjaxStatus(request);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: ConsultCompWrkcalDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     *
     * @param consultCompWrkcalDetailForm
     * @param request
     */
    private void updateDetail(ConsultCompWrkcalDetailForm consultCompWrkcalDetailForm, HttpServletRequest request) throws Exception
    {
    	ConsultCompWrkcalDetailService consultCompWrkcalDetailService = (ConsultCompWrkcalDetailService) getBean("consultCompWrkcalDetailService");

        ConsultCompWrkcalDetailDTO consultCompWrkcalDetailDTO = consultCompWrkcalDetailForm.getConsultCompWrkcalDetailDTO();

        consultCompWrkcalDetailService.updateDetail(consultCompWrkcalDetailDTO, getUser(request));


        setAjaxStatus(request);
    }
}