package dream.work.rpt.mabmctgchart.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.rpt.mabmctgchart.dto.MaBmCtgChartDTO;
import dream.work.rpt.mabmctgchart.form.MaBmCtgChartForm;
import dream.work.rpt.mabmctgchart.service.MaBmCtgChartService;

/**
 * ��������������м� Action
 * @author  kim21017
 * @version $Id: MaBmCtgChartAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maBmCtgChart" name="maBmCtgChartForm"
 *                input="/dream/work/rpt/bmctgchart/maBmCtgChart.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maBmCtgChartDetail" name="maBmCtgChartForm"
 *                input="/dream/work/rpt/bmctgchart/maBmCtgChartDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maBmCtgChart" path="/dream/work/rpt/bmctgchart/maBmCtgChart.jsp"
 *                        redirect="false"
 */
public class MaBmCtgChartAction extends AuthAction
{
    /** ��������������м� ��ȸ */
    public static final int BM_LIST_FIND 		= 1001;
    /** ��������������м� ��Ʈ ��ȸ */
    public static final int BM_CHART_FIND 		= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaBmCtgChartForm maBmCtgChartForm = (MaBmCtgChartForm) form;
        
        switch (maBmCtgChartForm.getStrutsAction())
        {
            case MaBmCtgChartAction.BM_LIST_FIND:
                findBmList(request, maBmCtgChartForm, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaBmCtgChartAction.BM_CHART_FIND:
                findBmChart(request, maBmCtgChartForm, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * ��������������м� �׸��� find
     * @author  kim2107
     * @version $Id: MaBmCtgChartAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maBmCtgChartForm
     * @param response
     * @throws Exception
     */
    private void findBmList(HttpServletRequest request, MaBmCtgChartForm maBmCtgChartForm, HttpServletResponse response) throws IOException
    {
    	MaBmCtgChartService maBmCtgChartService = (MaBmCtgChartService) getBean("maBmCtgChartService");        

    	MaBmCtgChartDTO maBmCtgChartDTO = maBmCtgChartForm.getMaBmCtgChartDTO();
    	maBmCtgChartDTO.setCompNo(getUser(request).getCompNo());
    	maBmCtgChartDTO.setUserLang(getUser(request).getLangId());
        //����Ʈ�� ��ȸ�Ѵ�.
        List resultList = maBmCtgChartService.findBmList(maBmCtgChartDTO, getUser(request));

        super.makeJsonResult(resultList, request, response, maBmCtgChartForm.getListId());
	}
    
    /**
     * ��������������м� ��Ʈ find
     * @author  kim2107
     * @version $Id: MaBmCtgChartAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maBmCtgChartForm
     * @param response
     * @throws Exception
     */
    private void findBmChart(HttpServletRequest request, MaBmCtgChartForm maBmCtgChartForm, HttpServletResponse response) throws IOException
    {
    	MaBmCtgChartService maBmCtgChartService = (MaBmCtgChartService) getBean("maBmCtgChartService");

    	MaBmCtgChartDTO maBmCtgChartDTO = maBmCtgChartForm.getMaBmCtgChartDTO();

    	//����Ʈ�� ��ȸ�Ѵ�.
        List resultList = maBmCtgChartService.findBmChart(maBmCtgChartDTO, getUser(request));

        super.makeJsonResult(resultList, request, response, maBmCtgChartForm.getListId());
	}
}
