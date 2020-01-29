package dream.part.list.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.part.list.dao.MaPtMstrIssStatListDAO;
import dream.part.list.dto.MaPtMstrCommonDTO;

/**
 * 부품마스터 출고이력 - 목록 dao
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maPtMstrIssStatListDAOTarget"
 * @spring.txbn id="maPtMstrIssStatListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPtMstrIssStatListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaPtMstrIssStatListDAO
{
    /**
     * grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtMstrIssStatListDTO
     * @return List
     */
    public List findPtMstrIssStatList(MaPtMstrCommonDTO maPtMstrCommonDTO,User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                                                                                                                                ");
        query.append("    ''                                                                                                                                            AS isDelCheck       ");
        query.append("    ,''                                                                                                                                           AS seqNo            ");
        query.append("    ,CASE WHEN a.iss_date IS NOT NULL THEN SUBSTRING(a.iss_date,1,4)+'-'+SUBSTRING(a.iss_date,5,2)+'-'+SUBSTRING(a.iss_date,7,2) ELSE a.iss_date END   AS issDate          ");
        query.append("    ,(SELECT wname FROM TAWAREHOUSE WHERE comp_no=a.comp_no AND wcode_id=a.wcode_id)                                                              AS issWname         ");
        query.append("    ,dbo.SFACODE_TO_DESC(a.ptiss_mode, 'PTISS_MODE', 'SYS', '','"+user.getLangId()+"')                                                            AS ptIssModeDesc    ");
        query.append("    ,dbo.SFACODE_TO_DESC(a.ptiss_type, 'PTISS_TYPE', 'SYS', '','"+user.getLangId()+"')                                                            AS ptIssTypeDesc    ");
        query.append("    ,dbo.SFACODE_TO_DESC(a.part_grade, 'PART_GRADE', 'SYS', '','"+user.getLangId()+"')                                                            AS partGradeDesc    ");
        query.append("    ,a.iss_qty                                                                                                                                    AS issQty           ");
        query.append("FROM TAPTISSHIST a                                                                                                                                                    ");
        query.append("WHERE 1 = 1                                                                                                                                                           ");
        query.append(this.getWhere(maPtMstrCommonDTO, user));
        query.getOrderByQuery("a.ptisshist_id","a.iss_date desc", maPtMstrCommonDTO.getOrderBy(), maPtMstrCommonDTO.getDirection());
        
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
        query.append("FROM TAPTISSHIST a        ");
        query.append("WHERE 1 = 1               ");
        query.append(this.getWhere(maPtMstrCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
}