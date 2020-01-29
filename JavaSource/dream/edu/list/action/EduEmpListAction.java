package dream.edu.list.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;

import dream.edu.list.dto.EduCommonDTO;
import dream.edu.list.dto.EduEmpListDTO;
import dream.edu.list.form.EduEmpListForm;
import dream.edu.list.service.EduEmpListService;

/**
 * 작업결과-작업자 목록
 * @author  kim21017
 * @version $Id: EduEmpListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/eduEmpList" name="eduEmpListForm"
 *                input="/dream/edu/list/eduEmpList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="eduEmpList" path="/dream/edu/list/eduEmpList.jsp"
 *                        redirect="false"
 */
public class EduEmpListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int EDU_EMP_LIST_FIND 			= 1001;
    /** 삭제 */
    public static final int EDU_EMP_LIST_DELETE 		= 7002;

    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        EduEmpListForm eduEmpListForm = (EduEmpListForm) form;
        String strutsAction = request.getParameter("strutsAction");
        if(strutsAction!="") eduEmpListForm.setStrutsAction(Integer.parseInt(strutsAction));

        switch (eduEmpListForm.getStrutsAction())
        {

            case EduEmpListAction.EDU_EMP_LIST_FIND:
                findEmpList(request,response, eduEmpListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case EduEmpListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, eduEmpListForm.getListId(), eduEmpListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case EduEmpListAction.EDU_EMP_LIST_DELETE:
            	deleteEmpList(request,eduEmpListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case EduEmpListAction.BASE_GRID_EXPORT:
            	findEmpList(request,response, eduEmpListForm, true);
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
     * @author  kim2107
     * @version $Id: EduEmpListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     *
     * @param request
     * @param eduEmpListForm
     * @throws Exception
     */
    private void findEmpList(HttpServletRequest request,HttpServletResponse response, EduEmpListForm eduEmpListForm, boolean excelExport) throws Exception
    {
        EduEmpListService eduEmpListService = (EduEmpListService) getBean("eduEmpListService");
        EduCommonDTO eduCommonDTO = eduEmpListForm.getEduCommonDTO();
        EduEmpListDTO eduEmpListDTO = eduEmpListForm.getEduEmpListDTO();
    	
    	//Paging
        eduEmpListDTO.setIsLoadMaxCount("Y".equals(eduEmpListForm.getIsLoadMaxCount())?true:false);
        eduEmpListDTO.setFirstRow(eduEmpListForm.getFirstRow());
        eduEmpListDTO.setOrderBy(eduEmpListForm.getOrderBy());
        eduEmpListDTO.setDirection(eduEmpListForm.getDirection());

        //리스트를 조회한다.
        List resultList = eduEmpListService.findEmpList(eduCommonDTO, eduEmpListDTO, getUser(request));

        //Paging
        String totalCount = "";
        if(Integer.parseInt(eduEmpListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = eduEmpListService.findTotalCount(eduCommonDTO,eduEmpListDTO,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,eduEmpListForm.getListId(),eduEmpListForm.getCurrentPageId(), eduEmpListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: EduEmpListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     *
     * @param request
     * @param eduEmpListForm
     * @throws Exception
     */
    private void deleteEmpList(HttpServletRequest request, EduEmpListForm eduEmpListForm) throws Exception
    {
    	EduEmpListService eduEmpListService = (EduEmpListService) getBean("eduEmpListService");

    	String[] deleteRows = eduEmpListForm.getDeleteRows();

    	eduEmpListService.deleteEmpList(deleteRows, getUser(request));

    	setAjaxStatus(request);
    }
}