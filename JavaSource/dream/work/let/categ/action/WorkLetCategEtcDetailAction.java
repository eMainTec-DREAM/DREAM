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
import dream.work.let.categ.dto.WorkLetCategEtcDetailDTO;
import dream.work.let.categ.dto.WorkLetCategEtcListDTO;
import dream.work.let.categ.form.WorkLetCategEtcDetailForm;
import dream.work.let.categ.service.WorkLetCategEtcDetailService;

/**
 * 안전작업유형 - 보완사항 디테일 페이지 - Detail Action
 * 
 * @author euna0207
 * @version $Id: WorkLetCategEtcDetailAction.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
 * @since 1.0
 * @struts:action path="/workLetCategEtcDetail" name="workLetCategEtcDetailForm"
 *                input="/dream/work/let/categ/workLetCategEtcDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workLetCategEtcDetail" path="/dream/work/let/categ/workLetCategEtcDetail.jsp"
 *                        redirect="false"
 */
public class WorkLetCategEtcDetailAction extends AuthAction
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
        WorkLetCategEtcDetailForm workLetCategEtcDetailForm = (WorkLetCategEtcDetailForm) form;

        super.updateAudit(workLetCategEtcDetailForm.getWorkLetCategEtcDetailDTO().getAuditKey()==""?workLetCategEtcDetailForm.getWorkLetCategEtcListDTO().getAuditKey():workLetCategEtcDetailForm.getWorkLetCategEtcDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (workLetCategEtcDetailForm.getStrutsAction())
        {
            case WorkLetCategEtcDetailAction.DETAIL_INIT:
                this.findDetail(request, response, workLetCategEtcDetailForm);
                returnActionForward = mapping.findForward("workLetCategEtcDetail");
                break;
            case WorkLetCategEtcDetailAction.DETAIL_INPUT:
            	insertDetail(request, response, workLetCategEtcDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkLetCategEtcDetailAction.DETAIL_UPDATE:
            	updateDetail(request, response, workLetCategEtcDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("workLetCategEtcDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * FIND DETAIL
     * @param request
     * @param response
     * @param workLetCategEtcDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, WorkLetCategEtcDetailForm workLetCategEtcDetailForm) throws Exception 
    {   
    	WorkLetCategEtcDetailService workLetCategEtcDetailService = (WorkLetCategEtcDetailService)getBean("workLetCategEtcDetailService");
    	
    	WorkLetCategEtcListDTO workLetCategEtcListDTO = workLetCategEtcDetailForm.getWorkLetCategEtcListDTO(); 
    	WorkLetCategCommonDTO workLetCategCommonDTO = workLetCategEtcDetailForm.getWorkLetCategCommonDTO();
    	User user = getUser(request);
    	
    	WorkLetCategEtcDetailDTO workLetCategEtcDetailDTO = workLetCategEtcDetailService.findDetail(workLetCategCommonDTO, workLetCategEtcListDTO, user);
    	workLetCategEtcDetailForm.setWorkLetCategEtcDetailDTO(workLetCategEtcDetailDTO);
    	workLetCategEtcDetailForm.setWorkLetCategCommonDTO(workLetCategCommonDTO);
    }

    /**
     * INSERT DETAIL
     * @param request
     * @param response
     * @param workLetCategEtcDetailForm
     * @throws Exception
     */
    private void insertDetail(HttpServletRequest request, HttpServletResponse response, WorkLetCategEtcDetailForm workLetCategEtcDetailForm) throws Exception
    {
    	WorkLetCategEtcDetailService workLetCategEtcDetailService = (WorkLetCategEtcDetailService)getBean("workLetCategEtcDetailService");
    	WorkLetCategEtcDetailDTO  workLetCategEtcDetailDTO = workLetCategEtcDetailForm.getWorkLetCategEtcDetailDTO(); 
    	WorkLetCategCommonDTO workLetCategCommonDTO = workLetCategEtcDetailForm.getWorkLetCategCommonDTO();
    	
    	User user = getUser(request);
    	
    	workLetCategEtcDetailService.insertDetail(workLetCategCommonDTO, workLetCategEtcDetailDTO, user);
        setAjaxStatus(request);
    }

    /**
     * UPDATE DETAIL
     * @param request
     * @param response
     * @param workLetCategEtcDetailForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, WorkLetCategEtcDetailForm workLetCategEtcDetailForm) throws Exception
    {
    	WorkLetCategEtcDetailService workLetCategEtcDetailService = (WorkLetCategEtcDetailService)getBean("workLetCategEtcDetailService");
    	WorkLetCategEtcDetailDTO  workLetCategEtcDetailDTO = workLetCategEtcDetailForm.getWorkLetCategEtcDetailDTO();
    	WorkLetCategCommonDTO workLetCategCommonDTO = workLetCategEtcDetailForm.getWorkLetCategCommonDTO();
    	
    	User user = getUser(request);
    	
    	workLetCategEtcDetailService.updateDetail(workLetCategCommonDTO, workLetCategEtcDetailDTO, user);
        
        setAjaxStatus(request);
    }

}