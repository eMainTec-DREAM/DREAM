package mobile.dream.asset.loc.list.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.SuperAuthAction;
import mobile.dream.asset.loc.list.dto.AssetLocListLovDTO;
import mobile.dream.asset.loc.list.form.AssetLocListLovForm;
import mobile.dream.asset.loc.list.service.AssetLocListLovService;


/**
 * 위치분류 팝업
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * 
 * @struts:action path="/assetLocListLov" name="assetLocListLovForm"
 *                input="/mobile/dream/asset/loc/list/assetLocListLov.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovEqLocPopup" path="/mobile/dream/asset/loc/list/assetLocListLov.jsp"
 *                        redirect="false"
 */
public class AssetLocListLovAction extends SuperAuthAction
{
    public static final int LOV_EQLOC_DEFAULT 	= 1001;
    public static final int LOV_EQLOC_FIND      = 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        AssetLocListLovForm assetLocListLovForm = (AssetLocListLovForm)form;
        ActionForward returnActionForward = null;
        
        switch (assetLocListLovForm.getStrutsAction())
        {
            case AssetLocListLovAction.LOV_EQLOC_DEFAULT :
                returnActionForward = mapping.findForward("lovEqLocPopup");
                break;
            case AssetLocListLovAction.LOV_EQLOC_FIND :
                findEqLocList(request, assetLocListLovForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssetLocListLovAction.BASE_SET_HEADER:
                setHeader(request, response, assetLocListLovForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
                break;
        }
        
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, AssetLocListLovForm assetLocListLovForm) throws IOException
    {
        super.setHeader(request, response, assetLocListLovForm.getListId(),assetLocListLovForm.getCurrentPageId()); 
    }

    /**
     * TAEQLOC 를 검색한다.
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param assetLocListLovForm
     */
    private void findEqLocList(HttpServletRequest request,
            AssetLocListLovForm assetLocListLovForm,HttpServletResponse response) throws IOException
    {
        AssetLocListLovService assetLocListLovService = (AssetLocListLovService)getBean("assetLocListLovService");
        
        AssetLocListLovDTO assetLocListLovDTO = assetLocListLovForm.getAssetLocListLovDTO();
        
        List resultList = assetLocListLovService.findEqLocList(assetLocListLovDTO, getUser(request));
        
        //super.makeJsonResult(resultList, request, response, assetLocListLovForm.getListId());
        super.makeJsonResult(resultList, request, response, assetLocListLovForm.getListId());
    }

}