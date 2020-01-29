package dream.consult.comp.time.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.consult.comp.time.dao.MaLineTimeDowListDAO;
import dream.consult.comp.time.dto.MaLineTimeCommonDTO;
import dream.consult.comp.time.dto.MaLineTimeDowListDTO;

/**
 * 요일별 설정 목록 dao
 * @author  kim21017
 * @version $Id: MaLineTimeDowListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maLineTimeDowListDAOTarget"
 * @spring.txbn id="maLineTimeDowListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaLineTimeDowListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaLineTimeDowListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaLineTimeDowListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maLineTimeCommonDTO
     * @param maLineTimeDowListDTO
     * @param loginUser
     * @return List
     */
    public List findDowList(MaLineTimeCommonDTO maLineTimeCommonDTO, MaLineTimeDowListDTO maLineTimeDowListDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT											");
        query.append("       '' 					seqNo				");
        query.append("       ,'' isDelCheck								");
        query.append("       ,x.eqloc_id 			eqLocId			");
        query.append("       ,x.comp_no             compNo            ");
        query.append("       ,x.eqlocdowrun_id 		eqLocDowRunId		");
        query.append("       ,x.dow 					dow				");
        query.append("		 ,dbo.SFACODE_TO_DESC(x.dow,'DOW','SYS','','"+loginUser.getLangId()+"') dowDesc		");
        query.append("       ,x.dtime				dayRunTime			");
        query.append("       ,x.ntime				nightRunTime		");
        query.append("       ,x.etime				extraRunTime		");
        query.append("       ,x.is_use 				isUse				");
        query.append("       ,x.ucnt 				UCNT				");
        query.append("FROM   TAEQLOCDOWRUN x							");
        query.append("WHERE  1=1					 					");
        query.append(this.getWhere(maLineTimeCommonDTO,maLineTimeDowListDTO,loginUser));
        query.getOrderByQuery("x.eqlocdowrun_id","x.ord_no", maLineTimeCommonDTO.getOrderBy(), maLineTimeCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maLineTimeCommonDTO.getIsLoadMaxCount(), maLineTimeCommonDTO.getFirstRow()));
    }
    private String getWhere(MaLineTimeCommonDTO maLineTimeCommonDTO, MaLineTimeDowListDTO maLineTimeDowListDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	query.getAndQuery("x.lnwrklist_id", maLineTimeCommonDTO.getLnWrkListId());
        query.getAndQuery("x.comp_no", maLineTimeCommonDTO.getCompNo());
    	if (!"".equals(maLineTimeDowListDTO.getEqLocDowRunId()))
        {
            query.getAndQuery("x.eqlocdowrun_id", maLineTimeDowListDTO.getEqLocDowRunId());
            return query.toString();
        }
    	
    	return query.toString();
    }

    public String findTotalCount(MaLineTimeCommonDTO maLineTimeCommonDTO, MaLineTimeDowListDTO maLineTimeDowListDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT                                            ");
        query.append("       COUNT(1)                                   ");
        query.append("FROM   TAEQLOCDOWRUN x							");
        query.append("WHERE  1=1					 					");
        query.append(this.getWhere(maLineTimeCommonDTO,maLineTimeDowListDTO,user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }   
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaLineTimeDowListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteDowList(String id, String compNo)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	String eqLocDowRunId=id;
    	
    	query.append("DELETE FROM TAEQLOCDOWRUN							");
    	query.append("WHERE  eqlocdowrun_id 	= '"+eqLocDowRunId+"'	");
    	query.append("  AND  comp_no		 	= '"+compNo+"'			");
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
}