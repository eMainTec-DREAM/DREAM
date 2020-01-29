package dream.consult.program.guide.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.ConsultAuthAction;
import dream.consult.program.guide.dto.ConsultPgmGuideCommonDTO;
import dream.consult.program.guide.dto.ConsultPgmGuideDetailDTO;
import dream.consult.program.guide.form.ConsultPgmGuideDetailForm;
import dream.consult.program.guide.service.ConsultPgmGuideDetailService;

/**
 * Guide Page - Detail Action
 * 
 * @author kim21017
 * @version $Id: ConsultPgmGuideDetailAction.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/consultPgmGuideDetail" name="consultPgmGuideDetailForm"
 *                input="/dream/consult/program/guide/consultPgmGuideDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="consultPgmGuideDetail" path="/dream/consult/program/guide/consultPgmGuideDetail.jsp"
 *                        redirect="false"
 */
public class ConsultPgmGuideDetailAction extends ConsultAuthAction
{
	//�Ϲ� ������ ����� AuthAction ���� �����ؾ��մϴ�.
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int DETAIL_INIT 		= 1001;
    /** ���� */
    public static final int DETAIL_INPUT 		= 1002;
    /** ���� */
    public static final int DETAIL_UPDATE 		= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        ConsultPgmGuideDetailForm consultPgmGuideDetailForm = (ConsultPgmGuideDetailForm) form;
        
        switch (consultPgmGuideDetailForm.getStrutsAction())
        {
            case ConsultPgmGuideDetailAction.DETAIL_INIT:
                this.findDetail(request, response, consultPgmGuideDetailForm);
                returnActionForward = mapping.findForward("consultPgmGuideDetail");
                break;
            case ConsultPgmGuideDetailAction.DETAIL_INPUT:
            	insertDetail(request, response, consultPgmGuideDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case ConsultPgmGuideDetailAction.DETAIL_UPDATE:
            	updateDetail(request, response, consultPgmGuideDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("consultPgmGuideDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * FIND DETAIL
     * @param request
     * @param response
     * @param consultPgmGuideDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, ConsultPgmGuideDetailForm consultPgmGuideDetailForm) throws Exception 
    {   
    	ConsultPgmGuideDetailService consultPgmGuideDetailService = (ConsultPgmGuideDetailService)getBean("consultPgmGuideDetailService");
    	
    	ConsultPgmGuideCommonDTO consultPgmGuideCommonDTO = consultPgmGuideDetailForm.getConsultPgmGuideCommonDTO(); 

    	//����Ʈ ���������� user��ü�� ȸ���ڵ尡 ��������ʽ��ϴ�. �� �������� ������ �������̱� ������ ���⿡�� ȸ���ڵ带 ���Ƿ� �����߽��ϴ�.
    	User user = getUser(request);
    	user.setCompNo("100");
    	
    	ConsultPgmGuideDetailDTO consultPgmGuideDetailDTO = consultPgmGuideDetailService.findPgmGuideDetail(consultPgmGuideCommonDTO, user);
    	consultPgmGuideDetailForm.setConsultPgmGuideDetailDTO(consultPgmGuideDetailDTO);
    }

    /**
     * INSERT DETAIL
     * @param request
     * @param response
     * @param consultPgmGuideDetailForm
     * @throws Exception
     */
    private void insertDetail(HttpServletRequest request, HttpServletResponse response, ConsultPgmGuideDetailForm consultPgmGuideDetailForm) throws Exception
    {
    	ConsultPgmGuideDetailService consultPgmGuideDetailService = (ConsultPgmGuideDetailService)getBean("consultPgmGuideDetailService");
    	ConsultPgmGuideDetailDTO  consultPgmGuideDetailDTO = consultPgmGuideDetailForm.getConsultPgmGuideDetailDTO(); 
    	
    	//����Ʈ ���������� user��ü�� ȸ���ڵ尡 ��������ʽ��ϴ�. �� �������� ������ �������̱� ������ ���⿡�� ȸ���ڵ带 ���Ƿ� �����߽��ϴ�.
    	User user = getUser(request);
    	user.setCompNo("100");
    	
    	consultPgmGuideDetailService.insertPgmGuideDetail(consultPgmGuideDetailDTO, user);
        setAjaxStatus(request);
    }

    /**
     * UPDATE DETAIL
     * @param request
     * @param response
     * @param consultPgmGuideDetailForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, ConsultPgmGuideDetailForm consultPgmGuideDetailForm) throws Exception
    {
    	ConsultPgmGuideDetailService consultPgmGuideDetailService = (ConsultPgmGuideDetailService)getBean("consultPgmGuideDetailService");
    	ConsultPgmGuideDetailDTO  consultPgmGuideDetailDTO = consultPgmGuideDetailForm.getConsultPgmGuideDetailDTO();
    	
    	//����Ʈ ���������� user��ü�� ȸ���ڵ尡 ��������ʽ��ϴ�. �� �������� ������ �������̱� ������ ���⿡�� ȸ���ڵ带 ���Ƿ� �����߽��ϴ�.
    	User user = getUser(request);
    	user.setCompNo("100");
    	
    	consultPgmGuideDetailService.updatePgmGuideDetail(consultPgmGuideDetailDTO, user);
        
        setAjaxStatus(request);
    }

}