package dream.work.rpt.mabdpoint.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.work.rpt.mabdpoint.dto.MaBdPointCommonDTO;
import dream.work.rpt.mabdpoint.dto.MaBdPointDetailDTO;
import dream.work.rpt.mabdpoint.form.MaBdPointDetailForm;
import dream.work.rpt.mabdpoint.service.MaBdPointDetailService;

/**
 * �̻�������ġ - �� action
 * 
 * @author kim21017
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/maBdPointDetail" name="maBdPointDetailForm"
 *                input="/dream/work/rpt/bdpoint/maBdPointDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maBdPointDetail" path="/dream/work/rpt/bdpoint/maBdPointDetail.jsp"
 *                        redirect="false"
 */
public class MaBdPointDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int BD_DETAIL_INIT         = 8001;
    /** ���� */
    public static final int BD_DETAIL_UPDATE       = 6001;
    /** ID �������� */
    public static final int FIND_ID         	   = 8002;
    /** STATUS �������� */
    public static final int FIND_STATUS        	   = 8003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaBdPointDetailForm maBdPointDetailForm = (MaBdPointDetailForm) form;
        
        super.updateAudit(maBdPointDetailForm.getMaBdPointDetailDTO().getAuditKey()==""?maBdPointDetailForm.getMaBdPointCommonDTO().getAuditKey():maBdPointDetailForm.getMaBdPointDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maBdPointDetailForm.getStrutsAction())
        {
            case MaBdPointDetailAction.BD_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, maBdPointDetailForm);
                returnActionForward = mapping.findForward("maBdPointDetail");
                break;
            case MaBdPointDetailAction.BD_DETAIL_UPDATE:
            	updateDetail(maBdPointDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaBdPointDetailAction.FIND_ID:
            	findId(request, response, maBdPointDetailForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaBdPointDetailAction.FIND_STATUS:
            	findStatus(request, response, maBdPointDetailForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maBdPointDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     *  �� ��ȸ
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maBdPointDetailForm
     */
    private void findDetail(HttpServletRequest request, MaBdPointDetailForm maBdPointDetailForm)throws Exception 
    {   
    	// Service ��ü ����
    	MaBdPointDetailService maBdPointDetailService = (MaBdPointDetailService)getBean("maBdPointDetailService");
    	
    	MaBdPointCommonDTO maBdPointCommonDTO = maBdPointDetailForm.getMaBdPointCommonDTO();
    	
        // ��ȸ�� �� �κ�
        MaBdPointDetailDTO maBdPointDetailDTO = maBdPointDetailService.findDetail(maBdPointCommonDTO, getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
        maBdPointDetailForm.setMaBdPointDetailDTO(maBdPointDetailDTO);
    }
    
    /**
     * detail update
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maBdPointDetailForm
     * @param request
     */
    private void updateDetail(MaBdPointDetailForm maBdPointDetailForm, HttpServletRequest request) throws Exception
    {
    	MaBdPointDetailService maBdPointDetailService = (MaBdPointDetailService) getBean("maBdPointDetailService");
        
        MaBdPointDetailDTO maBdPointDetailDTO = maBdPointDetailForm.getMaBdPointDetailDTO();
        
        maBdPointDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maBdPointDetailService.updateDetail(maBdPointDetailDTO);
        
        setAjaxStatus(request);
    }
    
    private void findId(HttpServletRequest request, HttpServletResponse response, MaBdPointDetailForm maBdPointDetailForm) throws Exception
    {
    	MaBdPointDetailService maBdPointDetailService = (MaBdPointDetailService) getBean("maBdPointDetailService");        
    	MaBdPointDetailDTO maBdPointDetailDTO = maBdPointDetailForm.getMaBdPointDetailDTO();
    	
    	User user = getUser(request);
    	String id = maBdPointDetailService.findId(maBdPointDetailDTO,user);
    	
    	setAjaxDesc(request,id);
    }
    
    private void findStatus(HttpServletRequest request, HttpServletResponse response, MaBdPointDetailForm maBdPointDetailForm) throws Exception
    {
    	MaBdPointDetailService maBdPointDetailService = (MaBdPointDetailService) getBean("maBdPointDetailService");        
    	
    	MaBdPointCommonDTO maBdPointCommonDTO = maBdPointDetailForm.getMaBdPointCommonDTO();
    	
    	String status = maBdPointDetailService.findStatus(maBdPointCommonDTO, getUser(request));
    	
    	setAjaxDesc(request, status);
    }
}