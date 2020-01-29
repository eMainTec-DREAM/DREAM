package dream.asset.rpt.lcc.loc.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.asset.rpt.lcc.loc.dto.AssetRptLccLocCommonDTO;
import dream.asset.rpt.lcc.loc.form.AssetRptLccLocListForm;
import dream.asset.rpt.lcc.loc.service.AssetRptLccLocListService;

/**
 * 고장TOP(위치)
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/assetRptLccLocList" name="assetRptLccLocListForm"
 *                input="/dream/asset/rpt/lcc/loc/assetRptLccLocList.jsp" scope="request"
 *                validate="false"
 */
public class AssetRptLccLocListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int LCC_LOC_LIST_FIND 			= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        AssetRptLccLocListForm assetRptLccLocListForm = (AssetRptLccLocListForm) form;
        
        switch (assetRptLccLocListForm.getStrutsAction())
        {
        
            case AssetRptLccLocListAction.LCC_LOC_LIST_FIND:
                findList(request,response, assetRptLccLocListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssetRptLccLocListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, assetRptLccLocListForm.getListId(), assetRptLccLocListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssetRptLccLocListAction.BASE_GRID_EXPORT:
            	findList(request,response, assetRptLccLocListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param assetRptLccLocListForm
     * @param excelExport
     * @throws Exception
     */
    private void findList(HttpServletRequest request,HttpServletResponse response, AssetRptLccLocListForm assetRptLccLocListForm, boolean excelExport) throws Exception
    {
        AssetRptLccLocListService assetRptLccLocListService = (AssetRptLccLocListService) getBean("assetRptLccLocListService");
        
        AssetRptLccLocCommonDTO assetRptLccLocCommonDTO = assetRptLccLocListForm.getAssetRptLccLocCommonDTO();
        
        //Paging
        assetRptLccLocCommonDTO.setIsLoadMaxCount("Y".equals(assetRptLccLocListForm.getIsLoadMaxCount())?true:false);
        assetRptLccLocCommonDTO.setFirstRow(assetRptLccLocListForm.getFirstRow());
        assetRptLccLocCommonDTO.setOrderBy(assetRptLccLocListForm.getOrderBy());
        assetRptLccLocCommonDTO.setDirection(assetRptLccLocListForm.getDirection());
        
        List resultList = assetRptLccLocListService.findList(assetRptLccLocCommonDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(assetRptLccLocListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = assetRptLccLocListService.findTotalCount(assetRptLccLocCommonDTO,getUser(request));
        
        if(excelExport) super.makeGridExport(resultList, request, response,assetRptLccLocListForm.getListId(),assetRptLccLocListForm.getCurrentPageId(), assetRptLccLocListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
}