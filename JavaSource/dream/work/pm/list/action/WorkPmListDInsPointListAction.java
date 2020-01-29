package dream.work.pm.list.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.WorkPmListDInsPointDetailDTO;
import dream.work.pm.list.form.WorkPmListDInsPointListForm;
import dream.work.pm.list.service.WorkPmListDInsPointListService;

/**
 * WorkPmListDInsPoint Page - List Action
 * 
 * @author youngjoo38
 * @version $Id: WorkPmListDInsPointListAction.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @struts:action path="/workPmListDInsPointList" name="workPmListDInsPointListForm"
 *                input="/dream/work/pm/list/ins/workPmListDInsPointList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workPmListDInsPointList" path="/dream/work/pm/list/ins/workPmListDInsPointList.jsp"
 *                        redirect="false"
 */
public class WorkPmListDInsPointListAction extends AuthAction
{
    
  //일반 페이지 적용시 AuthAction 으로 변경해야합니다.
    /** 조회 */
    public static final int LIST_FIND       = 1001;
    /** 삭제 */
    public static final int LIST_DELETE     = 1002;
    /** 표준점검항목 선택 LOV 입력 */
    public static final int LOV_INPUT       = 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        WorkPmListDInsPointListForm workPmListDInsPointListForm = (WorkPmListDInsPointListForm) form;
        
        switch (workPmListDInsPointListForm.getStrutsAction())
        {
            case WorkPmListDInsPointListAction.BASE_SET_HEADER:
                setHeader(request, response, workPmListDInsPointListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkPmListDInsPointListAction.LIST_FIND:
                findList(request, response, workPmListDInsPointListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case WorkPmListDInsPointListAction.LIST_DELETE:
                deleteList(request, workPmListDInsPointListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case WorkPmListDInsPointListAction.LOV_INPUT:
            	insertLovDetail(request, workPmListDInsPointListForm);
            	returnActionForward = new ActionForward("/ajaxXmlVal");
            	break;                
            case WorkPmListDInsPointListAction.BASE_GRID_EXPORT:
                findList(request, response, workPmListDInsPointListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("workPmListDInsPointList");
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, WorkPmListDInsPointListForm workPmListDInsPointListForm) throws IOException
    {
        super.setHeader(request, response, workPmListDInsPointListForm.getListId(), workPmListDInsPointListForm.getCurrentPageId()); 
    }
   
    /**
     * FIND LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param workPmListDInsPointListForm
     */
    
    private void findList(HttpServletRequest request, HttpServletResponse response, WorkPmListDInsPointListForm workPmListDInsPointListForm, boolean excelExport) throws Exception
    {
        WorkPmListDInsPointListService workPmListDInsPointListService = (WorkPmListDInsPointListService) getBean("workPmListDInsPointListService");
        MaPmMstrCommonDTO maPmMstrCommonDTO = workPmListDInsPointListForm.getMaPmMstrCommonDTO();
      
        //Paging
        maPmMstrCommonDTO.setIsLoadMaxCount("Y".equals(workPmListDInsPointListForm.getIsLoadMaxCount())?true:false);
        maPmMstrCommonDTO.setFirstRow(workPmListDInsPointListForm.getFirstRow());
        maPmMstrCommonDTO.setOrderBy(workPmListDInsPointListForm.getOrderBy());
        maPmMstrCommonDTO.setDirection(workPmListDInsPointListForm.getDirection());
        
        User user = getUser(request);
        List resultList = workPmListDInsPointListService.findList(maPmMstrCommonDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workPmListDInsPointListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workPmListDInsPointListService.findTotalCount(maPmMstrCommonDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,workPmListDInsPointListForm.getListId(),workPmListDInsPointListForm.getCurrentPageId(), workPmListDInsPointListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * DELETE LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param workPmListDInsPointListForm
     */
    private void deleteList(HttpServletRequest request, WorkPmListDInsPointListForm workPmListDInsPointListForm) throws Exception
    {
        WorkPmListDInsPointListService workPmListDInsPointListService = (WorkPmListDInsPointListService) getBean("workPmListDInsPointListService");
        String[] deleteRows = workPmListDInsPointListForm.getDeleteRows();
        
        User user = getUser(request);
        workPmListDInsPointListService.deleteList(deleteRows, user);
        setAjaxStatus(request);
    }
    
    private void insertLovDetail(HttpServletRequest request, WorkPmListDInsPointListForm workPmListDInsPointListForm) throws Exception
    {
    	WorkPmListDInsPointListService workPmListDInsPointListService = (WorkPmListDInsPointListService) getBean("workPmListDInsPointListService");
    	
    	MaPmMstrCommonDTO maPmMstrCommonDTO = workPmListDInsPointListForm.getMaPmMstrCommonDTO();
    	WorkPmListDInsPointDetailDTO workPmListDInsPointDetailDTO = workPmListDInsPointListForm.getWorkPmListDInsPointDetailDTO();
    	
    	workPmListDInsPointDetailDTO.setEnterBy(getUser(request).getUserId());
    	maPmMstrCommonDTO.setCompNo(getUser(request).getCompNo());
    	
    	workPmListDInsPointListService.insertLovDetail(workPmListDInsPointDetailDTO, maPmMstrCommonDTO, getUser(request));
    	
    	setAjaxStatus(request);
    }
}