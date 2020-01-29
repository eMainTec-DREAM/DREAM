package dream.fail.library.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.fail.library.dao.FailLibraryListDAO;
import dream.fail.library.dto.FailLibraryCommonDTO;

/**
 * ����library - ��� dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="failLibraryListDAOTarget"
 * @spring.txbn id="failLibraryListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class FailLibraryListDAOSqlImpl extends BaseJdbcDaoSupportSql implements FailLibraryListDAO
{
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param failLibraryCommonDTO
     * @return List
     */
    public List findList(FailLibraryCommonDTO failLibraryCommonDTO, User user)
    { 
        QuerySqlBuffer query = new QuerySqlBuffer();
        String lang = user.getLangId();
       
        query.append("SELECT 														");
        query.append("       ''                                       seqNo,       	");
        query.append("       ''                                       isDelCheck,  	");
        query.append("       x.comp_no                                compNo,      	");
        query.append("       x.failsetlist_id                         failsetlistId,");
        query.append("       x.eqctg_no                               eqctgNo,		");
        query.append("       (SELECT a.description FROM TAEQCTG a   WHERE a.eqctg_id = x.eqctg_id AND a.comp_no = x.comp_no) eqctgDesc,			");
        query.append("       x.failure_pt_no failurePtNo,							");
        query.append("       (SELECT a.description FROM TAFAILURE a WHERE a.fail_type ='PT' AND a.failure_id = x.failure_pt_id) failurePtDesc,	");
        query.append("       x.failure_mo_no failureMoNo,							");
        query.append("       (SELECT a.description FROM TAFAILURE a WHERE a.fail_type ='MO' AND a.failure_id = x.failure_mo_id) failureMoDesc,	");
        query.append("       x.failure_ca_no failureCaNo,							");
        query.append("       (SELECT a.description FROM TAFAILURE a WHERE a.fail_type ='CA' AND a.failure_id = x.failure_ca_id) failureCaDesc,	");
        query.append("       x.failure_re_no failureReNo,							");
        query.append("       (SELECT a.description FROM TAFAILURE a WHERE a.fail_type ='RE' AND a.failure_id = x.failure_re_id) failureReDesc,	");
        query.append("       x.failure_pm_no failurePmNo,							");
        query.append("       (SELECT a.description FROM TAFAILURE a WHERE a.fail_type ='PM' AND a.failure_id = x.failure_pm_id) failurePmDesc,	");
        query.append("       is_use isUse											");
        query.append("FROM   tafailsetlist x                                       	");
    	query.append("WHERE  x.comp_no = '"+failLibraryCommonDTO.getCompNo()+"'");
        query.append(this.getWhere(failLibraryCommonDTO,user));
    	query.append("ORDER  BY x.failsetlist_id DESC                               ");
        
        return getJdbcTemplate().queryForList(query.toString());
    } 
    
    /**
     * Filter ����
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param failLibraryCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(FailLibraryCommonDTO failLibraryCommonDTO, User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        String lang = user.getLangId();
        
     // CommonDTO�� equipNo�� �ִ°��� �󼼿��� ���� �Ǿ List�� �� Row���� ����ȸ
        if (!"".equals(failLibraryCommonDTO.getFailsetlistId()))
        {
            query.getAndQuery("x.failsetlist_id", failLibraryCommonDTO.getFailsetlistId());
            return query.toString();
        }
        //����
        query.getEqCtgLevelQuery("x.eqctg_id", failLibraryCommonDTO.getEqctgId(), failLibraryCommonDTO.getEqctgDesc(), user.getCompNo());
        //����
        query.getCodeLikeQuery("failure_pt_id", "(SELECT description FROM TAFAILURE a WHERE a.fail_type ='PT' AND a.failure_id = x.failure_pt_id)", failLibraryCommonDTO.getFailurePtId(), failLibraryCommonDTO.getFailurePtDesc());
        //����
        query.getCodeLikeQuery("failure_ca_id", "(SELECT description FROM TAFAILURE a WHERE a.fail_type ='CA' AND a.failure_id = x.failure_ca_id)", failLibraryCommonDTO.getFailureCaId(), failLibraryCommonDTO.getFailureCaDesc());
        //����
        query.getCodeLikeQuery("failure_mo_id", "(SELECT description FROM TAFAILURE a WHERE a.fail_type ='MO' AND a.failure_id = x.failure_mo_id)", failLibraryCommonDTO.getFailureMoId(), failLibraryCommonDTO.getFailureMoDesc());
        //��ġ
        query.getCodeLikeQuery("failure_re_id", "(SELECT description FROM TAFAILURE a WHERE a.fail_type ='RE' AND a.failure_id = x.failure_re_id)", failLibraryCommonDTO.getFailureReId(), failLibraryCommonDTO.getFailureReDesc());
        //�������
        query.getCodeLikeQuery("failure_pm_id", "(SELECT description FROM TAFAILURE a WHERE a.fail_type ='PM' AND a.failure_id = x.failure_pm_id)", failLibraryCommonDTO.getFailurePmId(), failLibraryCommonDTO.getFailurePmDesc());
        //�ۼ�����
        query.getAndDateQuery("cre_date", failLibraryCommonDTO.getCreDateFrom(), failLibraryCommonDTO.getCreDateTo());
        //Ȯ�ο���
        query.getLikeQuery("check_yn", failLibraryCommonDTO.getCheckYn());
        
        return query.toString();
    }

    /**
     * ����
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param failureId
     * @return
     */
    public int deleteParts(String compNo, String failureId)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("DELETE TAFAILURE                                          ");
        query.append("WHERE  comp_no   = ?                                      ");
        query.append("  AND  failure_id = ?                                     ");      
        
        Object[] objects = new Object[] {   
                compNo,
                failureId
                };
        
        return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }
}