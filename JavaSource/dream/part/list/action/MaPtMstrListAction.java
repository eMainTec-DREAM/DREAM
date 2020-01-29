package dream.part.list.action;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.message.DataBaseMessageResources;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.org.dept.action.MaDeptListAction;
import dream.org.dept.dto.MaDeptCommonDTO;
import dream.org.dept.service.MaDeptListService;
import dream.part.list.dto.MaPtMstrCommonDTO;
import dream.part.list.form.MaPtMstrListForm;
import dream.part.list.service.MaPtMstrListService;

import com.google.gson.Gson;

/**
 * 보전자재분류(마스터) - 목록 action
 * @author  ssong
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/maPtMstrList" name="maPtMstrListForm"
 *                input="/dream/part/list/maPtMstrList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPtMstrList" path="/dream/part/list/maPtMstrList.jsp"
 *                        redirect="false"
 */
public class MaPtMstrListAction extends AuthAction
{
    /** 조회 */
    public static final int PTMSTR_LIST_FIND 	= 8001;
    /** 삭제 */
    public static final int PTMSTR_LIST_DELETE 	= 7000;
    /** 복사 */
    public static final int PTMSTR_LIST_COPY 	= 5003;
    /** TAEXCELTAB 데이터 가져오기 */
    public static final int GET_DATA		 	= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaPtMstrListForm maPtMstrListForm = (MaPtMstrListForm) form;
        
        super.updateAudit(maPtMstrListForm.getMaPtMstrCommonDTO().getAuditKey()==""?maPtMstrListForm.getMaPtMstrCommonDTO().getAuditKey():maPtMstrListForm.getMaPtMstrCommonDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maPtMstrListForm.getStrutsAction())
        {
            case MaPtMstrListAction.BASE_SET_HEADER:
                setHeader(request, response, maPtMstrListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPtMstrListAction.PTMSTR_LIST_FIND:
                findList(request, response, maPtMstrListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case MaPtMstrListAction.PTMSTR_LIST_DELETE:
            	deleteList(request, maPtMstrListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaPtMstrListAction.PTMSTR_LIST_COPY:
            	copyParts(request, response, maPtMstrListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPtMstrListAction.GET_DATA:
            	getData(request, maPtMstrListForm, response);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPtMstrListAction.BASE_GRID_EXPORT:
            	findList(request, response, maPtMstrListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maPtMstrList");
                break;
        }

        return returnActionForward;
    }

    private void getData(HttpServletRequest request, MaPtMstrListForm maPtMstrListForm, HttpServletResponse response) {
    	
    	MaPtMstrListService maPtMstrListService = (MaPtMstrListService) getBean("maPtMstrListService");        
    	MaPtMstrCommonDTO maPtMstrCommonDTO = maPtMstrListForm.getMaPtMstrCommonDTO();

    	User user = getUser(request);

    	String result = maPtMstrListService.getData(user, maPtMstrCommonDTO);
    	
    	setAjaxDesc(request, result);
	}

	private void setHeader(HttpServletRequest request, HttpServletResponse response, MaPtMstrListForm maPtMstrListForm) throws IOException
    {
        super.setHeader(request, response, maPtMstrListForm.getListId(), maPtMstrListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param maPtMstrListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, MaPtMstrListForm maPtMstrListForm, boolean excelExport) throws IOException
    {
    	MaPtMstrListService maPtMstrListService = (MaPtMstrListService) getBean("maPtMstrListService");        

    	MaPtMstrCommonDTO maPtMstrCommonDTO = maPtMstrListForm.getMaPtMstrCommonDTO();
    	
    	//Paging
    	maPtMstrCommonDTO.setIsLoadMaxCount("Y".equals(maPtMstrListForm.getIsLoadMaxCount())?true:false);
    	maPtMstrCommonDTO.setFirstRow(maPtMstrListForm.getFirstRow());
    	maPtMstrCommonDTO.setOrderBy(maPtMstrListForm.getOrderBy());
    	maPtMstrCommonDTO.setDirection(maPtMstrListForm.getDirection());
    	
    	// 로긴 comp_no 를 셋팅한다.
    	maPtMstrCommonDTO.setFilterCompNo((getUser(request).getCompNo()));
        
        //리스트를 조회한다.
        List resultList = maPtMstrListService.findList(maPtMstrCommonDTO, getUser(request));

        // 조회한 List 를 form에 셋팅한다.
//        super.makeJsonResult(resultList, request, response, maPtMstrListForm.getListId());
        //Paging
        String totalCount = "";
        if(Integer.parseInt(maPtMstrListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maPtMstrListService.findTotalCount(maPtMstrCommonDTO,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,maPtMstrListForm.getListId(),maPtMstrListForm.getCurrentPageId(), maPtMstrListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * delete
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPtMstrListForm
     * @param request
     */
    private void deleteList(HttpServletRequest request, MaPtMstrListForm maPtMstrListForm) throws Exception
    {
    	MaPtMstrListService maPtMstrListService = (MaPtMstrListService) getBean("maPtMstrListService");        

        String[] deleteRows = maPtMstrListForm.getDeleteRows();    // sheet 내역
        
        maPtMstrListService.deleteList(deleteRows, getUser(request));
        
        setAjaxStatus(request);
    }
    
    private void copyParts(HttpServletRequest request, HttpServletResponse response,MaPtMstrListForm maPtMstrListForm) throws Exception
    {	
    	MaPtMstrListService maPtMstrListService = (MaPtMstrListService) getBean("maPtMstrListService");        
    	String[] selectRows = maPtMstrListForm.getSelectRows();

    	List partIdList = maPtMstrListService.copyParts(selectRows, getUser(request));
    	Map result = CommonUtil.makeResultJson(partIdList);
    	
        Gson gson = new Gson();
        String jsonString = gson.toJson(result);
        response.getWriter().print(jsonString);
        
//        setAjaxStatus(request);
    }
}
