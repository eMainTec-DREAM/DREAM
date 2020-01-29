package dream.asset.list.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.fins.org.json.JSONObject;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrPartDetailDTO;
import dream.asset.list.dto.MaEqMstrPartListDTO;
import dream.asset.list.form.MaEqMstrPartListForm;
import dream.asset.list.service.MaEqMstrPartListService;

/**
 * 구성자재 목록
 * @author  kim21017
 * @version $Id: MaEqMstrPartListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maEqMstrPartList" name="maEqMstrPartListForm"
 *                input="/dream/asset/list/maEqMstrPartList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maEqMstrPartList" path="/dream/asset/list/maEqMstrPartList.jsp"
 *                        redirect="false"
 */
public class MaEqMstrPartListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int EQ_MSTR_PART_LIST_FIND 			= 1001;
    /** 삭제 */
    public static final int EQ_MSTR_PART_LIST_DELETE 		= 7002;
    /** 입력 */
    public static final int EQ_MSTR_PART_LIST_INPUT         = 5003;
    /** LIST 저장  */
    public static final int LIST_SAVE 						= 5004;
    /** 종류별공통부품입력 */
    public static final int EQ_MSTR_CTG_PART_LIST_INPUT     = 5005;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaEqMstrPartListForm maEqMstrPartListForm = (MaEqMstrPartListForm) form;
        
        switch (maEqMstrPartListForm.getStrutsAction())
        {
        
            case MaEqMstrPartListAction.EQ_MSTR_PART_LIST_FIND:
                findPartList(request,response, maEqMstrPartListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaEqMstrPartListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, maEqMstrPartListForm.getListId(), maEqMstrPartListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaEqMstrPartListAction.EQ_MSTR_PART_LIST_DELETE:
            	deletePartList(request,maEqMstrPartListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaEqMstrPartListAction.EQ_MSTR_PART_LIST_INPUT:
                inputPartList(request,maEqMstrPartListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaEqMstrPartListAction.EQ_MSTR_CTG_PART_LIST_INPUT:
                inputEqCtgPartList(request,maEqMstrPartListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaEqMstrPartListAction.LIST_SAVE:
            	saveList(request,response,maEqMstrPartListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case MaEqMstrPartListAction.BASE_GRID_EXPORT:
            	findPartList(request,response, maEqMstrPartListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maEqMstrPartList");
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
     * @param maEqMstrPartListForm
     * @throws Exception
     */
    private void findPartList(HttpServletRequest request,HttpServletResponse response, MaEqMstrPartListForm maEqMstrPartListForm, boolean excelExport) throws Exception
    {
        MaEqMstrPartListService maEqMstrPartListService = (MaEqMstrPartListService) getBean("maEqMstrPartListService",request);
        MaEqMstrCommonDTO maEqMstrCommonDTO = maEqMstrPartListForm.getMaEqMstrCommonDTO();
        MaEqMstrPartListDTO maEqMtrPartListDTO = maEqMstrPartListForm.getMaEqMstrPartListDTO();
        
        //Paging
        maEqMtrPartListDTO.setIsLoadMaxCount("Y".equals(maEqMstrPartListForm.getIsLoadMaxCount())?true:false);
        maEqMtrPartListDTO.setFirstRow(maEqMstrPartListForm.getFirstRow());
        maEqMtrPartListDTO.setOrderBy(maEqMstrPartListForm.getOrderBy());
        maEqMtrPartListDTO.setDirection(maEqMstrPartListForm.getDirection());
        
        List resultList = maEqMstrPartListService.findPartList(maEqMstrCommonDTO, maEqMtrPartListDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        if(Integer.parseInt(maEqMstrPartListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maEqMstrPartListService.findTotalCount(maEqMstrCommonDTO,maEqMtrPartListDTO, getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,maEqMstrPartListForm.getListId(),maEqMstrPartListForm.getCurrentPageId(), maEqMstrPartListForm.getFileName());
        
        else super.makeJsonResult(resultList, request, response, totalCount);

    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: MaEqMstrPartListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maEqMstrPartListForm
     * @throws Exception
     */
    private void deletePartList(HttpServletRequest request, MaEqMstrPartListForm maEqMstrPartListForm) throws Exception
    {
		MaEqMstrPartListService maEqMstrPartListService = (MaEqMstrPartListService) getBean("maEqMstrPartListService",request);
        
    	String[] deleteRows = maEqMstrPartListForm.getDeleteRows();
    
    	maEqMstrPartListService.deletePartList(deleteRows, getUser(request).getCompNo());
    	
    	setAjaxStatus(request);
    }
    
    /**
     * input
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maEqMstrPartListForm
     * @throws Exception
     */
    private void inputPartList(HttpServletRequest request, MaEqMstrPartListForm maEqMstrPartListForm) throws Exception
    {
        MaEqMstrPartListService maEqMstrPartListService = (MaEqMstrPartListService) getBean("maEqMstrPartListService",request);
        
        MaEqMstrCommonDTO maEqMstrCommonDTO = maEqMstrPartListForm.getMaEqMstrCommonDTO();
        MaEqMstrPartDetailDTO maEqMtrPartDetailDTO = maEqMstrPartListForm.getMaEqMstrPartDetailDTO();
        
        maEqMstrPartListService.inputPartList(maEqMstrCommonDTO, maEqMtrPartDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * 
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maEqMstrPartListForm
     * @throws Exception
     */
    private void inputEqCtgPartList(HttpServletRequest request, MaEqMstrPartListForm maEqMstrPartListForm) throws Exception
    {
        MaEqMstrPartListService maEqMstrPartListService = (MaEqMstrPartListService) getBean("maEqMstrPartListService");
        
        MaEqMstrCommonDTO maEqMstrCommonDTO = maEqMstrPartListForm.getMaEqMstrCommonDTO();
        MaEqMstrPartListDTO maEqMstrPartListDTO = maEqMstrPartListForm.getMaEqMstrPartListDTO();
        
        maEqMstrPartListService.inputEqCtgPartList(maEqMstrCommonDTO, maEqMstrPartListDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    private void saveList(HttpServletRequest request,HttpServletResponse response, MaEqMstrPartListForm maEqMstrPartListForm)throws Exception
    {
    	MaEqMstrPartListService maEqMstrPartListService = (MaEqMstrPartListService) getBean("maEqMstrPartListService",request);

        List<Map> gridList = CommonUtil.setGridMap(request);

        maEqMstrPartListService.saveList(gridList, getUser(request));
        
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("status", "ok");
        response.getWriter().print(jsonObj.toString());
    }
}