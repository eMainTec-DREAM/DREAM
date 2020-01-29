package dream.work.pm.check.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.work.pm.check.dao.WorkPmCheckDetailDAO;
import dream.work.pm.check.dto.WorkPmCheckCommonDTO;
import dream.work.pm.check.dto.WorkPmCheckDetailDTO;

/**
 * WorkPmCheck Page - Detail DAO implements
 * @author youngjoo38
 * @version $Id: WorkPmCheckDetailDAOOraImpl.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * 
 * @spring.bean id="workPmCheckDetailDAOTarget"
 * @spring.txbn id="workPmCheckDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkPmCheckDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkPmCheckDetailDAO
{

    public WorkPmCheckDetailDTO findDetail(WorkPmCheckCommonDTO workPmCheckCommonDTO, User user) 
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                            ");
        query.append("        x.std_check_point_id      checkPointId    ");
        query.append("      , x.std_check_point_no      checkPointNo    ");
        query.append("      , x.check_point_type        checkPointTypeId");
        query.append("      , SFACODE_TO_DESC( x.check_point_type , 'CHECK_POINT_TYPE','SYS',x.comp_no, ?)  checkPointTypeDesc  ");
        query.append("      , x.check_point             checkPoint      ");
        query.append("      , x.check_value             checkValue      ");
        query.append("      , x.uom                     uom             ");
        query.append("      , x.part_id                 partId          ");
        query.append("      , (SELECT part_no                           ");
        query.append("         FROM TAPARTS                             ");
        query.append("         WHERE part_id = x.part_id) 	partNo      ");
        query.append("      , (SELECT description                       ");
        query.append("         FROM TAPARTS                             ");
        query.append("         WHERE part_id = x.part_id) 	partDesc    ");
        query.append("      , x.plant	                 	plantId     ");
        query.append("      , (SELECT a.description                     ");
        query.append("           FROM TAPLANT a                         ");
        query.append("          WHERE a.comp_no = x.comp_no             ");
        query.append("            AND a.plant   = x.plant)  plantDesc   ");
        query.append("      , x.is_active               isActive        ");
        query.append("      , x.remark                  remark          ");
        query.append("      , x.bvalue                  bvalue          ");
        query.append("FROM TASTDCHECKPOINT x                            ");
        query.append("WHERE  1=1                                        ");
        query.append("AND    x.comp_no    = ?                           ");
        query.append("AND    x.std_check_point_id    = ?                ");
        
        Object[] objects = new Object[] {
                user.getLangId()
               , user.getCompNo()
               , workPmCheckCommonDTO.getCheckPointId()
        };
    
        WorkPmCheckDetailDTO workPmCheckDetailDTO = 
                (WorkPmCheckDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new WorkPmCheckDetailDTO()));
        
        return workPmCheckDetailDTO;
        
    }
    

    public int insertDetail(WorkPmCheckDetailDTO workPmCheckDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        int rtnValue  = 0;

        query.append("INSERT INTO TASTDCHECKPOINT(  ");
        query.append("  comp_no                     ");
        query.append(", std_check_point_id          ");
        query.append(", std_check_point_no          ");
        query.append(", check_point_type            ");
        query.append(", check_point                 ");
        query.append(", check_value                 ");
        query.append(", uom                         ");
        query.append(", part_id                     ");
        query.append(", is_active                   ");
        query.append(", remark                      ");
        query.append(", bvalue                      ");
        query.append(", plant                       ");
        query.append(")VALUES(                      ");
        query.append("   ?                          ");
        query.append(" , ?                          ");
        query.append(" , ?                          ");
        query.append(" , ?                          ");
        query.append(" , ?                          ");
        query.append(" , ?                          ");
        query.append(" , ?                          ");
        query.append(" , ?                          ");
        query.append(" , ?                          ");
        query.append(" , ?                          ");
        query.append(" , ?                          ");
        query.append(" , ?                          ");
        query.append(" )                            ");
        
        Object[] objects = new Object[] {
                user.getCompNo()
              , workPmCheckDetailDTO.getCheckPointId()
              , workPmCheckDetailDTO.getCheckPointNo()
              , workPmCheckDetailDTO.getCheckPointTypeId()
              , workPmCheckDetailDTO.getCheckPoint()
              , workPmCheckDetailDTO.getCheckValue()
              , workPmCheckDetailDTO.getUom()
              , workPmCheckDetailDTO.getPartId()
              , workPmCheckDetailDTO.getIsActive()
              , workPmCheckDetailDTO.getRemark()
              , workPmCheckDetailDTO.getBvalue()
              , workPmCheckDetailDTO.getPlantId()
        };
        
        rtnValue =  getJdbcTemplate().update(query.toString(), objects);
        
        return rtnValue;
    }

    public int updateDetail(WorkPmCheckDetailDTO workPmCheckDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        int rtnValue  = 0;

        query.append("UPDATE TASTDCHECKPOINT SET       ");
        query.append("  comp_no             = ?        ");
        query.append(", std_check_point_id  = ?        ");
        query.append(", std_check_point_no  = ?        ");
        query.append(", check_point_type    = ?        ");
        query.append(", check_point         = ?        ");
        query.append(", check_value         = ?        ");
        query.append(", uom                 = ?        ");
        query.append(", part_id             = ?        ");
        query.append(", is_active           = ?        ");
        query.append(", remark              = ?        ");
        query.append(", bvalue              = ?        ");
        query.append(", plant               = ?        ");
        query.append("WHERE  comp_no            = ?    ");
        query.append("  AND  std_check_point_id = ?    ");

        Object[] objects = new Object[] {
                user.getCompNo()
              , workPmCheckDetailDTO.getCheckPointId()
              , workPmCheckDetailDTO.getCheckPointNo()
              , workPmCheckDetailDTO.getCheckPointTypeId()
              , workPmCheckDetailDTO.getCheckPoint()
              , workPmCheckDetailDTO.getCheckValue()
              , workPmCheckDetailDTO.getUom()
              , workPmCheckDetailDTO.getPartId()
              , workPmCheckDetailDTO.getIsActive()
              , workPmCheckDetailDTO.getRemark()
              , workPmCheckDetailDTO.getBvalue()
              , workPmCheckDetailDTO.getPlantId()
              , user.getCompNo()
              , workPmCheckDetailDTO.getCheckPointId()
        };
        
        rtnValue =  getJdbcTemplate().update(query.toString(), objects);
        
        return rtnValue;
    }
}
