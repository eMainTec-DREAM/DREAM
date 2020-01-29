package dream.pers.appstb.prc.dao;

import dream.pers.appreq.dto.AppReqCommonDTO;
import dream.pers.appstb.prc.dto.AppPrcDetailDTO;

/**
 * 결재요청(결재문서작성)
 * @author javaworker
 * @version $Id: AppPrcDetailDAO.java,v 1.1 2013/08/30 09:12:11 javaworker Exp $
 * @since 1.0
 */
public interface AppPrcDetailDAO
{
   /* *//**
     * 결재선조회
     * @author  javaworker
     * @version $Id: AppPrcDetailDAO.java,v 1.1 2013/08/30 09:12:11 javaworker Exp $
     * @since   1.0
     * 
     * @param appPrcDetailDTO
     * @return
     *//*
    public List findFlowUserList(AppPrcDetailDTO appPrcDetailDTO)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                ");
        query.append("       x.seq_no,                                      ");
        query.append("       SF2CODETODESC(x.wf_status, 'WF_STATUS'),       ");
        query.append("       x.app_date||x.app_time,                        ");
        query.append("       DECODE(x.seq_no, 0,                            ");
        query.append("          SF2CODETODESC(x.wf_status, 'WF_STATUS'),    "); // 0 인경우 요청(기안)으로 표시
        query.append("          SF2CODETODESC(x.app_type, 'APP_TYPE')       ");
        query.append("       ),                                             ");
        query.append("       x.user_id                                      ");
        query.append("       ||' / '||                                      ");
        query.append("       SF2CODETODESC(x.user_id, 'USER')               ");
        query.append("       ||' / '||                                      ");
        query.append("      (SELECT SF2CODETODESC(a.dept_no, 'DEPT')        ");
        query.append("       FROM   T4USERS a                               ");
        query.append("       WHERE  a.user_id = x.user_id) flowUserInfo,    ");
        query.append("       x.app_desc,                                    ");
        query.append("       x.user_id,                                     ");
        query.append("       x.app_flow_no                                  ");
        query.append("FROM   T2APP_FLOW x                                   ");
        query.append("WHERE  x.app_doc_no = ?                               ");
        query.append("  AND  x.seq_no <> '999'                              "); // 자동통보된 건은 보여주지 않는다.
        query.append("ORDER BY x.seq_no, x.user_id                          ");

        Object[] objects = new Object[] {
                appPrcDetailDTO.getAppDocNo()
        };

        return getJdbcTemplate().queryForList(query.toString(), objects);
    }*/

