package dream.part.list.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.part.list.dao.MaPtMstrEqPartListDAO;
import dream.part.list.dto.MaPtMstrCommonDTO;

/**
 * 사용설비 목록 dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maPtMstrEqPartListDAOTarget"
 * @spring.txbn id="maPtMstrEqPartListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPtMstrEqPartListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaPtMstrEqPartListDAO
{
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtMstrCommonDTO
     * @param loginUser
     * @return List
     */
    public List findList(MaPtMstrCommonDTO maPtMstrCommonDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT											        ");
        query.append("       ''                                      seqNo,		");
        query.append("       ''                                      isDelCheck,");
        query.append("       x.comp_no                               compNo,	");
        query.append("       x.eqpart_id                             eqPartId,  ");
        query.append("       y.description                           equipDesc, ");
        query.append("       y.item_no                               equipNo,   ");
        query.append("      (SELECT full_desc FROM TAEQASMB                     ");
        query.append("       WHERE comp_no=x.comp_no                            ");
        query.append("         AND eqasmb_id=x.eqasmb_id)            eqAsmbDesc,");
        query.append("      (SELECT full_desc FROM TAEQLOC                      ");
        query.append("       WHERE  comp_no  = y.comp_no                        ");
        query.append("         AND  eqloc_id = y.eqloc_id)           eqLocDesc, ");
        query.append("      (SELECT full_desc FROM TAEQCTG                      ");
        query.append("       WHERE  comp_no  = y.comp_no                        ");
        query.append("         AND  eqctg_id = y.eqctg_id)           eqCtgDesc  ");
        query.append("FROM   TAEQPART x, TAEQUIPMENT y                          ");
        query.append("WHERE  x.comp_no  = y.comp_no	                            ");
        query.append("  AND  x.equip_id = y.equip_id	                        ");
        query.append(this.getWhere(maPtMstrCommonDTO,loginUser));
//        query.append("ORDER BY x.eqpart_id	                                    ");
//        return getJdbcTemplate().queryForList(query.toString());
        query.getOrderByQuery("x.eqpart_id", maPtMstrCommonDTO.getOrderBy(), maPtMstrCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maPtMstrCommonDTO.getIsLoadMaxCount(), maPtMstrCommonDTO.getFirstRow()));

    }
    
    /**
     * delete
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param eqPartId
     * @param loginUser
     * @return
     */
    public int deleteList(String eqPartId, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("DELETE FROM TAEQPART                                   ");
    	query.append("WHERE  comp_no   = '"+loginUser.getCompNo()+"'         ");
    	query.append("  AND  eqpart_id = '"+eqPartId+"'			             ");
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    private String getWhere(MaPtMstrCommonDTO maPtMstrCommonDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	query.getAndQuery("x.part_id", maPtMstrCommonDTO.getPartId());
    	query.getAndQuery("x.comp_no", loginUser.getCompNo());
    	if (!"".equals(maPtMstrCommonDTO.getEqPartId()))
        {
            query.getAndQuery("x.eqpart_id", maPtMstrCommonDTO.getEqPartId());
            return query.toString();
        }
    	
    	return query.toString();
    }

	@Override
	public String findTotalCount(MaPtMstrCommonDTO maPtMstrCommonDTO, User loginUser) throws Exception {

		QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT											        ");
        query.append("       COUNT(1)                                           ");
        query.append("FROM   TAEQPART x, TAEQUIPMENT y                          ");
        query.append("WHERE  x.comp_no  = y.comp_no	                            ");
        query.append("  AND  x.equip_id = y.equip_id	                        ");
        query.append(this.getWhere(maPtMstrCommonDTO,loginUser));
	
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);

	}
}