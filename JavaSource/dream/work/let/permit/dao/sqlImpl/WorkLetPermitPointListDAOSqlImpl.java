package dream.work.let.permit.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.work.let.permit.dao.WorkLetPermitPointListDAO;
import dream.work.let.permit.dto.WorkLetPermitDetailDTO;
import dream.work.let.permit.dto.WorkLetPermitPointDetailDTO;
import dream.work.let.permit.dto.WorkLetPermitPointListDTO;

/**
 * 안전작업허가서 작업유형 - 점검항목 목록 dao
 * @author syyang
 * @version $Id: WorkLetPermitPointListDAOSqlImpl.java,v 1.0 2015/12/02 09:12:40 syyang Exp $
 * @since 1.0
 * 
 * @spring.bean id="workLetPermitPointListDAOTarget"
 * @spring.txbn id="workLetPermitPointListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkLetPermitPointListDAOSqlImpl extends BaseJdbcDaoSupportSql implements WorkLetPermitPointListDAO
{
	public List findList(WorkLetPermitDetailDTO workLetPermitDetailDTO, WorkLetPermitPointListDTO workLetPermitPointListDTO, User user) throws Exception
    { 
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT																");
        query.append("		 ''                      				AS isDelCheck			");
        query.append("		, x.woletlistpoint_id                  	AS woletlistpointid		");
        query.append("		, x.step_num         					AS stepNum				");
        query.append("		, x.check_position 						AS checkPosition		");
        query.append("		, x.check_point 						AS 'CHECKPOINT'			");
        query.append("		, x.check_method 						AS checkMethod			");	
        query.append("		, x.fit_basis     						AS fitBasis				");
        query.append("		, dbo.SFACODE_TO_DESC(x.check_type, 'CHECK_TYPE', 'SYS', '', 'ko')	AS checkTypeDesc	");
        query.append("		, dbo.SFACODE_TO_DESC(x.wolet_rslt_status,'WOLET_RSLT_STATUS','SYS','','"+user.getLangId()+"')	AS woLetRsltStatusDesc	");
        query.append("		, convert(nvarchar,x.check_min)+' / '+convert(nvarchar,x.check_basis_val)+' / '+convert(nvarchar,x.check_max)	AS minBasisMax	");
        query.append("		, x.result_value 						AS resultValue			");
        query.append("		, x.uom                               	AS uom					");
        query.append("		, x.remark                              AS remark				");
        query.append("		, x.check_type     						AS checkType			");
        query.append("		, x.wolet_rslt_status					AS woLetRsltStatus		");
        query.append("		, x.woletctg_id                         AS woLetCtgId			");
        query.append("		, x.woletlist_id      					AS woLetListId			");
        query.append("FROM TAWOLETLISTPOINT x												");
        query.append("WHERE  1=1															");
    	query.append(this.getWhere(workLetPermitDetailDTO, workLetPermitPointListDTO, user));

    	query.getOrderByQuery("x.woletlistpoint_id", "x.step_num", workLetPermitPointListDTO.getOrderBy(), workLetPermitPointListDTO.getDirection());
        
    	return getJdbcTemplate().queryForList(query.toString(workLetPermitPointListDTO.getIsLoadMaxCount(), workLetPermitPointListDTO.getFirstRow()));
    } 

	private String getWhere(WorkLetPermitDetailDTO workLetPermitDetailDTO, WorkLetPermitPointListDTO workLetPermitPointListDTO, User user)
    {        
		QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        
        query.getAndQuery("x.woletlist_id", workLetPermitDetailDTO.getWoLetListId());
        
        if(!"".equals(workLetPermitPointListDTO.getWoLetListPointId())){
        	query.getAndQuery("x.woletlistpoint_id", workLetPermitPointListDTO.getWoLetListPointId());
        	return query.toString();
        }
        
    	return query.toString();
    }

    public int deleteList(String id, User user) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
        query.append("DELETE FROM TAWOLETLISTPOINT			");
        query.append("WHERE  comp_no 			= ?			");
        query.append("  AND  woletlistpoint_id 	= ?			");
        
        Object[] objects = new Object[] {   
                user.getCompNo()
                ,id
        };
        
        return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    public String findTotalCount(WorkLetPermitDetailDTO workLetPermitDetailDTO, WorkLetPermitPointListDTO workLetPermitPointListDTO, User user) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                    ");
        query.append("      COUNT(1)                            ");
        query.append("FROM TAWOLETLISTPOINT x					");
        query.append("WHERE  1=1								");
    	query.append(this.getWhere(workLetPermitDetailDTO, workLetPermitPointListDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QuerySqlBuffer.listToString(resultList);
    }

	@Override
	public WorkLetPermitPointDetailDTO getWoLetCategPointDetail(String woLetCtgPointId, User user) throws Exception 
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
	
        query.append("SELECT																					");
        query.append("		step_num         												AS stepNum			");
        query.append("		, check_position 												AS checkPosition	");
        query.append("		, check_point 													AS 'CHECKPOINT'		");
        query.append("		, check_method 													AS checkMethod		");
        query.append("		, fit_basis     												AS fitBasis			");
        query.append("		, a.check_type												 	AS checkTypeId		");
        query.append("		, dbo.SFACODE_TO_DESC(a.check_type, 'CHECK_TYPE', 'SYS', '', 'ko') 	AS checkTypeDesc	");
        query.append("		, check_min                                                     AS checkMin			");
        query.append("		, check_basis_val                                               AS checkBasisVal	");
        query.append("		, check_max                                                     AS checkMax			");
        query.append("		, uom                                                           AS uom				");
        query.append(" 		, remark                                                 		AS remark			");
        query.append("		, woletctgpoint_id                    	 						AS woLetCtgPointId	");
        query.append("		, woletctg_id                    	 							AS woLetCtgId		");
        query.append("FROM TAWOLETCTGPOINT a																	");
		query.append("WHERE  1=1																				");
		query.append(" AND a.comp_no   	     = ?																");
        query.append(" AND a.woletctgpoint_id 	 = ?															");
        
        Object[] objects = new Object[] {
        		user.getCompNo()
        		,woLetCtgPointId
    	};
    
        WorkLetPermitPointDetailDTO workLetPermitPointDetailDTO = 
        		(WorkLetPermitPointDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new WorkLetPermitPointDetailDTO()));
        
		return workLetPermitPointDetailDTO;
	}
}