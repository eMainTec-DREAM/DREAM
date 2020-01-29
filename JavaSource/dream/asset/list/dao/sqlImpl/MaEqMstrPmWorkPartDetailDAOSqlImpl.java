package dream.asset.list.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.asset.list.dao.MaEqMstrPmWorkPartDetailDAO;
import dream.asset.list.dto.MaEqMstrPmWorkDetailDTO;
import dream.asset.list.dto.MaEqMstrPmWorkPartDetailDTO;
import dream.asset.list.dto.MaEqMstrPmWorkPartListDTO;

/**
 * 설비 예방작업 부품 상세 dao
 * @author  kim21017
 * @version $Id: MaEqMstrPmWorkPartDetailDAOSqlImpl.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maEqMstrPmWorkPartDetailDAOTarget"
 * @spring.txbn id="maEqMstrPmWorkPartDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaEqMstrPmWorkPartDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaEqMstrPmWorkPartDetailDAO
{

	@Override
	public MaEqMstrPmWorkPartDetailDTO findDetail(MaEqMstrPmWorkDetailDTO maEqMstrPmWorkDetailDTO,
			MaEqMstrPmWorkPartListDTO maEqMstrPmWorkPartListDTO, User user) throws Exception {
		
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("SELECT 											");
		query.append("		x.pm_id AS pmId								");
		query.append("		,x.pm_part_id AS pmPartId					");
		query.append("		,x.part_id AS partId						");
		query.append("		,y.part_no AS partNo						");
		query.append("		,y.description AS partDesc					");
		query.append("		,y.pt_size AS partSize						");
		query.append("		,x.use_qty AS useQty						");
		query.append("FROM TAPMPART x INNER JOIN TAPARTS y				");
		query.append("ON x.comp_no = y.comp_no							");
		query.append("AND x.part_id = y.part_id							");
		query.append("WHERE 1=1											");
		query.getAndQuery("x.comp_no", user.getCompNo());
		query.getAndQuery("x.pm_id", maEqMstrPmWorkDetailDTO.getPmId());
		query.getAndQuery("x.pm_part_id", maEqMstrPmWorkPartListDTO.getPmPartId());
		
		MaEqMstrPmWorkPartDetailDTO maEqMstrPmWorkPartDetailDTO = 
				(MaEqMstrPmWorkPartDetailDTO)this.getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaEqMstrPmWorkPartDetailDTO()));
		
		return maEqMstrPmWorkPartDetailDTO;
	}

}