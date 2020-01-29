package dream.work.pm.list.dao.sqlImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.work.pm.list.dao.WorkPmiDInsPointListDAO;
import dream.work.pm.list.dto.WorkPmiDInsCommonDTO;
import dream.work.pm.list.dto.WorkPmiDInsPointDetailDTO;

/**
 * WorkPmiDInsPoint Page - List DAO implements
 * @author youngjoo38
 * @version $Id: WorkPmiDInsPointListDAOOraImpl.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * 
 * @spring.bean id="workPmiDInsPointListDAOTarget"
 * @spring.txbn id="workPmiDInsPointListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkPmiDInsPointListDAOSqlImpl  extends BaseJdbcDaoSupportSql implements WorkPmiDInsPointListDAO
{

    /**
     * grid find
     * @author  youngjoo38
     * @version $Id: workPmiDInsPointListDAO.java,v 1.0 2017/08/22 15:20:12 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param workPmiDInsCommonDTO
     * @return List
     */
    public List findList(WorkPmiDInsCommonDTO workPmiDInsCommonDTO, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                        ");
        query.append("  ''                          seqNo           ");
        query.append("  ,''                         isDelCheck      ");
        query.append("  , x.pm_point_id             pmPointId       ");
        query.append("  , x.pm_id                   pmId            ");
        query.append("  , x.step_num                stepNum         ");
        query.append("  , x.eqasmb_id               eqAsmbId        ");
        query.append("  , CASE WHEN x.eqasmb_id IS NULL             ");
        query.append("    THEN x.eqasmb_desc                        ");
        query.append("    ELSE (SELECT ISNULL(a.full_desc, a.description)    ");
        query.append("          FROM TAEQASMB a                     ");
        query.append("          WHERE a.comp_no = x.comp_no         ");
        query.append("              AND a.eqasmb_id = x.eqasmb_id)  ");
        query.append("    END                       eqAsmbDesc      ");
        query.append("  , x.check_point         AS 'checkPoint'     ");
        query.append("  , x.check_method            checkMethod     ");
        query.append("  , x.fit_basis               fitBasis        ");
        query.append("  , x.check_type              checkTypeId     ");
        query.append("  , dbo.SFACODE_TO_DESC(x.check_type, 'CHECK_TYPE', 'SYS', '"+user.getCompNo()+"','"+user.getLangId()+"')        				checkTypeDesc      	");
        query.append("  , dbo.SFACODE_TO_DESC(y.pm_point_rslt_status,'PM_POINT_RSLT_STATUS','SYS','"+user.getCompNo()+"', '"+user.getLangId()+"')   pmPointRsltStatus	");
        query.append("  , convert(nvarchar,y.result_value)+' / '+convert(nvarchar,y.result_value2)+' / '+convert(nvarchar,y.result_value3)			resultVal         	");
        query.append("  , x.check_min               checkMin        ");
        query.append("  , x.check_basis_val         checkBasisVal   ");
        query.append("  , x.check_max               checkMax        ");
        query.append("  , x.uom                     uom             ");
        query.append("  , x.is_active               isActive        ");
        query.append("  , y.pminsdpoint_id          pmInsDPointId   ");
        query.append("  , (SELECT a.check_point                     ");
        query.append("     FROM TASTDCHECKPOINT a                   ");
        query.append("     WHERE a.std_check_point_id = x.stwrk_point_id)	stwrkPointDesc	");
        query.append("  , y.REMARK                  insDetail       ");
        query.append("  , y.result_value			resultValue     ");
        query.append("  , y.result_value2         	resultValue2    ");
        query.append("  , y.result_value3           resultValue3	");
        query.append("  , y.pm_point_rslt_status	pmPointRsltStatusId	");
        query.append("  , CASE WHEN (SELECT COUNT(*)								");
        query.append("                 FROM TAOBJDOC a								");
        query.append("					WHERE a.comp_no = x.comp_no					");
        query.append("  				AND  a.object_id = y.pminsdpoint_id			");
        query.append("  				AND  a.object_type = 'PM_DAY_POINT') > 0	");
        query.append("  	THEN 'Y'												");
        query.append("  	ELSE 'N'												");
        query.append("  	END                                           docIsYn   ");
        query.append("FROM TAPMPOINT x JOIN TAPMINSDPOINT y         ");
        query.append("ON x.pm_point_id = y.pm_point_id              ");
        query.append("WHERE  1=1                                    ");
        query.append(this.getWhere(workPmiDInsCommonDTO, user));
        query.getOrderByQuery("x.pm_point_id", "x.step_num", workPmiDInsCommonDTO.getOrderBy(), workPmiDInsCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(workPmiDInsCommonDTO.getIsLoadMaxCount(), workPmiDInsCommonDTO.getFirstRow()));
    }

    /**
     * Filter 조건
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param workPmiDInsCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(WorkPmiDInsCommonDTO workPmiDInsCommonDTO, User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.getAndQuery("x.comp_no", user.getCompNo());
        
        if (!"".equals(workPmiDInsCommonDTO.getPmInsDPointId()))
        {
            query.getAndQuery("y.pminsdpoint_id", workPmiDInsCommonDTO.getPmInsDPointId());
        }
        
        query.getAndQuery("x.is_deleted", "N");
        
        query.getAndNumKeyQuery("y.pm_point_id", workPmiDInsCommonDTO.getPmPointId());

        query.getAndQuery("y.pminsdlist_id", workPmiDInsCommonDTO.getPmInsDListId());
        
        query.getSysCdQuery("y.pmsched_status", workPmiDInsCommonDTO.getPmSchedStatus(), "", "PMSCHED_STATUS", user.getCompNo(), user.getLangId());
        
        return query.toString();
    }

    /**
     * Filter 조건
     * @author  youngjoo38
     * @version $Id: workPmiDInsPointListDAO.java,v 1.0 2017/08/22 15:20:12 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param workPmiDInsCommonDTO
     * @return
     * @throws Exception
     */
    public int[] deleteList(final List<WorkPmiDInsPointDetailDTO> list, final User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("DELETE FROM TAPMINSDPOINT         ");
        query.append("WHERE  pminsdpoint_id     = ?     ");
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
                WorkPmiDInsPointDetailDTO workPmiDInsPointDetailDTO = list.get(i);

                Object[] objects = getObject(new Object[] {
                        workPmiDInsPointDetailDTO.getPmInsDPointId()
                        ,user.getCompNo()
                });
                
                for(int j=0;j<objects.length;j++){
                    ps.setObject(j+1, objects[j]);
                }
            }
            
        });
    }

    public String findTotalCount(WorkPmiDInsCommonDTO workPmiDInsCommonDTO, User user) {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                ");
        query.append("       COUNT(1)                       ");
        query.append("FROM TAPMPOINT x JOIN TAPMINSDPOINT y     ");
        query.append("ON x.pm_point_id = y.pm_point_id      ");
        query.append("WHERE  1=1                            ");
        query.append(this.getWhere(workPmiDInsCommonDTO,user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }    
}
