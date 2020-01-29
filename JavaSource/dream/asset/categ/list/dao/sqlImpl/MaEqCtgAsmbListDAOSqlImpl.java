package dream.asset.categ.list.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
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
public class MaEqCtgAsmbListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaEqCtgAsmbListDAO
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
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                            ");
        query.append("       ''                     SEQNO,              ");
        query.append("       ''                     ISDELCHECK,         ");
        query.append("       x.eqctg_id             EQCTGID,            ");
        query.append("       x.eq_ctg_asmb_id       EQCTGASMBID,        ");
        query.append("       x.description          EQCTGASMBDESC,      ");
        query.append("       x.remark               REMARK,             ");
        query.append("       x.ord_no               ORDNO,              ");
        query.append("       x.is_use               ISUSE               ");
        query.append("     , x.eq_ctg_asmb_id       ID                  ");
        query.append("     , x.eq_ctg_asmb_no       EQCTGASMBNO         ");
        query.append("     , x.is_eqtype_asmb       ISEQASMB            ");
        query.append("     , x.is_eqtype_asmb       ISEQTYPEASMB        ");
        query.append("     , x.p_eq_ctg_asmb_id     PEQCTGASMBID        ");
        query.append("     , x.full_desc            FULLDESC            ");
        query.append("FROM   TAEQCTGASMB x                              ");
        query.append("WHERE  1=1                                        ");
        query.append(this.getWhere(maEqCatalogCommonDTO,maEqCtgAsmbListDTO,loginUser));
        query.getOrderByQuery("x.eq_ctg_asmb_id", "x.ord_no", maEqCatalogCommonDTO.getOrderBy(), maEqCatalogCommonDTO.getDirection());

        return getJdbcTemplate().queryForList(query.toString(maEqCatalogCommonDTO.getIsLoadMaxCount(), maEqCatalogCommonDTO.getFirstRow()));
    }
    private String getWhere(MaEqCatalogCommonDTO maEqCatalogCommonDTO, MaEqCtgAsmbListDTO maEqCtgAsmbListDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
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
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("DELETE FROM TAEQCTGASMB				");
    	query.append("WHERE  eq_ctg_asmb_id 	= ?		    ");
    	query.append("  AND  comp_no		 	= ?			");
    	
    	Object[] objects = new Object[] {
    			id
    			,compNo
    			
    	};
    	
    	return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    public String findTotalCount(
            MaEqCatalogCommonDTO maEqCatalogCommonDTO, MaEqCtgAsmbListDTO maEqCtgAsmbListDTO, User user) {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                    ");
        query.append("       COUNT(1)           ");
        query.append("FROM   TAEQCTGASMB x      ");
        query.append("WHERE  1=1                ");
        query.append(this.getWhere(maEqCatalogCommonDTO,maEqCtgAsmbListDTO,user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }   
}