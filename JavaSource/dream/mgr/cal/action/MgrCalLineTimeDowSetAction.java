package dream.mgr.cal.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.mgr.cal.dto.MgrCalLineTimeDowSetDTO;
import dream.mgr.cal.dto.MgrCalLineTimeSetDTO;
import dream.mgr.cal.form.MgrCalLineTimeDowSetForm;
import dream.mgr.cal.service.MgrCalLineTimeDowSetService;

/**
 * 요일별 설정 목록
 * @author  euna0207
 * @version $Id: MgrCalLineTimeDowSetAction.java,v 1.0 2015/12/04 09:09:30 euna0207 Exp $
 * @since   1.0
 * @struts:action path="/mgrCalLineTimeDowSetList" name="mgrCalLineTimeDowSetForm"
 *                input="/dream/mgr/cal/mgrCalLineTimeDowSetList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/mgrCalLineTimeDowSetDetail" name="mgrCalLineTimeDowSetForm"
 *                input="/dream/mgr/cal/mgrCalLineTimeDowSetDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrCalLineTimeDowSetList" path="/dream/mgr/cal/mgrCalLineTimeDowSetList.jsp"
 *                        redirect="false"
 * @struts.action-forward name="mgrCalLineTimeDowSetDetail" path="/dream/mgr/cal/mgrCalLineTimeDowSetDetail.jsp"
 *                        redirect="false"
 */
