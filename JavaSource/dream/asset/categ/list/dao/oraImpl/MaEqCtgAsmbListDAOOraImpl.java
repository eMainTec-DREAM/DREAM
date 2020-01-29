package dream.asset.categ.list.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.asset.categ.list.dao.MaEqCtgAsmbListDAO;
import dream.asset.categ.list.dto.MaEqCatalogCommonDTO;
import dream.asset.categ.list.dto.MaEqCtgAsmbListDTO;

/**
 * 설비종류별 작업부위 목록 dao
 * @author  kim21017
 * @version $Id: MaEqCtgAsmbListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maEqCtgAsmbListDAOTarget"
 * @spring.txbn id="maEqCtgAsmbListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaEqCtgAsmbListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaEqCtgAsmbListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaEqCtgAsmbListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqCatalogCommonDTO
     * @param maEqCtgAsmbListDTO
     * @param loginUser
     * @return List
     */
    public List findAsmbList(MaEqCatalogCommonDTO maEqCatalogCommonDTO, MaEqCtgAsmbListDTO maEqCtgAsmbListDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT											");
        query.append("       '' 					seqNo,				");
        query.append("       '' isDelCheck,								");
        query.append("       x.eqctg_id 			eqCtgId,			");
        query.append("       x.eq_ctg_asmb_id 		eqCtgAsmbId,		");
        query.append("       x.description 			eqCtgAsmbDesc,		");
        query.append("       x.remark 				remark,				");
        query.append("       x.ord_no 				ordNo,				");
        query.append("       x.is_use 				isUse				");
        query.append("     , x.eq_ctg_asmb_id       id                  ");
        query.append("     , x.eq_ctg_asmb_no       eqCtgAsmbNo         ");
        query.append("     , x.is_eqtype_asmb       isEqAsmb            ");
        query.append("     , x.is_eqtype_asmb       isEqTypeAsmb        ");
        query.append("     , x.p_eq_ctg_asmb_id     pEqCtgAsmbId        ");
        query.append("     , x.full_desc            fullDesc            ");
        query.append("FROM   TAEQCTGASMB x								");
        query.append("WHERE  1=1					 					");
        query.append(this.getWhere(maEqCatalogCommonDTO,maEqCtgAsmbListDTO,loginUser));
        query.getOrderByQuery("x.ord_no", maEqCatalogCommonDTO.getOrderBy(), maEqCatalogCommonDTO.getDirection());

        return getJdbcTemplate().queryForList(query.toString(maEqCatalogCommonDTO.getIsLoadMaxCount(), maEqCatalogCommonDTO.getFirstRow()));

    }
    private String getWhere(MaEqCatalogCommonDTO maEqCatalogCommonDTO, MaEqCtgAsmbListDTO maEqCtgAsmbListDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	query.getAndQuery("x.eqctg_id", maEqCatalogCommonDTO.getEqCtgId());
    	query.getAndQuery("x.comp_no", loginUser.getCompNo());
    	if (!"".equals(maEqCtgAsmbListDTO.getEqCtgAsmbId()))
        {
            query.getAndQuery("x.eq_ctg_asmb_id", maEqCtgAsmbListDTO.getEqCtgAsmbId());
            return query.toString();
        }
    	
    	return query.toString();
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaEqCtgAsmbListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteAsmbList(String id, String compNo)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("DELETE FROM TAEQCTGASMB				");
    	query.append("WHERE  eq_ctg_asmb_id 	= ?		    ");
    	query.append("  AND  comp_no		 	= ?			");
    	
    	Object[] objects = new Object[] {
    			id
    			,compNo
    			
    	};
    	
    	return this.getJdbcTemplate().update(query.toString(), objects);
    	
    }
    
    public String findTotalCount(
            MaEqCatalogCommonDTO maEqCatalogCommonDTO, MaEqCtgAsmbListDTO maEqCtgAsmbListDTO, User user) {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                    ");
        query.append("       COUNT(1)           ");
        query.append("FROM   TAEQCTGASMB x      ");
        query.append("WHERE  1=1                ");
        query.append(this.getWhere(maEqCatalogCommonDTO,maEqCtgAsmbListDTO,user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }   
}