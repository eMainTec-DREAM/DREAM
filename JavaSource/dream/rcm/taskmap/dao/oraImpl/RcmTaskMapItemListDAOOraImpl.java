package dream.rcm.taskmap.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.rcm.taskmap.dao.RcmTaskMapItemListDAO;
import dream.rcm.taskmap.dto.RcmTaskMapItemListDTO;
import dream.rcm.taskmap.dto.RcmTaskMapCommonDTO;

/**
 * 답변 목록 dao
 * @author  kim21017
 * @version $Id: RcmTaskMapItemListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="rcmTaskMapItemListDAOTarget"
 * @spring.txbn id="rcmTaskMapItemListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class RcmTaskMapItemListDAOOraImpl extends BaseJdbcDaoSupportOra implements RcmTaskMapItemListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: RcmTaskMapItemListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmTaskMapCommonDTO
     * @param rcmTaskMapItemListDTO
     * @return List
     */
    public List findItemList(RcmTaskMapCommonDTO rcmTaskMapCommonDTO, RcmTaskMapItemListDTO rcmTaskMapItemListDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        String compNo = rcmTaskMapCommonDTO.getCompNo();
        
        query.append("SELECT 									");
        query.append("       '' seqNo                           ");
        query.append("       ,'' isDelCheck     				");
        query.append("       ,x.pmtaskmap_no pmTaskmapNo		");
        query.append("       ,x.description description			");
        query.append("       ,SFACODE_TO_DESC(x.taskmap_rslt_ytype,'TASKMAP_RSLT_TYPE','SYS','','"+user.getLangId()+"') yType		");
        query.append("       ,SFACODE_TO_DESC(x.taskmap_rslt_ntype,'TASKMAP_RSLT_TYPE','SYS','','"+user.getLangId()+"') nType		");
        query.append("       ,(SELECT z.pmtaskmap_no FROM TAPMTASKMAP z WHERE x.pmtaskmap_yid = z.pmtaskmap_id AND x.comp_no=z.comp_no ) yId		");
        query.append("       ,(SELECT z.pmtaskmap_no FROM TAPMTASKMAP z WHERE x.pmtaskmap_nid = z.pmtaskmap_id AND x.comp_no=z.comp_no ) nId      ");
        query.append("       ,x.remark remark		");
        query.append("       ,x.pmtaskmap_id pmTaskmapId       		");
        query.append("FROM TAPMTASKMAP x							");
        query.append("WHERE 1=1										");
        query.append("AND x.comp_no = '"+compNo+"'					");
        query.append(this.getWhere(rcmTaskMapCommonDTO,rcmTaskMapItemListDTO));
        query.getOrderByQuery("x.pmtaskmap_id","x.pmtaskmap_no", rcmTaskMapCommonDTO.getOrderBy(), rcmTaskMapCommonDTO.getDirection());
        return getJdbcTemplate().queryForList(query.toString(rcmTaskMapCommonDTO.getIsLoadMaxCount(), rcmTaskMapCommonDTO.getFirstRow()));
}
    
    /**
     * delete
     * @author kim21017
     * @version $Id: RcmTaskMapItemListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRow
     * @param deleteRowsExt 
     * @return
     */
    public int deleteItemList(String deleteRow)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	String rcmffeq_id=deleteRow;
    	
    	query.append("DELETE FROM TAPMTASKMAP						");
    	query.append("WHERE pmtaskmap_id 	= '"+rcmffeq_id+"'	");
    	
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    private String getWhere(RcmTaskMapCommonDTO rcmTaskMapCommonDTO, RcmTaskMapItemListDTO rcmTaskMapItemListDTO)
    {
    	QueryBuffer query = new QueryBuffer();
    	query.getAndQuery("x.pmtaskmaplist_id", rcmTaskMapCommonDTO.getPmTaskMapListId());
    	if (!"".equals(rcmTaskMapItemListDTO.getPmTaskmapId()))
        {
            query.getAndQuery("x.pmtaskmap_id", rcmTaskMapItemListDTO.getPmTaskmapId());
            return query.toString();
        }
    	
    	return query.toString();
    }

	@Override
	public String findTotalCount(RcmTaskMapCommonDTO rcmTaskMapCommonDTO, RcmTaskMapItemListDTO rcmTaskMapItemListDTO,
			User user) {
		QueryBuffer query = new QueryBuffer();
        String compNo = rcmTaskMapCommonDTO.getCompNo();
        
		query.append("SELECT 								");
		query.append("   COUNT(1)	                        ");
		query.append("FROM TAPMTASKMAP x					");
		query.append("WHERE 1=1								");
		query.append("AND x.comp_no = '"+compNo+"'						");
		query.append(this.getWhere(rcmTaskMapCommonDTO, rcmTaskMapItemListDTO));
		
        List resultList = getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
	}
}