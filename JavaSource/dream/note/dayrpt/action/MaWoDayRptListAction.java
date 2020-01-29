package dream.note.dayrpt.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.asset.list.action.MaEqMstrListAction;
import dream.note.dayrpt.dto.MaWoDayRptCommonDTO;
import dream.note.dayrpt.form.MaWoDayRptListForm;
import dream.note.dayrpt.service.MaWoDayRptListService;

/**
 * 업무일지 - 목록 action
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/maWoDayRptList" name="maWoDayRptListForm"
 *                input="/dream/note/dayrpt/maWoDayRptList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maWoDayRptList" path="/dream/note/dayrpt/maWoDayRptList.jsp"
 *                        redirect="false"
 * @struts.action-forward name="maWoDayRptSelect" path="/dream/note/dayrpt/maWoDayRptSelect.jsp"
 *                        redirect="false"
 */
public class MaWoDayRptListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND         = 1001;
    /** 삭제 */
    public static final int LIST_DELETE       = 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaWoDayRptListForm maWoDayRptListForm = (MaWoDayRptListForm) form;
        
        switch (maWoDayRptListForm.getStrutsAction())
        {
            case MaWoDayRptListAction.LIST_FIND:
            	findBdList(request, maWoDayRptListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaWoDayRptListAction.LIST_DELETE:
            	deleteList(request, maWoDayRptListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaWoDayRptListAction.BASE_SET_HEADER:
                setHeader(request, response, maWoDayRptListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaEqMstrListAction.BASE_GRID_EXPORT:
                findBdList(request, maWoDayRptListForm, response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maWoDayRptList");
                break;
        }

        return returnActionForward;
    }

    private void deleteList(HttpServletRequest request, MaWoDayRptListForm maWoDayRptListForm)
    {
        MaWoDayRptListService maWoDayRptListService = (MaWoDayRptListService) getBean("maWoDayRptListService");        

        String[] deleteRows = maWoDayRptListForm.getDeleteRows();    // sheet 내역
        
        maWoDayRptListService.deleteList(deleteRows,getUser(request).getCompNo());
        
        setAjaxStatus(request);
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaWoDayRptListForm maWoDayRptListForm) throws IOException
    {
        super.setHeader(request, response, maWoDayRptListForm.getListId(), maWoDayRptListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maWoDayRptListForm
     * @throws Exception
     */
    private void findBdList(HttpServletRequest request, MaWoDayRptListForm maWoDayRptListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	MaWoDayRptListService maWoDayRptListService = (MaWoDayRptListService) getBean("maWoDayRptListService");        

    	MaWoDayRptCommonDTO maWoDayRptCommonDTO = maWoDayRptListForm.getMaWoDayRptCommonDTO();
    	maWoDayRptCommonDTO.setCompNo(getUser(request).getCompNo());
    	
    	maWoDayRptCommonDTO.setIsLoadMaxCount("Y".equals(maWoDayRptListForm.getIsLoadMaxCount())?true:false);
    	maWoDayRptCommonDTO.setFirstRow(maWoDayRptListForm.getFirstRow());
    	maWoDayRptCommonDTO.setOrderBy(maWoDayRptListForm.getOrderBy());
        maWoDayRptCommonDTO.setDirection(maWoDayRptListForm.getDirection());
        
        //리스트를 조회한다.
        List resultList = maWoDayRptListService.findList(maWoDayRptCommonDTO,getUser(request));

        String totalCount = "";
        if(Integer.parseInt(maWoDayRptListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maWoDayRptListService.findTotalCount(maWoDayRptCommonDTO,getUser(request));

        //리스트를 조회한다.
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, maWoDayRptListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
   
}
