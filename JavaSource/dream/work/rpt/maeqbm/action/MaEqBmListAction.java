package dream.work.rpt.maeqbm.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.work.rpt.maeqbm.dto.MaEqBmListDTO;
import dream.work.rpt.maeqbm.form.MaEqBmListForm;
import dream.work.rpt.maeqbm.service.MaEqBmListService;

/**
 * 설비고장내역 Action
 * @author  kim21017
 * @version $Id: MaEqBmListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maEqBmList" name="maEqBmListForm"
 *                input="/dream/work/rpt/eqbm/maEqBmList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maEqBmList" path="/dream/work/rpt/eqbm/maEqBmList.jsp"
 *                        redirect="false"
 */
public class MaEqBmListAction extends AuthAction
{
    /** 조회 */
    public static final int BM_LIST_FIND 		= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaEqBmListForm maEqBmListForm = (MaEqBmListForm) form;
        
        switch (maEqBmListForm.getStrutsAction())
        {
            case MaEqBmListAction.BM_LIST_FIND:
                findEqBmList(request, maEqBmListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaEqBmListAction.BASE_SET_HEADER:
                setHeader(request, response, maEqBmListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaEqBmListAction.BASE_GRID_EXPORT:
            	findEqBmList(request, maEqBmListForm, response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maEqBmList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaEqBmListForm maEqBmListForm) throws IOException
    {
        super.setHeader(request, response, maEqBmListForm.getListId(),maEqBmListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaEqBmListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqBmListForm
     * @param response
     * @throws Exception
     */
    private void findEqBmList(HttpServletRequest request, MaEqBmListForm maEqBmListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	MaEqBmListService maEqBmListService = (MaEqBmListService) getBean("maEqBmListService");        

    	MaEqBmListDTO maEqBmListDTO = maEqBmListForm.getMaEqBmListDTO();
    	maEqBmListDTO.setCompNo(getUser(request).getCompNo());
        
    	//Paging
    	maEqBmListDTO.setIsLoadMaxCount("Y".equals(maEqBmListForm.getIsLoadMaxCount())?true:false);
    	maEqBmListDTO.setFirstRow(maEqBmListForm.getFirstRow());
    	maEqBmListDTO.setOrderBy(maEqBmListForm.getOrderBy());
    	maEqBmListDTO.setDirection(maEqBmListForm.getDirection());

    	//리스트를 조회한다.
        List resultList = maEqBmListService.findEqBmList(maEqBmListDTO,getUser(request));
      
        //Paging
        String totalCount = "";
                
        if(Integer.parseInt(maEqBmListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maEqBmListService.findTotalCount(maEqBmListDTO,getUser(request));
                
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, maEqBmListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
	}
}
