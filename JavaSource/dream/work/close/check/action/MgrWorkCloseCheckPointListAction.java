package dream.work.close.check.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.work.close.check.dto.MgrWorkCloseCheckCommonDTO;
import dream.work.close.check.dto.MgrWorkCloseCheckPointListDTO;
import dream.work.close.check.form.MgrWorkCloseCheckPointListForm;
import dream.work.close.check.service.MgrWorkCloseCheckPointListService;

/**
 * 표준항목 리스트 - 목록 action
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/mgrWorkCloseCheckPointList" name="mgrWorkCloseCheckPointListForm"
 *                input="/dream/work/close/check/mgrWorkCloseCheckPointList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrWorkCloseCheckPointList" path="/dream/work/close/check/mgrWorkCloseCheckPointList.jsp"
 *                        redirect="false"
 */
public class MgrWorkCloseCheckPointListAction extends AuthAction
{
    /** 조회 */
    public static final int STD_LIST_FIND      = 8001;
    /** 삭제 */
    public static final int STD_LIST_DELETE    = 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MgrWorkCloseCheckPointListForm mgrWorkCloseCheckPointListForm = (MgrWorkCloseCheckPointListForm) form;
        
        switch (mgrWorkCloseCheckPointListForm.getStrutsAction())
        {
            case MgrWorkCloseCheckPointListAction.BASE_SET_HEADER:
                setHeader(request, response, mgrWorkCloseCheckPointListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrWorkCloseCheckPointListAction.STD_LIST_FIND:
                findList(request, response, mgrWorkCloseCheckPointListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;               
            case MgrWorkCloseCheckPointListAction.STD_LIST_DELETE:
                deleteList(request, mgrWorkCloseCheckPointListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MgrWorkCloseCheckPointListAction.BASE_GRID_EXPORT:
            	findList(request, response, mgrWorkCloseCheckPointListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("mgrWorkCloseCheckPointList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MgrWorkCloseCheckPointListForm mgrWorkCloseCheckPointListForm) throws IOException
    {
        super.setHeader(request, response, mgrWorkCloseCheckPointListForm.getListId(), mgrWorkCloseCheckPointListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param mgrWorkCloseCheckPointListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, MgrWorkCloseCheckPointListForm mgrWorkCloseCheckPointListForm, boolean excelExport)  throws IOException
    {
    	MgrWorkCloseCheckPointListService mgrWorkCloseCheckPointListService = (MgrWorkCloseCheckPointListService) getBean("mgrWorkCloseCheckPointListService");        

    	MgrWorkCloseCheckCommonDTO mgrWorkCloseCheckCommonDTO = mgrWorkCloseCheckPointListForm.getMgrWorkCloseCheckCommonDTO();
    	MgrWorkCloseCheckPointListDTO mgrWorkCloseCheckPointListDTO = mgrWorkCloseCheckPointListForm.getMgrWorkCloseCheckPointListDTO();
    	
    	//Paging
    	mgrWorkCloseCheckPointListDTO.setIsLoadMaxCount("Y".equals(mgrWorkCloseCheckPointListForm.getIsLoadMaxCount())?true:false);
    	mgrWorkCloseCheckPointListDTO.setFirstRow(mgrWorkCloseCheckPointListForm.getFirstRow());
    	mgrWorkCloseCheckPointListDTO.setOrderBy(mgrWorkCloseCheckPointListForm.getOrderBy());
    	mgrWorkCloseCheckPointListDTO.setDirection(mgrWorkCloseCheckPointListForm.getDirection());
        
    	// 로긴 comp_no 를 셋팅한다.
    	mgrWorkCloseCheckCommonDTO.setCompNo((getUser(request).getCompNo()));
        
        //리스트를 조회한다.
        List resultList = mgrWorkCloseCheckPointListService.findStdPointList(mgrWorkCloseCheckCommonDTO,mgrWorkCloseCheckPointListDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(mgrWorkCloseCheckPointListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = mgrWorkCloseCheckPointListService.findTotalCount(mgrWorkCloseCheckCommonDTO, mgrWorkCloseCheckPointListDTO,getUser(request));
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, mgrWorkCloseCheckPointListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * delete
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param mgrWorkCloseCheckPointListForm
     * @param request
     */
    private void deleteList(HttpServletRequest request, MgrWorkCloseCheckPointListForm mgrWorkCloseCheckPointListForm) throws Exception
    {
        MgrWorkCloseCheckPointListService mgrWorkCloseCheckPointListService = (MgrWorkCloseCheckPointListService) getBean("mgrWorkCloseCheckPointListService");        

        String[] deleteRows = mgrWorkCloseCheckPointListForm.getDeleteRows();    // sheet 내역
        
        mgrWorkCloseCheckPointListService.deleteList(deleteRows, getUser(request));
        
        setAjaxStatus(request);
    }
}
