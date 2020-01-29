package dream.work.list.action;

import java.io.IOException;
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
import dream.work.list.dto.WorkListPointListDTO;
import dream.work.list.dto.WorkListPtrlResultCommonDTO;
import dream.work.list.form.WorkListPointListForm;
import dream.work.list.service.WorkListPointListService;

/**
 * WorkListPoint Page - List Action
 * 
 * @author youngjoo38
 * @version $Id: WorkListPointListAction.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @struts:action path="/workListPointList" name="workListPointListForm"
 *                input="/dream/work/list/workListPointList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workListPointList" path="/dream/work/list/workListPointList.jsp"
 *                        redirect="false"
 */
public class WorkListPointListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND       = 1001;
    /** 삭제 */
    public static final int LIST_DELETE     = 7002;
    /** LIST Edit 저장  */
    public static final int LIST_SAVE 		= 6003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        WorkListPointListForm workListPointListForm = (WorkListPointListForm) form;
        
        switch (workListPointListForm.getStrutsAction())
        {
            case WorkListPointListAction.BASE_SET_HEADER:
                setHeader(request, response, workListPointListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkListPointListAction.LIST_FIND:
                findList(request, response, workListPointListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case WorkListPointListAction.LIST_DELETE:
                deleteList(request, workListPointListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;     
            case WorkListPointListAction.LIST_SAVE:
                saveList(request,response, workListPointListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;  
            case WorkListPointListAction.BASE_GRID_EXPORT:
                findList(request, response, workListPointListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("workListPointList");
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, WorkListPointListForm workListPointListForm) throws IOException
    {
        super.setHeader(request, response, workListPointListForm.getListId(), workListPointListForm.getCurrentPageId()); 
    }
   
    /**
     * FIND LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param workListPointListForm
     */
    
    private void findList(HttpServletRequest request, HttpServletResponse response, WorkListPointListForm workListPointListForm, boolean excelExport) throws Exception
    {
        WorkListPointListService workListPointListService = (WorkListPointListService) getBean("workListPointListService");
        WorkListPointListDTO workListPointListDTO = workListPointListForm.getWorkListPointListDTO();
        WorkListPtrlResultCommonDTO workListPtrlResultCommonDTO = workListPointListForm.getWorkListPtrlResultCommonDTO();
      
        //Paging
        workListPointListDTO.setIsLoadMaxCount("Y".equals(workListPointListForm.getIsLoadMaxCount())?true:false);
        workListPointListDTO.setFirstRow(workListPointListForm.getFirstRow());
        workListPointListDTO.setOrderBy(workListPointListForm.getOrderBy());
        workListPointListDTO.setDirection(workListPointListForm.getDirection());
        
        User user = getUser(request);
        List resultList = workListPointListService.findList(workListPtrlResultCommonDTO, workListPointListDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workListPointListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workListPointListService.findTotalCount(workListPtrlResultCommonDTO,workListPointListDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,workListPointListForm.getListId(),workListPointListForm.getCurrentPageId(), workListPointListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * DELETE LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param workListPointListForm
     */
    private void deleteList(HttpServletRequest request, WorkListPointListForm workListPointListForm) throws Exception
    {
        WorkListPointListService workListPointListService = (WorkListPointListService) getBean("workListPointListService");
        String[] deleteRows = workListPointListForm.getDeleteRows();
        
        User user = getUser(request);
        workListPointListService.deleteList(deleteRows, user);
        setAjaxStatus(request);
    }

    
    private void saveList(HttpServletRequest request, HttpServletResponse response, WorkListPointListForm workListPointListForm) throws Exception 
    {
        WorkListPointListService workListPointListService = (WorkListPointListService) getBean("workListPointListService");
        
        List<Map> gridList = CommonUtil.setGridMap(request);

        workListPointListService.saveList(gridList, getUser(request));
        
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("status", "ok");
        response.getWriter().print(jsonObj.toString());
	}
    
}