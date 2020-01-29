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
import dream.note.daily.dto.MaWoDailyDetailDTO;
import dream.note.daily.form.MaWoDailyPmiListForm;
import dream.note.daily.service.MaWoDailyPmiListService;

/**
 * 일일작업확인 - 작업목록 action
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/maWoDailyPmiList" name="maWoDailyPmiListForm"
 *                input="/dream/note/daily/maWoDailyPmiList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maWoDailyPmiList" path="/dream/note/daily/maWoDailyPmiList.jsp"
 *                        redirect="false"
 */
public class MaWoDailyPmiListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND         = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaWoDailyPmiListForm maWoDailyPmiListForm = (MaWoDailyPmiListForm) form;
        
        switch (maWoDailyPmiListForm.getStrutsAction())
        {
            case MaWoDailyPmiListAction.LIST_FIND:
            	findList(request, maWoDailyPmiListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaWoDailyPmiListAction.BASE_GRID_EXPORT:
            	findList(request, maWoDailyPmiListForm, response, true);
            	returnActionForward = new ActionForward("/gridExport");
            	break;
            case MaWoDailyPmiListAction.BASE_SET_HEADER:
                setHeader(request, response, maWoDailyPmiListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                returnActionForward = mapping.findForward("maWoDailyPmiList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaWoDailyPmiListForm maWoDailyPmiListForm) throws IOException
    {
        super.setHeader(request, response, maWoDailyPmiListForm.getListId(), maWoDailyPmiListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maWoDailyPmiListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, MaWoDailyPmiListForm maWoDailyPmiListForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	MaWoDailyPmiListService maWoDailyPmiListService = (MaWoDailyPmiListService) getBean("maWoDailyPmiListService");        

    	MaWoDailyDetailDTO maWoDailyDetailDTO = maWoDailyPmiListForm.getMaWoDailyDetailDTO();
    	maWoDailyDetailDTO.setCompNo(getUser(request).getCompNo());
    	
        //Paging
    	maWoDailyDetailDTO.setIsLoadMaxCount("Y".equals(maWoDailyPmiListForm.getIsLoadMaxCount())?true:false);
    	maWoDailyDetailDTO.setFirstRow(maWoDailyPmiListForm.getFirstRow());
    	maWoDailyDetailDTO.setOrderBy(maWoDailyPmiListForm.getOrderBy());
    	maWoDailyDetailDTO.setDirection(maWoDailyPmiListForm.getDirection());
        
        User user = getUser(request);
        //리스트를 조회한다.
        List resultList = maWoDailyPmiListService.findList(maWoDailyDetailDTO,getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(maWoDailyPmiListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maWoDailyPmiListService.findTotalCount(maWoDailyDetailDTO,user);
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, maWoDailyPmiListForm);//makeGridExport(resultList, request, response,maWoDailyPmiListForm.getListId(),maWoDailyPmiListForm.getCurrentPageId(), maWoDailyPmiListForm.getFileName());
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
   
}
