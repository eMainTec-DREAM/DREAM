package dream.mgr.cal.action;


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
import dream.mgr.cal.dto.MgrCalLineTimeSetDTO;
import dream.mgr.cal.form.MgrCalLineTimeSetForm;
import dream.mgr.cal.service.MgrCalLineTimeSetService;

/**
 * 가동달력설정 - 목록 
 * @author  euna0207
 * @version $Id: MgrCalLineTimeSetAction.java,v 1.0 2015/12/02 09:10:21 euna0207 Exp $
 * @since   1.0
 * @struts:action path="/mgrCalLineTimeSetList" name="mgrCalLineTimeSetForm"
 *                input="/dream/mgr/cal/mgrCalLineTimeSetList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/mgrCalLineTimeSetDetail" name="mgrCalLineTimeSetForm"
 *                input="/dream/mgr/cal/mgrCalLineTimeSetDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrCalLineTimeSetList" path="/dream/mgr/cal/mgrCalLineTimeSetList.jsp"
 *                        redirect="false"
 * @struts.action-forward name="mgrCalLineTimeSetDetail" path="/dream/mgr/cal/mgrCalLineTimeSetDetail.jsp"
 *                        redirect="false"
 */
public class MgrCalLineTimeSetAction extends AuthAction
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
        MgrCalLineTimeSetForm mgrCalLineTimeSetForm = (MgrCalLineTimeSetForm) form;
        
        switch (mgrCalLineTimeSetForm.getStrutsAction())
        {
            case MgrCalLineTimeSetAction.LINE_LIST_FIND:
                findList(request, mgrCalLineTimeSetForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrCalLineTimeSetAction.LINE_LIST_DELETE:
                deleteList(request, mgrCalLineTimeSetForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MgrCalLineTimeSetAction.LINE_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, mgrCalLineTimeSetForm);
                returnActionForward = mapping.getInputForward();
                break;
            case MgrCalLineTimeSetAction.LINE_DETAIL_INPUT:
                insertDetail(mgrCalLineTimeSetForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MgrCalLineTimeSetAction.LINE_DETAIL_UPDATE:
            	updateDetail(mgrCalLineTimeSetForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MgrCalLineTimeSetAction.BASE_SET_HEADER:
                setHeader(request, response, mgrCalLineTimeSetForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrCalLineTimeSetAction.BASE_GRID_EXPORT:
            	findList(request, mgrCalLineTimeSetForm, response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MgrCalLineTimeSetForm mgrCalLineTimeSetForm) throws IOException
    {
        super.setHeader(request, response, mgrCalLineTimeSetForm.getListId(),mgrCalLineTimeSetForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  euna0207
     * @version $Id: MgrCalLineTimeSetAction.java,v 1.0 2015/12/02 09:10:21 euna0207 Exp $
     * @since   1.0
     * 
     * @param request
     * @param mgrCalLineTimeSetForm
     * @param response
     * @throws Exception
     */
    private void findList(HttpServletRequest request, MgrCalLineTimeSetForm mgrCalLineTimeSetForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	MgrCalLineTimeSetService mgrCalLineTimeSetService = (MgrCalLineTimeSetService) getBean("mgrCalLineTimeSetService");        

    	MgrCalLineTimeSetDTO mgrCalLineTimeSetDTO = mgrCalLineTimeSetForm.getMgrCalLineTimeSetDTO();
        
        mgrCalLineTimeSetDTO.setIsLoadMaxCount("Y".equals(mgrCalLineTimeSetForm.getIsLoadMaxCount())?true:false);
        mgrCalLineTimeSetDTO.setFirstRow(mgrCalLineTimeSetForm.getFirstRow());
        mgrCalLineTimeSetDTO.setOrderBy(mgrCalLineTimeSetForm.getOrderBy());
        mgrCalLineTimeSetDTO.setDirection(mgrCalLineTimeSetForm.getDirection());
        //리스트를 조회한다.
        List resultList = mgrCalLineTimeSetService.findList(mgrCalLineTimeSetDTO,getUser(request));

        String totalCount = "";
        if(Integer.parseInt(mgrCalLineTimeSetForm.getIsTotalCount()) == 0 && !excelExport) totalCount = mgrCalLineTimeSetService.findTotalCount(mgrCalLineTimeSetDTO,getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,mgrCalLineTimeSetForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
	}
    
    private void findDetail(HttpServletRequest request, MgrCalLineTimeSetForm mgrCalLineTimeSetForm) throws Exception
    {
    	MgrCalLineTimeSetService mgrCalLineTimeSetService = (MgrCalLineTimeSetService) getBean("mgrCalLineTimeSetService");        
    	
    	MgrCalLineTimeSetDTO mgrCalLineTimeSetDTO = mgrCalLineTimeSetForm.getMgrCalLineTimeSetDTO();
    	
    	// 유저
    	User user = getUser(request);

        // 조회된 상세 부분
    	mgrCalLineTimeSetDTO = mgrCalLineTimeSetService.findDetail(mgrCalLineTimeSetDTO,user);
    	
        // 조회된 상세  셋팅한다.
    	mgrCalLineTimeSetForm.setMgrCalLineTimeSetDTO(mgrCalLineTimeSetDTO);

    }
    
    /**
     * delete
     * @author  euna0207
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param mgrCalLineTimeSetForm
     * @throws Exception
     */
    private void deleteList(HttpServletRequest request, MgrCalLineTimeSetForm mgrCalLineTimeSetForm) throws Exception
    {
        MgrCalLineTimeSetService mgrCalLineTimeSetService = (MgrCalLineTimeSetService) getBean("mgrCalLineTimeSetService");
        
        String[] deleteRows = mgrCalLineTimeSetForm.getDeleteRows();
    
        mgrCalLineTimeSetService.deleteList(deleteRows, getUser(request));
        
        setAjaxStatus(request);
    }

    private void insertDetail(MgrCalLineTimeSetForm mgrCalLineTimeSetForm, HttpServletRequest request) throws Exception
    {
    	// Service 객체 생성
    	MgrCalLineTimeSetService mgrCalLineTimeSetService = (MgrCalLineTimeSetService) getBean("mgrCalLineTimeSetService");
    	
    	MgrCalLineTimeSetDTO mgrCalLineTimeSetDTO = mgrCalLineTimeSetForm.getMgrCalLineTimeSetDTO();
        
        User user = getUser(request);
        
        mgrCalLineTimeSetService.insertDetail(mgrCalLineTimeSetDTO, user);
        
        setAjaxStatus(request);
    }
    
    private void updateDetail(MgrCalLineTimeSetForm mgrCalLineTimeSetForm, HttpServletRequest request) throws Exception
    {
    	// Service 객체 생성
    	MgrCalLineTimeSetService mgrCalLineTimeSetService = (MgrCalLineTimeSetService) getBean("mgrCalLineTimeSetService");
    	
    	MgrCalLineTimeSetDTO mgrCalLineTimeSetDTO = mgrCalLineTimeSetForm.getMgrCalLineTimeSetDTO();
    	
    	User user = getUser(request);
    	
    	mgrCalLineTimeSetService.updateDetail(mgrCalLineTimeSetDTO, user);
    	
    	setAjaxStatus(request);
    }
}
