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
 * ���ѱ׷� - �� action
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
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int USRGRP_DETAIL_INIT 		= 8001;
    /** ���� */
    public static final int USRGRP_DETAIL_INPUT 	= 5002;
    /** ���� */
    public static final int USRGRP_DETAIL_UPDATE 	= 6003;
    /** �ߺ� üũ */
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
                // ������ ��ȸ
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
        // Service ��ü ����
        ConsultCompUsrGrpDetailService consultCompUsrGrpDetailService = (ConsultCompUsrGrpDetailService)getBean("consultCompUsrGrpDetailService");

        // �Ѱ��� ���ѱ׷��ȣ ����
        String compNo = consultCompUsrGrpDetailForm.getConsultCompUsrGrpCommonDTO().getCompNo();
        String usrGrpId  = consultCompUsrGrpDetailForm.getConsultCompUsrGrpCommonDTO().getUsrGrpId();
        
        // ��ȸ�� �� �κ�
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
     * ���ѱ׷� �� ��ȸ
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param consultCompUsrGrpDetailForm
     */
    private void findDetail(HttpServletRequest request, ConsultCompUsrGrpDetailForm consultCompUsrGrpDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	ConsultCompUsrGrpDetailService consultCompUsrGrpDetailService = (ConsultCompUsrGrpDetailService)getBean("consultCompUsrGrpDetailService");

        // �Ѱ��� ���ѱ׷��ȣ ����
        String compNo = consultCompUsrGrpDetailForm.getConsultCompUsrGrpCommonDTO().getCompNo();
        String usrGrpId  = consultCompUsrGrpDetailForm.getConsultCompUsrGrpCommonDTO().getUsrGrpId();
        
        // ��ȸ�� �� �κ�
        ConsultCompUsrGrpDetailDTO consultCompUsrGrpDetailDTO = consultCompUsrGrpDetailService.findDetail(compNo, usrGrpId);
        
        // ��ȸ�� ��  �����Ѵ�.
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
        // Service ��ü ����
        ConsultCompUsrGrpDetailService consultCompUsrGrpDetailService = (ConsultCompUsrGrpDetailService)getBean("consultCompUsrGrpDetailService");

        // �Ѱ��� ���ѱ׷��ȣ ����
        String compNo = consultCompUsrGrpDetailForm.getConsultCompUsrGrpCommonDTO().getCompNo();
        String usrGrpId  = consultCompUsrGrpDetailForm.getConsultCompUsrGrpCommonDTO().getUsrGrpId();
        
        // ��ȸ�� �� �κ�
        List consultCompUsrGrpMenuDTOList = consultCompUsrGrpDetailService.findMenuList(consultCompUsrGrpDetailForm.getConsultCompUsrGrpCommonDTO());
        
        // ��ȸ�� ��  �����Ѵ�.
        consultCompUsrGrpDetailForm.setConsultCompUsrGrpMenuDTOList(consultCompUsrGrpMenuDTOList);;
    }
}