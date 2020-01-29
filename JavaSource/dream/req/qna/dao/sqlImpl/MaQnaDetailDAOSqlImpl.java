package dream.req.qna.dao.sqlImpl;

import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.req.qna.dao.MaQnaDetailDAO;
import dream.req.qna.dto.MaQnaCommonDTO;
import dream.req.qna.dto.MaQnaDetailDTO;

/**
 * 질의 - 상세 dao
 * 
 * @author kim21017
 * @version $Id: MaQnaDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="maQnaDetailDAOTarget"
 * @spring.txbn id="maQnaDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaQnaDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaQnaDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaQnaDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maQnaCommonDTO
     * @return
     */
    public MaQnaDetailDTO findDetail(MaQnaCommonDTO maQnaCommonDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                   								");
        query.append("       x.question_id AS questionId,					");
        query.append("       x.question_no AS questionNo,					");
        query.append("       x.title AS questionTitle,						");
        query.append("       x.description AS questionDesc,					");
        query.append("       x.dept_id AS deptId,							");
        query.append("       (SELECT description 							");
        query.append("          FROM TADEPT a								");
        query.append("         WHERE a.comp_no = x.comp_no					");
        query.append("           AND a.dept_id = x.dept_id) deptDesc,		");
        query.append("       x.user_id AS userId,							");
        query.append("       (SELECT user_name 								");
        query.append("          FROM TAUSER a								");
        query.append("         WHERE a.comp_no = x.comp_no					");
        query.append("           AND a.user_id = x.user_id) userDesc,		");
        query.append("		x.reg_date AS regDate							");
        query.append("FROM   TAQUESTION x        							");
        query.append("WHERE  x.comp_no = '"+maQnaCommonDTO.getCompNo()+"' 	");
        query.append("  AND  x.question_id = "+maQnaCommonDTO.getQuestionId()+"");
    
        MaQnaDetailDTO maQnaDetailDTO = 
        		(MaQnaDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaQnaDetailDTO()));
        
        return maQnaDetailDTO;
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaQnaDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maQnaDetailDTO
     * @return
     */
    public int insertDetail(MaQnaDetailDTO maQnaDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAQUESTION							");
    	query.append("	(comp_no,		question_id,	question_no,	");
    	query.append("	 reg_date,		dept_id,		user_id,		");
    	query.append("	 title,			description						");
    	query.append("	)	VALUES										");
    	query.append("	(?,				?,				?,				");
    	query.append("	 ?,				?,				?,				");
    	query.append("	 ?,				?								");
    	query.append("	)												");
    	
    	Object[] objects = new Object[] {
    			maQnaDetailDTO.getCompNo(),
    			maQnaDetailDTO.getQuestionId(),
    			maQnaDetailDTO.getQuestionNo(),
    			maQnaDetailDTO.getRegDate(),
    			maQnaDetailDTO.getDeptId(),
    			maQnaDetailDTO.getUserId(),
    			maQnaDetailDTO.getQuestionTitle(),
    			maQnaDetailDTO.getQuestionDesc()
    	};
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaQnaDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maQnaDetailDTO
     * @return
     */
    public int updateDetail(MaQnaDetailDTO maQnaDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAQUESTION SET			");
    	query.append("	title				= ?,	");
    	query.append("	description			= ?		");
    	query.append("WHERE question_id		= ?		");
    	query.append("  AND comp_no 		= ?		");
    	
    	Object[] objects = new Object[] {
    			maQnaDetailDTO.getQuestionTitle(),
    			maQnaDetailDTO.getQuestionDesc(),
    			maQnaDetailDTO.getQuestionId(),
    			maQnaDetailDTO.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    /**
     * detail confirm
     * @author kim21017
     * @version $Id: MaQnaDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maQnaDetailDTO
     * @return
     */
    public int confirmDetail(MaQnaDetailDTO maQnaDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("");
    	
    	Object[] objects = new Object[] {
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
}