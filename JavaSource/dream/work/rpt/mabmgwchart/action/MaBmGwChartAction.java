package dream.work.rpt.mabmgwchart.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.rpt.mabmgwchart.dto.MaBmGwChartDTO;
import dream.work.rpt.mabmgwchart.form.MaBmGwChartForm;
import dream.work.rpt.mabmgwchart.service.MaBmGwChartService;

/**
 * ��������м� Action
 * @author  kim21017
 * @version $Id: MaBmGwChartAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maBmGwChart" name="maBmGwChartForm"
 *                input="/dream/work/rpt/bmgwchart/maBmGwChart.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maBmGwChartDetail" name="maBmGwChartForm"
 *                input="/dream/work/rpt/bmgwchart/maBmGwChartDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maBmGwChart" path="/dream/work/rpt/bmgwchart/maBmGwChart.jsp"
 *                        redirect="false"
 */
public class MaBmGwChartAction extends AuthAction
{
    /** ��������м� ��ȸ */
    public static final int BM_LIST_FIND 		= 1001;
    /** ��������м� ��Ʈ ��ȸ */
    public static final int BM_CHART_FIND 		= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaBmGwChartForm maBmGwChartForm = (MaBmGwChartForm) form;
        
        switch (maBmGwChartForm.getStrutsAction())
        {
            case MaBmGwChartAction.BM_LIST_FIND:
                findBmList(request, maBmGwChartForm, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaBmGwChartAction.BM_CHART_FIND:
                findBmChart(request, maBmGwChartForm, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * ��������м� �׸��� find
     * @author  kim2107
     * @version $Id: MaBmGwChartAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maBmGwChartForm
     * @param response
     * @throws Exception
     */
    private void findBmList(HttpServletRequest request, MaBmGwChartForm maBmGwChartForm, HttpServletResponse response) throws IOException
    {
    	MaBmGwChartService maBmGwChartService = (MaBmGwChartService) getBean("maBmGwChartService");        

    	MaBmGwChartDTO maBmGwChartDTO = maBmGwChartForm.getMaBmGwChartDTO();
    	maBmGwChartDTO.setCompNo(getUser(request).getCompNo());
    	maBmGwChartDTO.setUserLang(getUser(request).getLangId());
        //����Ʈ�� ��ȸ�Ѵ�.
        List resultList = maBmGwChartService.findBmList(maBmGwChartDTO);

        super.makeJsonResult(resultList, request, response, maBmGwChartForm.getListId());
	}
    
    /**
     * ��������м� ��Ʈ find
     * @author  kim2107
     * @version $Id: MaBmGwChartAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maBmGwChartForm
     * @param response
     * @throws Exception
     */
    private void findBmChart(HttpServletRequest request, MaBmGwChartForm maBmGwChartForm, HttpServletResponse response) throws IOException
    {
    	MaBmGwChartService maBmGwChartService = (MaBmGwChartService) getBean("maBmGwChartService");

    	MaBmGwChartDTO maBmGwChartDTO = maBmGwChartForm.getMaBmGwChartDTO();
    	maBmGwChartDTO.setCompNo(getUser(request).getCompNo());
    	maBmGwChartDTO.setUserLang(getUser(request).getLangId());
        //����Ʈ�� ��ȸ�Ѵ�.
        List resultList = maBmGwChartService.findBmChart(maBmGwChartDTO);

        super.makeJsonResult(resultList, request, response, maBmGwChartForm.getListId());
	}
}
