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
 * ���� - �� action
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
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int HELP_DETAIL_INIT 			= 1001;
    /** ���� */
    public static final int HELP_DETAIL_INPUT 		= 1002;
    /** ���� */
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
                // ������ ��ȸ
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
     * ���� �� ��ȸ
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param consultProgramOnlinehelpDetailForm
     */
    private void findDetail(HttpServletRequest request, ConsultProgramOnlinehelpDetailForm consultProgramOnlinehelpDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	ConsultProgramOnlinehelpDetailService consultProgramOnlinehelpDetailService = (ConsultProgramOnlinehelpDetailService)getBean("consultProgramOnlinehelpDetailService");

        // �Ѱ��� Id ����
        String id = consultProgramOnlinehelpDetailForm.getConsultProgramOnlinehelpCommonDTO().getOnlineHelpId();
        
        // ��ȸ�� �� �κ�
        ConsultProgramOnlinehelpDetailDTO consultProgramOnlinehelpDetailDTO = consultProgramOnlinehelpDetailService.findDetail(id,getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
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