package dream.app.doc.dao;

import java.util.List;

import common.util.QueryBuffer;
import dream.app.doc.dto.AppDocReqDTO;
//import dream.app.line.dto.FlowDtlDTO;

/**
 * �����û(���繮���ۼ�)
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
     * ���繮��List �Է�
     * @author  javaworker
     * @version $Id: AppDocReqDAO.java,v 1.10 2014/07/02 04:13:55 pochul2423 Exp $
     * @since   1.0
     * 
     * @param appDocReqDTO
     */
    public void insertAppDocList(AppDocReqDTO appDocReqDTO);

    /**
     * �Էµ� ���缱�� ��ȸ�Ѵ�.
     * @author  javaworker
     * @version $Id: AppDocReqDAO.java,v 1.10 2014/07/02 04:13:55 pochul2423 Exp $
     * @since   1.0
     * 
     * @param appDocReqDTO
     * @return
     */
    public String[][] getAppFlowList(AppDocReqDTO appDocReqDTO);

    /**
     * ���缱 ó�����, ó������, �ٷ��� ������ ó�����¸� �����Ѵ�.
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
     * ���缱��ȸ
     * @author  javaworker
     * @version $Id: AppDocReqDAO.java,v 1.10 2014/07/02 04:13:55 pochul2423 Exp $
     * @since   1.0
     * 
     * @param appDocReqDTO
     * @return
     */
    public List findFlowUserList(AppDocReqDTO appDocReqDTO);
    
    /**
     * ���繮�� ���� ����
     * @author  javaworker
     * @version $Id: AppDocReqDAO.java,v 1.10 2014/07/02 04:13:55 pochul2423 Exp $
     * @since   1.0
     * 
     * @param appPrcDetailDTO
     * @param  
     */
    public void setAppDocStatus(AppDocReqDTO appDocReqDTO);
    /**
     * �뺸�̿��� ���α����� �ִ��� ��ȸ
     * @author  javaworker
     * @version $Id: AppDocReqDAO.java,v 1.10 2014/07/02 04:13:55 pochul2423 Exp $
     * @since   1.0
     * 
     * @param appDocReqDTO
     * @return
     */
    public boolean checkAppFlow(AppDocReqDTO appDocReqDTO);

    /**
     * �뺸����� ��� ó����� ó��
     * @author  javaworker
     * @version $Id: AppDocReqDAO.java,v 1.10 2014/07/02 04:13:55 pochul2423 Exp $
     * @since   1.0
     * 
     * @param appDocReqDTO
     */
    public void updateNotWfAction(AppDocReqDTO appDocReqDTO);
    
    /**
     * �����û ���� �Է½� Default ���� �����´�.
     * @author  javaworker
     * @version $Id: AppDocReqDAO.java,v 1.10 2014/07/02 04:13:55 pochul2423 Exp $
     * @since   1.0
     * 
     * @param appDocReqDTO
     * @return
     */
    public String[] findDefaultInform(AppDocReqDTO appDocReqDTO);

    /**
     * ���� ��������[wf_type]�� Object No �� �������������� üũ�Ѵ�.
     * @author  javaworker
     * @version $Id: AppDocReqDAO.java,v 1.10 2014/07/02 04:13:55 pochul2423 Exp $
     * @since   1.0
     * 
     * @param appDocReqDTO
     * @return
     */
    public boolean checkAppDocObj(AppDocReqDTO appDocReqDTO);
    
}