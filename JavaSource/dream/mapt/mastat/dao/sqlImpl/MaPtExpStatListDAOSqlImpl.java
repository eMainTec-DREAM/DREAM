package dream.mapt.mastat.dao.sqlImpl;

import java.util.List;

import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.mapt.mastat.dao.MaPtExpStatListDAO;
import dream.mapt.mastat.dto.MaPtExpStatCommonDTO;

/**
 * 자재비용분석 - 목록 dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maPtExpStatListDAOTarget"
 * @spring.txbn id="maPtExpStatListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPtExpStatListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaPtExpStatListDAO
{
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtExpStatCommonDTO
     * @return List
     */
    public List findList(MaPtExpStatCommonDTO maPtExpStatCommonDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        String compNo = maPtExpStatCommonDTO.getCompNo();
        
        query.append("SELECT                                                    ");
        query.getDate("x.rec_date", "recDate,");
        query.append("       dbo.SFAIDTODESC(x.dept_id, '', 'DEPT', x.comp_no) deptDesc,    ");
        query.append("       y.description              description,    ");
        query.append("       y.pt_size                  ptSize,         ");
        query.append("       y.maker                    maker,          ");
        query.append("       SUM(x.rec_qty)             recQty,         ");
        query.append("       SUM(x.unit_price)          unitPrice,      ");
        query.append("       SUM(x.tot_price)           totPrice        ");
        query.append("FROM   TAPTPRRECLIST x LEFT OUTER JOIN TAPARTS y          ");
        query.append("	 ON x.comp_no = y.comp_no                            	");
        query.append("  AND x.part_id = y.part_id                          		");
        query.append("WHERE 1=1							                        ");
        query.append("  AND x.comp_no = '"+compNo+"'                            ");
        query.append(this.getWhere(maPtExpStatCommonDTO));
        query.append("group by x.rec_date, x.dept_id, x.comp_no, y.description, y.pt_size, y.maker ");
        query.append("ORDER BY x.rec_date DESC                                  ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * Filter 조건
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtExpStatCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaPtExpStatCommonDTO maPtExpStatCommonDTO)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
      
        // 부서
        query.getDeptLevelQuery("x.dept_id", maPtExpStatCommonDTO.getFilterDeptId(), maPtExpStatCommonDTO.getFilterDeptDesc(), maPtExpStatCommonDTO.getCompNo());

        return query.toString();
    }
}