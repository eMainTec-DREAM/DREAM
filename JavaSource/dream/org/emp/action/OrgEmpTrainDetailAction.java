package dream.org.emp.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.org.emp.dto.MaEmpCommonDTO;
import dream.org.emp.dto.OrgEmpTrainDetailDTO;
import dream.org.emp.dto.OrgEmpTrainListDTO;
import dream.org.emp.form.OrgEmpTrainDetailForm;
import dream.org.emp.service.OrgEmpTrainDetailService;

/**
 * 구매신청 item 상세
 * @author  kim21017
 * @version $Id: OrgEmpTrainDetailAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/orgEmpTrainDetail" name="orgEmpTrainDetailForm"
 *                input="/dream/org/emp/orgEmpTrainDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="orgEmpTrainDetail" path="/dream/org/emp/orgEmpTrainDetail.jsp"
 *                        redirect="false"
 */
public class OrgEmpTrainDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int BUY_ITEM_DETAIL_INIT 		= 8001;
    /** 수정 */
    public static final int BUY_ITEM_DETAIL_UPDATE 		= 6002;
    /** 입력 */
    public static final int BUY_ITEM_DETAIL_INPUT 		= 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        OrgEmpTrainDetailForm orgEmpTrainDetailForm = (OrgEmpTrainDetailForm) form;
        
        super.updateAudit(orgEmpTrainDetailForm.getOrgEmpTrainDetailDTO().getAuditKey()==""?orgEmpTrainDetailForm.getOrgEmpTrainListDTO().getAuditKey():orgEmpTrainDetailForm.getOrgEmpTrainDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (orgEmpTrainDetailForm.getStrutsAction())
        {
            case OrgEmpTrainDetailAction.BUY_ITEM_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, orgEmpTrainDetailForm);
                returnActionForward = mapping.findForward("orgEmpTrainDetail");
                break;
            case OrgEmpTrainDetailAction.BUY_ITEM_DETAIL_UPDATE:
            	updateDetail(request, orgEmpTrainDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case OrgEmpTrainDetailAction.BUY_ITEM_DETAIL_INPUT:
            	insertDetail(orgEmpTrainDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("orgEmpTrainDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 구매신청 item 조회
     * @author kim2107
     * @version $Id: OrgEmpTrainDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param orgEmpTrainDetailForm
     */
    private void findDetail(HttpServletRequest request, OrgEmpTrainDetailForm orgEmpTrainDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	OrgEmpTrainDetailService orgEmpTrainDetailService = (OrgEmpTrainDetailService)getBean("orgEmpTrainDetailService");
    	MaEmpCommonDTO maEmpCommonDTO = orgEmpTrainDetailForm.getMaEmpCommonDTO();
    	OrgEmpTrainListDTO orgEmpTrainListDTO = orgEmpTrainDetailForm.getOrgEmpTrainListDTO();
    	maEmpCommonDTO.setCompNo(getUser(request).getCompNo());
    	maEmpCommonDTO.setUserLang(getUser(request).getLangId());
        // 조회된 상세 부분
        OrgEmpTrainDetailDTO orgEmpTrainDetailDTO = orgEmpTrainDetailService.findDetail(orgEmpTrainListDTO,maEmpCommonDTO, getUser(request));
        
        // 조회된 상세  셋팅한다.
        orgEmpTrainDetailForm.setOrgEmpTrainDetailDTO(orgEmpTrainDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: OrgEmpTrainDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param orgEmpTrainDetailForm
     * @param request
     */
    private void updateDetail(HttpServletRequest request, OrgEmpTrainDetailForm orgEmpTrainDetailForm) throws Exception
    {
    	OrgEmpTrainDetailService orgEmpTrainDetailService = (OrgEmpTrainDetailService) getBean("orgEmpTrainDetailService");
        
        OrgEmpTrainDetailDTO orgEmpTrainDetailDTO = orgEmpTrainDetailForm.getOrgEmpTrainDetailDTO();
        MaEmpCommonDTO maEmpCommonDTO = orgEmpTrainDetailForm.getMaEmpCommonDTO();
    	maEmpCommonDTO.setCompNo(getUser(request).getCompNo());
        
        orgEmpTrainDetailService.updateDetail(orgEmpTrainDetailDTO, maEmpCommonDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: OrgEmpTrainDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param orgEmpTrainDetailForm
     * @param request
     */
    private void insertDetail(OrgEmpTrainDetailForm orgEmpTrainDetailForm, HttpServletRequest request) throws Exception
    {
    	OrgEmpTrainDetailService orgEmpTrainDetailService = (OrgEmpTrainDetailService) getBean("orgEmpTrainDetailService");
        
        OrgEmpTrainDetailDTO orgEmpTrainDetailDTO = orgEmpTrainDetailForm.getOrgEmpTrainDetailDTO();
        
        MaEmpCommonDTO maEmpCommonDTO = orgEmpTrainDetailForm.getMaEmpCommonDTO();
    	maEmpCommonDTO.setCompNo(getUser(request).getCompNo());
        
        orgEmpTrainDetailService.insertDetail(orgEmpTrainDetailDTO, maEmpCommonDTO);
        
        setAjaxStatus(request);
    }
}