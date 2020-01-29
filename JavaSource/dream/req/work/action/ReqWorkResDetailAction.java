package dream.req.work.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.req.work.dto.ReqWorkCommonDTO;
import dream.req.work.dto.ReqWorkResDetailDTO;
import dream.req.work.form.ReqWorkResDetailForm;
import dream.req.work.service.ReqWorkResDetailService;

/**
 * �۾���û�� - ó���۾�
 * @author  kim21017
 * @version $Id: ReqWorkResDetailAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/reqWorkResDetail" name="reqWorkResDetailForm"
 *                input="/dream/req/work/reqWorkResDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="reqWorkResDetail" path="/dream/req/work/reqWorkResDetail.jsp"
 *                        redirect="false"
 */
public class ReqWorkResDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int DETAIL_INIT 	= 1001;
    /** ���� */
    public static final int DETAIL_UPDATE 	= 1002;
    /** �Է� */
    public static final int DETAIL_INPUT 	= 1003;

    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        ReqWorkResDetailForm reqWorkResDetailForm = (ReqWorkResDetailForm) form;
        String strutsAction = request.getParameter("strutsAction");
        if(strutsAction!="") reqWorkResDetailForm.setStrutsAction(Integer.parseInt(strutsAction));
        switch (reqWorkResDetailForm.getStrutsAction())
        {
            case ReqWorkResDetailAction.DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, reqWorkResDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case ReqWorkResDetailAction.DETAIL_UPDATE:
            	updateDetail(reqWorkResDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case ReqWorkResDetailAction.DETAIL_INPUT:
            	insertDetail(reqWorkResDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

    /**
     * �� ��ȸ
     * @author kim2107
     * @version $Id: ReqWorkResDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     *
     * @param request
     * @param reqWorkResDetailForm
     */
    private void findDetail(HttpServletRequest request, ReqWorkResDetailForm reqWorkResDetailForm)throws Exception
    {
        // Service ��ü ����
    	ReqWorkResDetailService reqWorkResDetailService = (ReqWorkResDetailService)getBean("reqWorkResDetailService");

    	// ��û�� id
        String woReqId = reqWorkResDetailForm.getReqWorkCommonDTO().getWoReqId();
        // ó������ id
        String woReqResId = reqWorkResDetailForm.getReqWorkResListDTO().getWoReqResId();

        // ��ȸ�� �� �κ�
        ReqWorkResDetailDTO reqWorkResDetailDTO = reqWorkResDetailService.findDetail(woReqId, woReqResId, getUser(request));

        // ��ȸ�� ��  �����Ѵ�.
        reqWorkResDetailForm.setReqWorkResDetailDTO(reqWorkResDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: ReqWorkResDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     *
     * @param reqWorkResDetailForm
     * @param request
     */
    private void updateDetail(ReqWorkResDetailForm reqWorkResDetailForm, HttpServletRequest request) throws Exception
    {
    	ReqWorkResDetailService reqWorkResDetailService = (ReqWorkResDetailService) getBean("reqWorkResDetailService");

        ReqWorkResDetailDTO reqWorkResDetailDTO = reqWorkResDetailForm.getReqWorkResDetailDTO();
        ReqWorkCommonDTO reqWorkCommonDTO = reqWorkResDetailForm.getReqWorkCommonDTO();
        reqWorkResDetailDTO.setEnterBy(getUser(request).getUserId());
        reqWorkCommonDTO.setCompNo(getUser(request).getCompNo());

        reqWorkResDetailService.updateDetail(reqWorkResDetailDTO,reqWorkCommonDTO);

        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: ReqWorkResDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     *
     * @param reqWorkResDetailForm
     * @param request
     */
    private void insertDetail(ReqWorkResDetailForm reqWorkResDetailForm, HttpServletRequest request) throws Exception
    {
    	ReqWorkResDetailService reqWorkResDetailService = (ReqWorkResDetailService) getBean("reqWorkResDetailService");

        ReqWorkResDetailDTO reqWorkResDetailDTO = reqWorkResDetailForm.getReqWorkResDetailDTO();

        ReqWorkCommonDTO reqWorkCommonDTO = reqWorkResDetailForm.getReqWorkCommonDTO();
        reqWorkResDetailDTO.setEnterBy(getUser(request).getUserId());
        reqWorkCommonDTO.setCompNo(getUser(request).getCompNo());
        
        
        reqWorkResDetailService.insertDetail(reqWorkResDetailDTO, reqWorkCommonDTO);

        setAjaxStatus(request);
    }
}