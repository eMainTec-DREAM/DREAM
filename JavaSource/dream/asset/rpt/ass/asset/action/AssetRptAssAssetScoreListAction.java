package dream.asset.rpt.ass.asset.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.asset.rpt.ass.asset.dto.AssetRptAssAssetScoreCommonDTO;
import dream.asset.rpt.ass.asset.form.AssetRptAssAssetScoreListForm;
import dream.asset.rpt.ass.asset.service.AssetRptAssAssetScoreListService;

/**
 * AssetRptAssAssetScoreCont Page - List Action
 * 
 * @author nhkim8548
 * @version $Id: AssetRptAssAssetScoreListAction.java,v 1.0 2018/08/23 15:37:40 nhkim8548 Exp $
 * @since 1.0
 * @struts:action path="/assetRptAssAssetScoreList" name="assetRptAssAssetScoreListForm"
 *                input="/dream/asset/rpt/ass/asset/assetRptAssAssetScoreList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="assetRptAssAssetScoreList" path="/dream/asset/rpt/ass/asset/assetRptAssAssetScoreList.jsp"
 *                        redirect="false"
 */
public class AssetRptAssAssetScoreListAction extends AuthAction
{
    /** Á¶È¸ */
    public static final int LIST_FIND               = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AssetRptAssAssetScoreListForm assetRptAssAssetScoreListForm = (AssetRptAssAssetScoreListForm) form;
        
        switch (assetRptAssAssetScoreListForm.getStrutsAction())
        {
            case AssetRptAssAssetScoreListAction.BASE_SET_HEADER:
                setHeader(request, response, assetRptAssAssetScoreListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssetRptAssAssetScoreListAction.BASE_GRID_EXPORT:
                findList(request, response, assetRptAssAssetScoreListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            case AssetRptAssAssetScoreListAction.LIST_FIND:
                findList(request, response, assetRptAssAssetScoreListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break; 
            default:
                returnActionForward = mapping.findForward("assetRptAssAssetScoreList");
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, AssetRptAssAssetScoreListForm assetRptAssAssetScoreListForm) throws IOException
    {
        super.setHeader(request, response, assetRptAssAssetScoreListForm.getListId(), assetRptAssAssetScoreListForm.getCurrentPageId()); 
    }
   
    /**
     * FIND LIST
     * @author  nhkim8548
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param excelExport
     * @param assetRptAssAssetScoreListForm
     */
    
    private void findList(HttpServletRequest request, HttpServletResponse response, AssetRptAssAssetScoreListForm assetRptAssAssetScoreListForm, boolean excelExport) throws Exception
    {
        AssetRptAssAssetScoreListService assetRptAssAssetScoreListService = (AssetRptAssAssetScoreListService) getBean("assetRptAssAssetScoreListService");
        
        AssetRptAssAssetScoreCommonDTO assetRptAssAssetScoreCommonDTO = assetRptAssAssetScoreListForm.getAssetRptAssAssetScoreCommonDTO();
      
        //Paging
        assetRptAssAssetScoreCommonDTO.setIsLoadMaxCount("Y".equals(assetRptAssAssetScoreListForm.getIsLoadMaxCount())?true:false);
        assetRptAssAssetScoreCommonDTO.setFirstRow(assetRptAssAssetScoreListForm.getFirstRow());
        assetRptAssAssetScoreCommonDTO.setOrderBy(assetRptAssAssetScoreListForm.getOrderBy());
        assetRptAssAssetScoreCommonDTO.setDirection(assetRptAssAssetScoreListForm.getDirection());
        
        User user = getUser(request);
        List resultList = assetRptAssAssetScoreListService.findList(assetRptAssAssetScoreCommonDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(assetRptAssAssetScoreListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = assetRptAssAssetScoreListService.findTotalCount(assetRptAssAssetScoreCommonDTO, user);
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,assetRptAssAssetScoreListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
}