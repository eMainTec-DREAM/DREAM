package dream.part.pur.buy.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.part.pur.buy.dto.MaPtBuyReqHdrCommonDTO;
import dream.part.pur.buy.dto.MaPtBuyReqHdrDetailDTO;
import dream.part.pur.buy.form.MaPtBuyReqHdrDetailForm;
import dream.part.pur.buy.service.MaPtBuyReqHdrDetailService;

/**
 * ���Ž�û - �� action
 * 
 * @author kim2107
 * @version $Id: MaPtBuyReqHdrDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/maPtBuyReqHdrDetail" name="maPtBuyReqHdrDetailForm"
 *                input="/dream/part/pur/buy/maPtBuyReqHdrDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPtBuyReqHdrDetail" path="/dream/part/pur/buy/maPtBuyReqHdrDetail.jsp"
 *                        redirect="false"
 */
public class MaPtBuyReqHdrDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int BUY_DETAIL_INIT 		= 8001;
    /** ���� */
    public static final int BUY_DETAIL_UPDATE 		= 6002;
    /** �Է� */
    public static final int BUY_DETAIL_INPUT 		= 5003;
    /** ��û�Ϸ� */
    public static final int BUY_DETAIL_CONFIRM		= 1004;
    /** ǰ�� ���� üũ */
    public static final int BUY_DETAIL_CHECK		= 1005;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaPtBuyReqHdrDetailForm maPtBuyReqHdrDetailForm = (MaPtBuyReqHdrDetailForm) form;
        
        super.updateAudit(maPtBuyReqHdrDetailForm.getMaPtBuyReqHdrDetailDTO().getAuditKey()==""?maPtBuyReqHdrDetailForm.getMaPtBuyReqHdrCommonDTO().getAuditKey():maPtBuyReqHdrDetailForm.getMaPtBuyReqHdrDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));

        switch (maPtBuyReqHdrDetailForm.getStrutsAction())
        {
            case MaPtBuyReqHdrDetailAction.BUY_DETAIL_INIT:
                this.findDetail(request, maPtBuyReqHdrDetailForm);
                returnActionForward = mapping.findForward("maPtBuyReqHdrDetail");
                break;
            case MaPtBuyReqHdrDetailAction.BUY_DETAIL_UPDATE:
            	updateDetail(maPtBuyReqHdrDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPtBuyReqHdrDetailAction.BUY_DETAIL_INPUT:
            	insertDetail(maPtBuyReqHdrDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPtBuyReqHdrDetailAction.BUY_DETAIL_CONFIRM:
            	confirmDetail(maPtBuyReqHdrDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPtBuyReqHdrDetailAction.BUY_DETAIL_CHECK:
            	checkDetail(maPtBuyReqHdrDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPtBuyReqHdrDetailAction.BASE_ACTION_REPORT:
            	findReport(request, maPtBuyReqHdrDetailForm);
                returnActionForward = mapping.findForward("pdfViewer");
                break;
            default:
                returnActionForward = mapping.findForward("maPtBuyReqHdrDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * ���Ž�û ��ȸ
     * @author kim2107
     * @version $Id: MaPtBuyReqHdrDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maPtBuyReqHdrDetailForm
     */
    private void findDetail(HttpServletRequest request, MaPtBuyReqHdrDetailForm maPtBuyReqHdrDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaPtBuyReqHdrDetailService maPtBuyReqHdrDetailService = (MaPtBuyReqHdrDetailService)getBean("maPtBuyReqHdrDetailService", request);
    	MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO = maPtBuyReqHdrDetailForm.getMaPtBuyReqHdrCommonDTO();
    	maPtBuyReqHdrCommonDTO.setCompNo(getUser(request).getCompNo());
        // ��ȸ�� �� �κ�
        MaPtBuyReqHdrDetailDTO maPtBuyReqHdrDetailDTO = maPtBuyReqHdrDetailService.findDetail(maPtBuyReqHdrCommonDTO,getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
        maPtBuyReqHdrDetailForm.setMaPtBuyReqHdrDetailDTO(maPtBuyReqHdrDetailDTO);
    }
    
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: MaPtBuyReqHdrDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPtBuyReqHdrDetailForm
     * @param request
     */
    private void insertDetail(MaPtBuyReqHdrDetailForm maPtBuyReqHdrDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPtBuyReqHdrDetailService maPtBuyReqHdrDetailService = (MaPtBuyReqHdrDetailService) getBean("maPtBuyReqHdrDetailService", request);
        
        MaPtBuyReqHdrDetailDTO maPtBuyReqHdrDetailDTO = maPtBuyReqHdrDetailForm.getMaPtBuyReqHdrDetailDTO();
        maPtBuyReqHdrDetailDTO.setCompNo(getUser(request).getCompNo());
        
        User loginUser = getUser(request);
        
        maPtBuyReqHdrDetailService.insertDetail(maPtBuyReqHdrDetailDTO, loginUser);
        
        setAjaxStatus(request);
    }
    
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaPtBuyReqHdrDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPtBuyReqHdrDetailForm
     * @param request
     */
    private void updateDetail(MaPtBuyReqHdrDetailForm maPtBuyReqHdrDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPtBuyReqHdrDetailService maPtBuyReqHdrDetailService = (MaPtBuyReqHdrDetailService) getBean("maPtBuyReqHdrDetailService", request);
        
        MaPtBuyReqHdrDetailDTO maPtBuyReqHdrDetailDTO = maPtBuyReqHdrDetailForm.getMaPtBuyReqHdrDetailDTO();
        
        maPtBuyReqHdrDetailService.updateDetail(maPtBuyReqHdrDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * detail confirm
     * @author  kim21017
     * @version $Id: MaPtBuyReqHdrDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPtBuyReqHdrDetailForm
     * @param request
     */
    private void confirmDetail(MaPtBuyReqHdrDetailForm maPtBuyReqHdrDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPtBuyReqHdrDetailService maPtBuyReqHdrDetailService = (MaPtBuyReqHdrDetailService) getBean("maPtBuyReqHdrDetailService", request);
        
        MaPtBuyReqHdrDetailDTO maPtBuyReqHdrDetailDTO = maPtBuyReqHdrDetailForm.getMaPtBuyReqHdrDetailDTO();
        maPtBuyReqHdrDetailDTO.setCompNo(getUser(request).getCompNo());
        
        String[] result = maPtBuyReqHdrDetailService.confirmDetail(maPtBuyReqHdrDetailDTO,getUser(request));
        
        setAjaxDesc(request, result);
    }
    /**
     * qty check
     * @author  kim21017
     * @version $Id: MaPtBuyReqHdrDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPtBuyReqHdrDetailForm
     * @param request
     */
    private void checkDetail(MaPtBuyReqHdrDetailForm maPtBuyReqHdrDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPtBuyReqHdrDetailService maPtBuyReqHdrDetailService = (MaPtBuyReqHdrDetailService) getBean("maPtBuyReqHdrDetailService", request);
        
        MaPtBuyReqHdrDetailDTO maPtBuyReqHdrDetailDTO = maPtBuyReqHdrDetailForm.getMaPtBuyReqHdrDetailDTO();
        maPtBuyReqHdrDetailDTO.setCompNo(getUser(request).getCompNo());
        
        String isValid = maPtBuyReqHdrDetailService.checkDetail(maPtBuyReqHdrDetailDTO);

        setAjaxDesc(request, isValid);
    }
    /**
     * Report �� ��ȸ�Ѵ�.
     * @param request
     * @param maWoDailyDetailForm
     */
    private void findReport(HttpServletRequest request, MaPtBuyReqHdrDetailForm maPtBuyReqHdrDetailForm)
    {
    	MaPtBuyReqHdrDetailService maPtBuyReqHdrDetailService = (MaPtBuyReqHdrDetailService) getBean("maPtBuyReqHdrDetailService", request);
        
        MaPtBuyReqHdrDetailDTO maPtBuyReqHdrDetailDTO = maPtBuyReqHdrDetailForm.getMaPtBuyReqHdrDetailDTO();
        maPtBuyReqHdrDetailDTO.setCompNo(getUser(request).getCompNo());
        maPtBuyReqHdrDetailDTO.setUserLang(getUser(request).getLangId());
        
        List reportList = maPtBuyReqHdrDetailService.getReportView(maPtBuyReqHdrDetailDTO);

        //System.out.println(getUser(request).getCompNo());
        String rptName = "ptBuyReqHdrList";
        
        if("110".equals(getUser(request).getCompNo())) rptName = rptName + "_"+ getUser(request).getCompNo();
        
        // ��ȸ�� List �� form�� �����Ѵ�.
        request.setAttribute(REPORT_NAME_ATTRIBUTE, rptName);
        request.setAttribute(REPORT_MAP_ATTRIBUTE, reportList);
    }
}