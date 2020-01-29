package dream.app.not.dao.sqlImpl;

import java.util.List;

import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.app.not.dao.AppNotListDAO;
import dream.app.not.dto.AppNotCommonDTO;
import dream.app.not.dto.AppNotDTO;

/**
 * �������� - ���
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
        query.append("  AND  y.wf_status <> 'A'                         "); // ��û����
        query.append("  AND  y.app_type = 'NT'                          "); // �뺸
        query.append(getWhere(appNotCommonDTO));
        
        // �뺸ó���� ���� �ʰ� �Էµ� ������ �������� ��(update_date 
        query.append("ORDER BY y.update_date DESC, y.app_flow_no DESC           ");
 
        return getJdbcTemplate().queryForList(query.toString(appNotCommonDTO.getIsLoadMaxCount(), ""));
    }
    
    /**
     * Filter ����
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
        
        // CommonDTO�� ReqNo�� �ִ°��� �󼼿��� ���� �Ǿ List�� �� Row���� ����ȸ�ϴ� ����̴�.
        if (!"".equals(appNotCommonDTO.getAppFlowNo()))
        {
            query.getAndQuery("y.app_flow_no", appNotCommonDTO.getAppFlowNo());
            return query.toString();
        }
        
        query.getAndQuery("x.app_doc_no", appNotCommonDTO.getFilterAppDocNo());  // �����ȣ
        query.getAndQuery("x.title",      appNotCommonDTO.getTitle());        // ����
        query.getAndQuery("x.app_status", appNotCommonDTO.getAppStatus());             // �������
        
        query.getAndDateQuery("y.app_date", appNotCommonDTO.getAppDateFrom(), appNotCommonDTO.getAppDateTo());  // ��������
        query.getAndDateQuery("x.enter_date", appNotCommonDTO.getEnterDateFrom(), appNotCommonDTO.getEnterDateTo());  // �������
        
        query.getAndQuery("x.wf_type", appNotCommonDTO.getWfType());             // ��������
        
        String objectNo = appNotCommonDTO.getObjectNo();
        if (objectNo != null && !"".equals(objectNo))
        {
            query.append("  AND EXISTS (SELECT 1                        ");
            query.append("              FROM   T2APP_DOC_DTL a          ");
            query.append("              WHERE  a.app_doc_no = x.app_doc_no      ");
            query.getAndQuery("a.object_no", objectNo);
            query.append("              )                               ");
        }
        
        // Ȯ��- WF_ACTION : ó���Ϸ�[C]
        String wfAction = appNotCommonDTO.getWfAction();
        if ("C".equals(wfAction))
        {
            query.append("  AND  y.wf_action = 'C'          ");
            query.append("  AND  y.wf_status = 'F'          "); // Ȯ��
            
            query.getAndQuery("y.autho_by", appNotCommonDTO.getEnterBy());   // ������(ó����)
        }
        // ��Ȯ�� - WF_ACTION : ó�����[P]
        else
        {
            query.append("  AND  y.wf_action = 'P'          ");
            query.append("  AND  y.wf_status = 'Z'          "); // ���
            
            query.getAndQuery("y.user_id", appNotCommonDTO.getEnterBy());   // ������(�����)
        }
        
        return query.toString();
    }
    
    public int findAppNotListCount(AppNotCommonDTO appNotCommonDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT COUNT(*)                                   ");
        query.append("FROM   T2APP_DOC x, T2APP_FLOW y                  ");
        query.append("WHERE  x.app_doc_no = y.app_doc_no                ");
        query.append("  AND  y.wf_status <> 'A'                         "); // ��û����
        query.append("  AND  y.app_type = 'NT'                          "); // �뺸����
        query.append(this.getWhere(appNotCommonDTO));
        
        return (int)getJdbcTemplate().queryForObject(query.toString(), Integer.class);
    }

    /**
     * �뺸����Ȯ��
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