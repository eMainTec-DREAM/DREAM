package dream.asset.list.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.asset.list.dao.MaEqMstrMoldProdListDAO;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrMoldProdListDTO;

/**
 * 구성자재 목록 dao
 * @author  kim21017
 * @version $Id: MaEqMstrPartListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maEqMstrMoldProdListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaEqMstrMoldProdListDAOOraImpl  extends BaseJdbcDaoSupportOra implements MaEqMstrMoldProdListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaEqMstrPartListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrCommonDTO
     * @param maEqMstrMoldProdListDTO
     * @param loginUser
     * @return List
     */
    public List findMoldProdList(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrMoldProdListDTO maEqMstrMoldProdListDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("select                                                  ");
        query.append("         '' seqNo			 							  ");
        query.append("         ,'' isDelCheck 								  ");
        query.append("         ,a.equip_id              as equipId            ");
        query.append("         ,a.eqproduct_id      as eqMoldProductId    ");
        query.append("         ,a.product_no            as productNo          ");
        query.append("         ,a.product_desc          as productDesc        ");
        query.append("         ,a.ord_no                as ordNo              ");
        query.append("         ,a.remark                as remark             ");
        query.append("from TAEQPRODUCT a                                  ");
        query.append("where 1=1                                               ");
        query.append(this.getWhere(maEqMstrCommonDTO,maEqMstrMoldProdListDTO,loginUser));
        query.getOrderByQuery("a.ord_no", maEqMstrMoldProdListDTO.getOrderBy(), maEqMstrMoldProdListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maEqMstrMoldProdListDTO.getIsLoadMaxCount(), maEqMstrMoldProdListDTO.getFirstRow()));

    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaEqMstrPartListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteMoldProdList(String id, String compNo)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("DELETE FROM TAEQPRODUCT		        		");
    	query.append("WHERE  eqproduct_id 		= '"+id+"'			");
    	query.append("  AND  comp_no		        = '"+compNo+"'		");
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    private String getWhere(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrMoldProdListDTO maEqMstrMoldProdListDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	query.getAndQuery("a.equip_id", maEqMstrCommonDTO.getEquipId());
    	query.getAndQuery("a.comp_no", loginUser.getCompNo());
    	if (!"".equals(maEqMstrMoldProdListDTO.getEqMoldProductId()))
        {
            query.getAndQuery("a.eqproduct_id", maEqMstrMoldProdListDTO.getEqMoldProductId());
            return query.toString();
        }
    	
    	return query.toString();
    }
    
    public String findTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrMoldProdListDTO maEqMstrMoldProdListDTO, User loginUser) {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                    ");
        query.append("       COUNT(1)           ");
        query.append("from TAEQPRODUCT a		");
        query.append("where 1=1                 ");
        query.append(this.getWhere(maEqMstrCommonDTO,maEqMstrMoldProdListDTO,loginUser));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }    
}