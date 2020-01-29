package dream.work.pm.list.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.consult.comp.cdsys.action.CdSysCodeParentLovAction;
import dream.consult.comp.cdsys.form.CdSysCodeParentLovForm;
import dream.work.pm.list.dto.WorkPmListMsTimeLovDTO;
import dream.work.pm.list.form.WorkPmListMsTimeLovForm;
import dream.work.pm.list.service.WorkPmListMsTimeLovService;

/**
 * 작업요청유형 선택팝업
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 * 
 * @struts:action path="/workPmListMsTimeLov" name="workPmListMsTimeLovForm"
 *                input="/dream/work/pm/list/workPmListMsTimeLov.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workPmListMsTimeLov" path="/dream/work/pm/list/workPmListMsTimeLov.jsp"
 *                        redirect="false"
 */
public class WorkPmListMsTimeLovAction extends AuthAction
{
    public static final int MS_TIME_SELECT_DEFAULT		= 1001;
    /** 조회 */
    public static final int MS_TIME_SELECT_FIND			= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
    	WorkPmListMsTimeLovForm workPmListMsTimeLovForm = (WorkPmListMsTimeLovForm)form;
        ActionForward returnActionForward = null;
        
        switch (workPmListMsTimeLovForm.getStrutsAction())
        {
        	case WorkPmListMsTimeLovAction.MS_TIME_SELECT_DEFAULT :
        		returnActionForward = mapping.findForward("workPmListMsTimeLov");
        		break;
            case WorkPmListMsTimeLovAction.BASE_SET_HEADER:
                setHeader(request, response, workPmListMsTimeLovForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkPmListMsTimeLovAction.MS_TIME_SELECT_FIND :
                findList(request, workPmListMsTimeLovForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                returnActionForward = mapping.findForward("workPmListMsTimeLov");
                break;
        }
        
        return returnActionForward;
    }
    
    private void setHeader(HttpServletRequest request, HttpServletResponse response, WorkPmListMsTimeLovForm workPmListMsTimeLovForm) throws IOException
    {
        super.setHeader(request, response, workPmListMsTimeLovForm.getListId(), workPmListMsTimeLovForm.getCurrentPageId()); 
    }

    /**
     * 작업요청유형 리스트 
     * @author  js.lee
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param workPmListMsTimeLovForm
     */
    private void findList(HttpServletRequest request,
    		WorkPmListMsTimeLovForm workPmListMsTimeLovForm, HttpServletResponse response) throws IOException
    {
        WorkPmListMsTimeLovService workPmListMsTimeLovService = (WorkPmListMsTimeLovService)getBean("workPmListMsTimeLovService");
        WorkPmListMsTimeLovDTO workPmListMsTimeLovDTO = workPmListMsTimeLovForm.getWorkPmListMsTimeLovDTO();
        List resultList = workPmListMsTimeLovService.findList(getUser(request),workPmListMsTimeLovDTO);
        
        super.makeJsonResult(resultList, request, response, workPmListMsTimeLovForm.getListId());
    }
}