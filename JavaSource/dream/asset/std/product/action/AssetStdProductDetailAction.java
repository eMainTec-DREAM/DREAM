package dream.asset.std.product.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.asset.std.product.dto.AssetStdProductDetailDTO;
import dream.asset.std.product.form.AssetStdProductDetailForm;
import dream.asset.std.product.service.AssetStdProductDetailService;

/**
 * 생산품목 - 상세 action
 * 
 * @author ghlee
 * @version $Id: $
 * @since 1.0
 * @struts:action path="/assetStdProductDetail" name="assetStdProductDetailForm"
 *                input="/dream/asset/std/product/assetStdProductDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="assetStdProductDetail" path="/dream/asset/std/product/assetStdProductDetail.jsp"
 *                        redirect="false"
 */
public class AssetStdProductDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int PRODUCT_DETAIL_INIT 		= 8001;
    /** 저장 */
    public static final int PRODUCT_DETAIL_INPUT 		= 5002;
    /** 수정 */
    public static final int PRODUCT_DETAIL_UPDATE 		= 6003;
    /** 중복 체크 */
    public static final int PRODUCT_DETAIL_CHECK 		= 1004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        AssetStdProductDetailForm assetStdProductDetailForm = (AssetStdProductDetailForm) form;
        
        super.updateAudit(assetStdProductDetailForm.getAssetStdProductDetailDTO().getAuditKey()==""?assetStdProductDetailForm.getAssetStdProductCommonDTO().getAuditKey():assetStdProductDetailForm.getAssetStdProductDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (assetStdProductDetailForm.getStrutsAction())
        {
            case AssetStdProductDetailAction.PRODUCT_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, assetStdProductDetailForm);
                returnActionForward = mapping.findForward("assetStdProductDetail");
                break;
            case AssetStdProductDetailAction.PRODUCT_DETAIL_INPUT:
            	insertDetail(assetStdProductDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case AssetStdProductDetailAction.PRODUCT_DETAIL_UPDATE:
            	updateDetail(assetStdProductDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case AssetStdProductDetailAction.PRODUCT_DETAIL_CHECK:
            	validProductNo(assetStdProductDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("assetStdProductDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 생산품목 상세 조회
     * @author  ghlee
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param assetStdProductDetailForm
     */
    private void findDetail(HttpServletRequest request, AssetStdProductDetailForm assetStdProductDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	AssetStdProductDetailService assetStdProductDetailService = (AssetStdProductDetailService)getBean("assetStdProductDetailService");
    	
        String productId = assetStdProductDetailForm.getAssetStdProductCommonDTO().getProductId();
        
        // 조회된 상세 부분
        AssetStdProductDetailDTO assetStdProductDetailDTO = assetStdProductDetailService.findDetail(getUser(request), productId);
        
        // 조회된 상세  셋팅한다.
        assetStdProductDetailForm.setAssetStdProductDetailDTO(assetStdProductDetailDTO);
    }
    /**
     * detail insert
     * @author  
     * @version $Id:  $
     * @since   1.0
     * 
     * @param assetStdProductDetailForm
     * @param request
     */
    private void insertDetail(AssetStdProductDetailForm assetStdProductDetailForm, HttpServletRequest request) throws Exception
    {
        AssetStdProductDetailService assetStdProductDetailService = (AssetStdProductDetailService) getBean("assetStdProductDetailService");
        
        AssetStdProductDetailDTO assetStdProductDetailDTO = assetStdProductDetailForm.getAssetStdProductDetailDTO();
        
        assetStdProductDetailService.insertDetail(assetStdProductDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * detail update
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param assetStdProductDetailForm
     * @param request
     */
    private void updateDetail(AssetStdProductDetailForm assetStdProductDetailForm, HttpServletRequest request) throws Exception
    {
    	AssetStdProductDetailService assetStdProductDetailService = (AssetStdProductDetailService) getBean("assetStdProductDetailService");
        
        AssetStdProductDetailDTO assetStdProductDetailDTO = assetStdProductDetailForm.getAssetStdProductDetailDTO();
        
        assetStdProductDetailService.updateDetail(assetStdProductDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * valid product no
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param assetStdProductDetailForm
     * @param request
     */
    private void validProductNo(AssetStdProductDetailForm assetStdProductDetailForm, HttpServletRequest request) throws Exception
    {
    	AssetStdProductDetailService assetStdProductDetailService = (AssetStdProductDetailService) getBean("assetStdProductDetailService");
        
        AssetStdProductDetailDTO assetStdProductDetailDTO = assetStdProductDetailForm.getAssetStdProductDetailDTO();
        
        String isValid = assetStdProductDetailService.validProductNo(assetStdProductDetailDTO, getUser(request));
        
        setAjaxDesc(request, isValid);
    }
    
}