public class MgrCalLineTimeDowSetAction extends AuthAction
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
        MgrCalLineTimeDowSetForm mgrCalLineTimeDowSetForm = (MgrCalLineTimeDowSetForm) form;
        
        switch (mgrCalLineTimeDowSetForm.getStrutsAction())
        {
        
            case MgrCalLineTimeDowSetAction.LINE_DOW_LIST_FIND:
                findDowList(request,response, mgrCalLineTimeDowSetForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrCalLineTimeDowSetAction.BASE_SET_HEADER:
            	super.setHeader(request, response, mgrCalLineTimeDowSetForm.getListId(), mgrCalLineTimeDowSetForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrCalLineTimeDowSetAction.LINE_DOW_LIST_DELETE:
            	deleteDowList(request,mgrCalLineTimeDowSetForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MgrCalLineTimeDowSetAction.LINE_DOW_DETAIL_INIT:
                // 페이지 조회
                findDetail(request, mgrCalLineTimeDowSetForm);
                returnActionForward = mapping.findForward("mgrCalLineTimeDowSetDetail");
                break;
            case MgrCalLineTimeDowSetAction.LINE_DOW_DETAIL_UPDATE:
            	updateDetail(mgrCalLineTimeDowSetForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MgrCalLineTimeDowSetAction.LINE_DOW_DETAIL_INPUT:
            	insertDetail(mgrCalLineTimeDowSetForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MgrCalLineTimeDowSetAction.LINE_DOW_DAY_CHECK:
            	validDay(mgrCalLineTimeDowSetForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MgrCalLineTimeDowSetAction.BASE_GRID_EXPORT:
            	findDowList(request,response, mgrCalLineTimeDowSetForm, true);
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
     * @author  euna0207
     * @version $Id: MgrCalLineTimeDowSetAction.java,v 1.0 2015/12/02 09:10:21 euna0207 Exp $
     * @since   1.0
     * 
     * @param request
     * @param mgrCalLineTimeDowSetForm
     * @throws Exception
     */
    private void findDowList(HttpServletRequest request,HttpServletResponse response, MgrCalLineTimeDowSetForm mgrCalLineTimeDowSetForm, boolean excelExport) throws Exception
    {
        MgrCalLineTimeDowSetService mgrCalLineTimeDowSetService = (MgrCalLineTimeDowSetService) getBean("mgrCalLineTimeDowSetService");
        MgrCalLineTimeDowSetDTO mgrCalLineTimeDowSetDTO = mgrCalLineTimeDowSetForm.getMgrCalLineTimeDowSetDTO();
        
    	//Paging
        mgrCalLineTimeDowSetDTO.setIsLoadMaxCount("Y".equals(mgrCalLineTimeDowSetForm.getIsLoadMaxCount())?true:false);
        mgrCalLineTimeDowSetDTO.setFirstRow(mgrCalLineTimeDowSetForm.getFirstRow());
        mgrCalLineTimeDowSetDTO.setOrderBy(mgrCalLineTimeDowSetForm.getOrderBy());
        mgrCalLineTimeDowSetDTO.setDirection(mgrCalLineTimeDowSetForm.getDirection());
        
        //리스트를 조회한다.
        List resultList = mgrCalLineTimeDowSetService.findDowList(mgrCalLineTimeDowSetDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(mgrCalLineTimeDowSetForm.getIsTotalCount()) == 0 && !excelExport) totalCount = mgrCalLineTimeDowSetService.findTotalCount(mgrCalLineTimeDowSetDTO, getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,mgrCalLineTimeDowSetForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  euna0207
     * @version $Id: MgrCalLineTimeDowSetAction.java,v 1.0 2015/12/02 09:10:21 euna0207 Exp $
     * @since   1.0
     * 
     * @param request
     * @param mgrCalLineTimeDowSetForm
     * @throws Exception
     */
    private void deleteDowList(HttpServletRequest request, MgrCalLineTimeDowSetForm mgrCalLineTimeDowSetForm) throws Exception
    {
    	MgrCalLineTimeDowSetService mgrCalLineTimeDowSetService = (MgrCalLineTimeDowSetService) getBean("mgrCalLineTimeDowSetService");
        
    	String[] deleteRows = mgrCalLineTimeDowSetForm.getDeleteRows();
        User user = getUser(request);
    
    	mgrCalLineTimeDowSetService.deleteDowList(deleteRows, user);
    	
    	setAjaxStatus(request);
    }
    

    /**
     * 요일별 설정 상세 조회
     * @author euna0207
     * @version $Id: MgrCalLineTimeDowSetAction.java,v 1.2 2015/12/02 01:21:30 euna0207 Exp $
     * @since   1.0
     * 
     * @param request
     * @param mgrCalLineTimeDowSetForm
     */
    private void findDetail(HttpServletRequest request, MgrCalLineTimeDowSetForm mgrCalLineTimeDowSetForm)throws Exception 
    {   
        // Service 객체 생성
    	MgrCalLineTimeDowSetService mgrCalLineTimeDowSetService = (MgrCalLineTimeDowSetService)getBean("mgrCalLineTimeDowSetService");
    	
    	MgrCalLineTimeDowSetDTO mgrCalLineTimeDowSetDTO = mgrCalLineTimeDowSetForm.getMgrCalLineTimeDowSetDTO();
        
        // 조회된 상세 부분
        mgrCalLineTimeDowSetDTO = mgrCalLineTimeDowSetService.findDetail(mgrCalLineTimeDowSetDTO, getUser(request));
        
        // 조회된 상세  셋팅한다.
        mgrCalLineTimeDowSetForm.setMgrCalLineTimeDowSetDTO(mgrCalLineTimeDowSetDTO);
    }
    /**
     * detail update
     * @author  euna0207
     * @version $Id: MgrCalLineTimeDowSetAction.java,v 1.2 2015/12/02 01:21:30 euna0207 Exp $
     * @since   1.0
     * 
     * @param mgrCalLineTimeDowSetForm
     * @param request
     */
    private void updateDetail(MgrCalLineTimeDowSetForm mgrCalLineTimeDowSetForm, HttpServletRequest request) throws Exception
    {
    	MgrCalLineTimeDowSetService mgrCalLineTimeDowSetService = (MgrCalLineTimeDowSetService) getBean("mgrCalLineTimeDowSetService");
        
        MgrCalLineTimeDowSetDTO mgrCalLineTimeDowSetDTO = mgrCalLineTimeDowSetForm.getMgrCalLineTimeDowSetDTO();
        
        mgrCalLineTimeDowSetService.updateDetail(mgrCalLineTimeDowSetDTO,getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  euna0207
     * @version $Id: MgrCalLineTimeDowSetAction.java,v 1.2 2015/12/02 01:21:30 euna0207 Exp $
     * @since   1.0
     * 
     * @param mgrCalLineTimeDowSetForm
     * @param request
     */
    private void insertDetail(MgrCalLineTimeDowSetForm mgrCalLineTimeDowSetForm, HttpServletRequest request) throws Exception
    {
    	MgrCalLineTimeDowSetService mgrCalLineTimeDowSetService = (MgrCalLineTimeDowSetService) getBean("mgrCalLineTimeDowSetService");
        
        MgrCalLineTimeDowSetDTO mgrCalLineTimeDowSetDTO = mgrCalLineTimeDowSetForm.getMgrCalLineTimeDowSetDTO();
        
        mgrCalLineTimeDowSetService.insertDetail(mgrCalLineTimeDowSetDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * 요일 중복 데이터 검사
     */
    private void validDay(MgrCalLineTimeDowSetForm mgrCalLineTimeDowSetForm, HttpServletRequest request) throws Exception
    {
    	MgrCalLineTimeDowSetService mgrCalLineTimeDowSetService = (MgrCalLineTimeDowSetService) getBean("mgrCalLineTimeDowSetService");
        
    	MgrCalLineTimeSetDTO mgrCalLineTimeSetDTO = mgrCalLineTimeDowSetForm.getMgrCalLineTimeSetDTO();
    	MgrCalLineTimeDowSetDTO mgrCalLineTimeDowSetDTO = mgrCalLineTimeDowSetForm.getMgrCalLineTimeDowSetDTO();
        
        String isValid = mgrCalLineTimeDowSetService.validDay(mgrCalLineTimeSetDTO, mgrCalLineTimeDowSetDTO, getUser(request));
        setAjaxDesc(request,isValid);
    }
 
}