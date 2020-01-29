package dream.asset.rpt.mtbfmttr.usdept.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.asset.rpt.mtbfmttr.usdept.dto.AssetRptMtbfmttrUsDeptCommonDTO;
import dream.asset.rpt.mtbfmttr.usdept.form.AssetRptMtbfmttrUsDeptListForm;
import dream.asset.rpt.mtbfmttr.usdept.service.AssetRptMtbfmttrUsDeptListService;

/**
 * MTBF,MTTR(사용부서)
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/assetRptMtbfmttrUsDeptList" name="assetRptMtbfmttrUsDeptListForm"
 *                input="/dream/asset/rpt/mtbfmttr/usdept/assetRptMtbfmttrUsDeptList.jsp" scope="request"
 *                validate="false"
 */
public class AssetRptMtbfmttrUsDeptListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int MTTRMTBF_EQUIP_LIST_FIND 			= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        AssetRptMtbfmttrUsDeptListForm assetRptMtbfmttrUsDeptListForm = (AssetRptMtbfmttrUsDeptListForm) form;
        
        switch (assetRptMtbfmttrUsDeptListForm.getStrutsAction())
        {
        
            case AssetRptMtbfmttrUsDeptListAction.MTTRMTBF_EQUIP_LIST_FIND:
                findList(request,response, assetRptMtbfmttrUsDeptListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssetRptMtbfmttrUsDeptListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, assetRptMtbfmttrUsDeptListForm.getListId(), assetRptMtbfmttrUsDeptListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssetRptMtbfmttrUsDeptListAction.BASE_GRID_EXPORT:
            	findList(request,response, assetRptMtbfmttrUsDeptListForm, true);
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
     * @param assetRptMtbfmttrUsDeptListForm
     * @param excelExport
     * @throws Exception
     */
    private void findList(HttpServletRequest request,HttpServletResponse response, AssetRptMtbfmttrUsDeptListForm assetRptMtbfmttrUsDeptListForm, boolean excelExport) throws Exception
    {
        AssetRptMtbfmttrUsDeptListService assetRptMtbfmttrUsDeptListService = (AssetRptMtbfmttrUsDeptListService) getBean("assetRptMtbfmttrUsDeptListService",request);
        
        AssetRptMtbfmttrUsDeptCommonDTO assetRptMtbfmttrUsDeptCommonDTO = assetRptMtbfmttrUsDeptListForm.getAssetRptMtbfmttrUsDeptCommonDTO();
        
        //Paging
        assetRptMtbfmttrUsDeptCommonDTO.setIsLoadMaxCount("Y".equals(assetRptMtbfmttrUsDeptListForm.getIsLoadMaxCount())?true:false);
        assetRptMtbfmttrUsDeptCommonDTO.setFirstRow(assetRptMtbfmttrUsDeptListForm.getFirstRow());
        assetRptMtbfmttrUsDeptCommonDTO.setOrderBy(assetRptMtbfmttrUsDeptListForm.getOrderBy());
        assetRptMtbfmttrUsDeptCommonDTO.setDirection(assetRptMtbfmttrUsDeptListForm.getDirection());
        
        List resultList = assetRptMtbfmttrUsDeptListService.findList(assetRptMtbfmttrUsDeptCommonDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(assetRptMtbfmttrUsDeptListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = assetRptMtbfmttrUsDeptListService.findTotalCount(assetRptMtbfmttrUsDeptCommonDTO,getUser(request));
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,assetRptMtbfmttrUsDeptListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
}