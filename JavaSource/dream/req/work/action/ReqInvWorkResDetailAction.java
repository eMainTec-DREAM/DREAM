package dream.req.work.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.req.work.dto.ReqInvWorkResDetailDTO;
import dream.req.work.dto.ReqWorkResListDTO;
import dream.req.work.form.ReqInvWorkResDetailForm;
import dream.req.work.service.ReqInvWorkResDetailService;

/**
 * �۾���û��(����) - ó���۾�
 * @author  js.lee
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/reqInvWorkResDetail" name="reqInvWorkResDetailForm"
 *                input="/dream/req/work/reqInvWorkResDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="reqInvWorkResDetail" path="/dream/req/work/reqInvWorkResDetail.jsp"
 *                        redirect="false"
 */
public class ReqInvWorkResDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int DETAIL_INIT 	= 1001;

    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        ReqInvWorkResDetailForm reqInvWorkResDetailForm = (ReqInvWorkResDetailForm) form;

        switch (reqInvWorkResDetailForm.getStrutsAction())
        {
            case ReqInvWorkResDetailAction.DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, reqInvWorkResDetailForm);
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
     * @author js.lee
     * @version $Id: $
     * @since   1.0
     *
     * @param request
     * @param reqWorkResDetailForm
     */
    private void findDetail(HttpServletRequest request, ReqInvWorkResDetailForm reqInvWorkResDetailForm)throws Exception
    {
        // Service ��ü ����
    	ReqInvWorkResDetailService reqInvWorkResDetailService = (ReqInvWorkResDetailService)getBean("reqInvWorkResDetailService");
    	
    	ReqWorkResListDTO reqWorkResListDTO = reqInvWorkResDetailForm.getReqWorkResListDTO();

        ReqInvWorkResDetailDTO reqInvWorkResDetailDTO = reqInvWorkResDetailService.findDetail(reqWorkResListDTO, getUser(request));

        // ��ȸ�� ��  �����Ѵ�.
        reqInvWorkResDetailForm.setReqInvWorkResDetailDTO(reqInvWorkResDetailDTO);
    }
}