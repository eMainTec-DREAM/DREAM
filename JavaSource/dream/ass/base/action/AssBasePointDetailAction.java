package dream.ass.base.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.ass.base.dto.AssBaseCommonDTO;
import dream.ass.base.dto.AssBasePointDetailDTO;
import dream.ass.base.dto.AssBasePointListDTO;
import dream.ass.base.form.AssBasePointDetailForm;
import dream.ass.base.service.AssBasePointDetailService;

/**
 * 평가항목 - Detail Action
 * 
 * @author kim21017
 * @version $Id: AssBasePointDetailAction.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/assBasePointDetail" name="assBasePointDetailForm"
 *                input="/dream/ass/base/assBasePointDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="assBasePointDetail" path="/dream/ass/base/assBasePointDetail.jsp"
 *                        redirect="false"
 */
public class AssBasePointDetailAction extends AuthAction
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
        AssBasePointDetailForm assBasePointDetailForm = (AssBasePointDetailForm) form;
        
        super.updateAudit(assBasePointDetailForm.getAssBasePointDetailDTO().getAuditKey()==""?assBasePointDetailForm.getAssBasePointListDTO().getAuditKey():assBasePointDetailForm.getAssBasePointDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (assBasePointDetailForm.getStrutsAction())
        {
            case AssBasePointDetailAction.DETAIL_INIT:
                this.findDetail(request, response, assBasePointDetailForm);
                returnActionForward = mapping.findForward("assBasePointDetail");
                break;
            case AssBasePointDetailAction.DETAIL_INPUT:
            	insertDetail(request, response, assBasePointDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case AssBasePointDetailAction.DETAIL_UPDATE:
            	updateDetail(request, response, assBasePointDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("assBasePointDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * FIND DETAIL
     * @param request
     * @param response
     * @param assBasePointDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, AssBasePointDetailForm assBasePointDetailForm) throws Exception 
    {   
    	AssBasePointDetailService assBasePointDetailService = (AssBasePointDetailService)getBean("assBasePointDetailService");
    	
    	AssBaseCommonDTO assBaseCommonDTO = assBasePointDetailForm.getAssBaseCommonDTO(); 
    	AssBasePointListDTO assBasePointListDTO = assBasePointDetailForm.getAssBasePointListDTO();
    	AssBasePointDetailDTO assBasePointDetailDTO = assBasePointDetailService.findDetail(assBaseCommonDTO,assBasePointListDTO, getUser(request));
    	assBasePointDetailForm.setAssBasePointDetailDTO(assBasePointDetailDTO);
    }

    /**
     * INSERT DETAIL
     * @param request
     * @param response
     * @param assBasePointDetailForm
     * @throws Exception
     */
    private void insertDetail(HttpServletRequest request, HttpServletResponse response, AssBasePointDetailForm assBasePointDetailForm) throws Exception
    {
    	AssBasePointDetailService assBasePointDetailService = (AssBasePointDetailService)getBean("assBasePointDetailService");
    	AssBaseCommonDTO assBaseCommonDTO = assBasePointDetailForm.getAssBaseCommonDTO(); 
    	AssBasePointDetailDTO  assBasePointDetailDTO = assBasePointDetailForm.getAssBasePointDetailDTO(); 
    	assBasePointDetailService.insertDetail(assBaseCommonDTO,assBasePointDetailDTO, getUser(request));
        setAjaxStatus(request);
    }

    /**
     * UPDATE DETAIL
     * @param request
     * @param response
     * @param assBasePointDetailForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, AssBasePointDetailForm assBasePointDetailForm) throws Exception
    {
    	AssBasePointDetailService assBasePointDetailService = (AssBasePointDetailService)getBean("assBasePointDetailService");
    	AssBaseCommonDTO assBaseCommonDTO = assBasePointDetailForm.getAssBaseCommonDTO(); 
    	AssBasePointDetailDTO  assBasePointDetailDTO = assBasePointDetailForm.getAssBasePointDetailDTO();
    	assBasePointDetailService.updateDetail(assBaseCommonDTO, assBasePointDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }

}