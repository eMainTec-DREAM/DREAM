package dream.app.doc.service.spring;

import java.util.Iterator;
import java.util.List;

import dream.app.AppObjDTO;
import dream.app.AppObjService;
import dream.app.doc.dao.AppDocReqDAO;
import dream.app.doc.dto.AppDocReqDTO;
import dream.app.doc.service.AppDocReqService;
//import dream.app.line.dto.FlowDtlDTO;

/**
 * �����û 
 * @author  javaworker
 * @version $Id: AppDocReqServiceImpl.java,v 1.5 2014/02/17 02:39:13 javaworker Exp $
 * @since   1.0
 * @spring.bean id="appDocReqServiceTarget"
 * @spring.txbn id="appDocReqService"
 * @spring.property name="appDocReqDAO" ref="appDocReqDAO"
 * @spring.property name="appObjService" ref="appObjService"
 */
public class AppDocReqServiceImpl implements AppDocReqService
{
    /** ���繮��ó�� */
    private AppObjService appObjService = null;
    /** ������DAO */
    private AppDocReqDAO appDocReqDAO = null;

    public AppDocReqDAO getAppDocReqDAO()
    {
        return appDocReqDAO;
    }
    public void setAppDocReqDAO(AppDocReqDAO appDocReqDAO)
    {
        this.appDocReqDAO = appDocReqDAO;
    }
    
    @Override
    public List findFlowUserList(AppDocReqDTO appDocReqDTO)
    {
        return appDocReqDAO.findFlowUserList(appDocReqDTO);
    }
    
    public String inputAppDocReq(AppDocReqDTO appDocReqDTO, List flowDtlDTOList)
    {
        // �ش� ���������� ������ȣ�� �����û���� ������ �ִ��� üũ�Ѵ�.
        if (!"".equals(appDocReqDTO.getObjectNo()) && appDocReqDAO.checkAppDocObj(appDocReqDTO)) return "ERROR";
        
        //===============================================
        // ���繮���ش� �ۼ�
        appDocReqDAO.insertAppDocReq(appDocReqDTO);
        //===============================================
        
        //===============================================
        // ���繮��List �Է�
        appDocReqDAO.insertAppDocList(appDocReqDTO);
        //===============================================
        
        //===============================================
        // ���缱 �Է½� ����ڸ� �Է�
        appDocReqDAO.insertRootFlow(appDocReqDTO);
        
        // ���缱 �Է�
        saveFlowDtl(flowDtlDTOList, appDocReqDTO);
        //===============================================
        
        //==================================
        // ����� �����͹����� ���¸� �����Ѵ�.
        setDocStatus(appDocReqDTO, "A");
        //==================================
        
        // ���� ���� ����(���簡 �뺸�� �ִٸ� ���� ���� ���·� �����Ѵ�.
        String appStatus = setAppDocStatus(appDocReqDTO);
        if ("APP03".equals(appStatus))
        {
            //==================================
            // ����� �����͹����� ���¸� �����Ѵ�.
            setDocStatus(appDocReqDTO, "C");
            //==================================
        }
        
        return appStatus;
    }
    
    /**
     * ���� ���� ����(���簡 �뺸�� �ִٸ� ���� ���� ���·� �����Ѵ�.
     * @author  javaworker
     * @version $Id: AppDocReqServiceImpl.java,v 1.5 2014/02/17 02:39:13 javaworker Exp $
     * @since   1.0
     * 
     * @param appDocReqDTO
     * @return
     */
    private String setAppDocStatus(AppDocReqDTO appDocReqDTO)
    {
        String appStatus = "APP01";
        
        boolean appLine = appDocReqDAO.checkAppFlow(appDocReqDTO);
        if (!appLine)
        {
            // �뺸�̿��� ���α����� ���ٸ� ���� ó����
            appStatus = "APP03";
            
            appDocReqDTO.setAppStatus(appStatus);
            appDocReqDAO.setAppDocStatus(appDocReqDTO);
        }
        
        return appStatus;
    }
    
