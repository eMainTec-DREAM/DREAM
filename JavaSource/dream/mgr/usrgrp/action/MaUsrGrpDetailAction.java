package dream.mgr.usrgrp.action;

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
import dream.mgr.usrgrp.dto.MaUsrGrpCommonDTO;
import dream.mgr.usrgrp.dto.MaUsrGrpDetailDTO;
import dream.mgr.usrgrp.form.MaUsrGrpDetailForm;
import dream.mgr.usrgrp.service.MaUsrGrpDetailService;

/**
 * ���ѱ׷� - �� action
 * 
 * @author 
 * @version $Id: $
 * @since 1.0
 * @struts:action path="/maUsrGrpDetail" name="maUsrGrpDetailForm"
 *                input="/dream/mgr/usrgrp/maUsrGrpDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maUsrGrpDetail" path="/dream/mgr/usrgrp/maUsrGrpDetail.jsp"
 *                        redirect="false"
 */
public class MaUsrGrpDetailAction extends AuthAction
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
        MaUsrGrpDetailForm maUsrGrpDetailForm = (MaUsrGrpDetailForm) form;
        
        super.updateAudit(maUsrGrpDetailForm.getMaUsrGrpDetailDTO().getAuditKey()==""?maUsrGrpDetailForm.getMaUsrGrpCommonDTO().getAuditKey():maUsrGrpDetailForm.getMaUsrGrpDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maUsrGrpDetailForm.getStrutsAction())
        {
            case MaUsrGrpDetailAction.USRGRP_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, maUsrGrpDetailForm);
                this.findMenuList(request, maUsrGrpDetailForm);
                returnActionForward = mapping.findForward("maUsrGrpDetail");
                break;
            case MaUsrGrpDetailAction.USRGRP_DETAIL_INPUT:
            	insertDetail(maUsrGrpDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaUsrGrpDetailAction.USRGRP_DETAIL_UPDATE:
            	updateDetail(maUsrGrpDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaUsrGrpDetailAction.USRGRP_DETAIL_CHECK:
            	validEmpNo(maUsrGrpDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaUsrGrpDetailAction.USRGRP_DETAIL_TREE:
                this.findMenuListForTree(request, maUsrGrpDetailForm, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                this.findMenuList(request, maUsrGrpDetailForm);
                returnActionForward = mapping.findForward("maUsrGrpDetail");
                break;
        }

        return returnActionForward;
    }
    
    private void findMenuListForTree(HttpServletRequest request, MaUsrGrpDetailForm maUsrGrpDetailForm, HttpServletResponse response) throws IOException
    {
        // Service ��ü ����
        MaUsrGrpDetailService maUsrGrpDetailService = (MaUsrGrpDetailService)getBean("maUsrGrpDetailService");

        // �Ѱ��� ���ѱ׷��ȣ ����
        String compNo = maUsrGrpDetailForm.getMaUsrGrpCommonDTO().getCompNo();
        String usrGrpId  = maUsrGrpDetailForm.getMaUsrGrpCommonDTO().getUsrGrpId();
        
        // ��ȸ�� �� �κ�
        Map maUsrGrpMenuDTOList = maUsrGrpDetailService.findMenuListForTree(maUsrGrpDetailForm.getMaUsrGrpCommonDTO(), getUser(request));

        Gson gson = new Gson();
        
        String jsonString = gson.toJson(maUsrGrpMenuDTOList);
        
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
     * @param maUsrGrpDetailForm
     */
    private void findDetail(HttpServletRequest request, MaUsrGrpDetailForm maUsrGrpDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaUsrGrpDetailService maUsrGrpDetailService = (MaUsrGrpDetailService)getBean("maUsrGrpDetailService");

        // �Ѱ��� ���ѱ׷��ȣ ����
    	MaUsrGrpCommonDTO maUsrGrpCommonDTO = maUsrGrpDetailForm.getMaUsrGrpCommonDTO();
        
        // ��ȸ�� �� �κ�
        MaUsrGrpDetailDTO maUsrGrpDetailDTO = maUsrGrpDetailService.findDetail(maUsrGrpCommonDTO, getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
        maUsrGrpDetailForm.setMaUsrGrpDetailDTO(maUsrGrpDetailDTO);
    }
    
    /**
     * detail insert
     * @author  
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maUsrGrpDetailForm
     * @param request
     */
    private void insertDetail(MaUsrGrpDetailForm maUsrGrpDetailForm, HttpServletRequest request) throws Exception
    {
        MaUsrGrpDetailService maUsrGrpDetailService = (MaUsrGrpDetailService) getBean("maUsrGrpDetailService");
        
        MaUsrGrpDetailDTO maUsrGrpDetailDTO = maUsrGrpDetailForm.getMaUsrGrpDetailDTO();
        String[] checkRows = maUsrGrpDetailForm.getCheckRows();    // tree ����
        
        maUsrGrpDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        maUsrGrpDetailService.insertDetail(maUsrGrpDetailDTO, checkRows);
        
        setAjaxStatus(request);
    }
    /**
     * detail update
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maUsrGrpDetailForm
     * @param request
     */
    private void updateDetail(MaUsrGrpDetailForm maUsrGrpDetailForm, HttpServletRequest request) throws Exception
    {
    	MaUsrGrpDetailService maUsrGrpDetailService = (MaUsrGrpDetailService) getBean("maUsrGrpDetailService");
        
        MaUsrGrpDetailDTO maUsrGrpDetailDTO = maUsrGrpDetailForm.getMaUsrGrpDetailDTO();
        String[] checkRows = maUsrGrpDetailForm.getCheckRows();    // tree ����
        
        maUsrGrpDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        maUsrGrpDetailService.updateDetail(maUsrGrpDetailDTO, checkRows);
        
        setAjaxStatus(request);
    }
    /**
     * valid user group
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maUsrGrpDetailForm
     * @param request
     */
    private void validEmpNo(MaUsrGrpDetailForm maUsrGrpDetailForm, HttpServletRequest request) throws Exception
    {
    	MaUsrGrpDetailService maUsrGrpDetailService = (MaUsrGrpDetailService) getBean("maUsrGrpDetailService");
        
        MaUsrGrpDetailDTO maUsrGrpDetailDTO = maUsrGrpDetailForm.getMaUsrGrpDetailDTO();
        maUsrGrpDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        String isValid = maUsrGrpDetailService.validUserGroup(maUsrGrpDetailDTO);
        
        setAjaxDesc(request, isValid);
    }
    /**
     * selectbox obtain
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param maUsrGrpDetailForm
     */
    public void findMenuList(HttpServletRequest request, MaUsrGrpDetailForm maUsrGrpDetailForm)throws Exception 
    {
        // Service ��ü ����
        MaUsrGrpDetailService maUsrGrpDetailService = (MaUsrGrpDetailService)getBean("maUsrGrpDetailService");

        // �Ѱ��� ���ѱ׷��ȣ ����
        String compNo = maUsrGrpDetailForm.getMaUsrGrpCommonDTO().getCompNo();
        String usrGrpId  = maUsrGrpDetailForm.getMaUsrGrpCommonDTO().getUsrGrpId();
        
        // ��ȸ�� �� �κ�
        List maUsrGrpMenuDTOList = maUsrGrpDetailService.findMenuList(maUsrGrpDetailForm.getMaUsrGrpCommonDTO());
        
        // ��ȸ�� ��  �����Ѵ�.
        maUsrGrpDetailForm.setMaUsrGrpMenuDTOList(maUsrGrpMenuDTOList);;
    }
}