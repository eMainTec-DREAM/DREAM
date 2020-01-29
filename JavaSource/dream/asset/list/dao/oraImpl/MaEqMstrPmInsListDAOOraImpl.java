package dream.asset.list.dao.oraImpl;

import java.util.List;

import common.bean.MwareConfig;
import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.DateUtil;
import common.util.QueryBuffer;
import dream.asset.list.dao.MaEqMstrPmInsListDAO;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrPmInsListDTO;

/**
 * 설비 예방점검 목록 DAO IMPL
 * @author  kim21017
 * @version $Id: MaEqMstrPmInsListDAOOraImpl.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maEqMstrPmInsListDAOTarget"
 * @spring.txbn id="maEqMstrPmInsListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaEqMstrPmInsListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaEqMstrPmInsListDAO
{

	@Override
	public List findEqPmInsList(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPmInsListDTO maEqMstrPmInsListDTO, User user)
			throws Exception {
		QueryBuffer query = new QueryBuffer();
		
		query.append("SELECT '' AS isDelCheck							");
		query.append("		,'' AS seqNo								");
		query.append("		,x.pm_id AS pmId							");
		query.append("		,y.pmequip_id AS pmEquipId					");
		query.append("		,x.pm_no AS pmNo							");
		query.append("		,x.pm_type AS pmTypeId						");
		query.append("		,SFACODE_TO_DESC(x.pm_type,'PMI_TYPE','SYS','','"+user.getLangId()+"') AS pmTypeDesc    ");
		query.append("		,x.description AS description				");
		query.append("      ,(CASE WHEN x.schedule_type != 'T' THEN x.USAGE END) USAGE		");
		query.append("      ,(CASE WHEN x.schedule_type = 'T' THEN x.CYCLE END ) AS cycle		");
		query.append("		,x.period_type AS periodType				");
		query.append("      ,(SELECT (CASE WHEN x.schedule_type = 'T' THEN SFACODE_TO_DESC(x.period_type,'PERIOD_TYPE','SYS','','"+user.getLangId()+"') END) 		");
		query.append("          FROM dual) AS periodTypeDesc			");
		query.append("		,y.init_wrk_date AS initWrkDate				");
		query.append("		,x.dept_id AS deptId						");
		query.append("		,(SELECT a.description						");
		query.append("			FROM TADEPT a 							");
		query.append("			WHERE 1=1								");
		query.append("			AND a.comp_no = x.comp_no				");
		query.append("			AND a.dept_id = x.dept_id				");
		query.append("		) AS deptDesc								");
		query.append("		,x.wkctr_id AS wkCtrId						");
		query.append("		,(SELECT a.description						");
		query.append("			FROM TAWKCTR a 							");
		query.append("			WHERE 1=1								");
		query.append("			AND a.comp_no = x.comp_no				");
		query.append("			AND a.wkctr_id = x.wkctr_id				");
		query.append("		) AS wkCtrDesc								");
		query.append("		,x.emp_id AS empId							");
		query.append("		,(SELECT a.emp_name							");
		query.append("			FROM TAEMP a 							");
		query.append("			WHERE 1=1								");
		query.append("			AND a.comp_no = x.comp_no				");
		query.append("			AND a.emp_id = x.emp_id					");
		query.append("		) AS empDesc								");
		query.append("		,x.is_active AS isActive					");
		query.append("      ,(SELECT SFACODE_TO_DESC(x.schedule_type, 'SCHEDULE_TYPE', 'SYS', x.comp_no, '"+user.getLangId()+"') 		");
		query.append("          FROM dual) 						scheduleTypeDesc				");
		query.append("FROM TAPMLST x INNER JOIN TAPMEQUIP y				");
		query.append("ON x.comp_no = y.comp_no							");
		query.append("AND x.pm_id = y.pm_id 							");
		query.getAndQuery("x.is_deleted", "N");
		query.getAndQuery("y.is_deleted", "N");
		query.append("WHERE 1=1											");
		query.append("AND x.is_active = 'Y'         ");
		query.append(this.getWhere(maEqMstrCommonDTO, maEqMstrPmInsListDTO, user));
		query.getOrderByQuery("x.pm_no", maEqMstrPmInsListDTO.getOrderBy(), maEqMstrPmInsListDTO.getDirection());
        
        
        return getJdbcTemplate().queryForList(query.toString(maEqMstrPmInsListDTO.getIsLoadMaxCount(), maEqMstrPmInsListDTO.getFirstRow()));
	}
	
	private String getWhere(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPmInsListDTO maEqMstrPmInsListDTO,User user)
    {
		QueryBuffer query = new QueryBuffer();
		
		query.getAndQuery("x.comp_no", user.getCompNo());
		query.getAndQuery("x.wo_type", "PMI");
		//query.getAndQuery("y.equip_id", maEqMstrCommonDTO.getEquipId());
		
		if(!"".equals(maEqMstrPmInsListDTO.getPmId()) && !"".equals(maEqMstrPmInsListDTO.getPmEquipId())){
			query.getAndQuery("x.pm_id", maEqMstrPmInsListDTO.getPmId());
			query.getAndQuery("y.pmequip_id", maEqMstrPmInsListDTO.getPmEquipId());
			return query.toString();
		}
    	
    	query.append("AND y.equip_id IN (SELECT b.equip_id						");
    	query.append("					 FROM TAEQUIPMENT b 					");
    	query.append("					 WHERE 1=1 								");
    	query.getAndQuery("b.comp_no", user.getCompNo());
    	query.append("					  AND b.item_no=(SELECT c.item_no		");
    	query.append("									 FROM TAEQUIPMENT c 	");
    	query.append("									 WHERE 1=1 				");
    	query.getAndQuery("c.comp_no", user.getCompNo());
    	query.getAndQuery("c.equip_id", maEqMstrCommonDTO.getEquipId());
    	query.append("									)						");
    	query.getAndQuery("b.is_last_version", "Y");
    	query.append("					)										");
    	
    	if("Y".equals(MwareConfig.getIsUsePmRevision())){
    		query.append("AND x.is_last_version = 'Y'              				");
    	}
    	query.append("AND x.is_active = 'Y'     								");
		
		return query.toString();
    }


	@Override
	public String findTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPmInsListDTO maEqMstrPmInsListDTO, User user)
			throws Exception {
		QueryBuffer query = new QueryBuffer();
		
		query.append("SELECT COUNT(1)								");
		query.append("FROM TAPMLST x INNER JOIN TAPMEQUIP y			");
		query.append("ON x.comp_no = y.comp_no						");
		query.append("AND x.pm_id = y.pm_id 						");
		query.getAndQuery("x.is_deleted", "N");
		query.getAndQuery("y.is_deleted", "N");
		query.append("WHERE 1=1										");
		query.append(this.getWhere(maEqMstrCommonDTO, maEqMstrPmInsListDTO, user));
		
		List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
	}

	@Override
	public int deletePmPoint(String pmId, User user) throws Exception {
		
		QueryBuffer query = new QueryBuffer();
		
		query.append("UPDATE TAPMPOINT 				");
		query.append("SET 	is_deleted 		= ?		");
		query.append("		,delete_by 		= ?		");
		query.append("		,delete_time 	= ?		");
		query.append("WHERE comp_no 	= ?			");
		query.append("AND   pm_id	 	= ?			");
		
		Object[] objects = new Object[]{
			"Y"
			,user.getUserId()
			,DateUtil.getDate()
			,user.getCompNo()
			,pmId
		};
		
		return this.getJdbcTemplate().update(query.toString(), objects);
	}

	@Override
	public int deletePmEquip(String pmId, String pmEquipId, User user) throws Exception {
		
		QueryBuffer query = new QueryBuffer();
		
		query.append("UPDATE TAPMEQUIP 								");
		query.append("SET 	is_deleted 		= ?						");
		query.append("		,description 	= ?||description		");
		query.append("		,delete_by 		= ?						");
		query.append("		,delete_time 	= ?						");
		query.append("WHERE comp_no 	= ?							");
		query.append("AND   pm_id	 	= ?							");
		query.append("AND   pmequip_id 	= ?							");
		
		Object[] objects = new Object[]{
				"Y"
				,"[DELETED]"
				,user.getUserId()
				,DateUtil.getDate()
				,user.getCompNo()
				,pmId
				,pmEquipId
		};
		
		return this.getJdbcTemplate().update(query.toString(), objects);
	}

	@Override
	public int deletePmList(String pmId, User user) throws Exception {
		
		QueryBuffer query = new QueryBuffer();
		
		query.append("UPDATE TAPMLST 								");
		query.append("SET 	is_deleted 		= ?						");
		query.append("		,description 	= ?||description		");
		query.append("		,delete_by 		= ?						");
		query.append("		,delete_time 	= ?						");
		query.append("WHERE comp_no 	= ?							");
		query.append("AND   pm_id	 	= ?							");
		
		Object[] objects = new Object[]{
				"Y"
				,"[DELETED]"
				,user.getUserId()
				,DateUtil.getDate()
				,user.getCompNo()
				,pmId
		};
		
		return this.getJdbcTemplate().update(query.toString(), objects);
	}

	@Override
	public int checkOtherPmEquip(String pmId, User user) throws Exception {
		QueryBuffer query = new QueryBuffer();
		
		query.append("SELECT COUNT(*)			");
		query.append("FROM TAPMEQUIP			");
		query.append("WHERE 1=1					");
		query.append("AND comp_no 	= ?			");
		query.append("AND pm_id		= ?			");
		query.append("AND is_deleted= ?			");
		
		Object[] objects = new Object[]{
				user.getCompNo()
				,pmId
				,"N"
		};
		
		return Integer.parseInt(QueryBuffer.listToString(this.getJdbcTemplate().queryForList(query.toString(), objects)));
	}
}