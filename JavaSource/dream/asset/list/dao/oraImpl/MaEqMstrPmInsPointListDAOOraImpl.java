package dream.asset.list.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.DateUtil;
import common.util.QueryBuffer;
import dream.asset.list.dao.MaEqMstrPmInsPointListDAO;
import dream.asset.list.dto.MaEqMstrPmInsDetailDTO;
import dream.asset.list.dto.MaEqMstrPmInsPointListDTO;

/**
 * 설비 예방점검 항목 목록 DAO IMPL
 * @author  kim21017
 * @version $Id: MaEqMstrPmInsPointListDAOOraImpl.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maEqMstrPmInsPointListDAOTarget"
 * @spring.txbn id="maEqMstrPmInsPointListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaEqMstrPmInsPointListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaEqMstrPmInsPointListDAO
{

	@Override
	public List findEqPmInsPointList(MaEqMstrPmInsDetailDTO maEqMstrPmInsDetailDTO, MaEqMstrPmInsPointListDTO maEqMstrPmInsPointListDTO, User user) throws Exception {
		QueryBuffer query = new QueryBuffer();
		
		query.append("SELECT '' AS isDelCheck							");
		query.append("		,'' AS seqNo								");
		query.append("		,x.pm_id AS pmId							");
		query.append("		,x.pm_point_id AS pmPointId					");
		query.append("		,x.step_num AS stepNum						");
		query.append("		,x.eqasmb_id AS eqAsmbId					");
		query.append("		,(SELECT a.full_desc						");
		query.append("			FROM TAEQASMB a 						");
		query.append("			WHERE 1=1								");
		query.append("			AND a.comp_no = x.comp_no				");
		query.append("			AND a.eqasmb_id = x.eqasmb_id			");
		query.append("		) AS eqAsmbDesc								");
		query.append("		,x.check_point AS checkPoint				");
		query.append("		,x.fit_basis AS fitBasis					");
		query.append("		,x.check_method AS checkMethod				");
		query.append("		,x.check_type AS checkTypeId				");
		query.append("		,SFACODE_TO_DESC(x.check_type,'CHECK_TYPE','SYS','','"+user.getLangId()+"') AS checkTypeDesc    ");
		query.append("		,x.is_active AS isActive					");
        query.append("		,(SELECT a.param1 													");
        query.append("			FROM TACDSYSD a													");
        query.append("			WHERE 1=1														");
        query.append("			AND a.list_type='CHECK_TYPE'									");
        query.append("			AND a.cdsysd_no = x.check_type) AS detailPage					");
		query.append("FROM TAPMPOINT x									");
		query.append("WHERE 1=1											");
		query.append(this.getWhere(maEqMstrPmInsDetailDTO, maEqMstrPmInsPointListDTO, user));
		query.getOrderByQuery("x.step_num", maEqMstrPmInsPointListDTO.getOrderBy(), maEqMstrPmInsPointListDTO.getDirection());
        
        
        return getJdbcTemplate().queryForList(query.toString(maEqMstrPmInsPointListDTO.getIsLoadMaxCount(), maEqMstrPmInsPointListDTO.getFirstRow()));
	}
	private String getWhere(MaEqMstrPmInsDetailDTO maEqMstrPmInsDetailDTO, MaEqMstrPmInsPointListDTO maEqMstrPmInsPointListDTO, User user)
    {
		QueryBuffer query = new QueryBuffer();
		
		query.getAndQuery("x.comp_no", user.getCompNo());
		query.getAndQuery("x.pm_id", maEqMstrPmInsDetailDTO.getPmId());
		query.getAndQuery("x.is_deleted", "N");
		
		if(!"".equals(maEqMstrPmInsPointListDTO.getPmPointId())){
			query.getAndQuery("x.pm_point_id", maEqMstrPmInsPointListDTO.getPmPointId());
			return query.toString();
		}
		
		return query.toString();
    }
	@Override
	public int deleteEqPmInsPointList(String pmId, String pmPointId, User user)
			throws Exception {
		
		QueryBuffer query = new QueryBuffer();
		
		query.append("UPDATE TAPMPOINT 				");
		query.append("SET 	is_deleted 		= ?		");
		query.append("		,delete_by 		= ?		");
		query.append("		,delete_time 	= ?		");
		query.append("WHERE 1=1						");
		query.append("AND comp_no 		= ?			");
		query.append("AND pm_id 		= ?			");
		query.append("AND pm_point_id 	= ?			");
		Object[] objects = new Object[]{
			"Y"
			,user.getUserId()
			,DateUtil.getDateTime()
			,user.getCompNo()
			,pmId
			,pmPointId
		};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
	}

	@Override
	public String findTotalCount(MaEqMstrPmInsDetailDTO maEqMstrPmInsDetailDTO, MaEqMstrPmInsPointListDTO maEqMstrPmInsPointListDTO, User user) throws Exception {
		QueryBuffer query = new QueryBuffer();
		
		query.append("SELECT COUNT(*)					");
		query.append("FROM TAPMPOINT x					");
		query.append("WHERE 1=1							");
		query.append(this.getWhere(maEqMstrPmInsDetailDTO, maEqMstrPmInsPointListDTO, user));
        
		List resultList=  getJdbcTemplate().queryForList(query.toString());
		return QueryBuffer.listToString(resultList);
	}
}