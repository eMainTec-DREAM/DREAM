package dream.consult.comp.time.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.ConsultAuthAction;
import common.util.CommonUtil;
import dream.consult.comp.time.dto.MaLineTimeCommonDTO;
import dream.consult.comp.time.dto.MaLineTimeDowListDTO;
import dream.consult.comp.time.form.MaLineTimeDowListForm;
import dream.consult.comp.time.service.MaLineTimeDowListService;

/**
 * 요일별 설정 목록
 * @author  kim21017
 * @version $Id: MaLineTimeDowListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maLineTimeDowList" name="maLineTimeDowListForm"
 *                input="/dream/consult/comp/time/maLineTimeDowList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maLineTimeDowList" path="/dream/consult/comp/time/maLineTimeDowList.jsp"
 *                        redirect="false"
 */
public class MaLineTimeDowListAction extends ConsultAuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int LINE_DOW_LIST_FIND 			= 1001;
    /** 삭제 */
    public static final int LINE_DOW_LIST_DELETE 		= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaLineTimeDowListForm maLineTimeDowListForm = (MaLineTimeDowListForm) form;
        
        switch (maLineTimeDowListForm.getStrutsAction())
        {
        
            case MaLineTimeDowListAction.LINE_DOW_LIST_FIND:
                findDowList(request,response, maLineTimeDowListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaLineTimeDowListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, maLineTimeDowListForm.getListId(), maLineTimeDowListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaLineTimeDowListAction.LINE_DOW_LIST_DELETE:
            	deleteDowList(request,maLineTimeDowListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaLineTimeDowListAction.BASE_GRID_EXPORT:
            	findDowList(request,response, maLineTimeDowListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maLineTimeDowList");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaLineTimeDowListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maLineTimeDowListForm
     * @throws Exception
     */
    private void findDowList(HttpServletRequest request,HttpServletResponse response, MaLineTimeDowListForm maLineTimeDowListForm, boolean excelExport) throws Exception
    {
        MaLineTimeDowListService maLineTimeDowListService = (MaLineTimeDowListService) getBean("maLineTimeDowListService");
        MaLineTimeCommonDTO maLineTimeCommonDTO = maLineTimeDowListForm.getMaLineTimeCommonDTO();
        MaLineTimeDowListDTO maLineTimeDowListDTO = maLineTimeDowListForm.getMaLineTimeDowListDTO();
        
        //Paging
        maLineTimeCommonDTO.setIsLoadMaxCount("Y".equals(maLineTimeDowListForm.getIsLoadMaxCount())?true:false);
        maLineTimeCommonDTO.setFirstRow(maLineTimeDowListForm.getFirstRow());
        maLineTimeCommonDTO.setOrderBy(maLineTimeDowListForm.getOrderBy());
        maLineTimeCommonDTO.setDirection(maLineTimeDowListForm.getDirection());

        List resultList = maLineTimeDowListService.findDowList(maLineTimeCommonDTO, maLineTimeDowListDTO, getUser(request));
        
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(maLineTimeDowListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maLineTimeDowListService.findTotalCount(maLineTimeCommonDTO, maLineTimeDowListDTO,getUser(request));
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,maLineTimeDowListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: MaLineTimeDowListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maLineTimeDowListForm
     * @throws Exception
     */
    private void deleteDowList(HttpServletRequest request, MaLineTimeDowListForm maLineTimeDowListForm) throws Exception
    {
    	MaLineTimeDowListService maLineTimeDowListService = (MaLineTimeDowListService) getBean("maLineTimeDowListService");
        
    	String[] deleteRows = maLineTimeDowListForm.getDeleteRows();
    	String[] deleteRowsExt = maLineTimeDowListForm.getDeleteRowsExt();
    
    	maLineTimeDowListService.deleteDowList(deleteRows, deleteRowsExt);
    	
    	setAjaxStatus(request);
    }
}