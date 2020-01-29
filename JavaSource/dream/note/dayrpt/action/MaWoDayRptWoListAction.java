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
import dream.note.dayrpt.dto.MaWoDayRptDetailDTO;
import dream.note.dayrpt.form.MaWoDayRptWoListForm;
import dream.note.dayrpt.service.MaWoDayRptWoListService;

/**
 * 일일작업확인 - 작업목록 action
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/maWoDayRptWoList" name="maWoDayRptWoListForm"
 *                input="/dream/note/dayrpt/maWoDayRptWoList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maWoDayRptWoList" path="/dream/note/dayrpt/maWoDayRptWoList.jsp"
 *                        redirect="false"
 */
public class MaWoDayRptWoListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND         = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaWoDayRptWoListForm maWoDayRptWoListForm = (MaWoDayRptWoListForm) form;
        
        switch (maWoDayRptWoListForm.getStrutsAction())
        {
            case MaWoDayRptWoListAction.LIST_FIND:
            	findList(request, maWoDayRptWoListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaWoDayRptWoListAction.BASE_SET_HEADER:
                setHeader(request, response, maWoDayRptWoListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaEqMstrListAction.BASE_GRID_EXPORT:
                findList(request, maWoDayRptWoListForm, response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maWoDayRptWoList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaWoDayRptWoListForm maWoDayRptWoListForm) throws IOException
    {
        super.setHeader(request, response, maWoDayRptWoListForm.getListId(), maWoDayRptWoListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maWoDayRptWoListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, MaWoDayRptWoListForm maWoDayRptWoListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	MaWoDayRptWoListService maWoDayRptWoListService = (MaWoDayRptWoListService) getBean("maWoDayRptWoListService");        

    	MaWoDayRptCommonDTO maWoDayRptCommonDTO = maWoDayRptWoListForm.getMaWoDayRptCommonDTO();
    	MaWoDayRptDetailDTO maWoDayRptDetailDTO = maWoDayRptWoListForm.getMaWoDayRptDetailDTO();
    	maWoDayRptCommonDTO.setCompNo(getUser(request).getCompNo());
    	
    	maWoDayRptDetailDTO.setIsLoadMaxCount("Y".equals(maWoDayRptWoListForm.getIsLoadMaxCount())?true:false);
    	maWoDayRptDetailDTO.setFirstRow(maWoDayRptWoListForm.getFirstRow());
    	maWoDayRptDetailDTO.setOrderBy(maWoDayRptWoListForm.getOrderBy());
    	maWoDayRptDetailDTO.setDirection(maWoDayRptWoListForm.getDirection());
        
        //리스트를 조회한다.
        List resultList = maWoDayRptWoListService.findList(maWoDayRptDetailDTO,getUser(request));

        String totalCount = "";

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, maWoDayRptWoListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
   
}
