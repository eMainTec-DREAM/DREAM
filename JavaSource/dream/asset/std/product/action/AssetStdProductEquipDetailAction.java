package dream.asset.std.product.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.asset.std.product.dto.AssetStdProductCommonDTO;
import dream.asset.std.product.dto.AssetStdProductEquipDetailDTO;
import dream.asset.std.product.dto.AssetStdProductEquipListDTO;
import dream.asset.std.product.form.AssetStdProductEquipDetailForm;
import dream.asset.std.product.service.AssetStdProductEquipDetailService;

/**
 * 생산설비 - Detail Action
 * 
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @struts:action path="/assetStdProductEquipDetail" name="assetStdProductEquipDetailForm"
 *                input="/dream/asset/std/product/assetStdProductEquipDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="assetStdProductEquipDetail" path="/dream/asset/std/product/assetStdProductEquipDetail.jsp"
 *                        redirect="false"
 */
public class AssetStdProductEquipDetailAction extends AuthAction
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
        AssetStdProductEquipDetailForm assetStdProductEquipDetailForm = (AssetStdProductEquipDetailForm) form;
        
        super.updateAudit(assetStdProductEquipDetailForm.getAssetStdProductEquipDetailDTO().getAuditKey()==""?assetStdProductEquipDetailForm.getAssetStdProductEquipListDTO().getAuditKey():assetStdProductEquipDetailForm.getAssetStdProductEquipDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (assetStdProductEquipDetailForm.getStrutsAction())
        {
            case AssetStdProductEquipDetailAction.DETAIL_INIT:
                this.findDetail(request, response, assetStdProductEquipDetailForm);
                returnActionForward = mapping.findForward("assetStdProductEquipDetail");
                break;
            case AssetStdProductEquipDetailAction.DETAIL_INPUT:
            	insertDetail(request, response, assetStdProductEquipDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case AssetStdProductEquipDetailAction.DETAIL_UPDATE:
            	updateDetail(request, response, assetStdProductEquipDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("assetStdProductEquipDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * FIND DETAIL
     * @param request
     * @param response
     * @param assetStdProductEquipDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, AssetStdProductEquipDetailForm assetStdProductEquipDetailForm) throws Exception 
    {   
    	AssetStdProductEquipDetailService assetStdProductEquipDetailService = (AssetStdProductEquipDetailService)getBean("assetStdProductEquipDetailService");
    	
    	AssetStdProductCommonDTO assetStdProductCommonDTO = assetStdProductEquipDetailForm.getAssetStdProductCommonDTO(); 
    	AssetStdProductEquipListDTO assetStdProductEquipListDTO = assetStdProductEquipDetailForm.getAssetStdProductEquipListDTO();
    	AssetStdProductEquipDetailDTO assetStdProductEquipDetailDTO = assetStdProductEquipDetailService.findDetail(assetStdProductCommonDTO,assetStdProductEquipListDTO, getUser(request));
    	assetStdProductEquipDetailForm.setAssetStdProductEquipDetailDTO(assetStdProductEquipDetailDTO);
    }

    /**
     * INSERT DETAIL
     * @param request
     * @param response
     * @param assetStdProductEquipDetailForm
     * @throws Exception
     */
    private void insertDetail(HttpServletRequest request, HttpServletResponse response, AssetStdProductEquipDetailForm assetStdProductEquipDetailForm) throws Exception
    {
    	AssetStdProductEquipDetailService assetStdProductEquipDetailService = (AssetStdProductEquipDetailService)getBean("assetStdProductEquipDetailService");
    	AssetStdProductCommonDTO assetStdProductCommonDTO = assetStdProductEquipDetailForm.getAssetStdProductCommonDTO(); 
    	AssetStdProductEquipDetailDTO  assetStdProductEquipDetailDTO = assetStdProductEquipDetailForm.getAssetStdProductEquipDetailDTO(); 
    	assetStdProductEquipDetailService.insertDetail(assetStdProductCommonDTO,assetStdProductEquipDetailDTO, getUser(request));
        setAjaxStatus(request);
    }

    /**
     * UPDATE DETAIL
     * @param request
     * @param response
     * @param assetStdProductEquipDetailForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, AssetStdProductEquipDetailForm assetStdProductEquipDetailForm) throws Exception
    {
    	AssetStdProductEquipDetailService assetStdProductEquipDetailService = (AssetStdProductEquipDetailService)getBean("assetStdProductEquipDetailService");
    	AssetStdProductCommonDTO assetStdProductCommonDTO = assetStdProductEquipDetailForm.getAssetStdProductCommonDTO(); 
    	AssetStdProductEquipDetailDTO  assetStdProductEquipDetailDTO = assetStdProductEquipDetailForm.getAssetStdProductEquipDetailDTO();
    	assetStdProductEquipDetailService.updateDetail(assetStdProductCommonDTO, assetStdProductEquipDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }

}