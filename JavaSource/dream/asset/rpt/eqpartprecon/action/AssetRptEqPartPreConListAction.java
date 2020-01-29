package dream.asset.rpt.eqpartprecon.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.asset.rpt.eqpartprecon.dto.AssetRptEqPartPreConCommonDTO;
import dream.asset.rpt.eqpartprecon.form.AssetRptEqPartPreConListForm;
import dream.asset.rpt.eqpartprecon.service.AssetRptEqPartPreConListService;

/**
 * AssetRptEqPartPreCon Page - List Action
 * 
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/assetRptEqPartPreConList" name="assetRptEqPartPreConListForm"
 *                input="/dream/asset/rpt/eqpartprecon/assetRptEqPartPreConList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="assetRptEqPartPreConList" path="/dream/asset/rpt/eqpartprecon/assetRptEqPartPreConList.jsp"
 *                        redirect="false"
 */
public class AssetRptEqPartPreConListAction extends AuthAction
{
    /** Á¶È¸ */
    public static final int LIST_FIND       = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AssetRptEqPartPreConListForm assetRptEqPartPreConListForm = (AssetRptEqPartPreConListForm) form;
        
        switch (assetRptEqPartPreConListForm.getStrutsAction())
        {
            case AssetRptEqPartPreConListAction.BASE_SET_HEADER:
                setHeader(request, response, assetRptEqPartPreConListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssetRptEqPartPreConListAction.LIST_FIND:
                findList(request, response, assetRptEqPartPreConListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case AssetRptEqPartPreConListAction.BASE_GRID_EXPORT:
                findList(request, response, assetRptEqPartPreConListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("assetRptEqPartPreConList");
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, AssetRptEqPartPreConListForm assetRptEqPartPreConListForm) throws IOException
    {
        super.setHeader(request, response, assetRptEqPartPreConListForm.getListId(), assetRptEqPartPreConListForm.getCurrentPageId()); 
    }
   
    /**
     * FIND LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param assetRptEqPartPreConListForm
     */
    
    private void findList(HttpServletRequest request, HttpServletResponse response, AssetRptEqPartPreConListForm assetRptEqPartPreConListForm, boolean excelExport) throws Exception
    {
        AssetRptEqPartPreConListService assetRptEqPartPreConListService = (AssetRptEqPartPreConListService) getBean("assetRptEqPartPreConListService");
        AssetRptEqPartPreConCommonDTO assetRptEqPartPreConCommonDTO = assetRptEqPartPreConListForm.getAssetRptEqPartPreConCommonDTO();
      
        //Paging
        assetRptEqPartPreConCommonDTO.setIsLoadMaxCount("Y".equals(assetRptEqPartPreConListForm.getIsLoadMaxCount())?true:false);
        assetRptEqPartPreConCommonDTO.setFirstRow(assetRptEqPartPreConListForm.getFirstRow());
        assetRptEqPartPreConCommonDTO.setOrderBy(assetRptEqPartPreConListForm.getOrderBy());
        assetRptEqPartPreConCommonDTO.setDirection(assetRptEqPartPreConListForm.getDirection());
        
        User user = getUser(request);
        List resultList = assetRptEqPartPreConListService.findList(assetRptEqPartPreConCommonDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(assetRptEqPartPreConListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = assetRptEqPartPreConListService.findTotalCount(assetRptEqPartPreConCommonDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,assetRptEqPartPreConListForm.getListId(),assetRptEqPartPreConListForm.getCurrentPageId(), assetRptEqPartPreConListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
}