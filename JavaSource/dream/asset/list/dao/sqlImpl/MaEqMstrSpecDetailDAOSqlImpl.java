package dream.asset.list.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.asset.list.dao.MaEqMstrSpecDetailDAO;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrSpecDetailDTO;
import dream.asset.list.dto.MaEqMstrSpecListDTO;

/**
 * 설비제원(스펙) 상세 dao
 * @author  kim21017
 * @version $Id: MaEqMstrSpecDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maEqMstrSpecDetailDAOTarget"
 * @spring.txbn id="maEqMstrSpecDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaEqMstrSpecDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaEqMstrSpecDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaEqMstrSpecDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param equipId
     * @param eqSpecId
     * @param compNo
     * @return
     */
    public MaEqMstrSpecDetailDTO findDetail(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrSpecListDTO maEqMstrSpecListDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
		query.append("SELECT										");
        query.append("       x.equip_id			equipId,			");
        query.append("       x.eqspec_id		eqSpecId,			");
        query.append("       x.categ			categ,				");
        query.append("       x.prompt			prompt,				");
        query.append("       x.response			response,			");
        query.append("       x.uom				uom,				");
        query.append("       x.ord_no			ordNo				");
        query.append("     , x.eqasmb_id        eqAsmbId			");
        query.append("     ,(SELECT a.full_desc 					");
        query.append("       FROM TAEQASMB a 						");
        query.append("       WHERE a.comp_no = x.comp_no 			");
        query.append("         AND a.eqasmb_id = x.eqasmb_id) eqAsmbDesc	");
        query.append("	   , x.remark 			REMARK				");
        query.append("FROM   TAEQSPEC x								");
        query.append("WHERE 1=1 									");
        query.append("	AND  x.eqspec_id 		= ?					");
        query.append("  AND  x.comp_no			= ?					");

        Object[] objects = new Object[] {
        		maEqMstrSpecListDTO.getEqSpecId()
              , user.getCompNo()
        };
    
        MaEqMstrSpecDetailDTO maEqMstrSpecDetailDTO = 
        		(MaEqMstrSpecDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new MaEqMstrSpecDetailDTO()));
        
        return maEqMstrSpecDetailDTO;
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaEqMstrSpecDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrSpecDetailDTO
     * @param maEqMstrCommonDTO
     * @return
     */
    public int updateDetail(MaEqMstrSpecDetailDTO maEqMstrSpecDetailDTO, MaEqMstrCommonDTO maEqMstrCommonDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAEQSPEC SET			");
    	query.append("	categ				= ?,	");
    	query.append("	prompt				= ?,	");
    	query.append("	response			= ?,	");
    	query.append("	uom					= ?,	");
    	query.append("	eqasmb_id			= ?,	");
    	query.append("	ord_no				= ?,	");
    	query.append("	remark				= ?,	");
    	query.append("	eqctgspec_id		= ?,	");
    	query.append("	is_eqtype_spec		= ?		");
    	query.append("WHERE eqspec_id		= ?		");
    	query.append("  AND comp_no			= ?		");
    	
    	Object[] objects = new Object[] {
    			maEqMstrSpecDetailDTO.getCateg(),
    			maEqMstrSpecDetailDTO.getPrompt(),
    			maEqMstrSpecDetailDTO.getResponse(),
    			maEqMstrSpecDetailDTO.getUom(),
    			maEqMstrSpecDetailDTO.getEqAsmbId(),
    			maEqMstrSpecDetailDTO.getOrdNo(),
    			maEqMstrSpecDetailDTO.getRemark(),
    			maEqMstrSpecDetailDTO.getEqCtgSpecId(),
    			maEqMstrSpecDetailDTO.getIsEqTypeSpec(),
    			maEqMstrSpecDetailDTO.getEqSpecId(),
    			user.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaEqMstrSpecDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrSpecDetailDTO
     * @param maEqMstrCommonDTO
     * @return
     */
    public int insertDetail(MaEqMstrSpecDetailDTO maEqMstrSpecDetailDTO, MaEqMstrCommonDTO maEqMstrCommonDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAEQSPEC				");
    	query.append("	(comp_no,		eqspec_id,		");
    	query.append("	 equip_id,		categ,			");
    	query.append("	 prompt,		response,		");
    	query.append("	 uom,			ord_no			");
    	query.append("  ,eqasmb_id		,eqctgspec_id	");
    	query.append("  ,is_eqtype_spec	,remark			");
    	query.append("	)	VALUES						");
    	query.append("	(?,				?,				");
    	query.append("	 ?,				?,				");
    	query.append("	 ?,				?,				");
    	query.append("	 ?,				?,				");
    	query.append("	 ?,				?,				");
    	query.append("	 ?,             ?				");
    	query.append("	)								");
    	
    	Object[] objects = new Object[] {
    			user.getCompNo(),
    			maEqMstrSpecDetailDTO.getEqSpecId(),
    			maEqMstrCommonDTO.getEquipId(),
    			maEqMstrSpecDetailDTO.getCateg(),
    			maEqMstrSpecDetailDTO.getPrompt(),
    			maEqMstrSpecDetailDTO.getResponse(),
    			maEqMstrSpecDetailDTO.getUom(),
    			maEqMstrSpecDetailDTO.getOrdNo(),
    			maEqMstrSpecDetailDTO.getEqAsmbId(),
    			maEqMstrSpecDetailDTO.getEqCtgSpecId(),
    			maEqMstrSpecDetailDTO.getIsEqTypeSpec()
    			,maEqMstrSpecDetailDTO.getRemark()
    	};
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    public String copyDetail(String oldEquipId, String newEquipId, String oldKeyId, String newKeyId, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAEQSPEC				");
    	query.append("	(comp_no,		eqspec_id,		");
    	query.append("	 equip_id,		categ,			");
    	query.append("	 prompt,		response,		");
    	query.append("	 uom,			ord_no			");
    	query.append("  ,eqasmb_id	   ,remark			");
    	query.append("	)								");
    	query.append("SELECT 							");
    	query.append("	 comp_no,						");
    	
    	if(!"".equals(newKeyId))
    	{	// Unit 복사인 경우
    		query.append("   '"+newKeyId+"',   ");
    	}
    	else
    	{	// 전체 복사인 경우
    		query.append("   NEXT VALUE FOR sqaeqspec_id,   	");
    	}
    	
    	query.append("	 ?,				categ,			");
    	query.append("	 prompt,		response,		");
    	query.append("	 uom,			ord_no			");
    	query.append("  ,eqasmb_id	   ,remark			");
    	query.append("FROM TAEQSPEC						");
    	query.append("WHERE comp_no   = ?				");
    	query.append("  AND equip_id  = ?				");

    	// Unit 복사인 경우 Key 값을 넣어 하나만 복사한다.
    	query.getAndQuery("eqspec_id", oldKeyId);
    	
    	Object[] objects = new Object[] {
    			newEquipId
    		  ,	user.getCompNo()
  			  , oldEquipId
	  	};
	  	
	  	getJdbcTemplate().update(query.toString(), objects);
	  	
	  	return "0";
    }
}