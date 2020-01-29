package mobile.dream.asset.categ.list.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.SuperAuthAction;
import mobile.dream.asset.categ.list.dto.AssetCategListLovDTO;
import mobile.dream.asset.categ.list.form.AssetCategListLovForm;
import mobile.dream.asset.categ.list.service.AssetCategListLovService;

/**
 * 설비종류 팝업
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * 
 * @struts:action path="/assetCategListLov" name="assetCategListLovForm"
 *                input="/mobile/dream/asset/categ/list/assetCategListLov.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovEqCtgPopup" path="/mobile/dream/asset/categ/list/assetCategListLov.jsp"
 *                        redirect="false"
 */
public class AssetCategListLovAction extends SuperAuthAction
{
    public static final int LOV_EQCTG_DEFAULT 	= 1001;
    public static final int LOV_EQCTG_FIND      = 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        AssetCategListLovForm assetCategListLovForm = (AssetCategListLovForm)form;
        ActionForward returnActionForward = null;
        
        switch (assetCategListLovForm.getStrutsAction())
        {
            case AssetCategListLovAction.LOV_EQCTG_DEFAULT :
                returnActionForward = mapping.findForward("lovEqCtgPopup");
                break;
            case AssetCategListLovAction.LOV_EQCTG_FIND :
                findEqCtgList(request, assetCategListLovForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssetCategListLovAction.BASE_SET_HEADER:
                setHeader(request, response, assetCategListLovForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
                break;
        }
        
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, AssetCategListLovForm assetCategListLovForm) throws IOException
    {
        super.setHeader(request, response, assetCategListLovForm.getListId(),assetCategListLovForm.getCurrentPageId()); 
    }

    /**
     * TAEQCTG 를 검색한다.
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param assetCategListLovForm
     */
    private void findEqCtgList(HttpServletRequest request,
            AssetCategListLovForm assetCategListLovForm,HttpServletResponse response) throws IOException
    {
        AssetCategListLovService assetCategListLovService = (AssetCategListLovService)getBean("assetCategListLovService");
        
        AssetCategListLovDTO assetCategListLovDTO = assetCategListLovForm.getAssetCategListLovDTO();
        List resultList = assetCategListLovService.findEqCtgList(assetCategListLovDTO, getUser(request));
        
        super.makeJsonResult(resultList, request, response, assetCategListLovForm.getListId());
        //super.makeJsonResult(resultList, request, response, assetCategListLovForm.getListId());
    	
    }

}