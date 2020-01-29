package dream.org.emp.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.org.emp.dao.MaEmpDetailDAO;
import dream.org.emp.dto.MaEmpDetailDTO;

/**
 * 사원 - 상세 dao
 * 
 * @author 
 * @version $Id:  $
 * @since 1.0
 * @spring.bean id="maEmpDetailDAOTarget"
 * @spring.txbn id="maEmpDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaEmpDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaEmpDetailDAO
{
    /**
     * detail insert
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maEmpDetailDTO
     * @return
     */
    public int insertDetail(MaEmpDetailDTO maEmpDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAEMP(					                    ");
    	query.append("   comp_no,   emp_id,     emp_no,      dept_id,	        ");
    	query.append("	 emp_name,  grade,		m_phone,     e_mail,            ");
    	query.append("	 join_date, retire_date,is_join,	 plant,             ");
    	query.append("	 wkctr_id,	is_mail_rec, is_direct,  vendor_id		    ");
    	query.append(")VALUES(							                        ");
    	query.append("	 ?,			?,			?,	         ?,                 ");
    	query.append("	 ?,			?,			?,	         ?,                 ");
    	query.append("	 ?,			?,           ?,          ?,                 ");
    	query.append("	 ?,			?,			?,			 ?	                ");
    	query.append(")													        ");
    	
    	Object[] objects = new Object[] {
    			maEmpDetailDTO.getCompNo(),
    			maEmpDetailDTO.getEmpId(),
    			maEmpDetailDTO.getEmpNo(),
    			maEmpDetailDTO.getDeptId(),
    			maEmpDetailDTO.getEmpName(),
    			maEmpDetailDTO.getGrade(),
    			maEmpDetailDTO.getMphone(),
    			maEmpDetailDTO.getEmail(),
    			maEmpDetailDTO.getJoinDate(),
    			maEmpDetailDTO.getRetireDate(),
    			maEmpDetailDTO.getIsJoin(),
    			maEmpDetailDTO.getPlantId(),
    			maEmpDetailDTO.getWkCtrId(),
    			maEmpDetailDTO.getIsMailRec(),
    			maEmpDetailDTO.getIsDirect(),
    			maEmpDetailDTO.getVendorId()
    	};
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail update
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maEmpDetailDTO
     * @return
     */
    public int updateDetail(MaEmpDetailDTO maEmpDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAEMP SET	        ");
    	query.append("	     dept_id	  = ?,	");
    	query.append("	     emp_name	  = ?,	");
    	query.append("	     grade	      = ?,	");
    	query.append("	     m_phone	  = ?,	");
    	query.append("	     e_mail	      = ?,	");
    	query.append("	     join_date	  = ?,	");
    	query.append("	     retire_date  = ?,	");
    	query.append("	     plant   	  = ?,  ");
    	query.append("	     is_join	  = ?,  ");
    	query.append("	     is_mail_rec  = ?,  ");
    	query.append("	     wkctr_id	  = ?,  ");
    	query.append("	     is_direct	  = ?, 	");
    	query.append("	     vendor_id	  = ? 	");
    	query.append("WHERE  comp_no      = ?	");
    	query.append("  AND  emp_id       = ?	");
    	
    	Object[] objects = new Object[] {
    			maEmpDetailDTO.getDeptId(),
    			maEmpDetailDTO.getEmpName(),
    			maEmpDetailDTO.getGrade(),
    			maEmpDetailDTO.getMphone(),
    			maEmpDetailDTO.getEmail(),
    			maEmpDetailDTO.getJoinDate(),
    			maEmpDetailDTO.getRetireDate(),
    			maEmpDetailDTO.getPlantId(),
    			maEmpDetailDTO.getIsJoin(),
    			maEmpDetailDTO.getIsMailRec(),
    			maEmpDetailDTO.getWkCtrId(),
    			maEmpDetailDTO.getIsDirect(),
    			maEmpDetailDTO.getVendorId(),
    			maEmpDetailDTO.getCompNo(),
    			maEmpDetailDTO.getEmpId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    /**
     * valid empId
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maEmpDetailDTO
     * @return
     */
    public String validEmpNo(String empId, String empNo, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT COUNT(*)                        ");
        query.append("FROM   TAEMP x                         ");
        query.append("WHERE  x.comp_no = '" + user.getCompNo() + "'");
        query.getInequalityQuery("x.emp_id", "!=", empId );
        query.append("  AND  UPPER(x.emp_no)  = UPPER('" + empNo + "')");
     
        return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    
    }
	@Override
	public int updateUserStatus(MaEmpDetailDTO maEmpDetailDTO, User user) {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAUSER					");
    	query.append("SET 	 is_use = ?				");
    	query.append("    	,is_monitor = ?			");
    	query.append("WHERE comp_no = ?				");
    	query.append("    and emp_id = ?			");

    	
    	Object[] objects = new Object[] {
    			"N"
    			,"N"
    			,user.getCompNo()
    			,maEmpDetailDTO.getEmpId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
}