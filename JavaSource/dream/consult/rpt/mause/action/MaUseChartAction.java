package dream.consult.rpt.mause.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.consult.rpt.mause.dto.MaUseChartDTO;
import dream.consult.rpt.mause.form.MaUseChartForm;
import dream.consult.rpt.mause.service.MaUseChartService;

/**
 * 사용현황 Action
 * @author  kim21017
 * @version $Id: MaUseChartAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maUseChart" name="maUseChartForm"
 *                input="/dream/consult/rpt/mause/maUseChart.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maUseChart" path="/dream/consult/rpt/mause/maUseChart.jsp"
 *                        redirect="false"
 */
public class MaUseChartAction extends AuthAction
{
    /** 사용현황 리스트 조회 */
    public static final int USE_LIST_FIND			= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaUseChartForm maUseChartForm = (MaUseChartForm) form;
        
        switch (maUseChartForm.getStrutsAction())
        {
	        case MaUseChartAction.USE_LIST_FIND:
	            findUseList(request, maUseChartForm, response, false);
	            returnActionForward = mapping.findForward("jsonPage");
	            break;
	        case MaUseChartAction.BASE_GRID_EXPORT:
	            findUseList(request, maUseChartForm, response, true);
	            returnActionForward = new ActionForward("/gridExport");
	            break;
            default:
                returnActionForward = mapping.findForward("maUseChart");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaUseChartAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maUseChartForm
     * @param response
     * @throws Exception
     */
    private void findUseList(HttpServletRequest request, MaUseChartForm maUseChartForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	MaUseChartService maUseChartService = (MaUseChartService) getBean("maUseChartService");        

    	MaUseChartDTO maUseChartDTO = maUseChartForm.getMaUseChartDTO();
    	maUseChartDTO.setCompNo(getUser(request).getCompNo());
    	maUseChartDTO.setUserLang(getUser(request).getLangId());
    	
    	//Paging
        maUseChartDTO.setIsLoadMaxCount("Y".equals(maUseChartForm.getIsLoadMaxCount())?true:false);
        maUseChartDTO.setFirstRow(maUseChartForm.getFirstRow());
        maUseChartDTO.setOrderBy(maUseChartForm.getOrderBy());
        maUseChartDTO.setDirection(maUseChartForm.getDirection());
        
        //리스트를 조회한다.
        List resultList = maUseChartService.findUseList(maUseChartDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(maUseChartForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maUseChartService.findTotalCount(maUseChartDTO, getUser(request));
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, maUseChartForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
	}
}
