package dream.cert.rpt.emplist.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;

import dream.cert.rpt.emplist.dao.CertRptEmpListDAO;
import dream.cert.rpt.emplist.dto.CertRptEmpCommonDTO;

/**
 * 자격증분류 - 목록 dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="certRptEmpListDAOTarget"
 * @spring.txbn id="certRptEmpListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class CertRptEmpListDAOOraImpl extends BaseJdbcDaoSupportOra implements CertRptEmpListDAO
{
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     *
     * @param certRptEmpCommonDTO
     * @return List
     */
    public List findList(CertRptEmpCommonDTO certRptEmpCommonDTO    ,User user)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                                       ");
        query.append("      ''                                  as seqNo           ");
        query.append("     ,c.cert_no                           as certNo          ");
        query.append("     ,c.cert_name                         as certName        ");
        query.append("     ,c.cert_agency                       as certAgency      ");
        query.append("    ,b.emp_no                             as empNo           ");
        query.append("    ,b.emp_name                           as empName         ");
        query.append("    ,(select aa.description                                  ");
        query.append("      from tadept aa                                         ");
        query.append("      where b.comp_no = aa.comp_no                           ");
        query.append("             and b.dept_id = aa.dept_id) as deptName         ");
        query.append("    ,a.acq_date                          as acqDate          ");
        query.append("    ,a.exp_date                          as expDate          ");
        query.append("    ,SFACODE_TO_DESC(a.empcert_status, 'EMPCERT_STATUS', 'SYS', a.comp_no,'100') as empCertStatusName      ");
        query.append("    ,a.remark                                                                    as remark                 ");
        query.append("    ,a.empcertlist_id                                                            as empCertListId          ");
        query.append("from TAEMPCERTLIST a inner join TAEMP B on a.comp_no = b.comp_no and a.emp_id = b.emp_id                                    ");
        query.append("                                    inner join TACERTLIST c on a.comp_no = b.comp_no and a.certlist_id = c.certlist_id      ");
        query.append("where 1=1            ");
        query.append(this.getWhere(certRptEmpCommonDTO, user));
        query.append("ORDER BY c.cert_no                                   ");

        return getJdbcTemplate().queryForList(query.toString());
    }


    /**
     * Filter 조건
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     *
     * @param certRptEmpCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(CertRptEmpCommonDTO certRptEmpCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();


        //상태
        query.getCodeLikeQuery("c.cert_type", "SFACODE_TO_DESC(c.cert_type, 'CERT_TYPE', 'USR', a.comp_no,'"+user.getLangId()+"')", 
                                         certRptEmpCommonDTO.getFilterCertType(), certRptEmpCommonDTO.getFilterCertTypeDesc());

        query.getLikeQuery("c.cert_name", certRptEmpCommonDTO.getFilterCertName());
        return query.toString();
    }
}