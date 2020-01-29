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
 * 긴급출고 - 상세 action
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
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int PTISSEMG_DETAIL_INIT         = 8001;
    /** 저장 */
    public static final int PTISSEMG_DETAIL_INPUT        = 5002;
    /** 수정 */
    public static final int PTISSEMG_DETAIL_UPDATE       = 6003;
    /** 출고 완료 */
    public static final int PTISSEMG_ISSUE               = 6004;
    /** 출고 취소 */
    public static final int PTISSEMG_ISSUE_CANCEL        = 6005;
    /** 현재고 조회 */
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
                // 페이지 조회
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
     * 출고
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
     * 출고취소
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
     * 자재재고 상세 조회
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maPtIssEmgDetailForm
     */
    private void findDetail(HttpServletRequest request, MaPtIssEmgDetailForm maPtIssEmgDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaPtIssEmgDetailService maPtIssEmgDetailService = (MaPtIssEmgDetailService)getBean("maPtIssEmgDetailService");
    	
    	MaPtIssEmgCommonDTO maPtIssEmgCommonDTO = maPtIssEmgDetailForm.getMaPtIssEmgCommonDTO();
    	
    	maPtIssEmgCommonDTO.setCompNo(getUser(request).getCompNo());
        // 조회된 상세 부분
        MaPtIssEmgDetailDTO maPtIssEmgDetailDTO = maPtIssEmgDetailService.findDetail(maPtIssEmgCommonDTO, getUser(request));
        
        // 조회된 상세  셋팅한다.
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