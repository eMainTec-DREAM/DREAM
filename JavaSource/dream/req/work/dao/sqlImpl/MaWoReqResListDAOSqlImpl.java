package dream.req.work.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.req.work.dao.MaWoReqResListDAO;
import dream.req.work.dto.MaWoReqCommonDTO;
import dream.req.work.dto.MaWoReqResListDTO;

/**
 * 작업요청-처리사항 - 목록 dao
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maWoReqResListDAOTarget"
 * @spring.txbn id="maWoReqResListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaWoReqResListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaWoReqResListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maWoReqCommonDTO
     * @return List
     */
    public List findList(MaWoReqCommonDTO maWoReqCommonDTO,MaWoReqResListDTO maWoReqResListDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
            query.append("SELECT												");
            query.append("        ''						SEQNO,				");
            query.append("        ''						ISDELCHECK,			");
            query.append("        x.WOREQRES_ID				WOREQRESID,			");
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
            query.append("        x.response				RESPONSE			");
            
            query.append("         ,(SELECT b.wo_type FROM TAWORKORDER b WHERE b.wkor_id = x.wkor_id) WOTYPE  ");
            query.append("         ,(SELECT b.pm_type FROM TAWORKORDER b WHERE b.wkor_id = x.wkor_id) PMTYPE  ");
            query.append("         ,(SELECT (SELECT b.param1 FROM TACDSYSD b WHERE b.list_type=a.wo_type+'_TYPE' AND b.cdsysd_no= a.pm_type)FROM TAWORKORDER a WHERE a.comp_no = x.comp_no AND a.wkor_id = x.wkor_id    ) WOPARAM      ");
            query.append("         , x.invtlist_id          invtlistId			");
            query.append("FROM TAWOREQRES x 									");
            query.append("WHERE 1=1												");
            query.append(this.getWhere(maWoReqCommonDTO,maWoReqResListDTO,user));
            query.getOrderByQuery("x.woreqres_id","x.woreqres_id", maWoReqResListDTO.getOrderBy(), maWoReqResListDTO.getDirection());
            
            return getJdbcTemplate().queryForList(query.toString(maWoReqResListDTO.getIsLoadMaxCount(), maWoReqResListDTO.getFirstRow()));

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
    private String getWhere(MaWoReqCommonDTO maWoReqCommonDTO,MaWoReqResListDTO maWoReqResListDTO, User user){
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.getStringEqualQuery("x.comp_no", user.getCompNo());
    	query.append("AND x.WOREQRES_METHOD IN ('EM','WO') ");
        query.getAndNumKeyQuery("x.woreq_id", maWoReqCommonDTO.getWoReqId());
        
        if (!"".equals(maWoReqResListDTO.getWoReqResId()))
        {
            query.getAndQuery("x.woreqres_id", maWoReqResListDTO.getWoReqResId());
            return query.toString();
        }
        
        return query.toString();
    }


	@Override
	public String findTotalCount(MaWoReqCommonDTO maWoReqCommonDTO, MaWoReqResListDTO maWoReqResListDTO, User user)
			throws Exception {

		QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT				");
        query.append("       COUNT(1)       ");
        query.append("FROM TAWOREQRES x		");
        query.append("WHERE 1=1				");
        query.append(this.getWhere(maWoReqCommonDTO,maWoReqResListDTO,user));

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);

	}
    
}