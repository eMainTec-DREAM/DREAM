package dream.asset.rpt.mtbfmttr.usdept.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.asset.rpt.mtbfmttr.usdept.dto.AssetRptMtbfmttrUsDeptCommonDTO;
import dream.asset.rpt.mtbfmttr.usdept.dto.AssetRptMtbfmttrUsDeptDetailDTO;
import dream.asset.rpt.mtbfmttr.usdept.form.AssetRptMtbfmttrUsDeptDetailForm;
import dream.asset.rpt.mtbfmttr.usdept.service.AssetRptMtbfmttrUsDeptDetailService;

/**
 * MTBF,MTTR(사용부서) 상세
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/assetRptMtbfmttrUsDeptDetailList" name="assetRptMtbfmttrUsDeptDetailForm"
 *                input="/dream/asset/rpt/mtbfmttr/usdept/assetRptMtbfmttrUsDeptDetailList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/assetRptMtbfmttrUsDeptDetailChart" name="assetRptMtbfmttrUsDeptDetailForm"
 *                input="/dream/asset/rpt/mtbfmttr/usdept/assetRptMtbfmttrUsDeptDetailChart.jsp" scope="request"
 *                validate="false"            
 */
public class AssetRptMtbfmttrUsDeptDetailAction extends AuthAction
{
    public static final int MTTRMTBF_EQUIP_DETAIL_FIND  = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        AssetRptMtbfmttrUsDeptDetailForm assetRptMtbfmttrUsDeptDetailForm = (AssetRptMtbfmttrUsDeptDetailForm) form;
        switch (assetRptMtbfmttrUsDeptDetailForm.getStrutsAction())
        {
            case AssetRptMtbfmttrUsDeptDetailAction.MTTRMTBF_EQUIP_DETAIL_FIND:
                // 페이지 조회
                this.findDetail(request, response, assetRptMtbfmttrUsDeptDetailForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssetRptMtbfmttrUsDeptDetailAction.BASE_SET_HEADER:
                super.setHeader(request, response, assetRptMtbfmttrUsDeptDetailForm.getListId(), assetRptMtbfmttrUsDeptDetailForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssetRptMtbfmttrUsDeptDetailAction.BASE_GRID_EXPORT:
                findDetail(request,response, assetRptMtbfmttrUsDeptDetailForm, true);
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
     * @param assetRptMtbfmttrUsDeptDetailForm
     */
    private void findDetail(HttpServletRequest request,HttpServletResponse response, AssetRptMtbfmttrUsDeptDetailForm assetRptMtbfmttrUsDeptDetailForm, boolean excelExport) throws Exception
    {
        AssetRptMtbfmttrUsDeptDetailService assetRptMtbfmttrUsDeptDetailService = (AssetRptMtbfmttrUsDeptDetailService) getBean("assetRptMtbfmttrUsDeptDetailService",request);
        
        AssetRptMtbfmttrUsDeptDetailDTO assetRptMtbfmttrUsDeptDetailDTO = assetRptMtbfmttrUsDeptDetailForm.getAssetRptMtbfmttrUsDeptDetailDTO();
        AssetRptMtbfmttrUsDeptCommonDTO assetRptMtbfmttrUsDeptCommonDTO = assetRptMtbfmttrUsDeptDetailForm.getAssetRptMtbfmttrUsDeptCommonDTO();
        
        List resultList = assetRptMtbfmttrUsDeptDetailService.findDetail(assetRptMtbfmttrUsDeptCommonDTO, assetRptMtbfmttrUsDeptDetailDTO, getUser(request));
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,assetRptMtbfmttrUsDeptDetailForm);
        else CommonUtil.makeJsonResult(resultList, request, response, assetRptMtbfmttrUsDeptDetailForm.getListId());
    }
    
}