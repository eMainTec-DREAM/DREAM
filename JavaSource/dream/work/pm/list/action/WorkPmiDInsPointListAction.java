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
import dream.work.pm.list.dto.WorkPmiDInsCommonDTO;
import dream.work.pm.list.form.WorkPmiDInsPointListForm;
import dream.work.pm.list.service.WorkPmiDInsPointListService;

/**
 * WorkPmiDInsPoint Page - List Action
 * 
 * @author youngjoo38
 * @version $Id: WorkPmiDInsPointListAction.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @struts:action path="/workPmiDInsPointList" name="workPmiDInsPointListForm"
 *                input="/dream/work/pmi/list/workPmiDInsPointList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workPmiDInsPointList" path="/dream/work/pmi/list/workPmiDInsPointList.jsp"
 *                        redirect="false"
 */
public class WorkPmiDInsPointListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND       = 1001;
    /** 삭제 */
    public static final int LIST_DELETE     = 7002;
    /** LIST Edit 저장  */
    public static final int EDIT_LIST_SAVE	= 6003;
      
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        WorkPmiDInsPointListForm workPmiDInsPointListForm = (WorkPmiDInsPointListForm) form;
        
        switch (workPmiDInsPointListForm.getStrutsAction())
        {
            case WorkPmiDInsPointListAction.BASE_SET_HEADER:
                setHeader(request, response, workPmiDInsPointListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkPmiDInsPointListAction.LIST_FIND:
                findList(request, response, workPmiDInsPointListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case WorkPmiDInsPointListAction.LIST_DELETE:
                deleteList(request, workPmiDInsPointListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case WorkPmiDInsPointListAction.EDIT_LIST_SAVE:
                saveList(request,response, workPmiDInsPointListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkPmiDInsPointListAction.BASE_GRID_EXPORT:
                findList(request, response, workPmiDInsPointListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, WorkPmiDInsPointListForm workPmiDInsPointListForm) throws IOException
    {
        super.setHeader(request, response, workPmiDInsPointListForm.getListId(), workPmiDInsPointListForm.getCurrentPageId()); 
    }
   
    /**
     * FIND LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param workPmiDInsPointListForm
     */
    
    private void findList(HttpServletRequest request, HttpServletResponse response, WorkPmiDInsPointListForm workPmiDInsPointListForm, boolean excelExport) throws Exception
    {
        WorkPmiDInsPointListService workPmiDInsPointListService = (WorkPmiDInsPointListService) getBean("workPmiDInsPointListService");
        WorkPmiDInsCommonDTO workPmiDInsCommonDTO = workPmiDInsPointListForm.getWorkPmiDInsCommonDTO();
      
        //Paging
        workPmiDInsCommonDTO.setIsLoadMaxCount("Y".equals(workPmiDInsPointListForm.getIsLoadMaxCount())?true:false);
        workPmiDInsCommonDTO.setFirstRow(workPmiDInsPointListForm.getFirstRow());
        workPmiDInsCommonDTO.setOrderBy(workPmiDInsPointListForm.getOrderBy());
        workPmiDInsCommonDTO.setDirection(workPmiDInsPointListForm.getDirection());
        
        User user = getUser(request);
        List resultList = workPmiDInsPointListService.findList(workPmiDInsCommonDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workPmiDInsPointListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workPmiDInsPointListService.findTotalCount(workPmiDInsCommonDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,workPmiDInsPointListForm.getListId(),workPmiDInsPointListForm.getCurrentPageId(), workPmiDInsPointListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * DELETE LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param workPmiDInsPointListForm
     */
    private void deleteList(HttpServletRequest request, WorkPmiDInsPointListForm workPmiDInsPointListForm) throws Exception
    {
        WorkPmiDInsPointListService workPmiDInsPointListService = (WorkPmiDInsPointListService) getBean("workPmiDInsPointListService");
        String[] deleteRows = workPmiDInsPointListForm.getDeleteRows();
        
        User user = getUser(request);
        workPmiDInsPointListService.deleteList(deleteRows, user);
        setAjaxStatus(request);
    }

    private void saveList(HttpServletRequest request, HttpServletResponse response, WorkPmiDInsPointListForm workPmiDInsPointListForm) throws Exception 
    {
        WorkPmiDInsPointListService workPmiDInsPointListService = (WorkPmiDInsPointListService) getBean("workPmiDInsPointListService");
        
        List<Map> gridList = CommonUtil.setGridMap(request);

        workPmiDInsPointListService.saveList(gridList, getUser(request));
        
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("status", "ok");
        response.getWriter().print(jsonObj.toString());
	}   
    
}