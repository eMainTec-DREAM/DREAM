package dream.work.pm.check.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.work.pm.check.dao.WorkPmCheckListDAO;
import dream.work.pm.check.dto.WorkPmCheckCommonDTO;

/**
 * WorkPmCheck Page - List DAO implements
 * @author youngjoo38
 * @version $Id: WorkPmCheckListDAOOraImpl.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * 
 * @spring.bean id="workPmCheckListDAOTarget"
 * @spring.txbn id="workPmCheckListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkPmCheckListDAOOraImpl  extends BaseJdbcDaoSupportOra implements WorkPmCheckListDAO
{

    /**
     * grid find
     * @author  youngjoo38
     * @version $Id: workPmCheckListDAO.java,v 1.0 2017/08/22 15:20:12 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param workPmCheckCommonDTO
     * @return List
     */
    public List findList(WorkPmCheckCommonDTO workPmCheckCommonDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                            ");
        query.append("      ''                          seqNo           ");
        query.append("      ,''                         isDelCheck      ");
        query.append("      , x.std_check_point_id      checkPointId    ");
        query.append("      , x.std_check_point_no      checkPointNo    ");
        query.append("      , x.check_point_type        checkPointTypeId  ");
        query.append("      , SFACODE_TO_DESC( x.check_point_type , 'CHECK_POINT_TYPE','SYS',x.comp_no, '"+user.getLangId()+"')  checkPointTypeDesc  ");
        query.append("      , x.check_point             checkPoint      ");
        query.append("      , x.check_value             checkValue      ");
        query.append("      , x.uom                     uom             ");
        query.append("      , x.part_id                 partId          ");
        query.append("      , x.is_active               isActive        ");
        query.append("      , x.remark                  remark          ");
        query.append("      , x.bvalue                  bvalue          ");
        query.append("      , (SELECT a.description                     ");
        query.append("           FROM TAPLANT a                         ");
        query.append("          WHERE a.comp_no = x.comp_no             ");
        query.append("            AND a.plant   = x.plant) plantDesc    ");
        query.append("FROM TASTDCHECKPOINT x                            ");
        query.append("WHERE  1=1                                        ");
        
        query.append(this.getWhere(workPmCheckCommonDTO, user));
        query.getOrderByQuery("x.std_check_point_no", workPmCheckCommonDTO.getOrderBy(), workPmCheckCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(workPmCheckCommonDTO.getIsLoadMaxCount(), workPmCheckCommonDTO.getFirstRow()));
    }

    /**
     * Filter 조건
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param workPmCheckCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(WorkPmCheckCommonDTO workPmCheckCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        
        if(!"".equals(workPmCheckCommonDTO.getCheckPointId())){
            query.getAndQuery("x.std_check_point_id", workPmCheckCommonDTO.getCheckPointId());
            return query.toString();
        }
        
        // 점검항목 분류
        query.getSysCdQuery("x.check_point_type", workPmCheckCommonDTO.getFilterCheckPointTypeId(), workPmCheckCommonDTO.getFilterCheckPointTypeDesc(), "CHECK_POINT_TYPE", user.getCompNo(),user.getLangId());
        
        // 점검항목
        query.getLikeQuery("x.check_point", workPmCheckCommonDTO.getFilterCheckPoint());
        
        // 공장
        query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = '"+user.getCompNo()+"' AND a.plant = x.plant )", 
        		workPmCheckCommonDTO.getFilterPlant(), workPmCheckCommonDTO.getFilterPlantDesc());
        
        return query.toString();
    }

    /**
     * Filter 조건
     * @author  youngjoo38
     * @version $Id: workPmCheckListDAO.java,v 1.0 2017/08/22 15:20:12 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param workPmCheckCommonDTO
     * @return
     * @throws Exception
     */
    public int deleteList(String id, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();

        query.append("DELETE FROM TASTDCHECKPOINT   ");
        query.append("WHERE std_check_point_id = ?  ");
        query.append("  AND comp_no = ?             ");
        
        Object[] objects = new Object[] {   
                id
                ,user.getCompNo()
        };
        return this.getJdbcTemplate().update(query.toString(), objects);
    }

    public String findTotalCount(
            WorkPmCheckCommonDTO workPmCheckCommonDTO, User user) {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                ");
        query.append("       COUNT(1)                       ");
        query.append("FROM   TASTDCHECKPOINT x              ");
        query.append("WHERE  1=1                            ");
        query.append(this.getWhere(workPmCheckCommonDTO,user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }    
}
