package dream.pers.appstb.prc.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.pers.appreq.dto.AppReqCommonDTO;
import dream.pers.appstb.prc.dao.AppPrcListDAO;

/**
 * 예방점검 - 목록
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
     * Filter 조건
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
            
            //결재이력에는 전체를 볼수 있게 처리.
            if (!"Y".equals(appReqCommonDTO.getIsAppHist()))
            {
                query.append(" AND  x.apprusr_action  != 'C'                              "); //[P:처리대상 C:처리완료 Z:대기]
            }
        }
                
/*        // CommonDTO의 appDocNo 있는경우는 상세에서 수정 되어서 List의 한 Row만을 재조회
        if (!"".equals(appReqCommonDTO.getAppFlowNo()))
        {
            query.getAndQuery("y.app_flow_no", appReqCommonDTO.getAppFlowNo());
            return query.toString();
        }
        
        query.getAndQuery("x.app_doc_no", appReqCommonDTO.getFilterAppDocNo());  // 결재번호
        query.getAndQuery("x.title",      appReqCommonDTO.getTitle());        // 제목
        query.getAndQuery("x.app_status", appReqCommonDTO.getAppStatus());             // 결재상태
*/        
       /* query.getAndDateQuery("y.app_date", appPrcCommonDTO.getAppDateFrom(), appPrcCommonDTO.getAppDateTo());  // 결재일자
        query.getAndDateQuery("x.enter_date", appPrcCommonDTO.getEnterDateFrom(), appPrcCommonDTO.getEnterDateTo());  // 기안일자
        
        query.getAndQuery("x.wf_type", appPrcCommonDTO.getWfType());             // 결재종류
        
        String objectNo = appPrcCommonDTO.getObjectNo();
        if (objectNo != null && !"".equals(objectNo))
        {
            query.append("  AND EXISTS (SELECT 1                        ");
            query.append("              FROM   T2APP_DOC_DTL a          ");
            query.append("              WHERE  a.app_doc_no = x.app_doc_no      ");
            query.getAndQuery("a.object_no", objectNo);
            query.append("              )                               ");
        }
        
        // 승인- WF_ACTION : 처리완료[C], WF_STATUS : 승인[C]
        String wfStatus = appPrcCommonDTO.getWfStatus();
        if ("C".equals(wfStatus))
        {
            query.append("  AND  y.wf_action = 'C'          ");
            query.append("  AND  y.wf_status = 'C'          ");
            
            query.getAndQuery("y.autho_by", appPrcCommonDTO.getEnterBy());   // 결재자(처리자)
        }
        // 반려 - WF_ACTION : 처리완료[C], WF_STATUS : 반려[D]
        else if ("D".equals(wfStatus))
        {
            query.append("  AND  y.wf_action = 'C'          ");
            query.append("  AND  y.wf_status = 'D'          ");
            
            query.getAndQuery("y.autho_by", appPrcCommonDTO.getEnterBy());   // 결재자(처리자)
        }
        // 미승인 - WF_ACTION : 처리대상[P],  APP_TYPE : 합의[GR]/결재[AP]
        else
        {
            query.append("  AND  y.wf_action = 'P'              ");
            query.append("  AND  y.app_type IN ('GR', 'AP')     ");
            
            query.getAndQuery("y.user_id", appPrcCommonDTO.getEnterBy());   // 결재자(대상자)
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