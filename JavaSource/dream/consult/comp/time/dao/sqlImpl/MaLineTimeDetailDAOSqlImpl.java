package dream.consult.comp.time.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.consult.comp.time.dao.MaLineTimeDetailDAO;
import dream.consult.comp.time.dto.MaLineTimeCommonDTO;
import dream.consult.comp.time.dto.MaLineTimeDetailDTO;

/**
 * 가동시간설정 - 상세
 * 
 * @author kim21017
 * @version $Id: MaLineTimeDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="maLineTimeDetailDAOTarget"
 * @spring.txbn id="maLineTimeDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaLineTimeDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaLineTimeDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaLineTimeDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param eqLocId
     * @param compNo
     * @return
     */
    public MaLineTimeDetailDTO findDetail(MaLineTimeCommonDTO maLineTimeCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT        																								");
        query.append("    a.lnwrklist_id																			lnWrkListId     ");
        query.append("    ,a.comp_no                                    											compNo       	");
        query.append("    ,(SELECT description       																				");
        query.append("      FROM TACOMP      																						");
        query.append("      WHERE comp_no = a.comp_no)      														compDesc      	");
        query.append("    ,a.eqloc_id                                   											eqlocId        	");
        query.append("    ,(SELECT description       																				");
        query.append("      FROM TAEQLOC         																					");
        query.append("      WHERE comp_no = a.comp_no         																		");
        query.append("       AND eqloc_id = a.eqloc_id)             												eqlocDesc      	");
        query.append("    ,a.lnwrklist_no                               											lnWrkListNo     ");
        query.append("    ,a.description                                											lnWrkListDesc	");
        query.append("    ,a.wrkcallist_id                              											wrkCalListId    ");
        query.append("    ,(SELECT description       																				");
        query.append("      FROM TAWRKCALLIST        																				");
        query.append("      WHERE comp_no = a.comp_no         																		");
        query.append("       AND wrkcallist_id = a.wrkcallist_id)																	wrkCalListDesc		");
        query.append("    ,a.is_use                                  																isUse     			");
        query.append("    ,a.REMARK                                     															REMARK       		");
        query.append("	  ,a.plant    																								plantId 			");
        query.append("    ,(SELECT b.description        																								");
        query.append("      FROM TAPLANT b        																										");
        query.append("      WHERE b.comp_no = a.comp_no       																							");
        query.append("       AND b.plant = a.plant)   																				plantDesc			");
        query.append("    ,a.equip_id																			 					equipNo  			");
        query.append("    ,a.equip_id 	 																							equipNameId			");
        query.append("    ,(select description from TAEQUIPMENT WHERE comp_no = a.comp_no AND equip_id = a.equip_id) 				equipNameDesc		");
        query.append("    ,a.lnwrk_create_type    																					runTimeSettingId	");
        query.append("    ,dbo.SFACODE_TO_DESC(a.lnwrk_create_type,'LNWRK_CREATE_TYPE','SYS','','ko')    							runTimeSettingDesc	");
        query.append("FROM TALNWRKLIST a        																										");
        query.append("WHERE a.lnwrklist_id  = ?      																									");
        query.append("AND a.comp_no         = ?     																									");

        Object[] objects = new Object[] {
                maLineTimeCommonDTO.getLnWrkListId(),
                maLineTimeCommonDTO.getCompNo()
        };
        
        MaLineTimeDetailDTO maLineTimeDetailDTO = 
        		(MaLineTimeDetailDTO)getJdbcTemplate().query(query.toString(),objects, new MwareExtractor(new MaLineTimeDetailDTO()));
        
        return maLineTimeDetailDTO;
    }
    
    /**
     * insert update
     * @author ghlee
     * @version $Id: MaLineTimeDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maLineTimeDetailDTO
     * @param user
     * @return
     */
    @Override
    public int insertDetail(MaLineTimeDetailDTO maLineTimeDetailDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("INSERT INTO TALNWRKLIST                       					");
        query.append(" ( comp_no		,lnwrklist_id	,lnwrklist_no					");
        query.append("   ,eqloc_id		,description	,wrkcallist_id					");
        query.append("   ,is_use		,REMARK			,plant          				");
        query.append("   ,equip_id		,lnwrk_create_type								");
        query.append(" ) VALUES                                  						");
        query.append(" (  ?				,?				,? 		        				");
        query.append("   ,?				,?				,?          					");
        query.append("   ,?				,?				,?              				");
        query.append("   ,?				,?					             				");
        query.append(" )                                           						");
        
        Object[] objects = new Object[] {
                maLineTimeDetailDTO.getCompNo()
                ,maLineTimeDetailDTO.getLnWrkListId()
                ,maLineTimeDetailDTO.getLnWrkListNo()
                ,maLineTimeDetailDTO.getEqLocId()
                ,maLineTimeDetailDTO.getLnWrkListDesc()
                ,maLineTimeDetailDTO.getWrkCalListId()
                ,maLineTimeDetailDTO.getIsUse()
                ,maLineTimeDetailDTO.getRemark()
                ,maLineTimeDetailDTO.getPlantId()
                ,maLineTimeDetailDTO.getEquipNameId()
                ,maLineTimeDetailDTO.getRunTimeSettingId()
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaLineTimeDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maLineTimeDetailDTO
     * @return
     */
    public int updateDetail(MaLineTimeDetailDTO maLineTimeDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("UPDATE TALNWRKLIST SET       	");
        query.append("   comp_no 			= ?		");
        query.append("  ,eqloc_id    		= ?    	");
        query.append("  ,description 		= ?    	");
        query.append("  ,wrkcallist_id   	= ?    	");
        query.append(" 	,is_use   			= ?    	");
        query.append(" 	,remark   			= ?    	");
        query.append(" 	,plant 			  	= ?     ");
        query.append(" 	,lnwrklist_no	  	= ?     ");
        query.append(" 	,equip_id	  		= ?     ");
        query.append(" 	,lnwrk_create_type	= ?     ");
        query.append("WHERE lnwrklist_id 	= ?     ");
        query.append("  AND comp_no  		= ?     ");
        
        Object[] objects = new Object[] {
                maLineTimeDetailDTO.getCompNo()
                ,maLineTimeDetailDTO.getEqLocId()
                ,maLineTimeDetailDTO.getLnWrkListDesc()
                ,maLineTimeDetailDTO.getWrkCalListId()
                ,maLineTimeDetailDTO.getIsUse()
                ,maLineTimeDetailDTO.getRemark()
                ,maLineTimeDetailDTO.getPlantId()
                ,maLineTimeDetailDTO.getLnWrkListNo()
                ,maLineTimeDetailDTO.getEquipNameId()
                ,maLineTimeDetailDTO.getRunTimeSettingId()
                ,maLineTimeDetailDTO.getLnWrkListId()
                ,maLineTimeDetailDTO.getCompNo()
        };
    	
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
}