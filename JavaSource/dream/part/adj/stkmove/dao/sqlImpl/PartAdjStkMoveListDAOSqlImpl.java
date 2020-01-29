package dream.part.adj.stkmove.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.part.adj.stkmove.dao.PartAdjStkMoveListDAO;
import dream.part.adj.stkmove.dto.PartAdjStkMoveCommonDTO;

/**
 * 재고이동 dao
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="partAdjStkMoveListDAOTarget"
 * @spring.txbn id="partAdjStkMoveListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class PartAdjStkMoveListDAOSqlImpl extends BaseJdbcDaoSupportSql implements PartAdjStkMoveListDAO
{
    /**
     * grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param partAdjStkMoveCommonDTO
     * @return List
     */
    public List findItemList(PartAdjStkMoveCommonDTO partAdjStkMoveCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT                                                                                                                            ");
        query.append("    ''                                                                                            AS isDelCheck                   ");
        query.append("    ,''                                                                                           AS seqNo                        ");
        query.append("    ,a.ptstkmove_no                                                                               AS ptStkMoveNo                  ");
        query.append("    ,CASE WHEN a.move_date IS NOT NULL THEN SUBSTRING(a.move_date,1,4)+'-'+SUBSTRING(a.move_date,5,2)+'-'+SUBSTRING(a.move_date,7,2)");
        query.append("             ELSE a.move_date END                                                                 AS moveDate                     ");
        query.append("    ,a.ptstkmove_status                                                                           AS ptStkMoveStatus              ");
        query.append("    ,dbo.SFACODE_TO_DESC(a.ptstkmove_status,'PTSTKMOVE_STATUS','SYS','','"+user.getLangId()+"')   AS ptStkMoveStatusDesc          ");
        query.append("    ,(SELECT wname FROM TAWAREHOUSE WHERE comp_no = a.comp_no AND wcode_id = a.from_wcode_id)     AS fromWcodeDesc                ");
        query.append("    ,(SELECT wname FROM TAWAREHOUSE WHERE comp_no = a.comp_no AND wcode_id = a.to_wcode_id)       AS toWcodeDesc                  ");
        query.append("    ,b.part_no                                                                                    AS partNo                       ");
        query.append("    ,b.description+', '+ISNULL(b.pt_size,'')                                                      AS partNameSize                 ");
        query.append("    ,b.description                                                                                AS ptDesc                       ");
        query.append("    ,b.pt_size                                                                                    AS ptSize                       ");
        query.append("    ,b.model                                                                                      AS model                        ");
        query.append("    ,a.move_qty                                                                                   AS moveQty                      ");
        query.append("    ,(SELECT emp_name FROM TAEMP WHERE comp_no = a.comp_no AND emp_id = a.reg_id)                                                 ");
        query.append("     +'/'+(SELECT description FROM TADEPT WHERE comp_no = a.comp_no AND dept_id = a.reg_dept)     AS regEmpDept                   ");
        query.append("    ,a.remark                                                                                     AS remark                       ");
        query.append("    ,a.ptstkmove_id                                                                               AS ptStkMoveId                  ");
        query.append("FROM TAPTSTKMOVE a LEFT OUTER JOIN TAPARTS b                                                                                      ");
        query.append("ON a.comp_no = b.comp_no                                                                                                          ");
        query.append("AND a.part_id = b.part_id                                                                                                         ");
        query.append("WHERE 1=1                                                                                                                         ");
        query.append(this.getWhere(partAdjStkMoveCommonDTO, user));
        query.getOrderByQuery("a.ptstkmove_id","a.ptstkmove_no", partAdjStkMoveCommonDTO.getOrderBy(), partAdjStkMoveCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(partAdjStkMoveCommonDTO.getIsLoadMaxCount(), partAdjStkMoveCommonDTO.getFirstRow()));
    }
    
    private String getWhere(PartAdjStkMoveCommonDTO partAdjStkMoveCommonDTO, User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        String compNo = user.getCompNo();
        
        query.getAndQuery("a.comp_no", compNo);
        if (!"".equals(partAdjStkMoveCommonDTO.getPtStkMoveId()))
        {
            query.getAndQuery("a.ptstkmove_id", partAdjStkMoveCommonDTO.getPtStkMoveId());
            return query.toString();
        }
        
        //처리일자
        query.getAndDateQuery("a.move_date", partAdjStkMoveCommonDTO.getFilterStartMoveDate(), partAdjStkMoveCommonDTO.getFilterEndMoveDate());
        
        //상태
        query.getSysCdQuery("a.ptstkmove_status", partAdjStkMoveCommonDTO.getFilterPtStkMoveStatus(), partAdjStkMoveCommonDTO.getFilterPtStkMoveStatusDesc(), 
                "TASTKMOVE_STATUS", compNo, user.getLangId());
        
        //From창고
        query.getCodeLikeQuery("a.from_wcode_id", "(SELECT wname FROM TAWAREHOUSE WHERE comp_no = a.comp_no AND wcode_id = a.from_wcode_id)", 
                partAdjStkMoveCommonDTO.getFilterFromWcodeId(), partAdjStkMoveCommonDTO.getFilterFromWname());
        
        //To창고
        query.getCodeLikeQuery("a.to_wcode_id", "(SELECT wname FROM TAWAREHOUSE WHERE comp_no = a.comp_no AND wcode_id = a.to_wcode_id)", 
                partAdjStkMoveCommonDTO.getFilterToWcodeId(), partAdjStkMoveCommonDTO.getFilterToWname());
        
        //부품
        query.getCodeLikeQuery("a.part_id", "(SELECT full_desc FROM TAPARTS WHERE comp_no = a.comp_no AND part_id = a.part_id)", 
                partAdjStkMoveCommonDTO.getFilterPartId(), partAdjStkMoveCommonDTO.getFilterPartDesc());
        
        //담당자
        query.getCodeLikeQuery("a.reg_id", "(SELECT emp_name FROM TAEMP WHERE comp_no = a.comp_no AND emp_id = a.reg_id)", 
                partAdjStkMoveCommonDTO.getFilterRegId(), partAdjStkMoveCommonDTO.getFilterRegDesc());
        
        return query.toString();
    }
    
    /**
     * delete
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deleteItem(String id, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("DELETE FROM TAPTSTKMOVE               ");
        query.append("WHERE ptstkmove_id = '"+id+"'     ");
        query.getAndQuery("comp_no", user.getCompNo());
        query.getAndQuery("ptstkmove_status", "W");
        
        return this.getJdbcTemplate().update(query.toString());
    }

    @Override
    public String findTotalCount(PartAdjStkMoveCommonDTO partAdjStkMoveCommonDTO, User user) throws Exception {

        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT                                        ");
        query.append("       COUNT(1)                               ");
        query.append("FROM TAPTSTKMOVE a LEFT OUTER JOIN TAPARTS b  ");
        query.append("ON a.comp_no = b.comp_no                      ");
        query.append("AND a.part_id = b.part_id                     ");
        query.append("WHERE  1=1                                    ");
        query.append(this.getWhere(partAdjStkMoveCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);

    }

}