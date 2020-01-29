package dream.consult.program.linkedfunc.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.ConsultAuthAction;
import dream.consult.program.linkedfunc.dto.ConsultPgmLinkedFuncCommonDTO;
import dream.consult.program.linkedfunc.dto.ConsultPgmLinkedFuncDetailDTO;
import dream.consult.program.linkedfunc.form.ConsultPgmLinkedFuncDetailForm;
import dream.consult.program.linkedfunc.service.ConsultPgmLinkedFuncDetailService;

/**
 * PgmLinkedFunc Page - Detail Action
 * 
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @struts:action path="/consultPgmLinkedFuncDetail" name="consultPgmLinkedFuncDetailForm"
 *                input="/dream/consult/program/linkedfunc/consultPgmLinkedFuncDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="consultPgmLinkedFuncDetail" path="/dream/consult/program/linkedfunc/consultPgmLinkedFuncDetail.jsp"
 *                        redirect="false"
 */
public class ConsultPgmLinkedFuncDetailAction extends ConsultAuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int DETAIL_INIT         = 1001;
    /** 저장 */
    public static final int DETAIL_INPUT        = 1002;
    /** 수정 */
    public static final int DETAIL_UPDATE       = 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        ConsultPgmLinkedFuncDetailForm consultPgmLinkedFuncDetailForm = (ConsultPgmLinkedFuncDetailForm) form;
        
        switch (consultPgmLinkedFuncDetailForm.getStrutsAction())
        {
            case ConsultPgmLinkedFuncDetailAction.DETAIL_INIT:
                this.findDetail(request, response, consultPgmLinkedFuncDetailForm);
                returnActionForward = mapping.findForward("consultPgmLinkedFuncDetail");
                break;
            case ConsultPgmLinkedFuncDetailAction.DETAIL_INPUT:
                insertDetail(request, response, consultPgmLinkedFuncDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case ConsultPgmLinkedFuncDetailAction.DETAIL_UPDATE:
                updateDetail(request, response, consultPgmLinkedFuncDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            default:
                returnActionForward = mapping.findForward("consultPgmLinkedFuncDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * FIND DETAIL
     * @param request
     * @param response
     * @param consultPgmLinkedFuncDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, ConsultPgmLinkedFuncDetailForm consultPgmLinkedFuncDetailForm) throws Exception 
    {   
        ConsultPgmLinkedFuncDetailService consultPgmLinkedFuncDetailService = (ConsultPgmLinkedFuncDetailService)getBean("consultPgmLinkedFuncDetailService");
        
        ConsultPgmLinkedFuncCommonDTO consultPgmLinkedFuncCommonDTO = consultPgmLinkedFuncDetailForm.getConsultPgmLinkedFuncCommonDTO(); 

        ConsultPgmLinkedFuncDetailDTO consultPgmLinkedFuncDetailDTO = consultPgmLinkedFuncDetailService.findPgmLinkedFuncDetail(consultPgmLinkedFuncCommonDTO, getUser(request));
        consultPgmLinkedFuncDetailForm.setConsultPgmLinkedFuncDetailDTO(consultPgmLinkedFuncDetailDTO);
    }

    /**
     * INSERT DETAIL
     * @param request
     * @param response
     * @param consultPgmLinkedFuncDetailForm
     * @throws Exception
     */
    private void insertDetail(HttpServletRequest request, HttpServletResponse response, ConsultPgmLinkedFuncDetailForm consultPgmLinkedFuncDetailForm) throws Exception
    {
        ConsultPgmLinkedFuncDetailService consultPgmLinkedFuncDetailService = (ConsultPgmLinkedFuncDetailService)getBean("consultPgmLinkedFuncDetailService");
        ConsultPgmLinkedFuncDetailDTO  consultPgmLinkedFuncDetailDTO = consultPgmLinkedFuncDetailForm.getConsultPgmLinkedFuncDetailDTO(); 
        
        consultPgmLinkedFuncDetailService.insertPgmLinkedFuncDetail(consultPgmLinkedFuncDetailDTO, getUser(request));
        setAjaxStatus(request);
    }

    /**
     * UPDATE DETAIL
     * @param request
     * @param response
     * @param consultPgmLinkedFuncDetailForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, ConsultPgmLinkedFuncDetailForm consultPgmLinkedFuncDetailForm) throws Exception
    {
        ConsultPgmLinkedFuncDetailService consultPgmLinkedFuncDetailService = (ConsultPgmLinkedFuncDetailService)getBean("consultPgmLinkedFuncDetailService");
        ConsultPgmLinkedFuncDetailDTO  consultPgmLinkedFuncDetailDTO = consultPgmLinkedFuncDetailForm.getConsultPgmLinkedFuncDetailDTO();
        
        consultPgmLinkedFuncDetailService.updatePgmLinkedFuncDetail(consultPgmLinkedFuncDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }

}
