package dream.scheduler.list.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.scheduler.list.dao.MaBatchMngListDAO;
import dream.scheduler.list.dto.MaBatchMngCommonDTO;

/**
 * 버튼 - 목록 dao
 * @author  kim21017
 * @version $Id: MaBatchMngListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maBatchMngListDAOTarget"
 * @spring.txbn id="maBatchMngListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaBatchMngListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaBatchMngListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaBatchMngListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maBatchMngCommonDTO
     * @return List
     */
    public List findBatchList(MaBatchMngCommonDTO maBatchMngCommonDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        query.append("SELECT '' AS seqNo											");
        query.append("		,'' AS isDelCheck										");
        query.append("		,x.batpgm_id AS batPgmId								");
        query.append("		,x.batpgm_no AS batPgmNo								");
        query.append("		,x.description AS batPgmDesc							");
        query.append("		,x.is_exec AS isExec									");
        query.append("		,x.batch_pgm AS batchPgm								");
        query.append("		,x.remark AS remark										");
        query.append("		,CONVERT(DATETIME, x.last_exe_time)AS lastExeTime    	");
        query.append("		,(SELECT user_name										");
        query.append("			FROM TAUSER											");
        query.append("			WHERE comp_no = x.comp_no							");
        query.append("			AND user_id=x.exe_by) AS exeBy						");
        query.append("FROM TABATPGM x												");
        query.append("WHERE 1=1														");
        query.append(this.getWhere(maBatchMngCommonDTO));
        //query.append("ORDER by x.batpgm_id											");
        query.getOrderByQuery("x.batpgm_id","x.batpgm_id", maBatchMngCommonDTO.getOrderBy(), maBatchMngCommonDTO.getDirection());
                
        return getJdbcTemplate().queryForList(query.toString(maBatchMngCommonDTO.getIsLoadMaxCount(), maBatchMngCommonDTO.getFirstRow()));
    }
    
    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id: MaBatchMngListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maBatchMngCommonDTO
     * @return
     * @throws Exception                                                                                                                                                                       
     */
    private String getWhere(MaBatchMngCommonDTO maBatchMngCommonDTO)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        query.getAndQuery("x.comp_no", maBatchMngCommonDTO.getCompNo());
        if (!"".equals(maBatchMngCommonDTO.getBatPgmId()))
        {
            query.getAndQuery("x.batpgm_id", maBatchMngCommonDTO.getBatPgmId());
            return query.toString();
        }
        query.getLikeQuery("x.description", maBatchMngCommonDTO.getFilterDesc());
        return query.toString();
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaBatchMngListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deleteBatch(String id, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	String batPgmId = id;
    	
    	query.append("DELETE FROM TABATPGM				");
    	query.append("WHERE batpgm_id = '"+batPgmId+"'	");
    	query.getAndQuery("comp_no", user.getCompNo());
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    /**
     * exec
     * @author kim21017
     * @version $Id: MaBatchMngListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public String execBatch(String id, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	String batPgmId = id;
    	String[] batArr = getBatchName(batPgmId, user);
    	if (!"".equals(batArr[0])&&!"N".equals(batArr[1])) {
//    		query.append("begin                                                            ");
//            query.append(""+batArr[0]+"('"+user.getCompNo()+"','"+user.getUserNo()+"');    ");
//            query.append("end;                                                             ");
//            this.getJdbcTemplate().execute(query.toString());
            return batArr[0];
		}else{
			return "";
		}
    }
    
    public String[] getBatchName(String id, User user){
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	query.append("SELECT batch_pgm, is_exec		");
    	query.append("FROM TABATPGM					");
    	query.append("WHERE 1=1						");
    	query.getAndQuery("comp_no", user.getCompNo());
    	query.getAndQuery("batpgm_id", id);
    	return QuerySqlBuffer.singleRowToStringArray(this.getJdbcTemplate().queryForList(query.toString()));
    }

	@Override
	public String findTotalCount(MaBatchMngCommonDTO maBatchMngCommonDTO, User user)
	{
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT							");
        query.append("		COUNT(1)					");
        query.append("FROM TABATPGM x					");
        query.append("WHERE 1=1							");
        query.append(this.getWhere(maBatchMngCommonDTO));
        
        List resultList = getJdbcTemplate().queryForList(query.toString());
        
		return QuerySqlBuffer.listToString(resultList);
	}
}