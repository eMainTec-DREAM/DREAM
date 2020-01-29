package dream.asset.list.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.asset.list.dao.MaEqMstrPartDetailDAO;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrPartDetailDTO;
import dream.asset.list.dto.MaEqMstrPartListDTO;

/**
 * 구성자재 상세 dao
 * @author  kim21017
 * @version $Id: MaEqMstrPartDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maEqMstrPartDetailDAOTarget"
 * @spring.txbn id="maEqMstrPartDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaEqMstrPartDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MaEqMstrPartDetailDAO
{
    String compNo = "";
    
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaEqMstrPartDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrPartListDTO
     * @param user
     * @return
     */
    public MaEqMstrPartDetailDTO findDetail(MaEqMstrPartListDTO maEqMstrPartListDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();

        query.append("SELECT													");
        query.append("       x.equip_id 						equipId			");
        query.append("     , x.eqpart_id 						eqPartId		");
        query.append("     , x.part_id 							partId			");
        query.append("     , y.part_no                   		partNo			");
        query.append("     , y.description||', '||y.pt_size		partDesc		");
        query.append("     , y.description              		partName		");
        query.append("     , y.pt_size              		    ptSize		    ");
        query.append("     , y.model              		        model		    ");
        query.append("     , x.consist_qty						consistQty		");
        query.append("     , x.eqasmb_id						eqAsmbId		");
        query.append("     , (SELECT full_desc									");
        query.append("        FROM TAEQASMB										");
        query.append("        WHERE comp_no = x.comp_no							");
        query.append("           AND eqasmb_id = x.eqasmb_id)	eqAsmbDesc		");
        query.append("     , x.use_qty 							useQty			");
        query.append("     , x.issue_time 						issueTime		");
        query.append("     , x.issue_first_date 				issueFirstDate	");
        query.append("     , x.issue_last_date 					issueLastDate	");
        query.append("     , x.is_use               			isUse           ");
        query.append("     , x.eqasmb_id            			eqAsmbId        ");
        query.append("     , (SELECT a.description FROM TAEQASMB a WHERE x.eqasmb_id = a.eqasmb_id)	eqAsmbDesc  ");
        query.append("     , x.is_eqtype_part       			isEqtypePart    ");
        query.append("     , x.ord_no      						ordNo       	");
        query.append("     , x.consist_nbr      				consistNbr      ");
        query.append("     , x.dwg_no      						dwgNo       	");
        query.append("     , x.dwg_section_no      				dwgSectionNo    ");
        query.append("FROM   TAEQPART x INNER JOIN TAPARTS y					");
        query.append("ON x.comp_no = y.comp_no					                ");
        query.append("AND x.part_id = y.part_id					                ");
        query.append("WHERE	 x.eqpart_id 		= ?			    	            ");
        query.append("  AND  x.comp_no			= ?          					");
    
        Object[] objects = new Object[] {
        		maEqMstrPartListDTO.getEqPartId()
        		,user.getCompNo()
    	};
        
        compNo = user.getCompNo();
        
        MaEqMstrPartDetailDTO maEqMstrPartDetailDTO = 
        		(MaEqMstrPartDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new MaEqMstrPartDetailDTO()));
        
        return maEqMstrPartDetailDTO;
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaEqMstrPartDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrPartDetailDTO
     * @param maEqMstrCommonDTO
     * @param user
     * @return
     */
    public int updateDetail(MaEqMstrPartDetailDTO maEqMstrPartDetailDTO, MaEqMstrCommonDTO maEqMstrCommonDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("UPDATE TAEQPART SET			");
    	query.append("	part_id				= ?		");
    	query.append("	,consist_qty		= ?		");
    	query.append("	,eqasmb_id			= ?		");
    	query.append("	,ord_no				= ?		");
    	query.append("	,is_use  			= ?		");
    	query.append("	,eq_ctg_part_id  	= ?		");
    	query.append("	,eq_ctg_asmb_id  	= ?		");
    	query.append("	,is_eqtype_part  	= ?		");
    	query.append("	,use_qty 			= ?		");
    	query.append("	,issue_time 		= ?		");
    	query.append("	,issue_first_date   = ?		");
    	query.append("	,issue_last_date    = ?		");
    	query.append("	,consist_nbr        = ?		");
    	query.append("	,dwg_no      		= ?		");
    	query.append("	,dwg_section_no     = ?		");
    	query.append("WHERE eqpart_id		= ?		");
    	query.append("  AND comp_no			= ?		");
    	
    	Object[] objects = new Object[] {
    			maEqMstrPartDetailDTO.getPartId()
    			, maEqMstrPartDetailDTO.getConsistQty()
    			, maEqMstrPartDetailDTO.getEqAsmbId()
    			, maEqMstrPartDetailDTO.getOrdNo()
    			, maEqMstrPartDetailDTO.getIsUse()
    			, maEqMstrPartDetailDTO.getEqCtgPartId()
    			, maEqMstrPartDetailDTO.getEqCtgAsmbId()
    			, maEqMstrPartDetailDTO.getIsEqTypePart()
    			, maEqMstrPartDetailDTO.getUseQty()
    			, maEqMstrPartDetailDTO.getIssueTime()
    			, maEqMstrPartDetailDTO.getIssueFirstDate()
    			, maEqMstrPartDetailDTO.getIssueLastDate()
    			, maEqMstrPartDetailDTO.getConsistNbr()
    			, maEqMstrPartDetailDTO.getDwgNo()
    			, maEqMstrPartDetailDTO.getDwgSectionNo()
    			, maEqMstrPartDetailDTO.getEqPartId()
    			, user.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaEqMstrPartDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrPartDetailDTO
     * @param maEqMstrCommonDTO
     * @param user
     * @return
     */
    public int insertDetail(MaEqMstrPartDetailDTO maEqMstrPartDetailDTO, MaEqMstrCommonDTO maEqMstrCommonDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();

    	int result = 0;

    	query.append("INSERT INTO TAEQPART							");
    	query.append("	(comp_no				,eqpart_id			");
    	query.append("	 ,eqasmb_id				,equip_id			");
    	query.append("	 ,part_id				,consist_qty		");
    	query.append("	 ,eq_ctg_part_id		,eq_ctg_asmb_id     ");
    	query.append("	 ,is_eqtype_part		,ord_no			    ");
    	query.append("	 ,is_use                ,issue_time    		");
    	query.append("	 ,issue_first_date      ,issue_last_date    ");
    	query.append("	 ,use_qty				,consist_nbr		");
    	query.append("	 ,dwg_no				,dwg_section_no		");
    	query.append("	)	VALUES									");
    	query.append("	(?						,?					");
    	query.append("	 ,?						,?					");
    	query.append("	 ,?						,?					");
    	query.append("	 ,?						,?					");
    	query.append("	 ,?						,?					");
    	query.append("	 ,?						,?					");
    	query.append("	 ,?						,?					");
    	query.append("	 ,?						,?					");
    	query.append("	 ,?						,?					");
    	query.append("	)											");
    	
    	Object[] objects = new Object[] {
    			user.getCompNo()
    			, maEqMstrPartDetailDTO.getEqPartId()
    			, maEqMstrPartDetailDTO.getEqAsmbId()
    			, maEqMstrPartDetailDTO.getEquipId()
    			, maEqMstrPartDetailDTO.getPartId()
    			, maEqMstrPartDetailDTO.getConsistQty()
    			, maEqMstrPartDetailDTO.getEqCtgPartId()
    			, maEqMstrPartDetailDTO.getEqCtgAsmbId()
    			, maEqMstrPartDetailDTO.getIsEqTypePart()
    			, maEqMstrPartDetailDTO.getOrdNo()
    			, maEqMstrPartDetailDTO.getIsUse()
    			, maEqMstrPartDetailDTO.getIssueTime()
    			, maEqMstrPartDetailDTO.getIssueFirstDate()
    			, maEqMstrPartDetailDTO.getIssueLastDate()
    			, maEqMstrPartDetailDTO.getUseQty()
    			, maEqMstrPartDetailDTO.getConsistNbr()
    			, maEqMstrPartDetailDTO.getDwgNo()
    			, maEqMstrPartDetailDTO.getDwgSectionNo()
    	};
  
    	result = getJdbcTemplate().update(query.toString(), objects);
    	
    	return result;
    }
    
    /**
     * detail copy
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param oldEquipId
     * @param newEquipId
     * @param oldKeyId
     * @param newKeyId
     * @param user
     * @return
     */
    public String copyDetail(String oldEquipId, String newEquipId, String oldKeyId, String newKeyId, User user)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAEQPART                      ");
    	query.append("    (  comp_no          , eqpart_id       ");
    	query.append("     , eqasmb_id		  , equip_id        ");
    	query.append("     , part_id          , consist_qty		");
    	query.append("     , use_qty          , issue_time		");
    	query.append("     , is_eqtype_part   , ord_no			");
    	query.append("     , issue_first_date , issue_last_date	");
    	query.append("     , is_use           , consist_nbr     ");
    	query.append("     , dwg_no           , dwg_section_no )");
    	query.append("SELECT 									");
    	query.append("       comp_no           				   	");
    	
    	//eqpart_id
		if(!"".equals(newKeyId))
		{	// Unit 복사인 경우
			query.append("          , "+newKeyId+"				");
		}else
		{	// 전체 복사인 경우
			query.append("          , SQAeqpart_id.nextval		");
		}
		
		query.append("     , eqasmb_id        , ?     			");
    	query.append("     , part_id          , consist_qty     ");
    	query.append("     , eq_ctg_part_id   , eq_ctg_asmb_id	");
    	query.append("     , is_eqtype_part   , ord_no			");
    	query.append("     , issue_first_date , issue_last_date	");
    	query.append("     , is_use           , consist_nbr     ");
        query.append("     , dwg_no           , dwg_section_no  ");
    	query.append("FROM TAEQPART								");
    	query.append("WHERE comp_no   = ?						");
    	query.append("  AND equip_id  = ?						");
    	
    	// Unit 복사인 경우 Key 값을 넣어 하나만 복사한다.
        query.getAndQuery("eqpart_id", oldKeyId);
        
    	Object[] objects = new Object[] {
    			  newEquipId
      		  	, user.getCompNo()
    			, oldEquipId
    	};
    	
    	getJdbcTemplate().update(query.toString(), objects);
    	
    	return "0";
    }
}