package dream.app.doc.dao.oraImpl;

import java.util.List;

import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;

import dream.app.doc.dao.AppDocReqDAO;
import dream.app.doc.dto.AppDocReqDTO;
//import dream.app.line.dto.FlowDtlDTO;

/**
 * �����û(���繮���ۼ�)
 * @author javaworker
 * @version $Id: AppDocReqDAO.java,v 1.10 2014/07/02 04:13:55 pochul2423 Exp $
 * @since 1.0
 * @spring.bean id="appDocReqDAOTarget"
 * @spring.txbn id="appDocReqDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AppDocReqDAOOraImpl extends BaseJdbcDaoSupportOra implements AppDocReqDAO
{
    public void insertAppDocReq(AppDocReqDTO appDocReqDTO)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("INSERT INTO T2APP_DOC (               ");
        query.append("    enter_by,   enter_date,           ");
        query.append("    app_doc_no, enter_time,           ");
        query.append("    app_status,                       ");
        query.append("    remark,     title,                ");
        query.append("    wf_type                           ");
        query.append(")                                     ");
        query.append("VALUES (                                  ");
        query.append("    ?,   TO_CHAR(SYSDATE, 'YYYYMMDD'),    ");
        query.append("    ?,   TO_CHAR(SYSDATE, 'HH24MI'),      ");
        query.append("    ?,                                    ");
        query.append("    ?,   ?,                           ");
        query.append("    ?                                 ");
        query.append(")                                     ");
        
        Object[] objects = new Object[] {
                appDocReqDTO.getEnterBy(),
                appDocReqDTO.getAppDocNo(),
                appDocReqDTO.getAppStatus(),
                appDocReqDTO.getRemark(),
                appDocReqDTO.getTitle(),
                appDocReqDTO.getWfType()
        };
        
        this.getJdbcTemplate().update(query.toString(), objects);
    }
    
    public void insertRootFlow(AppDocReqDTO appDocReqDTO) 
    {
        QueryBuffer query = new QueryBuffer();

        query.append("INSERT INTO T2APP_FLOW (                  ");
        query.append("    app_flow_no,                          ");
        query.append("    enter_by,    enter_date,              ");
        query.append("    app_doc_no,  seq_no,                  ");
        query.append("    user_id,     autho_by,                ");
        query.append("    wf_action,   wf_status,               ");
        query.append("    app_date,                             ");
        query.append("    app_time                              ");
        query.append(")                                         ");
        query.append("VALUES (                                  ");
        query.append("    SQ2APP_FLOW_NO.NextVal,               ");
        query.append("    ?,    TO_CHAR(SYSDATE, 'YYYYMMDD'),   ");
        query.append("    ?,    0,                              ");
        query.append("    ?,    ?,                              ");
        query.append("    'C',  'A',                            ");
        query.append("    TO_CHAR(SYSDATE, 'YYYYMMDD'),         ");
        query.append("    TO_CHAR(SYSDATE, 'HH24MI')            ");
        query.append(")                                         ");

        Object[] objects = new Object[] {
                appDocReqDTO.getEnterBy(),
                appDocReqDTO.getAppDocNo(),
                appDocReqDTO.getEnterBy(),
                appDocReqDTO.getEnterBy()
        };
            
        this.getJdbcTemplate().update(query.toString(), objects);   
    }
    
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
    public void insertAppDocList(AppDocReqDTO appDocReqDTO)
    {
        String objectNo = appDocReqDTO.getObjectNo();
        if (objectNo == null || "".equals(objectNo)) return;
        
        QueryBuffer query = new QueryBuffer();
        
        query.append("INSERT INTO T2APP_DOC_DTL (       ");
        query.append("    app_doc_no, object_no         ");
        query.append(")                                 ");
        query.append("VALUES (                          ");
        query.append("    ?, ?                          ");
        query.append(")                                 ");

        String[] objectNoArray = objectNo.split(",");
        for (int i=0; i<objectNoArray.length; i++)
        {
            Object[] objects = new Object[] {
                    appDocReqDTO.getAppDocNo(),
                    objectNoArray[i]
            };
            
            this.getJdbcTemplate().update(query.toString(), objects);
        }
    }

    /**
     * �Էµ� ���缱�� ��ȸ�Ѵ�.
     * @author  javaworker
     * @version $Id: AppDocReqDAO.java,v 1.10 2014/07/02 04:13:55 pochul2423 Exp $
     * @since   1.0
     * 
     * @param appDocReqDTO
     * @return
     */
    public String[][] getAppFlowList(AppDocReqDTO appDocReqDTO)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                    ");
        query.append("       app_flow_no, app_type              ");
        query.append("FROM   T2APP_FLOW                         ");
        query.append("WHERE  app_doc_no = ?                     ");
        query.append("  AND  seq_no <> 0                        ");
        query.append("ORDER BY seq_no                           ");

        Object[] objects = new Object[] {
                appDocReqDTO.getAppDocNo()
        };
        
        List resultList = this.getJdbcTemplate().queryForList(query.toString(), objects);
        return QueryBuffer.toStringArray(resultList);
    }

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
    public void updateWfAction(String wfAction, String wfStatus, String appFlowNo, int seqNo)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("UPDATE T2APP_FLOW                 ");
        query.append("SET    wf_action   = ?,           ");
        query.append("       wf_status   = ?,           ");
        query.append("       seq_no      = ?            ");
        query.append("WHERE  app_flow_no = ?            ");

        Object[] objects = new Object[] {
                wfAction,
                wfStatus,
                seqNo,
                appFlowNo
        };
            
        this.getJdbcTemplate().update(query.toString(), objects);
    }

    /**
     * ���缱��ȸ
     * @author  javaworker
     * @version $Id: AppDocReqDAO.java,v 1.10 2014/07/02 04:13:55 pochul2423 Exp $
     * @since   1.0
     * 
     * @param appDocReqDTO
     * @return
     */
    public List findFlowUserList(AppDocReqDTO appDocReqDTO)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                ");
        query.append("       x.seq_no,                                      ");
        query.append("       x.app_type,                                    ");
        query.append("       x.user_id                                      ");
        query.append("       ||' / '||                                      ");
        query.append("       SF2CODETODESC(x.user_id, 'USER')               ");
        query.append("       ||' / '||                                      ");
        query.append("      (SELECT SF2CODETODESC(a.dept_no, 'DEPT')        ");
        query.append("       FROM   T4USERS a                               ");
        query.append("       WHERE  a.user_id = x.user_id) flowUserInfo,    ");
        query.append("       x.user_id                                      ");
        query.append("FROM   T2APP_FLOW x                                   ");
        query.append("WHERE x.app_doc_no = ?                                ");
        query.append("  AND x.seq_no <> 0                                   "); // �����û�� ���缱�� �����û�ڴ� ������ �ʰ��Ѵ�.
        query.append("ORDER BY x.seq_no, x.user_id                          ");

        Object[] objects = new Object[] {
                appDocReqDTO.getAppDocNo()
        };

        return getJdbcTemplate().queryForList(query.toString(), objects);
    }
    
    /**
     * ���繮�� ���� ����
     * @author  javaworker
     * @version $Id: AppDocReqDAO.java,v 1.10 2014/07/02 04:13:55 pochul2423 Exp $
     * @since   1.0
     * 
     * @param appPrcDetailDTO
     * @param  
     */
    public void setAppDocStatus(AppDocReqDTO appDocReqDTO)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("UPDATE T2APP_DOC x                                    ");
        query.append("SET    x.app_status  = ?,                             ");
        query.append("       x.update_by   = ?,                             ");
        query.append("       x.update_date = TO_CHAR(SYSDATE, 'YYYYMMDD')   ");
        query.append("WHERE  x.app_doc_no  = ?                              ");

        Object[] objects = new Object[] {
                appDocReqDTO.getAppStatus(),
                appDocReqDTO.getEnterBy(),
                appDocReqDTO.getAppDocNo()
        };
        
        this.getJdbcTemplate().update(query.toString(), objects);
    }

    /**
     * �뺸�̿��� ���α����� �ִ��� ��ȸ
     * @author  javaworker
     * @version $Id: AppDocReqDAO.java,v 1.10 2014/07/02 04:13:55 pochul2423 Exp $
     * @since   1.0
     * 
     * @param appDocReqDTO
     * @return
     */
    public boolean checkAppFlow(AppDocReqDTO appDocReqDTO)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT 1                          ");
        query.append("FROM   T2APP_FLOW                 ");
        query.append("WHERE  app_type <> 'NT'           "); // �뺸�̿��� ���α���
        query.append("  AND  seq_no <> 0                "); // ��û ����
        query.append("  AND  app_doc_no = ?             ");
        query.append("  AND  ROWNUM = 1                 ");

        Object[] objects = new Object[] {
                appDocReqDTO.getAppDocNo()
        };
        
        List resultList = this.getJdbcTemplate().queryForList(query.toString(), objects);
        return QueryBuffer.haveData(resultList);
    }

    /**
     * �뺸����� ��� ó����� ó��
     * @author  javaworker
     * @version $Id: AppDocReqDAO.java,v 1.10 2014/07/02 04:13:55 pochul2423 Exp $
     * @since   1.0
     * 
     * @param appDocReqDTO
     */
    public void updateNotWfAction(AppDocReqDTO appDocReqDTO)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("UPDATE T2APP_FLOW                ");
        query.append("SET    wf_action  = 'P',         ");
        query.append("       wf_status  = 'Z'          ");
        query.append("WHERE  app_doc_no = ?            ");
        
        Object[] objects = new Object[] {
                appDocReqDTO.getAppDocNo()
        };
        
        this.getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * �����û ���� �Է½� Default ���� �����´�.
     * @author  javaworker
     * @version $Id: AppDocReqDAO.java,v 1.10 2014/07/02 04:13:55 pochul2423 Exp $
     * @since   1.0
     * 
     * @param appDocReqDTO
     * @return
     */
    public String[] findDefaultInform(AppDocReqDTO appDocReqDTO)
    {
    	
    	String wfType = appDocReqDTO.getWfType();
    	
        QueryBuffer query = new QueryBuffer();
        
        // Į�� �� noData Į�� ���� ������, ������ else������ ? �ϳ� ó���ϱ� ���� ����
        // �������̺��� title Į���� 200byte�̹Ƿ�, Ȥ�� �𸣴� substr�� �� ����.
        
        if("WOR".equals(wfType))
        {
        	// �۾���û ������ ���
        	// ���뿡 ��û�󼼰� ���� ��
	        query.append("SELECT                                   	            	");
	        query.append("       substrb(description, 1, 198) appTitle,      ");
	        query.append("       req_desc  appRemark,                          ");
	        query.append("       ''  noData                                            ");
	        query.append("FROM   T2WO_REQ                                     ");
	        query.append("WHERE  req_no = ?                                      ");
        }
        else if("WOP".equals(wfType) || "WOC".equals(wfType))
        {
        	// �۾����質 �۾����� ������ ���
        	// ���뿡 �۾������� ���� ��
	        query.append("SELECT                                   	            	");
	        query.append("       substrb(description, 1, 198) appTitle,      ");
	        query.append("       work_desc  appRemark,                        ");
	        query.append("       ''  noData                                            ");
	        query.append("FROM   T2WORK_ORDER                             ");
	        query.append("WHERE  wo_no = ?                                      ");
        }
        else if("BMAI".equals(wfType))
        {
        	// ��������� ��Ͽ�û
        	// ���뿡 �����󳻿��� ���� ��
        	query.append("SELECT                                   	           		");
	        query.append("       substrb(SUBJECT, 1, 198) appTitle,        ");
	        query.append("       REG_CMT  appRemark,                         ");
	        query.append("       ''  noData                                            ");
	        query.append("FROM   T2ABN_OPER_MNG                          ");
	        query.append("WHERE  AOM_NO = ?                                   ");
        	
        }
        else if("BMAI".equals(wfType))
        {
        	// ��������� ������û
        	// ���뿡 ��ġ������ ���� ��
        	query.append("SELECT                                   	           		");
	        query.append("       substrb(SUBJECT, 1, 198) appTitle,        ");
	        query.append("       CLEAR_CMT  appRemark,                     ");
	        query.append("       ''  noData                                            ");
	        query.append("FROM   T2ABN_OPER_MNG                          ");
	        query.append("WHERE  AOM_NO = ?                                   ");
        	
        }
        else if("BMD".equals(wfType))
        {
        	// �ְ�����ȸ�� �����û
        	// ���뿡 ȸ�ǰ���� ���� ��
        	query.append("SELECT                                   	           		");
	        query.append("       substrb(SUBJECT, 1, 198) appTitle,        ");
	        query.append("       CONTENTS  appRemark,                       ");
	        query.append("       ''  noData                                            ");
	        query.append("FROM   T2DAY_MAINT_RPT                          ");
	        query.append("WHERE  DMR_NO = ?                                   ");
	        
        }
        else if("PJTL".equals(wfType) || "PJTC".equals(wfType))
        {
        	// ���缳�質 ������� ������ ���
        	// ���뿡 �� ���� ����
        	query.append("SELECT                                   	           		");
	        query.append("       substrb(DESCRIPTION, 1, 198) appTitle,  ");
	        query.append("       ''  appRemark,                                      ");
	        query.append("       ''  noData                                            ");
	        query.append("FROM   T2PROJECT                                     ");
	        query.append("WHERE  PJT_NO = ?                                    ");
        	

        }
        else if("PUR".equals(wfType) || "SERV".equals(wfType))
        {
        	// ���ſ�û �����û
        	// ���뿡 ��� ���� ��
        	query.append("SELECT                                   	           		");
	        query.append("       substrb(DESCRIPTION, 1, 198) appTitle,  ");
	        query.append("       REMARK  appRemark,                           ");
	        query.append("       ''  noData                                            ");
	        query.append("FROM   T2PURCHASE_REQ                          ");
	        query.append("WHERE  PR_NO = ?                                     ");
	        
        }
        else if("POR".equals(wfType))
        {
        	// ���Ź��� �����û
        	// ���뿡 ��� ���� ��
        	query.append("SELECT                                   	           		");
	        query.append("       substrb(DESCRIPTION, 1, 198) appTitle,  ");
	        query.append("       REMARK  appRemark,                           ");
	        query.append("       ''  noData                                            ");
	        query.append("FROM   T2PARTS_ORDER                             ");
	        query.append("WHERE  PO_NO = ?                                     ");
        	
        }
        else if("RTN".equals(wfType))
        {
        	// ����ݳ���û �����û
        	// ���뿡 ��� ���� ��
        	query.append("SELECT                                   	           		");
	        query.append("       substrb(DESCRIPTION, 1, 198) appTitle,  ");
	        query.append("       REMARK  appRemark,                           ");
	        query.append("       ''  noData                                            ");
	        query.append("FROM   T2PRTN_REQ                                   ");
	        query.append("WHERE  PRTN_REQ_NO = ?                          ");
        	
        }
        else if("PADD".equals(wfType))
        {
        	// ��������û �����û
        	// ���뿡 ��� ���� ��
        	query.append("SELECT                                   	           		");
	        query.append("       substrb(DESCRIPTION, 1, 198) appTitle,  ");
	        query.append("       REMARK  appRemark,                           ");
	        query.append("       ''  noData                                            ");
	        query.append("FROM   T2PADD_REQ                                   ");
	        query.append("WHERE  PADD_REQ_NO = ?                          ");
        	
        }
        else if("AI".equals(wfType))
        {
        	// ����ǻ� �����û
        	// ���뿡 ��� ���� ��
        	query.append("SELECT                                   	           		");
	        query.append("       substrb(DESCRIPTION, 1, 198) appTitle,  ");
	        query.append("       REMARK  appRemark,                           ");
	        query.append("       ''  noData                                            ");
	        query.append("FROM   T2AI                                                ");
	        query.append("WHERE  AI_NO = ?                                       ");
        }
        else if("HSEREV".equals(wfType))
        {
            // Request for Revison �����û
            query.append("SELECT                                                    ");
            query.append("       SUBSTRB(y.doc_name, 1, 198) appTitle,              ");
            query.append("       ''  appRemark,                                     ");
            query.append("       ''  noData                                         ");
            query.append("FROM   T2HSE_REV x, T2HSE_DOC y                           ");
            query.append("WHERE  x.doc_no=y.doc_no                                  ");
            query.append("  AND  x.req_no = ?                                       ");
        }
        // �� wf_type�� ������ �� �ִ´�.
        else
        {
        	// Ȥ�� �� ������ ���
        	query.append("SELECT                                   	            	");
	        query.append("       '' appTitle,    									    ");
	        query.append("       ''  appRemark,                        		        ");
	        query.append("       ?  noData                                             ");
	        query.append("FROM   DUAL                                   		    ");
        }

        Object[] objects = new Object[] {
                appDocReqDTO.getObjectNo()
        };
        
        List resultList = this.getJdbcTemplate().queryForList(query.toString(), objects);
        return QueryBuffer.singleRowToStringArray(resultList);
    }

    /**
     * ���� ��������[wf_type]�� Object No �� �������������� üũ�Ѵ�.
     * @author  javaworker
     * @version $Id: AppDocReqDAO.java,v 1.10 2014/07/02 04:13:55 pochul2423 Exp $
     * @since   1.0
     * 
     * @param appDocReqDTO
     * @return
     */
    public boolean checkAppDocObj(AppDocReqDTO appDocReqDTO)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT *                                                  ");
        query.append("FROM   T2APP_DOC x, T2APP_DOC_DTL y                       ");
        query.append("WHERE  x.app_doc_no = y.app_doc_no                        ");
        query.append("  AND  wf_type = ?                                        ");
        query.append("  AND  x.app_status IN ('APP01', 'APP02')                 "); // �����û, ������
        query.append("  AND  y.object_no IN (?)                                 ");

        Object[] objects = new Object[] {
                appDocReqDTO.getWfType(),
                appDocReqDTO.getObjectNo()
        };
        
        List resultList = this.getJdbcTemplate().queryForList(query.toString(), objects);
        return QueryBuffer.haveData(resultList);
    }
    
}