package dream.ass.asset.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.ass.asset.dto.AssAssetCommonDTO;
import dream.ass.asset.dto.AssAssetDetailDTO;
import dream.ass.asset.form.AssAssetDetailForm;
import dream.ass.asset.service.AssAssetDetailService;

/**
 * AssAsset Page - Detail Action
 * 
 * @author youngjoo38
 * @version $Id: AssAssetDetailAction.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @struts:action path="/assAssetDetail" name="assAssetDetailForm"
 *                input="/dream/ass/asset/assAssetDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/assAssetTlDetail" name="assAssetDetailForm"
 *                input="/dream/ass/asset/assAssetTlDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/assetListAssDetail" name="assAssetDetailForm"
 *                input="/dream/asset/list/assetListAssDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/assetListAssTlDetail" name="assAssetDetailForm"
 *                input="/dream/asset/list/assetListAssTlDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="assAssetDetail" path="/dream/ass/asset/assAssetDetail.jsp"
 *                        redirect="false"
 * @struts.action-forward name="assAssetTlDetail" path="/dream/ass/asset/assAssetTlDetail.jsp"
 *                        redirect="false"
 */
public class AssAssetDetailAction extends AuthAction
{
  //일반 페이지 적용시 AuthAction 으로 변경해야합니다.
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int DETAIL_INIT                 = 8001;
    /** 저장 */
    public static final int DETAIL_INPUT                = 5002;
    /** 수정 */
    public static final int DETAIL_UPDATE               = 6003;
    /** 평가완료 */
    public static final int DETAIL_ASSCOMPLETED         = 6004;
    /** 하위항목 작성 체크 */
    public static final int DETAIL_CHECK                = 8005;
    /** 평가 완료 후 등급 업데이트 */
    public static final int GRADE_UPDATE                = 6006;
    /** 평가 완료 후 점수 업데이트 */
    public static final int SCORE_UPDATE                = 6007;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        AssAssetDetailForm assAssetDetailForm = (AssAssetDetailForm) form;
        
