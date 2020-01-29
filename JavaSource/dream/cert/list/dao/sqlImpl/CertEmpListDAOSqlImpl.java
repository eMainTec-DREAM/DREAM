package dream.cert.list.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.cert.list.dao.CertEmpListDAO;
import dream.cert.list.dto.CertCommonDTO;
import dream.cert.list.dto.CertEmpListDTO;

/**
 * 작업결과 작업자 목록 dao
 * @author  kim21017
 * @version $Id: CertEmpListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="certEmpListDAOTarget"
 * @spring.txbn id="certEmpListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class CertEmpListDAOSqlImpl extends BaseJdbcDaoSupportSql implements CertEmpListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: CertEmpListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     *
     * @param certCommonDTO
     * @param certEmpListDTO
     * @param loginUser
     * @return List
     */
    public List findEmpList(CertCommonDTO certCommonDTO, CertEmpListDTO certEmpListDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT											          ");
        query.append("     '' seqNo				  					              ");
        query.append("    ,'' isDelCheck								          ");
        query.append("    ,a.empcertlist_id               as empCertListId        ");
        query.append("    ,a.emp_id                       as empId                ");
        query.append("    ,b.emp_no                       as empNo                ");
        query.append("    ,b.emp_name                     as empName              ");
        query.append("    ,a.certlist_id                  as certListId           ");
        query.append("    ,(select aa.description                                 ");
        query.append("      from tadept aa                                        ");
        query.append("      where b.comp_no = aa.comp_no                          ");
        query.append("             and b.dept_id = aa.dept_id) as deptName        ");
        query.append("    ,a.acq_date                          as acqDate         ");
        query.append("    ,a.exp_date                          as expDate         ");
        query.append("    ,a.empcert_status                    as empCertStatus   ");
        query.append("    ,dbo.SFACODE_TO_DESC(a.empcert_status, 'EMPCERT_STATUS', 'SYS', a.comp_no,'"+ loginUser.getCompNo()+ "') as empCertStatusName     ");
        query.append("    ,a.remark                            as remark          ");
        query.append("FROM TAEMPCERTLIST a INNER JOIN TAEMP B                     ");
        query.append("ON a.comp_no = b.comp_no                                    ");
        query.append("AND a.emp_id = b.emp_id                                     ");
        query.append("WHERE 1=1                                                   ");
        query.append(this.getWhere(certCommonDTO,certEmpListDTO,loginUser));
        query.getOrderByQuery("a.empcertlist_id", "b.dept_id, b.emp_no", certEmpListDTO.getOrderBy(), certEmpListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(certEmpListDTO.getIsLoadMaxCount(), certEmpListDTO.getFirstRow()));
    }

    /**
     * delete
     * @author kim21017
     * @version $Id: CertEmpListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param id
     * @param compNo
     * @return
     */
    public int deleteEmpList(String certEmpId, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("DELETE FROM TAEMPCERTLIST						                    ");
    	query.append("WHERE  empcertlist_id 	= '"+certEmpId+"'		                ");
    	query.append("  AND  comp_no		    = '"+loginUser.getCompNo()+"'			");

    	return this.getJdbcTemplate().update(query.toString());
    }

    private String getWhere(CertCommonDTO certCommonDTO, CertEmpListDTO certEmpListDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
    	query.getAndQuery("a.certlist_id", certCommonDTO.getCertListId());
    	query.getAndQuery("a.comp_no", loginUser.getCompNo());
    	if (!"".equals(certEmpListDTO.getEmpCertListId()))
        {
            query.getAndQuery("a.empcertlist_id", certEmpListDTO.getEmpCertListId());
            return query.toString();
        }

    	return query.toString();
    }

    @Override
    public String findTotalCount(CertCommonDTO certCommonDTO, CertEmpListDTO certEmpListDTO, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                      ");
        query.append("       COUNT(1)                                             ");
        query.append("FROM TAEMPCERTLIST a INNER JOIN TAEMP B                     ");
        query.append("ON a.comp_no = b.comp_no                                    ");
        query.append("AND a.emp_id = b.emp_id                                     ");
        query.append("WHERE 1=1                                                   ");
        query.append(this.getWhere(certCommonDTO,certEmpListDTO,user));
        
        List resultList = getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
}