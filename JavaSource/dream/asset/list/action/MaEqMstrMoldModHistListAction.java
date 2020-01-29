package dream.asset.list.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrMoldModHistListDTO;
import dream.asset.list.form.MaEqMstrMoldModHistListForm;
import dream.asset.list.service.MaEqMstrMoldModHistListService;

/**
 * 구성자재 목록
 * @author  kim21017
 * @version $Id: MaEqMstrMoldModHistListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maEqMstrMoldModHistList" name="maEqMstrMoldModHistListForm"
 *                input="/dream/asset/list/maEqMstrMoldModHistList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maEqMoldMstrModHistList" name="maEqMstrMoldModHistListForm"
 *                input="/dream/asset/list/maEqMoldMstrModHistList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maEqMoldMstrModHistList" path="/dream/asset/list/maEqMoldMstrModHistList.jsp"
 *                        redirect="false"
 */
public class MaEqMstrMoldModHistListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int EQ_MSTR_MOLDPROD_LIST_FIND 			= 1001;
    /** 삭제 */
    public static final int EQ_MSTR_MOLDPROD_LIST_DELETE 		= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaEqMstrMoldModHistListForm maEqMstrMoldModHistListForm = (MaEqMstrMoldModHistListForm) form;
        
        switch (maEqMstrMoldModHistListForm.getStrutsAction())
        {
        
            case MaEqMstrMoldModHistListAction.EQ_MSTR_MOLDPROD_LIST_FIND:
                findMoldProdList(request,response, maEqMstrMoldModHistListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaEqMstrMoldModHistListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, maEqMstrMoldModHistListForm.getListId(), maEqMstrMoldModHistListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaEqMstrMoldModHistListAction.EQ_MSTR_MOLDPROD_LIST_DELETE:
            	deleteMoldProdList(request,maEqMstrMoldModHistListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaEqMstrMoldModHistListAction.BASE_GRID_EXPORT:
            	findMoldProdList(request,response, maEqMstrMoldModHistListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maEqMoldMstrModHistList");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaEqMstrMoldModHistListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqMstrMoldModHistListForm
     * @throws Exception
     */
    private void findMoldProdList(HttpServletRequest request,HttpServletResponse response, MaEqMstrMoldModHistListForm maEqMstrMoldModHistListForm, boolean excelExport) throws Exception
    {
        MaEqMstrMoldModHistListService maEqMstrMoldModHistListService = (MaEqMstrMoldModHistListService) getBean("maEqMstrMoldModHistListService");
        MaEqMstrCommonDTO maEqMstrCommonDTO = maEqMstrMoldModHistListForm.getMaEqMstrCommonDTO();
        MaEqMstrMoldModHistListDTO maEqMstrMoldModHistListDTO = maEqMstrMoldModHistListForm.getMaEqMstrMoldModHistListDTO();
        
        //Paging
        maEqMstrMoldModHistListDTO.setIsLoadMaxCount("Y".equals(maEqMstrMoldModHistListForm.getIsLoadMaxCount())?true:false);
        maEqMstrMoldModHistListDTO.setFirstRow(maEqMstrMoldModHistListForm.getFirstRow());
        maEqMstrMoldModHistListDTO.setOrderBy(maEqMstrMoldModHistListForm.getOrderBy());
        maEqMstrMoldModHistListDTO.setDirection(maEqMstrMoldModHistListForm.getDirection());
        
        User user = getUser(request);
        List resultList = maEqMstrMoldModHistListService.findMoldProdList(maEqMstrCommonDTO, maEqMstrMoldModHistListDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(maEqMstrMoldModHistListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maEqMstrMoldModHistListService.findTotalCount(maEqMstrCommonDTO, maEqMstrMoldModHistListDTO,user);
                
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, maEqMstrMoldModHistListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: MaEqMstrMoldModHistListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqMstrMoldModHistListForm
     * @throws Exception
     */
    private void deleteMoldProdList(HttpServletRequest request, MaEqMstrMoldModHistListForm maEqMstrMoldModHistListForm) throws Exception
    {
    	MaEqMstrMoldModHistListService maEqMstrMoldModHistListService = (MaEqMstrMoldModHistListService) getBean("maEqMstrMoldModHistListService");
        
    	String[] deleteRows = maEqMstrMoldModHistListForm.getDeleteRows();
    
    	maEqMstrMoldModHistListService.deleteMoldProdList(deleteRows, getUser(request).getCompNo());
    	
    	setAjaxStatus(request);
    }
}