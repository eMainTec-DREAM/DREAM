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
 * 작업요청서(투자) - 처리작업
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
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
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
                // 페이지 조회
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
     * 상세 조회
     * @author js.lee
     * @version $Id: $
     * @since   1.0
     *
     * @param request
     * @param reqWorkResDetailForm
     */
    private void findDetail(HttpServletRequest request, ReqInvWorkResDetailForm reqInvWorkResDetailForm)throws Exception
    {
        // Service 객체 생성
    	ReqInvWorkResDetailService reqInvWorkResDetailService = (ReqInvWorkResDetailService)getBean("reqInvWorkResDetailService");
    	
    	ReqWorkResListDTO reqWorkResListDTO = reqInvWorkResDetailForm.getReqWorkResListDTO();

        ReqInvWorkResDetailDTO reqInvWorkResDetailDTO = reqInvWorkResDetailService.findDetail(reqWorkResListDTO, getUser(request));

        // 조회된 상세  셋팅한다.
        reqInvWorkResDetailForm.setReqInvWorkResDetailDTO(reqInvWorkResDetailDTO);
    }
}