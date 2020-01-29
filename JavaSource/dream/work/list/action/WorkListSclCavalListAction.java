package dream.work.list.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.WorkListSclCavalListDTO;
import dream.work.list.form.WorkListSclCavalListForm;
import dream.work.list.service.WorkListSclCavalListService;

/**
 * 작업상세  - 검교정(저울) - 측정값 목록
 * @author  kim21017
 * @version $Id: WorkListSclCavalListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/workListSclCavalList" name="workListSclCavalListForm"
 *                input="/dream/work/list/pmc/workListSclCavalList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workListSclCavalList" path="/dream/work/list/pmc/workListSclCavalList.jsp"
 *                        redirect="false"
 */
public class WorkListSclCavalListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int WORK_LIST_SCL_CAVAL_LIST_FIND 			= 1001;
    /** 삭제 */
    public static final int WORK_LIST_SCL_CAVAL_LIST_UPDATE 		= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkListSclCavalListForm workListSclCavalListForm = (WorkListSclCavalListForm) form;
        switch (workListSclCavalListForm.getStrutsAction())
        {
            case WorkListSclCavalListAction.WORK_LIST_SCL_CAVAL_LIST_FIND:
                findCavalList(request,response, workListSclCavalListForm);
                returnActionForward = mapping.getInputForward();
                break;
            case WorkListSclCavalListAction.WORK_LIST_SCL_CAVAL_LIST_UPDATE:
            	mergeCavalList(request,workListSclCavalListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
            	findCavalList(request,response, workListSclCavalListForm);
                returnActionForward = mapping.getInputForward();
                break;
        }
        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: WorkListSclCavalListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param workListSclCavalListForm
     * @throws Exception
     */
    private void findCavalList(HttpServletRequest request,HttpServletResponse response, WorkListSclCavalListForm workListSclCavalListForm) throws Exception
    {
        WorkListSclCavalListService workListSclCavalListService = (WorkListSclCavalListService) getBean("workListSclCavalListService");
        MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = workListSclCavalListForm.getMaWoResultMstrCommonDTO();
        WorkListSclCavalListDTO workListSclCavalListDTO = workListSclCavalListService.findCavalList(maWoResultMstrCommonDTO, getUser(request));
        workListSclCavalListForm.setWorkListSclCavalListDTO(workListSclCavalListDTO);
    }
    /**
     * merge
     * @author  kim2107
     * @version $Id: WorkListSclCavalListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param workListSclCavalListForm
     * @throws Exception
     */
    private void mergeCavalList(HttpServletRequest request, WorkListSclCavalListForm workListSclCavalListForm) throws Exception
    {
    	WorkListSclCavalListService workListSclCavalListService = (WorkListSclCavalListService) getBean("workListSclCavalListService");
    	MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = workListSclCavalListForm.getMaWoResultMstrCommonDTO();
        WorkListSclCavalListDTO workListSclCavalListDTO = workListSclCavalListForm.getWorkListSclCavalListDTO();
    	workListSclCavalListService.mergeCavalList(maWoResultMstrCommonDTO, workListSclCavalListDTO, getUser(request));
    	setAjaxStatus(request);
    }
}