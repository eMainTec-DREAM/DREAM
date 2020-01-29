package dream.pers.appstb.prc.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.ass.asset.action.AssAssetListAction;
import dream.pers.appreq.dto.AppReqCommonDTO;
import dream.pers.appstb.prc.form.AppPrcListForm;
import dream.pers.appstb.prc.service.AppPrcListService;

/**
 * 결재문서 - 목록 
 * @author  javaworker
 * @version $Id: AppPrcListAction.java,v 1.1 2013/08/30 09:11:16 javaworker Exp $
 * @since   1.0
 * @struts:action path="/appPrcList" name="appPrcListForm"
 *                input="/dream/pers/appstb/prc/appPrcList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/maAppPrcList" name="appPrcListForm"
 *                input="/dream/pers/appstb/prc/maAppPrcList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="appPrcList" path="/dream/pers/appstb/prc/appPrcList.jsp"
 *                        redirect="false"
 */
public class AppPrcListAction extends AuthAction
{
    /** 조회 */
    public static final int APP_PRC_FIND 	= 1001;
    /** 삭제 */
    public static final int APP_PRC_DELETE  = 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AppPrcListForm appPrcListForm = (AppPrcListForm) form;
        
        switch (appPrcListForm.getStrutsAction())
        {
            case AppPrcListAction.APP_PRC_DELETE:
                deleteLine(request, appPrcListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case AppPrcListAction.APP_PRC_FIND:
                findMasterList(request, appPrcListForm, response,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AppPrcListAction.BASE_SET_HEADER:
                super.setHeader(request, response, appPrcListForm.getListId(), appPrcListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssAssetListAction.BASE_GRID_EXPORT:
            	findMasterList(request, appPrcListForm, response,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }
    
    private void deleteLine(HttpServletRequest request, AppPrcListForm appPrcListForm)
    {
        AppPrcListService appPrcListService = (AppPrcListService) getBean("appPrcListService");        
        String[] deleteRows = appPrcListForm.getDeleteRows();    // sheet 내역
        
        appPrcListService.deleteLine(deleteRows,getUser(request).getCompNo());
        
        setAjaxStatus(request);
    }

    /**
     * grid find
     * @author  javaworker
     * @version $Id: AppPrcListAction.java,v 1.1 2013/08/30 09:11:16 javaworker Exp $
     * @since   1.0
     * 
     * @param request
     * @param appPrcListForm
     * @throws IOException 
     * @throws Exception
     */
    private void findMasterList(HttpServletRequest request, AppPrcListForm appPrcListForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
        AppPrcListService appPrcListService = (AppPrcListService) getBean("appPrcListService");        

        AppReqCommonDTO appReqCommonDTO = appPrcListForm.getAppReqCommonDTO();
        
        //Paging
        appReqCommonDTO.setIsLoadMaxCount("Y".equals(appPrcListForm.getIsLoadMaxCount())?true:false);
        appReqCommonDTO.setFirstRow(appPrcListForm.getFirstRow());
        appReqCommonDTO.setOrderBy(appPrcListForm.getOrderBy());
        appReqCommonDTO.setDirection(appPrcListForm.getDirection());
        
        User user = getUser(request);
        
        // 마스터 리스트를 조회한다.
        List resultList = appPrcListService.findAppPrcList(appReqCommonDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(appPrcListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = appPrcListService.findTotalCount(appReqCommonDTO,user);
        
        // 조회한 List 를 form에 셋팅한다.
        if(excelExport) super.makeGridExport(resultList, request, response,appPrcListForm.getListId(),appPrcListForm.getCurrentPageId(), appPrcListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }

}