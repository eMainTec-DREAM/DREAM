package dream.work.pm.std.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.pm.std.dto.MaStdPointCommonDTO;
import dream.work.pm.std.dto.MaStdWorkDetailDTO;
import dream.work.pm.std.dto.MaStdWorkListDTO;
import dream.work.pm.std.form.MaStdWorkDetailForm;
import dream.work.pm.std.service.MaStdWorkDetailService;

/**
 * ǥ���׸� ����Ʈ - �� action
 * 
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 * @struts:action path="/maStdWorkDetail" name="maStdWorkDetailForm"
 *                input="/dream/work/pm/std/maStdWorkDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maStdWorkDetail" path="/dream/work/pm/std/maStdWorkDetail.jsp"
 *                        redirect="false"
 */
public class MaStdWorkDetailAction extends AuthAction
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
        MaStdWorkDetailForm maStdWorkDetailForm = (MaStdWorkDetailForm) form;
        
        switch (maStdWorkDetailForm.getStrutsAction())
        {
            case MaStdWorkDetailAction.STD_DETAIL_INIT:
                this.findDetail(request, maStdWorkDetailForm);
                returnActionForward = mapping.findForward("maStdWorkDetail");
                break;
            case MaStdWorkDetailAction.STD_DETAIL_INPUT:
            	insertDetail(maStdWorkDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaStdWorkDetailAction.STD_DETAIL_UPDATE:
            	updateDetail(maStdWorkDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maStdWorkDetail");
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
     * @param maStdWorkDetailForm
     */
    private void findDetail(HttpServletRequest request, MaStdWorkDetailForm maStdWorkDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaStdWorkDetailService maStdWorkDetailService = (MaStdWorkDetailService)getBean("maStdWorkDetailService");

        // �Ѱ��� ǥ���׸��ȣ ����
    	MaStdPointCommonDTO maStdPointCommonDTO = maStdWorkDetailForm.getMaStdPointCommonDTO();
    	MaStdWorkListDTO maStdWorkListDTO = maStdWorkDetailForm.getMaStdWorkListDTO();
        
        // ��ȸ�� �� �κ�
        MaStdWorkDetailDTO maStdWorkDetailDTO = maStdWorkDetailService.findDetail(maStdPointCommonDTO, maStdWorkListDTO, getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
        maStdWorkDetailForm.setMaStdWorkDetailDTO(maStdWorkDetailDTO);
    }
    
    /**
     * detail insert
     * @author  
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maStdWorkDetailForm
     * @param request
     */
    private void insertDetail(MaStdWorkDetailForm maStdWorkDetailForm, HttpServletRequest request) throws Exception
    {
        MaStdWorkDetailService maStdWorkDetailService = (MaStdWorkDetailService) getBean("maStdWorkDetailService");
        
        MaStdWorkDetailDTO maStdWorkDetailDTO = maStdWorkDetailForm.getMaStdWorkDetailDTO();
    	MaStdPointCommonDTO maStdPointCommonDTO = maStdWorkDetailForm.getMaStdPointCommonDTO();
        
        maStdWorkDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        maStdWorkDetailService.insertDetail(maStdWorkDetailDTO,maStdPointCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * detail update
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maStdWorkDetailForm
     * @param request
     */
    private void updateDetail(MaStdWorkDetailForm maStdWorkDetailForm, HttpServletRequest request) throws Exception
    {
    	MaStdWorkDetailService maStdWorkDetailService = (MaStdWorkDetailService) getBean("maStdWorkDetailService");
        
        MaStdWorkDetailDTO maStdWorkDetailDTO = maStdWorkDetailForm.getMaStdWorkDetailDTO();
    	MaStdPointCommonDTO maStdPointCommonDTO = maStdWorkDetailForm.getMaStdPointCommonDTO();
        
        maStdWorkDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        maStdWorkDetailService.updateDetail(maStdWorkDetailDTO,maStdPointCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }

}