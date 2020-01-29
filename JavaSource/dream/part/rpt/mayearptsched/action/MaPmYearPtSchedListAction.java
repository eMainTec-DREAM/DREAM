package dream.part.rpt.mayearptsched.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.part.rpt.mayearptsched.dto.MaPmYearPtSchedCommonDTO;
import dream.part.rpt.mayearptsched.form.MaPmYearPtSchedListForm;
import dream.part.rpt.mayearptsched.service.MaPmYearPtSchedListService;

/**
 * 연간부품사용일정 Action
 * @author  ssong
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/maPmYearPtSchedList" name="maPmYearPtSchedListForm"
 *                input="/dream/part/rpt/mayearptsched/maPmYearPtSchedList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPmYearPtSchedList" path="/dream/part/rpt/mayearptsched/maPmYearPtSchedList.jsp"
 *                        redirect="false"
 */
public class MaPmYearPtSchedListAction extends AuthAction
{
    /** 전체작업현황 조회 */
    public static final int PMYEAR_LIST_FIND    = 1001;
    /** 예방작업현황 조회 */
    public static final int PMPARTS_LIST_FIND   = 1002;
    /** 부품입고 조회 */
    public static final int PMDATE_LIST_FIND    = 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaPmYearPtSchedListForm maPmYearPtSchedListForm = (MaPmYearPtSchedListForm) form;
        
        switch (maPmYearPtSchedListForm.getStrutsAction())
        {
            case MaPmYearPtSchedListAction.BASE_SET_HEADER:
                setHeader(request, response, maPmYearPtSchedListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPmYearPtSchedListAction.PMYEAR_LIST_FIND:
                findYearList(request, maPmYearPtSchedListForm, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPmYearPtSchedListAction.PMPARTS_LIST_FIND:
                findPartsList(request, maPmYearPtSchedListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPmYearPtSchedListAction.PMDATE_LIST_FIND:
                findDateList(request, maPmYearPtSchedListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPmYearPtSchedListAction.BASE_GRID_EXPORT:
                findYearList(request, maPmYearPtSchedListForm, response);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maPmYearPtSchedList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaPmYearPtSchedListForm maPmYearPtSchedListForm) throws IOException
    {
        super.setHeader(request, response, maPmYearPtSchedListForm.getListId(), maPmYearPtSchedListForm.getCurrentPageId()); 
    }
    
    /**
     * 연간사용내역 grid find
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param maPmYearPtSchedListForm
     * @param response
     * @throws Exception
     */
    private void findYearList(HttpServletRequest request, MaPmYearPtSchedListForm maPmYearPtSchedListForm, HttpServletResponse response) throws IOException
    {
    	MaPmYearPtSchedListService maPmYearPtSchedListService = (MaPmYearPtSchedListService) getBean("maPmYearPtSchedListService");        

    	MaPmYearPtSchedCommonDTO maPmYearPtSchedCommonDTO = maPmYearPtSchedListForm.getMaPmYearPtSchedCommonDTO();
    	maPmYearPtSchedCommonDTO.setCompNo(getUser(request).getCompNo());
    	maPmYearPtSchedCommonDTO.setUserLang(getUser(request).getLangId());
        //리스트를 조회한다.
        List resultList = maPmYearPtSchedListService.findYearList(maPmYearPtSchedCommonDTO, getUser(request));

        super.makeTreeJsonResult(resultList, request, response, maPmYearPtSchedListForm.getListId());
	}
    
    /**
     * 부품별 grid find
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param maPmYearPtSchedListForm
     * @param response
     * @throws Exception
     */
    private void findPartsList(HttpServletRequest request, MaPmYearPtSchedListForm maPmYearPtSchedListForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	MaPmYearPtSchedListService maPmYearPtSchedListService = (MaPmYearPtSchedListService) getBean("maPmYearPtSchedListService");        

    	MaPmYearPtSchedCommonDTO maPmYearPtSchedCommonDTO = maPmYearPtSchedListForm.getMaPmYearPtSchedCommonDTO();
    	maPmYearPtSchedCommonDTO.setCompNo(getUser(request).getCompNo());
    	maPmYearPtSchedCommonDTO.setUserLang(getUser(request).getLangId());
    	
    	//Paging
    	maPmYearPtSchedCommonDTO.setIsLoadMaxCount("Y".equals(maPmYearPtSchedListForm.getIsLoadMaxCount())?true:false);
    	maPmYearPtSchedCommonDTO.setFirstRow(maPmYearPtSchedListForm.getFirstRow());
    	maPmYearPtSchedCommonDTO.setOrderBy(maPmYearPtSchedListForm.getOrderBy());
    	maPmYearPtSchedCommonDTO.setDirection(maPmYearPtSchedListForm.getDirection());

        //리스트를 조회한다.
        List resultList = maPmYearPtSchedListService.findPartsList(maPmYearPtSchedCommonDTO, getUser(request));
    	        
    	//Paging
    	String totalCount = "";
    	        
    	if(Integer.parseInt(maPmYearPtSchedListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maPmYearPtSchedListService.findPartsTotalCount(maPmYearPtSchedCommonDTO,getUser(request));
    	        
    	if(excelExport) CommonUtil.makeGridExport(resultList, request, response, maPmYearPtSchedListForm);
    	else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
	}
    
    /**
     * 날짜별 grid find
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param maPmYearPtSchedListForm
     * @param response
     * @throws Exception
     */
    private void findDateList(HttpServletRequest request, MaPmYearPtSchedListForm maPmYearPtSchedListForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	MaPmYearPtSchedListService maPmYearPtSchedListService = (MaPmYearPtSchedListService) getBean("maPmYearPtSchedListService");        

    	MaPmYearPtSchedCommonDTO maPmYearPtSchedCommonDTO = maPmYearPtSchedListForm.getMaPmYearPtSchedCommonDTO();
    	maPmYearPtSchedCommonDTO.setCompNo(getUser(request).getCompNo());
    	maPmYearPtSchedCommonDTO.setUserLang(getUser(request).getLangId());

    	//Paging
    	maPmYearPtSchedCommonDTO.setIsLoadMaxCount("Y".equals(maPmYearPtSchedListForm.getIsLoadMaxCount())?true:false);
    	maPmYearPtSchedCommonDTO.setFirstRow(maPmYearPtSchedListForm.getFirstRow());
    	maPmYearPtSchedCommonDTO.setOrderBy(maPmYearPtSchedListForm.getOrderBy());
    	maPmYearPtSchedCommonDTO.setDirection(maPmYearPtSchedListForm.getDirection());
    	
        //리스트를 조회한다.
        List resultList = maPmYearPtSchedListService.findDateList(maPmYearPtSchedCommonDTO, getUser(request));

        //Paging
    	String totalCount = "";
    	        
    	if(Integer.parseInt(maPmYearPtSchedListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maPmYearPtSchedListService.findDateTotalCount(maPmYearPtSchedCommonDTO,getUser(request));
    	        
    	if(excelExport) CommonUtil.makeGridExport(resultList, request, response, maPmYearPtSchedListForm);
    	else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
	}

}
