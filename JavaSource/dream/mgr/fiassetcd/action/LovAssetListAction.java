package dream.mgr.fiassetcd.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.mgr.fiassetcd.dto.LovAssetListDTO;
import dream.mgr.fiassetcd.form.LovAssetListForm;
import dream.mgr.fiassetcd.service.LovAssetListService;

/**
 * 자산 팝업
 * @author  kim21017
 * @version $Id: LovAssetListAction.java,v 1.0 2015/01/18 07:49:29 kim21017 Exp $
 * @since   1.0
 * 
 * @struts:action path="/lovAssetList" name="lovAssetListForm"
 *                input="/dream/mgr/fiassetcd/lovAssetPopup.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/lovAssetAcList" name="lovAssetListForm"
 *                input="/dream/mgr/fiassetcd/lovAssetAcList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="lovAssetPopup" path="/dream/mgr/fiassetcd/lovAssetPopup.jsp"
 *                        redirect="false"
 */
public class LovAssetListAction extends AuthAction
{
    public static final int LOV_ASSET_DEFAULT 	= 1001;
    public static final int LOV_ASSET_FIND 		= 1002;
    
    public static final int LOV_ASSET_AC_FIND 	= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        LovAssetListForm lovAssetListForm = (LovAssetListForm)form;
        ActionForward returnActionForward = null;
        
        switch (lovAssetListForm.getStrutsAction())
        {
            case LovAssetListAction.LOV_ASSET_DEFAULT :
                returnActionForward = mapping.findForward("lovAssetPopup");
                break;
            case LovAssetListAction.LOV_ASSET_FIND :
                findAssetList(request, lovAssetListForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovAssetListAction.LOV_ASSET_AC_FIND :
                findAssetAcList(request, lovAssetListForm,response,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case LovAssetListAction.BASE_SET_HEADER:
                setHeader(request, response, lovAssetListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
            	returnActionForward = mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }

    private void findAssetAcList(HttpServletRequest request, LovAssetListForm lovAssetListForm,HttpServletResponse response,boolean excelExport) throws IOException {
    	
    	LovAssetListService lovAssetListService = (LovAssetListService)getBean("lovAssetListService");
        LovAssetListDTO lovAssetListDTO = lovAssetListForm.getLovAssetListDTO();
        
        //Paging
        lovAssetListDTO.setIsLoadMaxCount("Y".equals(lovAssetListForm.getIsLoadMaxCount())?true:false);
        lovAssetListDTO.setFirstRow(lovAssetListForm.getFirstRow());
        lovAssetListDTO.setOrderBy(lovAssetListForm.getOrderBy());
        lovAssetListDTO.setDirection(lovAssetListForm.getDirection());
        
        List resultList = lovAssetListService.findAssetAcList(lovAssetListDTO, getUser(request), lovAssetListForm);
        
        //Paging
        String totalCount = "";
                
        if(Integer.parseInt(lovAssetListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = lovAssetListService.findTotalCount(lovAssetListDTO,getUser(request),lovAssetListForm);
                
        if(excelExport) super.makeGridExport(resultList, request, response, lovAssetListForm.getListId(),lovAssetListForm.getCurrentPageId(), lovAssetListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
	}

	private void setHeader(HttpServletRequest request, HttpServletResponse response, LovAssetListForm lovAssetListForm) throws IOException
    {
        super.setHeader(request, response, lovAssetListForm.getListId(),lovAssetListForm.getCurrentPageId()); 
    }

    /**
     * TAASSET을 검색한다.
     * @author  kim21017
     * @version $Id: LovAssetListAction.java,v 1.2 2014/01/28 07:49:29 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param lovAssetListForm
     */
    private void findAssetList(HttpServletRequest request,
            LovAssetListForm lovAssetListForm,HttpServletResponse response) throws IOException
    {
        LovAssetListService lovAssetListService = (LovAssetListService)getBean("lovAssetListService");
        LovAssetListDTO lovAssetListDTO = lovAssetListForm.getLovAssetListDTO();
        List resultList = lovAssetListService.findAssetList(lovAssetListDTO, getUser(request));
        
        super.makeJsonResult(resultList, request, response, lovAssetListForm.getListId());
    	
    }

}