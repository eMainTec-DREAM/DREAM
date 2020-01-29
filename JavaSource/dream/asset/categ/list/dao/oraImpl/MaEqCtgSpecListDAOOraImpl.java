package dream.asset.categ.list.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.asset.categ.list.dao.MaEqCtgSpecListDAO;
import dream.asset.categ.list.dto.MaEqCatalogCommonDTO;
import dream.asset.categ.list.dto.MaEqCtgSpecListDTO;

/**
 * 설비종류별 표준제원 목록 dao
 * @author  syyang
 * @version $Id: MaEqCtgSpecListDAO.java,v 1.0 2015/12/02 09:14:12 syyang Exp $
 * @since   1.0
 * @spring.bean id="maEqCtgSpecListDAOTarget"
 * @spring.txbn id="maEqCtgSpecListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaEqCtgSpecListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaEqCtgSpecListDAO
{
    /**
     * grid find
     * @author  syyang
     * @version $Id: MaEqCtgSpecListDAO.java,v 1.0 2015/12/02 09:14:12 syyang Exp $
     * @since   1.0
     * 
     * @param maEqCatalogCommonDTO
     * @param maEqCtgSpecListDTO
     * @param loginUser
     * @return List
     */
    public List findSpecList(MaEqCatalogCommonDTO maEqCatalogCommonDTO, MaEqCtgSpecListDTO maEqCtgSpecListDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT															");
        query.append("		''			 								seqNo			");
        query.append("		,'' 										isDelCheck		");
        query.append("		,x.eqctgspec_id 							eqCtgSpecId		");
        query.append("		,x.eqctg_id 								eqCtgId			");
        query.append("		,x.eq_ctg_asmb_id 							eqCtgAsmbId		");
        query.append("		,(SELECT description										");
        query.append("		  FROM TAEQCTGASMB											");
        query.append("		  WHERE comp_no = x.comp_no									");
        query.append("		    AND eq_ctg_asmb_id=x.eq_ctg_asmb_id)	eqCtgAsmbDesc	");
        query.append("      ,x.categ 									categ      		");
        query.append("      ,x.prompt 									prompt     		");
        query.append("      ,x.uom	 									uom     		");
        query.append("		,x.ord_no 									ordNo			");
        query.append("		,x.is_use 									isUse			");
        query.append("		,x.is_eqtype_spec 							isEqTypeSpec	");
        query.append("FROM   TAEQCTGSPEC x												");
        query.append("WHERE  1=1														");
        query.append(this.getWhere(maEqCatalogCommonDTO,maEqCtgSpecListDTO,loginUser));
        query.getOrderByQuery("x.ord_no", maEqCatalogCommonDTO.getOrderBy(), maEqCatalogCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maEqCatalogCommonDTO.getIsLoadMaxCount(), maEqCatalogCommonDTO.getFirstRow()));

    }
    private String getWhere(MaEqCatalogCommonDTO maEqCatalogCommonDTO, MaEqCtgSpecListDTO maEqCtgSpecListDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.getAndQuery("x.eqctg_id", maEqCatalogCommonDTO.getEqCtgId());
    	query.getAndQuery("x.comp_no", loginUser.getCompNo());
    	
    	return query.toString();
    }
    
    /**
     * delete
     * @author syyang
     * @version $Id: MaEqCtgSpecListDAO.java,v 1.0 20155/12/02 08:25:47 syyang Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteSpecList(String id, String compNo)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("DELETE FROM TAEQCTGSPEC				");
    	query.append("WHERE  eqctgspec_id	 	= ?		    ");
    	query.append("  AND  comp_no		 	= ?			");
    	
    	Object[] objects = new Object[] {
    			id
    			,compNo
    	};
    	
    	return this.getJdbcTemplate().update(query.toString(), objects);
    	
    }
    
    public String findTotalCount(
            MaEqCatalogCommonDTO maEqCatalogCommonDTO, MaEqCtgSpecListDTO maEqCtgSpecListDTO, User user) {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                    ");
        query.append("       COUNT(1)           ");
        query.append("FROM   TAEQCTGSPEC x		");
        query.append("WHERE  1=1				");
        query.append(this.getWhere(maEqCatalogCommonDTO,maEqCtgSpecListDTO,user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QueryBuffer.listToString(resultList);
    }   
}