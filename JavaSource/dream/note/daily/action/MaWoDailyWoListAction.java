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
import dream.note.daily.form.MaWoDailyWoListForm;
import dream.note.daily.service.MaWoDailyWoListService;

/**
 * 일일작업확인 - 작업목록 action
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/maWoDailyWoList" name="maWoDailyWoListForm"
 *                input="/dream/note/daily/maWoDailyWoList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maWoDailyWoList" path="/dream/note/daily/maWoDailyWoList.jsp"
 *                        redirect="false"
 */
public class MaWoDailyWoListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND         = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaWoDailyWoListForm maWoDailyWoListForm = (MaWoDailyWoListForm) form;
        
        switch (maWoDailyWoListForm.getStrutsAction())
        {
            case MaWoDailyWoListAction.LIST_FIND:
            	findList(request, maWoDailyWoListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaWoDailyWoListAction.BASE_GRID_EXPORT:
            	findList(request, maWoDailyWoListForm, response, true);
            	returnActionForward = new ActionForward("/gridExport");
            	break;
            case MaWoDailyWoListAction.BASE_SET_HEADER:
                setHeader(request, response, maWoDailyWoListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default:
                returnActionForward = mapping.findForward("maWoDailyWoList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaWoDailyWoListForm maWoDailyWoListForm) throws IOException
    {
        super.setHeader(request, response, maWoDailyWoListForm.getListId(), maWoDailyWoListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maWoDailyWoListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, MaWoDailyWoListForm maWoDailyWoListForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	MaWoDailyWoListService maWoDailyWoListService = (MaWoDailyWoListService) getBean("maWoDailyWoListService");        

    	MaWoDailyDetailDTO maWoDailyDetailDTO = maWoDailyWoListForm.getMaWoDailyDetailDTO();
    	maWoDailyDetailDTO.setCompNo(getUser(request).getCompNo());
    	
        //Paging
    	maWoDailyDetailDTO.setIsLoadMaxCount("Y".equals(maWoDailyWoListForm.getIsLoadMaxCount())?true:false);
    	maWoDailyDetailDTO.setFirstRow(maWoDailyWoListForm.getFirstRow());
    	maWoDailyDetailDTO.setOrderBy(maWoDailyWoListForm.getOrderBy());
    	maWoDailyDetailDTO.setDirection(maWoDailyWoListForm.getDirection());
        
        User user = getUser(request);
        //리스트를 조회한다.
        List resultList = maWoDailyWoListService.findList(maWoDailyDetailDTO,getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(maWoDailyWoListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maWoDailyWoListService.findTotalCount(maWoDailyDetailDTO,user);
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, maWoDailyWoListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
   
}
