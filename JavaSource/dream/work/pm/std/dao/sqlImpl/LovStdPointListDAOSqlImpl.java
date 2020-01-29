package dream.work.pm.std.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.work.pm.std.dao.LovStdPointListDAO;
import dream.work.pm.std.dto.LovStdPointListDTO;

/**
 * 표준항목선택 팝업
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="lovStdPointListDAOTarget"
 * @spring.txbn id="lovStdPointListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovStdPointListDAOSqlImpl extends BaseJdbcDaoSupportSql implements LovStdPointListDAO
{
    /**
     * 항목 검색
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovStdPointListDTO
     * @param loginUser
     * @return
     */
    public List findStdPointList(LovStdPointListDTO lovStdPointListDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT																	");
        query.append("		''	 						seqNo,									");
        query.append("		'' 							isDelCheck,								");
        query.append("		x.step_num stepNum,													");
        query.append("		x.check_point 'CHECKPOINT',											");
        query.append("		x.check_method checkMethod,											");
        query.append("		x.fit_basis fitBasis,												");
        query.append("		dbo.SFACODE_TO_DESC(x.check_type,'CHECK_TYPE','SYS','','"+loginUser.getLangId()+"') checkTypeDesc,	");
        query.append("		x.check_min checkMin,												");
        query.append("		x.check_basis_val checkBasisVal,									");
        query.append("		x.check_max checkMax,												");
        query.append("		x.uom uom,															");
        query.append("		x.is_active isActive,												");
        query.append("		x.STWRK_POINT_ID stWrkPointId    									");
        query.append("FROM TASTWRKPOINT x				    									");
        query.append("WHERE 1=1																	");
        query.getLikeQuery("check_point", lovStdPointListDTO.getPointDesc());
        query.append("ORDER BY x.step_num														");
        
        return getJdbcTemplate().queryForList(query.toString());

    }

	public void inputStdPoint(LovStdPointListDTO lovStdPointListDTO, User user) 
	{
		QuerySqlBuffer query = new QuerySqlBuffer();

		query.append("INSERT INTO TAPMPOINT	( comp_no, pm_point_id, pm_id, step_num, eqasmb_id, check_point, check_method, fit_basis, check_type		");
		query.append("                        , check_min, check_basis_val, check_max, uom, is_active, remark, stwrk_point_id ) ");
		query.append("SELECT comp_no               		");
		query.append("       ,NEXT VALUE FOR SQAPM_POINT_ID	");
		query.append("       ,?							");
		query.append("       ,step_num					");
		query.append("       ,eqasmb_id					");
		query.append("       ,check_point				");
		query.append("       ,check_method				");
		query.append("       ,fit_basis					");
		query.append("       ,check_type				");
		query.append("       ,check_min					");
		query.append("       ,check_basis_val			");
		query.append("       ,check_max					");
		query.append("       ,uom						");
		query.append("       ,is_active					");
		query.append("       ,remark					");
		query.append("       ,stwrk_point_id			");
		query.append("FROM   TASTWRKPOINT 				");
		query.append("WHERE  stwrk_point_id = ?		    ");
		query.append("    and comp_no = ?		        ");
		
		Object[] objects = new Object[] {
				lovStdPointListDTO.getPmId(),
				lovStdPointListDTO.getStWrkPointId(),
				user.getCompNo()
    	};
		
    	this.getJdbcTemplate().update(query.toString(), objects);
	}

}