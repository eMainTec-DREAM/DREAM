package dream.work.pm.std.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.pm.std.dto.MaStdPointCommonDTO;
import dream.work.pm.std.dto.MaStdWoTypeDetailDTO;
import dream.work.pm.std.dto.MaStdWoTypeListDTO;
import dream.work.pm.std.form.MaStdWoTypeDetailForm;
import dream.work.pm.std.service.MaStdWoTypeDetailService;

/**
 * ǥ���׸� ����Ʈ - �� action
 * 
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 * @struts:action path="/maStdWoTypeDetail" name="maStdWoTypeDetailForm"
 *                input="/dream/work/pm/std/maStdWoTypeDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maStdWoTypeDetail" path="/dream/work/pm/std/maStdWoTypeDetail.jsp"
 *                        redirect="false"
 */
public class MaStdWoTypeDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int STD_DETAIL_INIT 		= 1001;
    /** ���� */
    public static final int STD_DETAIL_INPUT 		= 1002;
    /** ���� */
    public static final int STD_DETAIL_UPDATE 		= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaStdWoTypeDetailForm maStdWoTypeDetailForm = (MaStdWoTypeDetailForm) form;
        
        switch (maStdWoTypeDetailForm.getStrutsAction())
        {
            case MaStdWoTypeDetailAction.STD_DETAIL_INIT:
                this.findDetail(request, maStdWoTypeDetailForm);
                returnActionForward = mapping.findForward("maStdWoTypeDetail");
                break;
            case MaStdWoTypeDetailAction.STD_DETAIL_INPUT:
            	insertDetail(maStdWoTypeDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaStdWoTypeDetailAction.STD_DETAIL_UPDATE:
            	updateDetail(maStdWoTypeDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maStdWoTypeDetail");
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
     * @param maStdWoTypeDetailForm
     */
    private void findDetail(HttpServletRequest request, MaStdWoTypeDetailForm maStdWoTypeDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaStdWoTypeDetailService maStdWoTypeDetailService = (MaStdWoTypeDetailService)getBean("maStdWoTypeDetailService");

        // �Ѱ��� ǥ���׸��ȣ ����
    	MaStdPointCommonDTO maStdPointCommonDTO = maStdWoTypeDetailForm.getMaStdPointCommonDTO();
    	MaStdWoTypeListDTO maStdWoTypeListDTO = maStdWoTypeDetailForm.getMaStdWoTypeListDTO();
        
        // ��ȸ�� �� �κ�
        MaStdWoTypeDetailDTO maStdWoTypeDetailDTO = maStdWoTypeDetailService.findDetail(maStdPointCommonDTO, maStdWoTypeListDTO, getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
        maStdWoTypeDetailForm.setMaStdWoTypeDetailDTO(maStdWoTypeDetailDTO);
    }
    
    /**
     * detail insert
     * @author  
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maStdWoTypeDetailForm
     * @param request
     */
    private void insertDetail(MaStdWoTypeDetailForm maStdWoTypeDetailForm, HttpServletRequest request) throws Exception
    {
        MaStdWoTypeDetailService maStdWoTypeDetailService = (MaStdWoTypeDetailService) getBean("maStdWoTypeDetailService");
        
        MaStdWoTypeDetailDTO maStdWoTypeDetailDTO = maStdWoTypeDetailForm.getMaStdWoTypeDetailDTO();
    	MaStdPointCommonDTO maStdPointCommonDTO = maStdWoTypeDetailForm.getMaStdPointCommonDTO();
        
        maStdWoTypeDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        maStdWoTypeDetailService.insertDetail(maStdWoTypeDetailDTO,maStdPointCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * detail update
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maStdWoTypeDetailForm
     * @param request
     */
    private void updateDetail(MaStdWoTypeDetailForm maStdWoTypeDetailForm, HttpServletRequest request) throws Exception
    {
    	MaStdWoTypeDetailService maStdWoTypeDetailService = (MaStdWoTypeDetailService) getBean("maStdWoTypeDetailService");
        
        MaStdWoTypeDetailDTO maStdWoTypeDetailDTO = maStdWoTypeDetailForm.getMaStdWoTypeDetailDTO();
    	MaStdPointCommonDTO maStdPointCommonDTO = maStdWoTypeDetailForm.getMaStdPointCommonDTO();
        
        maStdWoTypeDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        maStdWoTypeDetailService.updateDetail(maStdWoTypeDetailDTO,maStdPointCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }

}