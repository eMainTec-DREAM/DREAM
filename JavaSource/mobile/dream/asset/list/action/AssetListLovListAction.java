package mobile.dream.asset.list.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.SuperAuthAction;
import mobile.dream.asset.list.dto.AssetListLovListDTO;
import mobile.dream.asset.list.form.AssetListLovListForm;
import mobile.dream.asset.list.service.AssetListLovListService;

/**
 * 설비 팝업
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * 
 * @struts:action path="/assetListLovList" name="assetListLovListForm"
 *                input="/mobile/dream/asset/list/assetListLovList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="assetListLovPopup" path="/mobile/dream/asset/list/assetListLovPopup.jsp"
 *                        redirect="false"
 */
public class AssetListLovListAction extends SuperAuthAction
{
    public static final int LOV_ASSET_DEFAULT 	= 1001;
    public static final int LOV_ASSET_FIND     = 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        AssetListLovListForm assetListLovListForm = (AssetListLovListForm)form;
        ActionForward returnActionForward = null;
        
        switch (assetListLovListForm.getStrutsAction())
        {
            case AssetListLovListAction.LOV_ASSET_DEFAULT :
                returnActionForward = mapping.findForward("assetListLovPopup");
                break;
            case AssetListLovListAction.LOV_ASSET_FIND :
                findEquipList(request, assetListLovListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssetListLovListAction.BASE_SET_HEADER:
                setHeader(request, response, assetListLovListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
                break;
        }
        
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, AssetListLovListForm assetListLovListForm) throws IOException
    {
        super.setHeader(request, response, assetListLovListForm.getListId(),assetListLovListForm.getCurrentPageId()); 
    }

    /**
     * TAPARTS 를 검색한다.
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param assetListLovListForm
     */
    private void findEquipList(HttpServletRequest request,
            AssetListLovListForm assetListLovListForm,HttpServletResponse response) throws IOException
    {
        AssetListLovListService assetListLovListService = (AssetListLovListService)getBean("assetListLovListService");
        
        AssetListLovListDTO assetListLovListDTO = assetListLovListForm.getAssetListLovListDTO();
        List resultList = assetListLovListService.findEquipList(assetListLovListDTO, getUser(request));
        
        super.makeJsonResult(resultList, request, response, assetListLovListForm.getListId());
    	
    }

}