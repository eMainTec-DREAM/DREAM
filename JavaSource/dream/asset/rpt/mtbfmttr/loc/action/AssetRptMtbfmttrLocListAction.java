package dream.asset.rpt.mtbfmttr.loc.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.asset.rpt.mtbfmttr.loc.dto.AssetRptMtbfmttrLocCommonDTO;
import dream.asset.rpt.mtbfmttr.loc.form.AssetRptMtbfmttrLocListForm;
import dream.asset.rpt.mtbfmttr.loc.service.AssetRptMtbfmttrLocListService;

/**
 * MTBF,MTTR(위치)
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/assetRptMtbfmttrLocList" name="assetRptMtbfmttrLocListForm"
 *                input="/dream/asset/rpt/mtbfmttr/loc/assetRptMtbfmttrLocList.jsp" scope="request"
 *                validate="false"
 */
public class AssetRptMtbfmttrLocListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int MTTRMTBF_LOC_LIST_FIND 			= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        AssetRptMtbfmttrLocListForm assetRptMtbfmttrLocListForm = (AssetRptMtbfmttrLocListForm) form;
        
        switch (assetRptMtbfmttrLocListForm.getStrutsAction())
        {
        
            case AssetRptMtbfmttrLocListAction.MTTRMTBF_LOC_LIST_FIND:
                findList(request,response, assetRptMtbfmttrLocListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssetRptMtbfmttrLocListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, assetRptMtbfmttrLocListForm.getListId(), assetRptMtbfmttrLocListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssetRptMtbfmttrLocListAction.BASE_GRID_EXPORT:
            	findList(request,response, assetRptMtbfmttrLocListForm, true);
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
     * @param assetRptMtbfmttrLocListForm
     * @param excelExport
     * @throws Exception
     */
    private void findList(HttpServletRequest request,HttpServletResponse response, AssetRptMtbfmttrLocListForm assetRptMtbfmttrLocListForm, boolean excelExport) throws Exception
    {
        AssetRptMtbfmttrLocListService assetRptMtbfmttrLocListService = (AssetRptMtbfmttrLocListService) getBean("assetRptMtbfmttrLocListService",request);
        
        AssetRptMtbfmttrLocCommonDTO assetRptMtbfmttrLocCommonDTO = assetRptMtbfmttrLocListForm.getAssetRptMtbfmttrLocCommonDTO();
        
        //Paging
        assetRptMtbfmttrLocCommonDTO.setIsLoadMaxCount("Y".equals(assetRptMtbfmttrLocListForm.getIsLoadMaxCount())?true:false);
        assetRptMtbfmttrLocCommonDTO.setFirstRow(assetRptMtbfmttrLocListForm.getFirstRow());
        assetRptMtbfmttrLocCommonDTO.setOrderBy(assetRptMtbfmttrLocListForm.getOrderBy());
        assetRptMtbfmttrLocCommonDTO.setDirection(assetRptMtbfmttrLocListForm.getDirection());
        
        List resultList = assetRptMtbfmttrLocListService.findList(assetRptMtbfmttrLocCommonDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(assetRptMtbfmttrLocListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = assetRptMtbfmttrLocListService.findTotalCount(assetRptMtbfmttrLocCommonDTO,getUser(request));
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,assetRptMtbfmttrLocListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
}