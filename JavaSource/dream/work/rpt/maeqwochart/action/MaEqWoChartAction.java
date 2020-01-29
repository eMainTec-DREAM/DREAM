package dream.work.rpt.maeqwochart.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.rpt.maeqwochart.dto.MaEqWoChartDTO;
import dream.work.rpt.maeqwochart.form.MaEqWoChartForm;
import dream.work.rpt.maeqwochart.service.MaEqWoChartService;

/**
 * 설비작업현황 Action
 * @author  kim21017
 * @version $Id: MaEqWoChartAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maEqWoChart" name="maEqWoChartForm"
 *                input="/dream/work/rpt/eqwochart/maEqWoChart.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maEqWoChart" path="/dream/work/rpt/eqwochart/maEqWoChart.jsp"
 *                        redirect="false"
 */
public class MaEqWoChartAction extends AuthAction
{
    /** 전체작업현황 조회 */
    public static final int WO_LIST_FIND 		= 1001;
    /** 예방작업현황 조회 */
    public static final int PM_LIST_FIND 		= 1002;
    /** 부품입고 조회 */
    public static final int PT_LIST_FIND 		= 1003;
    /** 부품사용 조회 */
    public static final int USEPT_LIST_FIND 	= 1004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaEqWoChartForm maEqWoChartForm = (MaEqWoChartForm) form;
        
        switch (maEqWoChartForm.getStrutsAction())
        {
            case MaEqWoChartAction.WO_LIST_FIND:
                findWoList(request, maEqWoChartForm, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaEqWoChartAction.PM_LIST_FIND:
                findPmList(request, maEqWoChartForm, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaEqWoChartAction.PT_LIST_FIND:
                findPtList(request, maEqWoChartForm, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaEqWoChartAction.USEPT_LIST_FIND:
                findUsePtList(request, maEqWoChartForm, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                returnActionForward = mapping.findForward("maEqWoChart");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * wogrid find
     * @author  kim2107
     * @version $Id: MaEqWoChartAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqWoChartForm
     * @param response
     * @throws Exception
     */
    private void findWoList(HttpServletRequest request, MaEqWoChartForm maEqWoChartForm, HttpServletResponse response) throws IOException
    {
    	MaEqWoChartService maEqWoChartService = (MaEqWoChartService) getBean("maEqWoChartService");        

    	MaEqWoChartDTO maEqWoChartDTO = maEqWoChartForm.getMaEqWoChartDTO();
    	maEqWoChartDTO.setCompNo(getUser(request).getCompNo());
    	maEqWoChartDTO.setUserLang(getUser(request).getLangId());
        //리스트를 조회한다.
        List resultList = maEqWoChartService.findWoList(maEqWoChartDTO);

        super.makeJsonResult(resultList, request, response, maEqWoChartForm.getListId());
	}
    
    /**
     * pmgrid find
     * @author  kim2107
     * @version $Id: MaEqWoChartAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqWoChartForm
     * @param response
     * @throws Exception
     */
    private void findPmList(HttpServletRequest request, MaEqWoChartForm maEqWoChartForm, HttpServletResponse response) throws IOException
    {
    	MaEqWoChartService maEqWoChartService = (MaEqWoChartService) getBean("maEqWoChartService");        

    	MaEqWoChartDTO maEqWoChartDTO = maEqWoChartForm.getMaEqWoChartDTO();
    	maEqWoChartDTO.setCompNo(getUser(request).getCompNo());
    	maEqWoChartDTO.setUserLang(getUser(request).getLangId());
        //리스트를 조회한다.
        List resultList = maEqWoChartService.findPmList(maEqWoChartDTO);

        super.makeJsonResult(resultList, request, response, maEqWoChartForm.getListId());
	}
    /**
     * ptgrid find
     * @author  kim2107
     * @version $Id: MaEqWoChartAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqWoChartForm
     * @param response
     * @throws Exception
     */
    private void findPtList(HttpServletRequest request, MaEqWoChartForm maEqWoChartForm, HttpServletResponse response) throws IOException
    {
    	MaEqWoChartService maEqWoChartService = (MaEqWoChartService) getBean("maEqWoChartService");        

    	MaEqWoChartDTO maEqWoChartDTO = maEqWoChartForm.getMaEqWoChartDTO();
    	maEqWoChartDTO.setCompNo(getUser(request).getCompNo());
    	maEqWoChartDTO.setUserLang(getUser(request).getLangId());
        //리스트를 조회한다.
        List resultList = maEqWoChartService.findPtList(maEqWoChartDTO);

        super.makeJsonResult(resultList, request, response, maEqWoChartForm.getListId());
	}
    /**
     * useptgrid find
     * @author  kim2107
     * @version $Id: MaEqWoChartAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqWoChartForm
     * @param response
     * @throws Exception
     */
    private void findUsePtList(HttpServletRequest request, MaEqWoChartForm maEqWoChartForm, HttpServletResponse response) throws IOException
    {
    	MaEqWoChartService maEqWoChartService = (MaEqWoChartService) getBean("maEqWoChartService");        

    	MaEqWoChartDTO maEqWoChartDTO = maEqWoChartForm.getMaEqWoChartDTO();
    	maEqWoChartDTO.setCompNo(getUser(request).getCompNo());
    	maEqWoChartDTO.setUserLang(getUser(request).getLangId());
        //리스트를 조회한다.
        List resultList = maEqWoChartService.findUsePtList(maEqWoChartDTO);

        super.makeJsonResult(resultList, request, response, maEqWoChartForm.getListId());
	}
}
