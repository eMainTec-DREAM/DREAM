package dream.part.rpt.mawopthist.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.struts.BaseForm;
import dream.part.rpt.mawopthist.dto.MaWoPtHistCommonDTO;
import dream.part.rpt.mawopthist.form.MaWoPtHistListForm;
import dream.part.rpt.mawopthist.service.MaWoPtHistListService;

/**
 * 부품사용이력 - 목록 action
 * @author  ssong
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/maWoPtHistList" name="maWoPtHistListForm"
 *                input="/dream/part/rpt/mawopthist/maWoPtHistList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maWoPtHistList" path="/dream/part/rpt/mawopthist/maWoPtHistList.jsp"
 *                        redirect="false"
 */
public class MaWoPtHistListAction extends AuthAction
{
    /** 조회 */
    public static final int WOPTHIST_LIST_FIND = 1001;
    /** 삭제 */
    public static final int WOPTHIST_LIST_DELETE = 1002;
    /** 구매신청 */
    public static final int WOPTHIST_LIST_BUYREQ = 1003;
    /** 매일유업 - 카트담기 */
    public static final int WOPTHIST_LIST_CART = 1004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaWoPtHistListForm maWoPtHistListForm = (MaWoPtHistListForm) form;
        
        switch (maWoPtHistListForm.getStrutsAction())
        {
            case MaWoPtHistListAction.BASE_SET_HEADER:
                setHeader(request, response, maWoPtHistListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaWoPtHistListAction.WOPTHIST_LIST_FIND:
                findList(request, response, maWoPtHistListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case MaWoPtHistListAction.WOPTHIST_LIST_DELETE:
            	deleteList(request, maWoPtHistListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaWoPtHistListAction.WOPTHIST_LIST_BUYREQ:
            	reqPtBuy(request, maWoPtHistListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaWoPtHistListAction.WOPTHIST_LIST_CART:
                intfMaeilSetCart(request, maWoPtHistListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaWoPtHistListAction.BASE_GRID_EXPORT:
            	findList(request, response, maWoPtHistListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maWoPtHistList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaWoPtHistListForm maWoPtHistListForm) throws IOException
    {
        super.setHeader(request, response, maWoPtHistListForm.getListId(), maWoPtHistListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param maWoPtHistListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, MaWoPtHistListForm maWoPtHistListForm, boolean excelExport) throws IOException
    {
    	MaWoPtHistListService maWoPtHistListService = (MaWoPtHistListService) getBean("maWoPtHistListService");

    	MaWoPtHistCommonDTO maWoPtHistCommonDTO = maWoPtHistListForm.getMaWoPtHistCommonDTO();

    	// 로긴 comp_no 를 셋팅한다.
    	maWoPtHistCommonDTO.setFilterCompNo((getUser(request).getCompNo()));
    	
    	//Paging
    	maWoPtHistCommonDTO.setIsLoadMaxCount("Y".equals(maWoPtHistListForm.getIsLoadMaxCount())?true:false);
    	maWoPtHistCommonDTO.setFirstRow(maWoPtHistListForm.getFirstRow());
    	maWoPtHistCommonDTO.setOrderBy(maWoPtHistListForm.getOrderBy());
    	maWoPtHistCommonDTO.setDirection(maWoPtHistListForm.getDirection());
        
        //리스트를 조회한다.
        List resultList = maWoPtHistListService.findList(maWoPtHistCommonDTO, getUser(request));

        //Paging
        String totalCount = "";
                
        if(Integer.parseInt(maWoPtHistListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maWoPtHistListService.findTotalCount(maWoPtHistCommonDTO, getUser(request));
                
        if(excelExport) super.makeGridExport(resultList, request, response, maWoPtHistListForm.getListId(),maWoPtHistListForm.getCurrentPageId(), maWoPtHistListForm.getFileName());
        // 조회한 List 를 form에 셋팅한다.
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * delete
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param maWoPtHistListForm
     * @param request
     */
    private void deleteList(HttpServletRequest request, MaWoPtHistListForm maWoPtHistListForm) throws Exception
    {
    	MaWoPtHistListService maWoPtHistListService = (MaWoPtHistListService) getBean("maWoPtHistListService");        

        String[] deleteRows = maWoPtHistListForm.getDeleteRows();    // sheet 내역
        
        maWoPtHistListService.deleteList(deleteRows, getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * req
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maWoPtHistListForm
     * @param request
     */
    private void reqPtBuy(HttpServletRequest request, MaWoPtHistListForm maWoPtHistListForm) throws Exception
    {
    	MaWoPtHistListService maWoPtHistListService = (MaWoPtHistListService) getBean("maWoPtHistListService");        
    	maWoPtHistListService.reqPtBuy(maWoPtHistListForm, getUser(request));
        
        setAjaxStatus(request);
    }
    
    private void intfMaeilSetCart(HttpServletRequest request, MaWoPtHistListForm maWoPtHistListForm) throws Exception
    {
//        MaeilImarketCartSetterService maeilImarketCartSetterService = (MaeilImarketCartSetterService) getBean("maeilImarketCartSetterService");        
//        maeilImarketCartSetterService.intfMaeilSetCart((BaseForm)maWoPtHistListForm, getUser(request));
//        
//        setAjaxStatus(request);
    }
}
