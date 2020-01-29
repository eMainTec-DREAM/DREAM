package dream.work.rpt.pmi.point.value.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.work.rpt.pmi.point.value.dao.WorkRptPmiPointValueListDAO;
import dream.work.rpt.pmi.point.value.dto.WorkRptPmiPointValueCommonDTO;

/**
 * 정기점검항목결과 - 목록 DAOImpl
 * @author  nhkim8548
 * @version $Id: WorkRptPmiPointValueListDAO.java, v1.0 2019/07/10 11:06:12 nhkim8548 Exp $
 * @since   1.0
 * @spring.bean id="workRptPmiPointValueListDAOTarget"
 * @spring.txbn id="workRptPmiPointValueListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkRptPmiPointValueListDAOSqlImpl extends BaseJdbcDaoSupportSql implements WorkRptPmiPointValueListDAO
{
    /**
     * grid find
     * @author  nhkim8548
     * @version $Id: WorkRptPmiPointValueListDAO.java, v1.0 2019/07/10 11:07:12 nhkim8548 Exp $
     * @since   1.0
     * 
     * @param   workRptPmiPointValueCommonDTO
     * @param   user
     * @return  List
     */
    public List findPointValueList(WorkRptPmiPointValueCommonDTO workRptPmiPointValueCommonDTO, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                                                                                                ");
        query.append("          ''                                                                                                      AS SEQNO            ");
        query.append("        , x.pminslist_id                                                                                          AS pmInsNo          ");
        query.append("        , (SELECT full_desc FROM TAEQLOC a WHERE a.comp_no = z.comp_no AND a.eqloc_id = w.eqloc_id)               AS eqLocDesc        ");
        query.append("        , w.item_no                                                                                               AS equipNo          ");
        query.append("        , w.description                                                                                           AS equipName        ");
        query.append("        , x.end_date                                                                                              AS inspectDate      ");
        query.append("        , (SELECT description FROM TAEQASMB a WHERE a.comp_no = x.comp_no AND a.eqasmb_id = z.eqasmb_id)          AS pmAsmbDesc       ");
        query.append("        , z.check_point                                                                                           AS checkPointDesc   ");
        query.append("        , z.check_method                                                                                          AS checkMethodDesc  ");
        query.append("        , z.fit_basis                                                                                             AS fitBasis         ");
        query.append("        , dbo.SFACODE_TO_DESC(z.check_type,'CHECK_TYPE','SYS','','"+ user.getCompNo() +"')                        AS checkType        ");
        query.append("        , z.check_min                                                                                             AS checkMinVal      ");
        query.append("        , z.check_basis_val                                                                                       AS checkBasisVal    ");
        query.append("        , z.check_max                                                                                             AS checkMaxVal      ");
        query.append("        , z.uom                                                                                                   AS uom              ");
        query.append("        , dbo.SFACODE_TO_DESC(y.pm_point_rslt_status,'PM_POINT_RSLT_STATUS','SYS','','"+ user.getCompNo() +"')    AS rsltStatusDesc   ");
        query.append("        , y.result_value                                                                                          AS resultVal        ");
        query.append("        , x.remark                                                                                                AS inspectRemark    ");
        query.append("  FROM TAPMINSLIST x INNER JOIN TAPMINSPOINT y                                                                                        ");
        query.append("                        ON y.comp_no = x.comp_no                                                                                      ");
        query.append("                       AND y.pminslist_id = x.pminslist_id                                                                            ");
        query.append("                     INNER JOIN TAPMPOINT z                                                                                           ");
        query.append("                        ON z.comp_no  = x.comp_no                                                                                     ");
        query.append("                       AND z.pm_point_id = y.pm_point_id                                                                              ");
        query.append("                     INNER JOIN TAEQUIPMENT w                                                                                         ");
        query.append("                        ON w.comp_no  = x.comp_no                                                                                     ");
        query.append("                       AND w.equip_id = x.equip_id                                                                                    ");
        query.append(" WHERE 1 = 1                                                                                                                          ");
        query.append(this.getWhere(workRptPmiPointValueCommonDTO, user));
        query.getOrderByQuery("x.comp_no", "x.pminslist_id DESC", workRptPmiPointValueCommonDTO.getOrderBy(), workRptPmiPointValueCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(workRptPmiPointValueCommonDTO.getIsLoadMaxCount(), workRptPmiPointValueCommonDTO.getFirstRow()));
    }
    
    private String getWhere(WorkRptPmiPointValueCommonDTO workRptPmiPointValueCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        // 회사번호
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        
        // 삭제여부
        query.getStringEqualQuery("x.is_deleted", "N");
        query.getStringEqualQuery("z.is_deleted", "N");
        query.getStringEqualQuery("w.is_deleted", "N");
        
        // 사용여부
        query.getStringEqualQuery("z.is_active", "Y");
        
        // 점검일자
        query.getAndDateQuery("x.end_date", workRptPmiPointValueCommonDTO.getFilterInsStartDate(), workRptPmiPointValueCommonDTO.getFilterInsEndDate());

        // 공장
        query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM TAPLANT a WHERE a.comp_no = '"+ user.getCompNo() +"' AND a.plant = x.plant )", 
                workRptPmiPointValueCommonDTO.getFilterPlantId(), workRptPmiPointValueCommonDTO.getFilterPlantDesc());
        
        // 작업부서
        query.getDeptLevelQuery("x.dept_id", workRptPmiPointValueCommonDTO.getFilterWorkDeptId(), workRptPmiPointValueCommonDTO.getFilterWorkDeptDesc(), user.getCompNo());
        
        // 작업그룹
        query.getWkCtrLevelQuery("x.wkctr_id", workRptPmiPointValueCommonDTO.getFilterWkCtrId(), workRptPmiPointValueCommonDTO.getFilterWkCtrDesc(), user.getCompNo());
        
        // 작업담당자
        query.getCodeLikeQuery("x.emp_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = x.emp_id AND a.comp_no='"+ user.getCompNo() +"')", 
                workRptPmiPointValueCommonDTO.getFilterWoManagerId(), workRptPmiPointValueCommonDTO.getFilterWoManagerDesc());
        
        // 설비
        query.getCodeLikeQuery("x.equip_id", "(SELECT a.description FROM TAEQUIPMENT a WHERE a.equip_id = x.equip_id AND a.comp_no='"+user.getCompNo()+"')", 
                workRptPmiPointValueCommonDTO.getFilterEquipId(), workRptPmiPointValueCommonDTO.getFilterEquipName());
        
        // 설비위치
        query.getEqLocLevelQuery("w.eqloc_id", workRptPmiPointValueCommonDTO.getFilterEqLocId(), workRptPmiPointValueCommonDTO.getFilterEqLocDesc(), user.getCompNo());
        
        // 설비종류
        query.getEqCtgLevelQuery("w.eqctg_id", workRptPmiPointValueCommonDTO.getFilterEqCtgId(), workRptPmiPointValueCommonDTO.getFilterEqCtgDesc(), user.getCompNo());
        
        // 예방작업번호
        query.getLikeQuery("x.pminslist_id", workRptPmiPointValueCommonDTO.getFilterPmInsListNo());
        
        // 점검결과
        query.getSysCdQuery("y.pm_point_rslt_status", workRptPmiPointValueCommonDTO.getFilterRsltStatusId(), workRptPmiPointValueCommonDTO.getFilterRsltStatusDesc(), "PM_POINT_RSLT_STATUS", user.getCompNo(), user.getLangId());
        
        return query.toString();
    }
    
    public String findTotalCount(WorkRptPmiPointValueCommonDTO workRptPmiPointValueCommonDTO, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                        ");
        query.append("          COUNT(1)                                            ");
        query.append("  FROM TAPMINSLIST x INNER JOIN TAPMINSPOINT y                ");
        query.append("                        ON y.comp_no = x.comp_no              ");
        query.append("                       AND y.pminslist_id = x.pminslist_id    ");
        query.append("                     INNER JOIN TAPMPOINT z                   ");
        query.append("                        ON z.comp_no  = y.comp_no             ");
        query.append("                       AND z.pm_point_id = y.pm_point_id      ");
        query.append("                     INNER JOIN TAEQUIPMENT w                 ");
        query.append("                        ON w.comp_no  = z.comp_no             ");
        query.append("                       AND w.equip_id = y.equip_id            ");
        query.append(" WHERE 1 = 1                                                  ");
        query.append(this.getWhere(workRptPmiPointValueCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QuerySqlBuffer.listToString(resultList);
    }
}