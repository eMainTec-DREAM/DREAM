package dream.consult.program.table.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.ConsultAuthAction;
import common.util.CommonUtil;
import dream.consult.program.table.dto.MaTableColListDTO;
import dream.consult.program.table.dto.MaTableCommonDTO;
import dream.consult.program.table.form.MaTableColListForm;
import dream.consult.program.table.service.MaTableColListService;

/**
 * 데이터 테이블 detail - code 목록
 * @author  kim21017
 * @version $Id: MaTableColListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maTableColList" name="maTableColListForm"
 *                input="/dream/consult/program/table/maTableColList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maTableColList" path="/dream/consult/program/table/maTableColList.jsp"
 *                        redirect="false"
 */
public class MaTableColListAction extends ConsultAuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int LISTTYPE_CODE_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int LISTTYPE_CODE_LIST_DELETE 		= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaTableColListForm maTableColListForm = (MaTableColListForm) form;
        
        switch (maTableColListForm.getStrutsAction())
        {
            case MaTableListAction.BASE_SET_HEADER:
                super.setHeader(request, response, maTableColListForm.getListId(), maTableColListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaTableColListAction.LISTTYPE_CODE_LIST_FIND:
                findCodeList(request, response, maTableColListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaTableColListAction.LISTTYPE_CODE_LIST_DELETE:
            	deleteCodeList(request,maTableColListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaTableColListAction.BASE_GRID_EXPORT:
            	findCodeList(request,response, maTableColListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maTableColList");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaTableColListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maTableColListForm
     * @throws Exception
     */
    private void findCodeList(HttpServletRequest request, HttpServletResponse response, MaTableColListForm maTableColListForm, boolean excelExport) throws Exception
    {
        MaTableColListService maTableColListService = (MaTableColListService) getBean("maTableColListService");
        MaTableCommonDTO maTableCommonDTO = maTableColListForm.getMaTableCommonDTO();
        maTableCommonDTO.setUserLang(getUser(request).getLangId());
        
        MaTableColListDTO maTableColListDTO = maTableColListForm.getMaTableColListDTO();
        
    	//Paging
    	maTableCommonDTO.setIsLoadMaxCount("Y".equals(maTableColListForm.getIsLoadMaxCount())?true:false);
    	maTableCommonDTO.setFirstRow(maTableColListForm.getFirstRow());
    	maTableCommonDTO.setOrderBy(maTableColListForm.getOrderBy());
    	maTableCommonDTO.setDirection(maTableColListForm.getDirection());

        List resultList = maTableColListService.findCodeList(maTableCommonDTO, maTableColListDTO,getUser(request).getLangId());
        
        //Paging
        String totalCount = "";
        if(Integer.parseInt(maTableColListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maTableColListService.findTotalCount(maTableCommonDTO, maTableColListDTO, getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,maTableColListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: MaTableColListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maTableColListForm
     * @throws Exception
     */
    private void deleteCodeList(HttpServletRequest request, MaTableColListForm maTableColListForm) throws Exception
    {
    	MaTableColListService maTableColListService = (MaTableColListService) getBean("maTableColListService");
        
//    	List ListTypeCodeDTOList = maTableColListForm.getListTypeCodeDTOList();
    
    	maTableColListService.deleteCodeList(maTableColListForm.getDeleteRows(), maTableColListForm.getDeleteRowsExt());
    	
    	setAjaxStatus(request);
    }
}