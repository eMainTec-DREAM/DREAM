package dream.consult.comp.emp.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.consult.comp.emp.dao.ConsultCompEmpDetailDAO;
import dream.consult.comp.emp.dto.ConsultCompEmpCommonDTO;
import dream.consult.comp.emp.dto.ConsultCompEmpDetailDTO;

/**
 * 사원 - 상세 dao
 * 
 * @author 
 * @version $Id:  $
 * @since 1.0
 * @spring.bean id="consultCompEmpDetailDAOTarget"
 * @spring.txbn id="consultCompEmpDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ConsultCompEmpDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements ConsultCompEmpDetailDAO
{
    /**
     * detail find
     * @author 
     * @version $Id: $
     * @since   1.0 
     * 
     * @param empId
     * @return
     */
    public ConsultCompEmpDetailDTO findDetail(User user, ConsultCompEmpCommonDTO consultCompEmpCommonDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT        ");
        query.append("    x.comp_no                                                                                           compNo     ");
        query.append("    ,(SELECT a.description FROM TACOMP a WHERE a.comp_no=x.comp_no)         compDesc  ");
        query.append("    ,x.emp_id                                                                                             empId       ");
        query.append("    ,x.emp_no                                                                                            empNo       ");
        query.append("    ,x.emp_name                                                                                        empName   ");
        query.append("    ,x.dept_id                                                                                             deptId      ");
        query.append("    ,dbo.SFAIDTODESC(x.dept_id, '', 'DEPT', x.comp_no)                                         deptDesc     ");
        query.append("    ,x.m_phone                                                                                          mphone       ");
        query.append("    ,x.grade                                                                                                grade       ");
        query.append("    ,x.join_date                                                                                           joinDate     ");
        query.append("    ,x.retire_date                                                                                        retireDate   ");
        query.append("    ,x.e_mail                                                                                               email        ");
        query.append("    ,x.is_join                                                                                               isJoin         ");
        query.append("    ,dbo.SFACODE_TO_DESC(x.is_join, 'IS_JOIN', 'SYS', x.comp_no,'"+user.getLangId()+"') isJoinDesc");
        query.append("    ,x.plant                                                                                                 plantId      ");
        query.append("    ,dbo.SFAPLANTTODESC(x.comp_no, x.plant)                                                      plantDesc  ");
        query.append("FROM TAEMP x      ");
        query.append("WHERE 1=1 ");
        query.append("AND  x.comp_no = '"+consultCompEmpCommonDTO.getCompNo()+"' ");
        query.append("  AND  x.emp_id  = '"+consultCompEmpCommonDTO.getEmpId()+"'    ");
    
        ConsultCompEmpDetailDTO consultCompEmpDetailDTO = 
        		(ConsultCompEmpDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new ConsultCompEmpDetailDTO()));
        
        return consultCompEmpDetailDTO;
    }
    /**
     * detail insert
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param consultCompEmpDetailDTO
     * @return
     */
    public int insertDetail(ConsultCompEmpDetailDTO consultCompEmpDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAEMP(					                    ");
    	query.append("   comp_no,   emp_id,     emp_no,      dept_id,	        ");
    	query.append("	 emp_name,  grade,		m_phone,     e_mail,            ");
    	query.append("	 join_date, retire_date,is_join,	 plant,             ");
    	query.append("	 wkctr_id									            ");
    	query.append(")VALUES(							                        ");
    	query.append("	 ?,			?,			?,	         ?,                 ");
    	query.append("	 ?,			?,			?,	         ?,                 ");
    	query.append("	 ?,			?,           ?,          ?,                 ");
    	query.append("	 ?										                ");
    	query.append(")													        ");
    	
    	Object[] objects = new Object[] {
    			consultCompEmpDetailDTO.getCompNo(),
    			consultCompEmpDetailDTO.getEmpId(),
    			consultCompEmpDetailDTO.getEmpNo(),
    			consultCompEmpDetailDTO.getDeptId(),
    			consultCompEmpDetailDTO.getEmpName(),
    			consultCompEmpDetailDTO.getGrade(),
    			consultCompEmpDetailDTO.getMphone(),
    			consultCompEmpDetailDTO.getEmail(),
    			consultCompEmpDetailDTO.getJoinDate(),
    			consultCompEmpDetailDTO.getRetireDate(),
    			consultCompEmpDetailDTO.getIsJoin(),
    			consultCompEmpDetailDTO.getPlantId(),
    			consultCompEmpDetailDTO.getWkCtrId()
    	};
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail update
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param consultCompEmpDetailDTO
     * @return
     */
    public int updateDetail(ConsultCompEmpDetailDTO consultCompEmpDetailDTO)
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
    	query.append("	     wkctr_id	  = ?   ");
    	query.append("WHERE  comp_no      = ?	");
    	query.append("  AND  emp_id       = ?	");
    	
    	Object[] objects = new Object[] {
    			consultCompEmpDetailDTO.getDeptId(),
    			consultCompEmpDetailDTO.getEmpName(),
    			consultCompEmpDetailDTO.getGrade(),
    			consultCompEmpDetailDTO.getMphone(),
    			consultCompEmpDetailDTO.getEmail(),
    			consultCompEmpDetailDTO.getJoinDate(),
    			consultCompEmpDetailDTO.getRetireDate(),
    			consultCompEmpDetailDTO.getPlantId(),
    			consultCompEmpDetailDTO.getIsJoin(),
    			consultCompEmpDetailDTO.getWkCtrId(),
    			consultCompEmpDetailDTO.getCompNo(),
    			consultCompEmpDetailDTO.getEmpId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    /**
     * valid empId
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param consultCompEmpDetailDTO
     * @return
     */
    public String validEmpNo(ConsultCompEmpDetailDTO consultCompEmpDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT COUNT(*)                        ");
        query.append("FROM   TAEMP x                         ");
        query.append("WHERE  x.comp_no = '" + consultCompEmpDetailDTO.getCompNo() + "'");
        query.append("  AND  UPPER(x.emp_no)  = UPPER('" + consultCompEmpDetailDTO.getEmpNo() + "')");
     
        return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    
    }
}