    /**
     * ���缱 �Է�
     * @author  javaworker
     * @version $Id: AppDocReqServiceImpl.java,v 1.5 2014/02/17 02:39:13 javaworker Exp $
     * @since   1.0
     * 
     * @param flowHdrDTOList
     * @param enterBy
     */
    private void saveFlowDtl(List flowDtlDTOList, AppDocReqDTO appDocReqDTO)
    {
//        FlowDtlDTO flowDtlDTO = null;
//        
//        // sheet�� row ����ŭ �ݺ��Ѵ�. 
//        for (Iterator it = flowDtlDTOList.iterator(); it.hasNext();)
//        {            
//            flowDtlDTO = (FlowDtlDTO) it.next();
//
//            switch (flowDtlDTO.getDtStatus())
//            {
//                case 'I':
//                    appDocReqDAO.insertAppFlow(flowDtlDTO, appDocReqDTO);
//                    break;
//            }
//        }
        
        // ù��° ����(ó���� ����/�뺸�� ������ �־ seqNo�� �� �����Ҽ� ����.)
        /** ó�������� */
        boolean isFirst = true;
        /** �ٷ��� ������� */
        String lastAppType = "";
        
        /** ���� */
        int seqNo = 0;
        
        // �Էµ� ���缱�� ��ȸ�Ѵ�.
        String[][] flowDtlArray = appDocReqDAO.getAppFlowList(appDocReqDTO);
        for (int i=0; i<flowDtlArray.length; i++)
        {
            String appFlowNo = flowDtlArray[i][0];
            String appType = flowDtlArray[i][1];
            
            // �ٷ��� ���籸���� [����] �̰�, ���絵 [����]��� seqNo�� �ٷ����� ���� ������ �Ѵ�.
            if ("GR".equals(lastAppType) && "GR".equals(appType)){}
            else seqNo++;
                
            // ù��° ���� ������� üũ
            isFirst = checkFirstUser(lastAppType, appType, isFirst);
            
            String wfAction = "";
            String wfStatus = "";
            
            if (isFirst)
            {
                wfAction = "P";    // ������
                wfStatus = "Z";    // ���
            }
            else
            {
                wfAction = "Z";    // ���
                wfStatus = "Z";    //  ��� 
            }
            
            appDocReqDAO.updateWfAction(wfAction, wfStatus, appFlowNo, seqNo);
            
            lastAppType = appType;
        }
    }
    
    /**
     * ù��° ���������� üũ
     * @author  javaworker
     * @version $Id: AppDocReqServiceImpl.java,v 1.5 2014/02/17 02:39:13 javaworker Exp $
     * @since   1.0
     * 
     * @param lastAppType
     * @param appType
     * @param isFirst
     * @return
     */
    private boolean checkFirstUser(String lastAppType, String appType,
            boolean isFirst)
    {
        /*
         * isFirst : false ��� false
         * isFirst :true �ΰ�� 
         *     - lastAppType : null �ΰ�� true
         *     - lastAppType : ����(AP) �ΰ�� false
         *     - lastAppType : �뺸(NT) �ΰ�� true
         *     - lastAppType : ����(GR) 
         *         - appType : ����(GR) �ΰ�� true
         *         - appType : ����(AP)/�뺸(NT) �ΰ�� false
         */
        if (!isFirst) return false;
        
        if (lastAppType == null || "".equals(lastAppType)) return true;
        if ("AP".equals(lastAppType)) return false;
        if ("NT".equals(lastAppType)) return true;
        if ("GR".equals(lastAppType))
        {
            if ("GR".equals(appType)) return true;
        }
        
        return false;
    }
    
    /**
     * �����Ž� �����͹����� ���¸� �����Ѵ�.
     * @author  javaworker
     * @version $Id: AppDocReqServiceImpl.java,v 1.5 2014/02/17 02:39:13 javaworker Exp $
     * @since   1.0
     * 
     * @param AppDocReqDTO
     */
    private void setDocStatus(AppDocReqDTO AppDocReqDTO, String wfStatus) 
    {
        String objectNo = AppDocReqDTO.getObjectNo();
        if (objectNo == null || "".equals(objectNo)) return;
        
        AppObjDTO appObjDTO = new AppObjDTO();
        
        String[] objectNoArray = objectNo.split(",");
        
        String wfType = AppDocReqDTO.getWfType();
        String enterBy = AppDocReqDTO.getEnterBy();
        
        for (int i=0; i<objectNoArray.length; i++)
        {
            appObjDTO.setWfType(wfType);
            appObjDTO.setEnterBy(enterBy);
            appObjDTO.setObjectNo(objectNoArray[i]);
            appObjDTO.setWfStatus(wfStatus);  // A:��û, C:����(�뺸�� �ְ� ����ó���� ���)
            
            appObjService.callService(appObjDTO);
        }
    }
    public AppObjService getAppObjService()
    {
        return appObjService;
    }
    public void setAppObjService(AppObjService appObjService)
    {
        this.appObjService = appObjService;
    }
    
    public String [] findDefaultInform(AppDocReqDTO appDocReqDTO)
    {
        return appDocReqDAO.findDefaultInform(appDocReqDTO);
    }
}
