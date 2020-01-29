package dream.tool.iss.rent.action;

import java.rmi.RemoteException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.tool.iss.rent.dto.MaPttIssCommonDTO;
import dream.tool.iss.rent.dto.MaPttIssDetailDTO;
import dream.tool.iss.rent.form.MaPttIssDetailForm;
import dream.tool.iss.rent.service.MaPttIssDetailService;

/**
 * �����԰� - �� action
 * 
 * @author ssong
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/maPttIssDetail" name="maPttIssDetailForm"
 *                input="/dream/tool/iss/rent/maPttIssDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPttIssDetail" path="/dream/tool/iss/rent/maPttIssDetail.jsp"
 *                        redirect="false"
 */
public class MaPttIssDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int PTISS_DETAIL_INIT         = 8001;
    /** ���� */
    public static final int PTISS_DETAIL_INPUT        = 5002;
    /** ���� */
    public static final int PTISS_DETAIL_UPDATE       = 6003;
    /** ���¼��� */
    public static final int PTISS_ISSUE			      = 6004;
    /** ������ */
    public static final int PTISS_CANCEL_ISSUE        = 6005;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaPttIssDetailForm maPttIssDetailForm = (MaPttIssDetailForm) form;
        
        super.updateAudit(maPttIssDetailForm.getMaPttIssDetailDTO().getAuditKey()==""?maPttIssDetailForm.getMaPttIssCommonDTO().getAuditKey():maPttIssDetailForm.getMaPttIssDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maPttIssDetailForm.getStrutsAction())
        {
            case MaPttIssDetailAction.PTISS_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, maPttIssDetailForm);
                returnActionForward = mapping.findForward("maPttIssDetail");
                break;
            case MaPttIssDetailAction.PTISS_DETAIL_INPUT:
            	insertDetail(maPttIssDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPttIssDetailAction.PTISS_DETAIL_UPDATE:
            	updateDetail(maPttIssDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPttIssDetailAction.PTISS_ISSUE:
            	 issuePart(maPttIssDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaPttIssDetailAction.PTISS_CANCEL_ISSUE:
                cancelIssueParts(maPttIssDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            default:
                returnActionForward = mapping.findForward("maPttIssDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * �����԰� �� ��ȸ
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maPttIssDetailForm
     */
    private void findDetail(HttpServletRequest request, MaPttIssDetailForm maPttIssDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaPttIssDetailService maPttIssDetailService = (MaPttIssDetailService)getBean("maPttIssDetailService");
    	
    	MaPttIssCommonDTO maPttIssCommonDTO = maPttIssDetailForm.getMaPttIssCommonDTO();
    	
        // ��ȸ�� �� �κ�
        MaPttIssDetailDTO maPttIssDetailDTO = maPttIssDetailService.findDetail(getUser(request), maPttIssCommonDTO.getPtIssListId());
        
        // ��ȸ�� ��  �����Ѵ�.
        maPttIssDetailForm.setMaPttIssDetailDTO(maPttIssDetailDTO);
    }
    
    /**
     * detail insert
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttIssDetailForm
     * @param request
     */
    private void insertDetail(MaPttIssDetailForm maPttIssDetailForm, HttpServletRequest request) throws Exception
    {
        MaPttIssDetailService maPttIssDetailService = (MaPttIssDetailService) getBean("maPttIssDetailService");
        
        MaPttIssDetailDTO maPttIssDetailDTO = maPttIssDetailForm.getMaPttIssDetailDTO();
        
        maPttIssDetailDTO.setCompNo(getUser(request).getCompNo());
        maPttIssDetailDTO.setEnterBy(getUser(request).getUserId());
        maPttIssDetailService.insertDetail(maPttIssDetailDTO);
        
        setAjaxStatus(request);
    }
    
    /**
     * detail update
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttIssDetailForm
     * @param request
     */
    private void updateDetail(MaPttIssDetailForm maPttIssDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPttIssDetailService maPttIssDetailService = (MaPttIssDetailService) getBean("maPttIssDetailService");
        
        MaPttIssDetailDTO maPttIssDetailDTO = maPttIssDetailForm.getMaPttIssDetailDTO();
        
        maPttIssDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maPttIssDetailService.updateDetail(maPttIssDetailDTO);
        
        setAjaxStatus(request);
    }
    
    /**
     * ���
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttIssDetailForm
     * @param request
     * @throws ServiceException 
     * @throws RemoteException 
     */
    private void issuePart(MaPttIssDetailForm maPttIssDetailForm, HttpServletRequest request) throws RemoteException, ServiceException
    {
        MaPttIssDetailService maPttIssDetailService = (MaPttIssDetailService) getBean("maPttIssDetailService");
        
        MaPttIssDetailDTO maPttIssDetailDTO = maPttIssDetailForm.getMaPttIssDetailDTO();
        
        maPttIssDetailDTO.setEnterBy(getUser(request).getUserId());
        maPttIssDetailDTO.setCompNo(getUser(request).getCompNo());
        
        String rtnValueArr[] = maPttIssDetailService.issuePart(maPttIssDetailDTO, getUser(request));
        
//        setAjaxStatus(request);
        setAjaxDesc(request, rtnValueArr);
    }
    
    /**
     * ������
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttIssDetailForm
     * @param request
     * @throws ServiceException 
     * @throws RemoteException 
     */
    private void cancelIssueParts(MaPttIssDetailForm maPttIssDetailForm, HttpServletRequest request) throws RemoteException, ServiceException
    {
        MaPttIssDetailService maPttIssDetailService = (MaPttIssDetailService) getBean("maPttIssDetailService");
        
        MaPttIssDetailDTO maPttIssDetailDTO = maPttIssDetailForm.getMaPttIssDetailDTO();
        
        maPttIssDetailDTO.setEnterBy(getUser(request).getUserId());
        maPttIssDetailDTO.setCompNo(getUser(request).getCompNo());
        
        String rtnValue[] = maPttIssDetailService.cancelIssuePart(maPttIssDetailDTO, getUser(request));
        setAjaxDesc(request, rtnValue);
//        setAjaxStatus(request);
    }
}