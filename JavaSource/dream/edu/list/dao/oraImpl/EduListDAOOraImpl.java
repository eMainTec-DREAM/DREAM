package dream.edu.list.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.edu.list.dao.EduListDAO;
import dream.edu.list.dto.EduCommonDTO;

/**
 * 자격증분류 - 목록 dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="eduListDAOTarget"
 * @spring.txbn id="eduListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class EduListDAOOraImpl extends BaseJdbcDaoSupportOra implements EduListDAO
{
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param eduCommonDTO
     * @return List
     */
    public List findList(EduCommonDTO eduCommonDTO,User user)
    {
        QueryBuffer query = new QueryBuffer();
        String compNo = eduCommonDTO.getCompNo();
        
        query.append("SELECT										");
        query.append("       '' seqNo,								");
        query.append("       '' isDelCheck,							");
        query.append("       x.courselist_no courseListNo,			");
        query.append("       x.description description,				");                
        query.append("       SFACODE_TO_DESC(x.course_type, 'COURSE_TYPE', 'USR', x.comp_no,'"+user.getLangId()+"') courseType,		");
        query.append("       x.is_use isUse,						");
        query.append("       x.contents contents,					");
        query.append("       x.comp_no compNo,						");
        query.append("       x.courselist_id courseListId			");
        query.append("FROM TACOURSELIST x							");
        query.append("WHERE 1=1										");
        query.getAndQuery("x.comp_no", compNo);
        query.append(this.getWhere(eduCommonDTO, user));
        //query.append("ORDER BY x.courselist_id                                   ");
        query.getOrderByQuery("x.courselist_id", eduCommonDTO.getOrderBy(), eduCommonDTO.getDirection());

        return getJdbcTemplate().queryForList(query.toString(eduCommonDTO.getIsLoadMaxCount(), eduCommonDTO.getFirstRow()));
    }
    
    /**
     * delete
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteList(String compNo, String courseListId)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query = new QueryBuffer();
    	
    	query.append("DELETE FROM TACOURSELIST		              ");
    	query.append("WHERE  comp_no       = '"+compNo+"'		  ");
    	query.append("  AND  courselist_id  = '"+courseListId+"'    ");
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	return rtnValue;
    }
    
    /**
     * Filter 조건
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param eduCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(EduCommonDTO eduCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
      
        if (!"".equals(eduCommonDTO.getCourseListId()))
        {
            query.getAndQuery("x.courselist_id", eduCommonDTO.getCourseListId());
            return query.toString();
        }

        //상태
        query.getCodeLikeQuery("x.course_type", "SFACODE_TO_DESC(x.course_type, 'COURSE_TYPE', 'USR', x.comp_no,'"+user.getLangId()+"')", 
                                        eduCommonDTO.getFilterEduType(), eduCommonDTO.getFilterEduTypeDesc());
        
        query.getLikeQuery("x.description", eduCommonDTO.getFilterEduName());
        return query.toString();
    }

	@Override
	public String findTotalCount(EduCommonDTO eduCommonDTO, User user)
	{
        QueryBuffer query = new QueryBuffer();
        String compNo = eduCommonDTO.getCompNo();
        
        query.append("SELECT							");
        query.append("       COUNT(1)					");
        query.append("FROM TACOURSELIST x				");
        query.append("WHERE 1=1							");
        query.getAndQuery("x.comp_no", compNo);
        query.append(this.getWhere(eduCommonDTO, user));
        
        List resultList = getJdbcTemplate().queryForList(query.toString());
        
        return QueryBuffer.listToString(resultList);
        
	}
}