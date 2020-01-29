package dream.asset.rpt.maintcost.action;

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
import dream.asset.rpt.maintcost.dto.AssetRptMaintCostCommonDTO;
import dream.asset.rpt.maintcost.form.AssetRptMaintCostListForm;
import dream.asset.rpt.maintcost.service.AssetRptMaintCostListService;

/**
 * 수선유지비 집행현황 목록 - List Action
 * 
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/assetRptMaintCostList" name="assetRptMaintCostListForm"
 *                input="/dream/asset/rpt/maintcost/assetRptMaintCostList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="assetRptMaintCostList" path="/dream/asset/rpt/maintcost/assetRptMaintCostList.jsp"
 *                        redirect="false"
 */
public class AssetRptMaintCostListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND       = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AssetRptMaintCostListForm assetRptMaintCostListForm = (AssetRptMaintCostListForm) form;
        
        switch (assetRptMaintCostListForm.getStrutsAction())
        {
            case AssetRptMaintCostListAction.BASE_SET_HEADER:
                setHeader(request, response, assetRptMaintCostListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssetRptMaintCostListAction.LIST_FIND:
                findList(request, response, assetRptMaintCostListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case AssetRptMaintCostListAction.BASE_GRID_EXPORT:
                findList(request, response, assetRptMaintCostListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("assetRptMaintCostList");
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, AssetRptMaintCostListForm assetRptMaintCostListForm) throws IOException
    {
        super.setHeader(request, response, assetRptMaintCostListForm.getListId(), assetRptMaintCostListForm.getCurrentPageId()); 
    }
   
    /**
     * FIND LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param assetRptMaintCostListForm
     */
    
    private void findList(HttpServletRequest request, HttpServletResponse response, AssetRptMaintCostListForm assetRptMaintCostListForm, boolean excelExport) throws Exception
    {
        AssetRptMaintCostListService assetRptMaintCostListService = (AssetRptMaintCostListService) getBean("assetRptMaintCostListService");
        AssetRptMaintCostCommonDTO assetRptMaintCostCommonDTO = assetRptMaintCostListForm.getAssetRptMaintCostCommonDTO();
      
        //Paging
        assetRptMaintCostCommonDTO.setIsLoadMaxCount("Y".equals(assetRptMaintCostListForm.getIsLoadMaxCount())?true:false);
        assetRptMaintCostCommonDTO.setFirstRow(assetRptMaintCostListForm.getFirstRow());
        assetRptMaintCostCommonDTO.setOrderBy(assetRptMaintCostListForm.getOrderBy());
        assetRptMaintCostCommonDTO.setDirection(assetRptMaintCostListForm.getDirection());
        
        User user = getUser(request);
        List resultList = assetRptMaintCostListService.findList(assetRptMaintCostCommonDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(assetRptMaintCostListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = assetRptMaintCostListService.findTotalCount(assetRptMaintCostCommonDTO,user);
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,assetRptMaintCostListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
}