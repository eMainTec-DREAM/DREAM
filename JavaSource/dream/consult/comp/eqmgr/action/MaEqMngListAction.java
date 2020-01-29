package dream.consult.comp.eqmgr.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.consult.comp.eqmgr.dto.MaEqMngCommonDTO;
import dream.consult.comp.eqmgr.form.MaEqMngListForm;
import dream.consult.comp.eqmgr.service.MaEqMngListService;

/**
 * 설비담당자변경 - 목록 action
 * @author  jung7126
 * @version $Id: MaEqMngListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maEqMngList" name="maEqMngListForm"
 *                input="/dream/consult/comp/eqmgr/maEqMngList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maEqMngList" path="/dream/consult/comp/eqmgr/maEqMngList.jsp"
 *                        redirect="false"
 */
public class MaEqMngListAction extends AuthAction
{
    /** 조회 */
    public static final int EQ_MNG_LIST_FIND 	= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaEqMngListForm maEqMngListForm = (MaEqMngListForm) form;
        
        switch (maEqMngListForm.getStrutsAction())
        {
            case MaEqMngListAction.EQ_MNG_LIST_FIND:
            	findEqMngList(request, maEqMngListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaEqMngListAction.BASE_SET_HEADER:
                setHeader(request, response, maEqMngListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;                
            case MaEqMngListAction.BASE_GRID_EXPORT:
            	findEqMngList(request, maEqMngListForm, response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maEqMngList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaEqMngListForm maEqMngListForm) throws IOException
    {
        super.setHeader(request, response, maEqMngListForm.getListId(), maEqMngListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaEqMngListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqMngListForm
     * @throws Exception
     */
    private void findEqMngList(HttpServletRequest request, MaEqMngListForm maEqMngListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	MaEqMngListService maEqMngListService = (MaEqMngListService) getBean("maEqMngListService");        

    	MaEqMngCommonDTO maEqMngCommonDTO = maEqMngListForm.getMaEqMngCommonDTO();
    	maEqMngCommonDTO.setCompNo(getUser(request).getCompNo());

    	//Paging
    	maEqMngCommonDTO.setIsLoadMaxCount("Y".equals(maEqMngListForm.getIsLoadMaxCount())?true:false);
    	maEqMngCommonDTO.setFirstRow(maEqMngListForm.getFirstRow());
    	maEqMngCommonDTO.setOrderBy(maEqMngListForm.getOrderBy());
    	maEqMngCommonDTO.setDirection(maEqMngListForm.getDirection());

    	
        //리스트를 조회한다.
        List resultList = maEqMngListService.findEqMngList(maEqMngCommonDTO,getUser(request));
       
        //Paging
		String totalCount = "";
		
		if(Integer.parseInt(maEqMngListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maEqMngListService.findTotalCount(maEqMngCommonDTO, getUser(request));
		
		if(excelExport) super.makeGridExport(resultList, request, response, maEqMngListForm.getListId(), maEqMngListForm.getCurrentPageId(), maEqMngListForm.getFileName());
		else super.makeJsonResult(resultList, request, response, totalCount);
    }
}
