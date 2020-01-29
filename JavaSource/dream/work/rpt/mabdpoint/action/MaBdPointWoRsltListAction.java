package dream.work.rpt.mabdpoint.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.work.rpt.mabdpoint.dto.MaBdPointCommonDTO;
import dream.work.rpt.mabdpoint.dto.MaBdPointWoRsltListDTO;
import dream.work.rpt.mabdpoint.form.MaBdPointWoRsltListForm;
import dream.work.rpt.mabdpoint.service.MaBdPointWoRsltListService;

/**
 * �̻�������ġ - �۾���� ��� action
 * @author  syyang
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/maBdPointWoRsltList" name="maBdPointWoRsltListForm"
 *                input="/dream/work/rpt/bdpoint/maBdPointWoRsltList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maBdPointWoRsltList" path="/dream/work/rpt/bdpoint/maBdPointWoRsltList.jsp"
 *                        redirect="false"
 */
public class MaBdPointWoRsltListAction extends AuthAction
{
    /** ��ȸ */
    public static final int LIST_FIND         = 8001;
    /** ���� */
    public static final int LIST_DELETE       = 7002;
    /** �ű� �۾���� ���� */
    public static final int LIST_WO_INPUT	  = 5001;
    /** ���� �۾���� ���� */
    public static final int LIST_WO_LINK	  = 5002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaBdPointWoRsltListForm maBdPointWoRsltListForm = (MaBdPointWoRsltListForm) form;
        
        super.updateAudit(maBdPointWoRsltListForm.getMaBdPointCommonDTO().getAuditKey()==""?maBdPointWoRsltListForm.getMaBdPointCommonDTO().getAuditKey():maBdPointWoRsltListForm.getMaBdPointCommonDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maBdPointWoRsltListForm.getStrutsAction())
        {
            case MaBdPointWoRsltListAction.LIST_FIND:
            	findList(request, maBdPointWoRsltListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaBdPointWoRsltListAction.LIST_DELETE:
            	deleteList(request, maBdPointWoRsltListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaBdPointWoRsltListAction.LIST_WO_INPUT:
            	insertWoList(request, maBdPointWoRsltListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaBdPointWoRsltListAction.LIST_WO_LINK:
            	woLinkList(request, maBdPointWoRsltListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaBdPointWoRsltListAction.BASE_SET_HEADER:
                setHeader(request, response, maBdPointWoRsltListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaBdPointWoRsltListAction.BASE_GRID_EXPORT:
            	findList(request, maBdPointWoRsltListForm, response, true);
            	returnActionForward = new ActionForward("/gridExport");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

    /**
     * �ű� �۾� ���
     * @author  syyang
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maBdPointWoRsltListForm
     * @throws Exception
     */
    private void insertWoList(HttpServletRequest request, MaBdPointWoRsltListForm maBdPointWoRsltListForm) throws Exception
    {
    	// Service ��ü ����
        MaBdPointWoRsltListService maBdPointWoRsltListService = (MaBdPointWoRsltListService) getBean("maBdPointWoRsltListService");        
        MaBdPointCommonDTO maBdPointCommonDTO = maBdPointWoRsltListForm.getMaBdPointCommonDTO();
        MaBdPointWoRsltListDTO maBdPointWoRsltList = maBdPointWoRsltListForm.getMaBdPointWoRsltListDTO();
        
        maBdPointWoRsltListService.insertWoNgPointRes(maBdPointCommonDTO, maBdPointWoRsltList, getUser(request));
        
        setAjaxStatus(request);
	}
    /**
     * ���� �۾� ���
     * @author  syyang
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maBdPointWoRsltListForm
     * @throws Exception
     */
    private void woLinkList(HttpServletRequest request, MaBdPointWoRsltListForm maBdPointWoRsltListForm) throws Exception
    {
    	// Service ��ü ����
    	MaBdPointWoRsltListService maBdPointWoRsltListService = (MaBdPointWoRsltListService) getBean("maBdPointWoRsltListService");        
    	MaBdPointCommonDTO maBdPointCommonDTO = maBdPointWoRsltListForm.getMaBdPointCommonDTO();
    	MaBdPointWoRsltListDTO maBdPointWoRsltList = maBdPointWoRsltListForm.getMaBdPointWoRsltListDTO();
    	
    	maBdPointWoRsltListService.linkWo(maBdPointCommonDTO, maBdPointWoRsltList, getUser(request));
    	
    	setAjaxStatus(request);
    }

	private void deleteList(HttpServletRequest request, MaBdPointWoRsltListForm maBdPointWoRsltListForm)
    {
        MaBdPointWoRsltListService maBdPointWoRsltListService = (MaBdPointWoRsltListService) getBean("maBdPointWoRsltListService");        

        String[] deleteRows = maBdPointWoRsltListForm.getDeleteRows();    // sheet ����
        
        maBdPointWoRsltListService.deleteList(deleteRows,getUser(request).getCompNo());
        
        setAjaxStatus(request);
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaBdPointWoRsltListForm maBdPointWoRsltListForm) throws IOException
    {
        super.setHeader(request, response, maBdPointWoRsltListForm.getListId(), maBdPointWoRsltListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  syyang
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maBdPointWoRsltListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, MaBdPointWoRsltListForm maBdPointWoRsltListForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
    	MaBdPointWoRsltListService maBdPointWoRsltListService = (MaBdPointWoRsltListService) getBean("maBdPointWoRsltListService");        

    	MaBdPointCommonDTO maBdPointCommonDTO = maBdPointWoRsltListForm.getMaBdPointCommonDTO();
    	MaBdPointWoRsltListDTO maBdPointWoRsltListDTO = maBdPointWoRsltListForm.getMaBdPointWoRsltListDTO();
    	
        //Paging
        maBdPointWoRsltListDTO.setIsLoadMaxCount("Y".equals(maBdPointWoRsltListForm.getIsLoadMaxCount())?true:false);
        maBdPointWoRsltListDTO.setFirstRow(maBdPointWoRsltListForm.getFirstRow());
        maBdPointWoRsltListDTO.setOrderBy(maBdPointWoRsltListForm.getOrderBy());
        maBdPointWoRsltListDTO.setDirection(maBdPointWoRsltListForm.getDirection());
        
        User user = getUser(request);
        //����Ʈ�� ��ȸ�Ѵ�.
        List resultList = maBdPointWoRsltListService.findList(maBdPointCommonDTO,maBdPointWoRsltListDTO,getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(maBdPointWoRsltListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maBdPointWoRsltListService.findTotalCount(maBdPointCommonDTO,maBdPointWoRsltListDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,maBdPointWoRsltListForm.getListId(),maBdPointWoRsltListForm.getCurrentPageId(), maBdPointWoRsltListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, maBdPointWoRsltListForm.getListId());
    }
    
}
