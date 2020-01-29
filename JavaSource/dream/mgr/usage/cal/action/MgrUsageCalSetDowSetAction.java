package dream.mgr.usage.cal.action;

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
import dream.mgr.usage.cal.dto.MgrUsageCalSetDowSetDTO;
import dream.mgr.usage.cal.form.MgrUsageCalSetDowSetForm;
import dream.mgr.usage.cal.service.MgrUsageCalSetDowSetService;

/**
 * 요일별 설정 목록
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @struts:action path="/mgrUsageCalSetDowSetList" name="mgrUsageCalSetDowSetForm"
 *                input="/dream/mgr/usage/cal/mgrUsageCalSetDowSetList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/mgrUsageCalSetDowSetDetail" name="mgrUsageCalSetDowSetForm"
 *                input="/dream/mgr/usage/cal/mgrUsageCalSetDowSetDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrUsageCalSetDowSetList" path="/dream/mgr/usage/cal/mgrUsageCalSetDowSetList.jsp"
 *                        redirect="false"
 * @struts.action-forward name="mgrUsageCalSetDowSetDetail" path="/dream/mgr/usage/cal/mgrUsageCalSetDowSetDetail.jsp"
 *                        redirect="false"
 */
public class MgrUsageCalSetDowSetAction extends AuthAction
{
    /** 목록 조회 */
    public static final int LINE_DOW_LIST_FIND 			= 8001;
    /** 목록 삭제 */
    public static final int LINE_DOW_LIST_DELETE 		= 7002;
    /** 상세 조회 */
    public static final int LINE_DOW_DETAIL_INIT 		= 8002;
    /** 상세 저장 */
    public static final int LINE_DOW_DETAIL_INPUT 		= 5001;
    /** 상세 수정 */
    public static final int LINE_DOW_DETAIL_UPDATE 		= 6001;
    /** 상세 유효성 검사 */
    public static final int LINE_DOW_DAY_CHECK	 		= 6002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MgrUsageCalSetDowSetForm mgrUsageCalSetDowSetForm = (MgrUsageCalSetDowSetForm) form;
        
