package dream.app.not.dao.sqlImpl;

import java.util.List;

import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.app.not.dao.AppNotListDAO;
import dream.app.not.dto.AppNotCommonDTO;
import dream.app.not.dto.AppNotDTO;

/**
 * 예방점검 - 목록
 * @author  javaworker
 * @version $Id: AppNotListDAO.java,v 1.1 2013/08/30 09:14:41 javaworker Exp $
 * @since   1.0
 * @spring.bean id="appNotListDAOTarget"
 * @spring.bean id="appNotListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AppNotListDAOSqlImpl extends BaseJdbcDaoSupportSql implements AppNotListDAO
{
    public List findAppNotList(AppNotCommonDTO appNotCommonDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                            ");
        query.append("       CASE y.wf_action WHEN 'P' THEN 'N' ELSE 'Y' END,	");
        query.append("       x.app_doc_no,                              ");
        query.append("       x.title,                                   ");
        query.append("       dbo.SF2CODETODESC(x.app_status, 'APP_STATUS'), ");
        query.append("       dbo.SF2CODETODESC(x.wf_type, 'WF_TYPE'),   ");
        query.append("      STUFF((SELECT ',' + a.object_no)            ");
        query.append("       FROM   T2APP_DOC_DTL a                     ");
        query.append("       WHERE a.app_doc_no = x.app_doc_no         	");
        query.append("       FOR XML PATH('')),1,1,'')			        ");
        query.append("       objectInfo,                                ");
        query.append("       y.app_date+y.app_time,                    	");
        query.append("       x.enter_date,                              ");
        query.append("       y.app_flow_no                              ");
        query.append("FROM   T2APP_DOC x, T2APP_FLOW y                  ");
        query.append("WHERE  x.app_doc_no = y.app_doc_no                ");
        query.append("  AND  y.wf_status <> 'A'                         "); // 요청제외
        query.append("  AND  y.app_type = 'NT'                          "); // 통보
        query.append(getWhere(appNotCommonDTO));
        
        // 통보처리가 가장 늦게 입력된 순부터 보여지게 함(update_date 
        query.append("ORDER BY y.update_date DESC, y.app_flow_no DESC           ");
 
        return getJdbcTemplate().queryForList(query.toString(appNotCommonDTO.getIsLoadMaxCount(), ""));
    }
    
    /**
     * Filter 조건
     * @author  javaworker
     * @version $Id: AppNotListDAO.java,v 1.1 2013/08/30 09:14:41 javaworker Exp $
     * @since   1.0
     * 
     * @param appNotCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(AppNotCommonDTO appNotCommonDTO)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        // CommonDTO의 ReqNo가 있는경우는 상세에서 수정 되어서 List의 한 Row만을 재조회하는 경우이다.
        if (!"".equals(appNotCommonDTO.getAppFlowNo()))
        {
            query.getAndQuery("y.app_flow_no", appNotCommonDTO.getAppFlowNo());
            return query.toString();
        }
        
        query.getAndQuery("x.app_doc_no", appNotCommonDTO.getFilterAppDocNo());  // 결재번호
        query.getAndQuery("x.title",      appNotCommonDTO.getTitle());        // 제목
        query.getAndQuery("x.app_status", appNotCommonDTO.getAppStatus());             // 결재상태
        
        query.getAndDateQuery("y.app_date", appNotCommonDTO.getAppDateFrom(), appNotCommonDTO.getAppDateTo());  // 결재일자
        query.getAndDateQuery("x.enter_date", appNotCommonDTO.getEnterDateFrom(), appNotCommonDTO.getEnterDateTo());  // 기안일자
        
        query.getAndQuery("x.wf_type", appNotCommonDTO.getWfType());             // 결재종류
        
        String objectNo = appNotCommonDTO.getObjectNo();
        if (objectNo != null && !"".equals(objectNo))
        {
            query.append("  AND EXISTS (SELECT 1                        ");
            query.append("              FROM   T2APP_DOC_DTL a          ");
            query.append("              WHERE  a.app_doc_no = x.app_doc_no      ");
            query.getAndQuery("a.object_no", objectNo);
            query.append("              )                               ");
        }
        
        // 확인- WF_ACTION : 처리완료[C]
        String wfAction = appNotCommonDTO.getWfAction();
        if ("C".equals(wfAction))
        {
            query.append("  AND  y.wf_action = 'C'          ");
            query.append("  AND  y.wf_status = 'F'          "); // 확인
            
            query.getAndQuery("y.autho_by", appNotCommonDTO.getEnterBy());   // 결재자(처리자)
        }
        // 미확인 - WF_ACTION : 처리대상[P]
        else
        {
            query.append("  AND  y.wf_action = 'P'          ");
            query.append("  AND  y.wf_status = 'Z'          "); // 대기
            
            query.getAndQuery("y.user_id", appNotCommonDTO.getEnterBy());   // 결재자(대상자)
        }
        
        return query.toString();
    }
    
    public int findAppNotListCount(AppNotCommonDTO appNotCommonDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT COUNT(*)                                   ");
        query.append("FROM   T2APP_DOC x, T2APP_FLOW y                  ");
        query.append("WHERE  x.app_doc_no = y.app_doc_no                ");
        query.append("  AND  y.wf_status <> 'A'                         "); // 요청제외
        query.append("  AND  y.app_type = 'NT'                          "); // 통보제외
        query.append(this.getWhere(appNotCommonDTO));
        
        return (int)getJdbcTemplate().queryForObject(query.toString(), Integer.class);
    }

    /**
     * 통보문서확인
     * @author  javaworker
     * @version $Id: AppNotListDAO.java,v 1.1 2013/08/30 09:14:41 javaworker Exp $
     * @since   1.0
     * 
     * @param appNotDTO
     */
    public void confirmAppNot(AppNotDTO appNotDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("UPDATE T2APP_FLOW                                   ");
        query.append("SET    wf_action = 'C',                             ");
        query.append("       wf_status = 'F',                             ");
        query.append("       autho_by  = ?,                               ");
        query.append("       app_date  = CONVERT(VARCHAR, GETDATE(), 112),    ");
        query.append("       app_time  = LEFT(REPLACE(CONVERT(VARCHAR, GETDATE(), 108),':',''),4)       ");
        query.append("WHERE  app_flow_no = ?                              ");
        query.append("  AND  wf_action = 'P'                              ");
        
        Object[] objects = new Object[]{
                appNotDTO.getEnterBy(),
                appNotDTO.getAppFlowNo()
        };
        
        this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }
}