package dream.asset.rpt.mtbfmttr.equip.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.asset.rpt.mtbfmttr.equip.dto.AssetRptMtbfmttrEquipCommonDTO;
import dream.asset.rpt.mtbfmttr.equip.dto.AssetRptMtbfmttrEquipDetailDTO;
import dream.asset.rpt.mtbfmttr.equip.form.AssetRptMtbfmttrEquipDetailForm;
import dream.asset.rpt.mtbfmttr.equip.service.AssetRptMtbfmttrEquipDetailService;

/**
 * MTBF,MTTR(설비) 상세
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/assetRptMtbfmttrEquipDetailList" name="assetRptMtbfmttrEquipDetailForm"
 *                input="/dream/asset/rpt/mtbfmttr/equip/assetRptMtbfmttrEquipDetailList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/assetRptMtbfmttrEquipDetailChart" name="assetRptMtbfmttrEquipDetailForm"
 *                input="/dream/asset/rpt/mtbfmttr/equip/assetRptMtbfmttrEquipDetailChart.jsp" scope="request"
 *                validate="false"            
 */
public class AssetRptMtbfmttrEquipDetailAction extends AuthAction
{
    public static final int MTTRMTBF_EQUIP_DETAIL_FIND 	= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        AssetRptMtbfmttrEquipDetailForm assetRptMtbfmttrEquipDetailForm = (AssetRptMtbfmttrEquipDetailForm) form;
        switch (assetRptMtbfmttrEquipDetailForm.getStrutsAction())
        {
            case AssetRptMtbfmttrEquipDetailAction.MTTRMTBF_EQUIP_DETAIL_FIND:
                // 페이지 조회
                this.findDetail(request, response, assetRptMtbfmttrEquipDetailForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssetRptMtbfmttrEquipDetailAction.BASE_SET_HEADER:
                super.setHeader(request, response, assetRptMtbfmttrEquipDetailForm.getListId(), assetRptMtbfmttrEquipDetailForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssetRptMtbfmttrEquipDetailAction.BASE_GRID_EXPORT:
                findDetail(request,response, assetRptMtbfmttrEquipDetailForm, true);
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
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param assetRptMtbfmttrEquipDetailForm
     */
    private void findDetail(HttpServletRequest request,HttpServletResponse response, AssetRptMtbfmttrEquipDetailForm assetRptMtbfmttrEquipDetailForm, boolean excelExport) throws Exception
    {
        AssetRptMtbfmttrEquipDetailService assetRptMtbfmttrEquipDetailService = (AssetRptMtbfmttrEquipDetailService) getBean("assetRptMtbfmttrEquipDetailService",request);
        
        AssetRptMtbfmttrEquipDetailDTO assetRptMtbfmttrEquipDetailDTO = assetRptMtbfmttrEquipDetailForm.getAssetRptMtbfmttrEquipDetailDTO();
        AssetRptMtbfmttrEquipCommonDTO assetRptMtbfmttrEquipCommonDTO =assetRptMtbfmttrEquipDetailForm.getAssetRptMtbfmttrEquipCommonDTO();
        
        //Paging
        assetRptMtbfmttrEquipDetailDTO.setIsLoadMaxCount("Y".equals(assetRptMtbfmttrEquipDetailForm.getIsLoadMaxCount())?true:false);
        assetRptMtbfmttrEquipDetailDTO.setFirstRow(assetRptMtbfmttrEquipDetailForm.getFirstRow());
        assetRptMtbfmttrEquipDetailDTO.setOrderBy(assetRptMtbfmttrEquipDetailForm.getOrderBy());
        assetRptMtbfmttrEquipDetailDTO.setDirection(assetRptMtbfmttrEquipDetailForm.getDirection());
        
        List resultList = assetRptMtbfmttrEquipDetailService.findDetail(assetRptMtbfmttrEquipCommonDTO, assetRptMtbfmttrEquipDetailDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(assetRptMtbfmttrEquipDetailForm.getIsTotalCount()) == 0 && !excelExport) totalCount = assetRptMtbfmttrEquipDetailService.findTotalCount(assetRptMtbfmttrEquipCommonDTO, assetRptMtbfmttrEquipDetailDTO, getUser(request));
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, assetRptMtbfmttrEquipDetailForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
}