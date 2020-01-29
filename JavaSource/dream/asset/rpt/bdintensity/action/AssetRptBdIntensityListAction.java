package dream.asset.rpt.bdintensity.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.asset.rpt.bdintensity.dto.AssetRptBdIntensityCommonDTO;
import dream.asset.rpt.bdintensity.form.AssetRptBdIntensityListForm;
import dream.asset.rpt.bdintensity.service.AssetRptBdIntensityListService;

/**
 * 설비별 고장강도율 목록 - List Action
 * 
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/assetRptBdIntensityList" name="assetRptBdIntensityListForm"
 *                input="/dream/asset/rpt/bdintensity/assetRptBdIntensityList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="assetRptBdIntensityList" path="/dream/asset/rpt/bdintensity/assetRptBdIntensityList.jsp"
 *                        redirect="false"
 */
public class AssetRptBdIntensityListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND       = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AssetRptBdIntensityListForm assetRptBdIntensityListForm = (AssetRptBdIntensityListForm) form;
        
        switch (assetRptBdIntensityListForm.getStrutsAction())
        {
            case AssetRptBdIntensityListAction.BASE_SET_HEADER:
                setHeader(request, response, assetRptBdIntensityListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssetRptBdIntensityListAction.LIST_FIND:
                findList(request, response, assetRptBdIntensityListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case AssetRptBdIntensityListAction.BASE_GRID_EXPORT:
                findList(request, response, assetRptBdIntensityListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("assetRptBdIntensityList");
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, AssetRptBdIntensityListForm assetRptBdIntensityListForm) throws IOException
    {
        super.setHeader(request, response, assetRptBdIntensityListForm.getListId(), assetRptBdIntensityListForm.getCurrentPageId()); 
    }
   
    /**
     * FIND LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param assetRptBdIntensityListForm
     */
    
    private void findList(HttpServletRequest request, HttpServletResponse response, AssetRptBdIntensityListForm assetRptBdIntensityListForm, boolean excelExport) throws Exception
    {
        AssetRptBdIntensityListService assetRptBdIntensityListService = (AssetRptBdIntensityListService) getBean("assetRptBdIntensityListService",request);
        AssetRptBdIntensityCommonDTO assetRptBdIntensityCommonDTO = assetRptBdIntensityListForm.getAssetRptBdIntensityCommonDTO();
      
        //Paging
        assetRptBdIntensityCommonDTO.setIsLoadMaxCount("Y".equals(assetRptBdIntensityListForm.getIsLoadMaxCount())?true:false);
        assetRptBdIntensityCommonDTO.setFirstRow(assetRptBdIntensityListForm.getFirstRow());
        assetRptBdIntensityCommonDTO.setOrderBy(assetRptBdIntensityListForm.getOrderBy());
        assetRptBdIntensityCommonDTO.setDirection(assetRptBdIntensityListForm.getDirection());
        
        User user = getUser(request);
        List resultList = assetRptBdIntensityListService.findList(assetRptBdIntensityCommonDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(assetRptBdIntensityListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = assetRptBdIntensityListService.findTotalCount(assetRptBdIntensityCommonDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,assetRptBdIntensityListForm.getListId(),assetRptBdIntensityListForm.getCurrentPageId(), assetRptBdIntensityListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
}