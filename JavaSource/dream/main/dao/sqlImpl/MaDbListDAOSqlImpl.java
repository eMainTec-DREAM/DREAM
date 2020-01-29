package dream.main.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.DateUtil;
import common.util.QuerySqlBuffer;
import dream.main.dao.MaDbListDAO;
import dream.main.dto.MaDbListDTO;

/**
 * Dashboard - 목록 dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="maDbListDAOTarget"
 * @spring.txbn id="maDbListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaDbListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaDbListDAO
{
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maDbListDTO
     * @param resultMap 
     * @param user 
     * @param admin 
     * @return List
     */
    public List findDbList(MaDbListDTO maDbListDTO, Map resultMap, Map ordNoMap, User user)
    { 
        QuerySqlBuffer query = new QuerySqlBuffer(); 
        boolean  flag = false;
        //미완료 작업 오늘
        if(resultMap.containsKey("NWD"))
        {
        	flag = true;
            query.append("SELECT                                            ");
            query.append("       'NWD' type,                                ");
            query.append("       '"+resultMap.get("NWD")+"' typeDesc,       ");
            query.append("       '"+ordNoMap.get("NWD")+"' ordNo,         ");
            query.append("       COUNT(1) count                             ");
            query.append("FROM   TAWORKORDER x                              ");
            query.append("WHERE  x.wkor_date = CONVERT(VARCHAR, GETDATE(), 112)  ");
            query.append("  AND  x.wo_status = 'P'                        ");
            query.append("  AND x.is_deleted = 'N'                         ");
            query.append("  AND x.wo_type NOT IN ( 'PMI' )                  ");
            if( (!"".equals(user.getEqLocId())&&!"null".equals(user.getEqLocId())) 
                    || (!"".equals(user.getEqLocDesc())&&!"null".equals(user.getEqLocDesc())) )
            {
                query.append("AND x.wkor_id IN (SELECT a.wkor_id                        ");
                query.append("                  FROM TAWOEQUIP a, TAEQUIPMENT b         ");
                query.append("                  WHERE a.comp_no = b.comp_no             ");
                query.append("                  AND   a.equip_id = b.equip_id           ");
                query.getStringEqualQuery("a.comp_no", user.getCompNo());
                query.getEqLocLevelQuery("b.eqloc_id", user.getEqLocId(), user.getEqLocDesc(), user.getCompNo());
                query.append("                  )                                       ");
            }
            if( (!"".equals(user.getEqCtgTypeId())&&!"null".equals(user.getEqCtgTypeId())) 
                    || (!"".equals(user.getEqCtgTypeDesc())&&!"null".equals(user.getEqCtgTypeDesc())) ){
                query.append("AND x.wkor_id IN (SELECT a.wkor_id                        ");
                query.append("                  FROM TAWOEQUIP a, TAEQUIPMENT b         ");
                query.append("                  WHERE a.comp_no = b.comp_no             ");
                query.append("                  AND   a.equip_id = b.equip_id           ");
                query.getStringEqualQuery("a.comp_no", user.getCompNo());
                query.getSysCdQuery("b.eqctg_type", user.getEqCtgTypeId(), user.getEqCtgTypeDesc(), "EQCTG_TYPE", user.getCompNo(),user.getLangId());
                query.append("                  )                                       ");
            }
            query.getCodeLikeQuery("x.emp_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = x.emp_id AND a.comp_no=x.comp_no)", 
                    user.getFilterEmpId(), user.getFilterEmpDesc());
            query.getDeptLevelQuery("x.dept_id", user.getFilterDeptId(), user.getFilterDeptDesc(), user.getCompNo());
            query.getWkCtrLevelQuery("x.wkctr_id", user.getWkctrId(), user.getWkctrDesc(), user.getCompNo());
            query.getAndQuery("x.comp_no", user.getCompNo());
            query.getAndQuery("x.plant", user.getPlant());
        }
        
        //미완료 분해점검 오늘
        if(resultMap.containsKey("NCD"))
        {
            if(flag) query.append("UNION ALL        						");
            
            flag = true;
            query.append("SELECT                                            ");
            query.append("       'NCD' type,                                ");
            query.append("       '"+resultMap.get("NCD")+"' typeDesc,       ");
            query.append("       '"+ordNoMap.get("NCD")+"' ordNo,         ");
            query.append("       COUNT(1) count                             ");
            query.append("FROM   TAWORKORDER x                              ");
            query.append("WHERE  x.wkor_date = CONVERT(VARCHAR, GETDATE(), 112)  ");
            query.append("  AND  x.wo_status = 'P'                        ");
            query.append("  AND x.is_deleted = 'N'                         ");
            query.append("  AND  x.wo_type IN ( 'PMI' )                     ");
            if( (!"".equals(user.getEqLocId())&&!"null".equals(user.getEqLocId())) 
                    || (!"".equals(user.getEqLocDesc())&&!"null".equals(user.getEqLocDesc())) )
            {
                query.append("AND x.wkor_id IN (SELECT a.wkor_id                        ");
                query.append("                  FROM TAWOEQUIP a, TAEQUIPMENT b         ");
                query.append("                  WHERE a.comp_no = b.comp_no             ");
                query.append("                  AND   a.equip_id = b.equip_id           ");
                query.getStringEqualQuery("a.comp_no", user.getCompNo());
                query.getEqLocLevelQuery("b.eqloc_id", user.getEqLocId(), user.getEqLocDesc(), user.getCompNo());
                query.append("                  )                                       ");
            }
            if( (!"".equals(user.getEqCtgTypeId())&&!"null".equals(user.getEqCtgTypeId())) 
                    || (!"".equals(user.getEqCtgTypeDesc())&&!"null".equals(user.getEqCtgTypeDesc())) ){
                query.append("AND x.wkor_id IN (SELECT a.wkor_id                        ");
                query.append("                  FROM TAWOEQUIP a, TAEQUIPMENT b         ");
                query.append("                  WHERE a.comp_no = b.comp_no             ");
                query.append("                  AND   a.equip_id = b.equip_id           ");
                query.getStringEqualQuery("a.comp_no", user.getCompNo());
                query.getSysCdQuery("b.eqctg_type", user.getEqCtgTypeId(), user.getEqCtgTypeDesc(), "EQCTG_TYPE", user.getCompNo(),user.getLangId());
                query.append("                  )                                       ");
            }
            query.getCodeLikeQuery("x.emp_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = x.emp_id AND a.comp_no=x.comp_no)", 
                    user.getFilterEmpId(), user.getFilterEmpDesc());
            query.getDeptLevelQuery("x.dept_id", user.getFilterDeptId(), user.getFilterDeptDesc(), user.getCompNo());
            query.getWkCtrLevelQuery("x.wkctr_id", user.getWkctrId(), user.getWkctrDesc(), user.getCompNo());
            query.getAndQuery("x.comp_no", user.getCompNo());
            query.getAndQuery("x.plant", user.getPlant());
        }
        
        //미완료  정기점검 오늘
        if(resultMap.containsKey("NRINSD"))
        {
            if(flag) query.append("UNION ALL                                ");
            
            flag = true;
            query.append("SELECT                                            ");
            query.append("       'NRINSD' type,                                ");
            query.append("       '"+resultMap.get("NRINSD")+"' typeDesc,       ");
            query.append("       '"+ordNoMap.get("NRINSD")+"' ordNo,         ");
            query.append("       COUNT(1) count                             ");
            query.append("FROM   TAPMINSLIST x                              ");
            query.append("WHERE  x.wkor_date = CONVERT(VARCHAR(8), GETDATE(), 112)  ");
            query.append("  AND  x.pmsched_status NOT IN ('C')                       ");
            query.append("  AND x.is_deleted = 'N'                         ");
            if( (!"".equals(user.getEqLocId())&&!"null".equals(user.getEqLocId())) 
                    || (!"".equals(user.getEqLocDesc())&&!"null".equals(user.getEqLocDesc())) )
            {
                query.append("AND x.pm_id IN (SELECT a.pm_id                        ");
                query.append("                  FROM TAPMEQUIP a, TAEQUIPMENT b         ");
                query.append("                  WHERE a.comp_no = b.comp_no             ");
                query.append("                  AND   a.equip_id = b.equip_id           ");
                query.getStringEqualQuery("a.comp_no", user.getCompNo());
                query.getEqLocLevelQuery("b.eqloc_id", user.getEqLocId(), user.getEqLocDesc(), user.getCompNo());
                query.append("                  )                                       ");
            }
            if( (!"".equals(user.getEqCtgTypeId())&&!"null".equals(user.getEqCtgTypeId())) 
                    || (!"".equals(user.getEqCtgTypeDesc())&&!"null".equals(user.getEqCtgTypeDesc())) ){
                query.append("AND x.pm_id IN (SELECT a.pm_id                        ");
                query.append("                  FROM TAPMEQUIP a, TAEQUIPMENT b         ");
                query.append("                  WHERE a.comp_no = b.comp_no             ");
                query.append("                  AND   a.equip_id = b.equip_id           ");
                query.getStringEqualQuery("a.comp_no", user.getCompNo());
                query.getSysCdQuery("b.eqctg_type", user.getEqCtgTypeId(), user.getEqCtgTypeDesc(), "EQCTG_TYPE", user.getCompNo(),user.getLangId());
                query.append("                  )                                       ");
            }
            query.getCodeLikeQuery("x.emp_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = x.emp_id AND a.comp_no=x.comp_no)", 
                    user.getFilterEmpId(), user.getFilterEmpDesc());
            query.getDeptLevelQuery("x.dept_id", user.getFilterDeptId(), user.getFilterDeptDesc(), user.getCompNo());
            query.getWkCtrLevelQuery("x.wkctr_id", user.getWkctrId(), user.getWkctrDesc(), user.getCompNo());
            query.getAndQuery("x.comp_no", user.getCompNo());
            query.getAndQuery("x.plant", user.getPlant());
        }
        
        //미완료  일상점검 오늘
        if(resultMap.containsKey("NDINSD"))
        {
            if(flag) query.append("UNION ALL                                ");
            
            flag = true;
            query.append("SELECT                                            ");
            query.append("       'NDINSD' type,                                ");
            query.append("       '"+resultMap.get("NDINSD")+"' typeDesc,       ");
            query.append("       '"+ordNoMap.get("NDINSD")+"' ordNo,         ");
            query.append("       COUNT(1) count                             ");
            query.append("FROM   TAPMINSDLIST x                              ");
            query.append("WHERE  x.wkor_date = CONVERT(VARCHAR(8), GETDATE(), 112)  ");
            query.append("  AND  x.pmsched_status NOT IN ('C')                       ");
            query.append("  AND x.is_deleted = 'N'                         ");
            if( (!"".equals(user.getEqLocId())&&!"null".equals(user.getEqLocId())) 
                    || (!"".equals(user.getEqLocDesc())&&!"null".equals(user.getEqLocDesc())) )
            {
                query.append("AND x.pm_id IN (SELECT a.pm_id                        ");
                query.append("                  FROM TAPMEQUIP a, TAEQUIPMENT b         ");
                query.append("                  WHERE a.comp_no = b.comp_no             ");
                query.append("                  AND   a.equip_id = b.equip_id           ");
                query.getStringEqualQuery("a.comp_no", user.getCompNo());
                query.getEqLocLevelQuery("b.eqloc_id", user.getEqLocId(), user.getEqLocDesc(), user.getCompNo());
                query.append("                  )                                       ");
            }
            if( (!"".equals(user.getEqCtgTypeId())&&!"null".equals(user.getEqCtgTypeId())) 
                    || (!"".equals(user.getEqCtgTypeDesc())&&!"null".equals(user.getEqCtgTypeDesc())) ){
                query.append("AND x.pm_id IN (SELECT a.pm_id                        ");
                query.append("                  FROM TAPMEQUIP a, TAEQUIPMENT b         ");
                query.append("                  WHERE a.comp_no = b.comp_no             ");
                query.append("                  AND   a.equip_id = b.equip_id           ");
                query.getStringEqualQuery("a.comp_no", user.getCompNo());
                query.getSysCdQuery("b.eqctg_type", user.getEqCtgTypeId(), user.getEqCtgTypeDesc(), "EQCTG_TYPE", user.getCompNo(),user.getLangId());
                query.append("                  )                                       ");
            }
            query.getCodeLikeQuery("x.emp_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = x.emp_id AND a.comp_no=x.comp_no)", 
                    user.getFilterEmpId(), user.getFilterEmpDesc());
            query.getDeptLevelQuery("x.dept_id", user.getFilterDeptId(), user.getFilterDeptDesc(), user.getCompNo());
            query.getWkCtrLevelQuery("x.wkctr_id", user.getWkctrId(), user.getWkctrDesc(), user.getCompNo());
            query.getAndQuery("x.comp_no", user.getCompNo());
            query.getAndQuery("x.plant", user.getPlant());
        }
        
        //미완료  교정작업 오늘
        if(resultMap.containsKey("NPMCD"))
        {
        	if(flag) query.append("UNION ALL                                ");
            
        	flag = true;
            query.append("SELECT                                            ");
            query.append("       'NPMCD' type                               ");
            query.append("       ,'"+resultMap.get("NPMCD")+"' typeDesc     ");
            query.append("       ,'"+ordNoMap.get("NPMCD")+"' ordNo         ");
            query.append("       ,COUNT(1) count                            ");
            query.append("FROM   TAWORKORDER x                              ");
            query.append("WHERE  x.wkor_date = CONVERT(VARCHAR, GETDATE(), 112)  ");
        	query.append("  AND  x.wo_status NOT IN ('C')                   ");
            query.append("  AND x.is_deleted = 'N'                         ");
        	query.append("  AND  x.wo_type = 'PMC'							");
            if( (!"".equals(user.getEqLocId())&&!"null".equals(user.getEqLocId())) 
                    || (!"".equals(user.getEqLocDesc())&&!"null".equals(user.getEqLocDesc())) )
            {
                query.append("AND x.wkor_id IN (SELECT a.wkor_id                        ");
                query.append("                  FROM TAWOEQUIP a, TAEQUIPMENT b         ");
                query.append("                  WHERE a.comp_no = b.comp_no             ");
                query.append("                  AND   a.equip_id = b.equip_id           ");
                query.getStringEqualQuery("a.comp_no", user.getCompNo());
                query.getEqLocLevelQuery("b.eqloc_id", user.getEqLocId(), user.getEqLocDesc(), user.getCompNo());
                query.append("                  )                                       ");
            }
            if( (!"".equals(user.getEqCtgTypeId())&&!"null".equals(user.getEqCtgTypeId())) 
                    || (!"".equals(user.getEqCtgTypeDesc())&&!"null".equals(user.getEqCtgTypeDesc())) ){
                query.append("AND x.wkor_id IN (SELECT a.wkor_id                        ");
                query.append("                  FROM TAWOEQUIP a, TAEQUIPMENT b         ");
                query.append("                  WHERE a.comp_no = b.comp_no             ");
                query.append("                  AND   a.equip_id = b.equip_id           ");
                query.getStringEqualQuery("a.comp_no", user.getCompNo());
                query.getSysCdQuery("b.eqctg_type", user.getEqCtgTypeId(), user.getEqCtgTypeDesc(), "EQCTG_TYPE", user.getCompNo(),user.getLangId());
                query.append("                  )                                       ");
            }
            query.getCodeLikeQuery("x.emp_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = x.emp_id AND a.comp_no=x.comp_no)", 
                    user.getFilterEmpId(), user.getFilterEmpDesc());
            query.getDeptLevelQuery("x.dept_id", user.getFilterDeptId(), user.getFilterDeptDesc(), user.getCompNo());
            query.getWkCtrLevelQuery("x.wkctr_id", user.getWkctrId(), user.getWkctrDesc(), user.getCompNo());
            query.getAndQuery("x.comp_no", user.getCompNo());
            query.getAndQuery("x.plant", user.getPlant());
        }
        
        //미완료 작업 금주
        if(resultMap.containsKey("NWW"))
        {
            if(flag) query.append("UNION ALL        						");
            
            flag = true;
            query.append("SELECT                                            ");
            query.append("       'NWW' type,                                ");
            query.append("       '"+resultMap.get("NWW")+"' typeDesc,       ");
            query.append("       '"+ordNoMap.get("NWW")+"' ordNo,           ");
            query.append("       COUNT(1) count                             ");
            query.append("FROM   TAWORKORDER x                              ");
            query.append("WHERE 1 = 1                                       ");
            query.append("  AND x.wo_status = 'P'                        ");
            query.append("  AND x.is_deleted = 'N'                         ");
            query.append("  AND x.wo_type NOT IN ( 'PMI' )                  ");
            query.append("  AND wkor_date >= CONVERT(VARCHAR, DATEADD(dd, DATEPART(WEEKDAY, GETDATE())*(-1)+2, GETDATE()), 112)        ");
            query.append("  AND wkor_date <= CONVERT(VARCHAR, GETDATE(), 112)         				");
            if( (!"".equals(user.getEqLocId())&&!"null".equals(user.getEqLocId())) 
                    || (!"".equals(user.getEqLocDesc())&&!"null".equals(user.getEqLocDesc())) )
            {
                query.append("AND x.wkor_id IN (SELECT a.wkor_id                        ");
                query.append("                  FROM TAWOEQUIP a, TAEQUIPMENT b         ");
                query.append("                  WHERE a.comp_no = b.comp_no             ");
                query.append("                  AND   a.equip_id = b.equip_id           ");
                query.getStringEqualQuery("a.comp_no", user.getCompNo());
                query.getEqLocLevelQuery("b.eqloc_id", user.getEqLocId(), user.getEqLocDesc(), user.getCompNo());
                query.append("                  )                                       ");
            }
            if( (!"".equals(user.getEqCtgTypeId())&&!"null".equals(user.getEqCtgTypeId())) 
                    || (!"".equals(user.getEqCtgTypeDesc())&&!"null".equals(user.getEqCtgTypeDesc())) ){
                query.append("AND x.wkor_id IN (SELECT a.wkor_id                        ");
                query.append("                  FROM TAWOEQUIP a, TAEQUIPMENT b         ");
                query.append("                  WHERE a.comp_no = b.comp_no             ");
                query.append("                  AND   a.equip_id = b.equip_id           ");
                query.getStringEqualQuery("a.comp_no", user.getCompNo());
                query.getSysCdQuery("b.eqctg_type", user.getEqCtgTypeId(), user.getEqCtgTypeDesc(), "EQCTG_TYPE", user.getCompNo(),user.getLangId());
                query.append("                  )                                       ");
            }
            query.getCodeLikeQuery("x.emp_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = x.emp_id AND a.comp_no=x.comp_no)", 
                    user.getFilterEmpId(), user.getFilterEmpDesc());
            query.getDeptLevelQuery("x.dept_id", user.getFilterDeptId(), user.getFilterDeptDesc(), user.getCompNo());
            query.getWkCtrLevelQuery("x.wkctr_id", user.getWkctrId(), user.getWkctrDesc(), user.getCompNo());
            query.getAndQuery("x.comp_no", user.getCompNo());
            query.getAndQuery("x.plant", user.getPlant());

        }
        
        //미완료 분해점검 금주 
        if(resultMap.containsKey("NCW"))
        {
            if(flag) query.append("UNION ALL        						");
            
            flag = true;
            query.append("SELECT                                            ");
            query.append("       'NCW' type,                                ");
            query.append("       '"+resultMap.get("NCW")+"' typeDesc,       ");
            query.append("       '"+ordNoMap.get("NCW")+"' ordNo,           ");
            query.append("       COUNT(1) count                             ");
            query.append("FROM   TAWORKORDER x                              ");
            query.append("WHERE 1 = 1                                       ");
            query.append("  AND x.wo_status = 'P'                        ");
            query.append("  AND x.is_deleted = 'N'                         ");
            query.append("  AND  x.wo_type IN ( 'PMI' )                     ");
            query.append("  AND wkor_date >= CONVERT(VARCHAR, DATEADD(dd, DATEPART(WEEKDAY, GETDATE())*(-1)+2, GETDATE()), 112)        ");
            query.append("  AND wkor_date <= CONVERT(VARCHAR, GETDATE(), 112)         				");
            if( (!"".equals(user.getEqLocId())&&!"null".equals(user.getEqLocId())) 
                    || (!"".equals(user.getEqLocDesc())&&!"null".equals(user.getEqLocDesc())) )
            {
                query.append("AND x.wkor_id IN (SELECT a.wkor_id                        ");
                query.append("                  FROM TAWOEQUIP a, TAEQUIPMENT b         ");
                query.append("                  WHERE a.comp_no = b.comp_no             ");
                query.append("                  AND   a.equip_id = b.equip_id           ");
                query.getStringEqualQuery("a.comp_no", user.getCompNo());
                query.getEqLocLevelQuery("b.eqloc_id", user.getEqLocId(), user.getEqLocDesc(), user.getCompNo());
                query.append("                  )                                       ");
            }
            if( (!"".equals(user.getEqCtgTypeId())&&!"null".equals(user.getEqCtgTypeId())) 
                    || (!"".equals(user.getEqCtgTypeDesc())&&!"null".equals(user.getEqCtgTypeDesc())) ){
                query.append("AND x.wkor_id IN (SELECT a.wkor_id                        ");
                query.append("                  FROM TAWOEQUIP a, TAEQUIPMENT b         ");
                query.append("                  WHERE a.comp_no = b.comp_no             ");
                query.append("                  AND   a.equip_id = b.equip_id           ");
                query.getStringEqualQuery("a.comp_no", user.getCompNo());
                query.getSysCdQuery("b.eqctg_type", user.getEqCtgTypeId(), user.getEqCtgTypeDesc(), "EQCTG_TYPE", user.getCompNo(),user.getLangId());
                query.append("                  )                                       ");
            }
            query.getCodeLikeQuery("x.emp_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = x.emp_id AND a.comp_no=x.comp_no)", 
                    user.getFilterEmpId(), user.getFilterEmpDesc());
            query.getDeptLevelQuery("x.dept_id", user.getFilterDeptId(), user.getFilterDeptDesc(), user.getCompNo());
            query.getWkCtrLevelQuery("x.wkctr_id", user.getWkctrId(), user.getWkctrDesc(), user.getCompNo());
            query.getAndQuery("x.comp_no", user.getCompNo());
            query.getAndQuery("x.plant", user.getPlant());
        }
        
        //미완료 정기점검 금주 
        if(resultMap.containsKey("NRINSW"))
        {
            if(flag) query.append("UNION ALL                                ");
            
            flag = true;
            query.append("SELECT                                            ");
            query.append("       'NRINSW' type,                                ");
            query.append("       '"+resultMap.get("NRINSW")+"' typeDesc,       ");
            query.append("       '"+ordNoMap.get("NRINSW")+"' ordNo,         ");
            query.append("       COUNT(1) count                             ");
            query.append("FROM   TAPMINSLIST x                              ");
            query.append("WHERE 1 = 1                                       ");
            query.append("  AND wkor_date >= CONVERT(varchar(8),  dateadd(day, -datepart(weekday, getdate()) +2, getdate()), 112)       ");
            query.append("  AND wkor_date <= CONVERT(VARCHAR(8), GETDATE(), 112)                ");
            query.append("  AND  x.pmsched_status NOT IN ('C')                       ");
            query.append("  AND x.is_deleted = 'N'                         ");
            if( (!"".equals(user.getEqLocId())&&!"null".equals(user.getEqLocId())) 
                    || (!"".equals(user.getEqLocDesc())&&!"null".equals(user.getEqLocDesc())) )
            {
                query.append("AND x.pm_id IN (SELECT a.pm_id                        ");
                query.append("                  FROM TAPMEQUIP a, TAEQUIPMENT b         ");
                query.append("                  WHERE a.comp_no = b.comp_no             ");
                query.append("                  AND   a.equip_id = b.equip_id           ");
                query.getStringEqualQuery("a.comp_no", user.getCompNo());
                query.getEqLocLevelQuery("b.eqloc_id", user.getEqLocId(), user.getEqLocDesc(), user.getCompNo());
                query.append("                  )                                       ");
            }
            if( (!"".equals(user.getEqCtgTypeId())&&!"null".equals(user.getEqCtgTypeId())) 
                    || (!"".equals(user.getEqCtgTypeDesc())&&!"null".equals(user.getEqCtgTypeDesc())) ){
                query.append("AND x.pm_id IN (SELECT a.pm_id                        ");
                query.append("                  FROM TAPMEQUIP a, TAEQUIPMENT b         ");
                query.append("                  WHERE a.comp_no = b.comp_no             ");
                query.append("                  AND   a.equip_id = b.equip_id           ");
                query.getStringEqualQuery("a.comp_no", user.getCompNo());
                query.getSysCdQuery("b.eqctg_type", user.getEqCtgTypeId(), user.getEqCtgTypeDesc(), "EQCTG_TYPE", user.getCompNo(),user.getLangId());
                query.append("                  )                                       ");
            }
            query.getCodeLikeQuery("x.emp_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = x.emp_id AND a.comp_no=x.comp_no)", 
                    user.getFilterEmpId(), user.getFilterEmpDesc());
            query.getDeptLevelQuery("x.dept_id", user.getFilterDeptId(), user.getFilterDeptDesc(), user.getCompNo());
            query.getWkCtrLevelQuery("x.wkctr_id", user.getWkctrId(), user.getWkctrDesc(), user.getCompNo());
            query.getAndQuery("x.comp_no", user.getCompNo());
            query.getAndQuery("x.plant", user.getPlant());
        }

        //미완료 교정작업 금주
        if(resultMap.containsKey("NPMCW"))
        {
            if(flag) query.append("UNION ALL        						");
            
            flag = true;
            query.append("SELECT                                            ");
            query.append("       'NPMCW' type                               ");
            query.append("       ,'"+resultMap.get("NPMCW")+"' typeDesc     ");
            query.append("       ,'"+ordNoMap.get("NPMCW")+"' ordNo         ");
            query.append("       ,COUNT(1) count                            ");
            query.append("FROM   TAWORKORDER x                              ");
            query.append("WHERE 1 = 1                                       ");
        	query.append("  AND  x.wo_status NOT IN ('C')                   ");
            query.append("  AND x.is_deleted = 'N'                         ");
        	query.append("  AND  x.wo_type = 'PMC'							");
            query.append("  AND wkor_date >= CONVERT(VARCHAR, DATEADD(dd, DATEPART(WEEKDAY, GETDATE())*(-1)+2, GETDATE()), 112)        ");
            query.append("  AND wkor_date <= CONVERT(VARCHAR, GETDATE(), 112)         				");
            if( (!"".equals(user.getEqLocId())&&!"null".equals(user.getEqLocId())) 
                    || (!"".equals(user.getEqLocDesc())&&!"null".equals(user.getEqLocDesc())) )
            {
                query.append("AND x.wkor_id IN (SELECT a.wkor_id                        ");
                query.append("                  FROM TAWOEQUIP a, TAEQUIPMENT b         ");
                query.append("                  WHERE a.comp_no = b.comp_no             ");
                query.append("                  AND   a.equip_id = b.equip_id           ");
                query.getStringEqualQuery("a.comp_no", user.getCompNo());
                query.getEqLocLevelQuery("b.eqloc_id", user.getEqLocId(), user.getEqLocDesc(), user.getCompNo());
                query.append("                  )                                       ");
            }
            if( (!"".equals(user.getEqCtgTypeId())&&!"null".equals(user.getEqCtgTypeId())) 
                    || (!"".equals(user.getEqCtgTypeDesc())&&!"null".equals(user.getEqCtgTypeDesc())) ){
                query.append("AND x.wkor_id IN (SELECT a.wkor_id                        ");
                query.append("                  FROM TAWOEQUIP a, TAEQUIPMENT b         ");
                query.append("                  WHERE a.comp_no = b.comp_no             ");
                query.append("                  AND   a.equip_id = b.equip_id           ");
                query.getStringEqualQuery("a.comp_no", user.getCompNo());
                query.getSysCdQuery("b.eqctg_type", user.getEqCtgTypeId(), user.getEqCtgTypeDesc(), "EQCTG_TYPE", user.getCompNo(),user.getLangId());
                query.append("                  )                                       ");
            }
            query.getCodeLikeQuery("x.emp_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = x.emp_id AND a.comp_no=x.comp_no)", 
                    user.getFilterEmpId(), user.getFilterEmpDesc());
            query.getDeptLevelQuery("x.dept_id", user.getFilterDeptId(), user.getFilterDeptDesc(), user.getCompNo());
            query.getWkCtrLevelQuery("x.wkctr_id", user.getWkctrId(), user.getWkctrDesc(), user.getCompNo());
            query.getAndQuery("x.comp_no", user.getCompNo());
            query.getAndQuery("x.plant", user.getPlant());

        }
        
        //미완료 작업요청
        if(resultMap.containsKey("NWREQ"))
        {
            if(flag) query.append("UNION ALL                                ");
            
            flag = true;
            query.append("SELECT                                            ");
            query.append("       'NWREQ' type,                              ");
            query.append("       '"+resultMap.get("NWREQ")+"' typeDesc,     ");
            query.append("        '"+ordNoMap.get("NWREQ")+"' ordNo,        ");
            query.append("       COUNT(1) count                             ");
            query.append("FROM   TAWOREQ x                                  ");
            query.append("WHERE  x.woreq_status NOT IN ( 'COM', 'INC' )     ");
            query.getCodeLikeQuery("x.eqloc_id", "(SELECT a.full_desc FROM  TAEQLOC a WHERE a.eqloc_id = x.eqloc_id AND a.comp_no=x.comp_no)",
                    user.getEqLocId(), user.getEqLocDesc());
            query.getCodeLikeQuery("x.req_emp_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = x.req_emp_id AND a.comp_no=x.comp_no)", 
                    user.getFilterEmpId(), user.getFilterEmpDesc());
            query.getDeptLevelQuery("x.req_dept_id", user.getFilterDeptId(), user.getFilterDeptDesc(), user.getCompNo());
            query.getAndQuery("x.comp_no", user.getCompNo());
            query.getAndQuery("x.plant", user.getPlant());

        }
        
        if(resultMap.containsKey("USQ"))
        {
            if(flag) query.append("UNION ALL        						");
            
            flag = true;
            query.append("SELECT                                            ");
            query.append("       'USQ' type,                                ");
            query.append("       '"+resultMap.get("USQ")+"' typeDesc,       ");
            query.append("        '"+ordNoMap.get("USQ")+"' ordNo,          ");
            query.append("       COUNT(1) count                             ");
            query.append("FROM ( SELECT                                     ");
            query.append("                a.comp_no                         ");
            query.append("               ,a.wcode_id                        ");
            query.append("               ,b.wname                           ");
            query.append("               ,a.part_id                         ");
            query.append("               ,c.part_no                         ");
            query.append("               ,c.model                           ");
            query.append("               ,c.vendor_code                     ");
            query.append("               ,c.part_group                      ");
            query.append("               ,c.description                     ");
            query.append("               ,c.pt_size                         ");
            query.append("               ,c.pt_abc_class                    ");
            query.append("               ,a.a_stock_qty                     ");
            query.append("               ,a.b_stock_qty                     ");
            query.append("               ,a.tot_stock_qty                   ");
            query.append("               ,a.a_bin_no                        ");
            query.append("               ,a.b_bin_no                        ");
            query.append("               ,(SELECT safty_qty                 ");
            query.append("                  FROM taptsaftystock aa          ");
            query.append("                  WHERE aa.comp_no = a.comp_no    ");
            query.append("                     AND aa.wcode_id = a.wcode_id ");
            query.append("                     AND aa.part_id = a.part_id)  AS min_safty_qty                                                                                                                    ");
            query.append("                ,(SELECT max_safty_qty            ");
            query.append("                  FROM taptsaftystock aa          ");
            query.append("                  WHERE aa.comp_no = a.comp_no    ");
            query.append("                    AND aa.wcode_id = a.wcode_id  ");
            query.append("                    AND aa.part_id = a.part_id)   AS max_safty_qty                                                                                                                     ");
            query.append("                 ,(select  sum(ISNULL(bb.rec_qty,0) - ISNULL(bb.po_qty,0)) as pr_qty                                                                                                          ");
            query.append("                   from taptprlist aa inner join taptpritem bb on aa.comp_no = bb.comp_no and aa.ptprlist_id = bb.ptprlist_id                                                           ");
            query.append("                   where a.comp_no = bb.comp_no and a.part_id = bb.part_id and a.wcode_id=aa.wcode_id  and aa.ptprlist_status in ('C','POW','POC','GRW','GRC')                          ");
            query.append("                 )                                AS pr_qty                                              ");
            query.append("                 ,(select  sum(ISNULL(bb.po_qty,0) - ISNULL(bb.gr_qty,0)) as po_qty                                                                                                           ");
            query.append("                   from TAPTPOLIST aa inner join TAPTPOITEM bb on aa.comp_no = bb.comp_no and aa.polist_id = bb.polist_id                                                               ");
            query.append("                   where a.comp_no = bb.comp_no and a.part_id = bb.part_id and a.wcode_id=aa.wcode_id  and aa.polist_status in ('C','GRW','GRC')                                        ");
            query.append("                 )                                AS ordered_qty                                         ");
            query.append("                  ,(SELECT SUM(aa.issue_qty)      ");
            query.append("                     FROM TAPTISSLIST aa          ");
            query.append("                     where aa.comp_no =a.comp_no  ");
            query.append("                         and aa.part_id = a.part_id ");
            query.append("                         and a.wcode_id = aa.wcode_id ");
            query.append("                         and aa.ptiss_status in ('W','X') ");
            query.append("                    )                             AS  emg_iss_qty                                         ");
            query.append("                  ,(SELECT SUM(aa.use_qty)        ");
            query.append("                     FROM TAWOPARTS aa inner join  TAWORKORDER bb on aa.comp_no = bb.comp_no                                                                                            ");
            query.append("                                                  and aa.wkor_id = bb.wkor_id                                                                                        ");
            query.append("                                                  and bb.is_deleted= 'N'                                                                                             ");
            query.append("                                                  and bb.wo_status in ( 'PRW','PRWDA','PRWRA','P','PRWOA','PRP' )                                                    ");
            query.append("                     where aa.comp_no =a.comp_no  																");
            query.append("                              and aa.part_id = a.part_id                                                                                                                                ");
            query.append("                              and  aa.wcode_id = a.wcode_id                                                                                                                             ");
            query.append("                              and aa.ptisslist_id is null                                                                                                                               ");
            query.append("                    )                                                                                                            AS current_using_qty                                   ");
            query.append("        from (                                                                                                                                                                          ");
            query.append("                select                                                                                                                                                                  ");
            query.append("                    a.comp_no                                                                                                                                                           ");
            query.append("                    ,a.wcode_id                                                                                                                                                         ");
            query.append("                    ,a.part_id                                                                                                                                                          ");
            query.append("                    ,ISNULL(max(CASE WHEN a.part_grade='A' THEN a.stock_qty else NULL END),0) AS a_stock_qty                                                                                  ");
            query.append("                    ,ISNULL(max(CASE WHEN a.part_grade='B' THEN a.stock_qty else NULL END),0) AS b_stock_qty                                                                                  ");
            query.append("                    ,ISNULL(sum(a.stock_qty),0) AS tot_stock_qty                                                                                                                           ");
            query.append("                    ,max(CASE WHEN a.part_grade='A' THEN a.bin_no END)    AS a_bin_no                                                                                                   ");
            query.append("                    ,max(CASE WHEN a.part_grade='B' THEN a.bin_no END)    AS b_bin_no                                                                                                   ");
            query.append("                from taptstock a                                                                                                                                                        ");
            query.append("                where 1=1                                                                                                                                                               ");
            query.append("                group by a.comp_no, a.wcode_id, a.part_id                                                                                                                               ");
            query.append("            ) a inner join tawarehouse b on a.comp_no = b.comp_no and a.wcode_id = b.wcode_id                                                                                           ");
            query.append("                inner join taparts c on a.comp_no = c.comp_no and a.part_id = c.part_id                                                                                                 ");
            query.append("        where 1=1                                                                                                                                                                       ");
            query.getAndQuery("a.comp_no", user.getCompNo());
            query.getAndQuery("b.wh_categ", "PART");
            query.getAndQuery("c.part_categ", "SPPT");
            query.getAndQuery("c.is_stock_control", "Y");
            query.getAndQuery("b.plant", user.getPlant());
            query.getAndQuery("a.wcode_id", user.getFilterWcodeId());
            query.append(") a                                                               ");
            query.append("WHERE 1=1                                                         ");
            query.append("AND (ISNULL(a.min_safty_qty,0) - ((ISNULL(a.tot_stock_qty,0)+(ISNULL(a.ordered_qty,0)+ISNULL(a.pr_qty,0))) - ISNULL(a.emg_iss_qty,0) - ISNULL(a.current_using_qty,0))) > 0	");
            query.append("AND a.min_safty_qty IS NOT NULL   ");
            query.append("AND a.min_safty_qty != 0          ");
            
        }
        
        //New S/Parts in SAP(Today)
        if(resultMap.containsKey("SPMD"))
        {
            if(flag) query.append("UNION ALL        						");
            
            flag = true;
            
            query.append("SELECT 											 ");
            query.append("       'SPMD' type,                                ");
            query.append("       '"+resultMap.get("SPMD")+"' typeDesc,       ");
            query.append("        '"+ordNoMap.get("SPMD")+"' ordNo,          ");
            query.append("        COUNT(1) count    						 ");
            query.append("FROM TXERPPARTS      								 ");
            query.append("WHERE REPLACE(SUBSTRING(received_date,1, 10),'-','') = CONVERT(VARCHAR, GETDATE(), 112)    ");

        }
        
        //New S/Parts in SAP(This Week)
        if(resultMap.containsKey("SPMW"))
        {
            if(flag) query.append("UNION ALL        						");
            
            flag = true;
            
            query.append("SELECT 											 ");
            query.append("       'SPMW' type,                                ");
            query.append("       '"+resultMap.get("SPMW")+"' typeDesc,       ");
            query.append("        '"+ordNoMap.get("SPMW")+"' ordNo,          ");
            query.append("        COUNT(1) count     						 ");
            query.append("  FROM TXERPPARTS      							 ");
            query.append(" WHERE REPLACE(SUBSTRING(received_date,1, 10),'-','') > CONVERT(VARCHAR, DATEADD(dd, DATEPART(WEEKDAY, GETDATE())*(-1)+2, GETDATE()), 112)	");
            query.append("   AND REPLACE(SUBSTRING(received_date,1, 10),'-','') <= CONVERT(VARCHAR, DATEADD(dd, DATEPART(WEEKDAY, GETDATE())*(-1)+2+6, GETDATE()), 112)	");

        }
        
        //GR today
        if(resultMap.containsKey("SPRD"))
        {
            if(flag) query.append("UNION ALL        						");
            
            flag = true;
            
            query.append("SELECT 											 ");
            query.append("       'SPRD' type,                                ");
            query.append("       '"+resultMap.get("SPRD")+"' typeDesc,       ");
            query.append("        '"+ordNoMap.get("SPRD")+"' ordNo,          ");
            query.append("        COUNT(1) count     						 ");
            query.append("FROM TXPTERPRECLIST      							 ");
            query.append("WHERE REPLACE(SUBSTRING(budat,1, 10),'-','') = CONVERT(VARCHAR, GETDATE(), 112)       ");

        }
        
        //GR this week
        if(resultMap.containsKey("SPRW"))
        {
            if(flag) query.append("UNION ALL        						");
            
            flag = true;
            
            query.append("SELECT 											 ");
            query.append("       'SPRW' type,                                ");
            query.append("       '"+resultMap.get("SPRW")+"' typeDesc,       ");
            query.append("       '"+ordNoMap.get("SPRW")+"' ordNo,           ");
            query.append("        COUNT(1) count     						 ");
            query.append("  FROM TXPTERPRECLIST      					     ");
            query.append(" WHERE REPLACE(SUBSTRING(budat,1, 10),'-','') >= CONVERT(VARCHAR, DATEADD(dd, DATEPART(WEEKDAY, GETDATE())*(-1)+2, GETDATE()), 112)	");
            query.append("   AND REPLACE(SUBSTRING(budat,1, 10),'-','') <= CONVERT(VARCHAR, DATEADD(dd, DATEPART(WEEKDAY, GETDATE())*(-1)+2+6, GETDATE()), 112)	");

        }
        
        //결재대기
        if(resultMap.containsKey("APPR"))
        {
            if(flag) query.append("UNION ALL                                ");
            
            flag = true;
            query.append("SELECT                                            ");
            query.append("       'APPR' type,                                ");
            query.append("       '"+resultMap.get("APPR")+"' typeDesc,       ");
            query.append("       '"+ordNoMap.get("APPR")+"' ordNo,          ");
            query.append("       COUNT(1) count                             ");
            query.append("FROM   TAAPPRLIST x INNER JOIN TAAPPRUSR y               ");
            query.append("ON x.apprlist_id = y.apprlist_id                                       ");
            query.append("AND x.comp_no = y.comp_no                         ");
            query.append("WHERE 1 = 1                                       ");
            query.append("  AND y.apprusr_action = 'P'                         ");
            query.append("  AND x.appr_status IN ('R','P','C')                 ");
            query.getAndQuery("y.appr_by", user.getEmpId());
            query.getAndQuery("x.comp_no", user.getCompNo());
            
        }
        
        //공지사항
        if(resultMap.containsKey("NOTICE"))
        {
        	if(flag) query.append("UNION ALL                                ");
        	
        	flag = true;

        	query.append("SELECT                                            		");
        	query.append("       'NOTICE' TYPE,                                		");
        	query.append("       '"+resultMap.get("NOTICE")+"' typeDesc,       		");
        	query.append("       '"+ordNoMap.get("NOTICE")+"' ordNo,                ");
        	query.append("       COUNT(1) COUNT                             		");
        	query.append("FROM TANOTICE x                          					");
        	query.append("WHERE 1 = 1												");
        	query.append("AND EXISTS(SELECT notiusr_id                              ");
            query.append("           FROM TANOTIUSR                                 ");
            query.append("           WHERE comp_no = x.comp_no                      ");
            query.append("           AND notice_id = x.notice_id                    ");
            query.append("           AND read_yn = 'N'                              ");
            query.append("           AND emp_id = '"+user.getEmpId()+"'             ");
            query.append("          )                                               ");
            query.append("AND x.notice_status = 'C'                                 ");
            query.append("AND x.limit_date >= '"+DateUtil.getDate()+"'              ");
            query.getAndQuery("x.comp_no", user.getCompNo());

        }
        
        // 변경설비(금주)
        if(resultMap.containsKey("EQREVW"))
        {
            if(flag) query.append("UNION ALL                                                    ");
            
            flag = true;
            query.append(" SELECT                                                               ");
            query.append("        'EQREVW' type,                                                ");
            query.append("        '"+resultMap.get("EQREVW")+"' typeDesc,                       ");
            query.append("        '"+ordNoMap.get("EQREVW")+"' ordNo,                           ");
            query.append("        COUNT(1) count                                                ");
            query.append("   FROM TAEQUIPMENT x                                                 ");
            query.append("  INNER JOIN TAREVISIONHIST y                                         ");
            query.append("          ON x.revisionhist_id = y.revisionhist_id                    ");
            query.append("  WHERE 1 = 1                                                         ");
            query.getAndQuery("x.comp_no", user.getCompNo());
            query.getAndQuery("x.plant", user.getPlant());
            query.getAndQuery("x.is_deleted", "N");
            query.getAndQuery("x.is_last_version", "Y");
            query.getAndQuery("x.eqctg_type", "MC");
            query.getAndQuery("y.revision_status", "C");
            query.getAndQuery("y.revision_object_type", "ASSET");
            query.append(" WHERE REPLACE(SUBSTRING(y.wrk_date,1, 10),'-','') > CONVERT(VARCHAR, DATEADD(dd, DATEPART(WEEKDAY, GETDATE())*(-1)+2, GETDATE()), 112)    ");
            query.append("   AND REPLACE(SUBSTRING(y.wrk_date,1, 10),'-','') <= CONVERT(VARCHAR, DATEADD(dd, DATEPART(WEEKDAY, GETDATE())*(-1)+2+6, GETDATE()), 112) ");
        }
        
        //미완료 작업 과거
        if(resultMap.containsKey("NWP"))
        {
        	if(flag) query.append("UNION ALL                                ");
        	
        	flag = true;

            query.append("SELECT                                            ");
            query.append("       'NWP' type,                                ");
            query.append("       '"+resultMap.get("NWP")+"' typeDesc,       ");
            query.append("       '"+ordNoMap.get("NWP")+"' ordNo,         	");
            query.append("       COUNT(1) count                             ");
            query.append("FROM   TAWORKORDER x                              ");
            query.append("WHERE  x.wkor_date <= CONVERT(VARCHAR, GETDATE(), 112)  ");
            query.append("  AND  x.wo_status = 'P'                        	");
            query.append("  AND x.is_deleted = 'N'                         	");
            query.append("  AND x.wo_type NOT IN ( 'PMI' )                  ");
            if( (!"".equals(user.getEqLocId())&&!"null".equals(user.getEqLocId())) 
                    || (!"".equals(user.getEqLocDesc())&&!"null".equals(user.getEqLocDesc())) )
            {
                query.append("AND x.wkor_id IN (SELECT a.wkor_id                        ");
                query.append("                  FROM TAWOEQUIP a, TAEQUIPMENT b         ");
                query.append("                  WHERE a.comp_no = b.comp_no             ");
                query.append("                  AND   a.equip_id = b.equip_id           ");
                query.getStringEqualQuery("a.comp_no", user.getCompNo());
                query.getEqLocLevelQuery("b.eqloc_id", user.getEqLocId(), user.getEqLocDesc(), user.getCompNo());
                query.append("                  )                                       ");
            }
            if( (!"".equals(user.getEqCtgTypeId())&&!"null".equals(user.getEqCtgTypeId())) 
                    || (!"".equals(user.getEqCtgTypeDesc())&&!"null".equals(user.getEqCtgTypeDesc())) ){
                query.append("AND x.wkor_id IN (SELECT a.wkor_id                        ");
                query.append("                  FROM TAWOEQUIP a, TAEQUIPMENT b         ");
                query.append("                  WHERE a.comp_no = b.comp_no             ");
                query.append("                  AND   a.equip_id = b.equip_id           ");
                query.getStringEqualQuery("a.comp_no", user.getCompNo());
                query.getSysCdQuery("b.eqctg_type", user.getEqCtgTypeId(), user.getEqCtgTypeDesc(), "EQCTG_TYPE", user.getCompNo(),user.getLangId());
                query.append("                  )                                       ");
            }
            query.getCodeLikeQuery("x.emp_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = x.emp_id AND a.comp_no=x.comp_no)", 
                    user.getFilterEmpId(), user.getFilterEmpDesc());
            query.getDeptLevelQuery("x.dept_id", user.getFilterDeptId(), user.getFilterDeptDesc(), user.getCompNo());
            query.getWkCtrLevelQuery("x.wkctr_id", user.getWkctrId(), user.getWkctrDesc(), user.getCompNo());
            query.getAndQuery("x.comp_no", user.getCompNo());
            query.getAndQuery("x.plant", user.getPlant());

            //최신버전의 설비의 작업만 보여줌.
            query.append("AND NOT EXISTS (SELECT a.wkor_id  				");
        	query.append("				  FROM TAWOEQUIP a, TAEQUIPMENT b	");
        	query.append("				  WHERE a.comp_no = b.comp_no		");
        	query.append("				   AND a.equip_id = b.equip_id		");
        	query.append("                  AND a.wkor_id = x.wkor_id		");
        	query.getStringEqualQuery("a.comp_no", user.getCompNo());
        	query.getAndQuery("b.is_last_version", "N");
            query.append("						)							");
            
        }
        
        // 작업요청접수대기
        if(resultMap.containsKey("NREC"))
        {
            if(flag) query.append("UNION ALL                                        ");
            
            flag = true;

            query.append("SELECT                                                    ");
            query.append("       'NREC' type,                                       ");
            query.append("       '"+resultMap.get("NREC")+"' typeDesc,              ");
            query.append("       '"+ordNoMap.get("NREC")+"' ordNo,                  ");
            query.append("       COUNT(1) count                                     ");
            query.append("FROM   TAWOREQ x                                          ");
            query.append("WHERE  x.woreq_status = 'REQ'                             ");
            query.getAndQuery("x.comp_no", user.getCompNo());
            query.getCodeLikeQuery("x.rec_emp_id", "(SELECT a.emp_name FROM TAEMP a WHERE a.emp_id = x.req_emp_id AND a.comp_no=x.comp_no)", 
                                    user.getFilterEmpId(), user.getFilterEmpDesc());
            query.getDeptLevelQuery("x.rec_dept_id", user.getFilterDeptId(), user.getFilterDeptDesc(), user.getCompNo());
            query.getAndQuery("x.plant", user.getFilterPlant());
        }
        
        query.append("    ORDER BY ordNo ASC                   ");
        return getJdbcTemplate().queryForList(query.toString());
    }     
    
    
    public List findAlarmCode(User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                                    ");
        query.append("          cdsysd_no code,                                                 ");
        query.append("          ISNULL((SELECT aa.key_name								        ");
        query.append("                    FROM talang aa									    ");
        query.append("                   WHERE lang = '"+loginUser.getLangId()+"'			    ");
        query.append("                     AND x.key_type = aa.key_type						    ");
        query.append("                     AND x.key_no = aa.key_no), x.description)			");
        query.append("          description,											        ");
        query.append("          ord_no     ordNo                                                ");
        query.append("  FROM    TACDSYSD x                                                      ");
        query.append(" WHERE    cdsysm_id = (SELECT a.cdsysm_id                                 ");
        query.append("                         FROM TACDSYSM a                                  ");
        query.append("                        WHERE a.list_type = 'INIT_ALARM_LIST')            ");
        query.append("   AND    cdsysd_no IN (SELECT b.init_alarm_list                          ");
        query.append("                          FROM TAINITALARMEMPSET b                        ");
        query.append("                         WHERE b.emp_id = '"+ loginUser.getEmpId() +"'    ");
        query.append("                           AND b.comp_no = '"+ loginUser.getCompNo() +"'  ");
        query.append("                           AND b.is_use = 'Y'                             ");
        query.append("                           AND b.is_notice = 'Y')                         ");
        query.append("   AND    is_use = 'Y'                                                    ");
        query.append("ORDER BY ISNULL(ord_no, '99999999')                                       ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }

}