        super.updateAudit(assAssetDetailForm.getAssAssetDetailDTO().getAuditKey()==""?assAssetDetailForm.getAssAssetCommonDTO().getAuditKey():assAssetDetailForm.getAssAssetDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (assAssetDetailForm.getStrutsAction())
        {
            case AssAssetDetailAction.DETAIL_INIT:
                this.findDetail(request, response, assAssetDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case AssAssetDetailAction.DETAIL_INPUT:
                insertDetail(request, response, assAssetDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case AssAssetDetailAction.DETAIL_UPDATE:
                updateDetail(request, response, assAssetDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case AssAssetDetailAction.DETAIL_ASSCOMPLETED:
                asscompletedDetail(request, response, assAssetDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case AssAssetDetailAction.DETAIL_CHECK:
                checkDetail(request, response, assAssetDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case AssAssetDetailAction.GRADE_UPDATE:
                updateGrade(request, response, assAssetDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case AssAssetDetailAction.SCORE_UPDATE:
                updateScore(request, response, assAssetDetailForm);
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
     * @param assAssetDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, AssAssetDetailForm assAssetDetailForm) throws Exception 
    {   
        AssAssetDetailService assAssetDetailService = (AssAssetDetailService)getBean("assAssetDetailService",request);
        
        AssAssetCommonDTO assAssetCommonDTO = assAssetDetailForm.getAssAssetCommonDTO(); 

        User user = getUser(request);
        AssAssetDetailDTO assAssetDetailDTO = assAssetDetailService.findDetail(assAssetCommonDTO, user);
        assAssetDetailForm.setAssAssetDetailDTO(assAssetDetailDTO);
    }

    /**
     * INSERT DETAIL
     * @param request
     * @param response
     * @param assAssetDetailForm
     * @throws Exception
     */
    private void insertDetail(HttpServletRequest request, HttpServletResponse response, AssAssetDetailForm assAssetDetailForm) throws Exception
    {
        AssAssetDetailService assAssetDetailService = (AssAssetDetailService)getBean("assAssetDetailService", request);
        AssAssetDetailDTO  assAssetDetailDTO = assAssetDetailForm.getAssAssetDetailDTO(); 
        
        User user = getUser(request);
        assAssetDetailService.insertDetail(assAssetDetailDTO, user);
        setAjaxStatus(request);
    }

    /**
     * UPDATE DETAIL
     * @param request
     * @param response
     * @param assAssetDetailForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, AssAssetDetailForm assAssetDetailForm) throws Exception
    {
        AssAssetDetailService assAssetDetailService = (AssAssetDetailService)getBean("assAssetDetailService", request);
        AssAssetDetailDTO  assAssetDetailDTO = assAssetDetailForm.getAssAssetDetailDTO();
        
        User user = getUser(request);
        assAssetDetailService.updateDetail(assAssetDetailDTO, user);
        setAjaxStatus(request);
    }
    
    /**
     * ASSCOMPLETED DETAIL
     * @param request
     * @param response
     * @param assAssetDetailForm
     * @throws Exception
     */
    private void asscompletedDetail(HttpServletRequest request, HttpServletResponse response, AssAssetDetailForm assAssetDetailForm) throws Exception
    {
        AssAssetDetailService assAssetDetailService = (AssAssetDetailService)getBean("assAssetDetailService",request);
        AssAssetDetailDTO  assAssetDetailDTO = assAssetDetailForm.getAssAssetDetailDTO();
        AssAssetCommonDTO assAssetCommonDTO = assAssetDetailForm.getAssAssetCommonDTO();
        
        assAssetCommonDTO.setFilterEquipId(assAssetDetailForm.getMaEqMstrCommonDTO().getEquipId());
        
        User user = getUser(request);
        assAssetDetailService.asscompletedDetail(assAssetCommonDTO, assAssetDetailDTO, user);
        setAjaxStatus(request);
    }
    
    /**
     * CHECK DETAIL
     * @param request
     * @param response
     * @param assAssetDetailForm
     * @throws Exception
     */
    private void checkDetail(HttpServletRequest request, HttpServletResponse response, AssAssetDetailForm assAssetDetailForm) throws Exception
    {
        AssAssetDetailService assAssetDetailService = (AssAssetDetailService) getBean("assAssetDetailService", request);
        AssAssetDetailDTO assAssetDetailDTO = assAssetDetailForm.getAssAssetDetailDTO();
        
        String checkDetail = assAssetDetailService.checkDetail(assAssetDetailDTO, getUser(request));
        setAjaxDesc(request, checkDetail);
    }
    /**
     * UPDATE GRADE
     * @param request
     * @param response
     * @param assAssetDetailForm
     * @throws Exception
     */
    private void updateGrade(HttpServletRequest request, HttpServletResponse response, AssAssetDetailForm assAssetDetailForm) throws Exception
    {
        AssAssetDetailService assAssetDetailService = (AssAssetDetailService) getBean("assAssetDetailService",request);
        AssAssetDetailDTO assAssetDetailDTO = assAssetDetailForm.getAssAssetDetailDTO();
        
        String checkDetail = assAssetDetailService.findGrade(assAssetDetailDTO, getUser(request));
        setAjaxDesc(request, checkDetail);
    }
    /**
     * UPDATE SCORE
     * @param request
     * @param response
     * @param assAssetDetailForm
     * @throws Exception
     */
    private void updateScore(HttpServletRequest request, HttpServletResponse response, AssAssetDetailForm assAssetDetailForm) throws Exception
    {
        AssAssetDetailService assAssetDetailService = (AssAssetDetailService) getBean("assAssetDetailService",request);
        AssAssetDetailDTO assAssetDetailDTO = assAssetDetailForm.getAssAssetDetailDTO();
        
        String checkDetail = assAssetDetailService.updateScore(assAssetDetailDTO, getUser(request));
        setAjaxDesc(request, checkDetail);
    }
    

}
