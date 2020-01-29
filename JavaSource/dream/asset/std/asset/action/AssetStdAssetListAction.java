package dream.asset.std.asset.action;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.asset.std.asset.dto.AssetStdAssetCommonDTO;
import dream.asset.std.asset.form.AssetStdAssetListForm;
import dream.asset.std.asset.service.AssetStdAssetListService;

/**
 * 회계자산 - 목록 action
 * @author  ghlee
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/assetStdAssetList" name="assetStdAssetListForm"
 *                input="/dream/asset/std/asset/assetStdAssetList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="assetStdAssetList" path="/dream/asset/std/asset/assetStdAssetList.jsp"
 *                        redirect="false"
 */
public class AssetStdAssetListAction extends AuthAction
{
    /** 조회 */
    public static final int ASSET_LIST_FIND     = 1001;
    /** 삭제 */
    public static final int ASSET_LIST_DELETE   = 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AssetStdAssetListForm assetStdAssetListForm = (AssetStdAssetListForm) form;
        
        super.updateAudit(assetStdAssetListForm.getAssetStdAssetCommonDTO().getAuditKey()==""?assetStdAssetListForm.getAssetStdAssetCommonDTO().getAuditKey():assetStdAssetListForm.getAssetStdAssetCommonDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (assetStdAssetListForm.getStrutsAction())
        {
            case AssetStdAssetListAction.BASE_SET_HEADER:
                setHeader(request, response, assetStdAssetListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssetStdAssetListAction.ASSET_LIST_FIND:
                findList(request, response, assetStdAssetListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case AssetStdAssetListAction.ASSET_LIST_DELETE:
            	deleteList(request, assetStdAssetListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case AssetStdAssetListAction.BASE_GRID_EXPORT:
            	findList(request, response, assetStdAssetListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, AssetStdAssetListForm assetStdAssetListForm) throws IOException
    {
        super.setHeader(request, response, assetStdAssetListForm.getListId(), assetStdAssetListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  ghlee
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param assetStdAssetListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, AssetStdAssetListForm assetStdAssetListForm, boolean excelExport) throws Exception
    {
    	AssetStdAssetListService assetStdAssetListService = (AssetStdAssetListService) getBean("assetStdAssetListService");        

    	AssetStdAssetCommonDTO assetStdAssetCommonDTO = assetStdAssetListForm.getAssetStdAssetCommonDTO();
        
    	//Paging
    	assetStdAssetCommonDTO.setIsLoadMaxCount("Y".equals(assetStdAssetListForm.getIsLoadMaxCount())?true:false);
    	assetStdAssetCommonDTO.setFirstRow(assetStdAssetListForm.getFirstRow());
        assetStdAssetCommonDTO.setOrderBy(assetStdAssetListForm.getOrderBy());
        assetStdAssetCommonDTO.setDirection(assetStdAssetListForm.getDirection());
    	
        //리스트를 조회한다.
        List resultList = assetStdAssetListService.findAssetList(assetStdAssetCommonDTO, getUser(request));
        
        //Paging
        String totalCount = "";

        if(Integer.parseInt(assetStdAssetListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = assetStdAssetListService.findTotalCount(assetStdAssetCommonDTO,getUser(request));
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,assetStdAssetListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * delete
     * @author  ghlee
     * @version $Id: $
     * @since   1.0
     * 
     * @param assetStdAssetListForm
     * @param request
     */
    private void deleteList(HttpServletRequest request, AssetStdAssetListForm assetStdAssetListForm) throws Exception
    {
    	AssetStdAssetListService assetStdAssetListService = (AssetStdAssetListService) getBean("assetStdAssetListService");        

        String[] deleteRows = assetStdAssetListForm.getDeleteRows();    // sheet 내역
        
        assetStdAssetListService.deleteList(deleteRows, getUser(request));
        
        setAjaxStatus(request);
    }
}
