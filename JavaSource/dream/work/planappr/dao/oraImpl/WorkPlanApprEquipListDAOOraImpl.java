package dream.work.planappr.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.work.planappr.dao.WorkPlanApprEquipListDAO;
import dream.work.planappr.dto.WorkPlanApprCommonDTO;
import dream.work.planappr.dto.WorkPlanApprDetailDTO;

/**
 * �۾���ȹ����-�۾���ȹ - ��� dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="workPlanApprEquipListDAOTarget"
 * @spring.txbn id="workPlanApprEquipListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkPlanApprEquipListDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkPlanApprEquipListDAO
{
    public List findList(WorkPlanApprCommonDTO workPlanApprCommonDTO,WorkPlanApprDetailDTO workPlanApprDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                                            ");
        query.append("      ''                                          seqNo,          ");
        query.append("      ''                                          isDelCheck,     ");
        query.append("      x.equip_id                                  equipId,        ");
        query.append("      x.item_no                                   itemNo,         ");
        query.append("      DECODE(x.eqctg_type,'MD','('||x.old_eq_no||')'||x.description,x.description) equipDesc,     ");
        query.append("      (SELECT a.eqloc_no                                          ");
        query.append("       FROM   TAEQLOC a                                           ");
        query.append("       WHERE  a.comp_no = x.comp_no                               ");
        query.append("         AND  a.eqloc_id = x.eqloc_id                             ");
        query.append("      ) eqlocNo,                                                  ");
        query.append("      (SELECT full_desc                                           ");
        query.append("         FROM TAEQLOC                                             ");
        query.append("        WHERE comp_no = x.comp_no                                 ");
        query.append("          AND eqloc_id = x.eqloc_id)              eqLocDesc,      ");
        query.append("      (SELECT full_desc                                           ");
        query.append("         FROM TAEQCTG                                             ");
        query.append("        WHERE comp_no = x.comp_no                                 ");
        query.append("          AND eqctg_id = x.eqctg_id)              eqCtgDesc,      ");
        query.append("      x.maker                                     maker,          ");
        query.append("      y.min_unit_value                            minUnitValue,   ");
        query.append("      x.model_no                                  modelNo,        ");
        query.append("      SFACODE_TO_DESC(x.plf_type,'PLF_TYPE','SYS','','"+user.getLangId()+"')  plfTypeDesc,");
        query.append("      x.eqctg_type                                eqCtgType,      ");
        query.append("      SFACODE_TO_DESC(x.eqctg_type,'EQCTG_TYPE','SYS','','"+user.getLangId()+"')  eqCtg2,");
        query.append("       x.is_law_eq                                isLawEq,        ");
        query.append("      (SELECT description                                         ");
        query.append("         FROM TADEPT                                              ");
        query.append("        WHERE comp_no = x.comp_no                                 ");
        query.append("          AND dept_id = x.dept_id)                deptDesc,       ");
        query.append("      (SELECT description                                         ");
        query.append("         FROM TAPLANT                                             ");
        query.append("        WHERE comp_no = x.comp_no                                 ");
        query.append("          AND plant = x.plant)                    plantDesc,      ");
        query.append("      (SELECT emp_name                                            ");
        query.append("         FROM TAEMP                                               ");
        query.append("        WHERE comp_no = x.comp_no                                 ");
        query.append("          AND emp_id = x.main_mng_id)             mainMngName,    ");
        query.append("      (SELECT emp_name                                            ");
        query.append("         FROM TAEMP                                               ");
        query.append("        WHERE comp_no = x.comp_no                                 ");
        query.append("          AND emp_id = x.sub_mng_id)              subMngName,     ");
        query.append("      x.dept_id                                   deptId,         ");
        query.append("      x.excel_no                                  excelNo,            ");
        query.append("      x.p_equip_id                                  parentEquipId,    ");
        query.append("      (SELECT xx.item_no FROM TAEQUIPMENT xx where xx.equip_id=x.p_equip_id) parentItemNo,    ");
        query.append("      (SELECT xx.description FROM TAEQUIPMENT xx where xx.equip_id=x.p_equip_id) parentEquipDesc,    ");
        query.append("      SFACODE_TO_DESC(x.eq_status,'EQ_STATUS','SYS','','"+user.getLangId()+"')eqstatus,    ");
        query.append("      y.guage_type                               guaTypeId,         ");
        query.append("       y.measure_unit                              measureunit,         ");
        query.append("       y.all_range                              totalRange,         ");
        query.append("       y.accuracy                              accuracy,         ");
        query.append("       y.use_range                              measureRange,         ");
        query.append("       y.tolerance                              allowValue,         ");
        query.append("      SFACODE_TO_DESC(y.guage_type,'GUAGE_TYPE','USR','','"+user.getLangId()+"') guaTypeDesc,         ");
        query.append("      (select param1 from tacdsysd where list_type='EQCTG_TYPE' AND cdsysd_no = x.eqctg_type) param,");
        query.append("      SFACODE_TO_DESC(x.revision_status,'REVISION_STATUS','SYS','','"+user.getLangId()+"')    revisionStatus,    ");
        query.append("      x.is_last_version                                  isLastVersion        ");
        query.append("      ,x.prod_shape                               prodShape    ");
        query.append("      ,x.old_eq_no                               oldEqNo    ");
        query.append("      ,x.eqstrloc_no       eqStrLocNo       ");
        query.append("      ,SFACODE_TO_DESC(x.eqstrloc_no, 'EQSTRLOC_NO', 'USR', x.comp_no, '"+user.getLangId()+"') eqStrLocDesc       ");
        query.append("      ,x.setup_date        setupDate        ");
        query.append("      ,x.capacity            capacity       ");
        query.append("      ,x.serial_no           serialNo       ");
        query.append("      ,x.supplier            supplier       ");
        query.append("      ,x.country_maker     countryMaker     ");
        query.append("      ,x.util_capa             utilCapa     ");
        query.append("      ,(SELECT (SELECT description FROM TADEPT WHERE comp_no=b.comp_no AND dept_id=b.dept_id)||'/'||b.emp_name    ");
        query.append("       FROM TAuser a INNER JOIN TAEMP b                           ");
        query.append("       ON a.comp_no=b.comp_no AND a.emp_id=b.emp_id               ");
        query.append("       WHERE a.comp_no=x.comp_no AND a.user_id=x.upd_by)  updBy   ");
        query.append("      ,x.upd_date               updDate                                 ");
        query.append("      ,(SELECT (SELECT description FROM TADEPT WHERE comp_no=b.comp_no AND dept_id=b.dept_id)||'/'||b.emp_name    ");
        query.append("       FROM TAuser a INNER JOIN TAEMP b                           ");
        query.append("       ON a.comp_no=b.comp_no AND a.emp_id=b.emp_id               ");
        query.append("       WHERE a.comp_no=x.comp_no AND a.user_id=x.cre_by)  creBy   ");
        query.append("      ,x.cre_date               creDate                                 ");
        query.append("      ,(SELECT A.CYCLE||SFACODE_TO_DESC(A.period_type,'PERIOD_TYPE','SYS','','"+user.getLangId()+"')        ");
        query.append("        FROM TAPMLST A INNER JOIN TAPMEQUIP b ON A.pm_id = b.pm_id AND A.comp_no = b.comp_no  ");
        query.append("        WHERE b.equip_id = x.equip_id ");
        query.append("          AND rownum = 1 ");
        query.append("       ) cycle        											");
        query.append("      ,x.usage_dept                           usageDeptId         ");
        query.append("      ,(SELECT a.description                                      ");
        query.append("         FROM TADEPT a                                            ");
        query.append("        WHERE a.comp_no = x.comp_no                               ");
        query.append("          AND a.dept_id = x.usage_dept)       usageDeptDesc       ");
        query.append("      ,(SELECT a.description                                      ");
        query.append("         FROM TADEPT a                                            ");
        query.append("        WHERE a.comp_no = x.comp_no                               ");
        query.append("          AND a.dept_id =  (SELECT xx.usage_dept FROM TAEQUIPMENT xx WHERE xx.equip_id=x.p_equip_id))       pUsageDeptDesc       ");
        query.append("        ,y.cal_point                                CalPoint      ");
        query.append("      ,(SELECT y.description FROM TAWKCTR y WHERE x.comp_no = y.comp_no AND x.wkctr_id = y.wkctr_id) wkctrDesc");
        query.append("      , SFACODE_TO_DESC(x.eq_grade,'EQ_GRADE','SYS','','"+user.getLangId()+"') TOOLGRADE                    ");
        query.append("FROM   TAEQUIPMENT x INNER JOIN TAEQTOOL y ON x.comp_no = y.comp_no AND x.equip_id = y.equip_id		");
        query.append("INNER JOIN TAWOPLANAPPREQUIP z ON x.comp_no = z.comp_no AND x.equip_id = z.equip_id  		");
        query.append("WHERE 1=1                                 						");
        query.append(this.getWhere(workPlanApprCommonDTO,workPlanApprDetailDTO,user));
        query.getOrderByQuery("x.ord_no", workPlanApprCommonDTO.getOrderBy(), workPlanApprCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(workPlanApprCommonDTO.getIsLoadMaxCount(), workPlanApprCommonDTO.getFirstRow()));

    }
    
    private String getWhere(WorkPlanApprCommonDTO workPlanApprCommonDTO,WorkPlanApprDetailDTO workPlanApprDetailDTO, User user){
    	QueryBuffer query = new QueryBuffer();
    	
    	query.getStringEqualQuery("z.comp_no", user.getCompNo());
    	query.getAndQuery("x.eqctg_type", "TL");
    	query.getAndQuery("x.is_last_version", "Y");
    	query.getAndNumKeyQuery("z.woplanappr_id", workPlanApprCommonDTO.getWoPlanApprId());
    	
        return query.toString();
    }
    
    public String findTotalCount(WorkPlanApprCommonDTO workPlanApprCommonDTO,WorkPlanApprDetailDTO workPlanApprDetailDTO, User user){
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("SELECT                	");
        query.append("       COUNT(1)       	");
        query.append("FROM   TAEQUIPMENT x INNER JOIN TAEQTOOL y ON x.comp_no = y.comp_no AND x.equip_id = y.equip_id		");
        query.append("INNER JOIN TAWOPLANAPPREQUIP z ON x.comp_no = z.comp_no AND x.equip_id = z.equip_id  		");
        query.append("WHERE 1=1                 ");
        query.append(this.getWhere(workPlanApprCommonDTO,workPlanApprDetailDTO,user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    	
    }

    @Override
    public void deleteWoPlanApprList( WorkPlanApprCommonDTO workPlanApprCommonDTO, WorkPlanApprDetailDTO workPlanApprDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("DELETE TAWOPLANAPPREQUIP          ");
        query.append("WHERE  comp_no            = ?     ");
        query.append("AND    woplanappr_id      = ?     ");
        Object[] objects = new Object[]{
                 user.getCompNo()
               , workPlanApprDetailDTO.getWoPlanApprId()
        };

        int result = this.getJdbcTemplate().update(query.toString(),objects);
    }

    @Override
    public void insertWoPlanApprList( WorkPlanApprCommonDTO workPlanApprCommonDTO,  WorkPlanApprDetailDTO workPlanApprDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("INSERT INTO TAWOPLANAPPREQUIP (comp_no, woplanapprequip_id, woplanappr_id, equip_id)  ");
        query.append("SELECT x.comp_no                          ");
        query.append("       ,sqawoplanapprequip_id.NEXTVAL     ");
        query.append("       ,?                                 ");
        query.append("       ,x.equip_id                        ");
        query.append("FROM TAEQUIPMENT x 						");
        query.append("WHERE 1=1 								");
        query.append("AND x.comp_no = ?							");
        query.append("AND x.eqctg_Type = ? 						");
        query.append("AND x.is_last_version = ? 				");
        
        Object[] objects = new Object[] {
                  workPlanApprDetailDTO.getWoPlanApprId()
                , user.getCompNo()
                , "TL"
                , "Y"
        };
        
        this.getJdbcTemplate().update(query.toString(), objects);
    }
    
}