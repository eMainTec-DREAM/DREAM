package dream.work.pm.list.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.WorkPmMsTimeUInsDetailDTO;
import dream.work.pm.list.dto.WorkPmMsTimeUInsListDTO;
import dream.work.pm.list.form.WorkPmMsTimeUInsListForm;
import dream.work.pm.list.service.WorkPmMsTimeUInsListService;

/**
 * 작업시간 List Action
 * @author  js.lee
 * @version $Id: Exp $
 * @since   1.0
 * @struts:action path="/workPmMsTimeUInsList" name="workPmMsTimeUInsListForm"
 *                input="/dream/work/pm/list/ins/workPmMsTimeUInsList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workPmMsTimeUInsList" path="/dream/work/pm/list/ins/workPmMsTimeUInsList.jsp"
 *                        redirect="false"
 */
public class WorkPmMsTimeUInsListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int PM_MS_TIME_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int PM_MS_TIME_LIST_DELETE 		= 7002;
    /** 입력 */
    public static final int PM_MS_TIME_LIST_INPUT		= 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkPmMsTimeUInsListForm workPmMsTimeUInsListForm = (WorkPmMsTimeUInsListForm) form;
        
        switch (workPmMsTimeUInsListForm.getStrutsAction())
        {
        
            case WorkPmMsTimeUInsListAction.PM_MS_TIME_LIST_FIND:
                findMsTimeList(request,response, workPmMsTimeUInsListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkPmMsTimeUInsListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, workPmMsTimeUInsListForm.getListId(), workPmMsTimeUInsListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkPmMsTimeUInsListAction.PM_MS_TIME_LIST_DELETE:
            	deleteMsTimeList(request,workPmMsTimeUInsListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkPmMsTimeUInsListAction.PM_MS_TIME_LIST_INPUT:
                inputMsTimeList(request,workPmMsTimeUInsListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case WorkPmMsTimeUInsListAction.BASE_GRID_EXPORT:
            	findMsTimeList(request,response, workPmMsTimeUInsListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: WorkPmMsTimeUInsListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param workPmMsTimeUInsListForm
     * @throws Exception
     */
    private void findMsTimeList(HttpServletRequest request,HttpServletResponse response, WorkPmMsTimeUInsListForm workPmMsTimeUInsListForm, boolean excelExport) throws Exception
    {
        WorkPmMsTimeUInsListService workPmMsTimeUInsListService = (WorkPmMsTimeUInsListService) getBean("workPmMsTimeUInsListService");
        MaPmMstrCommonDTO maPmMstrCommonDTO = workPmMsTimeUInsListForm.getMaPmMstrCommonDTO();
        WorkPmMsTimeUInsListDTO workPmMsTimeUInsListDTO = workPmMsTimeUInsListForm.getWorkPmMsTimeUInsListDTO();
        
        //Paging
        workPmMsTimeUInsListDTO.setIsLoadMaxCount("Y".equals(workPmMsTimeUInsListForm.getIsLoadMaxCount())?true:false);
        workPmMsTimeUInsListDTO.setFirstRow(workPmMsTimeUInsListForm.getFirstRow());
        workPmMsTimeUInsListDTO.setOrderBy(workPmMsTimeUInsListForm.getOrderBy());
        workPmMsTimeUInsListDTO.setDirection(workPmMsTimeUInsListForm.getDirection());
        
        User user = getUser(request);
        List resultList = workPmMsTimeUInsListService.findMsTimeList(maPmMstrCommonDTO, workPmMsTimeUInsListDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workPmMsTimeUInsListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workPmMsTimeUInsListService.findTotalCount(maPmMstrCommonDTO,workPmMsTimeUInsListDTO,user);
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, workPmMsTimeUInsListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: WorkPmMsTimeUInsListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param workPmMsTimeUInsListForm
     * @throws Exception
     */
    private void deleteMsTimeList(HttpServletRequest request, WorkPmMsTimeUInsListForm workPmMsTimeUInsListForm) throws Exception
    {
    	WorkPmMsTimeUInsListService workPmMsTimeUInsListService = (WorkPmMsTimeUInsListService) getBean("workPmMsTimeUInsListService");
        
    	String[] deleteRows = workPmMsTimeUInsListForm.getDeleteRows();
    
    	workPmMsTimeUInsListService.deleteMsTimeList(deleteRows, getUser(request));
    	
    	setAjaxStatus(request);
    }
    
    /**
     * input
     * @author  js.lee
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param workPmMsTimeUInsListForm
     * @throws Exception
     */
    private void inputMsTimeList(HttpServletRequest request, WorkPmMsTimeUInsListForm workPmMsTimeUInsListForm) throws Exception
    {
    	WorkPmMsTimeUInsListService workPmMsTimeUInsListService = (WorkPmMsTimeUInsListService) getBean("workPmMsTimeUInsListService");
        
    	MaPmMstrCommonDTO maPmMstrCommonDTO = workPmMsTimeUInsListForm.getMaPmMstrCommonDTO();
    	WorkPmMsTimeUInsDetailDTO workPmMsTimeUInsDetailDTO = workPmMsTimeUInsListForm.getWorkPmMsTimeUInsDetailDTO();
    	User user = getUser(request);

    	workPmMsTimeUInsListService.inputMsTimeList(workPmMsTimeUInsDetailDTO, maPmMstrCommonDTO, user);
        setAjaxStatus(request);
    }
}