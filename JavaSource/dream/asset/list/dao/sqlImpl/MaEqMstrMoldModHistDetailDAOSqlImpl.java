package dream.asset.list.dao.sqlImpl;

import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;

import dream.asset.list.dao.MaEqMstrMoldModHistDetailDAO;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrMoldModHistDetailDTO;

/**
 * 구성자재 상세 dao
 * @author  kim21017
 * @version $Id: MaEqMstrMoldModHistDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maEqMstrMoldModHistDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaEqMstrMoldModHistDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaEqMstrMoldModHistDetailDAO 
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaEqMstrMoldModHistDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param equipId
     * @param eqMoldModHistId
     * @param compNo
     * @return
     */
    public MaEqMstrMoldModHistDetailDTO findDetail(String equipId, String eqMoldModHistId, String compNo)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("select                                                ");
        query.append("     a.eqmoldmodhist_id           as eqMoldModHistId  ");
        query.append("    ,a.equip_id                   as equipId          ");
        query.append("    ,a.mod_cnt                    as modCnt           ");
        query.append("    ,a.cavity		                as capacity         ");
        query.append("    ,a.output                     as outPut           ");
        query.append("    ,a.ownership                  as ownerShip        ");
        query.append("    ,a.ord_no                     as ordNo            ");
        query.append("    ,a.remark                     as remark           ");
        query.append("from TAEQMOLDMODHIST a                                ");
        query.append("WHERE	 a.eqmoldmodhist_id		= '"+eqMoldModHistId+"'	");
        query.append("  AND  a.comp_no			    = '"+compNo+"'			");
    
        MaEqMstrMoldModHistDetailDTO maEqMstrMoldModHistDetailDTO = 
        		(MaEqMstrMoldModHistDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaEqMstrMoldModHistDetailDTO()));
        
        return maEqMstrMoldModHistDetailDTO;
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaEqMstrMoldModHistDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrMoldModHistDetailDTO
     * @param maEqMstrCommonDTO
     * @return
     */
    public int updateDetail(MaEqMstrMoldModHistDetailDTO maEqMstrMoldModHistDetailDTO, MaEqMstrCommonDTO maEqMstrCommonDTO)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("UPDATE TAEQMOLDMODHIST SET	    ");
    	query.append("	mod_cnt				 = ?	    ");
    	query.append("	,cavity				 = ? 	    ");
    	query.append("	,output			     = ?		");
    	query.append("	,ownership			 = ?		");
    	query.append("	,ord_no			     = ?		");
    	query.append("	,remark			     = ?		");
    	query.append("WHERE eqmoldmodhist_id = ?		");
    	query.append("  AND comp_no			 = ?		");
    	
    	Object[] objects = new Object[] {
    			maEqMstrMoldModHistDetailDTO.getModCnt()
    			,maEqMstrMoldModHistDetailDTO.getCapacity()
    			,maEqMstrMoldModHistDetailDTO.getOutPut()
    			,maEqMstrMoldModHistDetailDTO.getOwnerShip()
    			,maEqMstrMoldModHistDetailDTO.getOrdNo()
    			,maEqMstrMoldModHistDetailDTO.getRemark()
    			,maEqMstrMoldModHistDetailDTO.getEqMoldModHistId()
    			,maEqMstrCommonDTO.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaEqMstrMoldModHistDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrMoldModHistDetailDTO
     * @param maEqMstrCommonDTO
     * @return
     */
    public int insertDetail(MaEqMstrMoldModHistDetailDTO maEqMstrMoldModHistDetailDTO, MaEqMstrCommonDTO maEqMstrCommonDTO)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("INSERT INTO TAEQMOLDMODHIST							");
    	query.append("	(comp_no,				eqmoldmodhist_id,			");
    	query.append("	 equip_id,				mod_cnt,			        ");
    	query.append("	 cavity,				output,			            ");
    	query.append("	 ownership,				ord_no,			            ");
    	query.append("	 remark                  			                ");
    	query.append("	)	VALUES									");
    	query.append("	(?,						?,					");
    	query.append("	 ?,						NULLIF(?,''),		");
    	query.append("	 ?,						NULLIF(?,''),		");
    	query.append("	 ?,						?,					");
    	query.append("	 ?											");
    	query.append("	)											");
    	
    	Object[] objects = new Object[] {
    			maEqMstrCommonDTO.getCompNo()
    			,maEqMstrMoldModHistDetailDTO.getEqMoldModHistId()
    			,maEqMstrMoldModHistDetailDTO.getEquipId()
    			,maEqMstrMoldModHistDetailDTO.getModCnt()
    			,maEqMstrMoldModHistDetailDTO.getCapacity()
    			,maEqMstrMoldModHistDetailDTO.getOutPut()
    			,maEqMstrMoldModHistDetailDTO.getOwnerShip()
    			,maEqMstrMoldModHistDetailDTO.getOrdNo()
    			,maEqMstrMoldModHistDetailDTO.getRemark()
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
    }
}