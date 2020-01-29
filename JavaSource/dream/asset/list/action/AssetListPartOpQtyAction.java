package dream.asset.list.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.asset.list.dto.AssetListPartOpQtyDTO;
import dream.asset.list.form.AssetListPartOpQtyForm;
import dream.asset.list.service.AssetListPartOpQtyService;

/**
 * 설비운용기간사용예상수량
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/assetListPartOpQtyList" name="assetListPartOpQtyForm"
 *                input="/dream/asset/list/assetListPartOpQtyList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/assetListPartOpQtyDetail" name="assetListPartOpQtyForm"
 *                input="/dream/asset/list/assetListPartOpQtyDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="assetListPartOpQtyDetail" path="/dream/asset/list/assetListPartOpQtyDetail.jsp"
 *                        redirect="false"
 */

public class AssetListPartOpQtyAction extends AuthAction
{
    /** 목록 조회 */
    public static final int FIND_LIST           = 8001;
    /** 상세 저장 */
    public static final int INPUT_DETAIL        = 5001;
    /** 목록 삭제 */
    public static final int DELETE_LIST         = 7001;
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int INIT_DETAIL         = 8002;
    /** 상세 수정 */
    public static final int UPDATE_DETAIL       = 6001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AssetListPartOpQtyForm assetListPartOpQtyForm = (AssetListPartOpQtyForm) form;
        
        switch (assetListPartOpQtyForm.getStrutsAction())
        {
            case AssetListPartOpQtyAction.BASE_SET_HEADER:
                setHeader(request, response, assetListPartOpQtyForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssetListPartOpQtyAction.FIND_LIST:
                findList(request, response, assetListPartOpQtyForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case AssetListPartOpQtyAction.INPUT_DETAIL:
            	inputDetail(request, assetListPartOpQtyForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case AssetListPartOpQtyAction.DELETE_LIST:
                deleteList(request, assetListPartOpQtyForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case AssetListPartOpQtyAction.BASE_GRID_EXPORT:
            	findList(request, response, assetListPartOpQtyForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            case AssetListPartOpQtyAction.INIT_DETAIL:
                findDetail(request, response, assetListPartOpQtyForm);
                returnActionForward = mapping.findForward("assetListPartOpQtyDetail");
                break;
            case AssetListPartOpQtyAction.UPDATE_DETAIL:
                updateDetail(request, response, assetListPartOpQtyForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            default:
            	returnActionForward = mapping.getInputForward();
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, AssetListPartOpQtyForm assetListPartOpQtyForm) throws IOException
    {
        super.setHeader(request, response, assetListPartOpQtyForm.getListId(), assetListPartOpQtyForm.getCurrentPageId()); 
    }
    
    private void findList(HttpServletRequest request, HttpServletResponse response, AssetListPartOpQtyForm assetListPartOpQtyForm, boolean excelExport) throws Exception
    {
    	AssetListPartOpQtyService assetListPartOpQtyService = (AssetListPartOpQtyService) getBean("assetListPartOpQtyService");
    	AssetListPartOpQtyDTO assetListPartOpQtyDTO = assetListPartOpQtyForm.getAssetListPartOpQtyDTO();

    	//Paging
    	assetListPartOpQtyDTO.setIsLoadMaxCount("Y".equals(assetListPartOpQtyForm.getIsLoadMaxCount())?true:false);
    	assetListPartOpQtyDTO.setFirstRow(assetListPartOpQtyForm.getFirstRow());
    	assetListPartOpQtyDTO.setOrderBy(assetListPartOpQtyForm.getOrderBy());
    	assetListPartOpQtyDTO.setDirection(assetListPartOpQtyForm.getDirection());
    	
        List resultList = assetListPartOpQtyService.findList(assetListPartOpQtyDTO, getUser(request));
        //Paging
        String totalCount = "";
        if(Integer.parseInt(assetListPartOpQtyForm.getIsTotalCount()) == 0 && !excelExport) totalCount = assetListPartOpQtyService.findTotalCount(assetListPartOpQtyDTO,getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,assetListPartOpQtyForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
    private void inputDetail(HttpServletRequest request, AssetListPartOpQtyForm assetListPartOpQtyForm) throws Exception
    {
    	AssetListPartOpQtyService assetListPartOpQtyService = (AssetListPartOpQtyService) getBean("assetListPartOpQtyService");
    	AssetListPartOpQtyDTO assetListPartOpQtyDTO = assetListPartOpQtyForm.getAssetListPartOpQtyDTO();
        
        assetListPartOpQtyService.inputDetail(assetListPartOpQtyDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    private void deleteList(HttpServletRequest request, AssetListPartOpQtyForm assetListPartOpQtyForm) throws Exception
    {
        AssetListPartOpQtyService assetListPartOpQtyService = (AssetListPartOpQtyService) getBean("assetListPartOpQtyService");
        
        assetListPartOpQtyService.deleteList(assetListPartOpQtyForm.getDeleteRows(), getUser(request));
        
        setAjaxStatus(request);
    }
    
    private void findDetail(HttpServletRequest request, HttpServletResponse response, AssetListPartOpQtyForm assetListPartOpQtyForm) throws Exception 
    {   
        AssetListPartOpQtyService assetListPartOpQtyService = (AssetListPartOpQtyService)getBean("assetListPartOpQtyService");
        
        AssetListPartOpQtyDTO assetListPartOpQtyDTO = assetListPartOpQtyForm.getAssetListPartOpQtyDTO();
        
        assetListPartOpQtyDTO = assetListPartOpQtyService.findDetail(assetListPartOpQtyDTO, getUser(request));
        assetListPartOpQtyForm.setAssetListPartOpQtyDTO(assetListPartOpQtyDTO);
    }

    private void updateDetail(HttpServletRequest request, HttpServletResponse response, AssetListPartOpQtyForm assetListPartOpQtyForm) throws Exception
    {
        AssetListPartOpQtyService assetListPartOpQtyService = (AssetListPartOpQtyService)getBean("assetListPartOpQtyService");
        AssetListPartOpQtyDTO  assetListPartOpQtyDTO = assetListPartOpQtyForm.getAssetListPartOpQtyDTO();
        
        assetListPartOpQtyService.updateDetail(assetListPartOpQtyDTO, getUser(request));
        
        setAjaxStatus(request);
    }
}
