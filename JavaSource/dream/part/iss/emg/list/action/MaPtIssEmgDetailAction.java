package dream.part.iss.emg.list.action;

import java.rmi.RemoteException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.part.iss.emg.list.dto.MaPtIssEmgCommonDTO;
import dream.part.iss.emg.list.dto.MaPtIssEmgDetailDTO;
import dream.part.iss.emg.list.form.MaPtIssEmgDetailForm;
import dream.part.iss.emg.list.service.MaPtIssEmgDetailService;

/**
 * ������ - �� action
 * 
 * @author ssong
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/maPtIssEmgDetail" name="maPtIssEmgDetailForm"
 *                input="/dream/part/iss/emg/list/maPtIssEmgDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPtIssEmgDetail" path="/dream/part/iss/emg/list/maPtIssEmgDetail.jsp"
 *                        redirect="false"
 */
public class MaPtIssEmgDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int PTISSEMG_DETAIL_INIT         = 8001;
    /** ���� */
    public static final int PTISSEMG_DETAIL_INPUT        = 5002;
    /** ���� */
    public static final int PTISSEMG_DETAIL_UPDATE       = 6003;
    /** ��� �Ϸ� */
    public static final int PTISSEMG_ISSUE               = 6004;
    /** ��� ��� */
    public static final int PTISSEMG_ISSUE_CANCEL        = 6005;
    /** ����� ��ȸ */
    public static final int PTISSEMG_FIND_STOCK_QTY      = 6006;


    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaPtIssEmgDetailForm maPtIssEmgDetailForm = (MaPtIssEmgDetailForm) form;
        

        super.updateAudit(maPtIssEmgDetailForm.getMaPtIssEmgDetailDTO().getAuditKey()==""?maPtIssEmgDetailForm.getMaPtIssEmgCommonDTO().getAuditKey():maPtIssEmgDetailForm.getMaPtIssEmgDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));


        switch (maPtIssEmgDetailForm.getStrutsAction())
        {
            case MaPtIssEmgDetailAction.PTISSEMG_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, maPtIssEmgDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case MaPtIssEmgDetailAction.PTISSEMG_DETAIL_INPUT:
            	insertDetail(maPtIssEmgDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPtIssEmgDetailAction.PTISSEMG_DETAIL_UPDATE:
            	updateDetail(maPtIssEmgDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPtIssEmgDetailAction.PTISSEMG_ISSUE:
                issuePart(maPtIssEmgDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaPtIssEmgDetailAction.PTISSEMG_ISSUE_CANCEL:
            	issueCancel(maPtIssEmgDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaPtIssEmgDetailAction.PTISSEMG_FIND_STOCK_QTY:
                findStockQty(maPtIssEmgDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

    /**
     * ���
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtIssEmgDetailForm
     * @param request
     * @throws ServiceException 
     * @throws RemoteException 
     */
    private void issuePart(MaPtIssEmgDetailForm maPtIssEmgDetailForm, HttpServletRequest request) throws RemoteException, ServiceException
    {
        MaPtIssEmgDetailService maPtIssEmgDetailService = (MaPtIssEmgDetailService) getBean("maPtIssEmgDetailService",request);
        
        MaPtIssEmgDetailDTO maPtIssEmgDetailDTO = maPtIssEmgDetailForm.getMaPtIssEmgDetailDTO();
        
        maPtIssEmgDetailDTO.setEnterBy(getUser(request).getUserId());
        maPtIssEmgDetailDTO.setCompNo(getUser(request).getCompNo());
        
        String[] result = maPtIssEmgDetailService.issueComp(maPtIssEmgDetailDTO, getUser(request));
        
        setAjaxDesc(request, result);
    }

    /**
     * ������
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtIssEmgDetailForm
     * @param request
     * @throws ServiceException 
     * @throws RemoteException 
     */
    private void issueCancel(MaPtIssEmgDetailForm maPtIssEmgDetailForm, HttpServletRequest request) throws RemoteException, ServiceException
    {
        MaPtIssEmgDetailService maPtIssEmgDetailService = (MaPtIssEmgDetailService) getBean("maPtIssEmgDetailService",request);
        
        MaPtIssEmgDetailDTO maPtIssEmgDetailDTO = maPtIssEmgDetailForm.getMaPtIssEmgDetailDTO();
        
        maPtIssEmgDetailDTO.setEnterBy(getUser(request).getUserId());
        maPtIssEmgDetailDTO.setCompNo(getUser(request).getCompNo());
        
        String[] result = maPtIssEmgDetailService.issueCancel(maPtIssEmgDetailDTO, getUser(request));
        
        setAjaxDesc(request, result);
    }
    /**
     * ������� �� ��ȸ
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maPtIssEmgDetailForm
     */
    private void findDetail(HttpServletRequest request, MaPtIssEmgDetailForm maPtIssEmgDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaPtIssEmgDetailService maPtIssEmgDetailService = (MaPtIssEmgDetailService)getBean("maPtIssEmgDetailService");
    	
    	MaPtIssEmgCommonDTO maPtIssEmgCommonDTO = maPtIssEmgDetailForm.getMaPtIssEmgCommonDTO();
    	
    	maPtIssEmgCommonDTO.setCompNo(getUser(request).getCompNo());
        // ��ȸ�� �� �κ�
        MaPtIssEmgDetailDTO maPtIssEmgDetailDTO = maPtIssEmgDetailService.findDetail(maPtIssEmgCommonDTO, getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
        maPtIssEmgDetailForm.setMaPtIssEmgDetailDTO(maPtIssEmgDetailDTO);
    }
    /**
     * detail insert
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtIssEmgDetailForm
     * @param request
     */
    private void insertDetail(MaPtIssEmgDetailForm maPtIssEmgDetailForm, HttpServletRequest request) throws Exception
    {
        MaPtIssEmgDetailService maPtIssEmgDetailService = (MaPtIssEmgDetailService) getBean("maPtIssEmgDetailService");
        
        MaPtIssEmgDetailDTO maPtIssEmgDetailDTO = maPtIssEmgDetailForm.getMaPtIssEmgDetailDTO();
        
        maPtIssEmgDetailDTO.setEnterBy(getUser(request).getUserId());
        maPtIssEmgDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maPtIssEmgDetailService.insertDetail(maPtIssEmgDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * detail update
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtIssEmgDetailForm
     * @param request
     */
    private void updateDetail(MaPtIssEmgDetailForm maPtIssEmgDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPtIssEmgDetailService maPtIssEmgDetailService = (MaPtIssEmgDetailService) getBean("maPtIssEmgDetailService");
        
        MaPtIssEmgDetailDTO maPtIssEmgDetailDTO = maPtIssEmgDetailForm.getMaPtIssEmgDetailDTO();
        
        maPtIssEmgDetailDTO.setEnterBy(getUser(request).getUserId());
        maPtIssEmgDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maPtIssEmgDetailService.updateDetail(maPtIssEmgDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    private void findStockQty(MaPtIssEmgDetailForm maPtIssEmgDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPtIssEmgDetailService maPtIssEmgDetailService = (MaPtIssEmgDetailService) getBean("maPtIssEmgDetailService");
        
        MaPtIssEmgDetailDTO maPtIssEmgDetailDTO = maPtIssEmgDetailForm.getMaPtIssEmgDetailDTO();
        
        String rtnValue = maPtIssEmgDetailService.findStockQty(maPtIssEmgDetailDTO, getUser(request));
        setAjaxDesc(request, rtnValue);
    }
}