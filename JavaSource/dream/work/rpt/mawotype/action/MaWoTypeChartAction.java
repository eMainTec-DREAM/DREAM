package dream.work.rpt.mawotype.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.rpt.mawotype.dto.MaWoTypeChartDTO;
import dream.work.rpt.mawotype.form.MaWoTypeChartForm;
import dream.work.rpt.mawotype.service.MaWoTypeChartService;

/**
 * 작업유형별현황 Action
 * @author  kim21017
 * @version $Id: MaWoTypeChartAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maWoTypeChart" name="maWoTypeChartForm"
 *                input="/dream/work/rpt/wotype/maWoTypeChart.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maWoTypeChart" path="/dream/work/rpt/wotype/maWoTypeChart.jsp"
 *                        redirect="false"
 */
public class MaWoTypeChartAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND 				= 1001;
    /** 작업건수율 차트조회 */
    public static final int WOCNT_CHART_FIND 		= 1002;
    /** 작업시간율 차트조회 */
    public static final int WOTIME_CHART_FIND 		= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaWoTypeChartForm maWoTypeChartForm = (MaWoTypeChartForm) form;
        
        switch (maWoTypeChartForm.getStrutsAction())
        {
            case MaWoTypeChartAction.LIST_FIND:
                findList(request, maWoTypeChartForm, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaWoTypeChartAction.WOCNT_CHART_FIND:
            	findWoCntChart(request, maWoTypeChartForm, response);
                returnActionForward = mapping.findForward("jsonPage");
            	break;
            case MaWoTypeChartAction.WOTIME_CHART_FIND:
            	findWoTimeChart(request, maWoTypeChartForm, response);
                returnActionForward = mapping.findForward("jsonPage");
            	break;
            default:
                returnActionForward = mapping.findForward("maWoTypeChart");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaWoTypeChartAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maWoTypeChartForm
     * @param response
     * @throws Exception
     */
    private void findList(HttpServletRequest request, MaWoTypeChartForm maWoTypeChartForm, HttpServletResponse response) throws IOException
    {
    	MaWoTypeChartService maWoTypeChartService = (MaWoTypeChartService) getBean("maWoTypeChartService");        

    	MaWoTypeChartDTO maWoTypeChartDTO = maWoTypeChartForm.getMaWoTypeChartDTO();
    	maWoTypeChartDTO.setCompNo(getUser(request).getCompNo());
    	maWoTypeChartDTO.setUserLang(getUser(request).getLangId());
        //리스트를 조회한다.
        List resultList = maWoTypeChartService.findList(maWoTypeChartDTO);

        super.makeJsonResult(resultList, request, response, maWoTypeChartForm.getListId());
	}
    /**
     * wo cnt chart find
     * @author  kim2107
     * @version $Id: MaWoTypeChartAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maWoTypeChartForm
     * @param response
     * @throws Exception
     */
    private void findWoCntChart(HttpServletRequest request, MaWoTypeChartForm maWoTypeChartForm, HttpServletResponse response) throws IOException
    {
    	MaWoTypeChartService maWoTypeChartService = (MaWoTypeChartService) getBean("maWoTypeChartService");        

    	MaWoTypeChartDTO maWoTypeChartDTO = maWoTypeChartForm.getMaWoTypeChartDTO();
    	maWoTypeChartDTO.setCompNo(getUser(request).getCompNo());
        //리스트를 조회한다.
        List resultList = maWoTypeChartService.findWoCntChart(maWoTypeChartDTO);

        super.makeJsonResult(resultList, request, response, maWoTypeChartForm.getListId());
	}
    /**
     * wo time chart find
     * @author  kim2107
     * @version $Id: MaWoTypeChartAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maWoTypeChartForm
     * @param response
     * @throws Exception
     */
    private void findWoTimeChart(HttpServletRequest request, MaWoTypeChartForm maWoTypeChartForm, HttpServletResponse response) throws IOException
    {
    	MaWoTypeChartService maWoTypeChartService = (MaWoTypeChartService) getBean("maWoTypeChartService");        

    	MaWoTypeChartDTO maWoTypeChartDTO = maWoTypeChartForm.getMaWoTypeChartDTO();
    	maWoTypeChartDTO.setCompNo(getUser(request).getCompNo());
        //리스트를 조회한다.
        List resultList = maWoTypeChartService.findWoTimeChart(maWoTypeChartDTO);

        super.makeJsonResult(resultList, request, response, maWoTypeChartForm.getListId());
	}
}
