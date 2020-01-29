package dream.asset.list.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.asset.list.dao.MaEqMstrMoldModHistListDAO;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrMoldModHistListDTO;

/**
 * 구성자재 목록 dao
 * @author  kim21017
 * @version $Id: MaEqMstrMoldModHistListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maEqMstrMoldModHistListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaEqMstrMoldModHistListDAOOraImpl  extends BaseJdbcDaoSupportOra implements MaEqMstrMoldModHistListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaEqMstrMoldModHistListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrCommonDTO
     * @param maEqMstrMoldModHistListDTO
     * @param loginUser
     * @return List
     */
    public List findMoldModHistList(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrMoldModHistListDTO maEqMstrMoldModHistListDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("select                                                ");
        query.append("    '' seqNo										    ");
        query.append("    ,'' isDelCheck									");
        query.append("    ,a.eqmoldmodhist_id           as eqMoldModHistId  ");
        query.append("    ,a.equip_id                   as equipId          ");
        query.append("    ,a.mod_cnt                    as modCnt           ");
        query.append("    ,a.cavity                   	as capacity         ");
        query.append("    ,a.output                     as outPut           ");
        query.append("    ,a.ownership                  as ownerShip        ");
        query.append("    ,a.ord_no                     as ordNo            ");
        query.append("    ,a.remark                     as remark           ");
        query.append("from TAEQMOLDMODHIST a ");
        query.append("where 1=1 ");
        query.append(this.getWhere(maEqMstrCommonDTO,maEqMstrMoldModHistListDTO,loginUser));
        query.getOrderByQuery("a.ord_no", maEqMstrMoldModHistListDTO.getOrderBy(), maEqMstrMoldModHistListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maEqMstrMoldModHistListDTO.getIsLoadMaxCount(), maEqMstrMoldModHistListDTO.getFirstRow()));
        
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaEqMstrMoldModHistListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteMoldModHistList(String id, String compNo)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("DELETE FROM TAEQMOLDMODHIST						");
    	query.append("WHERE  eqmoldmodhist_id 		= '"+id+"'			");
    	query.append("  AND  comp_no		        = '"+compNo+"'		");
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    private String getWhere(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrMoldModHistListDTO maEqMstrMoldModHistListDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	query.getAndQuery("a.equip_id", maEqMstrCommonDTO.getEquipId());
    	query.getAndQuery("a.comp_no", loginUser.getCompNo());
    	if (!"".equals(maEqMstrMoldModHistListDTO.getEqMoldModHistId()))
        {
            query.getAndQuery("a.eqmoldmodhist_id", maEqMstrMoldModHistListDTO.getEqMoldModHistId());
            return query.toString();
        }
    	
    	return query.toString();
    }
    
    public String findTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrMoldModHistListDTO maEqMstrMoldModHistListDTO, User loginUser) {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                 ");
        query.append("       COUNT(1)        ");
        query.append("from TAEQMOLDMODHIST a ");
        query.append("where 1=1 			 ");
        query.append(this.getWhere(maEqMstrCommonDTO,maEqMstrMoldModHistListDTO,loginUser));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }    
}