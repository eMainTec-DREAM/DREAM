package dream.asset.rpt.lcc.equip.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.asset.rpt.lcc.equip.dto.AssetRptLccEquipCommonDTO;
import dream.asset.rpt.lcc.equip.form.AssetRptLccEquipListForm;
import dream.asset.rpt.lcc.equip.service.AssetRptLccEquipListService;

/**
 * 고장TOP(설비)
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/assetRptLccEquipList" name="assetRptLccEquipListForm"
 *                input="/dream/asset/rpt/lcc/equip/assetRptLccEquipList.jsp" scope="request"
 *                validate="false"
 */
public class AssetRptLccEquipListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int LCC_EQUIP_LIST_FIND 			= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        AssetRptLccEquipListForm assetRptLccEquipListForm = (AssetRptLccEquipListForm) form;
        
        switch (assetRptLccEquipListForm.getStrutsAction())
        {
        
            case AssetRptLccEquipListAction.LCC_EQUIP_LIST_FIND:
                findList(request,response, assetRptLccEquipListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssetRptLccEquipListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, assetRptLccEquipListForm.getListId(), assetRptLccEquipListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssetRptLccEquipListAction.BASE_GRID_EXPORT:
            	findList(request,response, assetRptLccEquipListForm, true);
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
     * @param assetRptLccEquipListForm
     * @param excelExport
     * @throws Exception
     */
    private void findList(HttpServletRequest request,HttpServletResponse response, AssetRptLccEquipListForm assetRptLccEquipListForm, boolean excelExport) throws Exception
    {
        AssetRptLccEquipListService assetRptLccEquipListService = (AssetRptLccEquipListService) getBean("assetRptLccEquipListService");
        
        AssetRptLccEquipCommonDTO assetRptLccEquipCommonDTO = assetRptLccEquipListForm.getAssetRptLccEquipCommonDTO();
        
        //Paging
        assetRptLccEquipCommonDTO.setIsLoadMaxCount("Y".equals(assetRptLccEquipListForm.getIsLoadMaxCount())?true:false);
        assetRptLccEquipCommonDTO.setFirstRow(assetRptLccEquipListForm.getFirstRow());
        assetRptLccEquipCommonDTO.setOrderBy(assetRptLccEquipListForm.getOrderBy());
        assetRptLccEquipCommonDTO.setDirection(assetRptLccEquipListForm.getDirection());
        
        List resultList = assetRptLccEquipListService.findList(assetRptLccEquipCommonDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(assetRptLccEquipListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = assetRptLccEquipListService.findTotalCount(assetRptLccEquipCommonDTO,getUser(request));
        
        if(excelExport) super.makeGridExport(resultList, request, response,assetRptLccEquipListForm.getListId(),assetRptLccEquipListForm.getCurrentPageId(), assetRptLccEquipListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
}