package dream.work.pm.list.dao.sqlImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.DateUtil;
import common.util.QuerySqlBuffer;
import dream.work.pm.list.dao.WorkPmListEquipListDAO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.WorkPmListEquipDetailDTO;

/**
 * 예방설비  dao
 * @author  kim21017
 * @version $Id: WorkPmListEquipListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="workPmListEquipListDAOTarget"
 * @spring.txbn id="workPmListEquipListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkPmListEquipListDAOSqlImpl extends BaseJdbcDaoSupportSql implements WorkPmListEquipListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: WorkPmListEquipListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPmMstrCommonDTO
     * @param loginUser
     * @return List
     */
    public List findEqList(MaPmMstrCommonDTO maPmMstrCommonDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                                                                   ");
        query.append("        ''                                                                          AS seqNo             ");
        query.append("      , ''                                                                          AS isDelCheck        ");
        query.append("      , (SELECT a.item_no                                                                                ");
        query.append("           FROM TAEQUIPMENT a                                                                            ");
        query.append("          WHERE a.comp_no = x.comp_no                                                                    ");
        query.append("            AND a.equip_id = x.equip_id)                                            AS itemNo            ");
        query.append("      , (SELECT a.item_no                                                                                ");
        query.append("           FROM TAEQUIPMENT a                                                                            ");
        query.append("          WHERE a.comp_no = x.comp_no                                                                    ");
        query.append("            AND a.equip_id = x.equip_id)                                            AS equipNo           ");
        query.append("      , (SELECT full_desc                                                                                ");
        query.append("           FROM TAEQASMB                                                                                 ");
        query.append("          WHERE comp_no = x.comp_no                                                                      ");
        query.append("            AND eqasmb_id = x.eqasmb_id)                                            AS eqAsmbDesc        ");
        query.append("      , (SELECT (SELECT aa.full_desc                                                                     ");
        query.append("                   FROM TAEQLOC aa                                                                       ");
        query.append("                  WHERE aa.eqloc_id = a.eqloc_id                                                         ");
        query.append("                    AND aa.comp_no = a.comp_no)                                                          ");
        query.append("           FROM TAEQUIPMENT a                                                                            ");
        query.append("          WHERE a.comp_no = x.comp_no                                                                    ");
        query.append("            AND a.equip_id = x.equip_id)                                            AS eqLocDesc         ");
        query.append("      , (SELECT a.description                                                                            ");
        query.append("           FROM TAEQUIPMENT a                                                                            ");
        query.append("          WHERE a.comp_no = x.comp_no                                                                    ");
        query.append("            AND a.equip_id = x.equip_id)                                            AS equipDesc         ");
        query.append("      , (SELECT b.item_no                                                                                ");
        query.append("           FROM TAEQUIPMENT b                                                                            ");
        query.append("          WHERE b.comp_no = x.comp_no                                                                    ");
        query.append("            AND b.equip_id = (SELECT a.p_equip_id                                                        ");
        query.append("                                FROM TAEQUIPMENT a                                                       ");
        query.append("                               WHERE a.comp_no = x.comp_no                                               ");
        query.append("                                 AND a.equip_id = x.equip_id))                      AS pitemNo           ");
        query.append("      , (SELECT b.description                                                                            ");
        query.append("           FROM TAEQUIPMENT b                                                                            ");
        query.append("          WHERE b.comp_no = x.comp_no                                                                    ");
        query.append("            AND b.equip_id = (SELECT a.p_equip_id                                                        ");
        query.append("                                FROM TAEQUIPMENT a                                                       ");
        query.append("                               WHERE a.comp_no = x.comp_no                                               ");
        query.append("                                 AND a.equip_id = x.equip_id))                      AS pequipDesc        ");
        query.append("      , (SELECT c.description                                                                            ");
        query.append("           FROM TADEPT c                                                                                 ");
        query.append("          WHERE c.comp_no = x.comp_no                                                                    ");
        query.append("            AND c.dept_id = (SELECT b.usage_dept                                                         ");
        query.append("                               FROM TAEQUIPMENT b                                                        ");
        query.append("                              WHERE b.comp_no = x.comp_no                                                ");
        query.append("                                AND b.equip_id = (SELECT a.p_equip_id                                    ");
        query.append("                                                    FROM TAEQUIPMENT a                                   ");
        query.append("                                                   WHERE a.comp_no = x.comp_no                           ");
        query.append("                                                     AND a.equip_id = x.equip_id))) AS pequipUsaDeptDesc ");
        query.append("      , x.pmequip_id                                                                AS pmEquipId         ");
        query.append("      , x.step_num                                                                  AS stepNum           ");
        query.append("      , x.description                                                               AS description       ");
        query.append("      , x.is_active                                                                 AS isActive,         ");
        query.getDate("x.init_wrk_date", "initWrkDate");
        query.append("      , x.last_sch_date                                                             AS lastSchDate       ");
        query.append("      , x.next_sch_date                                                             AS nextSchDate       ");
        query.append("      , x.last_cplt_date                                                            AS lastCpltDate      ");
        query.append("      , x.next_plan_date                                                            AS nextPlanDate      ");
        query.append("      , x.remark                                                                    AS remark            ");
        query.append("  FROM  TAPMEQUIP x                                                                                      ");
        query.append(" WHERE  1=1                                                                                              ");
        query.append(this.getWhere(maPmMstrCommonDTO,loginUser));
        query.getOrderByQuery("x.pmequip_id", "x.pmequip_id, x.is_active", maPmMstrCommonDTO.getOrderBy(), maPmMstrCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maPmMstrCommonDTO.getIsLoadMaxCount(), maPmMstrCommonDTO.getFirstRow()));

    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: WorkPmListEquipListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int[] updateDeleteTag(final List<WorkPmListEquipDetailDTO> list, final User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	final String deleteTime = DateUtil.getTimeStamp(user.getOffset(), "yyyyMMddHHmmss");
        
        query.append("UPDATE TAPMEQUIP SET  is_deleted  = 'Y' ");
        query.append("                    , delete_by   = ?   ");
        query.append("                    , delete_time = ?   ");
        query.append(" WHERE pmequip_id  = ?                  ");
        query.append("   AND comp_no     = ?                  ");
        
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
                WorkPmListEquipDetailDTO workPmListEquipDetailDTO = list.get(i);

                Object[] objects = getObject(new Object[] {
                        user.getEmpId()
                        ,deleteTime
                        ,workPmListEquipDetailDTO.getPmEquipId()
                        ,user.getCompNo()
                });
                
                for(int j=0;j<objects.length;j++){
                    ps.setObject(j+1, objects[j]);
                }
            }
            
        });
    }
    
    private String getWhere(MaPmMstrCommonDTO maPmMstrCommonDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.getAndNumKeyQuery("x.pm_id", maPmMstrCommonDTO.getPmId());
    	query.getStringEqualQuery("x.comp_no", loginUser.getCompNo());
        query.getStringEqualQuery("x.IS_DELETED", "N");
    	
        if (!"".equals(maPmMstrCommonDTO.getPmEquipId()))
        {
            query.getAndQuery("x.pmequip_id", maPmMstrCommonDTO.getPmEquipId());
            
            return query.toString();
        }
    	return query.toString();
    }

	@Override
	public String findTotalCount(MaPmMstrCommonDTO maPmMstrCommonDTO, User loginUser) throws Exception {
		QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                    ");
        query.append("       COUNT(1)           ");
        query.append("  FROM TAPMEQUIP x        ");
        query.append(" WHERE 1=1                ");
        query.append(this.getWhere(maPmMstrCommonDTO,loginUser));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QuerySqlBuffer.listToString(resultList);
	}
}