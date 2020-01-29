package dream.asset.categ.list.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.asset.categ.list.dao.MaEqCatalogListDAO;
import dream.asset.categ.list.dto.MaEqCatalogCommonDTO;

/**
 * 설비종류 - 목록 dao
 * @author  kim21017
 * @version $Id: MaEqCatalogListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maEqCatalogListDAOTarget"
 * @spring.txbn id="maEqCatalogListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaEqCatalogListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaEqCatalogListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaEqCatalogListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqCatalogCommonDTO
     * @return List
     */
    public List findEqCatalogList(MaEqCatalogCommonDTO maEqCatalogCommonDTO, User loginUser)
    { 
        QueryBuffer query = new QueryBuffer();
        String compNo = loginUser.getCompNo();
        
        query.append("SELECT                                                    ");
        query.append("       x.eqctg_id             AS id,                      ");
        query.append("       ''                     AS seqNo,                   ");
        query.append("       ''                     AS isDelCheck,              ");
        query.append("       x.eqctg_id             AS eqCtgId,                 ");
        query.append("       x.eqctg_no             AS eqCtgCode,               ");
        query.append("       x.eqctg_type           AS eqTypeId,                ");
        query.append("       (SELECT SFACODE_TO_DESC(x.eqctg_type,'EQCTG_TYPE','SYS','"+compNo+"','"+loginUser.getLangId()+"') FROM DUAL)   eqCtgType,");
        query.append("       x.description          AS eqCtgDesc,               ");
        query.append("       x.full_desc            AS fullDesc,                ");
        query.append("       x.ord_no               AS ordNo,                   ");
        query.append("       x.is_use               AS isUse,                   ");
        query.append("       x.p_eqctg_id           AS pEqCtgId                 ");
        query.append("     , x.mng_cd               AS mngCd                    ");
        query.append("     , x.remark               AS remark                   ");
        query.append("FROM   TAEQCTG x                                          ");
        query.append("WHERE  1=1                                                ");
        query.append(this.getWhere(maEqCatalogCommonDTO,loginUser)               );
        query.append("ORDER BY ordNo                                            ");

        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaEqCatalogListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteEqCatalog(String id, String compNo)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	query.append("DELETE FROM TAEQCTGASMB			");
    	query.append("WHERE eqctg_id	 = '"+id+"'		");
    	query.append("  AND comp_no	 = '"+compNo+"'		");
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	query = new QueryBuffer();
    	
    	query.append("DELETE FROM TAEQCTGPART			");
    	query.append("WHERE eqctg_id	 = '"+id+"'		");
    	query.append("  AND comp_no	 = '"+compNo+"'		");
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	query = new QueryBuffer();
    	
    	query.append("DELETE FROM TAEQCTG				");
    	query.append("WHERE eqctg_id	 = '"+id+"'		");
    	query.append("  AND comp_no	 = '"+compNo+"'		");
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	return rtnValue;
    }
    
    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id: MaEqCatalogListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqCatalogCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaEqCatalogCommonDTO maEqCatalogCommonDTO,User loginUser)
    {    
        QueryBuffer query = new QueryBuffer();
        
        // CommonDTO의 equipNo가 있는경우는 상세에서 수정 되어서 List의 한 Row만을 재조회
        if (!"".equals(maEqCatalogCommonDTO.getEqCtgId()))
        {
            query.getAndQuery("eqctg_id", maEqCatalogCommonDTO.getEqCtgId());
        }
        
        
        query.getCodeLikeQuery("p_eqctg_id", "(select a.description from taeqctg a where a.eqctg_id = x.p_eqctg_id)", maEqCatalogCommonDTO.getFilterPeqCtgId(), maEqCatalogCommonDTO.getFilterPeqCtgDesc());
        query.getStringEqualQuery("x.comp_no", loginUser.getCompNo());
        query.getLikeQuery("description", maEqCatalogCommonDTO.getFilterEqCtgDesc());
        //설비종류
        query.getSysCdQuery("eqctg_type", maEqCatalogCommonDTO.getEqTypeId(), maEqCatalogCommonDTO.getEqTypeDesc(),
                "EQCTG_TYPE",loginUser.getCompNo(),loginUser.getLangId());
        
        return query.toString();
    }
}