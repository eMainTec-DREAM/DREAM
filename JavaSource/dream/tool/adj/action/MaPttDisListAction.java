package dream.tool.adj.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.tool.adj.dto.MaPttDisCommonDTO;
import dream.tool.adj.form.MaPttDisListForm;
import dream.tool.adj.service.MaPttDisListService;

/**
 * 질의 - 목록 action
 * @author  kim21017
 * @version $Id: MaPttDisListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maPttDisList" name="maPttDisListForm"
 *                input="/dream/tool/adj/maPttDisList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPttDisList" path="/dream/tool/adj/maPttDisList.jsp"
 *                        redirect="false"
 */
public class MaPttDisListAction extends AuthAction
{
    /** 조회 */
    public static final int PTDIS_LIST_FIND 	= 1001;
    /** 삭제 */
    public static final int PTDIS_LIST_DELETE 	= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaPttDisListForm maPttDisListForm = (MaPttDisListForm) form;
        
        switch (maPttDisListForm.getStrutsAction())
        {
            case MaPttDisListAction.PTDIS_LIST_FIND:
                findDisList(request, maPttDisListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPttDisListAction.BASE_SET_HEADER:
                setHeader(request, response, maPttDisListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPttDisListAction.PTDIS_LIST_DELETE:
                deleteDis(request, maPttDisListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaPttDisListAction.BASE_GRID_EXPORT:
            	findDisList(request, maPttDisListForm, response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }
        return returnActionForward;
    }
    
    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaPttDisListForm maPttDisListForm) throws IOException
    {
        super.setHeader(request, response, maPttDisListForm.getListId(), maPttDisListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaPttDisListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maPttDisListForm
     * @throws IOException 
     * @throws Exception
     */
    private void findDisList(HttpServletRequest request, MaPttDisListForm maPttDisListForm, HttpServletResponse response, boolean excelExport) throws IOException 
    {
    	MaPttDisListService maPttDisListService = (MaPttDisListService) getBean("maPttDisListService");        

    	MaPttDisCommonDTO maPttDisCommonDTO = maPttDisListForm.getMaPttDisCommonDTO();
    	maPttDisCommonDTO.setCompNo(getUser(request).getCompNo());
    	maPttDisCommonDTO.setEnterBy(getUser(request).getEmpId());
    	
    	//Paging
    	maPttDisCommonDTO.setIsLoadMaxCount("Y".equals(maPttDisListForm.getIsLoadMaxCount())?true:false);
    	maPttDisCommonDTO.setFirstRow(maPttDisListForm.getFirstRow());
    	maPttDisCommonDTO.setOrderBy(maPttDisListForm.getOrderBy());
    	maPttDisCommonDTO.setDirection(maPttDisListForm.getDirection());

    	
        //리스트를 조회한다.
        List resultList = maPttDisListService.findDisList(maPttDisCommonDTO,getUser(request));
        String totalCount = "";
        
        if(Integer.parseInt(maPttDisListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maPttDisListService.findTotalCount(maPttDisCommonDTO, getUser(request));
        
        if(excelExport) super.makeGridExport(resultList, request, response,maPttDisListForm.getListId(),maPttDisListForm.getCurrentPageId(), maPttDisListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  kim21017
     * @version $Id: MaPttDisListAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPttDisListForm
     * @param request
     */
    private void deleteDis(HttpServletRequest request, MaPttDisListForm maPttDisListForm) throws Exception
    {
    	MaPttDisListService maPttDisListService = (MaPttDisListService) getBean("maPttDisListService");        

    	String[] deleteRows = maPttDisListForm.getDeleteRows();    // sheet 내역

    	maPttDisListService.deleteDis(getUser(request).getCompNo(), deleteRows);
        
        setAjaxStatus(request);
    }
}
