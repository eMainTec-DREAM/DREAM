package dream.app.doc.dao;

import java.util.List;

import common.util.QueryBuffer;
import dream.app.doc.dto.AppDocReqDTO;
//import dream.app.line.dto.FlowDtlDTO;

/**
 * 결재요청(결재문서작성)
 * @author javaworker
 * @version $Id: AppDocReqDAO.java,v 1.10 2014/07/02 04:13:55 pochul2423 Exp $
 * @since 1.0
 */
public interface AppDocReqDAO
{
    public void insertAppDocReq(AppDocReqDTO appDocReqDTO);
    
    public void insertRootFlow(AppDocReqDTO appDocReqDTO);
    
//    public int insertAppFlow(FlowDtlDTO flowDtlDTO, AppDocReqDTO appDocReqDTO)
//    {
//        QueryBuffer query = new QueryBuffer();
//
//        query.append("INSERT INTO T2APP_FLOW (                          ");
//        query.append("    app_flow_no, app_desc,                        ");
//        query.append("    enter_by,    enter_date,                      ");
//        query.append("    app_doc_no,  seq_no,                          ");
//        query.append("    user_id,     app_type                         ");
//        query.append(")                                                 ");
//        query.append("VALUES (                                          ");
//        query.append("    SQ2APP_FLOW_NO.NextVal,    ?,                 ");
//        query.append("    ?,           TO_CHAR(SYSDATE, 'YYYYMMDD'),    ");
//        query.append("    ?,           ?,                               ");
//        query.append("    ?,           ?                                ");
//        query.append(")                                                 ");
//
//        Object[] objects = new Object[] {
//                appDocReqDTO.getAppDesc(),
//                appDocReqDTO.getEnterBy(),
//                appDocReqDTO.getAppDocNo(),
//                flowDtlDTO.getSeqNo(),
//                flowDtlDTO.getUserId(),
//                flowDtlDTO.getAppType()
//        };
//            
//        return this.getJdbcTemplate().update(query.toString(), objects);
//    }

    /**
     * 결재문서List 입력
     * @author  javaworker
     * @version $Id: AppDocReqDAO.java,v 1.10 2014/07/02 04:13:55 pochul2423 Exp $
     * @since   1.0
     * 
     * @param appDocReqDTO
     */
    public void insertAppDocList(AppDocReqDTO appDocReqDTO);

    /**
     * 입력된 결재선을 조회한다.
     * @author  javaworker
     * @version $Id: AppDocReqDAO.java,v 1.10 2014/07/02 04:13:55 pochul2423 Exp $
     * @since   1.0
     * 
     * @param appDocReqDTO
     * @return
     */
    public String[][] getAppFlowList(AppDocReqDTO appDocReqDTO);

    /**
     * 결재선 처리대상, 처리상태, 바로전 결재자 처리상태를 셋팅한다.
     * @author  javaworker
     * @version $Id: AppDocReqDAO.java,v 1.10 2014/07/02 04:13:55 pochul2423 Exp $
     * @param seqNo 
     * @param appFlowNo 
     * @param lastStatus 
     * @param wfStatus 
     * @param wfAction 
     * @since   1.0
     * 
     * @param flowDtlDTO
     * @param appDocReqDTO
     */
    public void updateWfAction(String wfAction, String wfStatus, String appFlowNo, int seqNo);
    /**
     * 결재선조회
     * @author  javaworker
     * @version $Id: AppDocReqDAO.java,v 1.10 2014/07/02 04:13:55 pochul2423 Exp $
     * @since   1.0
     * 
     * @param appDocReqDTO
     * @return
     */
    public List findFlowUserList(AppDocReqDTO appDocReqDTO);
    
    /**
     * 결재문서 상태 변경
     * @author  javaworker
     * @version $Id: AppDocReqDAO.java,v 1.10 2014/07/02 04:13:55 pochul2423 Exp $
     * @since   1.0
     * 
     * @param appPrcDetailDTO
     * @param  
     */
    public void setAppDocStatus(AppDocReqDTO appDocReqDTO);
    /**
     * 통보이외의 승인구분이 있는지 조회
     * @author  javaworker
     * @version $Id: AppDocReqDAO.java,v 1.10 2014/07/02 04:13:55 pochul2423 Exp $
     * @since   1.0
     * 
     * @param appDocReqDTO
     * @return
     */
    public boolean checkAppFlow(AppDocReqDTO appDocReqDTO);

    /**
     * 통보대상자 모두 처리대상 처리
     * @author  javaworker
     * @version $Id: AppDocReqDAO.java,v 1.10 2014/07/02 04:13:55 pochul2423 Exp $
     * @since   1.0
     * 
     * @param appDocReqDTO
     */
    public void updateNotWfAction(AppDocReqDTO appDocReqDTO);
    
    /**
     * 결재요청 최초 입력시 Default 값을 가져온다.
     * @author  javaworker
     * @version $Id: AppDocReqDAO.java,v 1.10 2014/07/02 04:13:55 pochul2423 Exp $
     * @since   1.0
     * 
     * @param appDocReqDTO
     * @return
     */
    public String[] findDefaultInform(AppDocReqDTO appDocReqDTO);

    /**
     * 현재 문서종류[wf_type]의 Object No 가 결재진행중인지 체크한다.
     * @author  javaworker
     * @version $Id: AppDocReqDAO.java,v 1.10 2014/07/02 04:13:55 pochul2423 Exp $
     * @since   1.0
     * 
     * @param appDocReqDTO
     * @return
     */
    public boolean checkAppDocObj(AppDocReqDTO appDocReqDTO);
    
}