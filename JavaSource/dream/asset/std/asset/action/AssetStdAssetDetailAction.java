package dream.asset.std.asset.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.asset.std.asset.dto.AssetStdAssetDetailDTO;
import dream.asset.std.asset.form.AssetStdAssetDetailForm;
import dream.asset.std.asset.service.AssetStdAssetDetailService;

/**
 * ȸ���ڻ� - �� action
 * 
 * @author ghlee
 * @version $Id: $
 * @since 1.0
 * @struts:action path="/assetStdAssetDetail" name="assetStdAssetDetailForm"
 *                input="/dream/asset/std/asset/assetStdAssetDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="assetStdAssetDetail" path="/dream/asset/std/asset/assetStdAssetDetail.jsp"
 *                        redirect="false"
 */
public class AssetStdAssetDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int ASSET_DETAIL_INIT 		= 8001;
    /** ���� */
    public static final int ASSET_DETAIL_INPUT 		= 5002;
    /** ���� */
    public static final int ASSET_DETAIL_UPDATE 	= 6003;
    /** �ߺ� üũ */
    public static final int ASSET_DETAIL_CHECK 		= 1004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        AssetStdAssetDetailForm assetStdAssetDetailForm = (AssetStdAssetDetailForm) form;
        
        super.updateAudit(assetStdAssetDetailForm.getAssetStdAssetDetailDTO().getAuditKey()==""?assetStdAssetDetailForm.getAssetStdAssetCommonDTO().getAuditKey():assetStdAssetDetailForm.getAssetStdAssetDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (assetStdAssetDetailForm.getStrutsAction())
        {
            case AssetStdAssetDetailAction.ASSET_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, assetStdAssetDetailForm);
                returnActionForward = mapping.findForward("assetStdAssetDetail");
                break;
            case AssetStdAssetDetailAction.ASSET_DETAIL_INPUT:
            	insertDetail(assetStdAssetDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case AssetStdAssetDetailAction.ASSET_DETAIL_UPDATE:
            	updateDetail(assetStdAssetDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case AssetStdAssetDetailAction.ASSET_DETAIL_CHECK:
            	validAssetNo(assetStdAssetDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("assetStdAssetDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * ȸ���ڻ� �� ��ȸ
     * @author  ghlee
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param assetStdAssetDetailForm
     */
    private void findDetail(HttpServletRequest request, AssetStdAssetDetailForm assetStdAssetDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	AssetStdAssetDetailService assetStdAssetDetailService = (AssetStdAssetDetailService)getBean("assetStdAssetDetailService");
    	
        String assetId = assetStdAssetDetailForm.getAssetStdAssetCommonDTO().getAssetId();
        
        // ��ȸ�� �� �κ�
        AssetStdAssetDetailDTO assetStdAssetDetailDTO = assetStdAssetDetailService.findDetail(getUser(request), assetId);
        
        // ��ȸ�� ��  �����Ѵ�.
        assetStdAssetDetailForm.setAssetStdAssetDetailDTO(assetStdAssetDetailDTO);
    }
    /**
     * detail insert
     * @author  
     * @version $Id:  $
     * @since   1.0
     * 
     * @param assetStdAssetDetailForm
     * @param request
     */
    private void insertDetail(AssetStdAssetDetailForm assetStdAssetDetailForm, HttpServletRequest request) throws Exception
    {
        AssetStdAssetDetailService assetStdAssetDetailService = (AssetStdAssetDetailService) getBean("assetStdAssetDetailService");
        
        AssetStdAssetDetailDTO assetStdAssetDetailDTO = assetStdAssetDetailForm.getAssetStdAssetDetailDTO();
        
        assetStdAssetDetailService.insertDetail(assetStdAssetDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * detail update
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param assetStdAssetDetailForm
     * @param request
     */
    private void updateDetail(AssetStdAssetDetailForm assetStdAssetDetailForm, HttpServletRequest request) throws Exception
    {
    	AssetStdAssetDetailService assetStdAssetDetailService = (AssetStdAssetDetailService) getBean("assetStdAssetDetailService");
        
        AssetStdAssetDetailDTO assetStdAssetDetailDTO = assetStdAssetDetailForm.getAssetStdAssetDetailDTO();
        
        assetStdAssetDetailService.updateDetail(assetStdAssetDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * valid asset no
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param assetStdAssetDetailForm
     * @param request
     */
    private void validAssetNo(AssetStdAssetDetailForm assetStdAssetDetailForm, HttpServletRequest request) throws Exception
    {
    	AssetStdAssetDetailService assetStdAssetDetailService = (AssetStdAssetDetailService) getBean("assetStdAssetDetailService");
        
        AssetStdAssetDetailDTO assetStdAssetDetailDTO = assetStdAssetDetailForm.getAssetStdAssetDetailDTO();
        
        String isValid = assetStdAssetDetailService.validAssetNo(assetStdAssetDetailDTO, getUser(request));
        
        setAjaxDesc(request, isValid);
    }
    
}