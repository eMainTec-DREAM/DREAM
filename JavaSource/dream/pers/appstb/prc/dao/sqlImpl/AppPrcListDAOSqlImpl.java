package dream.pers.appstb.prc.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.pers.appreq.dto.AppReqCommonDTO;
import dream.pers.appstb.prc.dao.AppPrcListDAO;

/**
 * �������� - ���
 * @author  javaworker
 * @version $Id: AppPrcListDAO.java,v 1.1 2013/08/30 09:12:11 javaworker Exp $
 * @since   1.0
 * @spring.bean id="appPrcListDAOTarget"
 * @spring.txbn id="appPrcListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AppPrcListDAOSqlImpl extends BaseJdbcDaoSupportSql implements AppPrcListDAO
{
    public List findAppPrcList(AppReqCommonDTO appReqCommonDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        
        query.append("SELECT                                                                       ");
        query.append("       ''                                            seqNo,                  ");
        query.append("       ''                                             isDelCheck,            ");
        query.append("       appr_seq apprSeq,                                                     ");
        query.append("       y.emp_name apprByName,        											");
        query.append("       y.grade,      															");
        query.append("       (SELECT description       												");
        query.append("        FROM   TADEPT      													");
        query.append("        WHERE  dept_id = y.dept_id) deptName,         						");
        query.append("       dbo.SFACODE_TO_DESC(x.apprusr_action,'APPRUSR_ACTION','SYS','','"+loginUser.getLangId()+"') apprusrAction,           		");
        query.append("       dbo.SFACODE_TO_DESC(x.apprusr_status,'APPRUSR_STATUS','SYS','','"+loginUser.getLangId()+"') apprusrStatus,           ");
        query.append("       dbo.SFACODE_TO_DESC(x.apprusr_type,'APPRUSR_TYPE','SYS','','"+loginUser.getLangId()+"') apprusrType,           ");
        query.append("       CONVERT(VARCHAR,CONVERT(DATE,x.appr_date),23)+' '+SUBSTRING(appr_time,1,2)+':'+SUBSTRING(appr_time,3,2)+':00' apprTime,	");
        query.append("       x.REMARK,          ");
        query.append("       x.apprusr_id apprusrId             ");
        query.append("FROM TAAPPRUSR x, TAEMP y     ");
        query.append("WHERE x.appr_by = y.emp_id        ");
        query.append("    and  x.comp_no = y.comp_no                    ");
        query.append(getWhere(appReqCommonDTO, loginUser));
        query.getOrderByQuery("x.apprusr_id","x.appr_date+x.appr_time, appr_seq", appReqCommonDTO.getOrderBy(), appReqCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(appReqCommonDTO.getIsLoadMaxCount(), appReqCommonDTO.getFirstRow()));
    }
    
    /**
     * Filter ����
     * @author  javaworker
     * @version $Id: AppPrcListDAO.java,v 1.1 2013/08/30 09:12:11 javaworker Exp $
     * @since   1.0
     * 
     * @param appPrcCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(AppReqCommonDTO appReqCommonDTO, User loginUser)
    {         
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.getAndQuery("x.comp_no", loginUser.getCompNo());
        query.getAndQuery("x.apprlist_id", appReqCommonDTO.getApprlistId());

        if (!"".equals(appReqCommonDTO.getApprusrId()))
        {
            query.getAndQuery("x.apprusr_id", appReqCommonDTO.getApprusrId());
            return query.toString();
        }
        
        if(!"".equals(appReqCommonDTO.getObjectId()) && !"".equals(appReqCommonDTO.getApprType()))
        {
            query.append(" AND  x.apprlist_id  = (SELECT                             ");
            query.append("                               z.apprlist_id apprlistId    ");
            query.append("                        FROM   TAAPPRLIST z                ");
            query.append("                        WHERE  1 = 1                       ");
            //query.append("                            and z.appr_status <> 'W'       ");
            query.getAndQuery("z.comp_no", loginUser.getCompNo());
            query.getAndQuery("z.object_id", appReqCommonDTO.getObjectId());
            query.getAndQuery("z.appr_type", appReqCommonDTO.getApprType());
            query.append("                       )                                   ");
            
            //�����̷¿��� ��ü�� ���� �ְ� ó��.
            if (!"Y".equals(appReqCommonDTO.getIsAppHist()))
            {
                query.append(" AND  x.apprusr_action  != 'C'                              "); //[P:ó����� C:ó���Ϸ� Z:���]
            }
        }
                
/*        // CommonDTO�� appDocNo �ִ°��� �󼼿��� ���� �Ǿ List�� �� Row���� ����ȸ
        if (!"".equals(appReqCommonDTO.getAppFlowNo()))
        {
            query.getAndQuery("y.app_flow_no", appReqCommonDTO.getAppFlowNo());
            return query.toString();
        }
        
        query.getAndQuery("x.app_doc_no", appReqCommonDTO.getFilterAppDocNo());  // �����ȣ
        query.getAndQuery("x.title",      appReqCommonDTO.getTitle());        // ����
        query.getAndQuery("x.app_status", appReqCommonDTO.getAppStatus());             // �������
*/        
       /* query.getAndDateQuery("y.app_date", appPrcCommonDTO.getAppDateFrom(), appPrcCommonDTO.getAppDateTo());  // ��������
        query.getAndDateQuery("x.enter_date", appPrcCommonDTO.getEnterDateFrom(), appPrcCommonDTO.getEnterDateTo());  // �������
        
        query.getAndQuery("x.wf_type", appPrcCommonDTO.getWfType());             // ��������
        
        String objectNo = appPrcCommonDTO.getObjectNo();
        if (objectNo != null && !"".equals(objectNo))
        {
            query.append("  AND EXISTS (SELECT 1                        ");
            query.append("              FROM   T2APP_DOC_DTL a          ");
            query.append("              WHERE  a.app_doc_no = x.app_doc_no      ");
            query.getAndQuery("a.object_no", objectNo);
            query.append("              )                               ");
        }
        
        // ����- WF_ACTION : ó���Ϸ�[C], WF_STATUS : ����[C]
        String wfStatus = appPrcCommonDTO.getWfStatus();
        if ("C".equals(wfStatus))
        {
            query.append("  AND  y.wf_action = 'C'          ");
            query.append("  AND  y.wf_status = 'C'          ");
            
            query.getAndQuery("y.autho_by", appPrcCommonDTO.getEnterBy());   // ������(ó����)
        }
        // �ݷ� - WF_ACTION : ó���Ϸ�[C], WF_STATUS : �ݷ�[D]
        else if ("D".equals(wfStatus))
        {
            query.append("  AND  y.wf_action = 'C'          ");
            query.append("  AND  y.wf_status = 'D'          ");
            
            query.getAndQuery("y.autho_by", appPrcCommonDTO.getEnterBy());   // ������(ó����)
        }
        // �̽��� - WF_ACTION : ó�����[P],  APP_TYPE : ����[GR]/����[AP]
        else
        {
            query.append("  AND  y.wf_action = 'P'              ");
            query.append("  AND  y.app_type IN ('GR', 'AP')     ");
            
            query.getAndQuery("y.user_id", appPrcCommonDTO.getEnterBy());   // ������(�����)
        }*/
        
        return query.toString();
    }

    public int deleteLine(String id, String compNo)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query = new QuerySqlBuffer();
        
        query.append("DELETE FROM TAAPPRUSR               ");
        query.append("WHERE apprusr_id     = ?            ");
        query.append("    and comp_no     = ?             ");
        
        Object[] objects = new Object[] {
        		id
        		,compNo
        };
        return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }

	@Override
	public String findTotalCount(AppReqCommonDTO appReqCommonDTO, User loginUser) throws Exception {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                ");
        query.append("       COUNT(1)                       ");
        query.append("FROM TAAPPRUSR x, TAEMP y             ");
        query.append("WHERE x.appr_by = y.emp_id            ");
        query.append("    and  x.comp_no = y.comp_no		");
        query.getAndQuery("x.comp_no", loginUser.getCompNo());
        query.append(getWhere(appReqCommonDTO, loginUser));

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
    
}