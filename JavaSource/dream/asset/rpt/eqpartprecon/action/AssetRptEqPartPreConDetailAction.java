package dream.asset.rpt.eqpartprecon.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.asset.rpt.eqpartprecon.dto.AssetRptEqPartPreConDetailDTO;
import dream.asset.rpt.eqpartprecon.form.AssetRptEqPartPreConListForm;
import dream.asset.rpt.eqpartprecon.service.AssetRptEqPartPreConDetailService;

/**
 * AssetRptEqPartPreCon Page - Detail Action
 * 
 * @author youngjoo38
 * @version $Id: $
 * @since 1.0
 * @struts:action path="/assetRptEqPartPreConDetailPartList" name="assetRptEqPartPreConListForm"
 *                input="/dream/asset/rpt/eqpartprecon/assetRptEqPartPreConDetailPartList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/assetRptEqPartPreConDetailStockList" name="assetRptEqPartPreConListForm"
 *                input="/dream/asset/rpt/eqpartprecon/assetRptEqPartPreConDetailStockList.jsp" scope="request"
 *                validate="false"
 */
public class AssetRptEqPartPreConDetailAction extends AuthAction
{
    /** 상세조회 (설비부위-부품)*/
    public static final int PART_DETAIL_FIND       = 1001;
    /** 상세조회 (재고현황)*/
    public static final int STOCK_DETAIL_FIND      = 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AssetRptEqPartPreConListForm assetRptEqPartPreConListForm = (AssetRptEqPartPreConListForm) form;
        
        switch (assetRptEqPartPreConListForm.getStrutsAction())
        {
            case AssetRptEqPartPreConDetailAction.BASE_SET_HEADER:
                setHeader(request, response, assetRptEqPartPreConListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssetRptEqPartPreConDetailAction.PART_DETAIL_FIND:
                findPartDetail(request, response, assetRptEqPartPreConListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case AssetRptEqPartPreConDetailAction.STOCK_DETAIL_FIND:
                findStockDetail(request, response, assetRptEqPartPreConListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case AssetRptEqPartPreConDetailAction.BASE_GRID_EXPORT:
                findPartDetail(request, response, assetRptEqPartPreConListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, AssetRptEqPartPreConListForm assetRptEqPartPreConListForm) throws IOException
    {
        super.setHeader(request, response, assetRptEqPartPreConListForm.getListId(), assetRptEqPartPreConListForm.getCurrentPageId()); 
    }
   
    /**
     * PART FIND DETAIL
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param assetRptEqPartPreConListForm
     */
    
    private void findPartDetail(HttpServletRequest request, HttpServletResponse response, AssetRptEqPartPreConListForm assetRptEqPartPreConListForm, boolean excelExport) throws Exception
    {
        AssetRptEqPartPreConDetailService assetRptEqPartPreConDetailService = (AssetRptEqPartPreConDetailService) getBean("assetRptEqPartPreConDetailService");
        AssetRptEqPartPreConDetailDTO assetRptEqPartPreConDetailDTO = assetRptEqPartPreConListForm.getAssetRptEqPartPreConDetailDTO();
      
        User user = getUser(request);
        List resultList = assetRptEqPartPreConDetailService.findPartDetail(assetRptEqPartPreConDetailDTO, user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,assetRptEqPartPreConListForm.getListId(),assetRptEqPartPreConListForm.getCurrentPageId(), assetRptEqPartPreConListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, assetRptEqPartPreConListForm.getListId());
    }
    /**
     * STOCK FIND DETAIL
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param assetRptEqPartPreConListForm
     */
    
    private void findStockDetail(HttpServletRequest request, HttpServletResponse response, AssetRptEqPartPreConListForm assetRptEqPartPreConListForm, boolean excelExport) throws Exception
    {
        AssetRptEqPartPreConDetailService assetRptEqPartPreConDetailService = (AssetRptEqPartPreConDetailService) getBean("assetRptEqPartPreConDetailService");
        AssetRptEqPartPreConDetailDTO assetRptEqPartPreConDetailDTO = assetRptEqPartPreConListForm.getAssetRptEqPartPreConDetailDTO();
        
        User user = getUser(request);
        List resultList = assetRptEqPartPreConDetailService.findStockDetail(assetRptEqPartPreConDetailDTO, user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,assetRptEqPartPreConListForm.getListId(),assetRptEqPartPreConListForm.getCurrentPageId(), assetRptEqPartPreConListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, assetRptEqPartPreConListForm.getListId());
    }
}