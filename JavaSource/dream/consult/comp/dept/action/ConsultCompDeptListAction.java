package dream.consult.comp.dept.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.ConsultAuthAction;
import common.util.CommonUtil;
import dream.consult.comp.dept.dto.ConsultCompDeptCommonDTO;
import dream.consult.comp.dept.form.ConsultCompDeptListForm;
import dream.consult.comp.dept.service.ConsultCompDeptListService;

/**
 * 보전부서 - 목록 action
 * @author  hyosung
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/consultCompDeptList" name="consultCompDeptListForm"
 *                input="/dream/consult/comp/dept/consultCompDeptList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="consultCompDeptList" path="/dream/consult/comp/dept/consultCompDeptList.jsp"
 *                        redirect="false"
 */
public class ConsultCompDeptListAction extends ConsultAuthAction
{
    /** 조회 */
    public static final int DEPT_LIST_FIND = 1001;
    /** 삭제 */
    public static final int DEPT_LIST_DELETE = 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        ConsultCompDeptListForm consultCompDeptListForm = (ConsultCompDeptListForm) form;
        
        switch (consultCompDeptListForm.getStrutsAction())
        {
            case ConsultCompDeptListAction.BASE_SET_HEADER:
                setHeader(request, response, consultCompDeptListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ConsultCompDeptListAction.DEPT_LIST_FIND:
                findList(request, response, consultCompDeptListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case ConsultCompDeptListAction.DEPT_LIST_DELETE:
            	deleteList(request, consultCompDeptListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case ConsultCompDeptListAction.BASE_GRID_EXPORT:
            	findList(request, response, consultCompDeptListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("consultCompDeptList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, ConsultCompDeptListForm consultCompDeptListForm) throws IOException
    {
        super.setHeader(request, response, consultCompDeptListForm.getListId(), consultCompDeptListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  hyosung
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param consultCompDeptListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, ConsultCompDeptListForm consultCompDeptListForm,  boolean excelExport) throws IOException
    {
    	ConsultCompDeptListService consultCompDeptListService = (ConsultCompDeptListService) getBean("consultCompDeptListService");        

    	ConsultCompDeptCommonDTO consultCompDeptCommonDTO = consultCompDeptListForm.getConsultCompDeptCommonDTO();
    	// 로긴 comp_no 를 셋팅한다.
    	//consultCompDeptCommonDTO.setFilterCompNo((getUser(request).getCompNo()));
    	
        //리스트를 조회한다.
        List resultList = consultCompDeptListService.findDeptList(consultCompDeptCommonDTO, getUser(request));

        // 조회한 List 를 form에 셋팅한다.
        if(excelExport) CommonUtil.makeTreeGridExport(resultList, request, response,consultCompDeptListForm);
        else CommonUtil.makeTreeJsonResult(resultList, request, response, consultCompDeptListForm.getListId());
    }
    
    /**
     * delete
     * @author  hyosung
     * @version $Id: $
     * @since   1.0
     * 
     * @param consultCompDeptListForm
     * @param request
     */
    private void deleteList(HttpServletRequest request, ConsultCompDeptListForm consultCompDeptListForm) throws Exception
    {
    	ConsultCompDeptListService consultCompDeptListService = (ConsultCompDeptListService) getBean("consultCompDeptListService");        

        String[] deleteRows = consultCompDeptListForm.getDeleteRows();    // sheet 내역
        String[] deleteRowsExt= consultCompDeptListForm.getDeleteRowsExt();
        consultCompDeptListService.deleteList(deleteRows, deleteRowsExt);
        
        setAjaxStatus(request);
    }
}
