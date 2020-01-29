package dream.consult.program.dashboard.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.ConsultAuthAction;
import common.util.CommonUtil;
import dream.consult.program.dashboard.dto.ConsultPgmDashboardCommonDTO;
import dream.consult.program.dashboard.form.ConsultPgmDashboardListForm;
import dream.consult.program.dashboard.service.ConsultPgmDashboardListService;

/**
 * 대시보드 Contents - 목록 action
 * @author  kim21017
 * @version $Id: ConsultPgmDashboardListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/consultPgmDashboardList" name="consultPgmDashboardListForm"
 *                input="/dream/consult/program/dashboard/consultPgmDashboardList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="consultPgmDashboardList" path="/dream/consult/program/dashboard/consultPgmDashboardList.jsp"
 *                        redirect="false"
 */
public class ConsultPgmDashboardListAction extends ConsultAuthAction
{
    /** 조회 */
    public static final int FIND_LIST 		= 1001;
    /** 삭제 */
    public static final int DELETE_LIST 	= 1002;

    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        ConsultPgmDashboardListForm consultPgmDashboardListForm = (ConsultPgmDashboardListForm) form;

        switch (consultPgmDashboardListForm.getStrutsAction())
        {
            case ConsultPgmDashboardListAction.FIND_LIST:
                findList(request, consultPgmDashboardListForm, response,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ConsultPgmDashboardListAction.BASE_SET_HEADER:
                setHeader(request, response, consultPgmDashboardListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ConsultPgmDashboardListAction.DELETE_LIST:
            	deleteList(request, consultPgmDashboardListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case ConsultPgmDashboardListAction.BASE_GRID_EXPORT:
            	findList(request, consultPgmDashboardListForm, response,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("consultPgmDashboardList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, ConsultPgmDashboardListForm consultPgmDashboardListForm) throws IOException
    {
        super.setHeader(request, response, consultPgmDashboardListForm.getListId(),consultPgmDashboardListForm.getCurrentPageId());
    }

    /**
     * grid find
     * @author  kim2107
     * @version $Id: ConsultPgmDashboardListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     *
     * @param request
     * @param consultPgmDashboardListForm
     * @param response
     * @throws Exception
     */
    private void findList(HttpServletRequest request, ConsultPgmDashboardListForm consultPgmDashboardListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	ConsultPgmDashboardListService consultPgmDashboardListService = (ConsultPgmDashboardListService) getBean("consultPgmDashboardListService");
    	ConsultPgmDashboardCommonDTO consultPgmDashboardCommonDTO = consultPgmDashboardListForm.getConsultPgmDashboardCommonDTO();

    	consultPgmDashboardCommonDTO.setIsLoadMaxCount("Y".equals(consultPgmDashboardListForm.getIsLoadMaxCount())?true:false);
    	consultPgmDashboardCommonDTO.setFirstRow(consultPgmDashboardListForm.getFirstRow());
    	consultPgmDashboardCommonDTO.setOrderBy(consultPgmDashboardListForm.getOrderBy());
    	consultPgmDashboardCommonDTO.setDirection(consultPgmDashboardListForm.getDirection());
    	
        //리스트를 조회한다.
        List resultList = consultPgmDashboardListService.findList(consultPgmDashboardCommonDTO, getUser(request));

        String totalCount = "";
        if(Integer.parseInt(consultPgmDashboardListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = consultPgmDashboardListService.findTotalCount(consultPgmDashboardCommonDTO,getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,consultPgmDashboardListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
	}
    /**
     * delete
     * @author  kim21017
     * @version $Id: ConsultPgmDashboardListAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     *SSSS
     * @param request
     * @param consultPgmDashboardListForm
     */
    private void deleteList(HttpServletRequest request, ConsultPgmDashboardListForm consultPgmDashboardListForm) throws Exception
    {
    	ConsultPgmDashboardListService consultPgmDashboardListService = (ConsultPgmDashboardListService) getBean("consultPgmDashboardListService");

    	String[] deleteRows = consultPgmDashboardListForm.getDeleteRows();

    	consultPgmDashboardListService.deleteList(deleteRows, getUser(request));

        setAjaxStatus(request);
    }
}
