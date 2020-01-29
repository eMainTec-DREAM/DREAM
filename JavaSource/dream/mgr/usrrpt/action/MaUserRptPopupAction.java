package dream.mgr.usrrpt.action;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.mgr.usrrpt.dto.MaUserRptCommonDTO;
import dream.mgr.usrrpt.form.MaUserRptPopupForm;
import dream.mgr.usrrpt.service.MaUserRptPopupService;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRException;


/**
 * �޴� - ��� action
 * @author  kim21017
 * @version $Id: MaUserRptListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/maUserRptPopup" name="maUserRptPopupForm"
 *                input="/dream/mgr/usrrpt/maUserRptPopup.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maUserRptPopup" path="/dream/mgr/usrrpt/maUserRptPopup.jsp"
 *                        redirect="false"
 */
public class MaUserRptPopupAction extends AuthAction
{
    /** ��ȸ */
    public static final int USER_RPT_LIST_FIND 		= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaUserRptPopupForm maUserRptPopupForm = (MaUserRptPopupForm) form;
        
        switch (maUserRptPopupForm.getStrutsAction())
        {
            case MaUserRptListAction.USER_RPT_LIST_FIND:
                findList(request, maUserRptPopupForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaUserRptListAction.BASE_GRID_EXPORT:
            	findList(request, maUserRptPopupForm,response);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("maUserRptPopup");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaUserRptPopupForm maUserRptPopupForm) throws IOException
    {
        super.setHeader(request, response, maUserRptPopupForm.getListId(),maUserRptPopupForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: MaUserRptListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param maUserRptPopupForm
     * @throws IOException 
     * @throws JRException 
     * @throws DRException 
     * @throws Exception
     */
    private void findList(HttpServletRequest request, MaUserRptPopupForm maUserRptPopupForm, HttpServletResponse response) throws IOException, JRException 
    {
    	MaUserRptPopupService maUserRptPopupService = (MaUserRptPopupService) getBean("maUserRptPopupService");        

    	MaUserRptCommonDTO maUserRptCommonDTO = maUserRptPopupForm.getMaUserRptCommonDTO();

    	String errMsg = "";
        //����Ʈ�� ��ȸ�Ѵ�.
        List resultList = null;
		try {
			resultList = maUserRptPopupService.findList(maUserRptCommonDTO,getUser(request));
		} catch (Exception e) {
			e.printStackTrace();
			
			errMsg = e.getLocalizedMessage();
			errMsg = errMsg.split(":")[errMsg.split(":").length-1];
			resultList = new ArrayList();
		}finally{
		}

		maUserRptPopupService.makeLogForScript(maUserRptCommonDTO,errMsg, getUser(request));
		
		
		//-------------------Commmon Dynamic Jasper Report ----------------------// 
//		JasperFactory report = new JasperFactory(); ///ȸ���(�ɼ�), ȸ��ΰ� (�ɼ�)
//		report.setMainTitle(maUserRptCommonDTO.getTitle()); // ����Ʈ Ÿ��Ʋ (�ɼ�)
//		
//		report.setChartType("lineChart"); //��Ʈ �ʼ� //barChart,stackedBarChart,lineChart,areaChart,stackedBar3DChart,pieChart
//		report.setCategory("�����"); //íƮ �ʼ�
//		report.setSerie("���","���2"); //íƮ �ʼ�
//
//		report.setShowValues(true); //íƮ �ɼ�
//
//		report.makeReport(resultList);
//		report.view();
		//-----------------------------------------------------------------------//
		
        super.makeJsonListForReport(resultList, request, response, maUserRptPopupForm.getListId(), errMsg);
    }
  
}
