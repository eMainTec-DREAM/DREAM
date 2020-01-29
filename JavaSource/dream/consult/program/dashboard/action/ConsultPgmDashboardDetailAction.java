package dream.consult.program.dashboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.ConsultAuthAction;
import dream.consult.program.dashboard.dto.ConsultPgmDashboardDetailDTO;
import dream.consult.program.dashboard.form.ConsultPgmDashboardDetailForm;
import dream.consult.program.dashboard.service.ConsultPgmDashboardDetailService;

/**
 * ��ú��� Contents - �� action
 *
 * @author kim2107
 * @version $Id: ConsultPgmDashboardDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/consultPgmDashboardDetail" name="consultPgmDashboardDetailForm"
 *                input="/dream/consult/program/dashboard/consultPgmDashboardDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="consultPgmDashboardDetail" path="/dream/consult/program/dashboard/consultPgmDashboardDetail.jsp"
 *                        redirect="false"
 */
public class ConsultPgmDashboardDetailAction extends ConsultAuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int DETAIL_INIT				= 1001;
    /** ���� */
    public static final int DETAIL_INPUT			= 1002;
    /** ���� */
    public static final int DETAIL_UPDATE			= 1003;

    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        ConsultPgmDashboardDetailForm consultPgmDashboardDetailForm = (ConsultPgmDashboardDetailForm) form;

        switch (consultPgmDashboardDetailForm.getStrutsAction())
        {
            case ConsultPgmDashboardDetailAction.DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, consultPgmDashboardDetailForm);
                returnActionForward = mapping.findForward("consultPgmDashboardDetail");
                break;
            case ConsultPgmDashboardDetailAction.DETAIL_INPUT:
            	insertDetail(consultPgmDashboardDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case ConsultPgmDashboardDetailAction.DETAIL_UPDATE:
            	updateDetail(consultPgmDashboardDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("consultPgmDashboardDetail");
                break;
        }

        return returnActionForward;
    }

    /**
     * ȸ�缳�� �� ��ȸ
     * @author kim2107
     * @version $Id: ConsultPgmDashboardDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     *
     * @param request
     * @param consultPgmDashboardDetailForm
     */
    private void findDetail(HttpServletRequest request, ConsultPgmDashboardDetailForm consultPgmDashboardDetailForm)throws Exception
    {
        // Service ��ü ����
    	ConsultPgmDashboardDetailService consultPgmDashboardDetailService = (ConsultPgmDashboardDetailService)getBean("consultPgmDashboardDetailService");

        // ��ȸ�� �� �κ�
        ConsultPgmDashboardDetailDTO consultPgmDashboardDetailDTO = consultPgmDashboardDetailService.findDetail(consultPgmDashboardDetailForm.getConsultPgmDashboardCommonDTO(), getUser(request));

        // ��ȸ�� ��  �����Ѵ�.
        consultPgmDashboardDetailForm.setConsultPgmDashboardDetailDTO(consultPgmDashboardDetailDTO);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: ConsultPgmDashboardDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     *
     * @param consultPgmDashboardDetailForm
     * @param request
     */
    private void insertDetail(ConsultPgmDashboardDetailForm consultPgmDashboardDetailForm, HttpServletRequest request) throws Exception
    {
        ConsultPgmDashboardDetailService consultPgmDashboardDetailService = (ConsultPgmDashboardDetailService) getBean("consultPgmDashboardDetailService");

        ConsultPgmDashboardDetailDTO consultPgmDashboardDetailDTO = consultPgmDashboardDetailForm.getConsultPgmDashboardDetailDTO();

        consultPgmDashboardDetailDTO.setEnterBy(getUser(request).getUserId());

        consultPgmDashboardDetailService.insertDetail(consultPgmDashboardDetailDTO, getUser(request));

        setAjaxStatus(request);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: ConsultPgmDashboardDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     *
     * @param consultPgmDashboardDetailForm
     * @param request
     */
    private void updateDetail(ConsultPgmDashboardDetailForm consultPgmDashboardDetailForm, HttpServletRequest request) throws Exception
    {
    	ConsultPgmDashboardDetailService consultPgmDashboardDetailService = (ConsultPgmDashboardDetailService) getBean("consultPgmDashboardDetailService");

        ConsultPgmDashboardDetailDTO consultPgmDashboardDetailDTO = consultPgmDashboardDetailForm.getConsultPgmDashboardDetailDTO();

        consultPgmDashboardDetailDTO.setEnterBy(getUser(request).getUserId());

        consultPgmDashboardDetailService.updateDetail(consultPgmDashboardDetailDTO, getUser(request));


        setAjaxStatus(request);
    }
}