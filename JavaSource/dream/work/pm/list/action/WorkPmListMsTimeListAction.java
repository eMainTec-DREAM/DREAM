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
import dream.work.pm.list.dto.WorkPmListMsTimeDetailDTO;
import dream.work.pm.list.dto.WorkPmListMsTimeListDTO;
import dream.work.pm.list.form.WorkPmListMsTimeListForm;
import dream.work.pm.list.service.WorkPmListMsTimeListService;

/**
 * 작업시간 List Action
 * @author  js.lee
 * @version $Id: Exp $
 * @since   1.0
 * @struts:action path="/workPmListMsTimeList" name="workPmListMsTimeListForm"
 *                input="/dream/work/pm/list/workPmListMsTimeList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workPmListMsTimeList" path="/dream/work/pm/list/workPmListMsTimeList.jsp"
 *                        redirect="false"
 */
public class WorkPmListMsTimeListAction extends AuthAction
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
        WorkPmListMsTimeListForm workPmListMsTimeListForm = (WorkPmListMsTimeListForm) form;
        
        switch (workPmListMsTimeListForm.getStrutsAction())
        {
        
            case WorkPmListMsTimeListAction.PM_MS_TIME_LIST_FIND:
                findMsTimeList(request,response, workPmListMsTimeListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkPmListMsTimeListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, workPmListMsTimeListForm.getListId(), workPmListMsTimeListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkPmListMsTimeListAction.PM_MS_TIME_LIST_DELETE:
            	deleteMsTimeList(request,workPmListMsTimeListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkPmListMsTimeListAction.PM_MS_TIME_LIST_INPUT:
                inputMsTimeList(request,workPmListMsTimeListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case WorkPmListMsTimeListAction.BASE_GRID_EXPORT:
            	findMsTimeList(request,response, workPmListMsTimeListForm, true);
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
     * @version $Id: WorkPmListMsTimeListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param workPmListMsTimeListForm
     * @throws Exception
     */
    private void findMsTimeList(HttpServletRequest request,HttpServletResponse response, WorkPmListMsTimeListForm workPmListMsTimeListForm, boolean excelExport) throws Exception
    {
        WorkPmListMsTimeListService workPmListMsTimeListService = (WorkPmListMsTimeListService) getBean("workPmListMsTimeListService");
        MaPmMstrCommonDTO maPmMstrCommonDTO = workPmListMsTimeListForm.getMaPmMstrCommonDTO();
        WorkPmListMsTimeListDTO workPmListMsTimeListDTO = workPmListMsTimeListForm.getWorkPmListMsTimeListDTO();
        
        //Paging
        workPmListMsTimeListDTO.setIsLoadMaxCount("Y".equals(workPmListMsTimeListForm.getIsLoadMaxCount())?true:false);
        workPmListMsTimeListDTO.setFirstRow(workPmListMsTimeListForm.getFirstRow());
        workPmListMsTimeListDTO.setOrderBy(workPmListMsTimeListForm.getOrderBy());
        workPmListMsTimeListDTO.setDirection(workPmListMsTimeListForm.getDirection());
        
        User user = getUser(request);
        List resultList = workPmListMsTimeListService.findMsTimeList(maPmMstrCommonDTO, workPmListMsTimeListDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workPmListMsTimeListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workPmListMsTimeListService.findTotalCount(maPmMstrCommonDTO,workPmListMsTimeListDTO,user);
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, workPmListMsTimeListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: WorkPmListMsTimeListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param workPmListMsTimeListForm
     * @throws Exception
     */
    private void deleteMsTimeList(HttpServletRequest request, WorkPmListMsTimeListForm workPmListMsTimeListForm) throws Exception
    {
    	WorkPmListMsTimeListService workPmListMsTimeListService = (WorkPmListMsTimeListService) getBean("workPmListMsTimeListService");
        
    	String[] deleteRows = workPmListMsTimeListForm.getDeleteRows();
    
    	workPmListMsTimeListService.deleteMsTimeList(deleteRows, getUser(request));
    	
    	setAjaxStatus(request);
    }
    
    /**
     * input
     * @author  js.lee
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param workPmListMsTimeListForm
     * @throws Exception
     */
    private void inputMsTimeList(HttpServletRequest request, WorkPmListMsTimeListForm workPmListMsTimeListForm) throws Exception
    {
    	WorkPmListMsTimeListService workPmListMsTimeListService = (WorkPmListMsTimeListService) getBean("workPmListMsTimeListService");
        
    	MaPmMstrCommonDTO maPmMstrCommonDTO = workPmListMsTimeListForm.getMaPmMstrCommonDTO();
    	WorkPmListMsTimeDetailDTO workPmListMsTimeDetailDTO = workPmListMsTimeListForm.getWorkPmListMsTimeDetailDTO();
    	User user = getUser(request);

    	workPmListMsTimeListService.inputMsTimeList(workPmListMsTimeDetailDTO, maPmMstrCommonDTO, user);
        setAjaxStatus(request);
    }
}