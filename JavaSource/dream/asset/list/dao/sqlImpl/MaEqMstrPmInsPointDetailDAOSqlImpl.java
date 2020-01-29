package dream.asset.list.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.asset.list.dao.MaEqMstrPmInsPointDetailDAO;
import dream.asset.list.dto.MaEqMstrPmInsDetailDTO;
import dream.asset.list.dto.MaEqMstrPmInsPointDetailDTO;
import dream.asset.list.dto.MaEqMstrPmInsPointListDTO;

/**
 * 설비 예방점검 항목 상세 dao
 * @author  kim21017
 * @version $Id: MaEqMstrPmInsPointDetailDAOSqlImpl.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maEqMstrPmInsPointDetailDAOTarget"
 * @spring.txbn id="maEqMstrPmInsPointDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaEqMstrPmInsPointDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaEqMstrPmInsPointDetailDAO
{

	@Override
	public MaEqMstrPmInsPointDetailDTO findDetail(MaEqMstrPmInsDetailDTO maEqMstrPmInsDetailDTO,
			MaEqMstrPmInsPointListDTO maEqMstrPmInsPointListDTO, User user) throws Exception {
		
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("SELECT 											");
		query.append("		x.pm_id AS pmId								");
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
		query.append("		,dbo.SFACODE_TO_DESC(x.check_type,'CHECK_TYPE','SYS','','"+user.getLangId()+"') AS checkTypeDesc	");
		query.append("		,x.uom AS uom								");
		query.append("		,x.is_active AS isActiveId					");
		query.append("		,dbo.SFACODE_TO_DESC(x.is_active,'IS_USE','SYS','','"+user.getLangId()+"') AS isActiveDesc	");
		query.append("		,x.remark AS remark							");
		query.append("FROM TAPMPOINT x									");
		query.append("WHERE 1=1											");
		query.getAndQuery("x.comp_no", user.getCompNo());
		query.getAndQuery("x.pm_id", maEqMstrPmInsDetailDTO.getPmId());
		query.getAndQuery("x.pm_point_id", maEqMstrPmInsPointListDTO.getPmPointId());
		
		MaEqMstrPmInsPointDetailDTO maEqMstrPmInsPointDetailDTO = 
				(MaEqMstrPmInsPointDetailDTO)this.getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaEqMstrPmInsPointDetailDTO()));
		
		return maEqMstrPmInsPointDetailDTO;
	}

}