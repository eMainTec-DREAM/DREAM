package dream.asset.rpt.lcc.usdept.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.asset.rpt.lcc.usdept.dto.AssetRptLccUsDeptCommonDTO;
import dream.asset.rpt.lcc.usdept.form.AssetRptLccUsDeptListForm;
import dream.asset.rpt.lcc.usdept.service.AssetRptLccUsDeptListService;

/**
 * 고장TOP(사용부서)
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/assetRptLccUsDeptList" name="assetRptLccUsDeptListForm"
 *                input="/dream/asset/rpt/lcc/usdept/assetRptLccUsDeptList.jsp" scope="request"
 *                validate="false"
 */
public class AssetRptLccUsDeptListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int LCC_EQUIP_LIST_FIND 			= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        AssetRptLccUsDeptListForm assetRptLccUsDeptListForm = (AssetRptLccUsDeptListForm) form;
        
        switch (assetRptLccUsDeptListForm.getStrutsAction())
        {
        
            case AssetRptLccUsDeptListAction.LCC_EQUIP_LIST_FIND:
                findList(request,response, assetRptLccUsDeptListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssetRptLccUsDeptListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, assetRptLccUsDeptListForm.getListId(), assetRptLccUsDeptListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssetRptLccUsDeptListAction.BASE_GRID_EXPORT:
            	findList(request,response, assetRptLccUsDeptListForm, true);
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
     * @param assetRptLccUsDeptListForm
     * @param excelExport
     * @throws Exception
     */
    private void findList(HttpServletRequest request,HttpServletResponse response, AssetRptLccUsDeptListForm assetRptLccUsDeptListForm, boolean excelExport) throws Exception
    {
        AssetRptLccUsDeptListService assetRptLccUsDeptListService = (AssetRptLccUsDeptListService) getBean("assetRptLccUsDeptListService");
        
        AssetRptLccUsDeptCommonDTO assetRptLccUsDeptCommonDTO = assetRptLccUsDeptListForm.getAssetRptLccUsDeptCommonDTO();
        
        //Paging
        assetRptLccUsDeptCommonDTO.setIsLoadMaxCount("Y".equals(assetRptLccUsDeptListForm.getIsLoadMaxCount())?true:false);
        assetRptLccUsDeptCommonDTO.setFirstRow(assetRptLccUsDeptListForm.getFirstRow());
        assetRptLccUsDeptCommonDTO.setOrderBy(assetRptLccUsDeptListForm.getOrderBy());
        assetRptLccUsDeptCommonDTO.setDirection(assetRptLccUsDeptListForm.getDirection());
        
        List resultList = assetRptLccUsDeptListService.findList(assetRptLccUsDeptCommonDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(assetRptLccUsDeptListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = assetRptLccUsDeptListService.findTotalCount(assetRptLccUsDeptCommonDTO,getUser(request));
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,assetRptLccUsDeptListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
}