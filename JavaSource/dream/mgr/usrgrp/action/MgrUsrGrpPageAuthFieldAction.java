package dream.mgr.usrgrp.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.config.service.ConfigService;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.mgr.usrgrp.dto.MgrUsrGrpPageAuthFieldDTO;
import dream.mgr.usrgrp.form.MgrUsrGrpPageAuthFieldForm;
import dream.mgr.usrgrp.service.MgrUsrGrpPageAuthFieldService;

/**
 * 화면권한설정상세탭버튼권한
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/mgrUsrGrpPageAuthFieldList" name="mgrUsrGrpPageAuthFieldForm"
 *                input="/dream/mgr/usrgrp/mgrUsrGrpPageAuthFieldList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/mgrUsrGrpPageAuthFieldDetail" name="mgrUsrGrpPageAuthFieldForm"
 *                input="/dream/mgr/usrgrp/mgrUsrGrpPageAuthFieldDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrUsrGrpPageAuthFieldDetail" path="/dream/mgr/usrgrp/mgrUsrGrpPageAuthFieldDetail.jsp"
 *                        redirect="false"
 */

public class MgrUsrGrpPageAuthFieldAction extends AuthAction
{
    /** 목록 조회 */
    public static final int LIST_FIND           = 1001;
    /** 제한 없음 */
    public static final int LIST_NO_LIMIT    	= 1002;
    /** 수정 불가 */
    public static final int LIST_NO_EDIT    	= 1003;
    /** 조회 불가 */
    public static final int LIST_NO_VIEW    	= 1004;
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int DETAIL_INIT         = 1005;
    /** 상세 수정 */
    public static final int DETAIL_UPDATE       = 1006;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MgrUsrGrpPageAuthFieldForm mgrUsrGrpPageAuthFieldForm = (MgrUsrGrpPageAuthFieldForm) form;
        
