package dream.work.pm.std.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.MwareConfig;
import common.struts.AuthAction;
import dream.work.pm.std.dto.MaStdPointCommonDTO;
import dream.work.pm.std.dto.MaStdPointHdrDetailDTO;
import dream.work.pm.std.form.MaStdPointHdrDetailForm;
import dream.work.pm.std.service.MaStdPointHdrDetailService;

/**
 * 표준항목 - 상세 action
 * 
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 * @struts:action path="/maStdPointHdrDetail" name="maStdPointHdrDetailForm"
 *                input="/dream/work/pm/std/maStdPointHdrDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maStdPointHdrDetail" path="/dream/work/pm/std/maStdPointHdrDetail.jsp"
 *                        redirect="false"
 */
public class MaStdPointHdrDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int STD_HDR_DETAIL_INIT			= 8001;
    /** 저장 */
    public static final int STD_HDR_DETAIL_INPUT		= 5002;
    /** 수정 */
    public static final int STD_HDR_DETAIL_UPDATE		= 6003;
    /** 적용 */
    public static final int STD_HDR_DETAIL_CONFIRM		= 6004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaStdPointHdrDetailForm maStdPointHdrDetailForm = (MaStdPointHdrDetailForm) form;
        
        super.updateAudit(maStdPointHdrDetailForm.getMaStdPointHdrDetailDTO().getAuditKey()==""?maStdPointHdrDetailForm.getMaStdPointCommonDTO().getAuditKey():maStdPointHdrDetailForm.getMaStdPointHdrDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maStdPointHdrDetailForm.getStrutsAction())
        {
            case MaStdPointHdrDetailAction.STD_HDR_DETAIL_INIT:
                this.findDetail(request, maStdPointHdrDetailForm);
                returnActionForward = mapping.findForward("maStdPointHdrDetail");
                break;
            case MaStdPointHdrDetailAction.STD_HDR_DETAIL_INPUT:
            	insertDetail(maStdPointHdrDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaStdPointHdrDetailAction.STD_HDR_DETAIL_UPDATE:
            	updateDetail(maStdPointHdrDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaStdPointHdrDetailAction.STD_HDR_DETAIL_CONFIRM:
            	confirmDetail(maStdPointHdrDetailForm, request);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maStdPointHdrDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 표준항목 상세 조회
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param maStdPointHdrDetailForm
     */
    private void findDetail(HttpServletRequest request, MaStdPointHdrDetailForm maStdPointHdrDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaStdPointHdrDetailService maStdPointHdrDetailService = (MaStdPointHdrDetailService)getBean("maStdPointHdrDetailService");

        // 넘겨진 표준항목번호 구함
    	MaStdPointCommonDTO maStdPointCommonDTO = maStdPointHdrDetailForm.getMaStdPointCommonDTO();
        
        // 조회된 상세 부분
        MaStdPointHdrDetailDTO maStdPointHdrDetailDTO = maStdPointHdrDetailService.findDetail(maStdPointCommonDTO, getUser(request));
        
        // 조회된 상세  셋팅한다.
        maStdPointHdrDetailForm.setMaStdPointHdrDetailDTO(maStdPointHdrDetailDTO);
    }
    
    /**
     * detail insert
     * @author  
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maStdPointHdrDetailForm
     * @param request
     */
    private void insertDetail(MaStdPointHdrDetailForm maStdPointHdrDetailForm, HttpServletRequest request) throws Exception
    {
        MaStdPointHdrDetailService maStdPointHdrDetailService = (MaStdPointHdrDetailService) getBean("maStdPointHdrDetailService");
        
        MaStdPointHdrDetailDTO maStdPointHdrDetailDTO = maStdPointHdrDetailForm.getMaStdPointHdrDetailDTO();
        
        maStdPointHdrDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        maStdPointHdrDetailService.insertDetail(maStdPointHdrDetailDTO, getUser(request));
        if("N".equals(MwareConfig.getIsUsePmRevision())){
        	maStdPointHdrDetailService.insertRevisionHistAndUpdateMstr(maStdPointHdrDetailDTO, getUser(request));
        }
        setAjaxStatus(request);
    }
    
    /**
     * detail update
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maStdPointHdrDetailForm
     * @param request
     */
    private void updateDetail(MaStdPointHdrDetailForm maStdPointHdrDetailForm, HttpServletRequest request) throws Exception
    {
    	MaStdPointHdrDetailService maStdPointHdrDetailService = (MaStdPointHdrDetailService) getBean("maStdPointHdrDetailService");
        
        MaStdPointHdrDetailDTO maStdPointHdrDetailDTO = maStdPointHdrDetailForm.getMaStdPointHdrDetailDTO();
        
        maStdPointHdrDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        
        maStdPointHdrDetailService.updateDetail(maStdPointHdrDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * detail confirm
     * @author kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param maStdPointHdrDetailForm
     * @param request
     * @throws Exception 
     */
    private void confirmDetail(MaStdPointHdrDetailForm maStdPointHdrDetailForm, HttpServletRequest request) throws Exception{
    	MaStdPointHdrDetailService maStdPointHdrDetailService = (MaStdPointHdrDetailService) getBean("maStdPointHdrDetailService");
        MaStdPointHdrDetailDTO maStdPointHdrDetailDTO = maStdPointHdrDetailForm.getMaStdPointHdrDetailDTO();
        
        maStdPointHdrDetailDTO.setCompNo((getUser(request).getCompNo()));
        maStdPointHdrDetailService.confirmDetail(maStdPointHdrDetailDTO);
        
        setAjaxStatus(request);
    }
}