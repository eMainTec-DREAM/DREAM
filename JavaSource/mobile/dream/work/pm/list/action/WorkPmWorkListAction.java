package mobile.dream.work.pm.list.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.MobileAuthAction;
import mobile.dream.work.pm.list.dto.WorkPmWorkCommonDTO;
import mobile.dream.work.pm.list.form.WorkPmWorkListForm;
import mobile.dream.work.pm.list.service.WorkPmWorkListService;

/**
 * 계획작업 - 목록 action
 * @author  jung7126
 * @version $Id: WorkPmWorkListAction.java,v 1.0 2015/12/02 09:10:21 jung7126 Exp $
 * @since   1.0
 * @struts:action path="/workPmWorkList" name="workPmWorkListForm"
 *                input="/mobile/dream/work/pm/list/workPmWorkList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workPmWorkList" path="/mobile/dream/work/pm/list/workPmWorkList.jsp"
 *                        redirect="false"
 */
public class WorkPmWorkListAction extends MobileAuthAction
{
    /** 조회 */
    public static final int PM_WORK_LIST_FIND 		= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        WorkPmWorkListForm workPmWorkListForm = (WorkPmWorkListForm) form;
        
        switch (workPmWorkListForm.getStrutsAction())
        {
            case WorkPmWorkListAction.PM_WORK_LIST_FIND:
            	findPmWorkList(request, workPmWorkListForm, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
                //For Display Tab List Counter
            case WorkPmWorkListAction.BASE_QUICK_SEARCH:
            	findPmWorkList(request, workPmWorkListForm, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                returnActionForward = mapping.findForward("workPmWorkList");
                break;
        }

        return returnActionForward;
    }

    /**
     * grid find
     * @author  kim2107
     * @version $Id: WorkPmWorkListAction.java,v 1.0 2015/12/02 09:10:21 jung7126 Exp $
     * @since   1.0
     * 
     * @param request
     * @param workPmWorkListForm
     * @throws Exception
     */
    private void findPmWorkList(HttpServletRequest request, WorkPmWorkListForm workPmWorkListForm, HttpServletResponse response) throws IOException
    {
    	WorkPmWorkListService workPmWorkListService = (WorkPmWorkListService) getBean("workPmWorkListService");        

    	WorkPmWorkCommonDTO workPmWorkCommonDTO = workPmWorkListForm.getWorkPmWorkCommonDTO();
    	workPmWorkCommonDTO.setCompNo(getUser(request).getCompNo());

    	workPmWorkCommonDTO.setIsLoadMaxCount("Y".equals(workPmWorkListForm.getIsLoadMaxCount())?true:false); //set Paging 
    	workPmWorkCommonDTO.setFirstRow(workPmWorkListForm.getFirstRow()); //First Row for view
        
    	String totalCount = "";
        if(Integer.parseInt(workPmWorkListForm.getIsTotalCount()) == 0) totalCount = workPmWorkListService.findTotalCount(workPmWorkListForm,getUser(request));
        
    	//리스트를 조회한다.
        List resultList = workPmWorkListService.findList(workPmWorkCommonDTO,getUser(request));
        super.makeJsonResult(resultList, request, response, totalCount);
    }
    
}
