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
import dream.consult.comp.terminal.dto.ConsultCompTerminalCommonDTO;
import dream.consult.comp.terminal.form.ConsultCompTerminalListForm;
import dream.consult.comp.terminal.service.ConsultCompTerminalListService;

/**
 * 접근터미널 - 목록 action
 * @author  kim21017
 * @version $Id: ConsultCompTerminalListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/consultCompTerminalList" name="consultCompTerminalListForm"
 *                input="/dream/consult/comp/terminal/consultCompTerminalList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="consultCompTerminalList" path="/dream/consult/comp/terminal/consultCompTerminalList.jsp"
 *                        redirect="false"
 */
public class ConsultCompTerminalListAction extends ConsultAuthAction
{
    /** 조회 */
    public static final int TERMINAL_FIND 		= 1001;
    /** 삭제 */
    public static final int TERMINAL_DELETE 	= 1002;

    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        ConsultCompTerminalListForm consultCompTerminalListForm = (ConsultCompTerminalListForm) form;

        switch (consultCompTerminalListForm.getStrutsAction())
        {
            case ConsultCompTerminalListAction.TERMINAL_FIND:
                findTerminalList(request, consultCompTerminalListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ConsultCompTerminalListAction.BASE_SET_HEADER:
                setHeader(request, response, consultCompTerminalListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ConsultCompTerminalListAction.TERMINAL_DELETE:
            	deleteTerminal(request, consultCompTerminalListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case ConsultCompTerminalListAction.BASE_GRID_EXPORT:
            	findTerminalList(request, consultCompTerminalListForm, response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("consultCompTerminalList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, ConsultCompTerminalListForm consultCompTerminalListForm) throws IOException
    {
        super.setHeader(request, response, consultCompTerminalListForm.getListId(),consultCompTerminalListForm.getCurrentPageId());
    }

    /**
     * grid find
     * @author  kim2107
     * @version $Id: ConsultCompTerminalListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     *
     * @param request
     * @param consultCompTerminalListForm
     * @param response
     * @throws Exception
     */
    private void findTerminalList(HttpServletRequest request, ConsultCompTerminalListForm consultCompTerminalListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	ConsultCompTerminalListService consultCompTerminalListService = (ConsultCompTerminalListService) getBean("consultCompTerminalListService");

    	ConsultCompTerminalCommonDTO consultCompTerminalCommonDTO = consultCompTerminalListForm.getConsultCompTerminalCommonDTO();

        //Paging
        consultCompTerminalCommonDTO.setIsLoadMaxCount("Y".equals(consultCompTerminalListForm.getIsLoadMaxCount())?true:false);
        consultCompTerminalCommonDTO.setFirstRow(consultCompTerminalListForm.getFirstRow());
        consultCompTerminalCommonDTO.setOrderBy(consultCompTerminalListForm.getOrderBy());
        consultCompTerminalCommonDTO.setDirection(consultCompTerminalListForm.getDirection());
        
        User user = getUser(request);
        
        //리스트를 조회한다.
        List resultList = consultCompTerminalListService.findTerminalList(consultCompTerminalCommonDTO, getUser(request));

        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(consultCompTerminalListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = consultCompTerminalListService.findTotalCount(consultCompTerminalCommonDTO,user);
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,consultCompTerminalListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
	}
    /**
     * delete
     * @author  kim21017
     * @version $Id: ConsultCompTerminalListAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     *
     * @param request
     * @param consultCompTerminalListForm
     */
    private void deleteTerminal(HttpServletRequest request, ConsultCompTerminalListForm consultCompTerminalListForm) throws Exception
    {
    	ConsultCompTerminalListService consultCompTerminalListService = (ConsultCompTerminalListService) getBean("consultCompTerminalListService");

    	String[] deleteRows = consultCompTerminalListForm.getDeleteRows();
    	String[] deleteRowsExt = consultCompTerminalListForm.getDeleteRowsExt();

    	consultCompTerminalListService.deleteTerminal(deleteRows, deleteRowsExt, getUser(request));

        setAjaxStatus(request);
    }
}
