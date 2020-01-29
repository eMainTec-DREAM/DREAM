package dream.asset.std.ctctr.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.asset.std.ctctr.dto.AssetStdCtctrDetailDTO;
import dream.asset.std.ctctr.form.AssetStdCtctrDetailForm;
import dream.asset.std.ctctr.service.AssetStdCtctrDetailService;

/**
 * CostCenter - 상세 action
 * 
 * @author ghlee
 * @version $Id: $
 * @since 1.0
 * @struts:action path="/assetStdCtctrDetail" name="assetStdCtctrDetailForm"
 *                input="/dream/asset/std/ctctr/assetStdCtctrDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="assetStdCtctrDetail" path="/dream/asset/std/ctctr/assetStdCtctrDetail.jsp"
 *                        redirect="false"
 */
public class AssetStdCtctrDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int CTCTR_DETAIL_INIT 		= 8001;
    /** 저장 */
    public static final int CTCTR_DETAIL_INPUT 		= 5002;
    /** 수정 */
    public static final int CTCTR_DETAIL_UPDATE 	= 6003;
    /** 중복 체크 */
    public static final int CTCTR_DETAIL_CHECK 		= 1004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        AssetStdCtctrDetailForm assetStdCtctrDetailForm = (AssetStdCtctrDetailForm) form;
        
        super.updateAudit(assetStdCtctrDetailForm.getAssetStdCtctrDetailDTO().getAuditKey()==""?assetStdCtctrDetailForm.getAssetStdCtctrCommonDTO().getAuditKey():assetStdCtctrDetailForm.getAssetStdCtctrDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));

                
        switch (assetStdCtctrDetailForm.getStrutsAction())
        {
            case AssetStdCtctrDetailAction.CTCTR_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, assetStdCtctrDetailForm);
                returnActionForward = mapping.findForward("assetStdCtctrDetail");
                break;
            case AssetStdCtctrDetailAction.CTCTR_DETAIL_INPUT:
            	insertDetail(assetStdCtctrDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case AssetStdCtctrDetailAction.CTCTR_DETAIL_UPDATE:
            	updateDetail(assetStdCtctrDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case AssetStdCtctrDetailAction.CTCTR_DETAIL_CHECK:
            	validCtctrNo(assetStdCtctrDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("assetStdCtctrDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * CostCenter 상세 조회
     * @author  ghlee
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param assetStdCtctrDetailForm
     */
    private void findDetail(HttpServletRequest request, AssetStdCtctrDetailForm assetStdCtctrDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	AssetStdCtctrDetailService assetStdCtctrDetailService = (AssetStdCtctrDetailService)getBean("assetStdCtctrDetailService");
    	
        String ctctrId = assetStdCtctrDetailForm.getAssetStdCtctrCommonDTO().getCtctrId();
        
        // 조회된 상세 부분
        AssetStdCtctrDetailDTO assetStdCtctrDetailDTO = assetStdCtctrDetailService.findDetail(getUser(request), ctctrId);
        
        // 조회된 상세  셋팅한다.
        assetStdCtctrDetailForm.setAssetStdCtctrDetailDTO(assetStdCtctrDetailDTO);
    }
    /**
     * detail insert
     * @author  
     * @version $Id:  $
     * @since   1.0
     * 
     * @param assetStdCtctrDetailForm
     * @param request
     */
    private void insertDetail(AssetStdCtctrDetailForm assetStdCtctrDetailForm, HttpServletRequest request) throws Exception
    {
        AssetStdCtctrDetailService assetStdCtctrDetailService = (AssetStdCtctrDetailService) getBean("assetStdCtctrDetailService");
        
        AssetStdCtctrDetailDTO assetStdCtctrDetailDTO = assetStdCtctrDetailForm.getAssetStdCtctrDetailDTO();
        
        assetStdCtctrDetailService.insertDetail(assetStdCtctrDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * detail update
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param assetStdCtctrDetailForm
     * @param request
     */
    private void updateDetail(AssetStdCtctrDetailForm assetStdCtctrDetailForm, HttpServletRequest request) throws Exception
    {
    	AssetStdCtctrDetailService assetStdCtctrDetailService = (AssetStdCtctrDetailService) getBean("assetStdCtctrDetailService");
        
        AssetStdCtctrDetailDTO assetStdCtctrDetailDTO = assetStdCtctrDetailForm.getAssetStdCtctrDetailDTO();
        
        assetStdCtctrDetailService.updateDetail(assetStdCtctrDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * valid ctctr no
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param assetStdCtctrDetailForm
     * @param request
     */
    private void validCtctrNo(AssetStdCtctrDetailForm assetStdCtctrDetailForm, HttpServletRequest request) throws Exception
    {
    	AssetStdCtctrDetailService assetStdCtctrDetailService = (AssetStdCtctrDetailService) getBean("assetStdCtctrDetailService");
        
        AssetStdCtctrDetailDTO assetStdCtctrDetailDTO = assetStdCtctrDetailForm.getAssetStdCtctrDetailDTO();
        
        String isValid = assetStdCtctrDetailService.validCtctrNo(assetStdCtctrDetailDTO, getUser(request));
        
        setAjaxDesc(request, isValid);
    }
    
}