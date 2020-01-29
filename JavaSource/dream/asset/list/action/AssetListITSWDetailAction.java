package dream.asset.list.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.asset.list.dto.AssetListITSWDetailDTO;
import dream.asset.list.dto.AssetListITSWListDTO;
import dream.asset.list.form.AssetListITSWDetailForm;
import dream.asset.list.service.AssetListITSWDetailService;

/**
 * Detail Action
 * 
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @struts:action path="/assetListITSWDetail" name="assetListITSWDetailForm"
 *                input="/dream/asset/list/assetListITSWDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="assetListITSWDetail" path="/dream/asset/list/assetListITSWDetail.jsp"
 *                        redirect="false"
 */
public class AssetListITSWDetailAction extends AuthAction
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
        AssetListITSWDetailForm assetListITSWDetailForm = (AssetListITSWDetailForm) form;
        
        super.updateAudit(assetListITSWDetailForm.getAssetListITSWDetailDTO().getAuditKey()==""?assetListITSWDetailForm.getAssetListITSWListDTO().getAuditKey():assetListITSWDetailForm.getAssetListITSWDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        switch (assetListITSWDetailForm.getStrutsAction())
        {
            case AssetListITSWDetailAction.DETAIL_INIT:
                this.findDetail(request, response, assetListITSWDetailForm);
                returnActionForward = mapping.findForward("assetListITSWDetail");
                break;
            case AssetListITSWDetailAction.DETAIL_INPUT:
            	insertDetail(request, response, assetListITSWDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case AssetListITSWDetailAction.DETAIL_UPDATE:
            	updateDetail(request, response, assetListITSWDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("assetListITSWDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * FIND DETAIL
     * @param request
     * @param response
     * @param assetListITSWDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, AssetListITSWDetailForm assetListITSWDetailForm) throws Exception 
    {   
    	AssetListITSWDetailService assetListITSWDetailService = (AssetListITSWDetailService)getBean("assetListITSWDetailService");
    	
    	AssetListITSWListDTO assetListITSWListDTO = assetListITSWDetailForm.getAssetListITSWListDTO();
    	AssetListITSWDetailDTO assetListITSWDetailDTO = assetListITSWDetailService.findDetail(assetListITSWListDTO, getUser(request));
    	assetListITSWDetailForm.setAssetListITSWDetailDTO(assetListITSWDetailDTO);
    }

    /**
     * INSERT DETAIL
     * @param request
     * @param response
     * @param assetListITSWDetailForm
     * @throws Exception
     */
    private void insertDetail(HttpServletRequest request, HttpServletResponse response, AssetListITSWDetailForm assetListITSWDetailForm) throws Exception
    {
    	AssetListITSWDetailService assetListITSWDetailService = (AssetListITSWDetailService)getBean("assetListITSWDetailService");
    	AssetListITSWDetailDTO  assetListITSWDetailDTO = assetListITSWDetailForm.getAssetListITSWDetailDTO(); 
    	assetListITSWDetailService.insertDetail(assetListITSWDetailDTO, getUser(request));
        setAjaxStatus(request);
    }

    /**
     * UPDATE DETAIL
     * @param request
     * @param response
     * @param assetListITSWDetailForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, AssetListITSWDetailForm assetListITSWDetailForm) throws Exception
    {
    	AssetListITSWDetailService assetListITSWDetailService = (AssetListITSWDetailService)getBean("assetListITSWDetailService");
    	AssetListITSWDetailDTO  assetListITSWDetailDTO = assetListITSWDetailForm.getAssetListITSWDetailDTO();
    	assetListITSWDetailService.updateDetail(assetListITSWDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }

}