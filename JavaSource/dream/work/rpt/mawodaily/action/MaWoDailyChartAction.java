package dream.work.rpt.mawodaily.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.rpt.mawodaily.dto.MaWoDailyChartDTO;
import dream.work.rpt.mawodaily.form.MaWoDailyChartForm;
import dream.work.rpt.mawodaily.service.MaWoDailyChartService;

/**
 * 일별작업실행율 Action
 * @author  kim21017
 * @version $Id: MaWoDailyChartAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maWoDailyChart" name="maWoDailyChartForm"
 *                input="/dream/work/rpt/wodaily/maWoDailyChart.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maWoDailyChart" path="/dream/work/rpt/wodaily/maWoDailyChart.jsp"
 *                        redirect="false"
 */
public class MaWoDailyChartAction extends AuthAction
{
    /** 일별작업실행율 조회 */
    public static final int WO_LIST_FIND 		= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaWoDailyChartForm maWoDailyChartForm = (MaWoDailyChartForm) form;
        
        switch (maWoDailyChartForm.getStrutsAction())
        {
            case MaWoDailyChartAction.WO_LIST_FIND:
                findWoList(request, maWoDailyChartForm, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                returnActionForward = mapping.findForward("maWoDailyChart");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaWoDailyChartAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maWoDailyChartForm
     * @param response
     * @throws Exception
     */
    private void findWoList(HttpServletRequest request, MaWoDailyChartForm maWoDailyChartForm, HttpServletResponse response) throws IOException
    {
    	MaWoDailyChartService maWoDailyChartService = (MaWoDailyChartService) getBean("maWoDailyChartService",request);        

    	MaWoDailyChartDTO maWoDailyChartDTO = maWoDailyChartForm.getMaWoDailyChartDTO();
    	maWoDailyChartDTO.setCompNo(getUser(request).getCompNo());
        //리스트를 조회한다.
        List resultList = maWoDailyChartService.findWoList(maWoDailyChartDTO);

        super.makeTreeJsonResult(resultList, request, response, maWoDailyChartForm.getListId());
	}
}
