package dream.asset.categ.list.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.asset.categ.list.dao.MaEqCatalogPointDetailDAO;
import dream.asset.categ.list.dto.MaEqCatalogCommonDTO;
import dream.asset.categ.list.dto.MaEqCatalogPointDetailDTO;
import dream.asset.categ.list.dto.MaEqCatalogPointListDTO;

/**
 * 설비종류의 점검항목 탭 디테일 DAO IMPL
 * @author  euna0207
 * @version $Id: MaEqCatalogPointDetailDAOOraImpl.java,v 1.0 2015/12/04 08:10:27 euna0207 Exp $
 * @since   1.0
 * @spring.bean id="maEqCatalogPointDetailDAOTarget"
 * @spring.txbn id="maEqCatalogPointDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaEqCatalogPointDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MaEqCatalogPointDetailDAO
{

	@Override
	public MaEqCatalogPointDetailDTO findDetail(MaEqCatalogCommonDTO maEqCatalogCommonDTO, MaEqCatalogPointListDTO maEqCatalogPointListDTO,
			User user) throws Exception {
		
		QueryBuffer query = new QueryBuffer();
		
		query.append(" SELECT 																													");
		query.append(" x.comp_no 																							AS compNo			");
		query.append(" , x.eqctgpmpoint_id 																					AS eqCtgPmPointId	");
		query.append(" , (SELECT eqctg_id FROM TAEQCTG WHERE comp_no = x.comp_no AND eqctg_id = x.eqctg_id) 				AS eqCtgId			");
		query.append(" , x.cycle 																							AS cycle			");
		query.append(" , x.eq_ctg_asmb_id 																						AS eqasmbId			");
		query.append(" , (SELECT description FROM TAEQCTGASMB WHERE comp_no = x.comp_no AND eq_ctg_asmb_id = x.eq_ctg_asmb_id) 	AS eqasmbDesc	");
		query.append(" , x.period_type                                														AS periodTypeId		");
		query.append(" , SFACODE_TO_DESC(x.period_type, 'PERIOD_TYPE','SYS','"+user.getCompNo()+"', '"+user.getLangId()+"') AS periodTypeDesc	");
		query.append(" , x.check_point 																						AS checkPoint		");
		query.append(" , x.check_method 																					AS checkMethod		");
		query.append(" , x.fit_basis 															 							AS fitBasis			");
		query.append(" , x.check_type																						AS checkTypeId		");
		query.append(" , SFACODE_TO_DESC(x.check_type, 'CHECK_TYPE','SYS','"+user.getCompNo()+"','"+user.getLangId()+"')  	AS checkTypeDesc	");
		query.append(" , x.check_min 																						AS checkMin			");
		query.append(" , x.check_basis_val																					AS checkBasisVal	");
		query.append(" , x.check_max 																						AS checkMax			");
		query.append(" , x.uom 																								AS uom				");
		query.append(" , x.ord_no 																							AS ordNo			");
        query.append(" , SFACODE_TO_DESC(x.is_use, 'IS_USE', 'SYS', '"+user.getCompNo()+"','"+user.getLangId()+"') 			AS isUse			");
		query.append(" , x.remark																							AS remark			");
		query.append(" , x.work_time																						AS PredTime			");
		query.append(" , x.step_num 																						AS stepNum			");
        query.append(" , x.is_eqtype_point							 														AS isCommCtgPoint	");
        query.append(" , x.usage                 					 														AS USAGE			");
        query.append(" , x.schedule_type							 														AS SCHEDULETYPEID	");
        query.append(" , (SELECT SFACODE_TO_DESC(x.schedule_type, 'SCHEDULE_TYPE', 'SYS', '"+user.getCompNo()+"','"+user.getLangId()+"') FROM DUAL)    scheduleTypeDesc		");
		query.append(" FROM TAEQCTGPMPOINT x																									");
		query.append(" WHERE 1=1																												");
		query.append(" AND  eqctgpmpoint_id = ?																									");
    	query.append(" AND  comp_no  		  = ?																								");
    	query.getOrderByQuery("x.eqctgpmpoint_id", maEqCatalogPointListDTO.getOrderBy(), maEqCatalogPointListDTO.getDirection());
        
    	Object[] objects = new Object[] {
    			maEqCatalogPointListDTO.getEqCtgPmPointId()
        		,user.getCompNo()
    	};
        
		MaEqCatalogPointDetailDTO maEqCatalogPointDetailDTO = 
				(MaEqCatalogPointDetailDTO)this.getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new MaEqCatalogPointDetailDTO()));
		
		return maEqCatalogPointDetailDTO;
	}

    
    public int insertDetail(MaEqCatalogCommonDTO maEqCatalogCommonDTO, MaEqCatalogPointDetailDTO maEqCatalogPointDetailDTO, User user)
        {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query.append("INSERT INTO TAEQCTGPMPOINT (    		");
    	query.append("comp_no								");
    	query.append(", eqctgpmpoint_id                		");
    	query.append(", eqctg_id               		  		");
    	query.append(", check_point                    		");
    	query.append(", eq_ctg_asmb_id                 		");
    	query.append(", is_use                        		");
    	query.append(", cycle                        		");
    	query.append(", period_type                    		");
    	query.append(", check_method                		");
    	query.append(", check_type		               		");
    	query.append(", check_min                    		");
    	query.append(", check_basis_val                		");
    	query.append(", check_max                    		");
    	query.append(", uom                            		");
    	query.append(", ord_no                        		");
    	query.append(", remark                        		");
    	query.append(", fit_basis  							");
    	query.append(", work_time  							");
    	query.append(", step_num  							");
    	query.append(", usage	  							");
    	query.append(", is_eqtype_point						");
    	query.append(", schedule_type						");
    	query.append(")                  					");
    	query.append("VALUES (								");
    	query.append("?										");
    	query.append(", ?									");
    	query.append(", ?									");
    	query.append(", ?									");
    	query.append(", ?									");
    	query.append(", ?									");
    	query.append(", ?									");
    	query.append(", ?									");
    	query.append(", ?									");
    	query.append(", ?									");
    	query.append(", ?									");
    	query.append(", ?									");
    	query.append(", ?									");
    	query.append(", ?									");
    	query.append(", ?									");
    	query.append(", ?									");
    	query.append(", ?									");
    	query.append(", ?									");
    	query.append(", ?									");
    	query.append(", ?									");
    	query.append(", ?									");
    	query.append(", ?									");
    	query.append(")										");

    	
    	Object[] objects = new Object[] {
    			user.getCompNo()
    			,maEqCatalogPointDetailDTO.getEqCtgPmPointId()
    			,maEqCatalogCommonDTO.getEqCtgId()
    			,maEqCatalogPointDetailDTO.getCheckPoint()
    			,maEqCatalogPointDetailDTO.getEqasmbId()
    			,maEqCatalogPointDetailDTO.getIsUse()
    			,maEqCatalogPointDetailDTO.getCycle()
    			,maEqCatalogPointDetailDTO.getPeriodTypeId()
    			,maEqCatalogPointDetailDTO.getCheckMethod()
    			,maEqCatalogPointDetailDTO.getCheckTypeId()
    			,maEqCatalogPointDetailDTO.getCheckMin()
    			,maEqCatalogPointDetailDTO.getCheckBasisVal()
    			,maEqCatalogPointDetailDTO.getCheckMax()
    			,maEqCatalogPointDetailDTO.getUom()
    			,maEqCatalogPointDetailDTO.getOrdNo()
    			,maEqCatalogPointDetailDTO.getRemark()
    			,maEqCatalogPointDetailDTO.getFitBasis()
    			,maEqCatalogPointDetailDTO.getPredTime()
    			,maEqCatalogPointDetailDTO.getStepNum()
    			,maEqCatalogPointDetailDTO.getUsage()
    			,maEqCatalogPointDetailDTO.getIsCommCtgPoint()
    			,maEqCatalogPointDetailDTO.getScheduleTypeId()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    
    
    public int updateDetail(MaEqCatalogCommonDTO maEqCatalogCommonDTO, MaEqCatalogPointDetailDTO maEqCatalogPointDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	int rtnValue  = 0;

    	query.append("UPDATE TAEQCTGPMPOINT SET 			");
    	query.append(" check_point 			= ?             ");
    	query.append(", eq_ctg_asmb_id      = ? 	      	");
    	query.append(", is_use 				= ?             ");
    	query.append(", cycle    			= ?             ");
    	query.append(", period_type  		= ?             ");
    	query.append(", check_type		    = ?         	");
    	query.append(", check_method 		= ?             ");
    	query.append(", check_min     		= ?             ");
    	query.append(", check_basis_val     = ?             ");
    	query.append(", check_max      		= ?             ");
    	query.append(", uom   				= ?             ");
    	query.append(", ord_no    			= ?             ");
    	query.append(", remark    			= ?             ");
    	query.append(", fit_basis   		= ?				");
    	query.append(", work_time   		= ?				");
    	query.append(", step_num  			= ?				");
    	query.append(", usage	  			= ?				");
    	query.append(", is_eqtype_point		= ?				");
    	query.append(", schedule_type		= ?				");
    	query.append("WHERE comp_no 		= ?				");
    	query.append("AND eqctgpmpoint_id 	= ?				");

    	
    	Object[] objects = new Object[] {
    			maEqCatalogPointDetailDTO.getCheckPoint()
    			,maEqCatalogPointDetailDTO.getEqasmbId()
    			,maEqCatalogPointDetailDTO.getIsUse()
    			,maEqCatalogPointDetailDTO.getCycle()
    			,maEqCatalogPointDetailDTO.getPeriodTypeId()
    			,maEqCatalogPointDetailDTO.getCheckTypeId()
    			,maEqCatalogPointDetailDTO.getCheckMethod()
    			,maEqCatalogPointDetailDTO.getCheckMin()
    			,maEqCatalogPointDetailDTO.getCheckBasisVal()
    			,maEqCatalogPointDetailDTO.getCheckMax()
    			,maEqCatalogPointDetailDTO.getUom()
    			,maEqCatalogPointDetailDTO.getOrdNo()
    			,maEqCatalogPointDetailDTO.getRemark()
    			,maEqCatalogPointDetailDTO.getFitBasis()
    			,maEqCatalogPointDetailDTO.getPredTime()
    			,maEqCatalogPointDetailDTO.getStepNum()
    			,maEqCatalogPointDetailDTO.getUsage()
    			,maEqCatalogPointDetailDTO.getIsCommCtgPoint()
    			,maEqCatalogPointDetailDTO.getScheduleTypeId()
    			,user.getCompNo()
    			,maEqCatalogPointDetailDTO.getEqCtgPmPointId()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
}