        switch (mgrUsrGrpPageAuthFieldForm.getStrutsAction())
        {
            case MgrUsrGrpPageAuthFieldAction.BASE_SET_HEADER:
                setHeader(request, response, mgrUsrGrpPageAuthFieldForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrUsrGrpPageAuthFieldAction.LIST_FIND:
                findList(request, response, mgrUsrGrpPageAuthFieldForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case MgrUsrGrpPageAuthFieldAction.BASE_GRID_EXPORT:
            	findList(request, response, mgrUsrGrpPageAuthFieldForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            case MgrUsrGrpPageAuthFieldAction.DETAIL_INIT:
                findDetail(request, response, mgrUsrGrpPageAuthFieldForm);
                returnActionForward = mapping.findForward("mgrUsrGrpPageAuthFieldDetail");
                break;
            case MgrUsrGrpPageAuthFieldAction.DETAIL_UPDATE:
                updateDetail(request, response, mgrUsrGrpPageAuthFieldForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MgrUsrGrpPageAuthFieldAction.LIST_NO_LIMIT:
                noLimitAuth(request, mgrUsrGrpPageAuthFieldForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;      
            case MgrUsrGrpPageAuthFieldAction.LIST_NO_EDIT:
                noEditAuth(request, mgrUsrGrpPageAuthFieldForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;                 
            case MgrUsrGrpPageAuthFieldAction.LIST_NO_VIEW:
            	noViewAuth(request, mgrUsrGrpPageAuthFieldForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;                 
            default:
            	returnActionForward = mapping.getInputForward();
                break;
        }
        return returnActionForward;
    }

	private void setHeader(HttpServletRequest request, HttpServletResponse response, MgrUsrGrpPageAuthFieldForm mgrUsrGrpPageAuthFieldForm) throws IOException
    {
        super.setHeader(request, response, mgrUsrGrpPageAuthFieldForm.getListId(), mgrUsrGrpPageAuthFieldForm.getCurrentPageId()); 
    }
    
    private void findList(HttpServletRequest request, HttpServletResponse response, MgrUsrGrpPageAuthFieldForm mgrUsrGrpPageAuthFieldForm, boolean excelExport) throws Exception
    {
    	MgrUsrGrpPageAuthFieldService mgrUsrGrpPageAuthFieldService = (MgrUsrGrpPageAuthFieldService) getBean("mgrUsrGrpPageAuthFieldService");
    	MgrUsrGrpPageAuthFieldDTO mgrUsrGrpPageAuthFieldDTO = mgrUsrGrpPageAuthFieldForm.getMgrUsrGrpPageAuthFieldDTO();

    	//Paging
    	mgrUsrGrpPageAuthFieldDTO.setIsLoadMaxCount("Y".equals(mgrUsrGrpPageAuthFieldForm.getIsLoadMaxCount())?true:false);
    	mgrUsrGrpPageAuthFieldDTO.setFirstRow(mgrUsrGrpPageAuthFieldForm.getFirstRow());
    	mgrUsrGrpPageAuthFieldDTO.setOrderBy(mgrUsrGrpPageAuthFieldForm.getOrderBy());
    	mgrUsrGrpPageAuthFieldDTO.setDirection(mgrUsrGrpPageAuthFieldForm.getDirection());
    	
    	User user = getUser(request);
    	
        List resultList = mgrUsrGrpPageAuthFieldService.findList(mgrUsrGrpPageAuthFieldDTO, user);
        //Paging
        String totalCount = "";
        if(Integer.parseInt(mgrUsrGrpPageAuthFieldForm.getIsTotalCount()) == 0 && !excelExport) totalCount = mgrUsrGrpPageAuthFieldService.findTotalCount(mgrUsrGrpPageAuthFieldDTO,getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,mgrUsrGrpPageAuthFieldForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
        
    private void findDetail(HttpServletRequest request, HttpServletResponse response, MgrUsrGrpPageAuthFieldForm mgrUsrGrpPageAuthFieldForm) throws Exception 
    {   
        MgrUsrGrpPageAuthFieldService mgrUsrGrpPageAuthFieldService = (MgrUsrGrpPageAuthFieldService)getBean("mgrUsrGrpPageAuthFieldService");
        
        MgrUsrGrpPageAuthFieldDTO mgrUsrGrpPageAuthFieldDTO = mgrUsrGrpPageAuthFieldForm.getMgrUsrGrpPageAuthFieldDTO();

        User user = getUser(request);
        
        mgrUsrGrpPageAuthFieldDTO = mgrUsrGrpPageAuthFieldService.findDetail(mgrUsrGrpPageAuthFieldDTO, user);
        mgrUsrGrpPageAuthFieldForm.setMgrUsrGrpPageAuthFieldDTO(mgrUsrGrpPageAuthFieldDTO);
    }

    private void updateDetail(HttpServletRequest request, HttpServletResponse response, MgrUsrGrpPageAuthFieldForm mgrUsrGrpPageAuthFieldForm) throws Exception
    {
        MgrUsrGrpPageAuthFieldService mgrUsrGrpPageAuthFieldService = (MgrUsrGrpPageAuthFieldService)getBean("mgrUsrGrpPageAuthFieldService");
        MgrUsrGrpPageAuthFieldDTO  mgrUsrGrpPageAuthFieldDTO = mgrUsrGrpPageAuthFieldForm.getMgrUsrGrpPageAuthFieldDTO();
        
        User user = getUser(request);
        
        mgrUsrGrpPageAuthFieldService.updateDetail(mgrUsrGrpPageAuthFieldDTO, user);
        
        loadSec();
        
        setAjaxStatus(request);
    }
    
    private void loadSec()
    {
        Runnable myThreadTask = new Runnable(){ //Runnable 객체
            public void run(){
                ConfigService configService = (ConfigService) CommonUtil.getBean("configService");
                
                configService.loadSecurityTable();
            }
        };
        
        Thread thread = new Thread(myThreadTask);
        thread.start();
    }
    

	private void noLimitAuth(HttpServletRequest request, MgrUsrGrpPageAuthFieldForm mgrUsrGrpPageAuthFieldForm) throws Exception {

		MgrUsrGrpPageAuthFieldService mgrUsrGrpPageAuthFieldService = (MgrUsrGrpPageAuthFieldService)getBean("mgrUsrGrpPageAuthFieldService");
        MgrUsrGrpPageAuthFieldDTO  mgrUsrGrpPageAuthFieldDTO = mgrUsrGrpPageAuthFieldForm.getMgrUsrGrpPageAuthFieldDTO();
        
    	String[] pgfieldId = mgrUsrGrpPageAuthFieldForm.getDeleteRows();
        String[] ugpgfieldauId = mgrUsrGrpPageAuthFieldForm.getDeleteRowsExt();
        String[] usrgrpId = mgrUsrGrpPageAuthFieldForm.getDeleteRowsExt1();
        String changeStatus = mgrUsrGrpPageAuthFieldDTO.getAuthLimitTypeId();
    	User user = getUser(request);
        
    	mgrUsrGrpPageAuthFieldService.updAuthStatus(pgfieldId, ugpgfieldauId, usrgrpId, changeStatus, user);
        
    	loadSec();
        
        setAjaxStatus(request);
	}
	

	private void noEditAuth(HttpServletRequest request, MgrUsrGrpPageAuthFieldForm mgrUsrGrpPageAuthFieldForm) throws Exception {
		 
		MgrUsrGrpPageAuthFieldService mgrUsrGrpPageAuthFieldService = (MgrUsrGrpPageAuthFieldService)getBean("mgrUsrGrpPageAuthFieldService");
        MgrUsrGrpPageAuthFieldDTO  mgrUsrGrpPageAuthFieldDTO = mgrUsrGrpPageAuthFieldForm.getMgrUsrGrpPageAuthFieldDTO();
        
        String changeStatus = mgrUsrGrpPageAuthFieldDTO.getAuthLimitTypeId();
    	String[] pgfieldId = mgrUsrGrpPageAuthFieldForm.getDeleteRows();
        String[] ugpgfieldauId = mgrUsrGrpPageAuthFieldForm.getDeleteRowsExt();
        String[] usrgrpId = mgrUsrGrpPageAuthFieldForm.getDeleteRowsExt1();
        
    	User user = getUser(request);
        
    	mgrUsrGrpPageAuthFieldService.updAuthStatus(pgfieldId, ugpgfieldauId, usrgrpId, changeStatus, user);
        
    	loadSec();
        
        setAjaxStatus(request);
	}
	

	private void noViewAuth(HttpServletRequest request, MgrUsrGrpPageAuthFieldForm mgrUsrGrpPageAuthFieldForm) throws Exception {
		
		MgrUsrGrpPageAuthFieldService mgrUsrGrpPageAuthFieldService = (MgrUsrGrpPageAuthFieldService)getBean("mgrUsrGrpPageAuthFieldService");
        MgrUsrGrpPageAuthFieldDTO  mgrUsrGrpPageAuthFieldDTO = mgrUsrGrpPageAuthFieldForm.getMgrUsrGrpPageAuthFieldDTO();
        
    	String[] pgfieldId = mgrUsrGrpPageAuthFieldForm.getDeleteRows();
        String[] ugpgfieldauId = mgrUsrGrpPageAuthFieldForm.getDeleteRowsExt();
        String[] usrgrpId = mgrUsrGrpPageAuthFieldForm.getDeleteRowsExt1();
        String changeStatus = mgrUsrGrpPageAuthFieldDTO.getAuthLimitTypeId();
        
    	User user = getUser(request);
        
    	mgrUsrGrpPageAuthFieldService.updAuthStatus(pgfieldId, ugpgfieldauId, usrgrpId, changeStatus, user);
        
    	loadSec();
        
        setAjaxStatus(request);
	}
}
