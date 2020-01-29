package dream.part.list.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.part.list.dto.PartListBinDetailDTO;
import dream.part.list.dto.PartListBinListDTO;
import dream.part.list.form.PartListBinDetailForm;
import dream.part.list.service.PartListBinDetailService;

/**
 * 부품창고 보관위치 - Detail Action
 * 
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/partListBinDetail" name="partListBinDetailForm"
 *                input="/dream/part/list/partListBinDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="partListBinDetail" path="/dream/part/list/partListBinDetail.jsp"
 *                        redirect="false"
 */
public class PartListBinDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int DETAIL_INIT 		= 8001;
    /** 저장 */
    public static final int DETAIL_INPUT 		= 5002;
    /** 수정 */
    public static final int DETAIL_UPDATE 		= 6003;
    /** 중복 체크 */
    public static final int DETAIL_CHECK		= 1004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        PartListBinDetailForm partListBinDetailForm = (PartListBinDetailForm) form;
        
        super.updateAudit(partListBinDetailForm.getPartListBinDetailDTO().getAuditKey()==""?partListBinDetailForm.getPartListBinListDTO().getAuditKey():partListBinDetailForm.getPartListBinDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (partListBinDetailForm.getStrutsAction())
        {
            case PartListBinDetailAction.DETAIL_INIT:
                this.findDetail(request, response, partListBinDetailForm);
                returnActionForward = mapping.findForward("partListBinDetail");
                break;
            case PartListBinDetailAction.DETAIL_INPUT:
            	insertDetail(request, response, partListBinDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case PartListBinDetailAction.DETAIL_UPDATE:
            	updateDetail(request, response, partListBinDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case PartListBinDetailAction.DETAIL_CHECK:
            	validPtBin(partListBinDetailForm, request);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("partListBinDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 부품창고 보관위치 FIND DETAIL
     * @author cjscjs9
     * @param request
     * @param response
     * @param partListBinDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, PartListBinDetailForm partListBinDetailForm) throws Exception 
    {   
    	PartListBinDetailService partListBinDetailService = (PartListBinDetailService)getBean("partListBinDetailService");
    	
    	PartListBinListDTO partListBinListDTO = partListBinDetailForm.getPartListBinListDTO(); 

    	User user = getUser(request);
    	
    	PartListBinDetailDTO partListBinDetailDTO = partListBinDetailService.findPtWhBinDetail(partListBinListDTO, user);
    	partListBinDetailForm.setPartListBinDetailDTO(partListBinDetailDTO);
    }

    /**
     * 부품창고 보관위치 INSERT DETAIL
     * @author cjscjs9
     * @param request
     * @param response
     * @param partListBinDetailForm
     * @throws Exception
     */
    private void insertDetail(HttpServletRequest request, HttpServletResponse response, PartListBinDetailForm partListBinDetailForm) throws Exception
    {
    	PartListBinDetailService partListBinDetailService = (PartListBinDetailService)getBean("partListBinDetailService");
    	PartListBinDetailDTO  partListBinDetailDTO = partListBinDetailForm.getPartListBinDetailDTO(); 
    	
    	User user = getUser(request);
    	
    	partListBinDetailService.insertPtWhBinDetail(partListBinDetailDTO, user);
        setAjaxStatus(request);
    }

    /**
     * 부품창고 보관위치 UPDATE DETAIL
     * @author cjscjs9
     * @param request
     * @param response
     * @param partListBinDetailForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, PartListBinDetailForm partListBinDetailForm) throws Exception
    {
    	PartListBinDetailService partListBinDetailService = (PartListBinDetailService)getBean("partListBinDetailService");
    	PartListBinDetailDTO  partListBinDetailDTO = partListBinDetailForm.getPartListBinDetailDTO();
    	
    	User user = getUser(request);
    	
    	partListBinDetailService.updatePtWhBinDetail(partListBinDetailDTO, user);
        
        setAjaxStatus(request);
    }
    
    /**
     * 부품창고 보관위치 중복체크 (창고명, 재고등급, 보관위치)  
     * @author cjscjs9
     * @version $Id: $
     * @since   1.0
     * 
     * @param maEmpDetailForm
     * @param request
     */
    private void validPtBin(PartListBinDetailForm partListBinDetailForm, HttpServletRequest request) throws Exception
    {
    	PartListBinDetailService partListBinDetailService = (PartListBinDetailService)getBean("partListBinDetailService");
    	PartListBinListDTO partListBinListDTO = partListBinDetailForm.getPartListBinListDTO();
    	PartListBinDetailDTO partListBinDetailDTO = partListBinDetailForm.getPartListBinDetailDTO();
    	partListBinDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        String isValid = partListBinDetailService.validPtBin(partListBinListDTO, partListBinDetailDTO, getUser(request));
        
        setAjaxDesc(request, isValid);
    }

}