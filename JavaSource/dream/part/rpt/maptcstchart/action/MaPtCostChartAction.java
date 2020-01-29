package dream.part.rpt.maptcstchart.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.part.rpt.maptcstchart.dto.MaPtCostChartDTO;
import dream.part.rpt.maptcstchart.form.MaPtCostChartForm;
import dream.part.rpt.maptcstchart.service.MaPtCostChartService;

/**
 * ������м� Action
 * @author  
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/maPtCostChart" name="maPtCostChartForm"
 *                input="/dream/part/rpt/maptcstchart/maPtCostChart.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPtCostChart" path="/dream/part/rpt/maptcstchart/maPtCostChart.jsp"
 *                        redirect="false"
 */
public class MaPtCostChartAction extends AuthAction
{
    /** ������м� ��ȸ */
    public static final int PTCOST_LIST_FIND            = 1001;
    /** ������м�-�԰�ݾ� ��Ʈ ��ȸ */
    public static final int PTCOST_REC_CHART_FIND       = 1002;
    /** ������м�-�����ݾ� ��Ʈ ��ȸ */
    public static final int PTCOST_REPAIR_CHART_FIND    = 1003;
    /** ������м�-���� �հ� ��Ʈ ��ȸ */
    public static final int PTCOST_MONTH_TOTAL_CHART_FIND = 1004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaPtCostChartForm maPtCostChartForm = (MaPtCostChartForm) form;
        
        switch (maPtCostChartForm.getStrutsAction())
        {
            case MaPtCostChartAction.PTCOST_LIST_FIND:
                findList(request, maPtCostChartForm, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPtCostChartAction.PTCOST_REC_CHART_FIND:
                findRecChart(request, maPtCostChartForm, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPtCostChartAction.PTCOST_REPAIR_CHART_FIND:
                findRepairChart(request, maPtCostChartForm, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPtCostChartAction.PTCOST_MONTH_TOTAL_CHART_FIND:
                findMonthTotalChart(request, maPtCostChartForm, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                returnActionForward = mapping.findForward("maPtCostChart");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * ������м� �׸��� find
     * @author  
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maPtCostChartForm
     * @param response
     * @throws Exception
     */
    private void findList(HttpServletRequest request, MaPtCostChartForm maPtCostChartForm, HttpServletResponse response) throws IOException
    {
    	MaPtCostChartService maPtCostChartService = (MaPtCostChartService) getBean("maPtCostChartService");        

    	MaPtCostChartDTO maPtCostChartDTO = maPtCostChartForm.getMaPtCostChartDTO();
    	maPtCostChartDTO.setCompNo(getUser(request).getCompNo());
    	maPtCostChartDTO.setUserLang(getUser(request).getLangId());
        //����Ʈ�� ��ȸ�Ѵ�.
        List resultList = maPtCostChartService.findList(maPtCostChartDTO);

        super.makeJsonResult(resultList, request, response, maPtCostChartForm.getListId());
	}
    
    /**
     * ������м� -�԰�ݾ� ��Ʈ find
     * @author  
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maPtCostChartForm
     * @param response
     * @throws Exception
     */
    private void findRecChart(HttpServletRequest request, MaPtCostChartForm maPtCostChartForm, HttpServletResponse response) throws IOException
    {
    	MaPtCostChartService maPtCostChartService = (MaPtCostChartService) getBean("maPtCostChartService");

    	MaPtCostChartDTO maPtCostChartDTO = maPtCostChartForm.getMaPtCostChartDTO();
    	maPtCostChartDTO.setCompNo(getUser(request).getCompNo());
    	maPtCostChartDTO.setUserLang(getUser(request).getLangId());
        //����Ʈ�� ��ȸ�Ѵ�.
        List resultList = maPtCostChartService.findRecChart(maPtCostChartDTO);

        super.makeJsonResult(resultList, request, response, maPtCostChartForm.getListId());
	}
    
    /**
     * ������м� -�����ݾ� ��Ʈ find
     * @author  
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maPtCostChartForm
     * @param response
     * @throws Exception
     */
    private void findRepairChart(HttpServletRequest request, MaPtCostChartForm maPtCostChartForm, HttpServletResponse response) throws IOException
    {
        MaPtCostChartService maPtCostChartService = (MaPtCostChartService) getBean("maPtCostChartService");
        
        MaPtCostChartDTO maPtCostChartDTO = maPtCostChartForm.getMaPtCostChartDTO();
        maPtCostChartDTO.setCompNo(getUser(request).getCompNo());
        maPtCostChartDTO.setUserLang(getUser(request).getLangId());
        //����Ʈ�� ��ȸ�Ѵ�.
        List resultList = maPtCostChartService.findRepairChart(maPtCostChartDTO);
        
        super.makeJsonResult(resultList, request, response, maPtCostChartForm.getListId());
    }
    
    /**
     * ������м� - �����հ� ��Ʈ find
     * @author  
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maPtCostChartForm
     * @param response
     * @throws Exception
     */
    private void findMonthTotalChart(HttpServletRequest request, MaPtCostChartForm maPtCostChartForm, HttpServletResponse response) throws IOException
    {
        MaPtCostChartService maPtCostChartService = (MaPtCostChartService) getBean("maPtCostChartService");
        
        MaPtCostChartDTO maPtCostChartDTO = maPtCostChartForm.getMaPtCostChartDTO();
        maPtCostChartDTO.setCompNo(getUser(request).getCompNo());
        maPtCostChartDTO.setUserLang(getUser(request).getLangId());
        //����Ʈ�� ��ȸ�Ѵ�.
        List resultList = maPtCostChartService.findMonthTotalChart(maPtCostChartDTO);
        
        super.makeJsonResult(resultList, request, response, maPtCostChartForm.getListId());
    }
}
