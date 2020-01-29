package dream.work.pm.list.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.form.WorkPmListEquipListForm;
import dream.work.pm.list.service.WorkPmListEquipListService;

/**
 * 예방설비 목록
 * @author  kim21017
 * @version $Id: WorkPmListEquipListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/workPmListEquipList" name="workPmListEquipListForm"
 *                input="/dream/work/pm/list/workPmListEquipList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workPmListRprEquipList" name="workPmListEquipListForm"
 *                input="/dream/work/pm/list/work/workPmListRprEquipList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workPmListRplEquipList" name="workPmListEquipListForm"
 *                input="/dream/work/pm/list/work/workPmListRplEquipList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workPmListGmEquipList" name="workPmListEquipListForm"
 *                input="/dream/work/pm/list/work/workPmListGmEquipList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workPmListRInsEquipList" name="workPmListEquipListForm"
 *                input="/dream/work/pm/list/ins/workPmListRInsEquipList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workPmListDInsEquipList" name="workPmListEquipListForm"
 *                input="/dream/work/pm/list/ins/workPmListDInsEquipList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workPmListEInsEquipList" name="workPmListEquipListForm"
 *                input="/dream/work/pm/list/ins/workPmListEInsEquipList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workPmListInsEquipList" name="workPmListEquipListForm"
 *                input="/dream/work/pm/list/ins/workPmListInsEquipList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workPmListCalEquipList" name="workPmListEquipListForm"
 *                input="/dream/work/pm/list/cal/workPmListCalEquipList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workPmListPtrlRtList" name="workPmListEquipListForm"
 *                input="/dream/work/pm/list/workPmListPtrlRtList.jsp" scope="request"
 *                validate="false"
 */
public class WorkPmListEquipListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int WORK_PM_EQ_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int WORK_PM_EQ_LIST_DELETE 		= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkPmListEquipListForm workPmListEquipListForm = (WorkPmListEquipListForm) form;
        
        switch (workPmListEquipListForm.getStrutsAction())
        {
        
            case WorkPmListEquipListAction.WORK_PM_EQ_LIST_FIND:
                findEqList(request,response, workPmListEquipListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkPmListEquipListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, workPmListEquipListForm.getListId(), workPmListEquipListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkPmListEquipListAction.WORK_PM_EQ_LIST_DELETE:
            	deleteEqList(request,workPmListEquipListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkPmListEquipListAction.BASE_GRID_EXPORT:
            	findEqList(request,response, workPmListEquipListForm, true);
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
     * @version $Id: WorkPmListEquipListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param workPmListEquipListForm
     * @throws Exception
     */
    private void findEqList(HttpServletRequest request,HttpServletResponse response, WorkPmListEquipListForm workPmListEquipListForm, boolean excelExport) throws Exception
    {
        WorkPmListEquipListService workPmListEquipListService = (WorkPmListEquipListService) getBean("workPmListEquipListService");
        MaPmMstrCommonDTO maPmMstrCommonDTO = workPmListEquipListForm.getMaPmMstrCommonDTO();
        
        //Paging
        maPmMstrCommonDTO.setIsLoadMaxCount("Y".equals(workPmListEquipListForm.getIsLoadMaxCount())?true:false);
        maPmMstrCommonDTO.setFirstRow(workPmListEquipListForm.getFirstRow());
        maPmMstrCommonDTO.setOrderBy(workPmListEquipListForm.getOrderBy());
        maPmMstrCommonDTO.setDirection(workPmListEquipListForm.getDirection());
        
        User user = getUser(request);
        List resultList = workPmListEquipListService.findEqList(maPmMstrCommonDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workPmListEquipListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workPmListEquipListService.findTotalCount(maPmMstrCommonDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,workPmListEquipListForm.getListId(),workPmListEquipListForm.getCurrentPageId(), workPmListEquipListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: WorkPmListEquipListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param workPmListEquipListForm
     * @throws Exception
     */
    private void deleteEqList(HttpServletRequest request, WorkPmListEquipListForm workPmListEquipListForm) throws Exception
    {
    	WorkPmListEquipListService workPmListEquipListService = (WorkPmListEquipListService) getBean("workPmListEquipListService");
        
    	String[] deleteRows = workPmListEquipListForm.getDeleteRows();
    
    	workPmListEquipListService.deleteEqList(deleteRows, getUser(request));
    	
    	setAjaxStatus(request);
    }
}