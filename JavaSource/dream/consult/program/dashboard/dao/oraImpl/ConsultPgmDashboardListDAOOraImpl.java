package dream.consult.program.dashboard.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.consult.program.dashboard.dao.ConsultPgmDashboardListDAO;
import dream.consult.program.dashboard.dto.ConsultPgmDashboardCommonDTO;

/**
 * 대시보드 Contents - 목록 dao
 * @author  kim21017
 * @version $Id: ConsultPgmDashboardListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="consultPgmDashboardListDAOTarget"
 * @spring.txbn id="consultPgmDashboardListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ConsultPgmDashboardListDAOOraImpl extends BaseJdbcDaoSupportOra implements ConsultPgmDashboardListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: ConsultPgmDashboardListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     *
     * @param consultPgmDashboardCommonDTO
     * @return List
     */
    public List findList(ConsultPgmDashboardCommonDTO consultPgmDashboardCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        query.append("SELECT '' AS isDelCheck										");
        query.append("		,'' AS seqNo											");
        query.append("		,x.dbcontents_id AS dbContentsId						");
        query.append("		,x.description AS dbContentsDesc						");
        query.append("		,x.dbcontents_type AS dbContentsTypeId					");
        query.append("		,SFACODE_TO_DESC(x.dbcontents_type,'DBCONTENTS_TYPE','SYS','','"+user.getLangId()+"') AS dbContentsTypeDesc					");
        query.append("		,(SELECT a.key_name FROM TALANG a WHERE a.key_type=x.key_type AND a.key_no = x.key_no AND a.lang = '"+user.getLangId()+"') AS keyName	");
        query.append("		,(SELECT a.description FROM TAPAGE a WHERE a.page_id=x.page_id ) AS pageDesc												");
        query.append("		,x.dbcontents_width AS dbContentsWidthId				");
        query.append("		,SFACODE_TO_DESC(x.dbcontents_width,'DBCONTENTS_WIDTH','SYS','','"+user.getLangId()+"') AS dbContentsWidthDesc				");
        query.append("		,x.image_file AS imageFile								");
        query.append("		,x.is_use AS isUseDesc									");
        query.append("		,x.remark AS remark										");
        query.append("FROM TADBCONTENTS	x											");
        query.append("WHERE 1=1														");
        query.append(this.getWhere(consultPgmDashboardCommonDTO , user));
        query.getOrderByQuery("x.DBCONTENTS_ID", consultPgmDashboardCommonDTO.getOrderBy(), consultPgmDashboardCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(consultPgmDashboardCommonDTO.getIsLoadMaxCount(), consultPgmDashboardCommonDTO.getFirstRow()));
    }
    
    public String findTotalCount(ConsultPgmDashboardCommonDTO consultPgmDashboardCommonDTO, User user){
    	QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT COUNT(*)		");
        query.append("FROM 	TADBCONTENTS x	");
        query.append("WHERE 1=1				");
        query.append(this.getWhere(consultPgmDashboardCommonDTO , user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }

    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id: ConsultPgmDashboardListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     *
     * @param consultPgmDashboardCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(ConsultPgmDashboardCommonDTO consultPgmDashboardCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        if (!"".equals(consultPgmDashboardCommonDTO.getDbContentsId()))
        {
            query.getAndQuery("x.dbcontents_id", consultPgmDashboardCommonDTO.getDbContentsId());
            return query.toString();
        }
        query.getLikeQuery("x.description", consultPgmDashboardCommonDTO.getFilterDbContentsDesc());
    	query.getSysCdQuery("x.dbcontents_type", consultPgmDashboardCommonDTO.getFilterDbContentsTypeId(), consultPgmDashboardCommonDTO.getFilterDbContentsTypeDesc(), "DBCONTENTS_TYPE", user.getCompNo(),user.getLangId());
        
        return query.toString();
    }

    /**
     * delete
     * @author kim21017
     * @version $Id: ConsultPgmDashboardListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param id
     * @return
     */
    public int deleteList(String id, User user)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("DELETE FROM TADBCONTENTS		");
    	query.append("WHERE dbcontents_id = "+id+"	");

    	return this.getJdbcTemplate().update(query.toString());
    }
}