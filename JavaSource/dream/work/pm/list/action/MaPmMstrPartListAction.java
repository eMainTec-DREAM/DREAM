package dream.work.pm.list.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.MaPmMstrPartDetailDTO;
import dream.work.pm.list.form.MaPmMstrPartListForm;
import dream.work.pm.list.service.MaPmMstrPartListService;

/**
 * 예방작업기준-사용자재 목록
 * @author  jung7126
 * @version $Id: MaPmMstrPartListAction.java,v 1.0 2015/12/04 09:09:30 jung7126 Exp $
 * @since   1.0
 * @struts:action path="/maPmMstrPartList" name="maPmMstrPartListForm"
 *                input="/dream/work/pm/list/maPmMstrPartList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maPmOilPartList" name="maPmMstrPartListForm"
 *                input="/dream/work/pm/list/work/maPmOilPartList.jsp" scope="request"
 *                validate="false"
 */
public class MaPmMstrPartListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int PM_MSTR_PART_LIST_FIND 			= 1001;
    /** 삭제 */
    public static final int PM_MSTR_PART_LIST_DELETE 		= 7002;
    /** 입력 */
    public static final int PM_MSTR_PART_LIST_INPUT         = 5001;
    /** 표분 부품 입력 */
    public static final int PM_MSTR_STDPART_LIST_INPUT      = 5002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaPmMstrPartListForm maPmMstrPartListForm = (MaPmMstrPartListForm) form;
        
        switch (maPmMstrPartListForm.getStrutsAction())
        {
        
            case MaPmMstrPartListAction.PM_MSTR_PART_LIST_FIND:
                findPartList(request,response, maPmMstrPartListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPmMstrPartListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, maPmMstrPartListForm.getListId(), maPmMstrPartListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPmMstrPartListAction.PM_MSTR_PART_LIST_DELETE:
            	deletePartList(request,maPmMstrPartListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPmMstrPartListAction.PM_MSTR_PART_LIST_INPUT:
                insertPartList(request,maPmMstrPartListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaPmMstrPartListAction.PM_MSTR_STDPART_LIST_INPUT:
            	insertStdPartList(request,maPmMstrPartListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPmMstrPartListAction.BASE_GRID_EXPORT:
            	findPartList(request,response, maPmMstrPartListForm, true);
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
     * @version $Id: MaPmMstrPartListAction.java,v 1.0 2015/12/02 09:10:21 jung7126 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maPmMstrPartListForm
     * @throws Exception
     */
    private void findPartList(HttpServletRequest request,HttpServletResponse response, MaPmMstrPartListForm maPmMstrPartListForm, boolean excelExport) throws Exception
    {
        MaPmMstrPartListService maPmMstrPartListService = (MaPmMstrPartListService) getBean("maPmMstrPartListService");
        MaPmMstrCommonDTO maPmMstrCommonDTO = maPmMstrPartListForm.getMaPmMstrCommonDTO();
        
        //Paging
        maPmMstrCommonDTO.setIsLoadMaxCount("Y".equals(maPmMstrPartListForm.getIsLoadMaxCount())?true:false);
        maPmMstrCommonDTO.setFirstRow(maPmMstrPartListForm.getFirstRow());
        maPmMstrCommonDTO.setOrderBy(maPmMstrPartListForm.getOrderBy());
        maPmMstrCommonDTO.setDirection(maPmMstrPartListForm.getDirection());
        
        User user = getUser(request);
        List resultList = maPmMstrPartListService.findPartList(maPmMstrCommonDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(maPmMstrPartListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maPmMstrPartListService.findTotalCount(maPmMstrCommonDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,maPmMstrPartListForm.getListId(),maPmMstrPartListForm.getCurrentPageId(), maPmMstrPartListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, maPmMstrPartListForm.getListId());
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: MaPmMstrPartListAction.java,v 1.0 2015/12/02 09:10:21 jung7126 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maPmMstrPartListForm
     * @throws Exception
     */
    private void deletePartList(HttpServletRequest request, MaPmMstrPartListForm maPmMstrPartListForm) throws Exception
    {
    	MaPmMstrPartListService maPmMstrPartListService = (MaPmMstrPartListService) getBean("maPmMstrPartListService");
        
    	String[] deleteRows = maPmMstrPartListForm.getDeleteRows();
    
    	maPmMstrPartListService.deletePartList(deleteRows, getUser(request));
    	
    	setAjaxStatus(request);
    }
    
    /**
     * insert
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPmMstrPartDetailForm
     * @param request
     */
    private void insertPartList(HttpServletRequest request, MaPmMstrPartListForm maPmMstrPartListForm) throws Exception
    {
        MaPmMstrPartListService maPmMstrPartListService = (MaPmMstrPartListService) getBean("maPmMstrPartListService");
        
        MaPmMstrPartDetailDTO maPmMstrPartDetailDTO = maPmMstrPartListForm.getMaPmMstrPartDetailDTO();
        
        MaPmMstrCommonDTO maPmMstrMstrCommonDTO = maPmMstrPartListForm.getMaPmMstrCommonDTO();
        maPmMstrPartDetailDTO.setEnterBy(getUser(request).getUserId());
        maPmMstrMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        
        maPmMstrPartListService.insertPartList(maPmMstrPartDetailDTO, maPmMstrMstrCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * 표준부위 insert
     * @author  syyang
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPmMstrPartDetailForm
     * @param request
     */
    private void insertStdPartList(HttpServletRequest request, MaPmMstrPartListForm maPmMstrPartListForm) throws Exception
    {
    	MaPmMstrPartListService maPmMstrPartListService = (MaPmMstrPartListService) getBean("maPmMstrPartListService");
    	
    	MaPmMstrPartDetailDTO maPmMstrPartDetailDTO = maPmMstrPartListForm.getMaPmMstrPartDetailDTO();
    	
    	MaPmMstrCommonDTO maPmMstrMstrCommonDTO = maPmMstrPartListForm.getMaPmMstrCommonDTO();
    	maPmMstrPartDetailDTO.setEnterBy(getUser(request).getUserId());
    	maPmMstrMstrCommonDTO.setCompNo(getUser(request).getCompNo());
    	
    	maPmMstrPartListService.insertStdPartList(maPmMstrPartDetailDTO, maPmMstrMstrCommonDTO, getUser(request));
    	
    	setAjaxStatus(request);
    }
}