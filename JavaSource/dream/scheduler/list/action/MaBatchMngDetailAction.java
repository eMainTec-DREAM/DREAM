package dream.scheduler.list.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.scheduler.list.dto.MaBatchMngCommonDTO;
import dream.scheduler.list.dto.MaBatchMngDetailDTO;
import dream.scheduler.list.form.MaBatchMngDetailForm;
import dream.scheduler.list.service.MaBatchMngDetailService;

/**
 * ��ư - �� action
 * 
 * @author kim2107
 * @version $Id: MaBatchMngDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/maBatchMngDetail" name="maBatchMngDetailForm"
 *                input="/dream/scheduler/list/maBatchMngDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maBatchMngDetail" path="/dream/scheduler/list/maBatchMngDetail.jsp"
 *                        redirect="false"
 */
public class MaBatchMngDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int BATCH_DETAIL_INIT 		= 8001;
    /** ���� */
    public static final int BATCH_DETAIL_INPUT 		= 5002;
    /** ���� */
    public static final int BATCH_DETAIL_UPDATE 	= 6003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaBatchMngDetailForm maBatchMngDetailForm = (MaBatchMngDetailForm) form;
        
        super.updateAudit(maBatchMngDetailForm.getMaBatchMngDetailDTO().getAuditKey()==""?maBatchMngDetailForm.getMaBatchMngCommonDTO().getAuditKey():maBatchMngDetailForm.getMaBatchMngDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maBatchMngDetailForm.getStrutsAction())
        {
            case MaBatchMngDetailAction.BATCH_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, maBatchMngDetailForm);
                returnActionForward = mapping.findForward("maBatchMngDetail");
                break;
            case MaBatchMngDetailAction.BATCH_DETAIL_INPUT:
            	insertDetail(maBatchMngDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaBatchMngDetailAction.BATCH_DETAIL_UPDATE:
            	updateDetail(maBatchMngDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maBatchMngDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * ��ư �� ��ȸ
     * @author kim2107
     * @version $Id: MaBatchMngDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maBatchMngDetailForm
     */
    private void findDetail(HttpServletRequest request, MaBatchMngDetailForm maBatchMngDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaBatchMngDetailService maBatchMngDetailService = (MaBatchMngDetailService)getBean("maBatchMngDetailService");
    	MaBatchMngCommonDTO maBatchMngCommonDTO = maBatchMngDetailForm.getMaBatchMngCommonDTO();
    	maBatchMngCommonDTO.setCompNo(getUser(request).getCompNo());
        
        // ��ȸ�� �� �κ�
        MaBatchMngDetailDTO maBatchMngDetailDTO = maBatchMngDetailService.findDetail(maBatchMngCommonDTO);
        
        // ��ȸ�� ��  �����Ѵ�.
        maBatchMngDetailForm.setMaBatchMngDetailDTO(maBatchMngDetailDTO);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaBatchMngDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maBatchMngDetailForm
     * @param request
     */
    private void insertDetail(MaBatchMngDetailForm maBatchMngDetailForm, HttpServletRequest request) throws Exception
    {
        MaBatchMngDetailService maBatchMngDetailService = (MaBatchMngDetailService) getBean("maBatchMngDetailService");
        
        MaBatchMngDetailDTO maBatchMngDetailDTO = maBatchMngDetailForm.getMaBatchMngDetailDTO();
        maBatchMngDetailDTO.setCompNo(getUser(request).getCompNo());
        maBatchMngDetailDTO.setEnterBy(getUser(request).getUserId());
        
        maBatchMngDetailService.insertDetail(maBatchMngDetailDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaBatchMngDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maBatchMngDetailForm
     * @param request
     */
    private void updateDetail(MaBatchMngDetailForm maBatchMngDetailForm, HttpServletRequest request) throws Exception
    {
    	MaBatchMngDetailService maBatchMngDetailService = (MaBatchMngDetailService) getBean("maBatchMngDetailService");
        
        MaBatchMngDetailDTO maBatchMngDetailDTO = maBatchMngDetailForm.getMaBatchMngDetailDTO();
        maBatchMngDetailDTO.setCompNo(getUser(request).getCompNo());
        maBatchMngDetailDTO.setEnterBy(getUser(request).getUserId());
        
        maBatchMngDetailService.updateDetail(maBatchMngDetailDTO);
        
        setAjaxStatus(request);
    }
}