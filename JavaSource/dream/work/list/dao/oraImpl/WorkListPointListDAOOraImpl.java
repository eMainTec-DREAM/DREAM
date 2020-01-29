package dream.work.list.dao.oraImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.work.list.dao.WorkListPointListDAO;
import dream.work.list.dto.WorkListPointDetailDTO;
import dream.work.list.dto.WorkListPointListDTO;
import dream.work.list.dto.WorkListPtrlResultCommonDTO;

/**
 * WorkListPoint Page - List DAO implements
 * @author youngjoo38
 * @version $Id: WorkListPointListDAOOraImpl.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * 
 * @spring.bean id="workListPointListDAOTarget"
 * @spring.txbn id="workListPointListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkListPointListDAOOraImpl  extends BaseJdbcDaoSupportOra implements WorkListPointListDAO
{
 
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id: workListPointListDAO.java,v 1.0 2017/08/22 15:20:12 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param workListPointListDTO
     * @return List
     */
    public List findList(WorkListPtrlResultCommonDTO workListPtrlResultCommonDTO, WorkListPointListDTO workListPointListDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                                        ");
        query.append("       ''                                                     AS seqNo                        ");
        query.append("     , ''                                                     AS isDelCheck                   ");
        query.append("     , pmptrlsched_id                                         AS pmPtrlSchedId                "); 
        query.append("     , check_by                                               AS ptrlInspectorId              "); 
        query.append("     , (SELECT emp_name                                                                       ");
        query.append("        FROM TAEMP a                                                                          ");
        query.append("        WHERE a.emp_id = x.check_by)                          AS ptrlInspectorDesc            "); 
        query.append("     , (SELECT end_date                                                                       ");
        query.append("        FROM TAPMPTRLRSLTLIST a                                                               ");
        query.append("        WHERE a.pmptrlrsltlist_id = x.pmptrlrsltlist_id)      As ptrlComDate                  ");
        query.append("     , (SELECT end_time                                                                       ");
        query.append("        FROM TAPMPTRLRSLTLIST a                                                               ");
        query.append("        WHERE a.pmptrlrsltlist_id = x.pmptrlrsltlist_id)      AS ptrlComTime                  "); 
        query.append("     , (SELECT end_date                                                                       ");
        query.append("        FROM TAPMPTRLRSLTLIST a                                                               ");
        query.append("        WHERE a.pmptrlrsltlist_id = x.pmptrlrsltlist_id)                                      ");
        query.append("        || ' ' ||                                                                             ");
        query.append("       (SELECT end_time                                                                       ");
        query.append("        FROM TAPMPTRLRSLTLIST a                                                               ");
        query.append("        WHERE a.pmptrlrsltlist_id = x.pmptrlrsltlist_id)      AS ptrlComDateTime              "); 
        query.append("     , pm_point_id                                            AS pmPointId                    "); 
        query.append("     , (SELECT check_point                                                                    ");
        query.append("        FROM TAPMPOINT a                                                                      ");
        query.append("        WHERE a.pm_point_id = x.pm_point_id)                  AS pmPointDesc                  ");
        query.append("     , (SELECT step_num                                                                       ");
        query.append("        FROM TAPMPOINT a                                                                      ");
        query.append("        WHERE a.pm_point_id = x.pm_point_id)                  AS ptrlStepNum                  ");
        query.append("     , pm_point_rslt_status                                   AS pmPointRsltStatusId          "); 
        query.append("     , SFACODE_TO_DESC( x.pm_point_rslt_status, 'PM_POINT_RSLT_STATUS','SYS',x.comp_no, '"+user.getLangId()+"')    AS pmPointRsltStatusDesc ");
        query.append("     , pmsched_status                                         AS pmSchedStatus                "); 
        query.append("     , description                                            AS description                  "); 
        query.append("     , eqctg_id                                                                               ");
        query.append("     , equip_id                                                                               ");
        query.append("     , pmequip_id                                                                             ");
        query.append("     , (SELECT description                                                                    ");
        query.append("        FROM TAPMEQUIP a                                                                      ");
        query.append("        WHERE a.pmequip_id = x.pmequip_id)                    AS ptrlAreaDesc                 ");  
        query.append("     , pmptrlrsltlist_id                                      AS pmPtrlRsltListId             ");
        query.append("     , pmptrlrsltpoint_id                                     AS pmPtrlRsltPointId            ");
        query.append("     , remark                                                 AS remark                       ");
        query.append("FROM TAPMPTRLRSLTPOINT x                                                                      ");
        
        query.append("WHERE  1=1                                                        ");
        query.getStringEqualQuery("x.comp_no", user.getCompNo());

        query.getAndQuery("x.pmptrlrsltpoint_id", workListPointListDTO.getPmPtrlRsltPointId());
        query.getAndQuery("x.pmptrlrsltlist_id", workListPointListDTO.getFilterPmPtrlRsltListId());
        query.getAndNumKeyQuery("x.pm_point_id", workListPointListDTO.getPmPointId());
        query.getSysCdQuery("x.pmsched_status", workListPointListDTO.getPmSchedStatus(), "", "PMSCHED_STATUS", user.getCompNo(), user.getLangId());
        query.getOrderByQuery("x.pm_point_id", workListPtrlResultCommonDTO.getOrderBy(), workListPtrlResultCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(workListPtrlResultCommonDTO.getIsLoadMaxCount(), workListPtrlResultCommonDTO.getFirstRow()));

    }

    /**
     * Filter Á¶°Ç
     * @author  youngjoo38
     * @version $Id: workListPointListDAO.java,v 1.0 2017/08/22 15:20:12 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param workListPointListDTO
     * @return
     * @throws Exception
     */
    public int[] deleteList(final List<WorkListPointDetailDTO> list, final User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();

        query.append("DELETE FROM TAPMPTRLRSLTPOINT   ");
        query.append("WHERE pmPtrlRsltPoint_Id = ?     ");
        query.append("  AND comp_no = ?               ");
        query.append("  AND pmsched_status != 'C'     ");
        
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
                WorkListPointDetailDTO workListPointDetailDTO = list.get(i);

                Object[] objects = new Object[] {
                        workListPointDetailDTO.getPmPtrlRsltPointId()
                        ,user.getCompNo()
                };
                
                for(int j=0;j<objects.length;j++){
                    ps.setObject(j+1, objects[j]);
                }
            }
            
        });
    }

    public String findTotalCount(WorkListPtrlResultCommonDTO workListPtrlResultCommonDTO,
            WorkListPointListDTO workListPointListDTO, User user) throws Exception 
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       COUNT(1)                                           ");
        query.append("FROM   TAPMPTRLRSLTPOINT x                                ");
        query.append("WHERE  1=1                                                ");
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        query.getAndQuery("x.pmptrlrsltpoint_id", workListPointListDTO.getPmPtrlRsltPointId());
        query.getAndQuery("x.pmptrlrsltlist_id", workListPointListDTO.getFilterPmPtrlRsltListId());
        query.getAndNumKeyQuery("x.pm_point_id", workListPointListDTO.getPmPointId());
        query.getSysCdQuery("x.pmsched_status", workListPointListDTO.getPmSchedStatus(), "", "PMSCHED_STATUS", user.getCompNo(), user.getLangId());

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }    
}
