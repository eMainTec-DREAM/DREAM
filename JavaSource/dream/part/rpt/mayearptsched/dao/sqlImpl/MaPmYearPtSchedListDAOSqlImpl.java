package dream.part.rpt.mayearptsched.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.DateUtil;
import common.util.QuerySqlBuffer;
import dream.part.rpt.mayearptsched.dao.MaPmYearPtSchedListDAO;
import dream.part.rpt.mayearptsched.dto.MaPmYearPtSchedCommonDTO;

/**
 * 연간부품사용일정 DAO
 * @author  ssong
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="maPmYearPtSchedListDAOTarget"
 * @spring.txbn id="maPmYearPtSchedListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPmYearPtSchedListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaPmYearPtSchedListDAO
{  
    /**
     * 연간 grid find
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPmYearPtSchedCommonDTO
     * @param user
     * @return List
     */
    public List findYearList(MaPmYearPtSchedCommonDTO maPmYearPtSchedCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        String compNo = user.getCompNo();
        String year = maPmYearPtSchedCommonDTO.getFilterYear();
        String deptId = maPmYearPtSchedCommonDTO.getFilterDeptId();
        String deptDesc = maPmYearPtSchedCommonDTO.getFilterDeptDesc();
        String eqCtgTypeId = maPmYearPtSchedCommonDTO.getFilterEqCtgTypeId();
        String eqCtgTypeDesc = maPmYearPtSchedCommonDTO.getFilterEqCtgTypeDesc();
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                  ");
        if ("".equals(deptId)) {
        	query.append("SELECT dept_id				");
        	query.append("FROM TADEPT					");
        	query.append("WHERE comp_no = '"+compNo+"'	");
        	query.append("AND p_dept_id = 0				");
			deptId = QuerySqlBuffer.listToString(this.getJdbcTemplate().queryForList(query.toString()));
		}
        query = new QuerySqlBuffer();
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                  ");
        query.append("SELECT tb.dept_id as ID               ");
        query.append("      ,tb.dept_id as DEPTID           ");
        query.append("      ,tb.p_dept_id as PDEPTID        ");
        query.append("      ,dc.lvl as  LVL                  ");
        query.append("      ,MIN(dc.lvl) OVER() AS MINLVL    ");
        query.append("      ,tb.description as DEPTDESC     ");
        
        for (int i = 1; i <= 12; i++) {
            
            String month = String.valueOf(i);
            if(month.length()==1) month = "0"+month;
            
            query.append("  ,(SELECT COUNT(DISTINCT CONVERT(varchar,z.part_id)+','+CONVERT(varchar,xx.dept_id))  AS cnt         ");
            query.append("      FROM TAPMSCHED x INNER JOIN TAPMLST y                           ");
            query.append("      ON x.comp_no = y.comp_no                                        ");
            query.append("      AND x.pm_id = y.pm_id                                           ");
            query.append("      INNER JOIN TAPMPART z                                           ");
            query.append("      ON y.comp_no = z.comp_no                                        ");
            query.append("      AND y.pm_id = z.pm_id                                           ");
            query.append("      INNER JOIN TADEPT xx                                            ");
            query.append("      ON y.comp_no = xx.comp_no                                       ");
            query.append("      AND y.dept_id = xx.dept_id                                      ");
            query.append("      WHERE 1=1                                                       ");
            query.append("      AND  x.comp_no = tb.comp_no                                     ");
            query.append("      AND x.sched_date like '"+year+month+"__'                        ");
            query.append("      AND y.dept_id IN (SELECT a.dept_id                              ");
            query.append("                          FROM TADEPT a INNER JOIN dbo.SFADEPT_CALL(tb.comp_no,tb.dept_id,'') b                            ");
            query.append("                          ON a.comp_no = b.comp_no AND a.dept_id = b.dept_id and a.is_use='Y'                           ");
            query.append("                      )                                               ");
            //설비유형 
            if(!"".equals(eqCtgTypeId)||!"".equals(eqCtgTypeDesc)){
                query.append("      AND y.pm_id IN (SELECT a.pm_id                                      ");
                query.append("                      FROM TAPMEQUIP a                                    ");
                query.append("                      WHERE a.comp_no = tb.comp_no                        ");
                query.append("                      AND a.equip_id IN (SELECT b.equip_id                ");
                query.append("                                          FROM TAEQUIPMENT b              ");
                query.append("                                          WHERE b.comp_no = tb.comp_no    ");
                query.getSysCdQuery("b.eqctg_type", eqCtgTypeId, eqCtgTypeDesc, "EQCTG_TYPE", compNo,user.getLangId());
                query.append("                                          )                               ");
                query.append("                  )                                                       ");
            }
            
            query.append("      ) AS CNT"+month+"                                               ");
            
        }
        query.append("FROM TADEPT tb INNER JOIN dbo.SFADEPT_CALL('"+compNo+"','"+deptId+"','') dc ");
        query.append("ON tb.comp_no = dc.comp_no AND tb.dept_id = dc.dept_id                            ");
        query.append("AND tb.is_use = 'Y'                            ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * 부품별 grid find
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPmYearPtSchedCommonDTO
     * @param user
     * @return List
     */
    public List findPartsList(MaPmYearPtSchedCommonDTO maPmYearPtSchedCommonDTO, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        String year  = maPmYearPtSchedCommonDTO.getYearMon().substring(0, 4);
        String month = maPmYearPtSchedCommonDTO.getYearMon().substring(4, 6);   
        
        String lastDay = DateUtil.getLastDayOfMonth(year, month);
        
        String fromDate = maPmYearPtSchedCommonDTO.getYearMon()+"01";
        String toDate = maPmYearPtSchedCommonDTO.getYearMon()+lastDay;
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;         ");
        query.append("SELECT ''                             seqNo,              ");
        query.append("       deptDesc,                                          ");
        query.append("       y.part_no                      partNo,             ");
        query.append("       y.description+', '+ISNULL(y.pt_size,'') partNameSize,       	");
        query.append("       useQty,                                            ");
        query.append("      (SELECT SUM(ISNULL(stock_qty, 0)) FROM TAPTSTOCK    ");
        query.append("       WHERE  comp_no  = x.comp_no                        ");
        query.append("         AND  wcode_id = x.wcode_id                       ");
        query.append("         AND  part_id  = y.part_id)   stockQty,           ");
        query.append("       lead_time                      leadTime,         ");
        query.append("       y.description                  partDesc,           ");
        query.append("       ISNULL(y.model,'')           	model,				");
        query.append("       ISNULL(y.pt_size,'')           partSize           ");
        query.append("FROM (                                                    ");
        query.append("       SELECT TOP 100 PERCENT y.comp_no, y.dept_id        ");
        query.append("            , xx.description deptDesc, xx.wcode_id        ");
        query.append("            , SUBSTRING(x.sched_date,1,6)  AS yyyymm      ");
        query.append("            , z.part_id, SUM(z.use_qty) AS useQty         ");
        query.append("       FROM TAPMSCHED x, TAPMLST y, TAPMPART z, TADEPT xx ");
        query.append("       WHERE x.comp_no = y.comp_no                        ");
        query.append("         AND x.pm_id   = y.pm_id                          ");
        query.append("         AND y.comp_no = z.comp_no                        ");
        query.append("         AND y.pm_id   = z.pm_id                          ");
        query.append("         AND y.comp_no = xx.comp_no                       ");
        query.append("         AND y.dept_id = xx.dept_id                       ");
        query.getAndQuery("x.comp_no", maPmYearPtSchedCommonDTO.getCompNo());
        query.getDeptLevelQuery("y.dept_id", maPmYearPtSchedCommonDTO.getDeptId(), "", maPmYearPtSchedCommonDTO.getCompNo());
        query.getAndDateQuery("x.sched_date", fromDate, toDate);
    	//설비유형 
    	if(!"".equals(maPmYearPtSchedCommonDTO.getFilterEqCtgTypeId())||!"".equals(maPmYearPtSchedCommonDTO.getFilterEqCtgTypeDesc())){
        	query.append("AND y.pm_id IN (SELECT pm_id FROM TAPMEQUIP 			");
        	query.append("				WHERE equip_id IN (SELECT equip_id		");
        	query.append("									FROM TAEQUIPMENT	");
        	query.append("									WHERE 1=1			");
        	query.getSysCdQuery("eqctg_type", maPmYearPtSchedCommonDTO.getFilterEqCtgTypeId(), maPmYearPtSchedCommonDTO.getFilterEqCtgTypeDesc(), "EQCTG_TYPE", maPmYearPtSchedCommonDTO.getCompNo(),user.getLangId());
        	query.append("									))					");
        }
        query.append("       GROUP BY y.comp_no, y.dept_id, SUBSTRING(x.sched_date, 1, 6)	");
        query.append("               ,z.part_id, xx.description, xx.wcode_id, xx.ord_no     ");
        query.append("       ORDER BY xx.ord_no                                 ");
        query.append(")x, TAPARTS y                                             ");
        query.append("WHERE x.comp_no  = y.comp_no                              ");
        query.append("  AND  x.part_id = y.part_id                              ");
        query.getOrderByQuery("y.part_id","y.part_no", maPmYearPtSchedCommonDTO.getOrderBy(), maPmYearPtSchedCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maPmYearPtSchedCommonDTO.getIsLoadMaxCount(), maPmYearPtSchedCommonDTO.getFirstRow()));
    }
    
    /**
     * 일자별 grid find
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPmYearPtSchedCommonDTO
     * @param user
     * @return List
     */
    public List findDateList(MaPmYearPtSchedCommonDTO maPmYearPtSchedCommonDTO, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        String year  = maPmYearPtSchedCommonDTO.getYearMon().substring(0, 4);
        String month = maPmYearPtSchedCommonDTO.getYearMon().substring(4, 6);   
        String lastDay = DateUtil.getLastDayOfMonth(year, month);
        String fromDate = maPmYearPtSchedCommonDTO.getYearMon()+"01";
        String toDate = maPmYearPtSchedCommonDTO.getYearMon()+lastDay;
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;         ");
        query.append("SELECT ''                                 seqNo,          ");
        query.append("       dbo.SFAIDTODESC(xx.dept_id, '', 'DEPT', xx.comp_no) deptDesc, ");
        query.getDate("sched_date", "schedDate,");
        query.append("        (SELECT c.full_desc										");
        query.append("         FROM TAEQLOC c        									");
        query.append("         WHERE b.eqloc_id = c.eqloc_id							");
        query.append("         AND  b.comp_no = c.comp_no) AS eqlocDesc,        		");
        query.append("         b.description AS equipDesc,        						");
        query.append("         b.item_no AS itemNo,        								");
        query.append("         xx.use_qty                         	  useQty,          		");
        query.append("         xx.part_no       					  partNo,   			");
        query.append("         xx.description+', '+xx.pt_size       partNameSize,   		");
        query.append("         xx.description       				  partdesc,   			");
        query.append("         xx.model       						  model,   				");
        query.append("         xx.pt_size       					  partsize   			");
        query.append("FROM (                                                    ");
        query.append("       SELECT y.comp_no, y.dept_id, x.sched_date          ");
        query.append("            , z.part_id, x.pm_id                          ");
        query.append("            , SUM(z.use_qty) AS use_qty                   ");
        query.append("            , a.part_no, a.description, a.pt_size         ");
        query.append("            , a.model					                     ");
        query.append("       FROM   TAPMSCHED x, TAPMLST y, TAPMPART z, TAPARTS a   ");
        query.append("       WHERE  x.comp_no = y.comp_no                       ");
        query.append("         AND  x.pm_id   = y.pm_id                         ");
        query.append("         AND  y.comp_no = z.comp_no                       ");
        query.append("         AND  y.pm_id   = z.pm_id                         ");
        query.append("         AND  z.comp_no   = a.comp_no                     ");
        query.append("         AND  z.part_id   = a.part_id                     ");
        query.getAndQuery("x.comp_no", maPmYearPtSchedCommonDTO.getCompNo());
        query.getDeptLevelQuery("y.dept_id", maPmYearPtSchedCommonDTO.getDeptId(), "", maPmYearPtSchedCommonDTO.getCompNo());
        query.getAndDateQuery("x.sched_date", fromDate, toDate);
      //설비유형 
    	if(!"".equals(maPmYearPtSchedCommonDTO.getFilterEqCtgTypeId())||!"".equals(maPmYearPtSchedCommonDTO.getFilterEqCtgTypeDesc())){
        	query.append("AND y.pm_id IN (SELECT pm_id FROM TAPMEQUIP 			");
        	query.append("				WHERE equip_id IN (SELECT equip_id		");
        	query.append("									FROM TAEQUIPMENT	");
        	query.append("									WHERE 1=1			");
        	query.getSysCdQuery("eqctg_type", maPmYearPtSchedCommonDTO.getFilterEqCtgTypeId(), maPmYearPtSchedCommonDTO.getFilterEqCtgTypeDesc(), "EQCTG_TYPE", maPmYearPtSchedCommonDTO.getCompNo(),user.getLangId());
        	query.append("									))					");
        }
        query.append("       GROUP BY  y.comp_no, y.dept_id, x.sched_date ,z.part_id, x.pm_id, a.part_no, a.description, a.pt_size, a.model ");
        query.append(")xx                                                       ");
        query.append("INNER JOIN TAPMEQUIP a            	");
        query.append("ON xx.comp_no = a.comp_no        		");
        query.append("AND xx.pm_id = a.pm_id				");
        query.append("INNER JOIN TAEQUIPMENT b        		");
        query.append("ON a.comp_no = b.comp_no         		");
        query.append("AND a.equip_id = b.equip_id           ");
        query.append("WHERE 1=1 							");
        query.getOrderByQuery("xx.part_id","xx.sched_date", maPmYearPtSchedCommonDTO.getOrderBy(), maPmYearPtSchedCommonDTO.getDirection());

        return getJdbcTemplate().queryForList(query.toString(maPmYearPtSchedCommonDTO.getIsLoadMaxCount(), maPmYearPtSchedCommonDTO.getFirstRow()));
    }

	@Override
	public String findPartsTotalCount(MaPmYearPtSchedCommonDTO maPmYearPtSchedCommonDTO, User user) throws Exception
	{
        QuerySqlBuffer query = new QuerySqlBuffer();

        String year  = maPmYearPtSchedCommonDTO.getYearMon().substring(0, 4);
        String month = maPmYearPtSchedCommonDTO.getYearMon().substring(4, 6);   
        
        String lastDay = DateUtil.getLastDayOfMonth(year, month);
        
        String fromDate = maPmYearPtSchedCommonDTO.getYearMon()+"01";
        String toDate = maPmYearPtSchedCommonDTO.getYearMon()+lastDay;
        

        query.append("SELECT               ");
        query.append("       count(1)            ");
        query.append("FROM (                                                    ");
        query.append("       SELECT y.comp_no, y.dept_id                        ");
        query.append("            , xx.description deptDesc, xx.wcode_id        ");
        query.append("            , SUBSTRING(x.sched_date,1,6)  AS yyyymm         ");
        query.append("            , z.part_id, SUM(z.use_qty) AS useQty         ");
        query.append("       FROM TAPMSCHED x, TAPMLST y, TAPMPART z, TADEPT xx ");
        query.append("       WHERE x.comp_no = y.comp_no                        ");
        query.append("         AND x.pm_id   = y.pm_id                          ");
        query.append("         AND y.comp_no = z.comp_no                        ");
        query.append("         AND y.pm_id   = z.pm_id                          ");
        query.append("         AND y.comp_no = xx.comp_no                       ");
        query.append("         AND y.dept_id = xx.dept_id                       ");
        query.getAndQuery("x.comp_no", maPmYearPtSchedCommonDTO.getCompNo());
        query.getDeptLevelQuery("y.dept_id", maPmYearPtSchedCommonDTO.getDeptId(), "", maPmYearPtSchedCommonDTO.getCompNo());
        query.getAndDateQuery("x.sched_date", fromDate, toDate);
    	//설비유형 
    	if(!"".equals(maPmYearPtSchedCommonDTO.getFilterEqCtgTypeId())||!"".equals(maPmYearPtSchedCommonDTO.getFilterEqCtgTypeDesc())){
        	query.append("AND y.pm_id IN (SELECT pm_id FROM TAPMEQUIP 			");
        	query.append("				WHERE equip_id IN (SELECT equip_id		");
        	query.append("									FROM TAEQUIPMENT	");
        	query.append("									WHERE 1=1			");
        	query.getSysCdQuery("eqctg_type", maPmYearPtSchedCommonDTO.getFilterEqCtgTypeId(), maPmYearPtSchedCommonDTO.getFilterEqCtgTypeDesc(), "EQCTG_TYPE", maPmYearPtSchedCommonDTO.getCompNo(),user.getLangId());
        	query.append("									))					");
        }
        query.append("       GROUP BY y.comp_no, y.dept_id, SUBSTRING(x.sched_date, 1, 6)      ");
        query.append("               ,z.part_id, xx.description, xx.wcode_id, xx.ord_no     ");
        query.append(")x, TAPARTS y                                             ");
        query.append("WHERE x.comp_no  = y.comp_no                              ");
        query.append("  AND  x.part_id = y.part_id                              ");
		
        List resultList = getJdbcTemplate().queryForList(query.toString());
        
		return QuerySqlBuffer.listToString(resultList);
	}

	@Override
	public String findDateTotalCount(MaPmYearPtSchedCommonDTO maPmYearPtSchedCommonDTO, User user) throws Exception
	{
        QuerySqlBuffer query = new QuerySqlBuffer();

        String year  = maPmYearPtSchedCommonDTO.getYearMon().substring(0, 4);
        String month = maPmYearPtSchedCommonDTO.getYearMon().substring(4, 6);   
        String lastDay = DateUtil.getLastDayOfMonth(year, month);
        String fromDate = maPmYearPtSchedCommonDTO.getYearMon()+"01";
        String toDate = maPmYearPtSchedCommonDTO.getYearMon()+lastDay;

        query.append("SELECT               ");
        query.append("       count(1)            ");
        query.append("FROM (                                                    ");
        query.append("       SELECT y.comp_no, y.dept_id, x.sched_date          ");
        query.append("            , z.part_id, x.pm_id                          ");
        query.append("            , SUM(z.use_qty) AS use_qty                   ");
        query.append("            , a.part_no, a.description, a.pt_size         ");
        query.append("            , a.model					                     ");
        query.append("       FROM   TAPMSCHED x, TAPMLST y, TAPMPART z, TAPARTS a   ");
        query.append("       WHERE  x.comp_no = y.comp_no                       ");
        query.append("         AND  x.pm_id   = y.pm_id                         ");
        query.append("         AND  y.comp_no = z.comp_no                       ");
        query.append("         AND  y.pm_id   = z.pm_id                         ");
        query.append("         AND  z.comp_no   = a.comp_no                     ");
        query.append("         AND  z.part_id   = a.part_id                     ");
        query.getAndQuery("x.comp_no", maPmYearPtSchedCommonDTO.getCompNo());
        query.getDeptLevelQuery("y.dept_id", maPmYearPtSchedCommonDTO.getDeptId(), "", maPmYearPtSchedCommonDTO.getCompNo());
        query.getAndDateQuery("x.sched_date", fromDate, toDate);
        //설비유형 
    	if(!"".equals(maPmYearPtSchedCommonDTO.getFilterEqCtgTypeId())||!"".equals(maPmYearPtSchedCommonDTO.getFilterEqCtgTypeDesc())){
        	query.append("AND y.pm_id IN (SELECT pm_id FROM TAPMEQUIP 			");
        	query.append("				WHERE equip_id IN (SELECT equip_id		");
        	query.append("									FROM TAEQUIPMENT	");
        	query.append("									WHERE 1=1			");
        	query.getSysCdQuery("eqctg_type", maPmYearPtSchedCommonDTO.getFilterEqCtgTypeId(), maPmYearPtSchedCommonDTO.getFilterEqCtgTypeDesc(), "EQCTG_TYPE", maPmYearPtSchedCommonDTO.getCompNo(),user.getLangId());
        	query.append("									))					");
        }
        query.append("       GROUP BY  y.comp_no, y.dept_id, x.sched_date ,z.part_id, x.pm_id, a.part_no, a.description, a.pt_size, a.model ");
        query.append(")xx                                                       ");
        query.append("INNER JOIN TAPMEQUIP a            	");
        query.append("ON xx.comp_no = a.comp_no        		");
        query.append("AND xx.pm_id = a.pm_id				");
        query.append("INNER JOIN TAEQUIPMENT b        		");
        query.append("ON a.comp_no = b.comp_no         		");
        query.append("AND a.equip_id = b.equip_id           ");
        query.append("WHERE 1=1 							");
        
        List resultList = getJdbcTemplate().queryForList(query.toString());
        
		return QuerySqlBuffer.listToString(resultList);
	}
    
}