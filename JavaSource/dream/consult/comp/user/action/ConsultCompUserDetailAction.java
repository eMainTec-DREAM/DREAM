package dream.consult.comp.user.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.ConsultAuthAction;
import dream.consult.comp.user.dto.ConsultCompUserCommonDTO;
import dream.consult.comp.user.dto.ConsultCompUserDetailDTO;
import dream.consult.comp.user.form.ConsultCompUserDetailForm;
import dream.consult.comp.user.service.ConsultCompUserDetailService;

/**
 * CompUser Page - Detail Action
 * 
 * @author youngjoo38
 * @version $Id: ConsultCompUserDetailAction.java,v 1.0 2017/08/10 09:12:40 youngjoo38 Exp $
 * @since 1.0
 * @struts:action path="/consultCompUserDetail" name="consultCompUserDetailForm"
 *                input="/dream/consult/comp/user/consultCompUserDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="consultCompUserDetail" path="/dream/consult/comp/user/consultCompUserDetail.jsp"
 *                        redirect="false"
 */
public class ConsultCompUserDetailAction extends ConsultAuthAction
{
  //�Ϲ� ������ ����� AuthAction ���� �����ؾ��մϴ�.
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int DETAIL_INIT         = 1001;
    /** ���� */
    public static final int DETAIL_INPUT        = 1002;
    /** ���� */
    public static final int DETAIL_UPDATE       = 1003;
    /** �ߺ�üũ */
    public static final int DETAIL_CHECK	    = 1004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        ConsultCompUserDetailForm consultCompUserDetailForm = (ConsultCompUserDetailForm) form;
        
        switch (consultCompUserDetailForm.getStrutsAction())
        {
            case ConsultCompUserDetailAction.DETAIL_INIT:
                this.findDetail(request, response, consultCompUserDetailForm);
                returnActionForward = mapping.findForward("consultCompUserDetail");
                break;
            case ConsultCompUserDetailAction.DETAIL_INPUT:
                insertDetail(request, response, consultCompUserDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case ConsultCompUserDetailAction.DETAIL_UPDATE:
                updateDetail(request, response, consultCompUserDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case ConsultCompUserDetailAction.DETAIL_CHECK:
            	valUserNo(request, response, consultCompUserDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            default:
                returnActionForward = mapping.findForward("consultCompUserDetail");
                break;
        }

        return returnActionForward;
    }

	/**
     * FIND DETAIL
     * @param request
     * @param response
     * @param consultCompUserDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, ConsultCompUserDetailForm consultCompUserDetailForm) throws Exception 
    {   
        ConsultCompUserDetailService consultCompUserDetailService = (ConsultCompUserDetailService)getBean("consultCompUserDetailService");
        
        ConsultCompUserCommonDTO consultCompUserCommonDTO = consultCompUserDetailForm.getConsultCompUserCommonDTO(); 

        ConsultCompUserDetailDTO consultCompUserDetailDTO = consultCompUserDetailService.findCompUserDetail(consultCompUserCommonDTO, getUser(request));
        consultCompUserDetailForm.setConsultCompUserDetailDTO(consultCompUserDetailDTO);
    }

    /**
     * INSERT DETAIL
     * @param request
     * @param response
     * @param consultCompUserDetailForm
     * @throws Exception
     */
    private void insertDetail(HttpServletRequest request, HttpServletResponse response, ConsultCompUserDetailForm consultCompUserDetailForm) throws Exception
    {
        ConsultCompUserDetailService consultCompUserDetailService = (ConsultCompUserDetailService)getBean("consultCompUserDetailService");
        ConsultCompUserDetailDTO  consultCompUserDetailDTO = consultCompUserDetailForm.getConsultCompUserDetailDTO(); 
        
        consultCompUserDetailService.insertCompUserDetail(consultCompUserDetailDTO, getUser(request));
        setAjaxStatus(request);
    }

    /**
     * UPDATE DETAIL
     * @param request
     * @param response
     * @param consultCompUserDetailForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, ConsultCompUserDetailForm consultCompUserDetailForm) throws Exception
    {
        ConsultCompUserDetailService consultCompUserDetailService = (ConsultCompUserDetailService)getBean("consultCompUserDetailService");
        ConsultCompUserDetailDTO  consultCompUserDetailDTO = consultCompUserDetailForm.getConsultCompUserDetailDTO();
        
        consultCompUserDetailService.updateCompUserDetail(consultCompUserDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }

    
    private void valUserNo(HttpServletRequest request, HttpServletResponse response, ConsultCompUserDetailForm consultCompUserDetailForm) throws Exception {
        ConsultCompUserDetailService consultCompUserDetailService = (ConsultCompUserDetailService)getBean("consultCompUserDetailService");
        ConsultCompUserDetailDTO  consultCompUserDetailDTO = consultCompUserDetailForm.getConsultCompUserDetailDTO();
        ConsultCompUserCommonDTO consultCompUserCommonDTO = consultCompUserDetailForm.getConsultCompUserCommonDTO();
                
        String isValid = consultCompUserDetailService.valUserNo(consultCompUserCommonDTO, consultCompUserDetailDTO);
        
        setAjaxDesc(request, isValid);
        
    }
}
