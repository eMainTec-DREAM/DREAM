package dream.consult.comp.wrkcal.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.ConsultAuthAction;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalCommonDTO;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalDowsetDetailDTO;
import dream.consult.comp.wrkcal.form.ConsultCompWrkcalDowsetDetailForm;
import dream.consult.comp.wrkcal.service.ConsultCompWrkcalDowsetDetailService;

/**
 * ȸ�缳�� - �� action
 *
 * @author kim2107
 * @version $Id: ConsultCompWrkcalDowsetDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/consultCompWrkcalDowsetDetail" name="consultCompWrkcalDowsetDetailForm"
 *                input="/dream/consult/comp/wrkcal/consultCompWrkcalDowsetDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="consultCompWrkcalDowsetDetail" path="/dream/consult/comp/wrkcal/consultCompWrkcalDowsetDetail.jsp"
 *                        redirect="false"
 */
public class ConsultCompWrkcalDowsetDetailAction extends ConsultAuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int WRKCAL_DOWSET_DETAIL_INIT 		= 1001;
    /** ���� */
    public static final int WRKCAL_DOWSET_DETAIL_INPUT 		= 1002;
    /** ���� */
    public static final int WRKCAL_DOWSET_DETAIL_UPDATE 		= 1003;

    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        ConsultCompWrkcalDowsetDetailForm consultCompWrkcalDowsetDetailForm = (ConsultCompWrkcalDowsetDetailForm) form;

        switch (consultCompWrkcalDowsetDetailForm.getStrutsAction())
        {
            case ConsultCompWrkcalDowsetDetailAction.WRKCAL_DOWSET_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, consultCompWrkcalDowsetDetailForm);
                returnActionForward = mapping.findForward("consultCompWrkcalDowsetDetail");
                break;
            case ConsultCompWrkcalDowsetDetailAction.WRKCAL_DOWSET_DETAIL_INPUT:
            	insertDetail(consultCompWrkcalDowsetDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case ConsultCompWrkcalDowsetDetailAction.WRKCAL_DOWSET_DETAIL_UPDATE:
            	updateDetail(consultCompWrkcalDowsetDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("consultCompWrkcalDowsetDetail");
                break;
        }

        return returnActionForward;
    }

    /**
     * ȸ�缳�� �� ��ȸ
     * @author kim2107
     * @version $Id: ConsultCompWrkcalDowsetDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     *
     * @param request
     * @param consultCompWrkcalDowsetDetailForm
     */
    private void findDetail(HttpServletRequest request, ConsultCompWrkcalDowsetDetailForm consultCompWrkcalDowsetDetailForm)throws Exception
    {
        // Service ��ü ����
    	ConsultCompWrkcalDowsetDetailService consultCompWrkcalDowsetDetailService = (ConsultCompWrkcalDowsetDetailService)getBean("consultCompWrkcalDowsetDetailService");
    	
    	String wrkcalDowsetId = consultCompWrkcalDowsetDetailForm.getConsultCompWrkcalDowsetListDTO().getWrkcalDowsetId();
    	String lang = getUser(request).getLang();

        // ��ȸ�� �� �κ�
        ConsultCompWrkcalDowsetDetailDTO consultCompWrkcalDowsetDetailDTO = consultCompWrkcalDowsetDetailService.findDetail(wrkcalDowsetId, lang);

        // ��ȸ�� ��  �����Ѵ�.
        consultCompWrkcalDowsetDetailForm.setConsultCompWrkcalDowsetDetailDTO(consultCompWrkcalDowsetDetailDTO);
    }
    
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: ConsultCompWrkcalDowsetDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     *
     * @param consultCompWrkcalDowsetDetailForm
     * @param request
     */
    private void insertDetail(ConsultCompWrkcalDowsetDetailForm consultCompWrkcalDowsetDetailForm, HttpServletRequest request) throws Exception
    {
        ConsultCompWrkcalDowsetDetailService consultCompWrkcalDowsetDetailService = (ConsultCompWrkcalDowsetDetailService) getBean("consultCompWrkcalDowsetDetailService");

        ConsultCompWrkcalCommonDTO consultCompWrkcalCommonDTO = consultCompWrkcalDowsetDetailForm.getConsultCompWrkcalCommonDTO();
        ConsultCompWrkcalDowsetDetailDTO consultCompWrkcalDowsetDetailDTO = consultCompWrkcalDowsetDetailForm.getConsultCompWrkcalDowsetDetailDTO();

        consultCompWrkcalDowsetDetailService.insertDetail(consultCompWrkcalDowsetDetailDTO, consultCompWrkcalCommonDTO);

        setAjaxStatus(request);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: ConsultCompWrkcalDowsetDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     *
     * @param consultCompWrkcalDowsetDetailForm
     * @param request
     */
    private void updateDetail(ConsultCompWrkcalDowsetDetailForm consultCompWrkcalDowsetDetailForm, HttpServletRequest request) throws Exception
    {
    	ConsultCompWrkcalDowsetDetailService consultCompWrkcalDowsetDetailService = (ConsultCompWrkcalDowsetDetailService) getBean("consultCompWrkcalDowsetDetailService");

        ConsultCompWrkcalDowsetDetailDTO consultCompWrkcalDowsetDetailDTO = consultCompWrkcalDowsetDetailForm.getConsultCompWrkcalDowsetDetailDTO();

        consultCompWrkcalDowsetDetailService.updateDetail(consultCompWrkcalDowsetDetailDTO);

        setAjaxStatus(request);
    }
}