package dream.req.work.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.req.work.dto.ReqWorkReswoDetailDTO;
import dream.req.work.form.ReqWorkReswoDetailForm;
import dream.req.work.service.ReqWorkReswoDetailService;

/**
 * �۾���û�� - ó���۾�
 * @author  kim21017
 * @version $Id: ReqWorkReswoDetailAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/reqWorkReswoDetail" name="reqWorkReswoDetailForm"
 *                input="/dream/req/work/reqWorkReswoDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="reqWorkReswoDetail" path="/dream/req/work/reqWorkReswoDetail.jsp"
 *                        redirect="false"
 */
public class ReqWorkReswoDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int DETAIL_INIT 	= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        ReqWorkReswoDetailForm reqWorkReswoDetailForm = (ReqWorkReswoDetailForm) form;
        String strutsAction = request.getParameter("strutsAction");
        if(strutsAction!="") reqWorkReswoDetailForm.setStrutsAction(Integer.parseInt(strutsAction));
        switch (reqWorkReswoDetailForm.getStrutsAction())
        {
            case ReqWorkReswoDetailAction.DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, reqWorkReswoDetailForm);
                returnActionForward = mapping.getInputForward();
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
     * @version $Id: ReqWorkReswoDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param reqWorkReswoDetailForm
     */
    private void findDetail(HttpServletRequest request, ReqWorkReswoDetailForm reqWorkReswoDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	ReqWorkReswoDetailService reqWorkReswoDetailService = (ReqWorkReswoDetailService)getBean("reqWorkReswoDetailService");

    	// ��û�� id
        String woReqId = reqWorkReswoDetailForm.getMaWoReqCommonDTO().getWoReqId();
        // ó������ id
        String woReqResId = reqWorkReswoDetailForm.getMaWoReqResListDTO().getWoReqResId();
        if("".equals(woReqResId)){
        	woReqResId = reqWorkReswoDetailForm.getReqWorkResListDTO().getWoReqResId();
        }
        // ��ȸ�� �� �κ�
        ReqWorkReswoDetailDTO reqWorkReswoDetailDTO = reqWorkReswoDetailService.findDetail(woReqId, woReqResId, getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
        reqWorkReswoDetailForm.setReqWorkReswoDetailDTO(reqWorkReswoDetailDTO);
    }
    
}