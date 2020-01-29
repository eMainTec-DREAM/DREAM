package dream.ass.base.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.ass.base.dto.AssBaseCommonDTO;
import dream.ass.base.dto.AssBasePointListDTO;
import dream.ass.base.dto.AssBasePointValDetailDTO;
import dream.ass.base.dto.AssBasePointValListDTO;
import dream.ass.base.form.AssBasePointValDetailForm;
import dream.ass.base.service.AssBasePointValDetailService;

/**
 * 평가기준 - Detail Action
 * 
 * @author kim21017
 * @version $Id: AssBasePointValDetailAction.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/assBasePointValDetail" name="assBasePointValDetailForm"
 *                input="/dream/ass/base/assBasePointValDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="assBasePointValDetail" path="/dream/ass/base/assBasePointValDetail.jsp"
 *                        redirect="false"
 */
public class AssBasePointValDetailAction extends AuthAction
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
        AssBasePointValDetailForm assBasePointValDetailForm = (AssBasePointValDetailForm) form;
        
        super.updateAudit(assBasePointValDetailForm.getAssBasePointValDetailDTO().getAuditKey()==""?assBasePointValDetailForm.getAssBasePointValListDTO().getAuditKey():assBasePointValDetailForm.getAssBasePointValDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (assBasePointValDetailForm.getStrutsAction())
        {
            case AssBasePointValDetailAction.DETAIL_INIT:
                this.findDetail(request, response, assBasePointValDetailForm);
                returnActionForward = mapping.findForward("assBasePointValDetail");
                break;
            case AssBasePointValDetailAction.DETAIL_INPUT:
            	insertDetail(request, response, assBasePointValDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case AssBasePointValDetailAction.DETAIL_UPDATE:
            	updateDetail(request, response, assBasePointValDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("assBasePointValDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * FIND DETAIL
     * @param request
     * @param response
     * @param assBasePointValDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, AssBasePointValDetailForm assBasePointValDetailForm) throws Exception 
    {   
    	AssBasePointValDetailService assBasePointValDetailService = (AssBasePointValDetailService)getBean("assBasePointValDetailService");
    	
    	AssBaseCommonDTO assBaseCommonDTO = assBasePointValDetailForm.getAssBaseCommonDTO(); 
    	AssBasePointListDTO assBasePointListDTO = assBasePointValDetailForm.getAssBasePointListDTO();
    	AssBasePointValListDTO assBasePointValListDTO = assBasePointValDetailForm.getAssBasePointValListDTO();
    	AssBasePointValDetailDTO assBasePointValDetailDTO = assBasePointValDetailService.findDetail(assBaseCommonDTO,assBasePointListDTO,assBasePointValListDTO, getUser(request));
    	assBasePointValDetailForm.setAssBasePointValDetailDTO(assBasePointValDetailDTO);
    }

    /**
     * INSERT DETAIL
     * @param request
     * @param response
     * @param assBasePointValDetailForm
     * @throws Exception
     */
    private void insertDetail(HttpServletRequest request, HttpServletResponse response, AssBasePointValDetailForm assBasePointValDetailForm) throws Exception
    {
    	AssBasePointValDetailService assBasePointValDetailService = (AssBasePointValDetailService)getBean("assBasePointValDetailService");
    	AssBaseCommonDTO assBaseCommonDTO = assBasePointValDetailForm.getAssBaseCommonDTO(); 
    	AssBasePointListDTO  assBasePointListDTO = assBasePointValDetailForm.getAssBasePointListDTO(); 
    	AssBasePointValListDTO  assBasePointValListDTO = assBasePointValDetailForm.getAssBasePointValListDTO(); 
    	AssBasePointValDetailDTO  assBasePointValDetailDTO = assBasePointValDetailForm.getAssBasePointValDetailDTO(); 
    	assBasePointValDetailService.insertDetail(assBaseCommonDTO,assBasePointListDTO,assBasePointValListDTO,assBasePointValDetailDTO, getUser(request));
        setAjaxStatus(request);
    }

    /**
     * UPDATE DETAIL
     * @param request
     * @param response
     * @param assBasePointValDetailForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, AssBasePointValDetailForm assBasePointValDetailForm) throws Exception
    {
    	AssBasePointValDetailService assBasePointValDetailService = (AssBasePointValDetailService)getBean("assBasePointValDetailService");
    	AssBaseCommonDTO assBaseCommonDTO = assBasePointValDetailForm.getAssBaseCommonDTO(); 
    	AssBasePointListDTO  assBasePointListDTO = assBasePointValDetailForm.getAssBasePointListDTO(); 
    	AssBasePointValListDTO  assBasePointValListDTO = assBasePointValDetailForm.getAssBasePointValListDTO(); 
    	AssBasePointValDetailDTO  assBasePointValDetailDTO = assBasePointValDetailForm.getAssBasePointValDetailDTO(); 
    	assBasePointValDetailService.updateDetail(assBaseCommonDTO, assBasePointListDTO, assBasePointValListDTO, assBasePointValDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }

}