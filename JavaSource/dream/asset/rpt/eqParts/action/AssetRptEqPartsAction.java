package dream.asset.rpt.eqParts.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.asset.rpt.eqParts.dto.AssetRptEqPartsDTO;
import dream.asset.rpt.eqParts.form.AssetRptEqPartsForm;
import dream.asset.rpt.eqParts.service.AssetRptEqPartsService;

/**
 * 汲厚备己何前 - Action
 * 
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/assetRptEqParts" name="assetRptEqPartsForm"
 *                input="/dream/asset/rpt/eqParts/assetRptEqParts.jsp" scope="request"
 *                validate="false"
 */
public class AssetRptEqPartsAction extends AuthAction
{
    /** 炼雀 */
    public static final int LIST_FIND       = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AssetRptEqPartsForm assetRptEqPartsForm = (AssetRptEqPartsForm) form;
        
        switch (assetRptEqPartsForm.getStrutsAction())
        {
            case AssetRptEqPartsAction.BASE_SET_HEADER:
                setHeader(request, response, assetRptEqPartsForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssetRptEqPartsAction.LIST_FIND:
                findList(request, response, assetRptEqPartsForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case AssetRptEqPartsAction.BASE_GRID_EXPORT:
                findList(request, response, assetRptEqPartsForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, AssetRptEqPartsForm assetRptEqPartsForm) throws IOException
    {
        super.setHeader(request, response, assetRptEqPartsForm.getListId(), assetRptEqPartsForm.getCurrentPageId()); 
    }
    
    private void findList(HttpServletRequest request, HttpServletResponse response, AssetRptEqPartsForm assetRptEqPartsForm, boolean excelExport) throws Exception
    {
        AssetRptEqPartsService assetRptEqPartsService = (AssetRptEqPartsService) getBean("assetRptEqPartsService");
        AssetRptEqPartsDTO assetRptEqPartsDTO = assetRptEqPartsForm.getAssetRptEqPartsDTO();
      
        //Paging
        assetRptEqPartsDTO.setIsLoadMaxCount("Y".equals(assetRptEqPartsForm.getIsLoadMaxCount())?true:false);
        assetRptEqPartsDTO.setFirstRow(assetRptEqPartsForm.getFirstRow());
        assetRptEqPartsDTO.setOrderBy(assetRptEqPartsForm.getOrderBy());
        assetRptEqPartsDTO.setDirection(assetRptEqPartsForm.getDirection());
        
        User user = getUser(request);
        List resultList = assetRptEqPartsService.findList(assetRptEqPartsDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(assetRptEqPartsForm.getIsTotalCount()) == 0 && !excelExport) totalCount = assetRptEqPartsService.findTotalCount(assetRptEqPartsDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,assetRptEqPartsForm.getListId(),assetRptEqPartsForm.getCurrentPageId(), assetRptEqPartsForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
}