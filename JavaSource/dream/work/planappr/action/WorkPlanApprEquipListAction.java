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
import dream.work.planappr.form.WorkPlanApprEquipListForm;
import dream.work.planappr.service.WorkPlanApprEquipListService;

/**
 * 작업계획승인-작업계획  목록 action
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/workPlanApprEquipList" name="workPlanApprEquipListForm"
 *                input="/dream/work/planappr/workPlanApprEquipList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workPlanApprEquipList" path="/dream/work/planappr/workPlanApprEquipList.jsp"
 *                        redirect="false"
 */
public class WorkPlanApprEquipListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND         = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        WorkPlanApprEquipListForm workPlanApprEquipListForm = (WorkPlanApprEquipListForm) form;
        
        switch (workPlanApprEquipListForm.getStrutsAction())
        {
            case WorkPlanApprEquipListAction.LIST_FIND:
            	findList(request, workPlanApprEquipListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkPlanApprEquipListAction.BASE_GRID_EXPORT:
            	findList(request, workPlanApprEquipListForm, response, true);
            	returnActionForward = new ActionForward("/gridExport");
            	break;
            case WorkPlanApprEquipListAction.BASE_SET_HEADER:
                setHeader(request, response, workPlanApprEquipListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                returnActionForward = mapping.findForward("workPlanApprEquipList");
                break;
        }

        return returnActionForward;
    }


    private void setHeader(HttpServletRequest request, HttpServletResponse response, WorkPlanApprEquipListForm workPlanApprEquipListForm) throws IOException
    {
        super.setHeader(request, response, workPlanApprEquipListForm.getListId(), workPlanApprEquipListForm.getCurrentPageId()); 
    }
    
    private void findList(HttpServletRequest request, WorkPlanApprEquipListForm workPlanApprEquipListForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	WorkPlanApprEquipListService workPlanApprEquipListService = (WorkPlanApprEquipListService) getBean("workPlanApprEquipListService");        

    	WorkPlanApprCommonDTO workPlanApprCommonDTO = workPlanApprEquipListForm.getWorkPlanApprCommonDTO();
    	WorkPlanApprDetailDTO workPlanApprDetailDTO = workPlanApprEquipListForm.getWorkPlanApprDetailDTO();
        
    	//Paging
    	workPlanApprCommonDTO.setIsLoadMaxCount("Y".equals(workPlanApprEquipListForm.getIsLoadMaxCount())?true:false);
    	workPlanApprCommonDTO.setFirstRow(workPlanApprEquipListForm.getFirstRow());
    	workPlanApprCommonDTO.setOrderBy(workPlanApprEquipListForm.getOrderBy());
    	workPlanApprCommonDTO.setDirection(workPlanApprEquipListForm.getDirection());
        
        User user = getUser(request);
        //리스트를 조회한다.
        List resultList = workPlanApprEquipListService.findList(workPlanApprCommonDTO,workPlanApprDetailDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workPlanApprEquipListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workPlanApprEquipListService.findTotalCount(workPlanApprCommonDTO,workPlanApprDetailDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,workPlanApprEquipListForm.getListId(),workPlanApprEquipListForm.getCurrentPageId(), workPlanApprEquipListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
   
}
