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
import dream.asset.list.dto.AssetListITSWListDTO;
import dream.asset.list.form.AssetListITSWListForm;
import dream.asset.list.service.AssetListITSWListService;

/**
 * List Action
 * 
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @struts:action path="/assetListITSWList" name="assetListITSWListForm"
 *                input="/dream/asset/list/assetListITSWList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="assetListITSWList" path="/dream/asset/list/assetListITSWList.jsp"
 *                        redirect="false"
 */
public class AssetListITSWListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND       = 1001;
    /** 삭제 */
    public static final int LIST_DELETE     = 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AssetListITSWListForm assetListITSWListForm = (AssetListITSWListForm) form;
        
        //super.updateAudit(assetListITSWListForm.getAssetListITSWListDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (assetListITSWListForm.getStrutsAction())
        {
            case AssetListITSWListAction.BASE_SET_HEADER:
                setHeader(request, response, assetListITSWListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssetListITSWListAction.LIST_FIND:
                findList(request, response, assetListITSWListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case AssetListITSWListAction.LIST_DELETE:
                deleteList(request, assetListITSWListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case AssetListITSWListAction.BASE_GRID_EXPORT:
                findList(request, response, assetListITSWListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("assetListITSWList");
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, AssetListITSWListForm assetListITSWListForm) throws IOException
    {
        super.setHeader(request, response, assetListITSWListForm.getListId(), assetListITSWListForm.getCurrentPageId()); 
    }
   
    /**
     * FIND LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param assetListITSWListForm
     */
    
    private void findList(HttpServletRequest request, HttpServletResponse response, AssetListITSWListForm assetListITSWListForm, boolean excelExport) throws Exception
    {
        AssetListITSWListService assetListITSWListService = (AssetListITSWListService) getBean("assetListITSWListService");
        AssetListITSWListDTO assetListITSWListDTO = assetListITSWListForm.getAssetListITSWListDTO();
      
        //Paging
        assetListITSWListDTO.setIsLoadMaxCount("Y".equals(assetListITSWListForm.getIsLoadMaxCount())?true:false);
        assetListITSWListDTO.setFirstRow(assetListITSWListForm.getFirstRow());
        assetListITSWListDTO.setOrderBy(assetListITSWListForm.getOrderBy());
        assetListITSWListDTO.setDirection(assetListITSWListForm.getDirection());
        
        User user = getUser(request);
        List resultList = assetListITSWListService.findList(assetListITSWListDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(assetListITSWListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = assetListITSWListService.findTotalCount(assetListITSWListDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,assetListITSWListForm.getListId(),assetListITSWListForm.getCurrentPageId(), assetListITSWListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * DELETE LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param assetListITSWListForm
     */
    private void deleteList(HttpServletRequest request, AssetListITSWListForm assetListITSWListForm) throws Exception
    {
        AssetListITSWListService assetListITSWListService = (AssetListITSWListService) getBean("assetListITSWListService");
        String[] deleteRows = assetListITSWListForm.getDeleteRows();
        
        User user = getUser(request);
        assetListITSWListService.deleteList(deleteRows, user);
        setAjaxStatus(request);
    }
    
    
}