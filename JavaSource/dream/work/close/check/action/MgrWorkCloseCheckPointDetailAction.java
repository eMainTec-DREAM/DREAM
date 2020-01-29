package dream.work.close.check.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.close.check.dto.MgrWorkCloseCheckCommonDTO;
import dream.work.close.check.dto.MgrWorkCloseCheckPointDetailDTO;
import dream.work.close.check.dto.MgrWorkCloseCheckPointListDTO;
import dream.work.close.check.form.MgrWorkCloseCheckPointDetailForm;
import dream.work.close.check.service.MgrWorkCloseCheckPointDetailService;

/**
 * ǥ���׸� ����Ʈ - �� action
 * 
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 * @struts:action path="/mgrWorkCloseCheckPointDetail" name="mgrWorkCloseCheckPointDetailForm"
 *                input="/dream/work/close/check/mgrWorkCloseCheckPointDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrWorkCloseCheckPointDetail" path="/dream/work/close/check/mgrWorkCloseCheckPointDetail.jsp"
 *                        redirect="false"
 */
public class MgrWorkCloseCheckPointDetailAction extends AuthAction
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
        MgrWorkCloseCheckPointDetailForm mgrWorkCloseCheckPointDetailForm = (MgrWorkCloseCheckPointDetailForm) form;
      
        super.updateAudit(mgrWorkCloseCheckPointDetailForm.getMgrWorkCloseCheckPointDetailDTO().getAuditKey()==""?mgrWorkCloseCheckPointDetailForm.getMgrWorkCloseCheckCommonDTO().getAuditKey():mgrWorkCloseCheckPointDetailForm.getMgrWorkCloseCheckPointDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (mgrWorkCloseCheckPointDetailForm.getStrutsAction())
        {
            case MgrWorkCloseCheckPointDetailAction.STD_DETAIL_INIT:
                this.findDetail(request, mgrWorkCloseCheckPointDetailForm);
                returnActionForward = mapping.findForward("mgrWorkCloseCheckPointDetail");
                break;
            case MgrWorkCloseCheckPointDetailAction.STD_DETAIL_INPUT:
            	insertDetail(mgrWorkCloseCheckPointDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MgrWorkCloseCheckPointDetailAction.STD_DETAIL_UPDATE:
            	updateDetail(mgrWorkCloseCheckPointDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("mgrWorkCloseCheckPointDetail");
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
     * @param mgrWorkCloseCheckPointDetailForm
     */
    private void findDetail(HttpServletRequest request, MgrWorkCloseCheckPointDetailForm mgrWorkCloseCheckPointDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MgrWorkCloseCheckPointDetailService mgrWorkCloseCheckPointDetailService = (MgrWorkCloseCheckPointDetailService)getBean("mgrWorkCloseCheckPointDetailService");

        // �Ѱ��� ǥ���׸��ȣ ����
    	MgrWorkCloseCheckCommonDTO mgrWorkCloseCheckCommonDTO = mgrWorkCloseCheckPointDetailForm.getMgrWorkCloseCheckCommonDTO();
    	MgrWorkCloseCheckPointListDTO mgrWorkCloseCheckPointListDTO = mgrWorkCloseCheckPointDetailForm.getMgrWorkCloseCheckPointListDTO();
        
        // ��ȸ�� �� �κ�
        MgrWorkCloseCheckPointDetailDTO mgrWorkCloseCheckPointDetailDTO = mgrWorkCloseCheckPointDetailService.findDetail(mgrWorkCloseCheckCommonDTO, mgrWorkCloseCheckPointListDTO, getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
        mgrWorkCloseCheckPointDetailForm.setMgrWorkCloseCheckPointDetailDTO(mgrWorkCloseCheckPointDetailDTO);
    }
    
    /**
     * detail insert
     * @author  
     * @version $Id:  $
     * @since   1.0
     * 
     * @param mgrWorkCloseCheckPointDetailForm
     * @param request
     */
    private void insertDetail(MgrWorkCloseCheckPointDetailForm mgrWorkCloseCheckPointDetailForm, HttpServletRequest request) throws Exception
    {
        MgrWorkCloseCheckPointDetailService mgrWorkCloseCheckPointDetailService = (MgrWorkCloseCheckPointDetailService) getBean("mgrWorkCloseCheckPointDetailService");
        
        MgrWorkCloseCheckPointDetailDTO mgrWorkCloseCheckPointDetailDTO = mgrWorkCloseCheckPointDetailForm.getMgrWorkCloseCheckPointDetailDTO();
    	MgrWorkCloseCheckCommonDTO mgrWorkCloseCheckCommonDTO = mgrWorkCloseCheckPointDetailForm.getMgrWorkCloseCheckCommonDTO();
        
        mgrWorkCloseCheckPointDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        mgrWorkCloseCheckPointDetailService.insertDetail(mgrWorkCloseCheckPointDetailDTO,mgrWorkCloseCheckCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * detail update
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param mgrWorkCloseCheckPointDetailForm
     * @param request
     */
    private void updateDetail(MgrWorkCloseCheckPointDetailForm mgrWorkCloseCheckPointDetailForm, HttpServletRequest request) throws Exception
    {
    	MgrWorkCloseCheckPointDetailService mgrWorkCloseCheckPointDetailService = (MgrWorkCloseCheckPointDetailService) getBean("mgrWorkCloseCheckPointDetailService");
        
        MgrWorkCloseCheckPointDetailDTO mgrWorkCloseCheckPointDetailDTO = mgrWorkCloseCheckPointDetailForm.getMgrWorkCloseCheckPointDetailDTO();
    	MgrWorkCloseCheckCommonDTO mgrWorkCloseCheckCommonDTO = mgrWorkCloseCheckPointDetailForm.getMgrWorkCloseCheckCommonDTO();
        
        mgrWorkCloseCheckPointDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        mgrWorkCloseCheckPointDetailService.updateDetail(mgrWorkCloseCheckPointDetailDTO,mgrWorkCloseCheckCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }

}