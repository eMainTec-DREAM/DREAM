package dream.pers.appln.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;

import dream.pers.appln.dao.MaAppLineDetailDAO;
import dream.pers.appln.dto.MaAppLineCommonDTO;
import dream.pers.appln.dto.MaAppLineDetailDTO;

/**
 * »ó¼¼ dao
 * 
 * @author kim21017
 * @version $Id: MaAppLineDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="maAppLineDetailDAOTarget"
 * @spring.txbn id="maAppLineDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaAppLineDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaAppLineDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaAppLineDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maAppLineCommonDTO
     * @return
     */
    public MaAppLineDetailDTO findDetail(MaAppLineCommonDTO maAppLineCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                ");
        query.append("        comp_no compNo,               ");
        query.append("        apprline_id apprlineId,       ");
        query.append("        emp_id empId,                 ");
        query.append("        title,                        ");
        query.append("        REMARK                        ");
        query.append("FROM TAAPPRLINE                       ");
        query.append("WHERE  1 = 1 	                        ");
        query.append("   and apprline_id = ?                ");
        query.append("   and comp_no = ?                    ");
        
        Object[] objects = new Object[] {
        		maAppLineCommonDTO.getApprlineId()
    			,user.getCompNo()
    	};

        MaAppLineDetailDTO maAppLineDetailDTO = 
        		(MaAppLineDetailDTO)getJdbcTemplate().query(query.toString(),objects,  new MwareExtractor(new MaAppLineDetailDTO()));
        
        return maAppLineDetailDTO;
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaAppLineDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maAppLineDetailDTO
     * @return
     */
    public int insertDetail(MaAppLineDetailDTO maAppLineDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAAPPRLINE							");
    	query.append("	(comp_no,		apprline_id,	emp_id,	        ");
    	query.append("	 title,		    remark			                ");
    	query.append("	)	VALUES										");
    	query.append("	(?,				?,				?,				");
    	query.append("	 ?,				?								");
    	query.append("	)												");
    	
    	Object[] objects = new Object[] {
    			user.getCompNo(),
    			maAppLineDetailDTO.getApprlineId(),
    			user.getEmpId(),
    			maAppLineDetailDTO.getTitle(),
    			maAppLineDetailDTO.getRemark()
    	};
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaAppLineDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maAppLineDetailDTO
     * @return
     */
    public int updateDetail(MaAppLineDetailDTO maAppLineDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAAPPRLINE SET				");
    	query.append("	title					= ?,	");
    	query.append("	remark			    	= ?		");
    	query.append("WHERE apprline_id			= ?		");
    	query.append("AND comp_no             	= ?     ");
    	
    	Object[] objects = new Object[] {
    	        maAppLineDetailDTO.getTitle(),
    	        maAppLineDetailDTO.getRemark(),
    	        maAppLineDetailDTO.getApprlineId(),
    	        user.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    /**
     * detail confirm
     * @author kim21017
     * @version $Id: MaAppLineDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maAppLineDetailDTO
     * @return
     */
    public int confirmDetail(MaAppLineDetailDTO maAppLineDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("");
    	
    	Object[] objects = new Object[] {
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
}