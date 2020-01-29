package dream.work.rpt.mapmtrend.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.work.rpt.mapmtrend.dao.MaPmTrendDetailDAO;
import dream.work.rpt.mapmtrend.dto.MaPmTrendCommonDTO;
import dream.work.rpt.mapmtrend.dto.MaPmTrendDetailDTO;

/**
 * 상세 목록 dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maPmTrendDetailDAOTarget"
 * @spring.txbn id="maPmTrendDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPmTrendDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaPmTrendDetailDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPmTrendDetailDTO
     * @param loginUser
     * @return List
     */
    public List findDetail(MaPmTrendCommonDTO maPmTrendCommonDTO, MaPmTrendDetailDTO maPmTrendDetailDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT ROW_NUMBER() OVER(ORDER BY y.end_date) AS ID   ");
        query.append("      ,CONVERT(VARCHAR(5), CONVERT(DATE, y.end_date), 101) days");
        query.append("      ,x.result_value cnt                             ");
        query.append("      ,z.check_min minval                             ");
        query.append("      ,z.check_max maxval                             ");
        query.append("      ,z.check_point 'checkPoint'                       ");

        if("RINS".equals(maPmTrendDetailDTO.getPmType()))
        {
            query.append("FROM TAPMINSPOINT x, TAPMINSLIST y, TAPMPOINT z   ");
            query.append("WHERE x.comp_no = y.comp_no                       ");
            query.append("AND y.comp_no = z.comp_no                         ");
            query.append("AND x.pm_point_id = z.pm_point_id                 ");
            query.append("AND x.pminslist_id = y.pminslist_id               ");
            query.getAndQuery("y.equip_id", maPmTrendDetailDTO.getEquipId());
            //query.append("AND y.pmsched_status = 'C'                        ");
        }
        else if("DINS".equals(maPmTrendDetailDTO.getPmType()))
        {
            query.append("FROM TAPMINSDPOINT x, TAPMINSDLIST y, TAPMPOINT z ");
            query.append("WHERE x.comp_no = y.comp_no                       ");
            query.append("AND y.comp_no = z.comp_no                         ");
            query.append("AND x.pm_point_id = z.pm_point_id                 ");
            query.append("AND x.pminsdlist_id = y.pminsdlist_id             ");
            query.getAndQuery("y.equip_id", maPmTrendDetailDTO.getEquipId());
            //query.append("AND y.pmsched_status = 'C'                        ");
        }
        else// ("INS".equals(maPmTrendDetailDTO.getPmType()))
        {
            query.append("FROM TAWOPOINT x, TAWORKORDER y , TAPMPOINT z     ");
            query.append("WHERE x.comp_no = y.comp_no                       ");
            query.append("  AND y.comp_no = z.comp_no                       ");
            query.append("  AND x.wkor_id = y.wkor_id                       ");
            query.append("  AND x.pm_point_id = z.pm_point_id               ");
            query.append("  AND EXISTS(SELECT woequip_id FROM TAWOEQUIP     ");
            query.append("             WHERE comp_no = y.comp_no            ");
            query.append("             AND wkor_id = y.wkor_id              ");
            query.getAndQuery("equip_id", maPmTrendDetailDTO.getEquipId());
            query.append("             )                                    ");
        }
        query.getAndQuery("x.pm_point_id", maPmTrendDetailDTO.getPmPointId());
        query.getAndQuery("x.pm_id", maPmTrendDetailDTO.getPmId());
        query.getAndDateQuery("y.end_date", maPmTrendDetailDTO.getStartDate(), maPmTrendDetailDTO.getEndDate());
        query.getAndQuery("x.comp_no", maPmTrendCommonDTO.getCompNo());
        query.append("ORDER BY y.end_date                                   ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    public String getWhere(MaPmTrendDetailDTO maPmTrendDetailDTO,User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        return query.toString();
    }
    @Override
    public int createWo(MaPmTrendDetailDTO maPmTrendDetailDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT SQAWKOR_ID.nextval ");
        query.append("FROM DUAL                 ");
        
        String wkorId = QuerySqlBuffer.listToString(this.getJdbcTemplate().queryForList(query.toString()));
        
        query = new QuerySqlBuffer();
        int rtnValue  = 0;
        query.append("INSERT INTO TAWORKORDER(                          ");
        query.append("        comp_no     , wkor_id       , wo_no       ");
        query.append("      , wkor_date   , description   , wo_status   ");
        query.append("      , wo_type     , pm_type       , dept_id     ");
        query.append("      , emp_id      , wo_gen_type )               ");

        query.append("SELECT                                                                                                        ");
        query.append("  x.comp_no,                  '"+wkorId+"',           '"+wkorId+"',                                           ");
        query.append("  convert(varchar, getdate(), 112) , x.description         , 'P'                                                   ");
        query.append(" ,'"+maPmTrendDetailDTO.getSelectedWoType()+"',    '"+maPmTrendDetailDTO.getSelectedPmType()+"'      , '"+loginUser.getDeptId()+"' ");
        query.append(" , '"+loginUser.getEmpId()+"'          ,'WOPOINT'                                                                                ");
        query.append("FROM (                                                                                                        ");
        
        if("INS".equals(maPmTrendDetailDTO.getPmType()))
        {
        query.append("SELECT                                                                                                        ");
        query.append("     v.comp_no                                                                                                ");
        query.append("     ,c.description+' '+                                                                                      ");
        query.append("      (SELECT description FROM TAEQASMB WHERE comp_no = a.comp_no AND eqasmb_id = a.eqasmb_id)+' '+           ");
        query.append("      (SELECT check_point FROM TAPMPOINT WHERE comp_no = v.comp_no AND pm_point_id = v.pm_point_id) AS description      ");
        query.append("     , v.pm_point_id                                                                                          ");
        query.append("     , a.pm_id                                                                                                ");
        query.append("     , c.equip_id                                                                                             ");
        query.append("FROM TAWOPOINT v INNER JOIN TAWORKORDER a                                                                     ");
        query.append("ON v.comp_no = a.comp_no                                                                                      ");
        query.append("AND v.wkor_id = a.wkor_id                                                                                     ");
        query.append("INNER JOIN TAWOEQUIP b                                                                                        ");
        query.append("ON a.comp_no = b.comp_no                                                                                      ");
        query.append("AND a.wkor_id = b.wkor_id                                                                                     ");
        query.append("INNER JOIN TAEQUIPMENT c                                                                                      ");
        query.append("ON b.comp_no = c.comp_no                                                                                      ");
        query.append("AND b.equip_id = c.equip_id                                                                                   ");
        query.append("WHERE  1=1                                                                                                    ");
        query.append("ORDER BY LENGTH(description) DESC                                                                             ");
        query.append(") x                                                                                                           ");
        query.append("WHERE ROWNUM = 1                                                                                              ");

        }
        else if("RINS".equals(maPmTrendDetailDTO.getPmType()))
        {
            
        query.append("SELECT                                                                                                        ");
        query.append("     v.comp_no                                                                                                ");
        query.append("    ,(SELECT a.description FROM TAEQUIPMENT a WHERE a.equip_id = v.equip_id) +' '+                            ");
        query.append("      (SELECT a.description FROM TAEQASMB a WHERE a.comp_no = v.comp_no AND a.eqasmb_id =                     ");
        query.append("      (SELECT b.eqasmb_id FROM TAPMPOINT b WHERE b.comp_no = v.comp_no AND b.pm_point_id = v.pm_point_id))+' '+             ");
        query.append("      (SELECT c.check_point FROM TAPMPOINT c WHERE c.comp_no = v.comp_no AND c.pm_point_id = v.pm_point_id) AS description  ");
        query.append("    , v.pm_point_id                                                                                           ");
        query.append("    , v.pm_id                                                                                                 ");
        query.append("    , v.equip_id                                                                                              ");
        query.append("FROM TAPMINSPOINT v                                                                                           ");
        query.append("WHERE 1=1                                                                                                     ");
        query.append("ORDER BY LENGTH(description) DESC                                                                             ");
        query.append(") x                                                                                                           ");
        query.append("WHERE ROWNUM = 1                                                                                              ");
        
        }
        
        else if("DINS".equals(maPmTrendDetailDTO.getPmType()))
        {
            
        query.append("SELECT                                                                                                        ");
        query.append("     v.comp_no                                                                                                ");
        query.append("    ,(SELECT a.description FROM TAEQUIPMENT a WHERE a.equip_id = v.equip_id) +' '+                            ");
        query.append("      (SELECT a.description FROM TAEQASMB a WHERE a.comp_no = v.comp_no AND a.eqasmb_id =                     ");
        query.append("      (SELECT b.eqasmb_id FROM TAPMPOINT b WHERE b.comp_no = v.comp_no AND b.pm_point_id = v.pm_point_id))+' '+             ");
        query.append("      (SELECT c.check_point FROM TAPMPOINT c WHERE c.comp_no = v.comp_no AND c.pm_point_id = v.pm_point_id) AS description  ");
        query.append("    , v.pm_point_id                                                                                           ");
        query.append("    , v.pm_id                                                                                                 ");
        query.append("    , v.equip_id                                                                                              ");
        query.append("FROM TAPMINSDPOINT v                                                                                          ");
        query.append("WHERE 1=1                                                                                                     ");
        query.append("ORDER BY LENGTH(description) DESC                                                                             ");
        query.append(") x                                                                                                           ");
        query.append("WHERE ROWNUM = 1                                                                                              ");
             
        }
        
        query.getAndQuery("x.pm_point_id", maPmTrendDetailDTO.getPmPointId());
        query.getAndQuery("x.pm_id", maPmTrendDetailDTO.getPmId());
        query.getAndQuery("x.equip_id", maPmTrendDetailDTO.getEquipId());
        query.getAndQuery("x.comp_no", loginUser.getCompNo());
        
        rtnValue = this.getJdbcTemplate().update(query.toString());
        
        return rtnValue;
    }
}