package dream.consult.program.msg.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.ConsultAuthAction;
import common.util.CommonUtil;
import dream.consult.program.msg.dto.ConsultPgmMsgCommonDTO;
import dream.consult.program.msg.form.ConsultPgmMsgListForm;
import dream.consult.program.msg.service.ConsultPgmMsgListService;

/**
 * 메시지 설정(메일, SMS) - 목록 action
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @struts:action path="/consultPgmMsgList" name="consultPgmMsgListForm"
 *                input="/dream/consult/program/msg/consultPgmMsgList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="consultPgmMsgList" path="/dream/consult/program/msg/consultPgmMsgList.jsp"
 *                        redirect="false"
 */
public class ConsultPgmMsgListAction extends ConsultAuthAction
{
    /** 조회 */
    public static final int FIND_LIST 		= 1001;
    /** 삭제 */
    public static final int DELETE_LIST 	= 1002;

    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        ConsultPgmMsgListForm consultPgmMsgListForm = (ConsultPgmMsgListForm) form;

        switch (consultPgmMsgListForm.getStrutsAction())
        {
            case ConsultPgmMsgListAction.FIND_LIST:
                findList(request, consultPgmMsgListForm, response,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ConsultPgmMsgListAction.BASE_SET_HEADER:
                setHeader(request, response, consultPgmMsgListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ConsultPgmMsgListAction.DELETE_LIST:
            	deleteList(request, consultPgmMsgListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case ConsultPgmMsgListAction.BASE_GRID_EXPORT:
            	findList(request, consultPgmMsgListForm, response,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("consultPgmMsgList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, ConsultPgmMsgListForm consultPgmMsgListForm) throws IOException
    {
        super.setHeader(request, response, consultPgmMsgListForm.getListId(),consultPgmMsgListForm.getCurrentPageId());
    }

    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     *
     * @param request
     * @param consultPgmMsgListForm
     * @param response
     * @throws Exception
     */
    private void findList(HttpServletRequest request, ConsultPgmMsgListForm consultPgmMsgListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	ConsultPgmMsgListService consultPgmMsgListService = (ConsultPgmMsgListService) getBean("consultPgmMsgListService");
    	ConsultPgmMsgCommonDTO consultPgmMsgCommonDTO = consultPgmMsgListForm.getConsultPgmMsgCommonDTO();

    	consultPgmMsgCommonDTO.setIsLoadMaxCount("Y".equals(consultPgmMsgListForm.getIsLoadMaxCount())?true:false);
    	consultPgmMsgCommonDTO.setFirstRow(consultPgmMsgListForm.getFirstRow());
    	consultPgmMsgCommonDTO.setOrderBy(consultPgmMsgListForm.getOrderBy());
    	consultPgmMsgCommonDTO.setDirection(consultPgmMsgListForm.getDirection());
    	
        //리스트를 조회한다.
        List resultList = consultPgmMsgListService.findList(consultPgmMsgCommonDTO, getUser(request));

        String totalCount = "";
        if(Integer.parseInt(consultPgmMsgListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = consultPgmMsgListService.findTotalCount(consultPgmMsgCommonDTO,getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,consultPgmMsgListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
	}
    /**
     * delete
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     *SSSS
     * @param request
     * @param consultPgmMsgListForm
     */
    private void deleteList(HttpServletRequest request, ConsultPgmMsgListForm consultPgmMsgListForm) throws Exception
    {
    	ConsultPgmMsgListService consultPgmMsgListService = (ConsultPgmMsgListService) getBean("consultPgmMsgListService");

    	String[] deleteRows = consultPgmMsgListForm.getDeleteRows();

    	consultPgmMsgListService.deleteList(deleteRows, getUser(request));

        setAjaxStatus(request);
    }
}
