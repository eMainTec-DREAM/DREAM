package dream.req.work.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.req.work.dao.ReqWorkResListDAO;
import dream.req.work.dto.ReqWorkCommonDTO;
import dream.req.work.dto.ReqWorkResListDTO;

/**
 * �۾���û-ó������ - ��� dao
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="reqWorkResListDAOTarget"
 * @spring.txbn id="reqWorkResListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ReqWorkResListDAOSqlImpl extends BaseJdbcDaoSupportSql implements ReqWorkResListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     *
     * @param reqWorkCommonDTO
     * @return List
     */
    public List findList(ReqWorkCommonDTO reqWorkCommonDTO,ReqWorkResListDTO reqWorkResListDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

            query.append("SELECT												");
            query.append("        ''						SEQNO,				");
            query.append("        ''						ISDELCHECK,			");
            query.append("        x.woreqres_id				WOREQRESID,			");
	        query.getDate("x.res_date", "RESDATE,");
	        query.append("		  (SELECT a.emp_name							");
	        query.append("			FROM TAEMP a								");
	        query.append("			WHERE a.comp_no = x.comp_no					");
	        query.append("			AND a.emp_id = x.emp_id						");
	        query.append("			)+'/'+									");
	        query.append("			(SELECT a.description						");
	        query.append("			FROM TADEPT a								");
	        query.append("			WHERE a.comp_no = x.comp_no					");
	        query.append("			AND a.dept_id = x.dept_id					");
	        query.append("			)						RESBY,				");
	        query.append("			wkor_id					WKORID,				");
	        query.append("		  dbo.SFACODE_TO_DESC(x.wores_status,'WORES_STATUS','SYS','','"+user.getLangId()+"') WORESSTATUSDESC,");
            query.append("        x.response				RESPONSE,			");
            query.append("        x.invtlist_id				invtlistId			");
            query.append("FROM TAWOREQRES x 									");
            query.append("WHERE 1=1												");
            query.append(this.getWhere(reqWorkCommonDTO,reqWorkResListDTO,user));
            query.getOrderByQuery("x.woreqres_id","x.woreqres_id desc", reqWorkResListDTO.getOrderBy(), reqWorkResListDTO.getDirection());
            
            return getJdbcTemplate().queryForList(query.toString(reqWorkResListDTO.getIsLoadMaxCount(), reqWorkResListDTO.getFirstRow()));

    }


    public int deleteList(String id, String compNo)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        int rtnValue  = 0;

        query.append("DELETE FROM TAWOREQRES               ");
        query.append("WHERE woreqres_id     = "+id+"       ");
        query.append("  AND comp_no  = '"+compNo+"'        ");

        rtnValue = this.getJdbcTemplate().update(query.toString());

        return rtnValue;

    }
    private String getWhere(ReqWorkCommonDTO reqWorkCommonDTO,ReqWorkResListDTO reqWorkResListDTO, User user){
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.getStringEqualQuery("x.comp_no", user.getCompNo());
        query.getAndNumKeyQuery("x.woreq_id", reqWorkCommonDTO.getWoReqId());

        if (!"".equals(reqWorkResListDTO.getWoReqResId()))
        {
            query.getAndQuery("x.woreqres_id", reqWorkResListDTO.getWoReqResId());
            return query.toString();
        }

        return query.toString();
    }


	@Override
	public String findTotalCount(ReqWorkCommonDTO reqWorkCommonDTO, ReqWorkResListDTO reqWorkResListDTO, User user)
			throws Exception {

		QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT				");
        query.append("       COUNT(1)       ");
        query.append("FROM TAWOREQRES x		");
        query.append("WHERE 1=1				");
        query.append(this.getWhere(reqWorkCommonDTO,reqWorkResListDTO,user));
	
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);

	}

}