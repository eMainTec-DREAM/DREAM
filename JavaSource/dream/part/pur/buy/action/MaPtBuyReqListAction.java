package dream.part.pur.buy.action;

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
import dream.part.pur.buy.dto.MaPtBuyReqDetailDTO;
import dream.part.pur.buy.dto.MaPtBuyReqHdrCommonDTO;
import dream.part.pur.buy.dto.MaPtBuyReqListDTO;
import dream.part.pur.buy.form.MaPtBuyReqListForm;
import dream.part.pur.buy.service.MaPtBuyReqListService;

/**
 * 구매신청 item  목록
 * @author  kim21017
 * @version $Id: MaPtBuyReqListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maPtBuyReqList" name="maPtBuyReqListForm"
 *                input="/dream/part/pur/buy/maPtBuyReqList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPtBuyReqList" path="/dream/part/pur/buy/maPtBuyReqList.jsp"
 *                        redirect="false"
 */
public class MaPtBuyReqListAction extends AuthAction
{
    /** 조회 */
    public static final int BUY_ITEM_LIST_FIND 			= 1001;
    /** 삭제 */
    public static final int BUY_ITEM_LIST_DELETE 		= 7002;
    /** 입력 */
    public static final int BUY_ITEM_LIST_INPUT         = 1003;
    /** 현장신청부품 */
    public static final int BUY_ITEM_LIST_GOT_INPUT		= 1004;
    /** LIST 저장 */
    public static final int EDIT_LIST_SAVE		        = 1005;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaPtBuyReqListForm maPtBuyReqListForm = (MaPtBuyReqListForm) form;
        
        super.updateAudit(maPtBuyReqListForm.getMaPtBuyReqListDTO().getAuditKey()==""?maPtBuyReqListForm.getMaPtBuyReqListDTO().getAuditKey():maPtBuyReqListForm.getMaPtBuyReqListDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maPtBuyReqListForm.getStrutsAction())
        {
            case MaPtBuyReqHdrListAction.BASE_SET_HEADER:
                super.setHeader(request, response, maPtBuyReqListForm.getListId(), maPtBuyReqListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPtBuyReqListAction.BUY_ITEM_LIST_FIND:
                findItemList(request, response, maPtBuyReqListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPtBuyReqListAction.BUY_ITEM_LIST_DELETE:
            	deleteItemList(request,maPtBuyReqListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPtBuyReqListAction.BUY_ITEM_LIST_INPUT:
                insertItemList(request,maPtBuyReqListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaPtBuyReqListAction.BUY_ITEM_LIST_GOT_INPUT:
            	insertItemListByPtPn(request,maPtBuyReqListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaPtBuyReqListAction.BASE_GRID_EXPORT:
            	findItemList(request,response, maPtBuyReqListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            case MaPtBuyReqListAction.EDIT_LIST_SAVE:
                saveList(request,response,maPtBuyReqListForm);
                returnActionForward = mapping.findForward("jsonPage");
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
     * @version $Id: MaPtBuyReqListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maPtBuyReqListForm
     * @throws Exception
     */
    private void findItemList(HttpServletRequest request, HttpServletResponse response, MaPtBuyReqListForm maPtBuyReqListForm, boolean excelExport) throws Exception
    {
        MaPtBuyReqListService maPtBuyReqListService = (MaPtBuyReqListService) getBean("maPtBuyReqListService");
        MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO = maPtBuyReqListForm.getMaPtBuyReqHdrCommonDTO();
        MaPtBuyReqListDTO maPtBuyReqListDTO = maPtBuyReqListForm.getMaPtBuyReqListDTO();

        //Paging
        maPtBuyReqHdrCommonDTO.setIsLoadMaxCount("Y".equals(maPtBuyReqListForm.getIsLoadMaxCount())?true:false);
        maPtBuyReqHdrCommonDTO.setFirstRow(maPtBuyReqListForm.getFirstRow());
        maPtBuyReqHdrCommonDTO.setOrderBy(maPtBuyReqListForm.getOrderBy());
        maPtBuyReqHdrCommonDTO.setDirection(maPtBuyReqListForm.getDirection());
        maPtBuyReqHdrCommonDTO.setCompNo(getUser(request).getCompNo());
        maPtBuyReqHdrCommonDTO.setUserLang(getUser(request).getLangId());
        
        List resultList = maPtBuyReqListService.findItemList(maPtBuyReqHdrCommonDTO, maPtBuyReqListDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(maPtBuyReqListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maPtBuyReqListService.findTotalCount(maPtBuyReqHdrCommonDTO, maPtBuyReqListDTO, getUser(request));
        
        if(excelExport) super.makeGridExport(resultList, request, response,maPtBuyReqListForm.getListId(),maPtBuyReqListForm.getCurrentPageId(), maPtBuyReqListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: MaPtBuyReqListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maPtBuyReqListForm
     * @throws Exception
     */
    private void deleteItemList(HttpServletRequest request, MaPtBuyReqListForm maPtBuyReqListForm) throws Exception
    {
    	MaPtBuyReqListService maPtBuyReqListService = (MaPtBuyReqListService) getBean("maPtBuyReqListService");
        
    	maPtBuyReqListService.deleteItemList(maPtBuyReqListForm.getDeleteRows(), maPtBuyReqListForm.getDeleteRowsExt(), maPtBuyReqListForm.getDeleteRowsExt1(),getUser(request));
    	
    	setAjaxStatus(request);
    }
    
    /**
     * insert
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maPtBuyReqListForm
     */
    private void insertItemList(HttpServletRequest request, MaPtBuyReqListForm maPtBuyReqListForm) throws Exception
    {
        MaPtBuyReqListService maPtBuyReqListService = (MaPtBuyReqListService) getBean("maPtBuyReqListService");
        
        MaPtBuyReqDetailDTO maPtBuyReqDetailDTO = maPtBuyReqListForm.getMaPtBuyReqDetailDTO();
        
        MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO = maPtBuyReqListForm.getMaPtBuyReqHdrCommonDTO();
        maPtBuyReqHdrCommonDTO.setCompNo(getUser(request).getCompNo());
        
        maPtBuyReqListService.insertItemList(maPtBuyReqDetailDTO, maPtBuyReqHdrCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * 현장신청부품
     * @author  js.lee
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maPtBuyReqListForm
     */
    private void insertItemListByPtPn(HttpServletRequest request, MaPtBuyReqListForm maPtBuyReqListForm) throws Exception
    {
        MaPtBuyReqListService maPtBuyReqListService = (MaPtBuyReqListService) getBean("maPtBuyReqListService");
        
        MaPtBuyReqDetailDTO maPtBuyReqDetailDTO = maPtBuyReqListForm.getMaPtBuyReqDetailDTO();
        
        MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO = maPtBuyReqListForm.getMaPtBuyReqHdrCommonDTO();
        maPtBuyReqHdrCommonDTO.setCompNo(getUser(request).getCompNo());
        
        maPtBuyReqListService.insertItemListByPtPn(maPtBuyReqDetailDTO, maPtBuyReqHdrCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    private void saveList(HttpServletRequest request,HttpServletResponse response, MaPtBuyReqListForm maPtBuyReqListForm) throws Exception
    {
        MaPtBuyReqListService maPtBuyReqListService = (MaPtBuyReqListService) getBean("maPtBuyReqListService");

        List<Map> gridList = CommonUtil.setGridMap(request);

        maPtBuyReqListService.saveList(gridList, getUser(request));
        
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("status", "ok");
        response.getWriter().print(jsonObj.toString());
    }
}