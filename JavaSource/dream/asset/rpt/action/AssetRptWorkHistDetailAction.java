package dream.asset.rpt.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.asset.rpt.dto.AssetRptWorkHistCommonDTO;
import dream.asset.rpt.dto.AssetRptWorkHistDetailDTO;
import dream.asset.rpt.form.AssetRptWorkHistDetailForm;
import dream.asset.rpt.service.AssetRptWorkHistDetailService;

/**
 * 설비이력(과거) - Detail Action
 * 
 * @author js.lee
 * @version $Id: $
 * @since 1.0
 * @struts:action path="/assetRptWorkHistDetail" name="assetRptWorkHistDetailForm"
 *                input="/dream/asset/rpt/assetRptWorkHistDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="assetRptWorkHistDetail" path="/dream/asset/rpt/assetRptWorkHistDetail.jsp"
 *                        redirect="false"
 */
public class AssetRptWorkHistDetailAction extends AuthAction
{
	//일반 페이지 적용시 AuthAction 으로 변경해야합니다.
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int DETAIL_INIT 		= 8001;
    /** 저장 */
    public static final int DETAIL_INPUT 		= 5002;
    /** 수정 */
    public static final int DETAIL_UPDATE 		= 6003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        AssetRptWorkHistDetailForm assetRptWorkHistDetailForm = (AssetRptWorkHistDetailForm) form;
        
        super.updateAudit(assetRptWorkHistDetailForm.getAssetRptWorkHistDetailDTO().getAuditKey()==""?assetRptWorkHistDetailForm.getAssetRptWorkHistCommonDTO().getAuditKey():assetRptWorkHistDetailForm.getAssetRptWorkHistDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (assetRptWorkHistDetailForm.getStrutsAction())
        {
            case AssetRptWorkHistDetailAction.DETAIL_INIT:
                this.findDetail(request, response, assetRptWorkHistDetailForm);
                returnActionForward = mapping.findForward("assetRptWorkHistDetail");
                break;
            case AssetRptWorkHistDetailAction.DETAIL_INPUT:
            	insertDetail(request, response, assetRptWorkHistDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case AssetRptWorkHistDetailAction.DETAIL_UPDATE:
            	updateDetail(request, response, assetRptWorkHistDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("assetRptWorkHistDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * FIND DETAIL
     * @param request
     * @param response
     * @param assetRptWorkHistDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, AssetRptWorkHistDetailForm assetRptWorkHistDetailForm) throws Exception 
    {   
    	AssetRptWorkHistDetailService assetRptWorkHistDetailService = (AssetRptWorkHistDetailService)getBean("assetRptWorkHistDetailService");
    	
    	AssetRptWorkHistCommonDTO assetRptWorkHistCommonDTO = assetRptWorkHistDetailForm.getAssetRptWorkHistCommonDTO(); 

    	//컨설트 페이지에서 user객체에 회사코드가 담겨있지않습니다. 이 페이지는 교육용 페이지이기 때문에 여기에서 회사코드를 임의로 설정했습니다.
    	User user = getUser(request);
    	
    	AssetRptWorkHistDetailDTO assetRptWorkHistDetailDTO = assetRptWorkHistDetailService.findRptWorkHistDetail(assetRptWorkHistCommonDTO, user);
    	assetRptWorkHistDetailForm.setAssetRptWorkHistDetailDTO(assetRptWorkHistDetailDTO);
    }

    /**
     * INSERT DETAIL
     * @param request
     * @param response
     * @param assetRptWorkHistDetailForm
     * @throws Exception
     */
    private void insertDetail(HttpServletRequest request, HttpServletResponse response, AssetRptWorkHistDetailForm assetRptWorkHistDetailForm) throws Exception
    {
    	AssetRptWorkHistDetailService assetRptWorkHistDetailService = (AssetRptWorkHistDetailService)getBean("assetRptWorkHistDetailService");
    	AssetRptWorkHistDetailDTO  assetRptWorkHistDetailDTO = assetRptWorkHistDetailForm.getAssetRptWorkHistDetailDTO(); 
    	
    	User user = getUser(request);
    	
    	assetRptWorkHistDetailService.insertRptWorkHistDetail(assetRptWorkHistDetailDTO, user);
        setAjaxStatus(request);
    }

    /**
     * UPDATE DETAIL
     * @param request
     * @param response
     * @param assetRptWorkHistDetailForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, AssetRptWorkHistDetailForm assetRptWorkHistDetailForm) throws Exception
    {
    	AssetRptWorkHistDetailService assetRptWorkHistDetailService = (AssetRptWorkHistDetailService)getBean("assetRptWorkHistDetailService");
    	AssetRptWorkHistDetailDTO  assetRptWorkHistDetailDTO = assetRptWorkHistDetailForm.getAssetRptWorkHistDetailDTO();
    	
    	User user = getUser(request);
    	
    	assetRptWorkHistDetailService.updateRptWorkHistDetail(assetRptWorkHistDetailDTO, user);
        
        setAjaxStatus(request);
    }

}