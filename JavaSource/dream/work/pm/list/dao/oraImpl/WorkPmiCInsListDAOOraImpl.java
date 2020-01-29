package dream.work.pm.list.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.DateUtil;
import common.util.QueryBuffer;
import dream.work.pm.list.dao.WorkPmiCInsListDAO;
import dream.work.pm.list.dto.WorkPmiCInsCommonDTO;

/**
 * WorkPmiCIns Page - List DAO implements
 * @author youngjoo38
 * @version $Id: WorkPmiCInsListDAOOraImpl.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * 
 * @spring.bean id="workPmiCInsListDAOTarget"
 * @spring.txbn id="workPmiCInsListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkPmiCInsListDAOOraImpl  extends BaseJdbcDaoSupportOra implements WorkPmiCInsListDAO
{

    /**
     * grid find
     * @author  youngjoo38
     * @version $Id: workPmiCInsListDAO.java,v 1.0 2017/08/22 15:20:12 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param workPmiCInsCommonDTO
     * @return List
     */
    public List findList(WorkPmiCInsCommonDTO workPmiCInsCommonDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        String compNo = user.getCompNo();

        query.append("SELECT                                                                                ");
        query.append("        ''                                                            seqNo           ");
        query.append("      , ''                                                            isDelCheck      ");
        query.append("      , x.pmInsDlist_id                                               pmInsDListId    ");
        query.append("      , x.description                                                 description     ");
        query.append("      , x.wkor_date                                                   wkorDate        ");
        query.append("      , x.equip_id                                                    equipId         ");
        query.append("      ,(SELECT a.item_no                                                              ");
        query.append("        FROM TAEQUIPMENT a                                                            ");
        query.append("        WHERE a.equip_id = x.equip_id)                                equipNo         ");

        query.append("      , x.measure_time                                                measureTime     ");
        query.append("      , x.eqruntime                                                   operatingTime   ");
        query.append("      , (SELECT b.full_desc                                                        ");
        query.append("          FROM TAEQUIPMENT a, TAEQLOC b                                           ");
        query.append("         WHERE a.comp_no = b.comp_no                                              ");
        query.append("           AND a.eqloc_id = b.eqloc_id                                            ");
        query.append("           AND a.comp_no = x.comp_no                                              ");
        query.append("           AND a.equip_id = x.equip_id)                           AS eqLocDesc    ");
        query.append("      , (SELECT description                                                           ");
        query.append("         FROM TAEQUIPMENT                                                             ");
        query.append("         WHERE equip_id = x.equip_id)                                 equipDesc       ");
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
        query.append("       , SFACODE_TO_DESC(x.shift_type,'SHIFT_TYPE','SYS','','"+user.getLangId()+"')    shiftTypeDesc       ");
        query.append("       , x.wo_type                                                     woType         ");
        query.append("       , SFACODE_TO_DESC(x.wo_type,'WO_TYPE','SYS','','"+user.getLangId()+"')          woTypeDesc      ");
        query.append("       , x.pm_type                                                     pmTypeId       ");
        query.append("       , SFACODE_TO_DESC(x.pm_type,'PMI_TYPE','SYS','','"+user.getLangId()+"')          pmTypeDesc      ");
        query.append("       , x.work_time                                                   workTime       ");
        query.append("       , TO_CHAR(TO_DATE(x.start_time,'hh24mi'),'hh24:mi')             startTime      ");
        query.append("       , TO_CHAR(TO_DATE(x.end_time,'hh24mi'),'hh24:mi')               endTime        ");
        query.append("       , (SELECT a.emp_name                                                           ");
        query.append("          FROM TAEMP a                                                                ");
        query.append("          WHERE a.comp_no = x.comp_no                                                 ");
        query.append("             AND a.emp_id = x.emp_id)                                    empDesc      ");
        query.append("       , (SELECT param1 FROM TACDSYSD WHERE list_Type= x.wo_type||'_TYPE' AND cdsysd_no=x.pm_type) param ");
        query.append("       , (SELECT a.eqctg_type                                                         ");
        query.append("          FROM TAEQUIPMENT a                                                          ");
        query.append("          WHERE a.equip_id = x.equip_id)                                 eqctgTypeId  ");
        query.append("       , (SELECT SFACODE_TO_DESC(a.eqctg_type,'EQCTG_TYPE','SYS','','"+user.getLangId()+"')  ");
        query.append("          FROM TAEQUIPMENT a                                                          ");
        query.append("          WHERE a.equip_id = x.equip_id)                                 eqctgType    ");
        query.append("       , (SELECT a.description                                                        ");
        query.append("          FROM TAPMLST a                                                              ");
        query.append("          WHERE a.pm_id = x.pm_id)                                       pmDesc       ");
        query.append("       , (SELECT a.pm_no                                                              ");
        query.append("          FROM TAPMLST a                                                              ");
        query.append("          WHERE a.pm_id = x.pm_id)                                       pmNo         ");
        query.append("       , x.pmsched_status                                                schedStatusId");
        query.append("       , SFACODE_TO_DESC(x.pmsched_status,'PMSCHED_STATUS','SYS','"+user.getCompNo()+"','"+user.getLangId()+"')    pmiSchedStatus     ");
        query.append("       , x.pm_id                                                         pmId         ");
        query.append("       , x.work_number                                                   workNumber   ");
        query.append("       , x.remark                                                        remark     ");
        query.append("       , (SELECT description FROM TAPRODUCT WHERE product_id=x.product_id AND comp_no=x.comp_no)       productDesc     ");

        query.append("FROM TAPMINSDLIST x                                                                      ");
        query.append("WHERE  1=1                                                                            ");
        
        query.append(this.getWhere(workPmiCInsCommonDTO, user));
        query.getOrderByQuery("x.pmInsDlist_id", workPmiCInsCommonDTO.getOrderBy(), workPmiCInsCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(workPmiCInsCommonDTO.getIsLoadMaxCount(), workPmiCInsCommonDTO.getFirstRow()));
    }

    /**
     * Filter 조건
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param workPmiCInsCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(WorkPmiCInsCommonDTO workPmiCInsCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        String lang = user.getLangId();
        
        String startDate = workPmiCInsCommonDTO.getFilterStartDate();
        String endDate = workPmiCInsCommonDTO.getFilterEndDate();
        String compNo  = user.getCompNo();
        
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        query.getStringEqualQuery("x.pm_type", "CINS");
        query.getStringEqualQuery("x.wo_type", "PMI");
        query.getStringEqualQuery("x.IS_DELETED", "N");
        
        
        if(!"".equals(workPmiCInsCommonDTO.getPmInsDListId())){
            query.getAndQuery("x.pminsdlist_id", workPmiCInsCommonDTO.getPmInsDListId());
            return query.toString();
        }

        //결과#  
        query.getAndQuery("(SELECT a.pm_no FROM TAPMLST a where a.pm_id = x.pm_id)", workPmiCInsCommonDTO.getPmInsDListNo());
        
        //작업명
        query.getLikeQuery("x.description", workPmiCInsCommonDTO.getFilterWkOrDesc());
        
        //작업일자
        query.getAndDateQuery("x.wkor_date", startDate, endDate);
        
        // 설비 (ID/DESC)
        if(!"".equals(workPmiCInsCommonDTO.getFilterEquipDesc()) && !"".equals(workPmiCInsCommonDTO.getFilterEquipId()))
        {
            query.getAndQuery("x.equip_id", workPmiCInsCommonDTO.getFilterEquipId());
        }
        else if(!"".equals(workPmiCInsCommonDTO.getFilterEquipDesc()))
        {
            query.append("AND x.equip_id IN (SELECT a.equip_id       ");
            query.append("                      FROM TAEQUIPMENT a   ");
            query.append("                      WHERE 1=1            ");
            query.getLikeQuery("a.description", workPmiCInsCommonDTO.getFilterEquipDesc());
            query.append("                   )                       ");
        }
        
        //부서
        query.getDeptLevelQuery("x.dept_id", workPmiCInsCommonDTO.getFilterDeptId(), workPmiCInsCommonDTO.getFilterDeptDesc(), compNo);
        
        //담당자
        query.getCodeLikeQuery("x.emp_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = x.emp_id AND a.comp_no=x.comp_no)", 
                workPmiCInsCommonDTO.getFilterEmpId(), workPmiCInsCommonDTO.getFilterEmpDesc());
        
        //법정설비
        if(!"".equals(workPmiCInsCommonDTO.getFilterIsLawEq())){
            query.getLikeQuery("(SELECT is_law_eq FROM TAEQUIPMENT b WHERE  b.equip_id = x.equip_id)", workPmiCInsCommonDTO.getFilterIsLawEq());
        }
        
        //관리자(정)
        if(!"".equals(workPmiCInsCommonDTO.getFilterMainMngId())||!"".equals(workPmiCInsCommonDTO.getFilterMainMngName())){
            query.append("AND x.pm_id IN (SELECT pm_id                        ");
            query.append("                  FROM TAWOEQUIP a, TAEQUIPMENT b   ");
            query.append("                  WHERE a.comp_no = b.comp_no       ");
            query.append("                  AND   a.equip_id = b.equip_id     ");
            query.getStringEqualQuery("a.comp_no", compNo);
            query.getCodeLikeQuery("b.main_mng_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = b.main_mng_id AND a.comp_no='"+compNo+"')", 
                    workPmiCInsCommonDTO.getFilterMainMngId(), workPmiCInsCommonDTO.getFilterMainMngName());
            query.append("                  )                                 ");
        }
        //관리자(부)
        if(!"".equals(workPmiCInsCommonDTO.getFilterSubMngId())||!"".equals(workPmiCInsCommonDTO.getFilterSubMngName())){
            query.append("AND x.pm_id IN (SELECT pm_id                        ");
            query.append("                  FROM TAWOEQUIP a, TAEQUIPMENT b   ");
            query.append("                  WHERE a.comp_no = b.comp_no       ");
            query.append("                  AND   a.equip_id = b.equip_id     ");
            query.getStringEqualQuery("a.comp_no", compNo);
            query.getCodeLikeQuery("b.sub_mng_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = b.sub_mng_id AND a.comp_no='"+compNo+"')", 
                    workPmiCInsCommonDTO.getFilterSubMngId(), workPmiCInsCommonDTO.getFilterSubMngName());
            query.append("                  )                                       ");
        }
        
        //위치
        if(!"".equals(workPmiCInsCommonDTO.getFilterEqLocId())||!"".equals(workPmiCInsCommonDTO.getFilterEqLocDesc())){
            query.append("AND x.pm_id IN (SELECT a.pm_id                        ");
            query.append("                  FROM TAPMEQUIP a, TAEQUIPMENT b         ");
            query.append("                  WHERE a.comp_no = b.comp_no             ");
            query.append("                  AND   a.equip_id = b.equip_id           ");
            query.getStringEqualQuery("a.comp_no", compNo);
            query.getEqLocLevelQuery("b.eqloc_id", workPmiCInsCommonDTO.getFilterEqLocId(), workPmiCInsCommonDTO.getFilterEqLocDesc(), compNo);
            query.append("                  )                                       ");
        }
        
        //작업형태
        query.getSysCdQuery("x.pm_type", workPmiCInsCommonDTO.getFilterPmTypeId(), workPmiCInsCommonDTO.getFilterPmTypeDesc(), "x.wo_type||'_TYPE'", compNo,user.getLangId());
        
        if(!"".equals(workPmiCInsCommonDTO.getFilterSchedStatusId())||!"".equals(workPmiCInsCommonDTO.getFilterSchedStatusDesc())){
            query.getAndQuery("x.pmsched_status", workPmiCInsCommonDTO.getFilterSchedStatusId());
        }
        
        //예방작업번호
        if(!"".equals(workPmiCInsCommonDTO.getFilterPmNo())){
            query.append("AND x.pm_id IN (SELECT pm_id                      ");
            query.append("                  FROM TAPMLST a                  ");
            query.append("                  WHERE 1=1                       ");
            query.getStringEqualQuery("a.comp_no", compNo);
            query.getAndQuery("a.pm_no", workPmiCInsCommonDTO.getFilterPmNo());
            query.append("                  )                               ");
        }

        //작업그룹
        query.getWkCtrLevelQuery("x.wkctr_id", workPmiCInsCommonDTO.getFilterWkCtrId(), workPmiCInsCommonDTO.getFilterWkCtrDesc(), compNo);

        //shift
        query.getSysCdQuery("x.shift_type", workPmiCInsCommonDTO.getFilterShiftId(), workPmiCInsCommonDTO.getFilterShiftDesc(), "SHIFT_TYPE", compNo,user.getLangId());
        //제외하고....
        if(!"".equals(workPmiCInsCommonDTO.getNotPmTypeId())){
            query.append("AND x.pm_type  NOT IN ('"+workPmiCInsCommonDTO.getNotPmTypeId()+"') ");
        }
        
        
        
        //설비유형
        if(!"".equals(workPmiCInsCommonDTO.getFilterEqCtgTypeId())||!"".equals(workPmiCInsCommonDTO.getFilterEqCtgTypeDesc())){
            query.append("AND x.pm_id IN (SELECT a.pm_id                        ");
            query.append("                  FROM TAPMEQUIP a, TAEQUIPMENT b         ");
            query.append("                  WHERE a.comp_no = b.comp_no             ");
            query.append("                  AND   a.equip_id = b.equip_id           ");
            query.getStringEqualQuery("a.comp_no", compNo);
            query.getSysCdQuery("b.eqctg_type", workPmiCInsCommonDTO.getFilterEqCtgTypeId(), workPmiCInsCommonDTO.getFilterEqCtgTypeDesc(), "EQCTG_TYPE", compNo,user.getLangId());
            query.append("                  )                                       ");
        }
        
        //생산제품
        if("".equals(workPmiCInsCommonDTO.getProductId()) && !"".equals(workPmiCInsCommonDTO.getProductDesc()))
        {
            query.append("AND x.product_id IN (SELECT a.product_id              ");
            query.append("                      FROM TAPRODUCT a              ");
            query.append("                      WHERE comp_no = x.comp_no   ");
            query.getLikeQuery("a.description", workPmiCInsCommonDTO.getProductDesc());
            query.append("                   )                              ");
        }
        else
        {
            query.getAndQuery("x.product_id", workPmiCInsCommonDTO.getProductId());
        }
        
        //제외하고....
        if(!"".equals(workPmiCInsCommonDTO.getNotPmschedStatusId())){
            query.append("AND x.pmsched_status  NOT IN ('"+workPmiCInsCommonDTO.getNotPmschedStatusId()+"') ");
        }
        
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

    /**
     * Filter 조건
     * @author  youngjoo38
     * @version $Id: workPmiCInsListDAO.java,v 1.0 2017/08/22 15:20:12 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param workPmiCInsCommonDTO
     * @return
     * @throws Exception
     */
    public int deleteList(String id, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();

    	
    	int result = 0;
    	
        query.append("UPDATE TAPMINSDLIST  SET      ");
        query.append("         IS_DELETED = 'Y'     ");
        query.append("        ,DELETE_BY  = ?       ");
        query.append("        ,DELETE_TIME = ?      ");
        query.append("WHERE COMP_NO = ?             ");
    	query.append("  AND PMINSDLIST_ID = ?       ");
    	Object[] objects = new Object[]{
    			user.getEmpId()
    			,DateUtil.getDateTime()
    			,user.getCompNo()
        		,id
        };
        result = this.getJdbcTemplate().update(query.toString(),objects);
        
        
        query.setClear();
        query.append("UPDATE TAPMINSDSCHED  SET     ");
        query.append("         IS_DELETED = 'Y'     ");
        query.append("        ,DELETE_BY  = ?       ");
        query.append("        ,DELETE_TIME = ?     ");
        query.append("WHERE COMP_NO = ?              ");
        query.append("  AND PMINSDLIST_ID =  ?      ");
        objects = new Object[]{
        		user.getEmpId()
        		,DateUtil.getDateTime()
        		,user.getCompNo()
        		,id
        };
        
        return  this.getJdbcTemplate().update(query.toString(),objects);
        
        
    }

    
    
    
    public String findTotalCount(
            WorkPmiCInsCommonDTO workPmiCInsCommonDTO, User user) {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       COUNT(1)                                           ");
        query.append("FROM   TAPMINSDLIST x                                        ");
        query.append("WHERE  1=1                                                ");
        query.append(this.getWhere(workPmiCInsCommonDTO,user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }    
}
