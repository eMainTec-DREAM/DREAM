package dream.consult.comp.wrkcal.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.ConsultAuthAction;
import common.util.CommonUtil;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalCommonDTO;
import dream.consult.comp.wrkcal.form.ConsultCompWrkcalListForm;
import dream.consult.comp.wrkcal.service.ConsultCompWrkcalListService;

/**
 * 근무일달력 - 목록 action
 * @author  kim21017
 * @version $Id: ConsultCompWrkcalListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/consultCompWrkcalList" name="consultCompWrkcalListForm"
 *                input="/dream/consult/comp/wrkcal/consultCompWrkcalList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="consultCompWrkcalList" path="/dream/consult/comp/wrkcal/consultCompWrkcalList.jsp"
 *                        redirect="false"
 */
public class ConsultCompWrkcalListAction extends ConsultAuthAction
{
    /** 조회 */
    public static final int WRKCAL_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int WRKCAL_LIST_DELETE 	= 1002;

    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        ConsultCompWrkcalListForm consultCompWrkcalListForm = (ConsultCompWrkcalListForm) form;

        switch (consultCompWrkcalListForm.getStrutsAction())
        {
            case ConsultCompWrkcalListAction.WRKCAL_LIST_FIND:
                findWrkcalList(request, consultCompWrkcalListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ConsultCompWrkcalListAction.BASE_SET_HEADER:
                setHeader(request, response, consultCompWrkcalListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ConsultCompWrkcalListAction.WRKCAL_LIST_DELETE:
            	deleteWrkcal(request, consultCompWrkcalListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case ConsultCompWrkcalListAction.BASE_GRID_EXPORT:
            	findWrkcalList(request, consultCompWrkcalListForm, response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("consultCompWrkcalList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, ConsultCompWrkcalListForm consultCompWrkcalListForm) throws IOException
    {
        super.setHeader(request, response, consultCompWrkcalListForm.getListId(),consultCompWrkcalListForm.getCurrentPageId());
    }

    /**
     * grid find
     * @author  kim2107
     * @version $Id: ConsultCompWrkcalListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     *
     * @param request
     * @param consultCompWrkcalListForm
     * @param response
     * @throws Exception
     */
    private void findWrkcalList(HttpServletRequest request, ConsultCompWrkcalListForm consultCompWrkcalListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	ConsultCompWrkcalListService consultCompWrkcalListService = (ConsultCompWrkcalListService) getBean("consultCompWrkcalListService");

    	ConsultCompWrkcalCommonDTO consultCompWrkcalCommonDTO = consultCompWrkcalListForm.getConsultCompWrkcalCommonDTO();

    	//Paging
        consultCompWrkcalCommonDTO.setIsLoadMaxCount("Y".equals(consultCompWrkcalListForm.getIsLoadMaxCount())?true:false);
        consultCompWrkcalCommonDTO.setFirstRow(consultCompWrkcalListForm.getFirstRow());
        consultCompWrkcalCommonDTO.setOrderBy(consultCompWrkcalListForm.getOrderBy());
        consultCompWrkcalCommonDTO.setDirection(consultCompWrkcalListForm.getDirection());
        
        User user = getUser(request);
        
        //리스트를 조회한다.
        List resultList = consultCompWrkcalListService.findWrkcalList(consultCompWrkcalCommonDTO, user);

        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(consultCompWrkcalListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = consultCompWrkcalListService.findTotalCount(consultCompWrkcalCommonDTO,user);

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, consultCompWrkcalListForm); 
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
	}
    /**
     * delete
     * @author  kim21017
     * @version $Id: ConsultCompWrkcalListAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     *
     * @param request
     * @param consultCompWrkcalListForm
     */
    private void deleteWrkcal(HttpServletRequest request, ConsultCompWrkcalListForm consultCompWrkcalListForm) throws Exception
    {
    	ConsultCompWrkcalListService consultCompWrkcalListService = (ConsultCompWrkcalListService) getBean("consultCompWrkcalListService");

    	String[] deleteRows = consultCompWrkcalListForm.getDeleteRows();
    	String[] deleteRowsExt = consultCompWrkcalListForm.getDeleteRowsExt();

    	consultCompWrkcalListService.deleteWrkcal(deleteRows, deleteRowsExt, getUser(request));

        setAjaxStatus(request);
    }
}
