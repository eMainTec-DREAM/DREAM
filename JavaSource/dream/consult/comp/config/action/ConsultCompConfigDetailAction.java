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
 * �ý��� ȯ�溯�� - �� action
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
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int CONFIG_DETAIL_INIT 		= 1001;
    /** ���� */
    public static final int CONFIG_DETAIL_UPDATE 	= 1002;
    /** ���� */
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
                // ������ ��ȸ
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
     * �ý���ȯ�溯�� �� ��ȸ
     * @author kim2107
     * @version $Id: ConsultCompConfigDetailAction.java,v 1.2 2015/12/02 01:21:30 syyang Exp $
     * @since   1.0
     * 
     * @param request
     * @param consultCompConfigDetailForm
     */
    private void findDetail(HttpServletRequest request, ConsultCompConfigDetailForm consultCompConfigDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	ConsultCompConfigDetailService consultCompConfigDetailService = (ConsultCompConfigDetailService)getBean("consultCompConfigDetailService");

    	ConsultCompConfigCommonDTO consultCompConfigCommonDTO = consultCompConfigDetailForm.getConsultCompConfigCommonDTO();
    	
        // ��ȸ�� �� �κ�
        ConsultCompConfigDetailDTO consultCompConfigDetailDTO = consultCompConfigDetailService.findDetail(consultCompConfigCommonDTO, getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
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