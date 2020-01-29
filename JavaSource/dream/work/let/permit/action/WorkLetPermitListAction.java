package dream.work.let.permit.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.work.let.dto.WorkLetCommonDTO;
import dream.work.let.permit.dto.WorkLetPermitDetailDTO;
import dream.work.let.permit.dto.WorkLetPermitListDTO;
import dream.work.let.permit.form.WorkLetPermitListForm;
import dream.work.let.permit.service.WorkLetPermitListService;

/**
 * 안전작업 - 안전작업허가서 목록
 * @author  syyang
 * @version $Id$
 * @since   1.0
 * @struts:action path="/workLetPermitList" name="workLetPermitListForm"
 *                input="/dream/work/let/permit/workLetPermitList.jsp" scope="request"
 *                validate="false"
 */
public class WorkLetPermitListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int WO_LET_PERMIT_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int WO_LET_PERMIT_LIST_DELETE 		= 7002;
    /** 입력 */
    public static final int WO_LET_PERMIT_LIST_INPUT		= 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkLetPermitListForm workLetPermitListForm = (WorkLetPermitListForm) form;
        String strutsAction = request.getParameter("strutsAction");
        if(strutsAction!="") workLetPermitListForm.setStrutsAction(Integer.parseInt(strutsAction));
        
        switch (workLetPermitListForm.getStrutsAction())
        {
            case WorkLetPermitListAction.WO_LET_PERMIT_LIST_FIND:
                findWoLetPermitList(request,response, workLetPermitListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkLetPermitListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, workLetPermitListForm.getListId(), workLetPermitListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkLetPermitListAction.WO_LET_PERMIT_LIST_DELETE:
            	deleteWoLetPermitList(request,workLetPermitListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkLetPermitListAction.WO_LET_PERMIT_LIST_INPUT:
                inputWoLetList(request,workLetPermitListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case WorkLetPermitListAction.BASE_GRID_EXPORT:
            	findWoLetPermitList(request,response, workLetPermitListForm, true);
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
     * @author  syyang
     * @version $Id$
     * @since   1.0
     * 
     * @param request
     * @param workLetPermitListForm
     * @throws Exception
     */
    private void findWoLetPermitList(HttpServletRequest request,HttpServletResponse response, WorkLetPermitListForm workLetPermitListForm, boolean excelExport) throws Exception
    {
        WorkLetPermitListService workLetPermitListService = (WorkLetPermitListService) getBean("workLetPermitListService");

        WorkLetCommonDTO workLetCommonDTO = workLetPermitListForm.getWorkLetCommonDTO();
        WorkLetPermitListDTO workLetPermitListDTO = workLetPermitListForm.getWorkLetPermitListDTO();
        
        //Paging
        workLetPermitListDTO.setIsLoadMaxCount("Y".equals(workLetPermitListForm.getIsLoadMaxCount())?true:false);
        workLetPermitListDTO.setFirstRow(workLetPermitListForm.getFirstRow());
        workLetPermitListDTO.setOrderBy(workLetPermitListForm.getOrderBy());
        workLetPermitListDTO.setDirection(workLetPermitListForm.getDirection());
        
        User user = getUser(request);
        List resultList = workLetPermitListService.findWoLetPermitList(workLetCommonDTO, workLetPermitListDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workLetPermitListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workLetPermitListService.findTotalCount(workLetCommonDTO, workLetPermitListDTO,user);
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, workLetPermitListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  syyang
     * @version $Id$
     * @since   1.0
     * 
     * @param request
     * @param workLetPermitListForm
     * @throws Exception
     */
    private void deleteWoLetPermitList(HttpServletRequest request, WorkLetPermitListForm workLetPermitListForm) throws Exception
    {
    	WorkLetPermitListService workLetPermitListService = (WorkLetPermitListService) getBean("workLetPermitListService");
        
    	String[] deleteRows = workLetPermitListForm.getDeleteRows();
    
    	workLetPermitListService.deleteWoLetList(deleteRows, getUser(request).getCompNo());
    	
    	setAjaxStatus(request);
    }
    
    /**
     * input
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maWoResultWoLetListForm
     * @throws Exception
     */
    private void inputWoLetList(HttpServletRequest request, WorkLetPermitListForm workLetPermitListForm) throws Exception
    {
        WorkLetPermitListService workLetPermitListService = (WorkLetPermitListService) getBean("workLetPermitListService");
        
        WorkLetCommonDTO workLetCommonDTO = workLetPermitListForm.getWorkLetCommonDTO(); 
        WorkLetPermitDetailDTO workLetPermitDetailDTO = workLetPermitListForm.getWorkLetPermitDetailDTO();
        
        workLetPermitListService.inputWoLetList(workLetCommonDTO, workLetPermitDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
}