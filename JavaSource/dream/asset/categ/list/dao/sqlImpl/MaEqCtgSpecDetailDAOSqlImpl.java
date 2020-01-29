package dream.asset.categ.list.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.asset.categ.list.dao.MaEqCtgSpecDetailDAO;
import dream.asset.categ.list.dto.MaEqCatalogCommonDTO;
import dream.asset.categ.list.dto.MaEqCtgSpecDetailDTO;
import dream.asset.categ.list.dto.MaEqCtgSpecListDTO;

/**
 * 설비종류별 표준제원 상세 dao
 * @author  syyang
 * @version $Id: MaEqCtgSpecDetailDAO.java,v 1.0 2015/12/04 08:10:27 syyang Exp $
 * @since   1.0
 * @spring.bean id="maEqCtgSpecDetailDAOTarget"
 * @spring.txbn id="maEqCtgSpecDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaEqCtgSpecDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaEqCtgSpecDetailDAO
{
    /**
     * detail find
     * @author syyang
     * @version $Id: MaEqCtgSpecDetailDAO.java,v 1.0 20155/12/02 08:25:47 syyang Exp $
     * @since   1.0
     * 
     * @param maEqCtgSpecListDTO
     * @param user
     * @return
     */
    public MaEqCtgSpecDetailDTO findDetail(MaEqCtgSpecListDTO maEqCtgSpecListDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
    	query.append("SELECT															");
        query.append("       x.eqctg_id									eqctgId			");
        query.append("       ,x.eqctgspec_id							eqctgspecId		");
        query.append("       ,x.categ									categ			");
        query.append("       ,x.prompt									prompt			");
        query.append("       ,x.uom										uom				");
        query.append("       ,x.ord_no									ordNo			");
        query.append("       ,x.is_use									isUse			");
        query.append("       ,x.eq_ctg_asmb_id							eqCtgAsmbId		");
        query.append("     ,(SELECT a.description                               		");
        query.append("       FROM TAEQCTGASMB a                                 		");
        query.append("       WHERE a.eq_ctg_asmb_id=x.eq_ctg_asmb_id)	eqCtgAsmbDesc	");
        query.append("     , x.is_eqtype_spec							isEqTypeSpec	");
        query.append("FROM   TAEQCTGSPEC x												");
        query.append("WHERE	 1=1														");
        query.append("  AND  x.comp_no			= ?										");
        query.append("  AND	 x.eqctgspec_id 	= ?										");

        Object[] objects = new Object[] {
        		user.getCompNo(),
        		maEqCtgSpecListDTO.getEqCtgSpecId()
    	};
        MaEqCtgSpecDetailDTO maEqCtgSpecDetailDTO = 
        		(MaEqCtgSpecDetailDTO)getJdbcTemplate().query(query.toString(),getObject(objects), new MwareExtractor(new MaEqCtgSpecDetailDTO()));
        
        return maEqCtgSpecDetailDTO;
    }
    /**
     * detail update
     * @author syyang
     * @version $Id: MaEqCtgSpecDetailDAO.java,v 1.0 20155/12/02 08:25:47 syyang Exp $
     * @since   1.0
     * 
     * @param maEqCtgSpecDetailDTO
     * @param user
     * @return
     */
    public int updateDetail(MaEqCtgSpecDetailDTO maEqCtgSpecDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAEQCTGSPEC SET		");
    	query.append("	eq_ctg_asmb_id		= ?,	");
    	query.append("	categ				= ?,	");
    	query.append("	prompt				= ?,	");
    	query.append("	uom					= ?,	");
    	query.append("	is_use				= ?,	");
    	query.append("	ord_no				= ?,	");
    	query.append("	is_eqtype_spec		= ?		");
    	query.append("WHERE eqctgspec_id	= ?		");
    	query.append("  AND comp_no			= ?		");
    	
    	Object[] objects = new Object[] {
    			maEqCtgSpecDetailDTO.getEqCtgAsmbId(),
    			maEqCtgSpecDetailDTO.getCateg(),
    			maEqCtgSpecDetailDTO.getPrompt(),
    			maEqCtgSpecDetailDTO.getUom(),
    			maEqCtgSpecDetailDTO.getIsUse(),
    			maEqCtgSpecDetailDTO.getOrdNo(),
    			maEqCtgSpecDetailDTO.getIsEqTypeSpec(),
    			maEqCtgSpecDetailDTO.getEqCtgAsmbId(),
    			user.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail insert
     * @author syyang
     * @version $Id: MaEqCtgSpecDetailDAO.java,v 1.0 20155/12/02 08:25:47 syyang Exp $
     * @since   1.0
     * 
     * @param maEqCtgSpecDetailDTO
     * @param maEqCatalogCommonDTO
     * @param user
     * @return
     */
    public int insertDetail(MaEqCtgSpecDetailDTO maEqCtgSpecDetailDTO, MaEqCatalogCommonDTO maEqCatalogCommonDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAEQCTGSPEC              	");
    	query.append("	(comp_no,		eqctgspec_id,		");
    	query.append("	 eqctg_id,		eq_ctg_asmb_id,		");
    	query.append("	 categ,			prompt,				");
    	query.append("	 uom,			ord_no,				");
    	query.append("	 is_use,		is_eqtype_spec		");
    	query.append("	)	VALUES							");
    	query.append("	(?,				?,					");
    	query.append("	 ?,				?,					");
    	query.append("	 ?,				?,					");
    	query.append("	 ?,				?,					");
    	query.append("	 ?,				?			 		");
        query.append("  )                                   ");
    	
    	Object[] objects = new Object[] {
    			user.getCompNo(),
    			maEqCtgSpecDetailDTO.getEqCtgSpecId(),
    			maEqCatalogCommonDTO.getEqCtgId(),
    			maEqCtgSpecDetailDTO.getEqCtgAsmbId(),
    			maEqCtgSpecDetailDTO.getCateg(),
    			maEqCtgSpecDetailDTO.getPrompt(),
    			maEqCtgSpecDetailDTO.getUom(),
    			maEqCtgSpecDetailDTO.getOrdNo(),
    			maEqCtgSpecDetailDTO.getIsUse(),
    			maEqCtgSpecDetailDTO.getIsEqTypeSpec()
    	};
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
}