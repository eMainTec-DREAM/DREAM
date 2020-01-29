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
import dream.asset.list.dto.MaEqMstrAssetListDTO;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.form.MaEqMstrAssetListForm;
import dream.asset.list.service.MaEqMstrAssetListService;

/**
 * 관련자산 목록
 * @author  kim21017
 * @version $Id: MaEqMstrAssetListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maEqMstrAssetList" name="maEqMstrAssetListForm"
 *                input="/dream/asset/list/maEqMstrAssetList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maEqMstrAssetList" path="/dream/asset/list/maEqMstrAssetList.jsp"
 *                        redirect="false"
 */
public class MaEqMstrAssetListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int EQ_MSTR_ASSET_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int EQ_MSTR_ASSET_LIST_DELETE 		= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaEqMstrAssetListForm maEqMstrAssetListForm = (MaEqMstrAssetListForm) form;
        
        switch (maEqMstrAssetListForm.getStrutsAction())
        {
        
            case MaEqMstrAssetListAction.EQ_MSTR_ASSET_LIST_FIND:
                findAssetList(request,response, maEqMstrAssetListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaEqMstrAssetListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, maEqMstrAssetListForm.getListId(), maEqMstrAssetListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaEqMstrAssetListAction.EQ_MSTR_ASSET_LIST_DELETE:
            	deleteAssetList(request,maEqMstrAssetListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaEqMstrAssetListAction.BASE_GRID_EXPORT:
            	findAssetList(request,response, maEqMstrAssetListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maEqMstrAssetList");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaEqMstrAssetListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqMstrAssetListForm
     * @throws Exception
     */
    private void findAssetList(HttpServletRequest request,HttpServletResponse response, MaEqMstrAssetListForm maEqMstrAssetListForm, boolean excelExport) throws Exception
    {
        MaEqMstrAssetListService maEqMstrAssetListService = (MaEqMstrAssetListService) getBean("maEqMstrAssetListService");
        MaEqMstrCommonDTO maEqMstrCommonDTO = maEqMstrAssetListForm.getMaEqMstrCommonDTO();
        MaEqMstrAssetListDTO maEqMstrAssetListDTO = maEqMstrAssetListForm.getMaEqMstrAssetListDTO();
        
        //Paging
        maEqMstrAssetListDTO.setIsLoadMaxCount("Y".equals(maEqMstrAssetListForm.getIsLoadMaxCount())?true:false);
        maEqMstrAssetListDTO.setFirstRow(maEqMstrAssetListForm.getFirstRow());
        maEqMstrAssetListDTO.setOrderBy(maEqMstrAssetListForm.getOrderBy());
        maEqMstrAssetListDTO.setDirection(maEqMstrAssetListForm.getDirection());
        
        User user = getUser(request);
        List resultList = maEqMstrAssetListService.findAssetList(maEqMstrCommonDTO, maEqMstrAssetListDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(maEqMstrAssetListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maEqMstrAssetListService.findTotalCount(maEqMstrCommonDTO, maEqMstrAssetListDTO,user);
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,maEqMstrAssetListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: MaEqMstrAssetListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqMstrAssetListForm
     * @throws Exception
     */
    private void deleteAssetList(HttpServletRequest request, MaEqMstrAssetListForm maEqMstrAssetListForm) throws Exception
    {
    	MaEqMstrAssetListService maEqMstrAssetListService = (MaEqMstrAssetListService) getBean("maEqMstrAssetListService");
        
    	String[] deleteRows = maEqMstrAssetListForm.getDeleteRows();
    
    	maEqMstrAssetListService.deleteAssetList(deleteRows, getUser(request).getCompNo());
    	
    	setAjaxStatus(request);
    }
}