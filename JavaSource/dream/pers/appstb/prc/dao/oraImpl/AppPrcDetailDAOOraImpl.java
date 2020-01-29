package dream.pers.appstb.prc.dao.oraImpl;

import java.util.List;

import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.pers.appreq.dto.AppReqCommonDTO;
import dream.pers.appstb.prc.dao.AppPrcDetailDAO;
import dream.pers.appstb.prc.dto.AppPrcDetailDTO;

/**
 * 결재요청(결재문서작성)
 * @author javaworker
 * @version $Id: AppPrcDetailDAO.java,v 1.1 2013/08/30 09:12:11 javaworker Exp $
 * @since 1.0
 * @spring.bean id="appPrcDetailDAOTarget"
 * @spring.txbn id="appPrcDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AppPrcDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements AppPrcDetailDAO
{
    /**
     * 결재문서 상세조회
     * @author  javaworker
     * @version $Id: AppPrcDetailDAO.java,v 1.1 2013/08/30 09:12:11 javaworker Exp $
     * @since   1.0
     * 
     * @param appReqCommonDTO
     * @return
     */
    public AppPrcDetailDTO findDetail(AppReqCommonDTO appReqCommonDTO)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                            ");
        query.append("       x.appr_seq apprSeq,                          ");
        query.append("       x.appr_by apprBy,                          ");
        query.append("       y.emp_name apprByName,                     ");
        query.append("       y.grade,                                   ");
        query.append("       x.apprusr_type  apprUsrTypeId,               ");
        query.append("       SFACODE_TO_DESC(x.apprusr_type,'APPRUSR_TYPE','SYS','','"+appReqCommonDTO.getUserLang()+"') apprUsrTypeDesc ,          ");
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

        return (AppPrcDetailDTO)getJdbcTemplate().query(query.toString(), objects,
                new MwareExtractor(new AppPrcDetailDTO()));
    }

    public void updateDetail(AppPrcDetailDTO appPrcDetailDTO)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("UPDATE TAAPPRUSR x                                      ");
        query.append("SET    x.appr_seq     = ?                               ");
        query.append("       ,x.appr_by     = ?                               ");
        query.append("       ,x.apprusr_type     = ?                          ");
        query.append("WHERE  x.apprusr_id   = ?                               ");

        Object[] objects = new Object[]{
                appPrcDetailDTO.getApprSeq(),
                appPrcDetailDTO.getApprBy(),
                appPrcDetailDTO.getApprUsrTypeId(),
                appPrcDetailDTO.getApprusrId()
        };

        this.getJdbcTemplate().update(query.toString(), objects);
    }

    public void insertDetail(AppPrcDetailDTO appPrcDetailDTO)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("INSERT INTO TAAPPRUSR                               ");
        query.append("  (comp_no,       apprusr_id,     apprlist_id,      ");
        query.append("   appr_seq,      appr_by,        apprusr_action,   ");
        query.append("   apprusr_status ,apprusr_type                     ");
        query.append("  )   VALUES                                  ");
        query.append("  (?,             ?,              ?,          ");
        query.append("   ?,             ?,              ?,          ");
        query.append("   ?,             ?                           ");
        query.append("  )                                           ");
        
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
        
        this.getJdbcTemplate().update(query.toString(), objects);
    }

	public String checkSeqNum(AppReqCommonDTO appReqCommonDTO, AppPrcDetailDTO appPrcDetailDTO) {

		QueryBuffer query = new QueryBuffer();
        
		query.append("SELECT                                ");
        query.append("       COUNT(1)                       ");
        query.append("FROM TAAPPRUSR x, TAEMP y             ");
        query.append("WHERE x.appr_by = y.emp_id            ");
        query.append("   and  x.apprusr_action  != 'C'      "); //[P:처리대상 C:처리완료 Z:대기]
        query.append("   and  x.apprusr_action   = 'AP'     "); //AP:승인처리자만 체크
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

        return QueryBuffer.listToString(resultList);
	}
	
	public String nextAppSeq(AppReqCommonDTO appReqCommonDTO, AppPrcDetailDTO appPrcDetailDTO) {

		QueryBuffer query = new QueryBuffer();
        
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

        return QueryBuffer.listToString(resultList);
	}
}