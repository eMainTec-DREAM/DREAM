package dream.mgr.usrcd.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.mgr.usrcd.dto.MaCdUsrCdDetailDTO;
import dream.mgr.usrcd.dto.MaCdUsrCommonDTO;
import dream.mgr.usrcd.form.MaCdUsrCdDetailForm;
import dream.mgr.usrcd.service.MaCdUsrCdDetailService;

/**
 * 유형별 세부코드 팝업
 * @author   
 * @version $Id:  $
 * @since   1.0
 * @struts:action path="/maCdUsrCdDetail" name="maCdUsrCdDetailForm"
 *                input="/dream/mgr/usrcd/maCdUsrCdDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maCdUsrCdDetail" path="/dream/mgr/usrcd/maCdUsrCdDetail.jsp"
 *                        redirect="false"
 */
public class MaCdUsrCdDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int CDUSR_CD_DETAIL_INIT 	= 8001;
    /** 저장 */
    public static final int CDUSR_CD_DETAIL_INPUT 	= 5002;
    /** 수정 */
    public static final int CDUSR_CD_DETAIL_UPDATE 	= 6003;
    /** 중복 체크 */
    public static final int CDUSR_CD_DETAIL_CHECK 	= 1004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaCdUsrCdDetailForm maCdUsrCdDetailForm = (MaCdUsrCdDetailForm) form;
        
        super.updateAudit(maCdUsrCdDetailForm.getMaCdUsrCdDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maCdUsrCdDetailForm.getStrutsAction())
        {
            case MaCdUsrCdDetailAction.CDUSR_CD_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, maCdUsrCdDetailForm);
                returnActionForward = mapping.findForward("maCdUsrCdDetail");
                break;
            case MaCdUsrCdDetailAction.CDUSR_CD_DETAIL_INPUT:
            	insertDetail(maCdUsrCdDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaCdUsrCdDetailAction.CDUSR_CD_DETAIL_UPDATE:
            	updateDetail(maCdUsrCdDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaCdUsrCdDetailAction.CDUSR_CD_DETAIL_CHECK:
            	validCode(maCdUsrCdDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maCdUsrCdDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 상세 조회
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param request
     * @param maCdUsrCdDetailForm
     */
    private void findDetail(HttpServletRequest request, MaCdUsrCdDetailForm maCdUsrCdDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaCdUsrCdDetailService maCdUsrCdDetailService = (MaCdUsrCdDetailService)getBean("maCdUsrCdDetailService");

    	// pk
    	MaCdUsrCommonDTO maCdUsrCommonDTO = maCdUsrCdDetailForm.getMaCdUsrCommonDTO();
    	
        // 조회된 상세 부분
        MaCdUsrCdDetailDTO maCdUsrCdDetailDTO = maCdUsrCdDetailService.findDetail(maCdUsrCommonDTO, getUser(request));
        
        // 조회된 상세  셋팅한다.
        maCdUsrCdDetailForm.setMaCdUsrCdDetailDTO(maCdUsrCdDetailDTO);
    }
    
    /**
     * detail insert
     * @author   
     * @version $Id: MaCdUsrCdDetailAction.java,v 1.2 2015/12/02 01:21:30   Exp $
     * @since   1.0
     * 
     * @param maCdUsrCdDetailForm
     * @param request
     */
    private void insertDetail(MaCdUsrCdDetailForm maCdUsrCdDetailForm, HttpServletRequest request) throws Exception
    {
        MaCdUsrCdDetailService maCdUsrCdDetailService = (MaCdUsrCdDetailService) getBean("maCdUsrCdDetailService");
        
        MaCdUsrCdDetailDTO maCdUsrCdDetailDTO = maCdUsrCdDetailForm.getMaCdUsrCdDetailDTO();
        
        maCdUsrCdDetailService.insertDetail(maCdUsrCdDetailDTO,(getUser(request)));
        
        setAjaxStatus(request);
    }
    
    /**
     * detail update
     * @author   
     * @version $Id: $
     * @since   1.0
     * 
     * @param maCdUsrCdDetailForm
     * @param request
     */
    private void updateDetail(MaCdUsrCdDetailForm maCdUsrCdDetailForm, HttpServletRequest request) throws Exception
    {
    	MaCdUsrCdDetailService maCdUsrCdDetailService = (MaCdUsrCdDetailService) getBean("maCdUsrCdDetailService");
        
        MaCdUsrCdDetailDTO maCdUsrCdDetailDTO = maCdUsrCdDetailForm.getMaCdUsrCdDetailDTO();
        
        maCdUsrCdDetailService.updateDetail(maCdUsrCdDetailDTO,(getUser(request)));
        
        setAjaxStatus(request);
    }
    
    /**
     * valid code
     * @author   
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maCdUsrCdDetailForm
     * @param request
     */
    private void validCode(MaCdUsrCdDetailForm maCdUsrCdDetailForm, HttpServletRequest request) throws Exception
    {
    	MaCdUsrCdDetailService maCdUsrCdDetailService = (MaCdUsrCdDetailService) getBean("maCdUsrCdDetailService");
        
    	MaCdUsrCdDetailDTO maCdUsrCdDetailDTO = maCdUsrCdDetailForm.getMaCdUsrCdDetailDTO();
        
        String isValid = maCdUsrCdDetailService.validCode(maCdUsrCdDetailDTO,(getUser(request)));
        
        setAjaxDesc(request, isValid);
    }

}