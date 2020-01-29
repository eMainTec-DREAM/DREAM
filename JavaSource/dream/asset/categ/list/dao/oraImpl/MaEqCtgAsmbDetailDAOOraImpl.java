package dream.asset.categ.list.dao.oraImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.asset.categ.list.dao.MaEqCtgAsmbDetailDAO;
import dream.asset.categ.list.dto.MaEqCatalogCommonDTO;
import dream.asset.categ.list.dto.MaEqCtgAsmbDetailDTO;
import dream.asset.categ.list.dto.MaEqCtgAsmbListDTO;

/**
 * 설비종류별 작업부위 상세 dao
 * @author  kim21017
 * @version $Id: MaEqCtgAsmbDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maEqCtgAsmbDetailDAOTarget"
 * @spring.txbn id="maEqCtgAsmbDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaEqCtgAsmbDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MaEqCtgAsmbDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaEqCtgAsmbDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqCtgAsmbListDTO
     * @param user
     * @return
     */
    public MaEqCtgAsmbDetailDTO findDetail(MaEqCtgAsmbListDTO maEqCtgAsmbListDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
        
		query.append("SELECT													");
        query.append("       x.eqctg_id			    eqctgId						");
        query.append("     , x.eq_ctg_asmb_id	    eqCtgAsmbId					");
        query.append("     , x.description		    eqCtgAsmbDesc			    ");
        query.append("     , x.remark			    remark					    ");
        query.append("     , x.is_use			    isUse					    ");
        query.append("     , x.ord_no			    ordNo					    ");
        query.append("     , x.eq_ctg_asmb_no       eqCtgAsmbNo                 ");
        query.append("     , x.is_eqtype_asmb       isEqTypeAsmb                ");
        query.append("     , x.p_eq_ctg_asmb_id     pEqCtgAsmbId                ");
        query.append("     , (SELECT a.description                              ");
        query.append("        FROM TAEQCTGASMB a                                ");
        query.append("        WHERE a.eq_ctg_asmb_id = x.p_eq_ctg_asmb_id)  pEqCtgAsmbDesc     ");
        query.append("     , x.full_desc            fullDesc                    ");
        query.append("FROM   TAEQCTGASMB x										");
        query.append("WHERE	 1=1												");
        query.append("  AND  x.comp_no			= ?								");
        query.append("  AND	 x.eq_ctg_asmb_id 	= ?								");
    
        Object[] objects = new Object[] {
        		user.getCompNo(),
        		maEqCtgAsmbListDTO.getEqCtgAsmbId()
    	};
        
        MaEqCtgAsmbDetailDTO maEqCtgAsmbDetailDTO = 
        		(MaEqCtgAsmbDetailDTO)getJdbcTemplate().query(query.toString(),objects, new MwareExtractor(new MaEqCtgAsmbDetailDTO()));
        
        return maEqCtgAsmbDetailDTO;
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaEqCtgAsmbDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqCtgAsmbDetailDTO
     * @param user
     * @return
     */
    public int[] updateDetail(final List<MaEqCtgAsmbDetailDTO> list, final User user)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAEQCTGASMB SET		");
    	query.append("	description			= ?,	");
    	query.append("	full_desc			= ?,	");
    	query.append("	remark				= ?,	");
    	query.append("	is_use				= ?,	");
    	query.append("	ord_no				= ?,	");
    	query.append("	is_eqtype_asmb		= ?,	");
    	query.append("	p_eq_ctg_asmb_id	= ?		");
    	query.append("WHERE eq_ctg_asmb_id	= ?		");
    	query.append("  AND comp_no			= ?		");
    	
    	return getJdbcTemplate().batchUpdate(query.toString(), new BatchPreparedStatementSetter()
        {
            
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException
            {
                MaEqCtgAsmbDetailDTO maEqCtgAsmbDetailDTO = list.get(i);
                
                Object[] objects = new Object[] {
                        maEqCtgAsmbDetailDTO.getEqCtgAsmbDesc(),
                        maEqCtgAsmbDetailDTO.getFullDesc(),
                        maEqCtgAsmbDetailDTO.getRemark(),
                        maEqCtgAsmbDetailDTO.getIsUse(),
                        maEqCtgAsmbDetailDTO.getOrdNo(),
                        maEqCtgAsmbDetailDTO.getIsEqTypeAsmb(),
                        maEqCtgAsmbDetailDTO.getPeqCtgAsmbId(),
                        maEqCtgAsmbDetailDTO.getEqCtgAsmbId(),
                        user.getCompNo()
                };
                
                for(int j=0;j<objects.length;j++){
                    ps.setObject(j+1, objects[j]);
                }
            }
            
            @Override
            public int getBatchSize()
            {
                return list.size();
            }
        });
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaEqCtgAsmbDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqCtgAsmbDetailDTO
     * @param maEqCatalogCommonDTO
     * @param user
     * @return
     */
    public int insertDetail(MaEqCtgAsmbDetailDTO maEqCtgAsmbDetailDTO, MaEqCatalogCommonDTO maEqCatalogCommonDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("INSERT INTO TAEQCTGASMB					");
    	query.append("	(  comp_no		  , eq_ctg_asmb_id		");
    	query.append("	 , eqctg_id		  , description			");
    	query.append("	 , ord_no		  , is_use				");
    	query.append("	 , remark         , eq_ctg_asmb_no		");
    	query.append("	 , is_eqtype_asmb , p_eq_ctg_asmb_id	");
    	query.append("	 , full_desc 	                        ");
    	query.append("	)	VALUES								");
    	query.append("	(  ?		      , ?					");
    	query.append("	 , ?		      , ?					");
    	query.append("	 , ?		      , ?					");
    	query.append("	 , ?		      , ?					");
    	query.append("	 , ?			  , ? 			        ");
    	query.append("	 , ?			              			");
    	query.append("	)										");
    	
    	Object[] objects = new Object[] {
    			  user.getCompNo()
    			, maEqCtgAsmbDetailDTO.getEqCtgAsmbId()
    			, maEqCatalogCommonDTO.getEqCtgId()
    			, maEqCtgAsmbDetailDTO.getEqCtgAsmbDesc()
    			, maEqCtgAsmbDetailDTO.getOrdNo()
    			, maEqCtgAsmbDetailDTO.getIsUse()
    			, maEqCtgAsmbDetailDTO.getRemark()
    			, maEqCtgAsmbDetailDTO.getEqCtgAsmbNo()
    			, maEqCtgAsmbDetailDTO.getIsEqTypeAsmb()
    			, maEqCtgAsmbDetailDTO.getPeqCtgAsmbId()
    			, maEqCtgAsmbDetailDTO.getFullDesc()
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
    }
}