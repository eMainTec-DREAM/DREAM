package dream.consult.comp.wrkcal.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.ConsultAuthAction;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalCommonDTO;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalDaysetDetailDTO;
import dream.consult.comp.wrkcal.form.ConsultCompWrkcalDaysetDetailForm;
import dream.consult.comp.wrkcal.service.ConsultCompWrkcalDaysetDetailService;

/**
 * �޹��� ���� - �� action
 *
 * @author kim2107
 * @version $Id: ConsultCompWrkcalDaysetDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/consultCompWrkcalDaysetDetail" name="consultCompWrkcalDaysetDetailForm"
 *                input="/dream/consult/comp/wrkcal/consultCompWrkcalDaysetDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="consultCompWrkcalDaysetDetail" path="/dream/consult/comp/wrkcal/consultCompWrkcalDaysetDetail.jsp"
 *                        redirect="false"
 */
public class ConsultCompWrkcalDaysetDetailAction extends ConsultAuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int WRKCAL_DAYSET_DETAIL_INIT 		= 1001;
    /** ���� */
    public static final int WRKCAL_DAYSET_DETAIL_INPUT 		= 1002;
    /** ���� */
    public static final int WRKCAL_DAYSET_DETAIL_UPDATE 		= 1003;

    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        ConsultCompWrkcalDaysetDetailForm consultCompWrkcalDaysetDetailForm = (ConsultCompWrkcalDaysetDetailForm) form;

        switch (consultCompWrkcalDaysetDetailForm.getStrutsAction())
        {
            case ConsultCompWrkcalDaysetDetailAction.WRKCAL_DAYSET_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, consultCompWrkcalDaysetDetailForm);
                returnActionForward = mapping.findForward("consultCompWrkcalDaysetDetail");
                break;
            case ConsultCompWrkcalDaysetDetailAction.WRKCAL_DAYSET_DETAIL_INPUT:
            	insertDetail(consultCompWrkcalDaysetDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case ConsultCompWrkcalDaysetDetailAction.WRKCAL_DAYSET_DETAIL_UPDATE:
            	updateDetail(consultCompWrkcalDaysetDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("consultCompWrkcalDaysetDetail");
                break;
        }

        return returnActionForward;
    }

    /**
     * ȸ�缳�� �� ��ȸ
     * @author kim2107
     * @version $Id: ConsultCompWrkcalDaysetDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     *
     * @param request
     * @param consultCompWrkcalDaysetDetailForm
     */
    private void findDetail(HttpServletRequest request, ConsultCompWrkcalDaysetDetailForm consultCompWrkcalDaysetDetailForm)throws Exception
    {
        // Service ��ü ����
    	ConsultCompWrkcalDaysetDetailService consultCompWrkcalDaysetDetailService = (ConsultCompWrkcalDaysetDetailService)getBean("consultCompWrkcalDaysetDetailService");
    	
    	String wrkcalDaysetId = consultCompWrkcalDaysetDetailForm.getConsultCompWrkcalDaysetListDTO().getWrkcalDaysetId();

        // ��ȸ�� �� �κ�
        ConsultCompWrkcalDaysetDetailDTO consultCompWrkcalDaysetDetailDTO = consultCompWrkcalDaysetDetailService.findDetail(wrkcalDaysetId);

        // ��ȸ�� ��  �����Ѵ�.
        consultCompWrkcalDaysetDetailForm.setConsultCompWrkcalDaysetDetailDTO(consultCompWrkcalDaysetDetailDTO);
    }
    
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: ConsultCompWrkcalDaysetDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     *
     * @param consultCompWrkcalDaysetDetailForm
     * @param request
     */
    private void insertDetail(ConsultCompWrkcalDaysetDetailForm consultCompWrkcalDaysetDetailForm, HttpServletRequest request) throws Exception
    {
        ConsultCompWrkcalDaysetDetailService consultCompWrkcalDaysetDetailService = (ConsultCompWrkcalDaysetDetailService) getBean("consultCompWrkcalDaysetDetailService");

        ConsultCompWrkcalCommonDTO consultCompWrkcalCommonDTO = consultCompWrkcalDaysetDetailForm.getConsultCompWrkcalCommonDTO();
        ConsultCompWrkcalDaysetDetailDTO consultCompWrkcalDaysetDetailDTO = consultCompWrkcalDaysetDetailForm.getConsultCompWrkcalDaysetDetailDTO();

        consultCompWrkcalDaysetDetailService.insertDetail(consultCompWrkcalDaysetDetailDTO, consultCompWrkcalCommonDTO);

        setAjaxStatus(request);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: ConsultCompWrkcalDaysetDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     *
     * @param consultCompWrkcalDaysetDetailForm
     * @param request
     */
    private void updateDetail(ConsultCompWrkcalDaysetDetailForm consultCompWrkcalDaysetDetailForm, HttpServletRequest request) throws Exception
    {
    	ConsultCompWrkcalDaysetDetailService consultCompWrkcalDaysetDetailService = (ConsultCompWrkcalDaysetDetailService) getBean("consultCompWrkcalDaysetDetailService");

        ConsultCompWrkcalDaysetDetailDTO consultCompWrkcalDaysetDetailDTO = consultCompWrkcalDaysetDetailForm.getConsultCompWrkcalDaysetDetailDTO();

        consultCompWrkcalDaysetDetailService.updateDetail(consultCompWrkcalDaysetDetailDTO);

        setAjaxStatus(request);
    }
}