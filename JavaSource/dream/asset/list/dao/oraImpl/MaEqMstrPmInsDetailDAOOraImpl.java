package dream.asset.list.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.asset.list.dao.MaEqMstrPmInsDetailDAO;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrPmInsDetailDTO;
import dream.asset.list.dto.MaEqMstrPmInsListDTO;

/**
 * 설비 예방점검 상세 DAO IMPL
 * @author  kim21017
 * @version $Id: MaEqMstrPmInsDetailDAOOraImpl.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maEqMstrPmInsDetailDAOTarget"
 * @spring.txbn id="maEqMstrPmInsDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaEqMstrPmInsDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MaEqMstrPmInsDetailDAO
{

	@Override
	public MaEqMstrPmInsDetailDTO findDetail(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPmInsListDTO maEqMstrPmInsListDTO,
			User user) throws Exception {
		
		QueryBuffer query = new QueryBuffer();
		
		query.append("SELECT y.equip_id AS equipId												");
		query.append("		,x.pm_id AS pmId													");
		query.append("		,y.pmequip_id AS pmEquipId											");
		query.append("		,x.pm_no AS pmNo													");
		query.append("		,x.wo_type AS woTypeId												");
		query.append("		,x.pm_type AS pmTypeId												");
		query.append("		,SFACODE_TO_DESC(x.pm_type,'PMI_TYPE','SYS','','"+user.getLangId()+"') AS pmTypeDesc		");
		query.append("		,x.cycle AS cycle													");
		query.append("		,x.period_type AS periodTypeId										");
		query.append("		,x.cycle AS oldCycle												");
		query.append("		,x.period_type AS oldPeriodTypeId									");
		query.append("		,SFACODE_TO_DESC(x.period_type,'PERIOD_TYPE','SYS','','"+user.getLangId()+"') AS periodTypeDesc		");
		query.append("		,x.is_active AS isActiveId											");
		query.append("		,SFACODE_TO_DESC(x.is_active,'IS_USE','SYS','','"+user.getLangId()+"') AS isActiveDesc		");
		query.append("		,y.init_wrk_date AS initWrkDate										");
		query.append("		,y.init_wrk_date AS oldInitWrkDate										");
		query.append("		,x.dept_id AS deptId												");
		query.append("		,(SELECT a.description												");
		query.append("			FROM TADEPT a													");
		query.append("			WHERE 1=1														");
		query.append("			AND a.comp_no = x.comp_no										");
		query.append("			AND a.dept_id = x.dept_id) AS deptDesc							");
		query.append("		,x.wkctr_id AS wkCtrId												");
		query.append("		,(SELECT a.description												");
		query.append("			FROM TAWKCTR a													");
		query.append("			WHERE 1=1														");
		query.append("			AND a.comp_no = x.comp_no										");
		query.append("			AND a.wkctr_id = x.wkctr_id) AS wkCtrDesc						");
		query.append("		,x.emp_id AS empId													");
		query.append("		,(SELECT a.emp_name													");
		query.append("			FROM TAEMP a													");
		query.append("			WHERE 1=1														");
		query.append("			AND a.comp_no = x.comp_no										");
		query.append("			AND a.emp_id = x.emp_id) AS empDesc								");
		query.append("		,x.wrkcallist_id AS wrkCalListId									");
		query.append("		,(SELECT a.description												");
		query.append("			FROM TAWRKCALLIST a												");
		query.append("			WHERE 1=1														");
		query.append("			AND a.comp_no = x.comp_no										");
		query.append("			AND a.wrkcallist_id = x.wrkcallist_id) AS wrkCalListDesc		");
		query.append("		,x.remark AS remark													");
		query.append("		,x.description AS description										");
		query.append("      ,x.plant 							plantId							");
		query.append("      ,(SELECT a.description 												");
		query.append("          FROM TAPLANT a 													");
		query.append("         WHERE a.comp_no = x.comp_no 										");
		query.append("           AND a.plant = x.plant) 		plantDesc						");
		query.append("      ,x.schedule_type 					scheduleTypeId					");
		query.append("      ,(SELECT SFACODE_TO_DESC(x.schedule_type, 'SCHEDULE_TYPE', 'SYS', x.comp_no, '"+user.getLangId()+"') 		");
		query.append("          FROM DUAL ) 					scheduleTypeDesc				");
		query.append("      ,x.usage 							usage							");
		query.append("      ,x.wo_res_bef 						woResBef						");
		query.append("      ,x.lnwrklist_id 					lnWrkListId						");
		query.append("      ,(SELECT a.description 												");
		query.append("          FROM TALNWRKLIST a 												");
		query.append("         WHERE a.comp_no = x.comp_no 										");
		query.append("           AND a.lnwrklist_id = x.lnwrklist_id) 		lnWrkListDesc		");
		query.append("      ,x.work_time 						predWoTimeMin					");
		query.append("FROM TAPMLST x INNER JOIN TAPMEQUIP y										");
		query.append("ON x.comp_no = y.comp_no													");
		query.append("AND x.pm_id = y.pm_id 													");
		query.getAndQuery("x.is_deleted", "N");
		query.getAndQuery("y.is_deleted", "N");
		query.append("WHERE 1=1																	");
		query.getAndQuery("x.comp_no", user.getCompNo());
		query.getAndQuery("x.pm_id", maEqMstrPmInsListDTO.getPmId());
		query.getAndQuery("y.pmequip_id", maEqMstrPmInsListDTO.getPmEquipId());
		
		MaEqMstrPmInsDetailDTO maEqMstrPmInsDetailDTO = 
				(MaEqMstrPmInsDetailDTO)this.getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaEqMstrPmInsDetailDTO()));
		
		return maEqMstrPmInsDetailDTO;
	}

}