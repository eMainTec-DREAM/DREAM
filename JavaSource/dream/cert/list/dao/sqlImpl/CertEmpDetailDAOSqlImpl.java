package dream.cert.list.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.cert.list.dao.CertEmpDetailDAO;
import dream.cert.list.dto.CertCommonDTO;
import dream.cert.list.dto.CertEmpDetailDTO;

/**
 * 작업결과-작업자 상세 dao
 * @author  kim21017
 * @version $Id: CertEmpDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="certEmpDetailDAOTarget"
 * @spring.txbn id="certEmpDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class CertEmpDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements CertEmpDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: CertEmpDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param wkOrId
     * @param certEmpId
     * @param compNo
     * @return
     */
    public CertEmpDetailDTO findDetail(String empCertListId, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("SELECT											          ");
        query.append("     a.empcertlist_id               as empCertListId        ");
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
        query.append("    ,a.empcert_status                    as empCertStatusCode   ");
        query.append("    ,dbo.SFACODE_TO_DESC(a.empcert_status, 'EMPCERT_STATUS', 'SYS', a.comp_no,'"+ loginUser.getCompNo()+ "') as empCertStatusName     ");
        query.append("    ,a.remark                            as remark          ");
        query.append("from TAEMPCERTLIST a, TAEMP B                               ");
        query.append("where a.comp_no = b.comp_no                                 ");
        query.append("    and a.emp_id =b.emp_id                                  ");
        query.append("    and a.comp_no = '"+loginUser.getCompNo()+"'  ");
        query.append("    and a.empcertlist_id = "+empCertListId+" ");

        CertEmpDetailDTO certEmpDetailDTO =
        		(CertEmpDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new CertEmpDetailDTO()));

        return certEmpDetailDTO;
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: CertEmpDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param certEmpDetailDTO
     * @param certCommonDTO
     * @return
     */
    public int updateDetail(CertEmpDetailDTO certEmpDetailDTO, CertCommonDTO certCommonDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAEMPCERTLIST SET	    	");
    	query.append("	 emp_id					= ?	    ");
    	query.append("	,acq_date				= ?	    ");
    	query.append("	,exp_date				= ?	    ");
    	query.append("	,empcert_status			= ?	    ");
    	query.append("	,remark					= ?		");
    	query.append("WHERE empcertlist_id		= ?		");
    	query.append("  AND comp_no			    = ?		");

    	Object[] objects = new Object[] {
    			certEmpDetailDTO.getEmpId()
    			,certEmpDetailDTO.getAcqDate()
    			,certEmpDetailDTO.getExpDate()
    			,certEmpDetailDTO.getEmpCertStatusCode()
    			,certEmpDetailDTO.getRemark()
    			,certEmpDetailDTO.getEmpCertListId()
    			,loginUser.getCompNo()
    	};

    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }

    /**
     * detail insert
     * @author kim21017
     * @version $Id: CertEmpDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param certEmpDetailDTO
     * @param certCommonDTO
     * @return
     */
    public int insertDetail(CertEmpDetailDTO certEmpDetailDTO, CertCommonDTO certCommonDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAEMPCERTLIST					");
    	query.append("	(comp_no       ,empcertlist_id			");
    	query.append("	 ,emp_id       ,certlist_id 			");
    	query.append("	 ,acq_date     ,exp_date    			");
    	query.append("	 ,empcert_status ,remark    			");
    	query.append("	)	VALUES							");
    	query.append("	(?				,?					");
    	query.append("	 ,?				,?					");
    	query.append("	 ,?				,?					");
    	query.append("	 ,?				,?					");
    	query.append("	)									");

    	Object[] objects = new Object[] {
    			loginUser.getCompNo()
    			,certEmpDetailDTO.getEmpCertListId()
    			,certEmpDetailDTO.getEmpId()
    			,certCommonDTO.getCertListId()
    			,certEmpDetailDTO.getAcqDate()
    			,certEmpDetailDTO.getExpDate()
    			,certEmpDetailDTO.getEmpCertStatusCode()
    			,certEmpDetailDTO.getRemark()
    	};
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    /**
     * 재고확인
     */
    public String validEmp(CertEmpDetailDTO certEmpDetailDTO, CertCommonDTO certCommonDTO, User loginUser){
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	query.append("SELECT count(*) 			");
    	query.append("FROM TAEMPCERTLIST			");
    	query.append("WHERE 1=1					");
    	query.getAndQuery("certlist_id", certCommonDTO.getCertListId());
    	query.getAndQuery("comp_no", loginUser.getCompNo());
    	query.getAndQuery("emp_id", certEmpDetailDTO.getEmpId());
    	return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
}