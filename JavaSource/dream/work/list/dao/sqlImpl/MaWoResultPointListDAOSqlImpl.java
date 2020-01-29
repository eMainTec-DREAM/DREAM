package dream.work.list.dao.sqlImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.work.list.dao.MaWoResultPointListDAO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultPointDetailDTO;
import dream.work.list.dto.MaWoResultPointListDTO;

/**
 * 작업결과 검사항목 목록 dao
 * @author  kim21017
 * @version $Id: MaWoResultPointListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maWoResultPointListDAOTarget"
 * @spring.txbn id="maWoResultPointListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaWoResultPointListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaWoResultPointListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaWoResultPointListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrCommonDTO
     * @param maWoResultPointListDTO
     * @param loginUser
     * @return List
     */
    public List findPointList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultPointListDTO maWoResultPointListDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT																	");
        query.append("		''					 						seqNo					");
        query.append("		,'' 										isDelCheck				");
        query.append("		,x.wopoint_id 								woPointId				");
        query.append("		,y.step_num 								stepNum					");
        query.append("		,(SELECT ISNULL(a.full_desc, a.description)							");
        query.append("		    FROM TAEQASMB a													");
        query.append("		   WHERE a.comp_no = y.comp_no										");
        query.append("			 AND a.eqasmb_id = y.eqasmb_id) 		eqAsmbDesc				");
        query.append("		,y.check_point 								'CHECKPOINT'			");
        query.append("		,y.check_method 							checkMethod				");
        query.append("		,y.fit_basis 								fitBasis				");
        query.append("		,dbo.SFACODE_TO_DESC(y.check_type,'CHECK_TYPE','SYS','','"+loginUser.getLangId()+"') checkTypeDesc	");
        query.append("		,dbo.SFACODE_TO_DESC(x.pm_point_rslt_status,'PM_POINT_RSLT_STATUS','SYS','','"+loginUser.getLangId()+"') rsltStatusDesc	");
        query.append("		,x.result_value 							resultValue				");
        query.append("		,y.check_min 								checkMin				");
        query.append("		,y.check_basis_val 							checkBasisVal			");
        query.append("		,y.check_max 								checkMax				");
        query.append("		,y.uom 										uom						");
        query.append("		,y.is_active 								isActive				");
        query.append("		,y.pm_point_id 								pmPointId				");
        query.append("		,x.pm_point_rslt_status 					pmPointRsltStatus		");
        query.append("		,x.remark				 					resultRemark			");
        query.append("		,x.wkor_id				 					wkOrId					");
        query.append("		,y.check_type				 				checkTypeId				");
        query.append("FROM TAWOPOINT x RIGHT OUTER JOIN TAPMPOINT y								");
        query.append("	ON x.comp_no = y.comp_no												");
        query.append(" AND x.pm_point_id = y.pm_point_id										");
        query.getAndNumKeyQuery("x.wkor_id", maWoResultMstrCommonDTO.getWkOrId());
        query.append("WHERE 1=1																	");
        query.append(this.getWhere(maWoResultMstrCommonDTO,maWoResultPointListDTO,loginUser));
        query.getOrderByQuery("x.wopoint_id", "y.step_num", maWoResultPointListDTO.getOrderBy(), maWoResultPointListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maWoResultPointListDTO.getIsLoadMaxCount(), maWoResultPointListDTO.getFirstRow()));

    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaWoResultPointListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int[] deletePointList(final List<MaWoResultPointDetailDTO> list, final User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("DELETE FROM TAWOPOINT             ");
        query.append("WHERE  wopoint_id         = ?     ");
        query.append("  AND  comp_no            = ?     ");
        query.append("  AND  pmsched_status     != 'C'  ");
        
        return getJdbcTemplate().batchUpdate(query.toString(), new BatchPreparedStatementSetter()
        {

            @Override
            public int getBatchSize()
            {
                return list.size();
            }

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException
            {
                MaWoResultPointDetailDTO maWoResultPointDetailDTO = list.get(i);

                Object[] objects = getObject(new Object[] {
                        maWoResultPointDetailDTO.getWoPointId()
                        ,user.getCompNo()
                });
                
                for(int j=0;j<objects.length;j++){
                    ps.setObject(j+1, objects[j]);
                }
            }
            
        });
    }
    
    private String getWhere(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultPointListDTO maWoResultPointListDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.getStringEqualQuery("y.comp_no", loginUser.getCompNo());
    	
    	if (!"".equals(maWoResultMstrCommonDTO.getWkOrId())){
    		query.append("AND y.pm_id = (SELECT pm_id 												");
    		query.append("  			   FROM TAWORKORDER											");
        	query.append(" 				  WHERE comp_no = '"+loginUser.getCompNo()+"'				");
        	query.append(" 				  	AND wkor_id = '"+maWoResultMstrCommonDTO.getWkOrId()+"'	");
        	query.append("  			)															");
    	}
    	
    	if (!"".equals(maWoResultPointListDTO.getWoPointId()))
        {
            query.getAndQuery("x.wopoint_id", maWoResultPointListDTO.getWoPointId());
            return query.toString();
        }
    	
    	query.getAndNumKeyQuery("x.pm_point_id", maWoResultPointListDTO.getPmPointId());
        
        query.getSysCdQuery("x.pmsched_status", maWoResultPointListDTO.getPmSchedStatus(), "", "PMSCHED_STATUS", loginUser.getCompNo(), loginUser.getLangId());
        
    	return query.toString();
    }

	public String findTotalCount(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultPointListDTO maWoResultPointListDTO, User loginUser) throws Exception 
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("SELECT                                            ");
        query.append("       COUNT(1)                                   ");
        query.append("FROM TAWOPOINT x RIGHT OUTER JOIN TAPMPOINT y		");
        query.append("	ON x.comp_no = y.comp_no						");
        query.append(" AND x.pm_point_id = y.pm_point_id				");
        query.getAndNumKeyQuery("x.wkor_id", maWoResultMstrCommonDTO.getWkOrId());
        query.append("WHERE 1=1																	");
        query.append(this.getWhere(maWoResultMstrCommonDTO,maWoResultPointListDTO,loginUser));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
	}
	
}