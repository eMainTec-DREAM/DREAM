package dream.work.pm.list.action;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;

import common.bean.User;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.invt.list.action.InvtListAction;
import dream.invt.list.service.InvtListService;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.form.MaPmMstrListForm;
import dream.work.pm.list.service.MaPmMstrListService;

/**
 * 예방작업기준 - 목록 action
 * @author  jung7126
 * @version $Id: MaPmMstrListAction.java,v 1.0 2015/12/02 09:10:21 jung7126 Exp $
 * @since   1.0
 * @struts:action path="/maPmMstrList" name="maPmMstrListForm"
 *                input="/dream/work/pm/list/maPmMstrList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workPmListWorkList" name="maPmMstrListForm"
 *                input="/dream/work/pm/list/work/workPmListWorkList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workPmListCalList" name="maPmMstrListForm"
 *                input="/dream/work/pm/list/cal/workPmListCalList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workPmListTrList" name="maPmMstrListForm"
 *                input="/dream/work/pm/list/tr/workPmListTrList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workPmListInsList" name="maPmMstrListForm"
 *                input="/dream/work/pm/list/ins/workPmListInsList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workPmListUInsList" name="maPmMstrListForm"
 *                input="/dream/work/pm/list/ins/workPmListUInsList.jsp" scope="request"
 *                validate="false"
 */
public class MaPmMstrListAction extends AuthAction
{
    /** 조회 */
    public static final int PM_MSTR_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int PM_MSTR_LIST_DELETE 	= 7002;
    /** 복사 */
    public static final int PM_MSTR_LIST_COPY 		= 5003;
    /** 출력 */
    public static final int PM_MSTR_QR_INSERT 		= 5004;
    /** list 출력 */
    public static final int PM_MSTR_QR_LIST_INSERT 	= 5005;
    
    /** 예방수리 조회 */
    public static final int PM_WORK_LIST_FIND 		= 2001;
    /** 예방점검 조회 */
    public static final int PM_INS_LIST_FIND 		= 2002;
    /** 예방교육 조회 */
    public static final int PM_TR_LIST_FIND 		= 2003;
    /** 예방교정 조회 */
    public static final int PM_CAL_LIST_FIND 		= 2004;
    /** TAEXCELTAB 데이터 가져오기 */
    public static final int GET_DATA		 	= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaPmMstrListForm maPmMstrListForm = (MaPmMstrListForm) form;
        
