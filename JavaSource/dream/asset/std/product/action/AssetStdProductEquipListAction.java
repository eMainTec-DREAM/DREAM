package dream.asset.std.product.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.asset.std.product.dto.AssetStdProductCommonDTO;
import dream.asset.std.product.dto.AssetStdProductEquipDetailDTO;
import dream.asset.std.product.dto.AssetStdProductEquipListDTO;
import dream.asset.std.product.form.AssetStdProductEquipListForm;
import dream.asset.std.product.service.AssetStdProductEquipListService;

/**
 * 생산설비 - List Action
 * 
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @struts:action path="/assetStdProductEquipList" name="assetStdProductEquipListForm"
 *                input="/dream/asset/std/product/assetStdProductEquipList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="assetStdProductEquipList" path="/dream/asset/std/product/assetStdProductEquipList.jsp"
 *                        redirect="false"
 */

public class AssetStdProductEquipListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int LIST_DELETE 	= 7002;
    /** 설비 일괄등록 */
    public static final int EQ_LIST_INPUT	= 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AssetStdProductEquipListForm assetStdProductEquipListForm = (AssetStdProductEquipListForm) form;
        
        switch (assetStdProductEquipListForm.getStrutsAction())
        {
            case AssetStdProductEquipListAction.BASE_SET_HEADER:
                setHeader(request, response, assetStdProductEquipListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssetStdProductEquipListAction.LIST_FIND:
                findList(request, response, assetStdProductEquipListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case AssetStdProductEquipListAction.LIST_DELETE:
            	deleteList(request, assetStdProductEquipListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case AssetStdProductEquipListAction.EQ_LIST_INPUT:
            	insertList(request, response, assetStdProductEquipListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;       
            case AssetStdProductEquipListAction.BASE_GRID_EXPORT:
            	findList(request, response, assetStdProductEquipListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("assetStdProductEquipList");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, AssetStdProductEquipListForm assetStdProductEquipListForm) throws IOException
    {
        super.setHeader(request, response, assetStdProductEquipListForm.getListId(), assetStdProductEquipListForm.getCurrentPageId()); 
    }
    
    /**
     * FIND LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param assetStdProductEquipListForm
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, AssetStdProductEquipListForm assetStdProductEquipListForm, boolean excelExport) throws Exception
    {
    	AssetStdProductEquipListService assetStdProductEquipListService = (AssetStdProductEquipListService) getBean("assetStdProductEquipListService");
    	AssetStdProductCommonDTO assetStdProductCommonDTO = assetStdProductEquipListForm.getAssetStdProductCommonDTO();
    	AssetStdProductEquipListDTO assetStdProductEquipListDTO = assetStdProductEquipListForm.getAssetStdProductEquipListDTO();

    	//Paging
    	assetStdProductCommonDTO.setIsLoadMaxCount("Y".equals(assetStdProductEquipListForm.getIsLoadMaxCount())?true:false);
    	assetStdProductCommonDTO.setFirstRow(assetStdProductEquipListForm.getFirstRow());
    	assetStdProductCommonDTO.setOrderBy(assetStdProductEquipListForm.getOrderBy());
    	assetStdProductCommonDTO.setDirection(assetStdProductEquipListForm.getDirection());
    	
    	List resultList = assetStdProductEquipListService.findList(assetStdProductCommonDTO,assetStdProductEquipListDTO, getUser(request));
        
    	//Paging
        String totalCount = "";
        if(Integer.parseInt(assetStdProductEquipListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = assetStdProductEquipListService.findTotalCount(assetStdProductCommonDTO, assetStdProductEquipListDTO, getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,assetStdProductEquipListForm.getListId(),assetStdProductEquipListForm.getCurrentPageId(), assetStdProductEquipListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
        
    }
    
    /**
     * DELETE LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param assetStdProductEquipListForm
     */
    private void deleteList(HttpServletRequest request, AssetStdProductEquipListForm assetStdProductEquipListForm) throws Exception
    {
    	AssetStdProductEquipListService assetStdProductEquipListService = (AssetStdProductEquipListService) getBean("assetStdProductEquipListService");
        String[] deleteRows = assetStdProductEquipListForm.getDeleteRows();
        assetStdProductEquipListService.deleteList(deleteRows, getUser(request));
        setAjaxStatus(request);
    }
    
    private void insertList(HttpServletRequest request, HttpServletResponse response, AssetStdProductEquipListForm assetStdProductEquipListForm) throws Exception
    {
    	AssetStdProductEquipListService assetStdProductEquipListService = (AssetStdProductEquipListService) getBean("assetStdProductEquipListService");
    	AssetStdProductCommonDTO assetStdProductCommonDTO = assetStdProductEquipListForm.getAssetStdProductCommonDTO();
    	AssetStdProductEquipListDTO assetStdProductEquipListDTO = assetStdProductEquipListForm.getAssetStdProductEquipListDTO();
    	AssetStdProductEquipDetailDTO assetStdProductEquipDetailDTO = assetStdProductEquipListForm.getAssetStdProductEquipDetailDTO();
    	
    	User user = getUser(request);
    	
    	assetStdProductEquipListService.insertList(assetStdProductCommonDTO, assetStdProductEquipDetailDTO, assetStdProductEquipListDTO, user);
    	
    	setAjaxStatus(request);
    }
    
}
