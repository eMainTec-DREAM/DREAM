package dream.asset.rpt.unit.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.asset.rpt.unit.dto.AssetRptEqUnitsDTO;
import dream.asset.rpt.unit.form.AssetRptEqUnitsForm;
import dream.asset.rpt.unit.service.AssetRptEqUnitsService;

/**
 * 목록
 * @author  euna0207
 * @version $Id$
 * @since   1.0
 * @struts:action path="/assetRptEqUnits" name="assetRptEqUnitsForm"
 *                input="/dream/asset/rpt/unit/assetRptEqUnits.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="assetRptEqUnits" path="/dream/asset/rpt/unit/assetRptEqUnits.jsp"
 *                        redirect="false" 
 *                        
 */
public class AssetRptEqUnitsAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND 		= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        AssetRptEqUnitsForm assetRptEqUnitsForm = (AssetRptEqUnitsForm) form;
        System.out.println(assetRptEqUnitsForm.getStrutsAction());
        
        switch (assetRptEqUnitsForm.getStrutsAction())
        {
        
            case AssetRptEqUnitsAction.LIST_FIND:
                findList(request,response, assetRptEqUnitsForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssetRptEqUnitsAction.BASE_GRID_EXPORT:
            	findList(request,response, assetRptEqUnitsForm, true);
            	returnActionForward = new ActionForward("/gridExport");
            	break;
            case AssetRptEqUnitsAction.BASE_SET_HEADER:
            	super.setHeader(request, response, assetRptEqUnitsForm.getListId(), assetRptEqUnitsForm.getCurrentPageId());
            	returnActionForward = mapping.findForward("jsonPage");
            	break;          	
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    

	/**
     * grid find
     * @author  euna0207
     * @version $Id$
     * @since   1.0
     * 
     * @param request
     * @param assetRptEqUnitsForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request,HttpServletResponse response, AssetRptEqUnitsForm assetRptEqUnitsForm, boolean excelExport) throws Exception
    {
        AssetRptEqUnitsService assetRptEqUnitsService = (AssetRptEqUnitsService) getBean("assetRptEqUnitsService");
        AssetRptEqUnitsDTO assetRptEqUnitsDTO = assetRptEqUnitsForm.getAssetRptEqUnitsDTO();
     
        //Paging
        assetRptEqUnitsDTO.setIsLoadMaxCount("Y".equals(assetRptEqUnitsForm.getIsLoadMaxCount())?true:false);
        assetRptEqUnitsDTO.setFirstRow(assetRptEqUnitsForm.getFirstRow());
        assetRptEqUnitsDTO.setOrderBy(assetRptEqUnitsForm.getOrderBy());
        assetRptEqUnitsDTO.setDirection(assetRptEqUnitsForm.getDirection());
        
        User user = getUser(request);
        List resultList = assetRptEqUnitsService.findList(assetRptEqUnitsDTO, getUser(request));
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(assetRptEqUnitsForm.getIsTotalCount()) == 0 && !excelExport) totalCount = assetRptEqUnitsService.findTotalCount(assetRptEqUnitsDTO, user);
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, assetRptEqUnitsForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);}
    
}