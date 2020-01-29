package dream.asset.rpt.nyearpo.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.asset.rpt.nyearpo.dto.AssetRptNYearPOCommonDTO;
import dream.asset.rpt.nyearpo.form.AssetRptNYearPOListForm;
import dream.asset.rpt.nyearpo.service.AssetRptNYearPOListService;

/**
 * N Year Spare Part
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/assetRptNYearPOList" name="assetRptNYearPOListForm"
 *                input="/dream/asset/rpt/nyearpo/assetRptNYearPOList.jsp" scope="request"
 *                validate="false"
 */
public class AssetRptNYearPOListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int LCC_EQUIP_LIST_FIND 			= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        AssetRptNYearPOListForm assetRptNYearPOListForm = (AssetRptNYearPOListForm) form;
        
        switch (assetRptNYearPOListForm.getStrutsAction())
        {
        
            case AssetRptNYearPOListAction.LCC_EQUIP_LIST_FIND:
                findList(request,response, assetRptNYearPOListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssetRptNYearPOListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, assetRptNYearPOListForm.getListId(), assetRptNYearPOListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssetRptNYearPOListAction.BASE_GRID_EXPORT:
            	findList(request,response, assetRptNYearPOListForm, true);
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
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param assetRptNYearPOListForm
     * @param excelExport
     * @throws Exception
     */
    private void findList(HttpServletRequest request,HttpServletResponse response, AssetRptNYearPOListForm assetRptNYearPOListForm, boolean excelExport) throws Exception
    {
        AssetRptNYearPOListService assetRptNYearPOListService = (AssetRptNYearPOListService) getBean("assetRptNYearPOListService");
        
        AssetRptNYearPOCommonDTO assetRptNYearPOCommonDTO = assetRptNYearPOListForm.getAssetRptNYearPOCommonDTO();
        
        //Paging
        assetRptNYearPOCommonDTO.setIsLoadMaxCount("Y".equals(assetRptNYearPOListForm.getIsLoadMaxCount())?true:false);
        assetRptNYearPOCommonDTO.setFirstRow(assetRptNYearPOListForm.getFirstRow());
        assetRptNYearPOCommonDTO.setOrderBy(assetRptNYearPOListForm.getOrderBy());
        assetRptNYearPOCommonDTO.setDirection(assetRptNYearPOListForm.getDirection());
        
        List resultList = assetRptNYearPOListService.findList(assetRptNYearPOCommonDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(assetRptNYearPOListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = assetRptNYearPOListService.findTotalCount(assetRptNYearPOCommonDTO,getUser(request));
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,assetRptNYearPOListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
}