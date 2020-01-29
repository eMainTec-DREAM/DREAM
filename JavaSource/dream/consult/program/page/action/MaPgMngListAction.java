package dream.consult.program.page.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.ConsultAuthAction;
import dream.consult.program.page.dto.MaPgMngCommonDTO;
import dream.consult.program.page.form.MaPgMngListForm;
import dream.consult.program.page.service.MaPgMngListService;

/**
 * 화면 - 목록 action
 * @author  kim21017
 * @version $Id: MaPgMngListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maPgMngList" name="maPgMngListForm"
 *                input="/dream/consult/program/page/maPgMngList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPgMngList" path="/dream/consult/program/page/maPgMngList.jsp"
 *                        redirect="false"
 */
public class MaPgMngListAction extends ConsultAuthAction
{
    /** 조회 */
    public static final int PG_LIST_FIND 	= 1001;
    /** 삭제 */
    public static final int PG_LIST_DELETE 	= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaPgMngListForm maPgMngListForm = (MaPgMngListForm) form;
        
        switch (maPgMngListForm.getStrutsAction())
        {
            case MaPgMngListAction.PG_LIST_FIND:
            	findPgList(request, maPgMngListForm, response,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPgMngListAction.BASE_SET_HEADER:
                setHeader(request, response, maPgMngListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPgMngListAction.PG_LIST_DELETE:
            	deletePage(request, maPgMngListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaPgMngListAction.BASE_GRID_EXPORT:
            	findPgList(request, maPgMngListForm,response,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maPgMngList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaPgMngListForm maPgMngListForm) throws IOException
    {
        super.setHeader(request, response, maPgMngListForm.getListId(), maPgMngListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaPgMngListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maPgMngListForm
     * @throws Exception
     */
    private void findPgList(HttpServletRequest request, MaPgMngListForm maPgMngListForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	MaPgMngListService maPgMngListService = (MaPgMngListService) getBean("maPgMngListService");        

    	MaPgMngCommonDTO maPgMngCommonDTO = maPgMngListForm.getMaPgMngCommonDTO();
    	
    	//Paging
    	maPgMngCommonDTO.setIsLoadMaxCount("Y".equals(maPgMngListForm.getIsLoadMaxCount())?true:false);
    	maPgMngCommonDTO.setFirstRow(maPgMngListForm.getFirstRow());
    	maPgMngCommonDTO.setOrderBy(maPgMngListForm.getOrderBy());
    	maPgMngCommonDTO.setDirection(maPgMngListForm.getDirection());

        //리스트를 조회한다.
        List resultList = maPgMngListService.findPgList(maPgMngCommonDTO,getUser(request));
      
        //Paging
        String totalCount = "";
                
        if(Integer.parseInt(maPgMngListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maPgMngListService.findTotalCount(maPgMngCommonDTO,getUser(request));
        
        if(excelExport) super.makeGridExport(resultList, request, response, maPgMngListForm.getListId(),maPgMngListForm.getCurrentPageId(), maPgMngListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  kim21017
     * @version $Id: MaPgMngListAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgMngListForm
     * @param request
     */
    private void deletePage(HttpServletRequest request, MaPgMngListForm maPgMngListForm) throws Exception
    {
    	MaPgMngListService maPgMngListService = (MaPgMngListService) getBean("maPgMngListService");
        
    	String[] deleteRows = maPgMngListForm.getDeleteRows();    // sheet 내역
        
        maPgMngListService.deletePage(deleteRows);
        
        setAjaxStatus(request);
    }
}
