package dream.asset.list.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.asset.list.dao.MaEqMstrSpecListDAO;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrSpecListDTO;

/**
 * 설비제원(스펙) 목록 dao
 * @author  kim21017
 * @version $Id: MaEqMstrSpecListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maEqMstrSpecListDAOTarget"
 * @spring.txbn id="maEqMstrSpecListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaEqMstrSpecListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaEqMstrSpecListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaEqMstrSpecListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrCommonDTO
     * @param maEqMstrSpecListDTO
     * @param loginUser
     * @return List
     */
    public List findSpecList(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrSpecListDTO maEqMstrSpecListDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT											");
        query.append("       ''						seqNo,				");
        query.append("       '' isDelCheck,								");
        query.append("       x.equip_id 			equipId,			");
        query.append("       x.eqspec_id 			eqSpecId,			");
        query.append("       x.categ 				categ,				");
        query.append("       x.prompt 				prompt,				");
        query.append("       x.response 			response,			");
        query.append("       x.uom 					uom					");
        query.append("     , x.eqasmb_id        	eqAsmbId			");
        query.append("     ,(SELECT a.full_desc 						");
        query.append("       FROM TAEQASMB a 							");
        query.append("       WHERE a.comp_no = x.comp_no 				");
        query.append("         AND a.eqasmb_id = x.eqasmb_id) eqAsmbDesc");
        query.append("     , x.ord_no        	    ordNo			    ");
        query.append("     , x.eqctgspec_id        	eqCtgSpecId			");
        query.append("     , x.is_eqtype_spec       isEqTypeSpec		");
        query.append("	   , x.remark				remark				");
        query.append("FROM   TAEQSPEC x									");
        query.append("WHERE 1=1											");
        query.append(this.getWhere(maEqMstrCommonDTO,maEqMstrSpecListDTO,loginUser));
        query.getOrderByQuery("x.ord_no", maEqMstrSpecListDTO.getOrderBy(), maEqMstrSpecListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maEqMstrSpecListDTO.getIsLoadMaxCount(), maEqMstrSpecListDTO.getFirstRow()));

    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaEqMstrSpecListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteSpecList(String id, String compNo)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("DELETE FROM TAEQSPEC						");
    	query.append("WHERE  eqspec_id 		= '"+id+"'			");
    	query.append("  AND  comp_no		= '"+compNo+"'		");
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    private String getWhere(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrSpecListDTO maEqMstrSpecListDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	query.getAndQuery("x.equip_id", maEqMstrCommonDTO.getEquipId());
        query.getAndQuery("x.comp_no", loginUser.getCompNo());
    	if (!"".equals(maEqMstrSpecListDTO.getEqSpecId()))
        {
            query.getAndQuery("x.eqspec_id", maEqMstrSpecListDTO.getEqSpecId());
            return query.toString();
        }
    	query.getAndQuery("x.eqctgspec_id", maEqMstrSpecListDTO.getEqCtgSpecId());
    	
    	return query.toString();
    }
    
    public String findTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrSpecListDTO maEqMstrSpecListDTO, User loginUser) {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                ");
        query.append("       COUNT(1)       ");
        query.append("FROM   TAEQSPEC x		");
        query.append("WHERE 1=1				");
        query.append(this.getWhere(maEqMstrCommonDTO,maEqMstrSpecListDTO,loginUser));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
}