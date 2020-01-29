package dream.work.rpt.mapmtrend.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.work.rpt.mapmtrend.dto.MaPmTrendChartDTO;
import dream.work.rpt.mapmtrend.form.MaPmTrendChartForm;
import dream.work.rpt.mapmtrend.service.MaPmTrendChartService;
import dream.work.rpt.org.bd.action.WorkRptOrgBdListAction;

/**
 * 예방점검경향분석 Action
 * @author  kim21017
 * @version $Id: MaPmTrendChartAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maPmTrendChart" name="maPmTrendChartForm"
 *                input="/dream/work/rpt/pmtrend/maPmTrendChart.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPmTrendChart" path="/dream/work/rpt/pmtrend/maPmTrendChart.jsp"
 *                        redirect="false"
 */
public class MaPmTrendChartAction extends AuthAction
{
    /** 예방점검경향분석 조회 */
    public static final int PMTREND_LIST_FIND 		= 1001;
    /** 예방점검경향분석 차트 조회 */
    public static final int PMTREND_CHART_FIND 	= 1002;
    /** 조치 WO 발행 */
    public static final int WO_CREATE 		        = 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaPmTrendChartForm maPmTrendChartForm = (MaPmTrendChartForm) form;
        
        switch (maPmTrendChartForm.getStrutsAction())
        {
            case MaPmTrendChartAction.PMTREND_LIST_FIND:
            	findPmList(request, maPmTrendChartForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPmTrendChartAction.PMTREND_CHART_FIND:
                findPmChart(request, maPmTrendChartForm, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPmTrendChartAction.WO_CREATE:
                createWo(request, maPmTrendChartForm, response);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaPmTrendChartAction.BASE_GRID_EXPORT:
            	findPmList(request, maPmTrendChartForm, response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            case MaPmTrendChartAction.BASE_SET_HEADER:
                setHeader(request, response, maPmTrendChartForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaPmTrendChartForm maPmTrendChartForm) throws IOException
    {
        super.setHeader(request, response, maPmTrendChartForm.getListId(), maPmTrendChartForm.getCurrentPageId()); 
    }
    /**
     * 예방점검경향분석 그리드 find
     * @author  kim2107
     * @version $Id: MaPmTrendChartAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maPmTrendChartForm
     * @param response
     * @throws Exception
     */
    private void findPmList(HttpServletRequest request, MaPmTrendChartForm maPmTrendChartForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	MaPmTrendChartService maPmTrendChartService = (MaPmTrendChartService) getBean("maPmTrendChartService");        

    	MaPmTrendChartDTO maPmTrendChartDTO = maPmTrendChartForm.getMaPmTrendChartDTO();
    	maPmTrendChartDTO.setCompNo(getUser(request).getCompNo());
    	maPmTrendChartDTO.setUserLang(getUser(request).getLangId());
        
    	//Paging
    	maPmTrendChartDTO.setIsLoadMaxCount("Y".equals(maPmTrendChartForm.getIsLoadMaxCount())?true:false);
    	maPmTrendChartDTO.setFirstRow(maPmTrendChartForm.getFirstRow());
    	maPmTrendChartDTO.setOrderBy(maPmTrendChartForm.getOrderBy());
    	maPmTrendChartDTO.setDirection(maPmTrendChartForm.getDirection());
    	
    	//리스트를 조회한다.
        List resultList = maPmTrendChartService.findPmList(maPmTrendChartDTO,getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(maPmTrendChartForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maPmTrendChartService.findTotalCount(maPmTrendChartDTO,getUser(request));
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, maPmTrendChartForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
	}
    
    /**
     * 예방점검경향분석 차트 find
     * @author  kim2107
     * @version $Id: MaPmTrendChartAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maPmTrendChartForm
     * @param response
     * @throws Exception
     */
    private void findPmChart(HttpServletRequest request, MaPmTrendChartForm maPmTrendChartForm, HttpServletResponse response) throws IOException
    {
    	MaPmTrendChartService maPmTrendChartService = (MaPmTrendChartService) getBean("maPmTrendChartService");

    	MaPmTrendChartDTO maPmTrendChartDTO = maPmTrendChartForm.getMaPmTrendChartDTO();
    	maPmTrendChartDTO.setCompNo(getUser(request).getCompNo());
    	maPmTrendChartDTO.setUserLang(getUser(request).getLangId());
        //리스트를 조회한다.
        List resultList = maPmTrendChartService.findPmChart(maPmTrendChartDTO);

        super.makeJsonResult(resultList, request, response, maPmTrendChartForm.getListId());
	}
    
    private void createWo(HttpServletRequest request, MaPmTrendChartForm maPmTrendChartForm, HttpServletResponse response) throws Exception
    {
        MaPmTrendChartService maPmTrendChartService = (MaPmTrendChartService) getBean("maPmTrendChartService");
        MaPmTrendChartDTO maPmTrendChartDTO = maPmTrendChartForm.getMaPmTrendChartDTO();
        
        maPmTrendChartDTO.setCompNo(getUser(request).getCompNo());
        maPmTrendChartDTO.setUserLang(getUser(request).getLangId());

        maPmTrendChartService.createWo(maPmTrendChartDTO,getUser(request));
        
        setAjaxStatus(request);
    }
}
