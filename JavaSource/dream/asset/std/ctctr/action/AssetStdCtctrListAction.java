package dream.asset.std.ctctr.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.asset.std.ctctr.dto.AssetStdCtctrCommonDTO;
import dream.asset.std.ctctr.form.AssetStdCtctrListForm;
import dream.asset.std.ctctr.service.AssetStdCtctrListService;

/**
 * CostCenter - 목록 action
 * @author  ghlee
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/assetStdCtctrList" name="assetStdCtctrListForm"
 *                input="/dream/asset/std/ctctr/assetStdCtctrList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="assetStdCtctrList" path="/dream/asset/std/ctctr/assetStdCtctrList.jsp"
 *                        redirect="false"
 */
public class AssetStdCtctrListAction extends AuthAction
{
    /** 조회 */
    public static final int CTCTR_LIST_FIND     = 1001;
    /** 삭제 */
    public static final int CTCTR_LIST_DELETE   = 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AssetStdCtctrListForm assetStdCtctrListForm = (AssetStdCtctrListForm) form;
        
        switch (assetStdCtctrListForm.getStrutsAction())
        {
            case AssetStdCtctrListAction.BASE_SET_HEADER:
                setHeader(request, response, assetStdCtctrListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssetStdCtctrListAction.CTCTR_LIST_FIND:
                findList(request, response, assetStdCtctrListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case AssetStdCtctrListAction.CTCTR_LIST_DELETE:
            	deleteList(request, assetStdCtctrListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case AssetStdCtctrListAction.BASE_GRID_EXPORT:
            	findList(request, response, assetStdCtctrListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, AssetStdCtctrListForm assetStdCtctrListForm) throws IOException
    {
        super.setHeader(request, response, assetStdCtctrListForm.getListId(), assetStdCtctrListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  ghlee
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param assetStdCtctrListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, AssetStdCtctrListForm assetStdCtctrListForm, boolean excelExport) throws Exception
    {
    	AssetStdCtctrListService assetStdCtctrListService = (AssetStdCtctrListService) getBean("assetStdCtctrListService");        

    	AssetStdCtctrCommonDTO assetStdCtctrCommonDTO = assetStdCtctrListForm.getAssetStdCtctrCommonDTO();
        
    	//Paging
    	assetStdCtctrCommonDTO.setIsLoadMaxCount("Y".equals(assetStdCtctrListForm.getIsLoadMaxCount())?true:false);
    	assetStdCtctrCommonDTO.setFirstRow(assetStdCtctrListForm.getFirstRow());
        assetStdCtctrCommonDTO.setOrderBy(assetStdCtctrListForm.getOrderBy());
        assetStdCtctrCommonDTO.setDirection(assetStdCtctrListForm.getDirection());
    	
        //리스트를 조회한다.
        List resultList = assetStdCtctrListService.findCtctrList(assetStdCtctrCommonDTO, getUser(request));
        
        //Paging
        String totalCount = "";

        if(Integer.parseInt(assetStdCtctrListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = assetStdCtctrListService.findTotalCount(assetStdCtctrCommonDTO,getUser(request));
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,assetStdCtctrListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * delete
     * @author  ghlee
     * @version $Id: $
     * @since   1.0
     * 
     * @param assetStdCtctrListForm
     * @param request
     */
    private void deleteList(HttpServletRequest request, AssetStdCtctrListForm assetStdCtctrListForm) throws Exception
    {
    	AssetStdCtctrListService assetStdCtctrListService = (AssetStdCtctrListService) getBean("assetStdCtctrListService");        

        String[] deleteRows = assetStdCtctrListForm.getDeleteRows();    // sheet 내역
        
        assetStdCtctrListService.deleteList(deleteRows, getUser(request));
        
        setAjaxStatus(request);
    }
}
