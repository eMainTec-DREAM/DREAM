package dream.org.wrkgrp.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.org.wrkgrp.dto.MaWkCtrDetailDTO;
import dream.org.wrkgrp.form.MaWkCtrDetailForm;
import dream.org.wrkgrp.service.MaWkCtrDetailService;

/**
 * �۾��׷� - �� action
 * 
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 * @struts:action path="/maWkCtrDetail" name="maWkCtrDetailForm"
 *                input="/dream/org/wrkgrp/maWkCtrDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maWkCtrDetail" path="/dream/org/wrkgrp/maWkCtrDetail.jsp"
 *                        redirect="false"
 */
public class MaWkCtrDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int WKCTR_DETAIL_INIT 		= 8001;
    /** ���� */
    public static final int WKCTR_DETAIL_INPUT 		= 5002;
    /** ���� */
    public static final int WKCTR_DETAIL_UPDATE 	= 6003;
    /** �ߺ� üũ */
    public static final int WKCTR_DETAIL_CHECK 		= 1004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaWkCtrDetailForm maWkCtrDetailForm = (MaWkCtrDetailForm) form;
        
        super.updateAudit(maWkCtrDetailForm.getMaWkCtrDetailDTO().getAuditKey()==""?maWkCtrDetailForm.getMaWkCtrCommonDTO().getAuditKey():maWkCtrDetailForm.getMaWkCtrDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maWkCtrDetailForm.getStrutsAction())
        {
            case MaWkCtrDetailAction.WKCTR_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, maWkCtrDetailForm);
                returnActionForward = mapping.findForward("maWkCtrDetail");
                break;
            case MaWkCtrDetailAction.WKCTR_DETAIL_INPUT:
            	insertDetail(maWkCtrDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaWkCtrDetailAction.WKCTR_DETAIL_UPDATE:
            	updateDetail(maWkCtrDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaWkCtrDetailAction.WKCTR_DETAIL_CHECK:
            	validWkCtrNo(maWkCtrDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maWkCtrDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * �۾��׷� �� ��ȸ
     * @author kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param maWkCtrDetailForm
     */
    private void findDetail(HttpServletRequest request, MaWkCtrDetailForm maWkCtrDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaWkCtrDetailService maWkCtrDetailService = (MaWkCtrDetailService)getBean("maWkCtrDetailService");

        // �Ѱ��� �۾��׷�No ����
        String compNo  = getUser(request).getCompNo();
        String wkCtrId = maWkCtrDetailForm.getMaWkCtrCommonDTO().getWkCtrId();
        
        // ��ȸ�� �� �κ�
        MaWkCtrDetailDTO maWkCtrDetailDTO = maWkCtrDetailService.findDetail(compNo, wkCtrId);
        
        // ��ȸ�� ��  �����Ѵ�.
        maWkCtrDetailForm.setMaWkCtrDetailDTO(maWkCtrDetailDTO);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maWkCtrDetailForm
     * @param request
     */
    private void insertDetail(MaWkCtrDetailForm maWkCtrDetailForm, HttpServletRequest request) throws Exception
    {
        MaWkCtrDetailService maWkCtrDetailService = (MaWkCtrDetailService) getBean("maWkCtrDetailService");
        
        MaWkCtrDetailDTO maWkCtrDetailDTO = maWkCtrDetailForm.getMaWkCtrDetailDTO();
        
        maWkCtrDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        maWkCtrDetailService.insertDetail(maWkCtrDetailDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param maWkCtrDetailForm
     * @param request
     */
    private void updateDetail(MaWkCtrDetailForm maWkCtrDetailForm, HttpServletRequest request) throws Exception
    {
    	MaWkCtrDetailService maWkCtrDetailService = (MaWkCtrDetailService) getBean("maWkCtrDetailService");
        
        MaWkCtrDetailDTO maWkCtrDetailDTO = maWkCtrDetailForm.getMaWkCtrDetailDTO();
        
        maWkCtrDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        maWkCtrDetailService.updateDetail(maWkCtrDetailDTO);
        
        setAjaxStatus(request);
    }
    /**
     * valid wkctr no
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maWkCtrDetailForm
     * @param request
     */
    private void validWkCtrNo(MaWkCtrDetailForm maWkCtrDetailForm, HttpServletRequest request) throws Exception
    {
    	MaWkCtrDetailService maWkCtrDetailService = (MaWkCtrDetailService) getBean("maWkCtrDetailService");
        
        MaWkCtrDetailDTO maWkCtrDetailDTO = maWkCtrDetailForm.getMaWkCtrDetailDTO();
        
        maWkCtrDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        String isValid = maWkCtrDetailService.validWkCtrNo(maWkCtrDetailDTO);
        
        setAjaxDesc(request, isValid);
    }
    
}