        super.updateAudit(maPmMstrListForm.getMaPmMstrCommonDTO().getAuditKey()==""?maPmMstrListForm.getMaPmMstrCommonDTO().getAuditKey():maPmMstrListForm.getMaPmMstrCommonDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maPmMstrListForm.getStrutsAction())
        {
            case MaPmMstrListAction.PM_MSTR_LIST_FIND:
            	findList(request, maPmMstrListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPmMstrListAction.PM_WORK_LIST_FIND:
            	findWorkList(request, maPmMstrListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPmMstrListAction.PM_INS_LIST_FIND:
            	findInsList(request, maPmMstrListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPmMstrListAction.PM_TR_LIST_FIND:
            	findTrList(request, maPmMstrListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPmMstrListAction.PM_CAL_LIST_FIND:
            	findCalList(request, maPmMstrListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPmMstrListAction.BASE_SET_HEADER:
                setHeader(request, response, maPmMstrListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPmMstrListAction.PM_MSTR_LIST_DELETE:
            	deleteEqCatalog(request, maPmMstrListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaPmMstrListAction.PM_MSTR_QR_INSERT:
            	insertQrCode(request, maPmMstrListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaPmMstrListAction.PM_MSTR_QR_LIST_INSERT:
            	insertListQrCode(request, maPmMstrListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPmMstrListAction.PM_MSTR_LIST_COPY:
            	copyPm(request,response, maPmMstrListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPmMstrListAction.GET_DATA:
            	getData(request, maPmMstrListForm, response);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPmMstrListAction.BASE_GRID_EXPORT:
            	findList(request, maPmMstrListForm,response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
//                returnActionForward = mapping.findForward("maPmMstrList");
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

    private void getData(HttpServletRequest request, MaPmMstrListForm maPmMstrListForm, HttpServletResponse response) {
    	
    	MaPmMstrListService maPmMstrListService = (MaPmMstrListService) getBean("maPmMstrListService");        
    	MaPmMstrCommonDTO maPmMstrCommonDTO = maPmMstrListForm.getMaPmMstrCommonDTO();
    	
    	User user = getUser(request);
    	
    	String result = maPmMstrListService.getData(user, maPmMstrCommonDTO);
    	
    	setAjaxDesc(request, result);
	}

	private void setHeader(HttpServletRequest request, HttpServletResponse response, MaPmMstrListForm maPmMstrListForm) throws IOException
    {
        super.setHeader(request, response, maPmMstrListForm.getListId(), maPmMstrListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaPmMstrListAction.java,v 1.0 2015/12/02 09:10:21 jung7126 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maPmMstrListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, MaPmMstrListForm maPmMstrListForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	MaPmMstrListService maPmMstrListService = (MaPmMstrListService) getBean("maPmMstrListService");        

    	MaPmMstrCommonDTO maPmMstrCommonDTO = maPmMstrListForm.getMaPmMstrCommonDTO();
    	maPmMstrCommonDTO.setCompNo(getUser(request).getCompNo());
    	
    	//Paging
        maPmMstrCommonDTO.setIsLoadMaxCount("Y".equals(maPmMstrListForm.getIsLoadMaxCount())?true:false);
        maPmMstrCommonDTO.setFirstRow(maPmMstrListForm.getFirstRow());
        maPmMstrCommonDTO.setOrderBy(maPmMstrListForm.getOrderBy());
        maPmMstrCommonDTO.setDirection(maPmMstrListForm.getDirection());
        
        User user = getUser(request);
        //리스트를 조회한다.
        List resultList = maPmMstrListService.findList(maPmMstrCommonDTO,getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(maPmMstrListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maPmMstrListService.findTotalCount(maPmMstrCommonDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,maPmMstrListForm.getListId(),maPmMstrListForm.getCurrentPageId(), maPmMstrListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaPmMstrListAction.java,v 1.0 2015/12/02 09:10:21 jung7126 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maPmMstrListForm
     * @throws Exception
     */
    private void findWorkList(HttpServletRequest request, MaPmMstrListForm maPmMstrListForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	MaPmMstrListService maPmMstrListService = (MaPmMstrListService) getBean("maPmMstrListService");        

    	MaPmMstrCommonDTO maPmMstrCommonDTO = maPmMstrListForm.getMaPmMstrCommonDTO();
    	maPmMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        
    	//Paging
        maPmMstrCommonDTO.setIsLoadMaxCount("Y".equals(maPmMstrListForm.getIsLoadMaxCount())?true:false);
        maPmMstrCommonDTO.setFirstRow(maPmMstrListForm.getFirstRow());
        maPmMstrCommonDTO.setOrderBy(maPmMstrListForm.getOrderBy());
        maPmMstrCommonDTO.setDirection(maPmMstrListForm.getDirection());
        
        User user = getUser(request);
        //리스트를 조회한다.
        List resultList = maPmMstrListService.findWorkList(maPmMstrCommonDTO,getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(maPmMstrListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maPmMstrListService.findTotalCount(maPmMstrCommonDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,maPmMstrListForm.getListId(),maPmMstrListForm.getCurrentPageId(), maPmMstrListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    private void findInsList(HttpServletRequest request, MaPmMstrListForm maPmMstrListForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	MaPmMstrListService maPmMstrListService = (MaPmMstrListService) getBean("maPmMstrListService");        

    	MaPmMstrCommonDTO maPmMstrCommonDTO = maPmMstrListForm.getMaPmMstrCommonDTO();
    	maPmMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        
    	//Paging
        maPmMstrCommonDTO.setIsLoadMaxCount("Y".equals(maPmMstrListForm.getIsLoadMaxCount())?true:false);
        maPmMstrCommonDTO.setFirstRow(maPmMstrListForm.getFirstRow());
        maPmMstrCommonDTO.setOrderBy(maPmMstrListForm.getOrderBy());
        maPmMstrCommonDTO.setDirection(maPmMstrListForm.getDirection());
        
        User user = getUser(request);
        //리스트를 조회한다.
        List resultList = maPmMstrListService.findInsList(maPmMstrCommonDTO,getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(maPmMstrListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maPmMstrListService.findTotalCount(maPmMstrCommonDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,maPmMstrListForm.getListId(),maPmMstrListForm.getCurrentPageId(), maPmMstrListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    private void findTrList(HttpServletRequest request, MaPmMstrListForm maPmMstrListForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	MaPmMstrListService maPmMstrListService = (MaPmMstrListService) getBean("maPmMstrListService");        

    	MaPmMstrCommonDTO maPmMstrCommonDTO = maPmMstrListForm.getMaPmMstrCommonDTO();
    	maPmMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        
    	//Paging
        maPmMstrCommonDTO.setIsLoadMaxCount("Y".equals(maPmMstrListForm.getIsLoadMaxCount())?true:false);
        maPmMstrCommonDTO.setFirstRow(maPmMstrListForm.getFirstRow());
        maPmMstrCommonDTO.setOrderBy(maPmMstrListForm.getOrderBy());
        maPmMstrCommonDTO.setDirection(maPmMstrListForm.getDirection());
        
        User user = getUser(request);
        //리스트를 조회한다.
        List resultList = maPmMstrListService.findTrList(maPmMstrCommonDTO,getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(maPmMstrListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maPmMstrListService.findTotalCount(maPmMstrCommonDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,maPmMstrListForm.getListId(),maPmMstrListForm.getCurrentPageId(), maPmMstrListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    private void findCalList(HttpServletRequest request, MaPmMstrListForm maPmMstrListForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	MaPmMstrListService maPmMstrListService = (MaPmMstrListService) getBean("maPmMstrListService");        

    	MaPmMstrCommonDTO maPmMstrCommonDTO = maPmMstrListForm.getMaPmMstrCommonDTO();
    	maPmMstrCommonDTO.setCompNo(getUser(request).getCompNo());
    	
    	//Paging
        maPmMstrCommonDTO.setIsLoadMaxCount("Y".equals(maPmMstrListForm.getIsLoadMaxCount())?true:false);
        maPmMstrCommonDTO.setFirstRow(maPmMstrListForm.getFirstRow());
        maPmMstrCommonDTO.setOrderBy(maPmMstrListForm.getOrderBy());
        maPmMstrCommonDTO.setDirection(maPmMstrListForm.getDirection());
        
        User user = getUser(request);
        //리스트를 조회한다.
        List resultList = maPmMstrListService.findCalList(maPmMstrCommonDTO,getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(maPmMstrListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maPmMstrListService.findTotalCount(maPmMstrCommonDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,maPmMstrListForm.getListId(),maPmMstrListForm.getCurrentPageId(), maPmMstrListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * delete
     * @author  jung7126
     * @version $Id: MaPmMstrListAction.java,v 1.2 2015/12/02 01:21:30 jung7126 Exp $
     * @since   1.0
     * 
     * @param maPmMstrListForm
     * @param request
     */
    private void deleteEqCatalog(HttpServletRequest request, MaPmMstrListForm maPmMstrListForm) throws Exception
    {
    	MaPmMstrListService maPmMstrListService = (MaPmMstrListService) getBean("maPmMstrListService");
    	String[] deleteRows = maPmMstrListForm.getDeleteRows();    // sheet 내역
        
        maPmMstrListService.deleteList(deleteRows,getUser(request));
        
        setAjaxStatus(request);
    }
    
    private void copyPm(HttpServletRequest request,HttpServletResponse response, MaPmMstrListForm maPmMstrListForm) throws Exception
    {
    	MaPmMstrListService maPmMstrListService = (MaPmMstrListService) getBean("maPmMstrListService");
    	String[] selectRows = maPmMstrListForm.getSelectRows();

    	List pmIdList = maPmMstrListService.copyPm(selectRows, getUser(request));
        
    	Map result = CommonUtil.makeResultJson(pmIdList);
    	
        Gson gson = new Gson();
        String jsonString = gson.toJson(result);
        response.getWriter().print(jsonString);
    }
    private void insertQrCode(HttpServletRequest request, MaPmMstrListForm maPmMstrListForm) throws Exception
    {	
    	MaPmMstrListService maPmMstrListService = (MaPmMstrListService) getBean("maPmMstrListService");
    	String[] selectRows = maPmMstrListForm.getSelectRows();

    	maPmMstrListService.insertQrCode(selectRows, maPmMstrListForm.getFileName(), getUser(request));
        
        setAjaxStatus(request);
    }
    private void insertListQrCode(HttpServletRequest request, MaPmMstrListForm maPmMstrListForm) throws Exception
    {	
    	MaPmMstrListService maPmMstrListService = (MaPmMstrListService) getBean("maPmMstrListService");
    	MaPmMstrCommonDTO maPmMstrCommonDTO = maPmMstrListForm.getMaPmMstrCommonDTO();
    	maPmMstrCommonDTO.setCompNo(getUser(request).getCompNo());    	
    	maPmMstrListService.insertListQrCode(maPmMstrCommonDTO, maPmMstrListForm.getFileName(), getUser(request));
    	
    	setAjaxStatus(request);
    }
}
