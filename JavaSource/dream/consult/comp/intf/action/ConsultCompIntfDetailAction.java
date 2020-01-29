package dream.consult.comp.intf.action;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.ConsultAuthAction;
import dream.consult.comp.intf.dto.ConsultCompIntfCommonDTO;
import dream.consult.comp.intf.dto.ConsultCompIntfDetailDTO;
import dream.consult.comp.intf.form.ConsultCompIntfDetailForm;
import dream.consult.comp.intf.service.ConsultCompIntfDetailService;

/**
 * Interface Page - Detail Action
 * 
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/consultCompIntfDetail" name="consultCompIntfDetailForm"
 *                input="/dream/consult/comp/intf/consultCompIntfDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="consultCompIntfDetail" path="/dream/consult/comp/intf/consultCompIntfDetail.jsp"
 *                        redirect="false"
 */
public class ConsultCompIntfDetailAction extends ConsultAuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int DETAIL_INIT 		= 8001;
    /** 저장 */
    public static final int DETAIL_INPUT 		= 5002;
    /** 수정 */
    public static final int DETAIL_UPDATE 		= 6003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        ConsultCompIntfDetailForm consultCompIntfDetailForm = (ConsultCompIntfDetailForm) form;
        
//        super.updateAudit(consultCompIntfDetailForm.getConsultCompIntfDetailDTO().getAuditKey()==""?consultCompIntfDetailForm.getConsultCompIntfCommonDTO().getAuditKey():consultCompIntfDetailForm.getConsultCompIntfDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (consultCompIntfDetailForm.getStrutsAction())
        {
            case ConsultCompIntfDetailAction.DETAIL_INIT:
                this.findDetail(request, response, consultCompIntfDetailForm);
                returnActionForward = mapping.findForward("consultCompIntfDetail");
                break;
            case ConsultCompIntfDetailAction.DETAIL_INPUT:
            	insertDetail(request, response, consultCompIntfDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case ConsultCompIntfDetailAction.DETAIL_UPDATE:
            	updateDetail(request, response, consultCompIntfDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("consultCompIntfDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * FIND DETAIL
     * @param request
     * @param response
     * @param consultCompIntfDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, ConsultCompIntfDetailForm consultCompIntfDetailForm) throws Exception 
    {   
    	ConsultCompIntfDetailService consultCompIntfDetailService = (ConsultCompIntfDetailService)getBean("consultCompIntfDetailService");
    	
    	ConsultCompIntfCommonDTO consultCompIntfCommonDTO = consultCompIntfDetailForm.getConsultCompIntfCommonDTO(); 

    	User user = getUser(request);
    	
    	ConsultCompIntfDetailDTO consultCompIntfDetailDTO = consultCompIntfDetailService.findDetail(consultCompIntfCommonDTO, user);
    	consultCompIntfDetailForm.setConsultCompIntfDetailDTO(consultCompIntfDetailDTO);
    }

    /**
     * INSERT DETAIL
     * @param request
     * @param response
     * @param consultCompIntfDetailForm
     * @throws Exception
     */
    private void insertDetail(HttpServletRequest request, HttpServletResponse response, ConsultCompIntfDetailForm consultCompIntfDetailForm) throws Exception
    {
    	ConsultCompIntfDetailService consultCompIntfDetailService = (ConsultCompIntfDetailService)getBean("consultCompIntfDetailService");
    	ConsultCompIntfDetailDTO  consultCompIntfDetailDTO = consultCompIntfDetailForm.getConsultCompIntfDetailDTO(); 
    	
    	User user = getUser(request);
    	
    	consultCompIntfDetailService.insertDetail(consultCompIntfDetailDTO, user);
        setAjaxStatus(request);
    }

    /**
     * UPDATE DETAIL
     * @param request
     * @param response
     * @param consultCompIntfDetailForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, ConsultCompIntfDetailForm consultCompIntfDetailForm) throws Exception
    {
    	ConsultCompIntfDetailService consultCompIntfDetailService = (ConsultCompIntfDetailService)getBean("consultCompIntfDetailService");
    	ConsultCompIntfDetailDTO  consultCompIntfDetailDTO = consultCompIntfDetailForm.getConsultCompIntfDetailDTO();
    	
    	User user = getUser(request);
    	
    	consultCompIntfDetailService.updateDetail(consultCompIntfDetailDTO, user);
        
        setAjaxStatus(request);
    }
}