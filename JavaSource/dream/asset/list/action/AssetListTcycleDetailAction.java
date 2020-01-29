package dream.asset.list.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.asset.list.dto.AssetListTcycleDetailDTO;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.form.AssetListTcycleDetailForm;
import dream.asset.list.service.AssetListTcycleDetailService;

/**
 * 교정주기 상세
 * @author  kim21017
 * @version $Id: AssetListTcycleDetailAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/assetListTcycleDetail" name="assetListTcycleDetailForm"
 *                input="/dream/asset/list/assetListTcycleDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="assetListTcycleDetail" path="/dream/asset/list/assetListTcycleDetail.jsp"
 *                        redirect="false"
 */
public class AssetListTcycleDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int ASSET_LIST_TCYCLE_DETAIL_INIT 		= 8001;
    /** 수정 */
    public static final int ASSET_LIST_TCYCLE_DETAIL_UPDATE 	= 6002;
    /** 입력 */
    public static final int ASSET_LIST_TCYCLE_DETAIL_INPUT 		= 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        AssetListTcycleDetailForm assetListTcycleDetailForm = (AssetListTcycleDetailForm) form;
        
        super.updateAudit(assetListTcycleDetailForm.getAssetListTcycleDetailDTO().getAuditKey()==""?assetListTcycleDetailForm.getAssetListTcycleListDTO().getAuditKey():assetListTcycleDetailForm.getAssetListTcycleDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (assetListTcycleDetailForm.getStrutsAction())
        {
            case AssetListTcycleDetailAction.ASSET_LIST_TCYCLE_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, assetListTcycleDetailForm);
                returnActionForward = mapping.findForward("assetListTcycleDetail");
                break;
            case AssetListTcycleDetailAction.ASSET_LIST_TCYCLE_DETAIL_UPDATE:
            	updateDetail(assetListTcycleDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case AssetListTcycleDetailAction.ASSET_LIST_TCYCLE_DETAIL_INPUT:
            	insertDetail(assetListTcycleDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("assetListTcycleDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 교정주기 조회 
     * @author kim2107
     * @version $Id: AssetListTcycleDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param assetListTcycleDetailForm
     */
    private void findDetail(HttpServletRequest request, AssetListTcycleDetailForm assetListTcycleDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	AssetListTcycleDetailService assetListTcycleDetailService = (AssetListTcycleDetailService)getBean("assetListTcycleDetailService");

    	// 설비Id 구함
        String equipId = assetListTcycleDetailForm.getMaEqMstrCommonDTO().getEquipId();
        // pmCycleId 구함
        String eqPmCycleId = assetListTcycleDetailForm.getAssetListTcycleListDTO().getEqPmCycleId();
        
        // 조회된 상세 부분
        AssetListTcycleDetailDTO assetListTcycleDetailDTO = assetListTcycleDetailService.findDetail(equipId, eqPmCycleId, getUser(request));
        
        // 조회된 상세  셋팅한다.
        assetListTcycleDetailForm.setAssetListTcycleDetailDTO(assetListTcycleDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: AssetListTcycleDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param assetListTcycleDetailForm
     * @param request
     */
    private void updateDetail(AssetListTcycleDetailForm assetListTcycleDetailForm, HttpServletRequest request) throws Exception
    {
    	AssetListTcycleDetailService assetListTcycleDetailService = (AssetListTcycleDetailService) getBean("assetListTcycleDetailService");
        
    	MaEqMstrCommonDTO maEqMstrCommonDTO = assetListTcycleDetailForm.getMaEqMstrCommonDTO();
        AssetListTcycleDetailDTO assetListTcycleDetailDTO = assetListTcycleDetailForm.getAssetListTcycleDetailDTO();
        
        maEqMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        assetListTcycleDetailDTO.setEnterBy(getUser(request).getUserId());
        
        assetListTcycleDetailService.updateDetail(assetListTcycleDetailDTO, maEqMstrCommonDTO,getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: AssetListTcycleDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param assetListTcycleDetailForm
     * @param request
     */
    private void insertDetail(AssetListTcycleDetailForm assetListTcycleDetailForm, HttpServletRequest request) throws Exception
    {
    	AssetListTcycleDetailService assetListTcycleDetailService = (AssetListTcycleDetailService) getBean("assetListTcycleDetailService");
        
        MaEqMstrCommonDTO maEqMstrCommonDTO = assetListTcycleDetailForm.getMaEqMstrCommonDTO();
        AssetListTcycleDetailDTO assetListTcycleDetailDTO = assetListTcycleDetailForm.getAssetListTcycleDetailDTO();
        
        maEqMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        assetListTcycleDetailDTO.setEnterBy(getUser(request).getUserId());
        
        String rsltInt = assetListTcycleDetailService.insertDetail(assetListTcycleDetailDTO, maEqMstrCommonDTO,getUser(request));
        
        setAjaxDesc(request, rsltInt);
    }
}