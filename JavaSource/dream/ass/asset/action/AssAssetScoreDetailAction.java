package dream.ass.asset.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.ass.asset.dto.AssAssetCommonDTO;
import dream.ass.asset.dto.AssAssetScoreDetailDTO;
import dream.ass.asset.dto.AssAssetScoreListDTO;
import dream.ass.asset.form.AssAssetScoreDetailForm;
import dream.ass.asset.service.AssAssetScoreDetailService;

/**
 * 평가점수 - Detail Action
 * 
 * @author youngjoo38
 * @version $Id: AssAssetScoreDetailAction.java,v 1.0 2017/08/28 12:37:40 youngjoo38 Exp $
 * @since 1.0
 * @struts:action path="/assAssetScoreDetail" name="assAssetScoreDetailForm"
 *                input="/dream/ass/asset/assAssetScoreDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/assAssetTlScoreDetail" name="assAssetScoreDetailForm"
 *                input="/dream/ass/asset/assAssetTlScoreDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="assAssetScoreDetail" path="/dream/ass/asset/assAssetScoreDetail.jsp"
 *                        redirect="false"
 * @struts.action-forward name="assAssetTlScoreDetail" path="/dream/ass/asset/assAssetTlScoreDetail.jsp"
 *                        redirect="false"
 */
public class AssAssetScoreDetailAction extends AuthAction
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
        AssAssetScoreDetailForm assAssetScoreDetailForm = (AssAssetScoreDetailForm) form;
        
        super.updateAudit(assAssetScoreDetailForm.getAssAssetScoreDetailDTO().getAuditKey()==""?assAssetScoreDetailForm.getAssAssetScoreListDTO().getAuditKey():assAssetScoreDetailForm.getAssAssetScoreDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (assAssetScoreDetailForm.getStrutsAction())
        {
            case AssAssetScoreDetailAction.DETAIL_INIT:
                this.findDetail(request, response, assAssetScoreDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case AssAssetScoreDetailAction.DETAIL_INPUT:
            	insertDetail(request, response, assAssetScoreDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case AssAssetScoreDetailAction.DETAIL_UPDATE:
            	updateDetail(request, response, assAssetScoreDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * FIND DETAIL
     * @param request
     * @param response
     * @param assAssetScoreDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, AssAssetScoreDetailForm assAssetScoreDetailForm) throws Exception 
    {   
    	AssAssetScoreDetailService assAssetScoreDetailService = (AssAssetScoreDetailService)getBean("assAssetScoreDetailService");
    	
    	AssAssetCommonDTO assAssetCommonDTO = assAssetScoreDetailForm.getAssAssetCommonDTO(); 
    	AssAssetScoreListDTO assAssetScoreListDTO = assAssetScoreDetailForm.getAssAssetScoreListDTO();
    	AssAssetScoreDetailDTO assAssetScoreDetailDTO = assAssetScoreDetailService.findDetail(assAssetCommonDTO,assAssetScoreListDTO, getUser(request));
    	assAssetScoreDetailForm.setAssAssetScoreDetailDTO(assAssetScoreDetailDTO);
    }

    /**
     * INSERT DETAIL
     * @param request
     * @param response
     * @param assAssetScoreDetailForm
     * @throws Exception
     */
    private void insertDetail(HttpServletRequest request, HttpServletResponse response, AssAssetScoreDetailForm assAssetScoreDetailForm) throws Exception
    {
    	AssAssetScoreDetailService assAssetScoreDetailService = (AssAssetScoreDetailService)getBean("assAssetScoreDetailService");
    	AssAssetCommonDTO assAssetCommonDTO = assAssetScoreDetailForm.getAssAssetCommonDTO(); 
    	AssAssetScoreDetailDTO  assAssetScoreDetailDTO = assAssetScoreDetailForm.getAssAssetScoreDetailDTO(); 
    	assAssetScoreDetailService.insertDetail(assAssetCommonDTO,assAssetScoreDetailDTO, getUser(request));
        setAjaxStatus(request);
    }

    /**
     * UPDATE DETAIL
     * @param request
     * @param response
     * @param assAssetScoreDetailForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, AssAssetScoreDetailForm assAssetScoreDetailForm) throws Exception
    {
    	AssAssetScoreDetailService assAssetScoreDetailService = (AssAssetScoreDetailService)getBean("assAssetScoreDetailService");
    	AssAssetCommonDTO assAssetCommonDTO = assAssetScoreDetailForm.getAssAssetCommonDTO(); 
    	AssAssetScoreDetailDTO  assAssetScoreDetailDTO = assAssetScoreDetailForm.getAssAssetScoreDetailDTO();
    	assAssetScoreDetailService.updateDetail(assAssetCommonDTO, assAssetScoreDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }

}