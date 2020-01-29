package dream.work.pm.std.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.pm.std.dto.MaStdPointCommonDTO;
import dream.work.pm.std.dto.MaStdPartDetailDTO;
import dream.work.pm.std.dto.MaStdPartListDTO;
import dream.work.pm.std.form.MaStdPartDetailForm;
import dream.work.pm.std.service.MaStdPartDetailService;

/**
 * 표준항목 리스트 - 상세 action
 * 
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 * @struts:action path="/maStdPartDetail" name="maStdPartDetailForm"
 *                input="/dream/work/pm/std/maStdPartDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maStdPartDetail" path="/dream/work/pm/std/maStdPartDetail.jsp"
 *                        redirect="false"
 */
public class MaStdPartDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int STD_DETAIL_INIT 		= 1001;
    /** 저장 */
    public static final int STD_DETAIL_INPUT 		= 1002;
    /** 수정 */
    public static final int STD_DETAIL_UPDATE 		= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaStdPartDetailForm maStdPartDetailForm = (MaStdPartDetailForm) form;
        
        switch (maStdPartDetailForm.getStrutsAction())
        {
            case MaStdPartDetailAction.STD_DETAIL_INIT:
                this.findDetail(request, maStdPartDetailForm);
                returnActionForward = mapping.findForward("maStdPartDetail");
                break;
            case MaStdPartDetailAction.STD_DETAIL_INPUT:
            	insertDetail(maStdPartDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaStdPartDetailAction.STD_DETAIL_UPDATE:
            	updateDetail(maStdPartDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maStdPartDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 표준항목 리스트 상세 조회
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param maStdPartDetailForm
     */
    private void findDetail(HttpServletRequest request, MaStdPartDetailForm maStdPartDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaStdPartDetailService maStdPartDetailService = (MaStdPartDetailService)getBean("maStdPartDetailService");

        // 넘겨진 표준항목번호 구함
    	MaStdPointCommonDTO maStdPointCommonDTO = maStdPartDetailForm.getMaStdPointCommonDTO();
    	MaStdPartListDTO maStdPartListDTO = maStdPartDetailForm.getMaStdPartListDTO();
        
        // 조회된 상세 부분
        MaStdPartDetailDTO maStdPartDetailDTO = maStdPartDetailService.findDetail(maStdPointCommonDTO, maStdPartListDTO, getUser(request));
        
        // 조회된 상세  셋팅한다.
        maStdPartDetailForm.setMaStdPartDetailDTO(maStdPartDetailDTO);
    }
    
    /**
     * detail insert
     * @author  
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maStdPartDetailForm
     * @param request
     */
    private void insertDetail(MaStdPartDetailForm maStdPartDetailForm, HttpServletRequest request) throws Exception
    {
        MaStdPartDetailService maStdPartDetailService = (MaStdPartDetailService) getBean("maStdPartDetailService");
        
        MaStdPartDetailDTO maStdPartDetailDTO = maStdPartDetailForm.getMaStdPartDetailDTO();
    	MaStdPointCommonDTO maStdPointCommonDTO = maStdPartDetailForm.getMaStdPointCommonDTO();
        
        maStdPartDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        maStdPartDetailService.insertDetail(maStdPartDetailDTO,maStdPointCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * detail update
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maStdPartDetailForm
     * @param request
     */
    private void updateDetail(MaStdPartDetailForm maStdPartDetailForm, HttpServletRequest request) throws Exception
    {
    	MaStdPartDetailService maStdPartDetailService = (MaStdPartDetailService) getBean("maStdPartDetailService");
        
        MaStdPartDetailDTO maStdPartDetailDTO = maStdPartDetailForm.getMaStdPartDetailDTO();
    	MaStdPointCommonDTO maStdPointCommonDTO = maStdPartDetailForm.getMaStdPointCommonDTO();
        
        maStdPartDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        maStdPartDetailService.updateDetail(maStdPartDetailDTO,maStdPointCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }

}