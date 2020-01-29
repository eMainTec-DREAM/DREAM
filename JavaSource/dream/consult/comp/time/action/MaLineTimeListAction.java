package dream.consult.comp.time.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.ConsultAuthAction;
import common.util.CommonUtil;
import dream.consult.comp.time.dto.MaLineTimeCommonDTO;
import dream.consult.comp.time.form.MaLineTimeListForm;
import dream.consult.comp.time.service.MaLineTimeListService;

/**
 * 가동시간설정 - 목록 
 * @author  kim21017
 * @version $Id: MaLineTimeListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maLineTimeList" name="maLineTimeListForm"
 *                input="/dream/consult/comp/time/maLineTimeList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maLineTimeList" path="/dream/consult/comp/time/maLineTimeList.jsp"
 *                        redirect="false"
 */
public class MaLineTimeListAction extends ConsultAuthAction
{
    /** 조회 */
    public static final int LINE_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int LINE_LIST_DELETE      = 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaLineTimeListForm maLineTimeListForm = (MaLineTimeListForm) form;
        
        switch (maLineTimeListForm.getStrutsAction())
        {
            case MaLineTimeListAction.LINE_LIST_FIND:
                findEqLocList(request, maLineTimeListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaLineTimeListAction.LINE_LIST_DELETE:
                deleteList(request, maLineTimeListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaLineTimeListAction.BASE_SET_HEADER:
                setHeader(request, response, maLineTimeListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaLineTimeListAction.BASE_GRID_EXPORT:
            	findEqLocList(request, maLineTimeListForm, response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaLineTimeListForm maLineTimeListForm) throws IOException
    {
        super.setHeader(request, response, maLineTimeListForm.getListId(),maLineTimeListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaLineTimeListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maLineTimeListForm
     * @param response
     * @throws Exception
     */
    private void findEqLocList(HttpServletRequest request, MaLineTimeListForm maLineTimeListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	MaLineTimeListService maLineTimeListService = (MaLineTimeListService) getBean("maLineTimeListService");        

    	MaLineTimeCommonDTO maLineTimeCommonDTO = maLineTimeListForm.getMaLineTimeCommonDTO();
        
        maLineTimeCommonDTO.setIsLoadMaxCount("Y".equals(maLineTimeListForm.getIsLoadMaxCount())?true:false);
        maLineTimeCommonDTO.setFirstRow(maLineTimeListForm.getFirstRow());
        maLineTimeCommonDTO.setOrderBy(maLineTimeListForm.getOrderBy());
        maLineTimeCommonDTO.setDirection(maLineTimeListForm.getDirection());
        //리스트를 조회한다.
        List resultList = maLineTimeListService.findEqLocList(maLineTimeCommonDTO,getUser(request));

        String totalCount = "";
        if(Integer.parseInt(maLineTimeListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maLineTimeListService.findTotalCount(maLineTimeCommonDTO,getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,maLineTimeListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
	}
    
    /**
     * delete
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maLineTimeListForm
     * @throws Exception
     */
    private void deleteList(HttpServletRequest request, MaLineTimeListForm maLineTimeListForm) throws Exception
    {
        MaLineTimeListService maLineTimeListService = (MaLineTimeListService) getBean("maLineTimeListService");
        
        String[] deleteRows = maLineTimeListForm.getDeleteRows();
        String[] deleteRowsExt = maLineTimeListForm.getDeleteRowsExt();
    
        maLineTimeListService.deleteList(deleteRows, deleteRowsExt);
        
        setAjaxStatus(request);
    }
}
