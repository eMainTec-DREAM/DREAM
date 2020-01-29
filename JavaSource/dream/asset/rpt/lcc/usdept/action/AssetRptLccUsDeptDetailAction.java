package dream.asset.rpt.lcc.usdept.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.asset.rpt.lcc.usdept.dto.AssetRptLccUsDeptCommonDTO;
import dream.asset.rpt.lcc.usdept.dto.AssetRptLccUsDeptDetailDTO;
import dream.asset.rpt.lcc.usdept.form.AssetRptLccUsDeptDetailForm;
import dream.asset.rpt.lcc.usdept.service.AssetRptLccUsDeptDetailService;

/**
 * 고장TOP(사용부서) 상세
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/assetRptLccUsDeptDetailList" name="assetRptLccUsDeptDetailForm"
 *                input="/dream/asset/rpt/lcc/usdept/assetRptLccUsDeptDetailList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/assetRptLccUsDeptDetailChart" name="assetRptLccUsDeptDetailForm"
 *                input="/dream/asset/rpt/lcc/usdept/assetRptLccUsDeptDetailChart.jsp" scope="request"
 *                validate="false"            
 * @struts:action path="/assetRptLccUsDeptWorkTimeDetailChart" name="assetRptLccUsDeptDetailForm"
 *                input="/dream/asset/rpt/lcc/usdept/assetRptLccUsDeptWorkTimeDetailChart.jsp" scope="request"
 *                validate="false"             
 * @struts:action path="/assetRptLccUsDeptMaintAmtDetailChart" name="assetRptLccUsDeptDetailForm"
 *                input="/dream/asset/rpt/lcc/usdept/assetRptLccUsDeptMaintAmtDetailChart.jsp" scope="request"
 *                validate="false"            
 */
public class AssetRptLccUsDeptDetailAction extends AuthAction
{
    public static final int LCC_EQUIP_DETAIL_FIND 	= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        AssetRptLccUsDeptDetailForm assetRptLccUsDeptDetailForm = (AssetRptLccUsDeptDetailForm) form;
        switch (assetRptLccUsDeptDetailForm.getStrutsAction())
        {
            case AssetRptLccUsDeptDetailAction.LCC_EQUIP_DETAIL_FIND:
                // 페이지 조회
                this.findDetail(request, response, assetRptLccUsDeptDetailForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssetRptLccUsDeptDetailAction.BASE_SET_HEADER:
                super.setHeader(request, response, assetRptLccUsDeptDetailForm.getListId(), assetRptLccUsDeptDetailForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssetRptLccUsDeptDetailAction.BASE_GRID_EXPORT:
                findDetail(request,response, assetRptLccUsDeptDetailForm, true);
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
     * @param assetRptLccUsDeptDetailForm
     */
    private void findDetail(HttpServletRequest request,HttpServletResponse response, AssetRptLccUsDeptDetailForm assetRptLccUsDeptDetailForm, boolean excelExport) throws Exception
    {
        AssetRptLccUsDeptDetailService assetRptLccUsDeptDetailService = (AssetRptLccUsDeptDetailService) getBean("assetRptLccUsDeptDetailService");
        
        AssetRptLccUsDeptDetailDTO assetRptLccUsDeptDetailDTO = assetRptLccUsDeptDetailForm.getAssetRptLccUsDeptDetailDTO();
        AssetRptLccUsDeptCommonDTO assetRptLccUsDeptCommonDTO = assetRptLccUsDeptDetailForm.getAssetRptLccUsDeptCommonDTO();
        
        List resultList = assetRptLccUsDeptDetailService.findDetail(assetRptLccUsDeptCommonDTO, assetRptLccUsDeptDetailDTO, getUser(request));
        
        if(excelExport) super.makeGridExport(resultList, request, response,assetRptLccUsDeptDetailForm.getListId(),assetRptLccUsDeptDetailForm.getCurrentPageId(), assetRptLccUsDeptDetailForm.getFileName());
        else super.makeJsonResult(resultList, request, response, assetRptLccUsDeptDetailForm.getListId());
    }
    
}