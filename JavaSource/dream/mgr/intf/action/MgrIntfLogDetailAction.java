package dream.mgr.intf.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.mgr.intf.dto.MgrIntfLogDetailDTO;
import dream.mgr.intf.dto.MgrIntfLogListDTO;
import dream.mgr.intf.form.MgrIntfLogDetailForm;
import dream.mgr.intf.service.MgrIntfLogDetailService;

/**
 * Interface Log Page - Detail Action
 * 
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/mgrIntfLogDetail" name="mgrIntfLogDetailForm"
 *                input="/dream/mgr/intf/mgrIntfLogDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrIntfLogDetail" path="/dream/mgr/intf/mgrIntfLogDetail.jsp"
 *                        redirect="false"
 */
public class MgrIntfLogDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int DETAIL_INIT 		= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MgrIntfLogDetailForm mgrIntfLogDetailForm = (MgrIntfLogDetailForm) form;
        
        switch (mgrIntfLogDetailForm.getStrutsAction())
        {
            case MgrIntfLogDetailAction.DETAIL_INIT:
                this.findDetail(request, response, mgrIntfLogDetailForm);
                returnActionForward = mapping.findForward("mgrIntfLogDetail");
                break;
            default:
                returnActionForward = mapping.findForward("mgrIntfLogDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * FIND DETAIL
     * @param request
     * @param response
     * @param mgrIntfLogDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, MgrIntfLogDetailForm mgrIntfLogDetailForm) throws Exception 
    {   
    	MgrIntfLogDetailService mgrIntfLogDetailService = (MgrIntfLogDetailService)getBean("mgrIntfLogDetailService");
    	
    	MgrIntfLogListDTO mgrIntfLogListDTO = mgrIntfLogDetailForm.getMgrIntfLogListDTO(); 

    	User user = getUser(request);
    	
    	MgrIntfLogDetailDTO mgrIntfLogDetailDTO = mgrIntfLogDetailService.findDetail(mgrIntfLogListDTO, user);
    	mgrIntfLogDetailForm.setMgrIntfLogDetailDTO(mgrIntfLogDetailDTO);
    }

}