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
 * 통보문서 - 목록
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
    /** 통보문서-목록DAO */
    private AppNotListDAO appNotListDAO = null;
    /** 결재문서작성-DAO */
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
        
        // sheet의 row 수만큼 반복한다. 
        for (Iterator it = appNotList.iterator(); it.hasNext();)
        {
            appNotDTO = (AppNotDTO) it.next();
            
            // 해당 row의 상태(입력, 수정, 삭제)를 판단하여 값을 조회한다.
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
        appDocReqDTO.setAppStatus("APP03"); // 승인(통보 처리만 하는것이므로 결재상태는 승인)
        
        // 결재문서해더 작성
        appDocReqDAO.insertAppDocReq(appDocReqDTO);
        
        // 결재문서List 입력
        appDocReqDAO.insertAppDocList(appDocReqDTO);
        
        String[] appNotUser = appDocReqDTO.getAppNotUser();
//        FlowDtlDTO flowDtlDTO = null;
//        
//        for (String notUser : appNotUser)
//        {
//            flowDtlDTO = new FlowDtlDTO();
//            flowDtlDTO.setSeqNo("1");       // 모든 유저 순번 1로 함
//            flowDtlDTO.setUserId(notUser);
//            flowDtlDTO.setAppType("NT");    // 통보처리
//
//            appDocReqDAO.insertAppFlow(flowDtlDTO, appDocReqDTO);
//        }
        
        // 통보대상자 모두 처리대상 처리
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