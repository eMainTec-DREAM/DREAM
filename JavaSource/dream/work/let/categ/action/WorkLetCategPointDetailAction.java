package dream.work.let.categ.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.work.let.categ.dto.WorkLetCategCommonDTO;
import dream.work.let.categ.dto.WorkLetCategPointDetailDTO;
import dream.work.let.categ.dto.WorkLetCategPointListDTO;
import dream.work.let.categ.form.WorkLetCategPointDetailForm;
import dream.work.let.categ.service.WorkLetCategPointDetailService;

/**
 * 안전작업유형 점검항목 디테일 페이지 - Detail Action
 * 
 * @author euna0207
 * @version $Id: WorkLetCategPointDetailAction.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
 * @since 1.0
 * @struts:action path="/workLetCategPointDetail" name="workLetCategPointDetailForm"
 *                input="/dream/work/let/categ/workLetCategPointDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workLetCategPointDetail" path="/dream/work/let/categ/workLetCategPointDetail.jsp"
 *                        redirect="false"
 */
public class WorkLetCategPointDetailAction extends AuthAction
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
        WorkLetCategPointDetailForm workLetCategPointDetailForm = (WorkLetCategPointDetailForm) form;
        
        super.updateAudit(workLetCategPointDetailForm.getWorkLetCategPointDetailDTO().getAuditKey()==""?workLetCategPointDetailForm.getWorkLetCategPointListDTO().getAuditKey():workLetCategPointDetailForm.getWorkLetCategPointDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (workLetCategPointDetailForm.getStrutsAction())
        {
            case WorkLetCategPointDetailAction.DETAIL_INIT:
                this.findDetail(request, response, workLetCategPointDetailForm);
                returnActionForward = mapping.findForward("workLetCategPointDetail");
                break;
            case WorkLetCategPointDetailAction.DETAIL_INPUT:
            	insertDetail(request, response, workLetCategPointDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkLetCategPointDetailAction.DETAIL_UPDATE:
            	updateDetail(request, response, workLetCategPointDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("workLetCategPointDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * FIND DETAIL
     * @param request
     * @param response
     * @param workLetCategPointDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, WorkLetCategPointDetailForm workLetCategPointDetailForm) throws Exception 
    {   
    	WorkLetCategPointDetailService workLetCategPointDetailService = (WorkLetCategPointDetailService)getBean("workLetCategPointDetailService");
    	
    	WorkLetCategPointListDTO workLetCategPointListDTO = workLetCategPointDetailForm.getWorkLetCategPointListDTO(); 
    	WorkLetCategCommonDTO workLetCategCommonDTO = workLetCategPointDetailForm.getWorkLetCategCommonDTO();
		
    	User user = getUser(request);
    	
    	WorkLetCategPointDetailDTO workLetCategPointDetailDTO = workLetCategPointDetailService.findDetail(workLetCategCommonDTO, workLetCategPointListDTO, user);
    	workLetCategPointDetailForm.setWorkLetCategPointDetailDTO(workLetCategPointDetailDTO);
    	workLetCategPointDetailForm.setWorkLetCategCommonDTO(workLetCategCommonDTO);
    }

    /**
     * INSERT DETAIL
     * @param request
     * @param response
     * @param workLetCategPointDetailForm
     * @throws Exception
     */
    private void insertDetail(HttpServletRequest request, HttpServletResponse response, WorkLetCategPointDetailForm workLetCategPointDetailForm) throws Exception
    {
    	WorkLetCategPointDetailService workLetCategPointDetailService = (WorkLetCategPointDetailService)getBean("workLetCategPointDetailService");
    	WorkLetCategPointDetailDTO  workLetCategPointDetailDTO = workLetCategPointDetailForm.getWorkLetCategPointDetailDTO(); 
    	WorkLetCategCommonDTO workLetCategCommonDTO = workLetCategPointDetailForm.getWorkLetCategCommonDTO();
    			
    	User user = getUser(request);
    	
    	workLetCategPointDetailService.insertDetail(workLetCategCommonDTO, workLetCategPointDetailDTO, user);
        setAjaxStatus(request);
    }

    /**
     * UPDATE DETAIL
     * @param request
     * @param response
     * @param workLetCategPointDetailForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, WorkLetCategPointDetailForm workLetCategPointDetailForm) throws Exception
    {
    	WorkLetCategPointDetailService workLetCategPointDetailService = (WorkLetCategPointDetailService)getBean("workLetCategPointDetailService");
    	WorkLetCategPointDetailDTO  workLetCategPointDetailDTO = workLetCategPointDetailForm.getWorkLetCategPointDetailDTO();
    	WorkLetCategCommonDTO workLetCategCommonDTO = workLetCategPointDetailForm.getWorkLetCategCommonDTO();
    	
    	User user = getUser(request);
    	
    	workLetCategPointDetailService.updateDetail(workLetCategCommonDTO, workLetCategPointDetailDTO, user);
        
        setAjaxStatus(request);
    }

}