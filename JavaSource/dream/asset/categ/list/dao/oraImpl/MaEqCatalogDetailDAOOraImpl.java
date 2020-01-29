package dream.asset.categ.list.dao.oraImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.asset.categ.list.dao.MaEqCatalogDetailDAO;
import dream.asset.categ.list.dto.MaEqCatalogDetailDTO;

/**
 * 설비종류 - 상세 dao
 * 
 * @author kim21017
 * @version $Id: MaEqCatalogDetailDAO.java,v 1.0 2015/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="maEqCatalogDetailDAOTarget"
 * @spring.txbn id="maEqCatalogDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaEqCatalogDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MaEqCatalogDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaEqCatalogDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param eqCtgId
     * @param compNo
     * @return
     */
    public MaEqCatalogDetailDTO findDetail(String eqCtgId, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT															");
        query.append("       x.eqctg_id									AS eqCtgId,		");
        query.append("       x.eqctg_no 								AS eqCtgCode,	");
        query.append("       x.description								AS eqCtgDesc,	");
        query.append("       x.full_desc								AS fullDesc,	");
        query.append("       DECODE(x.p_eqctg_id,'0','',x.p_eqctg_id)	AS peqCtgId,	");
        query.append("       (SELECT full_desc											");
        query.append("          FROM TAEQCTG											");
        query.append("         WHERE comp_no =x.comp_no 								");
        query.append("           AND eqctg_id = x.p_eqctg_id )			AS peqCtgDesc,	");
        query.append("       x.eqctg_type							    AS eqTypeId,	");
        query.append("		 SFACODE_TO_DESC(x.eqctg_type,'EQCTG_TYPE','SYS','','"+loginUser.getLangId()+"')		AS eqTypeDesc,		");
        query.append("       x.ord_no									AS ordNo,		");
        query.append("       x.remark									AS remark,		");
        query.append("       x.mng_cd									AS mngCd,		");
        query.append("       x.is_use									AS isUse,		");
        query.append("      (SELECT COUNT(1)                                    		");
        query.append("       FROM   TAEQUIPMENT a                               		");
        query.append("       WHERE  a.comp_no = x.comp_no                       		");
        query.append("        AND a.is_deleted = 'N'                        			");
        query.append("        AND a.is_last_version = 'Y'                   			");
        query.append("        AND a.eqctg_id IN (SELECT aa.eqctg_id             		");
        query.append("        					 FROM TAEQCTG aa  	                   	");
        query.append("           				 WHERE aa.comp_no=x.comp_no            	");
        query.append("             	             START WITH eqctg_id=x.eqctg_id         ");
        query.append("             	             CONNECT BY PRIOR eqctg_id=p_eqctg_id))	AS eqCnt	");
        query.append("FROM   TAEQCTG x													");
        query.append("WHERE  1=1														");
        query.append("  AND  x.eqctg_id  = ?											");
        query.append("  AND  x.comp_no	 = ?											");

        Object[] objects = new Object[] {
        		eqCtgId,
        		loginUser.getCompNo()
    	};
        
        MaEqCatalogDetailDTO maEqCatalogDetailDTO = 
        		(MaEqCatalogDetailDTO)getJdbcTemplate().query(query.toString(),objects, new MwareExtractor(new MaEqCatalogDetailDTO()));
        
        return maEqCatalogDetailDTO;
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaEqCatalogDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqCatalogDetailDTO
     * @return
     */
    public int insertDetail(MaEqCatalogDetailDTO maEqCatalogDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("INSERT INTO TAEQCTG							");
    	query.append("	(comp_no,		eqctg_id,		eqctg_no,	");
    	query.append("	 description,	p_eqctg_id,		ord_no,		");
    	query.append("	 is_use,		remark,         eqctg_type,	");
    	query.append("	 mng_cd	                                    ");
    	query.append("	)	VALUES									");
    	query.append("	(?,				?,				?,			");
    	query.append("	 ?,				?,				?,			");
    	query.append("	 ?,				?,				?,			");
    	query.append("	 ?                              			");
    	query.append("	)											");
    	
    	Object[] objects = new Object[] {
    	        user.getCompNo(),
    			maEqCatalogDetailDTO.getEqCtgId(),
    			maEqCatalogDetailDTO.getEqCtgCode(),
    			maEqCatalogDetailDTO.getEqCtgDesc(),
    			maEqCatalogDetailDTO.getPeqCtgId(),
    			maEqCatalogDetailDTO.getOrdNo(),
    			maEqCatalogDetailDTO.getIsUse(),
    			maEqCatalogDetailDTO.getRemark(),
    			maEqCatalogDetailDTO.getEqTypeId(),
    			maEqCatalogDetailDTO.getMngCd()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaEqCatalogDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqCatalogDetailDTO
     * @return
     */
    public int[] updateDetail(final List<MaEqCatalogDetailDTO> list, final User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("UPDATE TAEQCTG SET		");
    	query.append("	eqctg_no		= ?,	");
    	query.append("	description		= ?,	");
    	query.append("	full_desc		= ?,	");
    	query.append("	p_eqctg_id		= ?,	");
    	query.append("	eqctg_type		= ?,	");
    	query.append("	ord_no			= ?,	");
    	query.append("	remark			= ?,	");
    	query.append("	mng_cd			= ?,	");
    	query.append("	is_use			= ?		");
    	query.append("WHERE eqctg_id 	= ?		");
    	query.append("  AND comp_no		= ?		");
    	
    	return getJdbcTemplate().batchUpdate(query.toString(), new BatchPreparedStatementSetter()
        {
            
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException
            {
                MaEqCatalogDetailDTO maEqCatalogDetailDTO = list.get(i);
                
                Object[] objects = new Object[] {
                        maEqCatalogDetailDTO.getEqCtgCode(),
                        maEqCatalogDetailDTO.getEqCtgDesc(),
                        maEqCatalogDetailDTO.getFullDesc(),
                        maEqCatalogDetailDTO.getPeqCtgId(),
                        maEqCatalogDetailDTO.getEqTypeId(),
                        maEqCatalogDetailDTO.getOrdNo(),
                        maEqCatalogDetailDTO.getRemark(),
                        maEqCatalogDetailDTO.getMngCd(),
                        maEqCatalogDetailDTO.getIsUse(),
                        maEqCatalogDetailDTO.getEqCtgId(),
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
}