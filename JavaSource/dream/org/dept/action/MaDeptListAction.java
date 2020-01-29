package dream.org.dept.action;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.org.dept.dto.MaDeptCommonDTO;
import dream.org.dept.form.MaDeptListForm;
import dream.org.dept.service.MaDeptListService;

/**
 * 보전부서 - 목록 action
 * @author  ssong
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/maDeptList" name="maDeptListForm"
 *                input="/dream/org/dept/maDeptList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maDeptList" path="/dream/org/dept/maDeptList.jsp"
 *                        redirect="false"
 */
public class MaDeptListAction extends AuthAction
{
    /** 조회 */
    public static final int DEPT_LIST_FIND      = 8001;
    /** 삭제 */
    public static final int DEPT_LIST_DELETE    = 7002;
    /** TAEXCELTAB 데이터 가져오기 */
    public static final int GET_DATA		 	= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaDeptListForm maDeptListForm = (MaDeptListForm) form;
        
        super.updateAudit(maDeptListForm.getMaDeptCommonDTO().getAuditKey()==""?maDeptListForm.getMaDeptCommonDTO().getAuditKey():maDeptListForm.getMaDeptCommonDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maDeptListForm.getStrutsAction())
        {
            case MaDeptListAction.BASE_SET_HEADER:
                setHeader(request, response, maDeptListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaDeptListAction.DEPT_LIST_FIND:
                findList(request, response, maDeptListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case MaDeptListAction.DEPT_LIST_DELETE:
            	deleteList(request, maDeptListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case MaDeptListAction.BASE_GRID_EXPORT:
            	findList(request, response, maDeptListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            case MaDeptListAction.GET_DATA:
            	getData(request, maDeptListForm, response);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maDeptList");
                break;
        }

        return returnActionForward;
    }

    
    private void getData(HttpServletRequest request, MaDeptListForm maDeptListForm, HttpServletResponse response) {
    	
    	MaDeptListService maDeptListService = (MaDeptListService) getBean("maDeptListService");        
    	MaDeptCommonDTO maDeptCommonDTO = maDeptListForm.getMaDeptCommonDTO();

    	User user = getUser(request);

    	String result = maDeptListService.getData(user, maDeptCommonDTO);
    	
    	setAjaxDesc(request, result);
	}
    

	private void setHeader(HttpServletRequest request, HttpServletResponse response, MaDeptListForm maDeptListForm) throws IOException
    {
        super.setHeader(request, response, maDeptListForm.getListId(), maDeptListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param maDeptListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, MaDeptListForm maDeptListForm, boolean excelExport) throws IOException
    {
    	MaDeptListService maDeptListService = (MaDeptListService) getBean("maDeptListService");        

    	MaDeptCommonDTO maDeptCommonDTO = maDeptListForm.getMaDeptCommonDTO();

    	// 로긴 comp_no 를 셋팅한다.
    	maDeptCommonDTO.setCompNo((getUser(request).getCompNo()));
        
        //리스트를 조회한다.
        List resultList = maDeptListService.findDeptList(maDeptCommonDTO, getUser(request), excelExport);

        // 조회한 List 를 form에 셋팅한다.
        //super.makeJsonResult(resultList, request, response, maDeptListForm.getListId());
        if(excelExport) super.makeTreeGridExport(resultList, request, response,maDeptListForm.getListId(),maDeptListForm.getCurrentPageId(), maDeptListForm.getFileName());
        else super.makeTreeJsonResult(resultList, request, response, maDeptListForm.getListId());
    }
    
    /**
     * delete
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param maDeptListForm
     * @param request
     */
    private void deleteList(HttpServletRequest request, MaDeptListForm maDeptListForm) throws Exception
    {
    	MaDeptListService maDeptListService = (MaDeptListService) getBean("maDeptListService");        

        String[] deleteRows = maDeptListForm.getDeleteRows();    // sheet 내역
        
        maDeptListService.deleteList((getUser(request).getCompNo()), deleteRows);
        
        setAjaxStatus(request);
    }
}
