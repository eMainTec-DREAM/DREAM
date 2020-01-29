package dream.note.daily.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.note.daily.dto.MaWoDailyCommonDTO;
import dream.note.daily.form.MaWoDailyListForm;
import dream.note.daily.service.MaWoDailyListService;

/**
 * 일일작업 - 목록 action
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/maWoDailyList" name="maWoDailyListForm"
 *                input="/dream/note/daily/maWoDailyList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maWoDailyList" path="/dream/note/daily/maWoDailyList.jsp"
 *                        redirect="false"
 */
public class MaWoDailyListAction extends AuthAction
{
    /** 조회 */
    public static final int WO_DAILY_LIST_FIND         = 1001;
    /** 삭제 */
    public static final int WO_DAILY_LIST_DELETE       = 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaWoDailyListForm maWoDailyListForm = (MaWoDailyListForm) form;
        
        switch (maWoDailyListForm.getStrutsAction())
        {
            case MaWoDailyListAction.WO_DAILY_LIST_FIND:
            	findBdList(request, maWoDailyListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaWoDailyListAction.BASE_GRID_EXPORT:
            	findBdList(request, maWoDailyListForm, response, true);
            	returnActionForward = new ActionForward("/gridExport");
            	break;
            case MaWoDailyListAction.WO_DAILY_LIST_DELETE:
            	deleteList(request, maWoDailyListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaWoDailyListAction.BASE_SET_HEADER:
                setHeader(request, response, maWoDailyListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                returnActionForward = mapping.findForward("maWoDailyList");
                break;
        }

        return returnActionForward;
    }

    private void deleteList(HttpServletRequest request, MaWoDailyListForm maWoDailyListForm)
    {
        MaWoDailyListService maWoDailyListService = (MaWoDailyListService) getBean("maWoDailyListService");        

        String[] deleteRows = maWoDailyListForm.getDeleteRows();    // sheet 내역
        
        maWoDailyListService.deleteList(deleteRows,getUser(request).getCompNo());
        
        setAjaxStatus(request);
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaWoDailyListForm maWoDailyListForm) throws IOException
    {
        super.setHeader(request, response, maWoDailyListForm.getListId(), maWoDailyListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maWoDailyListForm
     * @throws Exception
     */
    private void findBdList(HttpServletRequest request, MaWoDailyListForm maWoDailyListForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	MaWoDailyListService maWoDailyListService = (MaWoDailyListService) getBean("maWoDailyListService");        

    	MaWoDailyCommonDTO maWoDailyCommonDTO = maWoDailyListForm.getMaWoDailyCommonDTO();
    	maWoDailyCommonDTO.setCompNo(getUser(request).getCompNo());
    	
        //Paging
        maWoDailyCommonDTO.setIsLoadMaxCount("Y".equals(maWoDailyListForm.getIsLoadMaxCount())?true:false);
        maWoDailyCommonDTO.setFirstRow(maWoDailyListForm.getFirstRow());
        maWoDailyCommonDTO.setOrderBy(maWoDailyListForm.getOrderBy());
        maWoDailyCommonDTO.setDirection(maWoDailyListForm.getDirection());
        
        User user = getUser(request);
        //리스트를 조회한다.
        List resultList = maWoDailyListService.findList(maWoDailyCommonDTO,getUser(request));
        
        //Paging
        String totalCount = "";
        if(Integer.parseInt(maWoDailyListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maWoDailyListService.findTotalCount(maWoDailyCommonDTO,user);
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, maWoDailyListForm);//makeGridExport(resultList, request, response,maWoDailyListForm.getListId(),maWoDailyListForm.getCurrentPageId(), maWoDailyListForm.getFileName());
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
   
}
