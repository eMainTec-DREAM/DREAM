package dream.work.pm.std.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.pm.std.dto.MaStdPointCommonDTO;
import dream.work.pm.std.dto.MaStdPointDetailDTO;
import dream.work.pm.std.dto.MaStdPointListDTO;
import dream.work.pm.std.form.MaStdPointDetailForm;
import dream.work.pm.std.service.MaStdPointDetailService;

/**
 * ǥ���׸� ����Ʈ - �� action
 * 
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 * @struts:action path="/maStdPointDetail" name="maStdPointDetailForm"
 *                input="/dream/work/pm/std/maStdPointDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maStdPointDetail" path="/dream/work/pm/std/maStdPointDetail.jsp"
 *                        redirect="false"
 */
public class MaStdPointDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int STD_DETAIL_INIT 		= 8001;
    /** ���� */
    public static final int STD_DETAIL_INPUT 		= 5002;
    /** ���� */
    public static final int STD_DETAIL_UPDATE 		= 6003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaStdPointDetailForm maStdPointDetailForm = (MaStdPointDetailForm) form;
      
        super.updateAudit(maStdPointDetailForm.getMaStdPointDetailDTO().getAuditKey()==""?maStdPointDetailForm.getMaStdPointCommonDTO().getAuditKey():maStdPointDetailForm.getMaStdPointDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maStdPointDetailForm.getStrutsAction())
        {
            case MaStdPointDetailAction.STD_DETAIL_INIT:
                this.findDetail(request, maStdPointDetailForm);
                returnActionForward = mapping.findForward("maStdPointDetail");
                break;
            case MaStdPointDetailAction.STD_DETAIL_INPUT:
            	insertDetail(maStdPointDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaStdPointDetailAction.STD_DETAIL_UPDATE:
            	updateDetail(maStdPointDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maStdPointDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * ǥ���׸� ����Ʈ �� ��ȸ
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param maStdPointDetailForm
     */
    private void findDetail(HttpServletRequest request, MaStdPointDetailForm maStdPointDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaStdPointDetailService maStdPointDetailService = (MaStdPointDetailService)getBean("maStdPointDetailService");

        // �Ѱ��� ǥ���׸��ȣ ����
    	MaStdPointCommonDTO maStdPointCommonDTO = maStdPointDetailForm.getMaStdPointCommonDTO();
    	MaStdPointListDTO maStdPointListDTO = maStdPointDetailForm.getMaStdPointListDTO();
        
        // ��ȸ�� �� �κ�
        MaStdPointDetailDTO maStdPointDetailDTO = maStdPointDetailService.findDetail(maStdPointCommonDTO, maStdPointListDTO, getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
        maStdPointDetailForm.setMaStdPointDetailDTO(maStdPointDetailDTO);
    }
    
    /**
     * detail insert
     * @author  
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maStdPointDetailForm
     * @param request
     */
    private void insertDetail(MaStdPointDetailForm maStdPointDetailForm, HttpServletRequest request) throws Exception
    {
        MaStdPointDetailService maStdPointDetailService = (MaStdPointDetailService) getBean("maStdPointDetailService");
        
        MaStdPointDetailDTO maStdPointDetailDTO = maStdPointDetailForm.getMaStdPointDetailDTO();
    	MaStdPointCommonDTO maStdPointCommonDTO = maStdPointDetailForm.getMaStdPointCommonDTO();
        
        maStdPointDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        maStdPointDetailService.insertDetail(maStdPointDetailDTO,maStdPointCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * detail update
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maStdPointDetailForm
     * @param request
     */
    private void updateDetail(MaStdPointDetailForm maStdPointDetailForm, HttpServletRequest request) throws Exception
    {
    	MaStdPointDetailService maStdPointDetailService = (MaStdPointDetailService) getBean("maStdPointDetailService");
        
        MaStdPointDetailDTO maStdPointDetailDTO = maStdPointDetailForm.getMaStdPointDetailDTO();
    	MaStdPointCommonDTO maStdPointCommonDTO = maStdPointDetailForm.getMaStdPointCommonDTO();
        
        maStdPointDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        maStdPointDetailService.updateDetail(maStdPointDetailDTO,maStdPointCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }

}