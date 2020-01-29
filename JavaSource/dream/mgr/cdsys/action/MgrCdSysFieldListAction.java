package dream.mgr.cdsys.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.mgr.cdsys.dto.MgrCdSysCommonDTO;
import dream.mgr.cdsys.dto.MgrCdSysFieldListDTO;
import dream.mgr.cdsys.form.MgrCdSysFieldListForm;
import dream.mgr.cdsys.service.MgrCdSysFieldListService;

/**
 * 시스템코드 detail - code 목록
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @struts:action path="/mgrCdSysFieldList" name="mgrCdSysFieldListForm"
 *                input="/dream/mgr/cdsys/mgrCdSysFieldList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrCdSysFieldList" path="/dream/mgr/cdsys/mgrCdSysFieldList.jsp"
 *                        redirect="false"
 */
public class MgrCdSysFieldListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int LIST_FIND 		= 1001;
    
    protected ActionForward run(ActionMapping mgrpping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MgrCdSysFieldListForm mgrCdSysFieldListForm = (MgrCdSysFieldListForm) form;
        
        switch (mgrCdSysFieldListForm.getStrutsAction())
        {
            case MgrCdSysListAction.BASE_SET_HEADER:
                super.setHeader(request, response, mgrCdSysFieldListForm.getListId(), mgrCdSysFieldListForm.getCurrentPageId());
                returnActionForward = mgrpping.findForward("jsonPage");
                break;
            case MgrCdSysFieldListAction.LIST_FIND:
                findCodeList(request, response, mgrCdSysFieldListForm, false);
                returnActionForward = mgrpping.findForward("jsonPage");
                break;
            case MgrCdSysFieldListAction.BASE_GRID_EXPORT:
            	findCodeList(request,response, mgrCdSysFieldListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mgrpping.getInputForward();
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
     * @param mgrCdSysFieldListForm
     * @throws Exception
     */
    private void findCodeList(HttpServletRequest request, HttpServletResponse response, MgrCdSysFieldListForm mgrCdSysFieldListForm, boolean excelExport) throws Exception
    {
        MgrCdSysFieldListService mgrCdSysFieldListService = (MgrCdSysFieldListService) getBean("mgrCdSysFieldListService");
        MgrCdSysCommonDTO mgrCdSysCommonDTO = mgrCdSysFieldListForm.getMgrCdSysCommonDTO();
        mgrCdSysCommonDTO.setUserLang(getUser(request).getLangId());
        
        MgrCdSysFieldListDTO mgrCdSysFieldListDTO = mgrCdSysFieldListForm.getMgrCdSysFieldListDTO();
        
        //Paging
        mgrCdSysFieldListDTO.setIsLoadMaxCount("Y".equals(mgrCdSysFieldListForm.getIsLoadMaxCount())?true:false);
        mgrCdSysFieldListDTO.setFirstRow(mgrCdSysFieldListForm.getFirstRow());
        mgrCdSysFieldListDTO.setOrderBy(mgrCdSysFieldListForm.getOrderBy());
        mgrCdSysFieldListDTO.setDirection(mgrCdSysFieldListForm.getDirection());
        
        
        List resultList = mgrCdSysFieldListService.findCodeList(mgrCdSysCommonDTO, mgrCdSysFieldListDTO);
        
        //Paging
        String totalCount = "";
        if(Integer.parseInt(mgrCdSysFieldListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = mgrCdSysFieldListService.findTotalCount(mgrCdSysCommonDTO, mgrCdSysFieldListDTO);

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, mgrCdSysFieldListForm);//.makeGridExport(resultList, request, response,mgrCdSysListForm.getListId(),mgrCdSysListForm.getCurrentPageId(), mgrCdSysListForm.getFileName());
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
}