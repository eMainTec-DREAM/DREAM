package dream.pers.appstb.prc.dao.sqlImpl;

import java.util.List;

import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.pers.appreq.dto.AppReqCommonDTO;
import dream.pers.appstb.prc.dao.AppPrcDetailDAO;
import dream.pers.appstb.prc.dto.AppPrcDetailDTO;

/**
 * �����û(���繮���ۼ�)
 * @author javaworker
 * @version $Id: AppPrcDetailDAO.java,v 1.1 2013/08/30 09:12:11 javaworker Exp $
 * @since 1.0
 * @spring.bean id="appPrcDetailDAOTarget"
 * @spring.txbn id="appPrcDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AppPrcDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements AppPrcDetailDAO
{
   /* *//**
     * ���缱��ȸ
     * @author  javaworker
     * @version $Id: AppPrcDetailDAO.java,v 1.1 2013/08/30 09:12:11 javaworker Exp $
     * @since   1.0
     * 
     * @param appPrcDetailDTO
     * @return
     *//*
    public List findFlowUserList(AppPrcDetailDTO appPrcDetailDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                ");
        query.append("       x.seq_no,                                      ");
        query.append("       dbo.SF2CODETODESC(x.wf_status, 'WF_STATUS'),       ");
        query.append("       x.app_date+x.app_time,                        ");
        query.append("       DECODE(x.seq_no, 0,                            ");
        query.append("          dbo.SF2CODETODESC(x.wf_status, 'WF_STATUS'),    "); // 0 �ΰ�� ��û(���)���� ǥ��
        query.append("          dbo.SF2CODETODESC(x.app_type, 'APP_TYPE')       ");
        query.append("       ),                                             ");
        query.append("       x.user_id                                      ");
        query.append("       +' / '+                                      ");
        query.append("       dbo.SF2CODETODESC(x.user_id, 'USER')               ");
        query.append("       +' / '+                                      ");
        query.append("      (SELECT dbo.SF2CODETODESC(a.dept_no, 'DEPT')        ");
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
                appPrcDetailDTO.getAppDocNo()
        };

        return getJdbcTemplate().queryForList(query.toString(), objects);
    }*/

    /**
     * ���繮�� ����ȸ
     * @author  javaworker
     * @version $Id: AppPrcDetailDAO.java,v 1.1 2013/08/30 09:12:11 javaworker Exp $
     * @since   1.0
     * 
     * @param appReqCommonDTO
     * @return
     */
    public AppPrcDetailDTO findDetail(AppReqCommonDTO appReqCommonDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                            ");
        query.append("       x.appr_seq apprSeq,                        ");
        query.append("       x.appr_by apprBy,                          ");
        query.append("       y.emp_name apprByName,                     ");
        query.append("       y.grade,                                   ");
        query.append("       x.apprusr_type  apprUsrTypeId,             ");
        query.append("       dbo.SFACODE_TO_DESC(x.apprusr_type,'APPRUSR_TYPE','SYS','','"+appReqCommonDTO.getUserLang()+"') apprUsrTypeDesc ,          ");
        query.append("       (SELECT description                        ");
        query.append("        FROM   TADEPT                             ");
        query.append("        WHERE  dept_id = y.dept_id) deptName,     ");
        query.append("       x.apprusr_id apprusrId,                    ");
        query.append("       x.remark     remark                        ");
        query.append("FROM TAAPPRUSR x, TAEMP y                         ");
        query.append("WHERE x.appr_by = y.emp_id                        ");
        query.append("  AND  x.comp_no = y.comp_no                      ");
        query.append("  AND  x.comp_no = ?                              ");
        query.append("  AND  x.apprusr_id = ?                           ");
        
        Object[] objects = new Object[]{
        		appReqCommonDTO.getCompNo()
        		,appReqCommonDTO.getApprusrId()
        };

        return (AppPrcDetailDTO)getJdbcTemplate().query(query.toString(),objects, 
                new MwareExtractor(new AppPrcDetailDTO()));
    }


    public void updateDetail(AppPrcDetailDTO appPrcDetailDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("UPDATE TAAPPRUSR                                       ");
        query.append("SET    appr_seq     = ?                               ");
        query.append("       ,appr_by     = ?                               ");
        query.append("       ,apprusr_type     = ?                          ");
        query.append("WHERE  apprusr_id   = ?                               ");

        Object[] objects = new Object[]{
                appPrcDetailDTO.getApprSeq(),
                appPrcDetailDTO.getApprBy(),
                appPrcDetailDTO.getApprUsrTypeId(),
                appPrcDetailDTO.getApprusrId()
        };

        this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }

    public void insertDetail(AppPrcDetailDTO appPrcDetailDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("INSERT INTO TAAPPRUSR                               ");
        query.append("  (comp_no,       apprusr_id,     apprlist_id,      ");
        query.append("   appr_seq,      appr_by,        apprusr_action,   ");
        query.append("   apprusr_status ,apprusr_type                     ");
        query.append("  )   VALUES                                        ");
        query.append("  (?,             ?,              ?,                ");
        query.append("   ?,             ?,              ?,                ");
        query.append("   ?,             ?                                 ");
        query.append("  )                                                 ");
        
        Object[] objects = new Object[] {
                appPrcDetailDTO.getCompNo(),
                appPrcDetailDTO.getApprusrId(),
                appPrcDetailDTO.getApprlistId(),
                appPrcDetailDTO.getApprSeq(),
                appPrcDetailDTO.getApprBy(),
                "Z",
                "Z",
                appPrcDetailDTO.getApprUsrTypeId()
        };
        
        this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }

	public String checkSeqNum(AppReqCommonDTO appReqCommonDTO, AppPrcDetailDTO appPrcDetailDTO) {

		QuerySqlBuffer query = new QuerySqlBuffer();
        
		query.append("SELECT                                ");
        query.append("       COUNT(1)                       ");
        query.append("FROM TAAPPRUSR x, TAEMP y             ");
        query.append("WHERE x.appr_by = y.emp_id            ");
        query.append("   and  x.apprusr_action  != 'C'      "); //[P:ó����� C:ó���Ϸ� Z:���]
        query.append("   and  x.apprusr_action   = 'AP'     "); //AP:����ó���ڸ� üũ
        query.append("   and  x.comp_no = y.comp_no         ");
        query.append("   and  x.comp_no = ?                 ");
        query.append("   and  x.apprlist_id = ?             ");
        query.append("   and  x.appr_seq = ?                ");
        
        Object[] objects = new Object[] {
        		appPrcDetailDTO.getCompNo()
        		,appReqCommonDTO.getApprlistId()
        		,appPrcDetailDTO.getApprSeq()
        };
        
        List resultList = getJdbcTemplate().queryForList(query.toString(), objects);

        return QuerySqlBuffer.listToString(resultList);
	}
	public String nextAppSeq(AppReqCommonDTO appReqCommonDTO, AppPrcDetailDTO appPrcDetailDTO) {

		QuerySqlBuffer query = new QuerySqlBuffer();
        
		query.append("SELECT                                ");
        query.append("       COUNT(1)+1                     ");
        query.append("FROM TAAPPRUSR x, TAEMP y             ");
        query.append("WHERE x.appr_by = y.emp_id            ");
        query.append("   and  x.apprusr_action  != 'C'      "); 
        query.append("   and  x.comp_no = y.comp_no         ");
        query.append("   and  x.comp_no = ?                 ");
        query.append("   and  x.apprlist_id = ?             ");
        
        Object[] objects = new Object[] {
        		appPrcDetailDTO.getCompNo()
        		,appReqCommonDTO.getApprlistId()
        };
        
        List resultList = getJdbcTemplate().queryForList(query.toString(), objects);

        return QuerySqlBuffer.listToString(resultList);
	}
}