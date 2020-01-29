package dream.req.work.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.req.work.dto.ReqWorkCommonDTO;
import dream.req.work.dto.ReqWorkDetailDTO;
import dream.req.work.form.ReqWorkDetailForm;
import dream.req.work.service.ReqWorkDetailService;

/**
 * �۾���û�� - �� action
 *
 * @author kim21017
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/reqWorkDetail" name="reqWorkDetailForm"
 *                input="/dream/req/work/reqWorkDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/reqInvWorkDetail" name="reqWorkDetailForm"
 *                input="/dream/req/work/reqInvWorkDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="reqWorkDetail" path="/dream/req/work/reqWorkDetail.jsp"
 *                        redirect="false"
 */
public class ReqWorkDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int DETAIL_INIT         = 8001;
    /** ���� */
    public static final int DETAIL_UPDATE       = 6002;
    /** �Է� */
    public static final int DETAIL_INSERT       = 5003;
    /** ��û */
    public static final int REQ_STATUS_UPDATE 	= 6004;

    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        ReqWorkDetailForm reqWorkDetailForm = (ReqWorkDetailForm) form;
        
        super.updateAudit(reqWorkDetailForm.getReqWorkDetailDTO().getAuditKey()==""?reqWorkDetailForm.getReqWorkCommonDTO().getAuditKey():reqWorkDetailForm.getReqWorkDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (reqWorkDetailForm.getStrutsAction())
        {
            case ReqWorkDetailAction.DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, reqWorkDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case ReqWorkDetailAction.DETAIL_UPDATE:
            	updateDetail(reqWorkDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case ReqWorkDetailAction.DETAIL_INSERT:
            	insertDetail(reqWorkDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case ReqWorkDetailAction.REQ_STATUS_UPDATE:
                updateStatus(reqWorkDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            default:
            	returnActionForward = mapping.getInputForward();
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
     * @param reqWorkDetailForm
     */
    private void findDetail(HttpServletRequest request, ReqWorkDetailForm reqWorkDetailForm)throws Exception
    {
        // Service ��ü ����
    	ReqWorkDetailService reqWorkDetailService = (ReqWorkDetailService)getBean("reqWorkDetailService", request);

    	ReqWorkCommonDTO reqWorkCommonDTO = reqWorkDetailForm.getReqWorkCommonDTO();

        // ��ȸ�� �� �κ�
        ReqWorkDetailDTO reqWorkDetailDTO = reqWorkDetailService.findDetail(reqWorkCommonDTO, getUser(request));

        // ��ȸ�� ��  �����Ѵ�.
        reqWorkDetailForm.setReqWorkDetailDTO(reqWorkDetailDTO);
    }

    /**
     * detail update
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     *
     * @param reqWorkDetailForm
     * @param request
     */
    private void updateDetail(ReqWorkDetailForm reqWorkDetailForm, HttpServletRequest request) throws Exception
    {
    	ReqWorkDetailService reqWorkDetailService = (ReqWorkDetailService) getBean("reqWorkDetailService", request);

        ReqWorkDetailDTO reqWorkDetailDTO = reqWorkDetailForm.getReqWorkDetailDTO();

        reqWorkDetailDTO.setCompNo(getUser(request).getCompNo());

        reqWorkDetailService.updateDetail(reqWorkDetailDTO,getUser(request));

        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     *
     * @param reqWorkDetailForm
     * @param request
     */
    private void insertDetail(ReqWorkDetailForm reqWorkDetailForm, HttpServletRequest request) throws Exception
    {
    	ReqWorkDetailService reqWorkDetailService = (ReqWorkDetailService) getBean("reqWorkDetailService", request);

        ReqWorkDetailDTO reqWorkDetailDTO = reqWorkDetailForm.getReqWorkDetailDTO();

        reqWorkDetailDTO.setCompNo(getUser(request).getCompNo());

        reqWorkDetailService.insertDetail(reqWorkDetailDTO,getUser(request));

        setAjaxStatus(request);
    }
    
    /**
     * update status
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     *
     * @param reqWorkDetailForm
     * @param request
     */
    private void updateStatus(ReqWorkDetailForm reqWorkDetailForm, HttpServletRequest request) throws Exception
    {
        ReqWorkDetailService reqWorkDetailService = (ReqWorkDetailService) getBean("reqWorkDetailService", request);
        
        ReqWorkDetailDTO reqWorkDetailDTO = reqWorkDetailForm.getReqWorkDetailDTO();

        reqWorkDetailDTO.setCompNo(getUser(request).getCompNo());

        reqWorkDetailService.updateStatus(reqWorkDetailDTO, getUser(request));

        setAjaxStatus(request);
    }
}