package dream.asset.std.product.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.asset.std.product.dto.AssetStdProductCommonDTO;
import dream.asset.std.product.form.AssetStdProductListForm;
import dream.asset.std.product.service.AssetStdProductListService;

/**
 * 생산품목 - 목록 action
 * @author  ghlee
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/assetStdProductList" name="assetStdProductListForm"
 *                input="/dream/asset/std/product/assetStdProductList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="assetStdProductList" path="/dream/asset/std/product/assetStdProductList.jsp"
 *                        redirect="false"
 */
public class AssetStdProductListAction extends AuthAction
{
    /** 조회 */
    public static final int PRODUCT_LIST_FIND   = 1001;
    /** 삭제 */
    public static final int PRODUCT_LIST_DELETE = 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AssetStdProductListForm assetStdProductListForm = (AssetStdProductListForm) form;
        
        switch (assetStdProductListForm.getStrutsAction())
        {
            case AssetStdProductListAction.BASE_SET_HEADER:
                setHeader(request, response, assetStdProductListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssetStdProductListAction.PRODUCT_LIST_FIND:
                findList(request, response, assetStdProductListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case AssetStdProductListAction.PRODUCT_LIST_DELETE:
            	deleteList(request, assetStdProductListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case AssetStdProductListAction.BASE_GRID_EXPORT:
            	findList(request, response, assetStdProductListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, AssetStdProductListForm assetStdProductListForm) throws IOException
    {
        super.setHeader(request, response, assetStdProductListForm.getListId(), assetStdProductListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  ghlee
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param assetStdProductListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, AssetStdProductListForm assetStdProductListForm, boolean excelExport) throws Exception
    {
    	AssetStdProductListService assetStdProductListService = (AssetStdProductListService) getBean("assetStdProductListService");        

    	AssetStdProductCommonDTO assetStdProductCommonDTO = assetStdProductListForm.getAssetStdProductCommonDTO();
        
    	//Paging
    	assetStdProductCommonDTO.setIsLoadMaxCount("Y".equals(assetStdProductListForm.getIsLoadMaxCount())?true:false);
    	assetStdProductCommonDTO.setFirstRow(assetStdProductListForm.getFirstRow());
        assetStdProductCommonDTO.setOrderBy(assetStdProductListForm.getOrderBy());
        assetStdProductCommonDTO.setDirection(assetStdProductListForm.getDirection());
    	
        //리스트를 조회한다.
        List resultList = assetStdProductListService.findProductList(assetStdProductCommonDTO, getUser(request));
        
        //Paging
        String totalCount = "";

        if(Integer.parseInt(assetStdProductListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = assetStdProductListService.findTotalCount(assetStdProductCommonDTO,getUser(request));
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,assetStdProductListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * delete
     * @author  ghlee
     * @version $Id: $
     * @since   1.0
     * 
     * @param assetStdProductListForm
     * @param request
     */
    private void deleteList(HttpServletRequest request, AssetStdProductListForm assetStdProductListForm) throws Exception
    {
    	AssetStdProductListService assetStdProductListService = (AssetStdProductListService) getBean("assetStdProductListService");        

        String[] deleteRows = assetStdProductListForm.getDeleteRows();    // sheet 내역
        
        assetStdProductListService.deleteList(deleteRows, getUser(request));
        
        setAjaxStatus(request);
    }
}
