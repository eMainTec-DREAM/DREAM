package dream.req.qna.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.req.qna.dao.MaQnaListDAO;
import dream.req.qna.dto.MaQnaCommonDTO;

/**
 * 질의 dao
 * @author  kim21017
 * @version $Id: MaQnaListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maQnaListDAOTarget"
 * @spring.txbn id="maQnaListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaQnaListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaQnaListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaQnaListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maQnaCommonDTO
     * @return List
     */
    public List findQnaList(MaQnaCommonDTO maQnaCommonDTO)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                   								");
        query.append("       '' AS seqNo,									");
        query.append("       '' AS isDelCheck,								");
        query.append("       x.question_id AS questionId,					");
        query.append("       x.question_no AS questionNo,					");
        query.append("       x.title AS questionTitle,						");
        query.append("       x.description AS questionDesc,					");
        query.append("       (SELECT count(1) 								");
        query.append("          FROM TAANSWER a								");
        query.append("         WHERE a.comp_no = x.comp_no					");
        query.append("           AND a.question_id = x.question_id) ansCnt,	");
        query.append("       (SELECT description 							");
        query.append("          FROM TADEPT a								");
        query.append("         WHERE a.comp_no = x.comp_no					");
        query.append("           AND a.dept_id = x.dept_id) regDept,		");
        query.append("       (SELECT user_name 								");
        query.append("          FROM TAUSER a								");
        query.append("         WHERE a.comp_no = x.comp_no					");
        query.append("           AND a.user_id = x.user_id) regUser,		");
        query.getDate("x.reg_date", "regDate");
        query.append("FROM   TAQUESTION x        							");
        query.append("WHERE  1=1               								");
        query.append(this.getWhere(maQnaCommonDTO));
        //query.append("ORDER by x.reg_date desc       						");
        query.getOrderByQuery("x.reg_date desc", maQnaCommonDTO.getOrderBy(), maQnaCommonDTO.getDirection());

        return getJdbcTemplate().queryForList(query.toString(maQnaCommonDTO.getIsLoadMaxCount(), maQnaCommonDTO.getFirstRow()));
    }
    
    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id: MaQnaListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maQnaCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaQnaCommonDTO maQnaCommonDTO)
    {        
        QueryBuffer query = new QueryBuffer();
        if (!"".equals(maQnaCommonDTO.getQuestionId()))
        {
            query.getAndQuery("x.question_id", maQnaCommonDTO.getQuestionId());
            return query.toString();
        }
        query.getAndQuery("x.comp_no", maQnaCommonDTO.getCompNo());
        query.getLikeQuery("x.question_no", maQnaCommonDTO.getFilterQuestionNo());
        query.getLikeQuery("x.title", maQnaCommonDTO.getFilterQuestionTitle());
        query.getAndDateQuery("x.reg_date", maQnaCommonDTO.getFilterStartRegDate(), maQnaCommonDTO.getFilterEndRegDate());
        query.getCodeLikeQuery("x.user_id", "(SELECT a.user_name FROM  TAUSER a WHERE a.user_id = x.user_id AND a.comp_no='"+maQnaCommonDTO.getCompNo()+"')", 
        		maQnaCommonDTO.getFilterUserId(), maQnaCommonDTO.getFilterUserDesc());
        query.getDeptLevelQuery("x.dept_id", maQnaCommonDTO.getFilterDeptId(), maQnaCommonDTO.getFilterDeptDesc(), maQnaCommonDTO.getCompNo());
        
        
        
        return query.toString();
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaQnaListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deleteQna(String id, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	String qnaId = id;
    	
    	query.append("DELETE FROM TAQUESTION			");
    	query.append("WHERE question_id = '"+qnaId+"'	");
    	query.getAndQuery("comp_no", user.getCompNo());
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	query = new QueryBuffer();
    	
    	query.append("DELETE FROM TAANSWER				");
    	query.append("WHERE question_id = '"+qnaId+"'	");
    	query.getAndQuery("comp_no", user.getCompNo());
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	return rtnValue;
    }

	@Override
	public String findTotalCount(MaQnaCommonDTO maQnaCommonDTO, User user)
	{
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                   					");
        query.append("       COUNT(1)                           ");
        query.append("FROM   TAQUESTION x        				");
        query.append("WHERE  1=1               					");
        query.append(this.getWhere(maQnaCommonDTO));
        
        List resultList = getJdbcTemplate().queryForList(query.toString());
        
		return QueryBuffer.listToString(resultList);
	}
}