package dream.asset.list.dao.sqlImpl;

import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;

import dream.asset.list.dao.MaEqMstrMoldProdDetailDAO;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrMoldProdDetailDTO;

/**
 * 구성자재 상세 dao
 * @author  kim21017
 * @version $Id: MaEqMstrMoldProdDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maEqMstrMoldProdDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaEqMstrMoldProdDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaEqMstrMoldProdDetailDAO 
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaEqMstrMoldProdDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param equipId
     * @param eqPartId
     * @param compNo
     * @return
     */
    public MaEqMstrMoldProdDetailDTO findDetail(String equipId, String eqMoldProductId, String compNo)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("select                                                  ");
        query.append("          a.equip_id              as equipId            ");
        query.append("         ,a.eqproduct_id      as eqMoldProductId    ");
        query.append("         ,a.product_no            as productNo          ");
        query.append("         ,a.product_desc          as productDesc        ");
        query.append("         ,a.ord_no                as ordNo              ");
        query.append("         ,a.remark                as remark             ");
        query.append("from TAEQPRODUCT a                                  ");
        query.append("where 1=1                                               ");
        query.append("  and  a.eqproduct_id	= '"+eqMoldProductId+"'			");
        query.append("  and  a.comp_no			= '"+compNo+"'				");
    
        MaEqMstrMoldProdDetailDTO maEqMstrMoldProdDetailDTO = 
        		(MaEqMstrMoldProdDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaEqMstrMoldProdDetailDTO()));
        
        return maEqMstrMoldProdDetailDTO;
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaEqMstrMoldProdDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrMoldProdDetailDTO
     * @param maEqMstrCommonDTO
     * @return
     */
    public int updateDetail(MaEqMstrMoldProdDetailDTO maEqMstrMoldProdDetailDTO, MaEqMstrCommonDTO maEqMstrCommonDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAEQPRODUCT SET	    ");
    	query.append("	product_no				= ?	    ");
    	query.append("	,product_desc			= ?	    ");
    	query.append("	,ord_no			        = ?		");
    	query.append("	,remark			        = ?		");
    	query.append("WHERE eqproduct_id	= ?	    ");
    	query.append("  AND comp_no			= ?		    ");
    	
    	Object[] objects = new Object[] {
    			maEqMstrMoldProdDetailDTO.getProductNo()
    			,maEqMstrMoldProdDetailDTO.getProductDesc()
    			,maEqMstrMoldProdDetailDTO.getOrdNo()
    			,maEqMstrMoldProdDetailDTO.getRemark()
    			,maEqMstrMoldProdDetailDTO.getEqMoldProductId()
    			,maEqMstrCommonDTO.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaEqMstrMoldProdDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrMoldProdDetailDTO
     * @param maEqMstrCommonDTO
     * @return
     */
    public int insertDetail(MaEqMstrMoldProdDetailDTO maEqMstrMoldProdDetailDTO, MaEqMstrCommonDTO maEqMstrCommonDTO)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("INSERT INTO TAEQPRODUCT					");
    	query.append("	(comp_no,			eqproduct_id,	    ");
    	query.append("	 product_no,		product_desc,			");
    	query.append("	 ord_no,			remark,			        ");
    	query.append("	 equip_id                       	        ");
    	query.append("	)	VALUES									");
    	query.append("	(?,						?,					");
    	query.append("	 ?,						?,					");
    	query.append("	 ?,						?,					");
    	query.append("	 ?                      					");
    	query.append("	)											");
    	
    	Object[] objects = new Object[] {
    			maEqMstrCommonDTO.getCompNo()
    			,maEqMstrMoldProdDetailDTO.getEqMoldProductId()
    			,maEqMstrMoldProdDetailDTO.getProductNo()
    			,maEqMstrMoldProdDetailDTO.getProductDesc()
    			,maEqMstrMoldProdDetailDTO.getOrdNo()
    			,maEqMstrMoldProdDetailDTO.getRemark()
    			,maEqMstrMoldProdDetailDTO.getEquipId()
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
    }
}