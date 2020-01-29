package dream.mgr.user.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.mgr.user.dto.MaUserCommonDTO;
import dream.mgr.user.dto.MgrUserPlantauthDetailDTO;
import dream.mgr.user.form.MgrUserPlantauthDetailForm;
import dream.mgr.user.service.MgrUserPlantauthDetailService;

/**
 * 사용자 - 상세 action
 * 
 * @author 
 * @version $Id: $
 * @since 1.0
 * @struts:action path="/mgrUserPlantauthDetail" name="mgrUserPlantauthDetailForm"
 *                input="/dream/mgr/user/mgrUserPlantauthDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrUserPlantauthDetail" path="/dream/mgr/user/mgrUserPlantauthDetail.jsp"
 *                        redirect="false"
 */
public class MgrUserPlantauthDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int USERPLANT_DETAIL_INIT 		= 8001;
    /** 저장 */
    public static final int USERPLANT_DETAIL_INPUT 		= 5002;
    /** 수정 */
    public static final int USERPLANT_DETAIL_UPDATE     = 6003;
    /** 중복확인 */
    public static final int DETAIL_CHECK			    = 1004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MgrUserPlantauthDetailForm mgrUserPlantauthDetailForm = (MgrUserPlantauthDetailForm) form;
        
        super.updateAudit(mgrUserPlantauthDetailForm.getMgrUserPlantauthDetailDTO().getAuditKey()==""?mgrUserPlantauthDetailForm.getMaUserCommonDTO().getAuditKey():mgrUserPlantauthDetailForm.getMgrUserPlantauthDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (mgrUserPlantauthDetailForm.getStrutsAction())
        {
            case MgrUserPlantauthDetailAction.USERPLANT_DETAIL_INIT:
                this.findDetail(request, mgrUserPlantauthDetailForm);
                returnActionForward = mapping.findForward("mgrUserPlantauthDetail");
                break;
            case MgrUserPlantauthDetailAction.USERPLANT_DETAIL_INPUT:
            	insertDetail(mgrUserPlantauthDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MgrUserPlantauthDetailAction.USERPLANT_DETAIL_UPDATE:
            	updateDetail(mgrUserPlantauthDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MgrUserPlantauthDetailAction.DETAIL_CHECK:
            	validPlant(mgrUserPlantauthDetailForm, request);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("mgrUserPlantauthDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 사용자 상세 조회
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param mgrUserPlantauthDetailForm
     */
    private void findDetail(HttpServletRequest request, MgrUserPlantauthDetailForm mgrUserPlantauthDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MgrUserPlantauthDetailService mgrUserPlantauthDetailService = (MgrUserPlantauthDetailService)getBean("mgrUserPlantauthDetailService");

        // 넘겨진 사용자번호 구함
    	MaUserCommonDTO maUserCommonDTO = mgrUserPlantauthDetailForm.getMaUserCommonDTO();
        
        // 조회된 상세 부분
        MgrUserPlantauthDetailDTO mgrUserPlantauthDetailDTO = mgrUserPlantauthDetailService.findDetail(maUserCommonDTO, getUser(request));
        
        // 조회된 상세  셋팅한다.
        mgrUserPlantauthDetailForm.setMgrUserPlantauthDetailDTO(mgrUserPlantauthDetailDTO);
    }
    
    /**
     * detail insert
     * @author  
     * @version $Id:  $
     * @since   1.0
     * 
     * @param mgrUserPlantauthDetailForm
     * @param request
     */
    private void insertDetail(MgrUserPlantauthDetailForm mgrUserPlantauthDetailForm, HttpServletRequest request) throws Exception
    {
        MgrUserPlantauthDetailService mgrUserPlantauthDetailService = (MgrUserPlantauthDetailService) getBean("mgrUserPlantauthDetailService");
        
        MgrUserPlantauthDetailDTO mgrUserPlantauthDetailDTO = mgrUserPlantauthDetailForm.getMgrUserPlantauthDetailDTO();
        MaUserCommonDTO maUserCommonDTO = mgrUserPlantauthDetailForm.getMaUserCommonDTO();
        
        mgrUserPlantauthDetailDTO.setCompNo((getUser(request).getCompNo()));
        mgrUserPlantauthDetailDTO.setUserId(maUserCommonDTO.getUserId());
        
        mgrUserPlantauthDetailService.insertDetail(mgrUserPlantauthDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * detail update
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param mgrUserPlantauthDetailForm
     * @param request
     */
    private void updateDetail(MgrUserPlantauthDetailForm mgrUserPlantauthDetailForm, HttpServletRequest request) throws Exception
    {
    	MgrUserPlantauthDetailService mgrUserPlantauthDetailService = (MgrUserPlantauthDetailService) getBean("mgrUserPlantauthDetailService");
        
        MgrUserPlantauthDetailDTO mgrUserPlantauthDetailDTO = mgrUserPlantauthDetailForm.getMgrUserPlantauthDetailDTO();
        MaUserCommonDTO maUserCommonDTO = mgrUserPlantauthDetailForm.getMaUserCommonDTO();
        
        mgrUserPlantauthDetailDTO.setCompNo((getUser(request).getCompNo()));
        mgrUserPlantauthDetailDTO.setUserId(maUserCommonDTO.getUserId());   
        
        mgrUserPlantauthDetailService.updateDetail(mgrUserPlantauthDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * detail validation
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param mgrUserPlantauthDetailForm
     * @param request
     */
    private void validPlant(MgrUserPlantauthDetailForm mgrUserPlantauthDetailForm, HttpServletRequest request) throws Exception
    {
    	MgrUserPlantauthDetailService mgrUserPlantauthDetailService = (MgrUserPlantauthDetailService) getBean("mgrUserPlantauthDetailService");
    	
        MaUserCommonDTO maUserCommonDTO = mgrUserPlantauthDetailForm.getMaUserCommonDTO();
        MgrUserPlantauthDetailDTO mgrUserPlantauthDetailDTO = mgrUserPlantauthDetailForm.getMgrUserPlantauthDetailDTO();
    	
    	User user = getUser(request);
    	
    	String isValid = mgrUserPlantauthDetailService.validPlant(maUserCommonDTO, mgrUserPlantauthDetailDTO, user, "N");
    	
    	setAjaxDesc(request, isValid);
    }
    

}