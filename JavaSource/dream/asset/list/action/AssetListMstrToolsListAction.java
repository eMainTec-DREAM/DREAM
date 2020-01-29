package dream.asset.list.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.asset.list.dto.AssetListMstrToolsListDTO;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.form.AssetListMstrToolsListForm;
import dream.asset.list.service.AssetListMstrToolsListService;

/**
 * 구성자재 목록
 * @author  kim21017
 * @version $Id: AssetListMstrToolsListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/assetListMstrToolsList" name="assetListMstrToolsListForm"
 *                input="/dream/asset/list/assetListMstrToolsList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="assetListMstrToolsList" path="/dream/asset/list/assetListMstrToolsList.jsp"
 *                        redirect="false"
 */
public class AssetListMstrToolsListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int EQ_MSTR_PART_LIST_FIND 			= 1001;
    /** 상위설비정보 삭제하기 */
    public static final int EQ_MSTR_PEQUIP_DELETE 			= 7002;

    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        AssetListMstrToolsListForm assetListMstrToolsListForm = (AssetListMstrToolsListForm) form;

        switch (assetListMstrToolsListForm.getStrutsAction())
        {

            case AssetListMstrToolsListAction.EQ_MSTR_PART_LIST_FIND:
                findPartList(request,response, assetListMstrToolsListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssetListMstrToolsListAction.EQ_MSTR_PEQUIP_DELETE:
            	deleteList(request, assetListMstrToolsListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case AssetListMstrToolsListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, assetListMstrToolsListForm.getListId(), assetListMstrToolsListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssetListMstrToolsListAction.BASE_GRID_EXPORT:
            	findPartList(request,response, assetListMstrToolsListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("assetListMstrToolsList");
                break;
        }

        return returnActionForward;
    }

    /**
     * grid find
     * @author  kim2107
     * @version $Id: AssetListMstrToolsListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     *
     * @param request
     * @param assetListMstrToolsListForm
     * @throws Exception
     */
    private void findPartList(HttpServletRequest request,HttpServletResponse response, AssetListMstrToolsListForm assetListMstrToolsListForm, boolean excelExport) throws Exception
    {
        AssetListMstrToolsListService assetListMstrToolsListService = (AssetListMstrToolsListService) getBean("assetListMstrToolsListService");
        MaEqMstrCommonDTO maEqMstrCommonDTO = assetListMstrToolsListForm.getMaEqMstrCommonDTO();
        AssetListMstrToolsListDTO maEqMtrPartListDTO = assetListMstrToolsListForm.getAssetListMstrToolsListDTO();

        //Paging
        maEqMtrPartListDTO.setIsLoadMaxCount("Y".equals(assetListMstrToolsListForm.getIsLoadMaxCount())?true:false);
        maEqMtrPartListDTO.setFirstRow(assetListMstrToolsListForm.getFirstRow());
        maEqMtrPartListDTO.setOrderBy(assetListMstrToolsListForm.getOrderBy());
        maEqMtrPartListDTO.setDirection(assetListMstrToolsListForm.getDirection());

        User user = getUser(request);
        List resultList = assetListMstrToolsListService.findPartList(maEqMstrCommonDTO, maEqMtrPartListDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(assetListMstrToolsListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = assetListMstrToolsListService.findTotalCount(maEqMstrCommonDTO, maEqMtrPartListDTO,user);
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, assetListMstrToolsListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
    
    private void deleteList(HttpServletRequest request, AssetListMstrToolsListForm assetListMstrToolsListForm) throws Exception
	{
        AssetListMstrToolsListService assetListMstrToolsListService = (AssetListMstrToolsListService) getBean("assetListMstrToolsListService");

        String[] deleteRows = assetListMstrToolsListForm.getDeleteRows();
        User user = getUser(request);
        
        assetListMstrToolsListService.deleteList(deleteRows, user);
        
        setAjaxStatus(request);
	}
    
}