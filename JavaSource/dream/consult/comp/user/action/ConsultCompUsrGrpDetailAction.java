package dream.consult.comp.user.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;

import common.struts.AuthAction;
import common.struts.ConsultAuthAction;
import dream.consult.comp.user.dto.ConsultCompUsrGrpDetailDTO;
import dream.consult.comp.user.form.ConsultCompUsrGrpDetailForm;
import dream.consult.comp.user.service.ConsultCompUsrGrpDetailService;

/**
 * 권한그룹 - 상세 action
 * 
 * @author 
 * @version $Id: $
 * @since 1.0
 * @struts:action path="/consultCompUsrGrpDetail" name="consultCompUsrGrpDetailForm"
 *                input="/dream/consult/comp/user/consultCompUsrGrpDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="consultCompUsrGrpDetail" path="/dream/consult/comp/user/consultCompUsrGrpDetail.jsp"
 *                        redirect="false"
 */
public class ConsultCompUsrGrpDetailAction extends ConsultAuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int USRGRP_DETAIL_INIT 		= 8001;
    /** 저장 */
    public static final int USRGRP_DETAIL_INPUT 	= 5002;
    /** 수정 */
    public static final int USRGRP_DETAIL_UPDATE 	= 6003;
    /** 중복 체크 */
    public static final int USRGRP_DETAIL_CHECK 	= 1004;
    
    public static final int USRGRP_DETAIL_TREE      = 1005;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        ConsultCompUsrGrpDetailForm consultCompUsrGrpDetailForm = (ConsultCompUsrGrpDetailForm) form;
        
