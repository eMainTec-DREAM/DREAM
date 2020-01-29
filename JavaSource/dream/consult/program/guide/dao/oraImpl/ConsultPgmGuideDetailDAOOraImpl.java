package dream.consult.program.guide.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.consult.program.guide.dao.ConsultPgmGuideDetailDAO;
import dream.consult.program.guide.dto.ConsultPgmGuideCommonDTO;
import dream.consult.program.guide.dto.ConsultPgmGuideDetailDTO;

/**
 * Guide Page - Detail DAO implements
 * @author kim21017
 * @version $Id: ConsultPgmGuideDetailDAOOraImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="consultPgmGuideDetailDAOTarget"
 * @spring.txbn id="consultPgmGuideDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ConsultPgmGuideDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements ConsultPgmGuideDetailDAO
{
	
    public ConsultPgmGuideDetailDTO findPgmGuideDetail(ConsultPgmGuideCommonDTO consultPgmGuideCommonDTO, User user) 
    {
        QueryBuffer query = new QueryBuffer();
        
        String compNo = user.getCompNo();
        
        query.append("SELECT																			");
        query.append("		x.pgguide_id											AS pgGuideId		");
        query.append("		,x.pgguide_no											AS pgGuideNo		");
        query.append("		,x.description											AS pgGuideDesc		");
        query.append("		,x.eqloc_id												AS eqLocId			");
        query.append("		,(SELECT a.full_desc														");
        query.append("			FROM TAEQLOC a															");
        query.append("			WHERE a.comp_no 	= x.comp_no											");
        query.append("			AND a.eqloc_id 		= x.eqloc_id										");
        query.append("		) 														AS eqLocDesc		");
        query.append("		,x.equip_id												AS equipId			");
        query.append("		,(SELECT a.description														");
        query.append("			FROM TAEQUIPMENT a														");
        query.append("			WHERE a.comp_no 	= x.comp_no											");
        query.append("			AND a.equip_id 		= x.equip_id										");
        query.append("		) 														AS equipDesc		");
        query.append("		,x.eqctg_id												AS eqCtgId			");
        query.append("		,(SELECT a.full_desc														");
        query.append("			FROM TAEQCTG a															");
        query.append("			WHERE a.comp_no 	= x.comp_no											");
        query.append("			AND a.eqctg_id 		= x.eqctg_id										");
        query.append("		) 														AS eqCtgDesc		");
        query.append("		,x.dept_id												AS deptId			");
        query.append("		,(SELECT a.description														");
        query.append("			FROM TADEPT a															");
        query.append("			WHERE a.comp_no 	= x.comp_no											");
        query.append("			AND a.dept_id 		= x.dept_id											");
        query.append("		) 														AS deptDesc			");
        query.append("		,x.emp_id												AS empId			");
        query.append("		,(SELECT a.emp_name															");
        query.append("			FROM TAEMP a															");
        query.append("			WHERE a.comp_no 	= x.comp_no											");
        query.append("			AND a.emp_id 		= x.emp_id											");
        query.append("		) 														AS empDesc			");
        query.append("		,x.eq_status											AS eqStatusId		");
        query.append("		,SFACODE_TO_DESC(x.eq_status,'EQ_STATUS','SYS',''							");
        query.append("							,? ) 								AS eqStatusDesc		");
        query.append("		,x.plant												AS plantId			");
        query.append("		,(SELECT a.description														");
        query.append("			FROM TAPLANT a															");
        query.append("			WHERE a.comp_no 	= x.comp_no											");
        query.append("			AND a.plant 		= x.plant											");
        query.append("		) 														AS plantDesc		");
        query.append("		,x.part_id												AS partId			");
        query.append("		,(SELECT a.full_desc														");
        query.append("			FROM TAPARTS a															");
        query.append("			WHERE a.comp_no 	= x.comp_no											");
        query.append("			AND a.part_id 		= x.part_id											");
        query.append("		) 														AS partDesc			");
        query.append("		,x.remark												AS remark			");
        query.append("		,x.is_use												AS isUseId			");
        query.append("		,SFACODE_TO_DESC(x.is_use,'IS_USE','SYS',''									");
        query.append("							,? ) 								AS isUseDesc		");
        query.append("FROM TXPGGUIDE x																	");
    	query.append("WHERE  1=1																		");
    	query.append("AND    pgguide_id = ?																");
    	query.append("AND    comp_no    = ?																");
        
        Object[] objects = new Object[] {
        		user.getLangId()
        		,user.getLangId()
        		,consultPgmGuideCommonDTO.getPgGuideId()
    			,user.getCompNo()
    	};
    
        ConsultPgmGuideDetailDTO consultPgmGuideDetailDTO = 
        		(ConsultPgmGuideDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new ConsultPgmGuideDetailDTO()));
        
        return consultPgmGuideDetailDTO;
        
    }
    

    public int insertPgmGuideDetail(ConsultPgmGuideDetailDTO consultPgmGuideDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;

    	query.append("INSERT INTO TXPGGUIDE(	");
    	query.append("	comp_no					");
    	query.append("	,pgguide_id				");
    	query.append("	,pgguide_no				");
    	query.append("	,description			");
    	query.append("	,eqloc_id				");
    	query.append("	,equip_id				");
    	query.append("	,eqctg_id				");
    	query.append("	,dept_id				");
    	query.append("	,emp_id					");
    	query.append("	,eq_status				");
    	query.append("	,plant					");
    	query.append("	,part_id				");
    	query.append("	,remark					");
    	query.append("	,is_use					");
    	query.append(")VALUES(					");
    	query.append("	?						");
    	query.append("	,?						");
    	query.append("	,?						");
    	query.append("	,?						");
    	query.append("	,?						");
    	query.append("	,?						");
    	query.append("	,?						");
    	query.append("	,?						");
    	query.append("	,?						");
    	query.append("	,?						");
    	query.append("	,?						");
    	query.append("	,?						");
    	query.append("	,?						");
    	query.append("	,?						");
    	query.append(")							");
    	
    	Object[] objects = new Object[] {
    			 user.getCompNo()
    			,consultPgmGuideDetailDTO.getPgGuideId()
    			,consultPgmGuideDetailDTO.getPgGuideNo()
    			,consultPgmGuideDetailDTO.getPgGuideDesc()
    			,consultPgmGuideDetailDTO.getEqLocId()
    			,consultPgmGuideDetailDTO.getEquipId()
    			,consultPgmGuideDetailDTO.getEqCtgId()
    			,consultPgmGuideDetailDTO.getDeptId()
    			,consultPgmGuideDetailDTO.getEmpId()
    			,consultPgmGuideDetailDTO.getEqStatusId()
    			,consultPgmGuideDetailDTO.getPlantId()
    			,consultPgmGuideDetailDTO.getPartId()
    			,consultPgmGuideDetailDTO.getRemark()
    			,consultPgmGuideDetailDTO.getIsUseId()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    
    
    public int updatePgmGuideDetail(ConsultPgmGuideDetailDTO consultPgmGuideDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	int rtnValue  = 0;

    	query.append("UPDATE TXPGGUIDE SET					");
    	query.append("	description			= ?				");
    	query.append("	,eqloc_id			= ?				");
    	query.append("	,equip_id			= ?				");
    	query.append("	,eqctg_id			= ?				");
    	query.append("	,dept_id			= ?				");
    	query.append("	,emp_id				= ?				");
    	query.append("	,eq_status			= ?				");
    	query.append("	,plant				= ?				");
    	query.append("	,part_id			= ?				");
    	query.append("	,remark				= ?				");
    	query.append("	,is_use				= ?				");
    	query.append("WHERE  comp_no		= ?				");
    	query.append("  AND  pgguide_id		= ?				");
    	
    	Object[] objects = new Object[] {
    			consultPgmGuideDetailDTO.getPgGuideDesc()
    			,consultPgmGuideDetailDTO.getEqLocId()
    			,consultPgmGuideDetailDTO.getEquipId()
    			,consultPgmGuideDetailDTO.getEqCtgId()
    			,consultPgmGuideDetailDTO.getDeptId()
    			,consultPgmGuideDetailDTO.getEmpId()
    			,consultPgmGuideDetailDTO.getEqStatusId()
    			,consultPgmGuideDetailDTO.getPlantId()
    			,consultPgmGuideDetailDTO.getPartId()
    			,consultPgmGuideDetailDTO.getRemark()
    			,consultPgmGuideDetailDTO.getIsUseId()
    			,user.getCompNo()
    			,consultPgmGuideDetailDTO.getPgGuideId()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
}