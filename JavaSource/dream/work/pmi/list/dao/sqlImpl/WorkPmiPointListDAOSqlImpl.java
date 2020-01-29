package dream.work.pmi.list.dao.sqlImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.work.pmi.list.dao.WorkPmiPointListDAO;
import dream.work.pmi.list.dto.WorkPmiCommonDTO;
import dream.work.pmi.list.dto.WorkPmiPointDetailDTO;
import dream.work.pmi.list.dto.WorkPmiPointListDTO;

/**
 * 점검작업 점검 목록 dao
 * @author  kim21017
 * @version $Id: WorkPmiPointListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="workPmiPointListDAOTarget"
 * @spring.txbn id="workPmiPointListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkPmiPointListDAOSqlImpl extends BaseJdbcDaoSupportSql implements WorkPmiPointListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: WorkPmiPointListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param workPmiCommonDTO
     * @param workPmiPointListDTO
     * @param loginUser
     * @return List
     */
    public List findPointList(WorkPmiCommonDTO workPmiCommonDTO, WorkPmiPointListDTO workPmiPointListDTO, User loginUser, boolean isComplete) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        String joinType = "RIGHT OUTER JOIN";
        if(isComplete) {
            joinType = "INNER JOIN";
        }
        
        query.append("SELECT																	");
        query.append("		''					 				isDelCheck						");
        query.append("		,'' 								seqNo							");
        query.append("      ,y.pm_point_id                      pmPointId                       ");
        query.append("		,x.pminspoint_id 					pmInsPointId					");
        query.append("		,y.step_num 						stepNum							");
        query.append("		,(SELECT ISNULL(a.full_desc, a.description)							");
        query.append("		   FROM TAEQASMB a													");
        query.append("		  WHERE a.comp_no = y.comp_no										");
        query.append("			AND a.eqasmb_id = y.eqasmb_id)	eqAsmbDesc						");
        query.append("		,y.check_point 						'CHECKPOINT'					");
        query.append("		,y.check_method 					checkMethod						");
        query.append("		,y.fit_basis 						fitBasis						");
        query.append("		,dbo.SFACODE_TO_DESC(y.check_type,'CHECK_TYPE','SYS','','"+loginUser.getLangId()+"') 						checkTypeDesc	");
        query.append("		,dbo.SFACODE_TO_DESC(x.pm_point_rslt_status,'PM_POINT_RSLT_STATUS','SYS','','"+loginUser.getLangId()+"') 	rsltStatusDesc	");
        query.append("		,x.pm_point_rslt_status 		    pmPointRsltStatus				");
        query.append("		,x.comp_no                          compNo							");
        query.append("		,x.result_value 					resultValue						");
        query.append("		,y.check_min 						checkMin						");
        query.append("		,y.check_basis_val 					checkBasisVal					");
        query.append("		,y.check_max 						checkMax						");
        query.append("		,y.uom 								uom								");
        query.append("		,y.is_active 						isActive						");
        query.append("        ,(SELECT a.check_point                     ");
        query.append("             FROM TASTDCHECKPOINT a                   ");
        query.append("            WHERE a.std_check_point_id = y.stwrk_point_id)             stwrkPointDesc          ");
        query.append("        ,x.remark                         resultRemark        			");
        query.append("      ,y.check_type                       checkTypeId        				");
        query.append("		,(SELECT a.param2 													");
        query.append("			FROM TACDSYSD a													");
        query.append("			WHERE 1=1														");
        query.append("			AND a.list_type='CHECK_TYPE'									");
        query.append("			AND a.cdsysd_no = y.check_type) detailPage						");
        query.append("        , CASE WHEN (SELECT COUNT(*)										");
        query.append("                            FROM TAOBJDOC a								");
        query.append("							  WHERE a.comp_no = x.comp_no					");
        query.append("  						  AND  a.object_id = x.pminspoint_id			");
        query.append("  						  AND  a.object_type = 'PM_ROUTINE_POINT') > 0	");
        query.append("  		THEN 'Y'														");
        query.append("  		ELSE 'N'														");
        query.append("  		END												docIsYn			");
        query.append("FROM TAPMINSPOINT x "+joinType+" TAPMPOINT y							    ");
        query.append("	ON x.comp_no = y.comp_no												");
        query.append(" AND x.pm_point_id = y.pm_point_id										");
        query.getAndNumKeyQuery("x.pminslist_id", workPmiCommonDTO.getPminslistId());
        query.append("WHERE 1=1																	");
        query.append(this.getWhere(workPmiCommonDTO,workPmiPointListDTO,loginUser));
        query.getOrderByQuery("x.pminspoint_id", "y.step_num", workPmiCommonDTO.getOrderBy(), workPmiCommonDTO.getDirection());

        return getJdbcTemplate().queryForList(query.toString(workPmiCommonDTO.getIsLoadMaxCount(), workPmiCommonDTO.getFirstRow()));
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: WorkPmiPointListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int[] deletePointList(final List<WorkPmiPointDetailDTO> list, final User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("DELETE FROM TAPMINSPOINT          ");
        query.append("WHERE  pminspoint_id      = ?     ");
        query.append("  AND  comp_no            = ?     ");
        query.append("  AND  pmsched_status    != 'C'   ");
        
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
                WorkPmiPointDetailDTO workPmiPointDetailDTO = list.get(i);

                Object[] objects = getObject(new Object[] {
                        workPmiPointDetailDTO.getPmInsPointId()
                        ,user.getCompNo()
                });
                
                for(int j=0;j<objects.length;j++){
                    ps.setObject(j+1, objects[j]);
                }
            }
            
        });
    }
    
    private String getWhere(WorkPmiCommonDTO workPmiCommonDTO, WorkPmiPointListDTO workPmiPointListDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.getStringEqualQuery("y.comp_no", loginUser.getCompNo());
    	
    	if (!"".equals(workPmiCommonDTO.getPminslistId()))
        {
    		query.append("AND y.pm_id = (SELECT pm_id 													");
    		query.append("				   FROM TAPMINSLIST 											");
    		query.append("				  WHERE comp_no 	 = '"+loginUser.getCompNo()+"'				");
    		query.append("				    AND pminslist_id = '"+workPmiCommonDTO.getPminslistId()+"'	");
    		query.append("				 )																");
        }
    	
        query.getAndQuery("y.is_deleted", "N");

    	if (!"".equals(workPmiPointListDTO.getPmInsPointId()))
        {
            query.getAndNumKeyQuery("x.pminspoint_id", workPmiPointListDTO.getPmInsPointId());
            return query.toString();
            
        }
    	
    	query.getAndNumKeyQuery("x.pm_point_id", workPmiPointListDTO.getPmPointId());
    	
    	query.getSysCdQuery("x.pmsched_status", workPmiPointListDTO.getPmSchedStatus(), "", "PMSCHED_STATUS", loginUser.getCompNo(), loginUser.getCompNo());
    	
    	return query.toString();
    }
    
    public String findTotalCount(WorkPmiCommonDTO workPmiCommonDTO, WorkPmiPointListDTO workPmiPointListDTO, User loginUser, boolean isComplete) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	String joinType = "RIGHT OUTER JOIN";
        if(isComplete) {
            joinType = "INNER JOIN";
        }
        
        query.append("SELECT											");
        query.append("       COUNT(1)                                   ");
        query.append("FROM TAPMINSPOINT x "+joinType+" TAPMPOINT y	    ");
        query.append("	ON x.comp_no = y.comp_no						");
        query.append(" AND x.pm_point_id = y.pm_point_id				");
        query.getAndNumKeyQuery("x.pminslist_id", workPmiCommonDTO.getPminslistId());
        query.append("WHERE 1=1											");
        query.append(this.getWhere(workPmiCommonDTO,workPmiPointListDTO,loginUser));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);

    }
}