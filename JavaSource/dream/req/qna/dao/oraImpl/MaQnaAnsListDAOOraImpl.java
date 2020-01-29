package dream.req.qna.dao.oraImpl;

import java.util.List;

import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.req.qna.dao.MaQnaAnsListDAO;
import dream.req.qna.dto.MaQnaAnsListDTO;
import dream.req.qna.dto.MaQnaCommonDTO;

/**
 * 답변 목록 dao
 * @author  kim21017
 * @version $Id: MaQnaAnsListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maQnaAnsListDAOTarget"
 * @spring.txbn id="maQnaAnsListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaQnaAnsListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaQnaAnsListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaQnaAnsListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maQnaCommonDTO
     * @param maQnaAnsListDTO
     * @return List
     */
    public List findAnsList(MaQnaCommonDTO maQnaCommonDTO, MaQnaAnsListDTO maQnaAnsListDTO)
    {
        QueryBuffer query = new QueryBuffer();
        String compNo = maQnaCommonDTO.getCompNo();
        query.append("SELECT												");
        query.append("       '' seqNo,										");
        query.append("       '' isDelCheck,									");
        query.append("       x.question_id			AS questionId,			");
        query.append("       x.answer_no			AS answerNo,			");
        query.append("       x.description			AS answerDesc,			");
        query.append("       (SELECT description 							");
        query.append("          FROM TADEPT a								");
        query.append("         WHERE a.comp_no = x.comp_no					");
        query.append("           AND a.dept_id = x.dept_id) regDept,		");
        query.append("       (SELECT user_name 								");
        query.append("          FROM TAUSER a								");
        query.append("         WHERE a.comp_no = x.comp_no					");
        query.append("           AND a.user_id = x.user_id) regUser,		");
        query.append("       x.answer_id				AS answerId,		");
        query.getDate("x.reg_date", "regDate");
        query.append("FROM TAANSWER x										");
        query.append("WHERE x.comp_no = '"+compNo+"'						");
        query.append(this.getWhere(maQnaCommonDTO,maQnaAnsListDTO));
        query.append("ORDER BY x.answer_no									");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaQnaAnsListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRow
     * @param deleteRowsExt 
     * @return
     */
    public int deleteAnsList(String deleteRow, String deleteRowsExt)
    {
    	QueryBuffer query = new QueryBuffer();

    	String question_id=deleteRow;
    	String answer_id=deleteRowsExt;
    	
    	query.append("DELETE FROM TAANSWER						");
    	query.append("WHERE question_id 	= '"+question_id+"'	");
    	query.append("  AND answer_id 	= '"+answer_id+"'		");
    	
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    private String getWhere(MaQnaCommonDTO maQnaCommonDTO, MaQnaAnsListDTO maQnaAnsListDTO)
    {
    	QueryBuffer query = new QueryBuffer();
    	query.getAndQuery("x.question_id", maQnaCommonDTO.getQuestionId());
    	if (!"".equals(maQnaAnsListDTO.getAnswerId()))
        {
            query.getAndQuery("x.answer_id", maQnaAnsListDTO.getAnswerId());
            return query.toString();
        }
    	
    	return query.toString();
    }
}