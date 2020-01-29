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
 * 결재요청 
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
    /** 결재문서처리 */
    private AppObjService appObjService = null;
    /** 결재상신DAO */
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
        // 해당 결재종류의 문서번호로 결재요청중인 문서가 있는지 체크한다.
        if (!"".equals(appDocReqDTO.getObjectNo()) && appDocReqDAO.checkAppDocObj(appDocReqDTO)) return "ERROR";
        
        //===============================================
        // 결재문서해더 작성
        appDocReqDAO.insertAppDocReq(appDocReqDTO);
        //===============================================
        
        //===============================================
        // 결재문서List 입력
        appDocReqDAO.insertAppDocList(appDocReqDTO);
        //===============================================
        
        //===============================================
        // 결재선 입력시 기안자를 입력
        appDocReqDAO.insertRootFlow(appDocReqDTO);
        
        // 결재선 입력
        saveFlowDtl(flowDtlDTOList, appDocReqDTO);
        //===============================================
        
        //==================================
        // 결재시 마스터문서의 상태를 변경한다.
        setDocStatus(appDocReqDTO, "A");
        //==================================
        
        // 결재 상태 보정(결재가 통보만 있다면 결재 승인 상태로 변경한다.
        String appStatus = setAppDocStatus(appDocReqDTO);
        if ("APP03".equals(appStatus))
        {
            //==================================
            // 결재시 마스터문서의 상태를 변경한다.
            setDocStatus(appDocReqDTO, "C");
            //==================================
        }
        
        return appStatus;
    }
    
    /**
     * 결재 상태 보정(결재가 통보만 있다면 결재 승인 상태로 변경한다.
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
            // 통보이외의 승인구분이 없다면 승인 처리함
            appStatus = "APP03";
            
            appDocReqDTO.setAppStatus(appStatus);
            appDocReqDAO.setAppDocStatus(appDocReqDTO);
        }
        
        return appStatus;
    }
    
    /**
     * 결재선 입력
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
//        // sheet의 row 수만큼 반복한다. 
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
        
        // 첫번째 결재(처음에 합의/통보가 있을수 있어서 seqNo로 만 구분할수 없다.)
        /** 처음결재자 */
        boolean isFirst = true;
        /** 바로전 결재상태 */
        String lastAppType = "";
        
        /** 순번 */
        int seqNo = 0;
        
        // 입력된 결재선을 조회한다.
        String[][] flowDtlArray = appDocReqDAO.getAppFlowList(appDocReqDTO);
        for (int i=0; i<flowDtlArray.length; i++)
        {
            String appFlowNo = flowDtlArray[i][0];
            String appType = flowDtlArray[i][1];
            
            // 바로전 결재구분이 [합의] 이고, 현재도 [합의]라면 seqNo를 바로전과 같은 값으로 한다.
            if ("GR".equals(lastAppType) && "GR".equals(appType)){}
            else seqNo++;
                
            // 첫번째 결재 대상인지 체크
            isFirst = checkFirstUser(lastAppType, appType, isFirst);
            
            String wfAction = "";
            String wfStatus = "";
            
            if (isFirst)
            {
                wfAction = "P";    // 결재대상
                wfStatus = "Z";    // 대기
            }
            else
            {
                wfAction = "Z";    // 대기
                wfStatus = "Z";    //  대기 
            }
            
            appDocReqDAO.updateWfAction(wfAction, wfStatus, appFlowNo, seqNo);
            
            lastAppType = appType;
        }
    }
    
    /**
     * 첫번째 결재대상인지 체크
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
         * isFirst : false 라면 false
         * isFirst :true 인경우 
         *     - lastAppType : null 인경우 true
         *     - lastAppType : 결재(AP) 인경우 false
         *     - lastAppType : 통보(NT) 인경우 true
         *     - lastAppType : 합의(GR) 
         *         - appType : 합의(GR) 인경우 true
         *         - appType : 결재(AP)/통보(NT) 인경우 false
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
     * 결재상신시 마스터문서의 상태를 변경한다.
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
            appObjDTO.setWfStatus(wfStatus);  // A:요청, C:승인(통보만 넣고 결재처리한 경우)
            
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
