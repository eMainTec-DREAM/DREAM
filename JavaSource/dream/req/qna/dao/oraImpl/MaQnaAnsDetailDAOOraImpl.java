package dream.req.qna.dao.oraImpl;

import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.req.qna.dao.MaQnaAnsDetailDAO;
import dream.req.qna.dto.MaQnaAnsDetailDTO;
import dream.req.qna.dto.MaQnaAnsListDTO;
import dream.req.qna.dto.MaQnaCommonDTO;

/**
 * 답변 상세 dao
 * @author  kim21017
 * @version $Id: MaQnaAnsDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maQnaAnsDetailDAOTarget"
 * @spring.txbn id="maQnaAnsDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaQnaAnsDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MaQnaAnsDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaQnaAnsDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maQnaAnsListDTO
     * @param maQnaCommonDTO
     * @return
     */
    public MaQnaAnsDetailDTO findDetail(MaQnaAnsListDTO maQnaAnsListDTO, MaQnaCommonDTO maQnaCommonDTO)
    {
        QueryBuffer query = new QueryBuffer();
        String compNo = maQnaCommonDTO.getCompNo();
        
        query.append("SELECT												");
        query.append("       x.question_id			AS questionId,			");
        query.append("       x.answer_no			AS answerNo,			");
        query.append("       x.description			AS answerDesc,			");
        query.append("       x.dept_id				AS deptId,				");
        query.append("       (SELECT description 							");
        query.append("          FROM TADEPT a								");
        query.append("         WHERE a.comp_no = x.comp_no					");
        query.append("           AND a.dept_id = x.dept_id) deptDesc,		");
        query.append("       x.user_id				AS userId,				");
        query.append("       (SELECT user_name 								");
        query.append("          FROM TAUSER a								");
        query.append("         WHERE a.comp_no = x.comp_no					");
        query.append("           AND a.user_id = x.user_id) userDesc,		");
        query.append("       x.answer_id				AS answerId,		");
        query.append("		x.reg_date AS regDate							");
        query.append("FROM TAANSWER x										");
        query.append("WHERE x.comp_no = '"+compNo+"'						");
        query.getAndQuery("x.answer_id",maQnaAnsListDTO.getAnswerId());
        query.getAndQuery("x.question_id",maQnaCommonDTO.getQuestionId());
    
        MaQnaAnsDetailDTO maQnaAnsDetailDTO1 = 
        		(MaQnaAnsDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaQnaAnsDetailDTO()));
        
        return maQnaAnsDetailDTO1;
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaQnaAnsDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maQnaAnsDetailDTO
     * @param maQnaCommonDTO
     * @return
     */
    public int updateDetail(MaQnaAnsDetailDTO maQnaAnsDetailDTO, MaQnaCommonDTO maQnaCommonDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAANSWER SET				");
    	query.append("	description				= ?		");
    	query.append("WHERE answer_id 			= ?		");
    	query.append("  AND question_id			= ? 	");
    	query.append("  AND comp_no				= ? 	");
    	
    	Object[] objects = new Object[] {
    			maQnaAnsDetailDTO.getAnswerDesc(),
    			maQnaAnsDetailDTO.getAnswerId(),
    			maQnaCommonDTO.getQuestionId(),
    			maQnaCommonDTO.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaQnaAnsDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maQnaAnsDetailDTO
     * @param maQnaCommonDTO
     * @return
     */
    public int insertDetail(MaQnaAnsDetailDTO maQnaAnsDetailDTO, MaQnaCommonDTO maQnaCommonDTO)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query.append("INSERT INTO TAANSWER (								");
    	query.append("	comp_no,		answer_id,		question_id,		");
    	query.append("	description,	reg_date,		dept_id,			");
    	query.append("	user_id,		answer_no							");
    	query.append("	)	VALUES				(							");
    	query.append("	?,				?,					?,				");
    	query.append("	?,				?,					?,				");
    	query.append("	?,				?									");
    	query.append("	)													");
    	
    	Object[] objects = new Object[] {
    			maQnaCommonDTO.getCompNo(),
    			maQnaAnsDetailDTO.getAnswerId(),
    			maQnaCommonDTO.getQuestionId(),
    			maQnaAnsDetailDTO.getAnswerDesc(),
    			maQnaAnsDetailDTO.getRegDate(),
    			maQnaAnsDetailDTO.getDeptId(),
    			maQnaAnsDetailDTO.getUserId(),
    			maQnaAnsDetailDTO.getAnswerNo()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
}