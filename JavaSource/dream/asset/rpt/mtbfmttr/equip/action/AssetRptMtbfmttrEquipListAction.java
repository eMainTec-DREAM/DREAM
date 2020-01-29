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
import dream.asset.rpt.mtbfmttr.equip.form.AssetRptMtbfmttrEquipListForm;
import dream.asset.rpt.mtbfmttr.equip.service.AssetRptMtbfmttrEquipListService;

/**
 * MTBF,MTTR(설비)
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/assetRptMtbfmttrEquipList" name="assetRptMtbfmttrEquipListForm"
 *                input="/dream/asset/rpt/mtbfmttr/equip/assetRptMtbfmttrEquipList.jsp" scope="request"
 *                validate="false"
 */
public class AssetRptMtbfmttrEquipListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int MTTRMTBF_EQUIP_LIST_FIND 			= 1001;
    /** MTBF 차트 검색 */
    public static final int MTBF_EQUIP_CHART_FIND 			    = 1002;
    /** MTTR 차트 검색 */
    public static final int MTTR_EQUIP_CHART_FIND 			    = 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        AssetRptMtbfmttrEquipListForm assetRptMtbfmttrEquipListForm = (AssetRptMtbfmttrEquipListForm) form;
        
        switch (assetRptMtbfmttrEquipListForm.getStrutsAction())
        {
        
            case AssetRptMtbfmttrEquipListAction.MTTRMTBF_EQUIP_LIST_FIND:
                findList(request,response, assetRptMtbfmttrEquipListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssetRptMtbfmttrEquipListAction.MTBF_EQUIP_CHART_FIND:
                findMtbfList(request,response, assetRptMtbfmttrEquipListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssetRptMtbfmttrEquipListAction.MTTR_EQUIP_CHART_FIND:
                findMttrList(request,response, assetRptMtbfmttrEquipListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssetRptMtbfmttrEquipListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, assetRptMtbfmttrEquipListForm.getListId(), assetRptMtbfmttrEquipListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssetRptMtbfmttrEquipListAction.BASE_GRID_EXPORT:
            	findList(request,response, assetRptMtbfmttrEquipListForm, true);
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
     * @param assetRptMtbfmttrEquipListForm
     * @param excelExport
     * @throws Exception
     */
    private void findList(HttpServletRequest request,HttpServletResponse response, AssetRptMtbfmttrEquipListForm assetRptMtbfmttrEquipListForm, boolean excelExport) throws Exception
    {
        AssetRptMtbfmttrEquipListService assetRptMtbfmttrEquipListService = (AssetRptMtbfmttrEquipListService) getBean("assetRptMtbfmttrEquipListService",request);
        
        AssetRptMtbfmttrEquipCommonDTO assetRptMtbfmttrEquipCommonDTO = assetRptMtbfmttrEquipListForm.getAssetRptMtbfmttrEquipCommonDTO();
        
        //Paging
        assetRptMtbfmttrEquipCommonDTO.setIsLoadMaxCount("Y".equals(assetRptMtbfmttrEquipListForm.getIsLoadMaxCount())?true:false);
        assetRptMtbfmttrEquipCommonDTO.setFirstRow(assetRptMtbfmttrEquipListForm.getFirstRow());
        assetRptMtbfmttrEquipCommonDTO.setOrderBy(assetRptMtbfmttrEquipListForm.getOrderBy());
        assetRptMtbfmttrEquipCommonDTO.setDirection(assetRptMtbfmttrEquipListForm.getDirection());
        
        List resultList = assetRptMtbfmttrEquipListService.findList(assetRptMtbfmttrEquipCommonDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(assetRptMtbfmttrEquipListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = assetRptMtbfmttrEquipListService.findTotalCount(assetRptMtbfmttrEquipCommonDTO,getUser(request));
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,assetRptMtbfmttrEquipListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
    private void findMtbfList(HttpServletRequest request,HttpServletResponse response, AssetRptMtbfmttrEquipListForm assetRptMtbfmttrEquipListForm) throws Exception
    {
        AssetRptMtbfmttrEquipListService assetRptMtbfmttrEquipListService = (AssetRptMtbfmttrEquipListService) getBean("assetRptMtbfmttrEquipListService",request);
        
        AssetRptMtbfmttrEquipCommonDTO assetRptMtbfmttrEquipCommonDTO = assetRptMtbfmttrEquipListForm.getAssetRptMtbfmttrEquipCommonDTO();
        
        List resultList = assetRptMtbfmttrEquipListService.findMtbfList(assetRptMtbfmttrEquipCommonDTO, getUser(request));
        
        CommonUtil.makeJsonResult(resultList, request, response, "");
    }
    
    private void findMttrList(HttpServletRequest request,HttpServletResponse response, AssetRptMtbfmttrEquipListForm assetRptMtbfmttrEquipListForm) throws Exception
    {
        AssetRptMtbfmttrEquipListService assetRptMtbfmttrEquipListService = (AssetRptMtbfmttrEquipListService) getBean("assetRptMtbfmttrEquipListService",request);
        
        AssetRptMtbfmttrEquipCommonDTO assetRptMtbfmttrEquipCommonDTO = assetRptMtbfmttrEquipListForm.getAssetRptMtbfmttrEquipCommonDTO();
        
        List resultList = assetRptMtbfmttrEquipListService.findMttrList(assetRptMtbfmttrEquipCommonDTO, getUser(request));
        
        CommonUtil.makeJsonResult(resultList, request, response, "");
    }
    
}