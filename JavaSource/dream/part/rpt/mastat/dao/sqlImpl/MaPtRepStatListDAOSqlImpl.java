package dream.part.rpt.mastat.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.part.rpt.mastat.dao.MaPtRepStatListDAO;
import dream.part.rpt.mastat.dto.MaPtRepStatCommonDTO;

/**
 * 자재수리내역 - 목록 dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maPtRepStatListDAOTarget"
 * @spring.txbn id="maPtRepStatListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPtRepStatListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaPtRepStatListDAO
{
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRepStatCommonDTO
     * @return List
     */
    public List findList(MaPtRepStatCommonDTO maPtRepStatCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        String compNo = maPtRepStatCommonDTO.getCompNo();
        
        query.append("SELECT                                                                		");
        query.getDate("repair_date", "repairDate, 													");
        query.append("       dbo.SFAIDTODESC(x.dept_id, '', 'DEPT', x.comp_no) deptDesc,       		");
        query.append("        (SELECT c.full_desc                                           		");
        query.append("            FROM TAEQLOC c                                            		");
        query.append("            WHERE b.eqloc_id = c.eqloc_id                                		");
        query.append("                AND b.comp_no = c.comp_no) AS line,                   		");
        query.append("        (SELECT c.description                                            		");
        query.append("            FROM TAEQCTG c                                            		");
        query.append("            WHERE b.eqctg_id = c.eqctg_id                                		");
        query.append("                AND b.comp_no = c.comp_no) AS eqType,                    		");
        query.append("        b.description AS eqDesc,                                        		");
        query.append("      (SELECT description FROM TAPARTS                                		");
        query.append("       WHERE comp_no = x.comp_no                                      		");
        query.append("         AND part_id = x.part_id)             partDesc,               		");
        query.append("      (SELECT pt_size FROM TAPARTS                                    		");
        query.append("       WHERE comp_no = x.comp_no                                      		");
        query.append("         AND part_id = x.part_id)             ptSize,                 		");
        query.append("      (SELECT MODEL FROM TAPARTS                                      		");
        query.append("       WHERE comp_no = x.comp_no                                      		");
        query.append("         AND part_id = x.part_id)             MODEL,                  		");
        query.append("       dbo.SFAIDTODESC(y.vendor_id, '', 'VENDOR', y.comp_no)    vendorDesc,           ");
        query.append("       dbo.SFAIDTODESC(x.vendor_id, '', 'VENDOR', x.comp_no)    repairVendorDesc,     ");
        query.append("       (ISNULL(repair_qty, 0))                   repairQty,                      		");
        query.append("       (ISNULL(unit_price, 0))                   unitPrice,                      		");
        query.append("       (ISNULL(tot_price, 0))                    totPrice,                       		");
        query.append("       y.perform                              perform,                        		");
        query.append("       dbo.SFAIDTODESC(inspector, '', 'EMP', x.comp_no) inspectorName         		");
        query.append("FROM  TAPTREPAIRLIST x                                                    			");
        query.append("LEFT OUTER JOIN  TAWORKORDER y                                            			");
        query.append("ON x.comp_no = y.comp_no                                                    			");
        query.append("AND x.wkor_id = y.wkor_id                                                    			");
        query.append("INNER JOIN TAWOEQUIP a                        	");
        query.append("ON y.comp_no = a.comp_no                  		");
        query.append("AND y.wkor_id = a.wkor_id                 		");
        query.append("INNER JOIN TAEQUIPMENT b                  		");
        query.append("ON a.comp_no = b.comp_no                  		");
        query.append("AND a.equip_id = b.equip_id               		");
        query.append("WHERE 1=1                                   		");
        query.append("AND x.comp_no = '"+compNo+"'				");
        query.append("AND x.ptrepairlist_status IN('V','L')		");//사내수리,외주수리
        query.append(this.getWhere(maPtRepStatCommonDTO, user));
        query.getOrderByQuery("x.ptrepairlist_id","x.repair_date Desc", maPtRepStatCommonDTO.getOrderBy(), maPtRepStatCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maPtRepStatCommonDTO.getIsLoadMaxCount(), maPtRepStatCommonDTO.getFirstRow()));
    }
    
    /**
     * Filter 조건
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRepStatCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaPtRepStatCommonDTO maPtRepStatCommonDTO, User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.getAndQuery("x.comp_no", user.getCompNo());
      
        // 부서
        query.getDeptLevelQuery("x.dept_id", maPtRepStatCommonDTO.getFilterDeptId(),maPtRepStatCommonDTO.getFilterDeptDesc(), maPtRepStatCommonDTO.getCompNo());
        
        //입고일자
        String startDate = maPtRepStatCommonDTO.getFilterStartDate();
        String endDate = maPtRepStatCommonDTO.getFilterEndDate();
        query.getAndDateQuery("x.repair_date", startDate, endDate);

        return query.toString();
    }

	@Override
	public String findTotalCount(MaPtRepStatCommonDTO maPtRepStatCommonDTO, User user)
	{
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT                                      		");
        query.append("       COUNT(1)             						");
        query.append("FROM  TAPTREPAIRLIST x                            ");
        query.append("LEFT OUTER JOIN  TAWORKORDER y                    ");
        query.append("ON x.comp_no = y.comp_no                          ");
        query.append("AND x.wkor_id = y.wkor_id                         ");
        query.append("INNER JOIN TAWOEQUIP a                        	");
        query.append("ON y.comp_no = a.comp_no                  		");
        query.append("AND y.wkor_id = a.wkor_id                 		");
        query.append("INNER JOIN TAEQUIPMENT b                  		");
        query.append("ON a.comp_no = b.comp_no                  		");
        query.append("AND a.equip_id = b.equip_id               		");
        query.append("WHERE 1=1                                   		");
        query.append("AND x.comp_no = '"+user.getCompNo()+"'			");
        query.append("AND x.ptrepairlist_status IN('V','L')				");
        query.append(this.getWhere(maPtRepStatCommonDTO, user));
        
        List resultList = getJdbcTemplate().queryForList(query.toString());
        
		return QuerySqlBuffer.listToString(resultList);
	}
}