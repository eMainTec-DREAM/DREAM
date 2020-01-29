package dream.part.pur.req.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.part.pur.req.dto.MaPtPurReqDetailDTO;
import dream.part.pur.req.dto.MaPtReqCommonDTO;
import dream.part.pur.req.form.MaPtPurReqDetailForm;
import dream.part.pur.req.service.MaPtPurReqDetailService;

/**
 * ��ǰ���� - �� action
 * 
 * @author ssong
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/maPtPurReqDetail" name="maPtPurReqDetailForm"
 *                input="/dream/part/pur/req/maPtPurReqDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPtPurReqDetail" path="/dream/part/pur/req/maPtPurReqDetail.jsp"
 *                        redirect="false"
 */
public class MaPtPurReqDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int PTREQ_DETAIL_INIT           = 8001;
    /** ���� */
    public static final int PTREQ_DETAIL_INPUT          = 5002;
    /** ���� */
    public static final int PTREQ_DETAIL_UPDATE         = 6003;
    /** Ȯ�� */
    public static final int PTREQ_DETAIL_STATUS_UPDATE  = 6004;
    /** �����Ϸ�  */
    public static final int PTREQ_DETAIL_STATUS_REC     = 6005;
    /** ��û ��� */
    public static final int PTREQ_REQ_CANCEL    		= 6006;
    
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaPtPurReqDetailForm maPtPurReqDetailForm = (MaPtPurReqDetailForm) form;
        
        super.updateAudit(maPtPurReqDetailForm.getMaPtPurReqDetailDTO().getAuditKey()==""?maPtPurReqDetailForm.getMaPtReqCommonDTO().getAuditKey():maPtPurReqDetailForm.getMaPtPurReqDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maPtPurReqDetailForm.getStrutsAction())
        {
            case MaPtPurReqDetailAction.PTREQ_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, maPtPurReqDetailForm);
                returnActionForward = mapping.findForward("maPtPurReqDetail");
                break;
            case MaPtPurReqDetailAction.PTREQ_DETAIL_INPUT:
            	insertDetail(maPtPurReqDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPtPurReqDetailAction.PTREQ_DETAIL_UPDATE:
            	updateDetail(maPtPurReqDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPtPurReqDetailAction.PTREQ_DETAIL_STATUS_UPDATE:
                updateStatus(maPtPurReqDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaPtPurReqDetailAction.PTREQ_DETAIL_STATUS_REC:
            	recStatus(maPtPurReqDetailForm, request);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPtPurReqDetailAction.PTREQ_REQ_CANCEL:
            	recCancel(maPtPurReqDetailForm, request);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;            	
            default:
                returnActionForward = mapping.findForward("maPtPurReqDetail");
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
     * @param maPtPurReqDetailForm
     */
    private void findDetail(HttpServletRequest request, MaPtPurReqDetailForm maPtPurReqDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaPtPurReqDetailService maPtPurReqDetailService = (MaPtPurReqDetailService)getBean("maPtPurReqDetailService");
    	
    	MaPtReqCommonDTO maPtReqCommonDTO = maPtPurReqDetailForm.getMaPtReqCommonDTO();
    	
        // ��ȸ�� �� �κ�
        MaPtPurReqDetailDTO maPtPurReqDetailDTO = maPtPurReqDetailService.findDetail(maPtReqCommonDTO, getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
        maPtPurReqDetailForm.setMaPtPurReqDetailDTO(maPtPurReqDetailDTO);
    }
    
    /**
     * detail insert
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtPurReqDetailForm
     * @param request
     */
    private void insertDetail(MaPtPurReqDetailForm maPtPurReqDetailForm, HttpServletRequest request) throws Exception
    {
        MaPtPurReqDetailService maPtPurReqDetailService = (MaPtPurReqDetailService) getBean("maPtPurReqDetailService");
        
        MaPtPurReqDetailDTO maPtPurReqDetailDTO = maPtPurReqDetailForm.getMaPtPurReqDetailDTO();
        
        maPtPurReqDetailService.insertDetail(maPtPurReqDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * detail update
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtPurReqDetailForm
     * @param request
     */
    private void updateDetail(MaPtPurReqDetailForm maPtPurReqDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPtPurReqDetailService maPtPurReqDetailService = (MaPtPurReqDetailService) getBean("maPtPurReqDetailService");
        
        MaPtPurReqDetailDTO maPtPurReqDetailDTO = maPtPurReqDetailForm.getMaPtPurReqDetailDTO();
        
        maPtPurReqDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maPtPurReqDetailService.updateDetail(maPtPurReqDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * detail ���� update
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtPurReqDetailForm
     * @param request
     */
    private void updateStatus(MaPtPurReqDetailForm maPtPurReqDetailForm, HttpServletRequest request) throws Exception
    {
        MaPtPurReqDetailService maPtPurReqDetailService = (MaPtPurReqDetailService) getBean("maPtPurReqDetailService");
        
        MaPtPurReqDetailDTO maPtPurReqDetailDTO = maPtPurReqDetailForm.getMaPtPurReqDetailDTO();
        
        maPtPurReqDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maPtPurReqDetailService.updateStatus(maPtPurReqDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * detail ���� update�����Ϸ�
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtPurReqDetailForm
     * @param request
     */
    private void recStatus(MaPtPurReqDetailForm maPtPurReqDetailForm, HttpServletRequest request) throws Exception
    {
        MaPtPurReqDetailService maPtPurReqDetailService = (MaPtPurReqDetailService) getBean("maPtPurReqDetailService");
        
        MaPtPurReqDetailDTO maPtPurReqDetailDTO = maPtPurReqDetailForm.getMaPtPurReqDetailDTO();
        
        maPtPurReqDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maPtPurReqDetailService.recStatus(maPtPurReqDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }

    /**
     * ��û���
     * @author  euna0207
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtPurReqDetailForm
     * @param request
     */    
    private void recCancel(MaPtPurReqDetailForm maPtPurReqDetailForm, HttpServletRequest request) {
        MaPtPurReqDetailService maPtPurReqDetailService = (MaPtPurReqDetailService) getBean("maPtPurReqDetailService");
        
        MaPtPurReqDetailDTO maPtPurReqDetailDTO = maPtPurReqDetailForm.getMaPtPurReqDetailDTO();
        
        maPtPurReqDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maPtPurReqDetailService.recCancel(maPtPurReqDetailDTO, getUser(request));
        
        setAjaxStatus(request);
		
	}

}