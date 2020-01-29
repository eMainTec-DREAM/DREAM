package dream.consult.program.table.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.ConsultAuthAction;
import common.util.CommonUtil;
import dream.consult.program.table.dto.MaTableCommonDTO;
import dream.consult.program.table.form.MaTableListForm;
import dream.consult.program.table.service.MaTableListService;

/**
 * 데이터 테이블 - 목록 action
 * @author  kim21017
 * @version $Id: MaTableListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maTableList" name="maTableListForm"
 *                input="/dream/consult/program/table/maTableList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maTableList" path="/dream/consult/program/table/maTableList.jsp"
 *                        redirect="false"
 */
public class MaTableListAction extends ConsultAuthAction
{
    /** 조회 */
    public static final int LISTTYPE_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int LISTTYPE_LIST_DELETE 	= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaTableListForm maTableListForm = (MaTableListForm) form;
        
        switch (maTableListForm.getStrutsAction())
        {
            case MaTableListAction.LISTTYPE_LIST_FIND:
                findListTypeList(request, maTableListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaTableListAction.BASE_SET_HEADER:
                setHeader(request, response, maTableListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaTableListAction.LISTTYPE_LIST_DELETE:
                deleteListType(request, maTableListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaTableListAction.BASE_GRID_EXPORT:
            	findListTypeList(request, maTableListForm, response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maTableList");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaTableListForm maTableListForm) throws IOException
    {
        super.setHeader(request, response, maTableListForm.getListId(), maTableListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaTableListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maTableListForm
     * @throws Exception
     */
    private void findListTypeList(HttpServletRequest request, MaTableListForm maTableListForm, HttpServletResponse response, boolean excelExport) throws Exception 
    {
    	MaTableListService maTableListService = (MaTableListService) getBean("maTableListService");        

    	MaTableCommonDTO maTableCommonDTO = maTableListForm.getMaTableCommonDTO();
    	maTableCommonDTO.setUserLang(getUser(request).getLangId());

    	//Paging
    	maTableCommonDTO.setIsLoadMaxCount("Y".equals(maTableListForm.getIsLoadMaxCount())?true:false);
    	maTableCommonDTO.setFirstRow(maTableListForm.getFirstRow());
    	maTableCommonDTO.setOrderBy(maTableListForm.getOrderBy());
    	maTableCommonDTO.setDirection(maTableListForm.getDirection());
    	
    	User user = getUser(request);
        List resultList = maTableListService.findListTypeList(maTableCommonDTO);
        //Paging
        String totalCount = "";
        if(Integer.parseInt(maTableListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maTableListService.findTotalCount(maTableCommonDTO, getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,maTableListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  kim21017
     * @version $Id: MaTableListAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param maTableListForm
     * @param request
     */
    private void deleteListType(HttpServletRequest request, MaTableListForm maTableListForm) throws Exception
    {
    	MaTableListService maTableListService = (MaTableListService) getBean("maTableListService");        

    	String[] deleteRows = maTableListForm.getDeleteRows();    // sheet 내역
        
    	maTableListService.deleteListType(deleteRows);
        
        setAjaxStatus(request);
    }
}
