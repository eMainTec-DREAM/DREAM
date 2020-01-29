package dream.mgr.message.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import common.util.MailUtil;
import dream.mgr.message.dto.MgrMessageTransCommonDTO;
import dream.mgr.message.dto.MgrMessageTransDetailDTO;
import dream.mgr.message.form.MgrMessageTransDetailForm;
import dream.mgr.message.service.MgrMessageTransDetailService;

/**
 * Message Transfer Page - Detail Action
 * 
 * @author syyang
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/mgrMessageTransDetail" name="mgrMessageTransDetailForm"
 *                input="/dream/mgr/message/mgrMessageTransDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/sendErrorMail" name="mgrMessageTransDetailForm"
 *                scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrMessageTransDetail" path="/dream/mgr/message/mgrMessageTransDetail.jsp"
 *                        redirect="false"
 */
public class MgrMessageTransDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int DETAIL_INIT 		= 1001;
    /** 담당자에게 에러 메일링 */
    public static final int SEND_ERROR 		    = 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MgrMessageTransDetailForm mgrMessageTransDetailForm = (MgrMessageTransDetailForm) form;
        
        switch (mgrMessageTransDetailForm.getStrutsAction())
        {
            case MgrMessageTransDetailAction.DETAIL_INIT:
                this.findDetail(request, response, mgrMessageTransDetailForm);
                returnActionForward = mapping.findForward("mgrMessageTransDetail");
                break;
            case MgrMessageTransDetailAction.SEND_ERROR:
                this.sendErrorMail(request, response, mgrMessageTransDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            default:
                returnActionForward = mapping.findForward("mgrMessageTransDetail");
                break;
        }

        return returnActionForward;
    }
    
    private void sendErrorMail(HttpServletRequest request,
            HttpServletResponse response, MgrMessageTransDetailForm mgrMessageTransDetailForm) throws Exception
    {
        String result = result = MailUtil.sendMail("ERR10", mgrMessageTransDetailForm.getErrorLogId(), getUser(request));
        
        setAjaxDesc(request, result);
    }

    /**
     * FIND DETAIL
     * @param request
     * @param response
     * @param mgrMessageTransDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, MgrMessageTransDetailForm mgrMessageTransDetailForm) throws Exception 
    {   
    	MgrMessageTransDetailService mgrMessageTransDetailService = (MgrMessageTransDetailService)getBean("mgrMessageTransDetailService");
    	
    	MgrMessageTransCommonDTO mgrMessageTransCommonDTO = mgrMessageTransDetailForm.getMgrMessageTransCommonDTO(); 

    	User user = getUser(request);
    	
    	MgrMessageTransDetailDTO mgrMessageTransDetailDTO = mgrMessageTransDetailService.findDetail(mgrMessageTransCommonDTO, user);
    	mgrMessageTransDetailForm.setMgrMessageTransDetailDTO(mgrMessageTransDetailDTO);
    }

}