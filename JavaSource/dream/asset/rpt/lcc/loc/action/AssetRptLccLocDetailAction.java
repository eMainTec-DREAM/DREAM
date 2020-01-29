package dream.asset.rpt.lcc.loc.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.asset.rpt.lcc.loc.dto.AssetRptLccLocDetailDTO;
import dream.asset.rpt.lcc.loc.form.AssetRptLccLocDetailForm;
import dream.asset.rpt.lcc.loc.service.AssetRptLccLocDetailService;

/**
 * 고장TOP(위치) 상세
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/assetRptLccLocDetailList" name="assetRptLccLocDetailForm"
 *                input="/dream/asset/rpt/lcc/loc/assetRptLccLocDetailList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/assetRptLccLocDetailChart" name="assetRptLccLocDetailForm"
 *                input="/dream/asset/rpt/lcc/loc/assetRptLccLocDetailChart.jsp" scope="request"
 *                validate="false"            
 * @struts:action path="/assetRptLccLocWorkTimeDetailChart" name="assetRptLccLocDetailForm"
 *                input="/dream/asset/rpt/lcc/loc/assetRptLccLocWorkTimeDetailChart.jsp" scope="request"
 *                validate="false"            
 * @struts:action path="/assetRptLccLocMaintAmtDetailChart" name="assetRptLccLocDetailForm"
 *                input="/dream/asset/rpt/lcc/loc/assetRptLccLocMaintAmtDetailChart.jsp" scope="request"
 *                validate="false"            
 * @struts:action path="/assetRptLccLocFailCodeDetailChart" name="assetRptLccLocDetailForm"
 *                input="/dream/asset/rpt/lcc/loc/assetRptLccLocFailCodeDetailChart.jsp" scope="request"
 *                validate="false"            
 */
public class AssetRptLccLocDetailAction extends AuthAction
{
    public static final int LCC_LOC_DETAIL_FIND 	= 1001;
    
    public static final int LCC_LOC_MO_FIND         = 1002;
    
    public static final int LCC_LOC_CA_FIND         = 1003;
    
    public static final int LCC_LOC_RE_FIND         = 1004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        AssetRptLccLocDetailForm assetRptLccLocDetailForm = (AssetRptLccLocDetailForm) form;
        switch (assetRptLccLocDetailForm.getStrutsAction())
        {
            case AssetRptLccLocDetailAction.LCC_LOC_DETAIL_FIND:
                // 페이지 조회
                this.findDetail(request, response, assetRptLccLocDetailForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssetRptLccLocDetailAction.LCC_LOC_MO_FIND:
                this.findMo(request, response, assetRptLccLocDetailForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssetRptLccLocDetailAction.LCC_LOC_CA_FIND:
                this.findCa(request, response, assetRptLccLocDetailForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssetRptLccLocDetailAction.LCC_LOC_RE_FIND:
                this.findRe(request, response, assetRptLccLocDetailForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssetRptLccLocDetailAction.BASE_SET_HEADER:
                super.setHeader(request, response, assetRptLccLocDetailForm.getListId(), assetRptLccLocDetailForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssetRptLccLocDetailAction.BASE_GRID_EXPORT:
                findDetail(request,response, assetRptLccLocDetailForm, true);
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
     * @param assetRptLccLocDetailForm
     */
    private void findDetail(HttpServletRequest request,HttpServletResponse response, AssetRptLccLocDetailForm assetRptLccLocDetailForm, boolean excelExport) throws Exception
    {
        AssetRptLccLocDetailService assetRptLccLocDetailService = (AssetRptLccLocDetailService) getBean("assetRptLccLocDetailService");
        
        AssetRptLccLocDetailDTO assetRptLccLocDetailDTO = assetRptLccLocDetailForm.getAssetRptLccLocDetailDTO();
        
        List resultList = assetRptLccLocDetailService.findDetail(assetRptLccLocDetailDTO, getUser(request));
        
        if(excelExport) super.makeGridExport(resultList, request, response,assetRptLccLocDetailForm.getListId(),assetRptLccLocDetailForm.getCurrentPageId(), assetRptLccLocDetailForm.getFileName());
        else super.makeJsonResult(resultList, request, response, assetRptLccLocDetailForm.getListId());
    }
    
    private void findMo(HttpServletRequest request,HttpServletResponse response, AssetRptLccLocDetailForm assetRptLccLocDetailForm) throws Exception
    {
        AssetRptLccLocDetailService assetRptLccLocDetailService = (AssetRptLccLocDetailService) getBean("assetRptLccLocDetailService");
        
        AssetRptLccLocDetailDTO assetRptLccLocDetailDTO = assetRptLccLocDetailForm.getAssetRptLccLocDetailDTO();
        
        List resultList = assetRptLccLocDetailService.findMo(assetRptLccLocDetailDTO, getUser(request));
        
        CommonUtil.makeJsonResult(resultList, request, response, assetRptLccLocDetailForm.getListId());
    }
    
    private void findCa(HttpServletRequest request,HttpServletResponse response, AssetRptLccLocDetailForm assetRptLccLocDetailForm) throws Exception
    {
        AssetRptLccLocDetailService assetRptLccLocDetailService = (AssetRptLccLocDetailService) getBean("assetRptLccLocDetailService");
        
        AssetRptLccLocDetailDTO assetRptLccLocDetailDTO = assetRptLccLocDetailForm.getAssetRptLccLocDetailDTO();
        
        List resultList = assetRptLccLocDetailService.findCa(assetRptLccLocDetailDTO, getUser(request));
        
        CommonUtil.makeJsonResult(resultList, request, response, assetRptLccLocDetailForm.getListId());
    }
    
    private void findRe(HttpServletRequest request,HttpServletResponse response, AssetRptLccLocDetailForm assetRptLccLocDetailForm) throws Exception
    {
        AssetRptLccLocDetailService assetRptLccLocDetailService = (AssetRptLccLocDetailService) getBean("assetRptLccLocDetailService");
        
        AssetRptLccLocDetailDTO assetRptLccLocDetailDTO = assetRptLccLocDetailForm.getAssetRptLccLocDetailDTO();
        
        List resultList = assetRptLccLocDetailService.findRe(assetRptLccLocDetailDTO, getUser(request));
        
        CommonUtil.makeJsonResult(resultList, request, response, assetRptLccLocDetailForm.getListId());
    }
    
}