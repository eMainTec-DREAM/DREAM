package dream.app.not.service.spring;

import java.util.Iterator;
import java.util.List;

import dream.app.doc.dao.AppDocReqDAO;
import dream.app.doc.dto.AppDocReqDTO;
//import dream.app.line.dto.FlowDtlDTO;
import dream.app.not.dao.AppNotListDAO;
import dream.app.not.dto.AppNotCommonDTO;
import dream.app.not.dto.AppNotDTO;
import dream.app.not.service.AppNotListService;

/**
 * �뺸���� - ���
 * @author javaworker
 * @version $Id: AppNotListServiceImpl.java,v 1.1 2013/08/30 09:15:05 javaworker Exp $
 * @since 1.0
 * 
 * @spring.bean id="appNotListServiceTarget"
 * @spring.txbn id="appNotListService"
 * @spring.property name="appNotListDAO" ref="appNotListDAO"
 * @spring.property name="appDocReqDAO" ref="appDocReqDAO"
 */
public class AppNotListServiceImpl implements AppNotListService
{
    /** �뺸����-���DAO */
    private AppNotListDAO appNotListDAO = null;
    /** ���繮���ۼ�-DAO */
    private AppDocReqDAO appDocReqDAO = null;

    public AppNotListDAO getAppNotListDAO()
    {
        return appNotListDAO;
    }

    public void setAppNotListDAO(AppNotListDAO appNotListDAO)
    {
        this.appNotListDAO = appNotListDAO;
    }
    
    public List findAppNotList(AppNotCommonDTO appNotCommonDTO)
    {
        return appNotListDAO.findAppNotList(appNotCommonDTO);
    }
    
    public int findAppNotListCount(AppNotCommonDTO appNotCommonDTO)
    {
        return appNotListDAO.findAppNotListCount(appNotCommonDTO);
    }
    
    @Override
    public void confirmAppNot(List appNotList, AppNotCommonDTO appNotCommonDTO)
    {
        AppNotDTO appNotDTO = null;
        
        // sheet�� row ����ŭ �ݺ��Ѵ�. 
        for (Iterator it = appNotList.iterator(); it.hasNext();)
        {
            appNotDTO = (AppNotDTO) it.next();
            
            // �ش� row�� ����(�Է�, ����, ����)�� �Ǵ��Ͽ� ���� ��ȸ�Ѵ�.
            switch (appNotDTO.getDtStatus())
            {
                case 'U':
                    appNotDTO.setEnterBy(appNotCommonDTO.getEnterBy());
                    appNotListDAO.confirmAppNot(appNotDTO);
                    break;
            }
        }
    }
    
    public void inputAppDocNot(AppDocReqDTO appDocReqDTO)
    {
        String appDocNo = appNotListDAO.getNextSequence("SQ2APP_DOC_NO");
        appDocReqDTO.setAppDocNo(appDocNo);
        appDocReqDTO.setAppStatus("APP03"); // ����(�뺸 ó���� �ϴ°��̹Ƿ� ������´� ����)
        
        // ���繮���ش� �ۼ�
        appDocReqDAO.insertAppDocReq(appDocReqDTO);
        
        // ���繮��List �Է�
        appDocReqDAO.insertAppDocList(appDocReqDTO);
        
        String[] appNotUser = appDocReqDTO.getAppNotUser();
//        FlowDtlDTO flowDtlDTO = null;
//        
//        for (String notUser : appNotUser)
//        {
//            flowDtlDTO = new FlowDtlDTO();
//            flowDtlDTO.setSeqNo("1");       // ��� ���� ���� 1�� ��
//            flowDtlDTO.setUserId(notUser);
//            flowDtlDTO.setAppType("NT");    // �뺸ó��
//
//            appDocReqDAO.insertAppFlow(flowDtlDTO, appDocReqDTO);
//        }
        
        // �뺸����� ��� ó����� ó��
        appDocReqDAO.updateNotWfAction(appDocReqDTO);
    }

    public AppDocReqDAO getAppDocReqDAO()
    {
        return appDocReqDAO;
    }

    public void setAppDocReqDAO(AppDocReqDAO appDocReqDAO)
    {
        this.appDocReqDAO = appDocReqDAO;
    }
}