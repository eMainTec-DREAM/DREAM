package dream.mgr.usrcd.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.mgr.usrcd.dto.MaCdUsrCommonDTO;
import dream.mgr.usrcd.dto.MaCdUsrDetailDTO;
import dream.mgr.usrcd.form.MaCdUsrDetailForm;
import dream.mgr.usrcd.service.MaCdUsrDetailService;

/**
 * 사용자코드관리 Action
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @struts:action path="/maCdUsrDetail" name="maCdUsrDetailForm"
 *                input="/dream/mgr/usrcd/maCdUsrDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maCdUsrDetail" path="/dream/mgr/usrcd/maCdUsrDetail.jsp"
 *                        redirect="false"           
 */
public class MaCdUsrDetailAction extends AuthAction
{ 
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int CDUSR_DETAIL_INIT 	= 8000;
    /** 생성 */
    public static final int CDUSR_DETAIL_INPUT  = 5001;
    /** 수정 */
    public static final int CDUSR_DETAIL_UPDATE = 6002;
    /** 중복 체크 */
    public static final int CDUSR_DETAIL_CHECK 	= 1003;

   
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaCdUsrDetailForm maCdUsrDetailForm = (MaCdUsrDetailForm) form;
        
        super.updateAudit(maCdUsrDetailForm.getMaCdUsrDetailDTO().getAuditKey()==""?maCdUsrDetailForm.getMaCdUsrCommonDTO().getAuditKey():maCdUsrDetailForm.getMaCdUsrDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maCdUsrDetailForm.getStrutsAction()) 
        {
            case MaCdUsrDetailAction.CDUSR_DETAIL_INIT:
                this.findDetail(request, maCdUsrDetailForm);
                returnActionForward = mapping.findForward("maCdUsrDetail");
                break;    
            case MaCdUsrDetailAction.CDUSR_DETAIL_INPUT:
                this.insertDetail(maCdUsrDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;    
            case MaCdUsrDetailAction.CDUSR_DETAIL_UPDATE:
            	this.updateDetail(maCdUsrDetailForm, request);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;  
            case MaCdUsrDetailAction.CDUSR_DETAIL_CHECK:
            	validDirType(maCdUsrDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;       
            default:
                returnActionForward = mapping.findForward("maCdUsrDetail");
                break;
        }
        return returnActionForward;
    }
    
    /**
     * 코드 상세 조회
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param maCdUsrDetailForm
     */
    private void findDetail(HttpServletRequest request,  MaCdUsrDetailForm maCdUsrDetailForm) throws Exception
    {
        MaCdUsrDetailService maCdUsrDetailService = (MaCdUsrDetailService) getBean("maCdUsrDetailService");        
        
        MaCdUsrCommonDTO maCdUsrCommonDTO = maCdUsrDetailForm.getMaCdUsrCommonDTO();
        
        MaCdUsrDetailDTO maCdUsrDetailDTO = maCdUsrDetailService.findDetail(getUser(request), maCdUsrCommonDTO.getCdUsrmId());
        
        maCdUsrDetailForm.setMaCdUsrDetailDTO(maCdUsrDetailDTO);
    }
    
    /**
     * 사용자코드 생성
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param form
     * @param request
     * @throws Exception
     */
    private void insertDetail(MaCdUsrDetailForm form, HttpServletRequest request) throws Exception 
    {
        MaCdUsrDetailService maCdUsrDetailService = (MaCdUsrDetailService) getBean("maCdUsrDetailService");    
        
        MaCdUsrDetailDTO maCdUsrDetailDTO = form.getMaCdUsrDetailDTO();
        
        // 로긴 회사코드를 셋팅한다.
        maCdUsrDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        maCdUsrDetailService.insertDetail(maCdUsrDetailDTO);
        
        setAjaxStatus(request);
    }
    
    /**
     * 사용자코드 수정
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param form
     * @param request
     * @throws Exception
     */
    private void updateDetail(MaCdUsrDetailForm form, HttpServletRequest request) throws Exception 
    {
    	MaCdUsrDetailService maCdUsrDetailService = (MaCdUsrDetailService) getBean("maCdUsrDetailService");    
    	
    	MaCdUsrDetailDTO maCdUsrDetailDTO = form.getMaCdUsrDetailDTO();
    	
    	// 로긴 회사코드를 셋팅한다.
    	maCdUsrDetailDTO.setCompNo((getUser(request).getCompNo()));
    	
    	maCdUsrDetailService.updateDetail(maCdUsrDetailDTO);
    	
    	setAjaxStatus(request);
    }
    
    /**
     * valid dirType
     * @author  
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maCdUsrDetailForm
     * @param request
     */
    private void validDirType(MaCdUsrDetailForm maCdUsrDetailForm, HttpServletRequest request) throws Exception
    {
    	MaCdUsrDetailService maCdUsrDetailService = (MaCdUsrDetailService) getBean("maCdUsrDetailService");
        
        MaCdUsrDetailDTO maCdUsrDetailDTO = maCdUsrDetailForm.getMaCdUsrDetailDTO();
    	
    	// 로긴 회사코드를 셋팅한다.
    	maCdUsrDetailDTO.setCompNo((getUser(request).getCompNo()));
    	
        String isValid = maCdUsrDetailService.validDirType(maCdUsrDetailDTO);
        
        setAjaxDesc(request, isValid);
    }
}