package dream.work.let.categ.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.work.let.categ.dao.WorkLetCategDetailDAO;
import dream.work.let.categ.dto.WorkLetCategCommonDTO;
import dream.work.let.categ.dto.WorkLetCategDetailDTO;

/**
 * 안전작업유형 Detail Page - Detail DAO implements
 * @author euna0207
 * @version $Id: WorkLetCategDetailDAOSqlImpl.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
 * @since 1.0
 * 
 * @spring.bean id="workLetCategDetailDAOTarget"
 * @spring.txbn id="workLetCategDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkLetCategDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements WorkLetCategDetailDAO
{
	
    public WorkLetCategDetailDTO findDetail(WorkLetCategCommonDTO workLetCategCommonDTO, User user) 
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
        String compNo = user.getCompNo();
        
        query.append("SELECT 																										");
        query.append(" comp_no    																	  			AS compNo			");
        query.append(", woletctg_no    																	  		AS woletctgNo		");
        query.append(", dbo.SFACODE_TO_DESC(a.woletctg_type,'WOLETCTG_TYPE','SYS','', ?) 						AS woLetCtgTypeDesc	");
        query.append(", woletctg_type   										    							AS woLetCtgTypeId	");
        query.append(", description   										    								AS description		");
        query.append(", dept_id   										    									AS deptId			");
        query.append(", (SELECT description FROM TADEPT WHERE comp_no = a.comp_no AND dept_id = a.dept_id) 		AS deptIdDesc 		");
        query.append(", dbo.SFACODE_TO_DESC(a.is_use, 'IS_USE', 'SYS', '',?) 									AS isUseDesc		");
        query.append(", is_use   										    									AS isUseId			");
        query.append(", (SELECT emp_name FROM TAEMP WHERE comp_no = a.comp_no AND emp_id = a.emp_id)  			AS empIdDesc		");
        query.append(", emp_id																		  			AS empId			");
        query.append(", reg_date                                                                				AS regDate			");
        query.append(", remark                                                                 					AS remark			");
        query.append(", woletctg_id                                                              				AS woLetCtgId		");
        query.append("FROM TAWOLETCTG a																								");
        query.append("WHERE  1=1																									");
    	query.append("AND    woletctg_id = ?																						");
    	query.append("AND    comp_no     = ?																						");
        query.getOrderByQuery("a.woletctg_id", "a.woletctg_id", workLetCategCommonDTO.getOrderBy(), workLetCategCommonDTO.getDirection());
        
        Object[] objects = new Object[] {
        		user.getLangId()
        		,user.getLangId()
        		,workLetCategCommonDTO.getWoLetCtgId()
    			,user.getCompNo()
    	};
        
        WorkLetCategDetailDTO workLetCategDetailDTO = 
        		(WorkLetCategDetailDTO)getJdbcTemplate().query(query.toString(), getObject(objects), new MwareExtractor(new WorkLetCategDetailDTO()));
        
        return workLetCategDetailDTO;
        
    }
    

    public int insertDetail(WorkLetCategDetailDTO workLetCategDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;

    	query.append("INSERT INTO TAWOLETCTG (		");
    	query.append("comp_no						");
    	query.append(", woletctg_no					");
    	query.append(", woletctg_type				");
    	query.append(", description					");
    	query.append(", dept_id						");
    	query.append(", emp_id						");
    	query.append(", is_use						");
    	query.append(", reg_date					");
    	query.append(", remark						");
    	query.append(", woletctg_id )				");
    	query.append("VALUES (						");
    	query.append("?								");
    	query.append(",?							");
    	query.append(",?							");
    	query.append(",?							");
    	query.append(",?							");
    	query.append(",?							");
    	query.append(",?							");
    	query.append(",?							");
    	query.append(",?							");
    	query.append(",?							");
    	query.append(")								");
    	
    	Object[] objects = new Object[] {
    			user.getCompNo()
    			,workLetCategDetailDTO.getWoLetCtgNo()
    			,workLetCategDetailDTO.getWoLetCtgTypeId()
    			,workLetCategDetailDTO.getDescription()
    			,workLetCategDetailDTO.getDeptId()
    			,workLetCategDetailDTO.getEmpId()
    			,workLetCategDetailDTO.getIsUseId()
    			,workLetCategDetailDTO.getRegDate()
    			,workLetCategDetailDTO.getRemark()
    			,workLetCategDetailDTO.getWoLetCtgId()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), getObject(objects));
    	
    	return rtnValue;
    }
    
    
    
    public int updateDetail(WorkLetCategDetailDTO workLetCategDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	int rtnValue  = 0;

    	query.append("UPDATE TAWOLETCTG SET				");
    	query.append("woletctg_type	  = ?				");
    	query.append(", description	  = ?				");
    	query.append(", dept_id		  = ?				");
    	query.append(", emp_id 		  = ?				");
    	query.append(", is_use 		  = ?				");
    	query.append(", reg_date 	  = ?				");
    	query.append(", remark 		  = ?				");
    	query.append("WHERE comp_no   = ?				");
    	query.append("AND woletctg_id = ?				");

    	
    	Object[] objects = new Object[] {
    			workLetCategDetailDTO.getWoLetCtgTypeId()
    			,workLetCategDetailDTO.getDescription()
    			,workLetCategDetailDTO.getDeptId()
    			,workLetCategDetailDTO.getEmpId()
    			,workLetCategDetailDTO.getIsUseId()
    			,workLetCategDetailDTO.getRegDate()
    			,workLetCategDetailDTO.getRemark()
    			,user.getCompNo()
    			,workLetCategDetailDTO.getWoLetCtgId()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), getObject(objects));
    	
    	return rtnValue;
    }
}