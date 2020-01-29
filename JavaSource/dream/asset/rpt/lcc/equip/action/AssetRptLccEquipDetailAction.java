package dream.asset.rpt.lcc.equip.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.asset.rpt.lcc.equip.dto.AssetRptLccEquipDetailDTO;
import dream.asset.rpt.lcc.equip.form.AssetRptLccEquipDetailForm;
import dream.asset.rpt.lcc.equip.service.AssetRptLccEquipDetailService;

/**
 * 고장TOP(설비) 상세
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/assetRptLccEquipDetailList" name="assetRptLccEquipDetailForm"
 *                input="/dream/asset/rpt/lcc/equip/assetRptLccEquipDetailList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/assetRptLccEquipDetailChart" name="assetRptLccEquipDetailForm"
 *                input="/dream/asset/rpt/lcc/equip/assetRptLccEquipDetailChart.jsp" scope="request"
 *                validate="false"            
 * @struts:action path="/assetRptLccEquipWorkTimeDetailChart" name="assetRptLccEquipDetailForm"
 *                input="/dream/asset/rpt/lcc/equip/assetRptLccEquipWorkTimeDetailChart.jsp" scope="request"
 *                validate="false"            
 * @struts:action path="/assetRptLccEquipMaintAmtDetailChart" name="assetRptLccEquipDetailForm"
 *                input="/dream/asset/rpt/lcc/equip/assetRptLccEquipMaintAmtDetailChart.jsp" scope="request"
 *                validate="false"            
 * @struts:action path="/assetRptLccEquipFailCodeDetailChart" name="assetRptLccEquipDetailForm"
 *                input="/dream/asset/rpt/lcc/equip/assetRptLccEquipFailCodeDetailChart.jsp" scope="request"
 *                validate="false"            
 */
public class AssetRptLccEquipDetailAction extends AuthAction
{
    public static final int LCC_EQUIP_DETAIL_FIND 	= 1001;
    
    public static final int LCC_EQUIP_MO_FIND       = 1002;
    
    public static final int LCC_EQUIP_CA_FIND       = 1003;
    
    public static final int LCC_EQUIP_RE_FIND       = 1004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        AssetRptLccEquipDetailForm assetRptLccEquipDetailForm = (AssetRptLccEquipDetailForm) form;
        switch (assetRptLccEquipDetailForm.getStrutsAction())
        {
            case AssetRptLccEquipDetailAction.LCC_EQUIP_DETAIL_FIND:
                // 페이지 조회
                this.findDetail(request, response, assetRptLccEquipDetailForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssetRptLccEquipDetailAction.LCC_EQUIP_MO_FIND:
                this.findMo(request, response, assetRptLccEquipDetailForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssetRptLccEquipDetailAction.LCC_EQUIP_CA_FIND:
                this.findCa(request, response, assetRptLccEquipDetailForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssetRptLccEquipDetailAction.LCC_EQUIP_RE_FIND:
                this.findRe(request, response, assetRptLccEquipDetailForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssetRptLccEquipDetailAction.BASE_SET_HEADER:
                super.setHeader(request, response, assetRptLccEquipDetailForm.getListId(), assetRptLccEquipDetailForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssetRptLccEquipDetailAction.BASE_GRID_EXPORT:
                findDetail(request,response, assetRptLccEquipDetailForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 조회 
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param assetRptLccEquipDetailForm
     */
    private void findDetail(HttpServletRequest request,HttpServletResponse response, AssetRptLccEquipDetailForm assetRptLccEquipDetailForm, boolean excelExport) throws Exception
    {
        AssetRptLccEquipDetailService assetRptLccEquipDetailService = (AssetRptLccEquipDetailService) getBean("assetRptLccEquipDetailService");
        
        AssetRptLccEquipDetailDTO assetRptLccEquipDetailDTO = assetRptLccEquipDetailForm.getAssetRptLccEquipDetailDTO();
        
        List resultList = assetRptLccEquipDetailService.findDetail(assetRptLccEquipDetailDTO, getUser(request));
        
        if(excelExport) super.makeGridExport(resultList, request, response,assetRptLccEquipDetailForm.getListId(),assetRptLccEquipDetailForm.getCurrentPageId(), assetRptLccEquipDetailForm.getFileName());
        else super.makeJsonResult(resultList, request, response, assetRptLccEquipDetailForm.getListId());
    }
    
    private void findMo(HttpServletRequest request,HttpServletResponse response, AssetRptLccEquipDetailForm assetRptLccEquipDetailForm) throws Exception
    {
        AssetRptLccEquipDetailService assetRptLccEquipDetailService = (AssetRptLccEquipDetailService) getBean("assetRptLccEquipDetailService");
        
        AssetRptLccEquipDetailDTO assetRptLccEquipDetailDTO = assetRptLccEquipDetailForm.getAssetRptLccEquipDetailDTO();
        
        List resultList = assetRptLccEquipDetailService.findMo(assetRptLccEquipDetailDTO, getUser(request));
        
        CommonUtil.makeJsonResult(resultList, request, response, assetRptLccEquipDetailForm.getListId());
    }
    
    private void findCa(HttpServletRequest request,HttpServletResponse response, AssetRptLccEquipDetailForm assetRptLccEquipDetailForm) throws Exception
    {
        AssetRptLccEquipDetailService assetRptLccEquipDetailService = (AssetRptLccEquipDetailService) getBean("assetRptLccEquipDetailService");
        
        AssetRptLccEquipDetailDTO assetRptLccEquipDetailDTO = assetRptLccEquipDetailForm.getAssetRptLccEquipDetailDTO();
        
        List resultList = assetRptLccEquipDetailService.findCa(assetRptLccEquipDetailDTO, getUser(request));
        
        CommonUtil.makeJsonResult(resultList, request, response, assetRptLccEquipDetailForm.getListId());
    }
    
    private void findRe(HttpServletRequest request,HttpServletResponse response, AssetRptLccEquipDetailForm assetRptLccEquipDetailForm) throws Exception
    {
        AssetRptLccEquipDetailService assetRptLccEquipDetailService = (AssetRptLccEquipDetailService) getBean("assetRptLccEquipDetailService");
        
        AssetRptLccEquipDetailDTO assetRptLccEquipDetailDTO = assetRptLccEquipDetailForm.getAssetRptLccEquipDetailDTO();
        
        List resultList = assetRptLccEquipDetailService.findRe(assetRptLccEquipDetailDTO, getUser(request));
        
        CommonUtil.makeJsonResult(resultList, request, response, assetRptLccEquipDetailForm.getListId());
    }
    
}