package dream.conn.rpt.month.log.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.conn.rpt.month.log.dto.ConnRptMonthLogListDTO;
import dream.conn.rpt.month.log.form.ConnRptMonthLogListForm;
import dream.conn.rpt.month.log.service.ConnRptMonthLogListService;

/**
 * 월별접속현황 Action
 * @author  sy.yang
 * @version $Id: ConnRptMonthLogListAction.java,v 1.0 2015/12/02 09:10:21 sy.yang Exp $
 * @since   1.0
 * @struts:action path="/connRptMonthLogList" name="connRptMonthLogListForm"
 *                input="/dream/conn/rpt/month/log/connRptMonthLogList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="connRptMonthLogList" path="/dream/conn/rpt/month/log/connRptMonthLogList.jsp"
 *                        redirect="false"
 */
public class ConnRptMonthLogListAction extends AuthAction
{
    /** 접속현황 리스트 조회 */
    public static final int CONN_LIST_FIND			= 1001;
    /** 접속현황 차트 조회 */
    public static final int CONN_CHART_FIND			= 1002;
    /** 접속자 현황 리스트 조회 */
    public static final int CONN_USR_LIST_FIND		= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        ConnRptMonthLogListForm connRptMonthLogListForm = (ConnRptMonthLogListForm) form;
        
        switch (connRptMonthLogListForm.getStrutsAction())
        {
	        case ConnRptMonthLogListAction.CONN_LIST_FIND:
	            findConnList(request, connRptMonthLogListForm, response);
	            returnActionForward = mapping.findForward("jsonPage");
	            break;
            case ConnRptMonthLogListAction.CONN_CHART_FIND:
                findConnChart(request, connRptMonthLogListForm, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
	        case ConnRptMonthLogListAction.CONN_USR_LIST_FIND:
	        	findUsrList(request, connRptMonthLogListForm, response);
	            returnActionForward = mapping.findForward("jsonPage");
	            break;
            default:
                returnActionForward = mapping.findForward("connRptMonthLogList");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  sy.yang
     * @version $Id: ConnRptMonthLogListAction.java,v 1.0 2015/12/02 09:10:21 sy.yang Exp $
     * @since   1.0
     * 
     * @param request
     * @param connRptMonthLogListForm
     * @param response
     * @throws Exception
     */
    private void findConnList(HttpServletRequest request, ConnRptMonthLogListForm connRptMonthLogListForm, HttpServletResponse response) throws IOException
    {
    	ConnRptMonthLogListService connRptMonthLogListService = (ConnRptMonthLogListService) getBean("connRptMonthLogListService");        

    	ConnRptMonthLogListDTO connRptMonthLogListDTO = connRptMonthLogListForm.getConnRptMonthLogListDTO();

    	//리스트를 조회한다.
        List resultList = connRptMonthLogListService.findConnList(connRptMonthLogListDTO, getUser(request));

        super.makeTreeJsonResult(resultList, request, response, connRptMonthLogListForm.getListId());
	}
    /**
     * chart find
     * @author  sy.yang
     * @version $Id: ConnRptMonthLogListAction.java,v 1.0 2015/12/02 09:10:21 sy.yang Exp $
     * @since   1.0
     * 
     * @param request
     * @param connRptMonthLogListForm
     * @param response
     * @throws Exception
     */
    private void findConnChart(HttpServletRequest request, ConnRptMonthLogListForm connRptMonthLogListForm, HttpServletResponse response) throws IOException
    {
    	ConnRptMonthLogListService connRptMonthLogListService = (ConnRptMonthLogListService) getBean("connRptMonthLogListService");        

    	ConnRptMonthLogListDTO connRptMonthLogListDTO = connRptMonthLogListForm.getConnRptMonthLogListDTO();

    	//리스트를 조회한다.
        List resultList = connRptMonthLogListService.findConnChart(connRptMonthLogListDTO,getUser(request));

        super.makeJsonResult(resultList, request, response, connRptMonthLogListForm.getListId());
	}
    /**
     * usr grid find
     * @author  syyang
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param connRptMonthLogListForm
     * @param response
     * @throws Exception
     */
    private void findUsrList(HttpServletRequest request, ConnRptMonthLogListForm connRptMonthLogListForm, HttpServletResponse response) throws IOException
    {
    	ConnRptMonthLogListService connRptMonthLogListService = (ConnRptMonthLogListService) getBean("connRptMonthLogListService");        

    	ConnRptMonthLogListDTO connRptMonthLogListDTO = connRptMonthLogListForm.getConnRptMonthLogListDTO();
        //리스트를 조회한다.
        List resultList = connRptMonthLogListService.findUsrList(connRptMonthLogListDTO, getUser(request));

        super.makeJsonResult(resultList, request, response, connRptMonthLogListForm.getListId());
	} 
}
