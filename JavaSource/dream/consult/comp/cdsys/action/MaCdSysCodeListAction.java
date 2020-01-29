package dream.consult.comp.cdsys.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.struts.ConsultAuthAction;
import dream.consult.comp.cdsys.dto.MaCdSysCodeListDTO;
import dream.consult.comp.cdsys.dto.MaCdSysCommonDTO;
import dream.consult.comp.cdsys.form.MaCdSysCodeListForm;
import dream.consult.comp.cdsys.form.MaCdSysListForm;
import dream.consult.comp.cdsys.service.MaCdSysCodeListService;
import dream.consult.comp.cdsys.service.MaCdSysListService;

/**
 * 시스템코드 detail - code 목록
 * @author  kim21017
 * @version $Id: MaCdSysCodeListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maCdSysCodeList" name="maCdSysCodeListForm"
 *                input="/dream/consult/comp/cdsys/maCdSysCodeList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maCdSysCodeList" path="/dream/consult/comp/cdsys/maCdSysCodeList.jsp"
 *                        redirect="false"
 */
public class MaCdSysCodeListAction extends ConsultAuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int LISTTYPE_CODE_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int LISTTYPE_CODE_LIST_DELETE 		= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaCdSysCodeListForm maCdSysCodeListForm = (MaCdSysCodeListForm) form;
        
        switch (maCdSysCodeListForm.getStrutsAction())
        {
            case MaCdSysListAction.BASE_SET_HEADER:
                super.setHeader(request, response, maCdSysCodeListForm.getListId(), maCdSysCodeListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaCdSysCodeListAction.LISTTYPE_CODE_LIST_FIND:
                findCodeList(request, response, maCdSysCodeListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaCdSysCodeListAction.LISTTYPE_CODE_LIST_DELETE:
            	deleteCodeList(request,maCdSysCodeListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaCdSysCodeListAction.BASE_GRID_EXPORT:
            	findCodeList(request,response, maCdSysCodeListForm);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maCdSysCodeList");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaCdSysCodeListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maCdSysCodeListForm
     * @throws Exception
     */
    private void findCodeList(HttpServletRequest request, HttpServletResponse response, MaCdSysCodeListForm maCdSysCodeListForm) throws Exception
    {
        MaCdSysCodeListService maCdSysCodeListService = (MaCdSysCodeListService) getBean("maCdSysCodeListService");
        MaCdSysCommonDTO maCdSysCommonDTO = maCdSysCodeListForm.getMaCdSysCommonDTO();
        maCdSysCommonDTO.setUserLang(getUser(request).getLangId());
        
        MaCdSysCodeListDTO maCdSysCodeListDTO = maCdSysCodeListForm.getMaCdSysCodeListDTO();
        List resultList = maCdSysCodeListService.findCodeList(maCdSysCommonDTO, maCdSysCodeListDTO);
        
        super.makeJsonResult(resultList, request, response, maCdSysCodeListForm.getListId());
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: MaCdSysCodeListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maCdSysCodeListForm
     * @throws Exception
     */
    private void deleteCodeList(HttpServletRequest request, MaCdSysCodeListForm maCdSysCodeListForm) throws Exception
    {
    	MaCdSysCodeListService maCdSysCodeListService = (MaCdSysCodeListService) getBean("maCdSysCodeListService");
        
//    	List ListTypeCodeDTOList = maCdSysCodeListForm.getListTypeCodeDTOList();
    
    	maCdSysCodeListService.deleteCodeList(maCdSysCodeListForm.getDeleteRows(), maCdSysCodeListForm.getDeleteRowsExt());
    	
    	setAjaxStatus(request);
    }
}