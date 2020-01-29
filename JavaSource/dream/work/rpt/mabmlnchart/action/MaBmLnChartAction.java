package dream.work.rpt.mabmlnchart.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.rpt.mabmlnchart.dto.MaBmLnChartDTO;
import dream.work.rpt.mabmlnchart.form.MaBmLnChartForm;
import dream.work.rpt.mabmlnchart.service.MaBmLnChartService;

/**
 * 라인고장분석 Action
 * @author  kim21017
 * @version $Id: MaBmLnChartAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maBmLnChart" name="maBmLnChartForm"
 *                input="/dream/work/rpt/bmlnchart/maBmLnChart.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maBmLnChartDetail" name="maBmLnChartForm"
 *                input="/dream/work/rpt/bmlnchart/maBmLnChartDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maBmLnChart" path="/dream/work/rpt/bmlnchart/maBmLnChart.jsp"
 *                        redirect="false"
 */
public class MaBmLnChartAction extends AuthAction
{
    /** 라인고장분석 조회 */
    public static final int BM_LIST_FIND 		= 1001;
    /** 라인고장분석 차트 조회 */
    public static final int BM_CHART_FIND 		= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaBmLnChartForm maBmLnChartForm = (MaBmLnChartForm) form;
        
        switch (maBmLnChartForm.getStrutsAction())
        {
            case MaBmLnChartAction.BM_LIST_FIND:
                findBmList(request, maBmLnChartForm, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaBmLnChartAction.BM_CHART_FIND:
                findBmChart(request, maBmLnChartForm, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 라인고장분석 그리드 find
     * @author  kim2107
     * @version $Id: MaBmLnChartAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maBmLnChartForm
     * @param response
     * @throws Exception
     */
    private void findBmList(HttpServletRequest request, MaBmLnChartForm maBmLnChartForm, HttpServletResponse response) throws IOException
    {
    	MaBmLnChartService maBmLnChartService = (MaBmLnChartService) getBean("maBmLnChartService");        

    	MaBmLnChartDTO maBmLnChartDTO = maBmLnChartForm.getMaBmLnChartDTO();
    	maBmLnChartDTO.setCompNo(getUser(request).getCompNo());
    	maBmLnChartDTO.setUserLang(getUser(request).getLangId());
        //리스트를 조회한다.
        List resultList = maBmLnChartService.findBmList(maBmLnChartDTO);

        super.makeJsonResult(resultList, request, response, maBmLnChartForm.getListId());
	}
    
    /**
     * 라인고장분석 차트 find
     * @author  kim2107
     * @version $Id: MaBmLnChartAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maBmLnChartForm
     * @param response
     * @throws Exception
     */
    private void findBmChart(HttpServletRequest request, MaBmLnChartForm maBmLnChartForm, HttpServletResponse response) throws IOException
    {
    	MaBmLnChartService maBmLnChartService = (MaBmLnChartService) getBean("maBmLnChartService");

    	MaBmLnChartDTO maBmLnChartDTO = maBmLnChartForm.getMaBmLnChartDTO();
    	maBmLnChartDTO.setCompNo(getUser(request).getCompNo());
    	maBmLnChartDTO.setUserLang(getUser(request).getLangId());
        //리스트를 조회한다.
        List resultList = maBmLnChartService.findBmChart(maBmLnChartDTO);

        super.makeJsonResult(resultList, request, response, maBmLnChartForm.getListId());
	}
}
