package dream.mgr.cdsys.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.mgr.cdsys.dto.MgrCdSysCodeListDTO;
import dream.mgr.cdsys.dto.MgrCdSysCommonDTO;
import dream.mgr.cdsys.form.MgrCdSysCodeListForm;
import dream.mgr.cdsys.service.MgrCdSysCodeListService;

/**
 * 시스템코드 detail - code 목록
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @struts:action path="/mgrCdSysCodeList" name="mgrCdSysCodeListForm"
 *                input="/dream/mgr/cdsys/mgrCdSysCodeList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrCdSysCodeList" path="/dream/mgr/cdsys/mgrCdSysCodeList.jsp"
 *                        redirect="false"
 */
public class MgrCdSysCodeListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int LISTTYPE_CODE_LIST_FIND 		= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MgrCdSysCodeListForm mgrCdSysCodeListForm = (MgrCdSysCodeListForm) form;
        
        switch (mgrCdSysCodeListForm.getStrutsAction())
        {
            case MgrCdSysListAction.BASE_SET_HEADER:
                super.setHeader(request, response, mgrCdSysCodeListForm.getListId(), mgrCdSysCodeListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrCdSysCodeListAction.LISTTYPE_CODE_LIST_FIND:
                findCodeList(request, response, mgrCdSysCodeListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrCdSysCodeListAction.BASE_GRID_EXPORT:
            	findCodeList(request,response, mgrCdSysCodeListForm, true);
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
     * @version $Id$
     * @since   1.0
     * 
     * @param request
     * @param mgrCdSysCodeListForm
     * @throws Exception
     */
    private void findCodeList(HttpServletRequest request, HttpServletResponse response, MgrCdSysCodeListForm mgrCdSysCodeListForm, boolean excelExport) throws Exception
    {
        MgrCdSysCodeListService mgrCdSysCodeListService = (MgrCdSysCodeListService) getBean("mgrCdSysCodeListService");
        MgrCdSysCommonDTO mgrCdSysCommonDTO = mgrCdSysCodeListForm.getMgrCdSysCommonDTO();
        mgrCdSysCommonDTO.setUserLang(getUser(request).getLangId());
        
        MgrCdSysCodeListDTO mgrCdSysCodeListDTO = mgrCdSysCodeListForm.getMgrCdSysCodeListDTO();
        
        //Paging
        mgrCdSysCodeListDTO.setIsLoadMaxCount("Y".equals(mgrCdSysCodeListForm.getIsLoadMaxCount())?true:false);
        mgrCdSysCodeListDTO.setFirstRow(mgrCdSysCodeListForm.getFirstRow());
        mgrCdSysCodeListDTO.setOrderBy(mgrCdSysCodeListForm.getOrderBy());
        mgrCdSysCodeListDTO.setDirection(mgrCdSysCodeListForm.getDirection());
        
        //리스트를 조회한다.
        List resultList = mgrCdSysCodeListService.findCodeList(mgrCdSysCommonDTO, mgrCdSysCodeListDTO);
        
        //Paging
        String totalCount = "";
        if(Integer.parseInt(mgrCdSysCodeListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = mgrCdSysCodeListService.findTotalCount(mgrCdSysCommonDTO, mgrCdSysCodeListDTO);

        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, mgrCdSysCodeListForm);//.makeGridExport(resultList, request, response,mgrCdSysListForm.getListId(),mgrCdSysListForm.getCurrentPageId(), mgrCdSysListForm.getFileName());
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
        			
    }
}