//        super.updateAudit(consultCompUsrGrpDetailForm.getConsultCompUsrGrpDetailDTO().getAuditKey()==""?consultCompUsrGrpDetailForm.getConsultCompUsrGrpCommonDTO().getAuditKey():consultCompUsrGrpDetailForm.getConsultCompUsrGrpDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (consultCompUsrGrpDetailForm.getStrutsAction())
        {
            case ConsultCompUsrGrpDetailAction.USRGRP_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, consultCompUsrGrpDetailForm);
                this.findMenuList(request, consultCompUsrGrpDetailForm);
                returnActionForward = mapping.findForward("consultCompUsrGrpDetail");
                break;
            case ConsultCompUsrGrpDetailAction.USRGRP_DETAIL_INPUT:
            	insertDetail(consultCompUsrGrpDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case ConsultCompUsrGrpDetailAction.USRGRP_DETAIL_UPDATE:
            	updateDetail(consultCompUsrGrpDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case ConsultCompUsrGrpDetailAction.USRGRP_DETAIL_CHECK:
            	validEmpNo(consultCompUsrGrpDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case ConsultCompUsrGrpDetailAction.USRGRP_DETAIL_TREE:
                this.findMenuListForTree(request, consultCompUsrGrpDetailForm, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                this.findMenuList(request, consultCompUsrGrpDetailForm);
                returnActionForward = mapping.findForward("consultCompUsrGrpDetail");
                break;
        }

        return returnActionForward;
    }
    
    private void findMenuListForTree(HttpServletRequest request, ConsultCompUsrGrpDetailForm consultCompUsrGrpDetailForm, HttpServletResponse response) throws IOException
    {
        // Service 객체 생성
        ConsultCompUsrGrpDetailService consultCompUsrGrpDetailService = (ConsultCompUsrGrpDetailService)getBean("consultCompUsrGrpDetailService");

        // 넘겨진 권한그룹번호 구함
        String compNo = consultCompUsrGrpDetailForm.getConsultCompUsrGrpCommonDTO().getCompNo();
        String usrGrpId  = consultCompUsrGrpDetailForm.getConsultCompUsrGrpCommonDTO().getUsrGrpId();
        
        // 조회된 상세 부분
        Map consultCompUsrGrpMenuDTOList = consultCompUsrGrpDetailService.findMenuListForTree(consultCompUsrGrpDetailForm.getConsultCompUsrGrpCommonDTO(), getUser(request));

        Gson gson = new Gson();
        
        String jsonString = gson.toJson(consultCompUsrGrpMenuDTOList);
        
        jsonString = jsonString.replaceAll("\"OPEN\"","open");
        jsonString = jsonString.replaceAll("\"ID\"", "id");
        jsonString = jsonString.replaceAll("\"CHECKED\"","checked");
        jsonString = jsonString.replaceAll("\"TEXT\"","text");
        
        response.getWriter().print(jsonString);
    }

    /**
     * 권한그룹 상세 조회
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param consultCompUsrGrpDetailForm
     */
    private void findDetail(HttpServletRequest request, ConsultCompUsrGrpDetailForm consultCompUsrGrpDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	ConsultCompUsrGrpDetailService consultCompUsrGrpDetailService = (ConsultCompUsrGrpDetailService)getBean("consultCompUsrGrpDetailService");

        // 넘겨진 권한그룹번호 구함
        String compNo = consultCompUsrGrpDetailForm.getConsultCompUsrGrpCommonDTO().getCompNo();
        String usrGrpId  = consultCompUsrGrpDetailForm.getConsultCompUsrGrpCommonDTO().getUsrGrpId();
        
        // 조회된 상세 부분
        ConsultCompUsrGrpDetailDTO consultCompUsrGrpDetailDTO = consultCompUsrGrpDetailService.findDetail(compNo, usrGrpId);
        
        // 조회된 상세  셋팅한다.
        consultCompUsrGrpDetailForm.setConsultCompUsrGrpDetailDTO(consultCompUsrGrpDetailDTO);
    }
    
    /**
     * detail insert
     * @author  
     * @version $Id:  $
     * @since   1.0
     * 
     * @param consultCompUsrGrpDetailForm
     * @param request
     */
    private void insertDetail(ConsultCompUsrGrpDetailForm consultCompUsrGrpDetailForm, HttpServletRequest request) throws Exception
    {
        ConsultCompUsrGrpDetailService consultCompUsrGrpDetailService = (ConsultCompUsrGrpDetailService) getBean("consultCompUsrGrpDetailService");
        
        ConsultCompUsrGrpDetailDTO consultCompUsrGrpDetailDTO = consultCompUsrGrpDetailForm.getConsultCompUsrGrpDetailDTO();
        
        consultCompUsrGrpDetailService.insertDetail(consultCompUsrGrpDetailDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail update
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param consultCompUsrGrpDetailForm
     * @param request
     */
    private void updateDetail(ConsultCompUsrGrpDetailForm consultCompUsrGrpDetailForm, HttpServletRequest request) throws Exception
    {
    	ConsultCompUsrGrpDetailService consultCompUsrGrpDetailService = (ConsultCompUsrGrpDetailService) getBean("consultCompUsrGrpDetailService");
        
        ConsultCompUsrGrpDetailDTO consultCompUsrGrpDetailDTO = consultCompUsrGrpDetailForm.getConsultCompUsrGrpDetailDTO();

        consultCompUsrGrpDetailService.updateDetail(consultCompUsrGrpDetailDTO);
        
        setAjaxStatus(request);
    }
    /**
     * valid user group
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param consultCompUsrGrpDetailForm
     * @param request
     */
    private void validEmpNo(ConsultCompUsrGrpDetailForm consultCompUsrGrpDetailForm, HttpServletRequest request) throws Exception
    {
    	ConsultCompUsrGrpDetailService consultCompUsrGrpDetailService = (ConsultCompUsrGrpDetailService) getBean("consultCompUsrGrpDetailService");
        
        ConsultCompUsrGrpDetailDTO consultCompUsrGrpDetailDTO = consultCompUsrGrpDetailForm.getConsultCompUsrGrpDetailDTO();
        consultCompUsrGrpDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        String isValid = consultCompUsrGrpDetailService.validUserGroup(consultCompUsrGrpDetailDTO);
        
        setAjaxDesc(request, isValid);
    }
    /**
     * selectbox obtain
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param consultCompUsrGrpDetailForm
     */
    public void findMenuList(HttpServletRequest request, ConsultCompUsrGrpDetailForm consultCompUsrGrpDetailForm)throws Exception 
    {
        // Service 객체 생성
        ConsultCompUsrGrpDetailService consultCompUsrGrpDetailService = (ConsultCompUsrGrpDetailService)getBean("consultCompUsrGrpDetailService");

        // 넘겨진 권한그룹번호 구함
        String compNo = consultCompUsrGrpDetailForm.getConsultCompUsrGrpCommonDTO().getCompNo();
        String usrGrpId  = consultCompUsrGrpDetailForm.getConsultCompUsrGrpCommonDTO().getUsrGrpId();
        
        // 조회된 상세 부분
        List consultCompUsrGrpMenuDTOList = consultCompUsrGrpDetailService.findMenuList(consultCompUsrGrpDetailForm.getConsultCompUsrGrpCommonDTO());
        
        // 조회된 상세  셋팅한다.
        consultCompUsrGrpDetailForm.setConsultCompUsrGrpMenuDTOList(consultCompUsrGrpMenuDTOList);;
    }
}