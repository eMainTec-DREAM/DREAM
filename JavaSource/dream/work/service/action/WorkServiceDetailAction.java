package dream.work.service.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.work.service.dto.WorkServiceCommonDTO;
import dream.work.service.dto.WorkServiceDetailDTO;
import dream.work.service.form.WorkServiceDetailForm;
import dream.work.service.service.WorkServiceDetailService;

/**
 * 서비스 마스터 - Detail Action
 * 
 * @author cjscjs9
 * @version $Id: WorkServiceDetailAction.java,v 1.0 2018/08/02 09:12:40 cjscjs9 Exp $
 * @since 1.0
 * @struts:action path="/workServiceDetail" name="workServiceDetailForm"
 *                input="/dream/work/service/workServiceDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workServiceDetail" path="/dream/work/service/workServiceDetail.jsp"
 *                        redirect="false"
 */
public class WorkServiceDetailAction extends AuthAction
{
	//일반 페이지 적용시 AuthAction 으로 변경해야합니다.
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
        WorkServiceDetailForm workServiceDetailForm = (WorkServiceDetailForm) form;
        
        super.updateAudit(workServiceDetailForm.getWorkServiceDetailDTO().getAuditKey()==""?workServiceDetailForm.getWorkServiceCommonDTO().getAuditKey():workServiceDetailForm.getWorkServiceDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (workServiceDetailForm.getStrutsAction())
        {
            case WorkServiceDetailAction.DETAIL_INIT:
                this.findDetail(request, response, workServiceDetailForm);
                returnActionForward = mapping.findForward("workServiceDetail");
                break;
            case WorkServiceDetailAction.DETAIL_INPUT:
            	insertDetail(request, response, workServiceDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkServiceDetailAction.DETAIL_UPDATE:
            	updateDetail(request, response, workServiceDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("workServiceDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * FIND DETAIL
     * @param request
     * @param response
     * @param workServiceDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, WorkServiceDetailForm workServiceDetailForm) throws Exception 
    {   
    	WorkServiceDetailService workServiceDetailService = (WorkServiceDetailService)getBean("workServiceDetailService");
    	
    	WorkServiceCommonDTO workServiceCommonDTO = workServiceDetailForm.getWorkServiceCommonDTO(); 

    	User user = getUser(request);
    	
    	
    	WorkServiceDetailDTO workServiceDetailDTO = workServiceDetailService.findWorkServiceDetail(workServiceCommonDTO, user);
    	workServiceDetailForm.setWorkServiceDetailDTO(workServiceDetailDTO);
    }

    /**
     * INSERT DETAIL
     * @param request
     * @param response
     * @param workServiceDetailForm
     * @throws Exception
     */
    private void insertDetail(HttpServletRequest request, HttpServletResponse response, WorkServiceDetailForm workServiceDetailForm) throws Exception
    {
    	WorkServiceDetailService workServiceDetailService = (WorkServiceDetailService)getBean("workServiceDetailService");
    	WorkServiceDetailDTO  workServiceDetailDTO = workServiceDetailForm.getWorkServiceDetailDTO(); 

    	User user = getUser(request);
    	
    	workServiceDetailService.insertWorkServiceDetail(workServiceDetailDTO, user);
        setAjaxStatus(request);
    }

    /**
     * UPDATE DETAIL
     * @param request
     * @param response
     * @param workServiceDetailForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, WorkServiceDetailForm workServiceDetailForm) throws Exception
    {
    	WorkServiceDetailService workServiceDetailService = (WorkServiceDetailService)getBean("workServiceDetailService");
    	WorkServiceDetailDTO  workServiceDetailDTO = workServiceDetailForm.getWorkServiceDetailDTO();
    	
    	User user = getUser(request);
    	
    	workServiceDetailService.updateWorkServiceDetail(workServiceDetailDTO, user);
        
        setAjaxStatus(request);
    }

}