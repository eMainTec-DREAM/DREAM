package dream.work.pm.list.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.work.pm.list.dto.WorkPmiCInsCommonDTO;
import dream.work.pm.list.dto.WorkPmiCInsPointDetailDTO;
import dream.work.pm.list.form.WorkPmiCInsPointDetailForm;
import dream.work.pm.list.service.WorkPmiCInsPointDetailService;

/**
 * WorkPmiCInsPoint Page - Detail Action
 * 
 * @author youngjoo38
 * @version $Id: WorkPmiCInsPointDetailAction.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @struts:action path="/workPmiCInsPointDetail" name="workPmiCInsPointDetailForm"
 *                input="/dream/work/pmi/list/workPmiCInsPointDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workPmiCInsPointDetail" path="/dream/work/pmi/list/workPmiCInsPointDetail.jsp"
 *                        redirect="false"
 */
public class WorkPmiCInsPointDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int DETAIL_INIT                 = 1001;
    /** ���� */
    public static final int DETAIL_INPUT                = 1002;
    /** ���� */
    public static final int DETAIL_UPDATE               = 1003;
    /** eqAsmbId �������� */
    public static final int GET_ID                       = 1004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkPmiCInsPointDetailForm workPmiCInsPointDetailForm = (WorkPmiCInsPointDetailForm) form;
        
        switch (workPmiCInsPointDetailForm.getStrutsAction())
        {
            case WorkPmiCInsPointDetailAction.DETAIL_INIT:
                this.findDetail(request, response, workPmiCInsPointDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case WorkPmiCInsPointDetailAction.DETAIL_INPUT:
                insertDetail(request, response, workPmiCInsPointDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case WorkPmiCInsPointDetailAction.DETAIL_UPDATE:
                updateDetail(request, response, workPmiCInsPointDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case WorkPmiCInsPointDetailAction.GET_ID:
                getId(request, response, workPmiCInsPointDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * FIND DETAIL
     * @param request
     * @param response
     * @param workPmiCInsPointDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, WorkPmiCInsPointDetailForm workPmiCInsPointDetailForm) throws Exception 
    {   
        WorkPmiCInsPointDetailService workPmiCInsPointDetailService = (WorkPmiCInsPointDetailService)getBean("workPmiCInsPointDetailService");
        
        WorkPmiCInsCommonDTO workPmiCInsCommonDTO = workPmiCInsPointDetailForm.getWorkPmiCInsCommonDTO(); 

        User user = getUser(request);
        WorkPmiCInsPointDetailDTO workPmiCInsPointDetailDTO = workPmiCInsPointDetailService.findDetail(workPmiCInsCommonDTO, user);
        workPmiCInsPointDetailForm.setWorkPmiCInsPointDetailDTO(workPmiCInsPointDetailDTO);
    }

    /**
     * INSERT DETAIL
     * @param request
     * @param response
     * @param workPmiCInsPointDetailForm
     * @throws Exception
     */
    private void insertDetail(HttpServletRequest request, HttpServletResponse response, WorkPmiCInsPointDetailForm workPmiCInsPointDetailForm) throws Exception
    {
        WorkPmiCInsPointDetailService workPmiCInsPointDetailService = (WorkPmiCInsPointDetailService)getBean("workPmiCInsPointDetailService");
        WorkPmiCInsPointDetailDTO  workPmiCInsPointDetailDTO = workPmiCInsPointDetailForm.getWorkPmiCInsPointDetailDTO(); 
        
        User user = getUser(request);
        workPmiCInsPointDetailService.insertDetail(workPmiCInsPointDetailDTO, user);
        setAjaxStatus(request);
    }

    /**
     * UPDATE DETAIL
     * @param request
     * @param response
     * @param workPmiCInsPointDetailForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, WorkPmiCInsPointDetailForm workPmiCInsPointDetailForm) throws Exception
    {
        WorkPmiCInsPointDetailService workPmiCInsPointDetailService = (WorkPmiCInsPointDetailService)getBean("workPmiCInsPointDetailService");
        WorkPmiCInsPointDetailDTO  workPmiCInsPointDetailDTO = workPmiCInsPointDetailForm.getWorkPmiCInsPointDetailDTO();
        
        User user = getUser(request);
        workPmiCInsPointDetailService.updateDetail(workPmiCInsPointDetailDTO, user);
        setAjaxStatus(request);
    }
    
    /**
     * GET ID
     * @param request
     * @param response
     * @param workPmiCInsPointDetailForm
     * @throws Exception
     */
    private void getId(HttpServletRequest request, HttpServletResponse response, WorkPmiCInsPointDetailForm workPmiCInsPointDetailForm) throws Exception
    {
        WorkPmiCInsPointDetailService workPmiCInsPointDetailService = (WorkPmiCInsPointDetailService)getBean("workPmiCInsPointDetailService");
        WorkPmiCInsPointDetailDTO  workPmiCInsPointDetailDTO = workPmiCInsPointDetailForm.getWorkPmiCInsPointDetailDTO();
        
        User user = getUser(request);
        workPmiCInsPointDetailService.getId(workPmiCInsPointDetailDTO, user);
        setAjaxStatus(request);
    }
}
