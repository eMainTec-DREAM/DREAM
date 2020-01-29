package dream.asset.rpt.monthnew.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.asset.rpt.monthnew.dto.AssetRptMonthNewListDTO;
import dream.asset.rpt.monthnew.form.AssetRptMonthNewListForm;
import dream.asset.rpt.monthnew.service.AssetRptMonthNewListService;

/**
 * 신규설비등록현황 Action
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @struts:action path="/assetRptMonthNewList" name="assetRptMonthNewListForm"
 *                input="/dream/asset/rpt/monthnew/assetRptMonthNewList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="assetRptMonthNewList" path="/dream/asset/rpt/monthnew/assetRptMonthNewList.jsp"
 *                        redirect="false"
 */
public class AssetRptMonthNewListAction extends AuthAction
{
    /** 신규설비등록현황 리스트 조회 */
    public static final int LIST_FIND			= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AssetRptMonthNewListForm assetRptMonthNewListForm = (AssetRptMonthNewListForm) form;
        
        switch (assetRptMonthNewListForm.getStrutsAction())
        {
	        case AssetRptMonthNewListAction.LIST_FIND:
	            findConnList(request, assetRptMonthNewListForm, response);
	            returnActionForward = mapping.findForward("jsonPage");
	            break;
            default:
                returnActionForward = mapping.findForward("assetRptMonthNewList");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param request
     * @param assetRptMonthNewListForm
     * @param response
     * @throws Exception
     */
    private void findConnList(HttpServletRequest request, AssetRptMonthNewListForm assetRptMonthNewListForm, HttpServletResponse response) throws IOException
    {
    	AssetRptMonthNewListService assetRptMonthNewListService = (AssetRptMonthNewListService) getBean("assetRptMonthNewListService");        

    	AssetRptMonthNewListDTO assetRptMonthNewListDTO = assetRptMonthNewListForm.getAssetRptMonthNewListDTO();
    	assetRptMonthNewListDTO.setCompNo(getUser(request).getCompNo());
    	
    	User loginUser = getUser(request);
        //리스트를 조회한다.
        List resultList = assetRptMonthNewListService.findConnList(assetRptMonthNewListDTO, loginUser);

        super.makeTreeJsonResult(resultList, request, response, assetRptMonthNewListForm.getListId());
	}
}
