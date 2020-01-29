package dream.pers.appreq.dao.oraImpl;

import java.util.List;

import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.pers.appreq.dao.AppReqListDAO;
import dream.pers.appreq.dto.AppReqCommonDTO;

/**
 * �������� - ���
 * @author  javaworker
 * @version $Id: AppReqListDAO.java,v 1.2 2014/03/07 05:35:54 javaworker Exp $
 * @since   1.0
 * @spring.bean id="appReqListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AppReqListDAOOraImpl extends BaseJdbcDaoSupportOra implements AppReqListDAO
{
    public List findAppReqList(AppReqCommonDTO appReqCommonDTO)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                            ");
        query.append("       y.app_date||y.app_time,                    ");
        query.append("       x.app_doc_no,                              ");
        query.append("       x.title,                                   ");
        query.append("       SF2CODETODESC(x.app_status, 'APP_STATUS'), ");
        query.append("       SF2CODETODESC(x.wf_type, 'WF_TYPE'),       ");
        query.append("      (SELECT WM_CONCAT(a.object_no)              ");
        query.append("       FROM   T2APP_DOC_DTL a                     ");
        query.append("       WHERE a.app_doc_no = x.app_doc_no)         ");
        query.append("       objectInfo,                                ");
        query.append("       y.app_flow_no                              ");
        query.append("FROM   T2APP_DOC x, T2APP_FLOW y                  ");
        query.append("WHERE  x.app_doc_no = y.app_doc_no                ");
        query.append("  AND  y.wf_status = 'A'                          "); // ��û
        query.append(getWhere(appReqCommonDTO));
        query.append("ORDER BY x.app_doc_no DESC                        ");

        return getJdbcTemplate().queryForList(query.toString(appReqCommonDTO.getIsLoadMaxCount(),""));
    }
    
    /**
     * Filter ����
     * @author  javaworker
     * @version $Id: AppReqListDAO.java,v 1.2 2014/03/07 05:35:54 javaworker Exp $
     * @since   1.0
     * 
     * @param appReqCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(AppReqCommonDTO appReqCommonDTO)
    {        
        QueryBuffer query = new QueryBuffer();
        
        // CommonDTO�� appDonNo�� �ִ°��� �󼼿��� ���� �Ǿ List�� �� Row���� ����ȸ
        if (!"".equals(appReqCommonDTO.getAppFlowNo()))
        {
            query.getAndQuery("y.app_flow_no", appReqCommonDTO.getAppFlowNo());
            return query.toString();
        }
        
        query.getAndQuery("y.user_id", appReqCommonDTO.getEnterBy());  // ��û��
        
        query.getAndQuery("x.app_doc_no", appReqCommonDTO.getFilterAppDocNo());  // �����ȣ
        query.getAndQuery("x.title",      appReqCommonDTO.getTitle());        // ����
        query.getAndQuery("x.app_status", appReqCommonDTO.getAppStatus());             // �������
        
        query.getAndDateQuery("x.enter_date", appReqCommonDTO.getEnterDateFrom(), appReqCommonDTO.getEnterDateTo());  // �������
        
        query.getAndQuery("x.wf_type", appReqCommonDTO.getWfType());             // ��������
        
        String objectNo = appReqCommonDTO.getObjectNo();
        if (objectNo != null && !"".equals(objectNo))
        {
            query.append("  AND EXISTS (SELECT 1                        ");
            query.append("              FROM   T2APP_DOC_DTL a          ");
            query.append("              WHERE  a.app_doc_no = x.app_doc_no      ");
            query.getAndQuery("a.object_no", objectNo);
            query.append("              )                               ");
        }
        
        return query.toString();
    }
    
    public int findAppReqListCount(AppReqCommonDTO appReqCommonDTO)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT COUNT(*)                                   ");
        query.append("FROM   T2APP_DOC x, T2APP_FLOW y                  ");
        query.append("WHERE  x.app_doc_no = y.app_doc_no                ");
        query.append("  AND  y.wf_status = 'A'                          "); // ��û
        query.append(this.getWhere(appReqCommonDTO));
        
        return (int)getJdbcTemplate().queryForObject(query.toString(), Integer.class);
    }
}