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
import dream.work.let.categ.dto.WorkLetCategDetailDTO;
import dream.work.let.categ.form.WorkLetCategDetailForm;
import dream.work.let.categ.service.WorkLetCategDetailService;

/**
 * 안전작업유형 디테일 페이지 - Detail Action
 * 
 * @author euna0207
 * @version $Id: WorkLetCategDetailAction.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
 * @since 1.0
 * @struts:action path="/workLetCategDetail" name="workLetCategDetailForm"
 *                input="/dream/work/let/categ/workLetCategDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workLetCategDetail" path="/dream/work/let/categ/workLetCategDetail.jsp"
 *                        redirect="false"
 */
public class WorkLetCategDetailAction extends AuthAction
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
        // *
        WorkLetCategDetailForm workLetCategDetailForm = (WorkLetCategDetailForm) form;
        
        super.updateAudit(workLetCategDetailForm.getWorkLetCategDetailDTO().getAuditKey()==""?workLetCategDetailForm.getWorkLetCategCommonDTO().getAuditKey():workLetCategDetailForm.getWorkLetCategDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (workLetCategDetailForm.getStrutsAction())
        {
            case WorkLetCategDetailAction.DETAIL_INIT:
                this.findDetail(request, response, workLetCategDetailForm);
                //위에서 얻어진 DAO 쿼리 결과값이 아래에 어떻게 들어가는지?
                returnActionForward = mapping.findForward("workLetCategDetail");
                break;
            case WorkLetCategDetailAction.DETAIL_INPUT:
            	insertDetail(request, response, workLetCategDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkLetCategDetailAction.DETAIL_UPDATE:
            	updateDetail(request, response, workLetCategDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("workLetCategDetail");
                break;
        }

        // return값이 success 처리가 되면 forward태그의 action값에 맞는 JSP로 이동하게끔 설정해두었음 (struts-config에서)
        return returnActionForward;
    }
    
    /**
     * FIND DETAIL
     * @param request
     * @param response
     * @param workLetCategDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, WorkLetCategDetailForm workLetCategDetailForm) throws Exception 
    {   
    	WorkLetCategDetailService workLetCategDetailService = (WorkLetCategDetailService)getBean("workLetCategDetailService");
    	
    	WorkLetCategCommonDTO workLetCategCommonDTO = workLetCategDetailForm.getWorkLetCategCommonDTO(); 

    	User user = getUser(request);
    	
    	WorkLetCategDetailDTO workLetCategDetailDTO = workLetCategDetailService.findDetail(workLetCategCommonDTO, user);
    	workLetCategDetailForm.setWorkLetCategDetailDTO(workLetCategDetailDTO);
    }

    /**
     * INSERT DETAIL
     * @param request
     * @param response
     * @param workLetCategDetailForm
     * @throws Exception
     */
    private void insertDetail(HttpServletRequest request, HttpServletResponse response, WorkLetCategDetailForm workLetCategDetailForm) throws Exception
    {
    	WorkLetCategDetailService workLetCategDetailService = (WorkLetCategDetailService)getBean("workLetCategDetailService");
    	WorkLetCategDetailDTO  workLetCategDetailDTO = workLetCategDetailForm.getWorkLetCategDetailDTO(); 
    	
    	User user = getUser(request);
    	
    	workLetCategDetailService.insertDetail(workLetCategDetailDTO, user);
        setAjaxStatus(request);
    }

    /**
     * UPDATE DETAIL
     * @param request
     * @param response
     * @param workLetCategDetailForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, WorkLetCategDetailForm workLetCategDetailForm) throws Exception
    {
    	WorkLetCategDetailService workLetCategDetailService = (WorkLetCategDetailService)getBean("workLetCategDetailService");
    	WorkLetCategDetailDTO  workLetCategDetailDTO = workLetCategDetailForm.getWorkLetCategDetailDTO();
    	
    	User user = getUser(request);
    	
    	workLetCategDetailService.updateDetail(workLetCategDetailDTO, user);
        
        setAjaxStatus(request);
    }

}