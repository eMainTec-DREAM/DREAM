package dream.work.rpt.madeptwo.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.rpt.madeptwo.dto.MaDeptWoListDTO;
import dream.work.rpt.madeptwo.form.MaDeptWoListForm;
import dream.work.rpt.madeptwo.service.MaDeptWoListService;

/**
 * 부서별작업분석 Action
 * @author  kim21017
 * @version $Id: MaDeptWoListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maDeptWoList" name="maDeptWoListForm"
 *                input="/dream/work/rpt/deptwo/maDeptWoList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maDeptWoList" path="/dream/work/rpt/deptwo/maDeptWoList.jsp"
 *                        redirect="false"
 */
public class MaDeptWoListAction extends AuthAction
{
    /** 조회 */
    public static final int WO_LIST_FIND 		= 1001;
    /** 건수차트 */
    public static final int CNT_CHART_FIND 		= 1002;
    /** 시간차트 */
    public static final int TIME_CHART_FIND 		= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaDeptWoListForm maDeptWoListForm = (MaDeptWoListForm) form;
        
        switch (maDeptWoListForm.getStrutsAction())
        {
            case MaDeptWoListAction.WO_LIST_FIND:
                findDeptWoList(request, maDeptWoListForm, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaDeptWoListAction.CNT_CHART_FIND:
                findCntChart(request, maDeptWoListForm, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaDeptWoListAction.TIME_CHART_FIND:
                findTimeChart(request, maDeptWoListForm, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                returnActionForward = mapping.findForward("maDeptWoList");
                break;
        }

        return returnActionForward;
    }

    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaDeptWoListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maDeptWoListForm
     * @param response
     * @throws Exception
     */
    private void findDeptWoList(HttpServletRequest request, MaDeptWoListForm maDeptWoListForm, HttpServletResponse response) throws Exception
    {
    	MaDeptWoListService maDeptWoListService = (MaDeptWoListService) getBean("maDeptWoListService",request);        

    	MaDeptWoListDTO maDeptWoListDTO = maDeptWoListForm.getMaDeptWoListDTO();
    	maDeptWoListDTO.setCompNo(getUser(request).getCompNo());
        //리스트를 조회한다.
        List resultList = maDeptWoListService.findDeptWoList(maDeptWoListDTO,getUser(request));

        super.makeTreeJsonResult(resultList, request, response, maDeptWoListForm.getListId());
	}
    /**
     * 건수 chart find
     * @author  kim2107
     * @version $Id: MaDeptWoListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maDeptWoListForm
     * @param response
     * @throws Exception
     */
    private void findCntChart(HttpServletRequest request, MaDeptWoListForm maDeptWoListForm, HttpServletResponse response) throws IOException
    {
    	MaDeptWoListService maDeptWoListService = (MaDeptWoListService) getBean("maDeptWoListService");        

    	MaDeptWoListDTO maDeptWoListDTO = maDeptWoListForm.getMaDeptWoListDTO();
    	maDeptWoListDTO.setCompNo(getUser(request).getCompNo());
    	maDeptWoListDTO.setUserLang(getUser(request).getLangId());
        //리스트를 조회한다.
        List resultList = maDeptWoListService.findCntChart(maDeptWoListDTO);

        super.makeJsonResult(resultList, request, response, maDeptWoListForm.getListId());
	}
    
    /**
     * 시간 chart find
     * @author  kim2107
     * @version $Id: MaDeptWoListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maDeptWoListForm
     * @param response
     * @throws Exception
     */
    private void findTimeChart(HttpServletRequest request, MaDeptWoListForm maDeptWoListForm, HttpServletResponse response) throws IOException
    {
    	MaDeptWoListService maDeptWoListService = (MaDeptWoListService) getBean("maDeptWoListService");        

    	MaDeptWoListDTO maDeptWoListDTO = maDeptWoListForm.getMaDeptWoListDTO();
    	maDeptWoListDTO.setCompNo(getUser(request).getCompNo());
    	maDeptWoListDTO.setUserLang(getUser(request).getLangId());
        //리스트를 조회한다.
        List resultList = maDeptWoListService.findTimeChart(maDeptWoListDTO);

        super.makeJsonResult(resultList, request, response, maDeptWoListForm.getListId());
	}
}
