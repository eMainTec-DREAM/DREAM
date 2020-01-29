package dream.mgr.usrdata.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.mgr.usrdata.dao.McDataSelectPopupDAO;
import dream.mgr.usrdata.dto.McDataSelectCommonDTO;
import dream.mgr.usrdata.dto.McDataSelectDetailDTO;


/**
 * 메뉴 - 목록 dao
 * @author  kim21017
 * @version $Id: McDataSelectListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="mcDataSelectPopupDAOTarget"
 * @spring.txbn id="mcDataSelectPopupDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class McDataSelectPopupDAOSqlImpl extends BaseJdbcDaoSupportSql implements McDataSelectPopupDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: McDataSelectListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param mcDataSelectCommonDTO
     * @return List
     */
    public List findList(McDataSelectCommonDTO mcDataSelectCommonDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append(mcDataSelectCommonDTO.getScript());
        
        return getJdbcTemplate().queryForList(query.toString(mcDataSelectCommonDTO.getIsLoadMaxCount(), mcDataSelectCommonDTO.getFirstRow()));
    }

	@Override
	public void makeLogForScript(McDataSelectCommonDTO mcDataSelectCommonDTO, McDataSelectDetailDTO detailDTO,String errMsg) {
		// TODO Auto-generated method stub
		
	}
	
    /**
     * find count
     * @author  js.lee
     * @param mcDataSelectCommonDTO
     * @return List
     */
    public String findTotalCount(McDataSelectCommonDTO mcDataSelectCommonDTO, User user) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;     ");
        query.append("  SELECT count(1)				 						");
        query.append("  FROM (						 						");
        query.append(mcDataSelectCommonDTO.getScript());
        query.append("  ) AS sub							 				");
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }

    @Override
    public McDataSelectCommonDTO findScript(McDataSelectCommonDTO mcDataSelectCommonDTO, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       title title,                                       ");
        query.append("       script script,                                     ");
        query.append("       usrdata_id usrrptId                                ");
        query.append("FROM   TAUSRDATA x                                        ");
        query.append("WHERE  1 = 1                                              ");
        query.getAndQuery("x.comp_no", user.getCompNo());
        query.getAndQuery("x.usrdata_id", mcDataSelectCommonDTO.getUsrrptId());
        
        mcDataSelectCommonDTO = 
                (McDataSelectCommonDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new McDataSelectCommonDTO()));
        
        return mcDataSelectCommonDTO;
    }
	
	
}