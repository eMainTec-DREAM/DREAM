package dream.tool.iss.rtn.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.tool.iss.rtn.dto.MaPttRtnCommonDTO;
import dream.tool.iss.rtn.form.MaPttRtnListForm;
import dream.tool.iss.rtn.service.MaPttRtnListService;

/**
 * 공기구반납 - 목록 action
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/maPttRtnList" name="maPttRtnListForm"
 *                input="/dream/tool/iss/rtn/maPttRtnList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPttRtnList" path="/dream/tool/iss/rtn/maPttRtnList.jsp"
 *                        redirect="false"
 */
public class MaPttRtnListAction extends AuthAction
{
    /** 조회 */
    public static final int PTRTN_LIST_FIND     = 1001;
    /** 삭제 */
    public static final int PTRTN_LIST_DELETE   = 7002;
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaPttRtnListForm maPttRtnListForm = (MaPttRtnListForm) form;
        
        switch (maPttRtnListForm.getStrutsAction())
        {
            case MaPttRtnListAction.PTRTN_LIST_FIND:
            	findPtIssList(request, maPttRtnListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPttRtnListAction.BASE_SET_HEADER:
                setHeader(request, response, maPttRtnListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPttRtnListAction.PTRTN_LIST_DELETE:
            	deletePtIss(request, maPttRtnListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaPttRtnListAction.BASE_GRID_EXPORT:
            	findPtIssList(request, maPttRtnListForm,response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maPttRtnList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaPttRtnListForm maPttRtnListForm) throws IOException
    {
        super.setHeader(request, response, maPttRtnListForm.getListId(), maPttRtnListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maPttRtnListForm
     * @throws Exception
     */
    private void findPtIssList(HttpServletRequest request, MaPttRtnListForm maPttRtnListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	MaPttRtnListService maPttRtnListService = (MaPttRtnListService) getBean("maPttRtnListService");        

    	MaPttRtnCommonDTO maPttRtnCommonDTO = maPttRtnListForm.getMaPttRtnCommonDTO();
    	maPttRtnCommonDTO.setCompNo(getUser(request).getCompNo());
    	
    	//Paging
    	maPttRtnCommonDTO.setIsLoadMaxCount("Y".equals(maPttRtnListForm.getIsLoadMaxCount())?true:false);
    	maPttRtnCommonDTO.setFirstRow(maPttRtnListForm.getFirstRow());
    	maPttRtnCommonDTO.setOrderBy(maPttRtnListForm.getOrderBy());
    	maPttRtnCommonDTO.setDirection(maPttRtnListForm.getDirection());

    	
        //리스트를 조회한다.
        List resultList = maPttRtnListService.findList(maPttRtnCommonDTO,getUser(request));
        String totalCount = "";
        
        if(Integer.parseInt(maPttRtnListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maPttRtnListService.findTotalCount(maPttRtnCommonDTO, getUser(request));
        
        if(excelExport) super.makeGridExport(resultList, request, response,maPttRtnListForm.getListId(),maPttRtnListForm.getCurrentPageId(), maPttRtnListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * delete
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttRtnListForm
     * @param request
     */
    private void deletePtIss(HttpServletRequest request, MaPttRtnListForm maPttRtnListForm) throws Exception
    {
    	MaPttRtnListService maPttRtnListService = (MaPttRtnListService) getBean("maPttRtnListService");
    	String[] deleteRows = maPttRtnListForm.getDeleteRows();    // sheet 내역
        
        maPttRtnListService.deleteList(getUser(request).getCompNo(), deleteRows);
        
        setAjaxStatus(request);
    }
}
