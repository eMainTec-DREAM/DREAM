package dream.ass.base.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.ass.base.dto.AssBaseCommonDTO;
import dream.ass.base.dto.AssBaseGradeDetailDTO;
import dream.ass.base.dto.AssBaseGradeListDTO;
import dream.ass.base.form.AssBaseGradeDetailForm;
import dream.ass.base.service.AssBaseGradeDetailService;

/**
 * 등급기준 - Detail Action
 * 
 * @author kim21017
 * @version $Id: AssBaseGradeDetailAction.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/assBaseGradeDetail" name="assBaseGradeDetailForm"
 *                input="/dream/ass/base/assBaseGradeDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="assBaseGradeDetail" path="/dream/ass/base/assBaseGradeDetail.jsp"
 *                        redirect="false"
 */
public class AssBaseGradeDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int DETAIL_INIT 		= 8001;
    /** 저장 */
    public static final int DETAIL_INPUT 		= 5002;
    /** 수정 */
    public static final int DETAIL_UPDATE 		= 6003;
    /** 등급 중복 검사 */
    public static final int DETAIL_GRADE_CHECK	= 1004;
    /** FROM TO값  중복 검사 */
    public static final int DETAIL_FROMTO_CHECK	= 1005;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        AssBaseGradeDetailForm assBaseGradeDetailForm = (AssBaseGradeDetailForm) form;
        
        super.updateAudit(assBaseGradeDetailForm.getAssBaseGradeDetailDTO().getAuditKey()==""?assBaseGradeDetailForm.getAssBaseGradeListDTO().getAuditKey():assBaseGradeDetailForm.getAssBaseGradeDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (assBaseGradeDetailForm.getStrutsAction())
        {
            case AssBaseGradeDetailAction.DETAIL_INIT:
                this.findDetail(request, response, assBaseGradeDetailForm);
                returnActionForward = mapping.findForward("assBaseGradeDetail");
                break;
            case AssBaseGradeDetailAction.DETAIL_INPUT:
            	insertDetail(request, response, assBaseGradeDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case AssBaseGradeDetailAction.DETAIL_UPDATE:
            	updateDetail(request, response, assBaseGradeDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case AssBaseGradeDetailAction.DETAIL_GRADE_CHECK:
            	validGrade(assBaseGradeDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case AssBaseGradeDetailAction.DETAIL_FROMTO_CHECK:
            	validFromTo(assBaseGradeDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("assBaseGradeDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * FIND DETAIL
     * @param request
     * @param response
     * @param assBaseGradeDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, AssBaseGradeDetailForm assBaseGradeDetailForm) throws Exception 
    {   
    	AssBaseGradeDetailService assBaseGradeDetailService = (AssBaseGradeDetailService)getBean("assBaseGradeDetailService");
    	
    	AssBaseCommonDTO assBaseCommonDTO = assBaseGradeDetailForm.getAssBaseCommonDTO(); 
    	AssBaseGradeListDTO assBaseGradeListDTO = assBaseGradeDetailForm.getAssBaseGradeListDTO();
    	AssBaseGradeDetailDTO assBaseGradeDetailDTO = assBaseGradeDetailService.findDetail(assBaseCommonDTO,assBaseGradeListDTO, getUser(request));
    	assBaseGradeDetailForm.setAssBaseGradeDetailDTO(assBaseGradeDetailDTO);
    }

    /**
     * INSERT DETAIL
     * @param request
     * @param response
     * @param assBaseGradeDetailForm
     * @throws Exception
     */
    private void insertDetail(HttpServletRequest request, HttpServletResponse response, AssBaseGradeDetailForm assBaseGradeDetailForm) throws Exception
    {
    	AssBaseGradeDetailService assBaseGradeDetailService = (AssBaseGradeDetailService)getBean("assBaseGradeDetailService");
    	AssBaseCommonDTO assBaseCommonDTO = assBaseGradeDetailForm.getAssBaseCommonDTO(); 
    	AssBaseGradeDetailDTO  assBaseGradeDetailDTO = assBaseGradeDetailForm.getAssBaseGradeDetailDTO(); 
    	assBaseGradeDetailService.insertDetail(assBaseCommonDTO,assBaseGradeDetailDTO, getUser(request));
        setAjaxStatus(request);
    }

    /**
     * UPDATE DETAIL
     * @param request
     * @param response
     * @param assBaseGradeDetailForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, AssBaseGradeDetailForm assBaseGradeDetailForm) throws Exception
    {
    	AssBaseGradeDetailService assBaseGradeDetailService = (AssBaseGradeDetailService)getBean("assBaseGradeDetailService");
    	AssBaseCommonDTO assBaseCommonDTO = assBaseGradeDetailForm.getAssBaseCommonDTO(); 
    	AssBaseGradeDetailDTO  assBaseGradeDetailDTO = assBaseGradeDetailForm.getAssBaseGradeDetailDTO();
    	assBaseGradeDetailService.updateDetail(assBaseCommonDTO, assBaseGradeDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * DETAIL GRADE DUP CHECK
     * @param request
     * @param response
     * @param assBaseGradeDetailForm
     * @throws Exception
     */
    private void validGrade(AssBaseGradeDetailForm assBaseGradeDetailForm, HttpServletRequest request) throws Exception
    {
    	AssBaseGradeDetailService assBaseGradeDetailService = (AssBaseGradeDetailService)getBean("assBaseGradeDetailService");
    	AssBaseCommonDTO assBaseCommonDTO = assBaseGradeDetailForm.getAssBaseCommonDTO(); 
    	AssBaseGradeDetailDTO  assBaseGradeDetailDTO = assBaseGradeDetailForm.getAssBaseGradeDetailDTO();
    	String isValid = assBaseGradeDetailService.validGrade(assBaseCommonDTO, assBaseGradeDetailDTO, getUser(request));
        
    	setAjaxDesc(request, isValid);
    }
    /**
     * DETAIL FROM TO VALUE DUP CHECK
     * @param request
     * @param response
     * @param assBaseGradeDetailForm
     * @throws Exception
     */
    private void validFromTo(AssBaseGradeDetailForm assBaseGradeDetailForm, HttpServletRequest request) throws Exception
    {
    	AssBaseGradeDetailService assBaseGradeDetailService = (AssBaseGradeDetailService)getBean("assBaseGradeDetailService");
    	AssBaseCommonDTO assBaseCommonDTO = assBaseGradeDetailForm.getAssBaseCommonDTO(); 
    	AssBaseGradeDetailDTO  assBaseGradeDetailDTO = assBaseGradeDetailForm.getAssBaseGradeDetailDTO();
    	String isValid = assBaseGradeDetailService.validFromTo(assBaseCommonDTO, assBaseGradeDetailDTO, getUser(request));
        
    	setAjaxDesc(request, isValid);
    }

}