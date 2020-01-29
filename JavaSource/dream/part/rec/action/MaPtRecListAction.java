package dream.part.rec.action;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.fins.org.json.JSONObject;

import common.bean.User;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.part.rec.dto.MaPtRecCommonDTO;
import dream.part.rec.dto.MaPtRecDetailDTO;
import dream.part.rec.form.MaPtRecListForm;
import dream.part.rec.service.MaPtRecListService;

/**
 * 구매입고 - 목록 action
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/maPtRecList" name="maPtRecListForm"
 *                input="/dream/part/rec/maPtRecList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPtRecList" path="/dream/part/rec/maPtRecList.jsp"
 *                        redirect="false"
 */
public class MaPtRecListAction extends AuthAction
{
    /** 조회 */
    public static final int PTREC_LIST_FIND     		= 8001;
    /** 삭제 */
    public static final int PTREC_LIST_DELETE   		= 7002;
    /** 저장 */
    public static final int PTREC_LIST_INPUT    		= 5003;
    /** 부품확정&품번생성 */
    public static final int PTREC_LIST_REC_CONFIRM		= 6004;
    /** 출력 */
    public static final int PTREC_QR_INSERT         	= 4001;
    /** LIST 저장  */
    public static final int EDIT_LIST_SAVE 				= 5004;
    /** TAEXCELTAB 데이터 가져오기 */
    public static final int GET_DATA		 			= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaPtRecListForm maPtRecListForm = (MaPtRecListForm) form;
        
        super.updateAudit(maPtRecListForm.getMaPtRecCommonDTO().getAuditKey()==""?maPtRecListForm.getMaPtRecCommonDTO().getAuditKey():maPtRecListForm.getMaPtRecCommonDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maPtRecListForm.getStrutsAction())
        {
            case MaPtRecListAction.PTREC_LIST_FIND:
            	findPtRecList(request, maPtRecListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPtRecListAction.BASE_SET_HEADER:
                setHeader(request, response, maPtRecListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPtRecListAction.PTREC_LIST_DELETE:
            	deletePtRec(request, maPtRecListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaPtRecListAction.PTREC_LIST_INPUT:
                insertPtRec(request, maPtRecListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaPtRecListAction.PTREC_LIST_REC_CONFIRM:
                confirmPtRec(request, maPtRecListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaPtRecListAction.EDIT_LIST_SAVE:
            	saveList(request,response,maPtRecListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case MaPtRecListAction.GET_DATA:
            	getData(request,response, maPtRecListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaPtRecListAction.BASE_GRID_EXPORT:
            	findPtRecList(request, maPtRecListForm,response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            case MaPtRecListAction.PTREC_QR_INSERT:
                insertQrCode(request, maPtRecListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            default:
                returnActionForward = mapping.findForward("maPtRecList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaPtRecListForm maPtRecListForm) throws IOException
    {
        super.setHeader(request, response, maPtRecListForm.getListId(), maPtRecListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maPtRecListForm
     * @throws Exception
     */
    private void findPtRecList(HttpServletRequest request, MaPtRecListForm maPtRecListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	MaPtRecListService maPtRecListService = (MaPtRecListService) getBean("maPtRecListService");        

    	MaPtRecCommonDTO maPtRecCommonDTO = maPtRecListForm.getMaPtRecCommonDTO();
    	maPtRecCommonDTO.setCompNo(getUser(request).getCompNo());
    	
    	//Paging
    	maPtRecCommonDTO.setIsLoadMaxCount("Y".equals(maPtRecListForm.getIsLoadMaxCount())?true:false);
    	maPtRecCommonDTO.setFirstRow(maPtRecListForm.getFirstRow());
    	maPtRecCommonDTO.setOrderBy(maPtRecListForm.getOrderBy());
    	maPtRecCommonDTO.setDirection(maPtRecListForm.getDirection());
    	
        //리스트를 조회한다.
        List resultList = maPtRecListService.findList(maPtRecCommonDTO,getUser(request));
        
        //Paging
        String totalCount = "";
                
        if(Integer.parseInt(maPtRecListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maPtRecListService.findTotalCount(maPtRecCommonDTO,getUser(request));
                
        if(excelExport) super.makeGridExport(resultList, request, response, maPtRecListForm.getListId(),maPtRecListForm.getCurrentPageId(), maPtRecListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * delete
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRecListForm
     * @param request
     */
    private void deletePtRec(HttpServletRequest request, MaPtRecListForm maPtRecListForm) throws Exception
    {
    	MaPtRecListService maPtRecListService = (MaPtRecListService) getBean("maPtRecListService");
    	String[] deleteRows = maPtRecListForm.getDeleteRows();    // sheet 내역
        
        maPtRecListService.deleteList(getUser(request).getCompNo(), deleteRows);
        
        setAjaxStatus(request);
    }
    
    /**
     * insert
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maPtRecListForm
     */
    private void insertPtRec(HttpServletRequest request, MaPtRecListForm maPtRecListForm) throws Exception
    {
    	System.out.println("action inserPtRec::::::::::::::::::::::::::::");
        MaPtRecListService maPtRecListService = (MaPtRecListService) getBean("maPtRecListService");
        
        MaPtRecDetailDTO maPtRecDetailDTO = maPtRecListForm.getMaPtRecDetailDTO();
        
        maPtRecDetailDTO.setCompNo(getUser(request).getCompNo());
        
        User loginUser = getUser(request);
        
        maPtRecListService.insertList(maPtRecDetailDTO, loginUser);
        
        setAjaxStatus(request);
    }
    /**
     * 부품입고 확정 및 품번 생성
     * @author  kim21017
     */
    private void confirmPtRec(HttpServletRequest request, MaPtRecListForm maPtRecListForm) throws Exception
    {
    	 MaPtRecListService maPtRecListService = (MaPtRecListService) getBean("maPtRecListService");
    	 String[] selectedPtRecList = maPtRecListForm.getSelectRows();
    	 
    	 int result = maPtRecListService.confirmPtRec(selectedPtRecList,getUser(request));
    	 
    	 setAjaxDesc(request, result);
    }
    
    /**
     * Print
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maPtRecListForm
     */
    private void insertQrCode(HttpServletRequest request, MaPtRecListForm maPtRecListForm) throws Exception
    {   
        MaPtRecListService maPtRecListService = (MaPtRecListService) getBean("maPtRecListService");
        String[] deleteRows = maPtRecListForm.getSelectRows();
        String fileName = maPtRecListForm.getFileName();
        maPtRecListService.insertQrCode(deleteRows,fileName, getUser(request));
        
        setAjaxStatus(request);
    }
    
    private void saveList(HttpServletRequest request,HttpServletResponse response, MaPtRecListForm maPtRecListForm)throws Exception
    {
        MaPtRecListService maPtRecListService = (MaPtRecListService) getBean("maPtRecListService");

        List<Map> gridList = CommonUtil.setGridMap(request);

        maPtRecListService.saveList(gridList, getUser(request));
        
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("status", "ok");
        response.getWriter().print(jsonObj.toString());
    }
    
    private void getData(HttpServletRequest request, HttpServletResponse response, MaPtRecListForm maPtRecListForm) throws Exception
    {
        MaPtRecListService maPtRecListService = (MaPtRecListService) getBean("maPtRecListService");
    	
    	User user = getUser(request);
    	MaPtRecCommonDTO maPtRecCommonDTO = maPtRecListForm.getMaPtRecCommonDTO();
    	
    	String result = maPtRecListService.getData(maPtRecCommonDTO, user);
    	
    	setAjaxDesc(request, result);
    }
    
}