        switch (mgrUsageCalSetDowSetForm.getStrutsAction())
        {
        
            case MgrUsageCalSetDowSetAction.LINE_DOW_LIST_FIND:
                findDowList(request,response, mgrUsageCalSetDowSetForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrUsageCalSetDowSetAction.BASE_SET_HEADER:
            	super.setHeader(request, response, mgrUsageCalSetDowSetForm.getListId(), mgrUsageCalSetDowSetForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrUsageCalSetDowSetAction.LINE_DOW_LIST_DELETE:
            	deleteDowList(request,mgrUsageCalSetDowSetForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MgrUsageCalSetDowSetAction.LINE_DOW_DETAIL_INIT:
                // 페이지 조회
                findDetail(request, mgrUsageCalSetDowSetForm);
                returnActionForward = mapping.findForward("mgrUsageCalSetDowSetDetail");
                break;
            case MgrUsageCalSetDowSetAction.LINE_DOW_DETAIL_UPDATE:
            	updateDetail(mgrUsageCalSetDowSetForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MgrUsageCalSetDowSetAction.LINE_DOW_DETAIL_INPUT:
            	insertDetail(mgrUsageCalSetDowSetForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MgrUsageCalSetDowSetAction.LINE_DOW_DAY_CHECK:
            	validDay(mgrUsageCalSetDowSetForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MgrUsageCalSetDowSetAction.BASE_GRID_EXPORT:
            	findDowList(request,response, mgrUsageCalSetDowSetForm, true);
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
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param request
     * @param mgrUsageCalSetDowSetForm
     * @throws Exception
     */
    private void findDowList(HttpServletRequest request,HttpServletResponse response, MgrUsageCalSetDowSetForm mgrUsageCalSetDowSetForm, boolean excelExport) throws Exception
    {
        MgrUsageCalSetDowSetService mgrUsageCalSetDowSetService = (MgrUsageCalSetDowSetService) getBean("mgrUsageCalSetDowSetService");
        MgrUsageCalSetDowSetDTO mgrUsageCalSetDowSetDTO = mgrUsageCalSetDowSetForm.getMgrUsageCalSetDowSetDTO();
        
    	//Paging
        mgrUsageCalSetDowSetDTO.setIsLoadMaxCount("Y".equals(mgrUsageCalSetDowSetForm.getIsLoadMaxCount())?true:false);
        mgrUsageCalSetDowSetDTO.setFirstRow(mgrUsageCalSetDowSetForm.getFirstRow());
        mgrUsageCalSetDowSetDTO.setOrderBy(mgrUsageCalSetDowSetForm.getOrderBy());
        mgrUsageCalSetDowSetDTO.setDirection(mgrUsageCalSetDowSetForm.getDirection());
        
        //리스트를 조회한다.
        List resultList = mgrUsageCalSetDowSetService.findDowList(mgrUsageCalSetDowSetDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(mgrUsageCalSetDowSetForm.getIsTotalCount()) == 0 && !excelExport) totalCount = mgrUsageCalSetDowSetService.findTotalCount(mgrUsageCalSetDowSetDTO, getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,mgrUsageCalSetDowSetForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param request
     * @param mgrUsageCalSetDowSetForm
     * @throws Exception
     */
    private void deleteDowList(HttpServletRequest request, MgrUsageCalSetDowSetForm mgrUsageCalSetDowSetForm) throws Exception
    {
    	MgrUsageCalSetDowSetService mgrUsageCalSetDowSetService = (MgrUsageCalSetDowSetService) getBean("mgrUsageCalSetDowSetService");
        
    	String[] deleteRows = mgrUsageCalSetDowSetForm.getDeleteRows();
        User user = getUser(request);
    
    	mgrUsageCalSetDowSetService.deleteDowList(deleteRows, user);
    	
    	setAjaxStatus(request);
    }
    

    /**
     * 요일별 설정 상세 조회
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param request
     * @param mgrUsageCalSetDowSetForm
     */
    private void findDetail(HttpServletRequest request, MgrUsageCalSetDowSetForm mgrUsageCalSetDowSetForm)throws Exception 
    {   
        // Service 객체 생성
    	MgrUsageCalSetDowSetService mgrUsageCalSetDowSetService = (MgrUsageCalSetDowSetService)getBean("mgrUsageCalSetDowSetService");
    	
    	MgrUsageCalSetDowSetDTO mgrUsageCalSetDowSetDTO = mgrUsageCalSetDowSetForm.getMgrUsageCalSetDowSetDTO();
        
        // 조회된 상세 부분
        mgrUsageCalSetDowSetDTO = mgrUsageCalSetDowSetService.findDetail(mgrUsageCalSetDowSetDTO, getUser(request));
        
        // 조회된 상세  셋팅한다.
        mgrUsageCalSetDowSetForm.setMgrUsageCalSetDowSetDTO(mgrUsageCalSetDowSetDTO);
    }
    /**
     * detail update
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param mgrUsageCalSetDowSetForm
     * @param request
     */
    private void updateDetail(MgrUsageCalSetDowSetForm mgrUsageCalSetDowSetForm, HttpServletRequest request) throws Exception
    {
    	MgrUsageCalSetDowSetService mgrUsageCalSetDowSetService = (MgrUsageCalSetDowSetService) getBean("mgrUsageCalSetDowSetService");
        
        MgrUsageCalSetDowSetDTO mgrUsageCalSetDowSetDTO = mgrUsageCalSetDowSetForm.getMgrUsageCalSetDowSetDTO();
        
        mgrUsageCalSetDowSetService.updateDetail(mgrUsageCalSetDowSetDTO,getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param mgrUsageCalSetDowSetForm
     * @param request
     */
    private void insertDetail(MgrUsageCalSetDowSetForm mgrUsageCalSetDowSetForm, HttpServletRequest request) throws Exception
    {
    	MgrUsageCalSetDowSetService mgrUsageCalSetDowSetService = (MgrUsageCalSetDowSetService) getBean("mgrUsageCalSetDowSetService");
        
        MgrUsageCalSetDowSetDTO mgrUsageCalSetDowSetDTO = mgrUsageCalSetDowSetForm.getMgrUsageCalSetDowSetDTO();
        
        mgrUsageCalSetDowSetService.insertDetail(mgrUsageCalSetDowSetDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * 요일 중복 데이터 검사
     */
    private void validDay(MgrUsageCalSetDowSetForm mgrUsageCalSetDowSetForm, HttpServletRequest request) throws Exception
    {
    	MgrUsageCalSetDowSetService mgrUsageCalSetDowSetService = (MgrUsageCalSetDowSetService) getBean("mgrUsageCalSetDowSetService");
        
    	MgrUsageCalSetDTO mgrUsageCalSetDTO = mgrUsageCalSetDowSetForm.getMgrUsageCalSetDTO();
    	MgrUsageCalSetDowSetDTO mgrUsageCalSetDowSetDTO = mgrUsageCalSetDowSetForm.getMgrUsageCalSetDowSetDTO();
        
        String isValid = mgrUsageCalSetDowSetService.validDay(mgrUsageCalSetDTO, mgrUsageCalSetDowSetDTO, getUser(request));
        setAjaxDesc(request,isValid);
    }
 
}