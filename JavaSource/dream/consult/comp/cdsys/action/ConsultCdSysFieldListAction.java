package dream.consult.comp.cdsys.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.ConsultAuthAction;
import dream.consult.comp.cdsys.dto.ConsultCdSysFieldListDTO;
import dream.consult.comp.cdsys.dto.MaCdSysCommonDTO;
import dream.consult.comp.cdsys.form.ConsultCdSysFieldListForm;
import dream.consult.comp.cdsys.service.ConsultCdSysFieldListService;

/**
 * 시스템코드 detail - code 목록
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @struts:action path="/consultCdSysFieldList" name="consultCdSysFieldListForm"
 *                input="/dream/consult/comp/cdsys/consultCdSysFieldList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="consultCdSysFieldList" path="/dream/consult/comp/cdsys/consultCdSysFieldList.jsp"
 *                        redirect="false"
 */
public class ConsultCdSysFieldListAction extends ConsultAuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int LIST_FIND 		= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        ConsultCdSysFieldListForm consultCdSysFieldListForm = (ConsultCdSysFieldListForm) form;
        
        switch (consultCdSysFieldListForm.getStrutsAction())
        {
            case MaCdSysListAction.BASE_SET_HEADER:
                super.setHeader(request, response, consultCdSysFieldListForm.getListId(), consultCdSysFieldListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ConsultCdSysFieldListAction.LIST_FIND:
                findCodeList(request, response, consultCdSysFieldListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case ConsultCdSysFieldListAction.BASE_GRID_EXPORT:
            	findCodeList(request,response, consultCdSysFieldListForm);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("consultCdSysFieldList");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: ConsultCdSysFieldListAction.java,v 1.0 2015/12/02 09:10:21 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param request
     * @param consultCdSysFieldListForm
     * @throws Exception
     */
    private void findCodeList(HttpServletRequest request, HttpServletResponse response, ConsultCdSysFieldListForm consultCdSysFieldListForm) throws Exception
    {
        ConsultCdSysFieldListService consultCdSysFieldListService = (ConsultCdSysFieldListService) getBean("consultCdSysFieldListService");
        MaCdSysCommonDTO maCdSysCommonDTO = consultCdSysFieldListForm.getMaCdSysCommonDTO();
        maCdSysCommonDTO.setUserLang(getUser(request).getLangId());
        
        ConsultCdSysFieldListDTO consultCdSysFieldListDTO = consultCdSysFieldListForm.getConsultCdSysFieldListDTO();
        List resultList = consultCdSysFieldListService.findCodeList(maCdSysCommonDTO, consultCdSysFieldListDTO);
        
        super.makeJsonResult(resultList, request, response, consultCdSysFieldListForm.getListId());
    }
}