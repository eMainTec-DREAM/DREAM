package dream.pers.appstb.ready.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.pers.appreq.dto.AppReqDetailDTO;
import dream.pers.appstb.ready.dao.AppReadyListDAO;
import dream.pers.appstb.ready.dto.AppReadyCommonDTO;

/**
 * 목록
 * @author  javaworker
 * @version $Id: maAppLineUsrPopupDetailListDAO.java,v 1.1 2013/08/30 09:12:11 javaworker Exp $
 * @since   1.0
 * @spring.bean id="appReadyListDAOTarget"
 * @spring.txbn id="appReadyListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AppReadyListDAOOraImpl extends BaseJdbcDaoSupportOra implements AppReadyListDAO
{
    public List findList(AppReadyCommonDTO appReadyCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                    ");
        query.append("        '' seqNo,                         ");
        query.append("        '' isDelCheck,                    ");
        query.getDate("req_date", "reqDate,");
        query.append("        title,                            ");
        query.append("        (SELECT emp_name                  ");
        query.append("         FROM TAEMP                       ");
        query.append("         WHERE emp_id = req_by) reqBy,    ");
        query.append("        x.REMARK,                         ");
        query.append("        y.apprusr_id apprusrId,           ");
        query.append("        y.apprlist_id apprlistId,         ");
        query.append("        x.appr_type apprType,             ");
        query.append("        SFACODE_TO_DESC(x.appr_type,'APPR_TYPE','SYS','','"+user.getLangId()+"') apprTypeDesc,      ");
        query.append("        SFACODE_TO_DESC(x.appr_status,'APPR_STATUS','SYS','','"+user.getLangId()+"')  apprStatusDesc,        ");
        query.append("        SFACODE_TO_DESC(y.apprusr_type,'APPRUSR_TYPE','SYS','','"+user.getLangId()+"')  apprUsrType,        ");
        query.append("        x.object_id objectId              ");
        query.append("FROM  TAAPPRLIST x , TAAPPRUSR y          ");
        query.append("WHERE x.apprlist_id = y.apprlist_id       ");
        query.append("   AND x.comp_no = y.comp_no             ");
        query.append("   AND y.apprusr_action = 'P'             ");
        query.append("   AND x.appr_status IN ('R','P', 'C')    "); //결재요청, 진행중, 결재완료(참조자가 있을수 있음)
        query.append(getWhere(appReadyCommonDTO, user));
//        query.append("ORDER BY req_date                         ");
        query.getOrderByQuery("x.req_date", appReadyCommonDTO.getOrderBy(), appReadyCommonDTO.getDirection());

        return getJdbcTemplate().queryForList(query.toString(appReadyCommonDTO.getIsLoadMaxCount(), appReadyCommonDTO.getFirstRow()));
    }
    
    /**
     * Filter 조건
     * @author  javaworker
     * @version $Id: maAppLineUsrPopupDetailListDAO.java,v 1.1 2013/08/30 09:12:11 javaworker Exp $
     * @since   1.0
     * 
     * @param appReadyCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(AppReadyCommonDTO appReadyCommonDTO,User user)
    {        
        QueryBuffer query = new QueryBuffer();
        
        if (!"".equals(appReadyCommonDTO.getApprusrId()))
        {
            query.getAndQuery("y.apprusr_id", appReadyCommonDTO.getApprusrId());
            return query.toString();
        }
        
        query.getAndDateQuery("x.req_date", appReadyCommonDTO.getStartDate(), appReadyCommonDTO.getEndDate());
        query.getAndQuery("y.appr_by", user.getEmpId());
        query.getAndQuery("x.comp_no", user.getCompNo());
        
        return query.toString();
    }

    public int deleteLine(String id, String compNo)
    {
        QueryBuffer query = new QueryBuffer();

        query = new QueryBuffer();
        
        query.append("DELETE FROM TAAPPRUSR               ");
        query.append("WHERE apprusr_id     = ?            ");
        query.append("   and  comp_no     = ?            ");
        
        Object[] objects = new Object[] {
        		id,    
        		compNo
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }

    /**
     * 결재자 상태 및 액션 수정
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @param apprAction
     * @param apprStatus
     * @return
     */
    public int approveLine(String apprUsrId, User user, String apprAction, String apprStatus, AppReadyCommonDTO appReadyCommonDTO)
    {
        QueryBuffer query = new QueryBuffer();
        int rtnValue = 0;
        
        query.append("UPDATE TAAPPRUSR                   ");
        query.append("SET  apprusr_action  = ?            ");
        query.append("    ,apprusr_status = ?            ");
        query.append("    ,appr_date = TO_CHAR(SYSDATE, 'YYYYMMDD') ");
        query.append("    ,appr_time = TO_CHAR(SYSDATE, 'HH24MISS') ");
        query.append("    ,remark         = ?            ");
        query.append("WHERE apprusr_id    = ?            ");
        query.append("AND comp_no          = ?            ");

        Object[] objects = new Object[] {
                apprAction, // 결제처리 진행상태 [P:처리대상,C:처리완료 Z:대기]    
                apprStatus, // 결재처리상태[C:승인,D:반려,Z:대기] 참조자는 확인이 있어야 함.
                appReadyCommonDTO.getRemark(),
                apprUsrId,
                user.getCompNo()
        };

        rtnValue =  this.getJdbcTemplate().update(query.toString(), objects);
        
        return rtnValue;
    }

    /**
     * 다음결재자를 결재 대기로 변경..
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param apprlistId
     * @param compNo
     * @return
     */
    public int updateApprovalLine(String apprUsrId, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("UPDATE TAAPPRUSR                                      ");
        query.append("SET apprusr_action = ?                                ");
        query.append("WHERE appr_seq   = (SELECT                            ");
        query.append("                           min(appr_seq)              ");
        query.append("                    FROM   TAAPPRUSR                  ");
        query.append("                    WHERE  apprusr_action = 'Z'       ");
        query.append("                      AND  apprusr_type  = 'AP'                             ");
        query.append("                      and  apprlist_id =  (                                  ");
        query.append("                                            select aa.apprlist_id            ");
        query.append("                                            from TAAPPRUSR aa                ");
        query.append("                                            where aa.comp_no = ?             ");
        query.append("                                                and aa.apprusr_id = ?        ");
        query.append("                                           )                                 ");
        query.append("                      AND  comp_no    = ?             ");
        query.append("                   )                                  ");
        query.append("AND  comp_no    = ?                                   ");
        query.append("AND  apprusr_action    = 'Z'                          ");
    	query.append("AND  apprusr_type      = 'AP'                          ");
        query.append("and  apprlist_id =  (                                  ");
        query.append("                      select aa.apprlist_id            ");
        query.append("                      from TAAPPRUSR aa                ");
        query.append("                      where aa.comp_no = ?             ");
        query.append("                          and aa.apprusr_id = ?        ");
        query.append("                     )                                 ");

        Object[] objects = new Object[] {
                "P", //처리대상
                user.getCompNo(),
                apprUsrId,
                user.getCompNo(),
                user.getCompNo(),
                user.getCompNo(),
                apprUsrId
                
        };

        return this.getJdbcTemplate().update(query.toString(), objects);  
    }
    
    
    /**
     * 참조자를 결재대기로 변경처리.
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param apprlistId
     * @param compNo
     * @return
     */
    public int updateReferenceLine(String apprUsrId, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("UPDATE TAAPPRUSR                                      ");
    	query.append("SET apprusr_action = ?                                ");
    	query.append("WHERE appr_seq  <= (SELECT                            ");
    	query.append("                           nvl(min(appr_seq),999999999999)                   ");  
    	query.append("                    FROM   TAAPPRUSR                                         ");
    	query.append("                    WHERE  apprusr_action = 'Z'                              ");
    	query.append("                      AND  apprusr_type   = 'AP'                             ");
    	query.append("                      and  apprlist_id =  (                                  ");
    	query.append("                                            select aa.apprlist_id            ");
    	query.append("                                            from TAAPPRUSR aa                ");
    	query.append("                                            where aa.comp_no = ?             ");
    	query.append("                                                and aa.apprusr_id = ?        ");
    	query.append("                                           )                                 ");
    	query.append("                      AND  comp_no    = ?             ");
    	query.append("                   )                                  ");
    	query.append("AND  comp_no    = ?                                   ");
    	query.append("AND  apprusr_action    = 'Z'                          ");
    	query.append("AND  apprusr_type      = 'RF'                          ");
    	query.append("and  apprlist_id =  (                                  ");
    	query.append("                      select aa.apprlist_id            ");
    	query.append("                      from TAAPPRUSR aa                ");
    	query.append("                      where aa.comp_no = ?             ");
    	query.append("                          and aa.apprusr_id = ?        ");
    	query.append("                     )                                 ");
    	
    	Object[] objects = new Object[] {
    			"P", //처리대상
    			user.getCompNo(),
    			apprUsrId,
    			user.getCompNo(),
    			user.getCompNo(),
    			user.getCompNo(),
    			apprUsrId
    			
    	};
    	
    	return this.getJdbcTemplate().update(query.toString(), objects);  
    }
    
    public String checkApprUsrType(String apprUsrId, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("SELECT                       ");
    	query.append("       apprusr_type          ");
    	query.append("FROM   TAAPPRUSR             ");
    	query.append("WHERE  1=1                   ");
    	query.append("    and comp_no = ?          ");
    	query.append("    AND  apprusr_id  = ?     ");

        
        Object[] objects = new Object[] {
        		 user.getCompNo()
        		,apprUsrId
        };
        
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString(),objects));
    }
    
    public String isParalApprUser(String apprUsrId, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("select count(*)                                   ");
    	query.append("from  TAAPPRUSR                                   ");
    	query.append("WHERE appr_seq   = (                              ");
    	query.append("                       select aa.appr_seq         ");
    	query.append("                       from TAAPPRUSR aa          ");
    	query.append("                       where aa.comp_no = ?       ");
    	query.append("                           and aa.apprusr_id = ?  ");
    	query.append("                    )                             ");
    	query.append("AND  comp_no    =  ?                              ");
    	query.append("AND  apprusr_action    = 'P'                      ");
    	query.append("AND  apprusr_status    = 'Z'                      ");
    	query.append("AND  apprusr_type      = 'AP'                     ");
    	query.append("and  apprlist_id =  (                             ");
    	query.append("                      select aa.apprlist_id       ");
    	query.append("                      from TAAPPRUSR aa           ");
    	query.append("                      where aa.comp_no =  ?       ");
    	query.append("                          and aa.apprusr_id = ?   ");
    	query.append("                     )                            ");
    	
    	
    	Object[] objects = new Object[] {
    			user.getCompNo()
    			,apprUsrId
    			,user.getCompNo()
    			,user.getCompNo()
    			,apprUsrId
    	};
    	
    	return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString(),objects));
    }
    

    /**
     * 결재 요청 상태 변경
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param apprlistId
     * @param compNo
     * @param apprStatus
     */
    public int updateApproveList(String apprusrId, User user, String apprStatus)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("UPDATE TAAPPRLIST                             ");
        query.append("SET appr_status      = ?                      ");
        query.append("WHERE 1=1                                     ");
        query.append("    AND comp_no    = ?                                         ");
        query.append("    and  apprlist_id =      (                                  ");
        query.append("                              select aa.apprlist_id            ");
        query.append("                              from TAAPPRUSR aa                ");
        query.append("                              where aa.comp_no = ?             ");
        query.append("                              and aa.apprusr_id = ?            ");
        query.append("                             )                                 ");

        Object[] objects = new Object[] {
                 apprStatus                // 결재진행상태[W:작성중 R:결재요청 P:결재진행 C:결재완료 D:결재반려]
                ,user.getCompNo()
                ,user.getCompNo()
                ,apprusrId
        };

        return this.getJdbcTemplate().update(query.toString(), objects);  
    }
    
    /**
     * 결재반려처리.
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param apprlistId
     * @param compNo
     * @param apprStatus
     */
    public int updateRejectList(String apprusrId, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue = 0;
    	
    	query.append("DELETE FROM TAAPPRUSR                                 ");
    	query.append("WHERE appr_seq  > (SELECT                             ");
    	query.append("                           appr_seq                   ");  
    	query.append("                    FROM   TAAPPRUSR                  ");
    	query.append("                    WHERE  comp_no =  ?               ");
    	query.append("                      and  apprusr_id =  ?            ");
    	query.append("                   )                                  ");
    	query.append("    and  comp_no    = ?                               ");
    	query.append("    and  apprusr_action <> 'C'                        ");
    	query.append("    and  apprusr_status = 'Z'                         ");
    	query.append("    and  apprlist_id =  (                             ");
    	query.append("                      select aa.apprlist_id           ");
    	query.append("                      from TAAPPRUSR aa               ");
    	query.append("                      where aa.comp_no = ?            ");
    	query.append("                          and aa.apprusr_id = ?       ");
    	query.append("                     )                                ");

        Object[] objects = new Object[] {
        		 user.getCompNo()
        		,apprusrId
                ,user.getCompNo()
                ,user.getCompNo()
                ,apprusrId
        };

        rtnValue =  this.getJdbcTemplate().update(query.toString(), objects);
        
        
        query = new QueryBuffer();
        query.append("UPDATE TAAPPRUSR                   ");
        query.append("SET apprusr_action  = 'C'          ");
        query.append("    ,apprusr_status = 'D'          ");
        query.append("    ,appr_date = TO_CHAR(SYSDATE, 'YYYYMMDD')  ");
        query.append("    ,appr_time = TO_CHAR(SYSDATE, 'HH24MISS')  ");
        query.append("    ,remark         = '결재자 반려처리로 종료됨.'                ");
        query.append("WHERE   comp_no     = ?                       ");
        query.append("    and  apprusr_action <> 'C'                ");
        query.append("    and  apprusr_status = 'Z'                 ");
        query.append("    and  apprlist_id =      (                 ");
        query.append("                              select aa.apprlist_id            ");
        query.append("                              from TAAPPRUSR aa                ");
        query.append("                              where aa.comp_no = ?             ");
        query.append("                              and aa.apprusr_id = ?            ");
        query.append("                             )                                 ");
        
        objects = new Object[] {
        		user.getCompNo()
                ,user.getCompNo()
                ,apprusrId
        };
        
        rtnValue =  this.getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    
    

    public AppReqDetailDTO findListDetail(String apprusrId, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                               ");
        query.append("       x.comp_no compNo,      ");
        query.append("       x.apprlist_id apprlistId,      ");
        query.append("       x.appr_type apprType,      ");
        query.append("       SFACODE_TO_DESC(x.appr_type,'APPR_TYPE','SYS','','"+loginUser.getLangId()+"')  apprTypeDesc,     ");
        query.append("       x.object_id objectId,      ");
        query.append("       x.appr_status apprStatus,      ");
        query.append("       SFACODE_TO_DESC(x.appr_status,'APPR_STATUS','SYS','','"+loginUser.getLangId()+"')  apprStatusDesc,       ");
        query.append("       req_by reqBy,              ");
        query.append("       (SELECT emp_name           ");
        query.append("        FROM   TAEMP              ");
        query.append("        WHERE  emp_id = req_by) reqByName,      ");
        query.append("       req_date reqDate,      ");
        query.append("       title,                 ");
        query.append("       REMARK                 ");
        query.append("FROM   TAAPPRLIST x            ");
        query.append("WHERE  1 = 1                   ");
        query.append("    and  x.apprlist_id =   (                                  ");
        query.append("                              select aa.apprlist_id            ");
        query.append("                              from TAAPPRUSR aa                ");
        query.append("                              where aa.comp_no = ?             ");
        query.append("                              and aa.apprusr_id = ?            ");
        query.append("                             )                                 ");
        query.append("    and   x.comp_no = ?        ");
        
        Object[] objects = new Object[] {
        		loginUser.getCompNo()
        		,apprusrId   // 결재진행상태[W:작성중 R:결재요청 P:결재진행 C:결재완료 D:결재반려]
        		,loginUser.getCompNo()
        };

        return (AppReqDetailDTO)getJdbcTemplate().query(query.toString(), objects, 
                new MwareExtractor(new AppReqDetailDTO()));
    }

	@Override
	public String findTotalCount(AppReadyCommonDTO appReadyCommonDTO, User user)
	{
		QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                     	");
        query.append("       COUNT(1)                               ");
        query.append("FROM  TAAPPRLIST x , TAAPPRUSR y          ");
        query.append("WHERE x.apprlist_id = y.apprlist_id       ");
        query.append("   AND x.comp_no = y.comp_no             ");
        query.append("   AND y.apprusr_action = 'P'             ");
        query.append("   AND x.appr_status IN ('R','P', 'C')    "); //결재요청, 진행중, 결재완료(참조자가 있을수 있음)
        query.append(getWhere(appReadyCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QueryBuffer.listToString(resultList);
	}
    
}