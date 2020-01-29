package dream.pers.appreq.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.pers.appreq.dao.AppReqDetailDAO;
import dream.pers.appreq.dto.AppReqCommonDTO;
import dream.pers.appreq.dto.AppReqDetailDTO;

/**
 * �����û(��û�����ۼ�)
 * @author javaworker
 * @version $Id: AppReqDetailDAO.java,v 1.2 2014/03/07 05:35:54 javaworker Exp $
 * @since 1.0
 * @spring.bean id="appReqDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AppReqDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements AppReqDetailDAO
{
    /**
     * ���缱��ȸ
     * @author  javaworker
     * @version $Id: AppReqDetailDAO.java,v 1.2 2014/03/07 05:35:54 javaworker Exp $
     * @since   1.0
     * 
     * @param appReqDetailDTO
     * @return
     */
    public List findFlowUserList(AppReqDetailDTO appReqDetailDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                ");
        query.append("       x.seq_no,                                      ");
        query.append("       SF2CODETODESC(x.wf_status, 'WF_STATUS'),       ");
        query.append("       x.app_date+x.app_time,                        ");
        query.append("       CASE x.seq_no WHEN 0                           ");
        query.append("       	THEN dbo.SF2CODETODESC(x.wf_status, 'WF_STATUS')	"); // 0 �ΰ�� ��û(���)���� ǥ��
        query.append("          ELSE dbo.SF2CODETODESC(x.app_type, 'APP_TYPE')		");
        query.append("       END,                                           ");
        query.append("       x.user_id                                      ");
        query.append("       +' / '+                                      	");
        query.append("       dbo.SF2CODETODESC(x.user_id, 'USER')           ");
        query.append("       +' / '+                                      	");
        query.append("      (SELECT dbo.SF2CODETODESC(a.dept_no, 'DEPT')    ");
        query.append("       FROM   T4USERS a                               ");
        query.append("       WHERE  a.user_id = x.user_id) flowUserInfo,    ");
        query.append("       x.app_desc,                                    ");
        query.append("       x.user_id,                                     ");
        query.append("       x.app_flow_no                                  ");
        query.append("FROM   T2APP_FLOW x                                   ");
        query.append("WHERE  x.app_doc_no = ?                               ");
        query.append("  AND  x.seq_no <> '999'                              "); // �ڵ��뺸�� ���� �������� �ʴ´�.
        query.append("ORDER BY x.seq_no, x.user_id                          ");

        Object[] objects = new Object[] {
                appReqDetailDTO.getAppDocNo()
        };

        return getJdbcTemplate().queryForList(query.toString(), objects);
    }

    /**
     * ��û���� ����ȸ
     * @author  javaworker
     * @version $Id: AppReqDetailDAO.java,v 1.2 2014/03/07 05:35:54 javaworker Exp $
     * @since   1.0
     * 
     * @param appReqCommonDTO
     * @return
     */
    public AppReqDetailDTO findMstrDetail(AppReqCommonDTO appReqCommonDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                               ");
        query.append("       x.comp_no           compNo,              ");
        query.append("       x.apprlist_id       apprlistId,      ");
        query.append("       x.appr_type         apprType,         ");
        query.append("       dbo.SFACODE_TO_DESC(x.appr_type,'APPR_TYPE','SYS','','"+loginUser.getLangId()+"')  apprTypeDesc,     ");
        query.append("       x.object_id         objectId,      ");
        query.append("       x.appr_status       apprStatus,      ");
        query.append("       dbo.SFACODE_TO_DESC(x.appr_status,'APPR_STATUS','SYS','','"+loginUser.getLangId()+"')  apprStatusDesc,       ");
        query.append("       req_by              reqBy,      ");
        query.append("       (SELECT a.emp_name           ");
        query.append("        FROM   TAEMP a      ");
        query.append("        WHERE  a.comp_no = x.comp_no and a.emp_id = x.req_by) reqByName,       ");
        query.append("       req_date            reqDate,      ");
        query.append("       title,     ");
        query.append("       REMARK                 ");
        query.append("FROM   TAAPPRLIST x                               ");
        query.append("WHERE  1 = 1      ");
        query.getAndQuery("x.object_id", appReqCommonDTO.getObjectId());
        query.getAndQuery("x.appr_type", appReqCommonDTO.getApprType());
        query.getAndQuery("x.comp_no", loginUser.getCompNo());


        return (AppReqDetailDTO)getJdbcTemplate().query(query.toString(), 
                new MwareExtractor(new AppReqDetailDTO()));
    }

    /**
     * �������� ��� ��� ���·� ��
     * @author  javaworker
     * @version $Id: AppReqDetailDAO.java,v 1.2 2014/03/07 05:35:54 javaworker Exp $
     * @since   1.0
     * 
     * @param appReqDetailDTO
     */
    public void updateDetail(AppReqDetailDTO appReqDetailDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("UPDATE TAAPPRLIST                               ");
        query.append("SET    appr_status  = ?,                        ");
        query.append("       title        = ?,                        ");
        query.append("       remark       = ?                         ");
        query.append("WHERE  apprlist_id  = ?                         ");
        query.append("  AND  comp_no	  = ?                         ");

        Object[] objects = new Object[] {
                appReqDetailDTO.getApprStatus(),
                appReqDetailDTO.getTitle(),
                appReqDetailDTO.getRemark(),
                appReqDetailDTO.getApprlistId()
                ,user.getCompNo()
        };
            
        this.getJdbcTemplate().update(query.toString(), getObject(objects));  
    }

    /**
     * ��� ó���� �Է�
     * @author  javaworker
     * @version $Id: AppReqDetailDAO.java,v 1.2 2014/03/07 05:35:54 javaworker Exp $
     * @since   1.0
     * 
     * @param appReqDetailDTO
     */
    public void insertDetail(AppReqDetailDTO appReqDetailDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        
//        query.append("MERGE INTO TAAPPRLIST a																			");
//    	query.append("USING(	SELECT 	? compNo,																		");
//    	query.append("					? apprListId,																	");
//    	query.append("					? apprType,																		");
//    	query.append("					? objectId,																		");
//    	query.append("					? apprStatus,																	");
//    	query.append("					? reqBy,																		");
//    	query.append("					? title,																		");
//    	query.append("					? remark																		");
//    	query.append("			FROM DUAL) b																			");
//    	query.append("ON(	a.comp_no 		= b.compNo																	");
//    	query.append("	AND a.apprlist_id 	= b.apprListId	)															");
//    	query.append("WHEN MATCHED THEN																					");
//    	query.append("	UPDATE SET 	a.appr_type = b.apprType,															");
//    	query.append("				a.object_id = b.objectId,															");
//    	query.append("				a.appr_status = b.apprStatus,														");
//    	query.append("				a.req_by = b.reqBy,																	");
//    	query.append("				a.title = b.title,																	");
//    	query.append("				a.remark = b.remark,																");
//    	query.append("				a.req_date = TO_CHAR(SYSDATE, 'YYYYMMDD')											");
//    	query.append("WHEN NOT MATCHED THEN																				");
//    	query.append("	INSERT (a.comp_no,	a.apprlist_id,	a.appr_type,	a.object_id,	a.appr_status,				");
//    	query.append("			a.req_by,	a.title,		a.remark,		a.req_date						)			");
//    	query.append("	VALUES (b.compNo,	b.apprListId,	b.apprType,		b.objectId,		b.apprStatus,				");
//    	query.append("			b.reqBy,	b.title,		b.remark,		TO_CHAR(SYSDATE, 'YYYYMMDD')	)			");

    	query.append("DECLARE @t1 TABLE( 																		");
    	query.append("	comp_no NVARCHAR(1000), 																");
    	query.append("	apprlist_id NVARCHAR(1000), 															");
    	query.append("	appr_type NVARCHAR(1000), 																");
    	query.append("	object_id NVARCHAR(1000), 																");
    	query.append("	appr_status NVARCHAR(1000), 															");
    	query.append("	req_by NVARCHAR(1000), 																	");
    	query.append("	title NVARCHAR(1000), 																	");
    	query.append("	remark NVARCHAR(1000), 																	");
    	query.append("	req_date NVARCHAR(1000) 																");
    	query.append(") 																						");
    	query.append("INSERT INTO @t1 VALUES (?,?,?,?,															");
    	query.append("					?, ?, ?, ?,CONVERT(VARCHAR, getdate(), 112)	)							");
    	query.append("IF EXISTS( 																				");
    	query.append("	SELECT 1 																				");
    	query.append("	FROM TAAPPRLIST A, @t1 b 																");
    	query.append("	WHERE A.comp_no = b.comp_no 															");
    	query.append("	AND A.apprlist_id = b.apprlist_id 														");
    	query.append(") 																						");
    	query.append("BEGIN  																					");
    	query.append("	UPDATE TAAPPRLIST SET																	");
    	query.append("		appr_type = b.appr_type																");
    	query.append("		,object_id = b.object_id															");
    	query.append("		,appr_status = b.appr_status														");
    	query.append("		,req_by = b.req_by																	");
    	query.append("		,title = b.title																	");
    	query.append("		,remark = b.remark																	");
    	query.append("		,req_date = b.req_date																");
    	query.append("	FROM TAAPPRLIST A JOIN @t1 b															");
    	query.append("	ON A.comp_no = b.comp_no 																");
    	query.append("	AND A.apprlist_id = b.apprlist_id 														");
    	query.append(" END 																						");
    	query.append("ELSE 																						");
    	query.append("BEGIN 																					");
    	query.append("  INSERT INTO TAAPPRLIST 																	");
    	query.append("  (comp_no,	apprlist_id,	appr_type,	object_id,	appr_status							");
    	query.append("  ,req_by,	title,	remark,	req_date	)												");
    	query.append("SELECT 																					");
    	query.append("	b.comp_no,	b.apprlist_id,	b.appr_type,	b.object_id,	b.appr_status				");
    	query.append("  ,b.req_by,	b.title,	b.remark,	b.req_date											");
    	query.append("FROM 	@t1 b 																				");
    	query.append("	END 																					");
    	
    	
        Object[] objects = new Object[] {
        		user.getCompNo(),
                appReqDetailDTO.getApprlistId(),
                appReqDetailDTO.getApprType(),
                appReqDetailDTO.getObjectId(),
                appReqDetailDTO.getApprStatus(),
                appReqDetailDTO.getReqBy(),
                appReqDetailDTO.getTitle(),
                appReqDetailDTO.getRemark()
        };
            
        this.getJdbcTemplate().update(query.toString(), getObject(objects));  
    }

    public void actionReq(AppReqDetailDTO appReqDetailDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("UPDATE TAAPPRLIST                               ");
        query.append("SET    appr_status  = ?                         ");
        query.append("WHERE  apprlist_id  = ?                         ");
        query.append("  AND  comp_no  = ?                         	");

        Object[] objects = new Object[] {
                appReqDetailDTO.getApprStatus(), //�����û
                appReqDetailDTO.getApprlistId()
                ,user.getCompNo()
                
        };

        this.getJdbcTemplate().update(query.toString(), getObject(objects)); 
        
      //��û�ÿ��� �׻� ����ڸ� �⺻���� ������ �ش�.(APPRUSR_STATUS:A ������� ���)
        query = new QuerySqlBuffer();
        query.append("INSERT INTO TAAPPRUSR                                 ");
        query.append("  (comp_no    ,apprusr_id   ,apprlist_id              ");
        query.append("   ,appr_seq  ,appr_by      ,apprusr_action           ");
        query.append("   ,apprusr_status  ,appr_date, appr_time             ");
        query.append("   ,remark, apprusr_type                              ");
        query.append("  )   VALUES (                                        ");
        query.append("    ?    ,NEXT VALUE FOR sqaapprusr_id         ,?     ");
        query.append("   ,0    ,?                             ,'C'          ");
        query.append("   ,'A'  ,CONVERT(VARCHAR, getdate(), 112)   ,REPLACE(CONVERT(VARCHAR, GETDATE(), 108),':','')           ");
        query.append("   ,?,   'DF'                                           ");
        query.append("  )                                                     ");
        
        objects = new Object[] {
        		user.getCompNo()
        		,appReqDetailDTO.getApprlistId()
        		,user.getEmpId()
        		,appReqDetailDTO.getRemark()
        };
        
        this.getJdbcTemplate().update(query.toString(), getObject(objects));
        
        
        
    }
    
    public void initApprUsrStatus(AppReqDetailDTO appReqDetailDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("UPDATE TAAPPRUSR  set             ");
        query.append("    apprusr_action = ?            ");
        query.append("   ,apprusr_status = ?            ");
        query.append("WHERE apprlist_id = ?             ");
        query.append("    and  comp_no  = ?             ");
        query.append("    and  apprusr_action  != 'C'    "); //[P:ó����� C:ó���Ϸ� Z:���]

        
        Object[] objects = new Object[] {
        		"Z"
        		,"Z"
        		,appReqDetailDTO.getApprlistId()
        		,user.getCompNo()
        };

        this.getJdbcTemplate().update(query.toString(), getObject(objects));  
    }
    
    public void setApprUsrStatus(AppReqDetailDTO appReqDetailDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("UPDATE TAAPPRUSR                  ");
        query.append("SET apprusr_action = ?            ");
        query.append("WHERE appr_seq <=   (SELECT       ");
        query.append("                           ISNULL(min(appr_seq),1)        ");
        query.append("                    FROM   TAAPPRUSR                   ");
        query.append("                    WHERE  1=1                         ");
        query.append("                      AND  apprlist_id    = ?          ");
        query.append("                      AND  comp_no        = ?          ");
        query.append("                      and  apprusr_action  != 'C'      "); //[P:ó����� C:ó���Ϸ� Z:���]
        query.append("                   )                                   ");
        query.append("      AND  apprlist_id    = ?                          ");
        query.append("      AND  comp_no        = ?                          ");
        query.append("      and  apprusr_action  != 'C'                      "); //[P:ó����� C:ó���Ϸ� Z:���]

        Object[] objects = new Object[] {
        		"P" //ó�����
                ,appReqDetailDTO.getApprlistId()
                ,appReqDetailDTO.getCompNo()
                ,appReqDetailDTO.getApprlistId()
                ,user.getCompNo()
        };

        this.getJdbcTemplate().update(query.toString(), getObject(objects));  
    }
    public String checkUsr(AppReqDetailDTO appReqDetailDTO, User user){
    	
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
    	query.append("SELECT                                ");
        query.append("       COUNT(1)                       ");
        query.append("FROM TAAPPRUSR x						");
        query.append("WHERE 1=1                             ");
        query.append("   and x.apprusr_action  != 'C'       "); //[P:ó����� C:ó���Ϸ� Z:���]
        query.append("   and x.apprusr_type     = 'AP'      "); //AP:�����ڴ� �Ѹ��̻� �־�� ��.
        query.append("   and x.apprlist_id = ?              ");
        query.append("   and x.comp_no = ?                  ");
        
        Object[] objects = new Object[] {
                 appReqDetailDTO.getApprlistId()
                ,user.getCompNo()
        };
        
        List resultList = getJdbcTemplate().queryForList(query.toString(), objects);

        return QuerySqlBuffer.listToString(resultList);
    }

}