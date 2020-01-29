package dream.consult.rpt.maconn.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.consult.rpt.maconn.dto.MaConnChartDTO;
import dream.consult.rpt.maconn.form.MaConnChartForm;
import dream.consult.rpt.maconn.service.MaConnChartService;

/**
 * 접속현황 Action
 * @author  kim21017
 * @version $Id: MaConnChartAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maConnChart" name="maConnChartForm"
 *                input="/dream/consult/rpt/maconn/maConnChart.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maConnChart" path="/dream/consult/rpt/maconn/maConnChart.jsp"
 *                        redirect="false"
 */
public class MaConnChartAction extends AuthAction
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
        MaConnChartForm maConnChartForm = (MaConnChartForm) form;
        
        switch (maConnChartForm.getStrutsAction())
        {
	        case MaConnChartAction.CONN_LIST_FIND:
	            findConnList(request, maConnChartForm, response, false);
	            returnActionForward = mapping.findForward("jsonPage");
	            break;
	        case MaConnChartAction.CONN_USR_LIST_FIND:
	            findUsrList(request, maConnChartForm, response, false);
	            returnActionForward = mapping.findForward("jsonPage");
	            break;
            case MaConnChartAction.CONN_CHART_FIND:
                findConnChart(request, maConnChartForm, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaConnChartAction.BASE_GRID_EXPORT:
                switch(maConnChartForm.getStAct())
                {
                    case MaConnChartAction.CONN_LIST_FIND:
                        findConnList(request, maConnChartForm, response, true);
                        break;
                    case MaConnChartAction.CONN_USR_LIST_FIND:
                        findUsrList(request, maConnChartForm, response, true);
                        break;
                    default:
                        findConnList(request, maConnChartForm, response, true);
                        break;
                }
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maConnChart");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaConnChartAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maConnChartForm
     * @param response
     * @throws Exception
     */
    private void findConnList(HttpServletRequest request, MaConnChartForm maConnChartForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	MaConnChartService maConnChartService = (MaConnChartService) getBean("maConnChartService");        

    	MaConnChartDTO maConnChartDTO = maConnChartForm.getMaConnChartDTO();
    	maConnChartDTO.setCompNo(getUser(request).getCompNo());
        //리스트를 조회한다.
        List resultList = maConnChartService.findConnList(maConnChartDTO, getUser(request));

        if(excelExport) CommonUtil.makeTreeGridExport(resultList, request, response,maConnChartForm);
        else CommonUtil.makeTreeJsonResult(resultList, request, response, maConnChartForm.getListId());
	}
    /**
     * usr grid find
     * @author  kim2107
     * @version $Id: MaConnChartAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maConnChartForm
     * @param response
     * @throws Exception
     */
    private void findUsrList(HttpServletRequest request, MaConnChartForm maConnChartForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	MaConnChartService maConnChartService = (MaConnChartService) getBean("maConnChartService");        

    	MaConnChartDTO maConnChartDTO = maConnChartForm.getMaConnChartDTO();
    	maConnChartDTO.setCompNo(getUser(request).getCompNo());
        //리스트를 조회한다.
        List resultList = maConnChartService.findUsrList(maConnChartDTO, getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,maConnChartForm);
        else CommonUtil.makeJsonResult(resultList, request, response, maConnChartForm.getListId());
	}
    /**
     * chart find
     * @author  kim2107
     * @version $Id: MaConnChartAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maConnChartForm
     * @param response
     * @throws Exception
     */
    private void findConnChart(HttpServletRequest request, MaConnChartForm maConnChartForm, HttpServletResponse response) throws IOException
    {
    	MaConnChartService maConnChartService = (MaConnChartService) getBean("maConnChartService");        

    	MaConnChartDTO maConnChartDTO = maConnChartForm.getMaConnChartDTO();
    	maConnChartDTO.setCompNo(getUser(request).getCompNo());
    	maConnChartDTO.setUserLang(getUser(request).getLangId());
        //리스트를 조회한다.
        List resultList = maConnChartService.findConnChart(maConnChartDTO, getUser(request));

        CommonUtil.makeJsonResult(resultList, request, response, maConnChartForm.getListId());
	}
}
