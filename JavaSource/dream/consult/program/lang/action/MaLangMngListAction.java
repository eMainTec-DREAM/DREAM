package dream.consult.program.lang.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import common.struts.ConsultAuthAction;
import dream.consult.program.lang.dto.MaLangMngCommonDTO;
import dream.consult.program.lang.form.MaLangMngListForm;
import dream.consult.program.lang.service.MaLangMngListService;

/**
 * 다국어 - 목록 action
 * @author  kim21017
 * @version $Id: MaLangMngListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maLangMngList" name="maLangMngListForm"
 *                input="/dream/consult/program/lang/maLangMngList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maLangMngList" path="/dream/consult/program/lang/maLangMngList.jsp"
 *                        redirect="false"
 */
public class MaLangMngListAction extends ConsultAuthAction
{
    /** 조회 */
    public static final int LANG_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int LANG_LIST_DELETE 	= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaLangMngListForm maLangMngListForm = (MaLangMngListForm) form;
        
        switch (maLangMngListForm.getStrutsAction())
        {
            case MaLangMngListAction.LANG_LIST_FIND:
                findKeyList(request, maLangMngListForm,response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaLangMngListAction.BASE_SET_HEADER:
                setHeader(request, response, maLangMngListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaLangMngListAction.LANG_LIST_DELETE:
            	deleteKey(request, maLangMngListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaLangMngListAction.BASE_GRID_EXPORT:
            	findKeyList(request, maLangMngListForm,response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maLangMngList");
                break;
        }

        return returnActionForward;
    }
    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaLangMngListForm maLangMngListForm) throws IOException
    {
        super.setHeader(request, response, maLangMngListForm.getListId(),maLangMngListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaLangMngListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maLangMngListForm
     * @throws Exception
     */
    private void findKeyList(HttpServletRequest request, MaLangMngListForm maLangMngListForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	MaLangMngListService maLangMngListService = (MaLangMngListService) getBean("maLangMngListService");        

    	MaLangMngCommonDTO maLangMngCommonDTO = maLangMngListForm.getMaLangMngCommonDTO();

    	//BaseDTO
    	//스크롤 load max값
    	maLangMngCommonDTO.setIsLoadMaxCount("Y".equals(maLangMngListForm.getIsLoadMaxCount())?true:false);
    	//다음 스크롤처리시 어디부터 또 다음 load max값 가져와야하므로 첫 줄 알아야한다.
    	maLangMngCommonDTO.setFirstRow(maLangMngListForm.getFirstRow());
    	//순서
    	maLangMngCommonDTO.setOrderBy(maLangMngListForm.getOrderBy());
    	//ascending, descending
    	maLangMngCommonDTO.setDirection(maLangMngListForm.getDirection());
    	
    	User user = getUser(request);
        List resultList = maLangMngListService.findKeyList(maLangMngCommonDTO, getUser(request));

        //Paging 
        String totalCount = "";
        //gird list totalCount 처리
        if(Integer.parseInt(
        		maLangMngListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maLangMngListService.findTotalCount(maLangMngCommonDTO, getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,maLangMngListForm.getListId(),maLangMngListForm.getCurrentPageId(), maLangMngListForm.getFileName());
        
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * delete
     * @author  kim21017
     * @version $Id: MaLangMngListAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maLangMngDetailForm
     */
    private void deleteKey(HttpServletRequest request, MaLangMngListForm maLangMngListForm) throws Exception
    {
    	MaLangMngListService maLangMngListService = (MaLangMngListService) getBean("maLangMngListService");        

    	String[] deleteRows = maLangMngListForm.getDeleteRows();
        
        maLangMngListService.deleteKey(deleteRows);
        
        setAjaxStatus(request);
    }
}
