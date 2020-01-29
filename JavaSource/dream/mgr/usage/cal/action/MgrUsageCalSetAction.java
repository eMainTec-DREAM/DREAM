package dream.mgr.usage.cal.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.mgr.usage.cal.dto.MgrUsageCalSetDTO;
import dream.mgr.usage.cal.form.MgrUsageCalSetForm;
import dream.mgr.usage.cal.service.MgrUsageCalSetService;

/**
 * 사용달력설정 - 목록 
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @struts:action path="/mgrUsageCalSetList" name="mgrUsageCalSetForm"
 *                input="/dream/mgr/usage/cal/mgrUsageCalSetList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/mgrUsageCalSetDetail" name="mgrUsageCalSetForm"
 *                input="/dream/mgr/usage/cal/mgrUsageCalSetDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrUsageCalSetList" path="/dream/mgr/usage/cal/mgrUsageCalSetList.jsp"
 *                        redirect="false"
 * @struts.action-forward name="mgrUsageCalSetDetail" path="/dream/mgr/usage/cal/mgrUsageCalSetDetail.jsp"
 *                        redirect="false"
 */
public class MgrUsageCalSetAction extends AuthAction
{
    /** 조회 */
    public static final int LINE_LIST_FIND 			= 8001;
    /** 삭제 */
    public static final int LINE_LIST_DELETE      	= 7002;
    /** 상세 조회 */
    public static final int LINE_DETAIL_INIT 		= 8002;
    /** 상세 저장 */
    public static final int LINE_DETAIL_INPUT 		= 5001;
    /** 상세 수정 */
    public static final int LINE_DETAIL_UPDATE      = 6001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MgrUsageCalSetForm mgrUsageCalSetForm = (MgrUsageCalSetForm) form;
        
        switch (mgrUsageCalSetForm.getStrutsAction())
        {
            case MgrUsageCalSetAction.LINE_LIST_FIND:
                findList(request, mgrUsageCalSetForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrUsageCalSetAction.LINE_LIST_DELETE:
                deleteList(request, mgrUsageCalSetForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MgrUsageCalSetAction.LINE_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, mgrUsageCalSetForm);
                returnActionForward = mapping.getInputForward();
                break;
            case MgrUsageCalSetAction.LINE_DETAIL_INPUT:
                insertDetail(mgrUsageCalSetForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MgrUsageCalSetAction.LINE_DETAIL_UPDATE:
            	updateDetail(mgrUsageCalSetForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MgrUsageCalSetAction.BASE_SET_HEADER:
                setHeader(request, response, mgrUsageCalSetForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrUsageCalSetAction.BASE_GRID_EXPORT:
            	findList(request, mgrUsageCalSetForm, response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MgrUsageCalSetForm mgrUsageCalSetForm) throws IOException
    {
        super.setHeader(request, response, mgrUsageCalSetForm.getListId(),mgrUsageCalSetForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param request
     * @param mgrUsageCalSetForm
     * @param response
     * @throws Exception
     */
    private void findList(HttpServletRequest request, MgrUsageCalSetForm mgrUsageCalSetForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	MgrUsageCalSetService mgrUsageCalSetService = (MgrUsageCalSetService) getBean("mgrUsageCalSetService");        

    	MgrUsageCalSetDTO mgrUsageCalSetDTO = mgrUsageCalSetForm.getMgrUsageCalSetDTO();
        
        mgrUsageCalSetDTO.setIsLoadMaxCount("Y".equals(mgrUsageCalSetForm.getIsLoadMaxCount())?true:false);
        mgrUsageCalSetDTO.setFirstRow(mgrUsageCalSetForm.getFirstRow());
        mgrUsageCalSetDTO.setOrderBy(mgrUsageCalSetForm.getOrderBy());
        mgrUsageCalSetDTO.setDirection(mgrUsageCalSetForm.getDirection());
        //리스트를 조회한다.
        List resultList = mgrUsageCalSetService.findList(mgrUsageCalSetDTO,getUser(request));

        String totalCount = "";
        if(Integer.parseInt(mgrUsageCalSetForm.getIsTotalCount()) == 0 && !excelExport) totalCount = mgrUsageCalSetService.findTotalCount(mgrUsageCalSetDTO,getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,mgrUsageCalSetForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
	}
    
    private void findDetail(HttpServletRequest request, MgrUsageCalSetForm mgrUsageCalSetForm) throws Exception
    {
    	MgrUsageCalSetService mgrUsageCalSetService = (MgrUsageCalSetService) getBean("mgrUsageCalSetService");        
    	
    	MgrUsageCalSetDTO mgrUsageCalSetDTO = mgrUsageCalSetForm.getMgrUsageCalSetDTO();
    	
    	// 유저
    	User user = getUser(request);

        // 조회된 상세 부분
    	mgrUsageCalSetDTO = mgrUsageCalSetService.findDetail(mgrUsageCalSetDTO,user);
    	
        // 조회된 상세  셋팅한다.
    	mgrUsageCalSetForm.setMgrUsageCalSetDTO(mgrUsageCalSetDTO);

    }
    
    /**
     * delete
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param mgrUsageCalSetForm
     * @throws Exception
     */
    private void deleteList(HttpServletRequest request, MgrUsageCalSetForm mgrUsageCalSetForm) throws Exception
    {
        MgrUsageCalSetService mgrUsageCalSetService = (MgrUsageCalSetService) getBean("mgrUsageCalSetService");
        
        String[] deleteRows = mgrUsageCalSetForm.getDeleteRows();
    
        mgrUsageCalSetService.deleteList(deleteRows, getUser(request));
        
        setAjaxStatus(request);
    }

    private void insertDetail(MgrUsageCalSetForm mgrUsageCalSetForm, HttpServletRequest request) throws Exception
    {
    	// Service 객체 생성
    	MgrUsageCalSetService mgrUsageCalSetService = (MgrUsageCalSetService) getBean("mgrUsageCalSetService");
    	
    	MgrUsageCalSetDTO mgrUsageCalSetDTO = mgrUsageCalSetForm.getMgrUsageCalSetDTO();
        
        User user = getUser(request);
        
        mgrUsageCalSetService.insertDetail(mgrUsageCalSetDTO, user);
        
        setAjaxStatus(request);
    }
    
    private void updateDetail(MgrUsageCalSetForm mgrUsageCalSetForm, HttpServletRequest request) throws Exception
    {
    	// Service 객체 생성
    	MgrUsageCalSetService mgrUsageCalSetService = (MgrUsageCalSetService) getBean("mgrUsageCalSetService");
    	
    	MgrUsageCalSetDTO mgrUsageCalSetDTO = mgrUsageCalSetForm.getMgrUsageCalSetDTO();
    	
    	User user = getUser(request);
    	
    	mgrUsageCalSetService.updateDetail(mgrUsageCalSetDTO, user);
    	
    	setAjaxStatus(request);
    }
}
