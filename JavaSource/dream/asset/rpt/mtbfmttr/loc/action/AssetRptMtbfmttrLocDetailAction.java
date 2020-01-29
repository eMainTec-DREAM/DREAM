package dream.asset.rpt.mtbfmttr.loc.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.asset.rpt.mtbfmttr.loc.dto.AssetRptMtbfmttrLocDetailDTO;
import dream.asset.rpt.mtbfmttr.loc.form.AssetRptMtbfmttrLocDetailForm;
import dream.asset.rpt.mtbfmttr.loc.service.AssetRptMtbfmttrLocDetailService;

/**
 * MTBF,MTTR(위치) 상세
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/assetRptMtbfmttrLocDetailList" name="assetRptMtbfmttrLocDetailForm"
 *                input="/dream/asset/rpt/mtbfmttr/loc/assetRptMtbfmttrLocDetailList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/assetRptMtbfmttrLocDetailChart" name="assetRptMtbfmttrLocDetailForm"
 *                input="/dream/asset/rpt/mtbfmttr/loc/assetRptMtbfmttrLocDetailChart.jsp" scope="request"
 *                validate="false"            
 */
public class AssetRptMtbfmttrLocDetailAction extends AuthAction
{
    public static final int MTTRMTBF_LOC_DETAIL_FIND 	= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        AssetRptMtbfmttrLocDetailForm assetRptMtbfmttrLocDetailForm = (AssetRptMtbfmttrLocDetailForm) form;
        switch (assetRptMtbfmttrLocDetailForm.getStrutsAction())
        {
            case AssetRptMtbfmttrLocDetailAction.MTTRMTBF_LOC_DETAIL_FIND:
                // 페이지 조회
                this.findDetail(request, response, assetRptMtbfmttrLocDetailForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssetRptMtbfmttrLocDetailAction.BASE_SET_HEADER:
                super.setHeader(request, response, assetRptMtbfmttrLocDetailForm.getListId(), assetRptMtbfmttrLocDetailForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssetRptMtbfmttrLocDetailAction.BASE_GRID_EXPORT:
                findDetail(request,response, assetRptMtbfmttrLocDetailForm);
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
     * @param assetRptMtbfmttrLocDetailForm
     */
    private void findDetail(HttpServletRequest request,HttpServletResponse response, AssetRptMtbfmttrLocDetailForm assetRptMtbfmttrLocDetailForm) throws Exception
    {
        AssetRptMtbfmttrLocDetailService assetRptMtbfmttrLocDetailService = (AssetRptMtbfmttrLocDetailService) getBean("assetRptMtbfmttrLocDetailService",request);
        
        AssetRptMtbfmttrLocDetailDTO assetRptMtbfmttrLocDetailDTO = assetRptMtbfmttrLocDetailForm.getAssetRptMtbfmttrLocDetailDTO();
        
        List resultList = assetRptMtbfmttrLocDetailService.findDetail(assetRptMtbfmttrLocDetailDTO, getUser(request));
        
        CommonUtil.makeJsonResult(resultList, request, response, assetRptMtbfmttrLocDetailForm.getListId());
    }
    
}