    /**
     * 결재문서 상세조회
     * @author  javaworker
     * @version $Id: AppPrcDetailDAO.java,v 1.1 2013/08/30 09:12:11 javaworker Exp $
     * @since   1.0
     * 
     * @param appReqCommonDTO
     * @return
     */
    public AppPrcDetailDTO findDetail(AppReqCommonDTO appReqCommonDTO);

/*    *//**
     * 결재처리 내역의 처리구분 입력
     * @author  javaworker
     * @version $Id: AppPrcDetailDAO.java,v 1.1 2013/08/30 09:12:11 javaworker Exp $
     * @since   1.0
     * 
     * @param appPrcDetailDTO
     *//*
    public void setAppFlow(AppPrcDetailDTO appPrcDetailDTO)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("UPDATE T2APP_FLOW x                                   ");
        query.append("SET    x.wf_action = ?,                               ");
        query.append("       x.wf_status = ?,                               ");
        query.append("       x.autho_by  = ?,                               ");
        query.append("       x.app_desc  = ?,                               ");
        query.append("       x.app_date  = TO_CHAR(SYSDATE, 'YYYYMMDD'),    ");
        query.append("       x.app_time  = TO_CHAR(SYSDATE, 'HH24MI')       ");
        query.append("WHERE  x.app_flow_no = ?                               ");

        Object[] objects = new Object[]{
                appPrcDetailDTO.getWfAction(),
                appPrcDetailDTO.getWfStatus(),
                appPrcDetailDTO.getEnterBy(),
                appPrcDetailDTO.getAppDesc(),
                appPrcDetailDTO.getAppFlowNo()
        };

        this.getJdbcTemplate().update(query.toString(), objects);
    }

    *//**
     * 현재 seqNo가 모두 결채처리(C)되었는지 확인처리
     * @author  javaworker
     * @version $Id: AppPrcDetailDAO.java,v 1.1 2013/08/30 09:12:11 javaworker Exp $
     * @since   1.0
     * 
     * @param appDocNo
     * @param seqNo
     * @return true[결재처리(C)가 되지 않은 건이 있는 경우]
     *//*
    public boolean checkAppSeqNo(String appDocNo, String seqNo)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT 1                                  ");
        query.append("FROM   T2APP_FLOW x                       ");
        query.append("WHERE  x.app_doc_no = ?                   ");
        query.append("  AND  x.seq_no     = ?                   ");
        query.append("  AND  x.wf_action <> 'C'                 ");

        Object[] objects = new Object[]{
                appDocNo,
                seqNo
        };
        
        List resultList = getJdbcTemplate().queryForList(query.toString(), objects);
        return QueryBuffer.haveData(resultList);
    }

    *//**
     * 결재내역의 순번을 조회한다.
     * @author  javaworker
     * @version $Id: AppPrcDetailDAO.java,v 1.1 2013/08/30 09:12:11 javaworker Exp $
     * @since   1.0
     * 
     * @param appPrcDetailDTO
     * @return
     *//*
    public String getAppSeqNo(AppPrcDetailDTO appPrcDetailDTO)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT seq_no                         ");
        query.append("FROM   T2APP_FLOW                     ");
        query.append("WHERE  app_flow_no = ?                ");

        Object[] objects = new Object[]{
                appPrcDetailDTO.getAppFlowNo() 
        };
        
        List resultList = getJdbcTemplate().queryForList(query.toString(), objects);
        return QueryBuffer.listToString(resultList);
    }

    *//**
     * 다음 결재자의 순번 및 승인구분을 조회한다.
     * @author  javaworker
     * @version $Id: AppPrcDetailDAO.java,v 1.1 2013/08/30 09:12:11 javaworker Exp $
     * @since   1.0
     * 
     * @param appPrcDetailDTO
     * @param seqNo
     * @return
     *//*
    public String[] getNextAppInfo(AppPrcDetailDTO appPrcDetailDTO, String seqNo)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT x.seq_no, x.app_type               ");
        query.append("FROM   T2APP_FLOW x                       ");
        query.append("WHERE  x.app_doc_no = ?                   ");
        query.append("  AND  x.seq_no = (SELECT MIN(a.seq_no)       ");
        query.append("                   FROM   T2APP_FLOW a        ");
        query.append("                   WHERE  a.app_doc_no = ?    ");
        query.append("                     AND  a.seq_no > ?)       ");

        Object[] objects = new Object[]{
                appPrcDetailDTO.getAppDocNo(),
                appPrcDetailDTO.getAppDocNo(),
                seqNo
        };
        
        List resultList = getJdbcTemplate().queryForList(query.toString(), objects);
        return QueryBuffer.singleRowToStringArray(resultList);
    }

    *//**
     * 결재문서 상태 변경
     * @author  javaworker
     * @version $Id: AppPrcDetailDAO.java,v 1.1 2013/08/30 09:12:11 javaworker Exp $
     * @since   1.0
     * 
     * @param appPrcDetailDTO
     * @param  
     *//*
    public void setAppDocStatus(AppPrcDetailDTO appPrcDetailDTO)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("UPDATE T2APP_DOC x                                    ");
        query.append("SET    x.app_status  = ?,                             ");
        query.append("       x.update_by   = ?,                             ");
        query.append("       x.update_date = TO_CHAR(SYSDATE, 'YYYYMMDD')   ");
        query.append("WHERE  x.app_doc_no  = ?                              ");
        
        // 결재중으로 변경할때는 상태가 결재요청인 경우만 처리(합의인경우 동시에 누른경우 오동작 방지)
        if ("APP02".equals(appPrcDetailDTO.getAppStatus()))
        {
            // APP02 라도 다시 업데이트 한다.(최종 수정일자 셋팅을 위해서)
            query.append("  AND  x.app_status IN ('APP01', 'APP02')             ");
        }

        Object[] objects = new Object[]{
                appPrcDetailDTO.getAppStatus(),
                appPrcDetailDTO.getEnterBy(),
                appPrcDetailDTO.getAppDocNo()
        };
        
        this.getJdbcTemplate().update(query.toString(), objects);
    }

    *//**
     * 결재문서 마스터문서번호 조회
     * @author  javaworker
     * @version $Id: AppPrcDetailDAO.java,v 1.1 2013/08/30 09:12:11 javaworker Exp $
     * @since   1.0
     * 
     * @param appPrcDetailDTO
     * @return
     *//*
    public String[] findObjectNo(AppPrcDetailDTO appPrcDetailDTO)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT object_no                      ");
        query.append("FROM   T2APP_DOC_DTL                  ");
        query.append("WHERE  app_doc_no = ?                 ");

        Object[] objects = new Object[]{
                appPrcDetailDTO.getAppDocNo()
        };
        
        List resultList = getJdbcTemplate().queryForList(query.toString(), objects);
        return QueryBuffer.toStringSingleArray(resultList);
    }

    *//**
     * 결재대상 표시
     * @author  javaworker
     * @version $Id: AppPrcDetailDAO.java,v 1.1 2013/08/30 09:12:11 javaworker Exp $
     * @since   1.0
     * 
     * @param appDocNo
     * @param seqNo
     * @param string
     *//*
    public void setAppFlowTarget(String appDocNo, String seqNo, String wfAction)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("UPDATE T2APP_FLOW x                                   ");
        query.append("SET    x.wf_action  = ?                               ");
        query.append("WHERE  x.app_doc_no = ?                               ");
        query.append("  AND  x.seq_no     = ?                               ");

        Object[] objects = new Object[]{
                wfAction,
                appDocNo,
                seqNo
        };

        this.getJdbcTemplate().update(query.toString(), objects);
    }
    
    *//**
     * 최초 기안자에게 승인/반려 통보
     * @author  javaworker
     * @version $Id: AppPrcDetailDAO.java,v 1.1 2013/08/30 09:12:11 javaworker Exp $
     * @since   1.0
     * 
     * @param appPrcDTO
     * @return
     *//*
    public int insertNotApp(AppPrcDetailDTO appPrcDetailDTO)
    {
        QueryBuffer query = new QueryBuffer();

        
         * 기안자에게 반려 통보 처리
         
        query.append("INSERT INTO T2APP_FLOW (                          ");
        query.append("    app_flow_no,                                  ");
        query.append("    enter_by,    enter_date,                      ");
        query.append("    app_doc_no,  seq_no,                          ");
        query.append("    user_id,     app_type,                        ");
        query.append("    wf_action,   wf_status                        ");
        query.append(")                                                 ");
        query.append("SELECT                                            ");
        query.append("       SQ2APP_FLOW_NO.NextVal,                        ");
        query.append("       ?,             TO_CHAR(SYSDATE, 'YYYYMMDD'),   ");
        query.append("       x.app_doc_no,  '999',                          "); // seqNo 999 로 통보
        query.append("       x.user_id,     'NT',                           ");
        query.append("       'P',           'Z'                             ");
        query.append("FROM   T2APP_FLOW x                               ");
        query.append("WHERE  x.app_doc_no = ?                           ");
        query.append("  AND  x.seq_no = 0                               ");

        Object[] objects = new Object[] {
                appPrcDetailDTO.getEnterBy(),
                appPrcDetailDTO.getAppDocNo(),
        };
            
        return this.getJdbcTemplate().update(query.toString(), objects);   
    }

    *//**
     * 결재처리 seqNo의 상태 대기로함
     * @author  javaworker
     * @version $Id: AppPrcDetailDAO.java,v 1.1 2013/08/30 09:12:11 javaworker Exp $
     * @since   1.0
     * 
     * @param appPrcDetailDTO
     *//*
    public void setDappWfStatus(AppPrcDetailDTO appPrcDetailDTO)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("UPDATE T2APP_FLOW x                           ");
        query.append("SET    x.wf_action = 'Z'                      ");
        query.append("WHERE  x.app_doc_no = ?                       ");
        
        // 합의시 같은 순번의 결재대상으로 되어있는 결재자를 모두 대기로함(이미 결재한 사람 제외)
        query.append("  AND  x.wf_action = 'P'                      ");
        query.append("  AND  x.seq_no = (SELECT a.seq_no            ");
        query.append("                   FROM T2APP_FLOW a          ");
        query.append("                   WHERE a.app_flow_no = ?)   ");

        Object[] objects = new Object[] {
                appPrcDetailDTO.getAppDocNo(),
                appPrcDetailDTO.getAppFlowNo()
        };
            
        this.getJdbcTemplate().update(query.toString(), objects);  
    }*/

    public void updateDetail(AppPrcDetailDTO appPrcDetailDTO);

    public void insertDetail(AppPrcDetailDTO appPrcDetailDTO);

	public String checkSeqNum(AppReqCommonDTO appReqCommonDTO, AppPrcDetailDTO appPrcDetailDTO);
	public String nextAppSeq(AppReqCommonDTO appReqCommonDTO, AppPrcDetailDTO appPrcDetailDTO);


}