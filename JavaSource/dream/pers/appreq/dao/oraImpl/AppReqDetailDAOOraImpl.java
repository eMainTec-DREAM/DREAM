package dream.pers.appreq.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.pers.appreq.dao.AppReqDetailDAO;
import dream.pers.appreq.dto.AppReqCommonDTO;
import dream.pers.appreq.dto.AppReqDetailDTO;

/**
 * 결재요청(요청문서작성)
 * @author javaworker
 * @version $Id: AppReqDetailDAO.java,v 1.2 2014/03/07 05:35:54 javaworker Exp $
 * @since 1.0
 * @spring.bean id="appReqDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AppReqDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements AppReqDetailDAO
{
    /**
     * 결재선조회
     * @author  javaworker
     * @version $Id: AppReqDetailDAO.java,v 1.2 2014/03/07 05:35:54 javaworker Exp $
     * @since   1.0
     * 
     * @param appReqDetailDTO
     * @return
     */
    public List findFlowUserList(AppReqDetailDTO appReqDetailDTO)
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
        query.append("  AND  x.comp_no = ?                              	"); 
        query.append("ORDER BY x.seq_no, x.user_id                          ");

        Object[] objects = new Object[] {
                appReqDetailDTO.getAppDocNo()
        };

        return getJdbcTemplate().queryForList(query.toString(), objects);
    }

    /**
     * 요청문서 상세조회
     * @author  javaworker
     * @version $Id: AppReqDetailDAO.java,v 1.2 2014/03/07 05:35:54 javaworker Exp $
     * @since   1.0
     * 
     * @param appReqCommonDTO
     * @return
     */
    public AppReqDetailDTO findMstrDetail(AppReqCommonDTO appReqCommonDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                               ");
        query.append("       x.comp_no           compNo,              ");
        query.append("       x.apprlist_id       apprlistId,      ");
        query.append("       x.appr_type         apprType,         ");
        query.append("       SFACODE_TO_DESC(x.appr_type,'APPR_TYPE','SYS','','"+loginUser.getLangId()+"')  apprTypeDesc,     ");
        query.append("       x.object_id         objectId,      ");
        query.append("       x.appr_status       apprStatus,      ");
        query.append("       SFACODE_TO_DESC(x.appr_status,'APPR_STATUS','SYS','','"+loginUser.getLangId()+"')  apprStatusDesc,       ");
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
     * 결재대상을 모두 대시 상태로 함
     * @author  javaworker
     * @version $Id: AppReqDetailDAO.java,v 1.2 2014/03/07 05:35:54 javaworker Exp $
     * @since   1.0
     * 
     * @param appReqDetailDTO
     */
    public void updateDetail(AppReqDetailDTO appReqDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("UPDATE TAAPPRLIST x                               ");
        query.append("SET    x.appr_status  = ?,                        ");
        query.append("       x.title        = ?,                        ");
        query.append("       x.remark       = ?                         ");
        query.append("WHERE  x.apprlist_id  = ?                         ");
        query.append("  AND  x.comp_no 	 	= ?                         ");

        Object[] objects = new Object[] {
                appReqDetailDTO.getApprStatus(),
                appReqDetailDTO.getTitle(),
                appReqDetailDTO.getRemark(),
                appReqDetailDTO.getApprlistId()
                ,user.getCompNo()
        };
            
        this.getJdbcTemplate().update(query.toString(), objects);  
    }

    /**
     * 취소 처리자 입력
     * @author  javaworker
     * @version $Id: AppReqDetailDAO.java,v 1.2 2014/03/07 05:35:54 javaworker Exp $
     * @since   1.0
     * 
     * @param appReqDetailDTO
     */
    public void insertDetail(AppReqDetailDTO appReqDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("MERGE INTO TAAPPRLIST a																			");
    	query.append("USING(	SELECT 	? compNo,																		");
    	query.append("					? apprListId,																	");
    	query.append("					? apprType,																		");
    	query.append("					? objectId,																		");
    	query.append("					? apprStatus,																	");
    	query.append("					? reqBy,																		");
    	query.append("					? title,																		");
    	query.append("					? remark																		");
    	query.append("			FROM DUAL) b																			");
    	query.append("ON(	a.comp_no 		= b.compNo																	");
    	query.append("	AND a.apprlist_id 	= b.apprListId	)															");
    	query.append("WHEN MATCHED THEN																					");
    	query.append("	UPDATE SET 	a.appr_type = b.apprType,															");
    	query.append("				a.object_id = b.objectId,															");
    	query.append("				a.appr_status = b.apprStatus,														");
    	query.append("				a.req_by = b.reqBy,																	");
    	query.append("				a.title = b.title,																	");
    	query.append("				a.remark = b.remark,																");
    	query.append("				a.req_date = TO_CHAR(SYSDATE, 'YYYYMMDD')											");
    	query.append("WHEN NOT MATCHED THEN																				");
    	query.append("	INSERT (a.comp_no,	a.apprlist_id,	a.appr_type,	a.object_id,	a.appr_status,				");
    	query.append("			a.req_by,	a.title,		a.remark,		a.req_date						)			");
    	query.append("	VALUES (b.compNo,	b.apprListId,	b.apprType,		b.objectId,		b.apprStatus,				");
    	query.append("			b.reqBy,	b.title,		b.remark,		TO_CHAR(SYSDATE, 'YYYYMMDD')	)			");
    	
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
            
        this.getJdbcTemplate().update(query.toString(), objects);  
    }

    public void actionReq(AppReqDetailDTO appReqDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("UPDATE TAAPPRLIST x                               ");
        query.append("SET    x.appr_status  = ?                         ");
        query.append("WHERE  x.apprlist_id  = ?                         ");
        query.append("  AND  x.comp_no  = ?                             ");

        Object[] objects = new Object[] {
                appReqDetailDTO.getApprStatus() //결재요청
                , appReqDetailDTO.getApprlistId()
                , user.getCompNo()
        };

        this.getJdbcTemplate().update(query.toString(), objects);  
        
        //요청시엔는 항상 기안자를 기본으로 생성해 준다.(APPRUSR_STATUS:A 기안으로 등록)
        query = new QueryBuffer();
        query.append("INSERT INTO TAAPPRUSR                                 ");
        query.append("  (comp_no    ,apprusr_id   ,apprlist_id              ");
        query.append("   ,appr_seq  ,appr_by      ,apprusr_action           ");
        query.append("   ,apprusr_status  ,appr_date, appr_time             ");
        query.append("   ,remark,   apprusr_type                            ");
        query.append("  )   VALUES (                                        ");
        query.append("    ?    ,sqaapprusr_id.nextval         ,?            ");
        query.append("   ,0    ,?                             ,'C'          ");
        query.append("   ,'A'  ,to_char(sysdate,'yyyymmdd')   ,to_char(sysdate,'hh24miss')           ");
        query.append("   ,?,   'DF'                                         ");
        query.append("  )                                                     ");
        
        objects = new Object[] {
        		user.getCompNo()
        		,appReqDetailDTO.getApprlistId()
        		,user.getEmpId()
        		,appReqDetailDTO.getRemark()
        };
        
        this.getJdbcTemplate().update(query.toString(), objects);
    }
    
    
    
    public void initApprUsrStatus(AppReqDetailDTO appReqDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("UPDATE TAAPPRUSR  set             ");
        query.append("    apprusr_action = ?            ");
        query.append("   ,apprusr_status = ?            ");
        query.append("WHERE apprlist_id = ?             ");
        query.append("    and  comp_no  = ?             ");
        query.append("    and  apprusr_action  != 'C'    "); //[P:처리대상 C:처리완료 Z:대기]

        Object[] objects = new Object[] {
                "Z"
        		,"Z"
        		,appReqDetailDTO.getApprlistId()
        		,user.getCompNo()
        };

        this.getJdbcTemplate().update(query.toString(), objects);  
    }
    
    public void setApprUsrStatus(AppReqDetailDTO appReqDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("UPDATE TAAPPRUSR                  ");
        query.append("SET apprusr_action = ?            ");
        query.append("WHERE appr_seq <=   (SELECT       ");
        query.append("                           nvl(min(appr_seq),1)        ");
        query.append("                    FROM   TAAPPRUSR                   ");
        query.append("                    WHERE  1=1                         ");
        query.append("                      AND  apprlist_id    = ?          ");
        query.append("                      AND  comp_no        = ?          ");
        query.append("                      AND  apprusr_type   = 'AP'       ");
        query.append("                      and  apprusr_action  != 'C'      "); //[P:처리대상 C:처리완료 Z:대기]
        query.append("                   )                                   ");
        query.append("      AND  apprlist_id    = ?                          ");
        query.append("      AND  comp_no        = ?                          ");
        query.append("      and  apprusr_action  != 'C'                      "); //[P:처리대상 C:처리완료 Z:대기]

        Object[] objects = new Object[] {
                "P" //처리대상
                ,appReqDetailDTO.getApprlistId()
                ,appReqDetailDTO.getCompNo()
                ,appReqDetailDTO.getApprlistId()
                ,user.getCompNo()
        };

        this.getJdbcTemplate().update(query.toString(), objects);  
    }
    
    
    public String checkUsr(AppReqDetailDTO appReqDetailDTO, User user){
    	QueryBuffer query = new QueryBuffer();
        
		query.append("SELECT                                ");
        query.append("       COUNT(1)                       ");
        query.append("FROM TAAPPRUSR x						");
        query.append("WHERE 1=1                             ");
        query.append("   and x.apprusr_action  != 'C'       "); //[P:처리대상 C:처리완료 Z:대기]
        query.append("   and x.apprusr_type     = 'AP'      "); //AP:결재자는 한명이상 있어야 함.
        query.append("   and x.apprlist_id = ?              ");
        query.append("   and x.comp_no = ?                  ");
        
        Object[] objects = new Object[] {
                 appReqDetailDTO.getApprlistId()
                ,user.getCompNo()
        };
        
        List resultList = getJdbcTemplate().queryForList(query.toString(), objects);

        return QueryBuffer.listToString(resultList);
    }
}