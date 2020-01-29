package dream.part.list.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.part.list.dto.PartListSafQtyDetailDTO;
import dream.part.list.dto.PartListSafQtyListDTO;
import dream.part.list.form.PartListSafQtyDetailForm;
import dream.part.list.service.PartListSafQtyDetailService;

/**
 * 부품창고 보관위치 - Detail Action
 * 
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/partListSafQtyDetail" name="partListSafQtyDetailForm"
 *                input="/dream/part/list/partListSafQtyDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="partListSafQtyDetail" path="/dream/part/list/partListSafQtyDetail.jsp"
 *                        redirect="false"
 */
public class PartListSafQtyDetailAction extends AuthAction
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
        PartListSafQtyDetailForm partListSafQtyDetailForm = (PartListSafQtyDetailForm) form;
        
        super.updateAudit(partListSafQtyDetailForm.getPartListSafQtyDetailDTO().getAuditKey()==""?partListSafQtyDetailForm.getPartListSafQtyListDTO().getAuditKey():partListSafQtyDetailForm.getPartListSafQtyDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (partListSafQtyDetailForm.getStrutsAction())
        {
            case PartListSafQtyDetailAction.DETAIL_INIT:
                this.findDetail(request, response, partListSafQtyDetailForm);
                returnActionForward = mapping.findForward("partListSafQtyDetail");
                break;
            case PartListSafQtyDetailAction.DETAIL_INPUT:
            	insertDetail(request, response, partListSafQtyDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case PartListSafQtyDetailAction.DETAIL_UPDATE:
            	updateDetail(request, response, partListSafQtyDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case PartListSafQtyDetailAction.DETAIL_CHECK:
            	validEmpNo(partListSafQtyDetailForm, request);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("partListSafQtyDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 부품창고 보관위치 FIND DETAIL
     * @author cjscjs9
     * @param request
     * @param response
     * @param partListSafQtyDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, PartListSafQtyDetailForm partListSafQtyDetailForm) throws Exception 
    {   
    	PartListSafQtyDetailService partListSafQtyDetailService = (PartListSafQtyDetailService)getBean("partListSafQtyDetailService");
    	
    	PartListSafQtyListDTO partListSafQtyListDTO = partListSafQtyDetailForm.getPartListSafQtyListDTO(); 

    	User user = getUser(request);
    	
    	PartListSafQtyDetailDTO partListSafQtyDetailDTO = partListSafQtyDetailService.findPtWhEmpDetail(partListSafQtyListDTO, user);
    	partListSafQtyDetailForm.setPartListSafQtyDetailDTO(partListSafQtyDetailDTO);
    }

    /**
     * 부품창고 보관위치 INSERT DETAIL
     * @author cjscjs9
     * @param request
     * @param response
     * @param partListSafQtyDetailForm
     * @throws Exception
     */
    private void insertDetail(HttpServletRequest request, HttpServletResponse response, PartListSafQtyDetailForm partListSafQtyDetailForm) throws Exception
    {
    	PartListSafQtyDetailService partListSafQtyDetailService = (PartListSafQtyDetailService)getBean("partListSafQtyDetailService");
    	PartListSafQtyDetailDTO  partListSafQtyDetailDTO = partListSafQtyDetailForm.getPartListSafQtyDetailDTO(); 
    	
    	User user = getUser(request);
    	
    	partListSafQtyDetailService.insertPtWhEmpDetail(partListSafQtyDetailDTO, user);
        setAjaxStatus(request);
    }

    /**
     * 부품창고 보관위치 UPDATE DETAIL
     * @author cjscjs9
     * @param request
     * @param response
     * @param partListSafQtyDetailForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, PartListSafQtyDetailForm partListSafQtyDetailForm) throws Exception
    {
    	PartListSafQtyDetailService partListSafQtyDetailService = (PartListSafQtyDetailService)getBean("partListSafQtyDetailService");
    	PartListSafQtyDetailDTO  partListSafQtyDetailDTO = partListSafQtyDetailForm.getPartListSafQtyDetailDTO();
    	
    	User user = getUser(request);
    	
    	partListSafQtyDetailService.updatePtWhEmpDetail(partListSafQtyDetailDTO, user);
        
        setAjaxStatus(request);
    }
    
    /**
     * 부품창고 보관위치 중복체크 (valid emp id)  
     * @author cjscjs9
     * @version $Id: $
     * @since   1.0
     * 
     * @param maEmpDetailForm
     * @param request
     */
    private void validEmpNo(PartListSafQtyDetailForm partListSafQtyDetailForm, HttpServletRequest request) throws Exception
    {
    	PartListSafQtyDetailService partListSafQtyDetailService = (PartListSafQtyDetailService)getBean("partListSafQtyDetailService");
    	PartListSafQtyListDTO partListSafQtyListDTO = partListSafQtyDetailForm.getPartListSafQtyListDTO();
    	PartListSafQtyDetailDTO partListSafQtyDetailDTO = partListSafQtyDetailForm.getPartListSafQtyDetailDTO();
    	partListSafQtyDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        String isValid = partListSafQtyDetailService.validEmpNo(partListSafQtyListDTO, partListSafQtyDetailDTO, getUser(request));
        
        setAjaxDesc(request, isValid);
    }

}