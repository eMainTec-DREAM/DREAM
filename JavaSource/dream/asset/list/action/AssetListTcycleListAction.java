package dream.asset.list.action;

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
import dream.asset.list.dto.AssetListTcycleListDTO;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.form.AssetListTcycleListForm;
import dream.asset.list.service.AssetListTcycleListService;

/**
 * 교정주기 목록
 * @author  kim21017
 * @version $Id: AssetListTcycleListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/assetListTcycleList" name="assetListTcycleListForm"
 *                input="/dream/asset/list/assetListTcycleList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="assetListTcycleList" path="/dream/asset/list/assetListTcycleList.jsp"
 *                        redirect="false"
 */
public class AssetListTcycleListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int ASSET_LIST_TCYCLE_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int ASSET_LIST_TCYCLE_LIST_DELETE 		= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        AssetListTcycleListForm assetListTcycleListForm = (AssetListTcycleListForm) form;
        
        //super.updateAudit(assetListTcycleListForm.getAssetListTcycleListDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (assetListTcycleListForm.getStrutsAction())
        {
            case AssetListTcycleListAction.ASSET_LIST_TCYCLE_LIST_FIND:
                findTcycleList(request,response, assetListTcycleListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssetListTcycleListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, assetListTcycleListForm.getListId(), assetListTcycleListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssetListTcycleListAction.ASSET_LIST_TCYCLE_LIST_DELETE:
            	deleteTcycleList(request,assetListTcycleListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case AssetListTcycleListAction.BASE_GRID_EXPORT:
            	findTcycleList(request,response, assetListTcycleListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("assetListTcycleList");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: AssetListTcycleListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param assetListTcycleListForm
     * @throws Exception
     */
    private void findTcycleList(HttpServletRequest request,HttpServletResponse response, AssetListTcycleListForm assetListTcycleListForm, boolean excelExport) throws Exception
    {
        AssetListTcycleListService assetListTcycleListService = (AssetListTcycleListService) getBean("assetListTcycleListService");
        MaEqMstrCommonDTO maEqMstrCommonDTO = assetListTcycleListForm.getMaEqMstrCommonDTO();
        AssetListTcycleListDTO assetListTcycleListDTO = assetListTcycleListForm.getAssetListTcycleListDTO();
        
        //Paging
        assetListTcycleListDTO.setIsLoadMaxCount("Y".equals(assetListTcycleListForm.getIsLoadMaxCount())?true:false);
        assetListTcycleListDTO.setFirstRow(assetListTcycleListForm.getFirstRow());
        assetListTcycleListDTO.setOrderBy(assetListTcycleListForm.getOrderBy());
        assetListTcycleListDTO.setDirection(assetListTcycleListForm.getDirection());
        
        User user = getUser(request);
        List resultList = assetListTcycleListService.findTcycleList(maEqMstrCommonDTO, assetListTcycleListDTO, getUser(request));
       
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(assetListTcycleListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = assetListTcycleListService.findTotalCount(maEqMstrCommonDTO, assetListTcycleListDTO, user);
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, assetListTcycleListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: AssetListTcycleListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param assetListTcycleListForm
     * @throws Exception
     */
    private void deleteTcycleList(HttpServletRequest request, AssetListTcycleListForm assetListTcycleListForm) throws Exception
    {
    	AssetListTcycleListService assetListTcycleListService = (AssetListTcycleListService) getBean("assetListTcycleListService");
        
    	String[] deleteRows = assetListTcycleListForm.getDeleteRows();
    
    	assetListTcycleListService.deleteTcycleList(deleteRows, getUser(request).getCompNo());
    	
    	setAjaxStatus(request);
    }
}