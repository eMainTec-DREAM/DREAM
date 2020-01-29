package dream.consult.comp.terminal.action;


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
import dream.consult.comp.terminal.dto.ConsultCompTerminalExCommonDTO;
import dream.consult.comp.terminal.form.ConsultCompTerminalExListForm;
import dream.consult.comp.terminal.service.ConsultCompTerminalExListService;

/**
 * 접근터미널 - 목록 action
 * @author  kim21017
 * @version $Id: ConsultCompTerminalExListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/consultCompTerminalExList" name="consultCompTerminalExListForm"
 *                input="/dream/consult/comp/terminal/consultCompTerminalExList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="consultCompTerminalExList" path="/dream/consult/comp/terminal/consultCompTerminalExList.jsp"
 *                        redirect="false"
 */
public class ConsultCompTerminalExListAction extends ConsultAuthAction
{
    /** 조회 */
    public static final int TERMINAL_FIND 		= 1001;
    /** 삭제 */
    public static final int TERMINAL_DELETE 	= 1002;

    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        ConsultCompTerminalExListForm consultCompTerminalExListForm = (ConsultCompTerminalExListForm) form;

        switch (consultCompTerminalExListForm.getStrutsAction())
        {
            case ConsultCompTerminalExListAction.TERMINAL_FIND:
                findTerminalExList(request, consultCompTerminalExListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ConsultCompTerminalExListAction.BASE_SET_HEADER:
                setHeader(request, response, consultCompTerminalExListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ConsultCompTerminalExListAction.TERMINAL_DELETE:
            	deleteTerminal(request, consultCompTerminalExListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case ConsultCompTerminalExListAction.BASE_GRID_EXPORT:
            	findTerminalExList(request, consultCompTerminalExListForm, response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("consultCompTerminalExList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, ConsultCompTerminalExListForm consultCompTerminalExListForm) throws IOException
    {
        super.setHeader(request, response, consultCompTerminalExListForm.getListId(),consultCompTerminalExListForm.getCurrentPageId());
    }

    /**
     * grid find
     * @author  kim2107
     * @version $Id: ConsultCompTerminalExListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     *
     * @param request
     * @param consultCompTerminalExListForm
     * @param response
     * @throws Exception
     */
    private void findTerminalExList(HttpServletRequest request, ConsultCompTerminalExListForm consultCompTerminalExListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	ConsultCompTerminalExListService consultCompTerminalExListService = (ConsultCompTerminalExListService) getBean("consultCompTerminalExListService");

    	ConsultCompTerminalExCommonDTO consultCompTerminalExCommonDTO = consultCompTerminalExListForm.getConsultCompTerminalExCommonDTO();

        //Paging
        consultCompTerminalExCommonDTO.setIsLoadMaxCount("Y".equals(consultCompTerminalExListForm.getIsLoadMaxCount())?true:false);
        consultCompTerminalExCommonDTO.setFirstRow(consultCompTerminalExListForm.getFirstRow());
        consultCompTerminalExCommonDTO.setOrderBy(consultCompTerminalExListForm.getOrderBy());
        consultCompTerminalExCommonDTO.setDirection(consultCompTerminalExListForm.getDirection());
        
        User user = getUser(request);

        //리스트를 조회한다.
        List resultList = consultCompTerminalExListService.findTerminalExList(consultCompTerminalExCommonDTO, getUser(request));

        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(consultCompTerminalExListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = consultCompTerminalExListService.findTotalCount(consultCompTerminalExCommonDTO,user);
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,consultCompTerminalExListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
	}
    /**
     * delete
     * @author  kim21017
     * @version $Id: ConsultCompTerminalExListAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     *
     * @param request
     * @param consultCompTerminalExListForm
     */
    private void deleteTerminal(HttpServletRequest request, ConsultCompTerminalExListForm consultCompTerminalExListForm) throws Exception
    {
    	ConsultCompTerminalExListService consultCompTerminalExListService = (ConsultCompTerminalExListService) getBean("consultCompTerminalExListService");

    	String[] deleteRows = consultCompTerminalExListForm.getDeleteRows();
    	String[] deleteRowsExt = consultCompTerminalExListForm.getDeleteRowsExt();

    	consultCompTerminalExListService.deleteTerminal(deleteRows, deleteRowsExt, getUser(request));

        setAjaxStatus(request);
    }
}
