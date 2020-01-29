package dream.work.planappr.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.work.planappr.dto.WorkPlanApprCommonDTO;
import dream.work.planappr.dto.WorkPlanApprDetailDTO;
import dream.work.planappr.form.WorkPlanApprWorkListForm;
import dream.work.planappr.service.WorkPlanApprWorkListService;

/**
 * 작업계획승인-작업계획  목록 action
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/workPlanApprWorkList" name="workPlanApprWorkListForm"
 *                input="/dream/work/planappr/workPlanApprWorkList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workPlanApprWorkList" path="/dream/work/planappr/workPlanApprWorkList.jsp"
 *                        redirect="false"
 */
public class WorkPlanApprWorkListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND         = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        WorkPlanApprWorkListForm workPlanApprWorkListForm = (WorkPlanApprWorkListForm) form;
        
        switch (workPlanApprWorkListForm.getStrutsAction())
        {
            case WorkPlanApprWorkListAction.LIST_FIND:
            	findList(request, workPlanApprWorkListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkPlanApprWorkListAction.BASE_GRID_EXPORT:
            	findList(request, workPlanApprWorkListForm, response, true);
            	returnActionForward = new ActionForward("/gridExport");
            	break;
            case WorkPlanApprWorkListAction.BASE_SET_HEADER:
                setHeader(request, response, workPlanApprWorkListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                returnActionForward = mapping.findForward("workPlanApprWorkList");
                break;
        }

        return returnActionForward;
    }


    private void setHeader(HttpServletRequest request, HttpServletResponse response, WorkPlanApprWorkListForm workPlanApprWorkListForm) throws IOException
    {
        super.setHeader(request, response, workPlanApprWorkListForm.getListId(), workPlanApprWorkListForm.getCurrentPageId()); 
    }
    
    private void findList(HttpServletRequest request, WorkPlanApprWorkListForm workPlanApprWorkListForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	WorkPlanApprWorkListService workPlanApprWorkListService = (WorkPlanApprWorkListService) getBean("workPlanApprWorkListService");        

    	WorkPlanApprCommonDTO workPlanApprCommonDTO = workPlanApprWorkListForm.getWorkPlanApprCommonDTO();
    	WorkPlanApprDetailDTO workPlanApprDetailDTO = workPlanApprWorkListForm.getWorkPlanApprDetailDTO();
        
    	//Paging
    	workPlanApprCommonDTO.setIsLoadMaxCount("Y".equals(workPlanApprWorkListForm.getIsLoadMaxCount())?true:false);
    	workPlanApprCommonDTO.setFirstRow(workPlanApprWorkListForm.getFirstRow());
    	workPlanApprCommonDTO.setOrderBy(workPlanApprWorkListForm.getOrderBy());
    	workPlanApprCommonDTO.setDirection(workPlanApprWorkListForm.getDirection());
        
        User user = getUser(request);
        //리스트를 조회한다.
        List resultList = workPlanApprWorkListService.findList(workPlanApprCommonDTO,workPlanApprDetailDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workPlanApprWorkListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workPlanApprWorkListService.findTotalCount(workPlanApprCommonDTO,workPlanApprDetailDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,workPlanApprWorkListForm.getListId(),workPlanApprWorkListForm.getCurrentPageId(), workPlanApprWorkListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
   
}
