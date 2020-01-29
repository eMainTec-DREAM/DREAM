package dream.mgr.usrrpt.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.mgr.usrrpt.dto.MaUserRptTableDetailDTO;
import dream.mgr.usrrpt.form.MaUserRptTableDetailForm;
import dream.mgr.usrrpt.service.MaUserRptTableDetailService;

/**
 * 
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/maUserRptTableDetail" name="maUserRptTableDetailForm"
 *                input="/dream/mgr/usrrpt/maUserRptTableDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maUserRptTableDetail" path="/dream/mgr/usrrpt/maUserRptTableDetail.jsp"
 *                        redirect="false"
 */
public class MaUserRptTableDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int USER_TABLE_DETAIL_INIT  	= 8001;
    /** 수정 */
    public static final int USER_TABLE_DETAIL_UPDATE    = 6002;
    /** 입력 */
    public static final int USER_TABLE_DETAIL_INPUT 	= 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaUserRptTableDetailForm maUserRptTableDetailForm = (MaUserRptTableDetailForm) form;
       
        super.updateAudit(maUserRptTableDetailForm.getMaUserRptTableDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maUserRptTableDetailForm.getStrutsAction())
        {
            case MaUserRptTableDetailAction.USER_TABLE_DETAIL_INIT:
                this.findDetail(request, maUserRptTableDetailForm);
                returnActionForward = mapping.findForward("maUserRptTableDetail");
                break;
            case MaUserRptTableDetailAction.USER_TABLE_DETAIL_UPDATE:
            	updateDetail(maUserRptTableDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaUserRptTableDetailAction.USER_TABLE_DETAIL_INPUT:
            	insertDetail(maUserRptTableDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
            	checkNextNum(request, maUserRptTableDetailForm);
                returnActionForward = mapping.findForward("maUserRptTableDetail");
                break;
        }

        return returnActionForward;
    }
    
    private void checkNextNum(HttpServletRequest request, MaUserRptTableDetailForm maUserRptTableDetailForm) {
    	MaUserRptTableDetailService maUserRptTableDetailService = (MaUserRptTableDetailService)getBean("maUserRptTableDetailService");

        // 조회된 상세 부분
        int stepNum = maUserRptTableDetailService.checkNextNum(maUserRptTableDetailForm.getMaUserRptCommonDTO().getUsrrptlistId()) + 1;
        
        MaUserRptTableDetailDTO dto = new MaUserRptTableDetailDTO();
        dto.setStepNum(stepNum+"");
        // 조회된 상세  셋팅한다.
        maUserRptTableDetailForm.setMaUserRptTableDetailDTO(dto);
	}

	/**
     *  상세 조회
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maUserRptTableDetailForm
     */
    private void findDetail(HttpServletRequest request, MaUserRptTableDetailForm maUserRptTableDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaUserRptTableDetailService maUserRptTableDetailService = (MaUserRptTableDetailService)getBean("maUserRptTableDetailService");

        // 조회된 상세 부분
        MaUserRptTableDetailDTO maUserRptTableDetailDTO = maUserRptTableDetailService.findDetail(maUserRptTableDetailForm.getMaUserRptCommonDTO(), getUser(request));
        
        // 조회된 상세  셋팅한다.
        maUserRptTableDetailForm.setMaUserRptTableDetailDTO(maUserRptTableDetailDTO);
    }
    
    /**
     * detail update
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maUserRptTableDetailForm
     * @param request
     */
    private void updateDetail(MaUserRptTableDetailForm maUserRptTableDetailForm, HttpServletRequest request) throws Exception
    {
    	MaUserRptTableDetailService maUserRptTableDetailService = (MaUserRptTableDetailService) getBean("maUserRptTableDetailService");
        
        MaUserRptTableDetailDTO maUserRptTableDetailDTO = maUserRptTableDetailForm.getMaUserRptTableDetailDTO();
        
        maUserRptTableDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        maUserRptTableDetailService.updateDetail(maUserRptTableDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * detail insert
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maUserRptTableDetailForm
     * @param request
     */
    private void insertDetail(MaUserRptTableDetailForm maUserRptTableDetailForm, HttpServletRequest request) throws Exception
    {
    	MaUserRptTableDetailService maUserRptTableDetailService = (MaUserRptTableDetailService) getBean("maUserRptTableDetailService");
        
        MaUserRptTableDetailDTO maUserRptTableDetailDTO = maUserRptTableDetailForm.getMaUserRptTableDetailDTO();
        
        maUserRptTableDetailDTO.setCompNo((getUser(request).getCompNo()));
        maUserRptTableDetailService.insertDetail(maUserRptTableDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
}