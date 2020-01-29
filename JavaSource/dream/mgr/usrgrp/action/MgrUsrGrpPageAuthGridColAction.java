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
import dream.mgr.usrgrp.dto.MgrUsrGrpPageAuthGridColDTO;
import dream.mgr.usrgrp.form.MgrUsrGrpPageAuthFieldForm;
import dream.mgr.usrgrp.form.MgrUsrGrpPageAuthGridColForm;
import dream.mgr.usrgrp.service.MgrUsrGrpPageAuthFieldService;
import dream.mgr.usrgrp.service.MgrUsrGrpPageAuthGridColService;

/**
 * 화면권한설정상세탭버튼권한
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/mgrUsrGrpPageAuthGridColList" name="mgrUsrGrpPageAuthGridColForm"
 *                input="/dream/mgr/usrgrp/mgrUsrGrpPageAuthGridColList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/mgrUsrGrpPageAuthGridColDetail" name="mgrUsrGrpPageAuthGridColForm"
 *                input="/dream/mgr/usrgrp/mgrUsrGrpPageAuthGridColDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrUsrGrpPageAuthGridColDetail" path="/dream/mgr/usrgrp/mgrUsrGrpPageAuthGridColDetail.jsp"
 *                        redirect="false"
 */

public class MgrUsrGrpPageAuthGridColAction extends AuthAction
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
        MgrUsrGrpPageAuthGridColForm mgrUsrGrpPageAuthGridColForm = (MgrUsrGrpPageAuthGridColForm) form;
        
        switch (mgrUsrGrpPageAuthGridColForm.getStrutsAction())
        {
            case MgrUsrGrpPageAuthGridColAction.BASE_SET_HEADER:
                setHeader(request, response, mgrUsrGrpPageAuthGridColForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrUsrGrpPageAuthGridColAction.LIST_FIND:
                findList(request, response, mgrUsrGrpPageAuthGridColForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;         
            case MgrUsrGrpPageAuthGridColAction.BASE_GRID_EXPORT:
            	findList(request, response, mgrUsrGrpPageAuthGridColForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            case MgrUsrGrpPageAuthGridColAction.DETAIL_INIT:
                findDetail(request, response, mgrUsrGrpPageAuthGridColForm);
                returnActionForward = mapping.findForward("mgrUsrGrpPageAuthGridColDetail");
                break;
            case MgrUsrGrpPageAuthGridColAction.DETAIL_UPDATE:
                updateDetail(request, response, mgrUsrGrpPageAuthGridColForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MgrUsrGrpPageAuthGridColAction.LIST_NO_LIMIT:
                noLimitAuth(request, mgrUsrGrpPageAuthGridColForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;      
            case MgrUsrGrpPageAuthGridColAction.LIST_NO_EDIT:
                noEditAuth(request, mgrUsrGrpPageAuthGridColForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;                 
            case MgrUsrGrpPageAuthGridColAction.LIST_NO_VIEW:
            	noViewAuth(request, mgrUsrGrpPageAuthGridColForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break; 
            default:
            	returnActionForward = mapping.getInputForward();
                break;
        }
        return returnActionForward;
    }


	private void setHeader(HttpServletRequest request, HttpServletResponse response, MgrUsrGrpPageAuthGridColForm mgrUsrGrpPageAuthGridColForm) throws IOException
    {
        super.setHeader(request, response, mgrUsrGrpPageAuthGridColForm.getListId(), mgrUsrGrpPageAuthGridColForm.getCurrentPageId()); 
    }
    
    private void findList(HttpServletRequest request, HttpServletResponse response, MgrUsrGrpPageAuthGridColForm mgrUsrGrpPageAuthGridColForm, boolean excelExport) throws Exception
    {
    	MgrUsrGrpPageAuthGridColService mgrUsrGrpPageAuthGridColService = (MgrUsrGrpPageAuthGridColService) getBean("mgrUsrGrpPageAuthGridColService");
    	MgrUsrGrpPageAuthGridColDTO mgrUsrGrpPageAuthGridColDTO = mgrUsrGrpPageAuthGridColForm.getMgrUsrGrpPageAuthGridColDTO();

    	//Paging
    	mgrUsrGrpPageAuthGridColDTO.setIsLoadMaxCount("Y".equals(mgrUsrGrpPageAuthGridColForm.getIsLoadMaxCount())?true:false);
    	mgrUsrGrpPageAuthGridColDTO.setFirstRow(mgrUsrGrpPageAuthGridColForm.getFirstRow());
    	mgrUsrGrpPageAuthGridColDTO.setOrderBy(mgrUsrGrpPageAuthGridColForm.getOrderBy());
    	mgrUsrGrpPageAuthGridColDTO.setDirection(mgrUsrGrpPageAuthGridColForm.getDirection());
    	
    	User user = getUser(request);
    	
        List resultList = mgrUsrGrpPageAuthGridColService.findList(mgrUsrGrpPageAuthGridColDTO, user);
        //Paging
        String totalCount = "";
        if(Integer.parseInt(mgrUsrGrpPageAuthGridColForm.getIsTotalCount()) == 0 && !excelExport) totalCount = mgrUsrGrpPageAuthGridColService.findTotalCount(mgrUsrGrpPageAuthGridColDTO,getUser(request));

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,mgrUsrGrpPageAuthGridColForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }

    
    private void findDetail(HttpServletRequest request, HttpServletResponse response, MgrUsrGrpPageAuthGridColForm mgrUsrGrpPageAuthGridColForm) throws Exception 
    {   
        MgrUsrGrpPageAuthGridColService mgrUsrGrpPageAuthGridColService = (MgrUsrGrpPageAuthGridColService)getBean("mgrUsrGrpPageAuthGridColService");
        
        MgrUsrGrpPageAuthGridColDTO mgrUsrGrpPageAuthGridColDTO = mgrUsrGrpPageAuthGridColForm.getMgrUsrGrpPageAuthGridColDTO();

        User user = getUser(request);
        
        mgrUsrGrpPageAuthGridColDTO = mgrUsrGrpPageAuthGridColService.findDetail(mgrUsrGrpPageAuthGridColDTO, user);
        mgrUsrGrpPageAuthGridColForm.setMgrUsrGrpPageAuthGridColDTO(mgrUsrGrpPageAuthGridColDTO);
    }

    private void updateDetail(HttpServletRequest request, HttpServletResponse response, MgrUsrGrpPageAuthGridColForm mgrUsrGrpPageAuthGridColForm) throws Exception
    {
        MgrUsrGrpPageAuthGridColService mgrUsrGrpPageAuthGridColService = (MgrUsrGrpPageAuthGridColService)getBean("mgrUsrGrpPageAuthGridColService");
        MgrUsrGrpPageAuthGridColDTO  mgrUsrGrpPageAuthGridColDTO = mgrUsrGrpPageAuthGridColForm.getMgrUsrGrpPageAuthGridColDTO();
        
        User user = getUser(request);
        
        mgrUsrGrpPageAuthGridColService.updateDetail(mgrUsrGrpPageAuthGridColDTO, user);
        
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
    

	private void noViewAuth(HttpServletRequest request, MgrUsrGrpPageAuthGridColForm mgrUsrGrpPageAuthGridColForm) throws Exception {

		MgrUsrGrpPageAuthGridColService mgrUsrGrpPageAuthGridColService = (MgrUsrGrpPageAuthGridColService)getBean("mgrUsrGrpPageAuthGridColService");
        MgrUsrGrpPageAuthGridColDTO  mgrUsrGrpPageAuthGridColDTO = mgrUsrGrpPageAuthGridColForm.getMgrUsrGrpPageAuthGridColDTO();
        
    	String[] pggridcolId = mgrUsrGrpPageAuthGridColForm.getDeleteRows();
        String[] ugpgridcolauId = mgrUsrGrpPageAuthGridColForm.getDeleteRowsExt();
        String[] usrgrpId = mgrUsrGrpPageAuthGridColForm.getDeleteRowsExt1();
        String changeStatus = mgrUsrGrpPageAuthGridColDTO.getAuthLimitTypeId();
    	User user = getUser(request);
        
    	mgrUsrGrpPageAuthGridColService.updAuthStatus(pggridcolId, ugpgridcolauId, usrgrpId, changeStatus, user);
        
    	loadSec();
        
        setAjaxStatus(request);
	}



	private void noEditAuth(HttpServletRequest request, MgrUsrGrpPageAuthGridColForm mgrUsrGrpPageAuthGridColForm) throws Exception {

		MgrUsrGrpPageAuthGridColService mgrUsrGrpPageAuthGridColService = (MgrUsrGrpPageAuthGridColService)getBean("mgrUsrGrpPageAuthGridColService");
        MgrUsrGrpPageAuthGridColDTO  mgrUsrGrpPageAuthGridColDTO = mgrUsrGrpPageAuthGridColForm.getMgrUsrGrpPageAuthGridColDTO();
        
		String[] pggridcolId = mgrUsrGrpPageAuthGridColForm.getDeleteRows();
        String[] ugpgridcolauId = mgrUsrGrpPageAuthGridColForm.getDeleteRowsExt();
        String[] usrgrpId = mgrUsrGrpPageAuthGridColForm.getDeleteRowsExt1();
        String changeStatus = mgrUsrGrpPageAuthGridColDTO.getAuthLimitTypeId();
    	User user = getUser(request);
        
    	mgrUsrGrpPageAuthGridColService.updAuthStatus(pggridcolId, ugpgridcolauId, usrgrpId, changeStatus, user);
        
    	loadSec();
        
        setAjaxStatus(request);
	}



	private void noLimitAuth(HttpServletRequest request, MgrUsrGrpPageAuthGridColForm mgrUsrGrpPageAuthGridColForm) throws Exception {
		MgrUsrGrpPageAuthGridColService mgrUsrGrpPageAuthGridColService = (MgrUsrGrpPageAuthGridColService)getBean("mgrUsrGrpPageAuthGridColService");
        MgrUsrGrpPageAuthGridColDTO  mgrUsrGrpPageAuthGridColDTO = mgrUsrGrpPageAuthGridColForm.getMgrUsrGrpPageAuthGridColDTO();
        
		String[] pggridcolId = mgrUsrGrpPageAuthGridColForm.getDeleteRows();
        String[] ugpgridcolauId = mgrUsrGrpPageAuthGridColForm.getDeleteRowsExt();
        String[] usrgrpId = mgrUsrGrpPageAuthGridColForm.getDeleteRowsExt1();
        String changeStatus = mgrUsrGrpPageAuthGridColDTO.getAuthLimitTypeId();
    	User user = getUser(request);
        
    	mgrUsrGrpPageAuthGridColService.updAuthStatus(pggridcolId, ugpgridcolauId, usrgrpId, changeStatus, user);
        
    	loadSec();
        
        setAjaxStatus(request);		
		
		
	}

}
