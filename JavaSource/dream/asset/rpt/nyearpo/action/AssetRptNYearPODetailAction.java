package dream.asset.rpt.nyearpo.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.asset.rpt.nyearpo.dto.AssetRptNYearPOCommonDTO;
import dream.asset.rpt.nyearpo.dto.AssetRptNYearPODetailDTO;
import dream.asset.rpt.nyearpo.form.AssetRptNYearPODetailForm;
import dream.asset.rpt.nyearpo.service.AssetRptNYearPODetailService;

/**
 * N Year Spare Part 상세
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/assetRptNYearPartsList" name="assetRptNYearPODetailForm"
 *                input="/dream/asset/rpt/nyearpo/assetRptNYearPartsList.jsp" scope="request"
 *                validate="false"
 */
public class AssetRptNYearPODetailAction extends AuthAction
{
    public static final int DETAIL_FIND 	= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        AssetRptNYearPODetailForm assetRptNYearPODetailForm = (AssetRptNYearPODetailForm) form;
        switch (assetRptNYearPODetailForm.getStrutsAction())
        {
            case AssetRptNYearPODetailAction.DETAIL_FIND:
                // 페이지 조회
                this.findDetail(request, response, assetRptNYearPODetailForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssetRptNYearPODetailAction.BASE_SET_HEADER:
                super.setHeader(request, response, assetRptNYearPODetailForm.getListId(), assetRptNYearPODetailForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssetRptNYearPODetailAction.BASE_GRID_EXPORT:
                findDetail(request,response, assetRptNYearPODetailForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 조회 
     * @author youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param assetRptNYearPODetailForm
     */
    private void findDetail(HttpServletRequest request,HttpServletResponse response, AssetRptNYearPODetailForm assetRptNYearPODetailForm, boolean excelExport) throws Exception
    {
        AssetRptNYearPODetailService assetRptNYearPODetailService = (AssetRptNYearPODetailService) getBean("assetRptNYearPODetailService");
        
        AssetRptNYearPODetailDTO assetRptNYearPODetailDTO = assetRptNYearPODetailForm.getAssetRptNYearPODetailDTO();
        AssetRptNYearPOCommonDTO assetRptNYearPOCommonDTO = assetRptNYearPODetailForm.getAssetRptNYearPOCommonDTO();
        
        List resultList = assetRptNYearPODetailService.findDetail(assetRptNYearPOCommonDTO, assetRptNYearPODetailDTO, getUser(request));
        
        if(excelExport) super.makeGridExport(resultList, request, response,assetRptNYearPODetailForm.getListId(),assetRptNYearPODetailForm.getCurrentPageId(), assetRptNYearPODetailForm.getFileName());
        else super.makeJsonResult(resultList, request, response, assetRptNYearPODetailForm.getListId());
    }
    
}