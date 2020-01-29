package dream.tool.adj.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.tool.adj.dto.MaPttDisCommonDTO;
import dream.tool.adj.dto.MaPttDisPartsListDTO;
import dream.tool.adj.form.MaPttDisPartsListForm;
import dream.tool.adj.service.MaPttDisPartsListService;

/**
 * 응답  목록
 * @author  kim21017
 * @version $Id: MaPttDisPartsListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maPttDisPartsList" name="maPttDisPartsListForm"
 *                input="/dream/tool/adj/maPttDisPartsList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maPttDisPartsPopupList" name="maPttDisPartsListForm"
 *                input="/dream/tool/adj/maPttDisPartsPopupList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPttDisPartsList" path="/dream/tool/adj/maPttDisPartsList.jsp"
 *                        redirect="false"
 */
public class MaPttDisPartsListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int QNA_ANS_LIST_FIND 			= 1001;
    /** 삭제 */
    public static final int QNA_ANS_LIST_DELETE 		= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaPttDisPartsListForm maPttDisPartsListForm = (MaPttDisPartsListForm) form;
        
        switch (maPttDisPartsListForm.getStrutsAction())
        {
            case MaPttDisListAction.BASE_SET_HEADER:
                super.setHeader(request, response, maPttDisPartsListForm.getListId(), maPttDisPartsListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPttDisPartsListAction.QNA_ANS_LIST_FIND:
                findAnsList(request, response, maPttDisPartsListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPttDisPartsListAction.QNA_ANS_LIST_DELETE:
            	deleteItemList(request,maPttDisPartsListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPttDisPartsListAction.BASE_GRID_EXPORT:
            	findAnsList(request,response, maPttDisPartsListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaPttDisPartsListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maPttDisPartsListForm
     * @throws Exception
     */
    private void findAnsList(HttpServletRequest request, HttpServletResponse response, MaPttDisPartsListForm maPttDisPartsListForm, boolean excelExport) throws Exception
    {
        MaPttDisPartsListService maPttDisPartsListService = (MaPttDisPartsListService) getBean("maPttDisPartsListService");
        MaPttDisCommonDTO maPttDisCommonDTO = maPttDisPartsListForm.getMaPttDisCommonDTO();
        MaPttDisPartsListDTO maPttDisPartsListDTO = maPttDisPartsListForm.getMaPttDisPartsListDTO();

        maPttDisCommonDTO.setCompNo(getUser(request).getCompNo());
        
    	//Paging
        maPttDisCommonDTO.setIsLoadMaxCount("Y".equals(maPttDisPartsListForm.getIsLoadMaxCount())?true:false);
        maPttDisCommonDTO.setFirstRow(maPttDisPartsListForm.getFirstRow());
        maPttDisCommonDTO.setOrderBy(maPttDisPartsListForm.getOrderBy());
        maPttDisCommonDTO.setDirection(maPttDisPartsListForm.getDirection());

    	
        List resultList = maPttDisPartsListService.findAnsList(maPttDisCommonDTO, maPttDisPartsListDTO, getUser(request));
        String totalCount = "";
        
        if(Integer.parseInt(maPttDisPartsListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maPttDisPartsListService.findTotalCount(maPttDisCommonDTO, maPttDisPartsListDTO, getUser(request));
        
        if(excelExport) super.makeGridExport(resultList, request, response,maPttDisPartsListForm.getListId(),maPttDisPartsListForm.getCurrentPageId(), maPttDisPartsListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: MaPttDisPartsListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maPttDisPartsListForm
     * @throws Exception
     */
    private void deleteItemList(HttpServletRequest request, MaPttDisPartsListForm maPttDisPartsListForm) throws Exception
    {
    	MaPttDisPartsListService maPttDisPartsListService = (MaPttDisPartsListService) getBean("maPttDisPartsListService");
        
    	maPttDisPartsListService.deleteItemList(maPttDisPartsListForm.getDeleteRows(), maPttDisPartsListForm.getDeleteRowsExt());
    	
    	setAjaxStatus(request);
    }
}