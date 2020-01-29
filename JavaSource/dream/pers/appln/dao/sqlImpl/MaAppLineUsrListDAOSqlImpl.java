package dream.pers.appln.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.pers.appln.dao.MaAppLineUsrListDAO;
import dream.pers.appln.dto.MaAppLineCommonDTO;
import dream.pers.appln.dto.MaAppLineUsrListDTO;

/**
 * ¸ñ·Ï dao
 * @author  kim21017
 * @version $Id: MaAppLineUsrListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maAppLineUsrListDAOTarget"
 * @spring.txbn id="maAppLineUsrListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaAppLineUsrListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaAppLineUsrListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaAppLineUsrListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maAppLineCommonDTO
     * @param maAppLineUsrListDTO
     * @return List
     */
    public List findAnsList(MaAppLineCommonDTO maAppLineCommonDTO, MaAppLineUsrListDTO maAppLineUsrListDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT                                         ");
        query.append("        '' seqNo                               ");
        query.append("        ,'' isDelCheck                          ");
        query.append("        ,x.appr_seq apprSeq                     ");
        query.append("        ,x.appr_by apprBy                       ");
        query.append("        ,y.emp_name apprByName                  ");
        query.append("        ,y.grade                                ");
        query.append("        ,(SELECT description                     ");
        query.append("          FROM   TADEPT                          ");
        query.append("          WHERE  dept_id = y.dept_id) deptName   ");
        query.append("        ,x.apprlineusr_id apprlineusrId         ");
        query.append("        ,x.apprline_id apprlineId               ");
        query.append("        ,x.apprusr_type apprUsrTypeId             ");
        query.append("        ,dbo.SFACODE_TO_DESC(x.apprusr_type,'APPRUSR_TYPE','SYS','','"+maAppLineCommonDTO.getUserLang()+"') apprusrType           ");
        query.append("FROM TAAPPRLINEUSR x, TAEMP y                  ");
        query.append("WHERE x.appr_by = y.emp_id                     ");
        query.append(this.getWhere(maAppLineCommonDTO,maAppLineUsrListDTO, user));
        query.getOrderByQuery("x.apprlineusr_id","x.appr_seq", maAppLineUsrListDTO.getOrderBy(), maAppLineUsrListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maAppLineUsrListDTO.getIsLoadMaxCount(), maAppLineUsrListDTO.getFirstRow()));

    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaAppLineUsrListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRow
     * @return
     */
    public int deleteAnsList(String deleteRow)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	String question_id=deleteRow;

    	query.append("DELETE FROM TAAPPRLINEUSR						");
    	query.append("WHERE apprlineusr_id 	= '"+question_id+"'	");
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    private String getWhere(MaAppLineCommonDTO maAppLineCommonDTO, MaAppLineUsrListDTO maAppLineUsrListDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	query.getAndQuery("x.comp_no", maAppLineCommonDTO.getCompNo());
    	query.getAndQuery("x.apprline_id", maAppLineCommonDTO.getApprlineId());
    	if (!"".equals(maAppLineUsrListDTO.getApprlineusrId()))
        {
            query.getAndQuery("x.apprlineusr_id", maAppLineUsrListDTO.getApprlineusrId());
            return query.toString();
        }
    	
    	return query.toString();
    }

	@Override
	public String findTotalCount(MaAppLineCommonDTO maAppLineCommonDTO, MaAppLineUsrListDTO maAppLineUsrListDTO,
			User user) throws Exception {

		QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT                            ");
        query.append("       COUNT(1)                   ");
        query.append("FROM TAAPPRLINEUSR x, TAEMP y		");
        query.append("WHERE x.appr_by = y.emp_id        ");
        query.append(this.getWhere(maAppLineCommonDTO,maAppLineUsrListDTO, user));
	
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);

	}
}