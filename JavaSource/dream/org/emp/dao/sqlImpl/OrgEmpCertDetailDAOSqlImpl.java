package dream.org.emp.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.org.emp.dao.OrgEmpCertDetailDAO;
import dream.org.emp.dto.MaEmpCommonDTO;
import dream.org.emp.dto.OrgEmpCertDetailDTO;
import dream.org.emp.dto.OrgEmpCertListDTO;

/**
 * 구매신청 item 상세 dao
 * @author  kim21017
 * @version $Id: PartAdjStkTakeDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="orgEmpCertDetailDAOTarget"
 * @spring.txbn id="orgEmpCertDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class OrgEmpCertDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements OrgEmpCertDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: PartAdjStkTakeDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param orgEmpCertListDTO
     * @param maEmpCommonDTO
     * @return
     */
    public OrgEmpCertDetailDTO findDetail(OrgEmpCertListDTO orgEmpCertListDTO, MaEmpCommonDTO maEmpCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        String compNo = maEmpCommonDTO.getCompNo();
        
        query.append("SELECT 		");
        query.append("       x.empcertlist_id empCertListId,		");
        query.append("       x.emp_id empId,		");
        query.append("       x.certlist_id certListId,		");
        query.append("       y.cert_name certName,		");
        query.append("       y.cert_no certNo,		");
        query.append("       y.cert_type certType,		");
        query.append("		 dbo.SFACODE_TO_DESC(y.cert_type,'CERT_TYPE','USR','"+user.getCompNo()+"','"+user.getLangId()+"') 			certTypeDesc,	");
        query.append("       x.acq_date acqDate,		");
        query.append("       x.exp_date as expDate,		");      
        query.append("       x.empcert_status empCertStatus,		");
        query.append("		 dbo.SFACODE_TO_DESC(x.empcert_status,'EMPCERT_STATUS','SYS','','"+user.getLangId()+"') 			empCertStatusDesc,	");
        query.append("       x.remark remark                               		");
        query.append("FROM TAEMPCERTLIST x, TACERTLIST y		");
        query.append("WHERE x.certlist_id = y.certlist_id		");
        query.append("AND x.comp_no = '"+compNo+"'						");
        query.getAndQuery("x.empcertlist_id", orgEmpCertListDTO.getEmpCertListId());
    
        OrgEmpCertDetailDTO orgEmpCertDetailDTO1 = 
        		(OrgEmpCertDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new OrgEmpCertDetailDTO()));
        
        return orgEmpCertDetailDTO1;
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: PartAdjStkTakeDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param orgEmpCertDetailDTO
     * @param maEmpCommonDTO
     * @return
     */
    public int updateDetail(OrgEmpCertDetailDTO orgEmpCertDetailDTO, MaEmpCommonDTO maEmpCommonDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAEMPCERTLIST SET	    ");
    	query.append("	certlist_id					= ?	,	");
    	query.append("	acq_date					= ?	,	");
    	query.append("	exp_date					= ?	,	");
    	query.append("	empcert_status					= ?	,	");
    	query.append("	remark					= ?		");
    	query.append("WHERE empcertlist_id 		= ?		");
    	query.append("  AND comp_no				= ? 	");
    	
    	Object[] objects = new Object[] {
    			orgEmpCertDetailDTO.getCertListId(),
    			orgEmpCertDetailDTO.getAcqDate(),
    			orgEmpCertDetailDTO.getExpDate(),
    			orgEmpCertDetailDTO.getEmpCertStatus(),
    			orgEmpCertDetailDTO.getRemark(),
    			orgEmpCertDetailDTO.getEmpCertListId(),
    			maEmpCommonDTO.getCompNo()    			
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: PartAdjStkTakeDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param orgEmpCertDetailDTO
     * @param maEmpCommonDTO
     * @return
     */
    public int insertDetail(OrgEmpCertDetailDTO orgEmpCertDetailDTO, MaEmpCommonDTO maEmpCommonDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;
    	
    	query.append("INSERT INTO TAEMPCERTLIST (								");
    	query.append("	comp_no,		    empcertlist_id,	    emp_id,		");
    	query.append("	certlist_id,	    acq_date,		    exp_date,		");
    	query.append("	empcert_status,     remark   		");
    	query.append("	)	VALUES				(							");
    	query.append("	?,				?,					?,				");
    	query.append("	?,				?,					?,				");
    	query.append("	?,				?									");
    	query.append("	)													");
    	
    	Object[] objects = new Object[] {
    			maEmpCommonDTO.getCompNo(),
    			orgEmpCertDetailDTO.getEmpCertListId(),
    			maEmpCommonDTO.getEmpId(),
    			orgEmpCertDetailDTO.getCertListId(),
    			orgEmpCertDetailDTO.getAcqDate(),
    			orgEmpCertDetailDTO.getExpDate(),
    			orgEmpCertDetailDTO.getEmpCertStatus(),
    			orgEmpCertDetailDTO.getRemark()
    			
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }

    
    
}