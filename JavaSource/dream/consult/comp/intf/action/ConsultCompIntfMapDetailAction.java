package dream.consult.comp.intf.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.ConsultAuthAction;
import dream.consult.comp.intf.dto.ConsultCompIntfCommonDTO;
import dream.consult.comp.intf.dto.ConsultCompIntfMapDetailDTO;
import dream.consult.comp.intf.dto.ConsultCompIntfMapListDTO;
import dream.consult.comp.intf.form.ConsultCompIntfMapDetailForm;
import dream.consult.comp.intf.service.ConsultCompIntfMapDetailService;

/**
 * Interface Log Page - Detail Action
 * 
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/consultCompIntfMapDetail" name="consultCompIntfMapDetailForm"
 *                input="/dream/consult/comp/intf/consultCompIntfMapDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="consultCompIntfMapDetail" path="/dream/consult/comp/intf/consultCompIntfMapDetail.jsp"
 *                        redirect="false"
 */
public class ConsultCompIntfMapDetailAction extends ConsultAuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int DETAIL_INIT 		= 1001;
    /** 저장 */
    public static final int DETAIL_INPUT 		= 5002;
    /** 수정 */
    public static final int DETAIL_UPDATE 		= 6003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        ConsultCompIntfMapDetailForm consultCompIntfMapDetailForm = (ConsultCompIntfMapDetailForm) form;
        
        switch (consultCompIntfMapDetailForm.getStrutsAction())
        {
            case ConsultCompIntfMapDetailAction.DETAIL_INIT:
                this.findDetail(request, response, consultCompIntfMapDetailForm);
                returnActionForward = mapping.findForward("consultCompIntfMapDetail");
                break;
            case ConsultCompIntfMapDetailAction.DETAIL_INPUT:
            	insertDetail(request, response, consultCompIntfMapDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case ConsultCompIntfMapDetailAction.DETAIL_UPDATE:
            	updateDetail(request, response, consultCompIntfMapDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("consultCompIntfMapDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * FIND DETAIL
     * @param request
     * @param response
     * @param consultCompIntfMapDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, ConsultCompIntfMapDetailForm consultCompIntfMapDetailForm) throws Exception 
    {   
    	ConsultCompIntfMapDetailService consultCompIntfMapDetailService = (ConsultCompIntfMapDetailService)getBean("consultCompIntfMapDetailService");
    	ConsultCompIntfCommonDTO consultCompIntfCommonDTO = consultCompIntfMapDetailForm.getConsultCompIntfCommonDTO(); 
    	ConsultCompIntfMapListDTO consultCompIntfMapListDTO = consultCompIntfMapDetailForm.getConsultCompIntfMapListDTO(); 

    	User user = getUser(request);
    	user.setCompNo(consultCompIntfCommonDTO.getCompNo());
    	
    	ConsultCompIntfMapDetailDTO consultCompIntfMapDetailDTO = consultCompIntfMapDetailService.findDetail(consultCompIntfMapListDTO, user);
    	consultCompIntfMapDetailForm.setConsultCompIntfMapDetailDTO(consultCompIntfMapDetailDTO);
    }

    private void insertDetail(HttpServletRequest request, HttpServletResponse response, ConsultCompIntfMapDetailForm consultCompIntfMapDetailForm) throws Exception
    {
    	ConsultCompIntfMapDetailService consultCompIntfMapDetailService = (ConsultCompIntfMapDetailService)getBean("consultCompIntfMapDetailService");
    	ConsultCompIntfCommonDTO consultCompIntfCommonDTO = consultCompIntfMapDetailForm.getConsultCompIntfCommonDTO(); 
    	ConsultCompIntfMapDetailDTO  consultCompIntfMapDetailDTO = consultCompIntfMapDetailForm.getConsultCompIntfMapDetailDTO(); 
    	
    	User user = getUser(request);
    	user.setCompNo(consultCompIntfCommonDTO.getCompNo());
    	
    	consultCompIntfMapDetailService.insertDetail(consultCompIntfCommonDTO,consultCompIntfMapDetailDTO, user);
        setAjaxStatus(request);
    }
    
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, ConsultCompIntfMapDetailForm consultCompIntfMapDetailForm) throws Exception
    {
    	ConsultCompIntfMapDetailService consultCompIntfMapDetailService = (ConsultCompIntfMapDetailService)getBean("consultCompIntfMapDetailService");
    	ConsultCompIntfCommonDTO consultCompIntfCommonDTO = consultCompIntfMapDetailForm.getConsultCompIntfCommonDTO(); 
    	ConsultCompIntfMapDetailDTO  consultCompIntfMapDetailDTO = consultCompIntfMapDetailForm.getConsultCompIntfMapDetailDTO();
    	
    	User user = getUser(request);
    	user.setCompNo(consultCompIntfCommonDTO.getCompNo());
    	
    	consultCompIntfMapDetailService.updateDetail(consultCompIntfCommonDTO, consultCompIntfMapDetailDTO, user);
        
        setAjaxStatus(request);
    }
}