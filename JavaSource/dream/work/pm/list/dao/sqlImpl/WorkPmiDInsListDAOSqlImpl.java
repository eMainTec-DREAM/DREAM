package dream.work.pm.list.dao.sqlImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.DateUtil;
import common.util.QuerySqlBuffer;
import dream.work.pm.list.dao.WorkPmiDInsListDAO;
import dream.work.pm.list.dto.WorkPmiDInsCommonDTO;
import dream.work.pm.list.dto.WorkPmiDInsDetailDTO;

/**
 * 일상점검목록 dao
 * @author  youngjoo38
 * @version $Id: workPmiDInsListDAO.java,v 1.0 2015/12/02 09:14:12 youngjoo38 Exp $
 * @since   1.0
 * @spring.bean id="workPmiDInsListDAOTarget"
 * @spring.txbn id="workPmiDInsListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkPmiDInsListDAOSqlImpl extends BaseJdbcDaoSupportSql implements WorkPmiDInsListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id: workPmiDInsCommonDTO.java,v 1.0 2015/12/02 09:14:12 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param workPmiDInsCommonDTO
     * @return List
     */
    public List findList(WorkPmiDInsCommonDTO workPmiDInsCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                                                ");
        query.append("        ''                                                            seqNo           ");
        query.append("      , ''                                                            isDelCheck      ");
        query.append("      , x.pmInsDlist_id                                               pmInsDListId    ");
        query.append("      , x.pmInsDlist_id                                               pmInsDListNo    ");
        query.append("      , x.description                                                 description     ");
        query.append("      , x.wkor_date                                                   wkorDate        ");
        query.append("      , x.equip_id                                                    equipId         ");
        query.append("      ,(SELECT a.item_no                                                              ");
        query.append("        FROM TAEQUIPMENT a                                                            ");
        query.append("        WHERE a.comp_no = x.comp_no                                                   ");
        query.append("        AND a.equip_id = x.equip_id)                                  equipNo         ");
        query.append("      , x.measure_time                                                measureTime     ");
        query.append("      , x.eqruntime                                                   operatingTime   ");
        query.append("      , (SELECT b.full_desc                                                           ");
        query.append("          FROM TAEQUIPMENT a, TAEQLOC b                                               ");
        query.append("         WHERE a.comp_no = b.comp_no                                                  ");
        query.append("           AND a.eqloc_id = b.eqloc_id                                                ");
        query.append("           AND a.comp_no = x.comp_no                                                  ");
        query.append("           AND a.equip_id = x.equip_id)                               eqLocDesc       ");
        query.append("      , (SELECT description                                                           ");
        query.append("         FROM TAEQUIPMENT                                                             ");
        query.append("         WHERE comp_no = x.comp_no                                                    ");
        query.append("         AND equip_id = x.equip_id)                                   equipDesc       ");
        query.append("      , x.dept_id                                                     deptId          ");
        query.append("      , (SELECT description                                                           ");
        query.append("           FROM TADEPT                                                                ");
        query.append("          WHERE comp_no = x.comp_no                                                   ");
        query.append("            AND dept_id = x.dept_id)                                  deptDesc        ");
        query.append("       ,  x.wkctr_id                                                  wkctrId         ");
        query.append("       , (SELECT description                                                          ");
        query.append("          FROM TAWKCTR                                                                ");
        query.append("          WHERE comp_no = x.comp_no                                                   ");
        query.append("          AND wkctr_id = x.wkctr_id)                                  wkCtrDesc       ");
        query.append("       , x.shift_type                                                 shiftType       ");
        query.append("       , dbo.SFACODE_TO_DESC(x.shift_type,'SHIFT_TYPE','SYS','','"+user.getLangId()+"')    shiftTypeDesc       ");
        query.append("       , x.wo_type                                                     woType         ");
        query.append("       , dbo.SFACODE_TO_DESC(x.wo_type,'WO_TYPE','SYS','','"+user.getLangId()+"')          woTypeDesc      ");
        query.append("       , x.pm_type                                                     pmType         ");
        query.append("       , dbo.SFACODE_TO_DESC(x.pm_type,'PMI_TYPE','SYS','','"+user.getLangId()+"')          pmTypeDesc      ");
        query.append("       , x.work_time                                                   workTime       ");
        query.append("       , x.start_time                                                  startTime      ");
        query.append("       , x.end_time                                                    endTime        ");
        query.append("       ,  x.emp_id                                                     empId          ");
        query.append("       , (SELECT a.emp_name                                                           ");
        query.append("          FROM TAEMP a                                                                ");
        query.append("          WHERE a.comp_no = x.comp_no                                                 ");
        query.append("             AND a.emp_id = x.emp_id)                                    empDesc      ");
        query.append("       , (SELECT param1 FROM TACDSYSD WHERE list_Type= x.wo_type+'_TYPE' AND cdsysd_no=x.pm_type) param ");
        query.append("       , (SELECT a.eqctg_type                                                         ");
        query.append("          FROM TAEQUIPMENT a                                                          ");
        query.append("          WHERE a.equip_id = x.equip_id)                                 eqctgType    ");
        query.append("       , (SELECT dbo.SFACODE_TO_DESC(a.eqctg_type,'EQCTG_TYPE','SYS','','"+user.getLangId()+"')  ");
        query.append("          FROM TAEQUIPMENT a                                                          ");
        query.append("          WHERE a.equip_id = x.equip_id)                                 eqctgTypeDesc");
        query.append("       , (SELECT a.description                                                        ");
        query.append("          FROM TAPMLST a                                                              ");
        query.append("          WHERE a.pm_id = x.pm_id)                                       pmDesc       ");
        query.append("       , x.start_date                                                    startDate    ");
        query.append("       , x.end_date                                                      endDate      ");
        query.append("       , (SELECT a.pm_no                                                              ");
        query.append("          FROM TAPMLST a                                                              ");
        query.append("          WHERE a.pm_id = x.pm_id)                                       pmNo         ");
        query.append("       , x.close_id                                                      closeId      ");
        query.append("       , (SELECT emp_name                                                             ");
        query.append("          FROM TAEMP                                                                  ");
        query.append("          WHERE comp_no = x.comp_no                                                   ");
        query.append("             AND emp_id = x.close_id)                                    closeByDesc  ");
        query.append("       , x.pmsched_status                                                schedStatus  ");
        query.append("       , dbo.SFACODE_TO_DESC(x.pmsched_status,'PMSCHED_STATUS','SYS','"+user.getCompNo()+"','"+user.getLangId()+"')    schedStatusDesc     ");
        query.append("       , x.pm_id                                                         pmId         ");
        query.append("       , x.work_number                                                   workNumber   ");
        query.append("       , x.remark                                                        remark       ");
        query.append("       , x.product_id                                                    productId    ");
        query.append("       , (SELECT description FROM TAPRODUCT WHERE product_id=x.product_id AND comp_no=x.comp_no)       productDesc     ");
        query.append("       , x.pminsdsched_id                                                pminsDSchedId");   
        query.append("       ,( SELECT param2                                                               ");
        query.append("          FROM TACDSYSD                                                               ");
        query.append("          WHERE cdsysd_no=x.pm_type                                                   ");
        query.append("           AND list_type= x.wo_type+'_TYPE' )                            pmDParam     ");
        query.append("FROM TAPMINSDLIST x                                                                   ");
        query.append("WHERE  1=1                                                                            ");
        query.append(this.getWhere(workPmiDInsCommonDTO,user));
        query.getOrderByQuery("x.pminsdlist_id", "x.pmInsDlist_id", workPmiDInsCommonDTO.getOrderBy(), workPmiDInsCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(workPmiDInsCommonDTO.getIsLoadMaxCount(), workPmiDInsCommonDTO.getFirstRow()));
    }
    
    /**
     * Filter 조건
     * @author  youngjoo38
     * @version $Id: WorkPmiDInsListDAO.java,v 1.0 2015/12/02 09:14:12 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param workPmiDInsCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(WorkPmiDInsCommonDTO workPmiDInsCommonDTO, User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        String lang = user.getLangId();
        
        String startDate = workPmiDInsCommonDTO.getFilterStartDate();
        String endDate = workPmiDInsCommonDTO.getFilterEndDate();
        String compNo  = user.getCompNo();
        
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        query.getStringEqualQuery("x.IS_DELETED", "N");
        
        if(!"".equals(workPmiDInsCommonDTO.getPmInsDListId())){
            query.getAndNumKeyQuery("x.pminsdlist_id", workPmiDInsCommonDTO.getPmInsDListId());
            return query.toString();
        }
        
        query.getAndNumKeyQuery("x.pminsdsched_id", workPmiDInsCommonDTO.getPmInsDSchedId());

        //결과#  
        query.getAndQuery("(SELECT a.pm_no FROM TAPMLST a where a.pm_id = x.pm_id)", workPmiDInsCommonDTO.getPmInsDListNo());
        
        //작업명
        query.getLikeQuery("x.description", workPmiDInsCommonDTO.getFilterWkOrDesc());
        
        //작업일자
        query.getAndDateQuery("x.wkor_date", startDate, endDate);
        
        // 설비 (ID/DESC)
        if(!"".equals(workPmiDInsCommonDTO.getFilterEquipDesc()) && !"".equals(workPmiDInsCommonDTO.getFilterEquipId()))
        {
            query.getAndQuery("x.equip_id", workPmiDInsCommonDTO.getFilterEquipId());
        }
        else if(!"".equals(workPmiDInsCommonDTO.getFilterEquipDesc()))
        {
            query.append("AND x.equip_id IN (SELECT a.equip_id       ");
            query.append("                      FROM TAEQUIPMENT a   ");
            query.append("                      WHERE 1=1            ");
            query.getLikeQuery("a.description", workPmiDInsCommonDTO.getFilterEquipDesc());
            query.append("                   )                       ");
        }
        
        //부서
        query.getDeptLevelQuery("x.dept_id", workPmiDInsCommonDTO.getFilterDeptId(), workPmiDInsCommonDTO.getFilterDeptDesc(), compNo);
        
        //담당자
        query.getCodeLikeQuery("x.emp_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = x.emp_id AND a.comp_no=x.comp_no)", 
                workPmiDInsCommonDTO.getFilterEmpId(), workPmiDInsCommonDTO.getFilterEmpDesc());
        
        //법정설비
        if(!"".equals(workPmiDInsCommonDTO.getFilterIsLawEq())){
            query.getLikeQuery("(SELECT is_law_eq FROM TAEQUIPMENT b WHERE  b.equip_id = x.equip_id)", workPmiDInsCommonDTO.getFilterIsLawEq());
        }
        
        //관리자(정)
        if(!"".equals(workPmiDInsCommonDTO.getFilterMainMngId())||!"".equals(workPmiDInsCommonDTO.getFilterMainMngName())){
            query.append("AND x.pm_id IN (SELECT pm_id                        ");
            query.append("                  FROM TAWOEQUIP a, TAEQUIPMENT b   ");
            query.append("                  WHERE a.comp_no = b.comp_no       ");
            query.append("                  AND   a.equip_id = b.equip_id     ");
            query.getStringEqualQuery("a.comp_no", compNo);
            query.getCodeLikeQuery("b.main_mng_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = b.main_mng_id AND a.comp_no='"+compNo+"')", 
                    workPmiDInsCommonDTO.getFilterMainMngId(), workPmiDInsCommonDTO.getFilterMainMngName());
            query.append("                  )                                 ");
        }
        //관리자(부)
        if(!"".equals(workPmiDInsCommonDTO.getFilterSubMngId())||!"".equals(workPmiDInsCommonDTO.getFilterSubMngName())){
            query.append("AND x.pm_id IN (SELECT pm_id                        ");
            query.append("                  FROM TAWOEQUIP a, TAEQUIPMENT b   ");
            query.append("                  WHERE a.comp_no = b.comp_no       ");
            query.append("                  AND   a.equip_id = b.equip_id     ");
            query.getStringEqualQuery("a.comp_no", compNo);
            query.getCodeLikeQuery("b.sub_mng_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = b.sub_mng_id AND a.comp_no='"+compNo+"')", 
                    workPmiDInsCommonDTO.getFilterSubMngId(), workPmiDInsCommonDTO.getFilterSubMngName());
            query.append("                  )                                       ");
        }
        
        //위치
        if(!"".equals(workPmiDInsCommonDTO.getFilterEqLocId())||!"".equals(workPmiDInsCommonDTO.getFilterEqLocDesc())){
            query.append("AND x.pm_id IN (SELECT a.pm_id                        ");
            query.append("                  FROM TAPMEQUIP a, TAEQUIPMENT b         ");
            query.append("                  WHERE a.comp_no = b.comp_no             ");
            query.append("                  AND   a.equip_id = b.equip_id           ");
            query.getStringEqualQuery("a.comp_no", compNo);
            query.getEqLocLevelQuery("b.eqloc_id", workPmiDInsCommonDTO.getFilterEqLocId(), workPmiDInsCommonDTO.getFilterEqLocDesc(), compNo);
            query.append("                  )                                       ");
        }
        
        //작업형태
        query.getSysCdQuery("x.pm_type", workPmiDInsCommonDTO.getFilterPmTypeId(), workPmiDInsCommonDTO.getFilterPmTypeDesc(), "x.wo_type||'_TYPE'", compNo,user.getLangId());
        
        if(!"".equals(workPmiDInsCommonDTO.getFilterSchedStatusId())||!"".equals(workPmiDInsCommonDTO.getFilterSchedStatusDesc())){
            query.getAndQuery("x.pmsched_status", workPmiDInsCommonDTO.getFilterSchedStatusId());
        }
        
        //예방작업번호
        if(!"".equals(workPmiDInsCommonDTO.getFilterPmNo())){
            query.append("AND x.pm_id IN (SELECT pm_id                      ");
            query.append("                  FROM TAPMLST a                  ");
            query.append("                  WHERE 1=1                       ");
            query.getStringEqualQuery("a.comp_no", compNo);
            query.getAndQuery("a.pm_no", workPmiDInsCommonDTO.getFilterPmNo());
            query.append("                  )                               ");
        }

      //작업그룹
        query.getWkCtrLevelQuery("x.wkctr_id", workPmiDInsCommonDTO.getFilterWkCtrId(), workPmiDInsCommonDTO.getFilterWkCtrDesc(), compNo);

        //shift
        query.getSysCdQuery("x.shift_type", workPmiDInsCommonDTO.getFilterShiftId(), workPmiDInsCommonDTO.getFilterShiftDesc(), "SHIFT_TYPE", compNo,user.getLangId());
        //제외하고....
        if(!"".equals(workPmiDInsCommonDTO.getNotPmTypeId())){
            query.append("AND x.pm_type  NOT IN ('"+workPmiDInsCommonDTO.getNotPmTypeId()+"') ");
        }
        
        
        
        //설비유형
        if(!"".equals(workPmiDInsCommonDTO.getFilterEqCtgTypeId())||!"".equals(workPmiDInsCommonDTO.getFilterEqCtgTypeDesc())){
            query.append("AND x.pm_id IN (SELECT a.pm_id                        ");
            query.append("                  FROM TAPMEQUIP a, TAEQUIPMENT b         ");
            query.append("                  WHERE a.comp_no = b.comp_no             ");
            query.append("                  AND   a.equip_id = b.equip_id           ");
            query.getStringEqualQuery("a.comp_no", compNo);
            query.getSysCdQuery("b.eqctg_type", workPmiDInsCommonDTO.getFilterEqCtgTypeId(), workPmiDInsCommonDTO.getFilterEqCtgTypeDesc(), "EQCTG_TYPE", compNo,user.getLangId());
            query.append("                  )                                       ");
        }
        
        //생산제품
        if("".equals(workPmiDInsCommonDTO.getProductId()) && !"".equals(workPmiDInsCommonDTO.getProductDesc()))
        {
            query.append("AND x.product_id IN (SELECT a.product_id              ");
            query.append("                      FROM TAPRODUCT a              ");
            query.append("                      WHERE comp_no = x.comp_no   ");
            query.getLikeQuery("a.description", workPmiDInsCommonDTO.getProductDesc());
            query.append("                   )                              ");
        }
        else
        {
            query.getAndQuery("x.product_id", workPmiDInsCommonDTO.getProductId());
        }
        
        //제외하고....
        if(!"".equals(workPmiDInsCommonDTO.getNotPmschedStatusId())){
            query.append("AND x.pmsched_status  NOT IN ('"+workPmiDInsCommonDTO.getNotPmschedStatusId()+"') ");
        }
        
        //공장코드
        query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = x.comp_no AND a.plant = x.plant )", 
                workPmiDInsCommonDTO.getFilterPlantId(), workPmiDInsCommonDTO.getFilterPlantDesc());
        
        //최신버전의 설비의 작업만 보여줌.
        query.append("AND NOT EXISTS (SELECT a.equip_id							");
    	query.append("					 FROM  TAEQUIPMENT a            		");
    	query.append("					 WHERE 1=1                    			");
    	query.append("					 AND   a.equip_id = x.equip_id			");
    	query.getStringEqualQuery("a.comp_no", user.getCompNo());
    	query.getAndQuery("a.is_last_version", "N");
        query.append("									)						");

        return query.toString();
    }

    public String findTotalCount(WorkPmiDInsCommonDTO workPmiDInsCommonDTO,User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                 ");
        query.append("       COUNT(1)                        ");
        query.append("FROM   TAPMINSDLIST x                  ");
        query.append("WHERE  1=1                             ");
        query.append(this.getWhere(workPmiDInsCommonDTO,user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }

    @Override
    public int[] updateDeleteTag(final List<WorkPmiDInsDetailDTO> list, final User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        final String deleteTime = DateUtil.getTimeStamp(user.getOffset(), "yyyyMMddHHmmss");
        
        query.append("UPDATE TAPMINSDLIST  SET     ");
        query.append("         IS_DELETED = 'Y'     ");
        query.append("        ,DELETE_BY  = ?       ");
        query.append("        ,DELETE_TIME = ?     ");
        query.append("WHERE COMP_NO = ?              ");
        query.append("  AND PMINSDLIST_ID =  ?      ");
        query.append("  AND PMSCHED_STATUS != 'C'      ");
        
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
                WorkPmiDInsDetailDTO workPmiDInsDetailDTO = list.get(i);

                Object[] objects = getObject(new Object[] {
                        user.getEmpId(),
                        deleteTime,
                        user.getCompNo(),
                        workPmiDInsDetailDTO.getPmInsDListId()
                });
                
                for(int j=0;j<objects.length;j++){
                    ps.setObject(j+1, objects[j]);
                }
            }
            
        });
    }
}