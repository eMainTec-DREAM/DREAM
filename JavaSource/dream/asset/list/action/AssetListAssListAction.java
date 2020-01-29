package dream.asset.list.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.asset.list.dto.AssetListAssListDTO;
import dream.asset.list.form.AssetListAssListForm;
import dream.asset.list.service.AssetListAssListService;

/**
 * AssetListAss Page - List Action
 * 
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @struts:action path="/assetListAssList" name="assetListAssListForm"
 *                input="/dream/asset/list/assetListAssList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/assetListAssTlList" name="assetListAssListForm"
 *                input="/dream/asset/list/assetListAssTlList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="assetListAssList" path="/dream/asset/list/assetListAssList.jsp"
 *                        redirect="false"
 * @struts.action-forward name="assetListAssTlList" path="/dream/asset/list/assetListAssTlList.jsp"
 *                        redirect="false"
 */
public class AssetListAssListAction extends AuthAction
{
    /** 조회하기 */
    public static final int LIST_FIND       = 1001;
    /** 삭제하기 */
    public static final int LIST_DELETE     = 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AssetListAssListForm assetListAssListForm = (AssetListAssListForm) form;
        
        switch (assetListAssListForm.getStrutsAction())
        {
            case AssetListAssListAction.BASE_SET_HEADER:
                setHeader(request, response, assetListAssListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssetListAssListAction.LIST_FIND:
                findList(request, response, assetListAssListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case AssetListAssListAction.LIST_DELETE:
                deleteList(request, assetListAssListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case AssetListAssListAction.BASE_GRID_EXPORT:
                findList(request, response, assetListAssListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, AssetListAssListForm assetListAssListForm) throws IOException
    {
        super.setHeader(request, response, assetListAssListForm.getListId(), assetListAssListForm.getCurrentPageId()); 
    }
   
    /**
     * FIND LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param assetListAssListForm
     */
    
    private void findList(HttpServletRequest request, HttpServletResponse response, AssetListAssListForm assetListAssListForm, boolean excelExport) throws Exception
    {
        AssetListAssListService assetListAssListService = (AssetListAssListService) getBean("assetListAssListService",request);
        AssetListAssListDTO assetListAssListDTO = assetListAssListForm.getAssetListAssListDTO();
      
        //Paging
        assetListAssListDTO.setIsLoadMaxCount("Y".equals(assetListAssListForm.getIsLoadMaxCount())?true:false);
        assetListAssListDTO.setFirstRow(assetListAssListForm.getFirstRow());
        assetListAssListDTO.setOrderBy(assetListAssListForm.getOrderBy());
        assetListAssListDTO.setDirection(assetListAssListForm.getDirection());
        
        User user = getUser(request);
        List resultList = assetListAssListService.findList(assetListAssListDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(assetListAssListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = assetListAssListService.findTotalCount(assetListAssListDTO,user);
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,assetListAssListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * DELETE LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param assetListAssListForm
     */
    private void deleteList(HttpServletRequest request, AssetListAssListForm assetListAssListForm) throws Exception
    {
        AssetListAssListService assetListAssListService = (AssetListAssListService) getBean("assetListAssListService");
        String[] deleteRows = assetListAssListForm.getDeleteRows();
        
        User user = getUser(request);
        int result = assetListAssListService.deleteList(deleteRows, user);
        
        request.setAttribute("result", result);
        
        setAjaxStatus(request);
    }
}