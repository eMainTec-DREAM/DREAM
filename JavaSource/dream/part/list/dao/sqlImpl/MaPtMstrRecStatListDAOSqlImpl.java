package dream.part.list.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.part.list.dao.MaPtMstrRecStatListDAO;
import dream.part.list.dto.MaPtMstrCommonDTO;

/**
 * ��ǰ������ �԰��̷� - ��� dao
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maPtMstrRecStatListDAOTarget"
 * @spring.txbn id="maPtMstrRecStatListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPtMstrRecStatListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaPtMstrRecStatListDAO
{
    /**
     * grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtMstrRecStatListDTO
     * @return List
     */
    public List findPtMstrRecStatList(MaPtMstrCommonDTO maPtMstrCommonDTO,User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                                                                                                                                ");
        query.append("    ''                                                                                                                                            AS isDelCheck       ");
        query.append("    ,''                                                                                                                                           AS seqNo            ");
        query.append("    ,CASE WHEN a.rec_date IS NOT NULL THEN SUBSTRING(a.rec_date,1,4)+'-'+SUBSTRING(a.rec_date,5,2)+'-'+SUBSTRING(a.rec_date,7,2) ELSE a.rec_date END   AS recDate          ");
        query.append("    ,(SELECT wname FROM TAWAREHOUSE WHERE comp_no=a.comp_no AND wcode_id=a.wcode_id)                                                              AS recWname         ");
        query.append("    ,dbo.SFACODE_TO_DESC(a.ptrec_mode, 'PTREC_MODE', 'SYS', '','"+user.getLangId()+"')                                                            AS ptRecModeDesc    ");
        query.append("    ,dbo.SFACODE_TO_DESC(a.ptrec_type, 'PTREC_TYPE', 'SYS', '','"+user.getLangId()+"')                                                            AS ptRecTypeDesc    ");
        query.append("    ,(SELECT description FROM TAVENDOR WHERE comp_no=a.comp_no AND vendor_id=a.vendor_id)                                                         AS vendorDesc       ");
        query.append("    ,dbo.SFACODE_TO_DESC(a.part_grade, 'PART_GRADE', 'SYS', '','"+user.getLangId()+"')                                                            AS partGradeDesc    ");
        query.append("    ,a.rec_qty                                                                                                                                    AS recQty           ");
        query.append("    ,a.unit_price                                                                                                                                 AS unitPrice        ");
        query.append("    ,a.tot_price                                                                                                                                  AS totPrice         ");
        query.append("FROM TAPTRECHIST a                                                                                                                                                    ");
        query.append("WHERE 1 = 1                                                                                                                                                           ");
        query.append(this.getWhere(maPtMstrCommonDTO, user));
        query.getOrderByQuery("a.ptrechist_id desc","a.rec_date desc", maPtMstrCommonDTO.getOrderBy(), maPtMstrCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maPtMstrCommonDTO.getIsLoadMaxCount(), maPtMstrCommonDTO.getFirstRow()));
    }
    
    private String getWhere(MaPtMstrCommonDTO maPtMstrCommonDTO, User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.getStringEqualQuery("a.comp_no", user.getCompNo());
        query.getStringEqualQuery("a.part_id", maPtMstrCommonDTO.getPartId());
        
        return query.toString();
    }
    
    @Override
    public String findTotalCount(MaPtMstrCommonDTO maPtMstrCommonDTO, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                    ");
        query.append("       COUNT(1)           ");
        query.append("FROM TAPTRECHIST a        ");
        query.append("WHERE 1 = 1               ");
        query.append(this.getWhere(maPtMstrCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
}