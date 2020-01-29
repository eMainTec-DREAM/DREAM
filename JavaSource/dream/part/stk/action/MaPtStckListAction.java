package dream.part.stk.action;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.part.stk.dto.MaPtStckCommonDTO;
import dream.part.stk.form.MaPtStckListForm;
import dream.part.stk.service.MaPtStckListService;

/**
 * 자재재고 - 목록 action
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/maPtStckList" name="maPtStckListForm"
 *                input="/dream/part/stk/maPtStckList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPtStckList" path="/dream/part/stk/maPtStckList.jsp"
 *                        redirect="false"
 */
public class MaPtStckListAction extends AuthAction
{
    /** 조회 */
    public static final int PTSTCK_LIST_FIND 		= 8001;
    /** 삭제 */
    public static final int PTSTCK_LIST_DELETE 		= 7002;
    /** 구매신청 */
    public static final int PTSTCK_LIST_BUYREQ		= 5003;
    /** 출력 */
    public static final int PTSTCK_QR_INSERT 		= 4001;
    /**list 출력 */
    public static final int PTSTCK_QR_LIST_INSERT 	= 4002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaPtStckListForm maPtStckListForm = (MaPtStckListForm) form;
        
        super.updateAudit(maPtStckListForm.getMaPtStckCommonDTO().getAuditKey()==""?maPtStckListForm.getMaPtStckCommonDTO().getAuditKey():maPtStckListForm.getMaPtStckCommonDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maPtStckListForm.getStrutsAction())
        {
            case MaPtStckListAction.PTSTCK_LIST_FIND:
            	findPtStckList(request, maPtStckListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPtStckListAction.BASE_SET_HEADER:
                setHeader(request, response, maPtStckListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPtStckListAction.PTSTCK_LIST_DELETE:
            	deletePtStck(request, maPtStckListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaPtStckListAction.PTSTCK_LIST_BUYREQ:
            	reqPtStck(request, maPtStckListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaPtStckListAction.BASE_GRID_EXPORT:
            	findPtStckList(request, maPtStckListForm,response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            case MaPtStckListAction.PTSTCK_QR_INSERT:
            	insertQrCode(request, maPtStckListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaPtStckListAction.PTSTCK_QR_LIST_INSERT:
            	insertListQrCode(request, maPtStckListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaPtStckListForm maPtStckListForm) throws IOException
    {
        super.setHeader(request, response, maPtStckListForm.getListId(), maPtStckListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maPtStckListForm
     * @throws Exception
     */
    private void findPtStckList(HttpServletRequest request, MaPtStckListForm maPtStckListForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	MaPtStckListService maPtStckListService = (MaPtStckListService) getBean("maPtStckListService", request);        

    	MaPtStckCommonDTO maPtStckCommonDTO = maPtStckListForm.getMaPtStckCommonDTO();
    	maPtStckCommonDTO.setCompNo(getUser(request).getCompNo());
    	
    	//Paging
    	maPtStckCommonDTO.setIsLoadMaxCount("Y".equals(maPtStckListForm.getIsLoadMaxCount())?true:false);
    	maPtStckCommonDTO.setFirstRow(maPtStckListForm.getFirstRow());
    	maPtStckCommonDTO.setOrderBy(maPtStckListForm.getOrderBy());
    	maPtStckCommonDTO.setDirection(maPtStckListForm.getDirection());
    	
        //리스트를 조회한다.
        List resultList = maPtStckListService.findPtStckList(maPtStckCommonDTO,getUser(request));
        
        String totalCount = "";
        if(Integer.parseInt(maPtStckListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maPtStckListService.findTotalCount(maPtStckCommonDTO,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,maPtStckListForm.getListId(),maPtStckListForm.getCurrentPageId(), maPtStckListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * delete
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtStckListForm
     * @param request
     */
    private void deletePtStck(HttpServletRequest request, MaPtStckListForm maPtStckListForm) throws Exception
    {
    	MaPtStckListService maPtStckListService = (MaPtStckListService) getBean("maPtStckListService");
    	String[] deleteRows = maPtStckListForm.getDeleteRows();    // wcode_id list
    	String[] deleteRowsExt = maPtStckListForm.getDeleteRowsExt();    // part_id list
        
        maPtStckListService.deletePtStck(getUser(request).getCompNo(), deleteRows, deleteRowsExt, getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * req
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtStckListForm
     * @param request
     */
    private void reqPtStck(HttpServletRequest request, MaPtStckListForm maPtStckListForm) throws Exception
    {
    	MaPtStckListService maPtStckListService = (MaPtStckListService) getBean("maPtStckListService");
        maPtStckListService.reqPtStck(maPtStckListForm, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * Print
     * @author  pochul2423
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtStckListForm
     * @param request
     */
    private void insertQrCode(HttpServletRequest request, MaPtStckListForm maPtStckListForm ) throws Exception
    {	
    	MaPtStckListService maPtStckListService = (MaPtStckListService) getBean("maPtStckListService");
    	String[] deleteRows = maPtStckListForm.getDeleteRows();    // sheet 내역
    	String[] wcodeId = maPtStckListForm.getDeleteRowsExt();
    	maPtStckListService.insertQrCode(deleteRows,wcodeId,getUser(request).getCompNo(), getUser(request));
        
        setAjaxStatus(request);
    }
    private void insertListQrCode(HttpServletRequest request, MaPtStckListForm maPtStckListForm ) throws Exception
    {	
    	MaPtStckListService maPtStckListService = (MaPtStckListService) getBean("maPtStckListService");
    	MaPtStckCommonDTO maPtStckCommonDTO = maPtStckListForm.getMaPtStckCommonDTO();
    	maPtStckCommonDTO.setCompNo(getUser(request).getCompNo());
    	maPtStckListService.insertListQrCode(maPtStckCommonDTO, getUser(request));
    	
    	setAjaxStatus(request);
    }
}
