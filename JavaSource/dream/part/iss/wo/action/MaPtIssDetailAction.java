package dream.part.iss.wo.action;

import java.rmi.RemoteException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.part.iss.wo.dto.MaPtIssCommonDTO;
import dream.part.iss.wo.dto.MaPtIssDetailDTO;
import dream.part.iss.wo.form.MaPtIssDetailForm;
import dream.part.iss.wo.service.MaPtIssDetailService;

/**
 * �������Ȯ�� - �� action
 * 
 * @author ssong
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/maPtIssDetail" name="maPtIssDetailForm"
 *                input="/dream/part/iss/wo/maPtIssDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPtIssDetail" path="/dream/part/iss/wo/maPtIssDetail.jsp"
 *                        redirect="false"
 */
public class MaPtIssDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int PTISS_DETAIL_INIT         = 8001;
    /** ���� */
    public static final int PTISS_DETAIL_INPUT        = 5002;
    /** ���� */
    public static final int PTISS_DETAIL_UPDATE       = 6003;
    /** ��� */
    public static final int PTISS_ISSUE               = 6004;
    /** ������ */
    public static final int PTISS_CANCEL_ISSUE        = 1005;
    /** ����� ��ȸ */
    public static final int PTISS_FIND_STOCK_QTY      = 6006;

    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaPtIssDetailForm maPtIssDetailForm = (MaPtIssDetailForm) form;
        
        super.updateAudit(maPtIssDetailForm.getMaPtIssDetailDTO().getAuditKey()==""?maPtIssDetailForm.getMaPtIssCommonDTO().getAuditKey():maPtIssDetailForm.getMaPtIssDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));

        switch (maPtIssDetailForm.getStrutsAction())
        {
            case MaPtIssDetailAction.PTISS_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, maPtIssDetailForm);
                returnActionForward = mapping.findForward("maPtIssDetail");
                break;
            case MaPtIssDetailAction.PTISS_DETAIL_INPUT:
            	insertDetail(maPtIssDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPtIssDetailAction.PTISS_DETAIL_UPDATE:
            	updateDetail(maPtIssDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPtIssDetailAction.PTISS_ISSUE:
                confirmIssuePart(maPtIssDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaPtIssDetailAction.PTISS_CANCEL_ISSUE:
            	cancelIssuePart(maPtIssDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaPtIssDetailAction.PTISS_FIND_STOCK_QTY:
                findStockQty(maPtIssDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            default:
                returnActionForward = mapping.findForward("maPtIssDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * ������
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtIssDetailForm
     * @param request
     * @throws ServiceException 
     * @throws RemoteException 
     */
    private void cancelIssuePart(MaPtIssDetailForm maPtIssDetailForm, HttpServletRequest request) throws Exception
    {
        MaPtIssDetailService maPtIssDetailService = (MaPtIssDetailService) getBean("maPtIssDetailService");
        
        MaPtIssDetailDTO maPtIssDetailDTO = maPtIssDetailForm.getMaPtIssDetailDTO();
        
        maPtIssDetailDTO.setEnterBy(getUser(request).getUserId());
        maPtIssDetailDTO.setCompNo(getUser(request).getCompNo());
        
        String rtnValue[] = maPtIssDetailService.cancelIssuePart(maPtIssDetailDTO, getUser(request));
        setAjaxDesc(request, rtnValue);
//        setAjaxStatus(request);
    }

    /**
     * ���
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtIssDetailForm
     * @param request
     * @throws ServiceException 
     * @throws RemoteException 
     */
    private void confirmIssuePart(MaPtIssDetailForm maPtIssDetailForm, HttpServletRequest request) throws Exception
    {
        MaPtIssDetailService maPtIssDetailService = (MaPtIssDetailService) getBean("maPtIssDetailService");
        
        MaPtIssDetailDTO maPtIssDetailDTO = maPtIssDetailForm.getMaPtIssDetailDTO();
        
        maPtIssDetailDTO.setEnterBy(getUser(request).getUserId());
        maPtIssDetailDTO.setCompNo(getUser(request).getCompNo());
        
        String rtnValueArr[] = maPtIssDetailService.confirmIssuePart(maPtIssDetailDTO, getUser(request));
        
//        setAjaxStatus(request);
        setAjaxDesc(request, rtnValueArr);
    }

    /**
     * ������� �� ��ȸ
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maPtIssDetailForm
     */
    private void findDetail(HttpServletRequest request, MaPtIssDetailForm maPtIssDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaPtIssDetailService maPtIssDetailService = (MaPtIssDetailService)getBean("maPtIssDetailService");
    	
    	MaPtIssCommonDTO maPtIssCommonDTO = maPtIssDetailForm.getMaPtIssCommonDTO();
    	
    	maPtIssCommonDTO.setCompNo(getUser(request).getCompNo());
        // ��ȸ�� �� �κ�
        MaPtIssDetailDTO maPtIssDetailDTO = maPtIssDetailService.findDetail(maPtIssCommonDTO, getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
        maPtIssDetailForm.setMaPtIssDetailDTO(maPtIssDetailDTO);
    }
    /**
     * detail insert
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtIssDetailForm
     * @param request
     */
    private void insertDetail(MaPtIssDetailForm maPtIssDetailForm, HttpServletRequest request) throws Exception
    {
        MaPtIssDetailService maPtIssDetailService = (MaPtIssDetailService) getBean("maPtIssDetailService");
        
        MaPtIssDetailDTO maPtIssDetailDTO = maPtIssDetailForm.getMaPtIssDetailDTO();
        
        maPtIssDetailDTO.setEnterBy(getUser(request).getUserId());
        maPtIssDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maPtIssDetailService.insertDetail(maPtIssDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * detail update
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtIssDetailForm
     * @param request
     */
    private void updateDetail(MaPtIssDetailForm maPtIssDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPtIssDetailService maPtIssDetailService = (MaPtIssDetailService) getBean("maPtIssDetailService");
        
    	MaPtIssDetailDTO maPtIssDetailDTO = maPtIssDetailForm.getMaPtIssDetailDTO();
        
        maPtIssDetailDTO.setEnterBy(getUser(request).getUserId());
        maPtIssDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maPtIssDetailService.updateDetail(maPtIssDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    private void findStockQty(MaPtIssDetailForm maPtIssDetailForm, HttpServletRequest request) throws Exception
    {
        MaPtIssDetailService maPtIssDetailService = (MaPtIssDetailService) getBean("maPtIssDetailService");
        
        MaPtIssDetailDTO maPtIssDetailDTO = maPtIssDetailForm.getMaPtIssDetailDTO();
        
        String rtnValue = maPtIssDetailService.findStockQty(maPtIssDetailDTO, getUser(request));
        setAjaxDesc(request, rtnValue);
    }
    
}