package dream.work.pm.list.action;

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
import dream.work.pm.list.dto.WorkPmiCInsCommonDTO;
import dream.work.pm.list.form.WorkPmiCInsPointListForm;
import dream.work.pm.list.service.WorkPmiCInsPointListService;

/**
 * WorkPmiCInsPoint Page - List Action
 * 
 * @author youngjoo38
 * @version $Id: WorkPmiCInsPointListAction.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @struts:action path="/workPmiCInsPointList" name="workPmiCInsPointListForm"
 *                input="/dream/work/pmi/list/workPmiCInsPointList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workPmiCInsPointList" path="/dream/work/pmi/list/workPmiCInsPointList.jsp"
 *                        redirect="false"
 */
public class WorkPmiCInsPointListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND       = 1001;
    /** 삭제 */
    public static final int LIST_DELETE     = 1002;
    /** LIST Edit 저장  */
    public static final int EDIT_LIST_SAVE	= 6003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        WorkPmiCInsPointListForm workPmiCInsPointListForm = (WorkPmiCInsPointListForm) form;
        
        switch (workPmiCInsPointListForm.getStrutsAction())
        {
            case WorkPmiCInsPointListAction.BASE_SET_HEADER:
                setHeader(request, response, workPmiCInsPointListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkPmiCInsPointListAction.LIST_FIND:
                findList(request, response, workPmiCInsPointListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case WorkPmiCInsPointListAction.LIST_DELETE:
                deleteList(request, workPmiCInsPointListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case WorkPmiCInsPointListAction.EDIT_LIST_SAVE:
                saveList(request,response, workPmiCInsPointListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkPmiCInsPointListAction.BASE_GRID_EXPORT:
                findList(request, response, workPmiCInsPointListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, WorkPmiCInsPointListForm workPmiCInsPointListForm) throws IOException
    {
        super.setHeader(request, response, workPmiCInsPointListForm.getListId(), workPmiCInsPointListForm.getCurrentPageId()); 
    }
   
    /**
     * FIND LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param workPmiCInsPointListForm
     */
    
    private void findList(HttpServletRequest request, HttpServletResponse response, WorkPmiCInsPointListForm workPmiCInsPointListForm, boolean excelExport) throws Exception
    {
        WorkPmiCInsPointListService workPmiCInsPointListService = (WorkPmiCInsPointListService) getBean("workPmiCInsPointListService");
        WorkPmiCInsCommonDTO workPmiCInsCommonDTO = workPmiCInsPointListForm.getWorkPmiCInsCommonDTO();
      
        //Paging
        workPmiCInsCommonDTO.setIsLoadMaxCount("Y".equals(workPmiCInsPointListForm.getIsLoadMaxCount())?true:false);
        workPmiCInsCommonDTO.setFirstRow(workPmiCInsPointListForm.getFirstRow());
        workPmiCInsCommonDTO.setOrderBy(workPmiCInsPointListForm.getOrderBy());
        workPmiCInsCommonDTO.setDirection(workPmiCInsPointListForm.getDirection());
        
        User user = getUser(request);
        List resultList = workPmiCInsPointListService.findList(workPmiCInsCommonDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workPmiCInsPointListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workPmiCInsPointListService.findTotalCount(workPmiCInsCommonDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,workPmiCInsPointListForm.getListId(),workPmiCInsPointListForm.getCurrentPageId(), workPmiCInsPointListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * DELETE LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param workPmiCInsPointListForm
     */
    private void deleteList(HttpServletRequest request, WorkPmiCInsPointListForm workPmiCInsPointListForm) throws Exception
    {
        WorkPmiCInsPointListService workPmiCInsPointListService = (WorkPmiCInsPointListService) getBean("workPmiCInsPointListService");
        String[] deleteRows = workPmiCInsPointListForm.getDeleteRows();
        
        User user = getUser(request);
        workPmiCInsPointListService.deleteList(deleteRows, user);
        setAjaxStatus(request);
    }

    private void saveList(HttpServletRequest request, HttpServletResponse response, WorkPmiCInsPointListForm workPmiCInsPointListForm) throws Exception 
    {
        WorkPmiCInsPointListService workPmiCInsPointListService = (WorkPmiCInsPointListService) getBean("workPmiCInsPointListService");
        
        List<Map> gridList = CommonUtil.setGridMap(request);

        workPmiCInsPointListService.saveList(gridList, getUser(request));
        
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("status", "ok");
        response.getWriter().print(jsonObj.toString());
	} 
    
}