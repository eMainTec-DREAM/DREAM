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
 * 작업요청서 - 처리작업
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
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
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
                // 페이지 조회
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
     * 상세 조회
     * @author kim2107
     * @version $Id: ReqWorkReswoDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param reqWorkReswoDetailForm
     */
    private void findDetail(HttpServletRequest request, ReqWorkReswoDetailForm reqWorkReswoDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	ReqWorkReswoDetailService reqWorkReswoDetailService = (ReqWorkReswoDetailService)getBean("reqWorkReswoDetailService");

    	// 요청서 id
        String woReqId = reqWorkReswoDetailForm.getMaWoReqCommonDTO().getWoReqId();
        // 처리사항 id
        String woReqResId = reqWorkReswoDetailForm.getMaWoReqResListDTO().getWoReqResId();
        if("".equals(woReqResId)){
        	woReqResId = reqWorkReswoDetailForm.getReqWorkResListDTO().getWoReqResId();
        }
        // 조회된 상세 부분
        ReqWorkReswoDetailDTO reqWorkReswoDetailDTO = reqWorkReswoDetailService.findDetail(woReqId, woReqResId, getUser(request));
        
        // 조회된 상세  셋팅한다.
        reqWorkReswoDetailForm.setReqWorkReswoDetailDTO(reqWorkReswoDetailDTO);
    }
    
}