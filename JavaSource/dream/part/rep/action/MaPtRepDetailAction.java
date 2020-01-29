package dream.part.rep.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.part.rep.dto.MaPtRepCommonDTO;
import dream.part.rep.dto.MaPtRepDetailDTO;
import dream.part.rep.form.MaPtRepDetailForm;
import dream.part.rep.service.MaPtRepDetailService;

/**
 * ��ǰ���� - �� action
 * 
 * @author ssong
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/maPtRepDetail" name="maPtRepDetailForm"
 *                input="/dream/part/rep/maPtRepDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPtRepDetail" path="/dream/part/rep/maPtRepDetail.jsp"
 *                        redirect="false"
 */
public class MaPtRepDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int PTREP_DETAIL_INIT           = 8001;
    /** ���� */
    public static final int PTREP_DETAIL_INPUT          = 5002;
    /** ���� */
    public static final int PTREP_DETAIL_UPDATE         = 6003;
    /** ���� ���� */
    public static final int PTREP_DETAIL_STATUS_UPDATE  = 6004;
    /** ���� ���� �� ���� ���  */
    public static final int PTREP_DETAIL_STATUS_CANCEL  = 6010;
    /** �ߺ�üũ */
    public static final int PTREP_DETAIL_CHECK          = 8020;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaPtRepDetailForm maPtRepDetailForm = (MaPtRepDetailForm) form;

        super.updateAudit(maPtRepDetailForm.getMaPtRepDetailDTO().getAuditKey()==""?maPtRepDetailForm.getMaPtRepCommonDTO().getAuditKey():maPtRepDetailForm.getMaPtRepDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
 
        switch (maPtRepDetailForm.getStrutsAction())
        {
            case MaPtRepDetailAction.PTREP_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, maPtRepDetailForm);
                returnActionForward = mapping.findForward("maPtRepDetail");
                break;
            case MaPtRepDetailAction.PTREP_DETAIL_INPUT:
            	insertDetail(maPtRepDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPtRepDetailAction.PTREP_DETAIL_UPDATE:
            	updateDetail(maPtRepDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPtRepDetailAction.PTREP_DETAIL_STATUS_UPDATE:
                updatePtRepairListStatus(maPtRepDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaPtRepDetailAction.PTREP_DETAIL_STATUS_CANCEL:
                updatePtRepairListStatusCancel(maPtRepDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaPtRepDetailAction.PTREP_DETAIL_CHECK:
                validPtRepairListNo(maPtRepDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            default:
                returnActionForward = mapping.findForward("maPtRepDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * ��ǰ���� �� ��ȸ
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maPtRepDetailForm
     */
    private void findDetail(HttpServletRequest request, MaPtRepDetailForm maPtRepDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaPtRepDetailService maPtRepDetailService = (MaPtRepDetailService)getBean("maPtRepDetailService");
    	
    	MaPtRepCommonDTO maPtRepCommonDTO = maPtRepDetailForm.getMaPtRepCommonDTO();
    	
        // ��ȸ�� �� �κ�
        MaPtRepDetailDTO maPtRepDetailDTO = maPtRepDetailService.findDetail(maPtRepCommonDTO, getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
        maPtRepDetailForm.setMaPtRepDetailDTO(maPtRepDetailDTO);
    }
    
    /**
     * detail insert
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRepDetailForm
     * @param request
     */
    private void insertDetail(MaPtRepDetailForm maPtRepDetailForm, HttpServletRequest request) throws Exception
    {
        MaPtRepDetailService maPtRepDetailService = (MaPtRepDetailService) getBean("maPtRepDetailService");
        
        MaPtRepDetailDTO maPtRepDetailDTO = maPtRepDetailForm.getMaPtRepDetailDTO();
        
        maPtRepDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maPtRepDetailService.insertDetail(maPtRepDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * detail update
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRepDetailForm
     * @param request
     */
    private void updateDetail(MaPtRepDetailForm maPtRepDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPtRepDetailService maPtRepDetailService = (MaPtRepDetailService) getBean("maPtRepDetailService");
        
        MaPtRepDetailDTO maPtRepDetailDTO = maPtRepDetailForm.getMaPtRepDetailDTO();
        
        maPtRepDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maPtRepDetailService.updateDetail(maPtRepDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * detail ���� update
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRepDetailForm
     * @param request
     */
    private void updatePtRepairListStatus(MaPtRepDetailForm maPtRepDetailForm, HttpServletRequest request) throws Exception
    {
        MaPtRepDetailService maPtRepDetailService = (MaPtRepDetailService) getBean("maPtRepDetailService");
        
        MaPtRepDetailDTO maPtRepDetailDTO = maPtRepDetailForm.getMaPtRepDetailDTO();
        
        maPtRepDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maPtRepDetailService.updatePtRepairListStatus(maPtRepDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * detail ���� update �� ���� ���(�԰��̷� ���)
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRepDetailForm
     * @param request
     */
    private void updatePtRepairListStatusCancel(MaPtRepDetailForm maPtRepDetailForm, HttpServletRequest request) throws Exception
    {
        MaPtRepDetailService maPtRepDetailService = (MaPtRepDetailService) getBean("maPtRepDetailService");
        
        MaPtRepDetailDTO maPtRepDetailDTO = maPtRepDetailForm.getMaPtRepDetailDTO();
        
        maPtRepDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maPtRepDetailService.updatePtRepairListStatusCancel(maPtRepDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * valid No
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPtRepDetailForm
     * @param request
     */
    private void validPtRepairListNo(MaPtRepDetailForm maPtRepDetailForm, HttpServletRequest request) throws Exception
    {
        MaPtRepDetailService maPtRepDetailService = (MaPtRepDetailService) getBean("maPtRepDetailService");
        
        MaPtRepDetailDTO maPtRepDetailDTO = maPtRepDetailForm.getMaPtRepDetailDTO();
        
        maPtRepDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        String isValid = maPtRepDetailService.validPtRepairListNo(maPtRepDetailDTO, getUser(request));
        
        setAjaxDesc(request, isValid);
    }
}