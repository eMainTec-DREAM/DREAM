package dream.ass.base.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.ass.base.dto.AssBaseCommonDTO;
import dream.ass.base.dto.AssBaseDetailDTO;
import dream.ass.base.form.AssBaseDetailForm;
import dream.ass.base.service.AssBaseDetailService;

/**
 * 설비등급 평가기준 - Detail Action
 * 
 * @author kim21017
 * @version $Id: AssBaseDetailAction.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/assBaseDetail" name="assBaseDetailForm"
 *                input="/dream/ass/base/assBaseDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="assBaseDetail" path="/dream/ass/base/assBaseDetail.jsp"
 *                        redirect="false"
 */
public class AssBaseDetailAction extends AuthAction
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
        AssBaseDetailForm assBaseDetailForm = (AssBaseDetailForm) form;
        
        super.updateAudit(assBaseDetailForm.getAssBaseDetailDTO().getAuditKey()==""?assBaseDetailForm.getAssBaseCommonDTO().getAuditKey():assBaseDetailForm.getAssBaseDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (assBaseDetailForm.getStrutsAction())
        {
            case AssBaseDetailAction.DETAIL_INIT:
                this.findDetail(request, response, assBaseDetailForm);
                returnActionForward = mapping.findForward("assBaseDetail");
                break;
            case AssBaseDetailAction.DETAIL_INPUT:
            	insertDetail(request, response, assBaseDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case AssBaseDetailAction.DETAIL_UPDATE:
            	updateDetail(request, response, assBaseDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("assBaseDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * FIND DETAIL
     * @param request
     * @param response
     * @param assBaseDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, AssBaseDetailForm assBaseDetailForm) throws Exception 
    {   
    	AssBaseDetailService assBaseDetailService = (AssBaseDetailService)getBean("assBaseDetailService");
    	
    	AssBaseCommonDTO assBaseCommonDTO = assBaseDetailForm.getAssBaseCommonDTO(); 
    	AssBaseDetailDTO assBaseDetailDTO = assBaseDetailService.findDetail(assBaseCommonDTO, getUser(request));
    	assBaseDetailForm.setAssBaseDetailDTO(assBaseDetailDTO);
    }

    /**
     * INSERT DETAIL
     * @param request
     * @param response
     * @param assBaseDetailForm
     * @throws Exception
     */
    private void insertDetail(HttpServletRequest request, HttpServletResponse response, AssBaseDetailForm assBaseDetailForm) throws Exception
    {
    	AssBaseDetailService assBaseDetailService = (AssBaseDetailService)getBean("assBaseDetailService");
    	AssBaseDetailDTO  assBaseDetailDTO = assBaseDetailForm.getAssBaseDetailDTO(); 
    	assBaseDetailService.insertDetail(assBaseDetailDTO, getUser(request));
        setAjaxStatus(request);
    }

    /**
     * UPDATE DETAIL
     * @param request
     * @param response
     * @param assBaseDetailForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, AssBaseDetailForm assBaseDetailForm) throws Exception
    {
    	AssBaseDetailService assBaseDetailService = (AssBaseDetailService)getBean("assBaseDetailService");
    	AssBaseDetailDTO  assBaseDetailDTO = assBaseDetailForm.getAssBaseDetailDTO();
    	assBaseDetailService.updateDetail(assBaseDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }

}