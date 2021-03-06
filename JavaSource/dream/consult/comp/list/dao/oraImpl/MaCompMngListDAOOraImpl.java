package dream.consult.comp.list.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.consult.comp.list.dao.MaCompMngListDAO;
import dream.consult.comp.list.dto.MaCompMngCommonDTO;

/**
 * 회사설정 - 목록 dao
 * @author  kim21017
 * @version $Id: MaCompMngListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maCompMngListDAOTarget"
 * @spring.txbn id="maCompMngListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaCompMngListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaCompMngListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaCompMngListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maCompMngCommonDTO
     * @return List
     */
    public List findCompList(MaCompMngCommonDTO maCompMngCommonDTO, User user)
    {
    	

        QueryBuffer query = new QueryBuffer();
        query.append("SELECT                   					                                           ");
        query.append("       ''  seqNo							                                           ");
        query.append("		 ,'' isDelCheck						                                           ");
        query.append("       ,x.comp_no compNo      			                                           ");
        query.append("       ,x.description compDesc      		                                           ");
        query.append("		 ,(SELECT SFACODE_TO_DESC(x.ct_path,'CT_PATH','SYS','','"+user.getLangId()+"') FROM DUAL)	ctPath ");
        query.append("       ,x.is_use          isUse				                                       ");
        query.append("       ,x.comp_id         compId				                                       ");
        query.append("FROM   TACOMP x        					                                           ");
        query.append("WHERE  1=1               					                                           ");
        query.append(this.getWhere(maCompMngCommonDTO));
        query.getOrderByQuery("x.comp_no", maCompMngCommonDTO.getOrderBy(), maCompMngCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maCompMngCommonDTO.getIsLoadMaxCount(), maCompMngCommonDTO.getFirstRow()));
        
    }
    
    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id: MaCompMngListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maCompMngCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaCompMngCommonDTO maCompMngCommonDTO)
    {        
        QueryBuffer query = new QueryBuffer();
        if (!"".equals(maCompMngCommonDTO.getCompId()))
        {
//            query.getAndQuery("x.comp_no", maCompMngCommonDTO.getCompNo());
            query.getAndQuery("x.comp_id", maCompMngCommonDTO.getCompId());
            return query.toString();
        }
        
        query.getAndQuery("x.comp_no", maCompMngCommonDTO.getCompNo());
        query.getAndQuery("x.init_ct_path_yn", maCompMngCommonDTO.getFilterInitCtPathYn());
        query.getAndQuery("x.is_use", maCompMngCommonDTO.getFilterIsUse());
        
        query.getLikeQuery("x.description", maCompMngCommonDTO.getFilterCompName());
        return query.toString();
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaCompMngListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deleteComp(String id)
    {
    	QueryBuffer query = new QueryBuffer();

    	String compId = id;
    	
    	query.append("DELETE FROM TACOMP				");
    	query.append("WHERE comp_id = '"+compId+"'		");
    	
    	return this.getJdbcTemplate().update(query.toString());
    }

    public String findTotalCount(MaCompMngCommonDTO maCompMngCommonDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("SELECT                ");
        query.append("       COUNT(1)       ");
        query.append("FROM   TACOMP x		");
        query.append("WHERE  1=1            ");
        query.append(this.getWhere(maCompMngCommonDTO));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
}