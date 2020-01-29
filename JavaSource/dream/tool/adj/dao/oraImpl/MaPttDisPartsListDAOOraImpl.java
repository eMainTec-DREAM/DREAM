package dream.tool.adj.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.tool.adj.dao.MaPttDisPartsListDAO;
import dream.tool.adj.dto.MaPttDisCommonDTO;
import dream.tool.adj.dto.MaPttDisPartsListDTO;
import dream.tool.rec.dto.MaPttRecCommonDTO;

/**
 * ¸ñ·Ï dao
 * @author  kim21017
 * @version $Id: MaPttDisPartsListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maPttDisPartsListDAOTarget"
 * @spring.txbn id="maPttDisPartsListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPttDisPartsListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaPttDisPartsListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaPttDisPartsListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPttDisCommonDTO
     * @param maPttDisPartsListDTO
     * @return List
     */
    public List findAnsList(MaPttDisCommonDTO maPttDisCommonDTO, MaPttDisPartsListDTO maPttDisPartsListDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();

        query.append(" SELECT												");
        query.append("        '' seqNo,     								");
        query.append("        '' isDelCheck,        						");
        query.append("        x.comp_no compNo,								");
        query.append("        x.ptdisuseitem_id ptdisuseitemId,				");
        query.append("        y.part_no partNo,								");
        query.append("        y.description||', '||y.pt_size  partnameSize, ");
        query.append("        x.disuse_qty disQty,							");
        query.append("        x.remark										");
        query.append(" FROM TAPTDISUSEITEM x, TAPARTS y						");
        query.append(" WHERE x.part_id  = y.part_id							");
        query.append("  AND  x.comp_no      = '"+maPttDisCommonDTO.getCompNo()+"'			");
        query.append(this.getWhere(maPttDisCommonDTO, maPttDisPartsListDTO, user)			);
        query.getOrderByQuery("x.ptdisuseitem_id DESC", maPttDisCommonDTO.getOrderBy(), maPttDisCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maPttDisCommonDTO.getIsLoadMaxCount(), maPttDisCommonDTO.getFirstRow()));
   }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaPttDisPartsListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRow
     * @return
     */
    public int deleteItemList(String deleteRow)
    {
    	QueryBuffer query = new QueryBuffer();

    	String ptdisuseitem_id=deleteRow;

    	query.append("DELETE FROM TAPTDISUSEITEM						");
    	query.append("WHERE ptdisuseitem_id 	= '"+ptdisuseitem_id+"'	");
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    private String getWhere(MaPttDisCommonDTO maPttDisCommonDTO, MaPttDisPartsListDTO maPttDisPartsListDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	query.getAndQuery("x.ptdisuselist_id", maPttDisCommonDTO.getPtdisuselistId());
    	if (!"".equals(maPttDisPartsListDTO.getPtdisuseitemId()))
        {
            query.getAndQuery("x.ptdisuseitem_id", maPttDisPartsListDTO.getPtdisuseitemId());
            return query.toString();
        }
    	
    	return query.toString();
    }

	@Override
	public String findTotalCount(MaPttDisCommonDTO maPttDisCommonDTO, MaPttDisPartsListDTO maPttDisPartsListDTO, User user) {
		QueryBuffer query = new QueryBuffer();
		
        query.append("SELECT          		            				 	");
        query.append("		 COUNT(*) 	     			                	");
        query.append(" FROM TAPTDISUSEITEM x, TAPARTS y						");
        query.append(" WHERE x.part_id  = y.part_id							");
        query.append("  AND  x.comp_no      = '"+user.getCompNo()+"'		");
        query.append(this.getWhere(maPttDisCommonDTO, maPttDisPartsListDTO, user));
        
        List resultList =  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
		
	}
}