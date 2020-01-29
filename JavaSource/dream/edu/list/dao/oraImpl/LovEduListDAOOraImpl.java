package dream.edu.list.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.edu.list.dao.LovEduListDAO;
import dream.edu.list.dto.LovEduListDTO;

/**
 * ±³À°°úÁ¤ ÆË¾÷
 * @author  hyosung
 * @version $Id: LovEduListDAO.java,v 1.0 2016/01/18 00:16:44 hyosung Exp $
 * @since   1.0
 * @spring.bean id="lovEduListDAOTarget"
 * @spring.txbn id="lovEduListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovEduListDAOOraImpl extends BaseJdbcDaoSupportOra implements LovEduListDAO
{
    /**
     * ±³À°°úÁ¤ °Ë»ö
     * @author  hyosung
     * @version $Id: LovEduListDAO.java,v 1.0 2016/01/18 00:16:44 hyosung Exp $
     * @since   1.0
     * 
     * @param lovEduListDTO
     * @param loginUser
     * @param conditionMap
     * @return
     */
    public List findEduList(LovEduListDTO lovEduListDTO, User loginUser, Map<String, String> conditionMap)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT																");
        query.append("       x.courselist_id 												");
        query.append("       ,x.courselist_no 												");
        query.append("       ,x.description description									    ");
        query.append("		 ,SFACODE_TO_DESC(x.course_type,'COURSE_TYPE','USR','"+loginUser.getCompNo()+"','"+loginUser.getLangId()+"')  course_type ");
        query.append("       ,x.is_use   							    				    ");
        query.append("       ,x.comp_no  											        ");
        query.append("FROM TACOURSELIST x													");
        query.append("WHERE 1=1																");
        query.getAndQuery("comp_no", loginUser.getCompNo());
        query.getLikeQuery("description", lovEduListDTO.getDescription());
        query.getLikeQuery("courselist_no", lovEduListDTO.getCourseNo());
        query.getAndQuery("is_use", lovEduListDTO.getIsUse());
        
        return getJdbcTemplate().queryForList(query.toString());
    }
}