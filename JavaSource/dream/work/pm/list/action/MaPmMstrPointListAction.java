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
import dream.work.pm.list.dto.MaPmMstrPointDetailDTO;
import dream.work.pm.list.form.MaPmMstrPointListForm;
import dream.work.pm.list.service.MaPmMstrPointListService;

/**
 * 작업결과-검사항목 목록
 * @author  kim21017
 * @version $Id: MaPmMstrPointListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maPmMstrPointList" name="maPmMstrPointListForm"
 *                input="/dream/work/pm/list/maPmMstrPointList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workPmListPtrlRtPointList" name="maPmMstrPointListForm"
 *                input="/dream/work/pm/list/workPmListPtrlRtPointList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPmMstrPointList" path="/dream/work/pm/list/maPmMstrPointList.jsp"
 *                        redirect="false"
 */
public class MaPmMstrPointListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int PM_MSTR_POINT_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int PM_MSTR_POINT_LIST_DELETE 		= 7002;
    /** 입력 */
    public static final int PM_MSTR_STDPART_LIST_INPUT      = 5002;
    /** 표준점검항목 선택 LOV 입력 */
    public static final int PM_MSTR_STDPART_LOV_INPUT       = 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaPmMstrPointListForm maPmMstrPointListForm = (MaPmMstrPointListForm) form;
        
        switch (maPmMstrPointListForm.getStrutsAction())
        {
        
            case MaPmMstrPointListAction.PM_MSTR_POINT_LIST_FIND:
                findPointList(request,response, maPmMstrPointListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPmMstrPointListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, maPmMstrPointListForm.getListId(), maPmMstrPointListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPmMstrPointListAction.PM_MSTR_POINT_LIST_DELETE:
            	deletePointList(request,maPmMstrPointListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPmMstrPointListAction.PM_MSTR_STDPART_LIST_INPUT:
            	insertPointList(request,maPmMstrPointListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPmMstrPointListAction.PM_MSTR_STDPART_LOV_INPUT:
            	insertLovDetail(request,maPmMstrPointListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPmMstrPointListAction.BASE_GRID_EXPORT:
                findPointList(request,response, maPmMstrPointListForm, true);
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
     * @author  jung7126
     * @version $Id: MaPmMstrPointListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maPmMstrPointListForm
     * @throws Exception
     */
    private void findPointList(HttpServletRequest request,HttpServletResponse response, MaPmMstrPointListForm maPmMstrPointListForm, boolean excelExport) throws Exception
    {
        MaPmMstrPointListService maPmMstrPointListService = (MaPmMstrPointListService) getBean("maPmMstrPointListService");
        MaPmMstrCommonDTO maPmMstrCommonDTO = maPmMstrPointListForm.getMaPmMstrCommonDTO();
        
        //Paging
        maPmMstrCommonDTO.setIsLoadMaxCount("Y".equals(maPmMstrPointListForm.getIsLoadMaxCount())?true:false);
        maPmMstrCommonDTO.setFirstRow(maPmMstrPointListForm.getFirstRow());
        maPmMstrCommonDTO.setOrderBy(maPmMstrPointListForm.getOrderBy());
        maPmMstrCommonDTO.setDirection(maPmMstrPointListForm.getDirection());
        
        User user = getUser(request);
        List resultList = maPmMstrPointListService.findPointList(maPmMstrCommonDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(maPmMstrPointListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maPmMstrPointListService.findTotalCount(maPmMstrCommonDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,maPmMstrPointListForm.getListId(),maPmMstrPointListForm.getCurrentPageId(), maPmMstrPointListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  jung7126
     * @version $Id: MaPmMstrPointListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maPmMstrPointListForm
     * @throws Exception
     */
    private void deletePointList(HttpServletRequest request, MaPmMstrPointListForm maPmMstrPointListForm) throws Exception
    {
    	MaPmMstrPointListService maPmMstrPointListService = (MaPmMstrPointListService) getBean("maPmMstrPointListService");
        
    	String[] deleteRows = maPmMstrPointListForm.getDeleteRows();
    
    	maPmMstrPointListService.deletePointList(deleteRows, getUser(request));
    	
    	setAjaxStatus(request);
    }
    
    /**
     * 점검항목 insert
     * @author  euna0207
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPmMstrPartDetailForm
     * @param request
     */
    private void insertPointList(HttpServletRequest request, MaPmMstrPointListForm maPmMstrPointListForm) throws Exception
    {
    	MaPmMstrPointListService maPmMstrPointListService = (MaPmMstrPointListService) getBean("maPmMstrPointListService");
    	
    	MaPmMstrCommonDTO maPmMstrMstrCommonDTO = maPmMstrPointListForm.getMaPmMstrCommonDTO();
    	MaPmMstrPointDetailDTO maPmMstrPointDetailDTO = maPmMstrPointListForm.getMaPmMstrPointDetailDTO();
    	
    	maPmMstrPointDetailDTO.setEnterBy(getUser(request).getUserId());
    	maPmMstrMstrCommonDTO.setCompNo(getUser(request).getCompNo());
    	
    	maPmMstrPointListService.insertPointList(maPmMstrPointDetailDTO, maPmMstrMstrCommonDTO, getUser(request));
    	
    	setAjaxStatus(request);
    }
    
    private void insertLovDetail(HttpServletRequest request, MaPmMstrPointListForm maPmMstrPointListForm) throws Exception
    {
    	MaPmMstrPointListService maPmMstrPointListService = (MaPmMstrPointListService) getBean("maPmMstrPointListService");
    	
    	MaPmMstrCommonDTO maPmMstrMstrCommonDTO = maPmMstrPointListForm.getMaPmMstrCommonDTO();
    	MaPmMstrPointDetailDTO maPmMstrPointDetailDTO = maPmMstrPointListForm.getMaPmMstrPointDetailDTO();
    	
    	maPmMstrPointDetailDTO.setEnterBy(getUser(request).getUserId());
    	maPmMstrMstrCommonDTO.setCompNo(getUser(request).getCompNo());
    	
    	maPmMstrPointListService.insertLovDetail(maPmMstrPointDetailDTO, maPmMstrMstrCommonDTO, getUser(request));
    	
    	setAjaxStatus(request);
    }
}