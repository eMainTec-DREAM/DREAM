package dream.tool.iss.rtn.action;

import java.rmi.RemoteException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.tool.iss.rtn.dto.MaPttRtnCommonDTO;
import dream.tool.iss.rtn.dto.MaPttRtnDetailDTO;
import dream.tool.iss.rtn.form.MaPttRtnDetailForm;
import dream.tool.iss.rtn.service.MaPttRtnDetailService;

/**
 * ���ⱸ�ݳ� - �� action
 * 
 * @author ssong
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/maPttRtnDetail" name="maPttRtnDetailForm"
 *                input="/dream/tool/iss/rtn/maPttRtnDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPttRtnDetail" path="/dream/tool/iss/rtn/maPttRtnDetail.jsp"
 *                        redirect="false"
 */
public class MaPttRtnDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int PTRTN_DETAIL_INIT         = 8001;
    /** ���� */
    public static final int PTRTN_DETAIL_INPUT        = 5002;
    /** ���� */
    public static final int PTRTN_DETAIL_UPDATE       = 6003;
    /** �ݳ��Ϸ� */
    public static final int PTRTN_COMPLETE			  = 6004;
    /** ������ */
    public static final int PTRTN_CANCEL_ISSUE        = 6005;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaPttRtnDetailForm maPttRtnDetailForm = (MaPttRtnDetailForm) form;
        
        super.updateAudit(maPttRtnDetailForm.getMaPttRtnDetailDTO().getAuditKey()==""?maPttRtnDetailForm.getMaPttRtnCommonDTO().getAuditKey():maPttRtnDetailForm.getMaPttRtnDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maPttRtnDetailForm.getStrutsAction())
        {
            case MaPttRtnDetailAction.PTRTN_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, maPttRtnDetailForm);
                returnActionForward = mapping.findForward("maPttRtnDetail");
                break;
            case MaPttRtnDetailAction.PTRTN_DETAIL_INPUT:
            	insertDetail(maPttRtnDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPttRtnDetailAction.PTRTN_DETAIL_UPDATE:
            	updateDetail(maPttRtnDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPttRtnDetailAction.PTRTN_COMPLETE:
            	 rtnPart(maPttRtnDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaPttRtnDetailAction.PTRTN_CANCEL_ISSUE:
                cancelIssueParts(maPttRtnDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            default:
                returnActionForward = mapping.findForward("maPttRtnDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * ���ⱸ�ݳ� �� ��ȸ
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maPttRtnDetailForm
     */
    private void findDetail(HttpServletRequest request, MaPttRtnDetailForm maPttRtnDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaPttRtnDetailService maPttRtnDetailService = (MaPttRtnDetailService)getBean("maPttRtnDetailService");
    	
    	MaPttRtnCommonDTO maPttRtnCommonDTO = maPttRtnDetailForm.getMaPttRtnCommonDTO();
    	
        // ��ȸ�� �� �κ�
        MaPttRtnDetailDTO maPttRtnDetailDTO = maPttRtnDetailService.findDetail(getUser(request), maPttRtnCommonDTO.getPtRtnListId());
        
        // ��ȸ�� ��  �����Ѵ�.
        maPttRtnDetailForm.setMaPttRtnDetailDTO(maPttRtnDetailDTO);
    }
    
    /**
     * detail insert
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttRtnDetailForm
     * @param request
     */
    private void insertDetail(MaPttRtnDetailForm maPttRtnDetailForm, HttpServletRequest request) throws Exception
    {
        MaPttRtnDetailService maPttRtnDetailService = (MaPttRtnDetailService) getBean("maPttRtnDetailService");
        
        MaPttRtnDetailDTO maPttRtnDetailDTO = maPttRtnDetailForm.getMaPttRtnDetailDTO();
        
        maPttRtnDetailDTO.setCompNo(getUser(request).getCompNo());
        maPttRtnDetailDTO.setEnterBy(getUser(request).getUserId());
        maPttRtnDetailService.insertDetail(maPttRtnDetailDTO);
        
        setAjaxStatus(request);
    }
    
    /**
     * detail update
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttRtnDetailForm
     * @param request
     */
    private void updateDetail(MaPttRtnDetailForm maPttRtnDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPttRtnDetailService maPttRtnDetailService = (MaPttRtnDetailService) getBean("maPttRtnDetailService");
        
        MaPttRtnDetailDTO maPttRtnDetailDTO = maPttRtnDetailForm.getMaPttRtnDetailDTO();
        
        maPttRtnDetailDTO.setCompNo(getUser(request).getCompNo());
        
        maPttRtnDetailService.updateDetail(maPttRtnDetailDTO);
        
        setAjaxStatus(request);
    }
    
    /**
     * ���
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttRtnDetailForm
     * @param request
     * @throws ServiceException 
     * @throws RemoteException 
     */
    private void rtnPart(MaPttRtnDetailForm maPttRtnDetailForm, HttpServletRequest request) throws RemoteException, ServiceException
    {
        MaPttRtnDetailService maPttRtnDetailService = (MaPttRtnDetailService) getBean("maPttRtnDetailService");
        
        MaPttRtnDetailDTO maPttRtnDetailDTO = maPttRtnDetailForm.getMaPttRtnDetailDTO();
        
        maPttRtnDetailDTO.setEnterBy(getUser(request).getUserId());
        maPttRtnDetailDTO.setCompNo(getUser(request).getCompNo());
        
        String rtnValueArr[] = maPttRtnDetailService.rtnPart(maPttRtnDetailDTO, getUser(request));
        
//        setAjaxStatus(request);
        setAjaxDesc(request, rtnValueArr);
    }
    
    /**
     * ������
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttRtnDetailForm
     * @param request
     * @throws ServiceException 
     * @throws RemoteException 
     */
    private void cancelIssueParts(MaPttRtnDetailForm maPttRtnDetailForm, HttpServletRequest request) throws RemoteException, ServiceException
    {
        MaPttRtnDetailService maPttRtnDetailService = (MaPttRtnDetailService) getBean("maPttRtnDetailService");
        
        MaPttRtnDetailDTO maPttRtnDetailDTO = maPttRtnDetailForm.getMaPttRtnDetailDTO();
        
        maPttRtnDetailDTO.setEnterBy(getUser(request).getUserId());
        maPttRtnDetailDTO.setCompNo(getUser(request).getCompNo());
        
        String rtnValue[] = maPttRtnDetailService.cancelIssuePart(maPttRtnDetailDTO, getUser(request));
        setAjaxDesc(request, rtnValue);
//        setAjaxStatus(request);
    }
}