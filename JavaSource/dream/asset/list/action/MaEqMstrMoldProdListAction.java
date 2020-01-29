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
import dream.asset.list.dto.MaEqMstrMoldProdListDTO;
import dream.asset.list.form.MaEqMstrMoldProdListForm;
import dream.asset.list.service.MaEqMstrMoldProdListService;

/**
 * 구성자재 목록
 * @author  kim21017
 * @version $Id: MaEqMstrPartListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maEqMstrMoldProdList" name="maEqMstrMoldProdListForm"
 *                input="/dream/asset/list/maEqMstrMoldProdList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maEqMoldMstrProdList" name="maEqMstrMoldProdListForm"
 *                input="/dream/asset/list/maEqMoldMstrProdList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maEqMoldMstrProdList" path="/dream/asset/list/maEqMoldMstrProdList.jsp"
 *                        redirect="false"
 */
public class MaEqMstrMoldProdListAction extends AuthAction
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
        MaEqMstrMoldProdListForm maEqMstrMoldProdListForm = (MaEqMstrMoldProdListForm) form;

        switch (maEqMstrMoldProdListForm.getStrutsAction())
        {
        
            case MaEqMstrMoldProdListAction.EQ_MSTR_MOLDPROD_LIST_FIND:
                findMoldProdList(request,response, maEqMstrMoldProdListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaEqMstrMoldProdListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, maEqMstrMoldProdListForm.getListId(), maEqMstrMoldProdListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaEqMstrMoldProdListAction.EQ_MSTR_MOLDPROD_LIST_DELETE:
            	deleteMoldProdList(request,maEqMstrMoldProdListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaEqMstrMoldProdListAction.BASE_GRID_EXPORT:
            	findMoldProdList(request,response, maEqMstrMoldProdListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maEqMoldMstrProdList");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaEqMstrPartListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqMstrMoldProdListForm
     * @throws Exception
     */
    private void findMoldProdList(HttpServletRequest request,HttpServletResponse response, MaEqMstrMoldProdListForm maEqMstrMoldProdListForm, boolean excelExport) throws Exception
    {
        MaEqMstrMoldProdListService maEqMstrMoldProdListService = (MaEqMstrMoldProdListService) getBean("maEqMstrMoldProdListService");
        MaEqMstrCommonDTO maEqMstrCommonDTO = maEqMstrMoldProdListForm.getMaEqMstrCommonDTO();
        MaEqMstrMoldProdListDTO maEqMtrPartListDTO = maEqMstrMoldProdListForm.getMaEqMstrMoldProdListDTO();
        
        //Paging
        maEqMtrPartListDTO.setIsLoadMaxCount("Y".equals(maEqMstrMoldProdListForm.getIsLoadMaxCount())?true:false);
        maEqMtrPartListDTO.setFirstRow(maEqMstrMoldProdListForm.getFirstRow());
        maEqMtrPartListDTO.setOrderBy(maEqMstrMoldProdListForm.getOrderBy());
        maEqMtrPartListDTO.setDirection(maEqMstrMoldProdListForm.getDirection());
        
        User user = getUser(request);
        
        List resultList = maEqMstrMoldProdListService.findMoldProdList(maEqMstrCommonDTO, maEqMtrPartListDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(maEqMstrMoldProdListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maEqMstrMoldProdListService.findTotalCount(maEqMstrCommonDTO,maEqMtrPartListDTO,user);
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, maEqMstrMoldProdListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: MaEqMstrPartListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqMstrMoldProdListForm
     * @throws Exception
     */
    private void deleteMoldProdList(HttpServletRequest request, MaEqMstrMoldProdListForm maEqMstrMoldProdListForm) throws Exception
    {
    	MaEqMstrMoldProdListService maEqMstrMoldProdListService = (MaEqMstrMoldProdListService) getBean("maEqMstrMoldProdListService");
        
    	String[] deleteRows = maEqMstrMoldProdListForm.getDeleteRows();
    
    	maEqMstrMoldProdListService.deleteMoldProdList(deleteRows, getUser(request).getCompNo());
    	
    	setAjaxStatus(request);
    }
}