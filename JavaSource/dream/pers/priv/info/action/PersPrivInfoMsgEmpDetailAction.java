package dream.pers.priv.info.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.pers.priv.info.dto.MaMyInfoCommonDTO;
import dream.pers.priv.info.dto.PersPrivInfoMsgEmpDetailDTO;
import dream.pers.priv.info.form.PersPrivInfoMsgEmpDetailForm;
import dream.pers.priv.info.service.PersPrivInfoMsgEmpDetailService;


/**
 * 상세
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @struts:action path="/persPrivInfoMsgEmpDetail" name="persPrivInfoMsgEmpDetailForm"
 *                input="/dream/pers/priv/info/persPrivInfoMsgEmpDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="persPrivInfoMsgEmpDetail" path="/dream/pers/priv/info/persPrivInfoMsgEmpDetail.jsp"
 *                        redirect="false"
 */
public class PersPrivInfoMsgEmpDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int DETAIL_INIT 	= 8001;
    /** 입력 */
    public static final int DETAIL_INPUT 	= 5002;
    /** 수정 */
    public static final int DETAIL_UPDATE 	= 6003;
    /** 중복확인 */
    public static final int DETAIL_CHECK	= 1004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        PersPrivInfoMsgEmpDetailForm persPrivInfoMsgEmpDetailForm = (PersPrivInfoMsgEmpDetailForm) form;
        switch (persPrivInfoMsgEmpDetailForm.getStrutsAction())
        {
            case PersPrivInfoMsgEmpDetailAction.DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, persPrivInfoMsgEmpDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case PersPrivInfoMsgEmpDetailAction.DETAIL_UPDATE:
            	updateDetail(persPrivInfoMsgEmpDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case PersPrivInfoMsgEmpDetailAction.DETAIL_INPUT:
            	insertDetail(persPrivInfoMsgEmpDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case PersPrivInfoMsgEmpDetailAction.DETAIL_CHECK:
            	validMsgObjType(persPrivInfoMsgEmpDetailForm, request);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 화면별 버튼 상세 조회
     * @author kim2107
     * @version $Id$
     * @since   1.0
     * 
     * @param request
     * @param persPrivInfoMsgEmpDetailForm
     */
    private void findDetail(HttpServletRequest request, PersPrivInfoMsgEmpDetailForm persPrivInfoMsgEmpDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	PersPrivInfoMsgEmpDetailService persPrivInfoMsgEmpDetailService = (PersPrivInfoMsgEmpDetailService)getBean("persPrivInfoMsgEmpDetailService");

    	// 넘겨진 pageId 구함
    	MaMyInfoCommonDTO maMyInfoCommonDTO = persPrivInfoMsgEmpDetailForm.getMaMyInfoCommonDTO();
        
        // 조회된 상세 부분
        PersPrivInfoMsgEmpDetailDTO persPrivInfoMsgEmpDetailDTO = persPrivInfoMsgEmpDetailService.findDetail(maMyInfoCommonDTO, getUser(request));
        
        // 조회된 상세  셋팅한다.
        persPrivInfoMsgEmpDetailForm.setPersPrivInfoMsgEmpDetailDTO(persPrivInfoMsgEmpDetailDTO);
    }
    /**
     * detail update
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param persPrivInfoMsgEmpDetailForm
     * @param request
     */
    private void updateDetail(PersPrivInfoMsgEmpDetailForm persPrivInfoMsgEmpDetailForm, HttpServletRequest request) throws Exception
    {
    	PersPrivInfoMsgEmpDetailService persPrivInfoMsgEmpDetailService = (PersPrivInfoMsgEmpDetailService) getBean("persPrivInfoMsgEmpDetailService");
        
        PersPrivInfoMsgEmpDetailDTO persPrivInfoMsgEmpDetailDTO = persPrivInfoMsgEmpDetailForm.getPersPrivInfoMsgEmpDetailDTO();
        MaMyInfoCommonDTO maMyInfoCommonDTO = persPrivInfoMsgEmpDetailForm.getMaMyInfoCommonDTO();
        
        persPrivInfoMsgEmpDetailDTO.setEnterBy(getUser(request).getUserId());
        
        User user = getUser(request);
        persPrivInfoMsgEmpDetailService.updateDetail(persPrivInfoMsgEmpDetailDTO,maMyInfoCommonDTO, user);
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param persPrivInfoMsgEmpDetailForm
     * @param request
     */
    private void insertDetail(PersPrivInfoMsgEmpDetailForm persPrivInfoMsgEmpDetailForm, HttpServletRequest request) throws Exception
    {
    	PersPrivInfoMsgEmpDetailService persPrivInfoMsgEmpDetailService = (PersPrivInfoMsgEmpDetailService) getBean("persPrivInfoMsgEmpDetailService");
        
        PersPrivInfoMsgEmpDetailDTO persPrivInfoMsgEmpDetailDTO = persPrivInfoMsgEmpDetailForm.getPersPrivInfoMsgEmpDetailDTO();
        
        MaMyInfoCommonDTO maMyInfoCommonDTO = persPrivInfoMsgEmpDetailForm.getMaMyInfoCommonDTO();
        persPrivInfoMsgEmpDetailDTO.setEnterBy(getUser(request).getUserId());
        persPrivInfoMsgEmpDetailDTO.setCompNo(getUser(request).getCompNo());
 
        User user = getUser(request);
        persPrivInfoMsgEmpDetailService.insertDetail(persPrivInfoMsgEmpDetailDTO, maMyInfoCommonDTO, user);
        
        setAjaxStatus(request);
    }
    /**
     * detail validation
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param persPrivInfoMsgEmpDetailForm
     * @param request
     */
    private void validMsgObjType(PersPrivInfoMsgEmpDetailForm persPrivInfoMsgEmpDetailForm, HttpServletRequest request) throws Exception
    {
    	PersPrivInfoMsgEmpDetailService persPrivInfoMsgEmpDetailService = (PersPrivInfoMsgEmpDetailService) getBean("persPrivInfoMsgEmpDetailService");
    	PersPrivInfoMsgEmpDetailDTO persPrivInfoMsgEmpDetailDTO = persPrivInfoMsgEmpDetailForm.getPersPrivInfoMsgEmpDetailDTO();
    	persPrivInfoMsgEmpDetailDTO.setEnterBy(getUser(request).getUserId());
    	persPrivInfoMsgEmpDetailDTO.setCompNo(getUser(request).getCompNo());
    	
    	User user = getUser(request);
    	String isValid = persPrivInfoMsgEmpDetailService.validMsgObjType(persPrivInfoMsgEmpDetailDTO, user);
    	
    	setAjaxDesc(request, isValid);
    }
}