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
 * 표준항목 리스트 - 상세 action
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
     * 표준항목 리스트 상세 조회
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param maStdWorkDetailForm
     */
    private void findDetail(HttpServletRequest request, MaStdWorkDetailForm maStdWorkDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaStdWorkDetailService maStdWorkDetailService = (MaStdWorkDetailService)getBean("maStdWorkDetailService");

        // 넘겨진 표준항목번호 구함
    	MaStdPointCommonDTO maStdPointCommonDTO = maStdWorkDetailForm.getMaStdPointCommonDTO();
    	MaStdWorkListDTO maStdWorkListDTO = maStdWorkDetailForm.getMaStdWorkListDTO();
        
        // 조회된 상세 부분
        MaStdWorkDetailDTO maStdWorkDetailDTO = maStdWorkDetailService.findDetail(maStdPointCommonDTO, maStdWorkListDTO, getUser(request));
        
        // 조회된 상세  셋팅한다.
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