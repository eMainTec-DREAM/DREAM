package dream.rcm.ffail.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.rcm.ffail.dao.RcmFfailItemListDAO;
import dream.rcm.ffail.dto.RcmFfailCommonDTO;
import dream.rcm.ffail.dto.RcmFfailItemListDTO;

/**
 * 답변 목록 dao
 * @author  kim21017
 * @version $Id: RcmFfailItemListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="rcmFfailItemListDAOTarget"
 * @spring.txbn id="rcmFfailItemListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class RcmFfailItemListDAOSqlImpl extends BaseJdbcDaoSupportSql implements RcmFfailItemListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: RcmFfailItemListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFfailCommonDTO
     * @param rcmFfailItemListDTO
     * @return List
     */
    public List findItemList(RcmFfailCommonDTO rcmFfailCommonDTO, RcmFfailItemListDTO rcmFfailItemListDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        String compNo = rcmFfailCommonDTO.getCompNo();
        
        query.append("SELECT 									");
        query.append("       '' seqNo,                          ");
        query.append("       '' isDelCheck,               		");
        query.append("       x.rcmffail_no 	rcmFfailNo,			");
        query.append("       x.description 	description,		");
        query.append("       x.remark 		remark,				");
        query.append("       x.rcmffail_id 	rcmFfailId          ");
        query.append("FROM TARCMFFAIL x							");
        query.append("WHERE x.comp_no = '"+compNo+"'			");
        query.append(this.getWhere(rcmFfailCommonDTO,rcmFfailItemListDTO));
        query.getOrderByQuery("x.rcmlist_id","x.rcmffail_no", rcmFfailCommonDTO.getOrderBy(), rcmFfailCommonDTO.getDirection());
        return getJdbcTemplate().queryForList(query.toString(rcmFfailCommonDTO.getIsLoadMaxCount(), rcmFfailCommonDTO.getFirstRow()));
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: RcmFfailItemListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRow
     * @param deleteRowsExt 
     * @return
     */
    public int deleteItemList(String deleteRow)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	String rcmffail_id=deleteRow;
    	
    	query.append("DELETE FROM TARCMFFAIL						");
    	query.append("WHERE rcmffail_id 	= '"+rcmffail_id+"'	");
    	
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    private String getWhere(RcmFfailCommonDTO rcmFfailCommonDTO, RcmFfailItemListDTO rcmFfailItemListDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	query.getAndQuery("x.rcmfunc_id", rcmFfailCommonDTO.getRcmFuncId());
    	if (!"".equals(rcmFfailItemListDTO.getRcmFfailId()))
        {
            query.getAndQuery("x.rcmffail_id", rcmFfailItemListDTO.getRcmFfailId());
            return query.toString();
        }
    	
    	return query.toString();
    }

	@Override
	public String findTotalCount(RcmFfailCommonDTO rcmFfailCommonDTO, RcmFfailItemListDTO rcmFfailItemListDTO,
			User user) {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("SELECT					");
    	query.append("		count(1)			");
        query.append("FROM TARCMFFAIL x							");
        query.append("WHERE x.comp_no = '"+user.getCompNo()+"'			");
        query.append(this.getWhere(rcmFfailCommonDTO,rcmFfailItemListDTO));
        
    	List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    
	}
}