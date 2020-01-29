package dream.part.rpt.mastat.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.part.rpt.mastat.dao.MaPtRecStatListDAO;
import dream.part.rpt.mastat.dto.MaPtRecStatCommonDTO;

/**
 * 자재입고내역 - 목록 dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maPtRecStatListDAOTarget"
 * @spring.txbn id="maPtRecStatListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPtRecStatListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaPtRecStatListDAO
{
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRecStatCommonDTO
     * @return List
     */
    public List findList(MaPtRecStatCommonDTO maPtRecStatCommonDTO)
    {
        QueryBuffer query = new QueryBuffer();
        String compNo = maPtRecStatCommonDTO.getCompNo();
        
        query.append("SELECT                                                    ");
        query.getDate("x.rec_date", "recDate,");
        query.append("       SFAIDTODESC(x.dept_id, '', 'DEPT', x.comp_no) deptDesc,    ");
        query.append("       y.description              description,    ");
        query.append("       y.pt_size                  ptSize,         ");
        query.append("       y.maker                    maker,          ");
        query.append("       y.model                    model,          ");
        query.append("       x.rec_qty                  recQty,         ");
        query.append("       x.unit_price               unitPrice,      ");
        query.append("       x.tot_price                totPrice,       ");
        query.append("       y.part_no                  partNo,         ");
        query.append("       (SELECT wname FROM TAWAREHOUSE WHERE wcode_id=x.wcode_id AND comp_no=x.comp_no)      wname,      ");
        query.append("       (SELECT vendor_no FROM TAVENDOR WHERE vendor_id=x.vendor_id AND comp_no=x.comp_no)   vendorNo,   ");
        query.append("       (SELECT description FROM TAVENDOR WHERE vendor_id=x.vendor_id AND comp_no=x.comp_no) vendorName  ");
        query.append("FROM   TAPTPRRECLIST x, TAPARTS y                        ");
        query.append("WHERE x.comp_no = y.comp_no                              ");
        query.append("  AND x.part_id = y.part_id                              ");
        query.append(this.getWhere(maPtRecStatCommonDTO));
        query.getOrderByQuery("x.prreclist_id","x.rec_date desc", maPtRecStatCommonDTO.getOrderBy(), maPtRecStatCommonDTO.getDirection());
    	
        return getJdbcTemplate().queryForList(query.toString(maPtRecStatCommonDTO.getIsLoadMaxCount(), maPtRecStatCommonDTO.getFirstRow()));
    }
    
    /**
     * Filter 조건
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRecStatCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaPtRecStatCommonDTO maPtRecStatCommonDTO)
    {        
        QueryBuffer query = new QueryBuffer();
        String compNo = maPtRecStatCommonDTO.getCompNo();
        
        query.getAndQuery("x.prreclist_status", "C");
        query.getAndQuery("x.comp_no", compNo);
        query.getAndQuery("y.part_categ", "SPPT");
      
        // 부서
        query.getDeptLevelQuery("x.dept_id", maPtRecStatCommonDTO.getFilterDeptId(), maPtRecStatCommonDTO.getFilterDeptDesc(), maPtRecStatCommonDTO.getCompNo());
        
        //입고일자
        String startDate = maPtRecStatCommonDTO.getFilterRecStartDate();
        String endDate = maPtRecStatCommonDTO.getFilterRecEndDate();
        query.getAndDateQuery("x.rec_date", startDate, endDate);
        
        //공장코드
        query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = '"+compNo+"' AND a.plant = x.plant )", 
                maPtRecStatCommonDTO.getFilterPlantId(), maPtRecStatCommonDTO.getFilterPlantDesc());

        //부품
        query.getPartsQuery("x.part_id", maPtRecStatCommonDTO.getFilterPartId(), maPtRecStatCommonDTO.getFilterPartDesc(), compNo);
        
        //부품번호
        query.getLikeQuery("y.part_no", maPtRecStatCommonDTO.getFilterPartNo());
        
        return query.toString();
    }

    @Override
    public String findTotalCount(MaPtRecStatCommonDTO maPtRecStatCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       COUNT(1)                                           ");
        query.append("FROM   TAPTPRRECLIST x, TAPARTS y                        ");
        query.append("WHERE x.comp_no = y.comp_no                              ");
        query.append("  AND x.part_id = y.part_id                              ");
        query.append(this.getWhere(maPtRecStatCommonDTO));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
}