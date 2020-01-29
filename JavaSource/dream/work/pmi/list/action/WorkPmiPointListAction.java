package dream.work.pmi.list.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.fins.org.json.JSONObject;

import common.bean.User;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.work.pmi.list.dto.WorkPmiCommonDTO;
import dream.work.pmi.list.dto.WorkPmiPointListDTO;
import dream.work.pmi.list.form.WorkPmiPointListForm;
import dream.work.pmi.list.service.WorkPmiPointListService;

/**
 * 점검작업 점검
 * @author  kim21017
 * @version $Id: WorkPmiPointListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/workPmiPointList" name="workPmiPointListForm"
 *                input="/dream/work/pmi/list/workPmiPointList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workPmiPointList" path="/dream/work/pmi/list/workPmiPointList.jsp"
 *                        redirect="false"
 */
public class WorkPmiPointListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int WORK_PMI_POINT_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int WORK_PMI_POINT_LIST_DELETE 		= 7002;
    
    public static final int WORK_PMI_POINT_LIST_SAVE 		= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkPmiPointListForm workPmiPointListForm = (WorkPmiPointListForm) form;
        String strutsAction = request.getParameter("strutsAction");
        if(strutsAction!="") workPmiPointListForm.setStrutsAction(Integer.parseInt(strutsAction));
        
        super.updateAudit(workPmiPointListForm.getWorkPmiPointListDTO().getAuditKey()==""?workPmiPointListForm.getWorkPmiPointListDTO().getAuditKey():workPmiPointListForm.getWorkPmiPointListDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (workPmiPointListForm.getStrutsAction())
        {
        
            case WorkPmiPointListAction.WORK_PMI_POINT_LIST_FIND:
                findPointList(request,response, workPmiPointListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkPmiPointListAction.WORK_PMI_POINT_LIST_SAVE:
                savePointList(request,response, workPmiPointListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkPmiPointListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, workPmiPointListForm.getListId(), workPmiPointListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkPmiPointListAction.WORK_PMI_POINT_LIST_DELETE:
            	deletePointList(request,workPmiPointListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkPmiPointListAction.BASE_GRID_EXPORT:
            	findPointList(request,response, workPmiPointListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    private void savePointList(HttpServletRequest request, HttpServletResponse response, WorkPmiPointListForm workPmiPointListForm) throws Exception 
    {
    	WorkPmiPointListService workPmiPointListService = (WorkPmiPointListService) getBean("workPmiPointListService");
        
        List<Map> gridList = CommonUtil.setGridMap(request);

        workPmiPointListService.savePointList(gridList, getUser(request));
        
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("status", "ok");
        response.getWriter().print(jsonObj.toString());
	}

	/**
     * grid find
     * @author  kim2107
     * @version $Id: WorkPmiPointListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param workPmiPointListForm
     * @throws Exception
     */
    private void findPointList(HttpServletRequest request,HttpServletResponse response, WorkPmiPointListForm workPmiPointListForm, boolean excelExport) throws Exception
    {
        WorkPmiPointListService workPmiPointListService = (WorkPmiPointListService) getBean("workPmiPointListService");
        WorkPmiCommonDTO workPmiCommonDTO = workPmiPointListForm.getWorkPmiCommonDTO();
        WorkPmiPointListDTO workPmiPointListDTO = workPmiPointListForm.getWorkPmiPointListDTO();
        
        //Paging
        workPmiCommonDTO.setIsLoadMaxCount("Y".equals(workPmiPointListForm.getIsLoadMaxCount())?true:false);
        workPmiCommonDTO.setFirstRow(workPmiPointListForm.getFirstRow());
        workPmiCommonDTO.setOrderBy(workPmiPointListForm.getOrderBy());
        workPmiCommonDTO.setDirection(workPmiPointListForm.getDirection());

        User user = getUser(request);
        List resultList = workPmiPointListService.findPointList(workPmiCommonDTO,workPmiPointListDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workPmiPointListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workPmiPointListService.findTotalCount(workPmiCommonDTO,workPmiPointListDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,workPmiPointListForm.getListId(),workPmiPointListForm.getCurrentPageId(), workPmiPointListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: WorkPmiPointListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param workPmiPointListForm
     * @throws Exception
     */
    private void deletePointList(HttpServletRequest request, WorkPmiPointListForm workPmiPointListForm) throws Exception
    {
    	WorkPmiPointListService workPmiPointListService = (WorkPmiPointListService) getBean("workPmiPointListService");
        
    	String[] deleteRows = workPmiPointListForm.getDeleteRows();
    
    	workPmiPointListService.deletePointList(deleteRows, getUser(request));
    	
    	setAjaxStatus(request);
    }
}