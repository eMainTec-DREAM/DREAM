package dream.edu.rpt.emplist.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;

import dream.edu.rpt.emplist.dto.EduRptEmpCommonDTO;
import dream.edu.rpt.emplist.form.EduRptEmpListForm;
import dream.edu.rpt.emplist.service.EduRptEmpListService;

/**
 * 자격증분류 - 목록 action
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/eduRptEmpList" name="eduRptEmpListForm"
 *                input="/dream/edu/rpt/eduRptEmpList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="eduRptEmpList" path="/dream/edu/rpt/eduRptEmpList.jsp"
 *                        redirect="false"
 */
public class EduRptEmpListAction extends AuthAction
{
    /** 조회 */
    public static final int EDU_EMP_LIST_FIND     = 1001;

    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        EduRptEmpListForm eduRptEmpListForm = (EduRptEmpListForm) form;

        switch (eduRptEmpListForm.getStrutsAction())
        {
            case EduRptEmpListAction.EDU_EMP_LIST_FIND:
            	findPtRecList(request, eduRptEmpListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case EduRptEmpListAction.BASE_SET_HEADER:
                setHeader(request, response, eduRptEmpListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case EduRptEmpListAction.BASE_GRID_EXPORT:
            	findPtRecList(request, eduRptEmpListForm,response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("eduRptEmpList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, EduRptEmpListForm eduRptEmpListForm) throws IOException
    {
        super.setHeader(request, response, eduRptEmpListForm.getListId(), eduRptEmpListForm.getCurrentPageId());
    }

    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     *
     * @param request
     * @param eduRptEmpListForm
     * @throws Exception
     */
    private void findPtRecList(HttpServletRequest request, EduRptEmpListForm eduRptEmpListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	EduRptEmpListService eduRptEmpListService = (EduRptEmpListService) getBean("eduRptEmpListService");

    	EduRptEmpCommonDTO eduRptEmpCommonDTO = eduRptEmpListForm.getEduRptEmpCommonDTO();

    	//Paging
    	eduRptEmpCommonDTO.setIsLoadMaxCount("Y".equals(eduRptEmpListForm.getIsLoadMaxCount())?true:false);
    	eduRptEmpCommonDTO.setFirstRow(eduRptEmpListForm.getFirstRow());
    	eduRptEmpCommonDTO.setOrderBy(eduRptEmpListForm.getOrderBy());
    	eduRptEmpCommonDTO.setDirection(eduRptEmpListForm.getDirection());

        //리스트를 조회한다.
        List resultList = eduRptEmpListService.findList(eduRptEmpCommonDTO,getUser(request));

        //Paging
        String totalCount = "";
        if(Integer.parseInt(eduRptEmpListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = eduRptEmpListService.findTotalCount(eduRptEmpCommonDTO,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,eduRptEmpListForm.getListId(),eduRptEmpListForm.getCurrentPageId(), eduRptEmpListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }


}
